
package com.code.solvers.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Preferences implements Serializable
{

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -754278887053250280L;

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
