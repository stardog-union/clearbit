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

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.2
 */
public abstract class EnrichmentAPITests {
	protected final String getKey() {
		return System.getProperty("clearbit.key");
	}

	protected abstract EnrichmentAPI createAPI();

	@Test
	public void testGetPerson() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			Person aPerson = aClearbit.person("christianbaroni@me.com").lookup().get();

			assertEquals(new Name("Christian Baroni", "Christian", "Baroni"), aPerson.getName());
		}
	}

	@Test
	public void testGetCompany() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			Company aCompany = aClearbit.company("stripe.com").lookup().get();

			assertEquals("Stripe", aCompany.getName());
			assertEquals("https://stripe.com", aCompany.getURL());
		}
	}

	@Test
	@Ignore
	public void testLeadScoringCompany() throws Exception {
	}

	@Test
	public void testMissingPerson() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			assertFalse(aClearbit.person("employee@notarealcompany.com").lookup().isPresent());
		}
	}

	@Test
	public void testInvalidPerson() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			assertFalse(aClearbit.person("employee").lookup().isPresent());
		}
	}

	@Test
	public void testMissingCompany() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			assertFalse(aClearbit.company("notarealcompany.com").lookup().isPresent());
		}
	}

	@Test
	public void testInvalidCompany() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			assertFalse(aClearbit.company("not a domain").lookup().isPresent());
		}
	}
}
