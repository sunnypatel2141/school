package org.sunny.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.jni.Time;

public class CoursesDAO {
	
	private final Connection connection;

	private final List<String> listOfCourses;

	//course code(8), year start(4), year end(4), semester(1)
	private static final String ADD_COURSES = "INSERT INTO COURSES VALUES (?, ?, ?, ?)";
	
	public CoursesDAO(Connection connection)
	{
		this.connection = connection;
		this.listOfCourses = new ArrayList<String>();
	}
	
	public List<String> getList()
	{
		return Collections.unmodifiableList(listOfCourses);
	}
	
	public void addCourse(String course, int startYear, int endYear, String semester) throws SQLException
	{	
		// Statements allow to issue SQL queries to the database
		PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSES);
        
		preparedStatement.setString(1, course);
		preparedStatement.setInt(2, startYear);
		preparedStatement.setInt(3, endYear);
		preparedStatement.setString(4, semester);
         
		preparedStatement.executeUpdate();
	}
}
