//
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddAgent extends HttpServlet 
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

    String AgentID = request.getParameter("AgentID");
    String AgentName = request.getParameter("AgentName");
    String AgentEmail = request.getParameter("AgentEmail");
    

    try 
	{
      if (AgentID.length() == 0 || AgentName.length() == 0) {
        out.println("Please: Agent ID and Name are required");
        return; 
    }
    storeAgent(AgentID, AgentName, AgentEmail);
	out.println("<html><head><title>Agent Registeration Report</title>");	 
	out.print( "<br /><b><center><font color=\"RED\"><H2>Agent Registeration Report</H2></font>");
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
		
    out.println(AgentID + " " + AgentName +
        " is now added to the Agent table");
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
		String user = "SYSTEM";
		String password = "password";  
		Connection conn = DriverManager.getConnection(url,user, password);  
		pstmt = conn.prepareStatement("insert into AGENT " + "(AgentID, AgentName, AgentEmail) values (?, ?, ?)");
    }
    catch (Exception ex) 
	{
      ex.printStackTrace();
    }
  }

  
 private void storeAgent(String AgentID, String AgentName, String AgentEmail) throws SQLException {
     pstmt.setString(1, AgentID);
     pstmt.setString(2, AgentName);
     pstmt.setString(3, AgentEmail);
     pstmt.executeUpdate();

 }};