<!--frmSetupSuburusanDokumenEdit.jsp-->
#if ($result == "success")
<div class=info>Kemaskini Berjaya</div>
#end
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Maklumat Sub Urusan Dokumen</legend>
<table border="0" cellpadding="2" cellspacing="2">
      <tr>
         <td >Urusan</td>
         <td>             
	     $selectUrusan
         </td>
    </tr>
         <tr>
             <td >Sub Urusan</td>
             <td>             
	     $selectSuburusan
             </td>
    </tr>
       <!--<tr>
           <td >Status</td>
           <td>             
		$selectStatus2
           </td> 
        </tr>-->
          <tr>
             <td >Peringkat</td>
             <td> <input size=50 name="Form_peringkat" type=text value="$details.peringkat"> </td>
    		</tr>  
       		<tr>
             <td >Template</td>
             <td><input size=50 name="Form_template" type=text value="$details.template"></td>
    		</tr>   
    		<tr>
             <td >Jenis (T-SURAT,L-LAPORAN)</td>
             <td><input size=5 name="Form_dokumen" type=text value="$details.dokumen"></td>
    		</tr>  		   
    		<tr>
             <td >Jenis Dokumen</td>
             <td><input size=5 name="Form_id_jenisdokumen" type=text value="$details.jenisdokumen"></td>
    		</tr>
    		<tr>
    		<tr>
             <td >Module</td>
             <td> <input size=50 name="Form_module_id" type=text value="$details.moduleid"></td>
    		</tr>
    		<tr>
		    </tr>
           <td >Langkah</td>
           <td>
           <!--<input size=70 name="Form_nama_pejabat" type=text value="$details.nama_pejabat">-->
           
		#set ( $selected = "" )
		<select class="autoselect" name="Form_aturan">
	   		<option value="0" $selected>Sila Pilih</option>
	   		##set ( $ints = $langkah.size()+1 )
	   		#set ( $ints = 30 )
	   		#foreach ( $y in [1..$ints] )
	   		#if ( $y == $details.langkah )
	   			#set ( $selected = "selected" )
	   		#else
	   			#set ( $selected = "" )
	   		#end
	   		<option value="$y" $selected>$y</option>
	   		#end
		</select>
           </td>
        </tr>    
	 <tr>
	   <td >Aktif</td>
	   <td>
	   		#set ( $selected1 = "" )
	   		#set ( $selected2 = "" )
	   		#if ( 1 == $details.aktif )
	   			#set ( $selected1 = "selected" )
	   			#set ( $selected2 = "" )
	   		#else
	   			#set ( $selected1 = "" )
		   		#set ( $selected2 = "selected" )
	   		#end	   
		<select class="autoselect" name="FormX_aktif" >
		        <option value="0">Sila Pilih</option>
		        <option value="1" $selected1>YA</option>
		        <option value="2" $selected2>TIDAK</option>
		</select>
	   </td>
        </tr> 

</table>
</fieldset>

	<fieldset>

	<legend>Maklumat Modul</legend>
	<table border="0" cellpadding="2" cellspacing="2">
		<tr>
		 <td >Kumpulan</td>
		 <td>             
		     $selectgroup
		 </td>
	    	</tr>
		<!--<tr>
		     <td >Modul</td>
		     <td>             
		     $selectmodul
		     </td>
	    	</tr>-->
	</table>

	</fieldset>

</table>

<input type=hidden name="id" value="$id">
#if ($mode == "add")
	<input type=button value=Simpan onClick="javascript:doAjaxCall${formname}('doAdd')">
#else
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

<script type="javascript">
function doChanges() {
alert('xxxx');
  doAjaxCall${formName}("edit");
}

function doChanges1() {
  alert('HAI group');
  doAjaxCall${formName}("edit1");
}
</script>

