package com.psu.project_pvis;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;

public class SearchActivity extends Fragment{

	private String title;
	public TabHost tbHost;
	public Spinner spFac;
	public Button btnSearch, btnReset;
	public ListView lvResult;
	public SearchEventActivity searchClick;
	private Context context;
	
	public SearchActivity(Context context, String title) {
		super();
		this.title = title;
		this.context = context;
	}
}
interface SearchEventActivity {
	public void onHandler(Object x);
}
