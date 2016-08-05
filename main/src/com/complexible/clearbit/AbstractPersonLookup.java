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
public abstract class AbstractPersonLookup implements PersonLookup {
	protected String mTwitter;
	protected String mLinkedIn;
	protected String mFacebook;

	protected String mEmail;

	protected String mCompany;
	protected String mFirstName;
	protected String mDomain;
	protected String mLastName;
	protected String mLocation;
	protected String mIP;

	public AbstractPersonLookup(final String theEmail) {
		mEmail = Preconditions.checkNotNull(theEmail);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup company(final String theCompany) {
		mCompany = theCompany;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup email(final String theEmail) {
		mEmail = Preconditions.checkNotNull(theEmail);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup firstName(final String theFirstName) {
		mFirstName = theFirstName;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup lastName(final String theLastName) {
		mLastName = theLastName;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup ip(final String theIP) {
		mIP = theIP;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup location(final String theLocation) {
		mLocation = theLocation;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup companyDomain(final String theDomain) {
		mDomain = theDomain;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup linkedIn(final String theURL) {
		mLinkedIn = theURL;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup facebook(final String theURL) {
		mFacebook = theURL;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup twitter(final String theHandle) {
		mTwitter = theHandle;
		return this;
	}
}
