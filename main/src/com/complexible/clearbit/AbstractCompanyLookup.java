/*
 * Copyright (c) 2015-2016 Complexible Inc. <http://complexible.com>
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

import com.google.common.base.Preconditions;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.2
 * @version 0.2
 */
public abstract class AbstractCompanyLookup implements CompanyLookup {
	protected String mDomain;
	protected String mName;
	protected String mTwitter;
	protected String mLinkedIn;
	protected String mFacebook;

	public AbstractCompanyLookup(final String theDomain) {
		mDomain = Preconditions.checkNotNull(theDomain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup domain(final String theDomain) {
		mDomain = Preconditions.checkNotNull(theDomain);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup name(final String theName) {
		mName = theName;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup linkedIn(final String theURL) {
		mLinkedIn = theURL;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup facebook(final String theURL) {
		mFacebook = theURL;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup twitter(final String theHandle) {
		mTwitter = theHandle;
		return this;
	}
}
