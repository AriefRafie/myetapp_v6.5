package ekptg.engine;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class ContextListener
  implements ServletContextListener, ServletContextAttributeListener{
	
  private ServletContext context = null;
  private static AppWatcher watcher = null;

  public void contextInitialized(ServletContextEvent event){
    this.context = event.getServletContext();
    System.out.println("*********************************");
    System.out.println("** MyeTaPP Engine is ready....welcome...**");
    System.out.println("*********************************");

    //String realPath = this.context.getRealPath("/");
    //PropertyConfigurator.configure(realPath + "WEB-INF/classes/dbconnection.properties");
    //runAppWatcher();
  
  }

  public void contextDestroyed(ServletContextEvent event) {
	  System.out.println("MyeTaPP Engine is closing... good bye...");
	  stopAppWatcher();
	  this.context = null;
    
  }

  public void attributeAdded(ServletContextAttributeEvent event){ }

  public void attributeRemoved(ServletContextAttributeEvent event){ }

  public void attributeReplaced(ServletContextAttributeEvent event){ }

  void runAppWatcher(){
	  watcher = AppWatcher.getInstance();
	  watcher.start();
    
  }

  void stopAppWatcher(){
	  watcher.stop();
  }
  
  
}
