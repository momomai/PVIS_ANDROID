package com.psu.project_pvis;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.psu.entry.FacultyEntry;
import com.psu.project_pvis.PageSearchActivity.LvDataBaseAdapter;

public class ResultActivity extends Fragment{

	public ListView lvResult;
	public SearchActivity searchActivity;
	public ArrayList<FacultyEntry> items;
	public LvDataBaseAdapter lvDataBaseAdapter;
	public ImageButton ibBack;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		if (container == null) {
			return null;
		}
		
		View v = inflater.inflate(R.layout.result_activity, container,false);
		
		lvResult = (ListView) v.findViewById(R.id.lvResult);
		ibBack = (ImageButton) v.findViewById(R.id.ibBack);
		
		return v;
	}
}
