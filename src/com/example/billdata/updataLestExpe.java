package com.example.billdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
//Department allows updating the last element that was added to the program
public class updataLestExpe extends Activity implements OnClickListener,OnItemSelectedListener
{

	String[] arrMonth = { "ינואר-פברואר", "מרץ-אפריל", "מאי-יוני","יולי-אוגוסט", "ספטמבר-אוקטובר", "נובמבר-דצמבר"}; 
	
	EditText etCounterBillUpdata0,etCounterBillUpdata1,etCounterBillUpdata2;//EditText in addbill.xml
	Button bSaveNewBillUpdata;//Button addbill.xml
	int indexMonth;
	protected void onCreate(Bundle savedInstanceState) 
{
		// the method running when passe to this class
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatalestexp);
		
		//initialize Variables
		bSaveNewBillUpdata=(Button) findViewById(R.id.bSaveNewBillUpdata);
		bSaveNewBillUpdata.setOnClickListener(this);


		etCounterBillUpdata0=(EditText) findViewById(R.id.etCounterBillUpdata0);
		etCounterBillUpdata1=(EditText) findViewById(R.id.etCounterBillUpdata1);
		etCounterBillUpdata2=(EditText) findViewById(R.id.etCounterBillUpdata2);
		
		//Converts the string
		String data=getIntent().getExtras().getString("key");//the data (month#counter)		
		int point1=-1; int point2=-1; int point3=-1;
		for(int i=0; i<data.length(); i++)
			switch (data.charAt(i))
			{
				case '#': point1=i;	break;
				case '%': point2=i;	break;
				case '$': point3=i;	break;			
			}
		
		String stMonth=data.substring(0,point1); indexMonth=Integer.parseInt(stMonth);//month
		String stAm0=data.substring(point1+1,point2);etCounterBillUpdata0.setText(stAm0);//amount0
		String stAm1=data.substring(point2+1,point3);etCounterBillUpdata1.setText(stAm1);//amount1
		String stAm2=data.substring(point3+1,data.length());etCounterBillUpdata2.setText(stAm2);//amount2
		
		//initialize the spinnerMonth
        Spinner spin = (Spinner) findViewById(R.id.spinnerMonth);  
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrMonth);  
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        spin.setAdapter(aa); 
        spin.setSelection(indexMonth);
        
	}
	public void onClick(View v) 
	{
		//The method operates listener buttons
		if (v.getId()==R.id.bSaveNewBillUpdata)//press "ok"
		{			
			String stCounter0=etCounterBillUpdata0.getText().toString();//Counter/amount owner
			String stCounter1=etCounterBillUpdata1.getText().toString();//Counter/amount apart1
			String stCounter2=etCounterBillUpdata2.getText().toString();//Counter/amount apart2
			
			String st=indexMonth+"#"+stCounter0+"%"+stCounter1+"$"+stCounter2;// the string to the main
						
			Intent intentupdataLestExpe=new Intent();
			intentupdataLestExpe.putExtra("key",st);//put the data with the intent
			setResult(5,intentupdataLestExpe);//Activity is started with requestCode 5
			finish();//finishing activity  
		}	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
	    {			
			String stCounter0=etCounterBillUpdata0.getText().toString();//Counter/amount owner
			String stCounter1=etCounterBillUpdata1.getText().toString();//Counter/amount apart1
			String stCounter2=etCounterBillUpdata2.getText().toString();//Counter/amount apart2
			
			String st=indexMonth+"#"+stCounter0+"%"+stCounter1+"$"+stCounter2+"*";// the string to the main
			
			Intent intentupdataLestExpe=new Intent();
			intentupdataLestExpe.putExtra("key",st);//put the data with the intent
			setResult(5,intentupdataLestExpe);//Activity is started with requestCode 5
			finish();//finishing activity 
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
		indexMonth=position;
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
}//end
