package ekptg.engine;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ADTest {

 public static void main(String[] args) {
	 String username = null;
	 String LDAPusername = null;
	 String password = null;
	 String LDAPServer = null;
	 try {
		 LDAPServer = "10.19.135.245:389";
		 //135,137,139,445
		//username = "ekptgadmin@ekptgdomain.org";
		//password = "eKPTG2009";
		 //username = "AreJae@ekptgdomain.org";
		 //password = "Azam2009";
		 //String username = System.getProperty("user.name");
		 username = "arejae";
		 LDAPusername = username+"@ekptgdomain.org";
		 password = "Jkptg 2009";
		//Need to figure out on how to implement LDAP Cache-In case AD in HQ down.
	    Hashtable env = new Hashtable();
	    env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	    env.put(Context.PROVIDER_URL,"LDAP://"+LDAPServer+""); //replace with your server URL/IP
	    //env.put(Context.SECURITY_AUTHENTICATION,"DIGEST-MD5"); //
	    env.put(Context.SECURITY_AUTHENTICATION,"SIMPLE");
	    env.put(Context.SECURITY_PRINCIPAL,username); 
	    env.put(Context.SECURITY_CREDENTIALS, password); 
	    DirContext ctx = new InitialDirContext(env);
	    ctx.close();
	  } catch (AuthenticationException authEx) {
		  System.out.println("Authentication failed!");
		  System.out.println(authEx.getMessage());
		  return;
	  } catch(NamingException ne) {
		System.out.println("Error authenticating user:"+LDAPusername);
	    System.out.println(ne.getMessage());
	    return;
	  }
  //if no exception, the user is already authenticated.
  System.out.println("OK, successfully authenticating user "+LDAPusername);
}
 

}
