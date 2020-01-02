#if ($result == "success")

<div class=info>Kemaskini Berjaya</div>
#else
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Fail Arkib</legend>
<table border="0" cellpadding="2" cellspacing="2">
        <tr>
             <td >Urusan</td>
             <td>             
	     $!selectUrusanAdd
             </td>
    </tr>
    <tr>
             <td>Suburusan</td>
             <td>             
	     $!selectSuburusanAdd
             </td>
    </tr>
    <tr>
             <td >Sub Suburusan</td>
             <td>             
	     $!selectSubSuburusanAdd
             </td>
    </tr>
      
            <tr>
             <td >Sub Sub Suburusan</td>
             <td>             
	     $!selectSubSubSuburusanAdd
             </td>
    </tr>

</table>
</fieldset>
</table>
#end
#if ($mode == "edit")
	<input type=hidden name=idFailArkib value="$!idFailArkib">
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
	<input type=button value=Tambah onClick="tambah();">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

