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

package com.liferay.social.model;

import java.io.Serializable;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class Contact implements Serializable {

	public Contact(JSONArray jsonArray) throws JSONException {
		JSONObject contact = jsonArray.getJSONObject(0);
		_emailAddress = contact.getString("emailAddress");

		JSONArray phones = jsonArray.getJSONArray(1);

		for (int i = 0; i < phones.length(); i++) {
			JSONObject phone = phones.getJSONObject(i);
			_phones.add(phone.getString("number"));
		}

		long timestamp = contact.getLong("birthday");

		if (timestamp > 0) {
			_birthday = _getDateFormatter().format(new Date(timestamp));
		}
	}

	public String getBirthday() {
		return _birthday;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public ArrayList<String> getPhones() {
		return _phones;
	}

	public void setBirthday(String birthday) {
		_birthday = birthday;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setPhones(ArrayList<String> phones) {
		_phones = phones;
	}

	private DateFormat _getDateFormatter() {
		if (_dateFormatter == null) {
			_dateFormatter = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.SHORT);
		}

		return _dateFormatter;
	}

	private String _birthday;
	private transient DateFormat _dateFormatter;
	private String _emailAddress;
	private ArrayList<String> _phones = new ArrayList<String>();

}