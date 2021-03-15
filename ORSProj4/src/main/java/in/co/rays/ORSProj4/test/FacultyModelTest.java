package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.management.modelmbean.ModelMBean;

import in.co.rays.ORSProj4.bean.FacultyBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.FacultyModel;

public class FacultyModelTest {

	public static FacultyModel model = new FacultyModel();
	public static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		
		testAdd();
	//	testDelete();
		//testUpdate();
		//testFindByEmail();
//		testFindByPk();
		//testSearch();
//		testList();	
	}
	
	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		FacultyBean bean= new FacultyBean();
		
		
		bean.setCollegeId(5L);
		bean.setCourseId(1L);
		bean.setSubjectId(11L);
		bean.setFirstName("Eshwer");
		bean.setLastName("Patel");
		bean.setGender("male");
		bean.setDob(sdf.parse("20/01/1997"));
		bean.setEmailId("esh5555@gmail.com");
		bean.setMobileNo("1213456789");
//		bean.setCourseName("CS");
//		bean.setCollegeName("IPS");
//		bean.setSubjectName("Computer science");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime() ));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime() ));
		
		long pk=model.add(bean);
		
	}
	
	//**TestDelete Model Started
	public static void testDelete() throws ApplicationException {
		
		FacultyBean bean=new FacultyBean();
		bean.setId(4L);
		model.delete(bean);
		
	}
	//**TestUpdate Started here!!!
		public static void testUpdate() throws ParseException, ApplicationException, DuplicateRecordException {
			
			FacultyBean bean=new FacultyBean();

			bean.setCollegeId(2L);
			bean.setCourseId(11L);
			bean.setSubjectId(9L);
			bean.setFirstName("Ravi");
			bean.setLastName("Verma");
			bean.setGender("male");
			bean.setDob(sdf.parse("12/12/1999"));
			bean.setEmailId("rv@gmail.com");
			bean.setMobileNo("4455661122");
			bean.setCourseName("EE");
			bean.setCollegeName("GSITS");
			bean.setSubjectName("DATA Structure");
			//bean.setCreatedBy("Admin");
			bean.setModifiedBy("Ravi");
			//bean.setCreatedDatetime(new Timestamp(new Date().getTime() ));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime() ));
			bean.setId(5L);
			model.update(bean);
			System.out.println("update succefully");
		}
	
				public static void testFindByEmail() {
					
					FacultyBean bean=new FacultyBean();
					try {
						bean=model.findByEmail("rv@gmail.com");
						if(bean==null) {
							System.out.println("Email does't Exist ");
						}
						else {
							System.out.println(bean.getId());
							System.out.println(bean.getCollegeId());
							System.out.println(bean.getSubjectId());
							System.out.println(bean.getCourseId());
							System.out.println(bean.getFirstName());
							System.out.println(bean.getLastName());
							System.out.println(bean.getGender());
							System.out.println(bean.getDob());
							System.out.println(bean.getEmailId());
							System.out.println(bean.getMobileNo());
							System.out.println(bean.getCourseName());
							System.out.println(bean.getCollegeName());
							System.out.println(bean.getSubjectName());
							System.out.println(bean.getCreatedBy());
							System.out.println(bean.getModifiedBy());
							System.out.println(bean.getCreatedDatetime());
							System.out.println(bean.getModifiedDatetime());
							
						}
					
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}

				
				public static void testFindByPk() {
					
					FacultyBean bean =new FacultyBean();
					try {
						bean=model.findByPk(4L);
					
						if(bean==null) {
							System.out.println("Id does't Exist ");
						}
						else {
							System.out.println(bean.getId());
							System.out.println(bean.getCollegeId());
							System.out.println(bean.getSubjectId());
							System.out.println(bean.getCourseId());
							System.out.println(bean.getFirstName());
							System.out.println(bean.getLastName());
							System.out.println(bean.getGender());
							System.out.println(bean.getDob());
							System.out.println(bean.getEmailId());
							System.out.println(bean.getMobileNo());
							System.out.println(bean.getCourseName());
							System.out.println(bean.getCollegeName());
							System.out.println(bean.getSubjectName());
							System.out.println(bean.getCreatedBy());
							System.out.println(bean.getModifiedBy());
							System.out.println(bean.getCreatedDatetime());
							System.out.println(bean.getModifiedDatetime());
						
						}
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				//** Terst Search Start here!!
				public static void testSearch() {
					
					FacultyBean bean=new FacultyBean();
					List list= new ArrayList();
					bean.setFirstName("Eshwerr");
					try {
						list=model.search(bean, 1, 10);
						if(list.size()<=0)
						{
							System.out.println("record not found");
						}
						else {
							Iterator it=list.iterator();
							while(it.hasNext())
							{
								bean=(FacultyBean) it.next();
								System.out.println(bean.getId());
								System.out.println(bean.getCollegeId());
								System.out.println(bean.getSubjectId());
								System.out.println(bean.getCourseId());
								System.out.println(bean.getFirstName());
								System.out.println(bean.getLastName());
								System.out.println(bean.getGender());
								System.out.println(bean.getDob());
								System.out.println(bean.getEmailId());
								System.out.println(bean.getMobileNo());
								System.out.println(bean.getCourseName());
								System.out.println(bean.getCollegeName());
								System.out.println(bean.getSubjectName());
								System.out.println(bean.getCreatedBy());
								System.out.println(bean.getModifiedBy());
								
								
							}
						}
					
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}

				
				public static void testList() {
					
					FacultyBean bean  =new FacultyBean();
					List list = new ArrayList();
					try {
						list=model.list(0, 10);
						if(list.size()<=0)
						{
							System.out.println("record not Exist");
							
						}
						else {
							Iterator it= list.iterator();
							
							while(it.hasNext()) {
								bean= (FacultyBean) it.next();
								System.out.println(bean.getId());
								System.out.println(bean.getCollegeId());
								System.out.println(bean.getSubjectId());
								System.out.println(bean.getCourseId());
								System.out.println(bean.getFirstName());
								System.out.println(bean.getLastName());
								System.out.println(bean.getGender());
								System.out.println(bean.getDob());
								System.out.println(bean.getEmailId());
								System.out.println(bean.getMobileNo());
								}
						}
					
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					
				}
}
