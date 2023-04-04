//
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddNewHome extends HttpServlet 
{
  private PreparedStatement pstmt;
  public void init() throws ServletException {
    initializeJdbc();
  }
  public void doPost(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException  
 {
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String HOMEID = request.getParameter("HOMEID");
    String ADDRESS = request.getParameter("ADDRESS");
    String FLOORSPACE = request.getParameter("FLOORSPACE");
    String FLOORS = request.getParameter("FLOORS");
    String BEDROOMS= request.getParameter("BEDROOMS");
    String BATHROOMS = request.getParameter("BATHROOMS");
    String LANDSIZE = request.getParameter("LANDSIZE");
    String YEARCONSTRUCTED = request.getParameter("YEARCONSTRUCTED");

    try 
	{
      if (HOMEID.length() == 0 || ADDRESS.length() == 0) {
        out.println("Please: Home ID and Address are required");
        return; 
    }
    storeHome(HOMEID, FLOORSPACE, FLOORS, BEDROOMS,
	BATHROOMS, LANDSIZE, YEARCONSTRUCTED);
    storeLocation(HOMEID, HOMEID, ADDRESS);
	out.println("<html><head><title>Homes Registeration Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Homes Registeration Report</H2></font>");
    out.println( "</center><br />" );
	/*
	out.println("</head><body>");
	out.println("<center><table border=\"1\">"); 
	out.println("<tr BGCOLOR=\"#cccccc\">");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">HOME ID</td>");
    out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">ADDRESS</td>");
    out.println("</tr>");
	*/
	out.println("</table></center>");
		
    out.println(HOMEID + " " + ADDRESS +
        " is now added to the Homes table");
	out.println("</body></html>");
    }
    catch(Exception ex) 
	{
      out.println("\n Error: " + ex.getMessage());
    }
    finally 
	{
      out.close(); 
    }
 } 
  private void initializeJdbc() 
  {
    try 
	{
        String driver = "oracle.jdbc.driver.OracleDriver";  
        Class.forName(driver);
		// database URL is the unique identifier of the database on the Internet
		// thin is the oracle server
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "CSIPROJECT";
		String password = "mohammed";  
		Connection conn = DriverManager.getConnection(url,user, password);  
		pstmt = conn.prepareStatement("insert into homes " +
        "(HOMEID, FLOORSPACE, FLOORS, BEDROOMS, BATHROOMS,"
         + "LANDSIZE, YEARCONSTRUCTED) values (?, ?, ?, ?, ?, ?, ?)");
         pstmt1 = conn.prepareStatement("insert into location " +
        "(LocationID, HOMEID, ADDRESS) values (?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
  private void storeHome(String HOMEID,
      String FLOORSPACE, String FLOORS, String BEDROOMS, String BATHROOMS,
      String LANDSIZE, String YEARCONSTRUCTED) throws SQLException 
 {
    pstmt.setString(1, HOMEID);
    pstmt.setString(2, FLOORSPACE);
    pstmt.setString(3, FLOORS);
    pstmt.setString(4, BEDROOMS);
    pstmt.setString(5, BATHROOMS);
    pstmt.setString(6, LANDSIZE);
    pstmt.setString(7, YEARCONSTRUCTED);
    pstmt.executeUpdate();
 }

 private void storeLocation(String LocationID, String HOMEID,
      String ADDRESS) throws SQLException 
 {
    pstmt1.setString(1, LocationID);
    pstmt1.setString(2, HomeID);
    pstmt1.setString(3, ADDRESS);
    pstmt1.executeUpdate();
 }
}
