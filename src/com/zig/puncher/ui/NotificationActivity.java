package com.zig.puncher.ui;

import android.os.Bundle;

import com.zig.puncher.R;
import com.zig.puncher.utils.NotificationHelper;

public class NotificationActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Transparent);
		NotificationHelper.getInstance(this).show(context);
		finish();
	}

}
