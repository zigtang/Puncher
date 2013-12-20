package com.zig.puncher.utils;

import java.util.Calendar;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zig.puncher.conts.Action;

public class AlarmHelper {
	public static void alarm2NewDay(Context context) {
		ZLog.i("alarm2NewDay");
		Intent intent = new Intent(Action.NEW_DAY);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC, calcTimeBettwenZeroAndNow(), pi);
	}

	private static long calcTimeBettwenZeroAndNow() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTimeInMillis(System.currentTimeMillis());
		int currentHour = calendar.get(calendar.HOUR);
		calendar.add(Calendar.HOUR, 24 - currentHour);
		return calendar.getTimeInMillis();
	}
}
