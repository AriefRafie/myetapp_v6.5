<!-- frmCukaiTambahBayaran.jsp -->
<!-- CL-02-018 -->
#set ($idBayaranCukai = "")
#set ($tkh_bayaran = "")
#set ($nama_bank = "")
#set ($amaun = "")
#set ($no_rujbayaran = "")
##set ($no_cek = "")
#set ($no_resit = "")
#set ($tkh_resit = "")
#set ($idBaucer = "")

#if ( $pagemode == "baru" )
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

#end

#set ($btnName = "value='Kosongkan'")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
	
		<td>
		</td>
	</tr>
#if ( $pagemode == "baru" )
	<tr>
		<td>

			<fieldset><legend>MAKLUMAT BAYARAN</legend>
				<table width="100%" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Baucer</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							$!selectBaucer
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Rujukan Bayaran (No. Cek/EFT)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtNoRujBayaran" id="txtNoRujBayaran" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!no_rujbayaran"/>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Bayaran</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txdTarikhBayaran" id="txdTarikhBayaran" size="15" value="$!tkh_bayaran" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBayaran',false,'dmy');" style="display">
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nama Bank</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtNamaBank" id="txtNamaBank" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!nama_bank" >
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Amaun(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							#if ( $pagemode == "baru" )
								##foreach($amaun in $AmaunBaucer)
			              		<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="30" onblur="validateCurrency(this,this.value);addText(this);" value="$!amaun.amaun" /> 		 			
			                    <input type="hidden" name="idBaucer" value="$amaun.idBaucer" />
			                    ##end
							#elseif ( $pagemode == "viewBay" || $pagemode == "simpanBay")
								<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="30" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun" $mode /> 	
			                	<input type="hidden" name="idBaucer" value="$idBaucer" />
			                #end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Resit</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!no_resit" $mode />
						</td>
					</tr>	
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Resit</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$tkh_resit" onblur="check_date(this)" $mode>
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display">
						</td>
					</tr>																
				</table>
			</fieldset>
			
		</td>
	</tr>
	<tr>
		<td align="center">
			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBayaran()">
			<!-- <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Simpan()" > -->
			<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" $btnName >
			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembaliDariTambahBayaran()">
		</td>
    </tr>
#elseif($pagemode == "viewBay"||$pagemode == "kemaskinibayaran") 
	<tr>
		<td>
			
			<fieldset><legend>MAKLUMAT BAYARAN</legend>
				<table width="100%" border="0">
                	<tr>
						<td valign="top" width="1%">
				        #if ($pagemode != "viewBay" && $pagemode != "kemaskinibayaran")<span class="labelmandatory">*</span>#end</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Baucer</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							#if ($pagemode == "viewBay"||$pagemode == "kemaskinibayaran")
								$!bcukai.getDaerah().getNamaDaerah()
							##elseif ($pagemode == "kemaskinibayaran")
							#else
								$!selectBaucer
							#end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">#if ($pagemode != "viewBay")<span class="labelmandatory">*</span>#end</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Rujukan Bayaran (No. Cek/EFT)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	#if ($pagemode == "viewBay")
								$!bcukai.getBayaran().getNoRujukan()
							#else
								<input type="text" name="txtNoRujBayaran" id="txtNoRujBayaran" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!bcukai.getBayaran().getNoRujukan()" $mode />
							#end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">#if ($pagemode != "viewBay")<span class="labelmandatory">*</span>#end</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Bayaran</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				     		#if ($pagemode == "viewBay")
								$!bcukai.getBayaran().getTarikhBayaranFormat()
							#else
							<input type="text" name="txdTarikhBayaran" id="txdTarikhBayaran" size="15" value="$!bcukai.getBayaran().getTarikhBayaranFormat()" onblur="check_date(this)" $mode>
            		<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhBayaran',false,'dmy');" style="display">
							#end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">#if ($pagemode != "viewBay")<span class="labelmandatory">*</span>#end</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nama Bank</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				       		#if ($pagemode == "viewBay")
								$!bcukai.getBayaran().getBank()
							#else
								<input type="text" name="txtNamaBank" id="txtNamaBank" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!bcukai.getBayaran().getBank()" $mode>
							#end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">#if ($pagemode != "viewBay")<span class="labelmandatory">*</span>#end</td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Amaun(RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							#if ( $pagemode == "baru" )
								#foreach($amaun in $AmaunBaucer)
			              		<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="30" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun.amaun" $mode /> 		 			
			                    <input type="hidden" name="idBaucerX" value="$amaun.idBaucer" />
			                    #end
							#elseif ($pagemode == "simpanBay" || $pagemode == "kemaskinibayaran" )
								<input type="text" name="txtAmaun" id="txtAmaun" maxlength="16" size="30" onblur="validateCurrency(this,this.value);addText(this);" value="$amaun" $mode /> 	
			                	<input type="hidden" name="idBaucerX" value="$idBaucer" />
			                #elseif ( $pagemode == "viewBay")
			            		<input type="hidden" name="idBaucerX" value="$!bcukai.getBaucer().getId()" />
			                	$!bcukai.getBayaran().getJumlahFormat()
			                #end
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">No. Resit</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	#if ($pagemode == "viewBay")
								$!bcukai.getResit().getNoRujukan()
							#else
								<input type="text" name="txtNoResit" id="txtNoResit" maxlength="60" size="30" onkeyup="this.value=this.value.toUpperCase();" value="$!bcukai.getResit().getNoRujukan()" $mode />
							#end
						</td>
					</tr>	
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Resit</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				      		#if ($pagemode == "viewBay")
								$!bcukai.getResit().setTarikhFormat()
							#else
								<input type="text" name="txdTarikhResit" id="txdTarikhResit" size="15" value="$!bcukai.getResit().setTarikhFormat()" onblur="check_date(this)" $mode>
            					<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhResit',false,'dmy');" style="display">
            				#end

						</td>
					</tr>																
				</table>
			</fieldset>
			
		</td>
	</tr>
	
	<tr>
		<td align="center">
			#if($pagemode == "kemaskinibayaran")
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan('$!bcukai.getId()','$!bcukai.getBaucer().getId()')"> 
			#elseif	($pagemode == "viewBay")
			<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini('$!bcukai.getId()')" >
			<input class="stylobutton100" type="button" name="cmdDelete" value="Hapus" onclick="hapusBayaran('$!bcukai.getId()')" >
			#end
			<!-- <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()"> -->
			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembaliDariTambahBayaran()">
		</td>
    </tr>
#end

  <input type="hidden" name="XidPermohonan" value="$idPermohonan">
  <input type="hidden" name="idBayaranCukai" value="$!idBayaranCukai">
  <input type="hidden" name="XidPeringkatbayaran" value="$idPeringkatbayaran">
  <input type="hidden" name="Xperingkat_bayaran" value="$peringkat_bayaran" >
  <input type="hidden" name="XidNegeri" value="$idNegeri">
  <input type="hidden" name="Xnegeri" value="$negeri">
  <input type="hidden" name="XtxtNoBayaran" value="$idBayaranCukai">
  <input type="hidden" name="Xsocbayaran" value="$peringkat_bayaran" >
  <input type="hidden" name="XtabId" id="tabId" value="$selectedTab"/>
  <input type="hidden" name="Xcommand1" value="$command1">  
  <input type="hidden" name="Xpagemode" value="$pagemode">
  <input type="hidden" name="Xstyle1" value="$Style1">
  <input type="hidden" name="Xstyle2" value="$Style2">
  <input type="hidden" name="XidBaucer" />
  
</table>

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
	if(document.${formName}.pagemode.value == "baru"){
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

//document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
//document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
//document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
	
</script>
