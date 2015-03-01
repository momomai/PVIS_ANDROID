package com.example.androidvideostreaming;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity{
	
	private ProgressDialog pDialog;
	
	private VideoView videoView;
	
	String videoURL = "https://www.youtube.com/watch?v=B6vGCaVy7ng";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.videoview_main);
		
		
		
		videoView = (VideoView)findViewById(R.id.videoView);
		
		pDialog = new ProgressDialog(VideoViewActivity.this);
		
		pDialog.setTitle("Android Video Streaming Tutorial");
		
		pDialog.setMessage("Buffering...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
		
		try{
			MediaController mediacontroller = new MediaController(VideoViewActivity.this);
			mediacontroller.setAnchorView(videoView);
			
			Uri video = Uri.parse(videoURL);
			videoView.setMediaController(mediacontroller);
			videoView.setVideoURI(video);
			
		}catch(Exception e){
			
			Log.e("Error",e.getMessage());
			e.printStackTrace();
		}
		
		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				pDialog.dismiss();
				videoView.start();
			}
		});
	}
	
	

}
