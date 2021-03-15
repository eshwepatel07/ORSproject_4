package in.co.rays.ORSProj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ParseConversionEvent;
import javax.xml.ws.RequestWrapper;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.CourseBean;
import in.co.rays.ORSProj4.bean.SubjectBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.model.CourseModel;
import in.co.rays.ORSProj4.model.SubjectModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;

@WebServlet (name = "SubjectListCtl", urlPatterns = {"/ctl/SubjectListCtl"})
public class SubjectListCtl extends BaseCtl{

	@Override
	protected void preload(HttpServletRequest request) {
	
	SubjectModel smodel= new SubjectModel();
	CourseModel cmodel=new CourseModel();
	try {
		List slist=smodel.list();
		request.setAttribute("slist", slist);
		
		List clist=cmodel.list();
		request.setAttribute("clist", clist);
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		SubjectBean sbean=new SubjectBean();
		//CourseBean	cbean= new CourseBean();
		sbean.setSubjectName(request.getParameter("name"));
		sbean.setId(DataUtility.getLong(request.getParameter("subjectname")));
		sbean.setCourseId(DataUtility.getLong(request.getParameter("coursename")));
		//		sbean.setSubjectName("");
		
		ServletUtility.setBean(sbean, request);
		populateDTO(sbean, request);
		return sbean; 
			
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List list = null;
		List nextList=null;


		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		SubjectBean bean = (SubjectBean) populateBean(request);
		SubjectModel model = new SubjectModel();
	
		try {
			list=model.search(bean, pageNo, pageSize);
			
			nextList=model.search(bean, pageNo+1, pageSize);
			
			request.setAttribute("nextlist", nextList);
			
			ServletUtility.setList(list, request);
			
			if(list==null && list.size()==0)
			{
				ServletUtility.setErrorMessage("record not found", request);
			}

			
				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
				
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List list;
		List nextList=null;
		
		int pageNo= DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize= DataUtility.getInt(request.getParameter("pageSize"));
	
		pageNo=(pageNo==0) ? 1 : pageNo;
		pageSize=(pageSize==0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) :pageSize;
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		String[] ids= request.getParameterValues("ids");
		SubjectBean sbean = (SubjectBean) populateBean(request);
		SubjectModel model=new SubjectModel();
		
		
		if(OP_SEARCH.equalsIgnoreCase(op))
		{
			pageNo=1;
		}else if(OP_NEXT.equalsIgnoreCase(op))
		{
			pageNo ++;
		}
		else if(OP_PREVIOUS.equalsIgnoreCase(op))
		{
			if(pageNo>1)
			{
				pageNo--;
			}
			else
			{
			pageNo=1;	
			}
		}
		else if(OP_NEW.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op))
		{
			pageNo=1;
			
			if(ids!=null && ids.length>0)
			{
				SubjectBean deletebean= new SubjectBean();
				for(String id:ids)
				{
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						ServletUtility.handleException(e, request, response);
						return;
					}
					ServletUtility.setSuccessMessage("Record Deleted", request);
				}
				
			}
			else
			{
				ServletUtility.setErrorMessage("select atleast one record", request);
			}
			
		}
		
		
		try {
			list=model.search(sbean, pageNo, pageSize);
			
			//ServletUtility.setBean(sbean, request);
			nextList=model.search(sbean, pageNo, pageSize);
			
			
			request.setAttribute("nextlist", nextList.size());
			ServletUtility.setBean(sbean, request);
			
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			ServletUtility.handleException(e, request, response);
			return;
		
		}
		if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op))
		{
			ServletUtility.setErrorMessage("no record found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setBean(sbean, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView() , request, response);


	}
	
	
	
	@Override
	protected String getView() {
	
		return ORSView.SUBJECT_LIST_VIEW;
	}

}
