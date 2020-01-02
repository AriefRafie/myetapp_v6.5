#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<br/><br/>

<!-- <input type="text" value="$plugin_url"> -->
<center>


  	    <br/>
  	    
  	    #if($showStep=="3")
  	     	#set($color1="#C9BE62")
  	     	#set($color2="#C9BE62")
  	     	#set($color3="#F88017")
  	    #elseif($showStep=="2")
  	     	#set($color1="#C9BE62")
  	    	#set($color2="#F88017")
  	     	#set($color3="#C9BE62")
  	    #else
  	     	#set($color1="#F88017")
  	     	#set($color2="#C9BE62")
  	     	#set($color3="#C9BE62")
  	    #end
  	    
  	    <table width="80%" border="0">
  	    	<tr>
  	    		<td width="25%">&nbsp;</td>
  	    		<td width="25%" bgcolor=$!color1 align="center"><b>LANGKAH 1</b></td>
  	    		<td width="25%" bgcolor=$!color2 align="center"><b>LANGKAH 2</b></td>
  	    		<td width="25%" bgcolor=$!color3 align="center"><b>LANGKAH 3</b></td>
  	    	</tr>
  	    </table>
  	    
  	    #if($showStep=="1")
  	  	<fieldset style="width:79%" id="top">

			<table width="100%" border="0" cellpadding="4" cellspacing="4">
				<tr class="table_header">
					<td><b>JENIS BAYARAN</b></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="3%" align="right"><font color="red">*</font></td>
					<td width="26%"><b>JENIS BAYARAN</b></td>
					<td width="1%"><b>:</b></td>
					<td width="70%">$!selectJenisBayaran</td>	
				</tr>
			</table>

		</fieldset>	
  	    
  	  		<table width="79%" border="0">
				<tr>
				    <td align="left" width="50%"><a href="javascript:setTable('showTrans')"><font color="blue"><b>&raquo;Lihat Transaksi Terdahulu</b></font></a></td>
					<td align="right" width="50%"><input type="button" name="cmdNext1" value ="Seterusnya" onClick="javascript:goMaklumatBayaran()"></td>
				</tr>
			</table>
			
		<br/>
			
		<fieldset id="showTrans" style="display:none;width:79%" >	
			<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>Jenis Pembayaran</b></td>	
        			<td><b>Amaun</b></td>
        			<td><b>Tarikh Bayaran</b></td>	
                  	<td><b>No. Transaksi</b></td>             
                  	<td><b>Status</b></td>
					<td><b></b></td>		
        		</tr>
        		
        		#if($listHistory_Size!=0)
                    #foreach($listH in $listHistory)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listH.bil</td>
                   <td class="$row">$!listH.JENIS_BAYARAN</td>
                   <td class="$row">$!listH.AMAUN_BAYARAN</td>     
                   <td class="$row">$!listH.TARIKH_BAYARAN</td>
                   <td class="$row">$!listH.NO_TRANSAKSI</td>		
                   <td class="$row">$!listH.STATUS_TRANSAKSI</td>	
                   <td class="$row">
                   #if($!listH.STATUS_TRANSAKSI=='BERJAYA')
      				<input name="cmdTambah" type="button" value="Cetak Resit" onclick="javascript:cetakResit('$!listH.ID_FPX','$!listH.NILAI')" id="cmdTambah"/>
      				#end
      				</td>							
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  	</table>
		</fieldset>
			
		#end



		#if($showStep=="2")
		
		#if($jenisPembayar=="2")
         	#set($checkA="")
         	#set($checkB="checked")
        #else
         	#set($checkA="checked")
         	#set($checkB="")
        #end
         	
  	  	<fieldset style="width:80%" id="top">

			<table width="100%" border="0" cellpadding="4" cellspacing="4">
				<tr class="table_header">
					<td><b>MAKLUMAT BAYARAN</b></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="9%">&nbsp;</td>
            		<td width="24%"><b>JENIS PEMBAYAR</b></td>
            		<td width="1%"><b>:</b></td>
					<td >
                		<input name="sorFlagJenisPembayar" $!checkA type="radio" value="1" onclick="onClickJenisPembayar()" />Individu
                		<input name="sorFlagJenisPembayar" $!checkB type="radio" value="2" onclick="onClickJenisPembayar()" />Syarikat
                	</td> 
				</tr>
				<tr>
					<td>&nbsp;</td>
					#if($jenisPembayar=="2")
					<td><b>NAMA SYARIKAT</b><font color="red">*</font></td>
					#else
					<td><b>NAMA</b><font color="red">*</font></td>
					#end
					<td><b>:</b></td>
					<td><input type="text" name="txtNama" id="txtNama" value="$!txtNama" size="30" maxlength="100"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					#if($jenisPembayar=="2")
					<td><b>NO. SYARIKAT (MYCOID)</b><font color="red">*</font></td>
					#else
					<td><b>MyID<font color="red">*</font></b></td>
					#end
					<td><b>:</b></td>
					<td>
						<input type="text" name="txtNoPengenalan" id="txtNoPengenalan" value="$!txtNoPengenalan" size="20" maxlength="50">
						#if($jenisPembayar=="1")<i><font color='blue' style='font-size:10px'>cth: 701219045449 / A1234567 / I/20067</font></i>#end
					</td>
				</tr>
			</table>	
		</fieldset>
				
		<fieldset style="width:80%">	
			<table width="100%" border="0">	
				<tr>
					<td width="9%" align="right"></td>
					<td width="24%"><b>NO. FAIL</b><font color="red">*</font></td>
					<td width="1%"><b>:</b></td>
					<td><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" size="30" maxlength="80"><i><font color='blue' style='font-size:10px'>cth: JKPTG/881/SEL/04/01/2010</font></i></td>	
				</tr>
				
				<tr>
					<td></td>
					<td><b>AMAUN</b><font color="red">*</font></td>
					<td><b>:</b></td>
					<td>RM&nbsp;<input type="text" name="txtAmaun" id="txtAmaun" value="$!txtAmaun" size="15" maxlength="17" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaun')" ></td>	
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><b>NO. BIL</b></td>
					<td><b>:</b></td>
					<td><input type="text" name="txtNoBil" id="txtNoBil" value="$!txtNoBil" size="20" maxlength="50"></td>
				</tr>
				<tr>
    				<td>&nbsp;</td>
    				<td valign="top"><b>CATATAN</b></td>
    				<td valign="top"><b>:</b></td>
    				<td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="4" cols="40%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!txtCatatan</textarea></td>
    			</tr>					
			</table>
			
		</fieldset>	
		
			<table width="80%" border="0">
				<tr>
					<td align="right">
						<input type="button" name="cmdKosongkan" value ="Kembali" onClick="javascript:backToMain()">
						<input type="button" name="cmdKosongkan" value ="Kosongkan" onClick="javascript:resetValue()">
						<input type="button" name="cmdNext2" value ="Seterusnya" onClick="javascript:goPengesahanBayaran()">
					</td>
				</tr>
			</table>
			
			 <table width="38%" border="0" align="center" cellpadding="7" cellspacing="0" >
    <tr>
      <td colspan="100"></td>
    </tr>
    <!-- <tr>
  
	  <!-- Merchant will need to edit the Seller ID parameter to match their environment. -->
      <!-- <td width="67%"><input class=productdata name="sellerID" type="hidden" id="sellerID" value="SE00002654"/></td>
    </tr> -->    
    <tr>

      <td class="infoBelow"><input class=productdata name="msgOrdNum" type="hidden" id="msgOrdNum" size="4" value="1" readonly />
        </td>
    </tr>
    <tr>

      <td class="infoBelow"><input class=productdata name="serialno" type="hidden" id="serialno" size="4" value="1" readonly />
        </td>
    </tr>
    <tr>
      
      <td class="infoBelow"><input class=productdata name="MsgCurrency" type="hidden" id="MsgCurrency" size="8" value="MYR" readonly />
      </td>
    </tr>
    <tr>
     
      <td class="infoBelow"><input class=productdata name="ChargeType" type="hidden" id="ChargeType" size="8" value="AA" readonly /></td>
    </tr>
    <tr>

<!-- 		#if($jenisPembayar=="2")
      <td class="infoBelow"><input class=productdata name="SellerBank" type="hidden" id="SellerBank" size="8" value="02" readonly/>
      #else -->
      <td class="infoBelow"><input class=productdata name="SellerBank" type="hidden" id="SellerBank" size="8" value="01" readonly/>
      <!-- #end -->
     </td>
    </tr>
    <tr>
      <td class="infoBelow">&nbsp;</td>
      <td class="infoBelow">&nbsp;</td>
      <td class="infoBelow">
	  	<input type=hidden name="TxnOrderNo" id="TxnOrderNo" size="10" value="$txnStr" />
        <input type=hidden name="TxnDate" id="TxnDate" size="20" value="$txnStr" />
        <input type=hidden name="sellerID" id="sellerID" value="" />
      </td>
    </tr>
  </table>
  
  	    #end



		#if($showStep=="3")
		
		
			#if($dataFPX.size()!=0)
				#foreach($data in $dataFPX)
				#set($lblJenisBayaran=$data.jenis_bayaran)
				#set($lblNoFail=$data.no_fail)
				#set($lblAmaun=$data.amaun)
				#set($lblCatatan=$data.catatan)
				#set($lblTarikhBayaran=$data.tarikh_bayaran)
				#set($lblNama=$data.nama)
				#set($lblNoPengenalan=$data.no_kp)
				#set($lblJenisPembayar=$data.jenisPembayar)
				#set($lblNoBil=$data.no_bil)
				#end
			#else
				#set($lblJenisBayaran=$!txtjenisBayaran)
				#set($lblNoFail=$!txtNoFail)
				#set($lblAmaun=$!txtAmaun)
				#set($lblCatatan=$!txtCatatan)
				#set($lblTarikhBayaran=$!txtSysdate)
				
				#set($lblNama=$!txtNama)
				#set($lblNoPengenalan=$!txtNoPengenalan)
				#set($lblJenisPembayar=$!txtJenisPembayar)
				#set($lblNoBil=$!txtNoBil)
				
			#end
		
  	  	<fieldset style="width:80%" id="top">

			<table width="100%" border="0" cellpadding="4" cellspacing="4">
				<tr class="table_header">
					<td><b>PENGESAHAN MAKLUMAT BAYARAN</b></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="28%"><b>JENIS BAYARAN</b></td>
					<td width="1%"><b>:</b></td>
					<td width="68%">$!lblJenisBayaran</td>
				</tr>				
				<tr>
					<td>&nbsp;</td>
					<td><b>JENIS PEMBAYAR</b></td>
					<td><b>:</b></td>
					<td>$!lblJenisPembayar</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					#if($jenisPembayar=="2")
					<td><b>NAMA SYARIKAT</b></td>
					#else
					<td><b>NAMA</b></td>
					#end
					<td><b>:</b></td>
					<td>$!lblNama</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					#if($jenisPembayar=="2")
					<td><b>NO. SYARIKAT</b></td>
					#else
					<td><b>MyID</b></td>
					#end
					<td><b>:</b></td>
					<td>$!lblNoPengenalan</td>
				</tr>	
				<tr>
					<td>&nbsp;</td>
					<td><b>NO. FAIL</b></td>
					<td><b>:</b></td>
					<td>$!lblNoFail</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><b>AMAUN</b></td>
					<td><b>:</b></td>
					<td>RM&nbsp;$!lblAmaun</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><b>NO. BIL</b></td>
					<td><b>:</b></td>
					<td>$!lblNoBil</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="top"><b>CATATAN</b></td>
					<td valign="top"><b>:</b></td>
					<td valign="top">$!lblCatatan</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><b>TARIKH BAYARAN</b></td>
					<td><b>:</b></td>
					<td>$!lblTarikhBayaran</td>
				</tr>
			</table>
			
		</fieldset>	
  	  
  	  		<table width="70%" border="0">
  	  		<tr>
	  	  			<td width="40%" align="center">
	  	  				<img src="../pages/image/FPXButton.PNG" />
	  	  				
	  	  			</td>
	  	  			
  	  			</tr>
  	  		<tr>
  	  			<td colspan="2" valign="top">
  	  			<font color=red>
  	  				*Sila pastikan <i>Popup Blocker</i> dimatikan sebelum meneruskan proses pembayaran<br>
  	  				*Had transaksi FPX adalah RM 30,000 pada satu-satu masa<br>
  	  				*Sila pastikan anda mempunyai akaun perbankan <i>internet</i>
  	  			</font>
  	  			</td>
  	  		</tr>
  	  		
				<tr>
					<td align="center" colspan="2">
						#if($mode=="view")
						<input type="button" name="cmdCetak" value ="Cetak Resit" onClick="javascript:">
						<input type="button" name="cmdKembali" value ="Buat Bayaran Lain" onClick="javascript:back()">
						#else
						<input type="button" name="cmdSah" value ="Kembali" onClick="javascript:back()">
						<input type="button" name="cmdSah" value ="Bayar" onClick="javascript:simpanPengesahan()">
						
						#end
					</td>
				</tr>
				
			</table>
		
		<input type="hidden" name="MsgToFpx">
		<input type="hidden" name="ItemName" value="$!lblJenisBayaran">
		<input type="hidden" name="txtNama" value="$!txtNama">
		<input type="hidden" name="txtNoPengenalan" value="$!txtNoPengenalan">
		<input type="hidden" name="sorFlagJenisPembayar" value="$!jenisPembayar">
		<input type="hidden" name="txtNoBil" value="$!txtNoBil">
		
		<input type="hidden" name="txtNoFail" value="$!txtNoFail">
		<input type="hidden" name="txtAmaun" value="$!txtAmaun">
		<input type="hidden" name="txtCatatan" value="$!txtCatatan">	
		<input type="hidden" name="txtSysdate" value="$!txtSysdate">
		
		
			
		#end


</center>








<!-- START HIDDEN VALUE -->

<!-- Main Id -->
<input type="hidden" name="id_datafpx" value="$!id_datafpx">
<input type="hidden" name="url_FPX" value="">
<input type="hidden" name="valueJenisBayaran" value="$!valueJenisBayaran">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- Do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Others -->
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<input type="hidden" name="msgToken">

<!-- END HIDDEN VALUE -->



<!-- START MAIN JAVASCRIPT -->
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
	}
}
function backToMain(){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "mainPage";
	document.${formName}.action = "?_portal_module=ekptg.fpx.FrmFPXView";
	document.${formName}.submit();
}
function resetValue() {
	document.getElementById("txtNama").value = "" ;
	document.getElementById("txtNoPengenalan").value = "" ;
	document.getElementById("txtNoFail").value = "" ;
	document.getElementById("txtAmaun").value = "" ;
	document.getElementById("txtNoBil").value = "" ;
	document.getElementById("txtCatatan").value = "" ;
}
function onClickJenisPembayar() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "goMaklumatBayaran";
	document.${formName}.command2.value = "onClickJenisPembayar";
	document.${formName}.action = "?_portal_module=ekptg.fpx.FrmFPXView";
	document.${formName}.submit();
}
function back() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "back";
	document.${formName}.action = "?_portal_module=ekptg.fpx.FrmFPXView";
	document.${formName}.submit();
}
function simpanPengesahan() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	//document.${formName}.ScreenLocation.value = "top";
	//document.${formName}.command.value = "goPengesahanBayaran";
	//document.${formName}.command2.value = "simpanPengesahan";
	//document.${formName}.action = "../integration/fpxsender";
	//document.${formName}.submit();

	document.${formName}.action="$plugin_url";
	document.${formName}.MsgToFpx.value='$msgFromPlugin.trim()';
	document.${formName}.submit();
}
function goMaklumatBayaran() {
	if(document.${formName}.socJenisBayaran.value == ""){
	   	alert("Sila pilih \"Jenis Bayaran\" terlebih dahulu.");
		document.${formName}.socJenisBayaran.focus();
		return;
	}
	else{
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "goMaklumatBayaran";
		document.${formName}.action = "?_portal_module=ekptg.fpx.FrmFPXView";
		document.${formName}.submit();
	}
}
function goPengesahanBayaran() {
	//alert($jenisPembayar);
	if(document.${formName}.txtNoFail.value == ""){
	   	alert("Sila masukkan \"No. Fail\" terlebih dahulu.");
		document.${formName}.txtNoFail.focus();
		return;
	}
	else if(document.${formName}.txtAmaun.value == ""){
	   	alert("Sila masukkan amaun bayaran");
		document.${formName}.txtAmaun.focus();
		return;
	}
	else if(document.${formName}.txtNoPengenalan.value == ""){
	   	alert("Sila masukkan No.KP atau MYCOID");
		document.${formName}.txtNoPengenalan.focus();
		return;
	}
	else if(document.${formName}.txtNama.value == ""){
	   	alert("Sila masukkan Nama");
		document.${formName}.txtNama.focus();
		return;
	}
	else{
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "goPengesahanBayaran";
		if($jenisPembayar == "1") {
			document.${formName}.sellerID.value = "SE00003377";
			document.${formName}.url_FPX.value = "INDIVIDU";	
			document.${formName}.msgToken.value = "01";
		} else if($jenisPembayar == "2") {
			document.${formName}.sellerID.value = "SE00003156";
			document.${formName}.url_FPX.value = "SYARIKAT";
			document.${formName}.msgToken.value = "02";
		}
		document.${formName}.action = "?_portal_module=ekptg.fpx.FrmFPXView";
		document.${formName}.submit();
	}
}

function cetakResit(id,nilai) {
	
 	var url = "../servlet/ekptg.report.online.SalinanResitBayaran?id_fpx="+id+"&nilai="+nilai;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

</script>
<!-- END MAIN JAVASCRIPT -->



<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
}
function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}

function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}
</script>
<!-- END OTHERS JAVASCRIPT -->