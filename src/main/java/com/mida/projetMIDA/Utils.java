package com.mida.projetMIDA;


public class Utils {
	
	public static int formInt(int step,int num) {
		String numString=step+""+num;
		if(step==0) {
			 numString=0+""+num;
		}
	     int numero=Integer.parseInt(numString);
		return numero;
		
	}
	public static java.sql.Timestamp setDate(){
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}
}
