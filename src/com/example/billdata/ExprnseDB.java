package com.example.billdata;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;


//The department Performs the actions of the App
public class ExprnseDB extends Activity 
{
	private static double conversion;	

	public ExprnseDB(double con){this.conversion=con;}//Constructor
	public LinkedList <Expense> addExpense(LinkedList <Expense> linkExp,Expense newExP)throws AddException
	/* The method receives a linked list of "Expenses" and a new object type "expenses".
	The method performs the calculation of the cost per month. 
	And returns a new list with object	 */
	{ 
		if (linkExp==null)//Happens only once (when the file is empty)
			linkExp= new LinkedList <Expense>();

		if (linkExp.size()>0)//if there is more then one element - make the Calculation
		{
			int amountLestMonth0=linkExp.getLast().getAmountOwner();
			int amountLestMonth1=linkExp.getLast().getAmountApart1();
			int amountLestMonth2=linkExp.getLast().getAmountApart2();
			
			int amountThisAmount0=newExP.getAmountOwner();
			int amountThisAmount1=newExP.getAmountApart1();
			int amountThisAmount2=newExP.getAmountApart2();
						
			int courantAmount1=amountThisAmount1-amountLestMonth1;
			int courantAmount2=amountThisAmount2-amountLestMonth2;
			int courantAmount0=amountThisAmount0-amountLestMonth0-courantAmount1-courantAmount2;
			
			double courantPay0=courantAmount0*conversion/100;
			double courantPay1=courantAmount1*conversion/100;
			double courantPay2=courantAmount2*conversion/100;
		
			BigDecimal bd = new BigDecimal(courantPay0).setScale(2, RoundingMode.HALF_EVEN);
			courantPay0 = bd.doubleValue();		
			newExP.setPayOwner(courantPay0);
			
			bd = new BigDecimal(courantPay1).setScale(2, RoundingMode.HALF_EVEN);
			courantPay1 = bd.doubleValue();		
			newExP.setPayApart1(courantPay1);
			
			bd = new BigDecimal(courantPay2).setScale(2, RoundingMode.HALF_EVEN);
			courantPay2 = bd.doubleValue();		
			newExP.setPayApart2(courantPay2);
		} 
		linkExp.add(newExP);//*Adds the new object list

		return linkExp;		
	}
	public String toString(LinkedList <Expense> linkExp)
	{	
		String st="";

		if (linkExp==null)
			return "";
		
		for (int i=0; i<linkExp.size(); i++)
			st=st+linkExp.get(i)+"\n";
 
		return st;
	}
	public LinkedList<Expense> getAllExpenses(String dataFromFile)
	/*Method accepts a string which Save the file content. 
	And returns a linked list of "Expenses". 
	The method convert string(from file) to data(linkList) */
	//Example: Conversion$the data(json)
	{
		final int ZERO=0;		
		LinkedList <Expense> plist= new LinkedList <Expense>();//List that contain the result
		Expense tempExp;//Variable that will contain the object

		//set "conversion" from the file
		for (int i=0; i<dataFromFile.length(); i++)
			if (dataFromFile.charAt(i)=='$')
				{
					String stCov=dataFromFile.substring(0, i);
					dataFromFile=dataFromFile.substring(i+1,dataFromFile.length());					
					conversion=Double.parseDouble(stCov);					
					break;
				}
	
		if (dataFromFile.length()==0)//When the file is empty
			return null;
	
		try {			
			JSONObject jObj = new JSONObject(dataFromFile);
					
			JSONArray jArr = jObj.getJSONArray("Expense");
			for (int i=0; i < jArr.length(); i++) 
			{
			    JSONObject obj = jArr.getJSONObject(i); 
			    int month=obj.getInt("month");
			    
			    int amount0=obj.getInt("amount0");			    
			    double pay0=obj.getDouble("pay0");
			    
			    int amount1=obj.getInt("amount1");			    
			    double pay1=obj.getDouble("pay1");
			    
			    int amount2=obj.getInt("amount2");			    
			    double pay2=obj.getDouble("pay2");
			    
			    tempExp=new Expense(month, amount0, amount1, amount2, pay0, pay1, pay2);
			    plist.add(tempExp);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return plist;
	}

	public double getConversion() {return conversion;}
	public void setConversion(double conversion) {this.conversion = conversion;}
}//end
