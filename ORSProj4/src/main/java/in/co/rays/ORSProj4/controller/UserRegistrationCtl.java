package in.co.rays.ORSProj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.RoleBean;
import in.co.rays.ORSProj4.bean.UserBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.UserModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.DataValidator;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;

@ WebServlet(name="UserRegistrationCtl",urlPatterns={"/UserRegistrationCtl"})
public class UserRegistrationCtl extends BaseCtl{

	

	    public static final String OP_SIGN_UP = "SignUp";

	    private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("UserRegistrationCtl Method validate Started");

	        boolean pass = true;

	        String login = request.getParameter("login");
	        String dob = request.getParameter("dob");

	        if (DataValidator.isNull(request.getParameter("firstName"))) {
	            request.setAttribute("firstName",
	                    PropertyReader.getValue("error.require", "First Name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("lastName"))) {
	            request.setAttribute("lastName",
	                    PropertyReader.getValue("error.require", "Last Name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.require", "Login Id"));
	            pass = false;
	        } else if (!DataValidator.isEmail(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.email", "Login "));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("password"))) {
	            request.setAttribute("password",
	                    PropertyReader.getValue("error.require", "Password"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
	            request.setAttribute("confirmPassword", PropertyReader.getValue(
	                    "error.require", "Confirm Password"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("gender"))) {
	            request.setAttribute("gender",
	                    PropertyReader.getValue("error.require", "Gender"));
	            pass = false;
	        }
	        if (DataValidator.isNull(dob)) {
	            request.setAttribute("dob",
	                    PropertyReader.getValue("error.require", "Date Of Birth"));
	            pass = false;
	        } else if (!DataValidator.isDate(dob)) {
	            request.setAttribute("dob",
	                    PropertyReader.getValue("error.date", "Date Of Birth"));
	            pass = false;
	        }
	        if (!request.getParameter("password").equals(
	                request.getParameter("confirmPassword"))
	                && !"".equals(request.getParameter("confirmPassword"))) {
	            ServletUtility.setErrorMessage(
	                    "Confirm  Password  should not be matched.", request);

	            pass = false;
	        }
	        log.debug("UserRegistrationCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("UserRegistrationCtl Method populatebean Started");

	        UserBean bean = new UserBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setRoleId(RoleBean.STUDENT);

	        bean.setFirstName(DataUtility.getString(request
	                .getParameter("firstName")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        bean.setLogin(DataUtility.getString(request.getParameter("login")));

	        bean.setPassword(DataUtility.getString(request.getParameter("password")));

	        bean.setConfirmPassword(DataUtility.getString(request
	                .getParameter("confirmPassword")));

	        bean.setGender(DataUtility.getString(request.getParameter("gender")));

	        bean.setDob(DataUtility.getDate(request.getParameter("dob")));

	        populateDTO(bean, request);

	        log.debug("UserRegistrationCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Display concept of user registration
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("UserRegistrationCtl Method doGet Started");
	        System.out.println("user registration ctl view");
	        ServletUtility.forward(getView(), request, response);

	    }

	    /**
	     * Submit concept of user registration
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("in get method");
	        log.debug("UserRegistrationCtl Method doPost Started");
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        UserModel model = new UserModel();
	     //   long id = DataUtility.getLong(request.getParameter("id"));
//	        System.out.println("inside dopost");
//	        System.out.println("f name "+ request.getParameter("firstName"));
//	        System.out.println("last name "+ request.getParameter("lastName"));
//	        System.out.println("login: "+ request.getParameter("login"));
//	        System.out.println("password"+ request.getParameter("password"));
//            System.out.println("upper signup");

	        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
//                System.out.println("**inside signup");

	        	UserBean bean = (UserBean) populateBean(request);
	            try {
	//                System.out.println("**inside UB *");

	            	long pk = model.registerUser(bean);
	  //              System.out.println("**inside register method");

	            	bean.setId(pk);
	    //            System.out.println("**inside servlet utility");
	                //request.getSession().setAttribute("UserBean", bean);
	                //ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
	        //        System.out.println("**inside servlet utility2");
	                ServletUtility.setSuccessMessage("User Successfully Register", request);
	      //          System.out.println("***succ message :"+ServletUtility.getSuccessMessage(request));
	                ServletUtility.forward(getView(), request, response);
	                return;
	            } catch (ApplicationException e) {
	                e.printStackTrace();
	            	log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                log.error(e);
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	                ServletUtility.forward(getView(), request, response);
	            }
	        }
	        else if(OP_RESET.equalsIgnoreCase(op)) {
	        	ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
	        }
	        log.debug("UserRegistrationCtl Method doPost Ended");
	    }
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_REGISTRATION_VIEW;
	}

	
}
