/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.linkedin.connect;

import org.springframework.social.connect.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.connect.support.ConnectionRepository;
import org.springframework.social.linkedin.LinkedInApi;
import org.springframework.social.linkedin.LinkedInTemplate;
import org.springframework.social.oauth1.OAuth1Template;

/**
 * LinkedIn ServiceProvider implementation.
 * @author Keith Donald
 */
public final class LinkedInServiceProvider extends AbstractOAuth1ServiceProvider<LinkedInApi> {

	public LinkedInServiceProvider(String consumerKey, String consumerSecret, ConnectionRepository connectionRepository) {
		super("linkedin", connectionRepository, consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret, "https://api.linkedin.com/uas/oauth/requestToken",
				"https://www.linkedin.com/uas/oauth/authorize?oauth_token={requestToken}", "https://api.linkedin.com/uas/oauth/accessToken"));
	}

	@Override
	protected LinkedInApi getApi(String consumerKey, String consumerSecret, String accessToken, String secret) {
		return new LinkedInTemplate(consumerKey, consumerSecret, accessToken, secret);
	}
	
	@Override
	protected String getProviderAccountId(LinkedInApi api) {
		return api.getProfileId();
	}
}