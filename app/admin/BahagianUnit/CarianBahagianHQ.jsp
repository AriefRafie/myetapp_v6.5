<script>
if( $jquery('#div_CT_CARIAN_'+'$TYPE').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#div_CT_CARIAN_'+'$TYPE').offset().top);
}
</script>


<table border="0" cellpadding="0" cellspacing="0" width="98%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian Bahagian $TYPE </legend>
<table border="0" cellpadding="2" cellspacing="2" align="left">
	<tr>
	<td >Carian Bahagian</td><td >:</td>
	<td valign="top">
	#if ($TYPE.equals("HQ"))
	<input type="text" id="carianBahagian$TYPE" name="carianBahagian$TYPE" style="text-transform:uppercase;" size="50" value="$!carianBahagianHQ" 
	onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','FlagCari=Y'); return false; }">
	#else
	<input type="text" id="carianBahagian$TYPE" name="carianBahagian$TYPE" style="text-transform:uppercase;" size="50" value="$!carianBahagianNegeri" 
	onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','FlagCari=Y'); return false; }">
	#end
	</td>
	<td>
<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="doDivAjaxCall$formname('div_SenaraiBahagian$TYPE','showSenaraiBahagian','FlagCari=Y&TYPE=$TYPE');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="doDivAjaxCall$formname('div_CT_CARIAN_$TYPE','batalBahagian','FlagCari=Y&TYPE=$TYPE');" >

</td>
</tr>
</table>

</fieldset>


</table>
<br>