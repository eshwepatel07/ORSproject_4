package in.co.rays.ORSProj4.test;

import in.co.rays.ORSProj4.bean.RoleBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.exception.RecordNotFoundException;
import in.co.rays.ORSProj4.model.RoleModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoleModelTest {

	/**
	 * Model object to test
	 */

	public static RoleModel model = new RoleModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 */
	public static void main(String[] args) throws ParseException, RecordNotFoundException, ApplicationException, DuplicateRecordException {
		//testAdd();
		// testDelete();
//		 testUpdate();
//		testFindByPK();
		// testFindByName();
		// testSearch();
//		 testList();

	}

	/**
	 * Tests add a Role
	 *
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 */
	public static void testAdd() throws ParseException, RecordNotFoundException {

		 try {
	            RoleBean bean = new RoleBean();
	            // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	             bean.setId(4L);
	            bean.setName("Kiosk");
	            bean.setDescription("Banking");
	            bean.setCreatedBy("admin");
	            bean.setModifiedBy("admin");
	       	  bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	       	  bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			/* long pk = */ model.add(bean);
			System.out.println("success");
			/*
			 * RoleBean addedbean = model.findByPK(pk); if (addedbean == null) {
			 * System.out.println("Test add fail"); }
			 */
			 
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }

	/*
	 * try { RoleBean bean = new RoleBean(); // SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd-MM-yyyy");
	 * 
	 * // bean.setId(2L); bean.setName("student"); bean.setDescription("adm");
	 * bean.setCreatedBy("admin"); bean.setModifiedBy("admin");
	 * bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	 * bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	 * 
	 * long pk = model.add(bean);
	 * 
	 * 
	 * if (pk == 0) { System.out.println("add not  succss"); }
	 * 
	 * 
	 * 
	 * RoleBean addedbean = model.findByPK(pk); if (addedbean == null) {
	 * System.out.println("Test add fail"); }
	 * 
	 * } catch (ApplicationException e) { e.printStackTrace(); } catch
	 * (DuplicateRecordException e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	*//**
		 * Tests delete a Role
		 */

	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 1L;
			bean.setId(pk);
			System.out.println("hhhh");
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 * @throws DuplicateRecordException 
	 * @throws ApplicationException 
	 * @throws RecordNotFoundException 
	 */
	
	  public static void testUpdate() throws ApplicationException, DuplicateRecordException  {
	  
	  try { RoleBean bean = model.findByPK(1L);
	  bean.setName("Admin");
	  bean.setDescription("Admin"); 
	 /* if(bean==null)
	  {
		  System.out.println("record not found");
	  }
	  else
	  {*/
		  model.update(bean);
		  System.out.println("record Updated");
	//  }
	  
	  //RoleBean updatedbean = model.findByPK(6L); if
	 // (!"12".equals(updatedbean.getName())) {
	  //System.out.println("Test Update fail"); } } catch (ApplicationException e) {
	 // e.printStackTrace(); }
	 
	  }catch (RecordNotFoundException e) {
	  
		  e.printStackTrace(); }
	  
	  }
	 /**
		 * Tests find a User by PK.
		 */

	public static void testFindByPK() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Name.
	 * 
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 */

	public static void testFindByName() throws RecordNotFoundException, ApplicationException {
		try {
			RoleBean bean = new RoleBean();
			bean = model.findByName("admin");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("name not found" + e.getMessage());
//	  e.printStackTrace();

		}
	}

	/**
	 * Tests get Search
	 */

	public static void testSearch() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("admin");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	// Tests get List.

	public static void testList() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
