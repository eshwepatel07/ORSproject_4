package in.co.rays.ORSProj4.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.SubjectBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.CourseModel;
import in.co.rays.ORSProj4.model.SubjectModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.DataValidator;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;


@WebServlet(name = "SubjectCtl", urlPatterns = {"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl{

	private static	Logger log= Logger.getLogger(Subject.class);
	@Override
	protected void preload(HttpServletRequest request) {
	log.debug("method preload start");
	CourseModel cmodel=new CourseModel();
	try {
		List list= cmodel.list();
		request.setAttribute("clist", list);
		
	
	} catch (ApplicationException e) {
		e.printStackTrace();
	}

	log.debug("method preload end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass=true;
		log.debug("method validate start");
		if(DataValidator.isNull(request.getParameter("sname")))
		{
			request.setAttribute("sname",PropertyReader.getValue("error.require","Subject Name"));
			pass=false;
			
		}
		else if(!DataValidator.isName(request.getParameter("sname")))
		{
			request.setAttribute("sname",PropertyReader.getValue("error.name","subject name"));
			pass=false;
		}

		if(DataValidator.isNull(request.getParameter("description")))
		{
			request.setAttribute("description",PropertyReader.getValue("error.require","Description"));
			pass=false;
			
		}

		if(DataValidator.isNull(request.getParameter("cname")))
		{
			request.setAttribute("cname",PropertyReader.getValue("error.require","Course Name"));
			pass=false;
			
		}

		
		log.debug("method validate end");
		return pass;
	
	
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		log.debug("method populateBean start");
		
		SubjectBean bean=new SubjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("cname")));
		System.out.println("cname>>>>>>>"+request.getParameter("cname"));
		bean.setSubjectName(DataUtility.getString(request.getParameter("sname")));
		System.out.println("sname>>>>>>>>>"+request.getParameter("sname"));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
System.out.println("description>>>>>>>>>>"+request.getParameter("description"));
		populateDTO(bean, request);
		
		log.debug("method populateBean end");
		return bean;
	
	
	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("method doGet start");
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		SubjectModel model=new SubjectModel();
		SubjectBean bean;
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(id>0 || op!=null)
		{
			try {
				bean=model.findByPk(id);
				ServletUtility.setBean(bean, request);
			
			} catch (ApplicationException e) {
				
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		
		log.debug("method doGet end");
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method doPost start");
	
		
		String op=DataUtility.getString(request.getParameter("operation"));
		long id=DataUtility.getLong(request.getParameter("id"));
		
		SubjectModel model=new SubjectModel();
			if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
			{
				SubjectBean bean= (SubjectBean) populateBean(request);
				try {
					
				if(id>0) {
						model.update(bean);
						ServletUtility.setBean(bean, request);

						ServletUtility.setSuccessMessage("record updated", request);
					}
				else {
				
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Add Successfully", request);
				}
				
				}
				catch (ApplicationException e) {
						//e.printStackTrace();
						log.error(e);
						ServletUtility.handleException(e, request, response);
					
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						ServletUtility.setErrorMessage("Subject Name already exist", request);
						ServletUtility.setBean(bean, request);
						
						
					}
				}
			else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			}
			else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
				return;
			}
				ServletUtility.forward(getView(), request, response);
			
			
			
			
			log.debug("method doPost end");
			}
		
		
		
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_VIEW;
	}
	
	

}
