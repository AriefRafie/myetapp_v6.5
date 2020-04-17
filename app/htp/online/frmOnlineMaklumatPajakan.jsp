<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#set($hide='style="display:none"')
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

  <input name="actionPajakan" type="hidden" id="actionPajakan" value="$actionPajakan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
    <input name="selectedTab" type="hidden" id="selectedTab" value="$selectedTab"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="subUrusan" type="hidden" id="subUrusan" value="$subUrusan"/>
  <input name="idHakmilikUrusan" type="hidden" id="idHakmilikUrusan" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td>#parse("app/htp/frmPajakanHeaderOnline.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL ONLINE DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
  #if ($idFail != '')
	<tr>
    <td>
           <div id="TabbedPanels1" class="TabbedPanels">
            
              <ul class="TabbedPanelsTabGroup">              
                <li class="TabbedPanelsTab" title="Pemohon Pajakan" onclick="doChangeTab(0)" tabindex="0">MAKLUMAT PERMOHONAN ONLINE</li>
                <li class="TabbedPanelsTab" title="Ulasan KJP" onclick="doChangeTab(1)" tabindex="0">SENARAI HAKMILIK ONLINE</li>

              </ul>
              
              <div class="TabbedPanelsContentGroup">              
                <div class="TabbedPanelsContent">
                <!-- content pemohon pajakan -->
     				#if ($selectedTab == '0')

                   <fieldset>
                    <legend><strong>MAKLUMAT PERMOHONAN cccc</strong></legend>
                    
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
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
                            <td width="70%"><input name="txtNoFail" type="text" disabled="disabled" class="disabled" id="txtNoFail" value="$beanMaklumatPermohonan.noFail" size="30" onkeyup="this.value=this.value.toUpperCase();"/></td>
                         </tr>
                         <tr>
                            <td>
                         <!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
                            </td>
                            <td>No. Fail KJP</td>
                            <td>:</td>
                            <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$classDis" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" /></td>
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
                         <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();" $readOnly class="$classDis"/></td>
                         </tr>
                         <tr>
                           <td>
                         <!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
                           </td>
                           <td>Tarikh Surat Pemohon</td>
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
                         <tr>
                            <td></td>
                           <td valign="top">&nbsp;</td>
                            <td colspan="2" valign="top">
                       			     
                            #if ($mode == 'view')
                <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()" $hide/>
               				#elseif ($mode == 'update')
                <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniFail()" $hide/>
                <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>
                <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()" $hide />
           					 #end  
                            
                            
                            </td>
                          </tr>
                        
                        </table>
                    </fieldset>
                        
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
                              #if($idStatus == '138')
                              
                              <input class="stylobutton" name="cmdDaftar" type="button" value="Pilih Tanah" onClick="pilihTanah('$idPermohonan','$idKementarian')" $hide>
                              #end
                              </td>
                            </tr>
                            <tr class="table_header">
                              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
                              <!--<td width="15%"><strong>Pegangan Hakmilik</strong></td>-->
                            	<td width="18%"><strong>Negeri</strong></td>
                              	<td width="18%"><strong>Daerah</strong></td>
                              	<td width="18%"><strong>Bandar/Pekan/Mukim</strong></td>
                              	<td width="13%"><strong>No. Hakmilik</strong></td>
                              	<td width="13%"><strong>No. Warta</strong></td>
                             	<td width="13%"><strong>Lot</strong></td>
                              	<td width="7%">Hapus</td>
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
                            <td class="$row">$list.negeri</td>
                            <td class="$row">$list.daerah</td>
                            <td class="$row">$list.mukim</td>
                           	<td class="$row">$list.noHakmilik</td>
                            <td class="$row">$list.noWarta</td>
                            <td class="$row">$list.lot</td>
                            <td class="$row" align="center">
                            	<!--<a href="javascript: doDeleteHakmilik('$list.idHakmilikUrusan')">
                            		
                            	</a> --> <input type="button" value="Hapus" onclick="doDeleteHakmilik('$list.idHakmilikUrusan');" $hide/>
                            </td><!--<img src="../img/delete.gif" border="0">-->
                          </tr>
                          #end
                          #else
                          <tr>
                            <td class="row1" align="center">&nbsp;</td>
                            <td class="row1">Tiada Rekod</td>
                            <td class="row1">&nbsp;</td>
                            <td class="row1">&nbsp;</td>
                            <td class="row1">&nbsp;</td>
                            <td class="row1">&nbsp;</td>
                            <td class="row1">&nbsp;</td>
                            <td class="row1">&nbsp;</td>
                          </tr>
                          #end
                        </table>
                        #if ($idStatus == '1' || $idStatus == '6' || $idStatus == '12' || $idStatus == '63')
    <input class="stylobutton" name="cmdSeterusnya" type="button" value="Sahkan" onclick="seterusnya()" $hide/>
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
function seterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnya";
	doAjaxCall${formName}("");
}

function pilihTanah(idPermohonan,idKem) {
	var url = "../x/${securityToken}/ekptg.view.online.htp.pajakan.FrmPajakanPopupSenaraiTanahView?idPermohonan="+idPermohonan+"&idKementerian="+idKem;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah() {
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.submit();
	refreshAgain()
}
function refreshAgain() {
	document.${formName}.submit();
}
function doDeleteHakmilik(idHakmilikUrusan){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
	document.${formName}.hitButton.value = "deleteHakmilik";
	document.${formName}.submit();
}

function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}

function KemaskiniFail(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}

function batalKemaskiniFail(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function SimpanKemaskiniFail(){
	
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
	
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdateFail";
	doAjaxCall${formName}("");
}
</script>
