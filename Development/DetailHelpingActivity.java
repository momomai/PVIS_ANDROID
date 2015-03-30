package com.psu.pvis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;
import com.squareup.picasso.Picasso;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHelpingActivity extends Activity {
	private TextView tvPrjName, tvHelpName, tvReq, tvDstart, tvDstop, tvDetail,
			tvTel;
	private String value;
	private ImageView tvImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.helping_item_detail);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		tvPrjName = (TextView) findViewById(R.id.tvPrjName);
		tvHelpName = (TextView) findViewById(R.id.tvHelpName);
		tvDetail = (TextView) findViewById(R.id.tvDetail);
		tvReq = (TextView) findViewById(R.id.tvReq);
		tvDstart = (TextView) findViewById(R.id.tvDstart);
		tvDstop = (TextView) findViewById(R.id.tvDstop);
		tvImage = (ImageView) findViewById(R.id.tvImage);
		tvTel = (TextView) findViewById(R.id.tvTel);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			value = extras.getString("RequestID");

		}
		Log.i("value", value.toString());
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("RequestID", extras
				.getString("RequestID")));

		String result = HttpHelper.GetString(String.format(
				"http://%s/pvis2/request_result.php?RequestID=%s", Config.Ip,
				value), null);
		Log.i("RESULT", result);
		JSONArray JsArray;
		Log.i("IO", "2");
		try {
			Log.i("IO", "3");
			JsArray = new JSONArray(result);
			Log.i("IO", "4");

			if (JsArray.length() == 1) {
				Log.i("IO", "5");
				final JSONObject JSObj = JsArray.getJSONObject(0);
				tvPrjName.setText(JSObj.getString("ProjectName"));
				tvHelpName.setText(JSObj.getString("HelpingName"));
				tvReq.setText(JSObj.getString("RequestName"));
				
				tvDetail.setText(JSObj.getString("RequestDetail"));
				tvTel.setText(JSObj.getString("Counter"));
				tvDstart.setText("วันที่เริ่มกิจกรรม : "
						+ JSObj.getString("DateTimeStart"));
				tvDstop.setText("วันที่สิ้นสุดกิจกรรม : "
						+ JSObj.getString("DateTimeStop"));
				String imgurl = HttpHelper.GetString(String.format(
						"http://%s/pvis2/imageActivity.php?ImageActivity=%s",
						Config.Ip, JSObj.getString("ImageActivity")), null);
				Log.i("url", String.format(
						"http://%s/pvis2/imageActivity.php?ImageActivity=%s",
						Config.Ip, JSObj.getString("ImageActivity")));
				Picasso.with(getApplicationContext())
						.load("http://projectse03.ictte-project.com/PSUPhuketVolunteer/upload/activity/"
								+ JSObj.getString("ImageActivity"))
						.into(tvImage);

				Log.i("IO", "6");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#00008B"));
		getActionBar().setBackgroundDrawable(colorDrawable);
		getActionBar().setTitle("ความช่วยเหลือ");
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
