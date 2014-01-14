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

package com.liferay.social.callback;

import android.util.Log;

import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.social.activity.MainActivity;
import com.liferay.social.model.MicroblogsEntryModel;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Silvio Santos
 */
public class GetMicroblogsEntryCallback
	extends GenericAsyncTaskCallback<List<MicroblogsEntryModel>> {

	public GetMicroblogsEntryCallback(MainActivity activity) {
		_activity = activity;
	}

	public void onFailure(Exception e) {
		String message = "Couldn't get microblogs";

		Log.e(_CLASS_NAME, message, e);

		ToastUtil.show(_activity, message + ": " + e.getMessage(), true);
	}

	public void onSuccess(List<MicroblogsEntryModel> entries) {
		_activity.updateMicroblogs(entries);
	}

	public List<MicroblogsEntryModel> transform(Object obj) throws Exception {
		List<MicroblogsEntryModel> entries =
			new ArrayList<MicroblogsEntryModel>();

		JSONArray jsonArray = (JSONArray)obj;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = (JSONObject)jsonArray.get(i);

			entries.add(new MicroblogsEntryModel(json));
		}

		return entries;
	}

	private static String _CLASS_NAME =
		GetMicroblogsEntryCallback.class.getName();

	private MainActivity _activity;

}