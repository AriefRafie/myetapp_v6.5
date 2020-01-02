package training7;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;


public class TestFunction6 extends AjaxModule {
	private final String PATH="training7";
    private String vm = PATH +"/test10.jsp";
    HttpSession session = null;
    String action = null;
    
        
    public String doAction() throws Exception{
    	//get value
    	String command = getParam("command");
    	System.out.println("-------COMMAND:::"+command);
    	
    	context.put("value","");
    	String t1=getParam("t1");
		String t2=getParam("t2");
		context.put("t1",t1);
		context.put("t2",t2);
		
    	if(command.equals("Panggilskrin"))
    	{  	
    		Integer tm = Integer.parseInt(t1);
			context.put("t2",tm);
			return vm = PATH + "/skrin1.jsp";
    	}
    	
    	else if(command.equals("tutup_Panggilskrin"))
    	{  	
    		
			return vm = PATH + "/kosong2.jsp";
    	}
    
    	
    	 return vm;  
    }

}
