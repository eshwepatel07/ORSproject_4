package in.co.rays.ORSProj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProj4.bean.FacultyBean;
import in.co.rays.ORSProj4.bean.SubjectBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.SubjectModel;

public class SubjectModelTest {
		public static SubjectModel model=new SubjectModel();
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
//		testAdd();
//		testDelete();	
//		testFindByName();
//		testFindByPk();		
		testSearch();
//		testList();
	}
	
	
	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		
		SubjectBean bean=new SubjectBean();
		
		bean.setSubjectName("coporate Cloud");
		bean.setCourseId(3);
	//	bean.setCourseName("Production");
		bean.setDescription("diploma in cloud");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		long i=model.add(bean);
		if(i>0) {
		System.out.println("Subject Add Success");
		}
	}
	
	
	public static void testDelete() throws ApplicationException {
		
		SubjectBean bean=new SubjectBean();
		bean.setId(1);
		model.delete(bean);
		
		
	}
	
	
	public static void testFindByName() throws ApplicationException {
		SubjectBean bean=new SubjectBean();
		//bean.setSubjectName("BE");
			bean=model.findByName("BE");
			if(bean!=null)
			{
				System.out.println(bean.getId());
				System.out.println(bean.getSubjectName());
				
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				
			}
			else {
				System.out.println("Subject name not found");
			}
	}

	

	public static void testFindByPk() throws ApplicationException {
		SubjectBean bean=new SubjectBean();
		//bean.setSubjectName("BE");
			bean=model.findByPk(2L);
			if(bean!=null)
			{
				System.out.println(bean.getId());
				System.out.println(bean.getSubjectName());
				
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				
			}
			else {
				System.out.println("Subject name not found");
			}
	}

	public static void testSearch()  {
		SubjectBean bean=new SubjectBean();
		
			List list=new ArrayList();
		
//			bean.setCourseName("BE");
	bean.setSubjectName("z");		
			try {
				list=model.search(bean,1, 10);
				if(list.size()<=0)
				{
					System.out.println("record not found");
				}
				else
				{
				Iterator it=list.iterator();
				while(it.hasNext())
				{
					bean= (SubjectBean) it.next();
					System.out.println(bean.getId());
					System.out.println(bean.getCourseId());
					System.out.println(bean.getCourseName());
					System.out.println(bean.getSubjectName());
					System.out.println(bean.getDescription());
					System.out.println(bean.getModifiedBy());
					System.out.println(bean.getCreatedBy());
				}
				}
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
}
	
	public static void testList() {
		
		SubjectBean bean=new SubjectBean();
		List list=new ArrayList();
		
		
		try {
			list=model.list(1,10);
			if(list.size()<=0)
			{
				System.out.println("record not exist");
			}
			else
			{
				int R=1;
				Iterator it=list.iterator();
			while(it.hasNext())
			{
				
				System.out.println("R:= "+R);
				bean=(SubjectBean) it.next();
        
				System.out.println(bean.getId());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getSubjectName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
				R++;
			}
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}
	
	
	
	
}