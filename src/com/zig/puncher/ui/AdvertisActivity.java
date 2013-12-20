package com.zig.puncher.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import cn.domob.android.ads.DomobAdView;
import cn.domob.android.ads.DomobSplashAd;
import cn.domob.android.ads.DomobSplashAd.DomobSplashMode;
import cn.domob.android.ads.DomobSplashAdListener;

import com.zig.puncher.R;
import com.zig.puncher.conts.AD;

public class AdvertisActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advertis);
		intiAd();
	}

	DomobAdView adView;

	private void intiAd() {
		final DomobSplashAd splashAd = new DomobSplashAd(this, AD.publisherID, AD.splashPPID, DomobSplashMode.DomobSplashModeFullScreen);
		// setSplashTopMargin is available when you choose non-full-screen
		// splash mode.
		// splashAd.setSplashTopMargin(200);
		splashAd.setSplashAdListener(new DomobSplashAdListener() {
			@Override
			public void onSplashPresent() {
			}

			@Override
			public void onSplashDismiss() {
				// 开屏回调被关闭时，立即进行界面跳转，从开屏界面到主界面。
				// When splash ad is closed, jump to the next(main) Activity
				// immediately.
				jump();
				// 如果应用没有单独的闪屏Activity，需要调用closeSplash方法去关闭开屏广告
				// If you do not carry a separate advertising activity, you need
				// to call closeRTSplash way to close the splash ad

				// splashAd.closeSplash();
			}

			@Override
			public void onSplashLoadFailed() {
			}
		});

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (splashAd.isSplashAdReady()) {
					splashAd.splash(AdvertisActivity.this, AdvertisActivity.this.findViewById(R.id.layout_adv));
				} else {
					Toast.makeText(AdvertisActivity.this, "Splash ad is NOT ready.", Toast.LENGTH_SHORT).show();
					jump();
				}
			}
		}, 1);
	}

	protected void jump() {
		startActivity(new Intent(this, LookOverActivity.class));
		this.finish();
	}

}
