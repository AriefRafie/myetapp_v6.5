
    
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>
  <script src="../RGraph/libraries/RGraph.common.core.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.dynamic.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.tooltips.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.effects.js" ></script>
    <script src="../RGraph/libraries/RGraph.hbar.js" ></script>
    <script src="../RGraph/libraries/RGraph.pie.js" ></script>   

<!--
<table width="100%" cellspacing="0">
	<tr>
		<td width="1%">
		
		</td>
		<td><blink ><FONT color="RED" size="+1"><b>SILA ABAIKAN SKRIN INI, SKRIN INI DIDALAM PROSES PEMBANGUNAN DAN PENGUJIAN, TERIMA KASIH!</b></FONT></blink></td>
	</tr>
	
</table>

-->

<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
<tr>
<td width="1%" valign="top"   >






</td>
<td width="59%" valign="top"  >
<!-- class="dashboard_sub_kiri" -->

<table width="100%" border="0" align="left" class="dashboard_kiri">
  <tr>
    <td valign="top" align="left">
    
    
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
<tr>
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img src="../img/myprofile.png"  width="30" height="30" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Profil</b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan"><font color="blue"><li>&nbsp;Tukar Kata Laluan</li></font></a>
</td>
</tr>

<!--<tr>
<td>
#set ( $tab = $EkptgUtil.getTabID("'My Info'",$portal_role) )
<a href="$tab?_portal_module=lebah.pm.module.ActivitiesModule" class="help" title="MyCalendar">
							<font color="blue"><li><i>&nbsp;My Calendar</i></li></font>						
						</a>
                  
</td>
</tr>-->

<tr>
<td id="showNotifikasi_Inbox">
<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('showNotifikasi_Inbox','showNotifikasi_Inbox','');					
});
</script>
</td>
</tr>

</table>
</td>
</tr>
<tr>
<td width="15%" align="center" valign="top">&nbsp;</td>

<td>
<table>

<tr>
<td>
<b>Panduan</b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoPanduan()" class="help" title="Manual Pengguna"><font color="blue"><li>Manual Pengguna</li></font></a>
</td>
</tr>

<!--<tr>
<td>
#set ( $tab = $EkptgUtil.getTabID("'My Info'",$portal_role) )
<a href="$tab?_portal_module=lebah.pm.module.ActivitiesModule" class="help" title="MyCalendar">
							<font color="blue"><li><i>&nbsp;My Calendar</i></li></font>						
						</a>
                  
</td>
</tr>-->

<tr>
<td id="showNotifikasi_Inbox">
<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('showNotifikasi_Inbox','showNotifikasi_Inbox','');					
});
</script>
</td>
</tr>

</table>
</td>
</tr>
</table>

</td>
<td width="50%" valign="top">

<table width="100%" >
<!-- <tr> -->
<!-- <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/soalan.png" align="center"/></td> -->
<!-- <td width="85%"> -->
<!-- <table> -->

<!-- <tr> -->
<!-- <td> -->
<!-- <b>Pertanyaan</b> -->
<!-- </td> -->
<!-- </tr> -->

<!-- <tr> -->
<!-- <td> -->
<!-- <a href="#" onclick="window.open('http://www.etapp.gov.my/eTappv2/', 'sss', '');" class="help" > -->
<!-- 							<font color="blue">http://www.etapp.gov.my/eTappv2/</font>						 -->
<!-- 				  </a> -->
<!-- </td> -->
<!-- </tr> -->
<!-- </table> -->

<!-- </td> -->
<!-- </tr> -->

<tr>
	<td width="15%" align="center" valign="top"><!--<img width="30" height="30" src="../img/cari_icon.png" align="center"/>--></td>
		<td width="85%">
			<!--<table>
				<tr>
					<td>
						<b>Carian Kes Luar</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="search" id="search" maxlength="200" size="30" onBlur="this.value=this.value.toUpperCase()" onkeydown="return runScript(event)" onkeypress="return runScript(event)" onkeyup="return runScript(event)"  /> 
							<a href="javascript:open_info()" class="help" title="info">							
								<b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
							</a>
							<br/>
								<input type="button" id="but_search" name="but_search" value="Cari" onClick="cari()"  />
								<input type="button" id="but_search" name="but_search" value="Kosongkan" onClick="kosong()"  />
					</td>
				</tr>
				<tr id="tr_carianFail">
					<td>
						<div id="div_carianFail" ></div>
					</td>
				</tr>

			</table>-->
		</td>
	</tr>
</table>

</td>
</tr>
</table>
    
    
    
    </td>
  </tr>
  <tr>
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
<tr>

<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img  src="../img/main.png" width="30" height="30" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Tugasan</b>
</td>
</tr>
<!--
<tr>
<td>
bagi modul yang ada tugas spesifik untuk penguna yang login
<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
							<font color="blue"><li>Inbox</li></font>						
						</a>
</td>
</tr>
-->
<tr>
<td><a href="javascript:userPermohonan()" class="help" title="Fail Tugasan">
#if ($checkNotifikasiPermohonan != 0)
<li><label style="background-color:blue"  align="center" valign="top" >
<b><font color="WHITE"><blink>$checkNotifikasiPermohonan</blink></font></b></label> 
#end
<font color="blue">Permohonan ID Pengguna</font></li></a>
</td>
</tr>

<tr>
<td>
<!--<a href="javascript:userPermohonan()" class="help" title="Tambah ID Pengguna">
							<font color="blue"><li>&nbsp;Permohonan ID Pengguna</li></font>						
						</a>-->
</td>
</tr>


<!--<tr>
<td>
<a href="javascript:cetakSlipPendengaran('','')" class="help" title="Cetak Slip Pendengaran">
							<font color="blue"><li>&nbsp;Cetak Slip Pendengaran</li></font>						
						</a>
</td>
</tr>

<tr>
<td id="showCountOT" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountOT','showNotifikasi_OT','');					
});
</script>
</td>
</tr>

<tr>
<td id="showCountBU" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountBU','showNotifikasi_BU','');					
});
</script>
</td>
</tr>

<tr>
<td id="showCountMT" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountMT','showCountMT','');					
});
</script>
</td>
</tr>

<tr>
<td>


<a href="javascript:gotoMahkamahTinggiPermohonan()" class="help" title="Permohonan Mahkamah Tinggi">                     
                           <font color="blue"> <li>&nbsp;Permohonan Mahkamah Tinggi</li></font>						
				  </a>
</td>
</tr>


<tr>
<td id="showCountFailLengkap" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountFailLengkap','showCountFailLengkap','');					
});
</script>
</td>
</tr>-->

</table>
</td>
</tr>
</table>

</td>

<td width="50%" valign="top">


<!--<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/applyonline.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Capaian Pantas Permohonan <i>Online</i></b>
</td>
</tr>

<tr>
<td id="showNotifikasi_OnlineS8">

<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showNotifikasi_OnlineS8','showNotifikasi_OnlineS8','');					
});
</script>

</td>
</tr>

<tr>
<td id="showNotifikasi_OnlineS17">
	<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showNotifikasi_OnlineS17','showNotifikasi_OnlineS17','');					
});
</script>
</td>
</tr>

</table>
</td>
</tr>
</table>-->


</td>

</tr>
</table>
    
    </td>
  </tr>
 
  <tr>
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
<tr>
<td width="50%" valign="top">


<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
<td width="85%">
<table >

<tr>
<td>
<b>PLA</b> (Pengurusan Log Aduan)
</td>
</tr>

<tr>
<td id="showNotifikasi_Aduan">



<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('showNotifikasi_Aduan','showNotifikasi_Aduan','');					
});
</script>

                      
                        
</td>
</tr>

<tr>
<td>
<a href="javascript:paparAduan()" class="help" title="MAKLUMBALAS LOG (STATUS ADUAN, KOMEN-KOMEN ADUAN, TINDAKAN)">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_index_maklumbalas_aduan.size() > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_index_maklumbalas_aduan.size()</blink></font></b>
                             </label>&nbsp;
                             #end
                           Maklumbalas Log (Status Aduan, Komen-Komen Aduan)</li></font>	
						</a>

</td>
</tr>
<tr>
<td>


<a href="javascript:paparAduan()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_index_maklumbalas_teknikal.size() > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_index_maklumbalas_teknikal.size()</blink></font></b>
                             </label>&nbsp;
                             #end
                           Maklumbalas Log Teknikal (Agihan Tugas, Komen-Komen Teknikal)</li></font>	
						</a>



                      
                        
</td>
</tr>
<tr>
<td>
<a href="javascript:gotoFLMSstat()" class="help" title="Statistik Log FLMS">
							<font color="blue"><li>&nbsp;Statistik Log</li></font>						
						</a>
</td>
</tr>

</table>
</td>
</tr>
</table>
<br />
<br />
</td>
<td width="50%" valign="top">
</td>
</tr>
</table>
    
    </td>
  </tr>
</table>








</td>
<td width="39%" valign="top">
<table width="100%" border="0" >
<tr><td> 
<div id="dashboard_tab2"   ></div>
</td></tr>
</table>
<br />
<table width="100%" border="0" >
<tr><td> 
<div id="dashboard_tab"   >
</div>
</td></tr>
</table>

<br />

<table width="100%" border="0" >
<tr><td> 
<div id="dashboard_tab3"   >

<table width="100%" border="0" >

#if($list_memo_aktif.size()>0)
<tr>
<td>
<table width="100%" border="0" class="dashboard_bawah" >
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
#if($list_memo_aktif.size()>0)
<li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head">Pengumuman</li>
#end
</ul>
<div class="TabbedPanelsContentGroup">
#if($list_memo_aktif.size()>0)
<div class="TabbedPanelsContent"  id="Peringatan_Main" style="height:250" >  
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td valign="top">
#parse("app/pfd/frmPengumuman.jsp")
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
#end
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
#end
</table>
</div>
</td></tr>
</table>

<br />
<!-- <table width="100%" border="0" >
<tr><td> 
<div id="dashboard_tab4"   >

</div>
</td></tr>
</table> -->



</td>
<td width="1%" valign="top">

<div id="printStats" >

</td>
</tr>
</table>

<script>

$jquery(document).ready(function () {
	doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');		
	doDivAjaxCall$formname('dashboard_tab2','getTabStatData','');	
});

</script>

<script>

function printHideDiv(divName) {

    $jquery("#"+divName+" :button").hide();    
    //alert("1");
    var elementHide =  document.getElementById(divName);
    if (typeof(elementHide) != 'undefined' && elementHide != null)
    {
    	//alert("2");
    	elementHide.style.display = "";
    }    
    var printContents = document.getElementById(divName).innerHTML;   
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">'+'<div class="page-break" >'+ printContents + '</div>'+'</html>');
    popupWin.document.close(); 
    //document.body.innerHTML = originalContents;

    elementHide.style.display = "none";
    $jquery("#"+divName+" :button").show();
    return false;
    
    
    
}

function doCetak(statistik) {

	if(statistik=="LogMasuk"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=1');	
	}else if(statistik=="logJumlahDaftar"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=2');	
	}else if(statistik=="listLogPengguna"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=3');	
	}else if(statistik=="aduanBulan"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=4');	
	}else if(statistik=="aduanModul"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=5');	
	}else if(statistik=="aduanPejabat"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=6');	
	}else if(statistik=="aduanPrint"){
    	doDivAjaxCall$formname('printStats','getTabStatDataPrint','printId=7');	
	}
	
 
}


</script>




