package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flipkart.bean.Course;
import com.flipkart.bean.Prof;
import com.flipkart.utils.DBQueries;
import com.flipkart.utils.DBUtil;

public class AdminDaoServices implements AdminDaoInterface{

	public static Connection conn = DBUtil.getConnection();
	
	@Override
	public String addProf(Prof prof, String username) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conn.prepareStatement(DBQueries.FETCH_IDS);
			ResultSet rs=ps.executeQuery();
			List<String> userIds=new ArrayList<String>();
			while(rs.next())
			{
				userIds.add(rs.getString("userID"));
			}
			Random rand = new Random();
			String userID;
			while(true)
			{
				long id = 10000000 + rand.nextInt(10000000);
				userID =  "PROFESSOR" + Long.toString(id);
				if(!userIds.contains(userID))
					break;
			}
			PreparedStatement ps1 = conn.prepareStatement(DBQueries.ADD_USER);
			ps1.setString(1, userID);
			ps1.setString(2, prof.getName());
			ps1.setString(3, "Professor");
			ps1.setString(4, prof.getContact());
			ps1.setString(5, prof.getEmail());
			ps1.setString(6, prof.getPassword());
			ps1.setString(7, username);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement(DBQueries.ADD_PROFESSOR);
            ps2.setString(1, userID);
            ps2.setString(2, prof.getDept());
            ps2.setString(3, prof.getQualification());

            if(ps2.executeUpdate() == 1)
            	return userID;

        } catch (SQLException e) {
        	return null;
        }
        return null;
	}

	@Override
	public boolean removeProf(String profID) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement ps = conn.prepareStatement(DBQueries.REMOVE_USER);
            ps.setString(1, profID);
            if(ps.executeUpdate() == 1)
            {
            	ps = conn.prepareStatement(DBQueries.REMOVE_PROFESSOR);
            	ps.setString(1, profID);
            	if(ps.executeUpdate() == 1)
            		return true;
            }
            else {
            	return false;
            }
        }catch (SQLException e) {
        	return false;
        }
		return false;
	}

	@Override
	public boolean updateCourse(String courseID, Course updatedCourse) {
		// TODO Auto-generated method stub
		if(!this.removeCourse(courseID))return false;
		if(!this.addCourse(updatedCourse))return false;
		return true;
	}

	@Override
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement ps = conn.prepareStatement(DBQueries.ADD_COURSE);
            ps.setString(1, course.getCourseID());
            ps.setString(2, course.getCourseName());
            //ps.setString(3, course.getCourseProf());
            ps.setInt(3, course.getSeats());
            ps.setFloat(4, course.getPrice());

            int rs=ps.executeUpdate();
            if(rs == 1)
            	return true;

        } catch (SQLException e) {
        	return false;
        }
        return false;
		
	}

	@Override
	public boolean removeCourse(String courseID) {
		try {
            PreparedStatement ps = conn.prepareStatement(DBQueries.REMOVE_COURSE);
            ps.setString(1, courseID);
            if(ps.executeUpdate() == 1)
            	return true;
            else
            	return false;

        } catch (SQLException e) {
        	return false;
        }
	}

	@Override
	public boolean registerStudent(String studentID) {
		try {
            PreparedStatement ps = conn.prepareStatement(DBQueries.APPROVE_REGISTRATION);
            ps.setString(1, studentID);
            if(ps.executeUpdate() == 1)
            	return true;
            else
            	return false;

        } catch (SQLException e) {
        	return false;
        }
	}

}
