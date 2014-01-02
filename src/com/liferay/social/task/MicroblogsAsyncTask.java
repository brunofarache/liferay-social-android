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

package com.liferay.social.task;

import android.os.AsyncTask;

import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.microblogsentry.MicroblogsentryService;
import com.liferay.social.activity.MainActivity;
import com.liferay.social.model.Microblog;
import com.liferay.social.util.SettingsUtil;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Josiane Bezerra
 */

public class MicroblogsAsyncTask
		extends AsyncTask<Void, Void, ArrayList<Microblog>> {

	public MicroblogsAsyncTask(MainActivity activity) {
		_activity = activity;
	}

	public ArrayList<Microblog> doInBackground(Void... params) {
		ArrayList<Microblog> microblogs = new ArrayList<Microblog>();

		Session session = SettingsUtil.getSession();
		MicroblogsentryService service = new MicroblogsentryService(session);

		try {
			JSONArray jsonArray = service.getMicroblogsEntries(-1, -1);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);

				microblogs.add(new Microblog(jsonObj));
			}
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get microblogs", e);

			_exception = e;

			cancel(true);
		}

		return microblogs;
	}

	public void onCancelled(ArrayList<Microblog> result) {
		String message = "Couldn't get microblogs";

		Log.e(_CLASS_NAME, message, _exception);

		ToastUtil.show(
			_activity, message + ": " + _exception.getMessage(), true);
	}

	public void onPostExecute(ArrayList<Microblog> microblogs) {
		_activity.updateMicroblogs(microblogs);
	}

	private static String _CLASS_NAME = MicroblogsAsyncTask.class.getName();

	private MainActivity _activity;
	private Exception _exception;

}