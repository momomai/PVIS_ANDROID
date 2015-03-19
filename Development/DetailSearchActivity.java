package com.psu.pvis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

public class DetailSearchActivity extends Activity{

	private TextView tv_locationname, tv_helpingname, tv_locationaddress;
	private TextView tv_mobile, tv_tel, tv_contactname, tv_email, tv_website;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Log.i("BEGIN", "1");
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		tv_locationname = (TextView) findViewById(R.id.tv_locationname);
		tv_helpingname = (TextView) findViewById(R.id.tv_helpingname);
		tv_locationaddress = (TextView) findViewById(R.id.tv_locationaddress);
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		tv_tel = (TextView) findViewById(R.id.tv_tel);
		tv_contactname = (TextView) findViewById(R.id.tv_contactname);
		tv_email = (TextView) findViewById(R.id.tv_email);
		tv_website = (TextView) findViewById(R.id.tv_website);
		
		Bundle bundle = getIntent().getExtras();
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();

		data.add(new BasicNameValuePair("LocationID", bundle.getString("LocationID")));
		
		String result = HttpHelper.GetString(
				String.format("http://%s/pvis2/result_location_search.php",Config.Ip,data),null);
		
		Log.i("RESULT", result);
		
		JSONArray jsArr;
		try {
			jsArr = new JSONArray(result);
			if (jsArr.length() == 1) {
				JSONObject jsObj = jsArr.getJSONObject(0);

				tv_locationname.setText(jsObj.getString("LocationName"));
				tv_locationname.setTextColor(Color.parseColor("#B22222"));

				tv_helpingname.setText(jsObj.getString("HelpingName"));
				tv_helpingname.setTextColor(Color.parseColor("#B22222"));
				
				tv_locationaddress.setText(jsObj.getString("LocationAddress"));
				tv_locationaddress.setTextColor(Color.parseColor("#B22222"));

				tv_mobile.setText(jsObj.getString("MobileNumber"));
				tv_mobile.setTextColor(Color.parseColor("#B22222"));

				tv_tel.setText(jsObj.getString("Telephone"));
				tv_tel.setTextColor(Color.parseColor("#B22222"));

				tv_contactname.setText(jsObj.getString("ContactName"));
				tv_contactname.setTextColor(Color.parseColor("#B22222"));

				tv_email.setText(jsObj.getString("Email"));
				tv_email.setTextColor(Color.parseColor("#B22222"));
				
				tv_website.setText(jsObj.getString("Website"));
				tv_website.setTextColor(Color.parseColor("#B22222"));

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
