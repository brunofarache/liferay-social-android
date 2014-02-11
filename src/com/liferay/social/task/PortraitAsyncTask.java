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

package com.liferay.social.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;

import android.widget.ImageView;

import com.liferay.mobile.android.http.HttpUtil;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.util.PortraitUtil;
import com.liferay.social.model.User;

import java.io.IOException;
import java.io.InputStream;

import java.lang.ref.WeakReference;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * @author Bruno Farache
 */
public class PortraitAsyncTask extends AsyncTask<Void, Void, Bitmap> {

	public PortraitAsyncTask(Session session, User user, ImageView imageView) {
		_session = session;
		_user = user;
		_imageView = new WeakReference<ImageView>(imageView);
	}

	protected Bitmap doInBackground(Void... params) {
		InputStream is = null;
		Bitmap bitmap = null;

		try {
			HttpClient client = HttpUtil.getClient(_session);

			String portraitURL = PortraitUtil.getPortraitURL(
				_session, true, _user.getPortraitId(), _user.getUuid());

			HttpGet get = new HttpGet(portraitURL);

			HttpResponse response = client.execute(get);
			is = response.getEntity().getContent();

			bitmap = BitmapFactory.decodeStream(is);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		return bitmap;
	}

	protected void onPostExecute(Bitmap bitmap) {
		ImageView imageView = _imageView.get();

		if (imageView != null) {
			imageView.setImageBitmap(bitmap);
		}
	}

	private final WeakReference<ImageView> _imageView;
	private Session _session;
	private User _user;

}