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

    String SSN = request.getParameter("SSN");
    String OWNERNAME = request.getParameter("OWNERNAME");
    String DEPENDENTS = request.getParameter("DEPENDENTS");
    String INCOME = request.getParameter("INCOME");
    String AGE = request.getParameter("AGE");
    String PROFESSION = request.getParameter("PROFESSION");

    try 
	{
      if (SSN.length() == 0 || OWNERNAME.length() == 0) {
        out.println("Please: SSN and Name are required");
        return; 
    }
    storeOwner(SSN, OWNERNAME, DEPENDENTS, INCOME, AGE, PROFESSION);
	out.println("<html><head><title>Owner Registeration Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Owner Registeration Report</H2></font>");
  out.println( "</center><br />" );
	out.println("</table></center>");
		
    out.println(SSN + " " + OWNERNAME +
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
		pstmt = conn.prepareStatement("insert into OWNER " + "(SSN, OWNERNAME, DEPENDENTS, INCOME, AGE, PROFESSION) values (?, ?, ?, ?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
 private void storeOwner(String SSN, String OWNERNAME, String DEPENDENTS, String INCOME, String AGE, String PROFESSION) throws SQLException {
        pstmt.setString(1, SSN);
        pstmt.setString(2, OWNERNAME);
        pstmt.setString(3, DEPENDENTS);
        pstmt.setString(4, INCOME);
        pstmt.setString(5, AGE);
        pstmt.setString(6, PROFESSION);
        pstmt.executeUpdate();
    
}};
