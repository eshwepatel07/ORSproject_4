package in.co.rays.ORSProj4.bean;

import java.util.Date;

public class FacultyBean extends BaseBean{

    /** First Name of Faculty. */
	private String firstName;

    /** Last Name of Faculty. */
	private String lastName;

    /** Gender of Faculty. */
	private String Gender;

    /** Login ID of Faculty. */
	private String emailId;
	
	

    /** Mobile No of Faculty. */
	private String mobileNo;

    /** CollegeID of Faculty. */
	private long collegeId;

    /** College Name of Faculty. */
	private String collegeName;

    /** CourseID of Faculty. */
	private long courseId;

    /** Course Name of Faculty. */
	private String courseName;

	/** dob of Faculty. */
	private Date dob;
	
  

	/** SubjectID of Faculty. */
	private long subjectId;

    /** Subject Name of Faculty. */
	private String subjectName;



	
	
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

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}


}
