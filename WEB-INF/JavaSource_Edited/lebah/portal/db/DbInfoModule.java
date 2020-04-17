package lebah.portal.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.engine.StateLookup;
import ekptg.helpers.Utils;

public class DbInfoModule extends VTemplate
{

    static Logger myLogger = Logger.getLogger(DbInfoModule.class);

    public DbInfoModule()
    {
    }

    public Template doTemplate()
        throws Exception
    {
        String statecode;
        Db db;
        HttpSession session = request.getSession();
        StateLookup.getInstance();
        statecode = StateLookup.getTitle("StateCode");
        db = null;
        String sql = "";
        db = new Db();
        sql = "select instance_name,host_name,startup_time from v$instance";
        Statement stmt = db.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next())
        {
            context.put("instance_name", rs.getString("instance_name"));
            context.put("host_name", rs.getString("host_name"));
            context.put("startup_time", rs.getString("startup_time"));
        }
        context.put("instance_ip", (new StringBuilder()).append(context.get("host_name")).append("-").append(Utils.getIpAddressByHost((String)context.get("host_name"))).toString());
        context.put("dbUser", db.toString());
        context.put("dbUrl", db.getConnectionURL());
        Properties sysprops = System.getProperties();
        context.put("catalina", sysprops.getProperty("catalina.home"));
        context.put("AppServer", sysprops.getProperty("AppsServer.Name"));
        context.put("StateCode", statecode);
       
        /*
        break MISSING_BLOCK_LABEL_271;
        Exception exception;
        exception;
        */
        if(db != null)
        {
            db.close();
        }
        /*
        throw exception;
        if(db != null)
        {
            db.close();
        }
        */
        Template template = engine.getTemplate("vtl/main/dbinfo.vm");
        return template;
    }

}
