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

package com.liferay.social.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Josiane Bezerra
 */

public class Microblog {

	public static final String COMPANY_ID = "companyId";

	public static final String CONTENT = "content";

	public static final String CREATE_DATE = "createDate";

	public static final String MICROBLOGS_ENTRY_ID = "microblogsEntryId";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String RECEIVER_MICROBLOGS_ENTRY_ID =
		"receiverMicroblogsEntryId";

	public static final String RECEIVER_USER_ID = "receiverUserId";

	public static final String SOCIAL_RELATION_TYPE = "socialRelationType";

	public static final String TYPE = "type";

	public static final String USER_ID = "userId";

	public static final String USER_NAME = "userName";

	public Microblog(JSONObject jsonObj) throws JSONException {
		_companyId = jsonObj.getLong(COMPANY_ID);
		_content = jsonObj.getString(CONTENT);
		_createDate = jsonObj.getLong(CREATE_DATE);
		_microblogsEntryId = jsonObj.getLong(MICROBLOGS_ENTRY_ID);
		_modifiedDate = jsonObj.getLong(MODIFIED_DATE);

		_receiverMicroblogsEntryId = jsonObj.getLong(
			RECEIVER_MICROBLOGS_ENTRY_ID);

		_receiverUserId = jsonObj.getLong(RECEIVER_USER_ID);
		_type = jsonObj.getInt(TYPE);
		_userId = jsonObj.getLong(USER_ID);
		_userName = jsonObj.getString(USER_NAME);
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getContent() {
		return _content;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public long getMicroblogsEntryId() {
		return _microblogsEntryId;
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public long getReceiverMicroblogsEntryId() {
		return _receiverMicroblogsEntryId;
	}

	public long getReceiverUserId() {
		return _receiverUserId;
	}

	public int getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntryId = microblogsEntryId;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setReceiverMicroblogsEntryId(long receiverMicroblogsEntryId) {
		_receiverMicroblogsEntryId = receiverMicroblogsEntryId;
	}

	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;
	}

	public void setType(int type) {
		_type = type;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public String toString() {
		return _content;
	}

	private long _companyId;
	private String _content;
	private long _createDate;
	private long _microblogsEntryId;
	private long _modifiedDate;
	private long _receiverMicroblogsEntryId;
	private long _receiverUserId;
	private int _type;
	private long _userId;
	private String _userName;

}