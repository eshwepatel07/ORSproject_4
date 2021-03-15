package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.MarksheetBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.exception.RecordNotFoundException;
import in.co.rays.ORSProj4.model.MarksheetModel;
import in.co.rays.ORSProj4.model.StudentModel;

public class MarksheetModelTest {

	public static MarksheetModel model=new MarksheetModel();
	public static void main(String[] args) throws ApplicationException, RecordNotFoundException {

		//testAdd();
		//testdelete();
		//testFindByRollNo();
		//testFindByPK();
		testUpdate();
	//	testSearch();
	//	testFindByStudentId();
		//testList();
		//testGetMeritList();
	}

	


	private static void testGetMeritList() {

		
		
		MarksheetBean bean=new MarksheetBean();
		List list=new ArrayList();
		
		try {
			list=model.getMeritList(1, 10);
		if(list.size() <= 0)
		{
			System.out.println("Merit list not found");
		}
		Iterator it=list.iterator();
		while(it.hasNext()){
		
		bean= (MarksheetBean) it.next();
		
		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		
		}
		
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

		
		
		
	




	//** Test Add MarkSheet**//
	
	public static void testAdd() {

        try {
        	//MarksheetModel model=new MarksheetModel();
            MarksheetBean bean = new MarksheetBean();
            // bean.setId(1L);
            bean.setRollNo("522");
            bean.setStudentId(5L);
            //bean.setName("ravi");
            bean.setPhysics(84);
            bean.setChemistry(77);
            bean.setMaths(97);
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
          ///model.add(bean);
            model.add(bean);
            
           
			/*
			 * MarksheetBean addedbean = model.findByPK(pk); if (addedbean == null) {
			 * System.out.println("Test add fail"); }
			 */
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

    }
	
	//** Test Delete **//
		public static void testdelete()
		{
			MarksheetBean bean =new MarksheetBean();
			String pk="50";
			bean.setRollNo(pk);
			try {
				
			MarksheetBean deletebean = model.findByRollNo(pk);
				if(deletebean ==null)
				{
					System.out.println("test Delete Fail");
					
				}
				else {
					model.delete(bean);
					System.out.println("Test Delete Success");
				}
			
			} catch (ApplicationException e) {
			
				e.printStackTrace();
			}
			
		}
	
	
		 public static void testFindByRollNo() {
		 
		 try {
			MarksheetBean bean=model.findByRollNo("55");
			if(bean==null)
			{
				System.out.println("Roll no not Found");
				
			}
			else
			{
				System.out.println(bean.getId());
				System.out.println(bean.getStudentId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
				
			}
		 } catch (ApplicationException e) {
			
			e.printStackTrace();
		}
		 
			 
		 }
		 
		 public static void testFindByPK()
		 {
			 try {
				MarksheetBean bean= model.findByPK(2l);
				if(bean==null)
				{
					System.out.println("PK not Found");
					
				}
				else {
					System.out.println(bean.getId());
					System.out.println(bean.getRollNo());
					System.out.println(bean.getName());
					System.out.println(bean.getPhysics());
					System.out.println(bean.getChemistry());
					System.out.println(bean.getMaths());
				}
			 
			 } catch (ApplicationException e) {
				
				e.printStackTrace();
			}
			 
		 }
	
		 
		 /**
		     * Tests update a Marksheet
		 * @throws RecordNotFoundException 
		     */
		    public static void testUpdate() throws RecordNotFoundException {

		        try {
		            MarksheetBean bean = model.findByRollNo("2021AJ03");
		            if(bean==null)
		            {
		            	throw new RecordNotFoundException("Roll no. not found");
		            }
		            
		            else {
		            bean.setName("VV");
		            bean.setChemistry(85);
		            bean.setPhysics(78);
		            bean.setMaths(85);
		            // bean.setStudentId(2);
		            bean.setModifiedBy("eshwer");
		            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		            model.update(bean);

		            //MarksheetBean updatedbean = model.findByPK(3L);
		            System.out.println("Test Update succ");
//		            if (!"IIM".equals(updatedbean.getName())) {
//		                System.out.println("Test Update fail");
//		            }
		            }
		        } catch (ApplicationException e) {
		            e.printStackTrace();
		        } catch (DuplicateRecordException e) {
		            e.printStackTrace();
		        }

		    }

	
		    public static void testSearch() //**by rollno/name/pk etc.**
		    {
		    	try {
		    	MarksheetBean bean=new MarksheetBean();
		    	
		    	List list=new ArrayList();
		    	
//		    	bean.setRollNo("52");
	bean.setId(2);	    	
					list=model.search(bean, 1, 10);
					if(list.size() <= 0)
					{
						System.out.println("record not found");
						
						
					}
					else {
					Iterator it=list.iterator();
					while(it.hasNext())
					{
						bean= (MarksheetBean) it.next();
						System.out.println(bean.getId());
						System.out.println(bean.getRollNo());
						System.out.println(bean.getStudentId());
						System.out.println(bean.getName());
						System.out.println(bean.getPhysics());
						System.out.println(bean.getChemistry());
						System.out.println(bean.getMaths());
						System.out.println(bean.getCreatedBy());
						System.out.println(bean.getModifiedBy());
					
					}
						
					}
		    	} catch (ApplicationException e) {
					
					e.printStackTrace();
				}
		    	
		    }
		    
		
		    public static void testFindByStudentId() {
				 
				 try {
					MarksheetBean bean=model.findBystudentId(10l);
					if(bean==null)
					{
						System.out.println("student id not Found");
						
					}
					else
					{
						System.out.println(bean.getId());
						System.out.println(bean.getStudentId());
						System.out.println(bean.getRollNo());
						System.out.println(bean.getName());
						System.out.println(bean.getPhysics());
						System.out.println(bean.getChemistry());
						System.out.println(bean.getMaths());
						System.out.println(bean.getCreatedBy());
						System.out.println(bean.getModifiedBy());
						System.out.println(bean.getCreatedDatetime());
						System.out.println(bean.getModifiedDatetime());
						
					}
				 } catch (ApplicationException e) {
					
					e.printStackTrace();
				}
				 
					 
				 }
				
		    
			private static void testList() {
				MarksheetBean bean=new MarksheetBean();
				List list=new ArrayList();
				
				try {
					list=model.list(1, 10);
				if(list.size() <= 0)
				{
					System.out.println("list not found");
				}
				Iterator it=list.iterator();
				while(it.hasNext()){
				
				bean= (MarksheetBean) it.next();
				
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				}
				
				
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}

		 
}
