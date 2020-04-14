


<br />
<!-- tajuk besar modul -->
<table width="100%" align="center" border="0"  style="margin-bottom:5px;" cellpadding="0" cellspacing="0"  class="box_shadow" >
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong>SEMAKAN DATA  (MyeTaPP SUPPORT)</strong> </td>
<td width="20%" class="underline_td_main" align="right" valign="top" >		
</td>
<td width="20%" class="underline_td_main" align="right">
<input type="button" id="cmdKembaliSenarai" name="cmdKembaliSenarai" value="Senarai Utama" onClick="kembaliSenarai()" style="display:none;" >		
</td>
</tr>
</table>


<div id="div_maklumatUtama" ></div>
<!-- skrin carian utama -->
<div id="div_skrinCarianUtama" > 
#parse("app/ppk/FrmSemakanPPK/carian.jsp")
</div> 

<!-- skrin senarai utama -->
<div id="div_carianUtama" > 
<script>  
$jquery(document).ready(function (){  
    doDivAjaxCall$formname('div_carianUtama','showSenaraiUtama','&div=div_carianUtama&skrinName=carianUtama');  
}); 
</script>
</div >



<br /><br />
<!-- special skrin -->
<div id="div_SQLeditor"  class="boxViewShadow" >
<fieldset>
<legend>Lebah SQL Editor</legend>

<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > 
<tr>
<td width='1%' valign='top' align="center"><span class="red">*</span></td>
<td width='99%' valign='top' align="left" >JENIS STATEMENT : 
  <select name="stmtSQLeditor" id="stmtSQLeditor">
<option value="">SILA PILIH</option>
<option value="SELECT">SELECT</option>
<option value="INSERT">INSERT</option>
<option value="UPDATE">UPDATE</option>
<option value="DELETE">DELETE</option>
<option value="RUN">RUN SCRIPT</option>
</select>
</td>
</tr>
<tr>
<td  valign='top' align="center" ><span class="red">*</span></td>
<td  valign='top'  >
<textarea style="width:100%; height:100px" name="inputSQLeditor" id="inputSQLeditor" ></textarea>
</td>
</tr>
<tr>
<td></td>
<td >
<input type='button' id='cmdExecuteSQL' name='cmdExecuteSQL' value='Execute SQL' onClick="if(validateSQLeditor()){doDivAjaxCall$formname('div_resultSQLeditor','executeSQL','div=div_resultSQLeditor');}" >	
<input type='button' id='cmdResetEditor' name='cmdResetEditor' value='Reset Editor' onClick="resetSQLeditor()" >			
</td>
</tr>
</table>
<div id="div_resultSQLeditor"> 

</div>
</fieldset>

</div>




<script>

function resetSQLeditor()
{
	document.getElementById("stmtSQLeditor").value = "";
	document.getElementById("inputSQLeditor").value = "";
	document.getElementById("div_resultSQLeditor").innerHTML = "";
}


function validateSQLeditor()
{
	var bool_check = true;
	var stmtSQLeditor = document.getElementById("stmtSQLeditor").value;
	var inputSQLeditor = document.getElementById("inputSQLeditor").value;
	
	
	if(stmtSQLeditor.trim() == "")
	{
		alert("Sila Pilih Jenis Statement!");
		document.getElementById("stmtSQLeditor").focus();
		bool_check = false;
	}
	else if(inputSQLeditor.trim() == "")
	{
		alert("Sila Masukkan Statement!");
		document.getElementById("inputSQLeditor").focus();
		bool_check = false;
	}
	else if(stmtSQLeditor.trim() == "INSERT" || stmtSQLeditor.trim() == "UPDATE" || stmtSQLeditor.trim() == "DELETE")
	{
		if(inputSQLeditor.toUpperCase().indexOf(stmtSQLeditor.trim()) < 0){
		  	alert("Tiada "+stmtSQLeditor+" Statement!");
			document.getElementById("inputSQLeditor").focus();
			bool_check = false;
		}
	}
	else if(stmtSQLeditor.trim() == "SELECT" && (inputSQLeditor.toUpperCase().indexOf("INSERT") >= 0 || inputSQLeditor.toUpperCase().indexOf("UPDATE") >= 0 || inputSQLeditor.toUpperCase().indexOf("DELETE") >= 0))
	{
		alert("Terdapat INSERT/UPDATE/DELETE didalam SELECT statment!");
		document.getElementById("inputSQLeditor").focus();
		bool_check = false;
	}
	
	else if(inputSQLeditor.toUpperCase().indexOf("DROP") >= 0)
	{
		alert("DROP STATEMENT ADALAH HARAM SAMA SEKALI!!!!");
		document.getElementById("inputSQLeditor").focus();
		bool_check = false;
	}
	
	
	var bool_check_last = false;
	if(bool_check == true)
	{		
		input_box = confirm("Adakah anda pasti untuk EXECUTE statement ini?" );
		if (input_box == true) {
			bool_check_last = true;
		}
	}
	return bool_check_last;
}

function papar(id_fail, id_seksyen, skrinName)
{
	document.getElementById('cmdKembaliSenarai').style.display = "";
	document.getElementById('div_carianUtama').style.display = "none";
	document.getElementById('div_skrinCarianUtama').style.display = "none";
	doDivAjaxCall$formname('div_maklumatUtama','viewMaklumatMain','&div=div_maklumatUtama&skrinName='+skrinName+'&ID_FAIL='+id_fail+'&ID_SEKSYEN='+id_seksyen);
}

function kembaliSenarai()
{
	$jquery("#div_maklumatUtama").html("");
	document.getElementById('div_carianUtama').style.display = "";
	document.getElementById('div_skrinCarianUtama').style.display = "";
	document.getElementById('cmdKembaliSenarai').style.display = "none";
}


//resetSkrinCarian
function resetSkrinCarian(skrinName)
{
	document.getElementById(skrinName+"CARIAN_NO_FAIL").value = "";
	document.getElementById(skrinName+"CARIAN_NO_PERMOHONAN_ONLINE").value = "";
	document.getElementById(skrinName+"CARIAN_ID_FAIL").value = "";
	
	
	
	/*
	document.getElementById(skrinName+"CARIAN_NAMA_SIMATI").value = "";
	document.getElementById(skrinName+"CARIAN_PENGENALAN_SIMATI").value = "";
	document.getElementById(skrinName+"CARIAN_NAMA_PEMOHON").value = "";
	document.getElementById(skrinName+"CARIAN_PENGENALAN_PEMOHON").value = "";
	document.getElementById(skrinName+"CARIAN_STATUS").value = "";
	document.getElementById(skrinName+"CARIAN_NO_HAKMILIK").value = "";
	document.getElementById(skrinName+"CARIAN_NO_PT").value = "";
	document.getElementById(skrinName+"CARIAN_NO_SIJILMATI").value = "";
	document.getElementById(skrinName+"CARIAN_TARIKH_MOHON").value = "";
	document.getElementById(skrinName+"CARIAN_TARIKH_BICARA").value = "";
	*/
	//document.getElementById(skrinName+"CARIAN_PENDAFTAR").value = "";
	document.getElementById("WC"+skrinName+"CARIAN_ID_FAIL").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_FAIL").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_PERMOHONAN_ONLINE").value = "1";
	/*
	document.getElementById("WC"+skrinName+"CARIAN_NAMA_SIMATI").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PENGENALAN_SIMATI").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NAMA_PEMOHON").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PENGENALAN_PEMOHON").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_HAKMILIK").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_PT").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_NO_SIJILMATI").value = "1";
	*/
	//document.getElementById("WC"+skrinName+"CARIAN_PENDAFTAR").value = "1";
	
}



function checkSkrinCarian(skrinName)
{
	var CARIAN_ID_FAIL = document.getElementById(skrinName+"CARIAN_ID_FAIL").value;
	var CARIAN_NO_FAIL = document.getElementById(skrinName+"CARIAN_NO_FAIL").value;
	var CARIAN_ID_SEKSYEN = document.getElementById(skrinName+"CARIAN_ID_SEKSYEN").value;
	var CARIAN_NO_PERMOHONAN_ONLINE = document.getElementById(skrinName+"CARIAN_NO_PERMOHONAN_ONLINE").value;
	/*
	var CARIAN_NAMA_SIMATI = document.getElementById(skrinName+"CARIAN_NAMA_SIMATI").value;
	var CARIAN_PENGENALAN_SIMATI = document.getElementById(skrinName+"CARIAN_PENGENALAN_SIMATI").value;
	var CARIAN_NAMA_PEMOHON = document.getElementById(skrinName+"CARIAN_NAMA_PEMOHON").value;
	var CARIAN_PENGENALAN_PEMOHON = document.getElementById(skrinName+"CARIAN_PENGENALAN_PEMOHON").value;
	var CARIAN_STATUS = document.getElementById(skrinName+"CARIAN_STATUS").value;
	var CARIAN_NO_HAKMILIK = document.getElementById(skrinName+"CARIAN_NO_HAKMILIK").value;
	var CARIAN_NO_PT = document.getElementById(skrinName+"CARIAN_NO_PT").value;
	var CARIAN_NO_SIJILMATI = document.getElementById(skrinName+"CARIAN_NO_SIJILMATI").value;
	var CARIAN_TARIKH_MOHON = document.getElementById(skrinName+"CARIAN_TARIKH_MOHON").value;
	var CARIAN_TARIKH_BICARA = document.getElementById(skrinName+"CARIAN_TARIKH_BICARA").value;
	*/
	//var CARIAN_PENDAFTAR = document.getElementById(skrinName+"CARIAN_PENDAFTAR").value;
	
	
	var checkCari = true;
	
	if((CARIAN_NO_FAIL == "" || CARIAN_NO_FAIL == "JKPTG/PK/")
	&& CARIAN_NO_PERMOHONAN_ONLINE == "" && CARIAN_ID_SEKSYEN == "" && CARIAN_ID_FAIL == ""
	/*
	&& CARIAN_NAMA_SIMATI == ""
	&& CARIAN_PENGENALAN_SIMATI == "" 
	&& CARIAN_NAMA_PEMOHON == ""
	&& CARIAN_PENGENALAN_PEMOHON == "" 
	&& CARIAN_STATUS == ""
	&& CARIAN_NO_HAKMILIK == ""
	&& CARIAN_NO_PT == "" 
	&& CARIAN_NO_SIJILMATI == ""
	&& CARIAN_TARIKH_MOHON == ""
	&& CARIAN_TARIKH_BICARA == ""
	*/
	//&& CARIAN_PENDAFTAR == ""
	)
	{
		 alert("Sila Masukkan 'Filter' Carian!");
		 checkCari = false;
	}
	
	var bool_check_last = false;
	if(checkCari == true)
	{		
		//input_box = confirm("Adakah anda pasti?" );
		//if (input_box == true) {
			bool_check_last = true;
		//}
	}
	return bool_check_last;
	
}
	
</script>

<!-- default index calls, wajib -->
#parse("app/RazTemplate/index.jsp")