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
  	#parse("app/php2/online/ulasanKJP/mof/paparFail.jsp")
  #else 
  	#parse("app/php2/online/ulasanKJP/mof/senaraiFail.jsp")
  #end 
</div>
  
#parse("app/php2/online/ulasanKJP/mof/script.jsp") 