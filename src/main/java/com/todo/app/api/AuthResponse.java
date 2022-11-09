package com.todo.app.api;

public class AuthResponse {
    private String email;
    private String accessToken;
 
    public AuthResponse() { }
     
    public AuthResponse(String email, String accessToken) {
        this.setEmail(email);
        this.setAccessToken(accessToken);
    }

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
 
    
}