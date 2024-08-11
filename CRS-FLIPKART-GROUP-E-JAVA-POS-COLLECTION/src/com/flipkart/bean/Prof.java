package com.flipkart.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author JEDI-03
 * Class to store Professor information, inheriting from User
 * 
 */
public class Prof extends User {

    private String dept;
    private String qualification;
    //private Map<Course, Set<Student>> courseStudentMap; // Map of courses to enrolled students
    private Map<String,Course> idCourseMap;
    /**
     * Parameterized constructor
     * @param ID: the professor ID
     * @param name: the professor's name
     * @param contact: the professor's contact information
     * @param email: the professor's email
     * @param dept: department of the professor
     * @param qualification: qualification of the professor
     */
    public Prof(String ID, String name, String contact, String email, String dept, String qualification, String password) {
        super(ID, name, "Professor", contact, email, password);
        this.dept = dept;
        this.qualification = qualification;
        this.idCourseMap=new HashMap<>();
    }

    // Getters and Setters for dept and qualification
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    public Set<Student> getRegisteredStudents(String courseID) {
    	for (Map.Entry<String, Course> entry : idCourseMap.entrySet()) {
            String cid = entry.getKey();
            Set<Student> students = entry.getValue().getStudents();
            if(cid.equals(courseID)) {
            	return students;
            }
        }return null;
    }
    
    @Override
    public void update() {
        // Code to update professor information
    }

    @Override
    public void changePassword(String password) {
        // Code to change professor password
    	super.setPassword(password);
    }
    
    public void addCourse(Course course) {
    	idCourseMap.put(course.getCourseID(), course);
    }

    /*@Override
    public void login() {
        // Code for professor login
    }*/

}
