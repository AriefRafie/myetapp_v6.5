<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input name="actionPerletakhakan" type="hidden" value="$actionPerletakhakan" id="actionPerletakhakan" />
<input name="idFail" type="hidden" value="$idFail" id="idFail"/>
<input name="idPermohonan" type="hidden" value="$idPermohonan" id="idPermohonan" />
<input name="idLetakhak" type="hidden" value="$idLetakhak" id="idLetakhak"/>
<input name="idBayaran" type="hidden" value="$idBayaran" id="idBayaran" />
<input name="idBorang30A" type="hidden" value="$idBorang30A" id="idBorang30A" />
<input name="hitButton" type="hidden" id="hitButton"/>
<input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
<input name="mode" type="hidden" value="$mode" id="mode" />

<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>#parse("app/htp/frmPerletakhakanHak.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    
      <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li onclick="doChangeTabUpper(0)" class="TabbedPanelsTab" tabindex="0">Akta/Warta Terlibat</li>
        <li onclick="doChangeTabUpper(1)" class="TabbedPanelsTab" tabindex="0">Perintah Mahkamah</li>
        <li onclick="doChangeTabUpper(2)" class="TabbedPanelsTab" tabindex="0">Bayaran</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        
        <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">No. Akta</td>
            <td width="1%">:</td>
            <td width="70%"><input name="txtNoAkta" type="text"  id="txtNoAkta" value="$txtNoAkta" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Tarikh Kuatkuasa</td>
            <td>:</td>
            <td>
                <input name="txdTarikhKuatkuasa" type="text" class="$inputTextClass" id="txdTarikhKuatkuasa" value="$txdTarikhKuatkuasa" size="10" $readonly onblur="check_date(this)"/>
                #if ($mode != 'view')
                <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>#end
            </td>
          </tr>
          <tr>
            <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tajuk Akta</td>
            <td valign="top">:</td>
            <td valign="top"><textarea name="txtTajukAkta" id="txtTajukAkta" $readonly class="$inputTextClass" cols="45" rows="5" onBlur="this.value=this.value.toUpperCase();">$txtTajukAkta </textarea></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
            
             #if ($mode == 'view')
             	<input type="button" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskini()" class="stylobutton"/>
               	<input type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali()" class="stylobutton"/>
               	<input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setSenaraiSurat('tableSurat')" class="stylobutton" />
               	
             #end
             #if ($mode == 'update')
             	<input type="button" name="btnSimpan" id="btnSimpan" value="Simpan" onclick="simpanAkta()" class="stylobutton"/>
               	<input type="button" name="btnCancel" id="btnCancel" value="Batal" onClick="batal()" class="stylobutton"/>
             #end
            </td>
          </tr>
        </table>
        <fieldset id="tableSurat" style="display:none;">
		<legend>SENARAI SURAT</legend>
		<table width="100%" border="0">
		  <tr>
		    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=16&idpermohonan=$idPermohonan','urusan','&template=HTPPerletakhakanSuratPermohonanPTD');" class="pautanms">SURAT PERMOHONAN LETAKHAK </a></td>
		  </tr>
		</table>
		</fieldset>
        
        </div>
        <div class="TabbedPanelsContent">
        
        <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">No. Saman Pemula</td>
            <td width="1%">:</td>
            <td width="70%"><input name="txtNoSamanPemula" type="text"  id="txtNoSamanPemula" value="$txtNoSamanPemula" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Nama Mahkamah</td>
            <td>:</td>
            <td><input name="txtNamaMahkamah" type="text" class="$inputTextClass"  id="txtNamaMahkamah"  onBlur="this.value=this.value.toUpperCase();" value="$txtNamaMahkamah" size="48" $readonly/>            </td>
          </tr>
          <tr>
            <td width="1%" >#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td >Tarikh Perintah</td>
            <td >:</td>
            <td >
            <input name="txdTarikhPerintah" type="text" class="$inputTextClass"  id="txdTarikhPerintah" value="$txdTarikhPerintah" size="10" $readonly onblur="check_date(this)"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('txdTarikhPerintah',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
            #end
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
            
             #if ($mode == 'view')
             	<input type="button" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskini()" class="stylobutton"/>
               	<input type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali()" class="stylobutton"/>
               	
             #end
             #if ($mode == 'update')
             	<input type="button" name="btnSimpan" id="btnSimpan" value="Simpan" onclick="simpanPerintah()" class="stylobutton"/>
               	<input type="button" name="btnCancel" id="btnCancel" value="Batal" onClick="batal()" class="stylobutton"/>
             #end
            </td>
          </tr>
        </table>

        </div>
        <div class="TabbedPanelsContent">
        
        	<fieldset>
            	<legend>BAYARAN PERLETAKHAKAN</legend>
                
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="50%" valign="top">
                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                        <td width="29%">Cara Bayar</td>
                        <td width="70%">: $selectCaraBayar</td>
                      </tr>
                      <tr>
                        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                        <td>No. Cek/WP/KW</td>
                        <td>: 
                        <input name="txtNoCek" type="text" id="txtNoCek" value="$txtNoCek" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
                      </tr>
                      <tr>
                        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
                        <td>Jumlah Bayaran (RM)</td>
                        <td>: 
                        <input name="txtJumlahBayar" type="text" id="txtJumlahBayar" value="$txtJumlahBayar" $readonly class="$inputTextClass" onblur="formatCurrencySemasa(this.value)"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>No. Resit</td>
                        <td>: 
                        <input name="txtNoResit" type="text" id="txtNoResit" value="$txtNoResit" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"/></td>
                      </tr>
                    </table>
                    </td>
                    <td width="50%" valign="top">
                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td width="1%">&nbsp;</td>
                        <td width="29%">Tarikh Terima</td>
                        <td width="70%">: <input name="txdTarikhTerima" type="text" class="$inputTextClass" id="txdTarikhTerima" value="$txdTarikhTerima" size="10" $readonly onblur="check_date(this)"/>
    
    #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>Tarikh Resit</td>
                        <td>: <input name="txdTarikhResit" type="text" class="$inputTextClass" id="txdTarikhResit" value="$txdTarikhResit" size="10" $readonly onblur="check_date(this)"/>
      #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhResit',false,'dmy');"><img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>Tarikh Hantar Resit</td>
                        <td>: <input name="txdHantarResit" type="text" class="$inputTextClass" id="txdHantarResit" value="$txdHantarResit" size="10" $readonly onblur="check_date(this)"/>
        
        #if ($mode != 'view')
        <a href="javascript:displayDatePicker('txdHantarResit',false,'dmy');"><img src="../img/calendar.gif" border="0"/></a>
        #end</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>Amaun Resit</td>
                        <td>: 
                        <input name="txtAmaunResit" type="text" id="txtAmaunResit" value="$txtAmaunResit" $readonly class="$inputTextClass" onblur="formatCurrencySemasa1(this.value)"/></td>
                      </tr>
                    </table>
                    
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2">&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="2"><fieldset>
                    <legend>BORANG 30A KTN</legend>
                    
                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td width="50%" valign="top">
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr>
                            <td width="1%">&nbsp;</td>
                            <td width="29%">Tarikh Terima Borang 30A</td>
                            <td width="70%">: <input name="txdTarikhTerimaBorang30A" type="text" class="$inputTextClass" id="txdTarikhTerimaBorang30A" value="$txdTarikhTerimaBorang30A" size="10" $readonly onblur="check_date(this)"/>
    
    
    #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhTerimaBorang30A',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>Tarikh Tandatangan PTP</td>
                            <td>: <input name="txdTarikhTandatanganPTP" type="text" class="$inputTextClass" id="txdTarikhTandatanganPTP" value="$txdTarikhTandatanganPTP" size="10" $readonly onblur="check_date(this)"/>
    
    
    #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhTandatanganPTP',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                          </tr>
                        </table></td>
                        <td width="50%" valign="top">
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr>
                            <td width="1%">&nbsp;</td>
                            <td width="29%">Tarikh Hantar ke PTG/PTD</td>
                            <td width="70%">: <input name="txdTarikhHantar" type="text" class="$inputTextClass" id="txdTarikhHantar" value="$txdTarikhHantar" size="10" $readonly onblur="check_date(this)"/>
    
    
    #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhHantar',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>Tarikh Daftar 30A</td>
                            <td>: <input name="txdTarikhDaftar30A" type="text" class="$inputTextClass" id="txdTarikhDaftar30A" value="$txdTarikhDaftar30A" size="10" $readonly onblur="check_date(this)"/>
      
      
      #if ($mode != 'view')
      <a href="javascript:displayDatePicker('txdTarikhDaftar30A',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a>
      #end</td>
                          </tr>
                        </table>
                        </td>
                      </tr>
                      <tr>
                    	<td colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center">
                        
                        #if ($mode == 'view')
                            <input type="button" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskini()" class="stylobutton"/>
                            <input type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali()" class="stylobutton"/>
                         #end
                         #if ($mode == 'update')
                            <input type="button" name="btnSimpan" id="btnSimpan" value="Simpan" onclick="simpanBayaran()" class="stylobutton"/>
                            <input type="button" name="btnCancel" id="btnCancel" value="Batal" onClick="batal()" class="stylobutton"/>
                         #end
                        </td>
                      </tr>
                    </table>

                    
                    </fieldset>
                    </td>
                  </tr>
                </table>

                
            </fieldset>

        
        </div>
      </div>
    </div>
    
    </td>
  </tr>
</table>

<script>
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>
  
<script>

</script>

