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

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

import com.complexible.common.http.ApacheHttp;
import com.google.common.base.Preconditions;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.2
 */
public class EnrichmentAPIV2 implements EnrichmentAPI {
	private final CloseableHttpClient mClient;

	private final String mKey;

	private final boolean mStreaming;

	public EnrichmentAPIV2(final String theKey) {
		this(theKey, false /* use streaming */);
	}

	public EnrichmentAPIV2(final String theKey, final boolean theUseStreamingAPI) {
		mKey = Preconditions.checkNotNull(theKey);
		mStreaming = theUseStreamingAPI;

		mClient = HttpClientBuilder.create()
		                             .setUserAgent("clearbit.api")
		                             .setConnectionManager(new PoolingHttpClientConnectionManager())
		                             .build();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void close() throws IOException {
		mClient.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonLookup person(final String theEmail) throws IOException {
		return new AbstractPersonLookup(theEmail) {
			@Override
			public Optional<Person> lookup() {
				final HttpGet aRequest = new HttpGet(String.format("https://person%s.clearbit.com/v2/people/find?email=%s",
				                                                   mStreaming ? "-stream" : "",
				                                                   mEmail));

				ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);
				ApacheHttp.HttpRequests.header(aRequest, "API-Version", "2016-01-04");

				if (mFirstName != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "given_name", mFirstName);
				}

				if (mLastName != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "family_name", mLastName);
				}

				if (mCompany != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "company", mCompany);
				}

				if (mIP != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "ip_address", mIP);
				}

				if (mDomain != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "company_domain", mDomain);

				}

				if (mLocation != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "location", mFirstName);

				}

				if (mLinkedIn != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "twitter", mLinkedIn);
				}

				if (mTwitter != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "linkedin", mTwitter);
				}

				if (mFacebook != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "facebook", mFacebook);
				}

				try {
					return Clearbit.person(mClient.execute(aRequest));
				}
				catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			}
		};
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompanyLookup company(final String theDomain) throws IOException {
		return new AbstractCompanyLookup(theDomain) {
			@Override
			public Optional<Company> lookup() {
				final HttpGet aRequest = new HttpGet(String.format("https://company%s.clearbit.com/v2/companies/find",
				                                                   mStreaming ? "-stream" : "",
				                                                   mDomain));

				ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);
				ApacheHttp.HttpRequests.header(aRequest, "API-Version", "2016-05-18");

				ApacheHttp.HttpRequests.appendURIParameter(aRequest, "domain", mDomain);

				if (mName != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "company_name", mName);
				}

				if (mLinkedIn != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "twitter", mLinkedIn);
				}

				if (mTwitter != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "linkedin", mTwitter);
				}

				if (mFacebook != null) {
					ApacheHttp.HttpRequests.appendURIParameter(aRequest, "facebook", mFacebook);
				}


				try {
					return Clearbit.company(mClient.execute(aRequest));
				}
				catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			}
		};
	}
}
