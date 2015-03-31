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
import java.text.SimpleDateFormat;

import com.complexible.common.http.ApacheHttp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.1
 */
public class ClearBitAPI implements AutoCloseable {
	/**
	 * the logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClearBitAPI.class);

	private final CloseableHttpClient mClient;

	private final String mKey;

	private final boolean mStreaming;

	private static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();

		MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss"));
	}

	public ClearBitAPI(final String theKey) {
		this(theKey, false /* use streaming */);
	}

	public ClearBitAPI(final String theKey, final boolean theUseStreamingAPI) {
		mKey = Preconditions.checkNotNull(theKey);
		mStreaming = theUseStreamingAPI;

		mClient = HttpClientBuilder.create()
		                             .setUserAgent("clearbit.api")
		                             .setConnectionManager(new PoolingHttpClientConnectionManager())
		                             .build();
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		mClient.close();
	}

	/**
	 * Look up a person by email.
	 *
	 * @param theEmail  the email to lookup
	 * @return          the person, if found, or an empty optional if the lookup was not successful or returned an
	 *                  async result
	 *
	 * @throws IOException  if there was an error looking up the person
	 */
	public Optional<Person> findPerson(final String theEmail) throws IOException {
		final HttpGet aRequest = new HttpGet(String.format("https://person%s.clearbit.com/v1/people/email/%s",
		                                                   mStreaming ? "-stream" : "",
		                                                   theEmail));

		ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);

		HttpResponse aResponse = null;

		try {
			aResponse = mClient.execute(aRequest);

			if (aResponse.getStatusLine().getStatusCode() == 404) {
				return Optional.absent();
			}
			else if (aResponse.getStatusLine().getStatusCode() == 202) {
				LOGGER.warn("Finding person {} resulted in an async result.  Try back to ClearBit later", theEmail);
				return Optional.absent();
			}
			else if (aResponse.getStatusLine().getStatusCode() != 200) {
				throw new IOException(String.format("There was an error finding the person, result code was %s and the message was %s",
				                                    aResponse.getStatusLine().getStatusCode(),
				                                    aResponse.getStatusLine().getReasonPhrase()));
			}

			Person aPerson = MAPPER.readValue(aResponse.getEntity().getContent(), Person.class);

			scrubMissingValues(aPerson);

			return Optional.of(aPerson);
		}
		finally {
			ApacheHttp.HttpResponses.consumeQuietly(aResponse);
		}
	}

	private void scrubMissingValues(final Person thePerson) {
		if (thePerson.getGithub().getHandle() == null) {
			thePerson.setGithub(null);
		}

		if (thePerson.getTwitter().getHandle() == null) {
			thePerson.setTwitter(null);
		}

		if (thePerson.getFacebook().getHandle() == null) {
			thePerson.setFacebook(null);
		}

		if (thePerson.getLinkedin().getHandle() == null) {
			thePerson.setLinkedin(null);
		}

		if (thePerson.getGoogleplus().getHandle() == null) {
			thePerson.setGoogleplus(null);
		}

		if (thePerson.getFoursquare().getHandle() == null) {
			thePerson.setFoursquare(null);
		}

		if (thePerson.getAngellist().getHandle() == null) {
			thePerson.setAngellist(null);
		}

		if (thePerson.getAboutme().getHandle() == null) {
			thePerson.setAboutme(null);
		}
	}

	private void scrubMissingValues(final Company theCompany) {
		if (theCompany.getTwitter().getHandle() == null) {
			theCompany.setTwitter(null);
		}

		if (theCompany.getFacebook().getHandle() == null) {
			theCompany.setFacebook(null);
		}

		if (theCompany.getLinkedin().getHandle() == null) {
			theCompany.setLinkedin(null);
		}

		if (theCompany.getAngellist().getHandle() == null) {
			theCompany.setAngellist(null);
		}
	}

	/**
	 * Look up a company by their domain.
	 *
	 * @param theEmail  the domain to lookup
	 * @return          the company, if found, or an empty optional if the lookup was not successful or returned an
	 *                  async result
	 *
	 * @throws IOException  if there was an error looking up the person
	 */
	public Optional<Company> findCompany(final String theDomain) throws IOException {
		final HttpGet aRequest = new HttpGet(String.format("https://company%s.clearbit.com/v1/companies/domain/%s",
		                                     mStreaming ? "-stream" : "",
		                                     theDomain));

		ApacheHttp.HttpRequests.header(aRequest, "Authorization", "Bearer " + mKey);

		HttpResponse aResponse = null;

		try {
			aResponse = mClient.execute(aRequest);

			if (aResponse.getStatusLine().getStatusCode() == 404) {
				return Optional.absent();
			}
			else if (aResponse.getStatusLine().getStatusCode() == 202) {
				LOGGER.warn("Finding person {} resulted in an async result.  Try back to ClearBit later", theDomain);
				return Optional.absent();
			}
			else if (aResponse.getStatusLine().getStatusCode() != 200) {
				throw new IOException(String.format("There was an error finding the company, result code was %s and the message was %s",
				                                    aResponse.getStatusLine().getStatusCode(),
				                                    aResponse.getStatusLine().getReasonPhrase()));
			}

			final Company aCompany = MAPPER.readValue(aResponse.getEntity().getContent(), Company.class);

			scrubMissingValues(aCompany);

			return Optional.of(aCompany);
		}
		finally {
			ApacheHttp.HttpResponses.consumeQuietly(aResponse);
		}
	}
}
