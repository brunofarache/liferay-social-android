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

import android.R;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.liferay.social.model.User;

import java.util.ArrayList;

/**
 * @author Bruno Farache
 */
public class UsersAdapter extends BaseListAdapter<User> {

	public UsersAdapter(Context context, ArrayList<User> users) {
		super(context, users);
	}

	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.simple_list_item_1, null);

			holder = new ViewHolder();
			holder.name = (TextView)view.findViewById(R.id.text1);
			view.setTag(holder);
		}
		else {
			holder = (ViewHolder)view.getTag();
		}

		User user = getItem(position);

		holder.name.setText(user.getFullName());

		return view;
	}

	private class ViewHolder {

		TextView name;

	}

}