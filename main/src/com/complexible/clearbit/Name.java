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
public final class Name {
	private String mFullName;
	private String mGivenName;
	private String mFamilyName;

	public Name() {
	}

	public Name(final String theFullName, final String theGivenName, final String theFamilyName) {
		mFullName = theFullName;
		mGivenName = theGivenName;
		mFamilyName = theFamilyName;
	}

	public String getFamilyName() {
		return mFamilyName;
	}

	public void setFamilyName(final String theFamilyName) {
		mFamilyName = theFamilyName;
	}

	public String getFullName() {
		return mFullName;
	}

	public void setFullName(final String theFullName) {
		mFullName = theFullName;
	}

	public String getGivenName() {
		return mGivenName;
	}

	public void setGivenName(final String theGivenName) {
		mGivenName = theGivenName;
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(mFamilyName, mFullName, mGivenName);
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		if (theObj == this) {
			return true;
		}
		else if (theObj instanceof Name) {
			Name aName = (Name) theObj;

			return Objects.equal(mFullName, aName.mFullName)
				&& Objects.equal(mFamilyName, aName.mFamilyName)
				&& Objects.equal(mGivenName, aName.mGivenName);
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
		return MoreObjects.toStringHelper("Name")
			.add("fullName", mFullName)
			.add("givenName", mGivenName)
			.add("familyName", mFamilyName)
			.toString();
	}
}
