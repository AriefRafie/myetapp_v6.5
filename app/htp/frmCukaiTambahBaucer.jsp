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
#set ($idBaucer = "")
#set ($tkh_baucer = "")
#set ($no_resit = "")
#set ($tkh_resit = "")
#set ($jumlahCukai = "")
#set ($amaun_baucer = "")
#set ($tkh_terima = "")
#set ($noBaucer = "")


#if ( $pagemode == "baru" )
	#foreach ( $baucerbaru in $Baucer )
		#set ($idBaucer = $baucerbaru.id_baucer)
        #set ($noBaucer = $baucerbaru.no_baucer)
   		#set ($idPeringkatbayaran = $baucerbaru.idPeringkatbayaran)
        #set ($selectDaerah = $selectDaerah)
   		#set ($tkh_baucer = $util.getDateTime($baucerbaru.tkh_baucer, "dd/MM/yyyy"))
   		#set ($no_resit = $baucerbaru.no_resit)
   		#set ($tkh_resit = $util.getDateTime($baucerbaru.tkh_resit, "dd/MM/yyyy"))
   		#set ($amaun_baucer = $baucerbaru.amaun_baucer)
   		#set ($tkh_terima = $util.getDateTime($baucerbaru.tkh_terima, "dd/MM/yyyy"))
	#end
#elseif ( $pagemode == "view" )
	#foreach ( $baucerbaru in $Baucer )
		#set ($idBaucer = $baucerbaru.id_baucer)
        #set ($noBaucer = $baucerbaru.no_baucer)
   		#set ($idPeringkatbayaran = $baucerbaru.idPeringkatbayaran)
        #set ($selectDaerah = $baucerbaru.nama_daerah)
   		#set ($tkh_baucer = $baucerbaru.tkh_baucer)
   		#set ($no_resit = $baucerbaru.no_resit)
   		#set ($tkh_resit = $baucerbaru.tkh_resit)
   		#set ($amaun_baucer = $baucerbaru.amaun_baucer)
        #set ($idDaerah = $baucerbaru.idDaerah)
   		#set ($tkh_terima = $baucerbaru.tkh_terima)
	#end    
#elseif ( $pagemode == "simpanB" )
	#foreach ( $baucerbaru in $Baucer )
		#set ($idBaucer = $baucerbaru.id_baucer)
        #set ($noBaucer = $baucerbaru.no_baucer)
   		#set ($idPeringkatbayaran = $baucerbaru.idPeringkatbayaran)
        #set ($selectDaerah = $baucerbaru.nama_daerah)
   		#set ($tkh_baucer = $baucerbaru.tkh_baucer)
   		#set ($no_resit = $baucerbaru.no_resit)
   		#set ($tkh_resit = $baucerbaru.tkh_resit)
   		#set ($amaun_baucer = $baucerbaru.amaun_baucer)
        #set ($idDaerah = $baucerbaru.idDaerah)
   		#set ($tkh_terima = $baucerbaru.tkh_terima)
	#end    
#end

#set ($btnName = "value='Kosongkan'")
#if ($idBaucer != "")
	##set ($btnName = "value='Batal'")
#end
#if ( $pagemode == "baru" )
<strong>PENDAFTARAN BAUCER</strong>
#elseif ( $pagemode == "view" )
<strong>KEMASKINI BAUCER</strong>
#end
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Baucer telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Baucer telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br />
<fieldset>
<legend>MAKLUMAT BAUCER
<br />
  </legend>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="15%"><div align="left"><strong>Daerah</strong></div></td>
                <td width="1%">:</td>
				<td width="81%">$selectDaerah</td>               
              </tr>
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="15%"><div align="left"><strong>No. Baucer</strong></div></td>
                <td width="1%">:</td>
                <td width="81%"><input type="text" name="txtNoBaucer" size="30" value="$noBaucer" onkeyup="this.value=this.value.toUpperCase();">
                <input type="hidden" name="txtIdBaucer" size="30" value="$idBaucer"  readonly="readonly"></td>
          	  </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Baucer</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBaucer" id="txdTarikhBaucer" size="15" value="$tkh_baucer" readonly="readonly">
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBaucer',false,'dmy');" style="display"></td> 
              </tr>  
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>No. Cek/FT</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoCek" id="txtNoCek" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$no_resit" $mode></td>
              </tr>            
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Cek/FT</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhCek" id="txdTarikhCek" size="15" value="$tkh_resit" readonly="readonly">
          			<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhCek',false,'dmy');" style="display"></td>
              </tr>
              <tr>
              	<td><div align="right"><font color="#FF0000">*</font></div></td>
            	<td><div align="left"><strong>Amaun</strong></div></td>
                <td>:</td>
            	<td>RM 
              	<label>
                #if ( $pagemode == "baru" )
					#foreach($amaun in $AmaunCukai)
              		<input type="text" name="txtAmaunBaucer" id="txtAmaunBaucer" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun.jumlahCukai" $mode /> 		 			
                    <input type="hidden" name="idDaerah" value="$amaun.idDaerah" />
                    #end
				#elseif ( $pagemode == "view" || $pagemode == "simpanB")
					<input type="text" name="txtAmaunBaucer" id="txtAmaunBaucer" maxlength="16" size="19" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun_baucer" $mode>
                	<input type="hidden" name="idDaerah" value="$idDaerah" />
                #end
         
            	</label></td>
          	  </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Terima</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$tkh_terima" readonly="readonly" />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display"></td>
              </tr>
        </table></td>
	  </tr>
      <tr>
		<td align="center">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" >&nbsp;&nbsp;<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
  <input type="hidden" name="idNegeri" value="$idNegeri">
  <input type="hidden" name="negeri" value="$negeri">
  <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  <input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
  <input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran">
  <input type="hidden" name="idBaucer" value="$idBaucer">
  <input type="hidden" name="command1" value="$command1">  
  <input type="hidden" name="pagemode" value="$pagemode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>
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

function clearText(field) {
	if(field.defaultValue==field.value){
		field.value="";}
}

function addText(field) {
	if(field.value==""){
		field.defaultValue="";}
}

function doChangeDaerah() {
	doAjaxCall${formName}("tambahBaucer","pagemode=baru");
}

function Kembali() {
	document.${formName}.action = "";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.pagemode.value = "baucerview";
	document.${formName}.submit();
	//window.close();
}

function Batal() {
	document.${formName}.action = "";
	document.${formName}.pagemode.value = "baru";
	document.${formName}.command.value = "tambahBaucer";
	document.${formName}.submit();
}

function Simpan() {
	if(document.${formName}.pagemode.value == "baru"){
		if(document.${formName}.socDaerah.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
  			document.${formName}.socDaerah.focus(); 
			return; }
	}
	if(document.${formName}.txtNoBaucer.value == ""){
		alert('Sila masukkan " No Baucer " terlebih dahulu.');
  		document.${formName}.txtNoBaucer.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhBaucer.value == ""){
		alert('Sila masukkan " Tarikh Baucer " terlebih dahulu.');
  		document.${formName}.txdTarikhBaucer.focus(); 
		return; 
	}	
	if(document.${formName}.txtAmaunBaucer.value == ""){
		alert('Sila masukkan " Amaun Baucer " terlebih dahulu.');
  		document.${formName}.txtAmaunBaucer.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBaucer";
	document.${formName}.pagemode.value = "simpanB";	
	document.${formName}.submit();
}

document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
</script>
