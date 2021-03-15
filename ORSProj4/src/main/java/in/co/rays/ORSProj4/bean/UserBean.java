package in.co.rays.ORSProj4.bean;


import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.ORSProj4.bean.BaseBean;

public class UserBean extends BaseBean{

	
	public static final String ACTIVE="Active";
	public static final String INACTIVE="Inactive";
	
	private String firstName;
	private String lastName;
	
	private String login;
	private String password;
	private String otp;
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}


	private String confirmPassword;
	
	private Date dob;
	

	private String mobileNo;
	
	/* Role of User
    */
   private long roleId;
   /**
    * Number of unsuccessful login attempt
    */
   private int unSuccessfulLogin;
   /**
    * Gender of User
    */
   private String gender;
   /**
    * Last login timestamp
    */
   private Timestamp lastLogin;
   /**
    * User Lock
    */
   private String lock = INACTIVE;
   /**
    * IP Address of User from where User was registred.
    */
   private String registeredIP;
   /**
    * IP Address of User of his last login
    */
   private String lastLoginIP;


	
	


   /**
    * accessor
    */

   public String getMobileNo() {
       return mobileNo;
   }

   public void setMobileNo(String mobileNo) {
       this.mobileNo = mobileNo;
   }

   public String getConfirmPassword() {
       return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
       this.confirmPassword = confirmPassword;
   }

   public String getLock() {
       return lock;
   }

   public void setLock(String lock) {
       this.lock = lock;
   }

   public String getFirstName() {
       return firstName;
   }

   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }

   public String getLastName() {
       return lastName;
   }

   public void setLastName(String lastName) {
       this.lastName = lastName;
   }

   public String getLogin() {
       return login;
   }

   public void setLogin(String login) {
       this.login = login;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public Date getDob() {
       return dob;
   }

   public void setDob(Date dob) {
       this.dob = dob;
   }

   public long getRoleId() {
       return roleId;
   }

   public void setRoleId(long roleId) {
       this.roleId = roleId;
   }

   public int getUnSuccessfulLogin() {
       return unSuccessfulLogin;
   }

   public void setUnSuccessfulLogin(int unSuccessfulLogin) {
       this.unSuccessfulLogin = unSuccessfulLogin;
   }

   public String getRegisteredIP() {
       return registeredIP;
   }

   public void setRegisteredIP(String registeredIP) {
       this.registeredIP = registeredIP;
   }

   public String getLastLoginIP() {
       return lastLoginIP;
   }

   public void setLastLoginIP(String lastLoginIP) {
       this.lastLoginIP = lastLoginIP;
   }

   public String getGender() {
       return gender;
   }

   public void setGender(String gender) {
       this.gender = gender;
   }

   public Timestamp getLastLogin() {
       return lastLogin;
   }

   public void setLastLogin(Timestamp lastLogin) {
       this.lastLogin = lastLogin;
   }

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return login;
	}


}
