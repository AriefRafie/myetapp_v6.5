package ekptg.helpers;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NsLookup
{

  public void resolve(String host)
  {
    try
    {
      InetAddress inetAddress = InetAddress.getByName(host);

      System.out.println("Host: " +
          inetAddress.getHostName());
      System.out.println("IP Address: " +
          inetAddress.getHostAddress());
    }
    catch (UnknownHostException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    new NsLookup().resolve(args[0]);
  }

}

