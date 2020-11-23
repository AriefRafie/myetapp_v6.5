<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#set($hide='style="display:none"')
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="actionPajakan" id="actionPajakan" value="$actionPajakan"/>
  	<input type="hidden" name="mode" id="mode" value="$mode"/>
  	
  	<input type="hidden" name="hitButton" id="hitButton" value="$hitButton"/>
	<input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
	
	<input type="hidden" name="flagPopup" id="flagPopup" value="$!flagPopup"/>
	<input type="hidden" name="modePopup" id="modePopup" value="$!modePopup"/>
  	<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  	
  	<input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>  	
  	<input type="hidden" name="idPemohon"  id="idPemohon" value="$idPemohon"/>
  	<input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  	<input type="hidden" name="subUrusan" id="subUrusan" value="$subUrusan"/>
  	
  	<input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="idHakmilikUrusan" />
  	<input type="hidden" name="idHakmilik" id="idHakmilik" value="$idHakmilik" />
  	
  	<input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  	<input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
  	<input type="hidden" name="kategori" id="kategori" value="$!pemohon.get("kategoriPemohon")"/>  	
</p>

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if ($idFail != '')
	<tr>
    	<td>#parse("app/htp/frmPajakanHeaderOnline.jsp")</td>
  	</tr>
#else
  	<tr>
    	<td>&nbsp;<div class="warning">SILA PILIH FAIL ONLINE DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  	</tr>
#end

#if ($idFail != '')
	<tr>
    	<td>
        	<div id="TabbedPanels1" class="TabbedPanels">
            	<ul class="TabbedPanelsTabGroup">
            		<li onclick="doChangeTab(0);" class="TabbedPanelsTab"  tabindex="0">MAKLUMAT TANAH</li>
            	<!--<li onclick="doChangeTab(1);" class="TabbedPanelsTab"  tabindex="0">MEMORANDUM JEMAAH MENTERI</li>-->
                	<li onclick="doChangeTab(1);" class="TabbedPanelsTab"  tabindex="0">SENARAI SEMAK</li>
                	<li onclick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN PERMOHONAN</li>
				</ul>
				
				<div class="TabbedPanelsContentGroup">   
             		<div class="TabbedPanelsContent">
             		
             	<!------- CONTENT MAKLUMAT TANAH -------> 
                   	<fieldset>
                    <legend><strong>MAKLUMAT TANAH</strong></legend>
                    #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
    					<input type="hidden" name="idHakmilik" id="idHakmilik" value="$beanMaklumatTanah.idHakmilik" />
    			
                     <table width="100%" border="0" cellspacing="2" cellpadding="2">
                     
                    	<tr>
          					<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          					<td>No. Lot</td>
          					<td width="1%">:</td>
          					<td width="50%">$beanMaklumatTanah.noLot
          					</td>
        				</tr>
        				
        				<tr>
          					<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          					<td>No. Hakmilik</td>
          					<td width="1%">:</td>
          					<td width="50%"> $beanMaklumatTanah.noHakmilik
          					</td>
        				</tr>
        				<tr>
	        				<td width="1%"></td>
	          				<td width="28%">Pengangan Hakmilik</td>
	          				<td width="1%">:</td>
	          				<td width="50%"> $beanMaklumatTanah.peganganHakmilik
             				</td>
        			</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Luas Lot</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.luas
          						<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatTanah.idLuas" /> 
          						<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
			          		<td>Mukim</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.mukim
          						<input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Daerah</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.daerah
          						<input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Negeri</td>
          					<td>:</td>			
          					<td>$beanMaklumatTanah.negeri
            					<input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah">
            					<input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri">
          					</td>
        				</tr>
        				<!--<tr>
          					<td>&nbsp;</td>
          					<td>Kementerian</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.kementerian
            					<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian">
            					<input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian">
          					</td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Agensi</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.agensi
          						<input type="hidden" name="idAgensiTanah" id="idAgensiTanah" value="$beanMaklumatTanah.idAgensi">
        				</tr>-->
              			<tr>
			                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
			                <td>Luas Kegunaan</td>
			                <td>:</td>
			                <td >$selectLuasKegunaan</td>
			              </tr>
			              <tr>
			                <td>&nbsp;</td>
			                <td>Keluasan Asal</td>
			                <td>:</td>
			                <td>$beanMaklumatSewa.luasAsal $beanMaklumatSewa.keteranganLuasAsal
			                  <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$beanMaklumatSewa.luasAsal"/></td>
			              </tr>
			              #if ($idLuasKegunaan == '2')
			              <tr>
			                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
			                <td>Unit Luas</td>
			                <td>:</td>
			                <td>#parse("app/php2/unit_luas.jsp") </td>
			              </tr>
			              #if ($idLuas != '99999' && $idLuas != '')
			              <tr>
			                <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
			                <td>Luas Mohon</td>
			                <td>:</td>
			                <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
			                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/ >
			                  #elseif ($idLuas == '7')
			                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
			                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonly class="$inputTextClass">
			                  #elseif ($idLuas == '8' || $idLuas == '4')
			                  <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatSewa.luas1" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
			                  <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatSewa.luas2" style="text-align:right" onKeyUp="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
			                  <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatSewa.luas3" style="text-align:right" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonly class="$inputTextClass"/>
			                  #end </td>
			              </tr>
			              #end
			              #end
			              <tr>
			                <td>&nbsp;</td>
			                <td>Luas Bersamaan</td>
			                <td>:</td>
			                <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatSewa.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
			                  HEKTAR</td>
			              </tr>
			              <tr>
			                <td>&nbsp;</td>
			                <td>Baki Luas</td>
			                <td>:</td>
			                <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatSewa.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
			                  HEKTAR</td>
			              </tr>             
              			<tr>
                          	<td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td> 
			                <td>
                       		#if ($mode == 'view')
                			<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()"/>
                			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:doBackList()"/>
               				#elseif ($mode == 'update')
                			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniFail()"/>
                			<!--<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>-->
           					<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()"/>
       					 	#end</td>
                       	</tr>
					</table>
				</fieldset>
                #end
			</div>
              <!------- CLOSED SENARAI HAKMILIK------->
              
              
				  
				    <!--<div class="TabbedPanelsContent">-->
                <!------- CONTENT MAKLUMAT PERMOHONAN ------->
                	<!--<fieldset>
                		<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>                		
                    	<table width="100%" border="0" cellspacing="2" cellpadding="2">
                    	#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
                    	
                        	<tr>
                            	<td width="1%"></td>
                            	<td width="28%">Negeri</td>
                            	<td width="1%">:</td>
                            	<td width="70%">$beanMaklumatPermohonan.negeri</td>
                         	</tr>
                        	<tr>
                            	<td></td>
                            	<td>Kementerian</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.kementerian</td>
                         	</tr>
                         	<tr>
                            	<td></td>
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
                            	<td></td>
                            	<td>Sub Urusan</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.namaSubUrusan</td>
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>Status Tanah</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.statusTanah</td>
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>Jenis Fail</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.jenisFail</td>
                         	</tr>
                         	<tr>
                            	<td width="1%">&nbsp;</td>
                            	<td width="28%">No. Fail Seksyen</td>
                            	<td width="1%">:</td>
                            	<td width="70%">
                            	#if ($mode == 'update')
                            	<input name="txtNoFail" type="text" id="txtNoFail" value="$beanMaklumatPermohonan.noFail" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                            	#else
                            	<input name="txtNoFail" type="text" disabled="disabled" class="disabled" id="txtNoFail" value="$beanMaklumatPermohonan.noFail" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td> #end
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>No. Fail KJP</td>
                            	<td>:</td>
                            	<td>
                            	#if ($mode == 'update')
                            	<input type="text" name="txtNoFailKJP" id="txtNoFailKJP" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" />
															#else
															<input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$classDis" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" />                         	
                         		</td>#end
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td valign="top">Tarikh Surat KJP</td>
                            	<td>:</td>
                            	<td>
                            	#if ($mode == 'update')
                            	<input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
                            	<a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                            	#else
                            	<input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readOnly class="$classDis" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
                            	</td>
                            	#end
                         	</tr>
                         	<tr>
                         		<td></td>
                         		<td>No. Fail Lain / Pemohon</td>
                         		<td>:</td>
                         		<td>
                         		#if ($mode == 'update')
                         		<input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();"/>
                         		#else
                         		<input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();" $readOnly class="$classDis"/></td>
                         		</td>
                         		#end
                         	</tr>
                         	<tr>
                           		<td></td>
                           		<td>Tarikh Surat Pemohon</td>
                           		<td>:</td>
                           		<td>
                           		#if ($mode == 'update')
                           		<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon"/>
									<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/>
									#else
									<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
									</td>
								#end
                         	</tr>
                         	<tr>
                            	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                           		<td valign="top">Tujuan</td>
                            	<td valign="top">:</td>
                            	
                            	<td valign="top">
                            	#if ($mode == 'update')
                            	<textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea>
                            	#else
                            	<textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" $readOnly class="$classDis" onkeyup="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea>
                            	</td>
                            	#end
                         	</tr>
                         	#end
                         	<tr>
                            	<td></td>
                           		<td valign="top">&nbsp;</td>
                           		<td valign="top">&nbsp;</td>
                            	<td colspan="2" valign="top">
                       			#if ($mode == 'view')
                					<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()"/>
                					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:goBack()"/>                					
               						#elseif ($mode == 'update')
                					<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniFail()"/>
                					<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>
                					<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()"/>	           					 	
				                  #end </td>
                          	</tr>
            			</table>
                    </fieldset>                    
                	</div>--->
                	<!------- CLOSED CONTENT MAKLUMAT PERMOHONAN ------->
                    
                                 	
              		
              	<!------- SENARAI SEMAK ------->  
			<div class="TabbedPanelsContent">
			
           	<table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<tr>
  				<td> #parse("app/htp/online/frmPajakanSenaraiSemak.jsp") </td>
              </tr>
           	</table>
         </div>
                     
            
            <!------- PENGESAHAN ------->  
		<div class="TabbedPanelsContent">
				<fieldset>
				<legend><strong>PENGESAHAN PERMOHONAN</strong></legend>
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
        
           	<td valign="top">
           	#if ($idStatus == '9920199')
           	<input type="checkbox" name="pengesahan" id="pengesahan">
	        #else
	        <input type="checkbox" name="pengesahan" id="pengesahan" checked disabled></td>
	        #end
      		
           	<td>Kami, <b>$!namaPemohon.get("namaPemohon")</b>, MyCoID <b>$!namaPemohon.get("noPengenalan")</b> dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.
           	</td>
           	           	
           	<tr>
           	<td colspan=2 align="center">
           	#if ($idStatus == '9920199')
            	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onClick="doHantar()"/>
            	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
            	
            #elseif ($idStatus != '16103949')
            	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onClick="doHantar()"/>
            	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
            #end</td>
           	</tr>            
       </table>
				</fieldset>
			</div>
            <!------- CLOSED PENGESAHAN ------->
		</div>
	<!-- CLOSED TabbedPanelsContentGroup -->    
</div>
      		<!-- CLOSED TabbedPanels1 -->
		</td>
	</tr>
	<tr>
    	<td>&nbsp;</td>
  	</tr>
  	<tr>
	    <td align="center"></td>
  	</tr>
#end
</table>


<script language="javascript" type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
#end
</script>

<script>
//LAMPIRAN
//onlineAttach('$list.id','$list.jenisDokumen')
function onlineAttach_(idSenarai,idJenisDokumen) {
	   //
var url = "../x/${securityToken}/ekptg.view.online.UploadDokumen?actionrefresh=htppajakanmycoid&actionPopup=papar&idHarta="+idSenarai+"&idJenisDokumen="+idJenisDokumen+"&flagOnline=$!flagOnline";
	var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	      hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus(); /**/
	//
	var title = 'Cetakan';
	var w =1024;
	var h = 800;
	var left = (screen.width/2)-(w/2);
	//var top = (screen.height/2)-(h/2);
	//return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}

function onlineAttachView(id_){
	var url = "../servlet/ekptg.view.ppk.util.DisplayBlobHarta?iDokumen="+id_+"&tablename=hta";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}	

/*function seterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnya";
	doAjaxCall${formName}("");
}*/

/*function pilihTanah(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.htp.online.FrmPajakanPopupCarianSenaraiTanahView?idPermohonan="+idPermohonan;
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
}*/


function doHantar(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantar";
	document.${formName}.actionPajakan.value = "";
	doAjaxCall${formName}("");
}

function doHapus(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionPajakan.value = "";
	doAjaxCall${formName}("");
}

function doChangeTab(tabId) {
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	doAjaxCall${formName}("");
}

function KemaskiniFail(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.selectedTab.value = 0;
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}


/*function KemaskiniFail2(){
	
	document.${formName}.actionPajakan.value = "";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}*/


function batalKemaskiniFail(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function SimpanKemaskiniFail(){
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if (!window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTab.value = 0;
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.hitButton.value = "saveUpdateFail";
	doAjaxCall${formName}("");
}

function doBacklist() {
	alert('doBacklist');
	document.${formName}.actionPajakan.value = "";
	//document.${formName}.submit();
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function goBack() {
  window.history.back();
}


<!-- MAKLUMAT LAMPIRAN -->
/*function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.online.FrmPajakanOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionPajakan = "paparMaklumatPajakan";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}*/

function kemaskiniTanah() {
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("kemaskiniTanah");
}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

<!-- SENARAI SEMAK -->
function doSimpanKemaskiniSenaraiSemak(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}

function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function dokemaskiniSenarai() {
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	//document.${formName}.selectedTab.value = 0;
	document.${formName}.mode.value = "update";
	document.${formName}.submit();

}
</script>
$javascriptLampiran