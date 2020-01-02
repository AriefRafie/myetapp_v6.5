<table width="100%" align="right" border="0"  cellspacing="0" cellpadding="0">
<tr>
<td>
<fieldset>
<p>Tarikh Dokumen : <label>
      <input name="txdTarikhDokumen$ID_DOCTRM" type="text" id="txdTarikhDokumen$ID_DOCTRM" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" >
     <a href="javascript:displayDatePicker('txdTarikhDokumen$ID_DOCTRM',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></p>
<input size="50" type="file" id="LampiranUndang$ID_DOCTRM" name="LampiranUndang$ID_DOCTRM"
onChange="saveLampiran('divSubLampiran$ID_DOCTRM','saveLampiran','$ID_DOCTRM','$ID_DOCTRMUTAMA',$jquery('#txdTarikhDokumen$ID_DOCTRM').val());"  >

</fieldset>
</td>
</tr>
</table>  			