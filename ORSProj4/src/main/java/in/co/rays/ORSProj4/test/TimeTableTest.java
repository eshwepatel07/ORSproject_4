package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.TimeTableBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.TimeTableModel;

public class TimeTableTest {

	public static TimeTableModel model=new TimeTableModel();
	public static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
	
		
		//testAdd();
	      //  testDelete();	 
		// testFindByPK();
		// testFindByEmailId();
	 testSearch();
//		 testList();
	//testUpdate();
		
		
	}

	
	public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
		
		TimeTableBean bean=new TimeTableBean();
		bean.setCourseId(1L);
		//bean.setCourseName("BTECH");
		bean.setSubjectId(11L);
		//bean.setSubjectName("OS");
		bean.setSemester("5th");
		bean.setExamDate(sdf.parse("30/12/2020"));
		bean.setExamTime("10 AM to 1 PM");
		bean.setDescription("Hibernate timetable");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		System.out.println(bean.getExamTime());
		model.add(bean);
		
	}
	
	public static void testFindByPK() {
		try {
		    TimeTableBean bean = new TimeTableBean();
			long pk = 11;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getSemester());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getExamDate());
			
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test search.
	 * @throws ParseException 
	 */
	public static void testSearch() throws ParseException {
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
		try {
			
			TimeTableBean bean = new TimeTableBean();
			List list = new ArrayList();
			bean.setExamDate(sdf.parse("2020-12-30"));
			//bean.setSubjectName("OS");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (TimeTableBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSemester());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getExamDate());
				}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Test list.
	 */
	public static void testList() {

		try {
			TimeTableBean bean = new TimeTableBean();
			List list = new ArrayList();
			list = model.list(1, 12);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (TimeTableBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getExamTime());
				System.out.println(bean.getSemester());
				System.out.println(bean.getSubjectId());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getExamDate());
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
	 * Test update.
	 */
	public static void testUpdate() {

		try {
			TimeTableBean bean = model.findByPk(20L);
			bean.setSubjectId(3L);
			//bean.setSubjectName("Material Technology");
			model.update(bean);

			TimeTableBean updatedbean = model.findByPk(20L);
			/*if (!"Material Technology".equals(updatedbean.getSubjectName())) {
				System.out.println("Test Update fail");
			}else{
				System.out.println("Test Update Successfully");
			}*/
			System.out.println("-------------------------------");
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
          
	/**
	 * Test delete.
	 */
	public static void testDelete() {

		try {
			TimeTableBean bean = new TimeTableBean();
			long pk = 20L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete successfully");

			TimeTableBean deletedBean = model.findByPk(pk);
			if (deletedBean != null) {

				System.out.println("Test Delete fail");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}





}
