package com.example.billdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//Department deletes the information from the file
public class password extends Activity implements OnClickListener
{
	EditText etPassword;//EditText 
	Button bPassword;//Button 
	
	protected void onCreate(Bundle savedInstanceState) 
	// the method running when passe to this class
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		
		//initialize Variables
		bPassword=(Button) findViewById(R.id.bPassword);
		bPassword.setOnClickListener(this);
		
		etPassword=(EditText) findViewById(R.id.etPassword);
	} 
	public void onClick(View v) 
	//The method operates listener buttons
	{	
		String	st=etPassword.getText().toString();
		if (v.getId()==R.id.bPassword)//press "ok"
		{
			Intent intentPasword=new Intent();
 
			if (st.equals("1234"))//Checks if the user clicks the correct password
				intentPasword.putExtra("key","1");//delete file
			else
				intentPasword.putExtra("key","0");
			
			setResult(4,intentPasword);//Activity is started with requestCode 4
			finish();//finishing activity 
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	
	    	Intent intentPasword=new Intent();
	    	intentPasword.putExtra("key","0");
			setResult(4,intentPasword);//Activity is started with requestCode 4        
			finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}//end
