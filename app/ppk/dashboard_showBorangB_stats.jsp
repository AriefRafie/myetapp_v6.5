#if($getListCountBorangB>0) 
<table width="90%" align="center" border="0"  class="alert">   
   <tr>
   <td>
   
   
   
   
   </td>
   </tr> 
   <tr>
   <td width="2%" valign="top" align="right"> 
   
   </td>
   <td width="98%">
   
   <!--
   Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak.   
   Jumlah kemasukkan fail yang dimaksudkan adalah : 
   -->   
   <a href="javascript:gotoSenaraiBorangB()" title="Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak.">
   
  
    
    #if($!getListCountBorangB > 0)                         
                             <label style="background-color:red"  align="center" valign="top" > 
                            <b><font color="WHITE" class="blink"><blink>$!getListCountBorangB</blink></font></b>
                             </label>&nbsp;
                             #end
                             
     
    Fail yang tidak lengkap selepas 30 hari daripada tarikh Borang B dicetak.  
   
   </a>
  <!-- <a href="javascript:gotoSenaraiBorangB()" title="Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak.">
   <font  color="red" onClick="gotoSenaraiBorangB()" ><b><blink>$getListCountBorangB</blink></b></font> 
   </a>-->
   </td>
    
   </tr>
   </table>
   #end
   
<script>

$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountTukarPegawai','showCountTukarPegawai','');	
		
});

</script>
   