package model;
import model.Source;
import model.SolarPanel;
import model.Batt;
import model.Generator;
import model.Modelmath;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

public class DCMGmodel{
	double r;
	double rs;
	double ro;
	double rf;
	double rb;
	
	//load resistances
	double rl111;
	double rl112;
	double rl113;
	double rl121;
	double rl122;
	double rl123;
	double rl211;
	double rl212;
	double rl213;
	double rl221;
	double rl222;
	double rl223;
	
	//convert to conductivities
	double y;
	double ys;
	double yo;
	double yf;
	double yb;

	double yl111;
	double yl112;
	double yl113;
	double yl121;
	double yl122;
	double yl123;
	double yl211;
	double yl212;
	double yl213;
	double yl221;
	double yl222;
	double yl223;
	
	int dim;
			
	int BRANCH_1_BUS_1_PROXIMAL_User;
	int BRANCH_1_BUS_2_PROXIMAL_User;
	int BRANCH_2_BUS_1_PROXIMAL_User; 
	int BRANCH_2_BUS_2_PROXIMAL_User;
	int BRANCH_1_BUS_1_DISTAL_User;
	int BRANCH_1_BUS_2_DISTAL_User;
	int BRANCH_2_BUS_1_DISTAL_User;
	int BRANCH_2_BUS_2_DISTAL_User;

	int BRANCH_2_BUS_1_LOAD_1_User;
	int BRANCH_2_BUS_1_LOAD_2_User;
	int BRANCH_2_BUS_1_LOAD_3_User;
	int BRANCH_2_BUS_2_LOAD_1_User;
	int BRANCH_2_BUS_2_LOAD_2_User;
	int BRANCH_2_BUS_2_LOAD_3_User;
	int BRANCH_1_BUS_1_LOAD_1_User;
	int BRANCH_1_BUS_1_LOAD_2_User;
	int BRANCH_1_BUS_1_LOAD_3_User;
	int BRANCH_1_BUS_2_LOAD_1_User;
	int BRANCH_1_BUS_2_LOAD_2_User;
	int BRANCH_1_BUS_2_LOAD_3_User;

	int CROSSTIE_1_User;
	int CROSSTIE_2_User;

	int SOURCE_1_User;
	int SOURCE_2_User;
	int SOURCE_3_User;
	int SOURCE_4_User;
	int SOURCE_5_User;
	int SOURCE_6_User;
	
	double SOURCE_1_droopCoeff;
	double SOURCE_2_droopCoeff;
	double SOURCE_3_droopCoeff;
	double SOURCE_4_droopCoeff;
	double SOURCE_5_droopCoeff;
	double SOURCE_6_droopCoeff;
	
	double SOURCE_1_noLoadVoltage;
	double SOURCE_2_noLoadVoltage;
	double SOURCE_3_noLoadVoltage;
	double SOURCE_4_noLoadVoltage;
	double SOURCE_5_noLoadVoltage;
	double SOURCE_6_noLoadVoltage;
	
	int SOURCE_1_BatteryReqCharge;
	int SOURCE_2_BatteryReqCharge;
	int SOURCE_3_BatteryReqCharge;
	int SOURCE_4_BatteryReqCharge;
	int SOURCE_5_BatteryReqCharge;
	int SOURCE_6_BatteryReqCharge;
	
	int SOURCE_1_BatteryReqStop;
	int SOURCE_2_BatteryReqStop;
	int SOURCE_3_BatteryReqStop;
	int SOURCE_4_BatteryReqStop;
	int SOURCE_5_BatteryReqStop;
	int SOURCE_6_BatteryReqStop;
	
	double SOURCE_1_psetpoint;
	double SOURCE_2_psetpoint;
	double SOURCE_3_psetpoint;
	double SOURCE_4_psetpoint;
	double SOURCE_5_psetpoint;
	double SOURCE_6_psetpoint;
	
	int SOURCE_1_BATTERY_CHARGE_SELECT;
	int SOURCE_2_BATTERY_CHARGE_SELECT;
	int SOURCE_3_BATTERY_CHARGE_SELECT;
	int SOURCE_4_BATTERY_CHARGE_SELECT;
	int SOURCE_5_BATTERY_CHARGE_SELECT;
	int SOURCE_6_BATTERY_CHARGE_SELECT;
	
	int SOURCE_1_DROOP_SELECT;
	int SOURCE_2_DROOP_SELECT;
	int SOURCE_3_DROOP_SELECT;
	int SOURCE_4_DROOP_SELECT;
	int SOURCE_5_DROOP_SELECT;
	int SOURCE_6_DROOP_SELECT;
	
	Batt src1;
	//Batt src2;
	Generator src2;
	SolarPanel src3;
	SolarPanel src4;
	BattCharger src5;
	//BattCharger src6;
	Generator src6;
		
	int src1loc;
	int src2loc;
	int src3loc;
	int src4loc;
	int src5loc;
	int src6loc;
	
	double src1regv;
	double src2regv;
	double src3regv;
	double src4regv;
	double src5regv;
	double src6regv;
	
	double src1unregv;
	double src2unregv;
	double src3unregv;
	double src4unregv;
	double src5unregv;
	double src6unregv;
	
	
	int BRANCH_1_BUS_1_Fault;
	int BRANCH_1_BUS_2_Fault;
	int BRANCH_2_BUS_1_Fault;
	int BRANCH_2_BUS_2_Fault;
	int CROSSTIE_1_Fault_1;
	int CROSSTIE_1_Fault_2;
	int CROSSTIE_2_Fault_1;
	int CROSSTIE_2_Fault_2;
	int MAIN_BUS_Fault;
	
	double SolarSetpoint1Alias;
	double SolarSetpoint2Alias;
	
	double mbv;
	double b2b1v;
	double b1b1v;
	double b2ic1v;
	double b1ic1v;
	double b2b2v;
	double b1b2v;
	double b2ic2v;
	double b1ic2v;
	double b2b1l1v;
	double b2b1l2v;
	double b2b1l3v;
	double b1b1l1v;
	double b1b1l2v;
	double b1b1l3v;
	double b2b2l1v;
	double b2b2l2v;
	double b2b2l3v;
	double b1b2l1v;
	double b1b2l2v;
	double b1b2l3v;
	//source voltages already defined
	
	double b2b1c;
	double b1b1c;
	double b2b2c;
	double b1b2c;
	double ic1c;
	double ic2c;
	double b2b1l1c;
	double b2b1l2c;
	double b2b1l3c;
	double b1b1l1c;
	double b1b1l2c;
	double b1b1l3c;
	double b2b2l1c;
	double b2b2l2c;
	double b2b2l3c;
	double b1b2l1c;
	double b1b2l2c;
	double b1b2l3c;
	//source currents
	double src1regc;
	double src2regc;
	double src3regc;
	double src4regc;
	double src5regc;
	double src6regc;
	
	double src1unregc;
	double src2unregc;
	double src3unregc;
	double src4unregc;
	double src5unregc;
	double src6unregc;
	
	String logfilename = "modellog.csv";
	PrintWriter log = null;
	String[] signallist =  {"Main Bus Voltage", "Main Branch Voltage","Branch 2 Bus 1 Voltage", "Branch 1 Bus 1 Voltage",
					"Branch 2 CROSSTIE 1 Voltage", "Branch 1 CROSSTIE 1 Voltage", "Branch 2 Bus 2 Voltage", "Branch 1 Bus 2 Voltage",
					"Branch 2 CROSSTIE 2 Voltage", "Branch 1 CROSSTIE 2 Voltage", "Branch 2 Bus 1 Load 1 Voltage", "Branch 2 Bus 1 Load 2 Voltage",
					"Branch 2 Bus 1 Load 3 Voltage", "Branch 1 Bus 1 Load 1 Voltage", "Branch 1 Bus 1 Load 2 Voltage", "Branch 1 Bus 1 Load 3 Voltage", 
					"Branch 2 Bus 2 Load 1 Voltage", "Branch 2 Bus 2 Load 2 Voltage", "Branch 2 Bus 2 Load 3 Voltage", "Branch 1 Bus 2 Load 1 Voltage",
					"Branch 1 Bus 2 Load 2 Voltage", "Branch 1 Bus 2 Load 3 Voltage", "Branch 2 Bus 1 Current", "Branch 1 Bus 1 Current", 
					"CROSSTIE 1 Current", "Branch 2 Bus 2 Current", "Branch 1 Bus 2 Current", "CROSSTIE 2 Current", 
					"Branch 2 Bus 1 Load 1 Current", "Branch 2 Bus 1 Load 2 Current", "Branch 2 Bus 1 Load 3 Current", 
					"Branch 1 Bus 1 Load 1 Current", "Branch 1 Bus 1 Load 2 Current", "Branch 1 Bus 1 Load 3 Current",
					"Branch 2 Bus 2 Load 1 Current", "Branch 2 Bus 2 Load 2 Current", "Branch 2 Bus 2 Load 3 Current", 
					"Branch 1 Bus 2 Load 1 Current", "Branch 1 Bus 2 Load 2 Current", "Branch 1 Bus 2 Load 3 Current",
					"Source 1 Current", "Source 2 Current", "Source 3 Current", "Source 4 Current", "Source 5 Current", "Source 6 Current"};
	
	public static void main(String[] args){		
		DCMGmodel dcmg = new DCMGmodel();
		
		dcmg.solvemodel(true);
		
	}
	
	public DCMGmodel(){
		this.r = .1;
		this.rs = .05;
		this.ro = .1;
		this.rf = 5;
		this.rb = .05;
		
		//load resistances
		this.rl111 = 48;
		this.rl112 = 48;
		this.rl113 = 48;
		this.rl121 = 48;
		this.rl122 = 48;
		this.rl123 = 48;
		this.rl211 = 48;
		this.rl212 = 48;
		this.rl213 = 48;
		this.rl221 = 48;
		this.rl222 = 48;
		this.rl223 = 48;
		
		//convert to conductivities
		this.y = 1/r;
		this.ys = 1/rs;
		this.yo = 1/ro;
		this.yf = 1/rf;
		this.yb = 1/rb;
	
		this.yl111 = 1/rl111;
		this.yl112 = 1/rl112;
		this.yl113 = 1/rl113;
		this.yl121 = 1/rl121;
		this.yl122 = 1/rl122;
		this.yl123 = 1/rl123;
		this.yl211 = 1/rl211;
		this.yl212 = 1/rl212;
		this.yl213 = 1/rl213;
		this.yl221 = 1/rl221;
		this.yl222 = 1/rl222;
		this.yl223 = 1/rl223;
		
		this.dim = 23;
				
		this.BRANCH_1_BUS_1_PROXIMAL_User = 1;
		this.BRANCH_1_BUS_2_PROXIMAL_User = 1;
		this.BRANCH_2_BUS_1_PROXIMAL_User = 1;
		this.BRANCH_2_BUS_2_PROXIMAL_User = 1;
		this.BRANCH_1_BUS_1_DISTAL_User = 1;
		this.BRANCH_1_BUS_2_DISTAL_User = 1;
		this.BRANCH_2_BUS_1_DISTAL_User = 1;
		this.BRANCH_2_BUS_2_DISTAL_User = 1;

		this.BRANCH_2_BUS_1_LOAD_1_User = 0;
		this.BRANCH_2_BUS_1_LOAD_2_User = 0;
		this.BRANCH_2_BUS_1_LOAD_3_User = 0;
		this.BRANCH_2_BUS_2_LOAD_1_User = 0;
		this.BRANCH_2_BUS_2_LOAD_2_User = 0;
		this.BRANCH_2_BUS_2_LOAD_3_User = 0;
		this.BRANCH_1_BUS_1_LOAD_1_User = 0;
		this.BRANCH_1_BUS_1_LOAD_2_User = 0;
		this.BRANCH_1_BUS_1_LOAD_3_User = 0;
		this.BRANCH_1_BUS_2_LOAD_1_User = 0;
		this.BRANCH_1_BUS_2_LOAD_2_User = 0;
		this.BRANCH_1_BUS_2_LOAD_3_User = 0;

		this.CROSSTIE_1_User = 0;
		this.CROSSTIE_2_User = 0;

		this.SOURCE_1_User = 0;
		this.SOURCE_2_User = 0;
		this.SOURCE_3_User = 0;
		this.SOURCE_4_User = 0;
		this.SOURCE_5_User = 0;
		this.SOURCE_6_User = 0;
		
		this.SOURCE_1_droopCoeff = 10;
		this.SOURCE_2_droopCoeff = 10;
		this.SOURCE_3_droopCoeff = 10;
		this.SOURCE_4_droopCoeff = 10;
		this.SOURCE_5_droopCoeff = 10;
		this.SOURCE_6_droopCoeff = 10;
		
		this.SOURCE_1_noLoadVoltage = 12;
		this.SOURCE_2_noLoadVoltage = 12;
		this.SOURCE_3_noLoadVoltage = 12;
		this.SOURCE_4_noLoadVoltage = 12;
		this.SOURCE_5_noLoadVoltage = 12;
		this.SOURCE_6_noLoadVoltage = 12;
		
		this.SOURCE_1_BatteryReqCharge = 0;
		this.SOURCE_2_BatteryReqCharge = 0;
		this.SOURCE_3_BatteryReqCharge = 0;
		this.SOURCE_4_BatteryReqCharge = 0;
		this.SOURCE_5_BatteryReqCharge = 0;
		this.SOURCE_6_BatteryReqCharge = 0;
		
		this.SOURCE_1_BatteryReqStop = 0;
		this.SOURCE_2_BatteryReqStop = 0;
		this.SOURCE_3_BatteryReqStop = 0;
		this.SOURCE_4_BatteryReqStop = 0;
		this.SOURCE_5_BatteryReqStop = 0;
		this.SOURCE_6_BatteryReqStop = 0;
		
		this.SOURCE_1_psetpoint = 0;
		this.SOURCE_2_psetpoint = 0;
		this.SOURCE_3_psetpoint = 0;
		this.SOURCE_4_psetpoint = 0;
		this.SOURCE_5_psetpoint = 0;
		this.SOURCE_6_psetpoint = 0;
		
		this.SOURCE_1_BATTERY_CHARGE_SELECT = 0;
		this.SOURCE_2_BATTERY_CHARGE_SELECT = 0;
		this.SOURCE_3_BATTERY_CHARGE_SELECT = 0;
		this.SOURCE_4_BATTERY_CHARGE_SELECT = 0;
		this.SOURCE_5_BATTERY_CHARGE_SELECT = 0;
		this.SOURCE_6_BATTERY_CHARGE_SELECT = 0;
		
		this.SOURCE_1_DROOP_SELECT = 1;
		this.SOURCE_2_DROOP_SELECT = 1;
		this.SOURCE_3_DROOP_SELECT = 1;
		this.SOURCE_4_DROOP_SELECT = 1;
		this.SOURCE_5_DROOP_SELECT = 1;
		this.SOURCE_6_DROOP_SELECT = 1;
		
		this.src1 = new Batt(10.0,12.0,1.0);
		//this.src2 = new Batt(10.0,12.0,1.0);
		this.src2 = new Generator(10.0,12.0,4.0);
		this.src3 = new SolarPanel(10.0,12.0,100);
		this.src4 = new SolarPanel(10.0,12.0,100);
		this.src5 = new BattCharger(this.src1);
		//this.src6 = new BattCharger(this.src2);
		this.src6 = new Generator(10.0,12.0,4.0);
		
		
		this.src1loc = 4;	//supposed to be br1b1 (Crunderson)
		this.src2loc = 1;   //supposed to be main  (Enercon)
		this.src3loc = 4;	//supposed to be br1b1 (Robeski)
		this.src4loc = 3;	//supposed to be br2b1 (Enercon)
		this.src5loc = 4;
		this.src6loc = 1;
		
		this.src1regv = 12;
		this.src2regv = 12;
		this.src3regv = 12;
		this.src4regv = 12;
		this.src5regv = 12;
		this.src6regv = 12;
		
		this.src1unregv = 12;
		this.src2unregv = 12;
		this.src3unregv = 12;
		this.src4unregv = 12;
		this.src5unregv = 12;
		this.src6unregv = 12;
		
		this.src1regc = 0;
		this.src2regc = 0;
		this.src3regc = 0;
		this.src4regc = 0;
		this.src5regc = 0;
		this.src6regc = 0;
		
		this.src1unregc = 0;
		this.src2unregc = 0;
		this.src3unregc = 0;
		this.src4unregc = 0;
		this.src5unregc = 0;
		this.src6unregc = 0;
		
		this.BRANCH_1_BUS_1_Fault = 0;
		this.BRANCH_1_BUS_2_Fault = 0;
		this.BRANCH_2_BUS_1_Fault = 0;
		this.BRANCH_2_BUS_2_Fault = 0;
		this.CROSSTIE_1_Fault_1 = 0;
		this.CROSSTIE_1_Fault_2 = 0;
		this.CROSSTIE_2_Fault_1 = 0;
		this.CROSSTIE_2_Fault_2 = 0;
		this.MAIN_BUS_Fault = 0;
		
		this.SolarSetpoint1Alias = 10.0;
		this.SolarSetpoint2Alias = 10.0;
		
		//print signal names for csv log file
		try{
			System.out.println("creating model log file");
			this.log = new PrintWriter(new FileOutputStream(new File(this.logfilename),true));
			for(int i = 0; i < this.signallist.length; i++)
			{
				if(i < this.signallist.length-1)
				{
					log.printf("%s,",this.signallist[i]);
				}
				else
				{
					log.printf("%s\n", this.signallist[i]);
				}
			}	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void cleanup(){
		System.out.println("Closing log file");
		this.log.close();
	}
	
	public void solvemodel(boolean debugging){
		//size matrix based on number of sources connected
		dim = 23 + SOURCE_1_User + SOURCE_2_User + SOURCE_3_User + SOURCE_4_User + SOURCE_5_User + SOURCE_6_User;
		double[][] Y = new double[dim][dim];
		double[][] Yd = new double[dim][dim];
		double[] U = new double[dim];
		double[] K = new double[dim];
		double[] Kd = new double[dim];
		
		double[] gencurrents = new double[6];
		double[] busvoltages = new double[23];
		
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));

		//build admittance matrix
		Y[1][2] = -y;

		Y[2][3] = -(1/(2*r+rs))*BRANCH_2_BUS_1_PROXIMAL_User;
		Y[2][4] = -(1/(2*r+rs))*BRANCH_1_BUS_1_PROXIMAL_User;
		Y[2][0] = -yf * MAIN_BUS_Fault;

		Y[3][5] =  -yb * BRANCH_2_BUS_1_DISTAL_User;
		Y[3][11] = -ys * BRANCH_2_BUS_1_LOAD_1_User;
		Y[3][12] = -ys * BRANCH_2_BUS_1_LOAD_2_User;
		Y[3][13] = -ys * BRANCH_2_BUS_1_LOAD_3_User;
		Y[3][0] = -yf * BRANCH_2_BUS_1_Fault;

		Y[4][6] = -yb * BRANCH_1_BUS_1_DISTAL_User;
		Y[4][14] = -ys * BRANCH_1_BUS_1_LOAD_1_User;
		Y[4][15] = -ys * BRANCH_1_BUS_1_LOAD_1_User;
		Y[4][16] = -ys * BRANCH_1_BUS_1_LOAD_1_User;
		Y[4][0] = -yf * BRANCH_1_BUS_1_Fault;

		Y[5][6] = -(1/(r+rs))*CROSSTIE_1_User;
		Y[5][7] = -(1/(r+rs))*BRANCH_2_BUS_2_PROXIMAL_User;
		Y[5][0] = -yf * CROSSTIE_1_Fault_1;


		Y[6][8] = -(1/(r+rs))*BRANCH_1_BUS_2_PROXIMAL_User;
		Y[6][0] = -yf*CROSSTIE_1_Fault_2;

		Y[7][9] = -yb * BRANCH_2_BUS_2_DISTAL_User;
		Y[7][17] = -ys * BRANCH_2_BUS_2_LOAD_1_User;
		Y[7][18] = -ys * BRANCH_2_BUS_2_LOAD_2_User;
		Y[7][19] = -ys * BRANCH_2_BUS_2_LOAD_3_User;
		Y[7][0] = -yf * BRANCH_2_BUS_2_Fault;

		Y[8][10] = -yb * BRANCH_1_BUS_2_DISTAL_User;
		Y[8][20] = -ys * BRANCH_1_BUS_2_LOAD_1_User;
		Y[8][21] = -ys * BRANCH_1_BUS_2_LOAD_2_User;
		Y[8][22] = -ys * BRANCH_1_BUS_2_LOAD_3_User;
		Y[8][0] = -yf * BRANCH_1_BUS_2_Fault;

		Y[9][10] = -(1/(r + rs))* CROSSTIE_2_User;
		Y[9][0] = -yf * CROSSTIE_2_Fault_1;

		Y[10][0] = -yf * CROSSTIE_2_Fault_2;

		Y[11][0] = -yl211;

		Y[12][0] = -yl212;

		Y[13][0] = -yl213;

		Y[14][0] = -yl111;

		Y[15][0] = -yl112;

		Y[16][0] = -yl113;

		Y[17][0] = -yl221;

		Y[18][0] = -yl222;

		Y[19][0] = -yl223;

		Y[20][0] = -yl121;

		Y[21][0] = -yl122;

		Y[22][0] = -yl123;


		//attach sources
		int connindex = 23;
		int SOURCE_1_connindex = -1;
		int SOURCE_2_connindex = -1;
		int SOURCE_3_connindex = -1;
		int SOURCE_4_connindex = -1;
		int SOURCE_5_connindex = -1;
		int SOURCE_6_connindex = -1;
		
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));

		
		if(SOURCE_1_User == 1){
			Y[src1loc][connindex] = 1;	
			SOURCE_1_connindex = connindex;
			connindex++;
			
		}
		if(SOURCE_2_User == 1){
			Y[src2loc][connindex] = 1;	
			SOURCE_2_connindex = connindex;
			connindex++;
		}
		if(SOURCE_3_User == 1){
			Y[src3loc][connindex] = 1;	
			SOURCE_3_connindex = connindex;
			connindex++;
		}
		if(SOURCE_4_User == 1){
			Y[src4loc][connindex] = 1;
			SOURCE_4_connindex = connindex;
			connindex++;
		}
		if(SOURCE_5_User == 1){
			Y[src5loc][connindex] = 1;	
			SOURCE_5_connindex = connindex;
			connindex++;
		}
		if(SOURCE_6_User == 1){
			Y[src6loc][connindex] = 1;	
			SOURCE_6_connindex = connindex;
			connindex++;
		}
		
		
		//make the matrix symmetric
		for(int i= 1;i<dim;i++){
			for(int j = i+1;j<dim;j++){
				Y[j][i] = Y[i][j];
			}
		}
		
		//sum elements of rows and negate to find diagonal
		for(int i = 0;i<23;i++){
			double sum = 0;
			for(int j = 0;j<23;j++){
				sum += Y[i][j];
			}
			Y[i][i] = -sum;
		}
		//enforce ground voltage = 0
		Y[0][0] = 1;		
		
		for(int i = 0;i<K.length;i++){
			if(i<23){
				K[i] = 0;
			}
			if(i == SOURCE_1_connindex){
				if(SOURCE_1_BATTERY_CHARGE_SELECT == 1){
					K[i] = src1unregv;
				}
				else{
					K[i] = src1regv;
				}				
			}
			else if(i == SOURCE_2_connindex){
				if(SOURCE_2_BATTERY_CHARGE_SELECT == 1){
					K[i] = src2unregv;
				}
				else{
					K[i] = src2regv;
				}			}
			else if(i == SOURCE_3_connindex){
				if(SOURCE_3_BATTERY_CHARGE_SELECT == 1){
					K[i] = src3unregv;
				}
				else{
					K[i] = src3regv;
				}			}
			else if(i == SOURCE_4_connindex){
				if(SOURCE_4_BATTERY_CHARGE_SELECT == 1){
					K[i] = src4unregv;
				}
				else{
					K[i] = src4regv;
				}			}
			else if(i == SOURCE_5_connindex){
				if(SOURCE_5_BATTERY_CHARGE_SELECT == 1){
					K[i] = src5unregv;
				}
				else{
					K[i] = src5regv;
				}			}
			else if(i == SOURCE_6_connindex){
				if(SOURCE_6_BATTERY_CHARGE_SELECT == 1){
					K[i] = src6unregv;
				}
				else{
					K[i] = src6regv;
				}
			}
		}
		
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));

		//these entries help us find the unknown currents
		if(SOURCE_1_User == 1){
			Y[SOURCE_1_connindex][SOURCE_1_connindex] = -ro * SOURCE_1_User;
		}
		if(SOURCE_2_User == 1){
			Y[SOURCE_2_connindex][SOURCE_2_connindex] = -ro * SOURCE_2_User;
		}
		if(SOURCE_3_User == 1){
			Y[SOURCE_3_connindex][SOURCE_3_connindex] = -ro * SOURCE_3_User;
		}
		if(SOURCE_4_User == 1){
			Y[SOURCE_4_connindex][SOURCE_4_connindex] = -ro * SOURCE_4_User;
		}
		if(SOURCE_5_User == 1){
			Y[SOURCE_5_connindex][SOURCE_5_connindex] = -ro * SOURCE_5_User;
		}
		if(SOURCE_6_User == 1){
			Y[SOURCE_6_connindex][SOURCE_6_connindex] = -ro * SOURCE_6_User;
		}

		//display matrix for debugging
		if(debugging){
			Modelmath.printmat(Y);
		}
		
//***********************************************************************************
		//solve for the vector of unknowns		
		
		for(int i = 0; i<dim; i++)
			for(int j = 0; j<dim; j++)
				Yd[i][j] = Y[i][j];
		
		U = Modelmath.gausselim(Yd,K);
//***********************************************************************************
		if(debugging){
			Modelmath.printmat(Y);
		}
		
		//decompose vector
		for(int i = 0;i<U.length;i++){
			if(i<23){
				busvoltages[i] = U[i];
			}
			else if(i>22 && i<29){
				gencurrents[i-23] = -U[i];
			}
		}
		
			
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));

		//give tag values that are readable more convenient names
		mbv = busvoltages[2];
		b2b1v = busvoltages[3];
		b1b1v = busvoltages[4];
		b2ic1v = busvoltages[5];
		b1ic1v = busvoltages[6];
		b2b2v = busvoltages[7];
		b1b2v = busvoltages[8];
		b2ic2v = busvoltages[9];
		b1ic2v = busvoltages[10];
		b2b1l1v = busvoltages[11];
		b2b1l2v = busvoltages[12];
		b2b1l3v = busvoltages[13];
		b1b1l1v = busvoltages[14];
		b1b1l2v = busvoltages[15];
		b1b1l3v = busvoltages[16];
		b2b2l1v = busvoltages[17];
		b2b2l2v = busvoltages[18];
		b2b2l3v = busvoltages[19];
		b1b2l1v = busvoltages[20];
		b1b2l2v = busvoltages[21];
		b1b2l3v = busvoltages[22];
		//source voltages already defined
		
		b2b1c = -(mbv - b2b1v)*Y[2][3];
		b1b1c = -(mbv - b1b1v)*Y[2][4];
		b2b2c = -(b2ic1v - b2b2v)*Y[5][7];
		b1b2c = -(b1ic1v - b1b2v)*Y[6][8];
		ic1c = -(b2ic1v-b1ic1v)*Y[5][6];
		ic2c = -(b2ic2v-b1ic2v)*Y[9][10];
		b2b1l1c = -(b2b1v-b2b1l1v)*Y[3][11];
		b2b1l2c = -(b2b1v-b2b1l2v)*Y[3][12];
		b2b1l3c = -(b2b1v-b2b1l3v)*Y[3][13];
		b1b1l1c = -(b1b1v-b1b1l1v)*Y[4][14];
		b1b1l2c = -(b1b1v-b1b1l2v)*Y[4][15];
		b1b1l3c = -(b1b1v-b1b1l3v)*Y[4][16];
		b2b2l1c = -(b2b2v-b2b2l1v)*Y[7][17];
		b2b2l2c = -(b2b2v-b2b2l2v)*Y[7][18];
		b2b2l3c = -(b2b2v-b2b2l3v)*Y[7][19];
		b1b2l1c = -(b1b2v-b1b2l1v)*Y[8][20];
		b1b2l2c = -(b1b2v-b1b2l2v)*Y[8][21];
		b1b2l3c = -(b1b2v-b1b2l3v)*Y[8][22];
		
		//System.out.printf("Crosstie 1 admittance: %f    crosstie 1 current: %f", Y[5][6], ic1c);
		//System.out.printf("Crosstie 2 admittance: %f    crosstie 2 current: %f", Y[9][10], ic2c);
		
		//source currents
		if(SOURCE_1_User == 1){
			if(SOURCE_1_BATTERY_CHARGE_SELECT == 1){
				src1unregc = -U[SOURCE_1_connindex];
			}
			else{
				src1regc = -U[SOURCE_1_connindex];
			}			
		}
		else{
			if(SOURCE_1_BATTERY_CHARGE_SELECT == 1){
				src1unregc = 0;
			}
			else{
				src1regc = 0;
			}
		}
		if(SOURCE_2_User == 1){
			if(SOURCE_2_BATTERY_CHARGE_SELECT == 1){
				src2unregc = -U[SOURCE_2_connindex];
			}
			else{
				src2regc = -U[SOURCE_2_connindex];
			}			
		}
		else{
			if(SOURCE_2_BATTERY_CHARGE_SELECT == 1){
				src2unregc = 0;
			}
			else{
				src2regc = 0;
			}
		}
		if(SOURCE_3_User == 1){
			if(SOURCE_3_BATTERY_CHARGE_SELECT == 1){
				src3unregc = -U[SOURCE_3_connindex];
			}
			else{
				src3regc = -U[SOURCE_3_connindex];
			}			
		}
		else{
			if(SOURCE_3_BATTERY_CHARGE_SELECT == 1){
				src3unregc = 0;
			}
			else{
				src3regc = 0;
			}
		}
		if(SOURCE_4_User == 1){
			if(SOURCE_4_BATTERY_CHARGE_SELECT == 1){
				src4unregc = -U[SOURCE_4_connindex];
			}
			else{
				src4regc = -U[SOURCE_4_connindex];
			}			
		}
		else{
			if(SOURCE_4_BATTERY_CHARGE_SELECT == 1){
				src4unregc = 0;
			}
			else{
				src4regc = 0;
			}
		}
		if(SOURCE_5_User == 1){
			if(SOURCE_5_BATTERY_CHARGE_SELECT == 1){
				src5unregc = -U[SOURCE_5_connindex];
			}
			else{
				src5regc = -U[SOURCE_5_connindex];
			}			
		}
		else{
			if(SOURCE_5_BATTERY_CHARGE_SELECT == 1){
				src5unregc = 0;
			}
			else{
				src5regc = 0;
			}
		}
		if(SOURCE_6_User == 1){
			if(SOURCE_6_BATTERY_CHARGE_SELECT == 1){
				src6unregc = -U[SOURCE_6_connindex];
			}
			else{
				src6regc = -U[SOURCE_6_connindex];
			}			
		}
		else{
			if(SOURCE_6_BATTERY_CHARGE_SELECT == 1){
				src6unregc = 0;
			}
			else{
				src6regc = 0;
			}
		}
		
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));

		if(SOURCE_1_BATTERY_CHARGE_SELECT == 1){
			src1unregv = src1.simstep(src1unregc, src1unregv);
			src1regv = src1.outv;
			src1regc = src1.outc;
		}
		else{
			src1regv = src1.simstep(src1regc,src1regv);
			src1unregv = src1.inv;
			src1unregc = src1.ini;
		}

		if(SOURCE_2_BATTERY_CHARGE_SELECT == 1){
			src2unregv = src2.simstep(src2unregc, src2unregv);
			src2regv = src2.outv;
			src2regc = src2.outc;
		}
		else{
			src2regv = src2.simstep(src2regc,src2regv);
			src2unregv = src2.inv;
			src2unregc = src2.ini;
		}

		if(SOURCE_3_BATTERY_CHARGE_SELECT == 1){
			src3unregv = src3.simstep(src3unregc, src3unregv);
			src3regv = src3.outv;
			src3regc = src3.outc;
		}
		else{
			src3regv = src3.simstep(src3regc, src3regv);
			src3unregv = src3.inv;
			src3unregc = src3.ini;
		}

		if(SOURCE_4_BATTERY_CHARGE_SELECT == 1){
			src4unregv = src4.simstep(src4unregc, src4unregv);
			src4regv = src4.outv;
			src4regc = src4.outc;
		}
		else{
			src4regv = src4.simstep(src4regc, src4regv);
			src4unregv = src4.inv;
			src4unregc = src4.ini;
		}

		if(SOURCE_5_BATTERY_CHARGE_SELECT == 1){
			src5unregv = src5.simstep(src5unregc, src5unregv);
			src5regv = src5.outv;
			src5regc = src5.outc;
		}
		else{
			src5regv = src5.simstep(src5regc,  src5regv);
			src5unregv = src5.inv;
			src5unregc = src5.ini;
		}

		if(SOURCE_6_BATTERY_CHARGE_SELECT == 1){
			src6unregv = src6.simstep(src6unregc, src6unregv);
			src6regv = src6.outv;
			src6regc = src6.outc;
		}
		else{
			src6regv = src6.simstep(src6regc,  src6regv);
			src6unregv = src6.inv;
			src6unregc = src6.ini;
		}

		
		//System.out.println(String.format("regv: %f",src3regv));
		//System.out.println(String.format("regc: %f",src3regc));
		
		//write to log
		this.log.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
				String.valueOf(busvoltages[1]),String.valueOf(busvoltages[2]),String.valueOf(busvoltages[3]),String.valueOf(busvoltages[4]),String.valueOf(busvoltages[5]),String.valueOf(busvoltages[6]),
				String.valueOf(busvoltages[7]),String.valueOf(busvoltages[8]),String.valueOf(busvoltages[9]),
				String.valueOf(busvoltages[10]),String.valueOf(busvoltages[11]),String.valueOf(busvoltages[12]),String.valueOf(busvoltages[13]),String.valueOf(busvoltages[14]),
				String.valueOf(busvoltages[15]),String.valueOf(busvoltages[16]),String.valueOf(busvoltages[17]),String.valueOf(busvoltages[18]),String.valueOf(busvoltages[19]),
				String.valueOf(busvoltages[20]),String.valueOf(busvoltages[21]),String.valueOf(busvoltages[22]),String.valueOf(b2b1c),String.valueOf(b1b1c),String.valueOf(ic1c),
				String.valueOf(b2b2c),String.valueOf(b1b2c),String.valueOf(ic2c),String.valueOf(b2b1l1c),String.valueOf(b2b1l2c),String.valueOf(b2b1l3c),String.valueOf(b1b1l1c),
				String.valueOf(b1b1l2c),String.valueOf(b1b1l3c),String.valueOf(b2b2l1c),String.valueOf(b2b2l2c),String.valueOf(b2b2l3c),String.valueOf(b1b2l1c),String.valueOf(b1b2l2c),
				String.valueOf(b1b2l3c),String.valueOf(src1regc),String.valueOf(src2regc),String.valueOf(src3regc),String.valueOf(src4regc),String.valueOf(src5regc),String.valueOf(src6regc)));
		
		
		if(debugging){
			System.out.println("BUS VOLTAGES:");
			System.out.println(String.format("ground: %f", busvoltages[0]));
			System.out.println(String.format("main bus: %f", busvoltages[1]));
			System.out.println(String.format("main branch: %f", busvoltages[2]));
			System.out.println(String.format("branch 2 bus 1: %f", busvoltages[3]));
			System.out.println(String.format("branch 1 bus 1: %f", busvoltages[4]));
			System.out.println(String.format("branch 2 interconnect 1: %f", busvoltages[5]));
			System.out.println(String.format("branch 1 interconnect 1: %f", busvoltages[6]));
			System.out.println(String.format("branch 2 bus 2: %f", busvoltages[7]));
			System.out.println(String.format("branch 1 bus 2: %f", busvoltages[8]));
			System.out.println(String.format("branch 2 interconnect 2: %f", busvoltages[9]));
			System.out.println(String.format("branch 1 interconnect 2: %f", busvoltages[10]));
			System.out.println(String.format("branch 2 bus 1 load 1: %f", busvoltages[11]));
			System.out.println(String.format("branch 2 bus 1 load 2: %f", busvoltages[12]));
			System.out.println(String.format("branch 2 bus 1 load 3: %f", busvoltages[13]));
			System.out.println(String.format("branch 1 bus 1 load 1: %f", busvoltages[14]));
			System.out.println(String.format("branch 1 bus 1 load 2: %f", busvoltages[15]));
			System.out.println(String.format("branch 1 bus 1 load 3: %f", busvoltages[16]));
			System.out.println(String.format("branch 2 bus 2 load 1: %f", busvoltages[17]));
			System.out.println(String.format("branch 2 bus 2 load 2: %f", busvoltages[18]));
			System.out.println(String.format("branch 2 bus 2 load 3: %f", busvoltages[19]));
			System.out.println(String.format("branch 1 bus 2 load 1: %f", busvoltages[20]));
			System.out.println(String.format("branch 1 bus 2 load 2: %f", busvoltages[21]));
			System.out.println(String.format("branch 1 bus 2 load 3: %f", busvoltages[22]));
			System.out.println("GENERATOR CURRENTS");
			System.out.println(String.format("source 1: %f", src1regc));
			System.out.println(String.format("source 2: %f", src2regc));
			System.out.println(String.format("source 3: %f", src3regc));
			System.out.println(String.format("source 4: %f", src4regc));
			System.out.println(String.format("source 5: %f", src5regc));
			System.out.println(String.format("source 6: %f", src6regc));
			System.out.println("CURRENTS");
			System.out.println(String.format("branch 2 bus 1 current: %f", b2b1c));
			System.out.println(String.format("branch 1 bus 1 current: %f", b1b1c));
			System.out.println(String.format("interconnect 1 current: %f", ic1c));
			System.out.println(String.format("branch 2 bus 2 current: %f", b2b2c));
			System.out.println(String.format("branch 1 bus 2 current: %f", b1b2c));
			System.out.println(String.format("interconnect 2 current: %f", ic2c));
			System.out.println(String.format("branch 2 bus 1 load 1 current: %f", b2b1l1c));
			System.out.println(String.format("branch 2 bus 1 load 2 current: %f", b2b1l2c));
			System.out.println(String.format("branch 2 bus 1 load 3 current: %f", b2b1l3c));
			System.out.println(String.format("branch 1 bus 1 load 1 current: %f", b1b1l1c));
			System.out.println(String.format("branch 1 bus 1 load 2 current: %f", b1b1l2c));
			System.out.println(String.format("branch 1 bus 1 load 3 current: %f", b1b1l3c));
			System.out.println(String.format("branch 2 bus 2 load 1 current: %f", b2b2l1c));
			System.out.println(String.format("branch 2 bus 2 load 2 current: %f", b2b2l2c));
			System.out.println(String.format("branch 2 bus 2 load 3 current: %f", b2b2l3c));
			System.out.println(String.format("branch 1 bus 2 load 1 current: %f", b1b2l1c));
			System.out.println(String.format("branch 1 bus 2 load 2 current: %f", b1b2l2c));
			System.out.println(String.format("branch 1 bus 2 load 3 current: %f", b1b2l3c));
		}
	}
	
	public Object[] fakeinterface(String plc, String mode, String[] tags, Object[] values){
		Object[] retval = new Object[tags.length];
			if(plc.equals("user")){
				if(mode.equals("read")){
					for(int i = 0;i<tags.length;i++){
						if(tags[i].equals("BRANCH_1_BUS_1_LOAD_1_Current")){
								retval[i] = new Float(b1b1l1c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_2_Current")){
								retval[i] = new Float(b1b1l2c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_3_Current")){
							retval[i] = new Float(b1b1l3c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_1_Current")){
							retval[i] = new Float(b2b1l1c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_2_Current")){
							retval[i] = new Float(b2b1l2c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_3_Current")){
							retval[i] = new Float(b2b1l3c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_1_Current")){
							retval[i] = new Float(b1b2l1c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_2_Current")){
							retval[i] = new Float(b1b2l2c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_3_Current")){
							retval[i] = new Float(b1b2l3c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_1_Current")){
							retval[i] = new Float(b2b2l1c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_2_Current")){
							retval[i] = new Float(b2b2l2c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_3_Current")){
							retval[i] = new Float(b2b2l3c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_Current")){
							retval[i] = new Float(b1b1c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_Current")){
							retval[i] = new Float(b2b1c);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_Current")){
							retval[i] = new Float(b1b2c);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_Current")){
							retval[i] = new Float(b2b2c);
						}
						else if(tags[i].equals("CROSSTIE_1_Current")){
							retval[i] = new Float(ic1c);
						}
						else if(tags[i].equals("CROSSTIE_2_Current")){
							retval[i] = new Float(ic2c);
						}
						else if(tags[i].equals("SOURCE_1_RegCurrent")){
							retval[i] = new Float(src1regc);
						}
						else if(tags[i].equals("SOURCE_2_RegCurrent")){
							retval[i] = new Float(src2regc);
						}
						else if(tags[i].equals("SOURCE_3_RegCurrent")){
							retval[i] = new Float(src3regc);
						}
						else if(tags[i].equals("SOURCE_4_RegCurrent")){
							retval[i] = new Float(src4regc);
						}
						else if(tags[i].equals("SOURCE_5_RegCurrent")){
							retval[i] = new Float(src5regc);
						}
						else if(tags[i].equals("SOURCE_6_RegCurrent")){
							retval[i] = new Float(src6regc);
						}
						else if(tags[i].equals("SOURCE_1_RegVoltage")){
							retval[i] = new Float(src1regv);
						}
						else if(tags[i].equals("SOURCE_2_RegVoltage")){
							retval[i] = new Float(src2regv);
						}
						else if(tags[i].equals("SOURCE_3_RegVoltage")){
							retval[i] = new Float(src3regv);
						}
						else if(tags[i].equals("SOURCE_4_RegVoltage")){
							retval[i] = new Float(src4regv);
						}
						else if(tags[i].equals("SOURCE_5_RegVoltage")){
							retval[i] = new Float(src5regv);
						}
						else if(tags[i].equals("SOURCE_6_RegVoltage")){
							retval[i] = new Float(src6regv);
						}	
						else if(tags[i].equals("SOURCE_1_UnregCurrent")){
							retval[i] = new Float(src1unregc);
						}
						else if(tags[i].equals("SOURCE_2_UnregCurrent")){
							retval[i] = new Float(src2unregc);
						}
						else if(tags[i].equals("SOURCE_3_UnregCurrent")){
							retval[i] = new Float(src3unregc);
						}
						else if(tags[i].equals("SOURCE_4_UnregCurrent")){
							retval[i] = new Float(src4unregc);
						}
						else if(tags[i].equals("SOURCE_5_UnregCurrent")){
							retval[i] = new Float(src5unregc);
						}
						else if(tags[i].equals("SOURCE_6_UnregCurrent")){
							retval[i] = new Float(src6unregc);
						}
						else if(tags[i].equals("SOURCE_1_UnregVoltage")){
							retval[i] = new Float(src1unregv);
						}
						else if(tags[i].equals("SOURCE_2_UnregVoltage")){
							retval[i] = new Float(src2unregv);
						}
						else if(tags[i].equals("SOURCE_3_UnregVoltage")){
							retval[i] = new Float(src3unregv);
						}
						else if(tags[i].equals("SOURCE_4_UnregVoltage")){
							retval[i] = new Float(src4unregv);
						}
						else if(tags[i].equals("SOURCE_5_UnregVoltage")){
							retval[i] = new Float(src5unregv);
						}
						else if(tags[i].equals("SOURCE_6_UnregVoltage")){
							retval[i] = new Float(src6unregv);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_Voltage")){
							retval[i] = new Float(b1b1v);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_Voltage")){
							retval[i] = new Float(b2b1v);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_Voltage")){
							retval[i] = new Float(b1b2v);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_Voltage")){
							retval[i] = new Float(b2b2v);
						}
						else if(tags[i].equals("MAIN_BUS_Voltage")){
							retval[i] = new Float(mbv);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_1_User")){
							retval[i] = int2bool(BRANCH_1_BUS_1_LOAD_1_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_2_User")){
							retval[i] = int2bool(BRANCH_1_BUS_1_LOAD_2_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_3_User")){
							retval[i] = int2bool(BRANCH_1_BUS_1_LOAD_3_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_1_User")){
							retval[i] = int2bool(BRANCH_2_BUS_1_LOAD_1_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_2_User")){
							retval[i] = int2bool(BRANCH_2_BUS_1_LOAD_2_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_3_User")){
							retval[i] = int2bool(BRANCH_2_BUS_1_LOAD_3_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_1_User")){
							retval[i] = int2bool(BRANCH_1_BUS_2_LOAD_1_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_2_User")){
							retval[i] = int2bool(BRANCH_1_BUS_2_LOAD_2_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_3_User")){
							retval[i] = int2bool(BRANCH_1_BUS_2_LOAD_3_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_1_User")){
							retval[i] = int2bool(BRANCH_2_BUS_2_LOAD_1_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_2_User")){
							retval[i] = int2bool(BRANCH_2_BUS_2_LOAD_2_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_3_User")){
							retval[i] = int2bool(BRANCH_2_BUS_2_LOAD_3_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_PROXIMAL_User")){
							retval[i] = int2invbool(BRANCH_2_BUS_2_PROXIMAL_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_PROXIMAL_User")){
							retval[i] = int2invbool(BRANCH_1_BUS_2_PROXIMAL_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_PROXIMAL_User")){
							retval[i] = int2invbool(BRANCH_2_BUS_1_PROXIMAL_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_PROXIMAL_User")){
							retval[i] = int2invbool(BRANCH_1_BUS_1_PROXIMAL_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_DISTAL_User")){
							retval[i] = int2invbool(BRANCH_2_BUS_2_DISTAL_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_DISTAL_User")){
							retval[i] = int2invbool(BRANCH_1_BUS_2_DISTAL_User);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_DISTAL_User")){
							retval[i] = int2invbool(BRANCH_2_BUS_1_DISTAL_User);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_DISTAL_User")){
							retval[i] = int2invbool(BRANCH_1_BUS_1_DISTAL_User);
						}
						else if(tags[i].equals("CROSSTIE_1_User")){
							retval[i] = int2invbool(CROSSTIE_1_User);
						}
						else if(tags[i].equals("CROSSTIE_2_User")){
							retval[i] = int2invbool(CROSSTIE_2_User);
						}
						else if(tags[i].equals("SOURCE_1_droopCoeff")){
							retval[i] = new Float(SOURCE_1_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_2_droopCoeff")){
							retval[i] = new Float(SOURCE_2_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_3_droopCoeff")){
							retval[i] = new Float(SOURCE_3_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_4_droopCoeff")){
							retval[i] = new Float(SOURCE_4_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_5_droopCoeff")){
							retval[i] = new Float(SOURCE_5_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_6_droopCoeff")){
							retval[i] = new Float(SOURCE_6_droopCoeff);
						}
						else if(tags[i].equals("SOURCE_1_noLoadVoltage")){
							retval[i] = new Float(SOURCE_1_noLoadVoltage);
						}
						else if(tags[i].equals("SOURCE_2_noLoadVoltage")){
							retval[i] = new Float(SOURCE_2_noLoadVoltage);
						}
						else if(tags[i].equals("SOURCE_3_noLoadVoltage")){
							retval[i] = new Float(SOURCE_3_noLoadVoltage);
						}
						else if(tags[i].equals("SOURCE_4_noLoadVoltage")){
							retval[i] = new Float(SOURCE_4_noLoadVoltage);
						}
						else if(tags[i].equals("SOURCE_5_noLoadVoltage")){
							retval[i] = new Float(SOURCE_5_noLoadVoltage);
						}
						else if(tags[i].equals("SOURCE_6_noLoadVoltage")){
							retval[i] = new Float(SOURCE_6_noLoadVoltage);
						}
						//else if(tags[i].equals("BATT_1_SOC")){
							//retval[i] = new Float(src1.soc);
						//}
						//else if(tags[i].equals("BATT_2_SOC")){
							//retval[i] = new Float(src2.soc);
						//}
						else if(tags[i].equals("SOURCE_1_User")){
							retval[i] = int2bool(SOURCE_1_User);
						}
						else if(tags[i].equals("SOURCE_2_User")){
							retval[i] = int2bool(SOURCE_2_User);
						}
						else if(tags[i].equals("SOURCE_3_User")){
							retval[i] = int2bool(SOURCE_3_User);
						}
						else if(tags[i].equals("SOURCE_4_User")){
							retval[i] = int2bool(SOURCE_4_User);
						}
						else if(tags[i].equals("SOURCE_5_User")){
							retval[i] = int2bool(SOURCE_5_User);
						}
						else if(tags[i].equals("SOURCE_6_User")){
							retval[i] = int2bool(SOURCE_6_User);
						}
						else if(tags[i].equals("SOURCE_1_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_1_BatteryReqCharge);
						}
						else if(tags[i].equals("SOURCE_2_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_2_BatteryReqCharge);
						}
						else if(tags[i].equals("SOURCE_3_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_3_BatteryReqCharge);
						}
						else if(tags[i].equals("SOURCE_4_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_4_BatteryReqCharge);
						}
						else if(tags[i].equals("SOURCE_5_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_5_BatteryReqCharge);
						}
						else if(tags[i].equals("SOURCE_6_BatteryReqCharge")){
							retval[i] = int2bool(SOURCE_6_BatteryReqCharge);
						}						
						else if(tags[i].equals("SOURCE_1_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_1_BatteryReqStop);
						}
						else if(tags[i].equals("SOURCE_2_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_2_BatteryReqStop);
						}
						else if(tags[i].equals("SOURCE_3_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_3_BatteryReqStop);
						}
						else if(tags[i].equals("SOURCE_4_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_4_BatteryReqStop);		
						}
						else if(tags[i].equals("SOURCE_5_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_5_BatteryReqStop);
						}
						else if(tags[i].equals("SOURCE_6_BatteryReqStop")){
							retval[i] = int2bool(SOURCE_6_BatteryReqStop);
						}				
						else if(tags[i].equals("SOURCE_1_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_1_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_2_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_2_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_3_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_3_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_4_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_4_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_5_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_5_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_6_BATTERY_CHARGE_SELECT")){
							retval[i] = int2bool(SOURCE_6_BATTERY_CHARGE_SELECT);
						}
						else if(tags[i].equals("SOURCE_1_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_1_DROOP_SELECT);
						}
						else if(tags[i].equals("SOURCE_2_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_2_DROOP_SELECT);
						}
						else if(tags[i].equals("SOURCE_3_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_3_DROOP_SELECT);
						}
						else if(tags[i].equals("SOURCE_4_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_4_DROOP_SELECT);
						}
						else if(tags[i].equals("SOURCE_5_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_5_DROOP_SELECT);
						}
						else if(tags[i].equals("SOURCE_6_DROOP_SELECT")){
							retval[i] = int2bool(SOURCE_6_DROOP_SELECT);
						}
						else{
							System.out.println(String.format("tag %s does not exist",tags[i]));
							retval = null;
						}
					} //for loop
					
				} // read mode
				else if(mode.equals("write")){
					for(int i = 0;i<tags.length;i++){
						if(tags[i].equals("BRANCH_1_BUS_1_LOAD_1_User")){
							BRANCH_1_BUS_1_LOAD_1_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_2_User")){
							BRANCH_1_BUS_1_LOAD_2_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_LOAD_3_User")){
							BRANCH_1_BUS_1_LOAD_3_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_1_User")){
							BRANCH_2_BUS_1_LOAD_1_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_2_User")){
							BRANCH_2_BUS_1_LOAD_2_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_LOAD_3_User")){
							BRANCH_2_BUS_1_LOAD_3_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_1_User")){
							BRANCH_1_BUS_2_LOAD_1_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_2_User")){
							BRANCH_1_BUS_2_LOAD_2_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_LOAD_3_User")){
							BRANCH_1_BUS_2_LOAD_3_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_1_User")){
							BRANCH_2_BUS_2_LOAD_1_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_2_User")){
							BRANCH_2_BUS_2_LOAD_2_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_LOAD_3_User")){
							BRANCH_2_BUS_2_LOAD_3_User = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_PROXIMAL_User")){
							BRANCH_2_BUS_2_PROXIMAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_PROXIMAL_User")){
							BRANCH_1_BUS_2_PROXIMAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_PROXIMAL_User")){
							BRANCH_2_BUS_1_PROXIMAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_PROXIMAL_User")){
							BRANCH_1_BUS_1_PROXIMAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_DISTAL_User")){
							BRANCH_2_BUS_2_DISTAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_DISTAL_User")){
							BRANCH_1_BUS_2_DISTAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_DISTAL_User")){
							BRANCH_2_BUS_1_DISTAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_1_DISTAL_User")){
							BRANCH_1_BUS_1_DISTAL_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_1_User")){
							CROSSTIE_1_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_2_User")){
							CROSSTIE_2_User = invbool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_User")){
							SOURCE_1_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_User")){
							SOURCE_2_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_User")){
							SOURCE_3_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_User")){
							SOURCE_4_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_User")){
							SOURCE_5_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_User")){
							SOURCE_6_User = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_droopCoeff")){
							SOURCE_1_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_droopCoeff")){
							SOURCE_2_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_droopCoeff")){
							SOURCE_3_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_droopCoeff")){
							SOURCE_4_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_droopCoeff")){
							SOURCE_5_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_droopCoeff")){
							SOURCE_6_droopCoeff = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_noLoadVoltage")){
							SOURCE_1_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_noLoadVoltage")){
							SOURCE_2_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_noLoadVoltage")){
							SOURCE_3_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_noLoadVoltage")){
							SOURCE_4_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_noLoadVoltage")){
							SOURCE_5_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_noLoadVoltage")){
							SOURCE_6_noLoadVoltage = obj2double(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_noLoadVoltage")){
							SOURCE_1_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_noLoadVoltage")){
							SOURCE_2_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_noLoadVoltage")){
							SOURCE_3_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_noLoadVoltage")){
							SOURCE_4_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_noLoadVoltage")){
							SOURCE_5_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_noLoadVoltage")){
							SOURCE_6_noLoadVoltage = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_BatteryReqCharge")){
							SOURCE_1_BatteryReqCharge = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_BatteryReqCharge")){
							SOURCE_2_BatteryReqCharge = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_BatteryReqCharge")){
							SOURCE_3_BatteryReqCharge = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_BatteryReqCharge")){
							SOURCE_4_BatteryReqCharge = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_BatteryReqCharge")){
							SOURCE_5_BatteryReqCharge = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_BatteryReqCharge")){
							SOURCE_6_BatteryReqCharge = bool2int(values[i]);
						}				
						else if(tags[i].equals("SOURCE_1_BatteryReqStop")){
							SOURCE_1_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_BatteryReqStop")){
							SOURCE_2_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_BatteryReqStop")){
							SOURCE_3_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_BatteryReqStop")){
							SOURCE_4_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_BatteryReqStop")){
							SOURCE_5_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_BatteryReqStop")){
							SOURCE_6_BatteryReqStop = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_BATTERY_CHARGE_SELECT")){
							SOURCE_1_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_BATTERY_CHARGE_SELECT")){
							SOURCE_2_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_BATTERY_CHARGE_SELECT")){
							SOURCE_3_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_BATTERY_CHARGE_SELECT")){
							SOURCE_4_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_BATTERY_CHARGE_SELECT")){
							SOURCE_5_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_BATTERY_CHARGE_SELECT")){
							SOURCE_6_BATTERY_CHARGE_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_1_DROOP_SELECT")){
							SOURCE_1_DROOP_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_2_DROOP_SELECT")){
							SOURCE_2_DROOP_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_3_DROOP_SELECT")){
							SOURCE_3_DROOP_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_4_DROOP_SELECT")){
							SOURCE_4_DROOP_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_5_DROOP_SELECT")){
							SOURCE_5_DROOP_SELECT = bool2int(values[i]);
						}
						else if(tags[i].equals("SOURCE_6_DROOP_SELECT")){
							SOURCE_6_DROOP_SELECT = bool2int(values[i]);
						}
						else{
							System.out.println(String.format("tag %s does not exist",tags[i]));
							retval = null;
						}
					} //for loop
				} // write mode
						
			} // user plc
			else if(plc.equals("SG")){
				if (mode.equals("read")){
					for(int i = 0;i<tags.length;i++){
						if(tags[i].equals("BRANCH_1_BUS_1_FAULT")){
							retval[i] = int2bool(BRANCH_1_BUS_1_Fault);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_FAULT")){
							retval[i] = int2bool(BRANCH_1_BUS_2_Fault);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_FAULT")){
							retval[i] = int2bool(BRANCH_2_BUS_1_Fault);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_FAULT")){
							retval[i] = int2bool(BRANCH_2_BUS_2_Fault);
						}
						else if(tags[i].equals("CROSSTIE_1_FAULT_1")){
							retval[i] = int2bool(CROSSTIE_1_Fault_1);
						}
						else if(tags[i].equals("CROSSTIE_1_FAULT_2")){
							retval[i] = int2bool(CROSSTIE_1_Fault_2);
						}
						else if(tags[i].equals("CROSSTIE_2_FAULT_1")){
							retval[i] = int2bool(CROSSTIE_2_Fault_1);
						}
						else if(tags[i].equals("CROSSTIE_2_FAULT_2")){
							retval[i] = int2bool(CROSSTIE_2_Fault_2);
						}
						else if(tags[i].equals("MAIN_BUS_FAULT")){
							retval[i] = int2bool(MAIN_BUS_Fault);
						}
						else if(tags[i].equals("SolarSetpoint1Alias")){
							retval[i] = new Float(SolarSetpoint1Alias);
						}
						else if(tags[i].equals("SolarSetpoint2Alias")){
							retval[i] = new Float(SolarSetpoint2Alias);
						}
					}
				}
				else if(mode.equals("write")){
					for(int i = 0;i<tags.length;i++){
						if(tags[i].equals("BRANCH_1_BUS_1_FAULT")){
							BRANCH_1_BUS_1_Fault = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_1_BUS_2_FAULT")){
							BRANCH_1_BUS_2_Fault = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_1_FAULT")){
							BRANCH_2_BUS_1_Fault = bool2int(values[i]);
						}
						else if(tags[i].equals("BRANCH_2_BUS_2_FAULT")){
							BRANCH_2_BUS_2_Fault = bool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_1_FAULT_1")){
							CROSSTIE_1_Fault_1 = bool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_1_FAULT_2")){
							CROSSTIE_1_Fault_2 = bool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_2_FAULT_1")){
							CROSSTIE_2_Fault_1 = bool2int(values[i]);
						}
						else if(tags[i].equals("CROSSTIE_2_FAULT_2")){
							CROSSTIE_2_Fault_2 = bool2int(values[i]);
						}
						else if(tags[i].equals("MAIN_BUS_FAULT")){
							MAIN_BUS_Fault = bool2int(values[i]);
						}
						else if(tags[i].equals("SolarSetpoint1Alias")){
							SolarSetpoint1Alias = obj2double(values[i]);
						}
						else if(tags[i].equals("SolarSetpoint2Alias")){
							SolarSetpoint2Alias = obj2double(values[i]);
						}

					
					}

				}
				
				
			}
		if(retval != null){
			System.out.println("fake interface returns the following:");
			for(int i = 0;i<retval.length;i++){
				if(retval[i] != null){
					System.out.println(retval[i].toString());
				}
				else{
					System.out.println(String.format("index %d is null", i));
				}
			}
		}
		else{
			System.out.println("fake interface returns null");
		}

		return retval;
	}
	
	public double obj2double(Object in){
		double retval = 0;
		if(in instanceof Number){
			retval = ((Number) in).doubleValue();
		}		
		return retval;
	}
	
	public Boolean int2bool(int in){		
		Boolean retval;
		if(in == 1){
			retval = new Boolean(true);
		}
		else if(in == 0){
			retval = new Boolean(false);
		}
		else{
			System.out.println(String.format("int2bool got bad value: %d",in));
			retval = null;
		}
		return retval;
	}
	
	public Boolean int2invbool(int in){		
		Boolean retval;
		if(in == 1){
			retval = new Boolean(false);
		}
		else if(in == 0){
			retval = new Boolean(true);
		}
		else{
			System.out.println(String.format("int2bool got bad value: %d",in));
			retval = null;
		}
		return retval;
	}
	
	public int bool2int(Object boo){
		int retval;
		if(boo.equals(new Boolean(true))){
			retval = 1;
		}
		else if(boo.equals(new Boolean(false))){
			retval = 0;
		}
		else{
			System.out.println(String.format("bool2int got bad value: %s",boo.toString()));
			retval = -1;
		}
		return retval;
	}
	
	public int invbool2int(Object boo){
		int retval;
		if(boo.equals(new Boolean(true))){
			retval = 0;
		}
		else if(boo.equals(new Boolean(false))){
			retval = 1;
		}
		else{
			System.out.println(String.format("bool2int got bad value: %s",boo.toString()));
			retval = -1;
		}
		return retval;
	}
	
		
	
}

