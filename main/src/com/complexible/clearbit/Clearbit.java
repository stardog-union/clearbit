package com.complexible.clearbit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.2
 * @version 0.2
 */
public final class Clearbit {
	/**
	 * the logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Clearbit.class);

	private static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();

		MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss"));
	}

	/**
	 * no instances
	 */
	private Clearbit() {
		throw new AssertionError();
	}

	public static CloseableHttpResponse checkForErrors(final CloseableHttpResponse theResponse) throws IOException {
		final int aCode = theResponse.getStatusLine().getStatusCode();

		if (aCode == HttpStatus.SC_BAD_REQUEST
		    || aCode == HttpStatus.SC_UNPROCESSABLE_ENTITY
			|| aCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {

			final Error aError = MAPPER.readValue(theResponse.getEntity().getContent(), Error.class);

			if (aCode == HttpStatus.SC_BAD_REQUEST) {
				throw new IllegalArgumentException(aError.getMessage());
			}
			else {
				throw new RuntimeException(aError.getMessage());
			}
		}

		return theResponse;
	}

	public static Optional<Person> person(final CloseableHttpResponse theResponse) throws IOException {
		try (CloseableHttpResponse aResponse = theResponse) {

			if (aResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				return Optional.empty();
			}
			else if (aResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_ACCEPTED) {
				LOGGER.warn("Finding person resulted in an async result.  Try back to ClearBit later");
				return Optional.empty();
			}
			else if (aResponse.getStatusLine().getStatusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
				LOGGER.warn("Person was unprocessable");
				return Optional.empty();
			}

			checkForErrors(theResponse);

			Person aPerson = MAPPER.readValue(aResponse.getEntity().getContent(), Person.class);

			scrubMissingValues(aPerson);

			return Optional.of(aPerson);
		}
	}

	public static Optional<Company> company(final CloseableHttpResponse theResponse) throws IOException {
		try (CloseableHttpResponse aResponse = theResponse) {
			if (aResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				return Optional.empty();
			}
			else if (aResponse.getStatusLine().getStatusCode() == HttpStatus.SC_ACCEPTED) {
				LOGGER.warn("Finding company resulted in an async result.  Try back to ClearBit later");
				return Optional.empty();
			}
			else if (aResponse.getStatusLine().getStatusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
				LOGGER.warn("Company was unprocessable");
				return Optional.empty();
			}

			checkForErrors(theResponse);

			final Company aCompany = MAPPER.readValue(aResponse.getEntity().getContent(), Company.class);

			scrubMissingValues(aCompany);

			return Optional.of(aCompany);
		}
	}

	protected static Person scrubMissingValues(final Person thePerson) {
		if (thePerson.getGithub() != null && thePerson.getGithub().getHandle() == null) {
			thePerson.setGithub(null);
		}

		if (thePerson.getTwitter() != null && thePerson.getTwitter().getHandle() == null) {
			thePerson.setTwitter(null);
		}

		if (thePerson.getFacebook() != null && thePerson.getFacebook().getHandle() == null) {
			thePerson.setFacebook(null);
		}

		if (thePerson.getLinkedin() != null && thePerson.getLinkedin().getHandle() == null) {
			thePerson.setLinkedin(null);
		}

		if (thePerson.getGoogleplus() != null && thePerson.getGoogleplus().getHandle() == null) {
			thePerson.setGoogleplus(null);
		}

		if (thePerson.getFoursquare() != null && thePerson.getFoursquare().getHandle() == null) {
			thePerson.setFoursquare(null);
		}

		if (thePerson.getAngellist() != null && thePerson.getAngellist().getHandle() == null) {
			thePerson.setAngellist(null);
		}

		if (thePerson.getAboutme() != null && thePerson.getAboutme().getHandle() == null) {
			thePerson.setAboutme(null);
		}

		if (thePerson.getCompany() != null) {
			if (thePerson.getCompany().getName() == null) {
				thePerson.setCompany(null);
			}
			else {
				thePerson.setCompany(scrubMissingValues(thePerson.getCompany()));
			}
		}

		return thePerson;
	}

	private static Company scrubMissingValues(final Company theCompany) {
		if (theCompany.getTwitter() != null && theCompany.getTwitter().getHandle() == null) {
			theCompany.setTwitter(null);
		}

		if (theCompany.getFacebook() != null && theCompany.getFacebook().getHandle() == null) {
			theCompany.setFacebook(null);
		}

		if (theCompany.getLinkedin() != null && theCompany.getLinkedin().getHandle() == null) {
			theCompany.setLinkedin(null);
		}

		if (theCompany.getAngellist() != null && theCompany.getAngellist().getHandle() == null) {
			theCompany.setAngellist(null);
		}

		return theCompany;
	}

	public static final class Error {
		private String mType;
		private String mMessage;

		public String getMessage() {
			return mMessage;
		}

		public void setMessage(final String theMessage) {
			mMessage = theMessage;
		}

		public String getType() {
			return mType;
		}

		public void setType(final String theType) {
			mType = theType;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(final Object atheO) {
			if (this == atheO) {
				return true;
			}
			if (atheO == null || getClass() != atheO.getClass()) {
				return false;
			}
			final Error aError = (Error) atheO;
			return Objects.equals(mType, aError.mType) &&
			       Objects.equals(mMessage, aError.mMessage);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return Objects.hash(mType, mMessage);
		}
	}
}
