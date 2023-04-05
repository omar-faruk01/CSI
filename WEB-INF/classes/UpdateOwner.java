// Paul Pietrzyk
// Java MCS3603
// Assignment 6
// Nov 8, 2008

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UpdateOwner extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
			Statement state4 = null;
			ResultSet result = null;
			String query="";        
			Connection con=null; 

			String OwnerID = request.getParameter("OWNERID");
			String Profession = request.getParameter("PROFESSION");

		try
		{
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			// database URL is the unique identifier of the database on the Internet
			// thin is the oracle server
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String user = "CSIPROJECT";
			String password = "mohammed";
			con = DriverManager.getConnection(url,user, password);
		}
        catch(SQLException e)
		{	
			System.out.println("Error: "+e);
			
			
			
		}
		catch(Exception e) 
		{
			System.err.println("Exception while loading  driver");		
		}
	    try 
		{
        	state4 = con.createStatement();
		} 
		catch (SQLException e) 	
		{
			System.err.println("SQLException while creating statement");			
		}
		
		response.setContentType("text/html");
		PrintWriter out = null ;
		try
		{
			out =  response.getWriter();
		}
		catch (IOException e) 
		{
  			e.printStackTrace();
		}
		
		query = "update Owner set OwnerID = '"+OwnerID+"' where Profession  = '"+Profession+"'";											
      
		
		out.println("<html><head><title>  Record has been updated</title>");	 
		out.println("</head><body>");
		
		
		out.print( "<br /><b><center><font color=\"RED\"><H2>The following record has been updated in the database:</H2></font>");
		
        out.print( Profession );
		out.print( OwnerID );
		
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		
		try 
		{ 
   			result.close(); 
			state4.close(); 	
			con.close();
    		System.out.println("Connection is closed successfully.");
 	   	}
		catch (SQLException e) 
		{
			e.printStackTrace();	
		}

  		out.println("</body></html>");
    } 
}
