/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
      
        
        
    }


    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
         PrintWriter out = res.getWriter();
        
         res.setContentType("text/html;charset=UTF-8");
         Database db=new Database();
         db.connect();
         String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        Message message=new Message();
        try {
         message = db.login(email, pass);
        if(message.exists)
        {
           Cookie cookie=new Cookie("name",message.Name);
       
           res.addCookie(cookie);
        }
        else{
            RequestDispatcher rd=req.getRequestDispatcher("login.html");
            rd.include(req, res);
            out.print("User Does Not Exist");
            return;
        }
        
        } catch (SQLException ex) {
            message.message=ex.toString();
        }
        RequestDispatcher rd=req.getRequestDispatcher("index.html");
        rd.include(req, res);
        out.println("Welcome: "+message.Name);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       //  process
       
          Cookie  co[]=req.getCookies();
          for(int i=0;i<co.length;i++)
          {
             co[i].setMaxAge(0);
             
              RequestDispatcher rd=req.getRequestDispatcher("login.html");
            rd.include(req, res);
          }
        }
    }

  
    
    
    

 
   

