package in.co.rays.ORSProj4.bean;

public class CourseBean extends BaseBean{

	
    /** Name of Course. */
	private String name;

    /** Description of Course. */
	private String description;

    /** Duration of Course. */
	
	private String duration;
	


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+ "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

	

}
