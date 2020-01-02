<style type="text/css">
<!--
.style2 {font-size: 10px}
-->
#parse("css/eTapp_PFD.css")
.style3 {color: #0000FF}
</style>

<table width="100%">
  <tr>
    <td><fieldset>
<legend>
CARIAN</legend>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!user_negeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="id_fail_carian" id="id_fail_carian" type="hidden" value="$!id_fail_carian"/>
<input type="hidden" name="id_Fail" id="id_Fail" /> 
<input type="hidden" id="ScrollX" name='ScrollX'  />
<input type="hidden" id="ScrollY" name='ScrollY'  />
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No Fail Arkib</td>
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
<legend>SENARAI FAIL ARKIB</legend>
 
#set ($pagingTitle = "Jumlah Carian") #parse("app/utils/record_paging.jsp") 
<table width="100%" align="center" >
  
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="40%">NO FAIL ARKIB</td>
    <td width="49%">TAJUK FAIL</td>
   
    
  </tr>
  #foreach ($fail in $SenaraiFail)
  
  #if ($fail.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($fail.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  
  #if($fail.ID_FAIL  == $id_fail_carian)
     #set( $row = "tr_class" )
  #end
         
  <tr>
    <td class="$row">$fail.BIL</td>
       
    <td class="$row"><a href="#" class="style3" onClick="papar('$fail.ID_FAIL');">
    
   
     #if($fail.ID_FAIL  == $id_fail_carian)
         <font color="blue"> $fail.NO_FAIL   </font>
         #else
         <font color="blue">  $fail.NO_FAIL    </font>
         #end  
    
    </a></td>
    <td class="$row">$fail.TAJUK_FAIL</td>
    
  </tr>
  #end
  #if ($fail.size() == '')
  <tr>
  	<td></td>
  	<td>Tiada rekod.</td>
  </tr>
  #end
</table>
</fieldset>
    </td>
  </tr>
</table>

#if($detailFail =="yes")
    <fieldset>
<legend>MAKLUMAT FAIL ARKIB</legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    
    <td width="27%" scope="row"><div align="right">
      <div align="left">No Fail Arkib</div>
    </div></td>
    <td width="1%" scope="row">:</td>
    <td width="70%">$papar.noFail</td>
  </tr>
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">No Fail Lama</div>
    </div></td>
    <td scope="row">:</td>
    <td><input name="txtNoFailLama" type="text" id="txtNoFailLama" value="$!noFailLama" size="28" $disabledNoFail $readonlyNoFail/>
    <input type="button" name="cmdPilihFailLama" id="cmdPilihFailLama" value="Pilih No Fail Lama" onClick="pilihfail()"/>  
    </td>
  </tr>
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Seksyen</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.namaSeksyen</td>
  </tr>
  <tr>
   
    <td scope="row"><div align="right">
      <div align="left">Urusan</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.kodUrusan - $papar.namaUrusan</td>
  </tr>
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Sub Urusan</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.kodSuburusan - $papar.namaSuburusan</td>
  </tr>
 
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Sub-Sub Urusan</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.kodSubSuburusan - $papar.namaSubSuburusan</td>
  </tr>
  
   <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Sub Sub-Suburusan</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.kodSubSubSuburusan - $papar.namaSubSubSuburusan</td>
  </tr>
  
  
  
  <tr>
   
    <td scope="row"><div align="right">
      <div align="left">Taraf Keselamatan</div>
    </div></td>
   <td scope="row">:</td>
    <td>$papar.taraf</td>
  </tr>
  <tr>
    
    <td scope="row" valign="top"><div align="right" class="style40">
      <div align="left" class="style41">Tajuk Fail </div>
    </div></td>
    <td scope="row" valign="top">:</td>
    <td>$papar.tajukFail</td>
  </tr>
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Status Fail</div>
    </div></td>
   <td scope="row">:</td>
    <td>$papar.status</td>
  </tr>
  <tr>
   
    <td scope="row"><div align="right">
      <div align="left">Lokasi Simpanan Fail</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.lokasi</td>
  </tr>
  <tr>
   
    <td scope="row"><div align="right">
      <div align="left">Kabinet</div>
    </div></td>
    <td scope="row">:</td>
    <td>$papar.kabinet</td>
  </tr>
  
  <tr>
    
    <td scope="row"><div align="right">
      <div align="left">Tarikh Daftar Fail</div>
    </div></td>
  <td scope="row">:</td>
    <td>$papar.tarikhDaftar</td>
  </tr>
</table>
</fieldset>
<!--
<fieldset>
<legend>SENARAI FAIL YANG BERKAITAN</legend>
<input type="button" name="cmdPilihFail" id="cmdPilihFail" value="Pilih Fail" onClick="pilihfail()"/>
<table width="100%" align="center" >
  
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="40%">NO FAIL</td>
    <td width="49%">TAJUK FAIL</td>
   
    
  </tr>
  #foreach ($failBerkaitan in $SenaraiFailBerkaitan)
  
  #if ($failBerkaitan.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($failBerkaitan.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  
  #if($failBerkaitan.ID_FAIL  == $id_fail_carian)
     #set( $row = "tr_class" )
  #end
         
  <tr>
    <td class="$row">$failBerkaitan.BIL</td>
    <td class="$row">$failBerkaitan.NO_FAIL</td>  
    <td class="$row">$failBerkaitan.TAJUK_FAIL</td>  
     
  </tr>
  #end
  #if ($SaizFail == '0')
  <tr>
  	<td></td>
  	<td>Tiada rekod.</td>
  </tr>
  #end
</table>



</fieldset>-->
#end

<script>
function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
   hidy = '$ScrollY';
                        
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }
function papar(idFail){
	
	SaveScrollXY(); 
	document.${formName}.id_fail_carian.value = idFail;
	document.${formName}.action="?_portal_module=ekptg.view.pfd.FrmFailMapping";
	document.${formName}.command.value = "paparFail";
    document.${formName}.submit();
	
	
	
}
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
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
}
function pilihfail() {
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmPopupSenaraiFailAll";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihFailLama(id_Fail) {
	document.${formName}.id_Fail.value = id_Fail;
	document.${formName}.action="?_portal_module=ekptg.view.pfd.FrmFailMapping";
	document.${formName}.command.value = "paparFail";
	document.${formName}.submit();
	
}
</script>
<script language="JavaScript"> document.${formName}.txtNoFail.focus(); </script>