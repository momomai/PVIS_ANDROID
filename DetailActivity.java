package com.psu.pvis_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity{

	private ImageButton ibBack;
	private TextView tvActivityName, tvFac, tvContact;
	private TextView tvTel, tvObjective, tvDetail;
	private ImageView imActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detil_activity);
		
		ibBack = (ImageButton) findViewById(R.id.ibBack);
		imActivity = (ImageView) findViewById(R.id.tv_Pic);
		tvActivityName = (TextView) findViewById(R.id.tv_Name);
		tvFac = (TextView) findViewById(R.id.tv_Fac);
		tvContact = (TextView) findViewById(R.id.tv_Contact);
		tvTel = (TextView) findViewById(R.id.tv_Tel);
		tvObjective = (TextView) findViewById(R.id.tv_Object);
		tvDetail = (TextView) findViewById(R.id.tv_Detail);
		
		ibBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
					finish();
			}
		});
	}
}
