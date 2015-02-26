package com.psu.project_pvis;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;

public class PageSearchActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_search);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}
}
