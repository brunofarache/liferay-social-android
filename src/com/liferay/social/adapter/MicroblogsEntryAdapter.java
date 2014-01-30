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

import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liferay.social.R;
import com.liferay.social.model.MicroblogsEntry;

import java.util.List;

/**
 * @author Josiane Bezerra
 * @author Silvio Santos
 */
public class MicroblogsEntryAdapter extends ArrayAdapter<MicroblogsEntry> {

	public MicroblogsEntryAdapter(
		Context context, int resource, List<MicroblogsEntry> entries) {

		super(context, resource, entries);

		_context = context;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)_context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.microblogs_list_item, null);

			holder = new ViewHolder();

			holder.portrait = (ImageView)view.findViewById(R.id.portrait);
			holder.username = (TextView)view.findViewById(R.id.username);
			holder.content = (TextView)view.findViewById(R.id.content);

			view.setTag(holder);
		}
		else {
			holder = (ViewHolder)view.getTag();
		}

		MicroblogsEntry entry = getItem(position);

		holder.portrait.setImageResource(R.drawable.ic_contact_picture);
		holder.content.setText(entry.getContent());
		holder.username.setText(entry.getUserName());

		return view;
	}

	public void setEntries(List<MicroblogsEntry> entries) {
		setNotifyOnChange(false);

		clear();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			addAll(entries);
		}
		else {
			for (MicroblogsEntry entry : entries) {
				add(entry);
			}
		}

		notifyDataSetChanged();
	}

	private Context _context;

	private class ViewHolder {

		ImageView portrait;
		TextView username;
		TextView content;

	}

}