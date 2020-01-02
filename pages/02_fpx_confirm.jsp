<!-- 
# Name        : Sample Merchant Page - JSP Page 2 - FPX Confirmation Page
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
<%@ page import="java.io.*,java.util.*,java.net.*"%>
<html>
<head>
<title>SAMPLE FPX MERCHANT PAGE - Your One Stop Online Computer Shopping</title>
<script type="text/JavaScript" language="JavaScript">
	// Start of code to detect if customer browser support popup
	var mine = window.open("https://210.187.85.180/FPXMain/sellerB2CMesgRecv.jsp","test1","width=1,height=1,left=0,top=0,scrollbars=no");
	if(mine)
		var popUpsBlocked = false
	else
		var popUpsBlocked = true
	mine.close()
	
	if(popUpsBlocked)
		alert('We have detected that you are using popup blocking software.\n This website needs to open a window to your internet Banking Website.\n Please disable your popup blocker.');
	// End of code to detect if customer browser support popup.  
	
	function showpage(pageName)
	{
		if(pageName=="popup")
		{
			pageName	=	pageName+".html";
			window.open(pageName,'cont','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=820, height=500');
		}
		else
		{
			pageName	=	pageName+".html";
			window.open(pageName,'cont','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=400, height=250');
		}
	}
	
	window.name="seller";
	function openFpx()
	{
			if(navigator.appName.indexOf('Explorer') != -1)
			{
					document.form1.action="https://uat.mepsfpx.com.my/FPXMain/sellerB2CMesgRecv_v2.jsp";
					document.form1.target="seller";
					document.form1.submit();
			}
			else if(navigator.appName.indexOf('Netscape') != -1)
			{
					document.form1.action="https://uat.mepsfpx.com.my/FPXMain/sellerB2CMesgRecv_v2.jsp";
					document.form1.target="seller";
					document.form1.submit();
			}
	}
</script>
<link rel="stylesheet" type="text/css" href="files/style.css">
</head>
<body bgcolor="#C0E7FE">
<center>
<%
	//Merchant will need to edit the fpxPluginPort with unique number which match to daemon port number in plugin.xml .
	
	String fpxPluginIP = "127.0.0.1";
	int fpxPluginPort = 6666;
	
	String msgToPlugin =  null;
	String msgFromPlugin = null;	
	
	int bufferChar;

	try{
			// Creating TCP/IP socket
			Socket socket = new Socket(fpxPluginIP, fpxPluginPort);
            InputStream inSocket = socket.getInputStream();
            OutputStream outSocket = socket.getOutputStream();
	
			// Creating string msg to send to FPX Plugin			
			String txnSellerID = request.getParameter("sellerID");
			String txnSellerOrderNo = request.getParameter("TxnOrderNo");
			String txnExchangeOrderNo = request.getParameter("TxnOrderNo");
			String txnDate = request.getParameter("TxnDate");
			String txnAmount = request.getParameter("TxnAmount");
			msgToPlugin = "message:request|message.type:AR|message.token:01|message.orderno:" + txnExchangeOrderNo + "|message.ordercount:1|message.txntime:" + txnDate + "|message.serialno:1|message.currency:MYR|message.amount:" + txnAmount + "|charge.type:AA|seller.orderno:" + txnSellerOrderNo + "|seller.id:" + txnSellerID + "|seller.bank:01|\n";
						
			// Sending msg to FPX Plugin	
			byte buffer[] = msgToPlugin.getBytes();
            outSocket.write(buffer);
			
			// Receiving response from FPX Plugin
			msgFromPlugin = "";
            while ((bufferChar = inSocket.read()) != -1) {
				msgFromPlugin = msgFromPlugin + ((char)bufferChar) ;
			}
			
			// Closing Socket	
			socket.close();
		}
        catch(java.net.ConnectException e){
			out.println("<HR>Error :"+e);
			out.println("<BR>Pls check if the plugin is running in the background");				
        }
%>
  <table border="0" cellpadding="0" cellspacing="0" height="96%" width="722">
    <tbody>
      <tr>
        <td colspan="3" align="left" height="111"><table style="background:#FDE6C4; border: 1px solid rgb(222, 217, 197);" cellpadding="0" cellspacing="0" height="111" width="722">
            <tbody>
              <tr>
                <td><table style="border: 1px solid rgb(255, 255, 255);" border="0" cellpadding="0" cellspacing="0" height="109" width="720">
                    <tbody>
                      <tr>
                        <td align="center"><strong>SAMPLE FPX MERCHANT PAGE</strong></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
            </tbody>
          </table></td>
      </tr>
      <tr>
        <td colspan="3" align="left" height="21"><table style="border: 1px solid rgb(84, 141, 212);" class="menu" cellpadding="0" cellspacing="0" height="19" width="722">
            <tbody>
              <tr>
                <td align="center"><a href=# onClick="showpage('faq')">FPX FAQ</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href=# onClick="showpage('popup')">POP UP BLOCKER SETTING</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href=# onClick="showpage('contact')">
                  CONTACT US</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; <a href=# onClick=window.open("http://www.meps.com.my/products_services/fpx.asp")>ABOUT FPX</a> </td>
              </tr>
            </tbody>
          </table></td>
      </tr>
      <!-- header_eof //-->
      <!-- body //-->
      <tr>
        <td style="padding-left: 1px; padding-right: 1px;" align="left" valign="top" width="100%" colspan=2>
		<table bgcolor="#FDE6C4" border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
            <!-- body_text //-->
            <tbody>
			<tr>
			  <td class="main" align="left" >
			  * Please disable your pop-up blocker before you proceed. <a href=# onClick="showpage('popup')">(Refer to Pop up Blocker Settings for details)</a>
			  </td>
			  </tr>
              <tr>
                <td align="left" height="27" width="722"><p style="color: rgb(0, 0, 0); font-family: Tahoma,sans-serif; font-size: 12px; padding-left: 5px;"><b>My Shopping Cart&nbsp;&nbsp;>&nbsp;&nbsp;Transaction Details&nbsp;&nbsp;>&nbsp;&nbsp;Order Confirmation</b></p></td>
              </tr>
              <tr>
                <td style="padding-top: 2px;" valign="top"><table class="infoBox" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    <tbody>
                      <tr>
                        <td valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FDE6C4">
                            <tbody>
                              <tr>
                                <td><table class="infoBox" aborder="1" cellpadding="2" cellspacing="1" width="100%">
                                    <tbody>
                                      <tr class="infoBoxContents">
                                        <td valign="top" width="99%"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                                            <tbody>
                                              <tr>
                                                <td><table border="0" cellpadding="10" cellspacing="0" width="100%">
                                                    <tbody>
													<!-- List of Products Chosen --> 
                                                      <tr>
                                                        <td class="main" ><b>Products</b> </td>
                                                        <td class="main" align="right"><b>Quantity</b></td>
                                                        <td class="main" align="right"><b>Total</b></td>
                                                      </tr>
                                                      <tr>
                                                        <td valign='top' class='main'>Kensington Pocketmouse </td>
                                                        <td class='main' align='right' valign='top'>
															<input class=productdata type="text" size='4' value='1' disabled></td>
                                                        <td class='main' align='right' valign='top'>MYR
                                                          	<input class=productdata type="text" size='10' value='25.00' disabled></td>
                                                      </tr>
                                                      <tr>
                                                        <td valign='top' class='main'>Apple Keyboard </td>
                                                        <td class='main' align='right' valign='top'>
															<input class=productdata type="text" size='4' value='1' disabled></td>
                                                        <td class='main' align='right' valign='top'>MYR
                                                          	<input class=productdata type="text" size='10' value='25.00' disabled></td>
                                                      </tr>
                                                      <tr>
                                                        <td class='main' valign='top'>Earphone with Remote and Mic </td>
                                                        <td class='main' align='right' valign='top'>
															<input class=productdata type="text" size='4' value='1' disabled></td>
                                                        <td class='main' align='right' valign='top'>MYR
                                                          	<input class=productdata type="text" size='10' value='50.00' disabled></td>
                                                      </tr>
													  <!-- End of List Products Chosen --> 
                                                    </tbody>
                                                  </table>
                                                  <table border="0" cellpadding="10" cellspacing="0" width="100%" height="100%">
                                                    <tr>
                                                      <td width="71%" align=center class="main">&nbsp;</td>
                                                    </tr>                                                   
													<tr>
                                                       <td width="71%" align='right' valign='top' class='main'><b>Total Amount</b></td>
                                                        <td width="29%" align='right' valign='top' class='main'>MYR
                                                      		<input type="text" name="TxnAmount" id="TxnAmount" size='10' value="<%=request.getParameter("TxnAmount")%>" readonly>
														</td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </tbody>
                                          </table>
                                      </tr>
                                    </tbody>
                                  </table></td>
                              </tr>
							  <!-- Submit transaction via FPX --> 
                              <tr>
                                <td><form name="form1" method="post" target='fpx'>
                                  <table border="0" cellpadding="2" cellspacing="1" width="100%">
                                    <tbody>
                                      <tr class="infoBoxContents">
                                        <td valign="top" width="30%"><table border="0" cellpadding="2" cellspacing="0" width="100%">
                                            <tbody>                                              
                                              <tr>
                                                <td height="164" align="center" class="main"><b>Payment Method via FPX</b>
												<p>&nbsp;</p>
												<input type="button" style="cursor:hand" value="Click to Pay" onClick="openFpx()" />
												  <p>&nbsp;</p>
                                                  <p> <img src="image/FPXButton.PNG" border="2"/></p>
                                                  <p>&nbsp;</p>
												  <p class="main">&nbsp;</p>
                                                  <p class="main"><strong>* You must have Internet Banking Account in order to make transaction using FPX.</strong></p>
                                                  <p>&nbsp;</p>
                                                  <p class="main"><strong>* Please ensure that your browser's pop up blocker has been disabled to avoid any interruption during making transaction.</strong></p>
                                                  <p>&nbsp;</p>
                                                  <p class="main"><strong>* Do not close browser / refresh page until you receive response.</strong></p>
                                                <p>&nbsp;</p></td>
                                              </tr>
                                            </tbody>
                                          </table></td>
                                      </tr>
                                    </tbody>
                                  </table>                                  
                                 
                          </table></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
            </tbody>
          </table></td>
      </tr>
      <!-- footer //-->
      <tr>
        <td colspan="3" align="right" height="35" valign="top" width="722"><table border="0" cellpadding="0" cellspacing="0" width="722">
            <tbody>
              <tr> </tr>
              <tr>
                <td colspan="2"><table style="border: 1px solid rgb(84, 141, 212);" class="menu" cellpadding="0" cellspacing="0" height="19" width="722">
                    <tbody>
                      <tr>
                        <td align="center">&nbsp;&nbsp;Copyright © 2009 All rights reserved&nbsp;&nbsp; </td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              
            </tbody>
          </table></td>
      </tr>
    </tbody>
  </table>
  <hr>  
  <span class="infoBelow">This parameter should be hidden from customer </span>
  <p>&nbsp;</p>
  <table width="100%" border="0" align="center" cellpadding="7" cellspacing="0" >
    <tr>
      <td colspan="100"></td>
    </tr>
  	<tr>
	  <td class="infoBelow" align="center"><p>Sending message to FPX Plugin</p>
		  <p>&nbsp;</p>
		  <% { out.println("<textarea cols=80 rows=4>" + msgToPlugin + "</textarea>"); } %>
		  <p>&nbsp;</p>	  </tr>
	<tr>
		<td class="infoBelow" align="center"><p>Receiving response from FPX Plugin</p>
		  <p>&nbsp;</p>
		  <% { out.println("<textarea cols=80 rows=4>|" + msgFromPlugin.trim() + "|</textarea>"); } %>
		  <p>&nbsp;</p></tr>
	<label>
    <input type=hidden value='<%=msgFromPlugin.trim()%>' name="MsgToFpx">
	<input type="hidden" name="ItemName" value="Computer Appliances">
	</label>
  </table>
  </form>
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
