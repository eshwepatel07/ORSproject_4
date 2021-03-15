package in.co.rays.ORSProj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.RoleBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.model.RoleModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;

@WebServlet(name="RoleListCtl",urlPatterns = "/ctl/RoleListCtl")
public class RoleListCtl extends BaseCtl{

private static	Logger log=Logger.getLogger(RoleListCtl.class);
	
@Override
protected void preload(HttpServletRequest request){
   
   RoleModel rmodel = new RoleModel();
		
	try{
	    List rlist= rmodel.list();    
	    request.setAttribute("RoleList",rlist);
        }
        catch(ApplicationException e){
        e.printStackTrace();
        }
        }






@Override
	protected BaseBean populateBean(HttpServletRequest request) {
	
		//RoleModel emodel=new RoleModel();
		RoleBean bean=new RoleBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setId(DataUtility.getLong(request.getParameter("roleid")));
	
	
	return bean;
	}

	
	@Override
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException
	{
		
		System.out.println("inside do get");
		log.debug("Rolectl List Start");
		List list=null;
		List nextList=null;
		
		int pageNo=1;
		int pageSize= DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		RoleBean bean= (RoleBean) populateBean(request);
		String op=DataUtility.getString(request.getParameter("operation"));
		
		String[] ids=request.getParameterValues("ids");
		RoleModel model=new RoleModel();
		
		try {
			list= model.search(bean, pageNo, pageSize);
			
			nextList=model.search(bean, pageNo+1, pageSize);
			
			request.setAttribute("nextlist", nextList.size());

			if(list==null || list.size()==0)
			{
				ServletUtility.setErrorMessage("no record found", request);
			}
			
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo,request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
			
		} catch (Exception e) {
				e.printStackTrace();
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		System.out.println("inside do get");
		log.debug("RoleListCtl doGet End");
		
	}
	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside do Post");
		List list=null;
		List nextList=null;
		
		int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize=DataUtility.getInt(request.getParameter("pagesize"));
		
		pageNo=(pageNo==0) ? 1 :pageNo;
		pageSize=(pageSize==0) ? DataUtility.getInt(request.getParameter("page.size")) : pageSize;
		
		RoleBean bean= (RoleBean) populateBean(request);
		String op=DataUtility.getString(request.getParameter("operation"));
		
		String[] ids= request.getParameterValues("ids");
		RoleModel rmodel= new RoleModel();
		
		if(OP_SEARCH.equals(op))
		{
			pageNo=1;
			
		}
		else if(OP_NEXT.equals(op))
		{
			pageNo++;
		}
		else if(OP_NEW.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
			
		}
		else if(OP_RESET.equalsIgnoreCase(op))
			
		{
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
		}
		else if(OP_DELETE.equalsIgnoreCase(op))
			
		{
			pageNo=1;
			if(ids!=null && ids.length>0)
			{
				RoleBean deletebean =new RoleBean();
				for(String id : ids)
				{
					deletebean.setId(DataUtility.getInt(id));
					try {
						rmodel.delete(deletebean);
						
					
					} catch (ApplicationException e) {
						log.error(e);
			            ServletUtility.handleException(e, request, response);
			            return;
					}ServletUtility.setSuccessMessage("Role is Deleted Successfully ", request);       
                }
            } else {
                ServletUtility.setErrorMessage("Select at least one record", request);
            }
			}
			try {
				list=rmodel.search(bean, pageNo, pageSize);
				 nextList=rmodel.search(bean,pageNo+1,pageSize);
					
					request.setAttribute("nextlist", nextList.size());
					
			
			} catch (Exception e) {
					e.printStackTrace();
			
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
			 if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setBean(bean, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	            
	            log.debug("RoleListCtl doPost end");
		}
			
		
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_LIST_VIEW;
	}

}
