package com.example.billdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//Department receives from user new  of Conversion value 
public class coversionrate extends Activity implements OnClickListener
{
 
	EditText etSetConver;//EditText in coversionrate.xml
	Button bSetConver;//Button in coversionrate.xml
	
	protected void onCreate(Bundle savedInstanceState) 
	// the method running when passe to this class
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conversionrate);
		 
		//initialize Variables
		bSetConver=(Button) findViewById(R.id.bSetConver);
		bSetConver.setOnClickListener(this);
		etSetConver=(EditText) findViewById(R.id.etSetConver);
		String data=getIntent().getExtras().getString("key");//the data (coversionrate)
		etSetConver.setText(data);
	}
	public void onClick(View v) 
	//The method operates listener buttons
	{
		if (v.getId()==R.id.bSetConver)//press "ok"
		{
			String stNewConver=etSetConver.getText().toString();//Conversion		
			Intent intentNenConv=new Intent();
			intentNenConv.putExtra("key",stNewConver);//put the data with the intent
			setResult(3,intentNenConv); //Activity is started with requestCode 3 
			finish();//finishing activity  
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	Intent intentNenConv=new Intent();
			intentNenConv.putExtra("key","");//put the data with the intent
			setResult(3,intentNenConv); //Activity is started with requestCode 3 
	        finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}//end
