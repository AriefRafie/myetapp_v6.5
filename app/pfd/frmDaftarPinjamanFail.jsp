<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

<table width="100%">
  <tr>
    <td>
<fieldset>
  <legend>
  CARIAN  </legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" scope="row">No Fail</td>
      <td width="1%" class="style2" scope="row">:</td>
      <td width="70%"><label>
       
        <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase()" value="$txtNoFail" size="44" />
        </label>
        <input type="hidden" name="idFail" id="idFail" />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">Tajuk Fail</td>
      <td width="1%" valign="top" class="style2" scope="row">:</td>
      <td><label>
        
        <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase()" id="txtTajukFail">$txtTajukFail</textarea>
        </label>      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Negeri</td>
      <td width="1%" class="style2" scope="row">:</td>
      <td>$selectNegeriD   </td>
    </tr>
    <tr>
      <td align="right" scope="row">Seksyen</td>
      <td width="1%" class="style2" scope="row">:</td>
      <td><label></label> 
      $selectSeksyenD     </td>
    </tr>
    <tr>
      <td align="right" scope="row">Status</td>
     <td width="1%" class="style2" scope="row">:</td>
      <td><label></label> 
      $selectStatusD     </td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Daftar</td>
      <td width="1%" class="style2" scope="row">:</td>
      <td><label>
       
        <input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="10"  />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td colspan="2" align="right" scope="row"><label></label>      </td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>      </td>
    </tr>
  </table>
</fieldset>
</td>
  </tr>
  <tr>
    <td>
    <fieldset>
  <legend>SENARAI FAIL</legend>
   #parse("app/utils/record_paging.jsp")
  <table width="100%" align="center" >
    <tr class="table_header">
      <td width="1%" scope="row">NO</td>
      <td width="29%">NO FAIL</td>
      <td width="50%">TAJUK FAIL</td>
      <td width="20%">STATUS PERGERAKAN FAIL</td>
    </tr>
    #foreach ($fail in $SenaraiFail)
   #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end  
  <tr>
    <td class="$row">#set ($cnt = ($page - 1) * $itemsPerPage + $fail.bil )
    $cnt</td>
     #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$fail.id_Fail')" class="style1">$fail.no_Fail</a></td>
    #else
        <td class="$row">$fail.no_Fail</td>
	#end
    <td class="$row">$fail.tajuk_Fail</td>
    <td class="$row">$fail.keterangan2</td>
  </tr>
  #end
  </table>
  </fieldset>

    </td>
  </tr>
</table>


  <table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-09</strong></td>
  </tr>
</table>

<script>
function edit_item(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PinjamanFail&action=tambahPinjaman";
	document.${formName}.idFail.value = id;
	document.${formName}.submit();

}
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFail.value = "";
	document.${formName}.socNegeriD.value = "";
	document.${formName}.socSeksyenD.value = "";
	document.${formName}.socStatusD.value = "";
	document.${formName}.txdTarikhDaftar.value = "";
}
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
</script>
<script language="JavaScript"> document.forms[0].txtNoFail.focus(); </script>