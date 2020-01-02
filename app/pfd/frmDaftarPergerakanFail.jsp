<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>


<table width="100%">
  <tr>
    <td><fieldset>
  <legend>
  CARIAN  </legend>
  <input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" scope="row">No Fail</td>
      <td width="1%" class="style2" scope="row">:</td>
      <td width="70%"><label>
       
        <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$txtNoFail" size="44" />
        </label>
        <input type="hidden" name="idFail" id="idFail" />      </td>
    </tr>
    <tr>
    <td width="29%" scope="row" align="right">No Fail Lama</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="txtNoFailLama" type="text" id="txtNoFailLama" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!txtNoFailLama" size="44" />
        </label></td>
  </tr>
    <tr>
      <td align="right" valign="top" scope="row">Tajuk Fail</td>
      <td width="1%" valign="top" class="style2" scope="row">:</td>
      <td><label>
        
        <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="txtTajukFail">$txtTajukFail</textarea>
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
      <td>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
            </td>
    </tr>
  </table>
</fieldset>
</td>
  </tr>
  <tr>
    <td><fieldset>
  <legend>SENARAI FAIL</legend>
Jumlah Keseluruhan Fail : $JumlahFail
#set ($pagingTitle = "Jumlah Carian") #parse("app/utils/record_paging.jsp") 
  <table width="100%" align="center" >
 <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="20%">NO FAIL</td>
    <td width="20%">NO FAIL LAMA</td>
    <td width="20%">TAJUK FAIL</td>
    <td width="7%">STATUS PINJAMAN FAIL</td>
    <td width="20%">STATUS FAIL</td>
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
    <td width="1%" class="$row">$fail.bil</td>
#if($fail.keterangan1 == 'HILANG')
   	#if($fail.status_pinjaman_fail == '2' || $fail.status_pinjaman_fail == '1')
    	#if ($fail.bil != '') 
    	<td class="$row">$fail.no_Fail</td>
    	#else
        <td class="$row">$fail.no_Fail</td>
		#end
    #else
     	#if ($fail.bil != '') 
    	<td class="$row">$fail.no_Fail</td>
    	#else
        <td class="$row">$fail.no_Fail</td>
		#end
    #end
#else
	   	#if($fail.status_pinjaman_fail == '2' || $fail.status_pinjaman_fail == '1')
    	#if ($fail.bil != '') 
    	<td class="$row"><a href="javascript:edit_item1('$fail.id_Fail','$fail.status_pinjaman_fail')" class="style1">$fail.no_Fail</a></td>
    	#else
        <td class="$row">$fail.no_Fail</td>
		#end
    #else
     	#if ($fail.bil != '') 
    	<td class="$row"><a href="javascript:edit_item2('$fail.id_Fail','$fail.status_pinjaman_fail')" class="style1">$fail.no_Fail</a></td>
    	#else
        <td class="$row">$fail.no_Fail</td>
		#end
    #end
#end
<td class="$row">$fail.no_Fail_Asal</td>
    <td width="25%" class="$row">$Util.subString($fail.tajuk_Fail,0,50)</td>
  <td width="22%" class="$row">
    	#if($fail.status_pinjaman_fail == '1')
        <b>SEDANG DIPINJAM</b>
        #elseif($fail.status_pinjaman_fail == '2')
        <b>SEDANG DIPINJAM</b>
        #else
        TELAH DIPULANGKAN
        #end    </td>
    <td width="12%" class="$row">$fail.keterangan1</td>
  </tr>
  #end
  </table>
</fieldset></td>
  </tr>
</table>

<script>
function edit_item1(id){
	if ( !window.confirm("Fail sedang dipinjam. Adakah ingin meneruskan pinjaman fail?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakan";
	document.${formName}.idFail.value = id;
	document.${formName}.submit();

}
function edit_item2(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakan";
	document.${formName}.idFail.value = id;
	document.${formName}.submit();

}
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFailLama.value = "";
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