package com.psu.pvis;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.entry.HelppingResultEntry;
import com.psu.entry.InformationEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;
import com.psu.pvis.InformationFragment.Holder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HelpingActivity extends Activity {

	private ListView lvHelpping;
	private JSONArray jsArray;
	private String value;
	private ArrayList<HelppingResultEntry> listdata = new ArrayList<HelppingResultEntry>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.helpping_item);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		lvHelpping = (ListView) findViewById(R.id.lvHelpping);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			value = extras.getString("HelpingID");

		}
		Log.i("value", value.toString());
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("HelpingID", extras
				.getString("HelpingID")));
		String result = HttpHelper.GetString(String.format(
				"http://%s/pvis2/result_spinner.php?HelpingID=%s", Config.Ip,
				value), null);
		

			try {
				Log.i("I", "5");
				jsArray = new JSONArray(result);
				if (jsArray.length() > 0)
					Log.i("I", "6");
				{
					for (int i = 0; i < jsArray.length(); i++) {
						Log.i("I", i + "");
						JSONObject jsObj = jsArray.getJSONObject(i);
						listdata.add(new HelppingResultEntry(jsObj
								.getString("RequestID"), jsObj
								.getString("HelpingName"), jsObj
								.getString("HelpingID"), jsObj
								.getString("ProjectName"), jsObj
								.getString("RequestName"), jsObj
								.getString("RequestDetail"), jsObj
								.getString("RequestImage"), jsObj
								.getString("DateTimeStart"), jsObj
								.getString("DateTimeStop"), jsObj
								.getString("Counter"), jsObj
								.getString("Activate")));

					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i("I", "7");

			lvHelpping.setAdapter(new LvViewDataBaseAdapter());
			Log.i("I", "8");
			
			


			/*Toast.makeText(getApplicationContext(), "ไม่มีข้อมูลในรายการนี้",
					Toast.LENGTH_LONG).show();*/
			
		

		Log.i("I", "4");
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#00008B"));
		getActionBar().setBackgroundDrawable(colorDrawable);
		getActionBar().setTitle("ความช่วยเหลือ");
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);

	}

	public class LvViewDataBaseAdapter extends BaseAdapter {
		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listdata.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Log.i("I", "9");
			if (convertView == null) {

				holder = new Holder();

				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.lv_helping_item, null);

				holder.tvActName = (TextView) convertView
						.findViewById(R.id.tvActName);
				holder.tvRequest = (TextView) convertView
						.findViewById(R.id.tvRequest);
				holder.tvHelping = (TextView) convertView
						.findViewById(R.id.tvHelping);

				/*
				 * holder.imgNew = (ImageView) convertView
				 * .findViewById(R.id.imgNew);
				 */
				
				  holder.btnDetail2 = (TextView) convertView
				 .findViewById(R.id.btnDetail2);
				 
				/*
				 * holder.tvImage = (ImageView) convertView
				 * .findViewById(R.id.tvImage);
				 */

				convertView.setTag(holder);

			} else {

				holder = (Holder) convertView.getTag();
			}

			final HelppingResultEntry item = listdata.get(position);

			holder.tvActName.setText("โครงการ : " + item.getProjectName());
			holder.tvRequest.setText("คำร้องขอ : " + item.getRequestName());
			holder.tvHelping.setText("ประเภท : " + item.getHelpingName());
			Log.i("I", "17.1");
			holder.btnDetail2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("I", "17.2");
					Intent intent = new Intent(HelpingActivity.this, DetailHelpingActivity.class);
					Log.i("I", "17.3");
					intent.putExtra("RequestID", item.getRequestID());
					
					startActivity(intent);
					//Log.i("I", item.getActivityName());
				}
			});
			

			Log.i("I", "18");

			return convertView;

		}

	}

	public class Holder {
		public TextView btnDetail2;
		public TextView tvActName, tvRequest, tvHelping;
		
		public ImageView imgNew;
		public ImageView tvImage;

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
