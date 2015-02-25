package com.psu.projectii;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

public class DetailActivity extends Activity {
	
	private ImageButton ibBack;
	private TextView tvActivityName, tvFac, tvContact;
	private TextView tvTel, tvObjective, tvDetail;
	private ImageView imActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
			
			ibBack = (ImageButton) findViewById(R.id.ibBack);
			imActivity = (ImageView) findViewById(R.id.tv_Pic);
			tvActivityName = (TextView) findViewById(R.id.tv_Name);
			tvFac = (TextView) findViewById(R.id.tv_Fac);
			tvContact = (TextView) findViewById(R.id.tv_Contact);
			tvTel = (TextView) findViewById(R.id.tv_Tel);
			tvObjective = (TextView) findViewById(R.id.tv_Object);
			tvDetail = (TextView) findViewById(R.id.tv_Detail);

			Log.i("TEST","1");
			
			Intent intent= getIntent();
			Log.i("TEST","2");
			final String activityID = intent.getStringExtra("ActivityID");
			Log.i("TEST","3");
			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
			Log.i("TEST","4");
			data.add(new BasicNameValuePair("ActivityID", activityID));

			//data.add(new BasicNameValuePair("ActivityID", activityID.getString("ActivityID")));

			String result = HttpHelper.GetString(String.format("http://%s/172.19.133.208/Rest/index.php?rnd=%s&r=%s", 
					Config.Ip,Math.random()), data);

			Log.i("RESULT", result);
			
			JSONArray jsArr;
			try {
				jsArr = new JSONArray(result);
				if (jsArr.length() == 1) {
					JSONObject jsObj = jsArr.getJSONObject(0);

					tvActivityName.setText(jsObj.getString("ActivityName"));

					//tvFac.setText(jsObj.getString("DetailActivity"));
					
					//tvContact.setText(jsObj.getString("TITLE"));

					//tvTel.setText(jsObj.getString("PAGES"));

					tvObjective.setText(jsObj.getString("Objective"));

					tvDetail.setText(jsObj.getString("DetailActivity"));

				}
				ibBack.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						finish();
					}
				});
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
