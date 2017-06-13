import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Compile extends HttpServlet {

    public Compile() 
{

 }
    public void service(HttpServletRequest req , HttpServletResponse res)throws ServletException , IOException{
       res.setContentType("text/html");
       String path = req.getServletContext().getRealPath("/");
       PrintWriter out=res.getWriter();
       String filename = req.getParameter("className")+".java";
       FileOutputStream fout = new FileOutputStream(new File(path+"//Files//"+filename));
       byte b[] = req.getParameter("code").getBytes();
       fout.write(b);
       String command ="javac -d "+path+"\\Files\\classes\\"+path+"\\Files\\"+filename; 
Process error = Runtime.getRuntime().exec(command);
BufferedReader err = new BufferedReader(new InputStreamReader(error.getErrorStream()));
String result="";
while(true)
{
		        	
String temp = err.readLine();
		      if(temp!=null){
		       result+=temp;
		        result+="\n";
		        	}
		        	else break;
		        }
		        if(result.equals("")){
		           out.println("Compiled Sucessfully");
		           err.close();
		        }
		
          out.println(result);
      
    }
    
}