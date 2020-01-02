$formDynamicDropDown



#if($flagSalin == "Y")
<!-- SPECIAL CONDITION UNTUK SALIN MAKLUMAT -->
<!--
flagSalin :: $flagSalin<br />
divID :: $divID <br />
commandSalin :: $commandSalin<br />
ID_NEGERI :: $ID_NEGERI<br />
ID_BANDAR :: $ID_BANDAR<br />
ID_PERBICARAAN :: $ID_PERBICARAAN<br />
skrinName :: $skrinName<br />
-->
<script> 



$jquery(document).ready(function () {
//alert('1');	
doDivAjaxCall$formname('$divID','$commandSalin','ID_NEGERISALIN=$ID_NEGERI&ID_BANDARSALIN=$ID_BANDAR&skrinName=$skrinName&ID_PERBICARAAN=$ID_PERBICARAAN');
});
//alert('2');
</script>	
#end

<script>
//untuk handle drople yg maklumatnya disable readonly
//alert("$skrinName"+"NAMA_PEJABAT");
if('$skrinName'=="keputusan")
{
	var myEle = document.getElementById("$skrinName"+"NAMA_PEJABAT");
	if(myEle){
		var myEle = document.getElementById("$skrinName"+"NAMA_PEJABAT");
		//alert('ada : readonlystatus : '+myEle.readOnly);
		if(myEle.readOnly == true)
		{
			pejabatAddReadonly('$skrinName')
		}	
		else
		{
			pejabatRemoveReadonly('$skrinName')
		}
	}
	else
	{
		//alert('tiada');
	}
}
</script>