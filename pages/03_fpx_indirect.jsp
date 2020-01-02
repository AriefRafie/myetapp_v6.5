<!-- 
# Name        : Sample Merchant Page - JSP Page 3 - FPX Indirect Page
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
<html>
<head>
<title>SAMPLE FPX MERCHANT PAGE - Your One Stop Online Computer Shopping</title>
<link rel="stylesheet" type="text/css" href="files/style.css">
</head>

<center>
<%
	Date txnDate = new Date();
	DateFormat txDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	String txnStr = txDateFormat.format(txnDate);
	txnStr = txnStr.substring(0, 24);
%>
<%! 
	public String checkVal(String str)
    {
        if((str == null) || ((str.trim().equals("null"))))
			return "";
		else
			return str;
     }
		
	String fpxPluginIP = "127.0.0.1";
	//Merchant will need to edit the fpxPluginPort with unique number which match to daemon port number in plugin.xml .
	int fpxPluginPort = 6000;
	
	String resp=null; 
	String strmesgFromFpx=null;
	String msgFromPlugin=null;		
	String msgResult[];	
	String txnResult="";
	int bufferChar;		
%>
<%			
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
%>

  <table border="0" cellpadding="0" cellspacing="0" height="300" width="722">
    <tbody>
      <tr>
        <td colspan="3" align="left" height="111"><table style="background:#FDE6C4;border: 1px solid rgb(222, 217, 197);" cellpadding="0" cellspacing="0" height="111" width="722" >
            <tbody>
              <tr>
                <td align="center"><strong>SAMPLE FPX MERCHANT PAGE</strong></td>
              </tr>
            </tbody>
          </table>
		  </td>
      </tr>
      <!-- header_eof //-->
      <!-- body //-->
      <tr>
        <td style="padding-left: 1px; padding-right: 1px;" align="left" valign="top" width="716" colspan=2>
		<table bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="5" class="infoBelow" width="100%" height="100%">
		  <tbody>
              <tr>
                <td height="150" valign="top">
				<p class="normal">Thanks for shopping with us online! </p>
                  <p>&nbsp;</p>				  
				  <%
				  		//Display details for Receipt
						String receiptResult[];	
						String txnReceiptResult="";
						
				  		receiptResult = (msgFromPlugin.
								replaceAll("message.type:", "").
								replaceAll("message.token:", "").
								replaceAll("message.orderno:", "").
								replaceAll("message.ordercount:", "").
								replaceAll("message.txnTime:", "").
								replaceAll("message.fpxTransactionId:", "").
								replaceAll("message.serialno:", "").
								replaceAll("message.currency:", "").
								replaceAll("message.amount:", "").
								replaceAll("charge.type:", "").
								replaceAll("seller.orderno:", "").
								replaceAll("seller.bank:", "").
								replaceAll("buyer.name:", "").	
								replaceAll("buyer.id:", "").
								replaceAll("buyer.bank:", "").
								replaceAll("buyer.bankbranch:", "").
								replaceAll("buyer.accno:", "").
								replaceAll("buyer.iban:", "").
								replaceAll("buyer.name:", "").	
								replaceAll("maker.name:", "").
								replaceAll("debit.authcode:", "").
								replaceAll("debit.authno:", "").
								replaceAll("credit.authcode:", "").
								replaceAll("credit.authno:", "").
								replaceAll(" ", "")).
								split("\\|");
					
					for (int i=0; i < receiptResult.length; i++) 
					{
						txnReceiptResult = txnReceiptResult + receiptResult[receiptResult.length-i-1]+"\n";
					}
				  %>
				  <p class="normal"><b>TRANSACTION DETAILS</b></p>
				  <!-- Display details for Receipt -->
				  <table width="100%" align="center">
				  	<tr>
                      <td width="44%" align="left" class="main">Transaction Status</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main"><strong>
					  <!-- Comparing Debit Auth Code and Credit Auth Code to cater SUCCESSFUL and UNSUCCESSFUL result -->
					  <% { 
							if ( (receiptResult[20].equals("00")) && (receiptResult[22].equals("00")) )
							{
								out.println("SUCCESSFUL");					
							}
							else if (receiptResult[20].equals("99"))
							{
								out.println("PENDING FOR AUTHORIZER TO APPROVE");
							}
							else if (!(receiptResult[20].equals("00")) || !(receiptResult[20].equals("")) || !(receiptResult[20].equals("99")))
							{
								out.println("UNSUCCESSFUL");
							}
							
						} 
						%></strong></td>
                    </tr>
                    <tr>
                      <td width="44%" align="left" class="main">Date</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main"><% { out.println(txnStr);	} %></td>
                    </tr>
                    <tr>
                      <td width="44%" align="left" class="main">FPX Txn ID</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main"><% { out.println(receiptResult[6]);} %></td>
                    </tr>
                    <tr>
                      <td width="44%" align="left" class="main">Seller Order Number</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main"><% { out.println(receiptResult[11]);} %></td>
                    </tr>
					<tr>
                      <td width="44%" align="left" class="main">Buyer Bank Name</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main">
					  <!-- List of Banks involved is stored in Array List-->
					  <% { 
					  
					  		String testBank[] = {"TEST BANK A", "TEST BANK B", "TEST BANK C", "CIMB Bank Berhad", "Bank Islam Malaysia Berhad",
												"Citibank Berhad","Hong Leong Bank Berhad", "Malayan Banking Berhad","OCBC Bank (Malaysia) Berhad",
												"Public Bank Berhad","RHB Bank Berhad"};

							if (receiptResult[15].equals("TEST0001")) {
								out.println(testBank[0]);
							} else if (receiptResult[15].equals("TEST0002")) {
								out.println(testBank[1]);
							} else if (receiptResult[15].equals("TEST0003")) {
								out.println(testBank[2]);
							} else if (receiptResult[15].equals("BCBB0235")) {
								out.println(testBank[3]);
							} else if (receiptResult[15].equals("BIMB0340")) {
								out.println(testBank[4]);
							} else if (receiptResult[15].equals("CIT0217")) {
								out.println(testBank[5]);
							} else if (receiptResult[15].equals("HLB0224")) {
								out.println(testBank[6]);
							} else if ((receiptResult[15].equals("MB2U0227")) || (receiptResult[15].equals("MBB0227"))) {
								out.println(testBank[7]);
							} else if (receiptResult[15].equals("OCBC0229")) {
								out.println(testBank[8]);
							} else if (receiptResult[15].equals("PBB0233")) {
								out.println(testBank[9]);
							} else if (receiptResult[15].equals("RHB0218")) {
								out.println(testBank[10]);
							}
						} %>
						</td>
                    </tr>
					<tr>
                      <td width="44%" align="left" class="main">Transaction Amount</td>
                      <td width="7%" align="center" class="main">:</td>
                      <td width="49%" align="left" class="main">&nbsp;RM<% { out.println(receiptResult[9]);} %></td>
                    </tr>
                  </table>
				  </td>	  
              </tr>
            </tbody>
          </table></td>
      </tr>
      <!-- footer //-->  
    </tbody>
  </table>
  <p>&nbsp;</p>
  <hr>
  <center>
    <p class="infoBelow">&nbsp;</p>
    <p class="infoBelow">This parameter should be hidden from customer </p>  
	<p>&nbsp;</p>
	<tr>
        <td style="padding-left: 1px; padding-right: 1px;" align="left" valign="top" width="716" colspan=2>
			<table width="100%" border="0" align="center" cellpadding="7" cellspacing="0" >
				<tr>
				  <td colspan="100"></td>
				</tr>
				<tr>
				  <td class="infoBelow" align="center">1. Message From FPX - mesgFromFpx:
					<p>&nbsp;</p>
					<textarea name="textarea" cols=80 rows=4 ><%=strmesgFromFpx%></textarea>
					<p>&nbsp;</p>		
				</tr>
				<tr>
				  <td class="infoBelow" align="center"><p>2. Message From FPX Plugin:</p>
					<p>&nbsp;</p>
					<textarea COLS=80 ROWS=4><%=msgFromPlugin%></textarea>
					<p>&nbsp;</p>		
				</tr>
			</table>
		</td>
	</tr>
  <p>&nbsp;</p>
</center>
<!-- footer_eof //-->
<br>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript"> 
_uacct = "UA-2728676-1";
urchinTracker();
</script>
</body>
</html>
