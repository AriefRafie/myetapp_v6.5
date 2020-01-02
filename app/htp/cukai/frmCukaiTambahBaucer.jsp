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
<!--
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
-->
<table border="0" width="98%">
	<tr>
		<td>


<fieldset><legend>MAKLUMAT BAUCER</legend>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top">
			<table width="100%" border="0">
				<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Negeri/Daerah  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							$selectDaerah
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Baucer</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
  						#if($!pagemode.equals('view'))
  							$!bcukai.getTarikhFormat()
						#else
							<input type="text" name="txdTarikhBaucer" id="txdTarikhBaucer" size="15" value="$!bcukai.getTarikhFormat()" $mode>
  						#end
  						#if(!$!pagemode.equals('view'))
            					<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBaucer',false,'dmy');" style="display">
						#end
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Baucer</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
  						#if($!pagemode.equals('view'))
  							$!bcukai.getNoRujukan()
						#else				        
							<input type="text" name="txtNoBaucer" size="30" value="$!bcukai.getNoRujukan()" onkeyup="this.value=this.value.toUpperCase();" $mode>
    					#end
                			<input type="hidden" name="txtIdBaucer" size="30" value="$!bcukai.getId()"  readonly="readonly">
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%"></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Cek/EFT</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
  						#if($!pagemode.equals('view'))
  							$!bcukai.getResit().getTarikhFormat()
						#else
							<input type="text" name="txdTarikhCek" id="txdTarikhCek" size="15" value="$!bcukai.getResit().getTarikhFormat()" $mode>
                		#end
  						#if(!$!pagemode.equals('view'))
          			<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhCek',false,'dmy');" style="display">
						#end
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%"></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Cek/EFT</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
  						#if($!pagemode.equals('view'))
  							$!bcukai.getResit().getNoRujukan()
						#else							
							<input type="text" name="txtNoCek" size="30" id="txtNoCek" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$!bcukai.getResit().getNoRujukan()" $mode>
                		#end
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
						</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Terima</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
  						#if($!pagemode.equals('view'))
  							$!bcukai.getTarikhTerimaFormat()
						#else
							<input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$!bcukai.getTarikhTerimaFormat()" $mode/>
                		#end
 						#if(!$!pagemode.equals('view'))
                			<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display">
						#end
						</td>
				</tr>
				<tr>
						<td valign="top" width="1%"><span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Amaun(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
						#if ( $pagemode == "baru" || $!pagemode.equals('kemaskinibaucer'))
							##foreach($amaun in $AmaunCukai)
              					<input type="text" name="txtAmaunBaucer" id="txtAmaunBaucer" maxlength="16" size="30"  onblur="validateCurrency(this,this.value);addText(this);" value="$!Util.formatDecimal($!bcukai.getJumlah())" /> 		 			
                    			<input type="hidden" name="idDaerah" value="$amaun.idDaerah" />
                    		##end
						#elseif ( $pagemode == "view" || $pagemode == "simpanB")
							$!Util.formatDecimal($!bcukai.getJumlah()) <!-- <input type="text" name="txtAmaunBaucer" id="txtAmaunBaucer" maxlength="16" size="30"  onblur="validateCurrency(this,this.value);addText(this);" value="$!bcukai.getJumlah()" $mode/> -->
                			<input type="hidden" name="idDaerah" value="$idDaerah" />
                		#end
						</td>
				</tr>
        	</table>
        </td>
	  </tr>

    </tbody>
  </table>


</fieldset>
			
		</td>
	</tr>
	      <tr>
		<td align="center">
		#if($!pagemode.equals('baru')||$!pagemode.equals('kemaskinibaucer'))
			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBaucer()">
		#end
		#if($!pagemode.equals('view'))
			<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniBaucer()" >
			<input class="stylobutton100" type="button" name="cmdhapus" value="Hapus" onclick="hapusBaucer()" >
		#end
		#if($!pagemode.equals('baru'))
			<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" $btnName >
		#end
			<!----> 
			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="kembaliDariTambahBaucer()">
			
		</td>
      </tr>
</table>

	  <input type="hidden" name="XidPermohonan" value="$idPermohonan">
	  <input type="hidden" name="XidNegeri" value="$idNegeri">
	  <input type="hidden" name="Xnegeri" value="$negeri">
	  <input name="XtabId" type="hidden" id="tabId" value="$selectedTab"/>
	  <input type="hidden" name="Xperingkat_bayaran" value="$peringkat_bayaran" >
	  <input type="hidden" name="XidPeringkatbayaran" value="$idPeringkatbayaran">
	  <input type="hidden" name="XidBaucer" value="$idBaucer">
	  <input type="hidden" name="Xcommand1" value="$command1">  
	  <input type="hidden" name="Xpagemode" value="$pagemode">
	  <input type="hidden" name="Xstyle1" value="$Style1">
	  <input type="hidden" name="Xstyle2" value="$Style2">
	  <input type="hidden" name="xtahun_cukai" value="$tahun_cukai">
	  <input type="hidden" name="bil" value="$!bcukai.getBaucer().getBil()">

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
	if(document.${formName}.txdTarikhBaucer.value == ""){
		alert('Sila masukkan " Tarikh Baucer " terlebih dahulu.');
  		document.${formName}.txdTarikhBaucer.focus(); 
		return; 
	}	
	if(document.${formName}.txtNoBaucer.value == ""){
		alert('Sila masukkan " No Baucer " terlebih dahulu.');
  		document.${formName}.txtNoBaucer.focus(); 
		return; 
	}
	
	if(document.${formName}.txtAmaunBaucer.value == ""){
		alert('Sila masukkan " Amaun Baucer " terlebih dahulu.');
  		document.${formName}.txtAmaunBaucer.focus(); 
		return; 
	}
/*	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
*/
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBaucer";
	document.${formName}.pagemode.value = "simpanB";	
	document.${formName}.submit();
}

//document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
//document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
//document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
</script>
