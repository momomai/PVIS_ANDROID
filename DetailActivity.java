package com.psu.pvis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	
	private TextView tvActName, tvDetail, tvLocation, tvDstart,tvDstop;
	private String value;
	private TextView tvImage, btnMap, btnVideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_item_detail);
		

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		btnVideo = (TextView) findViewById(R.id.btnVideo);
		tvActName = (TextView) findViewById(R.id.tvActName);
		tvDetail = (TextView) findViewById(R.id.tvDetail);
		tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvDstart = (TextView) findViewById(R.id.tvDstart);
		tvDstop = (TextView) findViewById(R.id.tvDstop);
		
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
		            value = extras.getString("ActivityName");
		            
		}
		Log.i("value", value.toString());
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("ActivityName", extras.getString("ActivityName")));
		;
		
		  String result = HttpHelper.GetString(String.format("http://%s/pvis2/ActivityDetail.php?ActivityName=%s",Config.Ip,value), null);
		  Log.i("RESULT", result);
		  JSONArray JsArray;
		  Log.i("IO","2");
		  try{
			  Log.i("IO","3");
			  JsArray = new JSONArray(result);
			  Log.i("IO","4");
			  
			
			  if(JsArray.length() == 1){
				  Log.i("IO","5");
				  JSONObject jsObj = JsArray.getJSONObject(0);
				  tvActName.setText("กิจกรรม: "+jsObj.getString("ActivityName"));
				  tvDetail.setText("รายละเอียด: "+jsObj.getString("ActivityDetail"));
				  tvLocation.setText("สถานที่ตั้ง: "+jsObj.getString("LocationName")+" "+jsObj.getString("LocationAddress"));
				  tvDstart.setText("วันเริ่มต้น: "+jsObj.getString("ActivityDateStart"));
				  tvDstop.setText("วันสิ้นสุด: "+jsObj.getString("ActivityDateStop"));
				  
				  Log.i("IO","6");
			  }
			  
			  
			  
			  
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
			  
		  }
		btnVideo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetailActivity.this,VideoViewActivity.class);
				startActivity(intent);
			}
		});
	}

	private void length() {
		// TODO Auto-generated method stub
		
	}
}
