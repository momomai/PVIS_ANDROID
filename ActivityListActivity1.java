package com.psu.pvis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.entry.ActivityEntry;


import com.psu.helper.Config;
import com.psu.helper.HttpHelper;






import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityListActivity extends Activity {
	
	
	private ListView lvView;
	private TextView btnDetail;
	private JSONArray jsArray;
	private ArrayList<ActivityEntry> listdata = new ArrayList<ActivityEntry>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lv_search_item);
		
		lvView = (ListView) findViewById(R.id.lvResult);
		btnDetail = (TextView) findViewById(R.id.btnDetail);
		Bundle bundle = getIntent().getExtras();
		
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		
		data.add(new BasicNameValuePair("addressID", bundle.getString("addressID")));
		
		
		
		
		String result = HttpHelper.GetString(
				String.format("http://%s/index2.php", Config.Ip), null);
		
		try{
			jsArray = new JSONArray(result);
			if(jsArray.length()>0)
			{
				for(int i=0; i<jsArray.length(); i++){
					Log.i("I",i+ "");
					JSONObject jsObj = jsArray.getJSONObject(i);
					listdata.add(new ActivityEntry(
							jsObj.getString("ActivityName"),
							jsObj.getString("DetailActivity"),
							jsObj.getString("Objective"),
							jsObj.getString("AddressID"),
							jsObj.getString("ActivityID")));			
				}
			}
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lvView.setAdapter(new LvViewDataBaseAdapter());
		
		
	}
	
	public class LvViewDataBaseAdapter extends BaseAdapter{
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
			if(convertView == null){
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lv_item_activity, null);
				holder.tvActName = (TextView) convertView.findViewById(R.id.tvActName);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
				holder.btnDetail = (TextView) convertView.findViewById(R.id.btnDetail);
				
			
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
				final ActivityEntry item = listdata.get(position);
				holder.tvActName.setText("ActivityName: "+item.getActivityName());
				holder.tvDetail.setText("DetailActivity: "+item.getDetailActivity());
				
				
				Log.i("I", "2");
				
				holder.btnDetail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.i("I", "3");
						Intent intent = new Intent(ActivityListActivity.this,DetailActivity.class);
						intent.putExtra("addressID", item.getAddressID());
						intent.putExtra("activityID",item.getActivityID());
						intent.putExtra("activityName", item.getActivityName());
						/*intent.putExtra("detailActivity", item.getDetailActivity());
						intent.putExtra("objective", item.getObjective());*/
						
						Log.i("I", "3");
						startActivity(intent);
					}
				});
				
				
				
			return convertView;
			
			
		}}
	public class Holder{
		public TextView tvActName;
		public TextView tvDetail,btnDetail;
		
		
	}
}
