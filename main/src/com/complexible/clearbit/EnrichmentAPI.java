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

import java.io.IOException;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.2
 * @version 0.2
 */
public interface EnrichmentAPI extends AutoCloseable {

	/**
	 * Look up a person by email.
	 *
	 * @param theEmail  the email to lookup
	 * @return          the person, if found, or an empty optional if the lookup was not successful or returned an
	 *                  async result
	 *
	 * @throws IOException  if there was an error looking up the person
	 */
	public PersonLookup person(final String theEmail) throws IOException;

	/**
	 * Look up a company by their domain.
	 *
	 * @param theEmail  the domain to lookup
	 * @return          the company, if found, or an empty optional if the lookup was not successful or returned an
	 *                  async result
	 *
	 * @throws IOException  if there was an error looking up the person
	 */
	public CompanyLookup company(final String theDomain) throws IOException;
}
