<style type="text/css">
table.dashboard_sub {
	border-top:none;
	border-left:none;
	border-right:none;
	border-bottom:1px dotted #088A4B;
}
table.dashboard_tepi {
	border-top:1px solid #088A4B;
	border-left:1px solid #088A4B;
	border-right:1px solid #088A4B;
	border-bottom:1px solid #088A4B;
}
td.dashboard_sub_kiri {
	border-top:none;
	border-left:1px dotted #088A4B;
	border-right:none;
	border-bottom:none;
}
table.dashboard_kiri {
	border-top:none;
	border-left:none;
	border-right:none;
	border-bottom:none;
}
table.dashboard_bawah {
	border-top:1px solid #088A4B;
	border-left:1px solid #088A4B;
	border-right:1px solid #088A4B;
	border-bottom:1px solid #088A4B;
}
table.dashboard_tepi_bawah {
	border-top:none;
	border-left:1px solid #088A4B;
	border-right:none;
	border-bottom:none;
}
table.nav {
	border-top:3px solid #088A4B;
	border-left:none;
	border-right:none;
	border-bottom:3px solid #088A4B;
}
table.alert {
	border-top:none;
	border-left:none;
	border-right:none;
	border-bottom:1px solid #088A4B;
}
.tooltip {
	position: absolute!important;
	overflow:hidden;
	font-size: 12px;
	z-index: 10000!important;
}
.tooltip .xtop, .tooltip .xbottom {
	background: transparent;
	font-size: 0px;
}
.tooltip .xb1, .tooltip .xb2, .tooltip .xb3, .tooltip .xb4 {
	display: block;
	overflow: hidden;
}
.tooltip .xb1, .tooltip .xb2, .tooltip .xb3 {
	height: 0px;
}
.tooltip .xb2, .tooltip .xb3, .tooltip .xb4 {
	background: #666;
}
.tooltip .xbottom .xb2, .tooltip .xbottom .xb3, .tooltip .xbottom .xb4 {
	background: #666;
}
.tooltip .xb1 {
	margin: 0 0px;
}
.tooltip .xb2 {
	margin: 0 0px;
}
.tooltip .xb3 {
	margin: 0 0px;
}
.tooltip .xb4 {
	height: 0px;
}
.tooltip .xboxcontent {
	padding: 0 .5em;
	margin: 0;
	color: #000;
	word-wrap:break-word;
	border: none;
	background-color:white;
	font-family: Verdana;
}
a.nav:link {
	link:#333333;
}
a.nav:visited {
	color: #333333;
	text-decoration: none;
}
#marquee_replacement {
	border: 3px solid #088A4B;
	height: 300px;
	margin-bottom: 10px;
	overflow: auto;
	width: 100%;
}
</style>
<script type="text/javascript" src="../library/online/prototype.js"></script>
<script type="text/javascript" src="../library/online/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript" src="../library/online/tooltips.js"></script>
<p align="center">
<table cellpadding="2" cellspacing="1" border="0" width="100%" class="nav" align="center" >
  <tr>
    <td width="1%" valign="top"   ></td>
    <td width="59%" valign="top"  ><table width="100%" border="0" align="left" class="dashboard_kiri">
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img src="../img/myprofile.png"  width="30" height="30" align="center"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>Profil</b></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoProfile()" class="help" title="Tukar Kata Laluan"> <font color="blue">
                              <li>&nbsp;Tukar Kata Laluan</li>
                              </font> </a> </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoInbox()" class="help" title="Inbox"> <font color="blue">
                              <li> #if($!check_notifikasi_inbox > 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>$!check_notifikasi_inbox</blink></font></b> </label>
                                &nbsp;
                                #end                                
                                Ruangan Perbincangan Formal</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><a href="https://www.gis.myetapp.gov.my/myetappgis/guest.php" target = "_blank" style="color:#0000FF">Maklumat GIS</a>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img  src="../img/main.png" width="30" height="30" align="center"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>Tugasan</b> </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoSenaraiTugasan()" class="help" title="Fail Tugasan"> <font color="blue">
                              <li>&nbsp;Carian</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><table width="100%" >
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img  width="30" height="30" src="../img/new_application.png" align="center"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>Daftar Permohonan</b></td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoPenguatkuasaan()" class="help" title="Permohonan Baru Penguatkuasaan"> <font color="blue">
                              <li>Penguatkuasaan</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/utiliti.png" align="center"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>Utiliti</b> </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoKutipanCRB()" class="help" title="Kutipan Data Penguatkuasaan"> <font color="blue">
                              <li>&nbsp;Kutipan Data Penguatkuasaan</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="dashboard_sub" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/transfer.png" align="center"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>Senarai Laporan Tanah</b> </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoLaporanTanah()" class="help" title="Senarai Laporan Tanah"> <font color="blue">
                              <li>&nbsp;Laporan Tanah</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
                <td width="50%" valign="top"><table width="100%" >
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left"><table cellpadding="2" cellspacing="1" border="0" width="100%" class="" align="left">
              <tr>
                <td width="50%" valign="top"><table width="100%" >
                    <tr>
                      <td width="15%" align="center" valign="top"><img width="30" height="30" src="../img/aduan.png"/></td>
                      <td width="85%"><table width="100%">
                          <tr>
                            <td><b>PLA</b> (Pengurusan Log Aduan) </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoFLMS()" class="help" title="Hantar Log Aduan, Cadangan atau Pertanyaan"> <font color="blue">
                              <li> #if($!check_notifikasi_aduan > 0)
                                <label style="background-color:blue"  align="center" valign="top" > <b><font color="WHITE"><blink>$!check_notifikasi_aduan</blink></font></b> </label>
                                &nbsp;
                                #end
                                Log Aduan</li>
                              </font> </a> </td>
                          </tr>
                          <tr>
                            <td><a href="javascript:gotoFLMSstat()" class="help" title="Statistik Log FLMS"> <font color="blue">
                              <li>&nbsp;Statistik Log</li>
                              </font> </a> </td>
                          </tr>
                        </table></td>
                    </tr>
                  </table>
                  <br />
                  <br />
                </td>
                <td width="50%" valign="top"><table width="100%" >
                  </table>
                  <!--<table width="100%" border="0">
                    <tr>
                      <td width="15%" align="center" valign="top"></td>
                      <td width="85%"><table>
                        <tr>
                          <td><b>Capaian Sistem Imbasan Dokumen</b></td>
                        </tr>
                        <tr>
                          <td><input name="txtNoFail" id="txtNoFail" type="text" maxlength="200" size="30" style="text-transform:uppercase;" />
                            <a href="javascript:open_infoSID()" class="help" title="info"> <b><font color="blue"><img src="../img/info.png" alt=""  align="center" /></font></b></a> <br/>
                            <input type="button" id="input" name="input" value="Cari" onclick="javascript:arkibWindow()"/>
                            <input type="button" id="but_search2" name="but_search2" value="Kosongkan" onclick="kosong()"  /></td>
                        </tr>
                        <tr id="tr_carianFail2">
                          <td><div id="div_carianFail2" ></div></td>
                        </tr>
                        <tr id="tr_carianHakmilik2">
                          <td><div id="div_carianHakmilik2"></div></td>
                        </tr>
                        <tr>
                          <td><!--<a href="javascript:alert('dddd')" target="_blank" style="color:#0000FF">Maklumat GIS</a></td>
                        </tr>
                      </table></td>
                    </tr>
                  </table>--></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td width="39%" valign="top"><table width="100%" border="0" >
        <tr>
          <td width="100%"><table cellpadding="2" cellspacing="1" border="0" width="100%" class=" dashboard_tepi" align="left">
              <tr>
                <td width="100%" valign="top"><table width="100%"  id="table_stat">
                    <tr>
                      <td width="15%" align="center" valign="top"></td>
                      <td width="85%" ><table width="100%">
                          <tr>
                            <td colspan="3"><b>Statistik Fail di Pangkalan Data </b></td>
                          </tr>
                          <tr>
                            <td valign="top" width="49%"><font color="blue">
                              <li>Fail Penguatkuasaan</li>
                              </font></td>
                            <td  valign="top" width="1%"> :</td>
                            <td  valign="top" width="50%"><b>$!failPenguatkuasaan</b></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" class="dashboard_bawah" >
              <tr>
                <td><div id="TabbedPanels1" class="TabbedPanels">
                    <ul class="TabbedPanelsTabGroup">
                      #if($list_memo_aktif.size()>0)
                      <li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head" >Pengumuman</li>
                      #end
                      <li class="TabbedPanelsTab" tabindex="0"  id="Carta_Head"  >Carta</li>
                    </ul>
                    <div class="TabbedPanelsContentGroup"> #if($list_memo_aktif.size()>0)
                      <div class="TabbedPanelsContent"  id="Peringatan_Main" style="height:250" >
                        <table width="100%" >
                          <tr>
                            <td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
                            <td width="88%" valign="top"><table width="100%">
                                <tr>
                                  <td   valign="top" > #parse("app/ppk/frmPengumuman.jsp") </td>
                                </tr>
                              </table></td>
                          </tr>
                        </table>
                      </div>
                      #end
                      <div class="TabbedPanelsContent" id="Carta_Main">
                        <div style="height:250" id="div_stat">
                          <table width="100%" >
                            <tr>
                              <td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/images_stat.png" align="center"/> </td>
                              <td width="88%" valign="top"><table width="100%">
                                  <tr>
                                    <td   valign="top" ><b>Carta Status Permohonan di Pengkalan Data</b> </td>
                                  </tr>
                                  <tr>
                                    <td   valign="top" ><canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
                                    </td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td width="1%" valign="top"></td>
  </tr>
</table>
<script>
function gotoProfile() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}
function gotoSenaraiTugasan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSenaraiFailView";
	document.${formName}.submit();
}
function gotoOnline() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSenaraiFailOnlineView";
	document.${formName}.submit();
}
function gotoPenguatkuasaan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSenaraiFailView&actionPengkuatkuasaan=daftarBaru";
	document.${formName}.submit();
}
function gotoKutipanCRB() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBKutipanDataView";
	document.${formName}.submit();
}
function gotoLaporanTanah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.php2.FrmSenaraiLaporanTanahView";
	document.${formName}.submit();
}
function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}
function gotoFLMSstat() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
	document.${formName}.submit();
}
function gotoInbox() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.utils.FrmInboxUsers";
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
	
	var failBelumSelesai = '$!failBelumSelesai';	
	var failSelesai = '$!failSelesai';	
		
        window.onload = function (e)
        {
            var hbar = new RGraph.HBar('cvs', [parseInt(failBelumSelesai),parseInt(failSelesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
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
        }
		
</script>
<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>
<script>
function arkibWindow(){
	
	var noFail = document.${formName}.txtNoFail.value;
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PHP&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
	}
	
function open_infoSID() {
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
	new_window.document.write("<tr><td><font color='blue'><li>&nbsp;No. Fail Seksyen </li></font>");
	
	new_window.document.write("</td></tr></table>");
	
	new_window.document.write("<table><tr><td align='justify'>Sistem akan memaparkan senarai dokumen berkaitan dengan no fail yang di masukkan.</td></tr></table>");
	new_window.document.write("</body></html>");
	new_window.document.close();
}
</script>
#parse("app/integrasi/gis/javaScript.jsp")
<!-- 2020/09/04 -->