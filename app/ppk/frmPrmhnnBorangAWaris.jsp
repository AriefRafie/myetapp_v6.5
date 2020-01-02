<style type="text/css">
<!--    
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style38 {font-size: 10px}
.style42 {color: #0000FF;}
.style43 {font-family: Arial, Helvetica, sans-serif; color: #0000FF; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>

<body onLoad="submitForm()">
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="action">
<input type="hidden" name="hitButt">
<input type="hidden" name="mode">
<input type="hidden" name="idPermohonan" value="$IdPermohonan">
<input type="hidden" name="simpanStatus" value="$SimpanStatus">
<input type="hidden" name="idPemohon" value="$IdPemohon" />
<input type="hidden" name="idpermohonansimati" value="$idpermohonansimati" />
<input name="idSimati" type="hidden"  value="$idSimati"/>
<input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
<input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
<input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
<input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/> 
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
#end
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
<table width="100%" border="0" cellpadding="0" bordercolor="#000000">
<tr>
</tr>
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HAview('2','0','0','0')" >HARTA ALIH</li>
      #set ($hidden = "")
      #if ($hidden == "0")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      #end
      #if($hideTabPengesahan != "hide")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
      #end
   </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
           <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
            
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="WarisView('0','2','0','0')">WARIS</li>
           
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
         
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemiutangView('0','4','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PenghutangView('0','5','0','0')">PENGHUTANG</li>
            #set ($hidden = "")
      		#if ($hidden == "0")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SaksiView('0','4','0','0')">SAKSI</li>
            #end
            
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContentVisible"></div>
            
            <div class="TabbedPanelsContentVisible">
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
              
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContentVisible" ></div>
                   <div class="TabbedPanelsContentVisible" ></div>
                </div>
              </div>
            </div>
            <div class="TabbedPanelsContentVisible">
             <table width="98%" border="0" cellpadding="0">
                              #if($show_lapisan_berikut=="")
                             
                               #if($show_waris_update=="yes")
                               #foreach($lwu in $listWarisUpdate)
                               #set ($nokpbaru1 = $lwu.nokpbaru1)
                               #set ($nokpbaru2 = $lwu.nokpbaru2)
                               #set ($nokpbaru3 = $lwu.nokpbaru3)
                               #set ($nokplama = $lwu.nokplama)
                               #set ($jeniskp = $lwu.jeniskp)
                               #set ($nokplain = $lwu.nokplain)
                               #set ($nama_Ob = $lwu.nama_Ob)
                               #set ($umur = $lwu.umur)
                               #set ($agama = $lwu.agama)
                               #set ($saudara = $lwu.saudara)
                               #set ($statushidup = $lwu.statushidup)
                               #set ($tarikhmati = $lwu.tarikhmati)
                               #set ($waktumati = $lwu.waktumati)
                               #set ($alamat1 = $lwu.alamat1)
                               #set ($alamat2 = $lwu.alamat2)
                               #set ($alamat3 = $lwu.alamat3)
                               #set ($poskod = $lwu.poskod)
                               #set ($notel = $lwu.notel)
                               #set ($hp = $lwu.hp)
                               #set ($alamat1surat = $lwu.alamat1Surat)
                               #set ($alamat2surat = $lwu.alamat2Surat)
                               #set ($alamat3surat = $lwu.alamat3Surat)
                               #set ($poskodsurat = $lwu.poskodsurat)
                               
                              
     <tr>
    <td width="100%">
    <fieldset>
    <legend>MAKLUMAT WARIS</legend>
    <table width="100%" border="0">
      <tr>
        <td width="80%" valign="top">
        <table width="100%">
          <input type="hidden" name="idwarisup" value="$lwu.idwaris" />
          <input type="hidden" name="idparentlapis" value="$lwu.idwaris" />
          #set($idwarisup=$lwu.idwaris)
          <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
          <tr>
            <td width="29%"><div align="right"><span class="style38">MyID Baru</span></div></td>
            <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td width="70%" class="style36">
               <input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$lwu.nokpbaru1" $readmode size="5" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru1Waris')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris')"/>-<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$lwu.nokpbaru2" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru1Waris')"/>-<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris" style="width: 40px;" type="text" value="$lwu.nokpbaru3" $readmode size="3" maxlength="4" />            </td>
          </tr>
          <tr>
            <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
              <input name="txtNoKPLamaWaris" type="text" id="txtNoKPLamaWaris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;"  value="$!nokplama" size="8" maxlength="8" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" $readmode style="text-transform:uppercase; width: 128px;" onBlur="text-transform:uppercase;" >
								   #if ($jeniskp == "5")
              <option value="5" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" >Tentera</option>
              <option value="6" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" >Polis</option>
              <option value="4" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" >Pasport</option>
	                               #elseif($jeniskp=="6")
              <option value="6" style="text-transform:uppercase;" onBlur="uppercase()" >Polis</option>
              <option value="5" style="text-transform:uppercase;" onBlur="uppercase()" >Tentera</option>
              <option value="4" style="text-transform:uppercase;" onBlur="uppercase()" >Pasport</option>
								   #elseif($jeniskp=="3")
              <option value="4" style="text-transform:uppercase;" onBlur="uppercase()" >Pasport</option>
              <option value="5" style="text-transform:uppercase;" onBlur="uppercase()" >Tentera</option>
              <option value="6" style="text-transform:uppercase;" onBlur="uppercase()" >Polis</option>
	                               #else
              <option value="" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih KP</option>
              <option value="5" style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
              <option value="6" style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
              <option value="4" style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
	                               #end
            </select>&nbsp;<input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  style="text-transform:uppercase;" onBlur="uppercase()" value="$!nokplain" size="12" maxlength="12" $readmode />              </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Waris</span></div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><label>
              <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris" style="text-transform:uppercase;" onBlur="uppercase()" value="$!nama_Ob" size="34" maxlength="40" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Umur</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onBlur="Checkumur()" type="text" id="txtUmurWaris" value="$!umur" size="2" maxlength="3" $readmode onKeyUp="javascript:validatePoskod(this,this.value);" /></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Agama</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label>
              <select name="socAgamaWaris" id="socAgamaWaris" $readmode style="text-transform:uppercase;width: 225px;" onBlur="uppercase()">
                                   #if($agama=="1")
                <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
                <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
	                               #elseif($agama=="2")
               <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
                <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
	                               #else
                <option value="" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Agama</option>
                <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
                <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
	                               #end
              </select>
            </label></td>
          </tr>
          <tr>
            <td><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Talian Persaudaraan </div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            #foreach($listsau in $listsaudara)
	            #if($lwu.saudara==$listsau.id_Saudara)
		            #set($kodsaudara=$listsau.kod)
		            #set($kodsaudaraketerangan=$listsau.keterangan)
	            #end    
            #end
            <td> 
            
           #if( $saudara!="" || $lwu.saudara!="")
              <select name="socSaudaraWaris" $readmode style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                     <option value="$saudara" style="text-transform:uppercase;" onBlur="uppercase()">$!listsau.keterangan</option> 
                          #foreach($listsau in $listsaudara)                                
                                  	#if($lwu.saudara==$listsau.id_Saudara)
	                                 	
                <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()" selected>$!listsau.kod - $!listsau.keterangan</option>                                  	
                          			#end    
	             		  #end
                          #foreach($listsau in $listsaudara)
                          #if($lwu.saudara!=$saudara)
               <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()" selected>$!listsau.kod - $!listsau.keterangan</option>
                          #end
                          #end
                #foreach($listsau in $listsaudara)
                <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()">$!listsau.keterangan</option>
                                 
                       #end
              </select>
              #else
              <select name="socSaudaraWaris" $readmode  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()" >
                <option value="0">Sila Pilih Talian Persaudaraan</option>
                       #foreach($listsau in $listsaudara)
                <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()">$!listsau.keterangan</option>
                                 
                       #end
              </select>            </td>
            #end </tr>
          <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia </span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label> #if($statushidup=="1")
              #set($ch="checked")
              #else
              #set($ch="")
              #end
              <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $ch $readmode value="1" onKeyPress="setSelected(0,2,0,0);tarikh_waris_update()" onClick="setSelected(0,2,0,0);tarikh_waris_update()" />
            </label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Mati</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$!tarikhmati" size="10" maxlength="10" $readmode onBlur="trans_date1(this.value)"/>
            #if ($readmode=="")
                     <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
                     #end
                     &nbsp;<i>format : dd/mm/yyyy</i>                     </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Waktu Kematian</span></div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" onKeyUp="javascript:validatePoskod(this,this.value)" value="$!waktumati" size="4" maxlength="4" onBlur="validTarikhMati()" $readmode/>
              <span class="style44"><i>format : 12 jam (HHMM)</i></span></td>
          </tr>
          #end
          <tr>
            <td><div align="right"></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
              <span class="style44 style38"><i>
				
<input name="chkAddPemohonWaris" type="checkbox" id="chkAddPemohonWaris" value="1" onClick="getDuplicateAddressPemohonWaris()" $check4 $readmode>
              Alamat waris adalah alamat pemohon</i></span></td>
          </tr>
          <tr>
            <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Tetap</div></td>
            <td width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td width="70%"><label>
              <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onBlur="uppercase()" type="text" id="txtAlamatTerakhir1Waris" value="$!alamat1" size="34" maxlength="34"  $readmode/>
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td>&nbsp;</td>
            <td><label>
              <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onBlur="uppercase()" type="text" id="txtAlamatTerakhir2Waris"  value="$!alamat2" size="34" maxlength="34" $readmode />
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td>&nbsp;</td>
            <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onBlur="uppercase()" type="text" id="txtAlamatTerakhir3Waris" value="$!alamat3" size="34" maxlength="34" $readmode/></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><label>
              <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" style="text-transform:uppercase;" value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value);" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td>$selectNegeriTetap</td>
			</tr>
		  <tr>
            <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td>$selectBandarTetap</td>
          </tr>
          <tr>
            <td class="style38" valign="top"><div align="right" class="style38">No Telefon</div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onKeyUp="javascript:validatePoskod(this,this.value);" style="text-transform:uppercase;" onBlur="uppercase()" value="$!noTel" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52"><i>format : 031234567</i></span></td>
          </tr>
          <tr>
            <td class="style38" valign="top"><div align="right">No Telefon Bimbit </div></td>
            <td><div align="right"><span class="style38">:</span></div></td>
            <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" onKeyUp="javascript:validatePoskod(this,this.value);" value="$!nohp" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52"><i>format : 0121234567</i></span></td>
          </tr>
		  <tr>
			  <td class="style38" ><div align="right"></div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td><font class="style38"><input type="checkbox" value="1" name="chkAddWaris" onClick="getDuplicateAddress()" $readmode>&nbsp;<i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></font></td>
			</tr>
			<tr>
			  <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Surat Menyurat</div></td>
			  <td width="1%"><div align="right"><span class="style38">:</span></div></td>
			  <td width="70%"><label>
				<input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir1WarisSurat" value="$!alamat1surat" size="34" maxlength="40"  $readmode/>
			  </label></td>
			</tr>
			<tr>
			  <td class="style38"><div align="right"><span class="style7"></span></div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td><label>
				<input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$alamat2surat" size="34" maxlength="40" $readmode />
			  </label></td>
			</tr>
			<tr>
			  <td class="style38"><div align="right"><span class="style7"></span></div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir3WarisSurat" value="$alamat3surat" size="34" maxlength="40" $readmode/></td>
			</tr>
			<tr>
			  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td><label>
				<input name="txtPoskodWarisSurat" type="text" style="text-transform:uppercase;" id="txtPoskodWarisSurat" value="$!poskodsurat" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $readmode/>
			  </label></td>
			</tr>
            <tr>
			  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td>$selectNegeriSurat</td>
			  </tr>
			<tr>
			  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
			  <td><div align="right"><span class="style38">:</span></div></td>
			  <td>$selectBandarSurat</td>
			</tr>
			<tr>
			  <td class="style38">&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  </tr>
			<tr>
			  <td class="style38" colspan="3"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;                         </i></td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  </tr>
			 </table></td>
        <td width="20%" valign="top">
        </td>
      </tr>
	   <tr>
		 <td colspan="2" height="50px" valign="bottom"></td>
		</tr>
    </table>
    </fieldset>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" align="center">
      <tr>
        <td>
          <div align="center"> 
		  #if ( $idstatus=="150" || $idstatus=="171" )
				<input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris" onKeyPress="setSelected(0,2,0,0);tambah_waris()" onClick="setSelected(0,2,0,0);tambah_waris()"/>
				  
                  #if($buttonwaris=="Simpan" || $buttonwaris=="Kemaskini" )
					  #if($listWarisLapisanIdMatiDelete.size()==0)
					   <input type="button" name="tambahwarishapus" id="tambahwarishapus" value="Hapus" onClick="setSelected(0,2,0,0);hapus_waris()"/>
					   #end
				   #end
				##if(($show_lapisan_waris=="yes" || $ch=="checked" ) && $buttonwaris=="Kemaskini")
				#if(($ch=="checked" ) && $buttonwaris=="Kemaskini")
				<input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut" onKeyPress="setSelected(0,2,0,0);lapisan_waris()" onClick="setSelected(0,2,0,0);lapisan_waris()"/>
				#end
		 #end
            <input type="button" name="cmdKembali8" id="cmdKembali8" value="Kembali"  onclick="WarisView('0','2','0','0')"/>
          </div></td>
      </tr>
    </table></td>
  </tr>
                               #end
                               
                               #end
                               #if($show_table_waris=="yes")
	
                               <tr>
                              <td width="100%">
                              <fieldset>
                        <legend>MAKLUMAT WARIS </legend>
                        <table width="100%" border="0">
                                  <tr>
                                    <td width="80%" valign="top">
                                    <table width="100%">
                                       <input type="hidden" name="txtIdSimatiWaris" value="$idSimati" />
                                        <tr>
                                          <td width="29%"><div align="right"><span class="style38">MyID Baru</span></div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td width="70%"><input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" type="text" value="$!nokpbaru1" $readmode size="5" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris')"/>-<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" type="text" value="$!nokpbaru2" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris')"/>-<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris" type="text" value="$!nokpbaru3" $readmode size="3" maxlength="4"/>                                        </td>
                                      </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
                                          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoKPLamaWaris" style="text-transform:uppercase;" type="text" id="txtNoKPLamaWaris" value="$!nokpwaris" size="7" maxlength="8" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                                          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" $readmode style="text-transform:uppercase; width: 128px;">
								  			#if($jenisKp=="5")
                                            <option value="5" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Tentera</option>
                                              <option value="6" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Polis</option>
                                              <option value="4" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Pasport</option>
	                               			#elseif($jenisKp=="6")
                                            <option value="6" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Polis</option>
                                              <option value="5" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Tentera</option>
                                              <option value="4" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Pasport</option>
								   			#elseif($jenisKp=="3")
                                            <option value="4" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Pasport</option>
                                              <option value="5" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Tentera</option>
                                              <option value="6" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Polis</option>
	                               			#else
                                            <option value="" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Sila Pilih KP</option>
                                              <option value="5" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Tentera</option>
                                              <option value="6" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Polis</option>
                                              <option value="4" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Pasport</option>
	                               			#end
                                          </select>&nbsp;<input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  style="text-transform:uppercase;" value="$!nokplain" size="12" maxlength="12" $readmode /></td>
                                        </tr>
                                        
                                        <tr>
                                          <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Waris</span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>
                                            <input name="txtNamaOBWaris" type="text" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" id="txtNamaOBWaris" value="$!namaOB" size="34" $readmode/>										  </td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Umur</span></div></td>
                                          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onBlur="Checkumur()" type="text" id="txtUmurWaris" value="$!umur" size="3" maxlength="3" onKeyUp="javascript:validateIC(this,this.value,'txtUmurWaris')" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Agama</span></div></td>
                                          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socAgamaWaris" id="socAgamaWaris" $readmode style="text-transform:uppercase;width: 225px;" onBlur="text-transform:uppercase;">
                                   #if($agama=="1")
                                              <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Islam</option >
                                              <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Bukan Islam</option>
	                               #elseif($agama=="2")
                                              <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Bukan Islam</option>
                                              <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Islam</option>
	                               #else
                                              <option value="" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Sila Pilih Agama</option>
                                              <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Islam</option>
                                              <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Bukan Islam</option>
	                               #end
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Talian Persaudaraan</span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          #foreach($listsau in $listsaudara)
                                          #if($saudara==$listsau.id_Saudara)
                                          #set($kodsaudara=$listsau.kod)
                                          #set($kodsaudaraketerangan=$listsau.keterangan)
                                          #end    
                                          #end
                                          <td> #if($saudara!="")
                                            <select name="socSaudaraWaris" $readmode style="text-transform:uppercase; width: 225px;" onBlur="text-transform:uppercase;" >
                                                <option value="$saudara" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">$!kodsaudaraketerangan</option>
					                                  #foreach($listsau in $listsaudara)
					                                  #if($saudara!=$listsau.id_Saudara)
					                                     #if($jantina=="1")
					                                          #set($jan="01")
					                                          #end
					                                          #if($jantina=="2")
					                                          #set($jan="02")
					                                          #end                                  
					                                         
					                                          #if($listsau.jantina==$jan)
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="text-transform:uppercase;"> $!listsau.keterangan</option>
							                                   #end
							                                  #end    
								                               #end
                                            </select>
                                            #else
                                            <select name="socSaudaraWaris" $readmode style="text-transform:uppercase; width: 225px;" onBlur="text-transform:uppercase;">
                                              <option value="" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">Sila Pilih Talian Persaudaraan</option>
							                                  #foreach($listsau in $listsaudara)
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="text-transform:uppercase;"> $!listsau.keterangan</option>
  						                               		 #end
                                            </select>                                          </td>
                                          #end </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                                          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td class="style36"><label> #if($checkmati=="1")
                                            #set($chq="checked")
                                            #else
                                            #set($chq="")
                                            #end
                                            <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq value="$!checkmati" onKeyPress="setSelected(0,2,0,0);tarikh_waris()" onClick="setSelected(0,2,0,0);tarikh_waris()"/>
                                          </label></td>
                                        </tr>
                                      #if($show_tarikh=="yes")
                                      <tr>
                                        <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Mati</span></div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$!tarikhmati" size="10" maxlength="10" $readmode onBlur="trans_date(this.value)"/>
                     					<a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/>&nbsp;<i>format : dd/mm/yyyy</i></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Waktu Kematian</span></div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris9" style="text-transform:uppercase;" value="$!waktumatiwaris" size="4" maxlength="4" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validTarikhMati()"/>
                                        <span class="style44"><i>format 12 jam (HHMM)</i></span></td>
                                      </tr>
                                      #end
                                       <tr>
            <td><div align="right"></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36">
              <span class="style44 style38"><i>
              <input type="checkbox" name="chkAddPemohonWaris" id="chkAddPemohonWaris" value="1" onClick="getDuplicateAddressPemohonWaris()" $check4 $readmode>
              Alamat waris adalah alamat pemohon</i></span></td>
          </tr>
                                      <tr>
                                          <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Tetap</div></td>
                                          <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                          <td width="70%">
                                            <input name="txtAlamatTerakhir1Waris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir1Waris" value="$!alamat1" size="34" maxlength="40"  $readmode/>                                          </td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir2Waris"  value="$!alamat2" size="34" maxlength="40" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir3Waris" value="$!alamat3" size="34" maxlength="40" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><label>
                                            <input name="txtPoskodWaris" type="text" style="text-transform:uppercase;" id="txtPoskodWaris" value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $readmode/>
                                          </label></td>
                                        </tr>
										
                                        <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>$!selectNegeriTetap</td>
										  </tr>
										  <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>$!selectBandarTetap</td>
                                        </tr>
                                        <tr>
                                          <td class="style38" ><div align="right" class="style38">No Telefon</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" onKeyUp="javascript:validatePoskod(this,this.value);"  style="text-transform:uppercase;" onBlur="text-transform:uppercase;" value="$!notel" size="14" maxlength="14" $readmode />&nbsp;<span class="style52"><i>format : 031234567</i></span></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" ><div align="right">No Telefon Bimbit</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" value="$!hp" size="14" maxlength="12" $readmode onKeyUp="javascript:validatePoskod(this,this.value);"/>&nbsp;<span class="style52"><i>format  : 0121234567</i></span></td>
                                        </tr>
										<tr>
                                          <td class="style38" ><div align="right"></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><font class="style38"><input type="checkbox" value="1" name="chkAddWaris" onClick="getDuplicateAddress()" $check3>&nbsp;<i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></font></td>
                                        </tr>
										<tr>
                                          <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Surat Menyurat</div></td>
                                          <td width="1%"><div align="right"><span class="style38">:</span></div></td>
                                          <td width="70%"><label>
                                            <input name="txtAlamatTerakhir1WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir1WarisSurat" value="$!alamat1surat" size="34" maxlength="40"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir2WarisSurat"  value="$!alamat2surat" size="34" maxlength="40" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onBlur="text-transform:uppercase;" type="text" id="txtAlamatTerakhir3WarisSurat" value="$!alamat3surat" size="34" maxlength="40" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td><label>
                                            <input name="txtPoskodWarisSurat" type="text" style="text-transform:uppercase;" id="txtPoskodWarisSurat" value="$!poskodsurat" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $readmode/>
                                          </label></td>
                                        </tr>
										
                                        <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>$!selectNegeriSurat </td>
										  </tr>
										  <tr>
                                          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                          <td>$!selectBandarSurat</td>
                                        </tr>
										  <tr>
										    <td class="style38">&nbsp;</td>
										    <td>&nbsp;</td>
										    <td>&nbsp;</td>
								      </tr>
										  <tr>
										    <td class="style38" colspan="3"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i></td>
										    <td>&nbsp;</td>
										    <td>&nbsp;</td>
								      </tr>
                                    </table></td>
                                  <td width="40%" valign="top"></td>
                                  </tr>
								  
                              </table>
                              </fieldset>
                              </td>
                               </tr>
                            <tr>
                              <td><table width="100%" border="0" align="center">
                                  <tr>
							 #if ($idstatus=="150" || $idstatus=="171")
                                  #if($buttonwarisSimpan!="")
                                    <td align="center"><input type="button" name="tambahwarisSimpan" id="tambahwarisSimpan" value="$buttonwarisSimpan"  onclick="setSelected(0,2,0,0);tambah_waris_Simpan()"/><input type="reset" name="cmdBatal" value="Batal"></td>
                                   #end 
                                    #if($show_batal_waris=="yes")
                                    <td align="center">
                                      <input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onClick="cancelwaris()"/><input type="button" name="cmdTambah" id="cmdTambah" value="Tambah"  onclick="WarisView('0','2','0','0')"/>
                                    </td>
                                    #end
							 #end
                                    </tr>
                              </table></td>
                            </tr>
                               #end     
  <tr>
    <td>
     <fieldset>
<legend>SENARAI WARIS LAPISAN PERTAMA</legend>
    
    <table width="100%">
    </table>
        <table width="100%" >
          <tr >
            <td width="100%"><div align="center">
                <table width="100%">
                  <tr class="table_header">
                  <td width="5%"><div align="center" >NO</div></td>
                    <td width="20%"><div align="center" >NAMA WARIS</div></td>
                    <td width="10%"><div align="center">NO KP BARU</div></td>
                    <td width="5%"><div align="center">UMUR</div></td>
                    <td width="20%"><div align="center">TALIAN PERSAUDARAAN</div></td>
                    <td width="15%"><div align="center">STATUS</div></td>
                     <td width="8%"><div align="center">LAPISAN</div></td>
                  </tr>
            </table>      
               
                  #if($listWaris.size()==0)
                  <table width="100%">
                   <tr bgcolor="white">
                   <td align="left">
                   Tiada Rekod                   </td>
                  </tr>
                  </table>
                  #else
                   <input name="idwaris" type="hidden" id="idwaris" value="$idwaris" /> 
                   <table width="100%">
                   
                   #set($nowa=0)
                  #foreach($listwaris in $listWaris)
                 
                 #set($nowa=$nowa+1)
                 #if($nowa%2!=0)
                  <tr >
                  
                    <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                    <td width="20%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style42"> $listwaris.nama_Ob</a></div></td>
                    <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
                    <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
                   
                    #if($listwaris.saudara=="")
                    #set($wariskodsaudara="")
                    #set($warissaudaraketerangan="" )
                    #else
                    
                    
                    #foreach($listsaudaralist in $listsaudara)
                
                    #if($listwaris.saudara==$listsaudaralist.id_Saudara)
                    
                    #set($wariskodsaudara=$listsaudaralist.kod)
                    #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                   
                   
                    
                    #end    
                    #end
                    #end
                    <td width="20%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meninggal Dunia")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="15%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                     <td width="8%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                  </tr>
                  #else
                  
                   <tr class="table_header">
                    <!-- 
                                             <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
                                             -->
                    <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                    <td width="20%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$listwaris.idwaris')" class="style43"> $listwaris.nama_Ob</a></div></td>
                    <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.nokpbaru</div></td>
                    <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.umur</div></td>
                    #foreach($listsaudaralist in $listsaudara)
                    
                    #if($listwaris.saudara==$listsaudaralist.id_Saudara)
                    
                    #set($wariskodsaudara=$listsaudaralist.kod)
                    #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                    
                    
                    #end    
                    #end
                    <td width="20%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meninggal Dunia")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="15%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                     <td width="8%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwaris.lapis</div></td>
                  </tr>
                  #end
                   #end
                    </table>
                 
                  #end
              
            </div></td>
          </tr>
      </table>
      </fieldset>
      </td>
      
      #end      </tr>
  
  #if($show_lapisan_bawah=="yes")
    <tr>
      <td>
      
        <fieldset>
<legend>SENARAI WARIS LAPISAN BERIKUT</legend>
      <table width="700">
        #set($idwww=$idparent)
       <input type="hidden" name="idwarislapisX" value="$idwww" />
        <input type="hidden" name="idparentlapisX"/>
        
        </table>
          <div align="center">
            <table width="100%">
              <tr class="table_header">
               <td width="3%"><div align="center">NO</div></td>
                <td width="20%"><div align="center">NAMA WARIS</div></td>
                <td width="10%"><div align="center">NO KP BARU</div></td>
                <td width="15%"><div align="center">TALIAN PERSAUDARAAN</div></td>
                <td width="20%"><div align="center">NAMA WARIS YANG MENINGGAL</div></td>
                <td width="16%"><div align="center">STATUS</div></td>
                <td width="8%"><div align="center">LAPISAN</div></td>
              </tr>
              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
            </table>
            #if($listWarisLapisanIdMati.size()==0)
            <table width="100%">
              <tr bgcolor="white">
                <td align="left"> Tiada Rekod </td>
              </tr>
            </table>
            #else
            <table width="100%">
            #set($nowar=0)
              #foreach($listwarislapisan in $listWarisLapisanIdMati)
               
                 #set($nowar=$nowar+1)
                 #if($nowar%2!=0)
              <tr >
              <td width="3%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                 #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal Dunia")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="16%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="8%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              
              #else
               <tr class="table_header">
              <td width="3%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$nowar</div></td>
            <td width="20%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
                 #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meninggal Dunia")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="16%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                <td width="8%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
              </tr>
              #end
              #end
            </table>
            #end </div>
            </fieldset>
            </td>
    </tr>
 #end 
   #if($show_lapisan_berikut=="yes")
    #if($show_lapisan_berikut_tambah=="yes")
    <tr>
    <td>
        <fieldset>
        <legend>MAKLUMAT WARIS LAPISAN BERIKUT</legend>
        
        <table width="100%" border="0">
        
        <tr>
          <td width="80%" valign="top"><table width="100%">
          <input type="hidden" name="txtIdSimatiWaris" value="$id_Simati" />
          <input type="hidden" name="idparentlapiss" value="$idparentlapis" />
              <tr>
                <td width="35%"><div align="right"><span class="style38">Waris Yang Meninggal</span></div></td>
                  <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36" width="70%">
                     <span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
              
                 <input name="txtIdParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                   #set($ip=$listWarisParentlist.idwaris)
                    #set($lp=$listWarisParentlist.lapis)
               
                  <input name="txtLapisParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
            
                #end</tr>
              
              <tr>
                <td width="35%"><div align="right"><span class="style38">MyID Baru</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td width="70%" class="style36">
		           <input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Warisx" style="width: 50px;" type="text" value="$nokpbaru1" $readmode size="6" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Warisx')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris')"/>-<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Warisx" style="width: 20px;" type="text" value="$nokpbaru2" $readmode size="2" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Warisx')"/>-<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Warisx"  style="width: 40px;" type="text" value="$nokpbaru3" $readmode size="4" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Warisx')"/>                </td>
              </tr>
              <tr>
                <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <input name="txtNoKPLamaWaris" type="text" id="textfield33"  style="text-transform:uppercase;" value="$nokpwaris" size="9" maxlength="9" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" $readmode  style="text-transform:uppercase; width: 128px;">
								   #if($jenisKp=="5")
                  <option value="5"  style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
                    <option value="4"  style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
	                               #elseif($jenisKp=="6")
                  <option value="6"  style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
                    <option value="5"  style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
                    <option value="4"  style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
								   #elseif($jenisKp=="4")
                  <option value="4"  style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
                    <option value="5"  style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
	                               #else
                  <option value=""  style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih KP</option>
                    <option value="5"  style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
                    <option value="6"  style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
                    <option value="4"  style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
	                               #end
                </select>&nbsp;<input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$nokplain" size="12" maxlength="12" $readmode  style="text-transform:uppercase;" onBlur="getExistLapisan()"/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Waris</span></div></td>
                <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris2" value="$namaOB" size="34" maxlength="40" $readmode  style="text-transform:uppercase;" onBlur="uppercase()" />
                </label></td>
              </tr>
                <tr>
                <td><div align="right"><span class="style38">Umur</span></div></td>
                
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                
                <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris"  onkeyup="javascript:validateIC(this,this.value,'txtUmurWaris')" style="text-transform:uppercase;" onBlur="Checkumur()" value="$umur" size="3" maxlength="3" $readmode/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Agama</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label>
                  <select name="socAgamaWaris" id="socAgamaWaris" $readmode  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                   #if($agama=="1")
                    <option value="1"  style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
                    <option value="2"  style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
	                               #elseif($agama=="2")
                    <option value="2"  style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
                    <option value="1"  style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
	                               #else
                    <option value=""  style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Agama</option>
                    <option value="1"  style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
                    <option value="2"  style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
	                               #end
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tali Persaudaraan</span></div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                #foreach($listsau in $listsaudara)
                
                #if($$lwu.saudara==$listsau.id_Saudara)
                
                #set($kodsaudara=$listsau.kod)
                #set($kodsaudaraketerangan=$listsau.keterangan)
                
                
                
                #end    
                #end
                <td> #if($saudara!="")
                  <select name="socSaudaraWaris" $readmode id="socSaudaraWaris"  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                      <option value="$saudara"  style="text-transform:uppercase;" onBlur="uppercase()">$kodsaudara - $kodsaudaraketerangan</option>
                                           #foreach($listsau in $listsaudara)
                                  #if($$lwu.saudara!=$listsau.id_Saudara)
                                     #if($jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                         
                                          #if($listsau.jantina==$jan)
                    <option value="$listsau.id_Saudara"  style="text-transform:uppercase;" onBlur="uppercase()">$listsau.kod - $listsau.keterangan</option>
                                     #end         
                                  #end    
	                               #end
                  </select>
                  #else
                  <select name="socSaudaraWaris" $readmode id="socSaudaraWaris"  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                    <option value=""  style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Talian Persaudaraan</option>
                                  #foreach($listsau in $listsaudara)
                    <option value="$listsau.id_Saudara"  style="text-transform:uppercase;" onBlur="uppercase()">$listsau.kod - $listsau.keterangan</option>
	                               #end
                  </select>                </td>
                #end </tr>
              <tr>
                <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td class="style36"><label> 
                #if($checkmati=="1")
                  #set($chq="checked")
                  #else
                  #set($chq="")
                  #end
                  <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq value="$checkmati" onKeyPress="setSelected(0,2,0,0);tarikh_waris_lapisan()" onClick="setSelected(0,2,0,0);tarikh_waris_lapisan()" />
                </label></td>
              </tr>
            #if($show_tarikh=="yes")
            <tr>
              <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Mati</span></div></td>
              <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
              <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$tarikhmati" size="10" $readmode onBlur="trans_date(this.value)"/>&nbsp;
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp;&nbsp;<i>format : dd/mm/yyyy</i></td>
            </tr>
            <tr>
              <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Waktu Kematian</span></div></td>
               <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
              <td class="style36"><input name="txtWaktuKematianWaris" type="text"  style="text-transform:uppercase;" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validTarikhMati()" id="txtWaktuKematianWaris" value="$waktumatiwaris" size="4" maxlength="4" $readmode  />
                <span class="style44"><i>format 12 jam (HHMM)</i></span></td>
            </tr>
            #end
             <tr>
            <td><div align="right"></td>
            <td class="style36"><div align="right"><span class="style38"></span></div></td>
            <td class="style36">
              <span class="style44 style38"><i>
              <input name="chkAddPemohonWarisLapis" type="checkbox" id="chkAddPemohonWarisLapis" value="1" onClick="getDuplicateAddressPemohonWarisLapis()" $readmode>
              Alamat waris adalah alamat pemohon</i></span></td>
          </tr>
            <tr>
                <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Tetap</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td width="70%"><label>
                  <input name="txtAlamatTerakhir1Waris" type="text"  style="text-transform:uppercase;" onBlur="uppercase()" id="txtAlamatTerakhir1Waris" value="$!alamat1" size="34"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtAlamatTerakhir2Waris" type="text"  style="text-transform:uppercase;" onBlur="uppercase()" id="txtAlamatTerakhir2Waris"  value="$!alamat2" size="34" $readmode />
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris"  style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat3" size="34" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtPoskodWaris" type="text" id="txtPoskodWaris"  onKeyUp="javascript:validatePoskod(this,this.value);" style="text-transform:uppercase;" onBlur="validLength()" value="$poskod" size="5" maxlength="5" $readmode/>
                </label></td>
              </tr>
              
              <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <!--#foreach($listnegpomo in $listnegeri)
                
                #if($negeri==$listnegpomo.id_Negeri)
                
                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                
                
                
                #end 
                #end-->
                <td><!-- #if($negeri!="")
                  <select name="socNegeriWaris" $readmode id="socNegeriWaris"  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                      <option value="$!negeri"  style="text-transform:uppercase;" onBlur="uppercase()">$!negerikodpemoP - $!negeriketeranganpemoP</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onBlur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
     
                                   
                                  #end    
	                               #end
      
                  </select>
                  #else
                  <select name="socNegeriWaris" $readmode id="socNegeriWaris"  style="text-transform:uppercase;width: 225px;" onBlur="uppercase()">
                    <option value=""  style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Negeri</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              
                    <option value="$!listnegpomo.id_Negeri"  style="text-transform:uppercase;" onBlur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select> #end  -->$selectNegeriTetap            </td>
                 </tr>
                 <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><!--<label>
                  <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$bandar"  style="text-transform:uppercase;" onBlur="uppercase()" size="34" maxlength="40" $readmode/>
                </label>-->$selectBandarTetap</td>
              </tr>
               <tr>
                <td class="style38" ><div align="right" class="style38">No Telefon</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtNoTeleponWaris"  style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validateIC(this,this.value,'txtNoTeleponWaris')"  type="text" id="txtNoTeleponWaris" value="$!notel" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52">cth: 031234567</span></td>
              </tr>
              <tr>
                <td class="style38" ><div align="right">No Telefon Bimbit</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtNoTeleponBimbitWaris"  style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validateIC(this,this.value,'txtNoTeleponBimbitWaris')" type="text" id="txtNoTeleponBimbitWaris" value="$!hp" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52">cth: 0121234567</span></td>
              </tr>
				<!--Alamat Surat Menyurat-->
				<tr>
				  <td class="style38" ><div align="right"></div></td>
				  <td><div align="right"><span class="style38">:</span></div></td>
				  <td><font class="style38"><input type="checkbox" value="1" name="chkAddWarisWaris" onClick="javascript:getDuplicateAddressWaris();">
				  &nbsp;<i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></font></td>
				</tr>
				<tr>
                <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Surat Menyurat</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td width="70%"><label>
                  <input name="txtAlamatTerakhir1WarisSurat" type="text"  style="text-transform:uppercase;" onBlur="uppercase()" id="txtAlamatTerakhir1WarisSurat" value="$!alamat1surat" size="34"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtAlamatTerakhir2WarisSurat" type="text"  style="text-transform:uppercase;" onBlur="uppercase()" id="txtAlamatTerakhir2WarisSurat"  value="$!alamat2surat" size="34" $readmode />
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><div align="right"><span class="style38">:</span></div></td>
                <td><input name="txtAlamatTerakhir3WarisSurat" type="text" id="txtAlamatTerakhir3WarisSurat"  style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat3surat" size="34" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><label>
                  <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat"  onKeyUp="javascript:validatePoskod(this,this.value);" style="text-transform:uppercase;" onBlur="validLength()" value="$!poskodsurat" size="5" maxlength="5" $readmode/>
                </label></td>
              </tr>
              
              <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <!--#foreach($listnegpomo in $listnegeri)
                
                #if($negeri==$listnegpomo.id_Negeri)
                
                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                
                
                
                #end 
                #end-->
                <td> <!--#if($negeri!="")
                  <select name="socNegeriWaris" $readmode id="socNegeriWaris"  style="text-transform:uppercase; width: 225px;" onblur="uppercase()">
                      <option value="$!negeri"  style="text-transform:uppercase;" onblur="uppercase()">$!negerikodpemoP - $!negeriketeranganpemoP</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
     
                                   
                                  #end    
	                               #end
      
                  </select>
                  #else
                  <select name="socNegeriWaris" $readmode id="socNegeriWaris"  style="text-transform:uppercase;width: 225px;" onblur="uppercase()">
                    <option value=""  style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              
                    <option value="$!listnegpomo.id_Negeri"  style="text-transform:uppercase;" onblur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>#end  -->$selectNegeriSurat</td>
                 </tr>
                 <tr>
                <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                 <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                <td><!--<label>
                  <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$bandar"  style="text-transform:uppercase;" onblur="uppercase()" size="34" maxlength="40" $readmode/>
                </label>-->$selectBandarSurat</td>
              </tr>
                 <tr>
                   <td class="style38">&nbsp;</td>
                   <td class="style36">&nbsp;</td>
                   <td>&nbsp;</td>
                 </tr>
                 <tr>
                   <td class="style38" colspan="3"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i></td>
                   <td class="style36">&nbsp;</td>
                   <td>&nbsp;</td>
                 </tr>
             
          </table></td>
          <td width="20%" valign="top"></td>
        </tr>
        
        </table>
        </fieldset>
    </td>
  </tr>
  
  #end
  
  
  #if($show_lapisan_berikut_update=="yes")
  #foreach($lwu in $listWarisLapisanUpdate)
    #set ($nokpbaru1 = $lwu.nokpbaru1)
    #set ($nokpbaru2 = $lwu.nokpbaru2)
    #set ($nokpbaru3 = $lwu.nokpbaru3)
    #set ($nokplama = $lwu.nokplama)
    #set ($jeniskp = $lwu.jeniskp)
    #set ($nokplain = $lwu.nokplain)
    #set ($nama_Ob = $lwu.nama_Ob)
    #set ($umur = $lwu.umur)
    #set ($agama = $lwu.agama)
    #set ($saudara = $lwu.saudara)
    #set ($statushidup = $lwu.statushidup)
    #set ($tarikhmati = $lwu.tarikhmati)
    #set ($waktumati = $lwu.waktumati)
    #set ($alamat1 = $lwu.alamat1)
    #set ($alamat2 = $lwu.alamat2)
    #set ($alamat3 = $lwu.alamat3)
    #set ($poskod = $lwu.poskod)
    #set ($notel = $lwu.notel)
    #set ($hp = $lwu.hp)
    #set ($alamat1surat = $lwu.alamat1Surat)
    #set ($alamat2surat = $lwu.alamat2Surat)
    #set ($alamat3surat = $lwu.alamat3Surat)
    #set ($poskodsurat = $lwu.poskodsurat)
  
<tr>
  <td>
  <fieldset>
  <legend>MAKLUMAT WARIS LAPISAN BERIKUT</legend>
  
  <table width="100%" border="0">
    <tr>
      <td width="80%" valign="top">
      <table width="100%">
      <!--
       idwarisup2 :: <input type="text" name="idwarisup" value="$lwu.idwaris" />
       -->
       #set($idwarisup=$lwu.idwaris)
        <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
        <input type="hidden" name="idparentlapiss" value="$idparentlapis" />
        <tr>
          <td width="35%"><div align="right"><span class="style38">Waris Yang Meninggal</span></div></td>
         
                  <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36" width="70%">
                   <span class="style42" style="text-transform:uppercase;" onblur="uppercase()">$listWarisParentlist.nama_Ob</span>
             <input name="txtIdParent" type="hidden"  id="txtIdParent" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>  
          <input name="txtLapisParent" type="hidden"  id="txtLapisParent" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
                    #set($ip=$listWarisParentlist.idwaris)
                    #set($lp=$listWarisParentlist.lapis)
                #end        </tr>
        
        <tr>
          <td width="35%"><div align="right"><span class="style38">MyID Baru</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td width="70%" class="style36">
          
          
           <input name="txtNoKPBaru1Waris" id="txtNoKPBaru1Waris" style="width: 50px;" type="text" value="$lwu.nokpbaru1" $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Waris')" onBlur="getAgeByIC(this,this.value,'txtUmurWaris')"/>
           -<input name="txtNoKPBaru2Waris" id="txtNoKPBaru2Waris" style="width: 20px;" type="text" value="$lwu.nokpbaru2" $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris')"/>
           -<input name="txtNoKPBaru3Waris" id="txtNoKPBaru3Waris"  style="width: 40px;" type="text" value="$lwu.nokpbaru3" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Waris')"/>         </td>
        </tr>
        <tr>
          <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <input name="txtNoKPLamaWaris" type="text" style="text-transform:uppercase;" onBlur="uppercase()" id="textfield41" value="$lwu.nokplama" size="9" maxlength="9" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" $readmode style="text-transform:uppercase;width: 146px;" onBlur="uppercase()">
								   #if($lwu.jeniskp=="5")
            <option value="5" style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
            <option value="6" style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
            <option value="4" style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
	                               #elseif($lwu.jeniskp=="6")
            <option value="6" style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
            <option value="5" style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
            <option value="4" style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
								   #elseif($lwu.jeniskp=="4")
            <option value="4" style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
            <option value="5" style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
            <option value="6" style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
	                               #else
            <option value="" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih KP</option>
            <option value="5" style="text-transform:uppercase;" onBlur="uppercase()">Tentera</option>
            <option value="6" style="text-transform:uppercase;" onBlur="uppercase()">Polis</option>
            <option value="4" style="text-transform:uppercase;" onBlur="uppercase()">Pasport</option>
	                               #end
          </select>&nbsp;<input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris4"  style="text-transform:uppercase;" onBlur="uppercase()" value="$lwu.nokplain" size="9" maxlength="9" $readmode />           </td>
        </tr>
      <tr>
          <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Waris</span></div></td>
          <td class="style36"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris3" style="text-transform:uppercase;" onBlur="uppercase()" value="$lwu.nama_Ob" size="34" maxlength="40" $readmode/>
          </label></td>
        </tr>
 		<tr>
          <td><div align="right"><span class="style38">Umur</span></div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><input name="txtUmurWaris" style="text-transform:uppercase;" onBlur="Checkumur()" type="text" id="txtUmurWaris" onKeyUp="javascript:validateIC(this,this.value,'txtUmurWaris')" value="$lwu.umur" size="3" maxlength="3" $readmode/></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Agama</span></div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td class="style36"><label>
            <select name="socAgamaWaris" id="socAgamaWaris" $readmode style="text-transform:uppercase;width: 225px;" onBlur="uppercase()">
              
                
                            
                                      
                                   #if($lwu.agama=="1")
	                               
	                               
                                      
                                              
                                             
                
              
              <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
              <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.agama=="2")
	                               
                                      
                                              
                                             
                
              <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
              <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
              
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                
              <option value="" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Agama</option>
              <option value="1" style="text-transform:uppercase;" onBlur="uppercase()">Islam</option>
              <option value="2" style="text-transform:uppercase;" onBlur="uppercase()">Bukan Islam</option>
              
                
                                             
                                              
                                      
	                               #end
                                      
                                    
                                                    
                                           
              
            </select>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">x*</font>&nbsp;Tali Persaudaraan</span></div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          #foreach($listsau in $listsaudara)
          
          #if($lwu.saudara==$listsau.id_Saudara)
          
          #set($kodsaudara=$listsau.kod)
          #set($kodsaudaraketerangan=$listsau.keterangan)
           #end    
          #end
          <td> #if($lwu.saudara!="")
            <select name="socSaudaraWaris" $readmode id="socSaudaraWaris" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                    <option value="$lwu.saudara">$kodsaudara - $kodsaudaraketerangan</option>
                                   #foreach($listsau in $listsaudara)
                                          #if($lwu.jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($lwu.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                        
                                 
                                  #if($lwu.saudara!=$listsau.id_Saudara)
                                    
	                                 #if($lwu.jantina==$jan)
               <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()">$listsau.kod - $listsau.keterangan</option>
              
                                    #end
                                   
                                  #end    
	                               #end
             </select>
            #else
            <select name="socSaudaraWaris" class="largeselect" $readmode id="socSaudaraWaris" style="text-transform:uppercase;" onBlur="uppercase()">
              <option value="0" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Talian Persaudaraan</option>
              
                
                                  #foreach($listsau in $listsaudara)
                                 
                                
                                   #if($lwu.jantina=="1")
                                          #set($jan="01")
                                          #end
                                          #if($lwu.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                        
                                          #if($listsau.jantina==$jan)
               <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onBlur="uppercase()">$listsau.kod - $listsau.keterangan</option>
                #end
	                               #end
            </select>          </td>
          #end </tr>
           <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia</span></div></td>
             <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><label> #if($lwu.statushidup=="1")
              #set($ch="checked")
              #else
              #set($ch="")
              #end
              <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $ch $readmode value="$checkmati" onKeyPress="setSelected(0,2,0,0);tarikh_waris_lapisan_update()" onClick="setSelected(0,2,0,0);tarikh_waris_lapisan_update()" />
            </label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Mati</span></div></td>
             <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$lwu.tarikhmati" size="10" $readmode onBlur="trans_date(this.value)"/>&nbsp;
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp;&nbsp;<i>format : dd/mm/yyyy</i></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Waktu Kematian</span></div></td>
              <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
            <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" onKeyUp="javascript:validatePoskod(this,this.value)"  style="text-transform:uppercase;" onBlur="validTarikhMati()" value="$lwu.waktumati" size="4" maxlength="4" $readmode/>
              <span class="style44"><i>format 12 jam (HHMM)</i></span></td>
          </tr>
          #end
           <tr>
            <td><div align="right"></td>
            <td class="style36"><div align="right"><span class="style38"></span></div></td>
            <td class="style36">
              <span class="style44 style38"><i>
              <input name="chkAddPemohonWarisLapis" type="checkbox" id="chkAddPemohonWarisLapis" value="1" onClick="getDuplicateAddressPemohonWarisLapis()" $check4 $readmode>
              Alamat waris adalah alamat pemohon</i></span></td>
          </tr>
          <tr>
          <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Tetap</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td width="70%"><label>
            <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris"style="text-transform:uppercase;" onBlur="uppercase()"  value="$!alamat1" size="34" maxlength="50"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris" style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat2" size="34" maxlength="50" $readmode />
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat3" size="34" maxlength="50" $readmode/></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)" value="$!poskod" size="5" maxlength="5" $readmode/>
          </label></td>
        </tr>
        
        <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
          <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <!--#foreach($listnegpomo in $listnegeri)
          
          #if($lwu.idnegeri==$listnegpomo.id_Negeri)
          
          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
          
          
          
          #end 
          #end-->
          <td> <!--#if($lwu.idnegeri!="")
            <select name="socNegeriWaris" $readmode id="socNegeriWaris" style="text-transform:uppercase;width: 225px;" onBlur="uppercase()">
				<option value="0" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Negeri</option>
                    <option value="$lwu.idnegeri" style="text-transform:uppercase;" onBlur="uppercase()">$!negerikodpemoP - $!negeriketeranganpemoP</option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($lwu.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
            </select>
            #else
            <select name="socNegeriWaris" $readmode id="socNegeriWaris" style="text-transform:uppercase;width: 225px;" onBlur="uppercase()">
              <option value="0" style="text-transform:uppercase;" onBlur="uppercase()">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()">$!listnegpomo.kod_Negeri - !$listnegpomo.nama_Negeri</option>
                           #end
            </select> #end-->$selectNegeriTetap</td>
           </tr>
           <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><!--<label>
            <input name="txtBandarWaris" type="text" id="txtBandarWaris" style="text-transform:uppercase;" onBlur="uppercase()" value="$lwu.bandar" size="34" maxlength="40" $readmode/>
          </label>-->$selectBandarTetap</td>
        </tr>
        <tr>
          <td class="style38" ><div align="right" class="style38">No Telefon</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validatePoskod(this,this.value)" value="$lwu.noTel" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52">cth: 031234567</span></td>
        </tr>
        <tr>
          <td class="style38" ><div align="right">No Telefon Bimbit</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validatePoskod(this,this.value)"  value="$lwu.nohp" size="14" maxlength="14" $readmode/>&nbsp;<span class="style52">cth: 0121234567</span></td>
        </tr>
		<tr>
		  <td class="style38" ><div align="right"></div></td>
		  <td><div align="right"><span class="style38">:</span></div></td>
		  <td><font class="style38"><input type="checkbox" value="1" name="chkAddWarisWaris1" onClick="javascript:getDuplicateAddressWaris1();" $readmode $check3>&nbsp;<i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></font></td>
		</tr>
		<tr>
          <td class="style38" width="29%" ><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat Surat Menyurat</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td width="70%"><label>
            <input name="txtAlamatTerakhir1WarisSurat" type="text" id="txtAlamatTerakhir1WarisSurat"style="text-transform:uppercase;" onBlur="uppercase()"  value="$!alamat1surat" size="34" maxlength="50"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtAlamatTerakhir2WarisSurat" type="text" id="txtAlamatTerakhir2WarisSurat" style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat2surat" size="34" maxlength="50" $readmode />
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><div align="right"><span class="style38">:</span></div></td>
          <td><input name="txtAlamatTerakhir3WarisSurat" type="text" id="txtAlamatTerakhir3WarisSurat" style="text-transform:uppercase;" onBlur="uppercase()" value="$!alamat3surat" size="34" maxlength="50" $readmode/></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
           <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><label>
            <input name="txtPoskodWarisSurat" type="text" id="txtPoskodWarisSurat" style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)" value="$poskodsurat" size="5" maxlength="5" $readmode/>
          </label></td>
        </tr>
        
        <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
          <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <!--#foreach($listnegpomo in $listnegeri)
          
          #if($lwu.idnegerisurat==$listnegpomo.id_Negeri)
          
          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
          
          
          
          #end 
          #end-->
          <td><!-- #if($lwu.idnegerisurat!="")
            <select name="socNegeriWarisSurat" $readmode id="socNegeriWarisSurat" style="text-transform:uppercase;width: 225px;" onblur="uppercase()">
				<option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                    <option value="$lwu.idnegerisurat" style="text-transform:uppercase;" onblur="uppercase()">$!negerikodpemoP - $!negeriketeranganpemoP</option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($lwu.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
            </select>
            #else
            <select name="socNegeriWarissurat" $readmode id="socNegeriWaris" style="text-transform:uppercase;width: 225px;" onblur="uppercase()">
              <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
              <option value="$listnegpomo.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$!listnegpomo.kod_Negeri - !$listnegpomo.nama_Negeri</option>
                           #end
            </select> #end  --> $selectNegeriSurat </td>
          </tr>
          <tr>
          <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
            <td class="style36" width="1%"><div align="right"><span class="style38">:</span></div></td>
          <td><!--<label>
            <input name="txtBandarWarisSurat" type="text" id="txtBandarWarisSurat" style="text-transform:uppercase;" onblur="uppercase()" value="$lwu.bandarSurat" size="34" maxlength="40" $readmode/>
          </label>-->$selectBandarSurat</td>
        </tr>
          <tr>
            <td class="style38">&nbsp;</td>
            <td class="style36">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td class="style38" colspan="3"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i></td>
            <td class="style36">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
      </table></td>
      <td width="20%" valign="top"></td>
    </tr>
	<tr>
	
  </table>
  <input type="hidden" name="idparentlapiss">
  </fieldset>
  
  
  </td>
</tr>
 #end
 
 #end
 
 #if($show_button_lapisan=="yes")

   #foreach($lwu in $listWarisLapisanUpdate)
  <tr>
 
    <td><table width="100%" border="0" align="center">
        <tr>
        <td align="center"> 
		#if ($idstatus=="150" || $idstatus=="171")
			#if($buttonwarislapisanSimpan!="")
			<input type="button" name="tambahwarislapisanSimpan" id="tambahwarisSimpan" value="$buttonwarislapisanSimpan" onKeyPress="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()" onClick="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()"/>
			#end  
			  #if($buttonwarislapisanSimpan=="") 
			   <input type="button" name="tambahwarislapisan" id="tambahwaris4" value="$buttonwarislapisan" onKeyPress="setSelected(0,2,0,0);tambah_waris_lapisan()" onClick="setSelected(0,2,0,0);tambah_waris_lapisan()"/>
				  #if($buttonwarislapisan=="Simpan" || $buttonwarislapisan=="Kemaskini" )
				  
				  #if($listWarisLapisanIdMatiDelete.size()==0)
					<input name="txtIdParent" type="hidden" value="$idp">
				   <input type="button" name="tambahwarishapuslapisan" id="tambahwarishapuslapisan" value="Hapus" onClick="setSelected(0,2,0,0);hapus_waris_lapisan()"/>
				   #end
				   #end
			  #end
			   #if($lwu.lapis != "2" && $buttonwarislapisan=="Kemaskini")
			  <input type="button" name="lapisanberikut3" id="lapisanberikut3" value="Lapisan Sebelum" onKeyPress="setSelected(0,2,0,0);lapisan_waris_sebelum()" onClick="setSelected(0,2,0,0);lapisan_waris_sebelum()"  />       
			 #end
			 #if($lwu.statushidup==1 && $buttonwarislapisan=="Kemaskini")
			  <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut" onKeyPress="setSelected(0,2,0,0);lapisan_waris_lapisan()" onClick="setSelected(0,2,0,0);lapisan_waris_lapisan()"  />
			  #end
			  #if($buttonwarislapisanSimpan=="Simpan")
			  <input type="button" name="cmdSimpan8" id="cmdSimpan10" value="Batal" onKeyPress="setSelected(0,2,0,0);lapisan_waris_sebelum()" onClick="setSelected(0,2,0,0);lapisan_waris_sebelum()"/>
			  #end
			  #if($lwu.lapis!= "1")
				<input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="WarisView('0','2','0','0')"/>
			  #end
		  #end
        </td>
        </tr>
        #end
    </table></td>
  </tr>
#end
  <tr>
    <td>
    <fieldset>
    <legend>SENARAI WARIS LAPISAN BERIKUT</legend>
      <table width="100%">
        #set($idwww=$idparent)
      <input type="hidden" name="idwarislapis" value="$idwww">       
       <input type="hidden" name="idparentlapis" value="$ip">
      <input type="hidden" name="idwarisup" value="$idwarisup" />
      </table>      
      <div align="center">
        <table width="100%">
          <tr class="table_header">
           <td width="5%"><div align="center">NO</div></td>
           <td width="20%"><div align="center">NAMA WARIS</div></td>
            <td width="10%"><div align="center">NO KP BARU</div></td>
            <td width="15%"><div align="center">TALIAN PERSAUDARAAN</div></td>
            <td width="15%"><div align="center">NAMA WARIS YANG MENINGGAL</div></td>
            <td width="15%"><div align="center">STATUS</div></td>
            <td width="8%"><div align="center">LAPISAN</div></td>
          </tr>
       <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
       </table>
        #if($listWarisLapisan.size()==0)
        
                  <table width="100%">
                   <tr bgcolor="white">
                   <td align="left">
                   Tiada Rekod                   </td>
                  </tr>
                  </table>
                  #else
       <table width="100%">
         #set($bu=0)
          #foreach($listwarislapisan in $listWarisLapisan)
          #set($bu=$bu+1)
          #if($bu%2!=0)
          <tr>
          <td width="5%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$bu</div></td>
            <td width="20%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
            <td width="10%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
            #foreach($listsaudaralist in $listsaudara)
	            #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
		            #set($wariskodsaudara=$listsaudaralist.kod)
		            #set($warissaudaraketerangan=$listsaudaralist.keterangan)
	            #end    
            #end
            <td width="15%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
             #foreach($lw in $listWarisParent)
	              #if($lw.idwaris==$listwarislapisan.idparent)
		              #set($idpar=$lw.nama_Ob)
	              #else
		               #set($idpar="")
	              #end
             #end
            <td width="15%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>
            #if($listwarislapisan.statushidup=="0")
 	           #set($hidup="Masih Hidup")
            #end
            #if($listwarislapisan.statushidup=="1")
	            #set($hidup="Sudah Meninggal Dunia")
            #end
            #if($listwarislapisan.statushidup=="")
	            #set($hidup="")
            #end
            <td width="15%" class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
             <td width="8%" class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
          </tr>
          #else
            <tr class="table_header">
          
          <td width="5%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$bu</div></td>
            <td width="20%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')" class="style42"> $listwarislapisan.nama_Ob</a></div></td>
            <td width="10%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.nokpbaru</div></td>
            #foreach($listsaudaralist in $listsaudara)
            
            #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
            
            #set($wariskodsaudara=$listsaudaralist.kod)
            #set($warissaudaraketerangan=$listsaudaralist.keterangan)
            
            
            #end    
            #end
            
            
            
            <td width="15%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$warissaudaraketerangan</div></td>
              #foreach($lw in $listWarisParent)
              
              #if($lw.idwaris==$listwarislapisan.idparent)
              #set($idpar=$lw.nama_Ob)
              
              #else
               #set($idpar="")
              #end
              
             #end
            <td width="15%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$idpar</div></td>

            #if($listwarislapisan.statushidup=="0")
            #set($hidup="Masih Hidup")
            #end
            #if($listwarislapisan.statushidup=="1")
            #set($hidup="Sudah Meninggal Dunia")
            #end
            #if($listwarislapisan.statushidup=="")
            #set($hidup="")
            #end
            <td width="15%" class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
             <td width="8%" class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listwarislapisan.lapis</div></td>
          </tr>
          #end
          #end
        </table>
        #end      </div>    
        </fieldset>
        </td>
  </tr>
  #end
  <tr>
                <td>
                </td>
                </tr>
                    </table>
             </div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent">
        <div id="TabbedPanels4" class="TabbedPanelsContentVisible">
         
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    </div>
  </div></td>
  </tr>
</table>
</fieldset>
</fieldset
><script>
function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action="";
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.action="";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}



function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}
function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}
function cancelwaris() {
input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
document.${formName}.reset();
document.${formName}.txtNoKPBaru1Waris.focus();
}
}

<!-- WARIS -->
function tarikh_waris_lapisan()
 {
 	document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="Waristarikh_lapisan";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function tarikh_waris()
 {
 	document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="Waristarikh";
	document.${formName}.action.value="";
 	document.${formName}.submit();
 }

function tarikh_waris_update()
 {
	document.${formName}.method="post";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="Waristarikh_update";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function tarikh_waris_lapisan_update()
 {
 	document.${formName}.method="post";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="Waristarikh_lapisan_update";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function new_waris() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Newwaris";
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}

function get_waris(idw) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="Getwaris";
	document.${formName}.idwaris.value=idw;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}



function lapisan_sebelum() {
	document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");	
	document.${formName}.mode.value="Lapisan_sebelum";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function get_waris_lapisan(idw,idp) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="GetwarisLapisan";
	document.${formName}.idwarislapis.value=idw;
    document.${formName}.idparentlapis.value=idp;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function get_waris_lapisan_X(idw,idp) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="GetwarisLapisanX";
	document.${formName}.idwarislapisX.value=idw;
    document.${formName}.idparentlapisX.value=idp;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function lapisan_waris(idw) {
	document.${formName}.method="POST";
	//document.${formName}.mode.value="Lapisan_berikut";
	document.${formName}.mode.value="Tambah_lapisan_berikut";
	document.${formName}.idwaris.value=idw;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}

function lapisan_waris_lapisan() {
	document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="Lapisan_berikut_lapisan";
	//document.${formName}.idwarislapis.value=idw;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function lapisan_waris_sebelum() {
	document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");
	//document.${formName}.mode.value="Lapisan_waris_sebelum";
	document.${formName}.mode.value="Warisview";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function tambah_lapisan_berikut(idw) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="Tambah_lapisan_berikut";
	document.${formName}.idwarislapis.value=idw;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}


function tambah_lapisan(idw) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="Tambah_lapisan";
	document.${formName}.idwaris.value=idw;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function batal_waris_lapisan_Simpan() {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="POST";
	 	doAjaxCall${formName}("Waris");
		document.${formName}.mode.value="kemaskini_waris_lapisan";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}
function tambah_waris_lapisan_Simpan() {
	if( document.${formName}.tambahwarislapisanSimpan.value == "Simpan" ) {
		if(document.${formName}.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
			return; 
		}
		
		if(document.${formName}.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
			return; 
		}
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.method="POST";
		doAjaxCall${formName}("Waris");
		document.${formName}.mode.value="tambah_waris_lapisan";
		document.${formName}.action.value="";
		document.${formName}.submit();
		}
	}
}

function tambah_waris_lapisan() {
	if( document.${formName}.tambahwarislapisan.value == "Tambah" ) {
		if(document.${formName}.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
			return; 
		}
		else if(document.${formName}.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
			return; 
		}else{
			input_box=confirm("Adakah anda pasti?");
			if (input_box == true) {
				document.${formName}.method="POST";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="tambah_waris_lapisan";
				document.${formName}.action.value="";
				document.${formName}.submit();
			}
		}
	}

	if( document.${formName}.tambahwarislapisan.value == "Simpan" ) 
	{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="POST";
			doAjaxCall${formName}("Waris");
			document.${formName}.mode.value="simpan_waris_lapisan";
			document.${formName}.action.value="";
			document.${formName}.submit();
		}
	}
   
    if( document.${formName}.tambahwarislapisan.value == "Kemaskini" ) 
	{
		document.${formName}.method="POST";
		doAjaxCall${formName}("Waris");
	    document.${formName}.mode.value="kemaskini_waris_lapisan";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}

    function warisbatalupdate()
    {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.${formName}.method="POST";
	doAjaxCall${formName}("Waris");
	document.${formName}.mode.value="kemaskini_waris";
	document.${formName}.action.value="";
	document.${formName}.submit();
    }
}


function tambah_waris_Simpan(){
	if( document.${formName}.tambahwarisSimpan.value == "Simpan" ) {
		var negeri_code = document.${formName}.txtNoKPBaru2Waris.value;
		var tms = document.${formName}.txdTarikhMatiWaris;
		var dob_code = document.${formName}.txtNoKPBaru1Waris.value;
		
			if(dob_code.charAt(0)<3) {
			 var dum = "20";
			}
			else {
			var dum = "19";
			}
			
		var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
		var dt_dob   = parseInt(tt.substring(0,2),10);
		var mon_dob  = parseInt(tt.substring(3,5),10)-1;
		var yr_dob   = parseInt(tt.substring(6,10),10);
		var date_dob = new Date(yr_dob, mon_dob, dt_dob);
	
		if (document.${formName}.txtNoKPBaru1Waris.value == "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value == "" && document.${formName}.txtNoKPLamaWaris.value == "" && document.${formName}.txtNoKPLainWaris.value == "") {
			alert("Sila masukkan salah satu No KP");
			txtNoKPBaru1Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru1Waris.value.length < 6){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru1Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value.length < 2){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru2Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru3Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value.length < 4){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru3Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value == ""){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru2Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value == ""){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru3Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value != ""){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru2Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value == "" && document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value != ""){
			alert("Sila masukkan No KP Baru Waris sepenuhnya");
			txtNoKPBaru2Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
	  		txtNoKPBaru1Waris.focus();
		}
		else if (document.${formName}.txtNoKPBaru2Waris.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
			negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
			alert("Sila masukkan kod negeri yang sah");
			txtNoKPBaru2Waris.focus()
		}		
		else if (document.${formName}.socJenisKPLainWaris.value != "" && document.${formName}.txtNoKPLainWaris.value == ""){
			alert("Sila masukkan No KP Lain Waris");
			txtNoKPLainWaris.focus();
		}
		else if (document.${formName}.txtNoKPLainWaris.value != "" && document.${formName}.socJenisKPLainWaris.value == ""){
			alert("Sila pilih jenis No KP Lain Waris");
			socJenisKPLainWaris.focus();
		}
		else if(document.${formName}.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
		}
		else if(document.${formName}.socSaudaraWaris.value == ""){
			alert('Sila pilih " Tali Persaudaraan " terlebih dahulu.');
	  		document.${formName}.txtNamaOBWaris.focus(); 
		}
		else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txdTarikhMatiWaris.value==""){
			alert('Sila masukkan " Tarikh Mati " terlebih dahulu.');
			txdTarikhMatiWaris.focus()
		}
		else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txdTarikhMatiWaris.value!="" && isDate(tms.value)==false){
			tms.focus()
		}
		else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txtWaktuKematianWaris.value==""){
			alert('Sila masukkan " Waktu Mati " terlebih dahulu.');
			txdTarikhMatiWaris.focus()
		}
		else{
			input_box=confirm("Adakah anda pasti?");
			if (input_box == true) {
				document.${formName}.method="post";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="tambah_waris";
				document.${formName}.action.value="";
				document.${formName}.submit();
			}
		}
	}
}

function hapus_waris(){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("Waris");
		document.${formName}.mode.value="hapus_waris";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}
    
function hapus_waris_lapisan(){
    input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("Waris");
		document.${formName}.mode.value="hapus_waris_lapisan";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}

function tambah_waris(){
		if( document.${formName}.tambahwaris.value == "Tambah" ) 
		{
			if(document.${formName}.txtNamaOBWaris.value == ""){
				alert('Sila masukkan " Nama " terlebih dahulu.');
		  		document.${formName}.txtNamaOBWaris.focus(); 
				return; 
			}
			else if(document.${formName}.socSaudaraWaris.value == ""){
				alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
		  		document.${formName}.txtNamaOBWaris.focus(); 
				return; 
			}else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
					document.${formName}.method="post";
					doAjaxCall${formName}("Waris");
					document.${formName}.mode.value="tambah_waris";
					document.${formName}.action.value="";
					document.${formName}.submit();
				}
			}
		}
		
		if( document.${formName}.tambahwaris.value == "Simpan" ) 
		{
			 var negeri_code = document.${formName}.txtNoKPBaru2Waris.value;
			 var tms = document.${formName}.txdTarikhMatiWaris;
			 var dob_code = document.${formName}.txtNoKPBaru1Waris.value;
			
				if(dob_code.charAt(0)<3) {
				 var dum = "20";
				}
				else {
				var dum = "19";
				}
				
			 var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
			 var dt_dob   = parseInt(tt.substring(0,2),10);
			 var mon_dob  = parseInt(tt.substring(3,5),10)-1;
			 var yr_dob   = parseInt(tt.substring(6,10),10);
			 var date_dob = new Date(yr_dob, mon_dob, dt_dob);
			
			
			if (document.${formName}.txtNoKPBaru1Waris.value == "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value == "" && document.${formName}.txtNoKPLamaWaris.value == "" && document.${formName}.txtNoKPLainWaris.value == "") {
				alert("Sila masukkan salah satu No KP");
				txtNoKPBaru1Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru1Waris.value.length < 6){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value.length < 2){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Waris.focus();
			 }
			 else if (document.${formName}.txtNoKPBaru3Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value.length < 4){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Waris.value != "" && document.${formName}.txtNoKPBaru2Waris.value == "" && document.${formName}.txtNoKPBaru3Waris.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru2Waris.value != "" && document.${formName}.txtNoKPBaru3Waris.value != "" && document.${formName}.txtNoKPBaru1Waris.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Waris.value != "" && isIc(tt)==false){
				txtNoKPBaru1Waris.focus();
			}
			else if (document.${formName}.txtNoKPBaru2Waris.value != "" && (negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
				negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
				alert("Sila masukkan kod negeri yang sah");
				txtNoKPBaru2Waris.focus()
			}
			else if (document.${formName}.txtNamaOBWaris.value==""){
				alert('Sila masukkan " Nama " terlebih dahulu.');
		  		document.${formName}.txtNamaOBWaris.focus(); 
				return; 
			}
			else if (document.${formName}.socSaudaraWaris.value==""){
				alert('Sila pilih " Tali Persaudaraan " terlebih dahulu.');
		  		document.${formName}.txtNamaOBWaris.focus(); 
				return; 
			}
			else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txdTarikhMatiWaris.value==""){
				alert('Sila masukkan " Tarikh Mati " terlebih dahulu.');
				txdTarikhMatiWaris.focus()
			}
			else if (document.${formName}.txtNoKPBaru2Waris.value!="" && document.${formName}.checkHidupWaris.checked==true && document.${formName}.txdTarikhMatiWaris.value!="" && isDate(tms.value)==false){
				tms.focus()
			}
			else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txtWaktuKematianWaris.value==""){
				alert('Sila masukkan " Waktu Mati " terlebih dahulu.');
				txdTarikhMatiWaris.focus()
			}
			/*else if (document.${formName}.txtAlamatTerakhir1Waris.value==""){
				alert("Sila masukkan " Alamat " dengan lengkap");
				txtAlamatTerakhir1Waris.focus();
			}
			else if (document.${formName}.txtPoskodWaris.value!="" && document.${formName}.txtPoskodWaris.value.length < 5){
				alert("Sila masukkan " No Poskod " dengan lengkap");
				txtPoskodWaris.focus();
			}*/
			else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
					document.${formName}.method="post";
					doAjaxCall${formName}("Waris");
					document.${formName}.mode.value="simpan_waris";
					document.${formName}.action.value="";
					document.${formName}.submit();
				}
			}
		}

	if( document.${formName}.tambahwaris.value == "Kemaskini" ) 
	{
		document.${formName}.method="post";
		doAjaxCall${formName}("Waris");
	    document.${formName}.mode.value="kemaskini_waris";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}
}
<!-- PEMOHON -->
function PengesahanView(){
	document.${formName}.method="post";
	document.${formName}.action.value="";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}

function getExist(){
	if (document.${formName}.socJenisKPLainWaris.value=="" && document.${formName}.txtNoKPLainWaris.value!=""){
		alert("Sila pilih jenis kp");
		socJenisKPLainWaris.focus();
	}else if (document.${formName}.socJenisKPLainWaris.value!="" && document.${formName}.txtNoKPLainWaris.value==""){
		alert("Sila masukkan no kp lain");
		txtNoKPLainWaris.focus();
	}
	else{
		document.${formName}.method="post";
		document.${formName}.mode.value="Newwaris";
		document.${formName}.v_tab.value = v_t;
		document.${formName}.action.value="";
		doAjaxCall${formName}("Waris");
		document.${formName}.submit();
	}
}

function getExistLapisan(){
		document.${formName}.method="post";
		document.${formName}.mode.value="Tambah_lapisan_berikut";
		document.${formName}.action.value="";
		doAjaxCall${formName}("Waris");
		document.${formName}.submit();
}

function Checkumur() 
{
	if (document.${formName}.txtUmurWaris.value.length >= 3) {
		alert("Adakah anda pasti umur anda adalah "+document.${formName}.txtUmurWaris.value+" tahun?");
		txtUmurWaris.focus();
	}
}
function validTarikhMati() {
	if (document.${formName}.checkHidupWaris.checked == true && document.${formName}.txtWaktuKematianWaris.value != "" && document.${formName}.txtWaktuKematianWaris.value.length < 4) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		txtWaktuKematianWaris.focus();
	}
	else if (document.${formName}.checkHidupWaris.checked == true  && document.${formName}.txtWaktuKematianWaris.value != "" && (document.${formName}.txtWaktuKematianWaris.value.charAt(2)> 5)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		txtWaktuKematianWaris.focus();
	}
	else if (document.${formName}.checkHidupWaris.checked == true  && document.${formName}.txtWaktuKematianWaris.value != "" && (document.${formName}.txtWaktuKematianWaris.value.charAt(0)> 2)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		txtWaktuKematianWaris.focus();
	}
	else if (document.${formName}.checkHidupWaris.checked == true  && document.${formName}.txtWaktuKematianWaris.value != "" && (document.${formName}.txtWaktuKematianWaris.value.charAt(0)==2 && document.${formName}.txtWaktuKematianWaris.value.charAt(1)> 3)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		txtWaktuKematianWaris.focus();
	}	
}

function getFocus()
{
	if (document.${formName}.checkHidupWaris.checked==false && document.${formName}.txtNoKPBaru1Waris.value==""){
	document.${formName}.txtNoKPBaru1Waris.focus();
	}
	else if (document.${formName}.checkHidupWaris.checked==true && document.${formName}.txdTarikhMatiWaris.value==""){
		document.${formName}.txdTarikhMatiWaris.focus();
	}
}

function validLength(){
	if (document.${formName}.txtPoskodWaris.value!="" && document.${formName}.txtPoskodWaris.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap");
		txtPoskodWaris.focus();
	}
}
function validLength1(){
	if (document.${formName}.txtPoskodWaris.value!="" && document.${formName}.txtPoskodWaris.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap");
		txtPoskodWaris.focus();
	}
}

function validDate() {
	var currentTime=new Date()
	var month=currentTime.getMonth() + 1
	var day=currentTime.getDate()
	var year=currentTime.getFullYear()
	var currentDate=day + "/" + month + "/" + year;

	if (document.${formName}.txdTarikhMatiWaris.value > currentDate){
		alert("Sila pastikan tarikh mati tidak melebihi dari tarikh hari ini.");
		txdTarikhMatiWaris.focus();
	}
}

function submitForm() {    
   // document.val.focus();
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	
	//doucument.f1.'$val_tab'.focus();
} 

</script>
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}


function isIc(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}

function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.${formName}.txdTarikhMatiWaris.value = trans;

}
else
{
return;
}

}

function onChangeBandarTetap(){
	document.${formName}.method="POST";
	document.${formName}.mode.value="onChangeBandarTetap";
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="2";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}

function onChangeBandarTetapWaris(){
	document.${formName}.method="POST";
	document.${formName}.mode.value="onChangeBandarTetapWaris";
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="2";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}

function getDuplicateAddress(){
		if (document.${formName}.chkAddWaris.checked == true) {
				document.${formName}.method="POST";
				document.${formName}.chkAddWaris.checked = true;
				document.${formName}.mode.value="onChangeDuplicate";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();

		}else if (document.${formName}.chkAddWaris.checked == false) {
				document.${formName}.method="POST";
				document.${formName}.chkAddWaris.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicate";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();
		}
}
function getDuplicateAddressPemohonWaris(){
	
		if (document.${formName}.chkAddPemohonWaris.checked == true) {
				document.${formName}.method="POST";
				document.${formName}.chkAddPemohonWaris.checked = true;
				document.${formName}.mode.value="onChangeDuplicatePemohonWaris";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();

		}else if (document.${formName}.chkAddPemohonWaris.checked == false) {
				document.${formName}.method="POST";
				document.${formName}.chkAddPemohonWaris.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicatePemohonWaris";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();
		}
}
function getDuplicateAddressPemohonWarisLapis(){
	
		if (document.${formName}.chkAddPemohonWarisLapis.checked == true) {
				document.${formName}.method="POST";
				document.${formName}.chkAddPemohonWarisLapis.checked = true;
				document.${formName}.mode.value="onChangeDuplicatePemohonWarisLapis";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();

		}else if (document.${formName}.chkAddPemohonWarisLapis.checked == false) {
				document.${formName}.method="POST";
				document.${formName}.chkAddPemohonWarisLapis.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicatePemohonWarisLapis";
				doAjaxCall${formName}("Waris");
				document.${formName}.submit();
		}
}

function getDuplicateAddressWaris(){
		if (document.${formName}.chkAddWarisWaris.checked == true) {
				document.${formName}.method="POST";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="onChangeDuplicateWaris";
				document.${formName}.chkAddWarisWaris.checked = true;
				document.${formName}.submit();

		}else if (document.${formName}.chkAddWarisWaris.checked == false) {
				document.${formName}.method="POST";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="onChangeDuplicateWaris";
				document.${formName}.chkAddWarisWaris.checked = false;
				document.${formName}.submit();
		}

}
function getDuplicateAddressWaris1(){
		if (document.${formName}.chkAddWarisWaris1.value == "1") {
				document.${formName}.method="POST";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="onChangeDuplicateWaris";	
				document.${formName}.chkAddWarisWaris1.checked = true;
				document.${formName}.submit();
		}
		else if (document.${formName}.chkAddWarisWaris1.value == "") {
				document.${formName}.method="POST";
				doAjaxCall${formName}("Waris");
				document.${formName}.mode.value="onChangeDuplicateWaris";
				document.${formName}.chkAddWarisWaris1.checked = false;
				document.${formName}.submit();
		}
}
</script>
<script type="text/javascript">

var TabbedPanels1=new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2=new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3=new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4=new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>

