//import com.ab.gti.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

//import java.io.InputStreamReader;
import model.DCMGmodel;
import model.ModelRunner;

public class ServeModelTags{
	public static void main(String[] args){
		int lpnum = 12897;
		long now = 0;
		int modelinterval = 20;
		long recbytes;
		String line;
		String retval;
		
		String reqlogfname = "requests.log";

		//create model object
		DCMGmodel dcmg = new DCMGmodel();
		ByteBuffer recbuff = ByteBuffer.allocate(8192);
		//ByteBuffer buffer = ByteBuffer.allocate(8192);
		//CharBuffer charBuffer = CharBuffer.allocate(8192);
		
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		
		PrintWriter reqlog = null;
		
		Thread modelthread = null;
		
		try{
			//create log file
			reqlog = new PrintWriter(new FileOutputStream(new File(reqlogfname),false));
			
			//create listening socket
			ServerSocketChannel tserver = ServerSocketChannel.open();
			tserver.socket().bind(new InetSocketAddress(lpnum));
			tserver.configureBlocking(true);
			
			System.out.println("Launching model thread");
			modelthread = new ModelRunner(dcmg, modelinterval);
			modelthread.start();
			
			System.out.println("Should be connected");
			try{
				while(true){
					try{	
						SocketChannel tclient = tserver.accept();						
						//now = System.currentTimeMillis();
						
						if(tclient != null){
							recbuff.position(0);
							recbuff.limit(8192);
							recbytes = tclient.read(recbuff);
							recbuff.flip();
							line = decoder.decode(recbuff).toString();
							
							reqlog.printf("\nFROM %s, RECEIVED NEW MESSAGE: %s\n   AT %d",tclient.socket().getRemoteSocketAddress(), line, System.currentTimeMillis()/1000);
							
							System.out.println(String.format("line: %s", line));
							//System.out.println(recbytes);
							//System.out.println(recbuff);
	
							
							retval = processInput(dcmg, line);
							
							if(retval != null){
								System.out.println(retval);
								reqlog.printf("\nRESPONSE: %s\n     AT %d", retval, System.currentTimeMillis()/1000);
								tclient.write(ByteBuffer.wrap(retval.getBytes()));
							}
							else{
								String nullmsg = "null";
								tclient.write(ByteBuffer.wrap(nullmsg.getBytes()));
								//System.out.println("return value is null");
							}
	
							//System.out.println("closing client socket");
							tclient.close();
						}
					}
					catch(IOException e){
						System.out.println(e);
					}
				}
			}
			catch(Exception e){
				System.out.println("closing server socket");
				if(tserver != null){
					tserver.close();
				}
			}
		}	
		catch(IOException e){
			System.out.println(e);
		}
		catch(Exception E){
			System.out.println(E);
		}
		finally{
			dcmg.cleanup();
			reqlog.close();
		}
	}

	public static String processInput(DCMGmodel dcmg, String req){
		try{
			//System.out.println("processInput() called");
			String[] items = req.split(" ");
			String method = items[0].trim();
			String plc = items[1].trim();
			
			String retval;
			
			if(plc.equals("SG")){
				//retval = processMore(SG,method,items);
				retval = processMore(dcmg,plc,method,items);
				return retval;
			}
			else if(plc.equals("user")){
				//retval = processMore(user,method,items);
				retval = processMore(dcmg,plc,method,items);
				return retval;
			}
			else{
				System.out.println("invalid PLC type");
				return null;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		System.out.println("got to end of processInput()");
		return null;
	}
	
	public static String processMore(DCMGmodel dcmg, String plc, String method, String[] items){
		String retval;
		//System.out.println("processMore() called");
		if(method.equals("read")){
			//retval = handleRead(w,items);
			retval = handleRead(dcmg,plc,items);
			return retval;
		}
		else if(method.equals("write")){
			//retval = handleWrite(w,items);
			retval = handleWrite(dcmg,plc,items);
			return retval;
		}
		else{
			System.out.println("invalid method type");
			return null;
		}
		
	}
	
	public static String handleWrite(DCMGmodel dcmg, String plc, String[] items){
		try{
			//System.out.println("handleWrite() called");
			List<String> tagNames = new ArrayList<String>();
			List<Object> tagValues = new ArrayList<Object>();

			for(int i = 2; i < items.length; i++){
				String[] parts = items[i].split(":");
				//System.out.print(String.format("parts: %s , %s",parts[0], parts[1]));
				tagNames.add(parts[0].trim());
				if(parts[1].trim().equals("true") || parts[1].trim().equals("True")){
					//System.out.println("we're supposed to write true");
					tagValues.add(new Boolean(true));
				}
				else if(parts[1].trim().equals("false") || parts[1].trim().equals("False")){
					//System.out.println("we're supposed to write false");
					tagValues.add(new Boolean(false));
				}
				else{
					//System.out.println("we're supposed to write a float");
					tagValues.add(Float.parseFloat(parts[1]));
				}
			}
			//System.out.println("after for loop in write");

			String[] nameArray = new String[tagNames.size()];
			tagNames.toArray(nameArray);
			Object[] valueArray = new Object[tagValues.size()];
			tagValues.toArray(valueArray);
			
			if(nameArray.length == 1){
				String[] parts = items[2].split(":");
				//w.writeTag(parts[0], parts[1]);  //commented for testing
				writeModelTag(dcmg,plc,nameArray[0],valueArray[0]);
				
				//System.out.println(parts[0].toString());
				//System.out.println(parts[1].toString());
			}
			else if(nameArray.length > 1){
				//w.writeTags(nameArray, valueArray); //commented for testing
				writeModelTags(dcmg,plc,nameArray,valueArray);
				
				//System.out.println(nameArray.toString());
				//System.out.println(valueArray.toString());
			}
			else{
				return null;
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}	
		return null;
	}
	
	public static String handleRead(DCMGmodel dcmg, String plc, String[] items){
		try{
			//System.out.println("handleRead() called");
			List<String> tagNames = new ArrayList<String>();
			
			for(int i = 2;i < items.length; i++){
				tagNames.add(items[i].trim());
			}
			String[] nameArray = new String[tagNames.size()];
			tagNames.toArray(nameArray);

			//System.out.print(String.format("%d", nameArray.length));
			if(nameArray.length == 1){
				//Object retval = w.readTag(items[2]); //commented for testing
				//Object retval = new Boolean("true");   //inserted for testing
				Object retval = readModelTag(dcmg,plc,nameArray[0]);
				String output = String.format("%s:%s\n", nameArray[0] ,retval.toString());
				return output;
			}
			else if(nameArray.length > 1){
				//Object[] retval = w.readTags(nameArray); //commented for testing
				//Object[] retval = {new Boolean(false), new Boolean(true)};
				Object[] retval = readModelTags(dcmg,plc,nameArray);
				String output = "";
				for(int j = 0;j < retval.length; j++){					
					output = output.concat(String.format("%s:%s",nameArray[j],retval[j].toString()));
					if(j != retval.length - 1){
						output = output.concat(",");
					}
					//System.out.println(output);
				}
				output = output.concat("\n");
				return output;
			}
			else{
				System.out.println("read returned null :(");
				return null;
			}

		}
		catch(Exception e){
			System.out.println(e);
		}
		//System.out.println("got to end of handle read but shouldn't have");
		return null;
	}
	
	public static Object[] readModelTags(DCMGmodel dcmg, String plc, String[] tags){
		//System.out.println("readModelTags(); called");
		Object[] results = new Object[tags.length];
		Object[] dummyvals = new Object[1];
		
		synchronized(dcmg){
			results = dcmg.fakeinterface(plc, "read",tags,dummyvals);
		}		
		
		return results;
	}
	
	public static Object readModelTag(DCMGmodel dcmg, String plc, String tag){
		//System.out.println("readModelTag() called");
		Object result = new Object();
		Object[] dummyvals = new Object[1];
		String[] tags = new String[1];
		tags[0] = tag;
		
		synchronized(dcmg){
			Object[] results = dcmg.fakeinterface(plc, "read", tags, dummyvals);
			result = results[0];
		}
				
		//System.out.println(result);
		return result;		
	}
	
	public static void writeModelTag(DCMGmodel dcmg, String plc, String tag, Object value){
		//System.out.println("writeModelTag() called");
		Object[] values = new Object[1];
		String[] tags = new String[1];
		values[0] = value;
		tags[0] = tag;
		
		synchronized(dcmg){
			dcmg.fakeinterface(plc, "write", tags, values);
		}		
		
		//System.out.println("writeModelTag() returning");
	}
	
	public static void writeModelTags(DCMGmodel dcmg, String plc, String[] tags, Object[] values){
		//System.out.println("writeModelTags() called");
		synchronized(dcmg){
			dcmg.fakeinterface(plc, "write", tags, values);
		}
		
		//System.out.println("writeModelTags() returning");
	}
	
	

}