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

import android.annotation.SuppressLint;

import android.app.ListActivity;

import android.os.Build;
import android.os.Bundle;

import android.view.Menu;

import android.widget.ArrayAdapter;

import com.liferay.social.R;
import com.liferay.social.model.Microblog;
import com.liferay.social.task.MicroblogsAsyncTask;
import com.liferay.social.util.SettingsUtil;

import java.util.ArrayList;

/**
 * @author Josiane Bezerra
 */

public class MainActivity extends ListActivity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		SettingsUtil.init(this);

		ArrayAdapter<Microblog> adapter = new ArrayAdapter<Microblog>(
			this, android.R.layout.simple_list_item_1);

		setListAdapter(adapter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void onResume() {
		super.onResume();

		MicroblogsAsyncTask task = new MicroblogsAsyncTask(this);

		task.execute();
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	public void updateMicroblogs(ArrayList<Microblog> microblogs) {
		ArrayAdapter<Microblog> adapter =
			(ArrayAdapter<Microblog>)getListAdapter();

		adapter.clear();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			adapter.addAll(microblogs);
		}
		else {
			for (Microblog microblog : microblogs) {
				adapter.add(microblog);
			}
		}
	}

}