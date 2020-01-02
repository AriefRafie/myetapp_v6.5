<style type="text/css">
<!--
.style2 {	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
.style1 {color: #0000FF}
-->
</style>
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="mode" type="hidden" value="$mode" />
<fieldset>
<legend>PERGERAKAN HAKMILIK</legend>
<table width="100%" border="0">
  <tr>
    <td width="24%"><div align="right">Kementerian</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaKementerian</span></td>
    <td width="14%"><div align="right">No. Fail KJP</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="14%"><span class="style1">$txtFailKJP</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Agensi</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNamaAgensi</span></td>
    <td width="24%"><div align="right">No. Fail PTG</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtFailPTG</span></td>
  </tr>
  <tr>
    <td width="24%"><div align="right">No. Fail Seksyen</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtNoFailSeksyen</span></td>
    <td width="24%"><div align="right">No. Fail PTD</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%">&nbsp;</td>
  </tr>
  <tr>
    <td width="24%"><div align="right">Tajuk</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$txtTajuk</span></td>
    <td width="24%"><div align="right">Cara Perolehan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="24%"><span class="style1">$caraPerolehan</span></td>
  </tr>
  <tr>
    <td colspan="6"><hr /></td>
  </tr>
</table>
<table width="99%" border="0">
  <tr>
    <td><fieldset>
    <legend>MAKLUMAT PERGERAKAN</legend>
    <table width="100%" border="0" align="center">
      <tr>
        <td><div align="right"><i><font color="#ff0000">*</font></i> Jenis Dokumen</div></td>
        <td><div align="center">:</div></td>
        <td>
        #set($checked4 ="")
        #set($checkedH ="")
        #set($checked3 ="")
		#if($sorDokumen =="4")
          	#set($checked4 ="checked")
         	#set($checkedH ="")
            #set($checked3 ="")
        #elseif($sorDokumen =="H")
         	#set($checkedH ="checked")
         	#set($checked4 ="")
         	#set($checked3 ="")
        #elseif($sorDokumen =="3")
         	#set($checked3 ="checked")
            #set($checkedH ="")
            #set($checked4 ="")
        #end
          <input name="sorDokumen" type="radio" id="sorDokumen" value="H" $checkedH $disabled/> Hakmilik <br />
 		  <input type="radio" name="sorDokumen" id="sorDokumen" value="4" $checked4 $disabled/> KAD 284 <br />
          <input type="radio" name="sorDokumen" id="sorDokumen" value="3" $checked3 $disabled/> KAD 283 <br />        </td>
      </tr>

        <td><div align="right"><i><font color="#ff0000">*</font></i> Urusan</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtKeterangan" type="text" id="txtKeterangan" value="$txtKeterangan" size="50" $readonly class="$disabled" style="text-transform:uppercase;"/>        </td>
      </tr>
      <tr>
        <td><div align="right"><i><font color="#ff0000">*</font></i> Kepada</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input name="txtKepada" type="text" id="txtKepada" value="$txtKepada" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
        </label></td>
      </tr>
      <tr>
        <td width="526"><div align="right"> <i><font color="#ff0000">*</font></i> Tarikh Serahan</div></td>
        <td width="10"><div align="center">:</div></td>
        <td width="663"><input name="txdTarikhSerah" type="text" id="txdTarikhSerah" value="$txdTarikhSerah" size="9" $readonly class="$disabled"/>
            <a href="javascript:displayDatePicker('txdTarikhSerah',false,'dmy');"> <img border="0" src="../img/calendar.gif"/></a> <span class="style2"> dd/mm/yyyy </span>        </td>
      </tr>
      <tr>
        <td height="23"><div align="right"> <i><font color="#ff0000">*</font></i> Status Serahan</div></td>
        <td><div align="center">:</div></td>
        <td><select name="socStatusS" id="socStatusS" style="width:200px;" $readonly class="$disabled" $disabled >
          
         #if($socStatusS == 'D')
          
          <option value="">SILA PILIH</option>
          <option value="D" selected="selected"> D - DALAMAN</option>
          <option value="L"> L - LUARAN</option>
          
         #elseif ($socStatusS == 'L') 
          
          <option value="">SILA PILIH</option>
          <option value="D"> D - DALAMAN</option>
          <option value="L"selected="selected"> L - LUARAN</option>
          
         #else  
          
          <option value="" selected="selected">SILA PILIH</option>
          <option value="D"> D - DALAMAN</option>
          <option value="L"> L - LUARAN</option>
          
         #end
        
        </select></td>
      </tr>
      <tr>
        <td><div align="right">Tarikh Kembali</div></td>
        <td><div align="center">:</div></td>
		<td width="663"><input name="txdTarikhKembali" type="text" id="txdTarikhKembali" value="$txdTarikhKembali" size="9" $readonly class="$disabled"/>
          <a href="javascript:displayDatePicker('txdTarikhKembali',false,'dmy');"> <img border="0" src="../img/calendar.gif"/></a> <span class="style2"> dd/mm/yyyy </span>        </td>
      </tr>
      <tr>
        <td valign="top"><div align="right">Catatan</div></td>
        <td valign="top"><div align="center">:</div></td>
        <td><textarea name="txtCatatan" cols="27" rows="5" class="$disabled" id="txtCatatan" $readonly="$readonly">$txtCatatan</textarea></td>
      </tr>
      <tr>
        <td><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
    <label></label>
    </fieldset>
    </td>
  </tr>
</table>
<div align="center">
  <p>
  	#if ($mode =='new')
      <input type="button" class="stylobutton" name="btnAddHakmilik" id="btnAddHakmilik" value="Simpan" onclick="tambahPergerakanDetail($idHakmilik)"/>
    #end
  	#if ($mode == 'update')
    <input type="button" class="stylobutton" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Simpan" onclick="updatePergerakanDetail($idPergerakan)"/>
    #end
  	#if ($mode == 'view')
    <input type="button" class="stylobutton" name="btnKemaskiniHakmilik" id="btnKemaskiniHakmilik" value="Kemaskini" onclick="kemaskiniPergerakanDetail($idPergerakan)"/>
    <input type="button" class="stylobutton" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
    #end
    <input type="button" class="stylobutton" name="btnBackPergerakan" id="btnBackPergerakan" value="Kembali" onclick="kembaliSenaraiPergerakan()"/>
  </p>
<fieldset id="tableReport1" style="display:none;">
	<legend>SENARAI LAPORAN</legend>
	<table width="100%" border="0">
  	<tr>
   	 <td><a href="javascript:cetakPergerakan($idPergerakan);" class="style1">Borang Pergerakan Dokumen</a></td>
  	</tr>
	</table>
	</fieldset>
</div>
</fieldset>
<script>
function tambahPergerakanDetail(idhakmilik){
//VALIDATAION
 if(document.${formName}.sorDokumen.checked == false){ 
	alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
	document.${formName}.sorDokumen.focus();
	return; 
 }
 if(document.${formName}.txtKeterangan.value == ""){ 
	alert('Sila masukkan " Urusan " terlebih dahulu.'); 
	document.${formName}.txtKeterangan.focus();
	return; 
 }
 if(document.${formName}.txtKepada.value == ""){ 
	alert('Sila masukkan " Kepada " terlebih dahulu.'); 
	document.${formName}.txtKepada.focus();
	return; 
 }
 if(document.${formName}.txdTarikhSerah.value == ""){ 
	alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
	document.${formName}.txdTarikhSerah.focus(); 
	return; 
 }	
 if(document.${formName}.socStatusS.value == ""){
	alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
	document.${formName}.socStatusS.focus(); 
	return; 
 }
  var str1 = document.${formName}.txdTarikhSerah.value; 
  var dt1 = parseInt(str1.substring(0,2),10); 
  var mon1 = parseInt(str1.substring(3,5),10)-1; 
  var yr1 = parseInt(str1.substring(6,10),10);
  var tarikhSerah = new Date(yr1, mon1, dt1);
  var str2 = document.${formName}.txdTarikhKembali.value; 
  var dt2 = parseInt(str2.substring(0,2),10); 
  var mon2 = parseInt(str2.substring(3,5),10)-1; 
  var yr2 = parseInt(str2.substring(6,10),10); 
  var tarikhKembali = new Date(yr2, mon2, dt2); 
  var currentDate = new Date(); 
  if (tarikhSerah > currentDate){ 
  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
   	 document.${formName}.txdTarikhSerah.focus(); return; 
  } 
  if (tarikhKembali > currentDate){ 
   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
   	  document.${formName}.tarikhKembali.focus(); return; 
  } 
  if (tarikhSerah > tarikhKembali){ 
      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
     document.${formName}.tarikhKembali.focus(); return; 
  }
 //END OF VALIDATION
  document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=tambahPergerakanHakmilikDetail";
  document.${formName}.submit();
}
function kemaskiniPergerakanDetail(id){
  document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=kemaskiniPergerakanHakmilikDetail&idPergerakan="+id;
  document.${formName}.submit();
}
function updatePergerakanDetail(id){
//VALIDATAION
 if(document.${formName}.sorDokumen.checked == false){ 
	alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
	document.${formName}.sorDokumen.focus();
	return; 
 }
 if(document.${formName}.txtKeterangan.value == ""){ 
	alert('Sila masukkan " Urusan " terlebih dahulu.'); 
	document.${formName}.txtKeterangan.focus();
	return; 
 }
 if(document.${formName}.txtKepada.value == ""){ 
	alert('Sila masukkan " Kepada " terlebih dahulu.'); 
	document.${formName}.txtKepada.focus();
	return; 
 }
 if(document.${formName}.txdTarikhSerah.value == ""){ 
	alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
	document.${formName}.txdTarikhSerah.focus(); 
	return; 
 }	
 if(document.${formName}.socStatusS.value == ""){
	alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
	document.${formName}.socStatusS.focus(); 
	return; 
 }
   var str1 = document.${formName}.txdTarikhSerah.value; 
  var dt1 = parseInt(str1.substring(0,2),10); 
  var mon1 = parseInt(str1.substring(3,5),10)-1; 
  var yr1 = parseInt(str1.substring(6,10),10);
  var tarikhSerah = new Date(yr1, mon1, dt1);
  var str2 = document.${formName}.txdTarikhKembali.value; 
  var dt2 = parseInt(str2.substring(0,2),10); 
  var mon2 = parseInt(str2.substring(3,5),10)-1; 
  var yr2 = parseInt(str2.substring(6,10),10); 
  var tarikhKembali = new Date(yr2, mon2, dt2); 
  var currentDate = new Date(); 
  if (tarikhSerah > currentDate){ 
  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
   	 document.${formName}.txdTarikhSerah.focus(); return; 
  } 
  if (tarikhKembali > currentDate){ 
   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
   	  document.${formName}.tarikhKembali.focus(); return; 
  } 
  if (tarikhSerah > tarikhKembali){ 
      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
     document.${formName}.tarikhKembali.focus(); return; 
  }
 //END OF VALIDATION

	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=updatePergerakanHakmilikDetail&idPergerakan="+id;	
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakPergerakan(id){
	var url = "../servlet/ekptg.report.htp.BorangPergerakanHakmilik?idPergerakan="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function kembaliSenaraiPergerakan(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik";
	document.${formName}.submit();
}
</script>