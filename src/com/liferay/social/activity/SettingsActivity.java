/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.social.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import android.os.Bundle;

import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.liferay.social.R;
import com.liferay.social.util.PrefsUtil;

/**
 * @author Josiane Bezerra
 */

public class SettingsActivity extends PreferenceActivity
	implements OnSharedPreferenceChangeListener {

	public void finish() {
		super.finish();

		overridePendingTransition(0, 0);
	}

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle state) {
		super.onCreate(state);

		addPreferencesFromResource(R.layout.settings);

		PreferenceScreen preferenceScreen = getPreferenceScreen();

		_loginPreference =
			(EditTextPreference)preferenceScreen.findPreference(
			PrefsUtil.LOGIN);

		_serverPreference =
			(EditTextPreference)preferenceScreen.findPreference(
			PrefsUtil.SERVER);

		_loginPreference.setSummary(PrefsUtil.getLogin());
		_serverPreference.setSummary(PrefsUtil.getServer());

		SharedPreferences sharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(this);

		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(
		SharedPreferences sharedPreferences, String key) {

		if (key.equals(PrefsUtil.LOGIN)) {
			_loginPreference.setSummary(PrefsUtil.getLogin());
		}
		else if (key.equals(PrefsUtil.SERVER)) {
			_serverPreference.setSummary(PrefsUtil.getServer());
		}
	}

	private EditTextPreference _loginPreference;
	private EditTextPreference _serverPreference;

}