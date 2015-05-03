package com.psu.pvis;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.psu.entry.InformationEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class InformationFragment extends Fragment {

	private ListView lvView;
	private JSONArray jsArray;
	private ArrayList<InformationEntry> listdata = new ArrayList<InformationEntry>();

	public InformationFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("I", "1");
		View rootView = inflater.inflate(R.layout.fragment_information,
				container, false);
		Log.i("I", "2");
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		lvView = (ListView) rootView.findViewById(R.id.lvInformation);
		Log.i("I", "3");

		String result = HttpHelper.GetString(
				String.format("http://%s/pvis2/information.php", Config.Ip),
				null);
		Log.i("I", "4");
		try {
			Log.i("I", "5");
			jsArray = new JSONArray(result);
			if (jsArray.length() > 0)
				Log.i("I", "6");
			{
				for (int i = 0; i < jsArray.length(); i++) {
					Log.i("I", i + "");
					JSONObject jsObj = jsArray.getJSONObject(i);
					listdata.add(new InformationEntry(
							jsObj.getString("NewsID"), jsObj
									.getString("NewsHeader"), jsObj
									.getString("NewsTitle"), jsObj
									.getString("NewsDetail"), jsObj
									.getString("NewsImage")));
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

				convertView = LayoutInflater.from(
						getActivity().getApplicationContext()).inflate(
						R.layout.lv_information_item, null);

				
				holder.tvActName = (TextView) convertView
						.findViewById(R.id.tvActName);
				
				holder.imgNew = (ImageView) convertView
						.findViewById(R.id.imgNew);
				holder.btnDetail = (TextView) convertView
						.findViewById(R.id.btnDetail);
				/*holder.tvImage = (ImageView) convertView
						.findViewById(R.id.tvImage);*/
				

				convertView.setTag(holder);

			} else {

				holder = (Holder) convertView.getTag();
			}

			final InformationEntry item = listdata.get(position);

			
			holder.tvActName.setText("      " + item.getNewsHeader());
			//holder.tvActName.setPaintFlags(holder.tvActName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
			//holder.tvDetail.setText("DetailActivity: " + item.getNewsDetail());
			String imgurl = HttpHelper.GetString(String.format(
					"http://%s/pvis2/imageInformation.php?NewsImage=%s",
					Config.Ip, item.getNewsImage()), null);
			Log.i("url", String.format(
					"http://%s/pvis2/imageInformation.php?NewsImage=%s",
					Config.Ip, item.getNewsImage()));
			Picasso.with( getActivity().getApplicationContext()).load("http://projectse03.ictte-project.com/PSUPhuketVolunteer/upload/News/"+item.getNewsImage())
			.into(holder.imgNew);
			
			
			
			
			holder.btnDetail.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getActivity(), DetailInformation.class);
					
					intent.putExtra("NewsID", item.getNewsID());
					
					getActivity().startActivity(intent);
					Log.i("I", item.getNewsHeader());
					
				}
			});
			
			
			Log.i("I", "18");
			

			return convertView;

		}

	}

	public class Holder {
		public TextView tvActName;
		public TextView btnDetail, tvDetail2;
		public ImageView imgNew;
		public ImageView tvImage;

	}

}
