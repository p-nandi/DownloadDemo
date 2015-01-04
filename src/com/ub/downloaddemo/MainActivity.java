package com.ub.downloaddemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

	String[] listOfImages;
	ProgressBar progressBar;
	EditText downloadTxt;
	Button downloadBtn;
	ListView urls;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		downloadTxt=(EditText)findViewById(R.id.downloadTxt);
		downloadBtn=(Button)findViewById(R.id.downloadBtn);
		urls=(ListView)findViewById(R.id.urls);
		urls.setOnItemClickListener(urlsClickListener);
		listOfImages = getResources().getStringArray(R.array.imageUrls);
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
	}
	
	public void downloadImage(View view){
		downloadImageUsingThreads(listOfImages[0]);
	}
	
	public void downloadImageUsingThreads(String url){
		URL downloadUrl = null;
		HttpURLConnection urlConn=null;
		InputStream inputStream=null;
		try {
			downloadUrl = new URL(url);
			urlConn=(HttpURLConnection)downloadUrl.openConnection();
			inputStream=urlConn.getInputStream();
			int read=-1;
			while((read=inputStream.read())!=-1){
				L.m(""+read);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			if(urlConn!=null){
				urlConn.disconnect();
			}
		}
		
	}
	
    public OnItemClickListener urlsClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			downloadTxt.setText(listOfImages[arg2]);
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
