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

import android.app.ListFragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liferay.mobile.android.service.ServiceFactory;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.microblogsentry.MicroblogsentryService;
import com.liferay.social.adapter.MicroblogsAdapter;
import com.liferay.social.callback.GetMicroblogsEntriesCallback;
import com.liferay.social.model.MicroblogsEntry;
import com.liferay.social.util.PrefsUtil;
import com.liferay.social.util.ToastUtil;

import java.util.ArrayList;

/**
 * @author Bruno Farache
 */
public class MicroblogsFragment extends ListFragment {

	public static final String TAG = MicroblogsFragment.class.getSimpleName();

	public View onCreateView(
		LayoutInflater inflater, ViewGroup viewGroup, Bundle state) {

		ArrayList<MicroblogsEntry> entries = new ArrayList<MicroblogsEntry>();

		MicroblogsAdapter adapter = new MicroblogsAdapter(
			getActivity(), entries);

		setListAdapter(adapter);

		try {
			Session session = PrefsUtil.getSession(
				new GetMicroblogsEntriesCallback(this));

			MicroblogsentryService service = ServiceFactory.getService(
				MicroblogsentryService.class, session);

			service.getMicroblogsEntries(-1, -1);
		}
		catch (Exception e) {
			ToastUtil.show(getActivity(), e.getMessage());
		}

		return super.onCreateView(inflater, viewGroup, state);
	}

	public void setEntries(ArrayList<MicroblogsEntry> entries) {
		((MicroblogsAdapter)getListAdapter()).setEntries(entries);
	}

}