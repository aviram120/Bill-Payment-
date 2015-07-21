package com.example.billdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
//Department print on the screen  all Expenes data
public class showData  extends Activity
{

	protected void onCreate(Bundle savedInstanceState)
	// the method running when passe to this class
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdata);
		
		//initialize Variables
		TextView tvPrintBills=(TextView) findViewById(R.id.tvPrintBills);
		ScrollingMovementMethod srol=new ScrollingMovementMethod();
		tvPrintBills.setMovementMethod(srol);
		
		tvPrintBills.setText(getIntent().getExtras().getString("key"));
	}
}//end
