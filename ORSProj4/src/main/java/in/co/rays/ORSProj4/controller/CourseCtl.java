package in.co.rays.ORSProj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.KeySelector.Purpose;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.CourseBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.CourseModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.DataValidator;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;

@WebServlet(name="CourseCtl", urlPatterns = {"/ctl/CourseCtl"})
public class CourseCtl extends BaseCtl{

	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("method validate start");
		boolean pass=true;
		if(DataValidator.isNull(request.getParameter("cname")))
		{
			request.setAttribute("cname", PropertyReader.getValue("error.require","CourseName"));
			 pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("cname")))
		{
			request.setAttribute("cname", PropertyReader.getValue("error.name","Course Name"));
			 pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("duration")))
		{
			request.setAttribute("duration", PropertyReader.getValue("error.require","Duration"));
			 pass=false;
		}
		
		if(DataValidator.isNull(request.getParameter("description")))
		{
			request.setAttribute("description", PropertyReader.getValue("error.require","Description"));
			 pass=false;
		}

		log.debug("method validate end");
		return pass;
	
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
	log.debug("method populateBean started");
	CourseBean bean=new CourseBean();
	bean.setId(DataUtility.getLong(request.getParameter("id")));
	bean.setName(request.getParameter("cname"));
	System.out.println("name>>>>>>>"+request.getParameter("cname"));
	bean.setDuration(request.getParameter("duration"));
	System.out.println(">>>>>>>duration"+request.getParameter("duration"));
	bean.setDescription(request.getParameter("description"));
	System.out.println(">>>>>>description"+request.getParameter("description"));
	System.out.println("create  by>>>>>>>"+request.getParameter("createdBy"));
	System.out.println("modi by>>>>>>>"+request.getParameter("modifiedBy"));
	System.out.println("cretaet time>>>>>>>"+request.getParameter("createdDatetime"));
	System.out.println("modif time>>>>>>>"+request.getParameter("modifiedDatetime"));
	
	populateDTO(bean, request);
	log.debug("method populatebean end");
	return bean;
	}
	
	//for display Logic
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String op= request.getParameter("operation");
		
		//get model
		CourseModel model=new CourseModel();
		long id= DataUtility.getLong(request.getParameter("id"));
		
		if(id>0)
		{
			CourseBean bean;
			try {
			bean=	model.findByPk(id);
			
			ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("method dopost start");
		String op = DataUtility.getString(request.getParameter("operation"));
		
		//get model
		CourseModel model = new CourseModel();
		
		long id=Long.parseLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
		{
			CourseBean bean= (CourseBean) populateBean(request);
			try {
			if(id>0)
			{
				
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Course update Successfully", request);
			}
			else
			{
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Course Added Succesfully", request);
				
			}
			} catch (ApplicationException e) {
				//	e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				} catch (DuplicateRecordException e) {
//					e.printStackTrace();
					ServletUtility.setErrorMessage("Course Name already exist", request);
				ServletUtility.setBean(bean, request);
				}
				
			
		}
		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}
		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		}
	
		ServletUtility.forward(getView(), request, response);
		log.debug("method dopost end");
	
	}
	
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_VIEW;
	}

}
