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
import com.liferay.social.adapter.MicroblogsEntryAdapter;
import com.liferay.social.model.MicroblogsEntryModel;
import com.liferay.social.service.MicroblogsService;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Josiane Bezerra
 * @author Silvio Santos
 */

public class MainActivity extends Activity {

	public void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.main);

		List<MicroblogsEntryModel> entries =
			new ArrayList<MicroblogsEntryModel>();

		_adapter = new MicroblogsEntryAdapter(
			this, R.layout.list_item, entries);

		ListView listView = (ListView)findViewById(R.id.list);
		listView.setAdapter(_adapter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);

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

		try {
			MicroblogsService.getMicroblogsEntries(this, -1, -1);
		}
		catch (Exception e) {
			ToastUtil.show(this, e.getMessage());
		}
	}

	public void updateMicroblogs(List<MicroblogsEntryModel> entries) {
		_adapter.setEntries(entries);
	}

	private MicroblogsEntryAdapter _adapter;

}