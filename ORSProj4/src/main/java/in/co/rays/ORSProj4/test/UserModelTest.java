package in.co.rays.ORSProj4.test;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.UserBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.exception.RecordNotFoundException;
import in.co.rays.ORSProj4.model.UserModel;

import java.sql.Timestamp;
public class UserModelTest {

	
	/**
     * Model object to test
     */

    public static UserModel model = new UserModel();

    /**
     * Main method to call test methods.
     *
     * @param args
     * @throws ParseException
     * @throws DuplicateRecordException
     * @throws RecordNotFoundException 
     */
	
	
	public static void main(String[] args) throws ParseException, DuplicateRecordException, RecordNotFoundException {
	
		
	//testAdd();	
	//testDelete();
	//testFindByPK();
//	testFindByLogin();
//	testGetRoles();
//	testUpdate();
//		testAuthenticate();
//		testSearch();
		testList();
	//	testchangePassword();
//		testRegisterUser();
			//testforgetPassword();
//		testresetPassword();
	}
	
	
	/**
     * Tests add a User
     *
     * @throws ParseException
     * @throws DuplicateRecordException
     */
    public static void testAdd() throws ParseException,
            DuplicateRecordException {

        try {
            UserBean bean = new UserBean();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

            // bean.setId(5234L);
            bean.setFirstName("murli");
            bean.setLastName("patel");
            bean.setLogin("pmmm@g.com");
            bean.setPassword("pass1234");
            bean.setDob(sdf.parse("31-12-1990"));
            bean.setMobileNo("97445");
            bean.setRoleId(2L);
//            bean.setUnSuccessfulLogin(2);
            bean.setGender("Male");
        //    bean.setLastLogin(new Timestamp(new Date().getTime()));
  //          bean.setLock("Yes");
    //        bean.getRegisteredIP();
      //      bean.getLastLoginIP();
            bean.setCreatedBy("admin");
            bean.setModifiedBy("admin");
       	  bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
       	  bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
   //         bean.setConfirmPassword("pass1234");
            long pk = model.add(bean);
            //UserBean addedbean = model.findByPK(pk);
           // System.out.println("Test add succ");
            //if (addedbean == null) {
              //  System.out.println("Test add fail");
           // }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    public static void testDelete() throws RecordNotFoundException {

        try {
            UserBean bean = new UserBean();
            long pk = 2;
            bean.setId(pk);
            
            UserBean deletedbean = model.findByPK(pk); 
			  if (deletedbean == null) {
				  throw new RecordNotFoundException("ID not Found");
			  }
			  else {
            
            model.delete(bean);
            System.out.println("Test Delete succ" + bean.getId());
			
			  }
			  
			 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    
    
    
    /**
     * Tests find a User by PK.
     * @throws RecordNotFoundException 
     */
    public static void testFindByPK() throws RecordNotFoundException {
        try {
            UserBean bean =null;
            bean = new UserBean();
            long pk = 1L;
            
            bean = model.findByPK(pk);
            
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            
            
            System.out.println(bean.getId());
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLastName());
            System.out.println(bean.getLogin());
            System.out.println(bean.getPassword());
            System.out.println(bean.getDob());
            System.out.println(bean.getRoleId());
            System.out.println(bean.getGender());
            System.out.println(bean.getCreatedBy());
            
                      System.out.println(bean.getModifiedBy());
                      System.out.println(bean.getModifiedDatetime());
//            System.out.println(bean.getLastLogin());
  //          System.out.println(bean.getLock());
        } catch (Exception e) {
           // e.printStackTrace();
        	throw new RecordNotFoundException("ID not Found");
        
        }

    }

    	
    public static void testFindByLogin() throws RecordNotFoundException {
        
            UserBean bean = new UserBean();
            String login = "pateleshwer1432esh@gmail.com";
            try {    
            bean = model.findByLogin(login);
            
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            
            
            System.out.println(bean.getId());
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLastName());
            System.out.println(bean.getLogin());
            System.out.println(bean.getPassword());
            System.out.println(bean.getDob());
            System.out.println(bean.getRoleId());
            System.out.println(bean.getGender());
            System.out.println(bean.getCreatedBy());
            
    //                  System.out.println(bean.getModifiedBy());
//            System.out.println(bean.getLastLogin());
  //          System.out.println(bean.getLock());
        } catch (Exception e) {
           // e.printStackTrace();
        	throw new RecordNotFoundException("Login ID not Found:- " +login);
        
        }

    }

    
    
    /**
     * Tests get Roles.
     */
    public static void testGetRoles() {

        try {
            UserBean bean = new UserBean();
            List list = new ArrayList();
            bean.setRoleId(3L);
            list = model.getRoles(bean);
            if (list.size() == 0) {
                System.out.println("Role ID not Found");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getLogin());
                System.out.println(bean.getPassword());
                System.out.println(bean.getDob());
                System.out.println(bean.getRoleId());
                //System.out.println(bean.getUnSuccessfulLogin());
                System.out.println(bean.getGender());
                System.out.println(bean.getCreatedBy());
                //System.out.println(bean.getLastLogin());
                //System.out.println(bean.getLock());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void testUpdate() throws ParseException {
    	 SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");
    	
		
        
    	 try {
        
    		 UserBean bean = model.findByPK(2L);
    		 if(bean==null)
    		 {
    			 System.out.println("id does not exist");
    		 }
    		// String login1= bean.getLogin();
    		 //System.out.println(" "+login1);
            bean.setFirstName("AK");
            bean.setLastName("Choudhary");
            bean.setLogin("patelbhupendra881999@gmail.com");
            bean.setPassword("658986");
            bean.setDob(sdf.parse("31-12-1990"));
            bean.setMobileNo("9755584");
            bean.setRoleId(2L);
            bean.setGender("male");
            bean.setModifiedBy("AK");
         	
         	  bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
     
         	  //UserBean updatedbean = model.findByPK(5L);
//            if (login1.equals(bean.getLogin())) {
//            	//String lg=bean.getLogin();
//            	throw new DuplicateRecordException("Gmail already exist please update new....");
//                //System.out.println("Test Update fail");
//            }
            
             //   else {
         	 model.update(bean);
                	System.out.println("Test Update Successfull ");
               // }
            
        } catch (Exception e) {
            e.printStackTrace();
		} /*
			 * catch (DuplicateRecordException e) { e.printStackTrace(); }
			 */
    }

    
    //Tst Authenticate
    
    /**
     * Tests authenticate User.
     */
    public static void testAuthenticate() {

        try {
            UserBean bean = new UserBean();
            bean.setLogin("pateleshwer1432esh@gmail.com");
            bean.setPassword("Pass1234@");
			
            bean = model.authenticate(bean.getLogin(), bean.getPassword());
            if (bean != null) {
            	System.out.println("Successfully login");
            	System.out.println(bean.getRoleId() + "role id from usermodel authenticate");
            	System.out.println("Welcome:- "+bean.getFirstName()+" "+bean.getLastName());
            //	System.out.println("Welcome:- "+bean.getLogin()+" "+bean.getPassword());
            } else {
                System.out.println("Invalid");
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * Tests get Search
     */
    public static void testSearch() {

        try {
            UserBean bean = new UserBean();
            List list = new ArrayList();
           // bean.setFirstName("j");
            bean.setMobileNo("97555");
            list = model.search(bean, 0, 0);
                    if (list.size() <= 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getLogin());
                System.out.println(bean.getPassword());
                System.out.println(bean.getDob());
                System.out.println(bean.getRoleId());
            //    System.out.println(bean.getUnSuccessfulLogin());
                System.out.println(bean.getGender());
              //  System.out.println(bean.getLastLogin());
                //System.out.println(bean.getLock());
            }
            
        } 
                   catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    
    /**
     * Tests get List.
     */
    public static void testList() {

        try {
            UserBean bean = new UserBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getLogin());
                System.out.println(bean.getPassword());
                System.out.println(bean.getDob());
                System.out.println(bean.getRoleId());
              //  System.out.println(bean.getUnSuccessfulLogin());
                System.out.println(bean.getGender());
//                System.out.println(bean.getLastLogin());
//                System.out.println(bean.getLock());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedDatetime());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    
    	
    /**
     * Tests changepassword
     *
     * @throws ParseException
     */
    public static void testchangePassword() {

        try {
            UserBean bean = model.findByLogin("pateleshwer1432esh@gmail.com");
          
            if(bean==null)
            {
            	throw new RecordNotFoundException("Invalid Email Id");
            	//System.out.println("Invalid Email Id");
            }
            else
            {
            //String oldPassword = bean.getPassword();
//            bean.setId(2L);
           String oldpass="123456";
            bean.setPassword("@123456@");
  //          bean.setConfirmPassword("88");
            String newPassword = bean.getPassword();
           //long id=bean.getId();
            
            	boolean result=model.changePassword(bean.getLogin(), oldpass, newPassword);
                if(result== true)
              
                {
                	System.out.println("password has been change successfully");	
                }
        }
        }             
        catch (Exception e) {
                e.printStackTrace();
            }
        
        //    }
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }

    }
    
    
    
    /**
     * Tests add a User register
     *
     * @throws ParseException
     */

    public static void testRegisterUser() throws ParseException {
        try {
            UserBean bean = new UserBean();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            // bean.setId(8L);
            bean.setFirstName("VVVP");
             bean.setLastName("kumawat");
            bean.setLogin("eshwer142@gmail.com");
            bean.setPassword("rr");
            //bean.setConfirmPassword("4444");
            bean.setDob(sdf.parse("11/20/2015"));
            bean.setMobileNo("7896541236");
            bean.setRoleId(2);
            bean.setGender("Male");
            
            bean.setCreatedBy("admin");
            bean.setModifiedBy("admin");
       	  bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
       	  bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.registerUser(bean);
            System.out.println("Successfully register");
//            System.out.println(bean.getFirstName());
//            System.out.println(bean.getLogin());
//            System.out.println(bean.getLastName());
//            System.out.println(bean.getDob());
//            UserBean registerbean = model.findByPK(pk);
//            if (registerbean != null) {
//                System.out.println("Test registation fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
    
    	//**test Forget Password**//
    	public static void testforgetPassword() {
    		try {
				boolean b= model.forgetPassword("pateleshwer1432esh@gmail.com");
				
				System.out.println("Password Reset Success: Password send to your gmail");
    		} catch (ApplicationException e) {

				e.printStackTrace();
			} catch (RecordNotFoundException e) {

				e.printStackTrace();
			}
    	}
    
    
    	//**test Reset Password**//
    	
    		public static void testresetPassword() {
    			
    			UserBean bean = new UserBean();
    			
    						try {
								bean=  model.findByLogin("pateleshwer1432esh@gmail.com");
								if(bean!=null)
								{
									//otp process continue here!!!!!!
									boolean pass = false;// = model.resetPassword(bean);
									if(pass==true)
									{
										System.out.println("Password Reset succes");
									}
								}
    						
    						} catch (Exception e) {
							
								e.printStackTrace();
							}
    			
    			
    			
    		}
    
    
    
}

