package com.psu.project_pvis;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.psu.entry.ActivitysEntry;
import com.psu.entry.ScrollEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

public class PageSearchActivity extends FragmentActivity{

	private TabHost tbHost;
	private ImageButton ibBack;
	private ListView lvResult;
	private JSONArray jsArray;
	private SearchActivity searchActivity;
	private ResultActivity resultActivity;
	//private ArrayList<FacultyEntry> items;
	private ArrayList<ActivitysEntry> listData;
	private static LvDataBaseAdapter lvDataBaseAdapter;
	int page = 1;
	public boolean isLoad = true;
	private ScrollEntry scroll;
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		tbHost = (TabHost) findViewById(android.R.id.tabhost);
		LocalActivityManager lam = new LocalActivityManager(this, false);
		tbHost.setup(lam);
		lam.dispatchCreate(savedInstanceState);
		
		TabHost.TabSpec spec = tbHost.newTabSpec("TB1");
		Log.i("CREATE TAB","1");
		spec.setIndicator(CreateTabView("SEARCH",android.R.drawable.ic_search_category_default));
		Log.i("CREATE TAB","2");
		spec.setContent(R.id.tbItem);
		Log.i("CREATE TAB","3");
		tbHost.addTab(spec);
		spec = tbHost.newTabSpec("TB2").setIndicator(CreateTabView("RESULT", android.R.drawable.ic_menu_view)).setContent(R.id.tbItem);
		tbHost.addTab(spec);
		
		pager = (ViewPager) findViewById(R.id.viewpager);
		lvResult = (ListView) findViewById(R.id.lvResult);
		ibBack = (ImageButton) findViewById(R.id.ibBack);
		
		final List<Fragment> fragments = new ArrayList<Fragment>();
		searchActivity = new SearchActivity(getApplicationContext(),"");
		fragments.add(searchActivity);
		
		resultActivity = new ResultActivity();
		fragments.add(resultActivity);
		
		pager.setAdapter(new TabsAdapter(getSupportFragmentManager(), fragments));
		pager.setCurrentItem(0);
		
		tbHost.setOnTabChangedListener(tbHost_Changed);
		pager.setOnPageChangeListener(pager_Changed);
		
		searchActivity.searchClick  = new SearchEventActivity() {
			
			@Override
			public void onHandler(Object x) {
				// TODO Auto-generated method stub
				isLoad=false;
				resultActivity.lvDataBaseAdapter = new LvDataBaseAdapter();
				page=1;
				listData = new ArrayList<ActivitysEntry>();
				//Reload();
				resultActivity.lvResult.setAdapter(resultActivity.lvDataBaseAdapter);
				pager.setCurrentItem(1);
				
				resultActivity.lvResult.setOnScrollListener(new AbsListView.OnScrollListener() {	
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
						// TODO Auto-generated method stub
						if(firstVisibleItem+visibleItemCount >= totalItemCount){
							
							if(isLoad==false){
								Reload();
							}
						}
					}
				});
			}
		};
		
		ibBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private void Reload() {
		Log.i("ONSCROLL0", "LAST");
		
		Config.FacName = searchActivity.items.get(searchActivity.spFac.getSelectedItemPosition()).getFacName();
		Log.i("Config", "DATA1");
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		//data.add(new BasicNameValuePair("siteno", Config.SiteNo));
		//Log.i("Config", "DATA2");
		//data.add(new BasicNameValuePair("page", "" + page));
		//Log.i("Config", "DATA3");
		//data.add(new BasicNameValuePair("pmsno", searchJicActivity.etPmsNo.getText().toString()));
		//Log.i("Config", "DATA4");
		//data.add(new BasicNameValuePair("mopno", searchJicActivity.etMopNo.getText().toString()));
		Log.i("Config", "DATA5");

		/*if (searchActivity.chMMS.isChecked()) {
			data.add(new BasicNameValuePair("docno", searchJicActivity.items.get(searchJicActivity.spinner.getSelectedItemPosition()).getDocNo()));
		}*/
		isLoad = true;
		
		String result = HttpHelper.GetString(String.format("http://%s/Rest/index.php", Config.Ip), null);
		Log.i("RESULT", result);

		try {
			jsArray = new JSONArray(result);
			Log.i("LENGTH", jsArray.length() + "");
			if (jsArray.length() > 0) {
				for (int i = 0; i < jsArray.length(); i++) {
					Log.i("I", i + "");
					JSONObject jsobj = jsArray.getJSONObject(i);
					listData.add(new ActivitysEntry(
							jsobj.getString("ActivityID"),
							jsobj.getString("ActivityName"), 
							jsobj.getString("DetailActivity"), 
							jsobj.getString("Solution"), 
							jsobj.getString("Objective")));
				}
				Log.i("TEST", "1");
				page++;
				Log.i("TEST", "2");
				resultActivity.lvDataBaseAdapter.notifyDataSetChanged();
			}
			new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					try {
						Thread.sleep(2000);
						Log.i("TEST", "3");
						isLoad = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	TabHost.OnTabChangeListener tbHost_Changed = new TabHost.OnTabChangeListener() {
		@Override
		public void onTabChanged(String arg0) {
			// TODO Auto-generated method stub
			if(tbHost.getCurrentTab()!=pager.getCurrentItem())
			{
				Log.i("SET","TEST");
				pager.setCurrentItem(tbHost.getCurrentTab());
			}	
		}
	};
	
	ViewPager.OnPageChangeListener pager_Changed = new ViewPager.OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Log.i("POSITION",arg0+"");
			tbHost.setCurrentTab(arg0);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}
	};

	private View CreateTabView(String title, int img) {

		View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_item_tab, null, false);
		ImageView iv = (ImageView) v.findViewById(R.id.ivTitle);
		iv.setImageResource(img);
		return v;
	}

	public View GetView(){
		
		View v = new View(getApplicationContext());
		return v;
	}
	public class TabsAdapter extends FragmentPagerAdapter
	{
		private List<Fragment> Fragments;

		public TabsAdapter(FragmentManager fm,List<Fragment> fragment) {
			super(fm);
			Fragments = fragment;
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return this.Fragments.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Fragments.size();
		}
	}
	
	public class LvDataBaseAdapter extends BaseAdapter {
		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listData.size();
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
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lv_item_activity, null);
				
				holder.tvactivityName = (TextView) convertView.findViewById(R.id.tvActivityName);
				holder.tvactivityName.setTextColor(Color.parseColor("#FF0000"));
				holder.tvdetailActivity = (TextView) convertView.findViewById(R.id.tvDetailActivity);
				holder.tvdetailActivity.setTextColor(Color.parseColor("#FF0000"));
				holder.tvsolutions = (TextView) convertView.findViewById(R.id.tvSolutions);
				holder.tvobjective = (TextView) convertView.findViewById(R.id.tvObjective);
				holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);

				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			final ActivitysEntry item = listData.get(position);
			holder.tvactivityName.setText("PMS No. : " + item.getActivityName());
			holder.tvdetailActivity.setText("SWBS : " + item.getDetailActivity());
			holder.tvsolutions.setText("DOC : " + item.getSolutions());
			holder.tvobjective.setText("MOP : " + item.getObjective());

			/*holder.tvView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "File",Toast.LENGTH_SHORT).show();
					String result = "";
					try {
						ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
						data.add(new BasicNameValuePair("docno", item.getDocNo()));
						data.add(new BasicNameValuePair("pmsno", item.getPmsNo()));
						data.add(new BasicNameValuePair("mopno", item.getMopNo()));
						data.add(new BasicNameValuePair("siteno", Config.SiteNo));

						result = HttpHelper.GetString(String.format("http://%s/MSTUS/index.aspx?r=%s",
								"192.168.0.98", "reportjic"), data);
						Log.i("result", result);
						Log.i("url",
								String.format("http://%s/MSTUS/index.aspx?r=tasks&siteno=%s&pmsno=%s&mopno=%s",
										"192.168.0.98", Config.SiteNo,item.getPmsNo(), item.getMopNo()));
						if (result.equals("0")) {
							Toast.makeText(getApplicationContext(),"Network error", Toast.LENGTH_LONG).show();
							return;
						}
						final JSONObject Js = new JSONObject(result);
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.98/MSTUS/exports/"
										+ Js.getString("FILENAME")));
						startActivity(intent);
						Log.i("urldelete", "DELETE");
						new Thread() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								super.run();
								try {
									Thread.sleep(20000);
									ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
									data.add(new BasicNameValuePair("filename",Js.getString("FILENAME")));
									String result = HttpHelper.GetString(
											String.format("http://%s/MSTUS/index.aspx?r=%s",
													"192.168.0.98","reportjicdelete"), data);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}.start();

					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});*/

			holder.tvDetail.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(PageSearchActivity.this,DetailActivity.class);
					intent.putExtra("ActivityID", item.getActivityID());
					//intent.putExtra("DetailActivity", item.getDetailActivity());
					//intent.putExtra("Solution", item.getSolutions());
					//intent.putExtra("Objective", item.getObjective());
					startActivity(intent);
				}
			});
			
			return convertView;
		}
	}

	public class Holder {
		public TextView tvactivityName;
		public TextView tvdetailActivity;
		public TextView tvsolutions;
		public TextView tvobjective;
		public TextView tvDetail;
		//public TextView tvView;
		public TextView Title;
	}
}
