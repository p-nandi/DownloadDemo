package com.ub.downloaddemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {

	public static void m(String message){
		Log.d("pnandi", message);
	}
	
	public static void s(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
