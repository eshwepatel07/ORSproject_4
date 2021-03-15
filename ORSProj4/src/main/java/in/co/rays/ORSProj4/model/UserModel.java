package in.co.rays.ORSProj4.model;

import java.sql.Connection;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import in.co.rays.ORSProj4.bean.UserBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DatabaseException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.exception.RecordNotFoundException;
import in.co.rays.ORSProj4.util.EmailBuilder;
import in.co.rays.ORSProj4.util.EmailMessage;
import in.co.rays.ORSProj4.util.EmailUtility;
import in.co.rays.ORSProj4.util.JDBCDataSource;

public class UserModel {

	private static Logger log =Logger.getLogger(UserModel.class);
	
	
	 /**
     * Find next PK of user
     *
     * @throws DatabaseException
     */


	 public Integer nextPK() throws DatabaseException{
		 log.debug("model next pk Start");
		 
		 Connection conn=null;
		 int pk=0;
		 
		 
		 try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt= conn.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				pk=rs.getInt(1);
				
			}
			rs.close();
		 }
		 catch(Exception e)
		 {
			 log.error("DataBaseException...");
			 throw new DatabaseException("Exception : Exception in getting PK");
			 }
		 finally {
		 JDBCDataSource.closeConnection(conn);
		  }
		 log.debug("Model nextPk End");
		 
		 
		return pk+1;
		
		 
	 }
	
	 
	 /**
	     * Add a User
	     *
	     * @param bean
	     * @throws DatabaseException
	     *
	     */
	    public long add(UserBean bean) throws ApplicationException,
	            DuplicateRecordException {
	        log.debug("Model add Started");
	        Connection conn = null;
	        int pk = 0;
		
		  UserBean existbean = findByLogin(bean.getLogin());
		  UserBean duplicatebean = findBymobile(bean.getMobileNo());
		  if (existbean != null) { throw new
		  DuplicateRecordException("Login Id already exists"); 
		  }
		  else if (duplicatebean != null) { throw new
			  DuplicateRecordException("Mobile No. Id already exists"); 
			  }
		  else {
	        try {
	            conn = JDBCDataSource.getConnection();
	            pk = nextPK();
	           
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	            pstmt.setInt(1, pk);
	            pstmt.setString(2, bean.getFirstName());
	            pstmt.setString(3, bean.getLastName());
	            pstmt.setString(4, bean.getLogin());
	            pstmt.setString(5, bean.getPassword());
	            pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
	            pstmt.setString(7, bean.getMobileNo());
	            pstmt.setLong(8, bean.getRoleId());
	            //pstmt.setInt(9, bean.getUnSuccessfulLogin());
	            pstmt.setString(9, bean.getGender());
			/*
			 * pstmt.setTimestamp(11, bean.getLastLogin()); pstmt.setString(12,
			 * bean.getLock()); pstmt.setString(13, bean.getRegisteredIP());
			 * pstmt.setString(14, bean.getLastLoginIP());
			 */
	            pstmt.setString(10, bean.getCreatedBy());
	            pstmt.setString(11, bean.getModifiedBy());
	            pstmt.setTimestamp(12, bean.getCreatedDatetime());
	            pstmt.setTimestamp(13, bean.getModifiedDatetime());
	            pstmt.executeUpdate();
	            System.out.println(pk + " in UserModelJDBC");
	            System.out.println("Test add succ");
	            
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                throw new ApplicationException(
	                        "Exception : add rollback exception " + ex.getMessage());
	            }
	            throw new ApplicationException("Exception : Exception in add User");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model add End");
	        return pk;
	    }
	    
	    }
	    //Model Delete 
	    
	    public void delete(UserBean bean) throws ApplicationException
	    {
	    	log.debug("Model Delete Started");
	    	Connection conn=null;
	    	
	    	try {
				conn=JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				
				PreparedStatement ps=conn.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
				ps.setLong(1,bean.getId());
				ps.executeUpdate();
				conn.commit();
				ps.close();
				
				
				
			} catch (Exception e) {
			
				log.error("DataBase Exception ",e);
					try {
						conn.rollback();
					} catch (Exception ex) {
	throw new ApplicationException("Exception : Delete rollback exception "+ ex.getMessage());
					
					}
					throw new ApplicationException(
		                    "Exception : SQL DataBase Exception in delete User");
			}
	    	finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model delete End");
	    }
	    
//	    model FindbyPK
	    
	    public UserBean findByPK(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
	        UserBean bean = null;
	        Connection conn = null;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new UserBean();
	                bean.setId(rs.getLong(1));
	                bean.setFirstName(rs.getString(2));
	                bean.setLastName(rs.getString(3));
	                bean.setLogin(rs.getString(4));
	                bean.setPassword(rs.getString(5));
	                bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(7));
	                bean.setRoleId(rs.getLong(8));
	//                bean.setUnSuccessfulLogin(rs.getInt(9));
	                bean.setGender(rs.getString(9));
//	                bean.setLastLogin(rs.getTimestamp(11));
//	                bean.setLock(rs.getString(12));
//	                bean.setRegisteredIP(rs.getString(13));
//	                bean.setLastLoginIP(rs.getString(14));
	                bean.setCreatedBy(rs.getString(10));
	                bean.setModifiedBy(rs.getString(11));
//	                bean.setCreatedDatetime(rs.getTimestamp(12));
	                bean.setModifiedDatetime(rs.getTimestamp(13));

	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	    

	    //find by MObile
	    public UserBean findBymobile(String mobile) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT mobile_no FROM ST_USER WHERE mobile_no=?");
	        UserBean bean = null;
	        Connection conn = null;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, mobile);
	            
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new UserBean();
	             //   bean.setId(rs.getLong(1));
	               // bean.setFirstName(rs.getString(2));
	                //bean.setLastName(rs.getString(3));
	                //bean.setLogin(rs.getString(4));
	               // bean.setPassword(rs.getString(5));
	               // bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(1));
	                //bean.setRoleId(rs.getLong(8));
	//                bean.setUnSuccessfulLogin(rs.getInt(9));
	               // bean.setGender(rs.getString(9));
//	                bean.setLastLogin(rs.getTimestamp(11));
//	                bean.setLock(rs.getString(12));
//	                bean.setRegisteredIP(rs.getString(13));
//	                bean.setLastLoginIP(rs.getString(14));
//	                bean.setCreatedBy(rs.getString(10));
//	                bean.setModifiedBy(rs.getString(11));
//	                bean.setCreatedDatetime(rs.getTimestamp(12));
//	                bean.setModifiedDatetime(rs.getTimestamp(13));

	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	    

	    
	    
	    
	    
	    
// MOdel Find by Login
	    
	    public UserBean findByLogin(String Login) throws ApplicationException {
	        log.debug("Model findBylogin Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE login=?");
	        UserBean bean = null;
	        Connection conn = null;
	        System.out.println("findbylogin of user model");
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setString(1, Login);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new UserBean();
	                bean.setId(rs.getLong(1));
	                bean.setFirstName(rs.getString(2));
	                bean.setLastName(rs.getString(3));
	                bean.setLogin(rs.getString(4));
	                bean.setPassword(rs.getString(5));
	                bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(7));
	                bean.setRoleId(rs.getLong(8));
	//                bean.setUnSuccessfulLogin(rs.getInt(9));
	                bean.setGender(rs.getString(9));
//	                bean.setLastLogin(rs.getTimestamp(11));
//	                bean.setLock(rs.getString(12));
//	                bean.setRegisteredIP(rs.getString(13));
//	                bean.setLastLoginIP(rs.getString(14));
	                bean.setCreatedBy(rs.getString(10));
	                bean.setModifiedBy(rs.getString(11));
	                bean.setCreatedDatetime(rs.getTimestamp(12));
	                bean.setModifiedDatetime(rs.getTimestamp(13));

	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }

	    
	    /**
	     * Get User Roles
	     *
	     * @return List : User Role List
	     * @param bean
	     * @throws ApplicationException
	     */

	    public List getRoles(UserBean bean) throws ApplicationException {
	        log.debug("Model get roles Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM ST_USER WHERE role_Id=?");
	        Connection conn = null;
	        List list = new ArrayList();
	        try {

	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, bean.getRoleId());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new UserBean();
	                bean.setId(rs.getLong(1));
	                bean.setFirstName(rs.getString(2));
	                bean.setLastName(rs.getString(3));
	                bean.setLogin(rs.getString(4));
	                bean.setPassword(rs.getString(5));
	                bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(7));
	                bean.setRoleId(rs.getLong(8));
	               // bean.setUnSuccessfulLogin(rs.getInt(9));
	                bean.setGender(rs.getString(9));
	                //bean.setLastLogin(rs.getTimestamp(11));
	                //bean.setLock(rs.getString(12));
	               // bean.setRegisteredIP(rs.getString(13));
	               // bean.setLastLoginIP(rs.getString(14));
	                bean.setCreatedBy(rs.getString(10));
	               // bean.setModifiedBy(rs.getString(16));
	                //bean.setCreatedDatetime(rs.getTimestamp(17));
	                //bean.setModifiedDatetime(rs.getTimestamp(18));

	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception : Exception in get roles");

	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model get roles End");
	        return list;
	    }
	    
	    //
	    /**
	     * Update a user
	     *
	     * @param bean
	     * @throws DatabaseException
	     */

	    public void update(UserBean bean) throws ApplicationException,
	            DuplicateRecordException {
	        log.debug("Model update Started");
	        Connection conn = null;

//	        UserBean beanExist = findByLogin(bean.getLogin());
//	        // Check if updated LoginId already exist
//	        if (beanExist != null && !(beanExist.getId() == bean.getId())) {
//	            throw new DuplicateRecordException("LoginId is already exist");
//	        }
//	    
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            //System.out.println(bean.getModifiedBy());
	            PreparedStatement pstmt = conn 
	            		
.prepareStatement("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,GENDER=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
	            pstmt.setString(1, bean.getFirstName());
	            pstmt.setString(2, bean.getLastName());
	            pstmt.setString(3, bean.getLogin());
	            pstmt.setString(4, bean.getPassword());
	            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
	            pstmt.setString(6, bean.getMobileNo());
	            pstmt.setLong(7, bean.getRoleId());
	            //pstmt.setInt(8, bean.getUnSuccessfulLogin());
	            pstmt.setString(8, bean.getGender());
			/*
			 * pstmt.setTimestamp(10, bean.getLastLogin()); pstmt.setString(11,
			 * bean.getLock()); pstmt.setString(12, bean.getRegisteredIP());
			 * pstmt.setString(13, bean.getLastLoginIP());
			 */  
	            //pstmt.setString(14, bean.getCreatedBy());
	            pstmt.setString(9, bean.getModifiedBy());
	            //pstmt.setTimestamp(16, bean.getCreatedDatetime());
	            pstmt.setTimestamp(10, bean.getModifiedDatetime());
	            pstmt.setLong(11, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	            e.printStackTrace();
//	            log.error("Database Exception..", e);
//	            try {
//	                conn.rollback();
//	            } catch (Exception ex) {
//	            	System.out.println("");
//	            	//  throw new ApplicationException(
//	                //        "Exception : Delete rollback exception "
//	                  //              + ex.getMessage());
//	            }
//	            //throw new ApplicationException("Exception in updating User ");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model update End");
	    }
	        
	        
	        
	        
	    
	    

	    //Authentication Started here

	    public UserBean authenticate(String login, String password)
        throws ApplicationException {
    log.debug("Model authenticate Started");
    StringBuffer sql = new StringBuffer(
            "SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
    UserBean bean = null;
    Connection conn = null;

    try {
        conn = JDBCDataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1, login);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            bean = new UserBean();
            bean.setId(rs.getLong(1));
            bean.setFirstName(rs.getString(2));
            bean.setLastName(rs.getString(3));
            bean.setLogin(rs.getString(4));
            bean.setPassword(rs.getString(5));
            bean.setDob(rs.getDate(6));
            bean.setMobileNo(rs.getString(7));
            bean.setRoleId(rs.getLong(8));
            //bean.setUnSuccessfulLogin(rs.getInt(9));
            bean.setGender(rs.getString(9));
//            bean.setLastLogin(rs.getTimestamp(11));
  //          bean.setLock(rs.getString(12));
    //        bean.setRegisteredIP(rs.getString(13));
      //      bean.setLastLoginIP(rs.getString(14));
            bean.setCreatedBy(rs.getString(10));
            bean.setModifiedBy(rs.getString(11));
            bean.setCreatedDatetime(rs.getTimestamp(12));
            bean.setModifiedDatetime(rs.getTimestamp(13));

        }
    } catch (Exception e) {
        log.error("Database Exception..", e);
        throw new ApplicationException("Exception : Exception in get roles");

    } finally {
        JDBCDataSource.closeConnection(conn);
    }

    log.debug("Model authenticate End");
    return bean;
}
	    
	    
	    /**
	     * Search User
	     *
	     * @param bean
	     *            : Search Parameters
	     * @throws DatabaseException
	     */

	    public List search(UserBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	    /**
	     * Search User with pagination
	     *
	     * @return list : List of Users
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     *
	     * @throws DatabaseException
	     */

	    public List search(UserBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getLastName() != null && bean.getLastName().length() > 0) {
	                sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
	            }
	            if (bean.getLogin() != null && bean.getLogin().length() > 0) {
	                sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
	            }
	            if (bean.getPassword() != null && bean.getPassword().length() > 0) {
	                sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
	            }
	            if (bean.getDob() != null && bean.getDob().getDate() > 0) {
	                sql.append(" AND DOB = " + bean.getGender());
	            }
	            if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
	                sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
	            }
	            if (bean.getRoleId() > 0) {
	                sql.append(" AND ROLE_ID = " + bean.getRoleId());
	            }
	            
	            if(bean.getGender()!= null && bean.getGender().length()>0)
	            {
	            	sql.append(" AND GENDER like '"+bean.getGender()+"%'");
	            }
	            if(bean.getFirstName()!=null)
	            {
	            	sql.append(" AND FIRST_NAME like '"+bean.getFirstName()+ "%'");
	            }
	            

	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;

	            sql.append(" Limit " + pageNo + ", " + pageSize);
	        }

	        System.out.println(" preload search query>>>>>>>>: "+sql);
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new UserBean();
	                bean.setId(rs.getLong(1));
	                bean.setFirstName(rs.getString(2));
	                bean.setLastName(rs.getString(3));
	                bean.setLogin(rs.getString(4));
	                bean.setPassword(rs.getString(5));
	                bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(7));
	                bean.setRoleId(rs.getLong(8));
//	                bean.setUnSuccessfulLogin(rs.getInt(9));
	                bean.setGender(rs.getString(9));
//	                bean.setLastLogin(rs.getTimestamp(11));
//	                bean.setLock(rs.getString(12));
//	                bean.setRegisteredIP(rs.getString(13));
//	                bean.setLastLoginIP(rs.getString(14));
//	                bean.setCreatedBy(rs.getString(15));
//	                bean.setModifiedBy(rs.getString(16));
//	                bean.setCreatedDatetime(rs.getTimestamp(17));
//	                bean.setModifiedDatetime(rs.getTimestamp(18));

	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            e.printStackTrace();
	            throw new ApplicationException(
	                    "Exception : Exception in search user");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model search End");
	        return list;
	    }
	    
	    
	    /**
	     * Get List of User
	     *
	     * @return list : List of User
	     * @throws DatabaseException
	     */

	    public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	    /**
	     * Get List of User with pagination
	     *
	     * @return list : List of users
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * @throws DatabaseException
	     */

	    public List list(int pageNo, int pageSize) throws ApplicationException {
	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from ST_USER");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }

	        Connection conn = null;
	        System.out.println(sql);
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                UserBean bean = new UserBean();
	                bean.setId(rs.getLong(1));
	                bean.setFirstName(rs.getString(2));
	                bean.setLastName(rs.getString(3));
	                bean.setLogin(rs.getString(4));
	                bean.setPassword(rs.getString(5));
	                bean.setDob(rs.getDate(6));
	                bean.setMobileNo(rs.getString(7));
	                bean.setRoleId(rs.getLong(8));
	              //  bean.setUnSuccessfulLogin(rs.getInt(9));
	                bean.setGender(rs.getString(9));
//	                bean.setLastLogin(rs.getTimestamp(11));
//	                bean.setLock(rs.getString(12));
//	                bean.setRegisteredIP(rs.getString(13));
//	                bean.setLastLoginIP(rs.getString(14));
	                bean.setCreatedBy(rs.getString(10));
	                bean.setModifiedBy(rs.getString(11));
	                bean.setCreatedDatetime(rs.getTimestamp(12));
	                bean.setModifiedDatetime(rs.getTimestamp(13));

	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of users");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model list End");
	        return list;

	    }
	    
	    
	    /**
	     * Lock User for certain time duration
	     *
	     * @return boolean : true if success otherwise false
	     * @param login
	     *            : User Login
	     * @throws ApplicationException
	     * @throws RecordNotFoundException
	     *             : if user not found
	     */

	    public boolean lock(String login) throws RecordNotFoundException,
	            ApplicationException {
	        log.debug("Service lock Started");
	        boolean flag = false;
	        UserBean beanExist = null;
	        try {
	            beanExist = findByLogin(login);
	            if (beanExist != null) {
	                beanExist.setLock(UserBean.ACTIVE);
	                update(beanExist);
	                flag = true;
	            } else {
	                throw new RecordNotFoundException("LoginId not exist");
	            }
	        } catch (DuplicateRecordException e) {
	            log.error("Application Exception..", e);
	            throw new ApplicationException("Database Exception");
	        }
	        log.debug("Service lock End");
	        return flag;
	    }

	    	
	    /**
	     * @param id
	     *            : long id
	     * @param old
	     *            password : String oldPassword
	     * @param newpassword
	     *            : String newPassword
	     * @throws DatabaseException
	     */

	    public boolean changePassword(String login, String oldPassword,
	            String newPassword) throws RecordNotFoundException,
	            ApplicationException {

	        log.debug("model changePassword Started");
	        boolean flag = false;
	        UserBean beanExist = null;
	        System.out.println(login+"change pass user model id check");
	        beanExist = findByLogin(login);
	        if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
	            beanExist.setPassword(newPassword); 
	           
	            try {
	                update(beanExist);
	            } catch (DuplicateRecordException e) {
	                log.error(e);
	                throw new ApplicationException("LoginId is already exist");
	            }
	            flag = true;
	        } else {
	            throw new RecordNotFoundException("old password does't match");
	        }

	        System.out.println(beanExist.getLogin()+"  "+beanExist.getPassword()+" "+ beanExist.getFirstName()+" "+beanExist.getLastName());
	        HashMap<String, String> map = new HashMap<String, String>();

	        map.put("login", beanExist.getLogin());
	        map.put("password", beanExist.getPassword());
	        map.put("firstName", beanExist.getFirstName());
	        map.put("lastName", beanExist.getLastName());

	        
	        
	        String message = EmailBuilder.getChangePasswordMessage(map);

	        EmailMessage msg = new EmailMessage();

	        msg.setTo(login);
	        msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
	        msg.setMessage(message);
	        msg.setMessageType(EmailMessage.HTML_MSG);

	        EmailUtility.sendMail(msg);

	        log.debug("Model changePassword End");
	        return flag;

	    }

	    public UserBean updateAccess(UserBean bean) throws ApplicationException {
	        return null;
	    }
	    
	    
	    
	    /**
	     * Register a user
	     *
	     * @param bean
	     * @throws ApplicationException
	     * @throws DuplicateRecordException
	     *             : throws when user already exists
	     */

	    public long registerUser(UserBean bean) throws ApplicationException,
	            DuplicateRecordException {

	        log.debug("Model add Started");

	        long pk = add(bean);

	        HashMap<String, String> map = new HashMap<String, String>();
	        map.put("login", bean.getLogin());
	        map.put("password", bean.getPassword());

	        String message = EmailBuilder.getUserRegistrationMessage(map);

	        EmailMessage msg = new EmailMessage();
	        System.out.println("----------->"+bean.getLogin());

	        msg.setTo(bean.getLogin());
	        msg.setSubject("Registration is successful for ORS Project SunilOS");
	        msg.setMessage(message);
	        msg.setMessageType(EmailMessage.HTML_MSG);

	        EmailUtility.sendMail(msg);
	        return 1;
	    }
	    
	    
	    
	    public boolean forgetPassword(String login) throws ApplicationException,
        RecordNotFoundException {
	    	try {
	    		System.out.println("forgetPassword of User model>>>>>>>>");
	    	UserBean userdata= findByLogin(login);
	    	boolean flag=false;
	    	//System.out.println("email "+userdata.getFirstName());
	    	if(userdata==null)
	    	{
	    		throw new RecordNotFoundException("Invalid Email ID");
//	    		System.out.println("email id not Exist");
	    	}
	    
	    	 HashMap<String,String> map= new HashMap<String, String>();
	    	 System.out.println("set name pass into hashmap obj");
	    	 map.put("login", userdata.getLogin());
	    	 map.put("password", userdata.getPassword());
	    	 map.put("firstName", userdata.getFirstName());
	    	 map.put("lastName", userdata.getLastName());
	    	 System.out.println("call emailbuilder getfp method");
	    	 String message= EmailBuilder.getForgetPasswordMessage(map);
	    	 System.out.println("create obj of emailmsg class");
	    	 EmailMessage msg=new EmailMessage();
	    	 System.out.println("set TO , Subj, Message, msg type");
	    	 msg.setTo(login);
	    	 msg.setSubject("SUNARYS ORS Password reset");
	    	 msg.setMessage(message);
	    	 msg.setMessageType(EmailMessage.HTML_MSG);
	    	 System.out.println("call sendmail() of emailutility");
	    	 EmailUtility.sendMail(msg);
	    	 flag=true;
	    	// return flag;
	    
	    	}
	    	catch(Exception e)
	    	{
	    		throw new RecordNotFoundException("record not found"+e.getMessage());
	    	}
			return false;
	    	
	    	
	    }
	    
	    
	    
	    public boolean resetPassword(UserBean bean) throws ApplicationException {
		
	    			//int p = (int); 
	    	int p =(int)(Math.random()*1000)+1000;
	    	String  otp= String.valueOf(p); 
	    			//String.valueOf(new Date().getTime()).substring(0,6);
	    	System.out.println("new password:- "+otp);
	    	UserBean userData=findByPK(bean.getId());
	//     	System.out.println(bean.getModifiedBy());
	 	    
	    	userData.setOtp(otp);
	    	//System.out.println(bean.getModifiedBy());
	    	//System.out.println(userData.getModifiedBy());
	    	
	    	try {
	    		if(userData!=null)
	    		{
				update(userData);
				
	    		}
	    		else {
	    			
	    			System.out.println("user data null");
	    		}
			}  catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    	
	    	HashMap<String,String> map = new HashMap<String, String>();
	    	
	    	map.put("login", bean.getLogin());
	    	map.put("otp", otp);
	    	map.put("firstName", bean.getFirstName());
	    	map.put("lastName", bean.getLastName());
	    	
	    	String message = EmailBuilder.getForgetPasswordMessage(map);
	    	 
	    	EmailMessage msg=new EmailMessage();
	    	
	    	msg.setTo(bean.getLogin());
	    	msg.setSubject("OTP");
	    	msg.setMessage(message);
	    	msg.setMessageType(EmailMessage.HTML_MSG);
	    	
	    	EmailUtility.sendMail(msg);
	    	
	    	
	    	
	    	
	    	return true;
	    	
	    }
	    
	    
	    
}
