package com.zig.puncher.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zig.puncher.AppConts;
import com.zig.puncher.conts.Key;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferenceHelper {

	private static SharedPreferenceHelper sph;
	private final SharedPreferences sp;
	private final SharedPreferences.Editor editor;

	private SharedPreferenceHelper(Context context) {
		sp = context.getSharedPreferences(AppConts.PREFER_NAME, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public static SharedPreferenceHelper getInstance(Context context) {
		if (sph == null) {
			sph = new SharedPreferenceHelper(context);
		}
		return sph;
	}



	public void setTileTick() {
		Set<String> dataSet = sp.getStringSet(Key.DATE_CHANGE, null);
		if (dataSet == null) {
			dataSet = new HashSet<String>();
		}
		dataSet.add(DateUtil.getInstance().getTimeDetail());
		editor.putStringSet(Key.DATE_CHANGE, dataSet).commit();
	}

	public String getPunchTime() {
		return sp.getString(Key.PUNCH_TIME, null);
	}

	public void setPunchTime() {
		setPunchDate();
		editor.putString(Key.PUNCH_TIME, DateUtil.getInstance().getCurrentTime()).commit();
	}

	private void setPunchDate() {
		editor.putString(Key.PUNCH_DATE, DateUtil.getInstance().getTodayDate()).commit();
	}

	public String getPunchDate() {
		return sp.getString(Key.PUNCH_DATE, null);
	}

}
