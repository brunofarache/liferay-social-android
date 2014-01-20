/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
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

package com.liferay.social.util;

import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.task.callback.AsyncTaskCallback;

/**
 * @author Bruno Farache
 */
public class PrefsUtil {

	public static final String LOGIN = "login";

	public static final String PASSWORD = "password";

	public static final String SERVER = "server";

	public static String getLogin() {
		return _preferences.getString(LOGIN, StringPool.BLANK);
	}

	public static String getPassword() {
		return _preferences.getString(PASSWORD, StringPool.BLANK);
	}

	public static SharedPreferences getPreferences() {
		return _preferences;
	}

	public static String getServer() {
		return _preferences.getString(SERVER, StringPool.BLANK);
	}

	public static Session getSession() {
		return getSession(null);
	}

	public static Session getSession(AsyncTaskCallback callback) {
		if (_session == null) {
			_session = new SessionImpl(getServer(), getLogin(), getPassword());
		}

		_session.setCallback(callback);

		return _session;
	}

	public static void init(Context context) {
		_preferences = PreferenceManager.getDefaultSharedPreferences(context);

		_listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(
				SharedPreferences preferences, String key) {

				if (!key.equals(LOGIN) && !key.equals(PASSWORD) &&
					!key.equals(SERVER)) {

					return;
				}

				_session = null;
			}
		};

		_preferences.registerOnSharedPreferenceChangeListener(_listener);
	}

	private static SharedPreferences.OnSharedPreferenceChangeListener _listener;
	private static SharedPreferences _preferences;
	private static Session _session;

}