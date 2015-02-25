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
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;


@SuppressLint("NewApi") public class MainActivity extends ActionBarActivity {

	private Spinner spAumper,spTumbon;
	private ArrayList<AddressEntry> items,items2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Log.i("FINDVIEW", "lvResult1");
        	
       spAumper = (Spinner)findViewById(R.id.spAumper);
        spTumbon = (Spinner)findViewById(R.id.spTambon);
        Log.i("FINDVIEW", "lvResult2");
        
        String result = HttpHelper.GetString(String.format("http://%s/testsql2.php", Config.Ip), null);
        Log.i("RESULT", result);
        //String url = "http://%s/testsql.php";
        
       try {
			JSONArray JSArr = new JSONArray(result);
			items = new ArrayList<AddressEntry>();
			Log.i("FINDVIEW", "lvResult4");
			for (int i = 0; i < JSArr.length(); i++) {
				
				JSONObject JSObj = JSArr.getJSONObject(i);
				
				items.add(new AddressEntry(JSObj.getString("District")));
				//items.add(new AddressEntry(JSObj.getString("Tumbol")));
				Log.i("FINDVIEW", "lvResult5");
			}
			spAumper.setAdapter(new AumAdapter());
			spTumbon.setAdapter(new AumAdapter());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();
			
		}
       String result2 = HttpHelper.GetString(String.format("http://%s/testsql.php", Config.Ip), null);
       
       try {
			JSONArray JSArr = new JSONArray(result2);
			items2 = new ArrayList<AddressEntry>();
			Log.i("FINDVIEW", "lvResult4");
			for (int i = 0; i < JSArr.length(); i++) {
				
				JSONObject JSObj = JSArr.getJSONObject(i);
				
				
				items2.add(new AddressEntry(JSObj.getString("Tumbol")));
				Log.i("FINDVIEW", "lvResult5");
			}
			
			spTumbon.setAdapter(new TumAdapter());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			e.printStackTrace();
			
		}
        
       
    }
    public class AumAdapter extends BaseAdapter{
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
			holder.Title.setText(items.get(position).getDistrict());
			return v;
		}
		}
    
	/*private Collection getJSONUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}*/
    public class TumAdapter extends BaseAdapter{

private Holder holder;
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items2.size();
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
			holder.Title2.setText(items2.get(position).getDistrict());
			return v;
		}
    	
    }

   public class Holder {
	   public TextView Title,Title2;
	   
   }
}
