<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>

<style type="text/css">
.style1 {color: #FF0000}
.style2 {color: #0000FF}
#parse("css/eTapp_PPT.css")
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="idFail" value="$!idFail">
<input type="hidden" name="idPermohonan" value="$!idPermohonan">
<input type="hidden" name="jenisSkrin" value="$!jenisSkrin">
<input type="hidden" name="idPermohonanIntegrasi" value="$!idPermohonanIntegrasi">
<input type="hidden" name="idPPTWarta" value="$!idPPTWarta">
<input type="hidden" name="idPPTHakmilik" value="$!idPPTHakmilik">
<input type="hidden" name="idPPTPenarikanBalik" value="$!idPPTPenarikanBalik">

  #set($tajuk_popup = "")
  #if($!jenisSkrin == "BorangC")         
  	#set($tajuk_popup = "Skrin Integrasi Borang C")
	#elseif($!jenisSkrin == "Sekyen8")         
  		#set($tajuk_popup = "Skrin Integrasi Permohonan Seksyen 8")
  #elseif($!jenisSkrin == "BorangA")         
  	#set($tajuk_popup = "Skrin Integrasi Borang A")
  #elseif($!jenisSkrin == "MMK_S8")         
  	#set($tajuk_popup = "Skrin Integrasi Deraf MMK (Syor Pentadbir Tanah)")
  #elseif($!jenisSkrin == "MMK_S4")         
  	#set($tajuk_popup = "Skrin Integrasi Deraf MMK (Syor Pentadbir Tanah)")
  #elseif($!jenisSkrin == "BorangK")         
  	#set($tajuk_popup = "Skrin Integrasi Borang K")
  #elseif($!jenisSkrin == "hantarPelanChartingS8")         
  	#set($tajuk_popup = "Skrin Muat Naik Pelan Charting (Seksyen 8)")
  #elseif($!jenisSkrin == "hantarPelanChartingS4")         
  	#set($tajuk_popup = "Skrin Muat Naik Pelan Charting (Seksyen 4)")
  #elseif($!jenisSkrin == "PU")         
  	#set($tajuk_popup = "Skrin Integrasi Pelan PA & B1")  
  #elseif($!jenisSkrin == "SijilUkur")         
  	#set($tajuk_popup = "Skrin Hantar Sijil Pembebasan Ukur")  
  #elseif($!jenisSkrin == "BorangI")         
  	#set($tajuk_popup = "Skrin Integrasi Borang I")  
  #elseif($!jenisSkrin == "TarikBalik")         
  	#set($tajuk_popup = "Skrin Integrasi Penarikan Balik")  
  #elseif($!jenisSkrin == "WartaS4")         
  	#set($tajuk_popup = "Skrin Integrasi Hantar Borang B & Maklumat Warta")
  #elseif($!jenisSkrin == "WartaS8")         
  	#set($tajuk_popup = "Skrin Integrasi Hantar Borang D & Maklumat Warta")  
  #end

<fieldset><legend>$!tajuk_popup</legend>

#parse("$templateDir/formPermohonan.jsp")
#if ($!maklumatPermohonan.flagEndorsan == 'Y')
	#parse("$templateDir/formEndorsan.jsp")
#end

<div id="divButton"> #parse("$templateDir/button.jsp") </div>

</fieldset>  
