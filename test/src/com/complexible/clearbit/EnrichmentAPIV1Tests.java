package com.complexible.clearbit;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.2
 * @version 0.2
 */
public final class EnrichmentAPIV1Tests extends EnrichmentAPITests {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnrichmentAPI createAPI() {
		return new EnrichmentAPIV1(getKey());
	}
}
