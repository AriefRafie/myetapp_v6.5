<!-- 
# Name        : Sample Merchant Page - JSP Page 1 - FPX Main Page
# Author      : Aqilah Dayana Tumian - AD MEPS
				Copyright (c) 2009 MEPS(1997) Sdn.Bhd.
	 			8th Floor, Menara Weld,
	 			Jalan Raja Chulan,
	 			50200 Kuala Lumpur
	 			All rights reserved.
# Version     : 2.0
# Requirement : Merchant will need to edit the Seller ID parameter to match their environment.

# These sample pages are provided for information purposes only. It does not imply any recommendation or endorsement by anyone.
  These sample pages are provided for FREE, and no additional support will be provided for these sample pages. 
  There is no warranty and no additional document. USE AT YOUR OWN RISK.
-->
<%@ page import="java.util.*,java.text.*"%>
<%
	Date txnDate = new Date();
	DateFormat txDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	String txnStr = txDateFormat.format(txnDate);
	txnStr = txnStr.substring(0, 14);
%>
<html>
<script>
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
</script>
<head>
<title>SAMPLE FPX MERCHANT PAGE - Your One Stop Online Computer Shopping</title>
<link rel="stylesheet" type="text/css" href="files/style.css">
</head>
<center>
  <table bgcolor="#FDE6C4" border="0" cellpadding="0" cellspacing="0" height="90%" width="722">
    <tbody>
      <tr>
        <td colspan="3" align="left" height="111"><table style="background:#FDE6C4; border: 1px solid rgb(222, 217, 197);" cellpadding="0" cellspacing="0" height="111" width="722">
            <tbody>
              <tr>
                <td><table style="border: 1px solid rgb(255, 255, 255);" border="0" cellpadding="0" cellspacing="0" height="109" width="720">
                    <tbody>
                      <tr height=100%>
                        <td align="center"><strong>&nbsp;SAMPLE FPX MERCHANT PAGE</strong></td>
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
                <td align="center">PRODUCTS&nbsp;&nbsp;|&nbsp;&nbsp;
                  <a href=# onClick="showpage('faq')">FPX FAQ</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href=# onClick="showpage('popup')">POP UP BLOCKER SETTING</a>&nbsp;&nbsp;|&nbsp;&nbsp; FORUMS &nbsp;|&nbsp;&nbsp;<a href=# onClick="showpage('contact')">
                  CONTACT US</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=# onClick=window.open("http://www.meps.com.my/products_services/fpx.asp")>ABOUT FPX</a> </td>
              </tr>
			  
			  
            </tbody>
          </table></td>
      </tr>
      <!-- header_eof //-->
      <!-- body //-->
      <tr>
        <td style="padding-right: 1px;" align="right" valign="top" width="203"><table border="0" cellpadding="0" cellspacing="0" width="203">
            <tbody>
              <tr>
                <td style="padding-top: 1px;"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tbody>
                      <tr>
                        <td><table class="infoBoxContents" border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                              <tr>
                                <td class="boxText" align="center" valign="top">
                                  <div style="background: rgb(240, 234, 218) none repeat scroll 0%; padding-top: 10px; padding-left: 10px; margin-left: 1px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; height: 57px;" align="left">
                                    <table class="innersearch" border="0" cellpadding="0" cellspacing="0" height="" width="100">
                                      <tbody>
                                        <tr>
                                          <td colspan="2" style="padding-left: 0px;" align="left" width="153">search on site</td>
                                        </tr>
                                        <tr>
                                          <td style="padding-left: 0px;" align="left" height="13" width=""><input size="10" style="width: 115px; height: 13px;" type="text" disabled></td>
                                          <td style="padding-left: 1px;" align="left" width="23">
                                            <img src="image/go.gif" alt="Quick Find" title=" Quick Find " border="0" type="image"></td>
                                        </tr>
                                        <tr>
                                          <td class="searchc" style="padding-left: 0px;" colspan="2" align="left" valign="top"><a>advanced search</a></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>
                                  <!--</form>--></td>
                              </tr>
                            </tbody>
                          </table></td>
                      </tr>
                    </tbody>
                  </table>
                  <!-- search_eof //--></td>
              </tr>
            </tbody>
          </table>
          <table border="0" cellpadding="0" cellspacing="0" width="203" height="230" background="image/FPX_ParticipatingBanks.PNG" style="border: 1px solid rgb(222, 217, 197); background-repeat:no-repeat">
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
          <img src="image/Verisign.png"/>
          <p>&nbsp;</p>
          <p class="normal" align="left"><strong>&nbsp;What is FPX?</strong></p>
          <p class="infoBelow" align="justify">&nbsp;A real-time payment solution from your &nbsp;internet banking account.</p>
          <p class="infoBelow">&nbsp;</p>
          <p class="normal" align="left"><strong>&nbsp;Benefits of FPX</strong></p>
		  <p class="infoBelow" align="left">&nbsp; - SIMPLE : only in a single click.</p>
          <p class="infoBelow" align="left">&nbsp; - CONVENIENT payment anytime, &nbsp;&nbsp;anywhere.</p>
          <p class="infoBelow" align="left">&nbsp; - SECURE: FPX uses authentication and &nbsp;&nbsp;certification to ensure safe transaction.</p>
		  <p class="infoBelow" align="left">&nbsp; - Real-time transaction.</p>
        </td>
		  <td style="padding-left: 1px; padding-right: 1px;" align="left" valign="top" width="600" colspan=2>
		<form id="form2" name="form2" method="post" action="02_fpx_confirm.jsp">
		
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
            <tbody>
			<tr>
			  <td class="main" align="left" >
			  * Please disable your pop-up blocker before you proceed. <a href=# onClick="showpage('popup')">(Refer to Pop up Blocker Settings for details)</a>
			  </td>
			  </tr>
              <tr >
                <td align="left" bgcolor="#FDE6C4" height="30" width="350"><p style="font-family: Tahoma,sans-serif; font-weight:bold ;font-size: 12px; padding-left: 5px;">My Shopping Cart&nbsp;&nbsp;>&nbsp;&nbsp;Transaction Details</p></td>
              </tr>
              <tr valign=top>
                <td class="infoBox" align="left" height="352" valign="top">
				 
				 <!-- List of Products -->  
                  <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                      <TR>
                        <TD vAlign=top align=left><TABLE 
                  width="100%" height="554" border=0 cellPadding=5 cellSpacing=0 class=productListing>
                            <TBODY>
                              <TR>
                                <TD class=productListing-heading>Product(s)</TD>
                                <TD class=productListing-heading align=middle>Quantity</TD>
                                <TD class=productListing-heading align=middle>Total</TD>
                              </TR>
                              <TR>
                                <TD height="123" class=main>&nbsp;<img src="image/mouse.PNG" /></TD>
                                <TD align=middle><INPUT class=productdata size=4 value=1 disabled></TD>
                                <TD align=middle><INPUT class=productdata size=8 value='25.00' disabled></TD>
                              </TR>
                              <TR>
                                <TD height="123" class=main>&nbsp;<img src="image/keyboard.PNG" /></TD>
                                <TD align=middle><INPUT class=productdata size=4 value=1 disabled></TD>
                                <TD align=middle><INPUT class=productdata size=8 value='25.00'disabled></TD>
                              </TR>
                              <TR>
                                <TD height="123" class=main>&nbsp;<img src="image/earphone.PNG" /></TD>
                                <TD align=middle><INPUT class=productdata size=4 value=1 disabled></TD>
                                <TD align=middle><INPUT class=productdata size=8 value='50.00' disabled></TD>
                              </TR>
				<!-- End of List of Products --> 
                              <TR>
                                <TD class="infoBelow" height="56" colSpan=2 align="right"><BR>
                                <span class="infoBelow">TOTAL AMOUNT&nbsp;</span> </TD>
                                <TD class="infoBelow" height="56" colSpan=3 align="right"><BR>MYR
                                  <input class=productdata name="TxnAmount" type="text" id="TxnAmount" size="10" value="100.00">
                                <BR></TD>
                              </TR>
                              <TR>
                                <TD height="5" colSpan=3 align="right"><input type="submit"  height="16" width="78" name="Submit" value="Submit for Payment" />
                                  <BR>
                                  <BR></TD>
                              </TR>
                            </TBODY>
                          </TABLE></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
          </table>
      
      <!-- footer //-->
      <tr>
        <td colspan="3" align="right" height="35" valign="top" width="722"><table border="0" cellpadding="0" cellspacing="0" width="722">
            <tbody>
              
              <tr>
                <td colspan="2"><table style="border: 1px solid rgb(84, 141, 212);" class="menu" cellpadding="0" cellspacing="0" height="19" width="722">
                    <tbody>
                      <tr>
                        <td align="center">&nbsp;&nbsp;Copyright © 2009 All rights reserved&nbsp;&nbsp; </td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr>
                <td  colspan="2"><table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    <tbody>
                      <tr>
                        <td align="center" height="50%" valign="middle" ><table border="0" cellpadding="0" cellspacing="0" awidth="100%">
                            <tbody>
                              <tr>
                                <td ><img src="image/str-list.gif" height="14" width="5">&nbsp;</td>
                                <td class="atopmenu" >About Us&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td ><img src="image/str-list.gif" height="11" width="4">&nbsp;</td>
                                <td class="atopmenu" >FAQ&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td ><img src="image/str-list.gif" height="11" width="4">&nbsp;</td>
                                <td class="atopmenu" >News&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td ><img src="image/str-list.gif" height="11" width="4">&nbsp;</td>
                                <td><a class="atopmenu" href=# onClick="showpage('popup')">Pop Up Blocker Setting</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td ><img src="image/str-list.gif" height="11" width="4">&nbsp;</td>
                                <td><a class="atopmenu" href=# onClick="showpage('disclaimer')">Disclaimer</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                              </tr>
                            </tbody>
                          </table></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
            </tbody>
          </table></td>
      </tr>
    </tbody>
  </table>
  <p class="infoBelow">&nbsp;</p>
  <hr>
  <td class="infoBelow">This parameter should be hidden from customer</td>
  <p>&nbsp;</p>
  <table width="38%" border="0" align="center" cellpadding="7" cellspacing="0" >
    <tr>
      <td colspan="100"></td>
    </tr>
    <tr>
      <td width="30%" class="infoBelow" align="left">Seller ID </td>
      <td width="3%" class="infoBelow"><div align="left">:</div></td>
	  <!-- Merchant will need to edit the Seller ID parameter to match their environment. -->
      <td width="67%"><input class=productdata name="sellerID" type="text" id="sellerID" value="SE00002654"/></td>
    </tr>    
    <tr>
      <td class="infoBelow" align="left">Message Order Count </td>
      <td class="infoBelow"><div align="left">:</div></td>
      <td class="infoBelow"><input class=productdata name="msgOrdNum" type="text" id="msgOrdNum" size="4" value="1" readonly />
        *Always 1 </td>
    </tr>
    <tr>
      <td class="infoBelow" align="left">Message Serial No</td>
      <td class="infoBelow"><div align="left">:</div></td>
      <td class="infoBelow"><input class=productdata name="serialno" type="text" id="serialno" size="4" value="1" readonly />
        *Always 1 </td>
    </tr>
    <tr>
      <td class="infoBelow" align="left">Message Currency </td>
      <td class="infoBelow"><div align="left">:</div></td>
      <td class="infoBelow"><input class=productdata name="MsgCurrency" type="text" id="MsgCurrency" size="8" value="MYR" readonly />
        *Must be MYR </td>
    </tr>
    <tr>
      <td class="infoBelow" align="left">Charge Type </td>
      <td class="infoBelow"><div align="left">:</div></td>
      <td class="infoBelow"><input class=productdata name="ChargeType" type="text" id="ChargeType" size="8" value="AA" readonly /></td>
    </tr>
    <tr>
      <td class="infoBelow" align="left">Seller Bank Code </td>
      <td class="infoBelow"><div align="left">:</div></td>
      <td class="infoBelow"><input class=productdata name="SellerBank" type="text" id="SellerBank" size="8" value="01" readonly/>
        *As predefined by FPX </td>
    </tr>
    <tr>
      <td class="infoBelow">&nbsp;</td>
      <td class="infoBelow">&nbsp;</td>
      <td class="infoBelow">
	  	<input type=text name="TxnOrderNo" id="TxnOrderNo" size="10" value="<%= txnStr %>" />
        <input type=text name="TxnDate" id="TxnDate" size="20" value="<%= txnStr %>" />
      </td>
    </tr>
  </table>
</center>
<!-- footer_eof //-->
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript"> 
_uacct = "UA-2728676-1";
urchinTracker();
</script>
</body>
</form>
</html>