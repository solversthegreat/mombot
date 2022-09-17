package com.code.solvers.model.rocket;

import java.io.Serializable;

public class GroupCreateResponse implements Serializable {

	private static final long serialVersionUID = -4139453271259069226L;
	
	private Group group;
	private boolean success;
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	} 
}

