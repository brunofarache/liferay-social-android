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

package com.liferay.social.fragment;

import android.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liferay.social.R;
import com.liferay.social.model.Contact;
import com.liferay.social.model.User;

import java.util.ArrayList;

/**
 * @author Bruno Farache
 */
public class DetailsFragment extends Fragment {

	public static final String TAG = DetailsFragment.class.getSimpleName();

	public View onCreateView(
		LayoutInflater inflater, ViewGroup viewGroup, Bundle state) {

		return inflater.inflate(R.layout.details, null);
	}

	public void onViewCreated(View view, Bundle state) {
		Bundle arguments = getArguments();

		if (arguments != null) {
			User user = (User)arguments.getSerializable("user");

			TextView name = (TextView)findViewById(R.id.name_text_view);
			name.setText(user.getFullName());

			Contact contact = user.getContact();

			TextView email = (TextView)findViewById(R.id.email_text_view);
			email.setText(contact.getEmailAddress());

			TextView birthday = (TextView)findViewById(R.id.birthday_text_view);
			birthday.setText(contact.getBirthday());

			ArrayList<String> phones = contact.getPhones();

			if (!phones.isEmpty()) {
				ListView listView = (ListView)findViewById(
					R.id.phones_list_view);

				ListAdapter adapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1, phones);

				listView.setAdapter(adapter);
			}
		}
	}

	protected View findViewById(int id) {
		return getActivity().findViewById(id);
	}

}