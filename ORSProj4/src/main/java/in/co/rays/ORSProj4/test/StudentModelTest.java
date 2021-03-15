package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.StudentBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.StudentModel;

public class StudentModelTest {

	public static StudentModel model=new StudentModel();
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
	
	//testAdd();
		//testDelete();
//		testFindByPK();
		//testFindByEmail();
		//testUpdate();
		testSearch();
		//testlist();
	//	testFindByMobile();
	}
	
	
	
	public static void testAdd() throws ParseException, DuplicateRecordException {

        try {
            StudentBean bean = new StudentBean();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // bean.setId(1L);
            bean.setCollegeId(4L);
         //   bean.setCollegeName("Rays Tech");
            bean.setFirstName("fhhhg");
            bean.setLastName("V");
            bean.setDob(sdf.parse("12/12/1997"));
            bean.setMobileNo("478786357");
            bean.setEmail("gggbb@gmail.com.com");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
             model.add(bean);
            System.out.println("Add Success");
//            StudentBean addedbean = model.findByPK(pk);
//            if (addedbean == null) {
//                System.out.println("Test add fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
	
	//** test delete start**//
	public static void testDelete() throws ApplicationException
	{
	
		StudentBean bean=new StudentBean();
		long pk=2L;
		bean.setId(pk);
		
		StudentBean deletebean= model.findByPK(pk);
		if(deletebean!=null)
		{
			model.delete(bean);
			System.out.println("Delete Success");
		}
		else {
			System.out.println("Pk not Found");
		}
	}

	
	//** test find by pk**//
	
	    public static void testFindByPK() {
	        try {
	            StudentBean bean = new StudentBean();
	            long pk = 2L;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getMobileNo());
	            System.out.println(bean.getEmail());
	            System.out.println(bean.getCollegeId());
	            System.out.println(bean.getModifiedDatetime());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    //**test find by email**//
	    
	    public static void testFindByEmail() 
	    {
	    	StudentBean bean = new StudentBean();
	    	String email="vijay@gmail.com.com";
	    	try {
				bean=model.findByEmailId(email);
				if(bean!=null) {
					System.out.println(bean.getId());
		            System.out.println(bean.getFirstName());
		            System.out.println(bean.getLastName());
		            System.out.println(bean.getDob());
		            System.out.println(bean.getMobileNo());
		            System.out.println(bean.getEmail());
		            System.out.println(bean.getCollegeId());
				}
				else {
					System.out.println("Email not found");
				}
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

	    
	    	//**test update start**//
	    	
	    /**
	     * Tests update a Student
	     * @throws DuplicateRecordException 
	     * @throws ParseException 
	     */
	    public static void testUpdate() throws DuplicateRecordException, ParseException {
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            StudentBean bean = model.findByPK(2L);
	            if(bean!=null)
	            {
	            bean.setCollegeId(3L);
	            bean.setCollegeName("sgsits");
	            bean.setFirstName("Eshwer");
	            bean.setLastName("Patel");
	            //bean.setDob(sdf.parse("12/12/1999"));
	            //bean.setMobileNo("9755584");
	            //bean.setEmail("pateleshwer@gmail.com");
	            bean.setModifiedBy("Eshwer");
	            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	            bean.setId(1L);
	            model.update(bean);
	            System.out.println("Update Success");
	            }
	            else {
	            	System.out.println("id not found");
	            }
//	            StudentBean updatedbean = model.findByPK(2L);
//	            if (!"rr".equals(updatedbean.getFirstName())) {
//	                System.out.println("Test Update fail");
//	            }
	        } catch (ApplicationException e) {
	        	e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
}
	    
	    
	   
	     //** Tests get Search*//
	    public static void testSearch() {

	        try {
	            StudentBean bean = new StudentBean();
	            List list = new ArrayList();
	        bean.setCollegeId(5);
	            //bean.setCollegeName("IPS");
	            //   bean.setFirstName("Eshwer");
	          //  bean.setLastName("patel");
	            list = model.search(bean, 0, 0);
	            if (list.size() <= 0) {
	                System.out.println("name not found");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (StudentBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getMobileNo());
	                System.out.println(bean.getEmail());
	                System.out.println(bean.getCollegeId());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
}
	    
	    //** Test Get List**//
	    
	    public static void testlist() {
	    	StudentBean bean=new StudentBean();
	    	List list=new ArrayList();
	    	try {
				list=model.list(1,10);
				if(list.size() < 0) {
					System.out.println("List not Found");
				}
				
				Iterator it=list.iterator();
				while(it.hasNext()){
					bean=(StudentBean) it.next();
					System.out.println(bean.getId());
					System.out.println(bean.getCollegeId());
					System.out.println(bean.getCollegeName());
					System.out.println(bean.getFirstName());
					System.out.println(bean.getLastName());
					System.out.println(bean.getDob());
					System.out.println(bean.getMobileNo());
					System.out.println(bean.getEmail());
					System.out.println(bean.getCreatedBy());
					System.out.println(bean.getModifiedBy());
					System.out.println(bean.getCreatedDatetime());
					System.out.println(bean.getModifiedDatetime());
					
				}
				
	    	} catch (ApplicationException e) {
				
				e.printStackTrace();
			}
	    	
	    }
	    
	    
//find by mobile
	    
	    public static void testFindByMobile() 
	    {
	    	StudentBean bean = new StudentBean();
	    	String mno="47878677357";
	    	try {
				bean=model.findBymobile(mno);
				if(bean!=null) {
					System.out.println(bean.getId());
		            System.out.println(bean.getFirstName());
		            System.out.println(bean.getLastName());
		            System.out.println(bean.getDob());
		            System.out.println(bean.getMobileNo());
		            System.out.println(bean.getEmail());
		            System.out.println(bean.getCollegeId());
				}
				else {
					System.out.println("Mobile No. not found");
				}
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    }    
	    
}