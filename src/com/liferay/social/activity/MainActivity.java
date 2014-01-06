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

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;

import com.liferay.social.R;
import com.liferay.social.adapter.MicroblogsAdapter;
import com.liferay.social.model.Microblog;
import com.liferay.social.task.MicroblogsAsyncTask;
import com.liferay.social.util.SettingsUtil;

import java.util.ArrayList;

/**
 * @author Josiane Bezerra
 */

public class MainActivity extends Activity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);

		SettingsUtil.init(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.settings:
				Intent intent = new Intent(this, SettingsActivity.class);

				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

				startActivity(intent);

				return true;

			default:
				return false;
		}
	}

	public void onResume() {
		super.onResume();

		MicroblogsAsyncTask task = new MicroblogsAsyncTask(this);

		task.execute();
	}

	public void updateMicroblogs(ArrayList<Microblog> microblogs) {
		ListView list = (ListView)findViewById(R.id.list);

		MicroblogsAdapter adapter = new MicroblogsAdapter(
			this, R.layout.list_item, microblogs);

		list.setAdapter(adapter);
	}

}