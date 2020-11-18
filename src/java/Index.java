/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mehraj Bhat
 */
@WebServlet(urlPatterns = {"/Index"})
public class Index extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       // super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
    PrintWriter out = res.getWriter();
   
           Cookie  co[]=req.getCookies();
           try{
           if(req.getCookies().length==0)
           {
            RequestDispatcher rd=req.getRequestDispatcher("login.html");
            rd.include(req, res);
            return;
           }
           
             String value=co[0].getValue();
             RequestDispatcher rd=req.getRequestDispatcher("index.html");
            rd.include(req, res);
            //out.print("User Does Not Exist");
             out.println("YOU ARE LOGGED IN AS: "+value);
           }
            catch(Exception e)
            {
            
            }
    
    
    }

   
}
