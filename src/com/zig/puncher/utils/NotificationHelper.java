package com.zig.puncher.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.zig.puncher.R;
import com.zig.puncher.conts.Action;
import com.zig.puncher.ui.AdvertisActivity;

public class NotificationHelper {
	private final int nfID = android.R.id.toggle;
	private static NotificationHelper nfh;
	private final SharedPreferenceHelper spHelper;
	private final NotificationManager nm;

	private NotificationHelper(Context context) {
		spHelper = SharedPreferenceHelper.getInstance(context);
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	};

	public static synchronized NotificationHelper getInstance(Context context) {
		if (nfh == null) {
			nfh = new NotificationHelper(context);
		}
		return nfh;
	}

	private void showPunchNotification(Context context) {
		ZLog.i("showPunchNotification");
		showNotification(context, getPunchRemoteView(context));
	}

	public void showTimeNotification(Context context) {
		ZLog.i("showTimeNotification");
		showNotification(context, getShowTimeRemoteView(context));
	}

	private Notification getNotification(Context context, RemoteViews remoteViews) {
		ZLog.i("getNotification");
		Notification notification = new Notification(R.drawable.ic_launcher, "", System.currentTimeMillis());
		notification.contentView = remoteViews;
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, AdvertisActivity.class), 0);
		notification.contentIntent = contentIntent;
		return notification;
	}

	private RemoteViews getPunchRemoteView(Context context) {
		ZLog.i("getPunchRemoteView");
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.view_notify_puncher);
		Intent rtIntent = new Intent(Action.NOTIFICATION_BTN_CLICK);
		PendingIntent ci = PendingIntent.getBroadcast(context, 0, rtIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.btn_notification_puncher, ci);
		return remoteViews;
	}

	private RemoteViews getShowTimeRemoteView(Context context) {
		ZLog.i("getShowTimeRemoteView");
		String puncherTime = spHelper.getPunchTime();
		if (!isTodayAlreadyPunched()) {
			puncherTime = DateUtil.getInstance().getCurrentTime();
			spHelper.setPunchTime();
		}

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.view_notify_time);
		remoteViews.setTextViewText(R.id.tv_notification_time, puncherTime);
		return remoteViews;
	}

	private void showNotification(Context context, RemoteViews remoteViews) {
		ZLog.i("showNotification");
		Notification notification = getNotification(context, remoteViews);
		nm.notify(nfID, notification);
	}

	public void show(Context context) {
		ZLog.i("show");
		if (isTodayAlreadyPunched()) {
			showTimeNotification(context);
		} else {
			showPunchNotification(context);
		}
	}

	private boolean isTodayAlreadyPunched() {
		String todayDate = DateUtil.getInstance().getTodayDate();
		String punchDate = spHelper.getPunchDate();
		return todayDate.equals(punchDate);
	}
}
