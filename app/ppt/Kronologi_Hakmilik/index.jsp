<br><br>

#if($!carian=="yes")
#parse("app/ppt/Kronologi_Hakmilik/frmKronologi.jsp")
#end

#if($!skrinHakmilik=="yes")
#parse("app/ppt/Kronologi_Hakmilik/frmHakmilik.jsp")
#end

<div style="display:none">
ScrollX :<input type="text" id="ScrollX" name='ScrollX'  />
ScrollY :<input type="text" id="ScrollY" name='ScrollY'  />
<input type="text" id="ID_HAKMILIK" name="ID_HAKMILIK"  value = "$!ID_HAKMILIK"/>
</div>



<script type="text/javascript">
ResetScrollPosition();

function cetakHakmilik(id_hakmilik) {
    var url = "../servlet/ekptg.report.ppt.KronologiHakmilik?id_hakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
}
   
function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
    hidy = '$ScrollY';
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
}

function resetSkrin()
{
	SaveScrollXY();
	doAjaxCall${formName}("");
	document.${formName}.btnCariHakmilikReset.value = "Sila Tunggu....";
}


function paparHakmilik(id_hakmilik)
{
	  document.${formName}.ID_HAKMILIK.value = id_hakmilik;
	  SaveScrollXY();
	  doAjaxCall${formName}("paparHakmilik");
	
}


function cariHakmilik()
{
	if(document.${formName}.ID_JENISHAKMILIK.value == "" && document.${formName}.NO_HAKMILIK.value == "" && document.${formName}.NO_LOT_PT.value == "" && document.${formName}.NO_FAIL.value == "" && document.${formName}.NAMA_PROJEK.value == "")
   	{
   		alert("Sila Masukkan Maklumat Carian!");  
   	}  
   	else  
   	{
	  SaveScrollXY();
	  doAjaxCall${formName}("carianHakmilik");
	  document.${formName}.btnCariHakmilik.value = "Sila Tunggu....";
	}
}


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
		   if(lepas_or_xlepas == "2")
		   {	   
		   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
		   }
		   else
		   {	  
				   if (my_form.value.length >= maxLen) 
				   {
				   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
			my_form.value = my_form.value.substring(0, maxLen);
				   maxLen = 0;
				   }
				   else
				   {
				   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
				   maxLen = maxLen - my_form.value.length;
				   }		
		   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");



}

</script>