/*
 * Copyright (c) 2015 Complexible Inc. <http://complexible.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.complexible.clearbit;

import com.google.common.base.Objects;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.1
 */
public final class TwitterInfo {
	private String mHandle;
	private long mId;
	private String mBio;
	private long mFollowers;
	private long mFollowing;
	private String mLocation;
	private String mSite;
	private String mAvatar;

	public String getAvatar() {
		return mAvatar;
	}

	public void setAvatar(final String theAvatar) {
		mAvatar = theAvatar;
	}

	public String getBio() {
		return mBio;
	}

	public void setBio(final String theBio) {
		mBio = theBio;
	}

	public long getFollowers() {
		return mFollowers;
	}

	public void setFollowers(final long theFollowers) {
		mFollowers = theFollowers;
	}

	public long getFollowing() {
		return mFollowing;
	}

	public void setFollowing(final long theFollowing) {
		mFollowing = theFollowing;
	}

	public String getHandle() {
		return mHandle;
	}

	public void setHandle(final String theHandle) {
		mHandle = theHandle;
	}

	public long getId() {
		return mId;
	}

	public void setId(final long theId) {
		mId = theId;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(final String theLocation) {
		mLocation = theLocation;
	}

	public String getSite() {
		return mSite;
	}

	public void setSite(final String theSite) {
		mSite = theSite;
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(mHandle);
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		if (theObj == this) {
			return true;
		}
		else if (theObj instanceof TwitterInfo) {
			TwitterInfo aInfo = (TwitterInfo) theObj;
			return Objects.equal(mHandle, aInfo.mHandle);
		}
		else {
			return false;
		}
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Twitter(%s)", mHandle);
	}
}
