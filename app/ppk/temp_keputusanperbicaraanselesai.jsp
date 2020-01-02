#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $idFail = $data.get("id_fail") )
#set($perhatian="<i><font color=red >Perhatian</font><font >: Sila pastikan label bertanda</font>&nbsp;<font color=red >*</font>&nbsp;<font >diisi.</font></i>")


#foreach($list in $listSemak)
 	#set($noFail=$list.noFail)
 	#set($negeri=$list.pmNama_negeri)
 	#set($daerah=$list.namadaerah)
 	#set($unit=$list.namaPejabat)
 	#set($seksyen=$list.seksyen)
 	#set($statusFail=$list.keterangan)
 	#set($tarikhMohon=$list.tarikhMohon)
 	#set($namaSimati=$list.namaSimati)
 	#set($namaSipemohon=$list.namaPemohon)
    #set($idstatus=$list.id_Status)
    #set($idPermohonanSimati=$list.id_permohonansimati)
    #set($id_permohonan=$list.idPermohonan)
<!-- case utk butang Seterusnya ke Notis -->
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<!--<input type="text" name="id_status" id="id_status" value="$idstatus"> -->
#end

#set ( $idpermohonan = $dataPerbicaraan.get("idPermohonan") )
#set ( $idPemohon = $dataPerbicaraan.get("idPemohon") )
#set ( $id_perbicaraan = $dataPerbicaraan.get("id_perbicaraan") )
#set ( $namaPegawai = $dataPerbicaraan.get("nama_pegawai") )
<input type="hidden" readonly="readonly" name="idsuburusanstatusfail" id="idsuburusanstatusfail" value="$idsuburusanstatusfail" />
<input type="hidden" name="idpermohonan" id="idpermohonan" value="$idpermohonan"/>
<input type="hidden" name="flagFromSenaraiFailSek8"  id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input type="hidden" name="flagFromSenaraiPermohonanSek8" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input type="hidden" name="tabId" id="tabId" value="$selectedTab"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" readonly="readonly" name="idPermohonanSimati" id="idPermohonanSimati" value="$idPermohonanSimati" />
<input type="hidden" readonly="readonly" name="idstatus" id="idstatus" value="$idstatus" />
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>
<input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon">
<input type="hidden" name="idFail" id="idFail" value="$idFail">
<input type="hidden" name="noFail" id="noFail" value="$noFail">
<!-- case utk butang Seterusnya ke Perintah -->
<input type="hidden" name="actionPerintah">
<input type="hidden" name="idPermohonan" value="$idpermohonan">
<!--START CODE-->
<table width="100%">
<tr>
  <td><fieldset>
    <legend>MAKLUMAT PERMOHONAN</legend>
    <table width="100%">
      <tr>
        <td width="50%"><table width="101%"  cellpadding="1" cellspacing="1" border="0">
            <tr>
              <td width="33%" style="text-transform:uppercase"><div align="right">No Fail</div></td>
              <td width="2%">:</td>
              <td width="65%"><font color="blue">$noFail</font></td>
            </tr>
            <tr>
              <td><div align="right" style="text-transform:uppercase">Negeri</div></td>
              <td>:</td>
              <td><font color="blue">$negeri.toUpperCase()</font></td>
            </tr>
            <tr>
              <td valign="top"><div align="right" style="text-transform:uppercase">Daerah / Jajahan</div></td>
              <td valign="top">:</td>
              <td><font color="blue">$daerah.toUpperCase()</font></td>
            </tr>
            <tr>
              <td valign="top"><div align="right" style="text-transform:uppercase">Unit</div></td>
              <td valign="top">:</td>
              <td><font color="blue">$unit.toUpperCase()</font></td>
            </tr>
            <tr>
              <td><div align="right" style="text-transform:uppercase"></div></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        <td width="50%"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
            <tr>
              <td width="38%" valign="top" style="text-transform:uppercase"><div align="right">Status Fail</div></td>
              <td width="2%" valign="top">:</td>
              <td width="60%"><font color="blue">$statusFail.toUpperCase()</font></td>
            </tr>
            <tr>
              <td><div align="right" style="text-transform:uppercase">Seksyen</div></td>
              <td>:</td>
              <td><font color="blue">$seksyen.toUpperCase()</font></td>
            </tr>
            <tr>
              <td><div align="right" style="text-transform:uppercase">Tarikh Mohon</div></td>
              <td>:</td>
              <td><font color="blue">$tarikhMohon</font></td>
            </tr>
            <tr>
              <td valign="top"><div align="right" style="text-transform:uppercase">Nama Simati</div></td>
              <td valign="top">:</td>
              <td><font color="blue">$namaSimati.toUpperCase()</font></td>
            </tr>
            <tr>
              <td valign="top"><div align="right"><span style="text-transform:uppercase">Nama Pemohon</span></div></td>
              <td valign="top">:</td>
              <td><font color="blue">$namaSipemohon.toUpperCase()</font></td>
            </tr>
          </table></td>
      </tr>
    </table>
    </fieldset></td>
</tr>
#if ( $idstatus == "18" )
<!--------------------------------------------------- ADD MODE ---------------------------------------------->
#if ( $mode == "add" )
<tr>
  <td><fieldset>
    <legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
    <table width="100%"  cellspacing="1" cellpadding="1" border="0">
      <tr>
        <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
        <td width="33%">:&nbsp;$selectEditPegawai</td>
        #if ( $tarikh == "perintah" )
        <td width="11%"><font color="red">*</font>&nbsp;Tarikh Perintah</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintah" value="$!tarikh_bicara" size="10" id="txdTarikhPerintah" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhPerintah',false,'dmy');" /></td>
        #end
        
        #if ( $tarikh == "bicara" )
        <td width="14%"><font color="red">*</font>&nbsp;Tarikh Perbicaraan &nbsp;&nbsp;&nbsp;Terakhir</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintah" value="$!tarikh_bicara" size="10" id="txdTarikhPerintah" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhPerintah',false,'dmy');" /></td>
        #end </tr>
      <tr>
        <td width="16%"><font color="red">*</font>&nbsp;Keputusan Perbicaraan</td>
        <td colspan="5">:&nbsp;
          <input name="flag_jenis_keputusan" type="radio" value="0" $TEMPcheckedSelesai onClick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" type="radio" value="1" $TEMPcheckedTangguh onClick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" type="radio" value="2" $TEMPcheckedBatal onClick="tab_batal()" />
          Batal</td>
      </tr>
      <!--By Mohamad Rosli 21/02/2011-->
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><font color="blue"><u><strong>Nota</strong></u></font> <br>
          <!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
          <b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa <br>
          <b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara <br>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table>
    </fieldset></td>
</tr>
<tr>
  <td> #if( $button == "kembali" )
    <p></p>
    <div align="center">
      <input name="cmdKembali" type="button" value="Kembali" onClick="javascript:kembali_list('$idpermohonan','$id_perbicaraan');" />
    </div>
    #end </td>
</tr>
#foreach($dataSelesai in $dataBicaraView)
			#set ($jumlah_harta_tarikhmohon = $data.jumlah_harta_tarikhmohon)
		#end  
	
	#if ( $flag == "selesai" || $flag == "tangguh" || $flag == "batal")
    <tr>
    <td>
    
    
    
    <fieldset>
    <table width="100%">
    <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
  #if(  $flag == "selesai" )
  <!-- value selesai 0 -->
  <li class="TabbedPanelsTab" onClick="setSelected(0);SelesaiAdd()">SELESAI</li>
  #end
  #if(  $flag == "tangguh" )
  <!-- value selesai 1 -->
  <li class="TabbedPanelsTab" onClick="setSelected(1);TangguhAdd()">TANGGUH</li>
  #end
  #if( $flag == "batal" )
  <!-- value selesai 2 -->
  <li class="TabbedPanelsTab" onClick="setSelected(2);BatalAdd()">BATAL</li>
  #end
</ul>
    <div class="TabbedPanelsContentGroup">
    <!----------------------------------------- ADD SELESAI ----------------------------------------------->
    <div class="TabbedPanelsContent"> #if ( $flag == "selesai")
  <input type="hidden" name="id_jenisbayaranPerintah" id="id_jenisbayaranPerintah" value="24"/>
  <input type="hidden" name="id_jenisbayaranCukaiPusaka" id="id_jenisbayaranCukaiPusaka" value="26"/>
  <input type="hidden" name="id_jenisbayaranBaitulMal" id="id_jenisbayaranBaitulMal" value="29"/>
  #foreach($data in $dataJumlahBayaran)
  #set ($jumlah_harta_tarikhmohon = $data.jumlah_harta_tarikhmohon)
  #end
  <table width="100%" border="0">
        <tr>
      <td width="33%" valign="top"><fieldset>
        <legend>Bayaran Perintah</legend>
        <table width="100%">
          <tr>
            <td width="47%" ><div align="left">&nbsp;&nbsp;&nbsp;Jumlah Harta &nbsp;&nbsp;&nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumHarta" onBlur="this.value=this.value.toUpperCase();" id="txtJumHarta" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($txtJumHarta)" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left"><font color="red">*</font>&nbsp;Jumlah &nbsp;&nbsp;&nbsp;Bayaran(RM)</div></td>
            <td><div align="right">:</div></td>
            <td> #if ( $FlagtarikhMohon == 0 )
              <input type="text" size="15" name="txtJumBayaran1" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaran1" style="text-transform:uppercase;" value="$Util.formatDecimal($txtJumBayaran)" />
              <input type="hidden" size="12" name="txtJumBayaran" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaran" style="text-transform:uppercase;" readonly class="disabled" value="$txtJumBayaran" />
              #end
              
              #if ( $FlagtarikhMohon == 1 )
              <input type="text" size="15" name="txtJumBayaran" id="txtJumBayaran" onBlur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onKeyUp="validateNumber(this,this.value);" />
              #end </td>
          </tr>
          <tr>
            <td valign="top"><div align="left"><font color="red">*</font>&nbsp;No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPerintah" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintah" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left"><font color="red">*</font>&nbsp;Tarikh &nbsp;&nbsp;&nbsp;Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranPerintah" value="" size="15" id="txdTarikhBayaranPerintah" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranPerintah',false,'dmy');" /></td>
          </tr>
        </table>
        </fieldset></td>
      <td width="34%" valign="top"><fieldset>
        <legend>Bayaran Cukai</legend>
        <table width="100%" border="0">
          <tr>
            <td width="46%">Jumlah Bayaran (RM)</td>
            <td width="2%"><div align="right" class="style38">:</div></td>
            <td width="52%"><label>
              <input type="text" size="15" name="txtJumBayaranPusaka" id="txtJumBayaranPusaka" value="$!txtJumBayaranPusaka" maxlength="12" onBlur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onKeyUp="validateNumber(this,this.value);" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPusaka" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPusaka" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranPusaka" size="15" id="txdTarikhBayaranPusaka" type="text"  onblur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranPusaka',false,'dmy');" /></td>
          </tr>
          <tr>
            <td colspan="3">&nbsp;</td>
          </tr>
        </table>
        </fieldset></td>
      <td width="33%" valign="top"><fieldset>
        <legend>Bayaran Baitulmal</legend>
        <table width="100%" border="0">
          <tr>
            <td width="53%">Jumlah Bayaran (RM)</td>
            <td width="3%"><div align="right">:</div></td>
            <td width="44%"><label>
              <input type="text" size="12" name="txtJumBayaranBaitulmal" id="txtJumBayaranBaitulmal" value="$!txtJumBayaranBaitulmal" maxlength="12" onBlur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onKeyUp="validateNumber(this,this.value);" />
              </label>
            </td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitBaitulmal" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmal" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranBaitulmal" size="15" id="txdTarikhBayaranBaitulmal" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranBaitulmal',false,'dmy');" /></td>
          </tr>
          <tr>
            <td colspan="3">&nbsp;</td>
          </tr>
        </table>
        </fieldset></td>
    </tr>
        <tr>
      <td colspan="3" width="100%" >&nbsp;</td>
    </tr>
        <tr>
      <td colspan="3" width="100%" >Catatan Keputusan Perbicaraan  : </td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><textarea name="txtCatatanSelesai" id="txtCatatanSelesai" cols="80%" rows="8"></textarea>
            <!-- ADD MODE -->
            <script> 
								              	var area = new FCKeditor("txtCatatanSelesai");
									      		area.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area.ReplaceTextarea();             	
								              </script>
          </td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><div  class="disabled" id="word_count5">
          </td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><div align="center">
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript:Simpan_Selesai('$idpermohonan','$id_perbicaraan');" />
          <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
        </div></td>
    </tr>
      </table>
  #end </div>
    <!-- close div $flag == "selesai" -->
    <!----------------------------------------- END ADD SELESAI ----------------------------------------------->
    <!----------------------------------------- ADD TANGGUH ----------------------------------------------->
    <div class="TabbedPanelsContent"> #if ( $flag == "tangguh")
  <table width="100%" cellspacing="2" border="0">
        <tr align="center">
      <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onClick="SenaraiBicara('$idpermohonan')" /></td>
    </tr>
        <tr align="center">
      <td colspan="2" align="left">&nbsp;</td>
    </tr>
        <tr align="center">
      <td align="left" colspan="2"><font color="red" >*</font> Alasan Keputusan Perbicaraan :</td>
    </tr>
        <tr align="center">
      <td width="57%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="1" $TEMPcheckedTidakHadir>
            Pemohon / Waris Tidak Hadir </td>
      <td width="43%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="2" $TEMPcheckedWarisTidakLengkap >
            Senarai Waris Tidak Lengkap </td>
    </tr>
        <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="3" $TEMPcheckedMahkamahTinggi>
            Menunggu Keputusan Rujukan Mahkamah Syariah </td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="4" $TEMPcheckedBuktiTidakLengkap >
            Bukti Kematian Tidak Lengkap </td>
    </tr>
        <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="5" onClick="RulerOfTheState('$idpermohonan','$id_perbicaraan','$id_perintah')" $TEMPcheckedMahkamahSyariah >
            Menunggu Keputusan Rujukan Kepada Ruler of The State </td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" onClick="PertelingkahanKolateral('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
            Pertelingkahan Kolateral </td>
    </tr>
        <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi</td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="8" $tempcheckedsuratsetuju />
            Menunggu Surat Akuan Persetujuan</td>
    </tr>
        <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="7" $TEMPcheckedSijilFaraid>
            Menunggu Sijil Faraid</td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="9" $tempcheckedsebablain />
            Sebab-sebab Lain</td>
    </tr>
        <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tempoh Menunggu 
            :&nbsp;&nbsp;&nbsp;
            <input type="text" size="5" maxlength="2" name="txtTempoh" id="txtTempoh" value="$!tempoh_tunggu_faraid" onBlur="validateNumber(this,this.value);" onKeyUp="validateNumber(this,this.value);"/>
            Hari</td>
      <td align="left">&nbsp;</td>
    </tr>
        <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
        <tr>
      <td colspan="2" width="100%" >Catatan / Keterangan Tangguh  : </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><textarea name="txtCatatanTangguh" id="txtCatatanTangguh" cols="80%" rows="8"></textarea>
            <!-- ADD MODE -->
            <script>												   	
												var area = new FCKeditor("txtCatatanTangguh");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
          </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count6">
          </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
        <tr>
      <td colspan="2" width="100%" >Pendapat / Keputusan Mahkamah  : </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><textarea name="txtPendapatTangguh" id="txtPendapatTangguh" cols="80%" rows="8"></textarea>
            <!-- ADD MODE -->
            <script> 
												var area = new FCKeditor("txtPendapatTangguh");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
          </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count7">
          </td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
        <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ><div align="center">
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript:Simpan_Tangguh('$idpermohonan','$id_perbicaraan');" />
          <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
        </div></td>
    </tr>
        <tr>
      <td colspan="2" width="100%" ></td>
    </tr>
      </table>
  #end </div>
    <!-- close div $flag == "tangguh" -->
    <!----------------------------------------- END ADD TANGGUH ----------------------------------------------->
    <!----------------------------------------- ADD BATAL ----------------------------------------------->
    <div class="TabbedPanelsContent">
#if( $flag == "batal" )
<table width="100%" cellspacing="2" border="0">
  <tr> 
  <td>  
  <fieldset>  
  <legend><strong>Maklumat Batal</strong></legend>
  <!--<br/>  -->
  <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr align="center">
      <td width="1%"><font color="red">*</font></td>
      <td width="99%"align="left">&nbsp;Alasan Batal Perbicaraan : </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="1" onClick="MahkamahTinggi('$idpermohonan','$id_perbicaraan')" $TEMPcheckedMahkamahTinggiWasiat>
        Pindah ke Mahkamah Tinggi kerana ada wasiat </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="2" $TEMPcheckedTidakHadir3Kali >
        Tidak hadir setelah dipanggil 3 kali </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="3" $TEMPcheckedPermintaanPemohon >
        Atas Permintaan Pemohon </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="4" $TEMPcheckedMahkamahTinggi2Juta >
        Pindah ke Mahkamah Tinggi kerana harta melebihi RM2 juta </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio" value="5" $TEMPcheckedSebabLainBatal>
        Sebab-sebab lain </td>
    </tr>
    <tr align="center">
      <td colspan=2>&nbsp;</td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left">Catatan :</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><textarea name="txtCatatanBatal" id="txtCatatanBatal" cols="80%" rows="8"></textarea>
        <!-- ADD MODE -->
        <script> 
																   	
																var area = new FCKeditor("txtCatatanBatal");
					      										area.BasePath = '/${appname}/library/fck/' ;
																area.ReplaceTextarea();  
																           	
												              </script>
      </td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count8">
      </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"></td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><div align="center">
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript:Simpan_Batal('$idpermohonan','$id_perbicaraan');" />
          <input type="button" name="cmdKembali"  value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
        </div></td>
    </tr>
    </td>
    </tr>
    </table>
    </fieldset>
    
    </td>
    
    </tr>
    
  </table>
  #end
  </div>
  
  <!-- close div $flag == "batal" -->
  <!----------------------------------------- END ADD BATAL ----------------------------------------------->
  </div>
  
  <!-- close div tabbed panels content group -->
  </div>
  
  <!-- close div TabbedPanels1 -->
  </td>
  
  </tr>
  
</table>
</fieldset>
</td>
</tr>
#end ##EOF if ( $flag == "selesai" || $flag == "tangguh" || $flag == "batal") 
	
	#end
<!---------------------------------------------------END ADD MODE ---------------------------------------------->
#end ##EOF if ( $idstatus == "18" )
<!--------------------------------------------------- VIEW MODE ---------------------------------------------->

#foreach($dataPerintah1 in $dataPerintahView)
    #set ($idprintah = $dataPerintah1.id_perintah)
    #set ($id_perbicaraan = $dataPerintah1.id_perbicaraan)
    #set ($tarikh_perintah = $dataPerintah1.tarikh_perintah)
    #set ($idUnitPskView = $dataPerintah1.idUnitPskView)
    #set ($flag_jenis_keputusan = $dataPerintah1.flag_jenis_keputusan)
    #set ($catatan = $dataPerintah1.catatan)
    #set ($sebab_batal = $dataPerintah1.sebab_batal)
    #set ($sebab_tangguh = $dataPerintah1.sebab_tangguh)
    #set ($keputusan_mahkamah = $dataPerintah1.keputusan_mahkamah)
    #set ($tempoh_tunggu_faraid = $dataPerintah1.tempoh_tunggu_faraid)
      
    
#end
  

#foreach($dataSelesai in $dataBicaraView)   
    #set ($bayaran_perintah = $dataSelesai.bayaran_perintah)
    #set ($bayaran_pusaka = $dataSelesai.bayaran_pusaka)
    #set ($bayaran_baitulmal = $dataSelesai.bayaran_baitulmal)
    #set ($NoResit_perintah = $dataSelesai.NoResit_perintah)
    #set ($NoResit_pusaka = $dataSelesai.NoResit_pusaka)
    #set ($NoResit_baitulmal = $dataSelesai.NoResit_baitulmal)
    #set ($TarikhBayaran_perintah = $dataSelesai.TarikhBayaran_perintah)
    #set ($TarikhBayaran_pusaka = $dataSelesai.TarikhBayaran_pusaka)
    #set ($TarikhBayaran_baitulmal = $dataSelesai.TarikhBayaran_baitulmal)
    #set ($id_bayaran_perintah = $dataSelesai.id_bayaran_perintah)
    #set ($id_bayaran_pusaka = $dataSelesai.id_bayaran_pusaka)
    #set ($id_bayaran_baitulmal = $dataSelesai.id_bayaran_baitulmal)
#end

#if( $id_bayaran_perintah == "" )
	#set ( $id_bayaran_perintah = "0" )
    #set ( $id_bayaran_perintah = "0" )
#end

#if( $id_bayaran_pusaka == "" )
	#set ( $id_bayaran_pusaka = "0" )
    #set ( $bayaran_pusaka = "0" )
#end

#if( $id_bayaran_baitulmal == "" )
	#set ( $id_bayaran_baitulmal = "0" )
    #set ( $bayaran_baitulmal = "0" )
#end

#if ($idstatus == "41" || $idstatus == "44" || $idstatus == "47" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" || $idstatus == "25" || $idstatus == "21" || $idstatus == "64" || $idstatus == "163" || $idstatus == "164" || $idstatus == "165" || $idstatus == "166" || $idstatus == "167" || $idstatus == "180" )
<!--123 -->
#if ( $mode == "view" )
<!-- abc -->
<input type ="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
<!--<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>-->
<input type="hidden" name="id_bayaran_perintah" id="id_bayaran_perintah" value="$id_bayaran_perintah"/>
<input type="hidden" name="id_bayaran_pusaka" id="id_bayaran_pusaka" value="$id_bayaran_pusaka"/>
<input type="hidden" name="id_bayaran_baitulmal" id="id_bayaran_baitulmal" value="$id_bayaran_baitulmal"/>
<tr>
  <td><fieldset>
    <legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
    <table width="100%"  cellspacing="1" cellpadding="1" border="0">
      <tr>
        <td>Pegawai Pengendali</td>
        <td width="34%">:&nbsp;$selectViewPegawai</td>
        #if ( $tarikh == "perintah" )
        <td width="11%">Tarikh Perintah</td>
        <td width="9%">:&nbsp;
          <input name="txdTarikhPerintah" value="$!tarikh_perintah" size="11" id="txdTarikhPerintah" type="text" readonly class="disabled" />
        </td>
        #end
        
        #if ( $tarikh == "bicara" )
        <td width="18%">Tarikh Perbicaraan Terakhir</td>
        <td width="10%">:&nbsp;
          <input name="txdTarikhPerintah" value="$!tarikh_bicara" size="11" id="txdTarikhPerintah" type="text" readonly class="disabled" />
        </td>
        #end </tr>
      <tr>
        <td width="18%">Keputusan Perbicaraan</td>
        <td colspan="5">:&nbsp;
          <input name="flag_jenis_keputusan" type="radio" value="0" $TEMPcheckedSelesai disabled onClick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" type="radio" value="1" $TEMPcheckedTangguh disabled onClick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" type="radio" value="2" $TEMPcheckedBatal disabled onClick="tab_batal()" />
          Batal</td>
      </tr>
    </table>
    </fieldset></td>
</tr>
#foreach($dataSelesai in $dataJumlahBayaran)
			#set ($jumlah_harta_tarikhmohon = $dataSelesai.jumlah_harta_tarikhmohon)
		#end
<tr>
<td>
<fieldset>
<table width="100%">
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
  #if(  $flag == "selesai" )
  <!-- value selesai 0 -->
  <li class="TabbedPanelsTab" onClick="setSelected(0);SelesaiAdd()">SELESAI</li>
  #end
  #if(  $flag == "tangguh" )
  <!-- value selesai 1 -->
  <li class="TabbedPanelsTab" onClick="setSelected(1);TangguhAdd()">TANGGUH</li>
  #end
  #if( $flag == "batal" )
  <!-- value selesai 2 -->
  <li class="TabbedPanelsTab" onClick="setSelected(2);BatalAdd()">BATAL</li>
  #end
</ul>
<div class="TabbedPanelsContentGroup">
  <!----------------------------------------- VIEW SELESAI ----------------------------------------------->
  <div class="TabbedPanelsContent">
  #if ( $flag == "selesai")
  <input type="hidden" name="id_jenisbayaranPerintah" id="id_jenisbayaranPerintah" value="24"/>
  <input type="hidden" name="id_jenisbayaranCukaiPusaka" id="id_jenisbayaranCukaiPusaka" value="26"/>
  <input type="hidden" name="id_jenisbayaranBaitulMal" id="id_jenisbayaranBaitulMal" value="29"/>
  #foreach($dataSelesai in $dataJumlahBayaran)
  #set ($jumlah_harta_tarikhmohon = $dataSelesai.jumlah_harta_tarikhmohon)
  #end
  <table width="100%" border="0">
    <tr>
      <td width="33%" valign="top">
      <fieldset>
      <legend>Bayaran Perintah</legend>
      <table width="100%">
        <tr>
          <td width="47%"><div align="left">Jumlah Harta&nbsp;&nbsp;(RM)</div></td>
          <td width="2%"><div align="right">:</div></td>
          <td width="51%"><label>
            <input type="text" size="15" name="txtJumHarta" onBlur="this.value=this.value.toUpperCase();" id="txtJumHarta" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($txtJumHarta)" />
            </label></td>
        </tr>
        <tr>
          <td><div align="left">Jumlah Bayaran&nbsp;&nbsp;(RM)</div></td>
          <td><div align="right">:</div></td>
          <td><label>
            <input type="text" size="15" name="txtJumBayaran" id="txtJumBayaran" readonly class="disabled" value="$!Util.formatDecimal($!bayaran_perintah)" />
            </label></td>
        </tr>
        <tr>
          <td valign="top">No Resit
        </div>
        </td>
        
        <td><div align="right">:</div></td>
          <td>
          <label><span style="text-transform:uppercase;">
          <input type="text" size="15" name="txtNomborResitPerintah" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintah" style="text-transform:uppercase;" readonly value="$NoResit_perintah" class="disabled" />
          </td>
          </span></label>
        </tr>
        <tr>
          <td><div align="left">Tarikh Bayaran</div></td>
          <td><div align="right">:</div></td>
          <td><label>
            <input name="txdTarikhBayaranPerintah" size="15" id="txdTarikhBayaranPerintah" type="text" readonly value="$TarikhBayaran_perintah" class="disabled" />
            </label></td>
        </tr>
      </table>
      </fieldset>
    </td>
    
    <td width="34%" valign="top"><fieldset>
        <legend>Bayaran Cukai</legend>
        <table width="100%" border="0">
          <tr>
            <td width="46%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
            <td width="2%"><div align="right" class="style38">:</div></td>
            <td width="52%"><label>
              <input type="text" size="15" name="txtJumBayaranPusaka" value="$!Util.formatDecimal($!bayaran_pusaka)" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaranPusaka" style="text-transform:uppercase;" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPusaka" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPusaka" style="text-transform:uppercase;" value="$!NoResit_pusaka" readonly class="disabled" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input name="txdTarikhBayaranPusaka" value="$!TarikhBayaran_pusaka" size="15" id="txdTarikhBayaranPusaka" type="text" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td colspan="3">&nbsp;</td>
          </tr>
        </table>
        </fieldset></td>
      <td width="33%" valign="top"><fieldset>
        <legend>Bayaran Baitulmal</legend>
        <table width="100%" border="0">
          <tr>
            <td width="53%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
            <td width="3%"><div align="right">:</div></td>
            <td width="44%"><label>
              <input type="text" size="15" name="txtJumBayaranBaitulmal" value="$!Util.formatDecimal($!bayaran_baitulmal)" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaranBaitulmal" style="text-transform:uppercase;" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitBaitulmal" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmal" style="text-transform:uppercase;" readonly value="$NoResit_baitulmal" class="disabled" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input name="txdTarikhBayaranBaitulmal" value="$TarikhBayaran_baitulmal" size="15" id="txdTarikhBayaranBaitulmal" type="text" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td colspan="3">&nbsp;</td>
          </tr>
        </table>
        </fieldset></td>
    </tr>
    <tr>
      <td colspan="3" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" width="100%" >Catatan Keputusan Perbicaraan  : </td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><textarea name="txtCatatanSelesaiVIEW" id="txtCatatanSelesaiVIEW" cols="80%" rows="8" >$!catatan</textarea>
        <!-- VIEW MODE -->
        <script> 
              									var area = new FCKeditor("txtCatatanSelesaiVIEW");
									      		area.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area.ReplaceTextarea();             	
								              </script>
      </td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><div  class="disabled" id="word_count5">
      </td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />   -->
          <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembaliSenaraiFail('$noFail');"/>
          <!--<input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$idPermohonanSimati','$idpermohonan','$idstatus')" /> -->
          #end
          
          #if ($flagFromSenaraiPermohonanSek8 == 'yes')
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
          #end
          
          #if ( $flagForView == 'yes' )
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$noFail')"/>
          #end
          
          #if ( $idstatus == "41" || $idstatus == "25" )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onClick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />
          <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$idPermohonanSimati','$idpermohonan','$idstatus')" />
          #end </div></td>
    </tr>
  </table>
  #end </div>
<!-- close div $flag == "selesai" -->
<!----------------------------------------- END VIEW SELESAI ----------------------------------------------->
<!----------------------------------------- VIEW TANGGUH ----------------------------------------------->
<div class="TabbedPanelsContent"> #if ( $flag == "tangguh")
  <table width="100%" cellspacing="2" border="0">
    <input type="hidden" name="id_jenisbayaran" id="id_jenisbayaran" value="24"/>
    <tr align="center">
      <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onClick="SenaraiBicara('$idpermohonan')" /></td>
    </tr>
    <tr align="center">
      <td colspan="2" align="left">&nbsp;</td>
    </tr>
    <tr align="center">
      <td colspan="2" align="left">Alasan Keputusan Perbicaraan :</td>
    </tr>
    <tr align="center">
      <td width="57%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="1" disabled $TEMPcheckedTidakHadir >
        Pemohon / Waris Tidak Hadir </td>
      <td width="43%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio" value="2" disabled $TEMPcheckedWarisTidakLengkap >
        Senarai Waris Tidak Lengkap </td>
    </tr>
    <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio" value="3" disabled $TEMPcheckedMahkamahTinggi>
        Menunggu Keputusan Rujukan Mahkamah Syariah </td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio" value="4" disabled $TEMPcheckedBuktiTidakLengkap >
        Bukti Kematian Tidak Lengkap </td>
    </tr>
    <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio" value="5" disabled onClick="RulerOfTheState('$idpermohonan','$id_perbicaraan','$id_perintah')" $TEMPcheckedMahkamahSyariah >
        Menunggu Keputusan Rujukan Kepada Ruler of The State </td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" disabled onClick="PertelingkahanKolateral('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
        Pertelingkahan Kolateral </td>
    </tr>
    <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi</td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="8" disabled $tempcheckedsuratsetuju />
        Menunggu Surat Akuan Persetujuan</td>
    </tr>
    <tr align="center">
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="7" disabled $TEMPcheckedSijilFaraid>
        Menunggu Sijil Faraid</td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="9" disabled $tempcheckedsebablain />
        Sebab-sebab Lain</td>
    </tr>
    <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tempoh Menunggu 
        :&nbsp;&nbsp;&nbsp;
       
        <input type="text" size="5" maxlength="2" name="txtTempoh" value="$!tempoh_tunggu_faraid" onBlur="this.value=this.value.toUpperCase();" id="txtTempoh" style="text-transform:uppercase;" readonly class="disabled" />
        Hari</td>
      <td align="left">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >Catatan / Keterangan Tangguh  : </td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><textarea name="txtCatatanTangguhVIEW" id="txtCatatanTangguhVIEW" cols="80%" rows="8">$!sebab_tangguh</textarea>
        <!-- ADD MODE -->
        <script> 
												var area = new FCKeditor("txtCatatanTangguhVIEW");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
      </td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >Pendapat / Keputusan Mahkamah  : </td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><textarea name="txtPendapatTangguhVIEW" id="txtPendapatTangguhVIEW" cols="80%" rows="8">$!keputusan_mahkamah</textarea>
        <!-- ADD MODE -->
        <script>   												   	
												var area = new FCKeditor("txtPendapatTangguhVIEW");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
      </td>
    </tr>
    <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onClick="javascript:Skrin_KemaskiniTangguh('$idpermohonan','$id_perbicaraan');" />
          <input type="button" name="cmdKembali"  value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')" />
          #end
          
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <input type="button" name="cmdKembali"  value="Kembali" onClick="javascript: kembaliSenaraiFail('$noFail');"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')" />
          #end          
          #end
          
          #if ($flagFromSenaraiPermohonanSek8 == 'yes')
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')" />
          #end
          #end       
          
          #if ( $flagForView == 'yes' )
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$noFail')"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdTeruskan" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')"/>
          #end     
          #end </div></td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ></td>
    </tr>
  </table>
  #end </div>
<!-- close div $flag == "tangguh" -->
<!----------------------------------------- END VIEW TANGGUH ----------------------------------------------->
<!----------------------------------------- VIEW BATAL ----------------------------------------------->
<div class="TabbedPanelsContent">
#if( $flag == "batal" )
<table width="100%" cellspacing="2" border="0">
  <tr>
  
  <td>
  
  <fieldset>
  
  <legend><strong>Maklumat Batal</strong></legend>
  <!--<br/>  -->
  <table width="100%"  cellpadding="0" cellspacing="2" border="0">
    <tr align="center">
      <td width="1%">&nbsp;</td>
      <td width="99%"align="left">&nbsp;</td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" $ type="radio" value="1" $TEMPcheckedMahkamahTinggiWasiat  disabled>
        Pindah ke Mahkamah Tinggi kerana ada wasiat </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="2" $TEMPcheckedTidakHadir3Kali disabled>
        Tidak hadir setelah dipanggil 3 kali </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="3" $TEMPcheckedPermintaanPemohon disabled>
        Atas Permintaan Pemohon </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="4" $TEMPcheckedMahkamahTinggi2Juta disabled>
        Pindah ke Mahkamah Tinggi kerana harta melebihi RM2 juta </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio" value="5" $TEMPcheckedSebabLainBatal disabled >
        Sebab-sebab lain </td>
    </tr>
    <tr align="center">
      <td colspan=2>&nbsp;</td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left">Catatan :</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><textarea name="txtCatatanBatalView" id="txtCatatanBatalView" cols="80%" rows="8">$!sebab_batal</textarea>
        <!-- ADD MODE -->
        <script> 
																   	
																var area = new FCKeditor("txtCatatanBatalView");
					      										area.BasePath = '/${appname}/library/fck/' ;
																area.ReplaceTextarea();  
																           	
												              </script>
      </td>
    </tr>
    <!--
									                 	<tr align="center">
									                    	<td >&nbsp;</td>
									                      	<td align="left">
									                      		
									                      	</td>  
									                    </tr>  
																							                    								                    
														<tr>
					
															<td colspan="2" width="100%" >
																<div align="left">           
												      				$!perhatian
												     			 </div>
												      		</td>                                											
															
														</tr>
														-->
    <tr>
      <td colspan="2" width="100%" >&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onClick="javascript:Skrin_KemaskiniBatal('$idpermohonan','$id_perbicaraan');" />
          <input type="button" name="cmdKembali"  value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembaliSenaraiFail('$noFail');"/>
          #end
          
          #if ($flagFromSenaraiPermohonanSek8 == 'yes')
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
          #end        
          
          #if ( $flagForView == 'yes' )
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$noFail')"/>
          #end
          <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
        </div></td>
    </tr>
    </fieldset>
    
    </td>
    
    </tr>
    
  </table>
  #end
  </div>
  
  <!-- close div $flag == "batal" -->
  <!----------------------------------------- END VIEW BATAL ----------------------------------------------->
  </div>
  
  <div>
  
  </td>
  
  </tr>
  
</table>
</fieldset>
</td>
</tr>
#end ## END OF #if ( $mode == "view" )

#end ## END OF #if ($idstatus == "41" || $idstatus == "44" || $idstatus == "47" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" || $idstatus == "25" || $idstatus == "21" || $idstatus == "64" || $idstatus == "163" || $idstatus == "164" || $idstatus == "165" || $idstatus == "166" || $idstatus == "167" || $idstatus == "180" )
<!--------------------------------------------------- END VIEW MODE ---------------------------------------------->
<!--------------------------------------------------- EDIT MODE ---------------------------------------------->
#if( $mode == "edit" )
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
<!--<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>-->
<input type="hidden" name="id_bayaran_perintah" id="id_bayaran_perintah" value="$id_bayaran_perintah"/>
<input type="hidden" name="id_bayaran_pusaka" id="id_bayaran_pusaka" value="$id_bayaran_pusaka"/>
<input type="hidden" name="id_bayaran_baitulmal" id="id_bayaran_baitulmal" value="$id_bayaran_baitulmal"/>



#foreach($dataPerintah1 in $dataPerintahView)
		    #set ($idprintah = $dataPerintah1.id_perintah)
		    #set ($idBicara = $dataPerintah1.id_perbicaraan)
		    #set ($tarikh_perintah = $dataPerintah1.tarikh_perintah)
		    #set ($idUnitPskView = $dataPerintah1.idUnitPskView)
		    #set ($flag_jenis_keputusan = $dataPerintah1.flag_jenis_keputusan)
		    #set ($catatan = $dataPerintah1.catatan)
		    #set ($sebab_batal = $dataPerintah1.sebab_batal)
		    #set ($sebab_tangguh = $dataPerintah1.sebab_tangguh)
		    #set ($keputusan_mahkamah = $dataPerintah1.keputusan_mahkamah)
		    #set ($tempoh_tunggu_faraid = $dataPerintah1.tempoh_tunggu_faraid)
		#end
<tr>
  <td><fieldset>
    <legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
    <table width="100%"  cellspacing="1" cellpadding="1" border="0">
      <tr>
        <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
        <td width="28%">:&nbsp;$selectViewPegawai</td>
        #if ($tarikh == "perintah" )
        <td width="12%"><font color="red">*</font>&nbsp;Tarikh Perintah</td>
        <td width="12%">:&nbsp;
          <input name="txdTarikhPerintahEDIT" value="$!tarikh_bicara" size="10" id="txdTarikhPerintahEDIT" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhPerintahEDIT',false,'dmy');" /></td>
        #end
        
        #if ($tarikh == "bicara" )
        <td width="18%"><font color="red">*</font>&nbsp;Tarikh Perbicaraan &nbsp;&nbsp;&nbsp;Terakhir</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintahEDIT" value="$!tarikh_bicara" size="10" id="txdTarikhPerintahEDIT" type="text" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhPerintahEDIT',false,'dmy');" /> </td>
        #end </tr>
      <tr>
        <td width="17%"><font color="red">*</font>&nbsp;Keputusan Perbicaraan</td>
        <td colspan="5">:&nbsp;
          <input name="flag_jenis_keputusan" type="radio" disabled value="0" $TEMPcheckedSelesai onClick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" type="radio" disabled value="1" $TEMPcheckedTangguh onClick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" type="radio" disabled value="2" $TEMPcheckedBatal onClick="tab_batal()" />
          Batal</td>
      </tr>
      <!--By Mohamad Rosli 21/02/2011-->
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><font color="blue"><u><strong>Nota</strong></u></font> <br>
          <!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
          <b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa <br>
          <b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara <br>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table>
    </fieldset></td>
</tr>
#foreach($dataSelesai in $dataJumlahBayaran)
			#set ($jumlah_harta_tarikhmohon = $dataSelesai.jumlah_harta_tarikhmohon)
		#end
<tr>
<td>
<fieldset>
<table width="100%">
  <tr>
  
  <td>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  
  <ul class="TabbedPanelsTabGroup">
    #if(  $flag == "selesai" )
    <!-- value selesai 0 -->
    <li class="TabbedPanelsTab" onClick="setSelected(0);Selesai()">SELESAI</li>
    #end
    #if(  $flag == "tangguh" )
    <!-- value selesai 1 -->
    <li class="TabbedPanelsTab" onClick="setSelected(1);Tangguh()">TANGGUH</li>
    #end
    #if( $flag == "batal" )
    <!-- value selesai 2 -->
    <li class="TabbedPanelsTab" onClick="setSelected(2);Batal()">BATAL</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <!----------------------------------------- EDIT SELESAI ----------------------------------------------->
    <div class="TabbedPanelsContent">
    
    #if ( $flag == "selesai") 
    
    #foreach($dataSelesai in $dataJumlahBayaran)
    #set ($jumlah_harta_tarikhmohon = $dataSelesai.jumlah_harta_tarikhmohon)
    #end
    <table width="100%" border="0">
      <tr>
        <td width="33%" valign="top">
        <fieldset>
        <legend>Bayaran Perintah</legend>
        <table width="100%">
          <input type="hidden" name="txtIdSimatiPenting" value="$listob.idSimati" >
          <input type="hidden" name="txtIdOb" value="$listob.idOb" >
          <tr>
            <td width="47%"><div align="left">Jumlah Harta&nbsp;&nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumHartaEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtJumHartaEDIT" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumHarta)" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">Jumlah Bayaran&nbsp;&nbsp;(RM)</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input type="text" size="15" name="txtJumBayaranEDIT" id="txtJumBayaranEDIT" value="$!Util.formatDecimal($!bayaran_perintah)" onBlur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onKeyUp="validateNumber(this,this.value);" />
              <input type="hidden" size="12" name="txtJumBayaranEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaranEDIT" style="text-transform:uppercase;" readonly class="disabled" value="$bayaran_perintah" />
              </label></td>
          </tr>
          <tr>
            <td valign="top">No Resit
          </div>
          </td>
          
          <td><div align="right">:</div></td>
            <td>
            <label><span style="text-transform:uppercase;">
            <input type="text" size="15" name="txtNomborResitPerintahEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintahEDIT" style="text-transform:uppercase;" value="$!NoResit_perintah"/>
            </td>
            </span></label>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input type="text" size="15" name="txdTarikhBayaranPerintahEDIT" id="txdTarikhBayaranPerintahEDIT" value="$!TarikhBayaran_perintah" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranPerintahEDIT',false,'dmy');" /> </label></td>
          </tr>
        </table>
        </fieldset>
      </td>
      
      <td width="34%" valign="top"><fieldset>
          <legend>Bayaran Cukai</legend>
          <table width="100%" border="0">
            <tr>
              <td width="46%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
              <td width="2%"><div align="right" class="style38">:</div></td>
              <td width="52%"><label>
                <input type="text" size="15" name="txtJumBayaranPusakaEDIT" value="$!bayaran_pusaka" id="txtJumBayaranPusakaEDIT" onBlur="validateNumber(this,this.value);" onKeyUp="validateNumber(this,this.value);" />
                <input type="hidden" size="12" name="txtJumBayaranPusakaEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaranPusakaEDIT" style="text-transform:uppercase;" readonly="readonly" class="disabled" value="$!bayaran_pusaka" />
                </label></td>
            </tr>
            <tr>
              <td><div align="left">No Resit</div></td>
              <td><div align="right">:</div></td>
              <td><label><span style="text-transform:uppercase;">
                <input type="text" size="15" name="txtNomborResitPusakaEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitPusakaEDIT" style="text-transform:uppercase;" value="$!NoResit_pusaka"/>
                </span></label></td>
            </tr>
            <tr>
              <td><div align="left">Tarikh Bayaran</div></td>
              <td><div align="right">:</div></td>
              <td><label>
                <input type="text" size="15" name="txdTarikhBayaranPusakaEDIT" value="$!TarikhBayaran_pusaka" id="txdTarikhBayaranPusakaEDIT" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranPusakaEDIT',false,'dmy');" /> </label></td>
            </tr>
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
          </table>
          </fieldset></td>
        <td width="33%" valign="top"><fieldset>
          <legend>Bayaran Baitulmal</legend>
          <table width="100%" border="0">
            <tr>
              <td width="53%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
              <td width="3%"><div align="right">:</div></td>
              <td width="44%"><label>
                <input type="text" size="15" name="txtJumBayaranBaitulmalEDIT" value="$!Util.formatDecimal($!bayaran_baitulmal)" id="txtJumBayaranBaitulmalEDIT" onBlur="validateNumber(this,this.value);" onKeyUp="validateNumber(this,this.value);" />
                <input type="hidden" size="12" name="txtJumBayaranBaitulmalEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtJumBayaranBaitulmalEDIT" style="text-transform:uppercase;" readonly="readonly" class="disabled" value="$bayaran_baitulmal" />
                </label></td>
            </tr>
            <tr>
              <td><div align="left">No Resit</div></td>
              <td><div align="right">:</div></td>
              <td><label><span style="text-transform:uppercase;">
                <input type="text" size="15" name="txtNomborResitBaitulmalEDIT" onBlur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmalEDIT" style="text-transform:uppercase;" value="$!NoResit_baitulmal"/>
                </span></label></td>
            </tr>
            <tr>
              <td><div align="left">Tarikh Bayaran</div></td>
              <td><div align="right">:</div></td>
              <td><label>
                <input type="text" size="15" name="txdTarikhBayaranBaitulmalEDIT" value="$!TarikhBayaran_baitulmal" id="txdTarikhBayaranBaitulmalEDIT" onBlur="check_date(this);" onKeyUp="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onClick="displayDatePicker('txdTarikhBayaranBaitulmalEDIT',false,'dmy');" /> </label></td>
            </tr>
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
          </table>
          </fieldset></td>
      </tr>
      <tr>
        <td colspan="3" width="100%" >&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" width="100%" >Catatan Keputusan Perbicaraan  : </td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><textarea name="txtCatatanSelesaiEDIT" cols="80%" rows="8" id="txtCatatanSelesaiEDIT">$!catatan</textarea>
          <!-- VIEW MODE -->
          <script> 
              									var area = new FCKeditor("txtCatatanSelesaiEDIT");
									      		area.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area.ReplaceTextarea();             	
								              </script>
        </td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><div  class="disabled" id="word_count">
        </td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><div align="center">
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript: kemaskini_selesai('$idpermohonan','$id_perbicaraan','$id_perintah');" />
            <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          </div></td>
      </tr>
    </table>
    #end </div>
  <!-- close div $flag == "selesai" -->
  <!----------------------------------------- END EDIT SELESAI ----------------------------------------------->
  <!----------------------------------------- EDIT TANGGUH ----------------------------------------------->
  <div class="TabbedPanelsContent"> #if ( $flag == "tangguh")
    <fieldset>
    <legend><strong>Maklumat Tangguh</strong></legend>
    <input type="hidden" name="id_jenisbayaran" id="id_jenisbayaran" value="24"/>
    <table width="100%" cellspacing="2" border="0">
      <tr align="center">
        <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onClick="SenaraiBicara('$idpermohonan')" /></td>
      </tr>
      <tr align="center">
        <td colspan="2" align="left">&nbsp;</td>
      </tr>
      <tr align="center">
        <td colspan="2" align="left">Alasan Keputusan Perbicaraan :</td>
      </tr>
      <tr align="center">
        <td width="57%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="1" $TEMPcheckedTidakHadir>
          Pemohon / Waris Tidak Hadir </td>
        <td width="43%" align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="2" $TEMPcheckedWarisTidakLengkap >
          Senarai Waris Tidak Lengkap </td>
      </tr>
      <tr align="center">
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="3" $TEMPcheckedMahkamahTinggi>
          Menunggu Keputusan Rujukan Mahkamah Syariah </td>
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="4" $TEMPcheckedBuktiTidakLengkap >
          Bukti Kematian Tidak Lengkap </td>
      </tr>
      <tr align="center">
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="5" onClick="RulerOfTheStateEdit('$idpermohonan')" $TEMPcheckedMahkamahSyariah >
          Menunggu Keputusan Rujukan Kepada Ruler of The State </td>
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" onClick="PertelingkahanKolateralEdit('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
          Pertelingkahan Kolateral </td>
      </tr>
      <tr align="center">
        <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi</td>
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="8" $TEMPcheckedSuratSetuju />
          Menunggu Surat Akuan Persetujuan</td>
      </tr>
      <tr align="center">
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="7" $TEMPcheckedSijilFaraid >
          Menunggu Sijil Faraid </td>
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="9" $TEMPcheckedSebabLain />
          Sebab-sebab Lain </td>
      </tr>
      <tr align="center">
        <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tempoh Menunggu 
          :&nbsp;&nbsp;&nbsp;
          <input type="text" size="5" maxlength="2" name="txtTempoh" value="$!tempoh_tunggu_faraid" onBlur="this.value=this.value.toUpperCase();" id="txtTempoh" style="text-transform:uppercase;"  />
          Hari </td>
        <td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >Catatan / Keterangan Tangguh  : </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><textarea name="txtCatatanTangguhEDIT" id="txtCatatanTangguhEDIT" cols="80%" rows="8">$!sebab_tangguh</textarea>
          <!-- ADD MODE -->
          <script> 												   	
												var area = new FCKeditor("txtCatatanTangguhEDIT");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
        </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count2">
        </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >Pendapat / Keputusan Mahkamah  : </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><textarea name="txtPendapatTangguhEDIT" id="txtPendapatTangguhEDIT" cols="80%" rows="8">$!keputusan_mahkamah</textarea>
          <!-- ADD MODE -->
          <script> 												   	
												var area = new FCKeditor("txtPendapatTangguhEDIT");
	      										area.BasePath = '/${appname}/library/fck/' ;
												area.ReplaceTextarea();             	
								              </script>
        </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count3">
        </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div align="left"> $!perhatian </div></td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div align="center">
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript: Simpan_Edit_Tangguh('$idpermohonan','$id_perbicaraan');" />
            <input name="cmdBatal" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
          </div></td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ></td>
      </tr>
    </table>
    </fieldset>
    #end </div>
  <!-- close div $flag == "tangguh" -->
  <!----------------------------------------- END EDIT TANGGUH ----------------------------------------------->
  <!----------------------------------------- EDIT BATAL ----------------------------------------------->
  <div class="TabbedPanelsContent">
  
  #if( $flag == "batal" )
  <table width="100%" cellspacing="2" border="0">
    <tr>
    
    <td>
    
    <fieldset>
    
    <legend><strong>Maklumat Batal</strong></legend>
    <!--<br/>  -->
    <table width="100%"  cellpadding="0" cellspacing="2" border="0">
      <tr align="center">
        <td width="1%"><font color="red">*</font></td>
        <td width="99%"align="left">&nbsp;Alasan Batal Perbicaraan : </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="1" $TEMPcheckedMahkamahTinggiWasiat onClick="MahkamahTinggiEdit('$idpermohonan','$id_perbicaraan','$id_perintah')" >
          Pindah ke Mahkamah Tinggi kerana ada wasiat </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="2" $TEMPcheckedTidakHadir3Kali >
          Tidak hadir setelah dipanggil 3 kali </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="3" $TEMPcheckedPermintaanPemohon >
          Atas Permintaan Pemohon </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="4" $TEMPcheckedMahkamahTinggi2Juta >
          Pindah ke Mahkamah Tinggi kerana harta melebihi RM2 juta </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio" value="5" $TEMPcheckedSebabLainBatal>
          Sebab-sebab lain </td>
      </tr>
      <tr align="center">
        <td colspan=2>&nbsp;</td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left">Catatan :</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><textarea name="txtCatatanBatalEDIT" id="txtCatatanBatalEDIT" cols="80%" rows="8">$!sebab_batal</textarea>
          <!-- ADD MODE -->
          <script> 
																   	
																var area = new FCKeditor("txtCatatanBatalEDIT");
					      										area.BasePath = '/${appname}/library/fck/' ;
																area.ReplaceTextarea();  
																           	
												              </script>
        </td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count4">
        </td>
      </tr>
      <tr align="center">
        <td >&nbsp;</td>
        <td align="left"></td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div align="left"> $!perhatian </div></td>
      </tr>
      <tr>
        <td colspan="2" width="100%" >&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" width="100%" ><div align="center">
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onClick="javascript: Simpan_Edit_Batal('$idpermohonan','$id_perbicaraan');" />
            <input name="cmdKembali" type="button" value="Kembali" onClick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          </div></td>
      </tr>
      </fieldset>
      
      </td>
      
      </tr>
      
    </table>
    #end
    </div>
    
    <!-- close div $flag == "batal" -->
    <!----------------------------------------- END EDIT BATAL ----------------------------------------------->
    </div>
    
    <!-- close div tabbed panels content group -->
    </div>
    
    <!-- close div TabbedPanels1 -->
    </td>
    
    </tr>
    
  </table>
  </fieldset>
  
  </td>
  
  </tr>
  
  #end ## END OF #if ( $mode == "edit" )
  <!--------------------------------------------------- END EDIT MODE ---------------------------------------------->
  <tr>
    <td><fieldset id="tableReport1" style="display:none;">
      <legend><strong>Senarai Laporan</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td><a href="#" class="style2" onClick="javascript:cetaksuratBatalBicara('$noFail','$idFail','$id_perbicaraan')"><font color="blue"> Surat Batal Perbicaraan </font></a></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<!--END CODE-->
<script>

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetaksuratBatalBicara(noFail,idFail,id_perbicaraan) {
	//alert(noFail);alert(idFail);alert(id_perbicaraan);
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan+"&report=suratBatalBicara&flagReport=S";

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	    document.${formName}.command.value="getDaftarStatus";	
		document.${formName}.action="";
}

function ForView(noFail) {
    document.${formName}.action =
"$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="
+noFail;
    document.${formName}.submit();
}

function goNotis(id_permohonan,idstatus) {

	if(idstatus == '44'){
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	}
	else if(idstatus == '173'){
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	}
	else if(idstatus == '175'){
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	}
	else if(idstatus == '177'){
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
	}			
	else{
		alert("Status permohonan tidak sah untuk notis perbicaraan");
		return(false);
	}	
	document.${formName}.idstatus.value = idstatus;
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function seterusnya(idPermohonanSimati,idpermohonan,idstatus){	

	if(idstatus == '41'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";			
	}
	else if (idstatus == '21'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
	}	
	else if (idstatus == '25'){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
	}		
	else{
		alert("Status permohonan tidak sah untuk perintah perbicaraan");
		return(false);	
	}	
	document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.idstatus.value = idstatus;	
	document.${formName}.submit();	
}

function SenaraiBicara(idpermohonan,command) {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8SenaraiTangguh?idpermohonan="+idpermohonan+"&command="+command;
	var hWnd = window.open(url,'displayfile','width=800,height=400, resizable=yes,scrollbars=yes');	
}

function Simpan_Selesai(idpermohonan,id_perbicaraan){   
	
	var txtCatatanSelesai = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanSelesai = oEditor.GetXHTML(true); 		
	}
	 	
	var currentTime = new Date()
	var str1  = document.getElementById("txdTarikhBayaranPerintah").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);
	
	var str2  = document.getElementById("txdTarikhBayaranPusaka").value;   
    var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10);
    var yr2   = parseInt(str2.substring(6,10),10);   
    var date2 = new Date(yr2, mon2, dt2);
	
	var str3  = document.getElementById("txdTarikhBayaranBaitulmal").value;   
    var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10);
    var yr3   = parseInt(str3.substring(6,10),10);   
    var date3 = new Date(yr3, mon3, dt3);
	
	var str4  = document.getElementById("tarikhMohon").value;
	var dt4   = parseInt(str4.substring(0,2),10);
   	var mon4  = parseInt(str4.substring(3,5),10);
   	var yr4   = parseInt(str4.substring(6,10),10);	
	var trMOHON = new Date(yr4, mon4, dt4);
	
	var str5  = document.getElementById("txdTarikhPerintah").value;
	var dt5   = parseInt(str5.substring(0,2),10);
   	var mon5  = parseInt(str5.substring(3,5),10);
   	var yr5   = parseInt(str5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);
	
	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;
	}
	if(document.${formName}.txtJumBayaran.value == ""){
		alert("Sila masukkan \"Jumlah Bayaran\" terlebih dahulu.");
  		document.${formName}.txtJumBayaran.focus(); 
		return;
	}	
	if(document.${formName}.txtNomborResitPerintah.value == ""){
		alert("Sila masukkan \"No Resit\" terlebih dahulu.");
  		document.${formName}.txtNomborResitPerintah.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhBayaranPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran\" terlebih dahulu.");
  		document.${formName}.txdTarikhBayaranPerintah.focus(); 
		return;
	}	
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Bayaran tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhBayaranPerintah.focus();
	 	return;	
	}	
	if(date5 < trMOHON){
   		alert("Sila pastikan Tarikh Perintah/Tarikh Perbicaraan Terakhir tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhPerintah.focus();
	 	return;							
	}else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "Simpan_Selesai";
	document.${formName}.submit();
	}
}

function Simpan_Tangguh(idpermohonan,id_perbicaraan)	{

	var txtCatatanTangguh = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanTangguh = oEditor.GetXHTML(true); 		
	}
	
	var txtPendapatTangguh = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtPendapatTangguh = oEditor.GetXHTML(true); 		
	}
	
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.flag_tangguh.length;  i++){
		if (${formName}.flag_tangguh[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Alasan Keputusan Perbicaraan\" terlebih dahulu.");
		return (false);
	}	
	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;		
	}
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";	
	document.${formName}.command.value = "Simpan_Tangguh";
	document.${formName}.submit();
	}
}

function Skrin_Kemaskini(idpermohonan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "Skrin_Kemaskini";
	document.${formName}.submit();	
}

function Skrin_KemaskiniTangguh(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "Skrin_KemaskiniTangguh";
	document.${formName}.submit();	
}


function kemaskini_selesai(idpermohonan,id_perbicaraan,id_perintah) {
	var currentTime = new Date()
	var str1  = document.getElementById("txdTarikhBayaranPerintahEDIT").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);
	
	var str2  = document.getElementById("txdTarikhBayaranPusakaEDIT").value;   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10)-1;
    var yr2   = parseInt(str2.substring(6,10),10);   
    var date2 = new Date(yr2, mon2, dt2);
	
	var str3  = document.getElementById("txdTarikhBayaranBaitulmalEDIT").value;   
    var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);   
    var date3 = new Date(yr3, mon3, dt3);	
	
	var str5  = document.getElementById("txdTarikhPerintahEDIT").value;
	var dt5   = parseInt(str5.substring(0,2),10);
   	var mon5  = parseInt(str5.substring(3,5),10);
   	var yr5   = parseInt(str5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);

	var str4  = document.getElementById("tarikhMohon").value;
	var dt4   = parseInt(str4.substring(0,2),10);
   	var mon4  = parseInt(str4.substring(3,5),10);
   	var yr4   = parseInt(str4.substring(6,10),10);	
	var trMOHON = new Date(yr4, mon4, dt4);

	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhPerintahEDIT.value == ""){
		alert("Sila pilih \"Tarikh Perbicaraan Terakhir/Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerintahEDIT.focus(); 
		return;
	}	
	if(date5 < trMOHON){
   		alert("Sila pastikan Tarikh Perintah/Tarikh Perbicaraan Terakhir tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhPerintahEDIT.focus();
	 	return;
	}	
	if(document.${formName}.txtNomborResitPerintahEDIT.value == ""){
		alert("Sila masukkan \"No Resit\" terlebih dahulu.");
  		document.${formName}.txtNomborResitPerintahEDIT.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhBayaranPerintahEDIT.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran\" terlebih dahulu.");
  		document.${formName}.txdTarikhBayaranPerintahEDIT.focus(); 
		return;
	}	
/*	if ( date1 > currentTime ){
		alert("Sila pastikan Tarikh Bayaran Perintah tidak melebihi dari tarikh hari ini");	
		document.${formName}.txdTarikhBayaranPerintahEDIT.focus();
		return;
	}	
	if ( date2 > currentTime ){
		alert("Sila pastikan Tarikh Bayaran Cukai tidak melebihi dari tarikh hari ini");	
		document.${formName}.txdTarikhBayaranPusakaEDIT.focus();
		return;
	}
	if ( date3 > currentTime ){
		alert("Sila pastikan Tarikh Bayaran Baitulmal tidak melebihi dari tarikh hari ini");	
		document.${formName}.txdTarikhBayaranBaitulmalEDIT.focus();
		return;
	}*/
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "kemaskini_selesai";
	document.${formName}.submit();
	}
}
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}
function tab_selesai() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "tab_selesai";
	document.${formName}.submit();
}
function tab_tangguh() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "tab_tangguh";
	document.${formName}.submit();
}
function tab_batal() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "tab_batal";
	document.${formName}.submit();
}
function RulerOfTheState(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "RulerOfTheState";
	document.${formName}.submit();
}
function MahkamahTinggi(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "MahkamahTinggi";
	document.${formName}.submit();
}
function MahkamahTinggiEdit(idpermohonan,id_perbicaraan,id_perintah) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "MahkamahTinggiEdit";
	document.${formName}.submit();
}
function RulerOfTheStateEdit(idpermohonan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "RulerOfTheStateEdit";
	document.${formName}.submit();
}
function PertelingkahanKolateralEdit(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "PertelingkahanKolateralEdit";
	document.${formName}.submit();
}
function PertelingkahanKolateral(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "PertelingkahanKolateral";
	document.${formName}.submit();
}

function kembali_list(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;	
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "kembali_list";
	document.${formName}.submit();
}

function Simpan_Batal(idpermohonan,id_perbicaraan){   
	// Dikemaskini oleh Rosli pada 29/03/2011, tukar kepada FCK Editor 
	var txtCatatanBatal = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanBatal = oEditor.GetXHTML(true); 		
	}
	// end
	
	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;
	}
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.flag_batal.length;  i++){
		if (${formName}.flag_batal[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Alasan Batal Perbicaraan\" terlebih dahulu.");
		return (false);
	}		
	else{ 
		if ( !window.confirm("Adakah Anda Pasti?") ) return; 
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "Simpan_Batal";
		document.${formName}.submit();
	}		
}
function Skrin_KemaskiniBatal(idpermohonan,id_perbicaraan){   
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "Skrin_KemaskiniBatal";
	document.${formName}.submit();
}
function Simpan_Edit_Batal(idpermohonan,id_perbicaraan){  

	var txtCatatanBatalEDIT = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanBatalEDIT = oEditor.GetXHTML(true); 		
	}

	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;
	}
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.flag_batal.length;  i++){
		if (${formName}.flag_batal[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Alasan Batal Perbicaraan\" terlebih dahulu.");
		return (false);
	}	
	else{  
		if ( !window.confirm("Adakah Anda Pasti?") ) return; 
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "Simpan_Edit_Batal";
		document.${formName}.submit();
	}
}
function Simpan_Edit_Tangguh(idpermohonan,id_perbicaraan){
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.flag_tangguh.length;  i++){
		if (${formName}.flag_tangguh[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Alasan Keputusan Perbicaraan\" terlebih dahulu.");
		return (false);
	}	
	if(document.${formName}.EDITsocPegawaiPengendali.value == ""){
		alert("Sila pilih \"Nama Pengendali\" terlebih dahulu.");
  		document.${formName}.EDITsocPegawaiPengendali.focus(); 
		return;
	}  
	if(document.${formName}.txdTarikhPerintahEDIT.value == ""){
		alert("Sila pilih \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerintahEDIT.focus(); 
		return;
	}  
//	if(document.${formName}.txtCatatanTangguhEDIT.value == ""){
//		alert("Sila masukkan \"Catatan / Keterangan Tangguh\" terlebih dahulu.");
//  		document.${formName}.txtCatatanTangguhEDIT.focus(); 
//		return;
//	}  	
//	if(document.${formName}.txtPendapatTangguhEDIT.value == ""){
//		alert("Sila masukkan \"Pendapat / Keputusan Mahkamah\" terlebih dahulu.");
//  		document.${formName}.txtPendapatTangguhEDIT.focus(); 
//		return;
//	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return; 
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "Simpan_Edit_Tangguh";
		document.${formName}.submit();
	}
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function kembaliSenaraiFail(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.${formName}.submit();
}

function kembaliSenaraiPermohonan(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.${formName}.method="POST";
	document.${formName}.tarikhMohon.value = "";
	document.${formName}.submit();
}

<!-- Begin
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
// End -->


function FCKeditor_OnComplete( editorInstance ){

	if (editorInstance.Name == "txtCatatanSelesai" ) {		
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count5);			
	}	
	else if (editorInstance.Name == "txtCatatanSelesaiEDIT" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);		
	}
	else if (editorInstance.Name == "txtCatatanSelesaiVIEW" ) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';	
		editorInstance.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;';				
	}	
	else if (editorInstance.Name == "txtCatatanTangguh" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count6);
	}
	else if (editorInstance.Name == "txtCatatanTangguhVIEW" ) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';	
		editorInstance.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;'
	}	
	else if (editorInstance.Name == "txtCatatanTangguhEDIT" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count2);
	}	
	else if (editorInstance.Name == "txtPendapatTangguh" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count7);
	}
	else if (editorInstance.Name == "txtPendapatTangguhVIEW" ) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';	
		editorInstance.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;'		
	}	
	else if (editorInstance.Name == "txtPendapatTangguhEDIT" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count3);
	}		
	else if (editorInstance.Name == "txtCatatanBatalView" ) {
		editorInstance.EditorDocument.body.contentEditable='false';
		editorInstance.EditorDocument.designMode='off';	
		editorInstance.EditorDocument.body.style.cssText += 'color: #322805;background-color: #F2F2EE;'		
	}	
	else if (editorInstance.Name == "txtCatatanBatalEDIT" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count4);
	
	}else if (editorInstance.Name == "txtCatatanBatal" ) {
		editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count8);
		
	}	
	
}
function fckeditor_word_count() {
 //var count = editorInstance.GetHTML().replace('&#160','').length
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanSelesaiEDIT');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count2() {
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanTangguhEDIT');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count2').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count3() {
 var editorInstance = FCKeditorAPI.GetInstance('txtPendapatTangguhEDIT');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count3').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count4() {
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanBatalEDIT');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count4').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count5() {
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanSelesai');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count5').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count6() {
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanTangguh');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count6').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 
}

function fckeditor_word_count7() {
 var editorInstance = FCKeditorAPI.GetInstance('txtCatatanTangguh');	
 var count = editorInstance.GetHTML().length;
 document.getElementById('word_count7').innerHTML = (2000 - count) + " Baki Aksara";
 
 if (count > 2000) {
 editorInstance.EditorDocument.body.contentEditable='false';
 editorInstance.EditorDocument.designMode='off';
 }
 }
 	function fckeditor_word_count8() {
 		var editorInstance = FCKeditorAPI.GetInstance('txtCatatanBatal');	
 		var count = editorInstance.GetHTML().length;
 		document.getElementById('word_count8').innerHTML = (2000 - count) + " Baki Aksara";
 
 		if (count > 2000) {
 			editorInstance.EditorDocument.body.contentEditable='false';
 			editorInstance.EditorDocument.designMode='off';
 		}
 
	}

</script>
