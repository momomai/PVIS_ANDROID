package com.psu.pvis;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.psu.entry.LocationEntry;

public class SearchFragment extends Fragment  {

	private JSONArray jsArr;
	private EditText etLocationName, etLocationAddress;
	private TextView tvLocationName, tvLocationAddress;
	private Button btnSearch, btnReset;
	private ListView lvResult;
	private ArrayList<LocationEntry> listdata = new ArrayList<LocationEntry>();
	
	public SearchFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		Log.i("TEST", "1");
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
        
        Log.i("TEST", "2");
        etLocationName = (EditText) rootView.findViewById(R.id.etLocationName);
        etLocationAddress = (EditText) rootView.findViewById(R.id.etLocationAddress);
        tvLocationName = (TextView) rootView.findViewById(R.id.tvLocationName);
        tvLocationAddress = (TextView) rootView.findViewById(R.id.tvLocationAddress);
        btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        btnReset = (Button) rootView.findViewById(R.id.btnReset);
        lvResult = (ListView) rootView.findViewById(R.id.lvResult);
        Log.i("FINDVIEW", "3");
        
        Log.i("BTN", "1");
        btnSearch.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				/*ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
				// TODO Auto-generated method stub
				
				Log.i("DATA", "1");
				data.add(new BasicNameValuePair("LocationName", etLocationName.getText().toString()));
				
				Log.i("DATA", "2");
				data.add(new BasicNameValuePair("RequestName", etRequestName.getText().toString()));
				
				Log.i("DATA", "3");
				data.add(new BasicNameValuePair("ProjectName", etProjectName.getText().toString()));*/
                	//tbHost.setCurrentTab(1);

				String locationName = etLocationName.getText().toString();
				String locationAddress = etLocationAddress.getText().toString();
				Intent intent = new Intent(getActivity(), ResultActivity.class);
				
				intent.putExtra("LocationName", locationName);
				intent.putExtra("LocationAddress", locationAddress);
				//intent.putExtra("ProjectName", projectName);
				
				getActivity().startActivity(intent);
				/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
						R.layout.lv_result_search,listdata);*/
				//ResultActivity.setAdapter(new LvDataBaseAdapter(listdata));
			}
		});
        
        btnReset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				etLocationName.setText(null);
				etLocationAddress.setText(null);
			}
		});
        
        return rootView;
    }
	
	protected Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public class LvDataBaseAdapter extends BaseAdapter{
		private Holder holder;
		private ArrayList<String> listdata;
		
		LvDataBaseAdapter (ArrayList<String> listdata){
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
			Log.i("GETVIEW","BEGIN");
			if(convertView==null){
				holder = new Holder();
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.lv_result_search, null);
				holder.tvLocationname = (TextView) convertView.findViewById(R.id.tvLocName);
				holder.tvProjectname = (TextView) convertView.findViewById(R.id.tvProject_name);
				holder.tvRequestname = (TextView) convertView.findViewById(R.id.tvRequest_name);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
				holder.tvMap = (TextView) convertView.findViewById(R.id.tvMap);
				
				holder.tvLocationname.setTextColor(Color.parseColor("#FF0000"));
				holder.tvProjectname.setTextColor(Color.parseColor("#000033"));
				convertView.setTag(holder);
			}else{
				
				holder = (Holder) convertView.getTag();
			}
			
			final JSONObject jsObj;
			
			try {
				Log.i("JSONOBJ", jsArr.get(position).toString());
				jsObj = jsArr.getJSONObject(position);
				
				holder.tvLocationname.setText(listdata.get(0));
				holder.tvRequestname.setText(listdata.get(1));
				holder.tvProjectname.setText(listdata.get(2));
				
				if (jsObj.getString("MEDIA_TYPE").equals("PDF")) {
					holder.imType.setBackgroundResource(R.drawable.pdf_icon);
				}
				else if (jsObj.getString("MEDIA_TYPE").equals("MOV")) {
					holder.imType.setBackgroundResource(R.drawable.mov_icon);
				}
				else {
					holder.imType.setBackgroundResource(R.drawable.doc_icon);
				}

				holder.tvPrint.setOnClickListener(new OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
							String path =jsObj.getString("MEDIA_FILE_NAME").substring(0,jsObj.getString("MEDIA_FILE_NAME").lastIndexOf("/"))+"/";
							Log.i("PATH",path);
							String file =jsObj.getString("MEDIA_FILE_NAME").substring(jsObj.getString("MEDIA_FILE_NAME").lastIndexOf("/")+1);
							Log.i("FILE",file);
							Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format(
									"http://%s/%s%s", "192.168.0.98",path,Uri.encode(file))));
							Log.i("URL",String.format(
									"http://%s/%s%s", "192.168.0.98",path,Uri.encode(file)));
							startActivity(intent);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				//final LocationEntry items = listdata.get(position);
				
				holder.tvDetail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						Intent intent = new Intent(getActivity(), DetailInformation.class);
						
						intent.putExtra("NewsID", items.getLocatioID());
						
						getActivity().startActivity(intent);
						Log.i("I", items.getLocatioID());
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
			public TextView tvRequestname;
			public TextView tvProjectname;
			public TextView tvMap;
			public TextView tvDetail;
		}		
	}*/
}

