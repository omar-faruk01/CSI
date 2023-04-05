//
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddCompany extends HttpServlet 
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

    String COMPANYID = request.getParameter("COMPANYID");
    String COMPANYNAME = request.getParameter("COMPANYNAME");
    String COMMISSIONRATE = request.getParameter("COMMISSIONRATE");
    String OFFICECITY = request.getParameter("OFFICECITY");

    try 
	{
      if (COMPANYID.length() == 0 || COMPANYNAME.length() == 0) {
        out.println("Please: Company ID and Name are required");
        return; 
    }
    storeCompany(COMPANYID, COMPANYNAME, COMMISSIONRATE, OFFICECITY);
	out.println("<html><head><title>Company Registeration Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Company Registeration Report</H2></font>");
  out.println( "</center><br />" );
	out.println("</table></center>");
		
    out.println(COMPANYID + " " + COMPANYNAME +
        " is now added to the Company table");
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
		pstmt = conn.prepareStatement("insert into COMPANY " + "(COMPANYID, COMPANYNAME, COMMISSIONRATE, OFFICECITY) values (?, ?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
 private void storeCompany(String COMPANYID, String COMPANYNAME, String COMMISSIONRATE, String OFFICECITY) throws SQLException {
        pstmt.setString(1, COMPANYID);
        pstmt.setString(2, COMPANYNAME);
        pstmt.setString(3, COMMISSIONRATE);
        pstmt.setString(4, OFFICECITY);
        pstmt.executeUpdate();
    
}};
