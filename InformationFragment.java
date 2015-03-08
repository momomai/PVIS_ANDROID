package com.psu.pvis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.psu.entry.InformationEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InformationFragment extends Fragment {
	
	private ListView lvView;
	private JSONArray jsArray;
	private ArrayList<InformationEntry> listdata = new ArrayList<InformationEntry>();
	
	public InformationFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		Log.i("I", "1");
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        Log.i("I", "2");
        if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
        
        lvView = (ListView) rootView.findViewById(R.id.lvInformation);
        Log.i("I", "3");
        
        String result = HttpHelper.GetString(String.format("http://%s/index2.php", Config.Ip), null);
        Log.i("I", "4");
        try{
        	Log.i("I", "5");
			jsArray = new JSONArray(result);
			if(jsArray.length()>0)
				Log.i("I", "6");
			{
				for(int i=0; i<jsArray.length(); i++){
					Log.i("I",i+ "");
					JSONObject jsObj = jsArray.getJSONObject(i);
					listdata.add(new InformationEntry(
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
        Log.i("I", "7");
        lvView.setAdapter(new LvViewDataBaseAdapter());
        Log.i("I", "8");
        return rootView;
    }
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
			 Log.i("I", "9");
			if(convertView == null){
				 Log.i("I", "10");
				holder = new Holder();
				 Log.i("I", "11");
				convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.lv_information_item, null);
				 Log.i("I", "12");
				holder.tvActName = (TextView) convertView.findViewById(R.id.tvActName);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
				
				 Log.i("I", "13");
			
				convertView.setTag(holder);
				 Log.i("I", "14");
			}else{
				 Log.i("I", "15");
				holder = (Holder) convertView.getTag();
			}
			 Log.i("I", "16");
				final InformationEntry item = listdata.get(position);
				 Log.i("I", "17");
				holder.tvActName.setText("ActivityName: "+item.getActivityName());
				holder.tvDetail.setText("DetailActivity: "+item.getDetailActivity());
				
				
				Log.i("I", "18");
				
			
				
				
				
			return convertView;
			
			
		}

		}
	public class Holder{
		public TextView tvActName;
		public TextView tvDetail,btnDetail;
		
		
	}

}

	
