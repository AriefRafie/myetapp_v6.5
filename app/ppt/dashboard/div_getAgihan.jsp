
<table class="classFade">
<tr>
<td>
<b>Agihan Tugas</b>
</td>
</tr>
#set($TUNGGU_PENGESAHAN_DAN_AGIHAN = 0)
#set($TUNGGU_PENGESAHAN = 0)
#set($TELAH_DISAHKAN = 0)
#set($TUNGGU_UNTUK_DIAGIHAN = 0)
#set($TELAH_DIAGIHKAN= 0)
#set($TUNGGU_SEMAKAN_MMK= 0)
#set($TELAH_DISEMAK = 0)
#set($TUNGGU_SEMAKAN_MMK_PENARIKAN= 0)
#set($TELAH_DISEMAK_PENARIKAN= 0)
#set($LAIN_LAIN= 0)
#set($TELAH_DIAGIHKAN_PENTADBIR= 0)

<!--
#*
#set($TUNGGU_PENGESAHAN_DAN_AGIHAN = 10)
#set($TUNGGU_PENGESAHAN = 10)
#set($TELAH_DISAHKAN = 10)
#set($TUNGGU_UNTUK_DIAGIHAN = 10)
#set($TELAH_DIAGIHKAN= 10)
#set($TUNGGU_SEMAKAN_MMK= 10)
#set($TELAH_DISEMAK = 10)
#set($TUNGGU_SEMAKAN_MMK_PENARIKAN= 10)
#set($TELAH_DISEMAK_PENARIKAN= 10)
#set($LAIN_LAIN= 10)
*#
-->


#foreach ($list in $listAgihan)
<!-- ::::::::::::  $list.ID_STATUS - $list.FLAG_SEMAK - $list.ID_SUBURUSAN - $list.FLAG_SEMAKAN_PENGARAH -->

			#if($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1" && $list.ID_SUBURUSAN=="51")         	   
         	    #set($TUNGGU_PENGESAHAN_DAN_AGIHAN = $TUNGGU_PENGESAHAN_DAN_AGIHAN + 1)         	   
            #elseif($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1" && $list.ID_SUBURUSAN=="52")                  	
         	    #set($TUNGGU_PENGESAHAN = $TUNGGU_PENGESAHAN + 1)                
            #elseif($list.ID_STATUS=="11" && $list.FLAG_SEMAK=="1" && $list.ID_SUBURUSAN=="53")         	
         	    #set($TUNGGU_PENGESAHAN = $TUNGGU_PENGESAHAN + 1)             	 
         	#elseif($list.ID_STATUS=="127" && $list.FLAG_SEMAK=="2")            	
                #set($TELAH_DISAHKAN = $TELAH_DISAHKAN + 1)
            #elseif($list.ID_STATUS=="16")
            	#set($TUNGGU_UNTUK_DIAGIHAN = $TUNGGU_UNTUK_DIAGIHAN + 1)            	
            #elseif($list.ID_STATUS=="148")            	
                #set($TELAH_DIAGIHKAN = $TELAH_DIAGIHKAN + 1)  
            #elseif($list.ID_STATUS=="1612198") 
                   	
                #set($TELAH_DIAGIHKAN_PENTADBIR = $TELAH_DIAGIHKAN_PENTADBIR + 1)                	
            #elseif(($list.ID_STATUS=="132" && $list.FLAG_SEMAKAN_PENGARAH=="1") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="1"))
                #set($TUNGGU_SEMAKAN_MMK = $TUNGGU_SEMAKAN_MMK + 1) 	
            #elseif(($list.ID_STATUS=="133" && $list.FLAG_SEMAKAN_PENGARAH=="2") || ($list.ID_STATUS=="26" && $list.FLAG_SEMAKAN_PENGARAH=="2"))
                #set($TELAH_DISEMAK = $TELAH_DISEMAK + 1)            	
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="1")	
            	#set($TUNGGU_SEMAKAN_MMK_PENARIKAN = $TUNGGU_SEMAKAN_MMK_PENARIKAN + 1)         	
            #elseif($list.ID_STATUS=="74" && $list.FLAG_MMK_PENARIKAN=="2" && $list.STATUS_KEPUTUSAN == "")	
                 #set($TELAH_DISEMAK_PENARIKAN = $TELAH_DISEMAK_PENARIKAN + 1)   
            #else
            	 #set($LAIN_LAIN = $LAIN_LAIN + 1)            
            #end
           
          
       <!-- #*    
			#if($list.ID_STATUS=="11")
         	    #if($list.ID_SUBURUSAN=="51")
         	    #set($TUNGGU_PENGESAHAN_DAN_AGIHAN = $TUNGGU_PENGESAHAN_DAN_AGIHAN + 1)
         	    #else
         	    #set($TUNGGU_PENGESAHAN = $TUNGGU_PENGESAHANN + 1)
         	    #end
         	#elseif($list.ID_STATUS=="127")            	
                #set($TELAH_DISAHKAN = $TELAH_DISAHKAN + 1)
            #elseif($list.ID_STATUS=="16")
            	#set($TUNGGU_UNTUK_DIAGIHAN = $TUNGGU_UNTUK_DIAGIHAN + 1)            	
            #elseif($list.ID_STATUS=="148")            	
                #set($TELAH_DIAGIHKAN = $TELAH_DIAGIHKAN + 1)  	
            #elseif($list.ID_STATUS=="132")
                #set($TUNGGU_SEMAKAN_MMK = $TUNGGU_SEMAKAN_MMK + 1) 	
            #elseif($list.ID_STATUS=="133")            
                #set($TELAH_DISEMAK = $TELAH_DISEMAK + 1)            	
            #elseif($list.ID_STATUS=="74")	
            	#set($TUNGGU_SEMAKAN_MMK_PENARIKAN = $TUNGGU_SEMAKAN_MMK_PENARIKAN + 1)         	
            #elseif($list.ID_STATUS=="74")	
                 #set($TELAH_DISEMAK_PENARIKAN = $TELAH_DISEMAK_PENARIKAN + 1)   
            #else
              	 #set($LAIN_LAIN = $LAIN_LAIN + 1)            
            #end 
            
            
            *# -->
         


#end


   <!--               
saiz :::::::::::: $listAgihan.size()  <br />            
1. TUNGGU_PENGESAHAN_DAN_AGIHAN : $TUNGGU_PENGESAHAN_DAN_AGIHAN <br>
2. TUNGGU_PENGESAHAN : $TUNGGU_PENGESAHAN<br>
3. TELAH_DISAHKAN : $TELAH_DISAHKAN<br>
4. TUNGGU_UNTUK_DIAGIHAN : $TUNGGU_UNTUK_DIAGIHAN<br>
5. TELAH_DIAGIHKAN : $TELAH_DIAGIHKAN<br>
6. TUNGGU_SEMAKAN_MMK : $TUNGGU_SEMAKAN_MMK<br>
7. TELAH_DISEMAK : $TELAH_DISEMAK<br>
8. TUNGGU_SEMAKAN_MMK_PENARIKAN : $TUNGGU_SEMAKAN_MMK_PENARIKAN<br>
9. TELAH_DISEMAK_PENARIKAN : $TELAH_DISEMAK_PENARIKAN<br>
10. LAIN_LAIN : $LAIN_LAIN 
-->
 

#if($TUNGGU_PENGESAHAN_DAN_AGIHAN > 0 || $TUNGGU_PENGESAHAN > 0 || $TELAH_DISAHKAN > 0 || $TUNGGU_UNTUK_DIAGIHAN > 0 || $TELAH_DIAGIHKAN > 0 || $TUNGGU_SEMAKAN_MMK > 0 || $TELAH_DISEMAK > 0 || $TUNGGU_SEMAKAN_MMK_PENARIKAN > 0 || $TELAH_DISEMAK_PENARIKAN > 0 || $TELAH_DIAGIHKAN_PENTADBIR > 0)
         
 
<tr id="tr_agihan1" >
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_PENGESAHAN_DAN_AGIHAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TUNGGU_PENGESAHAN_DAN_AGIHAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan1()" class="help" title="Tunggu pengesahan dan agihan">
                            <font color="blue">Tunggu Pengesahan Dan Agihan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan1"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TUNGGU_PENGESAHAN_DAN_AGIHAN > 0)
<script>
document.getElementById('tr_agihan1').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan1').style.display = "none";
</script>
#end



  
<tr id="tr_agihan2">
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_PENGESAHAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TUNGGU_PENGESAHAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan2()" class="help" title="Tunggu pengesahan">
                            <font color="blue">Tunggu Pengesahan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan2"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TUNGGU_PENGESAHAN > 0)
<script>
document.getElementById('tr_agihan2').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan2').style.display = "none";
</script>
#end


<tr id="tr_agihan3">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DISAHKAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class ="blink">$!TELAH_DISAHKAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan3()" class="help" title="Telah disahkan">
                            <font color="blue">Telah Disahkan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan3"  style="width:40"></div>					
				  
                 
</td>
</tr>

#if($!TELAH_DISAHKAN > 0) 
<script>
document.getElementById('tr_agihan3').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan3').style.display = "none";
</script>
#end

<!--  
<tr id="tr_agihan4">
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_UNTUK_DIAGIHAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TUNGGU_UNTUK_DIAGIHAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan4()" class="help" title="Tunggu untuk diagihkan">
                            <font color="blue">Tunggu Untuk Diagihkan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan4"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TUNGGU_UNTUK_DIAGIHAN > 0)
<script>
document.getElementById('tr_agihan4').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan4').style.display = "none";
</script>
#end



<tr  id="tr_agihan10">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DIAGIHKAN_PENTADBIR > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TELAH_DIAGIHKAN_PENTADBIR</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan10()" class="help" title="Telah diagihkan">
                            <font color="blue">Tunggu Diagihkan Kepada Pentadbir Tanah</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan10"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DIAGIHKAN_PENTADBIR > 0)
<script>
document.getElementById('tr_agihan10').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan10').style.display = "none";
</script>
#end


<tr  id="tr_agihan5">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DIAGIHKAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TELAH_DIAGIHKAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan5()" class="help" title="Telah diagihkan">
                            <font color="blue">Telah Diagihkan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan5"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DIAGIHKAN > 0)
<script>
document.getElementById('tr_agihan5').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan5').style.display = "none";
</script>
#end

-->


<tr id="tr_agihan4">
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_UNTUK_DIAGIHAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!TUNGGU_UNTUK_DIAGIHAN</blink></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan4()" class="help" title="Tunggu untuk diagihkan">
                            <font color="blue">Tunggu Untuk Diagihkan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan4"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TUNGGU_UNTUK_DIAGIHAN > 0)
<script>
document.getElementById('tr_agihan4').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan4').style.display = "none";
</script>
#end



<tr  id="tr_agihan10">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DIAGIHKAN_PENTADBIR > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!TELAH_DIAGIHKAN_PENTADBIR</blink></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan10()" class="help" title="Telah diagihkan">
                            <font color="blue">Telah Diagihkan Kepada Pentadbir Tanah</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan10"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DIAGIHKAN_PENTADBIR > 0)
<script>
document.getElementById('tr_agihan10').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan10').style.display = "none";
</script>
#end


<tr  id="tr_agihan5">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DIAGIHKAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!TELAH_DIAGIHKAN</blink></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan5()" class="help" title="Telah diagihkan">
                            <font color="blue">Telah Diagihkan</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan5"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DIAGIHKAN > 0)
<script>
document.getElementById('tr_agihan5').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan5').style.display = "none";
</script>
#end


<tr id="tr_agihan6">
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_SEMAKAN_MMK > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TUNGGU_SEMAKAN_MMK</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan6()" class="help" title="Tunggu semakan MMK">
                            <font color="blue">Tunggu Semakan MMK</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan6"  style="width:40"></div>					
				  
                 
</td>
</tr>

#if($!TUNGGU_SEMAKAN_MMK > 0)
<script>
document.getElementById('tr_agihan6').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan6').style.display = "none";
</script>
#end

  
<tr id="tr_agihan7">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DISEMAK > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TELAH_DISEMAK</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan7()" class="help" title="Telah Disemak">
                            <font color="blue">Telah Disemak</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan7"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DISEMAK > 0)
<script>
document.getElementById('tr_agihan7').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan7').style.display = "none";
</script>
#end


<tr id="tr_agihan8">
<td>
							<font color="blue"><li>
                            #if($!TUNGGU_SEMAKAN_MMK_PENARIKAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TUNGGU_SEMAKAN_MMK_PENARIKAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan8()" class="help" title="Tunggu Semakan MMK Penarikan Balik">
                            <font color="blue">Tunggu Semakan MMK Penarikan Balik</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan8"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TUNGGU_SEMAKAN_MMK_PENARIKAN > 0)
<script>
document.getElementById('tr_agihan8').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan8').style.display = "none";
</script>
#end



<tr id="tr_agihan9">
<td>
							<font color="blue"><li>
                            #if($!TELAH_DISEMAK_PENARIKAN > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!TELAH_DISEMAK_PENARIKAN</span></font></b>
                             </label>&nbsp;
                             #end                            
                            <a href="javascript:gotoAgihan9()" class="help" title="Telah Disemak MMK Penarikan Balik">
                            <font color="blue">Telah Disemak MMK Penarikan Balik</font>
                            </a>
                            </li></font>                            
                            <div  id="div_agihan9"  style="width:40"></div>					
				  
                 
</td>
</tr>
#if($!TELAH_DISEMAK_PENARIKAN > 0)
<script>
document.getElementById('tr_agihan9').style.display = "";
</script>
#else
<script>
document.getElementById('tr_agihan9').style.display = "none";
</script>
 #end 
 
 #else
 
 <tr>
<td>
							<font color="blue"><li>
                           <font color="black">Tiada Rekod Agihan</font>
                            </a>
                            </li></font>                            
                            				
				  
                 
</td>
</tr>
 #end
</table>    

<script>
$jquery(document).ready(function () {
	doDivAjaxCall3$formname('dashboard_tab','getTabDashboard','');	
	//doDivAjaxCall$formname('div_getAgihan','getAgihan','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');			
});
</script>

       #parse("app/ppt/dashboard/script.jsp")  