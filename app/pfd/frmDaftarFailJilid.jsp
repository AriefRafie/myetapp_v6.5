<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

&nbsp;



<table width="100%">
  <tr>
    <td> <fieldset>
  <legend>
    CARIAN    </legend>
    <input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" scope="row" align="right">No Fail</td>
      <td width="1%" scope="row"><span class="style2">:</span></td>
      <td width="70%"><label>
        <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$txtNoFail" size="44" />
        </label>
        <input type="hidden" name="idFail" id="idFail" />
     </td>
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
      <td scope="row" align="right" valign="top">Tajuk Fail</td>
      <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
      <td><label>
        <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="txtTajukFail">$txtTajukFail</textarea>
        </label>      </td>
    </tr>
    <tr>
      <td scope="row" align="right">Negeri</td>
      <td width="1%" scope="row"><span class="style2">:</span></td>
      <td>$selectNegeriD   </td>
    </tr>
    <tr>
      <td scope="row" align="right">Seksyen</td>
      <td width="1%" scope="row"><span class="style2">:</span></td>
      <td><label></label> 
      $selectSeksyenD     </td>
    </tr>
    <tr>
      <td scope="row" align="right">Status</td>
      <td width="1%" scope="row"><span class="style2">:</span></td>
      <td><label></label> 
      $selectStatusD     </td>
    </tr>
    <tr>
      <td scope="row" align="right">Tarikh Daftar</td>
      <td width="1%" scope="row"><span class="style2">:</span></td>
      <td><label>
        <input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="10"  />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td colspan="2" scope="row"><label></label>      </td>
      <td>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
             </td>
    </tr>
  </table>
  </fieldset></td>
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
      <td width="41%">TAJUK FAIL</td>
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
    <td class="$row">$fail.bil</td>
    #if ($fail.keterangan1 == 'HILANG')
        #if ($fail.bil != '') 
        <td class="$row">$fail.no_Fail</td>
        #else
            <td class="$row">$fail.no_Fail</td>
        #end
    #else
        #if ($fail.bil != '') 
        <td class="$row"><a href="javascript:edit_item('$fail.id_Fail')" class="style1">$fail.no_Fail</a></td>
        #else
            <td class="$row">$fail.no_Fail</td>
        #end
    #end
     <td class="$row">$fail.no_Fail_Asal</td>
    <td class="$row">$fail.tajuk_Fail</td>
    <td class="$row">$fail.keterangan1</td>
  </tr>
  #end
  </table>
  </fieldset></td>
  </tr>
</table>

 
  
 <table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-14</strong></td>
  </tr>
</table>

<script>
function edit_item(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFailJilid&action1=tambahFailJilid";
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
</script>
<script language="JavaScript"> document.forms[0].txtNoFail.focus(); </script>