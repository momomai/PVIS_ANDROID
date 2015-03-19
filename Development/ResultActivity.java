package com.psu.pvis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

public class ResultActivity extends Activity {

	private ListView lvResult;
	private JSONArray jsArr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lv_result);

		Bundle bundle = getIntent().getExtras();
		String LocName = bundle.getString("LocationName");
		String LocAddress = bundle.getString("LocationAddress");
		//String ProName = bundle.getString("ProjectName");

		lvResult = (ListView) findViewById(R.id.lvResult);

		String result = HttpHelper.GetString(String
				.format("http://%s/pvis2/search_new.php?LocationName="
						+ LocName + "&LocationAddress=" + LocAddress, Config.Ip), null);

		ArrayList<String> listdata = new ArrayList<String>();
		try {
			jsArr = new JSONArray(result);
			for (int i = 0; i < jsArr.length(); i++) {
				JSONObject jsobj = jsArr.getJSONObject(i);
				listdata.add(jsobj.getString("LocationName"));
				listdata.add(jsobj.getString("LocationAddress"));
				listdata.add(jsobj.getString("HelpingName"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lvResult.setAdapter(new LvDataBaseAdapter(listdata));
	}

	public class LvDataBaseAdapter extends BaseAdapter {
		private Holder holder;
		private ArrayList<String> listdata;

		LvDataBaseAdapter(ArrayList<String> listdata) {
			this.listdata = listdata;
		}

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
			
			Log.i("GETVIEW", "BEGIN");
			
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lv_result_search, null);
				holder.tvLocationname = (TextView) convertView.findViewById(R.id.tvLocName);
				holder.tvLocationaddress = (TextView) convertView.findViewById(R.id.tv_Locationaddress);
				holder.tvHelpingName = (TextView) convertView.findViewById(R.id.tv_Helpingname);
				//holder.tvRequestname = (TextView) convertView.findViewById(R.id.tvRequest_name);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
				holder.tvMap = (TextView) convertView.findViewById(R.id.tvMap);

				holder.tvLocationname.setTextColor(Color.parseColor("#FF0000"));
				holder.tvLocationaddress.setTextColor(Color.parseColor("#000033"));
				convertView.setTag(holder);
			} else {

				holder = (Holder) convertView.getTag();
			}

			final JSONObject jsObj;

			try {
				Log.i("JSONOBJ", jsArr.get(position).toString());
				jsObj = jsArr.getJSONObject(position);

				/*holder.tvLocationname.setText(jsObj.getString("LocationName"));
				holder.tvLocationaddress.setText(jsObj.getString("LocationAddress"));
				holder.tvHelpingName.setText(jsObj.getString("HelpingName"));*/
				
				holder.tvLocationname.setText(listdata.get(0));
				holder.tvLocationaddress.setText(listdata.get(1));
				holder.tvHelpingName.setText(listdata.get(2));


				// final LocationEntry items = listdata.get(position);

				holder.tvDetail.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						
					Intent intent = new Intent(getApplicationContext(),DetailSearchActivity.class);
					
					try {
						intent.putExtra("HelpingID", jsObj.getString("HelpingID"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					getApplicationContext().startActivity(intent);
					}
				});
				
				holder.tvMap.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						/*Intent intent = new Intent(getApplicationContext(), MapActivity.class);
						startActivity(intent);*/
					}
				});

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertView;
		}

		public class Holder {
			public TextView tvLocationname;
			public TextView tvLocationaddress;
			public TextView tvHelpingName;
			public TextView tvMap;
			public TextView tvDetail;
		}
	}
}
