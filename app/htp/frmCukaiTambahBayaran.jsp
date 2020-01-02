
<style>
/* the div that holds the date picker calendar */
.dpDiv {
	}


/* the table (within the div) that holds the date picker calendar */
.dpTable {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 12px;
	text-align: center;
	color: #505050;
	background-color: #ece9d8;
	border: 1px solid #AAAAAA;
	}


/* a table row that holds date numbers (either blank or 1-31) */
.dpTR {
	}


/* the top table row that holds the month, year, and forward/backward buttons */
.dpTitleTR {
	}


/* the second table row, that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTR {
	}


/* the bottom table row, that has the "This Month" and "Close" buttons */
.dpTodayButtonTR {
	}


/* a table cell that holds a date number (either blank or 1-31) */
.dpTD {
	border: 1px solid #ece9d8;
	}


/* a table cell that holds a highlighted day (usually either today's date or the current date field value) */
.dpDayHighlightTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	}


/* the date number table cell that the mouse pointer is currently over (you can use contrasting colors to make it apparent which cell is being hovered over) */
.dpTDHover {
	background-color: #aca998;
	border: 1px solid #888888;
	cursor: pointer;
	color: red;
	}


/* the table cell that holds the name of the month and the year */
.dpTitleTD {
	}


/* a table cell that holds one of the forward/backward buttons */
.dpButtonTD {
	}


/* the table cell that holds the "This Month" or "Close" button at the bottom */
.dpTodayButtonTD {
	}


/* a table cell that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	color: white;
	}


/* additional style information for the text that indicates the month and year */
.dpTitleText {
	font-size: 12px;
	color: gray;
	font-weight: bold;
	}


/* additional style information for the cell that holds a highlighted day (usually either today's date or the current date field value) */ 
.dpDayHighlight {
	color: 4060ff;
	font-weight: bold;
	}


/* the forward/backward buttons at the top */
.dpButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	padding: 0px;
	}


/* the "This Month" and "Close" buttons at the bottom */
.dpTodayButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	}
</style>
#set ($idBayaranCukai = "")
#set ($tkh_bayaran = "")
#set ($nama_bank = "")
#set ($amaun = "")
#set ($no_rujbayaran = "")
##set ($no_cek = "")
#set ($no_resit = "")
#set ($tkh_resit = "")
#set ($idBaucer = "")

#if ( $pagemode == "baruBay" )
	#foreach ( $bayaranbaru in $Bayaran )
		#set ($idBayaranCukai = $bayaranbaru.id_bayarancukai)
    	#set ($tkh_bayaran = $util.getDateTime($bayaranbaru.tkh_bayaran, "dd/MM/yyyy"))
    	#set ($nama_bank = $bayaranbaru.nama_bank)
        #set ($selectBaucer = $selectBaucer)
    	#set ($no_rujbayaran = $bayaranbaru.no_rujbayaran)
        ##set ($no_cek = $bayaranbaru.no_cek)
		#set ($no_resit = $bayaranbaru.no_resit)
		#set ($tkh_resit = $util.getDateTime($bayaranbaru.tkh_resit, "dd/MM/yyyy"))
	#end
#else
	#foreach ( $bayaranbaru in $Bayaran )
		#set ($idBayaranCukai = $bayaranbaru.idBayaranCukai)
        #set ($idBaucer = $bayaranbaru.idBaucer)
    	#set ($tkh_bayaran = $bayaranbaru.tkh_bayaran)
    	#set ($nama_bank = $bayaranbaru.nama_bank)
    	#set ($amaun = $bayaranbaru.amaun)
        #set ($selectBaucer = $bayaranbaru.nama_daerah)
    	#set ($no_rujbayaran = $bayaranbaru.no_rujbayaran)
        ##set ($no_cek = $bayaranbaru.no_cek)
		#set ($no_resit = $bayaranbaru.no_resit)
		#set ($tkh_resit = $bayaranbaru.tkh_resit)
	#end
##elseif ( $pagemode == "simpanBay" )
	##foreach ( $bayaranbaru in $Bayaran )
		##set ($idBayaranCukai = $bayaranbaru.idBayaranCukai)
    	##set ($tkh_bayaran = $bayaranbaru.tkh_bayaran)
    	##set ($nama_bank = $bayaranbaru.nama_bank)
    	##set ($amaun = $bayaranbaru.amaun)
    	##set ($no_rujbayaran = $bayaranbaru.no_rujbayaran)
       ###set ($no_cek = $bayaranbaru.no_cek)
		##set ($no_resit = $bayaranbaru.no_resit)
		##set ($tkh_resit = $util.getDateTime($bayaranbaru.tkh_resit, "dd/MM/yyyy"))
	##end
#end

#set ($btnName = "value='Kosongkan'")
#if ($idBayaranCukai != "")
	##set ($btnName = "value='Batal'")
#end

#if ( $pagemode == "baruBay" )
<strong>PENDAFTARAN BAYARAN 1</strong>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Pendaftaran bayaran anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Bayaran anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br />
<fieldset>
<legend>MAKLUMAT BAYARAN
<br />

  </legend><table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
          <!-- <tr>
            <td width="3%"><div align="right"></div></td>
            <td width="15%"><div align="left"><strong>No. Bayaran</strong></div></td>
            <td width="1%">:</td>
            <td width="81%"><input type="text" name="txtNoBayaran" maxlength="400" size="40"  value="$idBayaranCukai" $inputstyle readonly></td>
          </tr> -->
          <tr>
            <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
            <td width="12%"><div align="left"><strong>Baucer</strong></div></td>
            <td width="1%">:</td>
            <td width="84%">$selectBaucer</td> 
          </tr>
          <tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font></div></td>
            <td><div align="left"><strong>No. Rujukan Bayaran</strong></div></td>
            <td>:</td>
            <td><input type="text" name="txtNoRujBayaran" id="txtNoRujBayaran" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_rujbayaran" $mode /></td>
        	</tr>
            <td><div align="right"><font color="#FF0000">*</font></div></td>
            <td><div align="left"><strong>Tarikh Bayaran</strong></div></td>
            <td>:</td>
            <td><input type="text" name="txdTarikhBayaran" id="txdTarikhBayaran" size="15" value="$tkh_bayaran" readonly="readonly">
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBayaran',false,'dmy');" style="display:$Style2"></td> 
          </tr>  
          <tr>
            <td><div align="right"><font color="#FF0000">*</font></div></td>
            <td><div align="left"><strong>Nama Bank</strong></div></td>
            <td>:</td>
            <td><input type="text" name="txtNamaBank" id="txtNamaBank" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$nama_bank" $mode></td>
          </tr>
          <tr>
           	<td><div align="right"><font color="#FF0000">*</font></div></td>
           	<td><div align="left"><strong>Amaun</strong></div></td>
            <td>:</td>
           	<td>RM <label>
                #if ( $pagemode == "baruBay" )
					#foreach($amaun in $AmaunBaucer)
              		<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun.amaun" $mode /> 		 			
                    <input type="hidden" name="idBaucer" value="$amaun.idBaucer" />
                    #end
				#elseif ( $pagemode == "viewBay" || $pagemode == "simpanBay")
					<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun" $mode /> 	
                	<input type="hidden" name="idBaucer" value="$idBaucer" />
                #end
            </label>
            </td>
       	  </tr>
                        
        <!--      
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>No. Cek</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoCek" id="txtNoCek" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_cek" readonly="readonly" disabled="disabled" $mode /></td>
         </tr> -->
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>No. Resit</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_resit"  $mode /></td>
         </tr>
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Resit</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tkh_resit" readonly="readonly" disabled="disabled">
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" ></td> 
         </tr>
        
        
        
        </table></td>
	  </tr>
      <tr>
		<td align="center">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" >&nbsp;&nbsp;<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
  <input type="hidden" name="idBayaranCukai" value="$idBayaranCukai">
  <input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran">
  <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
  <input type="hidden" name="idNegeri" value="$idNegeri">
  <input type="hidden" name="negeri" value="$negeri">
  <input type="hidden" name="txtNoBayaran" value="$idBayaranCukai">
  <input type="hidden" name="socbayaran" value="$peringkat_bayaran" >
  <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  <input type="hidden" name="command1" value="$command1">  
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>


#else
<strong>KEMASKINI BAYARAN</strong>

<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Pendaftaran bayaran anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Bayaran anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br />
<fieldset>
<legend>Maklumat Bayaran</legend>
<br />

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
           <!-- <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="15%"><div align="left"><strong>No. Bayaran</strong></div></td>
                <td width="1%">:</td>
                <td width="81%"><input type="text" name="txtNoBayaran" maxlength="400" size="40"  value="$idBayaranCukai" $inputstyle readonly></td>
          </tr> -->
          <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
            <td width="12%"><div align="left"><strong>Baucer</strong></div></td>
            <td width="1%">:</td>
            <td width="84%">$selectBaucer</td> 
          </tr>
          <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Rujukan Bayaran</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoRujBayaran" id="txtNoRujBayaran" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_rujbayaran" $mode /></td>
          </tr>
          <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Bayaran</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBayaran" id="txdTarikhBayaran" size="15" value="$tkh_bayaran" readonly="readonly">
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBayaran',false,'dmy');" style="display"></td> 
          </tr>  
          <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Nama Bank</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNamaBank" id="txtNamaBank" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$nama_bank" $mode></td>
          </tr>
          <tr>
              	<td><div align="right"><font color="#FF0000">*</font></div></td>
            	<td><div align="left"><strong>Amaun</strong></div></td>
                <td>:</td>
            	<td>RM 
              	<label>
                #if ( $pagemode == "baruBay" )
					#foreach($amaun in $AmaunBaucer)
              		<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun.amaun" $mode /> 		 			
                    <input type="hidden" name="idBaucer" value="$amaun.idBaucer" />
                    #end
				#elseif ( $pagemode == "viewBay" || $pagemode == "simpanBay")
					<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun" $mode /> 	
                	<input type="hidden" name="idBaucer" value="$idBaucer" />
                #end
                
              	</label></td>
       	  </tr>    
       <!--       
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>No. Cek</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoCek" id="txtNoCek" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_cek" $mode /></td>
         </tr> -->
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>No. Resit</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_resit" $mode /></td>
         </tr>
         <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Resit</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tkh_resit" readonly="readonly">
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display"></td> 
         </tr>
        
        
        
        </table></td>
	  </tr>
      <tr>
		<td align="center">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" >&nbsp;&nbsp;<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
  <input type="hidden" name="idBayaranCukai" value="$idBayaranCukai">
  <input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran">
  <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
  <input type="hidden" name="idNegeri" value="$idNegeri">
  <input type="hidden" name="negeri" value="$negeri">
  <input type="hidden" name="txtNoBayaran" value="$idBayaranCukai">
  <input type="hidden" name="socbayaran" value="$peringkat_bayaran" >
  <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  <input type="hidden" name="command1" value="$command1">  
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>

#end
<script>
function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function RemoveNonNumeric( strString )
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
function clearText(field) {
	if(field.defaultValue==field.value){
		field.value="";}
}
function addText(field) {
	if(field.value==""){
		field.defaultValue="";}
}
function Kembali() {
	document.${formName}.action = "";
	document.${formName}.command.value = "cukaiBayaran";
	document.${formName}.submit();
}
function Batal() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "baru";
	document.${formName}.command.value = "tambahBayaran";
	document.${formName}.submit();
}
function doChangeBaucer() {
doAjaxCall${formName}("doChangeBaucer");
}
function Simpan() {
	if(document.${formName}.pagemode.value == "baruBay"){
		if(document.${formName}.socBaucer.value == ""){
			alert('Sila pilih " Baucer " terlebih dahulu.');
  			document.${formName}.socBaucer.focus(); 
			return; }
	}
	if(document.${formName}.txdTarikhBayaran.value == ""){
		alert('Sila masukkan " Tarikh Bayaran " terlebih dahulu.');
  		document.${formName}.txdTarikhBayaran.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaBank.value == ""){
		alert('Sila masukkan " Nama Bank " terlebih dahulu.');
  		document.${formName}.txtNamaBank.focus(); 
		return; 
	}
	if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan " Amaun " terlebih dahulu.');
  		document.${formName}.txtAmaun.focus(); 
		return; 
	}
	if(document.${formName}.txtNoRujBayaran.value == ""){
		alert('Sila masukkan " No Rujukan Bayaran " terlebih dahulu.');
  		document.${formName}.txtNoRujBayaran.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	//document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBayaran";
	document.${formName}.pagemode.value = "simpanBay";	
	document.${formName}.submit();
}

document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;

</script>
