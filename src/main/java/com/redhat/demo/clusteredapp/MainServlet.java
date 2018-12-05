package com.redhat.demo.clusteredapp;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/")
public class MainServlet extends HttpServlet {


    @Inject
    SessionCounter sessionCounter;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
    	/* Pool of input character choices */
    	String inputCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\n";
    	
    	
    	/* Length of the generated string : 1M */
    	int stringLength = 1000000;

    	String message = generateRandomString(inputCharacters, stringLength);
    	
    	/* If you were using JSP which we should. Leaving the comments here for future code changes */
    	//request.setAttribute("message", message); 
        //request.getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
        
    	
    	/* This is a 1999 way of generating HTML inside a servlet */
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<title>Sample Java Application</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h1>This is an example workload with a randomly generated string (1M characters)</h1>");
        out.println("<h3>Generated string is: </h3>" + message);
        out.println("</body></html>");
    
    }
    
    /** Generate a random string of the specified length */
    public static String generateRandomString(String inputCharacters, int length) {
    	
        StringBuilder stringBuilder = new StringBuilder();
        
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
        	stringBuilder.append(inputCharacters.charAt(random.nextInt(inputCharacters
                    .length())));
        }

        return stringBuilder.toString();
    }
}
