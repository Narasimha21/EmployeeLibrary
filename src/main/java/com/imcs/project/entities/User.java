package com.imcs.project.entities;

public class User {

	private String username;
	private String password;

	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instanceof [type]"
		 * also returns false
		 */
		if (!(o instanceof User)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		User c = (User) o;

		// Compare the data members and return accordingly
		return username.compareTo(c.username) == 0 && password.compareTo(c.password) == 0;
	}

	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
