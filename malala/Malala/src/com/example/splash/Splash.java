package com.example.splash;

import com.example.malala.MenuUtama;
import com.example.malala.R;
import com.example.splash.Loading.LoadingTaskFinishedListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash extends Activity implements LoadingTaskFinishedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,        
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.spalsh);
		
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);
		 new Loading(progressBar, this).execute("");
		 
	}

	
	@Override
	public void onTaskFinished() {
		completeSplash();
	}
	private void completeSplash(){
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }
	private void startApp() {
        Intent intent = new Intent(Splash.this, MenuUtama.class);
        startActivity(intent);
    }

}
