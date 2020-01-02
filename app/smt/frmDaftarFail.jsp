<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())

<fieldset>
<legend>
<strong>Carian</strong></legend>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No Fail</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onkeyup="this.value=this.value.toUpperCase()" value="$txtNoFail" size="55" />
        </label>
      <input type="hidden" name="idFail" />
      <input type="hidden" name="action" value="$action" />
      <input type="hidden" name="mode" /></td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Fail</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td>
      <label>
      <textarea name="txtTajukFail" cols="53" onkeyup="this.value=this.value.toUpperCase()" id="txtTajukFail">$txtTajukFail</textarea>
        </label>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Negeri</td>
   <td width="1%" scope="row">:</td>
    <td>
        $selectNegeriD    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td>
     
     $selectSeksyenD</td>
  </tr>
  <tr>
    <td scope="row" align="right">Status</td>
    <td width="1%" scope="row">:</td>
    <td>
     
    $selectStatusD</td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Daftar</td>
    <td width="1%" scope="row">:</td>
    <td>
      <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$txdTarikhDaftar" />
        
        
        
        <a href="#" onClick="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    
      <!--
        <a href="#" onClick="cal.select(document.${formName}.txdTarikhDaftar,'anchor1');"
        NAME="anchor1" ID="anchor1"> 
        <img border="0" src="../img/calendar.gif"/></a>   -->  
        
        </td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
      <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>    </td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend><strong>Senarai Fail</strong></legend>

<label></label>
  <label></label>
<table width="100%" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td colspan="2" scope="row">
    <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"/>
    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" onClick="CetakSurat()" /></td>
    <td colspan="2" align="right">
     #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end
    </td>
  </tr>
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="29%"><strong>No Fail</strong></td>
    <td width="50%"><strong>Tajuk Fail</strong></td>
    <td width="20%"><strong>Status Fail</strong></td>
  </tr>
  #foreach ($fail in $Senarai)
  
  #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.bil</td>
     #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$fail.id_Fail')" class="style1">$fail.no_Fail</a></td>
    #else
        <td class="$row">$fail.no_Fail</td>
	#end
    <td class="$row">$EkptgUtil.subString($fail.tajuk_Fail,0,50)</td>
    <td class="$row">$fail.keterangan1</td>
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
 

<script>
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function tambah() {
	//alert('xx');
	document.${formName}.action.value = "tambah";
	document.${formName}.mode.value = "";
	//document.${formName}.action = "";
	document.${formName}.submit();
	
}
function edit_item(id){
	document.${formName}.action.value = "papar";
	document.${formName}.mode.value = "";
	//document.${formName}.action = "";
	document.${formName}.idFail.value = id;
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
function seterusnya(){    	
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}


function CetakSurat() {
	var url = "../servlet/ekptg.report.NoFailTajukFail?reportType=PDF";
	var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
}


</script>
<script language="JavaScript"> document.${formName}.txtNoFail.focus(); </script>