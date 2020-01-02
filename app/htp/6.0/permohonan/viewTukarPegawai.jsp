
#if($skrinName == "tukarpegawaiKPP")
<fieldset>
#end


#if($SuccessMesej!="")   
<div class="info" id="success_viewTukarPegawai" align="left">
    #if($SuccessMesej=="PermohonTukarPegawai")	    
    Permohonan Tukar Pegawai Berjaya Didaftarkan  
    #elseif($SuccessMesej=="KelulusanTukarPegawai")	    
    Kelulusan Permohonan Tukar Pegawai Berjaya Didaftarkan  
    #end
    <script >
	$jquery("#success_viewTukarPegawai").show().delay(3000).fadeOut();
	</script>
</div>
#end


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px"  class="classFade box_shadow">
<tr class="table_header" >
<td width="2%" class="underline_td_sub">
</td>
<td width="58%" class="underline_td_sub">
<strong>MAKLUMAT TUKAR PEGAWAI BICARA</strong></td>
<td width="38%" class="underline_td_sub" align="right" valign="top" >		
</td>
<td width="2%" class="underline_td_sub">		
</td>
</tr>
</table>

#parse("app/ppk/BicaraInteraktif/listPermohonanTukarPegawaiHistory.jsp")

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top:5px;margin-bottom:5px" class="box_shadow">
<tr  >
<td width="2%" >
</td>
<td width="96%" >
$htmlSkrinMaklumat
</td>
<td width="2%" >
</td>
</tr>
</table>

#if($skrinName == "tukarpegawaiKPP")
</fieldset>
#end


#if($command == "openSkrinTukarPegawai" || $command == "openSkrinTukarPegawai_multiple")
 <script>
 //alert('$div'); 
 $jquery(document).ready(function () {
     $jquery('#'+'$div').scrollView();     
 });	 
 </script> 
#elseif($command == "resetTukarPegawaiMultiple") 
  <script>
 	checkTPCheckBox('');
 </script>  
#end