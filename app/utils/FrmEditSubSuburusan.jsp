#if ($result == "success")

<div class=info>Kemaskini Berjaya</div>
#else
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Sub Suburusan</legend>
<table border="0" cellpadding="2" cellspacing="2">
       <tr>
             <td >Suburusan</td>
             <td>             
	     $!selectSuburusan
             </td>
    	</tr>
       
       
	   <td >Kod Sub Suburusan</td>
	   <td><input size=4 maxlength=4 name="Form_kod_subsuburusan" type=text value="$!details.kod_subsuburusan"></td>
        <tr>
	<tr>
	   <td >Nama Sub Suburusan</td>
	   <td><input size=45 maxlength=80 name="Form_nama_subsuburusan" type=text value="$!details.nama_subsuburusan"></td>
        <tr>

</table>
</fieldset>
</table>
#end
#if ($mode == "edit")
	<input type=hidden name=idSubSuburusan value="$!idSubSuburusan">
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
	<input type=button value=Tambah onClick="tambah();">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

