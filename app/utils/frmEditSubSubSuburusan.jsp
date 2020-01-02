#if ($result == "success")

<div class=info>Kemaskini Berjaya</div>
#else
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Sub Sub Suburusan</legend>
<table border="0" cellpadding="2" cellspacing="2">
       <tr>
             <td >Sub Suburusan</td>
             <td>             
	     $!selectSubSuburusan             </td>
    	</tr>
       
       
	   <td >Kod Sub Sub Suburusan</td>
	   <td><input size=4 maxlength=4 name="Form_kod_subsubsuburusan" type=text value="$!details.kod_subsubsuburusan"></td>
        <tr>
	<tr>
	   <td valign="top">Nama Sub Sub Suburusan</td>
	   <td><textarea name="Form_nama_subsubsuburusan" cols="41">$!details.nama_subsubsuburusan</textarea></td>
	<tr>
</table>
</fieldset>
</table>
#end
#if ($mode == "edit")
	<input type=hidden name=idSubSubSuburusan value="$!idSubSubSuburusan">
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
	<input type=button value=Tambah onClick="tambah();">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

