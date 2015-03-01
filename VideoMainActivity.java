package com.example.androidvideostreaming;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button)findViewById(R.id.btnVideo);
        
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this,VideoViewActivity.class);
				startActivity(intent);
				
				/*Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, "<<YOUTUBE_API_KEY>>", "<<Youtube Video ID>>", 0, true, false);   
				startActivity(intent);
				*/
			}
		});
    }


   
}
