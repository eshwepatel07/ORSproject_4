package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.CourseBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.exception.RecordNotFoundException;
import in.co.rays.ORSProj4.model.CourseModel;

public class CourseModelTest {
	public static CourseModel model =new CourseModel();
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException, RecordNotFoundException {

		
		
		testAdd();
		//testDelete();
//		testUpdate();
		//testFindByName();	
//		testFindByPk();
//		testSearch();	
//		testList();
	}

	
	
		public static void testAdd() throws ApplicationException, DuplicateRecordException {
			
			CourseBean bean=new CourseBean();
//			String name="python";
//			bean=model.findByName(name);
//			if(bean!=null)
//			{
//				System.out.println("name already Exist");
//			}
			bean.setName("BE");
			bean.setDuration("4 year");
			bean.setDescription("Bechelor Of Eng");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.add(bean);
			
		}

		public static void testDelete() throws ApplicationException, RecordNotFoundException {
			CourseBean bean =new CourseBean();
			
			bean.setId(5);
			model.delete(bean);
			System.out.println("Delete Success");
			
		}
		
		public static void testUpdate() throws ApplicationException, DuplicateRecordException {
			
			CourseBean bean=new CourseBean();
					
			bean.setName("Java Corporate");
			bean.setDuration("7 month");
			bean.setDescription("core and Adv Java included");
			bean.setModifiedBy("Adm");
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(1);
			model.update(bean);
		}
		
			public static void testFindByName() throws ApplicationException {
				
				CourseBean bean=new CourseBean();
				//String name="IPS";
				//bean.setName("IPS");
				bean=model.findByName("python");
				if(bean==null)
				{
					System.out.println("name not found");
				}
				
				System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
				
			}
			
			
public static void testFindByPk() throws ApplicationException {
				
				CourseBean bean=new CourseBean();
				//String name="IPS";
				//bean.setName("IPS");
				bean=model.findByPk(1L);
				if(bean==null)
				{
					System.out.println("Id not found");
				}
				
				System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
				
			}


			public static void testSearch() throws ApplicationException {
				
				CourseBean bean=new CourseBean();
				List list = new ArrayList();
				
				bean.setName("python");
				
				list=model.search(bean, 1, 10);
				if(list.size()<=0)
				{
					System.out.println("record not found");
				}
				
				else {
					Iterator it= list.iterator();
					while(it.hasNext())
					{
						bean=(CourseBean) it.next();
						System.out.println(bean.getId());
						System.out.println(bean.getName());
						System.out.println(bean.getDuration());
						System.out.println(bean.getDescription());
						System.out.println(bean.getCreatedBy());
						System.out.println(bean.getModifiedBy());
						System.out.println(bean.getCreatedDatetime());
						System.out.println(bean.getModifiedDatetime());

						
					}
				}
			}

				public static void testList() {
					
					CourseBean bean=new CourseBean();
					List list=new ArrayList();
					
					try {
						list=model.list(1, 10);
						if(list.size()<=0)
						{
							System.out.println("record not found");
						}
						Iterator it=list.iterator();
						while(it.hasNext())
						{
							bean= (CourseBean) it.next();
							System.out.println(bean.getId());
							System.out.println(bean.getName());
							System.out.println(bean.getDuration());
							System.out.println(bean.getDescription());
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

}
