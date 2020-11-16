<style type="text/css">
.style1 {color: #0033FF}


</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_fail" />
<input type="hidden" name="id_permohonan" />	
<input type="hidden" name="id_siasatan" />	
<input type="hidden" name="id_hakmilik" />		
<input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
<input type="hidden" name="flag_MyInfoPPT"/>
<input type="hidden" name="id_penarikanbalik" />
<input type="hidden" name="id_pembatalan" />

<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>
<script src="../RGraph/libraries/RGraph.common.core.js" ></script>
<script src="../RGraph/libraries/RGraph.common.dynamic.js" ></script>
<script src="../RGraph/libraries/RGraph.common.tooltips.js" ></script>
<script src="../RGraph/libraries/RGraph.common.effects.js" ></script>
<script src="../RGraph/libraries/RGraph.hbar.js" ></script>
<script src="../RGraph/libraries/RGraph.pie.js" ></script>  


 <SCRIPT>
function fade(div_id, button) {

	if(button.value == 'FadeOut') {
		$('#'+div_id).fadeOut('slow');
		button.value = 'FadeIn';
	}
	else {
		$('#'+div_id).fadeIn('slow');
		button.value = 'FadeOut';
	}
}
</SCRIPT>
<!--
::::::::::::::::::::::::::::::::$divVisible

#set ( $divVisible = "")
#if ( $divVisible != "" ) #set ( $divVisible = "visible") #else #set ( $divVisible = "hidden") #end

::::::::::::::::::::::::::::::::$divVisible


<div id="div-popup" class="regular" style="position: fixed; visibility:" + $divVisible>
<div style="height:200px;overflow-y:scroll">
<div id="div_getOnline8">

</div>
</div>
</div>
-->
<!----------------------------------------------------->
<p align="center" >
<div id="div_utama"  >
<table   id="table_utama" cellpadding="2" cellspacing="1"  border="0" width="100%"  class="nav" align="center"  >
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
							<font color="blue"><li>&nbsp;Tukar Kata Laluan </li></font>						
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
<td>
<a href="javascript:gotoInbox()" class="help" title="My Inbox">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_inbox > 0)                         
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!check_notifikasi_inbox</span></font></b>
                             </label>&nbsp;
                             #end
                            Ruang Perbincangan Formal</li></font>	
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
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/cari_icon.png" align="center"/></td>
<td width="85%">
<table width="100%">
<tr>
<td>
<b>Carian Terperinci</b>
</td>
</tr>

<tr>
<td>
<input type="text" name="search" id="search"    maxlength="200"   size="30" onkeydown="return runScript(event)" onkeypress="return runScript(event)" onkeyup="return runScript(event)"  /> 
<a href="javascript:open_info()" class="help" title="info">							
                            <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
                            </a>
                             
                          
<br />
<input type="button" id="but_search" name="but_search" value="Cari" onClick="cari()"  />
<input type="button" id="but_search" name="but_search" value="Kosongkan" onClick="kosong()"  />
</td>
</tr>

<tr id="tr_carianFail">
<td>
<div id="div_carianFail" ></div>
</td>
</tr>

<tr id="tr_carianHakmilik">
<td>
<div id="div_carianHakmilik"></div>
</td>
</tr>

<tr id="tr_carianPB">
<td>
<div id="div_carianPB"></div>
</td>
</tr>

<tr>
	<td>
												<table>
													<tr>
														<td>
															<a href="https://www.gis.myetapp.gov.my/myetappgis/guest.php" target = "_blank" style="color:#0000FF">Maklumat GIS</a>
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
</table>
    
    
    
    </td>
  </tr>
  #set($hideTR = "style='display:none'")
  #if($user_negeri_login != '16')
  	#set($hideTR = "")
  #end
  
  <tr $hideTR >
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
<b><em>My Info</em></b>
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





</table>
</td>
</tr>
</table>

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/utiliti.png" align="center"/></td>
<td width="85%">
<table>

<tr>
<td>
<b>Utiliti</b></td>
</tr>

<tr>
<td>
<a href="javascript:gotoKemaskini()" class="help" title="Kemaskini Fail">
							<font color="blue"><li>&nbsp;Kemaskini Fail</li></font>						
				  </a>
</td>
</tr>

<!--<tr>
<td>


<a href="javascript:gotoEtanah()" class="help" title="EndorsanEtanahWPKL">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_aduan > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!check_notifikasi_aduan</span></font></b>
                             </label>&nbsp;
                             #end
                           Endorsan Dari eTanah</li></font>	
						</a>



                      
                        
</td>
</tr>-->

#if($!user_negeri_login == '14')

<tr>
<td id="showCountEndorsan" >
<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('showCountEndorsan','showCountEndorsan','');					
});
</script>
</td>
</tr>
#end




</table>
</td>
</tr>
</table>



</td>

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

<tr>
<td>
<a href="javascript:gotoAddBantahanPB()" class="help" title="Permohonan Baru Bantahan Mahkamah (Pihak Berkepentingan)">
							<font color="blue"><li>&nbsp;Bantahan Mahkamah (PB)</li></font>						
						</a>
</td>
</tr>


<tr>
<td>
<a href="javascript:gotoAddBantahanAG()" class="help" title="Permohonan Baru Bantahan Mahkamah (Agensi)">
							<font color="blue"><li>&nbsp;Bantahan Mahkamah (Agensi)</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoAddPenarikan()" class="help" title="Permohonan Baru Penarikan Balik">
							<font color="blue"><li>&nbsp;Penarikan Balik</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoAddPembatalan()" class="help" title="Permohonan Baru Pembatalan">
							<font color="blue"><li>&nbsp;Pembatalan</li></font>						
						</a>
</td>
</tr>

<tr>
<td>
<a href="javascript:gotoAddSementara()" class="help" title="Permohonan Baru Pendudukan/Penggunaan Sementara">
							<font color="blue"><li>&nbsp;Pendudukan Sementara</li></font>						
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
  <tr $hideTR >
    <td valign="top" align="left">
    
    <table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
<tr>



<td width="50%" valign="top">


<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/applyonline.png" align="center"/></td>
<td width="85%" valign="top">
<div id="div_getMainOnline"></div>
</td>
</tr>
</table>


</td>
<td width="50%" valign="top">
<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30"  src="../img/task_distribution.png" align="center"/></td>
<td width="85%" valign="top">
<div id="div_getAgihan"></div>

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
<td>


<a href="javascript:gotoFLMS()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan">
                            <font color="blue">
                            <li>          
                             #if($!check_notifikasi_aduan > 0)                        
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!check_notifikasi_aduan</span></font></b>
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
<br />

</td>
<!-- penambahan yati -->
<td width="50%" valign="top">

<table width="100%" >
<tr>
<td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/cari_icon.png" align="center"/></td>
<td width="85%">
<table width="100%">
<tr>
<td>
<b>Carian No.Siasatan</b>
</td>
</tr>

<tr>
<td>
<input type="text" name="searchsiasatan" id="searchsiasatan"    maxlength="200"   size="30" onkeydown="return runScript(event)" onkeypress="return runScript(event)" onkeyup="return runScript(event)"  /> 
<a href="javascript:open_info()" class="help" title="info">							
                            <b><font color="blue"><img src="../img/info.png"  align="center" /></font></b>
                            </a>
                             
                          
<br />
<input type="button" id="siasatan_search" name="siasatan_search" value="Cari" onClick="cariSiasatan()"  />
<input type="button" id="siasatan_search" name="siasatan_search" value="Kosongkan" onClick="kosongSiasatan()"  />
</td>
</tr>

<tr id="tr_carianFailSiasatan">
<td>
<div id="div_carianFailSiasatan" ></div>
</td>
</tr>

<tr id="tr_carianHakmilikSiasatan">
<td>
<div id="div_carianHakmilikSiasatan"></div>
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
  
  
<td width="50%" valign="top">



</td>
</tr>
</table>
    
    </td>
  </tr>
</table>








</td>
<td width="39%" valign="top">
<table width="100%" border="0"  >
<tr>
<td width="100%" valign="top">




<div id="divMainStats">
<script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	doDivAjaxCall$formname('divMainStats','getMainStat','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');			
});
</script>


</div>








</td>
</tr>

<tr>
<td valign="top">

<div id="dashboard_tab"   ></div>


</td>
</tr>
</table>



</td>
<td width="1%" valign="top">



</td>
</tr>
</table>

</div>

<script>




function open_info() 
{

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

new_window.document.write("<table width='100%'><tr><td width='33%' valign='top'>");

new_window.document.write("<table><tr><td><b>Fail</b></td></tr>");
new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail JKPTG </li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTG</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail PTD</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Fail UPT</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;Nama Projek</li></font>	");
new_window.document.write("<font color='blue'><li>&nbsp;Kementerian</li></font></td></tr></table>");

new_window.document.write("</td><td width='33%' valign='top'>");

new_window.document.write("<table><tr><td><b>Hakmilik</b></td></tr>");
new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Hakmilik</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;Jenis Hakmilik</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Lot/PT</li></font>");
new_window.document.write("</td></tr></table>");

new_window.document.write("</td><td width='33%' valign='top'>");

new_window.document.write("<table><tr><td><b>Pihak Berkepentingan</b></td></tr>");
new_window.document.write("<tr><td><font color='blue'><li>&nbsp;Nama</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;MYid</li></font>");
new_window.document.write("<font color='blue'><li>&nbsp;No. Akaun</li></font></td></tr></table>");

new_window.document.write("</td></tr></table>");

new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan bilangan fail, hakmilik dan pihak berkepentingan bedasarkan maklumat carian yang dimasukkan. Jika bilangan menunjukkan lebih daripada '0', pengguna boleh klik pada <i>link</i> tersebut bagi memaparkan senarai fail, hakmilik dan pihak berkepentingan yang berkenaan.</td></tr></table>");
new_window.document.write("</body></html>");
new_window.document.close();



}

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






function cari()
{
	if(document.getElementById('search').value=="")	
	{
		alert("Sila Masukkan Maklumat Carian");
		document.getElementById('search').focus();
	}
	else
	{
	reset_jquery('_');	
	reset_jqueryAgihan('_');	
	document.getElementById('tr_carianFail').style.display="";
	document.getElementById('tr_carianHakmilik').style.display="";
	document.getElementById('tr_carianPB').style.display="";
	doDivAjaxCall$formname('div_carianFail','carianFail','');
	doDivAjaxCall$formname('div_carianHakmilik','carianHakmilik','');
	doDivAjaxCall$formname('div_carianPB','carianPB','');
	}
}

function kosong()
{	
	document.getElementById('search').value="";
	reset_jquery('_');	
	reset_jqueryAgihan('_');
	document.getElementById('tr_carianFail').style.display="none";
	document.getElementById('tr_carianHakmilik').style.display="none";
	document.getElementById('tr_carianPB').style.display="none"
	doDivAjaxCall$formname('div_carianFail','carianFail_close','');
	doDivAjaxCall$formname('div_carianHakmilik','carianHakmilik_close','');
	doDivAjaxCall$formname('div_carianPB','carianPB_close','');	
}

//penambahan yati
function cariSiasatan()
{
	if(document.getElementById('searchsiasatan').value=="")	
	{
		//alert("1");
		alert("Sila Masukkan Maklumat Carian Siasatan");
		document.getElementById('searchsiasatan').focus();
	}
	else
	{
	reset_jquery('_');	
	reset_jqueryAgihan('_');
	document.getElementById('tr_carianFailSiasatan').style.display="";
	document.getElementById('tr_carianHakmilikSiasatan').style.display="";
	//document.getElementById('tr_carianPB').style.display="";
	doDivAjaxCall$formname('div_carianFailSiasatan','carianFailSiasatan','');
	doDivAjaxCall$formname('div_carianHakmilikSiasatan','carianHakmilikSiasatan','');
	//doDivAjaxCall$formname('div_carianPB','carianPB','');
	}
}

function kosongSiasatan()
{	
	document.getElementById('searchsiasatan').value="";
	reset_jquery('_');	
	reset_jqueryAgihan('_');
	document.getElementById('tr_carianFailSiasatan').style.display="none";
	document.getElementById('tr_carianHakmilikSiasatan').style.display="none";
	//document.getElementById('tr_carianPB').style.display="none"
	doDivAjaxCall$formname('div_carianFailSiasatan','carianFailSiasatan_close','');
	doDivAjaxCall$formname('div_carianHakmilikSiasatan','carianHakmilikSiasatan_close','');
	//doDivAjaxCall$formname('div_carianPB','carianPB_close','');	
}


function all_reset(current_div)
{
//alert("1");
reset_jquery(current_div);	
//alert("2");
reset_jqueryAgihan(current_div);
//alert("3");
reset_jqueryCarian(current_div)	
//alert("4");
}



function getListFail()
{
all_reset('div_listFail');	
document.getElementById('div_listFail').style.display="";		
doDivAjaxCall$formname('div_listFail','getListFail','');
}

//penambahan yati
function getListFailSiasatan()
{
all_reset('div_listFailSiasatan');	
document.getElementById('div_listFailSiasatan').style.display="";		
doDivAjaxCall$formname('div_listFailSiasatan','getListFailSiasatan','');
}


function getListHakmilik()
{
all_reset('div_listHakmilik');	
document.getElementById('div_listHakmilik').style.display="";		
doDivAjaxCall$formname('div_listHakmilik','getListHakmilik','');
}

function getListEndorsanK()
{
all_reset('div_getListEndorsanK');;
document.getElementById('div_listEndorsanK').style.display="";	
doDivAjaxCall$formname('div_listEndorsanK','getListEndorsanK','');

}

function getListEndorsanKClose()
{
all_reset('divlistEndorsanK');	
document.getElementById('divlistEndorsanK').style.display="";		
doDivAjaxCall$formname('divlistEndorsanK','divlistEndorsanK','');
}

	
//penambahan yati
function getListHakmilikSiasatan()
{
all_reset('div_listHakmilikSiasatan');	
document.getElementById('div_listHakmilikSiasatan').style.display="";		
doDivAjaxCall$formname('div_listHakmilikSiasatan','getListHakmilikSiasatan','');
}

/*penambahan yati*/
function getListCaj()
{	
document.getElementById('div_listCaj').style.display="";		
doDivAjaxCall$formname('div_listCaj','getListCaj','');
}
function getListCajClose()
{
all_reset('divlistCaj');	
document.getElementById('divlistCaj').style.display="";		
doDivAjaxCall$formname('divlistCaj','divlistCaj','');
}

function getListPB()
{
all_reset('div_listPB');	
document.getElementById('div_listPB').style.display="";		
doDivAjaxCall$formname('div_listPB','getListPB','');
}


function gotoAgihan1()
{
all_reset('div_agihan1');	
document.getElementById('div_agihan1').style.display="";		
doDivAjaxCall$formname('div_agihan1','getAgihan1','');
}
function gotoAgihan2()
{
all_reset('div_agihan2');	
document.getElementById('div_agihan2').style.display="";		
doDivAjaxCall$formname('div_agihan2','getAgihan2','');
}
function gotoAgihan3()
{
all_reset('div_agihan3');	
document.getElementById('div_agihan3').style.display="";		
doDivAjaxCall$formname('div_agihan3','getAgihan3','');
}
function gotoAgihan4()
{
all_reset('div_agihan4');
document.getElementById('div_agihan4').style.display="";		
doDivAjaxCall$formname('div_agihan4','getAgihan4','');
}
function gotoAgihan5()
{

all_reset('div_agihan5');
document.getElementById('div_agihan5').style.display="";		
doDivAjaxCall$formname('div_agihan5','getAgihan5','');
}
function gotoAgihan6()
{
all_reset('div_agihan6');
document.getElementById('div_agihan6').style.display="";		
doDivAjaxCall$formname('div_agihan6','getAgihan6','');
}
function gotoAgihan7()
{
all_reset('div_agihan7');
document.getElementById('div_agihan7').style.display="";		
doDivAjaxCall$formname('div_agihan7','getAgihan7','');
}
function gotoAgihan8()
{
all_reset('div_agihan8');
document.getElementById('div_agihan8').style.display="";		
doDivAjaxCall$formname('div_agihan8','getAgihan8','');
}
function gotoAgihan9()
{
all_reset('div_agihan9');
document.getElementById('div_agihan9').style.display="";		
doDivAjaxCall$formname('div_agihan9','getAgihan9','');
}
function gotoAgihan10()
{
all_reset('div_agihan10');
document.getElementById('div_agihan10').style.display="";		
doDivAjaxCall$formname('div_agihan10','getAgihan10','');
}


function gotoSek4()
{
	//alert("masuk ler");
all_reset('div_getOnline4');
//alert("masuk2");
document.getElementById('div_getOnline4').style.display="";	
//alert("masuk3");
doDivAjaxCall$formname('div_getOnline4','getOnline4','');
//alert("masuk4");
}

function gotoSek8()
{
all_reset('div_getOnline8');
document.getElementById('div_getOnline8').style.display="";		
doDivAjaxCall$formname('div_getOnline8','getOnline8','');		
}

function gotoSekBantahanPB()
{
all_reset('div_getOnlineBantahanPB');	
document.getElementById('div_getOnlineBantahanPB').style.display="";	
doDivAjaxCall$formname('div_getOnlineBantahanPB','getOnlineBantahanPB','');		
}

function gotoSekBantahanAG()
{
all_reset('div_getOnlineBantahanAG');	
document.getElementById('div_getOnlineBantahanAG').style.display="";
doDivAjaxCall$formname('div_getOnlineBantahanAG','getOnlineBantahanAG','');		
}

function gotoSekPembatalan()
{
all_reset('div_getOnlinePembatalan');	
document.getElementById('div_getOnlinePembatalan').style.display="";
doDivAjaxCall$formname('div_getOnlinePembatalan','getOnlinePembatalan','');	
//alert("11");	
}

function gotoSekPenarikan()
{
all_reset('div_getOnlinePenarikan');
document.getElementById('div_getOnlinePenarikan').style.display="";	
doDivAjaxCall$formname('div_getOnlinePenarikan','getOnlinePenarikan','');	
//alert("22");	
}

function gotoSekSementara()
{
all_reset('div_getOnlineSementara');
document.getElementById('div_getOnlineSementara').style.display="";		
doDivAjaxCall$formname('div_getOnlineSementara','getOnlineSementara','');
//alert("33");		
}


function reset_jqueryCarian(current_div)
{
	if(current_div!="div_listFail")
	{
		
		if (document.getElementById('div_listFail') != null && document.getElementById('div_listFail') != undefined) 
		{
			
		document.getElementById('div_listFail').style.display="none";	
		
		//doDivAjaxCall$formname('div_listFail','getListFail_close','');
		document.getElementById('div_listFail').innerHTML = "";		
		}
	}
	
	if(current_div!="div_listHakmilik")
	{
		//alert("2");
		if (document.getElementById('div_listHakmilik') != null && document.getElementById('div_listHakmilik') != undefined) 
		{
		//	alert("2a");
		document.getElementById('div_listHakmilik').style.display="none";	
		
		//doDivAjaxCall$formname('div_listHakmilik','getListHakmilik_close','');
		document.getElementById('div_listHakmilik').innerHTML = "";	
		}
	}
	
	
	if(current_div!="div_listPB")
	{
		//alert("3");
		if (document.getElementById('div_listPB') != null && document.getElementById('div_listPB') != undefined) 
		{
		//	alert("3a");
		document.getElementById('div_listPB').style.display="none";	
		
		//doDivAjaxCall$formname('div_listPB','getListPB_close','');
		document.getElementById('div_listPB').innerHTML = "";		
		}
	}	
}

function reset_jqueryAgihan(current_div)
{
	if(current_div!="div_agihan1")
	{
	document.getElementById('div_agihan1').style.display="none";	
	//doDivAjaxCall$formname('div_agihan1','getAgihan1_close','');	
	document.getElementById('div_agihan1').innerHTML = "";
	}
	
	if(current_div!="div_agihan2")
	{
	document.getElementById('div_agihan2').style.display="none";	
	//doDivAjaxCall$formname('div_agihan2','getAgihan2_close','');	
	document.getElementById('div_agihan2').innerHTML = "";
	}
	
	if(current_div!="div_agihan3")
	{
	document.getElementById('div_agihan3').style.display="none";	
	//doDivAjaxCall$formname('div_agihan3','getAgihan3_close','');
	document.getElementById('div_agihan3').innerHTML = "";	
	}
	
	if(current_div!="div_agihan4")
	{
	document.getElementById('div_agihan4').style.display="none";	
	//doDivAjaxCall$formname('div_agihan4','getAgihan4_close','');
	document.getElementById('div_agihan4').innerHTML = "";	
	}
	
	if(current_div!="div_agihan5")
	{
	document.getElementById('div_agihan5').style.display="none";	
	//doDivAjaxCall$formname('div_agihan5','getAgihan5_close','');
	document.getElementById('div_agihan5').innerHTML = "";		
	}
	
	if(current_div!="div_agihan6")
	{
	document.getElementById('div_agihan6').style.display="none";	
	//doDivAjaxCall$formname('div_agihan6','getAgihan6_close','');
	document.getElementById('div_agihan6').innerHTML = "";	
	}
	
	if(current_div!="div_agihan7")
	{
	document.getElementById('div_agihan7').style.display="none";	
	//doDivAjaxCall$formname('div_agihan7','getAgihan7_close','');
	document.getElementById('div_agihan7').innerHTML = "";	
	}
	
	if(current_div!="div_agihan8")
	{
	document.getElementById('div_agihan8').style.display="none";	
	//doDivAjaxCall$formname('div_agihan8','getAgihan8_close','');
	document.getElementById('div_agihan8').innerHTML = "";	
	}
	
	if(current_div!="div_agihan9")
	{
	document.getElementById('div_agihan9').style.display="none";	
	//doDivAjaxCall$formname('div_agihan9','getAgihan9_close','');
	document.getElementById('div_agihan9').innerHTML = "";		
	}
	
	
}

function reset_jquery(current_div)
{
	
	if(current_div!="div_getOnline4")
	{
		if (document.getElementById('div_getOnline4') != null && document.getElementById('div_getOnline4') != undefined) 
		{	
		document.getElementById('div_getOnline4').style.display="none";	
		//doDivAjaxCall$formname('div_getOnline4','getOnline4_close','');	
		document.getElementById('div_getOnline4').innerHTML = "";
		}
	//alert("1");
	}
	
	
	if(current_div!="div_getOnline8")
	{
		if (document.getElementById('div_getOnline8') != null && document.getElementById('div_getOnline8') != undefined) 
		{	
		document.getElementById('div_getOnline8').style.display="none";	
		//doDivAjaxCall$formname('div_getOnline8','getOnline8_close','');
		document.getElementById('div_getOnline8').innerHTML = "";
		}
	//alert("2");
	}
	
	if(current_div!="div_getOnlineSementara")
	{
		if (document.getElementById('div_getOnlineSementara') != null && document.getElementById('div_getOnlineSementara') != undefined) 
		{
		document.getElementById('div_getOnlineSementara').style.display="none";	
		//doDivAjaxCall$formname('div_getOnlineSementara','getOnlineSementara_close','');
		document.getElementById('div_getOnlineSementara').innerHTML = "";
		}
	//alert("3");	
	}
	
	if(current_div!="div_getOnlineBantahanPB")
	{
		if (document.getElementById('div_getOnlineBantahanPB') != null && document.getElementById('div_getOnlineBantahanPB') != undefined) 
		{
		document.getElementById('div_getOnlineBantahanPB').style.display="none";	
		//doDivAjaxCall$formname('div_getOnlineBantahanPB','getOnlineBantahanPB_close','');
		document.getElementById('div_getOnlineBantahanPB').innerHTML = "";
		}
	//alert("4");
	}
	
	if(current_div!="div_getOnlineBantahanAG")
	{
		if (document.getElementById('div_getOnlineBantahanAG') != null && document.getElementById('div_getOnlineBantahanAG') != undefined) 
		{
		document.getElementById('div_getOnlineBantahanAG').style.display="none";	
		//doDivAjaxCall$formname('div_getOnlineBantahanAG','getOnlineBantahanAG_close','');
		document.getElementById('div_getOnlineBantahanAG').innerHTML = "";
		}
	//alert("5");
	}
	
	if(current_div!="div_getOnlinePenarikan")
	{
		//alert(current_div);
		if (document.getElementById('div_getOnlinePenarikan') != null && document.getElementById('div_getOnlinePenarikan') != undefined) 
		{	
		document.getElementById('div_getOnlinePenarikan').style.display="none";	
		//doDivAjaxCall$formname('div_getOnlinePenarikan','getOnlinePenarikan_close','');
		document.getElementById('div_getOnlinePenarikan').innerHTML = "";	
		}
	//alert("6");
	}
		
	if(current_div!="div_getOnlinePembatalan")
	{
		if (document.getElementById('div_getOnlinePembatalan') != null && document.getElementById('div_getOnlinePembatalan') != undefined) 
		{
		document.getElementById('div_getOnlinePembatalan').style.display="none";	
		//doDivAjaxCall$formname('div_getOnlinePembatalan','getOnlinePembatalan_close','');
		document.getElementById('div_getOnlinePembatalan').innerHTML = "";
		}
	//alert("7");
	}
	
	if(current_div!="div_listEndorsanK")
	{
		//alert(current_div);
		if (document.getElementById('div_listEndorsanK') != null && document.getElementById('div_listEndorsanK') != undefined) 
		{
		document.getElementById('div_listEndorsanK').style.display="none";	
		//yati
		//doDivAjaxCall$formname('div_listEndorsanK','getListEndorsanK_close','');
		document.getElementById('div_listEndorsanK').innerHTML = "";
		}
	}

	if(current_div!="div_listCaj")
	{
		if (document.getElementById('div_listCaj') != null && document.getElementById('div_listCaj') != undefined) 
		{
		document.getElementById('div_listCaj').style.display="none";	
		document.getElementById('div_listCaj').innerHTML = "";
		}
	}
	
}

function gotoAdd4() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

function gotoAdd8() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}

function gotoAddBantahanPB() {
	document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function gotoAddBantahanAG() {
	document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function gotoAddPenarikan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function gotoAddPembatalan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();
}

function gotoAddSementara() {
	document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}


	


function doCetak(namaborang) {

	if(namaborang=="BorangA"){
    	var url = "../reports/ppk/BORANG A0.pdf";
	}else if(namaborang=="BorangP"){
    	var url = "../reports/ppk/BORANG P0.pdf";
	}else if(namaborang=="BorangK1"){
    	var url = "../reports/ppk/BORANG K10.pdf";
	}else if(namaborang=="BorangK2"){
    	var url = "../reports/ppk/BORANG K20.pdf";
	}else if(namaborang=="BorangPersetujuan"){
    	var url = "../reports/ppk/BORANG PERSETUJUAN.pdf";
	}
	
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


function gotoKemaskini() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail";
	document.${formName}.submit();
}

function gotoEndorsanEtanah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiSenaraiEndorsan";
	document.${formName}.submit();
}

function gotoBKE() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE";
	document.${formName}.submit();
}





function gotoAddLama() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=FrmPrmhnnSek8SenaraiSemakInternalKutipan";
	document.${formName}.submit();
}

function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function gotoEtanah() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiSenaraiEndorsan";
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
	//document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah.planner.CalendarModule";	
	//document.${formName}.action = "1274928663956?_portal_module=lebah.pm.module.ActivitiesModule";
	//alert("xxxx"+document.${formName}.action)
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
    document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT";
	//document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT";
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

//penambahan yati

function paparCaj(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) {
	
	document.${formName}.id_fail.value = ID_FAIL;	
	document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&ScreenLocation=top&paging=3";
	document.${formName}.submit();
	
}

function paparSiasatan(ID_PERMOHONAN,ID_HAKMILIK,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE,ID_SIASATAN) {
	
	document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	document.${formName}.id_hakmilik.value = ID_HAKMILIK;
	document.${formName}.id_siasatan.value = ID_SIASATAN;	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=RecordSiasatan&subminor_command=Papar&location=maklumat_siasatan&point=maklumat_siasatan&id_permohonan="+ID_PERMOHONAN+"&id_hakmilik="+ID_HAKMILIK;
	document.${formName}.submit();
}

function paparFail(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) {
	
	document.${formName}.id_fail.value = ID_FAIL;
	document.${formName}.id_permohonan.value = ID_PERMOHONAN;	
	if (ID_STATUS == '82'){		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan=$id_permohonan&command=viewListHM&ScreenLocation=top&paging=16";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 
	document.${formName}.submit();
	
}

//bella buat bijak here
// JPPH
    var target = 'TotalEndorsan';
    var actionName = 'getTotalEndorsan';
 	doAjaxUpdater(document.Fekptg_view_ppt_FrmDashboard, url, target, actionName);

function gotoEndorsan() {

	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiSenaraiEndorsan";
	document.${formName}.method="POST";
	document.${formName}.submit();
}



/*
function papar(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) {

//**START SEKSYEN 4 DAN 8
	//alert(ID_STATUS);
	//menu : pendaftaran
	//status : permohonan cawangan
	if (ID_STATUS == '11' || ID_STATUS == '138'){	
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&paging=1";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=semakPendaftaran&ScreenLocation=top&paging=1";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan&command=semakPendaftaran&ScreenLocation=top&paging=1";				
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close permohonan cawangan

	
	//menu : agihan tugas(new form)
	//status : disahkan pengarah
	else if (ID_STATUS == '127'){	
		
		if (ID_SUBURUSAN == '51'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4&command=tambahAgihan&ScreenLocation=top&paging=2";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
			}
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&command=semakHM&ScreenLocation=top&paging=2";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik&command=semakHM&ScreenLocation=top&paging=2";			
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close agihan tugas(new form)

	
	//menu : Laporan awal tanah
	//status : Tindakan pegawai
	else if (ID_STATUS == '148'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai&command=tambahLaporan&paging=3";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=4";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah&command=tambahLaporan&paging=4";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Laporan awal tanah

	
	//menu : Penyediaan Kertas MMK
	//status : Penyediaan laporan awal
	else if (ID_STATUS == '147' || ID_STATUS == '26'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai&command=viewSenaraiMMK&ScreenLocation=top&paging=4";
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '147'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=10";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewMMK&paging=8";						
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penyediaan Kertas MMK

	
	//menu : Pewartaan / notis awam / Endorsan
	//status : Pewartaan
	else if (ID_STATUS == '31' || ID_STATUS == '52'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam&command=viewListNotis&ScreenLocation=top&paging=5";
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '31'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=11";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=14";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraNotisAwam&command=viewListNotis&paging=9";						
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close pewartaan dan notis awam

//menu : Agihan tugas
	//status : Tindakan Pentadbir Tanah
	else if (ID_STATUS == '1612198'){
		
		if (ID_SUBURUSAN == '52'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"  || USER_ROLE=="(PPT)PenolongPegawaiTanahUnitPengambilanTanah"  || USER_ROLE=="(PPT)PenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=screenAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pentadbir Tanah");
			}	
		}else if (ID_SUBURUSAN == '53'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"  || USER_ROLE=="(PPT)PenolongPegawaiTanahUnitPengambilanTanah"  || USER_ROLE=="(PPT)PenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pentadbir Tanah");
			}						
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//Tindakan Pentadbir Tanah
	
	
	//menu : Hakmilik dan Pihak Berkepentingan
	//status : Maklumat hakmilik
	else if (ID_STATUS == '16'){
		
		if (ID_SUBURUSAN == '52'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=screenAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
			}	
		}else if (ID_SUBURUSAN == '53'){
			if(USER_ROLE=="adminppt" || USER_ROLE=="(PPT)PengarahUnit" || USER_ROLE=="(PPT)KetuaPenolongPengarahUnit"){
				document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";
			}else{
				alert("Sila Tunggu Hakmilik Di Permohonan Ini Diagihkan Oleh Pengarah");
			}						
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close hakmilik dan pihak berkepentingan

	
	//menu : Penyediaan Kertas MMK Sek 8
	//status : JPBD / JPPH dan Maklumat Jabatan Teknikal dan status2 mmk
	else if (ID_STATUS == '43' || ID_STATUS == '22' || ID_STATUS == '132' || ID_STATUS == '133' || ID_STATUS == '134'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH&command=viewListHM&ScreenLocation=top&paging=11";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penyediaan Kertas MMK Sek 8


	//menu : Borang E dan F
	//status : memorial/endosan dhdk
	else if (ID_STATUS == '35'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=12";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Borang E dan F

	
	//menu : Notis Awam
	//status : Borang F
	else if (ID_STATUS == '54'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=13";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Notis Awam

	//menu : Penandaan Kawasan
	//status : Penyampaian Notis
	else if (ID_STATUS == '58'){
		
		if (ID_SUBURUSAN == '52'){
			if(FLAG_SEGERA == '1'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera&command=viewSegera&ScreenLocation=top&paging=22";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPenandaanKawasan&command=viewListHM&ScreenLocation=top&paging=12";				
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Penandaan Kawasan

	
	//menu : Siasatan dan Perintah
	//status : Tanda kawasan
	else if (ID_STATUS == '38' || ID_STATUS == '62'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Siasatan dan Perintah


	//menu : Pampasan
	//status : Bicara
	else if (ID_STATUS == '68'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewlistHM&ScreenLocation=top&paging=14";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Pampasan


	//menu : Borang K
	//status : Bayaran Pampasan
	else if (ID_STATUS == '72' || ID_STATUS == '59'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewListHM&ScreenLocation=top&paging=14";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Borang K


	//menu : Endorsan Borang K
	//status : Borang K
	else if (ID_STATUS == '76'){
		
		if (ID_SUBURUSAN == '52'){
			if(FLAG_SEGERA == '1'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK&command=viewEndosan&ScreenLocation=top&paging=20";
			}
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Endorsan Borang K


	//menu : Permintaan ukur
	//status : Permintaan ukur
	else if (ID_STATUS == '82'){		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=viewListHM&ScreenLocation=top&paging=23";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close Permintaan ukur
	
//**END SEKSYEN 4 DAN 8
	
	
// PENDUDUKAN/PENGGUNAAN SEMENTARA

	//menu : Rujukan Ke Mahkamah (Pendudukan/Penggunaan Sementara)
	//status : Rujukan Ke Mahkamah
	else if (ID_STATUS == '1610193'){		
		if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraBorangM&command=baruBorangM&ScreenLocation=top&paging=15";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//Rujukan Ke Mahkamah	
	
	
	//menu : Perundingan
	//status : Set Perundingan dan Perundingan
	else if (ID_STATUS == '1610192' || ID_STATUS == '1610194'){		
		if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//Rujukan Ke Mahkamah		
	
//** END PENDUDUKAN/PENGGUNAAN SEMENTARA	
	
	
//penarikan balik - razman

	else if  (ID_STATUS == '74'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//pembatalan - razman	
	else if (ID_STATUS == '235'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//bantahan - elly 

	// DALAM PROSES
	else if (ID_STATUS == '181'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// URUSAN DEPOSIT
	else if (ID_STATUS == '183'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH
	else if (ID_STATUS == '184'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// TARIK BALIK BANTAHAN
	else if (ID_STATUS == '185'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// URUSAN BAYARAN
	else if (ID_STATUS == '186'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// SELESAI
	else if (ID_STATUS == '187'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// PEMBATALAN OLEH MT
	else if (ID_STATUS == '220'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// BORANG O
	else if (ID_STATUS == '1610248'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// DALAM PROSES[AGENSI]
	else if (ID_STATUS == '199'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}		
	
	// URUSAN DEPOSIT[AGENSI]
	else if (ID_STATUS == '200'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH[AGENSI]
	else if (ID_STATUS == '201'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// BORANG O [AGENSI]
	else if (ID_STATUS == '1610249'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// TARIK BALIK[AGENSI]
	else if (ID_STATUS == '1610249'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		

	}else{
		alert("Sila Hubungi Admin");
		return;
	}	
	
	
	//document.${formName}.flag_MyInfoPPT.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
	
}
*/


</script>



<script>
$jquery(document).ready(function() {
      $jquery('a.div-window').click(function() {
            
                //Getting the variable's value from a link 
            var divBox = $(this).attr('href');
            //myDiv_label = document.getElementById(memo-box);
            
            //Fade in the Popup
            $jquery(divBox).fadeIn(300);
            
            //Set the center alignment padding + border see css style
            var popMargTop = ($jquery(divBox).height() + 24) / 2; 
            var popMargLeft = ($jquery(divBox).width() + 24) / 2; 
            
            $jquery(divBox).css({ 
                  'margin-top' : -popMargTop,
                  'margin-left' : -popMargLeft
            });
            
            // Add the mask to body
            //$jquery('body').append('<html><title>Paparan Selanjutnya</title>');
            $jquery('body').append('<div id="mask"></div>');
            //$jquery('body').append(myDiv_label.innerHTML);
            //$jquery('body').append('</div>');
            //$jquery('body').append('</html>');
            $jquery('#mask').fadeIn(300);
            
            return false;
      });
      
      // When clicking on the button close or the mask layer the popup closed
      $jquery('a.close, #mask').live('click', function() { 
        $jquery('#mask , .div-popup').fadeOut(300 , function() {
            $jquery('#mask').remove();  
      }); 
      return false;
      });
});
</script>
   
    <script type="text/javascript" charset="utf-8">
		
		$$("a.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#0000", borderColor: "#0000", textColor: "black",opacity:"100"});
		});
		$$("form input.help").each( function(input) {
			new Tooltip(input, {backgroundColor: "#FC9", borderColor: "#C96", textColor: "#000", textShadowColor: "#FFF"});
		});
		
		
function doHighlight(bodyText, searchTerm, highlightStartTag, highlightEndTag) 
{
  
  if ((!highlightStartTag) || (!highlightEndTag)) {
    highlightStartTag = "<font style='color:blue; background-color:yellow;'>";
    highlightEndTag = "</font>";
  }  
  var newText = "";
  var i = -1;
  var lcSearchTerm = searchTerm.toLowerCase();
  var lcBodyText = bodyText.toLowerCase();
    
  while (bodyText.length > 0) {
    i = lcBodyText.indexOf(lcSearchTerm, i+1);
    if (i < 0) {
      newText += bodyText;
      bodyText = "";
    } else {
      // skip anything inside an HTML tag
      if (bodyText.lastIndexOf(">", i) >= bodyText.lastIndexOf("<", i)) {
        // skip anything inside a <script> block
        if (lcBodyText.lastIndexOf("/script>", i) >= lcBodyText.lastIndexOf("<script", i)) {
          newText += bodyText.substring(0, i) + highlightStartTag + bodyText.substr(i, searchTerm.length) + highlightEndTag;
          bodyText = bodyText.substr(i + searchTerm.length);
          lcBodyText = bodyText.toLowerCase();
          i = -1;
        }
      }
    }
  }
  
  //alert("kuar:"+newText) 
  return newText;
}



function runScript(e) {
	 var key;     
     if(window.event)
          key = window.event.keyCode; //IE
     else
          key = e.which; //firefox     

     return (key != 13);
}	
	</script>

<script>
function arkibWindow(noFail){
	alert(noFail);
		//var url =  "http://www.etapp.gov.my/";
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PPT&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
}
</script>  
   
 
   #parse("app/ppt/dashboard/script.jsp")
   #parse("app/ppt/MyInfoPPT_script.jsp") 
   #parse("app/integrasi/gis/javaScript.jsp") 