package lebah.portal.db;

import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

// Referenced classes of package lebah.portal.db:
//            AuthenticateUser

public class UserLogger
{

    static Logger myLogger = Logger.getLogger(UserLogger.class);

    public UserLogger()
    {
    }

    public static void save(HttpServletRequest req, String username) throws Exception
    {
        String remoteAddr;
        int year;
        int month;
        int day;
        String logString;
        Db db;
        //remoteAddr = req.getRemoteAddr();
        
        remoteAddr =  req.getRemoteAddr();
		if (req != null) {
            remoteAddr = req.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = req.getRemoteAddr();
            }
        }
        
        Calendar cal = new GregorianCalendar();
        year = cal.get(1);
        month = cal.get(2) + 1;
        day = cal.get(5);
        int hour12 = cal.get(10);
        int min = cal.get(12);
        int sec = cal.get(13);
        String ampm = cal.get(9) != 0 ? "PM" : "AM";
        logString = (new StringBuilder("[")).append(remoteAddr).append("] - ").append(year).append("/").append(month).append("/").append(day).append(" ").append(hour12).append(":").append(min).append(" ").append(ampm).toString();
        logString = (new StringBuilder(String.valueOf(logString))).append(" ").append(username).toString();
        db = null;
        try
        {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.add("remote_add", remoteAddr);
            r.add("log_string", logString);
            r.add("user_name", username);
            r.add("log_year", year);
            r.add("log_month", month);
            r.add("log_day", day);
            r.add("log_date", r.unquote("sysdate"));
            String sql = r.getSQLInsert("web_logger");
            myLogger.debug(" SAVE web_logger : "+sql);
            stmt.executeUpdate(sql);
        }
        catch (Exception re) {
    		throw re;
    	}finally {
    		if (db != null)
    			db.close();
    	}
        /*
        catch(Exception exception)
        {
            if(db != null)
            {
                db.close();
            }
            break MISSING_BLOCK_LABEL_355;
        }
        break MISSING_BLOCK_LABEL_345;
        Exception exception1;
        exception1;
        if(db != null)
        {
            db.close();
        }
        throw exception1;
        if(db != null)
        {
            db.close();
        }
        */
    }

}
