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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.1
 */
public final class EmploymentInfo {
	private String mName;
	private String mTitle;
	private String mDomain;

	public String getDomain() {
		return mDomain;
	}

	public void setDomain(final String theDomain) {
		mDomain = theDomain;
	}

	public String getName() {
		return mName;
	}

	public void setName(final String theName) {
		mName = theName;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(final String theTitle) {
		mTitle = theTitle;
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(mName, mTitle, mDomain);
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		if (theObj == this) {
			return true;
		}
		else if (theObj instanceof EmploymentInfo) {
			EmploymentInfo aInfo = (EmploymentInfo) theObj;

			return Objects.equal(mName, aInfo.mName)
				&& Objects.equal(mTitle, aInfo.mTitle)
				&& Objects.equal(mDomain, aInfo.mDomain);
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
		return MoreObjects.toStringHelper("Employment")
		                  .add("name", mName)
		                  .add("title", mTitle)
		                  .toString();
	}
}
