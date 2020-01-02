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
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPermohonan" type="hidden" value="$idPermohonan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">
    
    <fieldset>
    <legend><strong>MAKLUMAT FAIL</strong></legend>
    
   		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
         <tr>
          	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Negeri</td>
            <td width="1%">:</td>
            <td width="70%">$selectNegeri</td>
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
         <!--<tr>
         	<td>&nbsp;</td>
            <td>Sub Urusan</td>
            <td>:</td>
            <td>$selectSuburusan</td>
         </tr>-->
  		<input name="socSuburusan" type="hidden" id="socSuburusan" value="62"/>
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
         	<td width="1%">&nbsp;</td>
            <td width="28%">No. Fail Seksyen</td>
            <td width="1%">:</td>
            <td width="70%">
            	<input type="text" size="30" maxlength="100" name="noFail"  id="noFail" value="$!beanMaklumatPermohonan.noFail"  readonly="readonly" class="disabled"/>        	
            </td>
         </tr>
        
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>No. Fail UKAS</td>
            <td>:</td>
            <td><input type="text" size="30" maxlength="100" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFailKJP" onblur="this.value=this.value.toUpperCase();" /></td>
         </tr>
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <!-- Komen pada 24/06/2010 <td valign="top">Tarikh Surat KJP</td>-->
            <td valign="top">Tarikh Surat UKAS</td>
            <td>:</td>
            <td><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr>
         <tr>
         <td>&nbsp;</td>
         <td>No. Fail Lain</td>
         <td>:</td>
         <td>
         	<input type="text" size="30" maxlength="100" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onblur="this.value=this.value.toUpperCase();" $readonly class="$inputTextClass"/>
        	#if ($mode == 'view')
        		<a href="javascript:tambahFailLain('$idFail','tambah')" class="style1">...</a>
	        #end
        	</td>
         </tr>
   		#foreach ($agenda in $AgendaMesyuarat)
         
		<tr>
         	<td></td>
         	<td></td>
        	<td></td>
         	<td>$agenda.noFail</td>
        </tr>
        #end
        <!-- <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tarikh Agihan</td>
            <td>:</td>
            <td> 
            	<input type="text" name="tarikhAgihan" id="tarikhAgihan" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhAgihan"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr> -->
         <input type="hidden" name="tarikhAgihan" value="$sekarang">
         <tr>
         	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
           <td valign="top">Tajuk/Projek</td>
            <td valign="top">:</td>
            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea></td>
         </tr>
         #end
        </table>
    </fieldset>    
    </td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    #if ($mode == 'new')
    <input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
    <input type="button" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    #end
    #if($mode == 'view')
        <input type="button" class="stylobutton" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:Kemaskini()" />
        <input class="stylobutton" type="button"  name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" />
        <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()" />
	#elseif ($mode == 'update')
        <input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:saveUpdate()" />
        <input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
        <input type="button" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal()" />
	#end
    </td>
  </tr>
  
    <tr>
  	<td colspan=2>
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
	if(document.${formName}.tarikhAgihan.value == ""){
		alert('Sila masukkan Tarikh Agihan.');
  		document.${formName}.tarikhAgihan.focus(); 
		return; 
	}
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

</script>
