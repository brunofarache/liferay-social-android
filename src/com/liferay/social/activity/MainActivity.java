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

package com.liferay.social.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liferay.social.R;
import com.liferay.social.fragment.MicroblogsFragment;

/**
 * @author Josiane Bezerra
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class MainActivity extends Activity
		implements AdapterView.OnItemClickListener {

	public void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.main);

		_drawer = (DrawerLayout)findViewById(R.id.drawer);

		ListView menu = (ListView)findViewById(R.id.menu);
		String[] menuItems = getResources().getStringArray(R.array.menu_items);

		menu.setAdapter(
			new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, menuItems));

		menu.setOnItemClickListener(this);

		FragmentManager manager = getFragmentManager();

		Fragment microblogsFragment = manager.findFragmentByTag(
			MicroblogsFragment.TAG);

		if (microblogsFragment == null) {
			FragmentTransaction transaction = manager.beginTransaction();

			transaction.add(
				R.id.right_fragment, new MicroblogsFragment(),
				MicroblogsFragment.TAG);

			transaction.commit();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);

		return true;
	}

	public void onItemClick(
		AdapterView adapterView, View view, int position, long id) {

		switch (position) {
			case 0:
				FragmentManager manager = getFragmentManager();

				Fragment microblogsFragment = manager.findFragmentByTag(
					MicroblogsFragment.TAG);

				FragmentTransaction transaction = manager.beginTransaction();

				if (microblogsFragment == null) {
					microblogsFragment = new MicroblogsFragment();
				}

				transaction.replace(
					R.id.right_fragment, microblogsFragment,
					MicroblogsFragment.TAG);

				transaction.commit();
				_drawer.closeDrawers();
		}
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

	private DrawerLayout _drawer;

}