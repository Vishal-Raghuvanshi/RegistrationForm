package com.jspiders.webapp.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class MyjdbcServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out=resp.getWriter();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		
		try {
			Driver driverref=new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/beja6?user=root&password=root";
			 con=DriverManager.getConnection(dburl);
			
			String query=" select * from studentsinfo ";
			 stmt=con.createStatement();
			 rs=stmt.executeQuery(query);
			 
			 out.print("<html>" + 
						"<body>" + 
						"<Table border =\"1px\" bordercolor=\"black\" bgcolor=\"yellow\"" + 
						"align=\"center\" cellspacing=\"1px\" cellpadding=\"10px\" rules=\"all\">" + 
						" <tr bgcolor=\"red\">" + 
						"      <th>regno</th>" + 
						"	  <th>firstname</th>" + 
						"	  <th>middlename</th>" + 
						"	  <th>lastname</th>"); 
			
			while(rs.next()) {
				
				
						
                        int regnum=rs.getInt("regno");
						String fName=rs.getString("firstname");
						String mName=rs.getString("middlename");
						String lName=rs.getString("lastname");
						
						out.print("<tr> <td>"+regnum+"</td>" + 
						"	  <td>"+fName+"</td>" + 
						"	  <td>"+mName+"</td>" + 
						"	  <td>"+lName+"</td></tr>");
					 
						
				
				out.println("Regno= "+regnum);
				out.println("Firstname=" +fName);
				out.println("Middlename=" +mName);
				out.println("Lastname =" +lName);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
		 
				try {
					if (con!=null)
					{
					con.close();
					}
					if (stmt!=null)
					{
						stmt.close();
					}
					if (rs!=null)
					{
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		    }
		}


