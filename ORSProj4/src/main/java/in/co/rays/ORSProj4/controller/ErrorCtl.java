package in.co.rays.ORSProj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSProj4.util.ServletUtility;

@WebServlet (name="ErrorCtl" , urlPatterns ={"/ctl/ErrorCtl"})
public class ErrorCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(ErrorCtl.class);
    
    /**
     * Contains Display logics.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Do get Method of Error Ctl started");
		System.out.println("_______________start doGet error ctl-_-------->" );
		ServletUtility.redirect(ORSView.ERROR_VIEW, request, response);
		System.out.println("_______________end doGet error ctl-_-------->" );
		log.debug("Do get Method of Error Ctl End");
		
	}

    /**
     * Contains Submit logics.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		log.debug("Do Post Method of Error Ctl started");
		System.out.println(">>>>>>>>>>>>>start doPOst>>>>>>>>>>>>");
		ServletUtility.redirect(getView(), request, response);
		System.out.println(">>>>>>>>>>>>>end doPOst>>>>>>>>>>>>");
		log.debug("Do Post Method of Error Ctl End");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}

