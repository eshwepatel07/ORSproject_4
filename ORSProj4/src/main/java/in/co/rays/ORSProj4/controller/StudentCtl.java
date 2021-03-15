package in.co.rays.ORSProj4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProj4.bean.BaseBean;
import in.co.rays.ORSProj4.bean.StudentBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.model.CollegeModel;
import in.co.rays.ORSProj4.model.StudentModel;
import in.co.rays.ORSProj4.util.DataUtility;
import in.co.rays.ORSProj4.util.DataValidator;
import in.co.rays.ORSProj4.util.PropertyReader;
import in.co.rays.ORSProj4.util.ServletUtility;

@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/StudentCtl" })
public class StudentCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("StudentCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstname"))) {
			request.setAttribute("firstname", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("firstname"))) {
			request.setAttribute("firstname", PropertyReader.getValue("error.name", "Invalid First"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastname"))) {
			request.setAttribute("lastname", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("lastname"))) {
			request.setAttribute("lastname", PropertyReader.getValue("error.name", "Invalid Last"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}
		
//		else if (!DataValidator.isMobileDupl(DataUtility.getString(request.getParameter("mobile")))) {
//			request.setAttribute("mobile", "mobile no. already exist");
//			pass = false;
//		}
		
		
		
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "Invalid "));
			pass = false;
		}
				if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isvalidateAge(request.getParameter("dob"))) {
			request.setAttribute("dob", "Student Age must be Greater then 18 year ");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeName"))) {
			request.setAttribute("collegeName", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		log.debug("StudentCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("StudentCtl Method populatebean Started");

		StudentBean bean = new StudentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("id>>>>"+request.getParameter("id"));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstname")));
		System.out.println("fname>>>>"+request.getParameter("firstname"));
		bean.setLastName(DataUtility.getString(request.getParameter("lastname")));
		System.out.println("lname>>>>"+request.getParameter("lastname"));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		System.out.println("dob>>>>"+request.getParameter("dob"));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		System.out.println("mobile>>>>"+request.getParameter("mobile"));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		System.out.println("email>>>>"+request.getParameter("email"));

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeName")));
		// bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		System.out.println("college name>>>>"+request.getParameter("collegeName"));

		populateDTO(bean, request);
		log.debug("StudentCtl Method populatebean Ended");
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("StudentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		StudentModel model = new StudentModel();
		if (id > 0 || op != null) {
			StudentBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
	ServletUtility.forward(getView(), request, response);
		log.debug("StudentCtl Method doGett Ended");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("StudentCtl Method doPost Started");
			
		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));
		// get model

		StudentModel model = new StudentModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("inside save op");
			StudentBean bean = (StudentBean) populateBean(request);
			System.out.println(bean.getCollegeName());
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" Student is successfully Updated", request);
				} else {
					System.out.println("upper add model");
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					System.out.println("upper add model");
					ServletUtility.setSuccessMessage("Student is successfully Added", request);
					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				// ServletUtility.setSuccessMessage(" Student is successfully Added",request);
			} catch (ApplicationException e) {
				//e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;

			}
			catch (DuplicateRecordException e) {
	//			System.out.println("e printstack>>>>>>");
				e.printStackTrace();
//				request.setAttribute("mobile no already", e);
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Student Email/mobile Id already exists", request);
            }
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("StudentCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}




}