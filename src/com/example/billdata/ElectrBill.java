package com.example.billdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
 
public class ElectrBill extends Activity implements OnClickListener{
 
	Button showData,addBill,bUpData;//button in menu.xml
	
	ExprnseDB expenseManager;
	LinkedList <Expense> linkExpense=null;//Linked list that contains the data(Expense)
	String FILENAME = "myFileBill.txt";//File name where the data will be saved
		 
	protected void onCreate(Bundle savedInstanceState) 
	// the method running when you run the application
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.electrmenu);
		
		expenseManager=new ExprnseDB(65.62);//new object of expense
		 
		initializeVars();//Boot Interface
	}//end onCreate 
	public void onClick(View v) 
	//The method operates listener buttons
	{
		switch (v.getId()) 
		{
		case R.id.bShowData://When pressed "show data" button
			Intent intentShowDate=new Intent(ElectrBill.this,showData.class);
			intentShowDate.putExtra("key", expenseManager.toString(linkExpense));//send intent with string to print on the Screen
			this.startActivity(intentShowDate);	//open new page
			break;	 

		case R.id.bAddBill://When pressed "add bill" button
			Intent intentAddBill=new Intent(ElectrBill.this,addBill.class);
			startActivityForResult(intentAddBill, 2);// Activity is started with requestCode 2
			break;	
						
		case R.id.bUpData://When pressed "updata" button (updata-the lest organ that added)
			if (linkExpense==null)
			{
				Toast.makeText(getBaseContext(), "לא ניתן לעבור לעמוד. יש להזין נתונים", Toast.LENGTH_SHORT).show();
				return;
			}
			Expense	lestEx=linkExpense.getLast();//the lest organ
			linkExpense.removeLast();//remove the organ
			String data=lestEx.getMonth()+"#"+lestEx.getAmountOwner()+
						"%"+lestEx.getAmountApart1()+"$"+lestEx.getAmountApart2();//convert the organ to string
			Intent intentUpData=new Intent(ElectrBill.this,updataLestExpe.class);
			intentUpData.putExtra("key",data);//send intent with string(the lest organ)
			
			startActivityForResult(intentUpData, 5);// Activity is started with requestCode 2
			
			break;
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	//The method receives data from the different pages that opens
	{  
		super.onActivityResult(requestCode, resultCode, data);  	
		String message="";

		switch (requestCode)
		{
		case 2://add data- "addBill.java"    
			message=data.getStringExtra("key");  
			if (message.equals("#"))//No data has been entered to field
			{	
				Toast.makeText(getBaseContext(), "אזהרה: לא הוזנו נתונים", Toast.LENGTH_SHORT).show();
				return;
			}
			
			Expense tempExp=stringToExpens(message);//convert string to Expense 
			addNewExpens(tempExp);// add a new object "Expense" to linkList 
			Toast.makeText(getBaseContext(), "נתונים הוספו בהצלחה!", Toast.LENGTH_SHORT).show();
			break;
		case 3: //set Conversion- "coversionrate.java"   
			message=data.getStringExtra("key"); 
			if (message.equals(""))//No data has been entered to field
			{ 
				Toast.makeText(getBaseContext(), "אזהרה: לא הוזנו נתונים", Toast.LENGTH_SHORT).show();
				return;
			}
			expenseManager.setConversion(Double.parseDouble(message));//set Conversion
			write();//write the new data to file
			Toast.makeText(getBaseContext(), "נתונים עודכנו בהצלחה!", Toast.LENGTH_SHORT).show();
			break;
		case 4://password - "password.java" 
			message=data.getStringExtra("key"); 
			if (message.equals("1"))
			{
				clearFile();//Delete the file
				Toast.makeText(getBaseContext(), "קובץ נמחק", Toast.LENGTH_SHORT).show();
			}
			break;	 
		case 5://Update lest expense in list- "updataLestExpe.java"
			message=data.getStringExtra("key");
			
			if (message.charAt(message.length()-1)=='*')//No data has been entered to field
			{
				Toast.makeText(getBaseContext(), "לא בוצעו שינויים", Toast.LENGTH_SHORT).show();
				message=message.substring(0, message.length()-1);//substring (*) from the string
			}			 
			else//data has been entered to field
				Toast.makeText(getBaseContext(), "נתונים עודכנו בהצלחה!", Toast.LENGTH_SHORT).show();
			
			Expense tempExpUpdata=stringToExpens(message);//write the new data to file
			write();//write the new data to file
			addNewExpens(tempExpUpdata);// add a new object Expense
			break;		
		}  
	}    
	private Expense stringToExpens(String st)
	//The method takes a string(month#amount) and returns object Expense 
	{		
		//find '#' in the string  
		int point1=-1; int point2=-1; int point3=-1;
		for(int i=0; i<st.length(); i++)
			switch (st.charAt(i))
			{
				case '#': point1=i;	break;
				case '%': point2=i;	break;
				case '$': point3=i;	break;			
			}
		
		String stMonth=st.substring(0,point1);//month
		String stAm0=st.substring(point1+1,point2);//amount0
		String stAm1=st.substring(point2+1,point3);//amount1
		String stAm2=st.substring(point3+1,st.length());//amount2

		int month = Integer.parseInt(stMonth);//convert to integer
		int amount0 = Integer.parseInt(stAm0);//convert to integer
		int amount1 = Integer.parseInt(stAm1);//convert to integer
		int amount2 = Integer.parseInt(stAm2);//convert to integer
		
		Expense tempEx=new Expense(month,amount0,amount1,amount2);//new Expense
		
		return tempEx;
	}
	private void addNewExpens(Expense ex)
	//The method receives an object Expense, and add it to the list and writes the updated to file
	{	 
		String dataFormFile=read();//all the data from the file
		linkExpense=expenseManager.getAllExpenses(dataFormFile);//convert the string from file to linkExpense
		
		try { 
			linkExpense=expenseManager.addExpense(linkExpense, ex);//add new object expenses
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		write();// write on file all the expenses (with the updata)
	}
	private void write()
	//The method write to file the linked list of expenses
	//Example: Conversion$month#amount#pay%month#amount#pay% 
	{
		//write Conversion
		Double cov=expenseManager.getConversion();
		String allObjExpens=cov.toString()+"$";//set the conv in the string
		
		if (linkExpense!=null)//if the list don't empty
			allObjExpens+=expenseToJSON();//Convert the data to JSON Object
		
		try 
		{	//write on file
			FileOutputStream fou=openFileOutput(FILENAME, MODE_PRIVATE);
			OutputStreamWriter osw=new OutputStreamWriter(fou);
			osw.write(allObjExpens);
			osw.flush();
			osw.close(); 
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	private String read()
	//The method reads from a file, and returns a string
	{
		int date_block=100;
		String final_date="";
		
		try 
		{//read from file
			FileInputStream fis=openFileInput(FILENAME);
			InputStreamReader isr=new InputStreamReader(fis);

			char[] date=new char[date_block];
			int size;
			while((size=isr.read(date))>0)
			{
				String read_date=String.copyValueOf(date, 0, size);
				final_date+=read_date;
				date=new char[date_block];
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return final_date;
	}
	private void clearFile()
	//The method deletes the information from the file
	{		
		try 
		{	
			FileOutputStream fou=openFileOutput(FILENAME, MODE_PRIVATE);
			OutputStreamWriter osw=new OutputStreamWriter(fou);
			osw.write("");
			osw.flush();
			osw.close(); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		linkExpense=null;
		write();//write Conversion
	}
	protected void initializeVars()
	//initialize Variables
	{
		showData=(Button) findViewById(R.id.bShowData); showData.setOnClickListener(this);
		addBill=(Button) findViewById(R.id.bAddBill);addBill.setOnClickListener(this);
		bUpData=(Button) findViewById(R.id.bUpData); bUpData.setOnClickListener(this);
		linkExpense=expenseManager.getAllExpenses(read());//read the data from the file
	}
    public boolean onCreateOptionsMenu(Menu menu) 
    {  
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu  
        return true;  
    }
    public boolean onOptionsItemSelected(MenuItem item) 
    //The method checks which button is pressed on the menu
    {  
        switch (item.getItemId()) 
        {  
            case R.id.itemDeleteFile://Press "delete file"  
    			Intent intentPassword=new Intent(ElectrBill.this,password.class);
    			startActivityForResult(intentPassword, 4);// Activity is started with requestCode 4
            return true;     
  
            case R.id.itemSetConv: //Press "set cover"   

    			Intent intentSetConv=new Intent(ElectrBill.this,coversionrate.class);
    			String data=expenseManager.getConversion()+"";
    			intentSetConv.putExtra("key", data);
    			startActivityForResult(intentSetConv, 3);// Activity is started with requestCode 3
              return true;        
  
              default:  
                return super.onOptionsItemSelected(item);  
        }  
    }
    public String expenseToJSON()
    //The method Convert the linkList to JSON Object
    {  	
    	try
    	{
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		for (int i=0; i<linkExpense.size(); i++) 
		{
			JSONObject pnObj = new JSONObject();
			pnObj.put("month", linkExpense.get(i).getMonth());
			
			pnObj.put("amount0", linkExpense.get(i).getAmountOwner());
			pnObj.put("pay0", linkExpense.get(i).getPayOwner());
			
			pnObj.put("amount1", linkExpense.get(i).getAmountApart1());
			pnObj.put("pay1", linkExpense.get(i).getPayApart1());
			
			pnObj.put("amount2", linkExpense.get(i).getAmountApart2());
			pnObj.put("pay2", linkExpense.get(i).getPayApart2());
			jsonArr.put(pnObj);
		}
		jsonObj.put("Expense", jsonArr);
		return jsonObj.toString();
    	}
		catch(JSONException ex) {
			ex.printStackTrace();
		}
		return null;
    }

    
}//end
