package tek.framework.api.modal;

import java.util.Date;

public class TokenResponse {
	
	private String fullName; 
	private String username; 
	private String token;
	private Date generateDate; 
	private String accountType;
	
	public TokenResponse() {}
	public TokenResponse(String fullName, String username, String token, Date generateDate, String accountType) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.token = token;
		this.generateDate = generateDate;
		this.accountType = accountType;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getGenerateDate() {
		return generateDate;
	}
	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	} 
}
