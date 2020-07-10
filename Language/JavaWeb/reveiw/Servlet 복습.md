# Servlet 복습

### Service 사용한 GET, POST호출

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost3")
//Service example
public class GetPostServlet3 extends HttpServlet{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String method = request.getMethod();
        if(method.equals("POST"))
            request.setCharacterEncoding("utf-8");
        out.print("<h2>요청 방식 : "+method+"</h2>");
        out.print("<h2>쿼리 문자열 : "+request.getParameter("name")+"</h2>");
        out.close();
        System.out.prinln(method+" 방식 수행")
    }
}



/////////////위 자료에 대한 html//////////////////////
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>ABOUT TITLE</title>
    </head>
    <body>
    <h1>The ways how to call the servlet</h1>
    <hr>
    <a href="/sedu/getpost?name=DUKE">USING HYPERLINK</a>
    <hr>
    <form method="get" action="/sedu/getpost">
    <input type="text" placeholder="Fill your name.." name="name">
    <input type="submit" value="GET TYPE CALLING">
    </form>
    <hr>
    <form method="post" action="/sedu/getpost">
    <input type="text" placeholder="Fill your name.." name="name">
    <input type="submid" value="POST TYPE CALLING">
    </form>
    </body>
    </html>
```





### ReservationServlet, html LocalDate Using

```java
package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax. servlet.http.HttpServletResponse;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletExcetpion, IOException{
        response.setContentType("text/html; charser=utf8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String rType = request.getParameter("rType");
        String[] reQ = request.getParameter("request");
        
        String date = request.getParameter("date");//date === String
        LocalDate newD = LocalDate.parse(date);//change String to Date
        DateTimeFormatter nD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date2 = newD.format(nd);
        
        if(pwd.equals("7777")){
            out.print("<h1>"+name+" \'s Reservation</h1><hr>");
            out.print("<ul><li>ROOM TYPE : "+rType+"</li>");
            out.print("<li>REQUEST : "+);
            if(reQ==null){
                out.print("NO REQUEST FOR THE GUEST");
            }else {
                for(int i=0;i<reQ.length;++i){
                    if (i==reQ.length-1){
                        out.print(reQ[i]);
                        break;
                    }
                    out.print(reQ[i])+", ";
                }
            }
            out.print("</li>");
            out.print("<li>DATE : "+date2+"</li>");
            out.print("</ul><hr>");
            out.print("<a href=''>RETURN TO FORM</a>");
        }else{out.print("PASSWORD IS NOT ACCEPTED!")}
        out.close();
    }
}


/////////HTML//////////////////

<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>HOTEL RESERVATION SERVICE</title>
    <style>
    </style>
    </head>
    <body>
    <div id="form">
    <form action="/sedu/reservation" method="POST">
    <h2>RESERVATION SERVICE</h2>
    <hr>
    Name : <input type="text" required name="name"><br>
    Password : <input type="password" required name="pwd"><br>
    
    </div>
    </body>
    </html>
```

