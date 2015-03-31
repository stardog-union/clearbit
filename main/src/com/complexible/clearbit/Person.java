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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   0.1
 * @version 0.1
 */
public final class Person {

	private String mId;

	private Name mName;

	private String mEmail;

	private String mGender;

	private String mLocation;

	private Geo mGeo;

	private String mBio;

	private String mSite;

	private String mAvatar;

	private EmploymentInfo mEmployment;

	private FacebookInfo mFacebook;

	private GitHubInfo mGithub;

	private TwitterInfo mTwitter;

	private LinkedInInfo mLinkedin;

	private GooglePlusInfo mGoogleplus;

	private FoursquareInfo mFoursquare;

	private AngelListInfo mAngellist;

	private Gravatar mGravatar;

	private AboutMe mAboutme;

	private boolean mFuzzy;

	private Company mCompany;

	public Company getCompany() {
		return mCompany;
	}

	public void setCompany(final Company theCompany) {
		mCompany = theCompany;
	}

	public AboutMe getAboutme() {
		return mAboutme;
	}

	public void setAboutme(final AboutMe theAboutme) {
		mAboutme = theAboutme;
	}

	public AngelListInfo getAngellist() {
		return mAngellist;
	}

	public void setAngellist(final AngelListInfo theAngellist) {
		mAngellist = theAngellist;
	}

	public String getAvatar() {
		return mAvatar;
	}

	public void setAvatar(final String theAvatar) {
		mAvatar = theAvatar;
	}

	public String getBio() {
		return mBio;
	}

	public void setBio(final String theBio) {
		mBio = theBio;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(final String theEmail) {
		mEmail = theEmail;
	}

	public EmploymentInfo getEmployment() {
		return mEmployment;
	}

	public void setEmployment(final EmploymentInfo theEmployment) {
		mEmployment = theEmployment;
	}

	public FacebookInfo getFacebook() {
		return mFacebook;
	}

	public void setFacebook(final FacebookInfo theFacebook) {
		mFacebook = theFacebook;
	}

	public FoursquareInfo getFoursquare() {
		return mFoursquare;
	}

	public void setFoursquare(final FoursquareInfo theFoursquare) {
		mFoursquare = theFoursquare;
	}

	public boolean isFuzzy() {
		return mFuzzy;
	}

	public void setFuzzy(final boolean theFuzzy) {
		mFuzzy = theFuzzy;
	}

	public String getGender() {
		return mGender;
	}

	public void setGender(final String theGender) {
		mGender = theGender;
	}

	public Geo getGeo() {
		return mGeo;
	}

	public void setGeo(final Geo theGeo) {
		mGeo = theGeo;
	}

	public GitHubInfo getGithub() {
		return mGithub;
	}

	public void setGithub(final GitHubInfo theGithub) {
		mGithub = theGithub;
	}

	public GooglePlusInfo getGoogleplus() {
		return mGoogleplus;
	}

	public void setGoogleplus(final GooglePlusInfo theGoogleplus) {
		mGoogleplus = theGoogleplus;
	}

	public Gravatar getGravatar() {
		return mGravatar;
	}

	public void setGravatar(final Gravatar theGravatar) {
		mGravatar = theGravatar;
	}

	public String getId() {
		return mId;
	}

	public void setId(final String theId) {
		mId = theId;
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

	public Name getName() {
		return mName;
	}

	public void setName(final Name theName) {
		mName = theName;
	}

	public String getSite() {
		return mSite;
	}

	public void setSite(final String theSite) {
		mSite = theSite;
	}

	public TwitterInfo getTwitter() {
		return mTwitter;
	}

	public void setTwitter(final TwitterInfo theTwitter) {
		mTwitter = theTwitter;
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper("Person")
			.add("name", mName)
			.add("email", mEmail)
			.toString();
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(mName, mEmail, mId);
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public boolean equals(final Object theObj) {
		if (theObj == this) {
			return true;
		}
		else if (theObj instanceof Person) {
			Person aPerson = (Person) theObj;

			return Objects.equal(mName, aPerson.mName)
			       && Objects.equal(mEmail, aPerson.mEmail)
			       && Objects.equal(mId, aPerson.mId);
		}
		else {
			return false;
		}
	}
}
