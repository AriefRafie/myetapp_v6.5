
<style>
.fontUpperCase{
	text-transform: uppercase;
}
</style>

<fieldset style="width:100%">
	
	<fieldset style="width:95%;margin:auto;text-align:center;text-transform: uppercase;font-size:14px;font-weight:bold">
		Maklumat Tanah Pesuruhjaya Tanah Persekutuan 
	</fieldset>
	
	<br/>
	
	#if($!selectedForm.equalsIgnoreCase("main"))
		#parse("app/htp/kjp/rekod/template/main.jsp")
	#elseif($!selectedForm.equalsIgnoreCase("reportNegeri"))
		#parse("app/htp/kjp/rekod/template/reportNegeri.jsp")
	#elseif($!selectedForm.equalsIgnoreCase("reportKementerian"))
		#parse("app/htp/kjp/rekod/template/reportKementerian.jsp")
	#elseif($!selectedForm.equalsIgnoreCase("reportNegeriDetail"))
		#parse("app/htp/kjp/rekod/template/reportNegeriDetail.jsp")
	#elseif($!selectedForm.equalsIgnoreCase("reportKementerianDetail"))
		#parse("app/htp/kjp/rekod/template/reportKementerianDetail.jsp")	
	#elseif($!selectedForm.equalsIgnoreCase("reportAgensiDetail"))
		#parse("app/htp/kjp/rekod/template/reportAgensiDetail.jsp")		
	#elseif($!selectedForm.equalsIgnoreCase("reportLot"))
		#parse("app/htp/kjp/rekod/template/reportLot.jsp")	
	#end
	
</fieldset>

<!-- javascript -->
#parse("app/htp/kjp/rekod/script.jsp")




