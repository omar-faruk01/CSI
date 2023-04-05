import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Selecthome extends HttpServlet 			//class
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException
    {        
		Statement state4 = null;
		ResultSet result = null;
		String query="";        
		Connection con=null; 
        String HOMEID = request.getParameter("HOMEID");		//user input                                                   
		try
		{
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			// database URL is the unique identifier of the database on the Internet
			// thin is the oracle server
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String user = "CSIPROJECT";
			String password = "mohammed";
			con = DriverManager.getConnection(url,user, password);      	}
        catch(SQLException e)
		{	
			System.out.println("Error: "+e);	// catch exception errors from user input
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
		
		query = "Select h.HomeID, l.Address, h.Floorspace, h.Floors, h.BedRooms, h.BathRooms, h.Landsize, h.YearConstructed, l.City, l.ZipCode from HOMES JOIN Location ON h.HomeID=l.HomeID where HOMEID = '"+HOMEID+"'";   //communicates with sql
		
		out.println("<html><head><title>  The House Attribute Report</title>");	 
		out.println("</head><body>");
		
		out.print( "<br /><b><center><font color=\"white\"><H2>The House Attribute Report</H2></font>");
        out.println( "</center><br />" );
       	try 
		{ 
			result=state4.executeQuery(query);
				
	  	}
		catch (SQLException e) 
		{
			System.err.println("SQLException while executing SQL Statement."); 
		}
		out.println("<center><table border=\"1\">"); 			// Chart printout of house attributes
		out.println("<tr BGCOLOR=\"#cccccc\">");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">HOMEID</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">ADDRESS</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">FLOORSPACE</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">FLOORS</td>");
		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">BEDROOMS</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">BATHROOMS</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">LANDSIZE</td>");
		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">YEARCONSTRUCTED</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">CITY</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">ZIP</td>");
		out.println("</tr>");
		try 
		{ 
            while(result.next()) 			//while loop that loops through sql chart line after line
			{ 
		    		out.println("<tr>");
                    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
                    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
					out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(5)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(6)+"</td>");
                    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(7)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(8)+"</td>");
					out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(9)+"</td>");
		    		out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(10)+"</td>");
                    out.println("</tr>");              		
			} 
	    }
		catch (SQLException e) 
		{
			System.out.println("Resultset is not connected"); 
		}

		out.println("</table></CENTER>");		//centers table output
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
