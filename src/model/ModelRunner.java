package model;


//runs a dcmg model in a new thread
public class ModelRunner extends Thread {
	public DCMGmodel model;
	public int interval;
	
	//runs the model model every interval milliseconds
	public ModelRunner(DCMGmodel model, int interval){
		this.model = model;
		
		if(interval < 10){
			this.interval = 10;
		}
		else{
			this.interval = interval;
		}
		
	}
	
	public void run(){
		while(true){
			try{
				synchronized(model){
					//long now = System.currentTimeMillis();
					model.solvemodel(false);	
					//System.out.println(now);
				}
				
				Thread.sleep(interval);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}

	}
	
}
