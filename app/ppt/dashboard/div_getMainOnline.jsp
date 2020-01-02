<table width="100%" class="classFade">
<tr>
<td>
<b>Capaian Pantas Permohonan <i>Online</i></b>
</td>
</tr>

#if($count_online_sek4 > 0 || $count_online_sek8 > 0 || $count_online_bantahan_pb > 0 || $count_online_bantahan_ag > 0 || $count_online_penarikan > 0 || $count_online_pembatalan > 0 || $count_online_sementara > 0)

<tr id="tr_getOnline4">
<td>


      

							<font color="blue"><li>
                            #if($!count_online_sek4 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_sek4</span></font></b>
                             </label>&nbsp;
                             #end                            
                           <a href="javascript:gotoSek4()" class="help" title="Senarai Penerimaan Permohonan Online Seksyen 4">
                            <font color="blue">Seksyen 4</font>
                            </a>
                            </li></font>						
				  
                  <div  id="div_getOnline4"  style="width:40"></div>
</td>
</tr>
#if($!count_online_sek4 > 0)
<script>
document.getElementById('tr_getOnline4').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnline4').style.display = "none";
</script>
#end


<tr id="tr_getOnline8">
<td>
							<font color="blue"><li>
                            #if($!count_online_sek8 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_sek8</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSek8()" class="help" title="Senarai Penerimaan Permohonan Online Seksyen 8">
                            <font color="blue">Seksyen 8</font></a>
                            </li></font>			  
                   
                 <div   id="div_getOnline8"  style="width:40"> </div>
                  
</td>
</tr>

#if($!count_online_sek8 > 0)
<script>
document.getElementById('tr_getOnline8').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnline8').style.display = "none";
</script>
#end






<tr id="tr_getOnlineBantahanPB">
<td>
							<font color="blue"><li>
                            #if($!count_online_bantahan_pb > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_bantahan_pb</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSekBantahanPB()" class="help" title="Senarai Penerimaan Permohonan Online Bantahan Mahkamah (Pihak Berkepentingan)"><font color="blue">Bantahan Mahkamah (PB)</font></a>
                            </li></font>
                  <div  id="div_getOnlineBantahanPB"  style="width:40"></div>
</td>
</tr>
#if($!count_online_bantahan_pb > 0)
<script>
document.getElementById('tr_getOnlineBantahanPB').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnlineBantahanPB').style.display = "none";
</script>
#end


<tr id="tr_getOnlineBantahanAG">
<td>
						<font color="blue"><li>
                            #if($!count_online_bantahan_ag > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_bantahan_ag</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSekBantahanAG()" class="help" title="Senarai Penerimaan Permohonan Online Bantahan Mahkamah Agensi"><font color="blue">Bantahan Mahkamah (Agensi)</font></a>
                            </li></font>						
				  
                  <div  id="div_getOnlineBantahanAG"  style="width:40"></div>
</td>
</tr>
#if($!count_online_bantahan_ag > 0)
<script>
document.getElementById('tr_getOnlineBantahanAG').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnlineBantahanAG').style.display = "none";
</script>
#end

<tr id="tr_getOnlinePenarikan">
<td>
							<font color="blue"><li>
                            #if($!count_online_penarikan > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_penarikan</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSekPenarikan()" class="help" title="Senarai Penerimaan Permohonan Online Penarikan Balik"><font color="blue">Penarikan Balik</font></a>
                            </li></font>						
				 
                  <div  id="div_getOnlinePenarikan"  style="width:40"></div>
</td>
</tr>
#if($!count_online_penarikan > 0)
<script>
document.getElementById('tr_getOnlinePenarikan').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnlinePenarikan').style.display = "none";
</script>
#end

<tr id="tr_getOnlinePembatalan">
<td>
							<font color="blue"><li>
                            #if($!count_online_pembatalan > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_pembatalan</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSekPembatalan()" class="help" title="Senarai Penerimaan Permohonan Online Pembatalan"><font color="blue">Pembatalan</font> </a>                            
                            </li></font>			 
                  <div  id="div_getOnlinePembatalan"  style="width:40"></div>
</td>
</tr>
#if($!count_online_pembatalan > 0)
<script>
document.getElementById('tr_getOnlinePembatalan').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnlinePembatalan').style.display = "none";
</script>
#end

<tr id="tr_getOnlineSementara">
<td>


							<font color="blue"><li>
                            #if($!count_online_sementara > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_online_sementara</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoSekSementara()" class="help" title="Senarai Penerimaan Permohonan Online Pengambilan Sementara">
                           <font color="blue">Pengambilan Sementara</font> </a>
                            
                            </li></font>						
				 
                  <div  id="div_getOnlineSementara"  style="width:40"></div>
</td>
</tr>
#if($!count_online_sementara > 0)
<script>
document.getElementById('tr_getOnlineSementara').style.display = "";
</script>
#else
<script>
document.getElementById('tr_getOnlineSementara').style.display = "none";
</script>
#end

#else
 
<tr>
<td>
							<font color="blue"><li>
                           <font color="black">Tiada Rekod Permohonan <i>Online</i></font>
                            </a>
                            </li></font>                            
                            				
				  
                 
</td>
</tr>
 #end

</table>

<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	doDivAjaxCall3$formname('div_getAgihan','getAgihan','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');			
});
</script>

 #parse("app/ppt/dashboard/script.jsp")