/*
 * Copyright 2012-2019 the original author or authors.
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
package org.springframework.security.oauth.samples.web;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joe Grandja
 */
@RestController
public class MessagesController {
	@GetMapping("/messages")
	public String[] getMessages() {
		String[] messages = new String[] {"Message 1", "Message 2", "Message 3"};
		return messages;
	}
	
	
	
	@GetMapping("/messages/infoToken")
	public String resource(@AuthenticationPrincipal Jwt jwt) {
		String test = jwt.getClaims();
	    return String.format("Token info:\n user_name: "); //%s \n client_id: %s\n expiration: %s\n scopes: %s\n (subjectId: %s)" ,
//	            jwt.getClaims().get("user_name"),
//	            jwt.getClaims().get("client_id"),
//	            jwt.getClaims().get("exp"),
//	            jwt.getClaims().get("scope"),
//	            jwt.getSubject());
	    }

}