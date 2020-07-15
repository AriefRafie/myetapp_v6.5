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
  #if ($!command == 'paparFail')
  	#parse("$templateDir/paparFail.jsp")
  #else 
  	#parse("$templateDir/senaraiFail.jsp")
  #end 
</div>
  
#parse("$templateDir/script.jsp") 