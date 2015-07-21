package com.example.billdata;

import java.io.Serializable;

//Class represents an object of type Expense
public class Expense implements Serializable
{
	private int month;
	
	private int amountOwner;	
	private double payOwner;
	
	private int amountApart1;	
	private double payApart1;
	
	private int amountApart2;	
	private double payApart2;
	
	public Expense(int month,int amountOwner,int amountApart1 ,int amountApart2)
	{
		this.month=month;
		
		this.amountOwner=amountOwner;
		this.amountApart1=amountApart1;
		this.amountApart2=amountApart2;
		
		payOwner=payApart1=payApart2=0;
	}
	public Expense(int month,int amountOwner,int amountApart1 ,int amountApart2,
							double payOwner,double payApart1,double payApart2)
	{
		this.month=month;
		this.amountOwner=amountOwner;
		this.amountApart1=amountApart1;
		this.amountApart2=amountApart2;
		
		this.payOwner=payOwner;
		this.payApart1=payApart1;
		this.payApart2=payApart2;
	}
	public String toString()
	{
		
		String st="";
		
/*		switch (month) 
 		{
		case 1:  st = "January";break;
		case 2:  st = "February";break;
		case 3:  st = "March";break;
		case 4:  st = "April";break;
		case 5:  st = "May";break;
		case 6:  st = "June";break;
		case 7:  st = "July";break;
		case 8:  st = "August";break;
		case 9:  st = "September";break;
		case 10:  st = "October";break;
		case 11:  st = "November";break;
		case 12:  st = "December";break;		
		}
		
		st=st+"-"+"counter:"+amount+" Total payment:"+pay+"₪";*/

		switch (month) 
 		{
		case 0:  st = "ינואר-פברואר";break;
		case 1:  st = "מרץ-אפריל";break;
		case 2:  st = "מאי-יוני";break;
		case 3:  st = "יולי-אוגוסט";break;
		case 4:  st = "ספטמבר-אוקטובר";break;
		case 5:  st = "נובמבר-דצמבר";break;
		}
		st+="\n";
		
	/*	st+="apart0-count:"+amountOwner+ " pay:"+payOwner+"\n";
		st+="apart1-count:"+amountApart1+ " pay:"+payApart1+"\n";
		st+="apart2-count:"+amountApart2+ " pay:"+payApart2+"\n";*/
		
		st+="אבי-מונה:"+amountOwner+ " סכום לתשלום:"+payOwner+"\n";
		st+="דירה1 מונה:"+amountApart1+ " סכום לתשלום:"+payApart1+"\n";
		st+="דירה2 מונה:"+amountApart2+ " סכום לתשלום:"+payApart2+"\n";
		//st=st+"-"+"מונה:"+amount+" סכום לתשלום:₪"+pay;
		
		return st;
	}
	
	
	public int getMonth() {return month;}
	public void setMonth(int month) {this.month = month;}
	public int getAmountOwner() {return amountOwner;}
	public void setAmountOwner(int amountOwner) {this.amountOwner = amountOwner;}
	public double getPayOwner() {return payOwner;}
	public void setPayOwner(double payOwner) {this.payOwner = payOwner;}
	public int getAmountApart1() {return amountApart1;}
	public void setAmountApart1(int amountApart1) {this.amountApart1 = amountApart1;}
	public double getPayApart1() {return payApart1;}
	public void setPayApart1(double payApart1) {this.payApart1 = payApart1;}
	public int getAmountApart2() {return amountApart2;}
	public void setAmountApart2(int amountApart2) {this.amountApart2 = amountApart2;}
	public double getPayApart2() {return payApart2;}
	public void setPayApart2(double payApart2) {this.payApart2 = payApart2;}

}

