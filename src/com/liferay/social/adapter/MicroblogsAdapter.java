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

package com.liferay.social.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liferay.social.R;
import com.liferay.social.model.Microblog;

import java.util.List;

/**
 * @author Josiane Bezerra
 */

public class MicroblogsAdapter extends ArrayAdapter<Microblog> {

	public MicroblogsAdapter(
			Context context, int resource, List<Microblog> objects) {

		super(context, resource, objects);

		this.context = context;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.list_item, null);

			holder = new ViewHolder();

			holder.portrait = (ImageView)view.findViewById(R.id.portrait);
			holder.username = (TextView)view.findViewById(R.id.username);
			holder.content = (TextView)view.findViewById(R.id.content);

			view.setTag(holder);
		}
		else {
			holder = (ViewHolder)view.getTag();
		}

		Microblog microblog = getItem(position);

		holder.portrait.setImageResource(R.drawable.ic_contact_picture);
		holder.content.setText(microblog.getContent());
		holder.username.setText(microblog.getUserName());

		return view;
	}

	protected Context context;

	private class ViewHolder {

		ImageView portrait;
		TextView username;
		TextView content;
	}

}