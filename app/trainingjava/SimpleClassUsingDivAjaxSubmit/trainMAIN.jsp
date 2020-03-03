<div id="skrinPAPAR"></div>
<div id="skrinLIST"></div>
<input name="ID" type="hidden" id="ID"  />

<script>

//$jquery("#beza_luas").html("<font color='blue'><b>"+beza_luas.toFixed(4)+"</b></font>");

$jquery(document).ready(function () {
	doDivAjaxCall$formname('skrinLIST','paparListTRAIN','');
});

function tambahPENGGUNA()
	{
	document.${formName}.ID.value = "";
	doDivAjaxCall${formName}("skrinPAPAR", "tambahPENGGUNA", "");
	}

function btnSIMPAN()
	{
		
		
	if (document.${formName}.NAMA.value == "")
		{
			alert("Please fill the information");
		}/*
	else if (document.${formName}.IC.value == "")
		{
			alert("Please fill the information");
		}
	else if (document.${formName}.UMUR.value == "")
		{
			alert("Please fill the information");
		}
	else if (document.${formName}.JANTINA.value == "")
		{
			alert("Please fill the information");
		}
	else if (document.${formName}.ALAMAT.value == "")
		{
			alert("Please fill the information");
		}
			else if (document.${formName}.POSKOD.value == "")
		{
			alert("Please fill the information");
		}
	else if (document.${formName}.NEGERI.value == "")
		{
			alert("Please select one");
		}*/
	else
		{
	doDivAjaxCall${formName}("skrinLIST", "SIMPAN", "");
		}
		
	}
	
function paparTRAIN(id)
{
	document.${formName}.ID.value = id;
	doDivAjaxCall$formname('skrinPAPAR','paparTRAIN','');
}
	
function btnBUANG(id)
	{
	document.${formName}.ID.value = id;
	doDivAjaxCall$formname('skrinLIST','deleteData','');	
	document.${formName}.ID.value = "";
	document.${formName}.NAMA.value = "";
	document.${formName}.IC.value = "";
	document.${formName}.UMUR.value = "";
	document.${formName}.JANTINA.value = "";
	document.${formName}.ALAMAT.value = "";
	document.${formName}.POSKOD.value = "";
	document.${formName}.NEGERI.value = "";
	}


</script>
