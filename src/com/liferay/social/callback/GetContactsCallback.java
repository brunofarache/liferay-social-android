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

package com.liferay.social.callback;

import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.social.fragment.ContactsFragment;
import com.liferay.social.model.User;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class GetContactsCallback
	extends GenericAsyncTaskCallback<ArrayList<User>> {

	public GetContactsCallback(ContactsFragment fragment) {
		_fragment = fragment;
	}

	public void onFailure(Exception e) {
		ToastUtil.show(
			_fragment.getActivity(), "Couldn't fetch users: " + e.getMessage(),
			true);
	}

	public void onSuccess(ArrayList<User> users) {
		_fragment.setUsers(users);
	}

	public ArrayList<User> transform(Object obj) throws Exception {
		ArrayList<User> users = new ArrayList<User>();

		JSONArray jsonArray = (JSONArray)obj;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = (JSONObject)jsonArray.get(i);

			users.add(new User(json));
		}

		return users;
	}

	private ContactsFragment _fragment;

}