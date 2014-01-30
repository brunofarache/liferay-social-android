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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class User implements Comparable<User> {

	public User(JSONObject jsonObj) throws JSONException {
		_emailAddress = jsonObj.getString("emailAddress");
		_fullName = jsonObj.getString("fullName");
		_portalUser = jsonObj.getBoolean("portalUser");

		if (_portalUser) {
			_block = jsonObj.getBoolean("block");
			_connected = jsonObj.getBoolean("connected");
			_connectionRequested = jsonObj.getBoolean("connectionRequested");
			_contactId = jsonObj.getLong("contactId");
			_firstName = jsonObj.getString("firstName");
			_following = jsonObj.getBoolean("following");
			_jobTitle = jsonObj.getString("jobTitle");
			_lastName = jsonObj.getString("lastName");
			_portraitId = jsonObj.getLong("portraitId");
			_userId = jsonObj.getLong("userId");
			_uuid = jsonObj.getString("uuid");
		}
		else {
			_comments = jsonObj.getString("comments");
			_entryId = jsonObj.getLong("entryId");
		}
	}

	public int compareTo(User user) {
		return _fullName.toLowerCase().compareTo(
			user.getFullName().toLowerCase());
	}

	public String getComments() {
		return _comments;
	}

	public long getContactId() {
		return _contactId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public long getEntryId() {
		return _entryId;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _fullName;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String getLastName() {
		return _lastName;
	}

	public long getPortraitId() {
		return _portraitId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isBlock() {
		return _block;
	}

	public boolean isConnected() {
		return _connected;
	}

	public boolean isConnectionRequested() {
		return _connectionRequested;
	}

	public boolean isFollowing() {
		return _following;
	}

	public boolean isPortalUser() {
		return _portalUser;
	}

	public void setBlock(boolean block) {
		_block = block;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public void setConnected(boolean connected) {
		_connected = connected;
	}

	public void setConnectionRequested(boolean connectionRequested) {
		_connectionRequested = connectionRequested;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setFollowing(boolean following) {
		_following = following;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setPortalUser(boolean portalUser) {
		_portalUser = portalUser;
	}

	public void setPortraitId(long portraitId) {
		_portraitId = portraitId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	private boolean _block;
	private String _comments;
	private boolean _connected;
	private boolean _connectionRequested;
	private long _contactId;
	private String _emailAddress;
	private long _entryId;
	private String _firstName;
	private boolean _following;
	private String _fullName;
	private String _jobTitle;
	private String _lastName;
	private boolean _portalUser;
	private long _portraitId;
	private long _userId;
	private String _uuid;

}