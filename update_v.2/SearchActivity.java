package com.psu.project_pvis;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.psu.entry.FacultyEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class SearchActivity extends Fragment{

	private String title;
	public TabHost tbHost;
	public Spinner spFac;
	public Button btnSearch, btnReset;
	public ListView lvResult;
	public SearchEventActivity searchClick;
	private Context context;
	public ArrayList<FacultyEntry> items;
	
	public SearchActivity(Context context, String title) {
		super();
		this.title = title;
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		if (container == null) {
			return null;
		}
		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.search_activity, container, false);
		
		btnSearch = (Button) ll.findViewById(R.id.btnSearch); 
		btnSearch.setOnClickListener(btnSearch_OnClick);
		btnReset = (Button) ll.findViewById(R.id.btnReset);
		btnReset.setOnClickListener(btnReset_OnClick);
		
		spFac = (Spinner) ll.findViewById(R.id.spFaculty);
		lvResult = (ListView) ll.findViewById(R.id.lvResult);
		
		TextView tvTitle = (TextView) ll.findViewById(R.id.tvTitle);
		tvTitle.setText(title);
		tvTitle.setBackgroundColor(Color.parseColor("#FF0000"));
		
		String result = HttpHelper.GetString(String.format("http://%s/Rest/search.php",Config.Ip), null);
		Log.i("RESULT", result);
		
		
		try {
			JSONArray JSArr = new JSONArray(result);
			items = new ArrayList<FacultyEntry>();

			for (int i = 0; i < JSArr.length(); i++) {
				JSONObject JSObj = JSArr.getJSONObject(i);
				items.add(new FacultyEntry(JSObj.getString("FacultyName")));
			}
			spFac.setAdapter(new FacAdapter());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("FINDVIEW", "lvResult");
		
		spFac.setEnabled(true);
		
		return ll;
	}
	
	View.OnClickListener btnReset_OnClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			spFac.setEnabled(false);
		}
	};

	View.OnClickListener btnSearch_OnClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (searchClick != null) {
				searchClick.onHandler(v);
			}
		}
	};
	
	public class FacAdapter extends BaseAdapter {

		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
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
				LinearLayout ll = new LinearLayout(context);
				holder = new Holder();
				holder.Title = new TextView(context);
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
			holder.Title.setText(items.get(position).getFacName());
			return v;
		}
	}
	public class Holder {
		public TextView Title;
	}
}
interface SearchEventActivity {
	public void onHandler(Object x);
}
