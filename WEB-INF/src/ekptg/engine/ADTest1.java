package ekptg.engine;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ADTest1 {
    public static void main(String[] args) {

      String query = "ekptgadmin@ekptgdomain.org";
      String attribute = "xxx";
      StringBuffer output = new StringBuffer();

      try {
          //String url = "ldap://directory.cornell.edu/o=Cornell%20University,c=US";
      	String url = "LDAP://10.19.135.245:389";
          Hashtable env = new Hashtable();
          env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
          env.put(Context.PROVIDER_URL, url);
          DirContext context = new InitialDirContext(env);

          SearchControls ctrl = new SearchControls();
          ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
          NamingEnumeration enumeration = context.search("", query, ctrl);
          while (enumeration.hasMore()) {
              SearchResult result = (SearchResult) enumeration.next();
              Attributes attribs = result.getAttributes();
              NamingEnumeration values = ((BasicAttribute) attribs.get(attribute)).getAll();
              while (values.hasMore()) {
                if (output.length() > 0) {
                  output.append("|");
                }
                output.append(values.next().toString());
              }
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
      System.out.print(output.toString());
  }

  public ADTest1() {}
}
