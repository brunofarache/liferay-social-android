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

package com.liferay.social.service;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.microblogsentry.MicroblogsentryService;
import com.liferay.social.activity.MainActivity;
import com.liferay.social.callback.GetMicroblogsEntryCallback;
import com.liferay.social.util.SettingsUtil;

/**
 * @author Silvio Santos
 */
public class MicroblogsService {

	public static void getMicroblogsEntries(
			MainActivity activity, int start, int end)
		throws Exception {

		Session session = SettingsUtil.getSession();

		GetMicroblogsEntryCallback callback = new GetMicroblogsEntryCallback(
			activity);

		session.setCallback(callback);

		MicroblogsentryService service = new MicroblogsentryService(session);

		service.getMicroblogsEntries(start, end);
	}

}