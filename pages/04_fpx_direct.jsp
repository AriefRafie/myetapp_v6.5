<!-- 
# Name        : Sample Merchant Page - JSP Page 4 - FPX Direct Page
# Author      : Aqilah Dayana Tumian - AD MEPS
				Copyright (c) 2009 MEPS(1997) Sdn.Bhd.
	 			8th Floor, Menara Weld,
	 			Jalan Raja Chulan,
	 			50200 Kuala Lumpur
	 			All rights reserved.
# Version     : 2.0
# Requirement : Merchant will need to edit the fpxPluginPort with unique number which match to daemon port number in plugin.xml . 

# These sample pages are provided for information purposes only. It does not imply any recommendation or endorsement by anyone.
  These sample pages are provided for FREE, and no additional support will be provided for these sample pages. 
  There is no warranty and no additional document. USE AT YOUR OWN RISK.
-->
<%@ page import="java.io.*,java.util.*,java.net.*,java.text.*"%>
<%
		
	String fpxPluginIP = "127.0.0.1";
	//Merchant will need to edit the fpxPluginPort with unique number which match to daemon port number in plugin.xml .
	int fpxPluginPort = 6000;
	
	String resp=null; 
	String strmesgFromFpx=null;
	String msgFromPlugin=null;		
	String msgResult[];	
	String txnResult="";
	int bufferChar;		
		
		try
			{	
				// Start verify mesgFromFpx
				strmesgFromFpx = request.getParameter("mesgFromFpx");	
				strmesgFromFpx = "message:response|response.string:" + strmesgFromFpx + "\n";
	
				// Creating TCP/IP socket
				Socket socket = new Socket(fpxPluginIP, fpxPluginPort);
				InputStream inSocket = socket.getInputStream();
				OutputStream outSocket = socket.getOutputStream();
				
				byte buffer[] = strmesgFromFpx.getBytes();
				outSocket.write(buffer);
			
				msgFromPlugin = "";
				while ((bufferChar = inSocket.read()) != -1) 
				{
					msgFromPlugin = msgFromPlugin + ((char)bufferChar) ;
				}
				socket.close();
	
				
		   }
		   catch(Exception e)
		   {
			  	out.println("<HR><H3>Error :"+e);
				out.println("<BR>Pls check if the plugin is running in the background</H3>");				
		   }         
%>OK
