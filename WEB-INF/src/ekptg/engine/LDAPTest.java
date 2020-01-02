package ekptg.engine;

import java.util.Properties;

import org.apache.log4j.Logger;

public class LDAPTest {
	static Logger myLogger = Logger.getLogger(LDAPTest.class);
	
    public static void main(String args[]) {
        String s = System.getenv("USER");
        String localuser = System.getProperty("localuser");
        String username = System.getProperty("user.name");
        myLogger.info("USER:"+s);
        myLogger.info("localuser:"+localuser);
        myLogger.info("username:"+username);
        Properties p = System.getProperties();
        p.list(System.out);

    }
}
