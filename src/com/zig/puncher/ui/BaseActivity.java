package com.zig.puncher.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zig.puncher.utils.SharedPreferenceHelper;

public abstract class BaseActivity extends Activity {

	protected SharedPreferenceHelper sph;
	protected final String TAG = getClass().getSimpleName();
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		sph = SharedPreferenceHelper.getInstance(context);
	}

	protected void showToast(String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	public void onClick(View v) {

	}

}
