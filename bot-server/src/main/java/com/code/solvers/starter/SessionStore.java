package com.code.solvers.starter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SessionStore {

	public Map<String, UserSession> SESSION = new HashMap<String, UserSession>();
	
}
