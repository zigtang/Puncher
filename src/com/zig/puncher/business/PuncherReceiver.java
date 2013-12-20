package com.zig.puncher.business;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zig.puncher.conts.Action;
import com.zig.puncher.utils.AlarmHelper;
import com.zig.puncher.utils.NotificationHelper;
import com.zig.puncher.utils.SharedPreferenceHelper;
import com.zig.puncher.utils.ZLog;

public class PuncherReceiver extends BroadcastReceiver {
	private final String TAG = "PuncherReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		ZLog.i(TAG, "onReceive");
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			NotificationHelper.getInstance(context).show(context);
		}

		if (Action.NEW_DAY.equals(intent.getAction())) {
			ZLog.i(TAG, "Action.NEW_DAY");
			SharedPreferenceHelper.getInstance(context).setTileTick();
			NotificationHelper.getInstance(context).show(context);
		}

		if (Action.NOTIFICATION_BTN_CLICK.equals(intent.getAction())) {
			AlarmHelper.alarm2NewDay(context);
			NotificationHelper.getInstance(context).showTimeNotification(context);
		}
	}
}
