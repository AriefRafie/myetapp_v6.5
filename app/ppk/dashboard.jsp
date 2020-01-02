
    
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
<a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan">
							<font color="blue"><li>&nbsp;Tukar Kata Laluan</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
#set ( $tab = $EkptgUtil.getTabID("'My Info'",$portal_role) )
<a href="$tab?_portal_module=lebah.pm.module.ActivitiesModule" class="help" title="MyCalendar">
							<font color="blue"><li><i>&nbsp;My Calendar</i></li></font>						
						</a>
                  
</td>
</tr>

<tr>
<td id="showNotifikasi_Inbox">
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showNotifikasi_Inbox','showNotifikasi_Inbox','');					
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
	<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/cari_icon.png" align="center"/></td>
		<td width="85%">
			<table>
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
			</table>
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
<td>
<a href="javascript:myinfo_skrin_carian()" class="help" title="Fail Tugasan">
							<font color="blue"><li>&nbsp;Carian</li></font>						
						</a>
</td>
</tr>


<tr>
<td>
<a href="javascript:cetakSlipPendengaran('','')" class="help" title="Cetak Slip Pendengaran">
							<font color="blue"><li>&nbsp;Cetak Slip Pendengaran</li></font>						
						</a>
</td>
</tr>
<!-- 
<tr>
<td id="showCountOT" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountOT','showNotifikasi_OT','');					
});
</script>
</td>
</tr>
 -->
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
</tr>

<tr>
<td id="showCountNotisPerbicaraan" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountNotisPerbicaraan','showCountNotisPerbicaraan','');					
});
</script>
</td>
</tr>

<!--IL -->
<tr id="">
	<td>
    <!--
		<font color="blue"><li>
        <a href="javascript:doGetListFailBelumKemaskiniSek8()" class="help" title="Senarai Fail Belum Kemaskini">
             <font color="blue">Laporan Penghantar Notis (5 hari sebelum tarikh bicara)</font>
           </a>
         </li></font>						
		<div  id="div_listFailBelumKemaskiniSek8"  style="width:40"></div>
        -->
        <a href="javascript:doGetListFailBelumKemaskiniSek8()" class="help" title="Senarai Fail Belum Kemaskini">
        <table cellpadding="1" cellspacing="0"  width="100%" style="vertical-align:top"  >
        <tr><td valign="top"  width="1%" style="white-space:nowrap;" >
        <font color="blue">
        <li >
       
        </li>
        </font>	
        </td>
        <td valign="top">  
        <font color="blue">                   
       Laporan Penghantar Notis (5 hari sebelum tarikh bicara)
        </font>
        </td>
        </tr>
        </table>
          </a>
        <div  id="div_listFailBelumKemaskiniSek8"  style="width:40"></div>
</td>
</tr>


<tr id="">
	<td>
    	<!--
		<font color="blue"><li> 
          <a href="javascript:doGetListFailTamatTempohKpi()" class="help" title="Senarai Fail Tamat Tempoh Kpi Sek 8">
             <font color="blue">Seksyen 8 - Senarai 20 hari sebelum tamat tempoh KPI</font>
           </a>
         </li></font>	
         -->					
		
        
         <a href="javascript:doGetListFailTamatTempohKpi()" class="help" title="Senarai Fail Tamat Tempoh Kpi Sek 8">
        <table cellpadding="1" cellspacing="0"  width="100%" style="vertical-align:top"  >
        <tr><td valign="top"  width="1%" style="white-space:nowrap;" >
        <font color="blue">
        <li >
       
        </li>
        </font>	
        </td>
        <td valign="top">  
        <font color="blue">                   
       Seksyen 8 - Senarai 20 hari sebelum tamat tempoh KPI
        </font>
        </td>
        </tr>
        </table>
          </a>
        <div  id="div_listFailTamatTempohKpi"  style="width:40"></div>
</td>
</tr>

<tr id="">
	<td>
    <!--
		<font color="blue"><li>
          <a href="javascript:doGetListFailTamatTempohKpiSek17()" class="help" title="Senarai Fail Tamat Tempoh Kpi Sek 17">
             <font color="blue">Seksyen 17 - Senarai 20 hari sebelum tamat tempoh KPI</font>
           </a>
         </li></font>						
		<div  id="div_listFailTamatTempohKpiSek17"  style="width:40"></div>
        -->
        
        <a href="javascript:doGetListFailTamatTempohKpiSek17()" class="help" title="Senarai Fail Tamat Tempoh Kpi Sek 17">
        <table cellpadding="1" cellspacing="0"  width="100%" style="vertical-align:top"  >
        <tr><td valign="top"  width="1%" style="white-space:nowrap;" >
        <font color="blue">
        <li >
       
        </li>
        </font>	
        </td>
        <td valign="top">  
        <font color="blue">                   
       Seksyen 17 - Senarai 20 hari sebelum tamat tempoh KPI
        </font>
        </td>
        </tr>
        </table>
          </a>
        <div  id="div_listFailTamatTempohKpiSek17"  style="width:40"></div>
</td>
</tr>

#set($trshowCountTukarPegawai = "")
#if(($id_jawatan_login != "4" && $id_jawatan_login != "5" && $portal_role == "adminppk") || ($portal_role != "adminppk"))
	#set($trshowCountTukarPegawai = "style='display:none'")
#end

<tr $trshowCountTukarPegawai >
<td id="showCountTukarPegawai" >
<a href="javascript:gotoTukarPegawai()" class="help" title="Permohonan Tukar Pegawai Bicara">
<font color="blue"><li>Ganti Pegawai Bicara</li></font>						
</a>
</td>
</tr>

#set($trshowCountBicaraOnline = "")
#if($portal_role != "adminppk")
	#set($trshowCountBicaraOnline = "style='display:none'")
#end

<tr $trshowCountBicaraOnline >
<td id="showCountBicaraOnline" >
<a href="javascript:gotoBicaraOnline()" class="help" title="Perbicaraan Interaktif">
<font color="blue"><li>Perbicaraan Interaktif</li></font>						
</a>
</td>
</tr>


<tr  >
<td id="showCountHelpDesk" >
<a href="javascript:gotoHelpDesk()" class="help" title="Help Desk">
<font color="blue"><li>Help Desk</li></font>						
</a>
</td>
</tr>




<!--end IL-->
</table>
</td>
</tr>
</table>

</td>

<td width="50%" valign="top">


<table width="100%" >
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
<td width="15%" align="center" valign="top"><img  width="30" height="30" src="../img/new_application.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Daftar Permohonan</b></td>
</tr>

<tr>
<td>
<a href="javascript:gotoAdd8()" class="help" title="Permohonan Baru Seksyen 8">
							<font color="blue"><li>&nbsp;Seksyen 8</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoAdd17()" class="help" title="Permohonan Baru Seksyen 17">
							<font color="blue"><li>&nbsp;Seksyen 17</li></font>						
						</a>
</td>
</tr>


<tr>
<td>
<a href="javascript:gotoAddLama()" class="help" title="Kemasukan Fail Lama">
							<font color="blue"><li>&nbsp;Kutipan Data</li></font>						
						</a>
</td>
</tr>

</table>
</td>
</tr>
</table>

</td>

<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/utiliti.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Utiliti</b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoKemaskini()" class="help" title="Kemaskini Fail">
							<font color="blue"><li>&nbsp;Kemaskini Fail</li></font>						
				  </a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoStatus()" class="help" title="Pertukaran Pada Status Fail">
							<font color="blue"><li>&nbsp;Pertukaran Status Fail</li></font>						
				  </a>
</td>
</tr>



<tr>
<td id="showKIV_stats">
<script>
        $jquery(document).ready(function () {
            doDivAjaxCall$formname('showKIV_stats','showKIV_stats','');	
                
        });
</script>   
</td>
</tr>



   


<tr>
<td id="showNotifikasi_Edit">
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showNotifikasi_Edit','showNotifikasi_Edit','');					
});
</script>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoCalculator()" class="help" title="Kalkulator">
							<font color="blue"><li>
                            Kalkulator</li></font>						
				  </a>
</td>
</tr>





</table>
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
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/transfer.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Bidang Kuasa Eksklusif</b>
</td>
</tr>

<tr>
<td id="showNotifikasi_Pindah">
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showNotifikasi_Pindah','showNotifikasi_Pindah','');					
});
</script>
</td>
</tr>


<tr>
<td>
<a href="javascript:gotoBKE()" class="help" title="Permindahan BKE">
							<font color="blue"><li>&nbsp;Permindahan BKE</li></font>						
				  </a>
</td>
</tr>

</table>
</td>
</tr>
</table>

</td>
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img src="../img/sms.png" width="30" height="30" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b><i>MySMS</i></b>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoSMS()" class="help" title="MySMS">
							<font color="blue"><li><i>&nbsp;MySMS</i></li></font>						
						</a>
</td>
</tr>

</table>
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
	doDivAjaxCall$formname('showNotifikasi_Aduan','showNotifikasi_Aduan','');					
});
</script>

                      
                        
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

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/printer.png"/></td>
<td width="85%">
<table width="100%" >

<tr>
<td>
<b>Cetakan Borang - Borang</b>
</tr>

<tr>
<td>




<a href="#" class="help"  onclick="Effect.toggle('table_borang', 'appear'); return false;"  title="Senarai Borang - Borang">
                            <font color="blue">
                           <li>Senarai Borang</li>
                           </font>	
						</a>



<table width="100%"  id="table_borang"  style="display:none;"  border="0">
	<tr>
    	<td width="5%">&nbsp;</td>
		<td width="5%" class="$row">1.</td>
		<td width="55%" class="$row">
    		<a href="#" onclick="doCetak('BorangA')" class="help" >
				<font color="blue">Borang A</font>						
			</a>
    	</td>
		<td width="35%" class="$row" align="left"></td>
	</tr>
	<tr>
    	<td width="5%">&nbsp;</td>
		<td width="5%" class="$row">2.</td>
		<td width="55%" class="$row">
    		<a href="#" onclick="doCetak('BorangAA')" class="help" >
				<font color="blue">Borang AA</font>						
			</a>
    	</td>
		<td width="35%" class="$row" align="left"></td>
	</tr>
	<tr>
    <td></td>
	<td class="$row">3.</td>
	<td class="$row">
      <a href="#" onclick="doCetak('BorangPersetujuan')" class="help" >
							<font color="blue">Borang DDA</font>						
	</a>
    </td>
	<td class="$row" align="left">
	
	</td>
	</tr>
	<tr>
    <td></td>
	<td class="$row">4.</td>
	<td class="$row">
    <a href="#" onclick="doCetak('BorangK1')" class="help" >
							<font color="blue">Borang K1</font>						
	</a>
    </td>
	<td class="$row" align="left">
	
	</td>
	</tr>
	<tr>
    <td></td>
	<td class="$row">5.</td>
	<td class="$row">
      <a href="#" onclick="doCetak('BorangK3')" class="help" >
							<font color="blue">Borang K3</font>						
	</a>
   </td>
	<td class="$row" align="left">
	
	</td>
	</tr>
	<tr>
    <td></td>
	<td class="$row">6.</td>
	<td class="$row" align="left">
     <a href="#" onclick="doCetak('BorangP')" class="help" >
							<font color="blue">Borang P</font>						
	</a>
    </td>
	<td class="$row">
	
	</td>
	</tr>
	
</table>

                      
                        
</td>

</table>
</td>
</tr>
</table>

</td>
</tr>
</table>
    
    </td>
  </tr>
</table>



<input type="hidden" id="idfail" name="idfail" value="">
<input type="hidden" id="idpermohonan" name="idpermohonan" value="">
<input type="hidden" id="ID_FAIL" name="ID_FAIL" value="">
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="">
<input type="hidden" id="idpermohonansimati" name="idpermohonansimati" value="">
<input type="hidden" id="idPermohonanSimati" name="idPermohonanSimati" value="">
<input type="hidden" id="ID_KEPUTUSANPERMOHONAN" name="ID_KEPUTUSANPERMOHONAN" value="">
<input type="hidden" id="idStatus" name="idStatus" value="">
<input type="hidden" id="idPermohonan" name="idPermohonan" value="">
<input type="hidden" id="id_permohonan" name="id_permohonan" value="">
<input type="hidden" id="id_status" name="id_status" value="">
<input type="hidden" id="flagFromSenaraiPermohonanSek8" name="flagFromSenaraiPermohonanSek8" value="">
<input type="hidden" id="idsimati" name="idsimati" value="">
<input type="hidden" id="id_Simati" name="id_Simati" value="">
<input type="hidden" id="idSimati" name="idSimati" value="">




</td>
<td width="39%" valign="top">
<table width="100%" border="0" >
<tr>
<td width="100%">


<table cellpadding="2" cellspacing="1" border="0" width="100%" class=" dashboard_tepi" align="left">
<tr>
<td width="100%" valign="top" id="show_mainstats">


<script>
$jquery(document).ready(function () {
	doDivAjaxCall3$formname('show_mainstats','show_mainstats','');					
});
</script>


</td>
</tr>
</table>




</td>
</tr>

<tr>
<td>

<div id="dashboard_tab" ></div>


</td>
</tr>
</table>



</td>
<td width="1%" valign="top">



</td>
</tr>
</table>

<script>

<!-- Carian Terperinci -->

function runScript(e) {
	 var key;     
   if(window.event)
        key = window.event.keyCode; //IE
   else
        key = e.which; //firefox     

   return (key != 13);
}	

function open_info() {
	var width  = 550;
	var height = 300;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;

	var params = 'width='+width+', height='+height;
	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=front';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	new_window = open("","title",params);
	new_window.document.open();

	new_window.document.write("<html><title>Info Maklumat Carian</title>");
	new_window.document.write("<body bgcolor=\"#FFFFFF\">");
	new_window.document.write("<table><tr><td><b><u>Jenis-Jenis Carian</u></b></td></tr></table>");
	
	new_window.document.write("<table width='100%'><tr><td width='50%' valign='top'>");
	
	new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
	new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail Permohonan </li></font>");
	
	new_window.document.write("</td><td width='50%' valign='top'>");
	
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan bilangan fail berdasarkan maklumat carian yang dimasukkan. Jika bilangan menunjukkan lebih daripada '0', pengguna boleh klik pada <i>link</i> tersebut bagi memaparkan senarai fail yang berkenaan.</td></tr></table>");
	new_window.document.write("</body></html>");
	new_window.document.close();
}

function cari(){
	if(document.getElementById('search').value==""){
		alert("Sila Masukkan Maklumat Carian");
		document.getElementById('search').focus();
	}
	else{	
		document.getElementById('tr_carianFail').style.display="";
		doDivAjaxCall$formname('div_carianFail','doCarianFail','');
	}
}

function kosong(){	
	document.getElementById('search').value="";
	//reset_jquery('_');	
	//reset_jqueryAgihan('_');
	document.getElementById('tr_carianFail').style.display="none";
	doDivAjaxCall$formname('div_carianFail','doCloseCarianFail','');
}

function doGetListFail(){
	reset_jqueryCarian('div_listFail');	
	document.getElementById('div_listFail').style.display="";		
	doDivAjaxCall$formname('div_listFail','doGetListFail','');
}

function reset_jqueryCarian(current_div){
	if(current_div!="div_listFail")
	{
		
		if (document.getElementById('div_listFail') != null && document.getElementById('div_listFail') != undefined) 
		{
			
		document.getElementById('div_listFail').style.display="none";	
		
		doDivAjaxCall$formname('div_listFail','doCloseListFail','');	
		}
	}
	
}

</script>



<script>

function open_new_window(div_memo) 
{

 var width  = 450;
 var height = 300;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 
 myDiv_label = document.getElementById(div_memo);
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();

new_window.document.write("<html><title>Paparan Selanjutnya</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write(myDiv_label.innerHTML);
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}

</script>



<script>
//IL
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	
	//displayStatistik();
});


function displayStatistik() {
	var url = '../div/ekptg.view.ppk.FrmDashboard?$queryString';
    
    //Borang C Mahkamah Tinggi
    var target = 'TotalMT';
    var actionName = 'getTotalMT';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);

    //Laporan Penghantar Notis (5 hari sebelum tarikh bicara)
    var target = 'count_fail_kemaskini';
    var actionName = 'getCountFailKemaskini';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);

 	//Seksyen 8 - Senarai 20 hari sebelum tamat tempoh KPI
    var target = 'count_fail_tempoh_kpi';
    var actionName = 'getCountFailTempohKpi';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Seksyen 17 - Senarai 20 hari sebelum tamat tempoh KPI
    var target = 'count_fail_tempoh_kpi_sek17';
 	var actionName = 'getCountFailTempohKpiSek17';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Online-Seksyen 8
    var target = 'check_notifikasi_online8';
 	var actionName = 'getCheckNotifikasiOnline8';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Online-Seksyen 17
    var target = 'check_notifikasi_online17';
 	var actionName = 'getCheckNotifikasiOnline17';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Kebenaran Kemaskini Fail
    var target = 'check_notifikasi_edit';
 	var actionName = 'getCheckNotifikasiEdit';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Penerimaan Fail Pindah
    var target = 'check_notifikasi_pindah';
 	var actionName = 'getCheckNotifikasiPindah';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Ruang Perbincangan Formal
    var target = 'check_notifikasi_inbox';
 	var actionName = 'getCheckNotifikasiInbox';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Log Aduan
    var target = 'check_notifikasi_aduan';
 	var actionName = 'getCheckNotifikasiAduan';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Keseluruhan Fail
    var target = 'jumlah_keseluruhan';
 	var actionName = 'getJumlahKeseluruhan';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Fail Seksyen 8
    var target = 'fail_sek8';
 	var actionName = 'getFailSek8';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
 	
	// Fail Seksyen 17
    var target = 'fail_sek17';
 	var actionName = 'getFailSek17';
 	doAjaxUpdater(document.Fekptg_view_ppk_FrmDashboard, url, target, actionName);
}



function doCetak(namaborang) {
	// /mnt/appshared/PPK/borang
	var pathBorang = "/mnt/appshared/PPK/borang/";
	var url = "../download?file="+pathBorang;
	if(namaborang=="BorangA"){
    	//var url = "../reports/ppk/BORANG A0.pdf";
    	url += "BORANG_A0.pdf";
	}else if(namaborang=="BorangAA"){
    	url += "BORANG_AA_3A_BM.pdf";
	}else if(namaborang=="BorangP"){
    	//var url = "../reports/ppk/BORANG P0.pdf";
    	url += "BORANG_P0.pdf";
	}else if(namaborang=="BorangK1"){
    	//var url = "../reports/ppk/BORANG K10.pdf";
    	url += "BORANG_K10.pdf";
	}else if(namaborang=="BorangK2"){
    	//var url = "../reports/ppk/BORANG K20.pdf";
    	url += "BORANG_K20.pdf";
	}else if(namaborang=="BorangK3"){
    	var url = "../reports/ppk/BORANG K3.pdf";
	}else if(namaborang=="BorangPersetujuan"){
    	//var url = "../reports/ppk/BORANG PERSETUJUAN.pdf";
    	url += "BORANG_PERSETUJUAN.pdf";
	}
	//alert(url);
    var hWnd = window.open(url,'Cetak','width=700,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function cetakSlipPendengaran(idfail,NO_FAIL) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idfail="+idfail+"&report=SlipPendengaran";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function paparBorangB(idPermohonan,idSimati,seksyen)
{

	if (seksyen == '8')
	{
	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";	
	
	}
	else
	{
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
	}
	
	document.${formName}.idpermohonan.value = idPermohonan;
	document.${formName}.idSimati.value = idSimati;
	document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
	
	document.${formName}.method="POST";
	
	document.${formName}.submit();
	
}


function dash() {	
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
	document.${formName}.submit();
}

function gotoSenaraiBorangB() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiBorangB";
	document.${formName}.submit();
}

function gotoSenaraiKiv() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiKiv";
	document.${formName}.submit();
}

function gotoPindah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah";
	document.${formName}.submit();
}

function gotoStatus() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarStatus";
	document.${formName}.submit();
}

function gotoEdit() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	document.${formName}.submit();
}

//IL
function gotoCalculator() {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmCalculator";
	var hWnd = window.open(url,'Cetak','width=500,height=410, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

<!-- Salnizam edit start -->
function gotoKIV() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiKiv";
	document.${formName}.submit();
}

function gotofailLengkap() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.SenaraiFailLengkap";
	document.${formName}.submit();
}
<!-- Salnizam edit end -->

function gotoKemaskini() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView";
	document.${formName}.submit();
}

function gotoBKE() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}

function gotoAdd8() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8SenaraiSemakInternal";
	document.${formName}.submit();
}

function gotoAdd17() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Internal";
	document.${formName}.submit();
}

function gotoAddLama() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8SenaraiSemakInternalKutipan";
	document.${formName}.submit();
}

function gotoOT() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.admin.PenetapanOT";
	document.${formName}.submit();
}

function gotoBU() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.PermohonanBantuUnit";
	document.${formName}.submit();
}

function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function gotoFLMSstat() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
	document.${formName}.submit();
}


function gotoProfile() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.admin.UserProfileInternal";
	document.${formName}.submit();
}

function gotoCalendar() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
	document.${formName}.submit();
}


function gotoInbox() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
	document.${formName}.submit();
}

function gotoSMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.sms.mySMS";
	document.${formName}.submit();
}

function myinfo_skrin_carian() {
    document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8";
	document.${formName}.submit();
}


function gotoOnline8() {

	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Online";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoTempohKpiSek8() {

	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function gotoMahkamahTinggi() {

	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmIntegrasiMTList";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoTukarPegawai() {

	document.${formName}.action = "$EkptgUtil.getTabID("Bicara Interaktif",$portal_role)?_portal_module=ekptg.view.ppk.BicaraInteraktif&fromDashboard=Y";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoBicaraOnline() {

	document.${formName}.action = "$EkptgUtil.getTabID("Bicara Interaktif",$portal_role)?_portal_module=ekptg.view.ppk.BicaraInteraktif";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoHelpDesk() {

	document.${formName}.action = "$EkptgUtil.getTabID("Help Desk",$portal_role)?_portal_module=ekptg.view.online.FrmAduanPublic";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoMahkamahTinggiPermohonan() {

	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmIntegrasiMTPermohonan";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoOnline17() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek8Online17";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

/* aishahlatip 12052017 */

function gotofailNotisP() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.CarianNotisPerbicaraan";
	document.${formName}.submit();
}





</script>

<!-- IL start  -->

<script>

function papar(idPermohonan,idStatus,idFail,idSimati,idpermohonansimati,tarikhMohon,jenisfail,seksyen) {

	//perintah - peje
	/*
	if (idStatus == '25'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idPermohonanSimati.value = idpermohonansimati;
			document.${formName}.idStatus.value = idStatus;
	} else 
	*/
		
	if (idStatus == '41'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar&idPermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar&idPermohonan="+idPermohonan;
		}
	
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idPermohonanSimati.value = idpermohonansimati;
		document.${formName}.idStatus.value = idStatus;
	
	}	
 	else 
		//shaz
		if (idStatus == '53' || idStatus == '151' ){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0&id_permohonan="+idPermohonan;
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0&id_permohonan="+idPermohonan;
			}
			
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
		
		} else  if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' ){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&id_permohonan="+idPermohonan;
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&id_permohonan="+idPermohonan;
			}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
		} 
/*	else 
	if (idStatus == '18'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
} */
	else 
	if (idStatus == '21' || idStatus == '25'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan&id_permohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan&id_permohonan="+idPermohonan;
		}			
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
} else 
	if (idStatus == '64'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=maklumatRayuan&tabId=0&id_permohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=maklumatRayuan&tabId=0&id_permohonan="+idPermohonan;
		}			
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
} else 
	if (idStatus == '163' || idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan&id_permohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan&id_permohonan="+idPermohonan;
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
} else 
//elly

	if (idStatus == '18'){
		
		
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_notis&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_notis&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
} else 
	if (idStatus == '47'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
}
else 
	if (idStatus == '172'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

} else 
	if (idStatus == '174'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
}else 
	if (idStatus == '176'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

}/*
else 
	if (idStatus == '56'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah&command=paparTunggu";
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_fail.value = idFail;
//} else 
//	if (idStatus == '169'){
//		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah&command=paparBatal";
//		document.${formName}.id_permohonan.value = idPermohonan;
//		document.${formName}.id_fail.value = idFail;
} */

else 
	if (idStatus == '56')
	{
	if (seksyen == '8'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar&idpermohonan="+idPermohonan;
		}
		
		if (seksyen == '17'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar17&idpermohonan="+idPermohonan;
		}
		
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idPermohonan.value = idPermohonan;
	}

else 
	if ((idStatus == '8' || idStatus == '9' || idStatus == '170' || idStatus == '169') && (jenisfail == '1' || jenisfail == '2' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar&idpermohonan="+idPermohonan;
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.idSimati.value = idSimati;
} 
 else 
	if ((idStatus == '8' || idStatus == '9' || idStatus == '170') && jenisfail == '3'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar&idpermohonan="+idPermohonan;
		document.${formName}.idpermohonan.value = idPermohonan;
} 
 else 
//man		
	if (idStatus == '14' || idStatus == '50' || idStatus == '152'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan&idpermohonan="+idPermohonan;
		}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idpermohonansimati;
			document.${formName}.idSimati.value = idSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
	
} /* else 
	
	if (idStatus == ''){
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idpermohonansimati.value = idpermohonansimati;
	document.${formName}.tarikh_mohon.value = tarikhMohon;
	
}*/  else 
//kak ayu		

if (idStatus == '153'){
	if (seksyen == '8'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&hitButt=papar&idpermohonan="+idPermohonan;
	}else{
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanSek17BKE&hitButt=papar&idpermohonan="+idPermohonan;
	}
	document.${formName}.idpermohonan.value = idPermohonan;
	document.${formName}.idsimati.value = idSimati;

	// Kemaskini oleh Mohamad Rosli pada 10/03/2011, tambah kondisi untuk status BATAL LAIN - LAIN KES (id_status=70)	
	} else if(idStatus == '70'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan&idpermohonan="+idPermohonan;
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan&idpermohonan="+idPermohonan;
		}
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idpermohonansimati.value = idpermohonansimati;
		document.${formName}.idSimati.value = idSimati;
		document.${formName}.tarikh_mohon.value = tarikhMohon;

	}else {
		alert("Sila Hubungi Sistem Admin")

	}

	document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();

}

</script>

<!-- Laporan Penghantar Notis -->
<script>


function doGetListFailBelumKemaskiniSek8(){
	reset_jqueryCarian('div_listFailBelumKemaskiniSek8');	
	document.getElementById('div_listFailBelumKemaskiniSek8').style.display="";		
	doDivAjaxCall$formname('div_listFailBelumKemaskiniSek8','doGetListFailBelumKemaskiniSek8','');
}

function reset_jqueryCarian(current_div){
	if(current_div!="div_listFailBelumKemaskiniSek8")
	{
		
		if (document.getElementById('div_listFailBelumKemaskiniSek8') != null && document.getElementById('div_listFailBelumKemaskiniSek8') != undefined) 
		{
			
		document.getElementById('div_listFailBelumKemaskiniSek8').style.display="none";	
		
		doDivAjaxCall$formname('div_listFailBelumKemaskiniSek8','doCloseListFailBelumKemaskiniSek8','');	
		}
	}
	
}

function papar1(ID_FAIL,ID_URUSAN,ID_SUBURUSAN,ID_STATUS,ID_PERMOHONAN,ID_KEPUTUSANPERMOHONAN,TARIKH_BICARA,NO_FAIL) {
	
	if (ID_SUBURUSAN == '59'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
	}
	
	
	else if (ID_SUBURUSAN == '60'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
		
	}
	
	document.${formName}.method="POST";
	document.${formName}.submit();
	
		
}
</script>


<!-- Seksyen 8 Tempoh Kpi -->
<script>

function doGetListFailTamatTempohKpi(){
	reset_jqueryCarian('div_listFailTamatTempohKpi');	
	document.getElementById('div_listFailTamatTempohKpi').style.display="";		
	doDivAjaxCall$formname('div_listFailTamatTempohKpi','doGetListFailTamatTempohKpi','');
}


function reset_jqueryCarian(current_div){
	if(current_div!="div_listFailTamatTempohKpi")
	{
		
		if (document.getElementById('div_listFailTamatTempohKpi') != null && document.getElementById('div_listFailTamatTempohKpi') != undefined) 
		{
			
		document.getElementById('div_listFailTamatTempohKpi').style.display="none";	
		
		doDivAjaxCall$formname('div_listFailTamatTempohKpi','doCloseListFailTamatTempohKpi','');	
		}
	}
	
}


 function papar2(ID_FAIL,ID_URUSAN,ID_SUBURUSAN,ID_STATUS,ID_PERMOHONAN,ID_KEPUTUSANPERMOHONAN,TARIKH_BICARA,NO_FAIL) {
	
	if (ID_SUBURUSAN == '59'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
	}
	
	
	else if (ID_SUBURUSAN == '60'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
		
	}
	
	document.${formName}.method="POST";
	document.${formName}.submit();
	
		
} 




</script>


<!-- Seksyen 17 Tempoh Kpi -->
<script>

function doGetListFailTamatTempohKpiSek17(){
	reset_jqueryCarian('div_listFailTamatTempohKpiSek17');	
	document.getElementById('div_listFailTamatTempohKpiSek17').style.display="";		
	doDivAjaxCall$formname('div_listFailTamatTempohKpiSek17','doGetListFailTamatTempohKpiSek17','');
}


function reset_jqueryCarian(current_div){
	if(current_div!="div_listFailTamatTempohKpiSek17")
	{
		
		if (document.getElementById('div_listFailTamatTempohKpiSek17') != null && document.getElementById('div_listFailTamatTempohKpiSek17') != undefined) 
		{
			
		document.getElementById('div_listFailTamatTempohKpiSek17').style.display="none";	
		
		doDivAjaxCall$formname('div_listFailTamatTempohKpiSek17','doCloseListFailTamatTempohKpiSek17','');	
		}
	}
	
}


/* function papar3(ID_FAIL,ID_URUSAN,ID_SUBURUSAN,ID_STATUS,ID_PERMOHONAN,ID_KEPUTUSANPERMOHONAN,TARIKH_BICARA,NO_FAIL) {
	
	if (ID_SUBURUSAN == '59'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
	}
	
	
	else if (ID_SUBURUSAN == '60'){	
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
		document.${formName}.ID_FAIL.value = ID_FAIL;
		document.${formName}.ID_PERMOHONAN.value = ID_PERMOHONAN;
		document.${formName}.ID_KEPUTUSANPERMOHONAN.value = ID_KEPUTUSANPERMOHONAN;
		
	}
	
	document.${formName}.method="POST";
	document.${formName}.submit();
	
		
} */

//end IL

</script>



   
    <script type="text/javascript" charset="utf-8">
		
		$$("a.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#0000", borderColor: "#0000", textColor: "black",opacity:"100"});
		});
		$$("form input.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#FC9", borderColor: "#C96", textColor: "#000", textShadowColor: "#FFF"});
		});
		
	</script>
   
    
   
