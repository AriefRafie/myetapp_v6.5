<style>
.showCursor{
	cursor: pointer;
}
.normalBar{ 
	background-color:#1463e5;
	border-right: 2px solid #1463e5;
	border-left: 2px solid #1463e5;
	color: white;	
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
.normalBarHM{ 
	background-color:#1463e5;
	border-right: 2px solid #1463e5;
	border-left: 2px solid #1463e5;
	color: white;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;	
}
.normalBorderAwal{ 
	border-left: 2px solid #1463e5;
}
.normalBorderAkhir{ 
	border-right: 2px solid #1463e5;
}
.normalBar:hover  {
  	background-color:#0e48a4;
}

.delayBar{ 
	background-color:#bf0e33;
	border-right: 2px solid #bf0e33;
	border-left: 2px solid #bf0e33;
	color: white;	
    text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
.delayBarHM{ 
	background-color:#bf0e33;
	border-right: 2px solid #bf0e33;
	border-left: 2px solid #bf0e33;
	color: white;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;	
}
.delayBar:hover  {
  	background-color:#8e0b27;
}
.delayBorderAwal{ 
	border-left: 2px solid #bf0e33;
}
.delayBorderAkhir{ 
	border-right: 2px solid #bf0e33;
}
</style>


<br />
<!-- tajuk besar modul -->
<table width="100%" align="center" border="0"  style="margin-bottom:5px;" cellpadding="0" cellspacing="0"  class="box_shadow" >
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong>KPI PPT</strong> </td>
<td width="20%" class="underline_td_main" align="right" valign="top" >		
</td>
<td width="20%" class="underline_td_main" align="right">
<!--
<input type="button" id="cmdKembaliSenarai" name="cmdKembaliSenarai" value="Senarai Utama" onClick="kembaliSenarai()" style="display:none;" >	
-->	
</td>
</tr>
</table>

$html

<div id="div_projekBased" > 
<script>  $jquery(document).ready(function (){  
	//doDivAjaxCall$formname('div_projekBased','showProjekBased','&div=div_projekBased&skrinName=projekBased');  
}); 
</script>
</div >


<script>
function checkSkrinCarian(skrinName)
{
	var CARIAN_NO_FAIL = document.getElementById(skrinName+"CARIAN_NO_FAIL").value;
	var CARIAN_PROJEK = document.getElementById(skrinName+"CARIAN_PROJEK").value;
	var CARIAN_JENIS_KPI = document.getElementById(skrinName+"CARIAN_JENIS_KPI").value;
	var CARIAN_TAHUN_MULA = document.getElementById(skrinName+"CARIAN_TAHUN_MULA").value;
	var CARIAN_TAHUN_AKHIR = document.getElementById(skrinName+"CARIAN_TAHUN_AKHIR").value;
	
	var checkCari = true;
	
	if(CARIAN_NO_FAIL == ""
	&& CARIAN_JENIS_KPI == "" 	
	&& CARIAN_PROJEK == "" 	
	&& CARIAN_TAHUN_MULA == "" 	
	&& CARIAN_TAHUN_AKHIR == "" 	
	)
	{
		 alert("Sila Masukkan 'Filter' Carian!");
		 checkCari = false;
	}
	else if (CARIAN_JENIS_KPI == "")
	{
		 alert("Sila Masukkan Jenis KPI!");
		 checkCari = false;
		 document.getElementById(skrinName+"CARIAN_JENIS_KPI").focus();
	}
	else if (CARIAN_TAHUN_MULA == "")
	{
		 alert("Sila Masukkan Tahun Mula Permohonan!");
		 checkCari = false;
		 document.getElementById(skrinName+"CARIAN_TAHUN_MULA").focus();
	}
	else if (CARIAN_TAHUN_AKHIR == "")
	{
		 alert("Sila Masukkan Tahun Akhir Permohonan!");
		 checkCari = false;
		 document.getElementById(skrinName+"CARIAN_TAHUN_AKHIR").focus();
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


function openHakmilikBased(ID_FAIL)
{
	//alert(ID_FAIL);
	
	//var myTd=document.getElementById("tdRowSpan"+ID_FAIL);
	//var myTr=document.getElementById("trHakmilikBase"+ID_FAIL);
    var setOpenClose =  document.getElementById("setOpenClose"+ID_FAIL).value;
	
	if(setOpenClose=="close")
	{
		//myTd.setAttribute("rowspan","2");
		//myTr.style.display = "";
		doDivAjaxCall$formname('div_hakmilikBased'+ID_FAIL,'showHakmilikBased','&div=div_hakmilikBased'+ID_FAIL+'&skrinName=hakmilikBased'+ID_FAIL+'&ID_FAIL='+ID_FAIL);
		document.getElementById('setOpenClose'+ID_FAIL).value = "open";  
	}
	else
	{
		//myTd.setAttribute("rowspan","1");
		//myTr.style.display = "none";
		document.getElementById('div_hakmilikBased'+ID_FAIL).innerHTML = "";
		document.getElementById('setOpenClose'+ID_FAIL).value = "close";  
	}
	
	
}

//resetSkrinCarian
function resetSkrinCarian(skrinName)
{
	document.getElementById(skrinName+"CARIAN_NO_FAIL").value = "";
	document.getElementById(skrinName+"CARIAN_PROJEK").value = "";
	document.getElementById(skrinName+"CARIAN_JENIS_KPI").value = "";	
	
	document.getElementById("WC"+skrinName+"CARIAN_NO_FAIL").value = "1";
	document.getElementById("WC"+skrinName+"CARIAN_PROJEK").value = "1";
	
	document.getElementById("div_projekBased").innerHTML = "";
	
}
</script>


#parse("app/RazTemplate/index.jsp")