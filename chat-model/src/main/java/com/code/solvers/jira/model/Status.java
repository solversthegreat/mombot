
package com.code.solvers.jira.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Status implements Serializable
{

    private Integer code;
    private String errorType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4014860309951332525L;

    // for jira
    private String name;
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
