<div id="divMainForm">
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0033FF
}
-->
</style>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" id="id" name="id" value="$!laporan.idLaporan">

	#if ($!command == 'daftarDokumen')
  		#parse("$templateDir/daftarDokumen.jsp")	
  	#elseif ($!command == 'paparLaporan' || $!command == 'hapusDokumen')
  		#parse("$templateDir/maklumatLaporan.jsp")		
  	#else 
  		#parse("$templateDir/senaraiLaporan.jsp")
  	#end 
</div>
  
#parse("$templateDir/script.jsp") 