package com.complexible.clearbit;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.2
 * @version 0.2
 */
public final class EnrichmentAPIV2Tests extends EnrichmentAPITests {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnrichmentAPI createAPI() {
		return new EnrichmentAPIV2(getKey());
	}

	@Test
	@Override
	public void testMissingCompany() throws Exception {
		try (EnrichmentAPI aClearbit = createAPI()) {
			// a company is always returned afaict in v2
			final Optional<Company> aCompany = aClearbit.company("notarealcompany.com").lookup();

			assertTrue(aCompany.isPresent());
			assertTrue(aCompany.get().getName() == null);
		}
	}
}
