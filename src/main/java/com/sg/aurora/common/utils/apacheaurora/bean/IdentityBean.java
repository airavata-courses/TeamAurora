package com.sg.aurora.common.utils.apacheaurora.bean;

public class IdentityBean {
	
	/** The user. */
	private String user;
	
	/**
	 * Instantiates a new identity bean.
	 *
	 * @param user the user
	 */
	public IdentityBean(String user) {
		this.user = user;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
