<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>

<!-- ::::::::::: $!total_harta -->
<br/>
<body >
<fieldset ><legend><b>Perubahan Akta</b></legend>
		
		<table width="40%" align="left" border="0">
			<tr>
      			<td align="left">Emel Pemohon : </td>
      			<td width="15%" align="left">
      			<select name="emelPemohon" id="emelPemohon" $disabled>
      			<#if ($FLAG_EMAIL_PEMOHON == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
				</td>
    		</tr>
    		<tr>
      			<td align="left">Telefon Bimbit Pemohon : </td>
      			<td width="15%" align="left">
      			<select name="hphone" id="hphone" $disabled>
      			<#if ($FLAG_NOTELEFONBIMBIT_PEMOHON == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
      			</td>
    		</tr>
    		<tr>
      			<td align="left">Emel Waris : </td>
      			<td width="15%" align="left">
      			<select name="emelWaris" id="emelWaris" $disabled>
      			<#if ($FLAG_EMAIL_WARIS == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
				</td>
    		</tr>
    		<tr>
      			<td align="left">5 Juta : </td>
      			<td width="15%" align="left">
      			<select name="flag5juta" id="flag5juta" $disabled>
      			<#if ($FLAG_5JUTA == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
      			</td>
    		</tr>
    		<tr>
      			<td align="left">Akses Skrin Keputusan Perbicaraan : </td>
      			<td width="15%" align="left">
      			<select name="flagAksesKepBicara" id="flagAksesKepBicara" $disabled>
      			<#if ($AKSES_SKRIN_KEPBICARA == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
      			</td>
    		</tr>
    		<tr>
      			<td align="left">PT boleh kemaskini Perintah : </td>
      			<td width="15%" align="left">
      			<select name="flagKemaskiniPerintahPT" id="flagKemaskiniPerintahPT" $disabled>
      			<#if ($FLAG_KEMASKINIPERINTAH_PT == "T")
 					 <option value="T" selected>True</option>
 					 <option value="F">False</option>
 				#else
 					 <option value="T">True</option>
 					 <option value="F" selected>False</option>
 				#end
				</select>
      			</td>
    		</tr>
    		
    		<tr>
    		
    		<td align = "center">
    		#if ($buttonKemaskini == "yes")
    		<input type="button" name="cmdKemaskini" id="cmdKemaskini1" value="Kemaskini" onClick="Kemaskini()"/>
    		#end
    		
    		#if ($buttonSimpan == "yes")
    		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="Simpan()"/>&nbsp;
    		<input type="button" name="cdmBatal" id="cdmBatal" value="Batal" onClick="Batal()"/>
    		#end
    		</td>
    		</tr>
    		
		</table>
		
	











</fieldset>

#parse("app/ppk/headerppk_script.jsp")



<script type="text/javascript">
function Kemaskini() {
	
		document.${formName}.method = "POST";
		document.${formName}.command.value="Kemaskini";
		
		
		document.${formName}.action = "";
		document.${formName}.submit();
}

function Batal() {

	input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
		document.${formName}.method = "POST";
	   
	    document.${formName}.command.value="";
	    document.${formName}.action = "";
		document.${formName}.submit();
		}
		else
		{
		return;
		}
	}
	
function Simpan() {

	input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
		document.${formName}.method = "POST";
	   
	    document.${formName}.command.value="simpan";
	    document.${formName}.action = "";
		document.${formName}.submit();
		}
		else
		{
		return;
		}
	}



</script>