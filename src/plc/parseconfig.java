package plc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.LinkedList;



public class parseconfig{
	List<plc> plclist;
	
	public static void main(String[] args)
	{
		parseconfig parser = new parseconfig();
		
		int state = 0;
		int pos;
		String line;
		
		String id = null;
		String ipaddr = null;
		String slot = null;
		String port = null;
		
		String attribute;
		String value;
		
		plc newplc;		
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("plcs.config"));
			
			while ((line = reader.readLine()) != null)
			{
				line = line.trim();
				System.out.print(line);
				System.out.print("\n");
				
				if(state == 0)
				{
					
					if((pos = line.indexOf('{')) >= 0)
					{
						System.out.println("processing new inverter");
						state = 1;		
						id = ipaddr = slot = port = null;
					}
				}
				else if(state == 1)
				{
					if((pos = line.indexOf('{')) >= 0)
					{
						
						throw new Exception("unexpected beginning of new inverter");
					}
					
					System.out.println("looking for attributes");
					
					String[] attvalpairs = line.split(",");
					for(int i = 0; i < attvalpairs.length; i++)
					{
						String attvalpair = attvalpairs[i];
						String[] attval = attvalpair.split(":");
						
						if(attval.length != 2)
						{
							throw new Exception("attribute and value are not paired correctly");
						}
						
						String attr = attval[0];
						String val = attval[1];
						
						attr = attr.replaceAll("\"","");	
						val = val.replaceAll("\"","");
						
						//System.out.println(attr);
						
						if(attr.matches("identifier"))
						{
							id = val;
						}
						else if(attr.matches("ipaddr"))
						{
							ipaddr = val;
						}
						else if(attr.matches("slot"))
						{
							slot = val;
						}
						else if(attr.matches("port"))
						{
							port = val;
						}
						else
						{
							System.out.printf("unexpected attribute: %s",attr);
							throw new Exception("unexpected attribute");
						}
						
					}
					
					//all fields have been read, we are waiting for a closing curly bracket
					if(id != null && ipaddr != null && slot != null && port != null)
					{
						state = 2;
					}
				}
				else if(state == 2)
				{
					//shouldn't be an open bracket inside another
					if((pos = line.indexOf('{')) >= 0)
					{
						throw new Exception("unexpected beginning of new inverter");
					}
								
					
					
					if(line.indexOf('}') >= 0)
					{
						System.out.println("finished processing inverter");
						newplc = new plc(id,ipaddr,slot,port);
						parser.plclist.add(newplc);
						
						state = 0;
					}
				}
				else
				{
					break;
				}
				
			}
			reader.close();
			
			// did we reach EOF while still trying to read inverter properties?
			if(state != 0)
			{
				throw new Exception("not expecting EOF");
			}
			
			parser.echolist();
			
		}
		catch(Exception e)
		{
			System.err.format("Problem trying to read config");
			e.printStackTrace();
		}
	}
	
	public parseconfig()
	{
		this.plclist = new LinkedList<plc>();
	}
	
	public void readconfigs()
	{
		
		int state = 0;
		int pos;
		String line;
		
		String id = null;
		String ipaddr = null;
		String slot = null;
		String port = null;
		
		String attribute;
		String value;
		
		plc newplc;		
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("plcs.config"));
			
			while ((line = reader.readLine()) != null)
			{
				line = line.trim();
				System.out.print(line);
				System.out.print("\n");
				
				if(state == 0)
				{
					
					if((pos = line.indexOf('{')) >= 0)
					{
						System.out.println("processing new inverter");
						state = 1;		
						id = ipaddr = slot = port = null;
					}
				}
				else if(state == 1)
				{
					if((pos = line.indexOf('{')) >= 0)
					{
						
						throw new Exception("unexpected beginning of new inverter");
					}
					
					System.out.println("looking for attributes");
					
					String[] attvalpairs = line.split(",");
					for(int i = 0; i < attvalpairs.length; i++)
					{
						String attvalpair = attvalpairs[i];
						String[] attval = attvalpair.split(":");
						
						if(attval.length != 2)
						{
							throw new Exception("attribute and value are not paired correctly");
						}
						
						String attr = attval[0];
						String val = attval[1];
						
						attr = attr.replaceAll("\"","");	
						val = val.replaceAll("\"","");
						
						//System.out.println(attr);
						
						if(attr.matches("identifier"))
						{
							id = val;
						}
						else if(attr.matches("ipaddr"))
						{
							ipaddr = val;
						}
						else if(attr.matches("slot"))
						{
							slot = val;
						}
						else if(attr.matches("port"))
						{
							port = val;
						}
						else
						{
							System.out.printf("unexpected attribute: %s",attr);
							throw new Exception("unexpected attribute");
						}
						
					}
					
					//all fields have been read, we are waiting for a closing curly bracket
					if(id != null && ipaddr != null && slot != null && port != null)
					{
						state = 2;
					}
				}
				else if(state == 2)
				{
					//shouldn't be an open bracket inside another
					if((pos = line.indexOf('{')) >= 0)
					{
						throw new Exception("unexpected beginning of new inverter");
					}
								
					
					
					if(line.indexOf('}') >= 0)
					{
						System.out.println("finished processing inverter");
						newplc = new plc(id,ipaddr,slot,port);
						plclist.add(newplc);
						
						state = 0;
					}
				}
				else
				{
					break;
				}
				
			}
			reader.close();
			
			// did we reach EOF while still trying to read inverter properties?
			if(state != 0)
			{
				throw new Exception("not expecting EOF");
			}
			
			echolist();
			
		}
		catch(Exception e)
		{
			System.err.format("Problem trying to read config");
			e.printStackTrace();
		}
	}
	
	
	public void echolist()
	{
		System.out.printf("printing command line arguments for all %d inverters in list \n",plclist.size());
		
		for(int i = 0; i < plclist.size(); i++)
		{
			String[] retvals = plclist.get(i).getargs();
			
			
			for(int j = 0; j < retvals.length; j++)
			{
				System.out.println(retvals[j]);
			}
		}
	}
	
	public void makeWrappers()
	{
		for(int i = 0; i < plclist.size(); i ++)
		{
			plclist.get(i).makeWrapper();
		}
	}
	
	public plc getplcbyname(String id)
	{
		for(int i = 0; i < plclist.size(); i++)
		{
			if(id.matches(plclist.get(i).identifier))
			{
				return plclist.get(i);
			}
			
		}
		return null;
	}

	
	
	
}