package com.psu.pvis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.psu.entry.ActivityEntry;
import com.psu.helper.Config;
import com.psu.helper.HttpHelper;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VideoViewActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	public static final String API_KEY = "AIzaSyCTRJXZFAf6RVDas9i8yE4_x9HIWLIBygE";
	// public static final String VIDEO_ID = "o7VVHhK9zf0";

	private YouTubePlayer youTubePlayer;
	private YouTubePlayerFragment youTubePlayerFragment;
	private TextView textVideoLog;
	private Button btnViewFullScreen;
	private String value;
	private ArrayList<ActivityEntry> listdata = new ArrayList<ActivityEntry>();

	private static final int RQS_ErrorDialog = 1;

	String log = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview_activity);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
				.findFragmentById(R.id.youtubeplayerfragment);
		
		
		Log.i("a", "4");
		//เข้าตรงนี้แล้วเออเร่อ NullPointerException
		youTubePlayerFragment.initialize(API_KEY, this);
		
		
		Log.i("a", "5");
		Bundle extras = getIntent().getExtras();
		Log.i("a", "1");
		if (extras != null) {
			value = extras.getString("VideoActivity");

		}
		Log.i("a", "2");
		Log.i("value", value.toString());
		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("VideoActivity", extras
				.getString("VideoActivity")));
		Log.i("a", "3");
		String result = HttpHelper.GetString(

		String.format("http://%s/pvis2/videoActivity.php?VideoActivity=%s",
				Config.Ip, value), null);
		
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00008B"));
	    getActionBar().setBackgroundDrawable(colorDrawable);
	    getActionBar().setTitle("วีดีโอกิจกรรม");
	    ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);
		
	}

	@Override
	public void onInitializationFailure(Provider provider,
			YouTubeInitializationResult result) {

		if (result.isUserRecoverableError()) {
			result.getErrorDialog(this, RQS_ErrorDialog).show();
		} else {
			Toast.makeText(
					this,
					"YouTubePlayer.onInitializationFailure(): "
							+ result.toString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean wasRestored) {

		youTubePlayer = player;
		
		if (!wasRestored) {
			player.cueVideo(value);
		}

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
	    switch (item.getItemId()) {
	    
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	        	finish();
	            return true;
	            
	            default:
	            return super.onOptionsItemSelected(item); 
	    }
	}
}
