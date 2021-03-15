package in.co.rays.ORSProj4.test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.CollegeBean;
import in.co.rays.ORSProj4.bean.UserBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.CollegeModel;

public class CollegeModelTest {

	public static CollegeModel model = new CollegeModel();
	
	
	public static void main(String[] args) {
		
		//testadd();
				//testDelete();
//			testFindByName();
		//testFindByPK();
		//testUpdate();
		testSearch();
//		testList();
	}
	
	
	


	public static void testadd() throws DuplicateRecordException
	{
		
		CollegeBean bean=new CollegeBean();
//		bean.setId(1l);
		bean.setName("Rays Tech2");
		bean.setAddress("Madhu Milan Square");
		bean.setCity("indore");
		bean.setState("MP");
		bean.setPhoneNo("654321");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			try {
				model.add(bean);
				System.out.println("College Succesfully Added");
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
	//delete by id
	
	/**
     * Tests delete a College
     */
    public static void testDelete() {

        try {
            CollegeBean bean = new CollegeBean();
            long pk = 4L;
            bean.setId(pk);
            CollegeBean deletedBean = model.findByPK(pk);
            if (deletedBean == null) {
                System.out.println("Test Delete fail");
            }
            else {
            model.delete(bean);
            System.out.println("Test Delete succ");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
	
	
    /**
     * Tests find a College by PK.
     */
    public static void testFindByPK() {
        try {
            CollegeBean bean = new CollegeBean();
            long pk = 1L;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getAddress());
            System.out.println(bean.getState());
            System.out.println(bean.getCity());
            System.out.println(bean.getPhoneNo());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
	//testfind by name
	
		public static void testFindByName() {
			
			try {
				CollegeBean bean= model.findbyname("Rays tech2"); 
				//List list = new ArrayList();
			/* list= */
				
				//System.out.println(list);

			//	Iterator it = list.iterator();
				if(bean==null)
				{
					System.out.println("College does not Exist");
				}
	            //while (it.hasNext()) {
	            	//bean = (CollegeBean) it.next();
	            	System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
	            
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	
		/**
	     * Tests update a College
	     */
	    public static void testUpdate() {

	    	 try {
	            CollegeBean bean = model.findByPK(5L);
	            if(bean==null)
	            {
	            	System.out.println("id does not exist");
	            }
	           
	            bean.setName("SDBCT");
	            bean.setAddress("Rau");
	            bean.setState("MP");
	            bean.setCity("Rau");
	            bean.setPhoneNo("789456");
	            bean.setModifiedBy("admin");
	            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	            //bean.setId(3l);
	            model.update(bean);
	            
	            System.out.println("Test Update succ");
//	            CollegeBean updateBean = model.findByPK(3L);
//	            if (!"oit".equals(updateBean.getName())) {
//	                System.out.println("Test Update fail");
//	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }

		
	    
	    /**
	     * Tests search a College by Name
	     */

	    public static void testSearch() {
	        try {
	            CollegeBean bean = new CollegeBean();
	            List list = new ArrayList();
	            bean.setName("LNCT");
	            // bean.setAddress("borawan");
	            list = model.search(bean, 1, 10);
	            if (list.size() <= 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getAddress());
	                System.out.println(bean.getState());
	                System.out.println(bean.getCity());
	                System.out.println(bean.getPhoneNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDatetime());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
		

	    
	    /**
	     * Tests get List a College.
	     */
	    public static void testList() {

	        try {
	            CollegeBean bean = new CollegeBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getAddress());
	                System.out.println(bean.getState());
	                System.out.println(bean.getCity());
	                System.out.println(bean.getPhoneNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    
	    
	    
		    }

			
			
		


		
		
		
		

