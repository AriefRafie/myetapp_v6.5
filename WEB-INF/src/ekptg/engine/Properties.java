package ekptg.engine;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class Properties
{
  private static Properties instance = null;
  private static Hashtable<String, String> titles = new Hashtable();
  private static String propertiesName = "dbconnection";
  private ResourceBundle rb;

  private Properties()
  {
    prepareLabels();
  }

  public static Properties getInstance(String name) {
    if (name.equals(propertiesName)) {
      if (instance == null) instance = new Properties();
    } else {
      propertiesName = name;
      instance = new Properties();
    }
    return instance;
  }

  public static Properties getInstance() {
    if (instance == null) instance = new Properties();
    return instance;
  }

  public static Properties reload() {
    instance = new Properties();
    return instance;
  }

  private void prepareLabels() {
    this.rb = ResourceBundle.getBundle(propertiesName);
    for (Enumeration keys = this.rb.getKeys(); keys.hasMoreElements(); ) {
      String key = (String)keys.nextElement();
      String value = this.rb.getString(key);
      titles.put(key, value);
    }
  }

  public static String getTitle(String name) {
	  Properties labels = getInstance("labels");
	  return ((titles.get(name) != null) ? (String)titles.get(name) : "");
  }

  public Hashtable getTitles()
  {
    return titles;
  }

  public static void main(String[] args) throws Exception {
    System.out.println(getTitle("password"));
    System.out.println(getTitle("username"));
  }
}