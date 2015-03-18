package com.psu.pvis;



import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.entry.ActivityEntry;
import com.psu.entry.InformationEntry;


import com.psu.helper.HttpHelper;
import com.psu.helper.Config;
import com.psu.pvis.InformationFragment.Holder;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityFragment extends Fragment {
	
	private ListView lvActivity;
	
	private JSONArray jsArray;
	private ArrayList<ActivityEntry> data = new ArrayList<ActivityEntry>();
public ActivityFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);
         
        if(android.os.Build.VERSION.SDK_INT > 9){
        	
        	
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        
       lvActivity = (ListView) rootView.findViewById(R.id.lvActivity);
    
         
       
       String result = HttpHelper.GetString(String.format("http://%s/pvis2/Activity.php",Config.Ip), null);
       
       
       try{
    	   
    	   jsArray = new JSONArray(result);
			if(jsArray.length()>0)
				Log.i("I", "6");
			{
				for(int i=0; i<jsArray.length(); i++){
					Log.i("I",i+ "");
					JSONObject jsObj = jsArray.getJSONObject(i);
					data.add(new ActivityEntry(
							jsObj.getString("ActivityID"),
							jsObj.getString("ActivityName"),
							jsObj.getString("ActivityDetail"),
							jsObj.getString("ActivityDateStart"),
							jsObj.getString("ActivityDateStop"),
							jsObj.getString("LocationID"),
							jsObj.getString("LocationName"),
							jsObj.getString("LocationAddress")));			
				}
			}
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
       }
      
       
       lvActivity.setAdapter(new LvViewDataBaseAdapter());
        return rootView;
    }
	public class LvViewDataBaseAdapter extends BaseAdapter{
		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
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
			 
			if(convertView == null){
				 Log.i("I", "10");
				holder = new Holder();
				 Log.i("I", "11");
				convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.lv_activity_item, null);
				 Log.i("I", "12");
				holder.tvActName = (TextView) convertView.findViewById(R.id.tvActName);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
				holder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
				holder.btnDetail = (TextView) convertView.findViewById(R.id.btnDetail2);
				
				 Log.i("I", "13");
			
				convertView.setTag(holder);
				 Log.i("I", "14");
			}else{
				 Log.i("I", "15");
				holder = (Holder) convertView.getTag();
			}
			 Log.i("I", "16");
				final ActivityEntry item = data.get(position);
				 Log.i("I", "17");
				holder.tvActName.setText("ActivityName: "+item.getActivityName());
				holder.tvDetail.setText("DetailActivity: "+item.getActivityDetail());
				holder.tvLocation.setText("Location: "+item.getLocationName()+" "+item.getLocationAddress());
				Log.i("I", "18");
				
				holder.btnDetail.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), DetailActivity.class);
						
						intent.putExtra("ActivityName", item.getActivityName());
						
						getActivity().startActivity(intent);
						Log.i("I", item.getActivityName());
					}
				});
			
				
				
				
			return convertView;
			
			
		}

		}
	public class Holder{
		public TextView tvActName,tvLocation;
		public TextView tvDetail,btnDetail;
		
		
	}
}
