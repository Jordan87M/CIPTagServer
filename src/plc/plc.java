package plc;
import com.ab.gti.Wrapper;
	

public class plc{
	String identifier;
	String ipaddr;
	String slot;
	String port;
	Wrapper wrapper;
	
	public static void main(String[] args)
	{
		plc testplc = new plc("USER","192.168.56.10","4","4001");
		System.out.printf("Created a new PLC object:\n ID: %s \n IP: %s \n SLOT: %s PORT: %s",testplc.identifier, testplc.ipaddr, testplc.slot, testplc.port);
	}
	
	public plc(String id, String ipaddr, String slot, String port)
	{
		this.identifier = id;
		this.ipaddr = ipaddr;
		this.slot = slot;
		this.port = port;
		
		this.wrapper = null;
		//this.wrapper = getWrapper();
	}
	
	public String[] getargs()
	{
		String[] args = {"-jucs", String.format("cip://2:%s/1:%s", this.ipaddr,slot), "-gtiServerPort", port};
		return args;
	}
	
	public Wrapper makeWrapper()
	{
		wrapper = new Wrapper(getargs());
		return wrapper;
	}
	
	public Wrapper getWrapper()
	{
		return wrapper;
	}
}