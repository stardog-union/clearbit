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

import java.util.List;

import com.google.common.base.Objects;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.1
 */
public class Company {
	private String mId;
	private String mName;
	private String mLegalName;
	private String mDomain;
	private String mURL;
	// private Site mSIte;
	private List<String> mCategories;
	private String mDescription;
	private List<String> mFounders;
	private int mRaised;
	private String mLocation;
	private int mEmployees;
	private String mLogo;
	private FacebookInfo mFacebook;
	private LinkedInInfo mLinkedin;
	private TwitterInfo mTwitter;
	private AngelListInfo mAngellist;

	private boolean mEmailProvider;
	private boolean mPersonal;

	private Alexa mAlexa;
	private GoogleRank mGoogle;

	/**
	 * Generate the score for a sales lead within the given company
	 *
	 * @param theCompany    the company
	 * @return              the sales score
	 *
	 * @see http://blog.clearbit.com/leadscoring
	 */
	public static double getScore(final Company theCompany) {
		final double twitter_followers_weight = 0.09;
		final double angellist_followers_weight = 0.05;
		final double klout_score_weight = 0.05;
		final double company_twitter_followers_weight = 0.05;
		final double company_alexa_rank_weight = 0.000005;
		final double company_google_rank_weight = 0.05;
		final double company_employees_weight = 0.5;
		final double company_raised_weight = 0.00005;
		final double company_score = 10;
		final double total_score = 1415;

		double score = company_score;

		if (theCompany.getRaised() > 0) {
			score += theCompany.getRaised() * company_raised_weight;
		}

		if (theCompany.getEmployees() > 0) {
			score += theCompany.getEmployees() * company_employees_weight;
		}

		if (theCompany.getAlexa() != null && theCompany.getAlexa().getGlobalRank() > 0) {
			score += 1 / (theCompany.getAlexa().getGlobalRank() * company_alexa_rank_weight);
		}

		if (theCompany.getGoogle() != null && theCompany.getGoogle().getRank() > 0) {
			score += 1 / (theCompany.getGoogle().getRank() * company_google_rank_weight);
		}

		if (theCompany.getTwitter() != null) {
			score += theCompany.getTwitter().getFollowers() * company_twitter_followers_weight;
		}

		score /= total_score;

		// base doesn't allow floats/doubles, so we have to scale this from 0..1 to 0..100.
		score *= 100;

		if (score == Double.POSITIVE_INFINITY) {
			score = 0;
		}

		return score;
	}

	public GoogleRank getGoogle() {
		return mGoogle;
	}

	public void setGoogle(final GoogleRank theGoogle) {
		mGoogle = theGoogle;
	}

	public Alexa getAlexa() {
		return mAlexa;
	}

	public void setAlexa(final Alexa theAlexa) {
		mAlexa = theAlexa;
	}

	public AngelListInfo getAngellist() {
		return mAngellist;
	}

	public void setAngellist(final AngelListInfo theAngellist) {
		mAngellist = theAngellist;
	}

	public List<String> getCategories() {
		return mCategories;
	}

	public void setCategories(final List<String> theCategories) {
		mCategories = theCategories;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(final String theDescription) {
		mDescription = theDescription;
	}

	public String getDomain() {
		return mDomain;
	}

	public void setDomain(final String theDomain) {
		mDomain = theDomain;
	}

	public boolean isEmailProvider() {
		return mEmailProvider;
	}

	public void setEmailProvider(final boolean theEmailProvider) {
		mEmailProvider = theEmailProvider;
	}

	public int getEmployees() {
		return mEmployees;
	}

	public void setEmployees(final int theEmployees) {
		mEmployees = theEmployees;
	}

	public FacebookInfo getFacebook() {
		return mFacebook;
	}

	public void setFacebook(final FacebookInfo theFacebook) {
		mFacebook = theFacebook;
	}

	public List<String> getFounders() {
		return mFounders;
	}

	public void setFounders(final List<String> theFounders) {
		mFounders = theFounders;
	}

	public String getId() {
		return mId;
	}

	public void setId(final String theId) {
		mId = theId;
	}

	public String getLegalName() {
		return mLegalName;
	}

	public void setLegalName(final String theLegalName) {
		mLegalName = theLegalName;
	}

	public LinkedInInfo getLinkedin() {
		return mLinkedin;
	}

	public void setLinkedin(final LinkedInInfo theLinkedin) {
		mLinkedin = theLinkedin;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(final String theLocation) {
		mLocation = theLocation;
	}

	public String getLogo() {
		return mLogo;
	}

	public void setLogo(final String theLogo) {
		mLogo = theLogo;
	}

	public String getName() {
		return mName;
	}

	public void setName(final String theName) {
		mName = theName;
	}

	public boolean isPersonal() {
		return mPersonal;
	}

	public void setPersonal(final boolean thePersonal) {
		mPersonal = thePersonal;
	}

	public int getRaised() {
		return mRaised;
	}

	public void setRaised(final int theRaised) {
		mRaised = theRaised;
	}

	public TwitterInfo getTwitter() {
		return mTwitter;
	}

	public void setTwitter(final TwitterInfo theTwitter) {
		mTwitter = theTwitter;
	}

	public String getURL() {
		return mURL;
	}

	public void setURL(final String theURL) {
		mURL = theURL;
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(mId, mName);
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		if (theObj == this) {
			return true;
		}
		else if (theObj instanceof Company) {
			Company aCompany = (Company) theObj;

			return Objects.equal(mId, aCompany.mId)
				&& Objects.equal(mName, aCompany.mName);
		}
		else {
			return false;
		}
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Company(%s)", mName);
	}
}
