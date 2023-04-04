//
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddOwner extends HttpServlet 
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

    String OWNERID = request.getParameter("OWNERID");
    String NAME = request.getParameter("NAME");
    String SSN = request.getParameter("SSN");
    String PROFESSION = request.getParameter("PROFESSION");
    String INCOME = request.getParameter("INCOME");
    

    try 
	{
      if (OWNERID.length() == 0 || NAME.length() == 0) {
        out.println("Please: OwnerID and Name are required");
        return; 
    }
    storeOwner(OWNERID, NAME, SSN, PROFESSION, INCOME);
	out.println("<html><head><title>Owner Registeration Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Owner Registeration Report</H2></font>");
  out.println( "</center><br />" );
	out.println("</table></center>");
		
    out.println(OWNERID + " " + NAME +
        " is now added to the Owner table");
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
		pstmt = conn.prepareStatement("insert into OWNER " + "(OWNERID, NAME, SSN, PROFESSION, INCOME) values (?, ?, ?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
 private void storeOwner(String OWNERID, String NAME, String SSN, String PROFESSION, String INCOME) throws SQLException {
        pstmt.setString(1, OWNERID);
        pstmt.setString(2, NAME);
        pstmt.setString(3, SSN);
        pstmt.setString(4, PROFESSION);
        pstmt.setString(5, INCOME);
        pstmt.executeUpdate();
    
}
