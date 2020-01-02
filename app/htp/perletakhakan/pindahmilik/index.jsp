<fieldset><legend>HAKMILIK PERLETAKHAKAN</legend>

<input name="actionPerletakhakan" type="hidden" />
<input name="idFail" type="hidden">
<table width="100%">
<tr>
	<td>
<fieldset>
<legend>Carian</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%" align="right">No Fail</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNoFail" id="txtNoFail" size="40" value="$txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">
    	<input type="button" class="stylobutton" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
    	<input type="button" class="stylobutton" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/>
    </td>
  </tr>
</table>
</fieldset>
</td>
</tr>
</table>
<table width="100%">
<tr>
<td>
<fieldset>
<legend>Senarai Fail</legend>
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%" align="center">Bil.</td>
    <td width="20%">No Fail</td>
    <td width="50%">Tajuk</td>
    <td width="10%">Negeri</td>
    <td width="15%">Status</td>
  </tr>
 
 #if ($SenaraiFail.size() > 0) 
 #foreach ($list in $SenaraiFail)  
  #if ($list.bil == '') 
  	#set ($row = 'row1')
  #elseif ($list.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr class="$row">
    <td align="center">$list.bil.</td>
    <td ><a href="javascript:papar('$list.idFail')"><font color="blue">$list.noFail</font></a></td>
    <td >$list.tujuan</td>
    <td >$list.negeri</td>
    <td >$list.keterangan</td>
  </tr>
  #end
  #else
   <tr>
   	<td class="row1" align="center">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1">&nbsp;</td>
   </tr>
  #end
  
</table></fieldset></td></tr></table>
</fieldset>

<input type="hidden" name="txtcarian" value="$!iscarian"></td>
  
<script>
	function papar(id){
		document.${formName}.actionPerletakhakan.value = "papar";
		document.${formName}.idFail.value = id;
		document.${formName}.submit();
	}
	
	function carian(){
		document.${formName}.txtcarian.value = "ya";
		document.${formName}.actionPerletakhakan.value = "";
		document.${formName}.submit();
	}
	
	function kosongkan(){
		document.${formName}.actionPerletakhakan.value = "";
		document.${formName}.txtNoFail.value = "";
		document.${formName}.submit();
	}
</script>