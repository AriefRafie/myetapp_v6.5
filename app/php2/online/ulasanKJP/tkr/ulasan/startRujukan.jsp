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
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
</p>

<div id="divMainForm">  
  #if ($!submit == 'paparFail')
  	#parse("$templateDir/paparFailRujukan.jsp")
  #else 
  	#parse("$templateDir/senaraiFailRujukan.jsp")
  #end 
</div>
  
#parse("$templateDir/script.jsp") 