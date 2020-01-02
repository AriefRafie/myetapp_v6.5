<style type="text/css">



table.dashboard_sub{
border-top:none;
border-left:none;
border-right:none;
border-bottom:1px  dotted #4C2F4D;
}


table.dashboard_tepi{
/*border:1px solid 4C2F4D;*/
border-top:1px solid #4C2F4D;
border-left:1px solid #4C2F4D;
border-right:1px solid #4C2F4D;
border-bottom:1px solid #4C2F4D;
}

td.dashboard_sub_kiri{
border-top:none;
border-left:1px  dotted #4C2F4D;
border-right:none;
border-bottom:none;
}

table.dashboard_kiri{
border-top:none;
border-left:none;
border-right:none;
border-bottom:none;
}


table.dashboard_bawah{
/*border:1px solid 4C2F4D;*/
border-top:1px solid #4C2F4D;
border-left:1px solid #4C2F4D;
border-right:1px solid #4C2F4D;
border-bottom:1px solid #4C2F4D;
}


table.dashboard_tepi_bawah{
border-top:none;
border-left:1px solid #4C2F4D;
border-right:none;
border-bottom:none;
}


table.nav{
border-top:3px solid #4C2F4D;
border-left:none;
border-right:none;
border-bottom:3px solid #4C2F4D;
}

.tooltip {
	position: absolute!important;
	overflow:hidden;
	font-size: 12px;
	z-index: 10000!important;
}
	.tooltip .xtop, .tooltip .xbottom { background: transparent; font-size: 0px; }
	.tooltip .xb1, .tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { display: block; overflow: hidden; }
	.tooltip .xb1, .tooltip .xb2, .tooltip .xb3 { height: 0px; }
	.tooltip .xb2, .tooltip .xb3, .tooltip .xb4 { background: #666;}
	.tooltip .xbottom .xb2, .tooltip .xbottom .xb3, .tooltip .xbottom .xb4 { background: #666; }
	.tooltip .xb1 { margin: 0 0px; }
	.tooltip .xb2 { margin: 0 0px; }
	.tooltip .xb3 { margin: 0 0px; }
	.tooltip .xb4 { height: 0px;}

	.tooltip .xboxcontent {
		padding: 0 .5em;
		margin: 0;
		color: #000;
		word-wrap:break-word;
		border: none;
		background-color:white;
		font-family: Verdana;
	}
	
	



a.nav:link{
	link:#333333;
}
a.nav:visited {color: #333333; text-decoration: none; }	




#marquee_replacement {
    border: 3px solid #4C2F4D;
    height: 300px;
    margin-bottom: 10px;
    overflow: auto;
    width: 100%;
}




</style>

    
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>
<!--
<table width="100%" cellspacing="0">
	<tr>
		<td width="1%">
		
		</td>
		<td><blink ><FONT color="RED" size="+1"><b>SILA ABAIKAN SKRIN INI, SKRIN INI DIDALAM PROSES PEMBANGUNAN DAN PENGUJIAN, TERIMA KASIH!</b></FONT></blink></td>
	</tr>
	
</table>
<!-- class="dashboard_sub_kiri" -->


<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
<tr>
<td width="1%" valign="top"   >

</td>
<td width="59%" valign="top"  >


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
<a href="javascript:gotoCalendar()" class="help" title="MyCalendar">
							<font color="blue"><li><i>&nbsp;My Calendar</i></li></font>						
						</a>
</td>
</tr>

<!-- <tr>
<td>
<a href="javascript:gotoInbox()" class="help" title="My Inbox">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_inbox > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_inbox</blink></font></b>
                             </label>&nbsp;
                             #end
                            Ruang Perbincangan Formal</li></font>	
						</a>
</td>
</tr> -->


</table>
</td>
</tr>
</table>
</td>
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top">
<!-- <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/soalan.png" align="center"/></td> -->
<td width="85%">
<table>

<tr>
<td>&nbsp;
<!-- <b>Pertanyaan</b>
</td>
</tr>

<tr>
<td>
<a href="#" onclick="window.open('http://www.etapp.gov.my/eTappv2/', 'sss', '');" class="help" >
							<font color="blue">http://www.etapp.gov.my/eTappv2/</font>						
				  </a> -->

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
							<font color="blue"><li>&nbsp;Carian Fail</li></font>						
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
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/applyonline.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Capaian Pantas Permohonan <i>Online</i></b>
</td>
</tr>

<tr>
<td>


<a href="javascript:gotoOnline8()" class="help" title="Senarai Penerimaan Permohonan Online Seksyen 4">
							<font color="blue"><li>
                            #if($!check_notifikasi_online8 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_online8</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Seksyen 4</li></font>						
				  </a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoOnline17()" class="help" title="Senarai Penerimaan Permohonan Online Seksyen 8">
							<font color="blue"><li>
                            
                             #if($!check_notifikasi_online17 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_online17</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Seksyen 8</li></font>						
				  </a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoOnline17()" class="help" title="Senarai Penerimaan Permohonan Online Pengambilan Sementara">
							<font color="blue"><li>
                            
                             #if($!check_notifikasi_online17 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_online17</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Pengambilan Sementara</li></font>						
				  </a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoOnline17()" class="help" title="Senarai Penerimaan Permohonan Online Pembatalan / Penarikan Balik">
							<font color="blue"><li>
                            
                             #if($!check_notifikasi_online17 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_online17</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Pembatalan / Penarikan Balik</li></font>						
				  </a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoOnline17()" class="help" title="Senarai Penerimaan Permohonan Online Bantahan">
							<font color="blue"><li>
                            
                             #if($!check_notifikasi_online17 > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_online17</blink></font></b>
                             </label>&nbsp;
                             #end
                            
                            Bantahan</li></font>						
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
<td width="15%" align="center" valign="top"><img  width="30" height="30" src="../img/new_application.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Daftar Permohonan</b></td>
</tr>

<tr>
<td>
<a href="javascript:gotoAdd4()" class="help" title="Permohonan Baru Seksyen 4">
							<font color="blue"><li>&nbsp;Seksyen 4</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoAdd8()" class="help" title="Permohonan Baru Seksyen 8">
							<font color="blue"><li>&nbsp;Seksyen 8</li></font>						
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


</table>
</td>
</tr>
</table>

</td>
</tr>
</table>
    
    </td>
  </tr>
  
<!-- <tr>
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
<b>PLA</b> (Pengurusan Log Aduan)
</td>
</tr>

<tr>
<td>


<a href="javascript:gotoFLMS()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_aduan > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!check_notifikasi_aduan</blink></font></b>
                             </label>&nbsp;
                             #end
                           Log Aduan</li></font>	
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
  </tr> -->  
  
  
</table>










<td width="39%" valign="top">
<table width="100%" border="0" >
<tr>
<td width="100%">

 <table cellpadding="2" cellspacing="1" border="0" width="100%" class=" dashboard_tepi" align="left">
<tr>
<td width="100%" valign="top">

<table width="100%"  id="table_stat">
<tr>
<td width="15%" align="center" valign="top">



</td>
<td width="85%" >
<table width="100%">

<tr>
<td   colspan="3">


<b>Statistik Fail di Pangkalan Data <br /><font color="blue">$!negeri_sever</font></b></td>
</tr>

<tr>
<td width="50%" valign="top">
<font color="blue"><li>Keseluruhan Fail</li></font></td>
<td width="1%" valign="top">
:</td>
<td width="49%" valign="top">
<b>4</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Fail Seksyen 4</li></font></td>
<td  valign="top">
:</td>
<td  valign="top">
<b>0</b></td>
</tr>

<tr>
<td valign="top">
<font color="blue"><li>Fail Seksyen 8</li></font></td>
<td valign="top">
:</td>
<td  valign="top">
<b>4</b></td>
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
<td>

 <table width="100%" border="0" class="dashboard_bawah" >
<tr>
<td>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
   
    #if($list_memo_aktif.size()>0)
    <li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head" >Pengumuman</li>
    #end
    #if($getListCountBorangB>0)
      <li class="TabbedPanelsTab" tabindex="0" id="Peringatan_Head"  ><font color="red"><blink>Peringatan!</blink></font>
      </li>
     #end 
   <li class="TabbedPanelsTab" tabindex="0" style="display:none" id="Carta_Head"  >Carta</li>
  </ul>
     <div class="TabbedPanelsContentGroup">
   
   
#if($list_memo_aktif.size()>0)
<div class="TabbedPanelsContent"  id="Peringatan_Main">  
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
<td width="88%" valign="top">
<table width="100%">


<tr>
<td   valign="top" >


#parse("app/ppk/frmPengumuman.jsp")

</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
#end
 #if($getListCountBorangB>0)  
   <div class="TabbedPanelsContent" id="Peringatan_Main">  
   <table width="100%" border="0">
   <tr>
   <td width="5%">
   
   </td>
   
   <td width="95%">
   <input type="hidden" name="idpermohonan" />
   <input type="hidden" name="idSimati" />
   <input type="hidden" name="flagFromSenaraiPermohonanSek8"/>
   <div style="height:225"> <li>  
   Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak.   
   Jumlah kemasukkan fail yang dimaksudkan adalah : 
   <a href="javascript:gotoSenaraiBorangB()">
   <font  color="red" onClick="gotoSenaraiBorangB()" ><b><blink>$getListCountBorangB</blink></b></font> 
   </a>
   </li>
 </div>
	

   </td>
   </tr>
   </table>
    </div>
    
    #end
    
    
    <div class="TabbedPanelsContent" id="Carta_Main">   
   
<div style="height:225" id="div_stat">
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top">
<img width="30" height="30" src="../img/images_stat.png" align="center"/>
</td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td   valign="top" >
<b>Carta Status Permohonan di Pengkalan Data <br /><font color="blue">$!negeri_sever</font></b>
</td>
</tr>

<tr>
<td   valign="top" >


    <canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
    
  
  
    



</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
    </div>
  
   </div>
</div>
 


</td>
</tr>
</table> 


</td>
</tr>
</table>



</td>
<td width="1%" valign="top">



</td>
</tr>
</table>







<script>

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

function gotoKemaskini() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail";
	document.${formName}.submit();
}

function gotoAdd4() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

function gotoAdd8() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
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
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}

function gotoCalendar() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=lebah.pm.module.ActivitiesModule";
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
    document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT";
	document.${formName}.submit();
}


function gotoOnline8() {

	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Online";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function gotoOnline17() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek8Online17";
	document.${formName}.method="POST";
	document.${formName}.submit();
}







</script>




<script type="text/javascript" charset="utf-8">





		
		$$("a.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#0000", borderColor: "#0000", textColor: "black",opacity:"100"});
		});
		$$("form input.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#FC9", borderColor: "#C96", textColor: "#000", textShadowColor: "#FFF"});
		});
	</script>
    
    
    
    
    
    
    <script src="../RGraph/libraries/RGraph.common.core.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.dynamic.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.tooltips.js" ></script>
    <script src="../RGraph/libraries/RGraph.common.effects.js" ></script>
    <script src="../RGraph/libraries/RGraph.hbar.js" ></script>
    <script src="../RGraph/libraries/RGraph.pie.js" ></script>   

    <script>
	var sek8 = '$!fail_sek8';
	var sek17 = '$!fail_sek17';
	var hapus = '$!fail_hapus';
	var semua = '$!jumlah_keseluruhan';
	var semua_pie = parseInt(sek8)+parseInt(sek17);
	var fail_selesai = '$!fail_selesai';
	var fail_xselesai = '$!fail_xselesai';
	
	
	
	
	
        window.onload = function (e)
        {
            var hbar = new RGraph.HBar('cvs', [parseInt(fail_xselesai),parseInt(fail_selesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Belum Selesai','Selesai']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Belum Selesai','Selesai']);
            }            
            hbar.Set('chart.labels.above.decimals',0);
            hbar.Set('chart.xlabels', false);
            hbar.Set('chart.gutter.left', 90);
            hbar.Set('chart.gutter.right', 60);
            hbar.Set('chart.gutter.top',10);
    
            hbar.Set('chart.noxaxis', true);
            hbar.Set('chart.noxtickmarks', true);
            hbar.Set('chart.noytickmarks', true);
            RGraph.isOld() ? hbar.Draw() : RGraph.Effects.HBar.Grow(hbar);
			
			/*
			 var pie = new RGraph.Pie('cvs_pie', [parseInt(sek8),parseInt(sek17)]);
			//pie.Set('chart.gutter.left', 45);
            pie.Set('chart.colors', ['purple','yellow']);
            pie.Set('chart.labels', ['Seksyen 8','Seksyen 17']);
			//pie.Set('chart.key', ['Seksyen 8','Seksyen 17']);
            //pie.Set('chart.key.background', 'white');
            //pie.Set('chart.labels.sticks', true);
            //pie.Set('chart.labels.sticks.length', 5);
            pie.Set('chart.exploded', 5);            
            if (!RGraph.isOld()) {
                pie.Set('chart.shadow', true);
                pie.Set('chart.shadow.offsetx', 4);
                pie.Set('chart.shadow.offsety', 4);
                pie.Set('chart.shadow.blur', 4);
                pie.Set('chart.tooltips', ['123','9291']);
            }			
            pie.Set('chart.radius', 50);
          //  pie.Set('chart.centerx', 90);
           // pie.Set('chart.centery', 120);
            pie.Set('chart.strokestyle', 'rgba(0,0,0,0)');            
            RGraph.isOld() ? pie.Draw() : RGraph.Effects.Pie.RoundRobin(pie); 
			*/          
        }
		/*
		function changeTab(index)
		{
		doAjaxCall${formName}("changeTab","tab_index="+index+"");
		}*/
	</script>
    
     <script>
    var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
	</script>	
   
   
   
    
   