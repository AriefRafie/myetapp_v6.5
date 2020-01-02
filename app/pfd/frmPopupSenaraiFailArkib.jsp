<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
#parse("css/eTapp_PFD.css")
</style>

<table width="100%">
  <tr>
    <td><fieldset>
<legend>
CARIAN</legend>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!user_negeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No. Fail Arkib</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFail" size="44" />
        </label></td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Urusan</td>
    <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
    <td>$!selectUrusan    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Suburusan</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
<td>$!selectSuburusan</td>
  </tr>
  <tr>
    <td scope="row" align="right">Sub Suburusan</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>$!selectSubSuburusan</td>
  </tr>
  <tr>
    <td scope="row" align="right">Sub Sub Suburusan</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>$!selectSubSubSuburusan</td>
  </tr>
  
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
      <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()" />
        </label>    </td>
  </tr>
</table>
</fieldset></td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
<legend>SENARAI FAIL</legend>
 
#set ($pagingTitle = "Jumlah Carian") 
#parse("app/utils/record_pagingPopup.jsp") 
<table width="100%" align="center" >
  
  <tr class="table_header">
    <td width="1%" scope="row">NO.</td>
    <td width="20%">NO. FAIL ARKIB</td>
    <td width="20%">URUSAN</td>
    <td width="20%">SUB URUSAN</td>
    <td width="20%">SUB SUBURUSAN</td>
    <td width="20%">SUB SUB SUBURUSAN</td>
    <td width="10%"></td>
  </tr>
  #foreach ($fail in $SenaraiFail)
  
  #if ($fail.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($fail.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.BIL</td>
       
    <td class="$row">$fail.NO_FAIL_ARKIB</td>
    <td class="$row">$fail.NAMA_URUSAN</td>
    <td class="$row">$fail.NAMA_SUBURUSAN</td>
    <td class="$row">$fail.NAMA_SUBSUBURUSAN</td>
    <td class="$row">$fail.NAMA_SUBSUBSUBURUSAN</td>
   <td class="$row"><input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Kod Arkib" onClick="pilihFail('$fail.ID_FAILARKIB')"></td>
  </tr>
  #end
</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right">
     
    </td>
  </tr>
</table>


 	
</fieldset>
    </td>
  </tr>
</table>



<script>
function doChangesUrusan() {
	doAjaxCall${formName}("doChangesUrusan");
	
}
function doChangesSuburusan() {
	doAjaxCall${formName}("doChangesSuburusan");
	
}
function doChangesSubSuburusan() {
	doAjaxCall${formName}("doChangesSubSuburusan");
	
}
function doChangesSubSubSuburusan() {
	doAjaxCall${formName}("doChangesSubSubSuburusan");
	
}
function pilihFail(id_FailArkib) {
	window.opener.refreshFromPilihFailArkib(id_FailArkib);
	window.close();
	
}
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
}
</script>
<script language="JavaScript"> document.${formName}.txtNoFail.focus(); </script>