<!--frmPajakanPenerimaanPermohonanDaftarHakmilik.jsp-->
<!--CL-02-023-->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="actionPajakan" id="actionPajakan" value="$!actionPajakan"/>
  <input type="hidden" name="mode" id="mode" value="$!mode"/>
  <input type="hidden" name="hitButton" id="hitButton" value="$!hitButton"/>
  <input type="hidden" name="selectedTab" id="selectedTab" value="$!selectedTab"/>
  <input type="hidden" name="idFail" id="idFail" value="$!idFail"/>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$!idPermohonan"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$!idStatus"/>
  <input type="hidden" name="subUrusan" id="subUrusan" value="$!subUrusan"/>
  <input type="hidden" name="idHakmilikUrusan" id="!idHakmilikUrusan" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">

  #if ($idFail != '')
 	<tr>
		<td>
		#parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
     #if ($!bayarPajakan.size()!= 0)

		<!-- <tr>
			<td>
			<div class="warning" align="left">
				Sila Ambil Perhatian. Bayaran Pajakan Masih Belum Dijelaskan.
			</div>
			</td>
		</tr> -->
		<script>
			alert("Sila Ambil Perhatian. Bayaran Pajakan Masih Belum Dijelaskan.");
		</script>
	#end

  <tr>
    <td>##parse("app/htp/pajakan/fail/frmPajakanHeader.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>

  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
  #if ($idFail != '')
	<tr>
    <td>
           <div id="TabbedPanels1" class="TabbedPanels">

              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Pemohon Pajakan" onclick="doChangeTab(0)" tabindex="0">MAKLUMAT PERMOHONAN</li>
                <li class="TabbedPanelsTab" title="Senarai Hakmilik" onclick="doChangeTab(1)" tabindex="0">SENARAI HAKMILIK</li>

              </ul>

              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                <!-- content pemohon pajakan -->
     			#if ($selectedTab == '0')
                	<table width="100%" border="0" >
					<tr>
						<td>


                   <fieldset>
                    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>

                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                        #set($flagFail = "")
                        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)

                        	#if($!beanMaklumatPermohonan.flagMohonFail.equalsIgnoreCase(""))
						    	#set($flagFail = "")
						    #else
						    	#set($flagFail = $!beanMaklumatPermohonan.flagMohonFail)
						    #end

                         <tr>
                            <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td width="28%">Negeri</td>
                            <td width="1%">:</td>
                            <td width="70%">$beanMaklumatPermohonan.negeri</td>
                         </tr>
                         <tr>
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td>Kementerian</td>
                            <td>:</td>
                            <td>$beanMaklumatPermohonan.kementerian</td>
                         </tr>
                         <tr>
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td>Agensi</td>
                            <td>:</td>
                            <td>$beanMaklumatPermohonan.agensi</td>
                         </tr>
                         <tr>
                            <td>&nbsp;</td>
                            <td>Urusan</td>
                            <td>:</td>
                            <td>882 - PAJAKAN TANAH</td>
                         </tr>
                         <tr>
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td>Sub Urusan</td>
                            <td>:</td>
                            <td>$beanMaklumatPermohonan.namaSubUrusan</td>
                         </tr>
                         <tr>
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td>Status Tanah</td>
                            <td>:</td>
                            <td>$beanMaklumatPermohonan.statusTanah</td>
                         </tr>
                         <tr>
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td>Jenis Fail</td>
                            <td>:</td>
                            <td>$beanMaklumatPermohonan.jenisFail</td>
                         </tr>
                         <tr>
                            <td width="1%">&nbsp;</td>
                            <td width="28%">No. Fail Seksyen</td>
                            <td width="1%">:</td>
                            <td width="70%"><input name="txtNoFail" type="text" disabled="disabled" class="disabled" id="txtNoFail" size="43" value="$beanMaklumatPermohonan.noFail" onkeyup="this.value=this.value.toUpperCase();"/></td>
                         </tr>
                         <tr>
                            <td>
                         <!--	#if ($mode != 'view')<span class="style1">*</span>#end -->
                            </td>
                            <td>No. Fail KJP</td>
                            <td>:</td>
                            <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$classDis" size="43" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" /></td>
                         </tr>
                         <tr>
                            <td>
                         <!--	#if ($mode != 'view')<span class="style1">*</span>#end -->
                            </td>
                            <td valign="top">Tarikh Surat KJP</td>
                            <td>:</td>
                            <td><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readOnly class="$classDis" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
                            #if ($mode != 'view')
                            <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                            #end            </td>
                         </tr>
                         <tr>
                         <td>
                         <!--	#if ($mode != 'view')<span class="style1">*</span>#end -->
                         </td>
                         <td>No. Fail Lain / Pemohon</td>
                         <td>:</td>
                         <td>
                         	<input type="text" name="txtNoFailLain" id="txtNoFailLain" size="43" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();" $readOnly class="$classDis"/>
                         	#if ($mode != 'view')
								<a href="javascript:tambahFailLain('$idFail','tambah')" class="style1">...</a>
							#end
                         </td>
                         </tr>
                   	#foreach ($agenda in $senaraiFailLain)
                   		#if($!agenda.isURL.equals('0'))
			       		<tr>
							<td >&nbsp;</td>
					        <td >&nbsp;</td>
					        <td >&nbsp;</td>
         					<td >$!agenda.noFail</td>
		              	</tr>
		              	#else
	       				<tr>
							<td >&nbsp;</td>
					        <td >&nbsp;</td>
					        <td >&nbsp;</td>
         					<td >
   								<a href="javascript:paparPautan('$agenda.idFailURL')" class="pautanms">$!agenda.noFail</a>
         					</td>
		              	</tr>
		              	#end
					#end
                         <tr>
                           <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                           <td>Tarikh Surat Permohonan</td>
                           <td>:</td>
                           <td>
                           	<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
								#if ($mode != 'view')
									<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/>
								#end
							</td>
                         </tr>
                         <tr style="display:none">
                            <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                            <td valign="top">Tarikh Agihan</td>
                            <td>:</td>
                            <td><input name="tarikhAgihan" type="text" class="$classDis" id="tarikhAgihan" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhAgihan" size="10" maxlength="10" $readOnly/>
                            #if ($mode != 'view')
                            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                            #end
                            </td>
                         </tr>
                         <tr>
                            <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                           <td valign="top">Tajuk</td>
                            <td valign="top">:</td>
                            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" $readOnly class="$classDis" onkeyup="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea></td>
                         </tr>
                         #end
                         <!--<tr>
                            <td></td>
                           <td valign="top">&nbsp;</td>
                            <td colspan="2" valign="top">
                             </td>
                          </tr> -->

                        </table>

                    </fieldset>

           				</td>
					</tr>
					<tr>
						<td align="center">
                        #if ($mode == 'view')
                          	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniFail()" />
               				<input class="stylobutton100" type="button"  name="Cetak" id="Cetak" value="Previu" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" />
          				#elseif ($mode == 'update')
               				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanKemaskiniFail()" />
               				<!-- <input class="stylobutton100" type="reset" name="cmdReset" id="cmdReset" value="Kosongkan"/> -->
               				<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()" />
           				#end
						</td>
					</tr>

			        <tr>
				  		<td >
				  			<fieldset id="tabledokumensurat" style="display:none;"><legend>SENARAI SURAT/DOKUMEN</legend>
							<table width="100%" border="0">
							  <tr>
							    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!idPermohonan','&template=NoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">KULIT FAIL</a></td>
							  </tr>
							  <tr>
							    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!idPermohonan','&template=rptNoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">DOKET</a></td>
							  </tr>
							  <tr>
							    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!idPermohonan','&template=HTPajakanMaklumat','ekptg.report.htp.NoFailTajukFail')" class="pautanms">MAKLUMAT PAJAKAN</a></td>
							  </tr>
							  </table>
							</fieldset>
					  </td>
				  	</tr>
					</table>
                	#end

                </div>
                    <!-- close content pemohon pajakan -->

                <div class="TabbedPanelsContent">
                <!-- content Ulasan KJP -->
   					#if ($selectedTab == '1')
                    <fieldset>
                    <legend><strong>SENARAI HAKMILIK</strong></legend>

                        <table align="center" width="100%">
                            <tr>
                              <td colspan="8" scope="row">
                              ##if($idStatus == '6' || $idStatus == '1' || $idStatus == '12' || $SenaraiHakmilik.size() == 0)
                              <input class="stylobutton100" name="cmdDaftar" type="button" value="Pilih Tanah" onClick="pilihTanah('$idPermohonan')">
                              ##end
                              </td>
                            </tr>
                            <tr class="table_header">
                              <!--<td scope="row" width="5%" align="center"><strong>Bil.</strong></td>

                            	<td width="15%"><strong>Negeri</strong></td>
                              	<td width="15%"><strong>Daerah</strong></td>
                              	<td width="16%"><strong>Bandar/Pekan/Mukim</strong></td>
                              	<td width="13%"><strong>No. Hakmilik</strong></td>
                              	<td width="13%"><strong>No. Warta</strong></td>
                             	<td width="10%"><strong>No Lot/PT</strong></td>
                             	<td width="8%"><strong>Luas Dipohon</strong></td>
                              	<td width="7%"><strong>Hapus</strong></td> -->
                              	<td scope="row" width="3%" align="center">Bil.</td>
                              	<td width="14%">Negeri</td>
                              	<td width="14%">Daerah</td>
                              	<td width="10%">Bandar/Pekan/Mukim</td>
                              	<td width="5%">Seksyen</td>

                              	<td width="14%">No. Hakmilik</td>
                              	<td width="13%">No. Warta</td>
                             	<td width="10%">No. Lot/PT</td>
                             	<td width="10%">Luas Pohon</td>
				  	  			<td width="7%"><div align="center">Tindakan</div></td>
                            </tr>
                          #set ($list = "")
                          #if ($SenaraiHakmilik.size() > 0)
                          #foreach ($list in $SenaraiHakmilik)
                            #if ($list.bil == '')
                                #set( $row = "row1" )
                            #elseif (($list.bil % 2) != 0)
                                #set( $row = "row1" )
                            #else
                                #set( $row = "row2" )
                            #end
                          <tr>
                            <td class="$row" align="center">$list.bil.</td>
                            <!-- <td class="$row">$list.peganganHakmilik</td> -->
                            <td class="$row"><a href="javascript:kemaskiniTanah('$!idPermohonan','$!list.idHakmilikUrusan','$!list.noLot');" class="pautanms">$!list.negeri</a></td>
                            <td class="$row">$!list.daerah</td>
                            <td class="$row">$!list.mukim</td>
                           <td class="$row">$!list.namaSeksyen</td>

                           	<td class="$row">$!list.kodJenisHakmilik $!list.noHakmilik</td>
                            <td class="$row">$!list.noWarta</td>
                            <td class="$row">$!list.lot</td>
                            <td class="$row">$!list.luasBersamaan $!list.kodLuasBersamaan</td>
                            <td>
                            	<div align="center">
    							    #if (!$!list.gisCharting.equals('N') )
    								<a alt="GIS" href = "javascript:gisWindow('TIADA','$!maklumatGis');">
						      			<img border="0" src="../img/online/map.png" width="20" height="15"/>
						      		</a>
						      		#end
						      		<a alt="Hapus" href = "javascript:doDeleteHakmilik('$list.idHakmilikUrusan');">
						      			<img border="0" src="../img/online/x.gif" width="20" height="15"/>
						      		</a>
   				 				</div>
				 			</td>
                          </tr>
                          #end
                          #else
                          <tr>
                            <td class="row1" align="center">&nbsp;</td>
                            <td class="row1" align="left" colspan="8">Tiada Rekod</td>
                          </tr>
                          #end
                        </table>
                        #if ( ($idStatus == '1' || $idStatus == '6' || $idStatus == '12' || $idStatus == '63') && ($!SenaraiHakmilik.size() > 0) )
    						<!-- <input class="stylobutton100" name="cmdSeterusnya" type="button" value="Sahkan" onclick="seterusnya()"/> -->
    						<input class="stylobutton100" name="cmdSeterusnya" type="button" value="Seterusnya" onclick="seterusnya()"/>
    					#end
                    </fieldset>
    				#end
              </div>
              <!-- close content Ulasan KJP -->


         </div>
         <!-- close TabbedPanelsContentGroup -->

      </div>
      <!-- close TabbedPanels1 -->



    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">

    </td>
  </tr>
  #end
</table>


<script language="javascript" type="text/javascript">
	#if ($idFail != '')
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
	#end
</script>

<script>
/*function seterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}

	document.${formName}.hitButton.value = "seterusnya";
	doAjaxCall${formName}("");
}
*/
	/** 2017/05/17
	function pilihTanah(idPermohonan) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPajakanPopupSenaraiTanahView?idPermohonan="+idPermohonan;
	    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}*/
/*function refreshFromPilihTanah() {
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.submit();
	refreshAgain()
}
function refreshAgain() {
	document.${formName}.submit();
}*/
/** 2017/05/17
function doDeleteHakmilik(idHakmilikUrusan){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "papardaftar";
			return;
		}

		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
		document.${formName}.hitButton.value = "deletehakmilik";
		document.${formName}.submit();

	}
*/
/*function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");

}
*/
	//, 2017/05/08 - Komen
	/*function KemaskiniFail(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");
	}

	function batalKemaskiniFail(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	} */

	//, 2017/05/08 - Komen
	//function SimpanKemaskiniFail(){

	/*if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan No Fail KJP.');
  		document.${formName}.txtNoFailKJP.focus();
		return;
	}
	if(document.${formName}.tarikhSuratKJP.value == ""){
		alert('Sila masukkan Tarikh Surat KJP.');
  		document.${formName}.tarikhSuratKJP.focus();
		return;
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan No. Fail Lain');
  		document.${formName}.txtNoFailLain.focus();
		return;
	}
	if(document.${formName}.tarikhAgihan.value == ""){
		alert('Sila masukkan Tarikh Agihan.');
  		document.${formName}.tarikhAgihan.focus();
		return;
	}*/
	/*
	if(document.${formName}.tarikhSuratPemohon.value == ""){
		alert('Sila masukkan Tarikh Surat Permohonan.');
  		document.${formName}.tarikhSuratPemohon.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus();
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		return;
	}

	document.${formName}.mode.value = "view";
	//document.${formName}.actionPajakan.value = "papar";
	document.${formName}.actionPajakan.value = "papardaftar";
	document.${formName}.hitButton.value = "saveUpdateFail";
	doAjaxCall${formName}("");

}
*/
// 19/08/2010, 2017/05/08 - Komen
/*	function senaraiDokumenSurat(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
*/
//19/08/2010 bertujuan mencetak doket atau Fail, , 2017/05/08 - Komen
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
/*	function cetakFailDoket(id,temp,urlserv) {
		var param ="";
	    var url = "../servlet/"+urlserv+"?"+id+temp;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
*/
/*function langkah1(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function langkah2(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah3(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();

}

function langkah4(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah5(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
*/
	function kemaskiniTanah_(idPermohonan,idHakmilikUrusan) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPajakanPopupSenaraiTanahView?idPermohonan="+idPermohonan+"&idhakmilikurusan="+idHakmilikUrusan+"&actionPopup=kemaskini";
	    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}

	//by Rosli 2012/08/14
	/** 2017/05/18
	function tambahFailLain(id,command){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmFailLainKemaskini?idFailLama="+id+"&command="+command;
	    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	  	if ((document.window != null) && (!hWnd.opener))
	   		hWnd.opener = document.window;
	  	if (hWnd.focus != null) hWnd.focus();
	}

	function paparPautan(idFail){
		url = "../servlet/ekptg.view.htp.FrmPenswastaan2Servlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);

		document.${formName}.action = "$EkptgUtil.getTabID('Penswastaan',$portal_role)?_portal_module=ekptg.view.htp.FrmPenswastaan2SyarikatView&actionPenswastaan=papar&mode=view&idFail="+idFail;
		document.${formName}.submit();
		//doAjaxCall${formName}("");

	} */

	//Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
	/**
	 * N = BARU SAVE
	 * H = HANTAR
	 * S = SAH (TELAH DISAHKAN)
	 *
	 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
	 */
	/** 2017/05/18
	function doHantarPengesahan(){
		 if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "papardaftar";
			return;

		 }
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.hitButton.value = "hantarpengesahandaftar";
		doAjaxCall${formName}("");

	 }

	 function doSahkan() {
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "papardaftar";
			return;

		}
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		//document.${formName}.hitButton.value = "sahkanPermohonan";
		document.${formName}.hitButton.value = "sahkanpermohonandaftar";
		doAjaxCall${formName}("");

	}*/
</script>
##parse("app/htp/utiliti/javascript/javaScriptPajakanLangkah2.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakanDaftarHakmilik.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
##parse("app/htp/utiliti/javaScriptUmum.jsp")
