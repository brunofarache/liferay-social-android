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

import android.widget.TextView;

import com.liferay.social.R;
import com.liferay.social.model.MicroblogsEntry;

import java.util.ArrayList;

/**
 * @author Josiane Bezerra
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class MicroblogsAdapter extends BaseListAdapter<MicroblogsEntry> {

	public MicroblogsAdapter(
		Context context, ArrayList<MicroblogsEntry> entries) {

		super(context, entries);
	}

	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.microblogs_list_item, null);

			holder = new ViewHolder();

			holder.username = (TextView)view.findViewById(R.id.username);
			holder.content = (TextView)view.findViewById(R.id.content);

			view.setTag(holder);
		}
		else {
			holder = (ViewHolder)view.getTag();
		}

		MicroblogsEntry entry = getItem(position);

		holder.content.setText(entry.getContent());
		holder.username.setText(entry.getUserName());

		return view;
	}

	private class ViewHolder {

		TextView username;
		TextView content;

	}

}