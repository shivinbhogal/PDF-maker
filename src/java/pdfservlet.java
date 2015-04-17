/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import static com.itextpdf.text.Annotation.URL;
import static com.itextpdf.text.pdf.PdfName.URL;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shivin Kulyash Bhopg
 */



@WebServlet(urlPatterns = {"/pdfservlet"})
public class pdfservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet pdfservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet pdfservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
        
    }
        

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                                ResultSet resultSet = null;
				Statement statement = null;
                            try {
                                Connection connection = ConnectionManager
						.createConnectionToDB();
				
                                String query = "SELECT * FROM employees";
                                statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
                                }
                            catch (SQLException e)
                            { e.printStackTrace();
                            }


        response.setContentType("application/pdf");
        Document document = new Document();
        Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
        Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.COURIER    , 10,
          Font.UNDERLINE);
          

           
        
        try {
            
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Phrase(new Date().toString()+"\n",font3));
              String imageUrl = "http://www.technolinks.in/images/bhel_logo.jpg";

            Image image2 = Image.getInstance(new URL(imageUrl));
            image2.scaleAbsolute(100f, 100f);
            image2.setAbsolutePosition(25f, 725f);
            document.add(image2);
            

        
            
            
            document.add(new Phrase("                                                                                                               BHEL Recruitment for Medical Professionals \n                                                              ", font3));
            document.add(new Phrase("Admit Card for Interview of Medical Professional in BHEL\n", font2));
            document.add(new Phrase("                                                 Acknowledgement No :", font3));
           
           
            document.addCreator("Shivin");
            document.addTitle("BHEL");
           
        
            resultSet.next();
            String trade_id ="100003";
            do{ 
                if(trade_id.matches(resultSet.getString("TRADE")))
                 break;
                
            }while(resultSet.next());
            
          
            
             document.add(new Phrase("Post Applied:           "+resultSet.getString("TRADE")+"\n"));
             document.add(new Phrase("Name:                   "+resultSet.getString("FIRSTNAME")+"\n"));
             document.add(new Phrase("Father's/Husband's Name:"+resultSet.getString("FATHERNAME")));
             document.add(new Phrase("Address:                "+resultSet.getString("ADDRESS1")+"\n"+resultSet.getString("ADDRESS2")+"\n"+resultSet.getString("DISTRICT")+resultSet.getString("CITY")+"\n"));
             document.add(new Phrase("State:                  "+resultSet.getString("STATE")+"\n"));
             document.add(new Phrase("Pin:                    "+resultSet.getString("PIN")+"\n"));
             document.add(new Phrase("Caste:                  "+resultSet.getString("CASTE")+"\n"));
             document.add(new Phrase("Interview Date:         "+resultSet.getString("INTERVIEW_DATE")+"\n"));
             document.add(new Phrase("Interview Time:                  "+resultSet.getString("INTERVIEW_TIME")+"\n"));
             document.add(new Phrase("Reporting Time:                  "+resultSet.getString("REPORTING_TIME")+"\n"));
             document.add(new Phrase("Interview Venue:          "+resultSet.getString("INTERVIEW_VENUE")+"\n"));
             
             document.add(new Phrase("Important Instructions: \n",font4));
             document.add(new Paragraph(
"1.You must bring along with you the original documents and 01 selfattested\n" +
"photocopy of the following :\n" +
"Xth Certificate/ Birth Certificate mentioning Date of Birth\n" +
"MBBS Degree Certificate / Marksheets of all year\n" +
"PG Diploma / PG Degree / DNB Certificate in relevant discipline\n" +
"Medical Council Registration Certificate\n" +
"Post Qualification Experience Certificate in support of the experience being claimed\n" +
"Certificate for SC/ST in the prescribed format, if applicable\n" +
"Certificate for OBC indicating Non Creamy Layer status in the prescribed format, if applicable\n" +
"Self Undertaking for Non Creamy Layer status of OBC in the prescribed format, if applicable\n" +
"Medical Certificate for Physically Handicapped candidates for their disability from Government Hospital or Medical Board, if applicable.\n" +
"Discharge Certificate for ExServicemen\n" +
"NOC from the present employer, if applicable\n" +
"2. The shortlisted candidates after attending the interviews will be reimbursed train fare to the extent of single AC 2nd class return train fare\n" +
"from the address mentioned in the Application form to Interview Centre, by the shortest route subject to submission of rail/bus Ticket\n" +
"3. Please ensure that you fulfill all the prescribed job specifications as advertised and produce all the relevant documents thereof as aforesaid (as\n" +
"applicable to you), failing which we will not be able to interview you nor reimburse the Travelling Allowance. In case it is found that a\n" +
"candidate does not possess the qualification and /or other specification as prescribed in our advertisement, their candidature will be cancelled abinitio\n" +
"and no Travelling Allowance will be paid.\n" +
"4. You have the option to answer either in English or in Hindi during the Interview. Please note that no change of Interview place and/or date of\n" +
"interview will be possible. Also, we will not be able to respond to any individual queries regarding the results of personal Interview."));
       document.add(new Phrase("Declaration by the candidate: \n",font4));       
      document.add(new Paragraph("I understand that my permission to this test interview is provisional and if it is found at the later date that I do not fulfill the essential eligibility\n" +
"criteria regarding qualification, marks, age, caste, Experience etc. as per requirement of the Company, I will be debarred abinitio\n" +
"and I will not\n" +
"be able to make any claim to the post.")) ;  
      document.add(new Phrase("DATE:",font2));
      document.add(new Phrase("\n"));
      document.add(new Phrase("PLACE:",font2));
      document.add(new Phrase("\n"));
      
      
             
             
           
            
            
          /* PdfPTable table2 = new PdfPTable(1);
           
           table2.addCell("1.");
           table2.addCell("2.");
           table2.addCell("3.");
        
           
          
            document.add(new Paragraph("                                                 \n\n\n\n            \n\n\n\n\n\n"));

            document.add(new Paragraph("             *Terms and Conditions      \n                               "));
            
            document.add(table2); 
            document.close();
            
            
            
        */
        }
        catch ( Exception e)
        { e.printStackTrace();
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
}
}
