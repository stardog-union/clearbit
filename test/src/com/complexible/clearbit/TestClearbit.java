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
 * @version 0.1
 */
public class TestClearbit {
	private String getKey() {
		return System.getProperty("clearbit.key");
	}

	@Test
	public void testGetPerson() throws Exception {
		try (ClearBitAPI aClearbit = new ClearBitAPI(getKey())) {
			Person aPerson = aClearbit.findPerson("christianbaroni@me.com").get();

			assertEquals(new Name("Christian Baroni", "Christian", "Baroni"), aPerson.getName());
		}
	}

	@Test
	public void testGetCompany() throws Exception {
		try (ClearBitAPI aClearbit = new ClearBitAPI(getKey())) {
			Company aCompany = aClearbit.findCompany("stripe.com").get();

			assertEquals("Stripe", aCompany.getName());
			assertEquals("http://stripe.com", aCompany.getURL());
		}
	}

	@Test
	@Ignore
	public void testLeadScoringCompany() throws Exception {
	}

	@Test
	public void testMissingPerson() throws Exception {
		try (ClearBitAPI aClearbit = new ClearBitAPI(getKey())) {
			assertFalse(aClearbit.findCompany("employee@notarealcompany.com").isPresent());
		}
	}

	@Test
	public void testMissingCompany() throws Exception {
		try (ClearBitAPI aClearbit = new ClearBitAPI(getKey())) {
			assertFalse(aClearbit.findCompany("notarealcompany.com").isPresent());
		}
	}
}
