package com.example.billdata;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class menu extends ListActivity {

		String classes[]= {"חשמל","מים","הגדרות"};

		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(menu.this, android.R.layout.simple_expandable_list_item_1, classes) );
		}

		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub

			super.onListItemClick(l, v, position, id);
		String cheese="";
		
		switch (position)
		{
			case 0:  cheese="ElectrBill"; break;
			case 1: cheese="WaterBill"; break;		
		}

			try
			{
				Class ourClass=Class.forName("com.example.billdata."+cheese);
				Intent ourIntent=new Intent (menu.this,ourClass);
				startActivity(ourIntent);
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	
	
