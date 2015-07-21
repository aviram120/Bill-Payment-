package com.example.billdata;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//Department receives from user new  of Expenes
public class addBill extends Activity implements OnClickListener,OnItemSelectedListener
{

	String[] arrMonth = { "ינואר-פברואר", "מרץ-אפריל", "מאי-יוני","יולי-אוגוסט", "ספטמבר-אוקטובר", "נובמבר-דצמבר"}; 

	EditText etCounterBill0,etCounterBill1,etCounterBill2;//EditText in addbill.xml
	Button bSaveNewBill;//Button addbill.xml
	int indexMonth;
	
	protected void onCreate(Bundle savedInstanceState) 
	// the method running when passe to this class
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbill);
		
		//initialize Variables
		bSaveNewBill=(Button) findViewById(R.id.bSaveNewBill);
		bSaveNewBill.setOnClickListener(this);
		
		etCounterBill0=(EditText) findViewById(R.id.etCounterBill0);
		etCounterBill1=(EditText) findViewById(R.id.etCounterBill1);
		etCounterBill2=(EditText) findViewById(R.id.etCounterBill2);
		
		//initialize the spinnerMonth
        Spinner spin = (Spinner) findViewById(R.id.spinnerMonth);  
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrMonth);  
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        spin.setAdapter(aa);  
	} 
	public void onClick(View v) 
	//The method operates listener buttons
	{
		String st="";
		
		//Checking if the EditText is empty 
		boolean flag=false;		
		if ((etCounterBill0.getText().length()==0)||
				(etCounterBill1.getText().length()==0)||
				(etCounterBill1.getText().length()==0))
			flag=true;


		if (v.getId()==R.id.bSaveNewBill)//press "ok"
		{					
			if (flag)
				st="#";//if the EditText is empty 
			else
			{
				String stCounter0=etCounterBill0.getText().toString();//Counter/amount owner
				String stCounter1=etCounterBill1.getText().toString();//Counter/amount apart1
				String stCounter2=etCounterBill2.getText().toString();//Counter/amount apart2

				st=indexMonth+"#"+stCounter0+"%"+stCounter1+"$"+stCounter2;// the string to the main
			}

			Intent intentAddBill=new Intent();
			intentAddBill.putExtra("key",st);//put the data with the intent
			setResult(2,intentAddBill);//Activity is started with requestCode 2  
			finish();//finishing activity  
		}	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	Intent intentAddBill=new Intent();
	    	intentAddBill.putExtra("key","#");//put the data with the intent
			setResult(2,intentAddBill); //Activity is started with requestCode 2
	        finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) 
    {  	
       // Toast.makeText(getApplicationContext(),arrMonth[position] ,Toast.LENGTH_SHORT).show();
        indexMonth=position;    
    }  
  
 
    public void onNothingSelected(AdapterView<?> arg0) {  
        // TODO Auto-generated method stub  
          
    } 
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.menuspinner, menu);  
        return true;  
    }
}//end
