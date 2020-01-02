<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<fieldset><legend>MAKLUMAT FAIL</legend>
				<!--<table>	-->
				<table width="100%">
        		##foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
					        		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
						            <td width="30%">Negeri</td>
						            <td width="1%">:</td>
						            <td width="68%">$selectNegeri</td>			
			              		</tr>
			              		<tr>
						          	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
						            <td>Kementerian</td>
						            <td>:</td>
						            <td>$selectKementerian</td>					        							        						
			              		</tr>
			              		<tr>
						         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
						            <td>Agensi</td>
						            <td>:</td>
						            <td>$selectAgensi</td>				
			              		</tr>
			              		<tr>
						         	<td>&nbsp;</td>
						            <td>Urusan</td>
						            <td>:</td>
						            <td>906 - PENSWASTAAN TANAH</td>				
			              		</tr>
			              		<tr>
							    	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
							        <td>Sub Urusan</td>
							        <td>:</td>
							        <td>$selectSuburusan</td>			
			              		</tr>
			              		<tr>
						         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
						            <td>Status Tanah</td>
						            <td>:</td>
						            <td>$selectStatusTanah</td>			
			              		</tr>
			              		<tr>
						         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
						         	<td>Jenis Fail</td>
						            <td>:</td>
						            <td>$selectJenisFail</td>			              		
			              		</tr>
			              		<tr>
					        		<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
           							<td valign="top">Tajuk/Projek</td>
            						<td valign="top">:</td>
            						<td valign="top">
            							<textarea name="txtTajuk" id="txtTajuk" rows="5" cols="41" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$!permohonan.getPermohonan().getTujuan()</textarea>
            						</td>				        									              		
					        	</tr>		

				       		</table>		
						</td>
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr>					        			
					        		<td width="1%">&nbsp;</td>
						            <td width="30%"><div align="left">No. Fail Seksyen</div></td>
						            <td width="1%"><div align="center">:</div></td>
						            <td width="68%"><input type="text" name="noFail" size="40" maxlength="100" disabled="disabled" class="disabled" readonly="readonly" value="$!permohonan.getPermohonan().getPfdFail().getNoFail()"/></td>				        					
			              		</tr>
			              		<tr>
						      		<td width="1%">#if ($mode != 'view')<span class="style1">*</span> #end </td>
					        		<td width="30%"><div align="left">No. Fail UKAS</div></td>
					        		<td width="1%"><div align="center">:</div></td>
            						<td width="68%"><input type="text" name="txtNoFailKJP" size="40" maxlength="100" $readonly class="$inputTextClass" value="$!permohonan.getNoRujukanKJP()" onblur="this.value=this.value.toUpperCase();" /></td>
		              			</tr>		
			              		<tr>
						      		 <td width="1%">#if ($mode != 'view')<span class="style1">*</span> #end </td> 
						      		<!--<td width="1%">&nbsp;</td>-->
					        		<td width="30%"><div align="left">Tarikh Surat UKAS</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						          	<td width="68%"><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" value="$!permohonan.getPermohonan().getTarikhSurat()" $readonly class="$inputTextClass" onblur="check_date(this)" />
						     	#if ($mode != 'view')
						            <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
						        #end            
						            </td>	
		              			</tr>
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%">&nbsp;</td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txtNoFailLain" size="28" maxlength="50" value="$!permohonan.getNoRujukanLain()" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase();" />
					        			#if ($mode != 'view')
							        		<a href="javascript:tambahFailLain('$idFail','tambah')" class="style1">...</a>
								        #end
					        		</td>							              			
		              			</tr>			              			        			
 							#foreach ($agenda in $senaraiFailLain)
			                  	#if($!agenda.isURL.equals('0'))
					       		<tr>
									<td >&nbsp;</td>
							        <td >&nbsp;</td>
							        <td >&nbsp;</td>
		         					<td >$!agenda.noFail</td>					              			
				              	</tr>	
				              	#else
			       				<tr>
									<td >&nbsp;</td>
							        <td >&nbsp;</td>
							        <td >&nbsp;</td>
		         					<td >
		   								<a href="javascript:paparPautan('$agenda.idFailURL','Pajakan')" class="pautanms">$!agenda.noFail</a>      					
		         					</td>					              			
				              	</tr>		              	
				              	#end	
							#end  				
				              	
				       		</table>	        				
    					</td>
    				</tr>
				</table>
			</fieldset>
		</td>
    </tr>
   
  <!-- <tr>
    <td colspan="2">&nbsp;</td>
  </tr> -->
  #if ($mode != 'view')
  <tr>
    <td valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td align=center>

    #if($!permohonan.getPermohonan().getFlagMohonFail().equalsIgnoreCase(""))
    	#set($flagFail = "")
    #else
    	#set($flagFail = $!permohonan.getPermohonan().getFlagMohonFail())
    #end
    
    #if ($mode == 'new')
    	<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
 		<input type="reset" class="stylobutton100" name="cmdReset" value="Kosongkan" onclick = "javascript:kosongkanPendaftaran()">
    	<input type="button" class="stylobutton100" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="kembali()"/>
    #end
    #if($mode == 'view')
    	#if($flagFail == "N")
    		<input type="button" class="stylobutton100" style="width:auto !important" name="cmdHantarSahkan" id="cmdHantarSahkan" value="Hantar Pengesahan" onclick="javascript:doHantarPengesahan()" />
        #elseif($flagFail == "H") <!-- and role $portal_role -->
        	<input type="button" class="stylobutton100" style="width:auto !important" name="cmdSahkan" id="cmdSahkan" value="Sahkan Permohonan" onclick="javascript:doSahkan()" />
        #end
        
        <input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:Kemaskini()" />
        <input type="button" class="stylobutton100"   name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" />
        <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()" />
	#elseif ($mode == 'update')
        <input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:saveUpdate()" />
        <input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
        <input type="button" class="stylobutton100" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal()" />
	#end
    </td>
  </tr>
  
    <tr>
  	<td>
  		 <fieldset id="tabledokumensurat" style="display:none;">
			<legend>SENARAI SURAT/DOKUMEN</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!idPermohonan','&template=NoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">KULIT FAIL</a></td>
			  </tr>
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!idPermohonan','&template=rptNoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">DOKET</a></td>
			  </tr>  
   
			  </table>
			</fieldset>
	  </td>
  </tr>
</table>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/> 
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPermohonan" type="hidden" value="$idPermohonan"/>
</p>
<script>
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function simpan() {
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	//if(document.${formName}.socAgensi.value == ""){
		//alert('Sila pilih Sub Urusan.');
  		//document.${formName}.socAgensi.focus(); 
		//return; 
	//}
	if(document.${formName}.socStatusTanah.value == ""){
		alert('Sila pilih Status Tanah.');
  		document.${formName}.socStatusTanah.focus(); 
		return; 
	}
	if(document.${formName}.socJenisFail.value == ""){
		alert('Sila pilih Jenis Fail.');
  		document.${formName}.socJenisFail.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan No. Fail KJP.');
  		document.${formName}.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSuratKJP.value == ""){
		alert('Sila masukkan Tarikh Surat KJP.');
  		document.${formName}.tarikhSuratKJP.focus(); 
		return; 
	}
	//if(document.${formName}.txtNoFailLain.value == ""){
//		alert('Sila masukkan No. Fail Lain.');
//  		document.${formName}.txtNoFailLain.focus(); 
//		return; 
//	}
	//if(document.${formName}.tarikhAgihan.value == ""){
	//	alert('Sila masukkan Tarikh Agihan.');
  	//	document.${formName}.tarikhAgihan.focus(); 
	//	return; 
	//}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenswastaan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.submit();
}
	function kembali() {	
		document.${formName}.actionPenswastaan.value = "";
		//document.${formName}.submit();
		var urlTemp = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.penswastaan.FrmPenswastaanSenaraiFailView";
		document.${formName}.action = urlTemp;
		document.${formName}.submit();
	}
	
function Kemaskini(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function saveUpdate(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdate";
	doAjaxCall${formName}("");
}

//by Rosli 2010/04/01
function tambahFailLain(id,command){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmFailLainKemaskini?idFailLama="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
  	if ((document.window != null) && (!hWnd.opener))
   		hWnd.opener = document.window;
  	if (hWnd.focus != null) hWnd.focus();
}


// 18/07/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//2010/07/18 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	function kosongkanPendaftaran() {
		document.${formName}.reset();
		document.${formName}.actionPenswastaan.value = "daftarBaruReset";
		document.${formName}.mode.value = "new";	
		doAjaxCall${formName}("");
		
	}

</script>

<!-- JAVASCRIPT UTK PENGESAHAN -->
#parse("app/htp/penswastaan/fail/script.jsp")


