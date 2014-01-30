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

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bruno Farache
 */
public abstract class BaseListAdapter<E extends Comparable<E>>
		extends BaseAdapter {

	public BaseListAdapter(Context context, ArrayList<E> entries) {
		this.context = context;

		setEntries(entries);
	}

	public void add(E entry) {
		add(entry, true);
	}

	public void add(E entry, boolean notify) {
		_lock.lock();

		try {
			int index = Collections.binarySearch(entries, entry);

			if (index < 0) {
				index = Math.abs(index + 1);
			}

			entries.add(index, entry);
		}
		finally {
			_lock.unlock();
		}

		if (notify) {
			notifyDataSetChanged();
		}
	}

	public int getCount() {
		return entries.size();
	}

	public ArrayList<E> getEntries() {
		return entries;
	}

	public E getItem(int position) {
		return entries.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public abstract View getView(int position, View view, ViewGroup parent);

	public boolean remove(ArrayList<E> entries) {
		return remove(entries, true);
	}

	public boolean remove(ArrayList<E> entries, boolean notify) {
		_lock.lock();

		boolean removed = false;

		try {
			removed = this.entries.removeAll(entries);
		}
		finally {
			_lock.unlock();
		}

		if (removed && notify) {
			notifyDataSetChanged();
		}

		return removed;
	}

	public boolean remove(E entry) {
		return remove(entry, true);
	}

	public boolean remove(E entry, boolean notify) {
		_lock.lock();

		boolean removed = false;

		try {
			removed = entries.remove(entry);
		}
		finally {
			_lock.unlock();
		}

		if (removed && notify) {
			notifyDataSetChanged();
		}

		return removed;
	}

	public void remove(int index, boolean notify) {
		_lock.lock();

		try {
			entries.remove(index);
		}
		finally {
			_lock.unlock();
		}

		if (notify) {
			notifyDataSetChanged();
		}
	}

	public void setEntries(ArrayList<E> entries) {
		setEntries(entries, true);
	}

	public void setEntries(ArrayList<E> entries, boolean notify) {
		_lock.lock();

		try {
			if (entries == null) {
				entries = new ArrayList<E>();
			}

			this.entries = entries;

			Collections.sort(entries);
		}
		finally {
			_lock.unlock();
		}

		if (notify) {
			notifyDataSetChanged();
		}
	}

	public void update(E entry) {
		update(entry, true);
	}

	public void update(E entry, boolean notify) {
		boolean removed = remove(entry, false);

		if (removed) {
			add(entry, false);
		}

		if (notify) {
			notifyDataSetChanged();
		}
	}

	public void update(E entry, int index, boolean notify) {
		_lock.lock();

		try {
			remove(index, false);
			add(entry, false);
		}
		finally {
			_lock.unlock();
		}

		if (notify) {
			notifyDataSetChanged();
		}
	}

	protected Context context;
	protected ArrayList<E> entries;

	private ReentrantLock _lock = new ReentrantLock();

}