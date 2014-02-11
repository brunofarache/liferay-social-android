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

package com.liferay.social.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.liferay.social.R;
import com.liferay.social.model.User;
import com.liferay.social.task.PortraitAsyncTask;
import com.liferay.social.util.PrefsUtil;

import java.util.ArrayList;

/**
 * @author Bruno Farache
 */
public class UsersAdapter extends BaseListAdapter<User> {

	public UsersAdapter(Context context, ArrayList<User> users) {
		super(context, users);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(
			Context.LAYOUT_INFLATER_SERVICE);

		view = inflater.inflate(R.layout.user_list_item, null);

		ImageView portrait = (ImageView)view.findViewById(R.id.portrait);
		TextView name = (TextView)view.findViewById(R.id.name);

		User user = getItem(position);

		portrait.setImageResource(R.drawable.ic_contact_picture);
		name.setText(user.getFullName());

		PortraitAsyncTask task = new PortraitAsyncTask(
			PrefsUtil.getSession(), user, portrait);

		task.execute();

		return view;
	}

}