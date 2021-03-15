package in.co.rays.ORSProj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProj4.bean.CollegeBean;
import in.co.rays.ORSProj4.bean.StudentBean;
import in.co.rays.ORSProj4.exception.ApplicationException;
import in.co.rays.ORSProj4.exception.DatabaseException;
import in.co.rays.ORSProj4.exception.DuplicateRecordException;
import in.co.rays.ORSProj4.util.JDBCDataSource;

public class StudentModel {

	private static Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Find next PK of Student
	 *
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_STUDENT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	// Add Student

	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model Add Started");
		
		 
		
		  CollegeModel model= new CollegeModel(); 
		  CollegeBean cbean=  model.findByPK(bean.getCollegeId());
		  bean.setCollegeName(cbean.getName());
		  StudentBean duplbean=findByEmailId(bean.getEmail()); 
		  StudentBean duplmno=findBymobile(bean.getMobileNo());
		  int pk=0 ;
		  if(duplbean!=null)
		  
		  { throw new DuplicateRecordException("student email already"); }
		  

		  if(duplmno!=null)
			  
		  { throw new DuplicateRecordException("student Mobile No. already"); }
		  
		  

		  
		  System.out.println("cname in model >>>>>>>>>>"+ cbean.getName());
		  System.out.println("in model>>>>>>>>>>>>>>>>>>>>>>>>"+bean.getCollegeName());
		 
		Connection conn = null;
		//System.out.println(">>>>>>>>>>>>>"+bean.getDob());
		try {
			conn = JDBCDataSource.getConnection();

			pk = nextPK();
			conn.setAutoCommit(false);
			String sql = "insert into st_student values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pk);
			ps.setLong(2, bean.getCollegeId());
			ps.setString(3, bean.getCollegeName());
			ps.setString(4, bean.getFirstName());
			ps.setString(5, bean.getLastName());
			System.out.println("kv");
			ps.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			// ps.setDate(6, bean.getDob().getTime());
			
			ps.setString(7, bean.getMobileNo());
			ps.setString(8, bean.getEmail());
			System.out.println("bean.getCreatedBy()"+bean.getCreatedBy()+"   "+bean.getCreatedBy().length());
			ps.setString(9, bean.getCreatedBy());
			ps.setString(10, bean.getModifiedBy());
			ps.setTimestamp(11, bean.getCreatedDatetime());
			ps.setTimestamp(12, bean.getModifiedDatetime());
			System.out.println("g");
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();

//			log.error("Database Exception", e);
//			try {
//				conn.rollback();
//			} catch (Exception ex) {
//				//ex.printStackTrace();
//				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
//			}
//			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	// ** Delete Student**//

	public void delete(StudentBean bean) throws ApplicationException {
		log.debug("model delete Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps;
			ps = conn.prepareStatement("delete from st_student where id=?");
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			log.error("Database Exception", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");
	}

	// **Find by pk start**//

	public StudentBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	// **find by email**//

	public StudentBean findByEmailId(String Email) throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE EMAIL=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Email End");
		return bean;
	}

	// ** u[date mmodel start**//

	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		StudentBean beanExist = findByEmailId(bean.getEmail());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id is already exist");
		}

		// get College Name
    CollegeModel cModel = new CollegeModel();
    CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
    bean.setCollegeName(collegeBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?, FIRSTNAME=?, LASTNAME=?, DOB=?, MOBILENO=?,EMAIL=?, CREATE_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(StudentBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LASTNAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME like '" + bean.getCollegeName() + "%'");
				System.out.println(sql);
			}
			if (bean.getCollegeId() > 0 ) {
				sql.append(" AND COLLEGE_ID = " + bean.getCollegeId() );
				System.out.println(sql);
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
		e.printStackTrace();
			//	throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	// **Get List of Student**//

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	// **Get List of Student with pagination**//

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");

//      ArrayList list = new ArrayList();
//      StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
//      // if page size is greater than zero then apply pagination
//      if (pageSize > 0) {
//          // Calculate start record index
//          pageNo = (pageNo - 1) * pageSize;
//          sql.append(" limit " + pageNo + "," + pageSize);
//      }
//	  

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);

		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);

			}

			rs.close();

		} catch (Exception e) {
			log.error("DataBase Exception", e);
			throw new ApplicationException("Exception : Exception in getting list of Student");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model LIst End");
		return list;
	}
	
	
	public StudentBean findBymobile(String mno) throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE mobileNo=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, mno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Email End");
		return bean;
	}


	

//    	 public List list(int pageNo, int pageSize) throws ApplicationException {
//    	        log.debug("Model list Started");
//    	        ArrayList list = new ArrayList();
//    	        StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
//    	        // if page size is greater than zero then apply pagination
//    	        if (pageSize > 0) {
//    	            // Calculate start record index
//    	            pageNo = (pageNo - 1) * pageSize;
//    	            sql.append(" limit " + pageNo + "," + pageSize);
//    	        }
//
//    	        Connection conn = null;
//
//    	        try {
//    	            conn = JDBCDataSource.getConnection();
//    	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
//    	            ResultSet rs = pstmt.executeQuery();
//    	            while (rs.next()) {
//    	            	
//				
//				  StudentBean bean = new StudentBean(); 
//				  bean.setId(rs.getLong(1));
//				  bean.setCollegeId(rs.getLong(2)); 
//				  bean.setCollegeName(rs.getString(3));
//				  bean.setFirstName(rs.getString(4));
//				  bean.setLastName(rs.getString(5));
//				  bean.setDob(rs.getDate(6)); 
//				  bean.setMobileNo(rs.getString(7));
//				  bean.setEmail(rs.getString(8)); 
//				  bean.setCreatedBy(rs.getString(9));
//				  bean.setModifiedBy(rs.getString(10));
//				  bean.setCreatedDatetime(rs.getTimestamp(11));
//				  bean.setModifiedDatetime(rs.getTimestamp(12));
//				  list.add(bean);
//				 
//    	            	
//    	            }
//    	            rs.close();
//    	        } catch (Exception e) {
//    	            log.error("Database Exception..", e);
//    	            throw new ApplicationException(
//    	                    "Exception : Exception in getting list of Student");
//    	        } finally {
//    	            JDBCDataSource.closeConnection(conn);
//    	        }
//
//    	        log.debug("Model list End");
//    	        return list;
//
//    	    }

}
