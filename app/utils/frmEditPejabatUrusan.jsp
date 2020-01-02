#if ($result == "success")
<div class=info>Kemaskini Berjaya</div>
#end
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Pejabat Urusan</legend>
<table border="0" cellpadding="2" cellspacing="2">
 	#if ($mode == "add")
	<tr>
	<td >Pejabat</td>
	<td>             
	<select id="Form_id_jenispejabat" name="Form_id_jenispejabat">
	$!selectPejabat
	</select>
	</td>
	</tr>
 	#end
       <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri
             </td>
    	</tr>
   
       <tr>
           <td >Daerah</td>
           <td>             
		$!selectDaerah
           </td>
           
    <tr>
         <td >Seksyen</td>
         <td>             
	     $!selectSeksyen
         </td>
    </tr>

        </tr>
           <tr>
           <td >Pejabat</td>
           <td>
           <input style="text-transform: uppercase;" size=70 name="Form_nama_pejabat" type=text value="$!details.nama_pejabat">
           </td>
        </tr>    
	 <tr>
	   <td >Alamat1</td>
	   <td>
	   <input style="text-transform: uppercase;" size=50 name="Form_alamat1" type=text value="$!details.alamat1">
	   </td>
        </tr> 
 	<tr>
	   <td >Alamat2</td>
	   <td><input style="text-transform: uppercase;"  size=50 name="Form_alamat2" type=text value="$!details.alamat2"></td>
        </tr>        
	<tr>
	   <td >Alamat3</td>
	   <td><input style="text-transform: uppercase;"  size=50 name="Form_alamat3" type=text value="$!details.alamat3"></td>
        </tr>         
        </tr>
	   <td >Poskod</td>
	   <td><input name="Form_poskod" type=text value="$!details.poskod"></td>
        <tr>
	<tr>
	   <td >Telefon</td>
	   <td><input name="Form_no_tel" type=text value="$!details.no_tel"></td>
        <tr>
	</tr>
	   <td >Fax</td>
	   <td><input name="Form_no_fax" type=text value="$!details.no_fax"></td>
        <tr>
	<td >No Akaun</td>
	<td><input name="Form_no_akaun" type=text value="$!details.no_akaun"></td>
	<tr>
	<td >Nama Bank</td>
	<td><input name="Form_nama_bank" size=50 type=text value="$!details.nama_bank"></td>
	<tr>
</table>
</fieldset>
</table>
	#if ($mode == "edit")
		<input type=hidden name=idPejabat value="$!idPejabat">
		<input type=hidden name=Form_id_jenispejabat value="$!id_jenispejabat">
		<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
	#else
		<input type=button value=Tambah onClick="tambah();">
	#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

