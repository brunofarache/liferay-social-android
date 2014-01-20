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

import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.social.activity.MainActivity;
import com.liferay.social.model.MicroblogsEntry;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Silvio Santos
 */
public class GetMicroblogsEntriesCallback
	extends GenericAsyncTaskCallback<List<MicroblogsEntry>> {

	public GetMicroblogsEntriesCallback(MainActivity activity) {
		_activity = activity;
	}

	public void onFailure(Exception e) {
		ToastUtil.show(
			_activity, "Couldn't fetch microblogs entries: " + e.getMessage(),
			true);
	}

	public void onSuccess(List<MicroblogsEntry> entries) {
		_activity.updateEntries(entries);
	}

	public List<MicroblogsEntry> transform(Object obj) throws Exception {
		List<MicroblogsEntry> entries = new ArrayList<MicroblogsEntry>();

		JSONArray jsonArray = (JSONArray)obj;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = (JSONObject)jsonArray.get(i);

			entries.add(new MicroblogsEntry(json));
		}

		return entries;
	}

	private MainActivity _activity;

}