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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

import com.complexible.common.http.ApacheHttp;
import com.google.common.base.Charsets;
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
public class EnrichmentAPIV1 implements EnrichmentAPI {
	private final CloseableHttpClient mClient;

	private final String mKey;

	private final boolean mStreaming;

	public EnrichmentAPIV1(final String theKey) {
		this(theKey, false /* use streaming */);
	}

	public EnrichmentAPIV1(final String theKey, final boolean theUseStreamingAPI) {
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
				final HttpGet aRequest;
				try {
					aRequest = new HttpGet(String.format("https://person%s.clearbit.com/v1/people/email/%s",
					                                                   mStreaming ? "-stream" : "",
					                                     URLEncoder.encode(mEmail, Charsets.UTF_8.name())));
				}
				catch (UnsupportedEncodingException e) {
					throw new AssertionError("Should not happen");
				}

				ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);
				ApacheHttp.HttpRequests.header(aRequest, "API-Version", "2015-04-30");

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
				final HttpGet aRequest;
				try {
					aRequest = new HttpGet(String.format("https://company%s.clearbit.com/v1/companies/domain/%s",
					                                                   mStreaming ? "-stream" : "",
					                                     URLEncoder.encode(mDomain, Charsets.UTF_8.name())));
				}
				catch (UnsupportedEncodingException e) {
					throw new AssertionError("Should not occur");
				}

				ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);
				ApacheHttp.HttpRequests.header(aRequest, "API-Version", "2015-04-30");

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
