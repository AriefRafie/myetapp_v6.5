<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
.style3 {color: #FF0000}
-->
</style>

<fieldset>
<legend>
CARIAN</legend>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No Rujukan Dokumen</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="noRujDok" type="text" id="no_Rujukan_Lain" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!no_Rujukan_Lain" size="44" />
        </label></td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Dokumen</td>
    <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
    <td>
      <label>
      <textarea name="tajuk_Dokumen" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="tajuk_Dokumen">$!tajuk_Dokumen</textarea>
        </label>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Dokumen (Surat/Memo)</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>  <input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" />
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Terima/ Dihantar Dokumen</td>
    <td scope="row">:</td>
    <td><input name="tarikh_Diterima_Dihantar" type="text" id="tarikh_Diterima_Dihantar" value="$!tarikh_Diterima_Dihantar" size="10" />
      <a href="javascript:displayDatePicker('tarikh_Diterima_Dihantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
     <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>      </td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>SENARAI DOKUMEN</legend>
Jumlah Keseluruhan Dokumen : $JumlahDokumen
#set ($pagingTitle = "Jumlah Carian") #parse("app/utils/record_paging.jsp") 

<table width="100%"  border="0">
 #if($portal_role != '(PFD)Ketua Pengarah/Timb Ketua Pengarah')
  <tr>
    <td colspan="6" scope="row"><input type="button" name="cmdTambah" id="cmdTambah" value="Daftar Dokumen" onclick="tambahDokumen()"/>
    <input type="button" name="cmdTambah2" id="cmdTambah2" value="Cetak Log Dokumen" onclick="cetakLogDokumenKPTG()"/></td>
  </tr>
  #end
  	#if ($action1 == 'cetakLogDokumen')
	#parse("app/pfd/frmCetakLogDokumen.jsp")
	#else

	#end
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="30%">NO RUJUKAN DOKUMEN</td>
    <td width="50%">TAJUK DOKUMEN</td>
    <td width="30%">TARIKH DOKUMEN</td>
    <td width="30%">TARIKH TERIMA/HANTAR DOKUMEN</td>
    <td width="30%">&nbsp;</td>
  </tr>
  #foreach ($listLogDokumenTKPK in $SenaraiDokumenTKPK)
  
  #if ($listLogDokumenTKPK.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumenTKPK.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  #if($listLogDokumenTKPK.flag_logdokumen == '1')
   <tr>
    <td class="$row">$listLogDokumenTKPK.bil</td>
    #if ($listLogDokumenTKPK.bil != '') 
    <td class="$row"><a href="javascript:edit_itemMasuk('$listLogDokumenTKPK.idLogDokumenKPTG')" class="style1">$listLogDokumenTKPK.no_Rujukan_Lain</a>
    </td>
    #else
        <td class="$row style3">$listLogDokumenTKPK.no_Rujukan_Lain</td>   
    #end
    <td class="$row style3">$listLogDokumenTKPK.tajuk_Dokumen</td>
	<td class="$row style3">$listLogDokumenTKPK.tarikh_Dokumen</td>
    <td class="$row style3">$listLogDokumenTKPK.tarikh_Diterima_Dihantar</td>
    <td class="$row style3"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$listLogDokumenTKPK.idLogDokumenKPTG')"/></td>
  </tr>
  #else
  <tr>
    <td class="$row">$listLogDokumenTKPK.bil</td>
    #if ($listLogDokumenTKPK.bil != '') 
    <td class="$row"><a href="javascript:edit_itemKeluar('$listLogDokumenTKPK.idLogDokumenKPTG')" class="style1">$listLogDokumenTKPK.no_Rujukan_Lain</a></td>
    #else
        <td class="$row">$listLogDokumenTKPK.no_Rujukan_Lain</td>   
    #end
    <td class="$row">$listLogDokumenTKPK.tajuk_Dokumen</td>
	<td class="$row">$listLogDokumenTKPK.tarikh_Dokumen</td>
    <td class="$row">$listLogDokumenTKPK.tarikh_Diterima_Dihantar</td>
  </tr>
  #end
  #end
</table>

<input type="hidden" name="idFail" />
      <input type="hidden" name="mode" />	
</fieldset>

<script>
function carian(){
	document.${formName}.submit();
}
function cetakLogDokumenKPTG() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=cetakLogDokumenKPTG";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}
function tambahDokumen() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenMasukKPTG&paparArahan=false";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}
function edit_itemMasuk(idLogDokumenKPTG){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&idLogDokumenKPTG="+idLogDokumenKPTG+"&pagemode=1&paparArahan=false";
	document.${formName}.submit();

}
function edit_itemKeluar(idLogDokumenKPTG){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&idLogDokumenKPTG="+idLogDokumenKPTG+"&pagemode=2";
	document.${formName}.submit();

}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.no_Rujukan_Lain.value = "";
	document.${formName}.tajuk_Dokumen.value = "";
	document.${formName}.tarikh_Dokumen.value = "";
	document.${formName}.tarikh_Dokumen_Diterima.value = "";
	
}

function tambahLampiran(id){
	  
	//document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tambahLampiran&idLogDokumenKPTG="+id;
	document.${formName}.submit();

}

//function cetak(nofail,tajukfail,idnegeri,idseksyen,idstatus,tarikhdaftar) {
//	
//		var url = "../servlet/ekptg.report.pfd.SenaraiFail?reportType=PDF&nofail="+nofail+"&tajukfail="+tajukfail+"&idnegeri="+idnegeri+"&idseksyen="+idseksyen+"&idstatus="+idstatus+"&tarikhdaftar="+tarikhdaftar;
//	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
//	    if ((document.window != null) && (!hWnd.opener))
//		hWnd.opener = document.window;
//	    if (hWnd.focus != null) hWnd.focus();
//
//}
//function doChanges() {
//	doAjaxCall${formName}("doChanges");
//}
</script>
<script language="JavaScript"> document.${formName}.txtNoFail.focus(); </script>