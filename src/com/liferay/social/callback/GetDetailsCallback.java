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

package com.liferay.social.callback;

import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;
import com.liferay.social.activity.MainActivity;
import com.liferay.social.model.Contact;
import com.liferay.social.model.User;
import com.liferay.social.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author Bruno Farache
 */
public class GetDetailsCallback extends BatchAsyncTaskCallback {

	public GetDetailsCallback(MainActivity activity, User user) {
		_activity = activity;
		_user = user;
	}

	public void onFailure(Exception e) {
		ToastUtil.show(
			_activity, "Couldn't fetch details: " + e.getMessage(), true);
	}

	public void onSuccess(JSONArray jsonArray) {
		try {
			_user.setContact(new Contact(jsonArray));
			_activity.showDetails(_user);
		}
		catch (JSONException je) {
			onFailure(je);
		}
	}

	private MainActivity _activity;
	private User _user;

}