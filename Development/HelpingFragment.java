package com.psu.pvis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.psu.entry.HelpingEntry;
import com.psu.entry.InformationEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;



import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HelpingFragment extends Fragment {
public HelpingFragment(){}
private Spinner spHep;
private TextView btnSearch;
private JSONArray jsArray;
private ArrayList<HelpingEntry> listdata = new ArrayList<HelpingEntry>();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_helping, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

        spHep = (Spinner) rootView.findViewById(R.id.spHep);
        btnSearch = (TextView) rootView.findViewById(R.id.btnSearch);
		Log.i("I", "3");

		String result = HttpHelper.GetString(
				String.format("http://%s/pvis2/helping_spinner.php", Config.Ip),
				null);
		Log.i("I", "4");
		
		
		try {
			JSONArray JSArr = new JSONArray(result);
			listdata = new ArrayList<HelpingEntry>();
			Log.i("FINDVIEW", "lvResult4");
			for (int i = 0; i < JSArr.length(); i++) {

				final JSONObject JSObj = JSArr.getJSONObject(i);

				listdata.add(new HelpingEntry(
						JSObj.getString("HelpingName"),
						JSObj.getString("HelpingID")
						));
				spHep.setAdapter(new HelpAdapter());
				
				Log.i("FINDVIEW", "lvResult5");
				
				btnSearch.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						try {
							Intent intent = new Intent(getActivity(), HelpingActivity.class);
							intent.putExtra("HelpingID", listdata.get(spHep.getSelectedItemPosition()).getHelpingID());
							
							getActivity().startActivity(intent);
							Log.i("I", JSObj.getString("HelpingID"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					}
				});
				
			}
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();

		}
		
		
		
		
        return rootView;
    }
	public class HelpAdapter extends BaseAdapter{
		private Holder holder;
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listdata.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View v, ViewGroup vg) {
			// TODO Auto-generated method stub

			if (v == null) {
				LinearLayout ll = new LinearLayout(getActivity());
				holder = new Holder();
				final HelpingEntry item = listdata.get(position);
				holder.Title = new TextView(getActivity());
				holder.Title.setGravity(Gravity.CENTER);
				holder.Title.setTextColor(Color.parseColor("#000000"));
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				lp.gravity = Gravity.CENTER;
				lp.setMargins(0, 20, 0, 20);
				ll.addView(holder.Title, lp);
				v = ll;
				v.setTag(holder);
			} else {
				holder = (Holder) v.getTag();
			}
			holder.Title.setText(listdata.get(position).getHelpingName());
			
			
			
			
			return v;
		}
		
		}
	public class Holder{
		
		public TextView Title;
		
		
	}
}
