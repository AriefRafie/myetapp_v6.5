#if ($result == "success")
<div class=info>Done.</div>
#end
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Pejabat</legend>
<table border="0" cellpadding="2" cellspacing="2">
 
        <tr>
              <td>Kod JKPTG</td>
<td><input size=4 name="Form_kod_jkptg" type=text value="$!details.kod_jkptg"></td>
    </tr>
    
       <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri2
             </td>
    </tr>
    <tr>
         <td >Seksyen</td>
         <td>             
	     $!selectSeksyen2
         </td>
    </tr>
   
       <tr>
           <td >Daerah</td>
           <td>             
		$!selectDaerah2
           </td>
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
	</tr>
            <tr>
	   <td >Fax</td>
	   <td><input name="Form_no_fax" type=text value="$!details.no_fax"></td>
        </tr>
        
            <tr>
	   <td >Emel</td>
	   <td><input name="Form_emel" type=text value="$!details.emel"></td>
        </tr>
</table>
</fieldset>
</table>
	#if ($mode == "edit")
		<input type=hidden name="id" value="$id">
		<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
	#else
		<input type=button value=Tambah onClick="javascript:doAdd();">
	#end


<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack2')">