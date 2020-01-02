<style type="text/css">
<!--
.style40 {color: #0000FF}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>



#if ($bolehProceed == "false")
<h2>Permohonan belum selesai!!.</h2><a href="javascript:history.go(-1)">Kembali</a>
#else
::: $DetailPemohon
#foreach ($fail in $DetailPemohon)
:::: $fail.id_faillama

	#set ($idPemohon = $fail.id_pemohon)
	#set ($noPermohonanOnline = $fail.noPermohonan)
	#set ($idfailSeksyen8 = $fail.id_faillama)
	#set ($nofaillama = $fail.nofaillama)
	#set ($nofail = $fail.nofail)
    #set ($idfaillama = $fail.id_faillama)
	#set ($daerah = $fail.namadaerah)
    #set ($nosubjaket = $fail.nosubjaket)
	#set ($negeri = $fail.namanegeri)
	#set ($pemohon = $fail.namaPemohon)
    #set ($hartatinggal = $fail.hartatinggal)
    #set ($batalpentadbir = $fail.batalpentadbir)
    #set ($batalamanah = $fail.batalamanah)
    #set ($lantikamanah = $fail.lantikamanah)
    #set ($lantikpentadbir = $fail.lantikpentadbir)
    #set ($perintahlama = $fail.perintahlama)
    #set ($perintahbaru = $fail.perintahbaru)
#end

<input type="hidden" name="idPemohon" value="$idPemohon">
<input type="hidden" name="noPermohonanOnline" value="$noPermohonanOnline">
<input type="hidden" name="idfaillama" value="$idfaillama">
<input type="hidden" name="idPermohonan" value="$idPermohonan">
<input type="hidden" name="idPermohonanSimati" value="$idpermohonansimati">
<input type="hidden" name="idSimati" value="$idSimati">
<input type="hidden" name="idfailSeksyen8" value="$idfailSeksyen8">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<p></p>
<fieldset>
<legend>Maklumat Permohonan Awal</legend>
<table border="0" align="center" width="100%">
    <tbody>
      <tr>
        <td width="1%" align="left" style="text-transform:uppercase;">&nbsp;</td>
        <td width="31%" align="left" style="text-transform:uppercase;">No Permohonan Online</td> 
        <td width="1%"><div align="center">:</div></td>
        <td width="67%" style="text-transform:uppercase;"><div class="style40">$!nopermohonan
        </div></td>
      </tr>
      <tr>
        <td width="1%" align="left" style="text-transform:uppercase;">&nbsp;</td>
        <td width="31%" align="left" style="text-transform:uppercase;">No Fail Lama</td> 
        <td width="1%" align="left"><div align="center">:</div></td>
        <td width="67%" style="text-transform:uppercase;"><div class="style40">$!nofaillama 
        </div></td>
      </tr>
       <tr>
         <td width="1%" align="left" style="text-transform:uppercase;">&nbsp;</td>
         <td width="31%" align="left" style="text-transform:uppercase;">Tempat Permohonan Lama </td> 
        <td width="1%"><div align="center">:</div></td>
        <td width="67%" style="text-transform:uppercase;"><div class="style40">$!daerah - $!negeri</div></td>
      </tr>
       <tr>
         <td width="1%" valign="middle" align="left" style="text-transform:uppercase;">&nbsp;</td>
         <td width="31%" valign="middle" align="left" style="text-transform:uppercase;">Nama Pemohon </td> 
        <td width="1%"><div align="center">:</div></td>
        <td width="67%" style="text-transform:uppercase;"><div class="style40">$!pemohon</div></td>
      </tr>
      </tbody>
      </table>
</fieldset>
<p>
<fieldset>
<legend>Tujuan Permohonan Borang P</legend>
	<table width="100%">
    <tr>
      	<td width="1%"><font color="#ff0000">*</font></td>
        <td width="31%">Tujuan Permohonan</td>
        <td width="1%"><div align="center">:</div></td>
      	<td colspan="2"><input type="checkbox" name="cb1" value="Y" $check1 onclick="javascript:setTable('tableHarta')">&nbsp;Harta simati tertinggal di permohonan awal</td>
    </tr>
    <tr>
      <td width="1%"></td>
    	<td width="31%"></td>
   	    <td width="1%"><div align="center"></div></td>
      <td colspan="2"><input type="checkbox" name="cb2" value="Y" onclick="getCbA()" $check2 $check3 >&nbsp;Pemegang Amanah</td>
    </tr>
    <tr>
      <td width="1%"></td>
    	<td width="31%"></td>
   	    <td width="1%"><div align="center"></div></td>
      <td width="4%"></td>
   	  <td width="63%"><input type="checkbox" name="cb3" value="Y" onclick="getCbB()" $check2>&nbsp;Batal</td>
    </tr>
    <tr>
      <td width="1%"></td>
		<td width="31%"></td>
	    <td width="1%"><div align="center"></div></td>
      <td width="4%"></td>
	  <td width="63%"><input type="checkbox" name="cb4" value="Y" onclick="getCbC()" $check3>&nbsp;Lantik Baru</td>
	</tr>
	<tr>
	  <td width="1%"></td>
		<td width="31%"></td>
	    <td width="1%"><div align="center"></div></td>
      <td colspan="2"><input type="checkbox" name="cb5" value="Y" onclick="getCbD()" $check4 $check5 >&nbsp;Pemegang Surat Kuasa Tadbir</td>
	</tr>
	<tr>
	  <td width="1%"></td>
		<td width="31%"></td>
        <td width="1%"><div align="center"></div></td>
      <td width="4%"></td>
      <td width="63%"><input type="checkbox" name="cb6" value="Y" onclick="getCbF()" $check4 >&nbsp;Batal</td>
    </tr>
    <tr>
      <td width="1%"></td>
        <td width="31%"></td>
        <td width="1%"><div align="center"></div></td>
      <td width="4%"></td>
      <td width="63%"><input type="checkbox" name="cb7" value="Y" onclick="getCbG()" $check5 />        &nbsp;Lantik Baru</td>
    </tr>
    <tr>
		<td colspan="5" height="20px"></td>
    </tr>
    <tr>
      	<td width="1%" valign="top">&nbsp;</td>
		<td width="31%" valign="top">Masukkan perintah dahulu berkenaan dengan permohonan yang sedang dibuat</td>
	    <td width="1%" valign="top">:</td>
      	<td colspan="2"><textarea cols="70" rows="7" name="perintahdahulu" onkeyup="textCounter(this.form.perintahdahulu,this.form.remPerintahDahulu,2000);" onkeydown="textCounter(this.form.perintahdahulu,this.form.remPerintahDahulu,2000);" >$!perintahlama</textarea></td>
	</tr>
    
    
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td width="1%" valign="top">&nbsp;</td>
        <td colspan="5"><input type="text" readonly class="disabled" name="remPerintahDahulu" size="3" maxlength="4" value="2000"> Baki Aksara </td>
    </tr>    
	<tr>
	  	<td width="1%" valign="top">&nbsp;</td>
		<td width="31%" valign="top">Masukkan jenis perintah yang diminta</td>
	    <td width="1%" valign="top">:</td>
      	<td colspan="2"><textarea cols="70" rows="7" name="perintahminta" onkeyup="textCounter(this.form.perintahminta,this.form.remPerintahMinta,2000);" onkeydown="textCounter(this.form.perintahminta,this.form.remPerintahMinta,2000);" >$!perintahbaru</textarea></td>
	</tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td width="1%" valign="top"></td>
      <td colspan="5"><input type="text" readonly class="disabled" name="remPerintahMinta" size="3" maxlength="4" value="2000"> Baki Aksara </td>
    </tr>    
	<tr>
	  <td colspan="5" valign="bottom">&nbsp;</td>
	  </tr>
	<tr>
		<td colspan="5" valign="bottom">        	
        	<i>
            <font color=red style=font-size:10px>Perhatian</font>
            <font style=font-size:10px>: Sila pastikan salah satu</font>&nbsp; <font color="red" style="font-size:10px">Tujuan Permohonan</font>&nbsp; <font style="font-size:10px">diisi.</font></i>         </td>
	</tr>
	<tr>
		<td colspan="5" height="50px;">
		  <div align="center">#if ($btnKemaskini == "yes")
            <input type="button" name="cmdKemaskini" value="Kemaskini" onClick="javascript:simpan();">
            #end
		#if ($btnSimpan == "yes")
        <input type="button" name="cmdSimpan" value="Simpan" onClick="javascript:simpan();">
        #end
		#if ($btnBatal == "yes")
        <input type="button" name="cmdBatal" value="Batal">
        #end
		#if ($btnSeterusnya == "yes")
        <input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="javascript:seterusnya();">
        #end
		#if ($btnKembali == "yes")
		<input type="button" name="cmdKembali" value="Kembali" onclick="getKembali()" />
</div></td>
	  #end	</tr>
</table>
</fieldset>
<input type="hidden" name="mode"/>
<input type="hidden" name="hitButt"/>
<input name="idPermohonanBaru" type="hidden" value="$idPermohonanBaru" />

<script> 
//var area = new FCKeditor("perintahdahulu");
//area.BasePath = '/${appname}/library/fck/' ;
//area.Height = 200;
//area.Width = 500;
//area.ReplaceTextarea();
//
//var area1 = new FCKeditor("perintahminta");
//area1.BasePath = '/${appname}/library/fck/' ;
//area1.Height = 200;
//area1.Width = 500;
//area1.ReplaceTextarea();
//			
//function textCounter(field, countfield, maxlimit) {
//	if (field.value.length > maxlimit) // if too long...trim it!
//		field.value = field.value.substring(0, maxlimit);
//		// otherwise, update 'characters left' counter
//	else 
//		countfield.value = maxlimit - field.value.length;
//}             	
</script>
<script>
function simpan() {
		if ( !window.confirm("Adakah Anda Pasti?") ) return; 
		document.${formname}.method="post";
		//document.${formName}.mode.value ="Simatiview";
		//document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
		document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online";		
		doAjaxCall${formName}("SimpanSemak");
		document.${formName}.submit();	
}

function seterusnyaP() {
	document.${formname}.method="post";
	document.${formName}.mode.value ="Htaamview";
	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

function seterusnya() {
	document.${formname}.method="post";
	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCall${formName}("CetakPengesahanView");
	document.${formName}.submit();
}

function getKembali() {
	document.${formName}.submit();
}

function getCbA(){
	if (document.${formName}.cb2.checked == true){
		document.${formName}.cb3.checked = true;
	}
	
	if (document.${formName}.cb2.checked == false){
		document.${formName}.cb3.checked = false;
		document.${formName}.cb4.checked = false;
	} 
}

function getCbB(){
	if (document.${formName}.cb3.checked == true){
		document.${formName}.cb2.checked = true
	}
	
	if (document.${formName}.cb3.checked == false){
		if (document.${formName}.cb4.checked == false){
			document.${formName}.cb2.checked = false;
		}
	} 
}

function getCbC(){
	if (document.${formName}.cb4.checked == true){
		document.${formName}.cb2.checked = true
	}
	
	if (document.${formName}.cb4.checked == false){
		if (document.${formName}.cb3.checked == false){
			document.${formName}.cb2.checked = false;
		}
	} 
}
function getCbD(){
	if (document.${formName}.cb5.checked == true){
		document.${formName}.cb6.checked = true;
	}
	
	if (document.${formName}.cb5.checked == false){
		document.${formName}.cb6.checked = false;
		document.${formName}.cb7.checked = false;
	} 
}

function getCbF(){
	if (document.${formName}.cb6.checked == true){
		document.${formName}.cb5.checked = true;
	}
	
	if (document.${formName}.cb6.checked == false){
		if (document.${formName}.cb7.checked == false){
			document.${formName}.cb5.checked = false;
		}
	} 
}

function getCbG(){
	if (document.${formName}.cb7.checked == true){
		document.${formName}.cb5.checked = true;
	}
	
	if (document.${formName}.cb7.checked == false){
		if (document.${formName}.cb6.checked == false){
			document.${formName}.cb5.checked = false;
		}
	} 
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function getKembali() {
}

</script>

#end