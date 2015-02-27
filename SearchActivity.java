package com.psu.pvis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.entry.AddressEntry;

import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	private Spinner spAumper, spTumbon;
	private Button btnSearch, btnReset;
	private ArrayList<AddressEntry> listdata = new ArrayList<AddressEntry>();
	private ArrayList<AddressEntry> listdatas = new ArrayList<AddressEntry>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		Log.i("FINDVIEW", "lvResult1");

		spAumper = (Spinner) findViewById(R.id.spAumper);
		spTumbon = (Spinner) findViewById(R.id.spTambon);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnReset = (Button) findViewById(R.id.btnReset);

		Log.i("FINDVIEW", "lvResult2");

		String result = HttpHelper.GetString(
				String.format("http://%s/testsql2.php", Config.Ip), null);
		Log.i("RESULT", result);
		// String url = "http://%s/testsql.php";

		try {
			JSONArray JSArr = new JSONArray(result);
			listdata = new ArrayList<AddressEntry>();
			Log.i("FINDVIEW", "lvResult4");
			for (int i = 0; i < JSArr.length(); i++) {

				JSONObject JSObj = JSArr.getJSONObject(i);

				listdata.add(new AddressEntry(JSObj.getString("District")));
				// items.add(new AddressEntry(JSObj.getString("Tumbol")));
				Log.i("FINDVIEW", "lvResult5");
			}
			spAumper.setAdapter(new AumAdapter());
			spTumbon.setAdapter(new AumAdapter());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();

		}
		String result2 = HttpHelper.GetString(
				String.format("http://%s/testsql.php", Config.Ip), null);

		try {
			JSONArray JSArr = new JSONArray(result2);
			listdatas = new ArrayList<AddressEntry>();
			Log.i("FINDVIEW", "lvResult4");
			for (int i = 0; i < JSArr.length(); i++) {

				JSONObject JSObj = JSArr.getJSONObject(i);

				listdatas.add(new AddressEntry(JSObj.getString("Tumbol")));
				Log.i("FINDVIEW", "lvResult5");
			}

			spTumbon.setAdapter(new TumAdapter());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();

		}

		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

	public class AumAdapter extends BaseAdapter {
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
			final AddressEntry item = listdata.get(position);
			if (v == null) {
				Log.i("FINDVIEW", "lvResult8");
				LinearLayout ll = new LinearLayout(getApplicationContext());
				holder = new Holder();
				holder.Title = new TextView(getApplicationContext());
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
			Log.i("FINDVIEW", "lvResult8");
			holder.Title.setText(listdata.get(position).getDistrict());

			return v;

		}
	}

	/*
	 * private Collection getJSONUrl(String url) { // TODO Auto-generated method
	 * stub return null; }
	 */
	public class TumAdapter extends BaseAdapter {

		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listdatas.size();
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
			final AddressEntry item = listdatas.get(position);
			if (v == null) {
				Log.i("FINDVIEW", "lvResult8");
				LinearLayout ll = new LinearLayout(getApplicationContext());
				holder = new Holder();
				holder.Title2 = new TextView(getApplicationContext());
				holder.Title2.setGravity(Gravity.CENTER);
				holder.Title2.setTextColor(Color.parseColor("#000000"));
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				lp.gravity = Gravity.CENTER;
				lp.setMargins(0, 20, 0, 20);
				ll.addView(holder.Title2, lp);
				v = ll;
				v.setTag(holder);
			} else {
				holder = (Holder) v.getTag();
			}
			Log.i("FINDVIEW", "lvResult8");
			holder.Title2.setText(listdatas.get(position).getDistrict());

			btnSearch.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Intent intent = new Intent(MainActivity.this,
							ActivityListActivity.class);
					intent.putExtra("addressID", item.getAddressID());
					startActivity(intent);
				}
			});
			return v;
		}

	}

	public class Holder {
		public TextView Title, Title2;

	}
}
