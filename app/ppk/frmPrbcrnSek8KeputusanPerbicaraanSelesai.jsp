#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $idFail = $data.get("id_fail") )
#set($perhatian="<i><font color=red >Perhatian</font><font >: Sila pastikan label bertanda</font>&nbsp;<font color=red >*</font>&nbsp;<font >diisi.</font></i>")



#if($size_M!=0)
	#foreach($m in $listMahkamah)
		#set($alamatM1=$m.alamat1)
		#set($alamatM2=$m.alamat2)
		#set($alamatM3=$m.alamat3)
		#set($poskodM=$m.poskod)
		#set($bandarM=$m.bandar)
	#end    
#else
		
		#set($alamatM1="")
		#set($alamatM2="")
		#set($alamatM3="")
		#set($poskodM="")
		#set($bandarM="")
#end	

#foreach ($ListData in $ViewPemohon)
	#set ($idSimati = $ListData.idSimati)
#end

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
    #set($tarikhKematian=$list.tarikh_mati) <!-- arief add -->
<!-- case utk butang Seterusnya ke Notis -->
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_negeri" value="$id_negeri">
<input type="hidden" name="command2" />
<input type="hidden" name="idSimati" value="$idSimati"/>
<!--<input type="text" name="id_status" id="id_status" value="$idstatus"> -->
#end

#set($namaDoC="")
#foreach($listSupportingDoc in $ViewSupportingDoc)
#set($namaDoC = $listSupportingDoc.NAMA_DOKUMEN)
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
<input type="hidden" name="valueKIV" id="valueKIV" value="$check_kiv">
<!-- case utk butang Seterusnya ke Perintah -->
<input type="hidden" name="actionPerintah">
<input type="hidden" name="idPermohonan" value="$idpermohonan">



<!-- arief add flag denda lewat OPEN -->
#foreach ($ListflagDendaLewatPendaftaran in $flagDendaLewatPendaftaran)
	#set($checkDendaLewatPendaftaran = $ListflagDendaLewatPendaftaran.flag_Denda_Lewat_Pendaftaran )
#end

#if($checkDendaLewatPendaftaran == 'T')
	#set($dateToday = $currentDate)
#end

<input type="text" name="txdflagDendaLewatPendaftaran" id="txdflagDendaLewatPendaftaran" value="$flagDendaLewatPendaftaran"/>
<input type="hidden" name="txtJumDendaLewat" value="$jumDendaLewat"/> 
<input type="text" name="tarikhMati" id="tarikhMati" value="$tarikhKematian"/>
<input type="hidden" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon"/>
<input type="hidden" name="txtBezaTarikhPermohonanDenganTarikhMati" id="txtBezaTarikhPermohonanDenganTarikhMati" value="txtBezaTarikhPermohonanDenganTarikhMati"/>
<input type="hidden" name="catatan_pengecualian" id="catatan_pengecualian" value="$catatan_pengecualian"/>
<input type="text" name="checkDendaLewatPendaftaran" id="$checkDendaLewatPendaftaran" value="$checkDendaLewatPendaftaran"/>
<!-- arief add flag denda lewat CLOSE-->
<!--START CODE-->
<table width="100%">
<tr>
  <td>
  #if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<fieldset id="header_lama" style="display:none" >
  
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
    </fieldset>
    
    #if($!headerppk.size()>0)
#else
<script  type="text/javascript">
if(document.getElementById("header_lama").style.display=="none")
{
document.getElementById("header_lama").style.display="block";
}
</script>
#end

    
    </td>
    
    
  
</tr>
#if ( $idstatus == "18" )
<!--------------------------------------------------- ADD MODE ---------------------------------------------->
#if ( $mode == "add" )
<tr>
  <td><fieldset>
    <legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
    <table width="100%"  cellspacing="1" cellpadding="1" border="0">
    <tr>
    	<td><input type ="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/></td>
    	<td></td>
    	<td></td>
    	<td></td>
    </tr>
      <tr>
        <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
        <td width="33%">:&nbsp;$selectEditPegawai</td>
        #if ( $tarikh == "perintah" )
        <td width="11%"><font color="red">*</font>&nbsp;Tarikh Perintah</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintah" disabled value="$!tarikh_bicara" size="10" id="txdTarikhPerintah" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintah',false,'dmy');" /></td>
        #end
        
        #if ( $tarikh == "bicara" )
        <td width="14%"><font color="red">*</font>&nbsp;Tarikh Perbicaraan &nbsp;&nbsp;&nbsp;Terakhir</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintah" disabled value="$!tarikh_bicara" size="10" id="txdTarikhPerintah" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintah',false,'dmy');" /></td>
        #end </tr>
      <tr>
        <td width="16%"><font color="red">*</font>&nbsp;Keputusan Perbicaraan</td>
        <td colspan="5">:
        
         #if (($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N") && ($listPenerimaNotis_size1 > 0))
        	&nbsp;
          <input name="flag_jenis_keputusan" disabled type="radio" value="0" $TEMPcheckedSelesai onclick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" disabled type="radio" value="1" $TEMPcheckedTangguh onclick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" disabled type="radio" value="2" $TEMPcheckedBatal onclick="tab_batal()" />
          Batal</td>
          #end
          
          #if (($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N") && ($listPenerimaNotis_size1 < 1))
        	&nbsp;
          <input name="flag_jenis_keputusan" disabled type="radio" value="0" $TEMPcheckedSelesai onclick="tab_selesai()" disabled/>
          Selesai
          <input name="flag_jenis_keputusan" disabled type="radio" value="1" $TEMPcheckedTangguh onclick="tab_tangguh()" disabled/>
          Tangguh
          <input name="flag_jenis_keputusan" disabled type="radio" value="2" $TEMPcheckedBatal onclick="tab_batal()" disabled/>
          Batal</td>
          <tr>
          <td colspan="3"><font color="red"><b>Sila lengkapkan Laporan Penghantaran Notis dahulu.</b></font></td>
          </tr>
          #end
      </tr>
      <!--By Mohamad Rosli 21/02/2011-->
      #if ( $flag == "selesai" )
       <tr>
       <td></td>
       <td >
       <fieldset>
       <legend>Maklumat KIV (Jika Ada)</legend>       
       <table width="100%" border="0">
       <tr>
        <td width="20%">Status KIV</td>
        <td width="1%">:</td>
        <td width="79%">
        #if($!check_kiv == "1")
        #set($check_kiv_mode = "checked")
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" checked/>KIV
		<input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)"/>Dokumen telah dikemukakan
        #elseif($!check_kiv == "0")
        #set($check_kiv_mode = "")
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" />KIV
		<input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" checked/>Dokumen telah dikemukakan
       
        #else
        #set($check_kiv_mode = "")
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" />KIV
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" />Dokumen telah dikemukakan
        #end
       <!--  <input type="checkbox" name="check_kiv" id="check_kiv" $check_kiv_mode value="1"  onclick="" /> -->
    
        &nbsp; 
        
       
        
        </td>
        </tr>
         <tr>
        <td ><font color="red">*</font>KIV Sehingga</td>
        <td >:</td>
        <td >
        <input name="date_kiv" type="text" id="date_kiv" value="$!date_kiv"  size="10" maxlength="10" onKeyUp="javascript:validateIC(event,this,this.value,'date_kiv')" onBlur="trans_date(this.value);"  />
		<a href="javascript:displayDatePicker('date_kiv',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
        </td>
        </tr>
          <tr>
        <td valign="top"><font color="red">*</font>Catatan KIV</td>
        <td valign="top">:</td>
        <td valign="top">
        <textarea name="catatan_kiv" cols="35" rows="3" id="catatan_kiv"  >$!catatan_kiv</textarea>
        </td>
        </tr>
        </table>
        </fieldset>
        </td>
        <td colspan="4">
        </td>
      </tr>
      #end
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
  <td> 
  #if( $button == "kembali" )
    <p></p>
    <div align="center">
      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript:kembali_list('$idpermohonan','$id_perbicaraan');" />
    </div>
    #end 
    </td>
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
  <li class="TabbedPanelsTab" onclick="setSelected(0);SelesaiAdd()">SELESAI</li>
  #end
  #if(  $flag == "tangguh" )
  <!-- value selesai 1 -->
  <li class="TabbedPanelsTab" onclick="setSelected(1);TangguhAdd()">TANGGUH</li>
  #end
  #if( $flag == "batal" )
  <!-- value selesai 2 -->
  <li class="TabbedPanelsTab" onclick="setSelected(2);BatalAdd()">BATAL</li>
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
              <input type="text" size="15" name="txtJumHarta" onblur="this.value=this.value.toUpperCase();" id="txtJumHarta" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumHarta)" />
              </label></td>
          </tr>
          <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
          <tr>
            <td width="47%" ><div align="left">Jumlah Harta yang Dikenakan Bayaran Perintah &nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumHartaDikenakanBayaranPerintah" onblur="this.value=this.value.toUpperCase();" id="txtJumHartaDikenakanBayaranPerintah" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumHartaDikenakanBayaranPerintah)" />
              </label></td>
          </tr>
          <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
          <!-- arief add Bayaran Denda Lewat Pendaftaran Open -->
           <tr>
            <td width="47%" ><div align="left">Bayaran Denda Lewat Pendaftaran&nbsp;&nbsp;&nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumDendaLewat" onblur="this.value=this.value.toUpperCase();" id="jumDendaLewat" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumDendaLewat)" />
              </label></td>
          </tr>
          <!-- arief add Bayaran Denda Lewat Pendaftaran Close -->
          <tr>
            <td><div align="left"><font color="red">*</font>&nbsp;Jumlah &nbsp;&nbsp;&nbsp;Bayaran(RM)</div></td>
            <td><div align="right">: </div></td>
            
            <td> #if ( $FlagtarikhMohon == 0 )
              <input type="text" size="15" name="txtJumBayaran1" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaran1" style="text-transform:uppercase;" value="$Util.formatDecimal($txtJumBayaran)" />
              <input type="hidden" size="12" name="txtJumBayaran" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaran" style="text-transform:uppercase;" readonly class="disabled" value="$txtJumBayaran" />
              #end
              
              #if ( $FlagtarikhMohon == 1 )
              <input type="text" size="15" name="txtJumBayaran" id="txtJumBayaran" onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onkeyup="validateNumber(this,this.value);" />
              #end </td>
          </tr>
          <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
          <tr>
            <td><div align="left"><font color="red">*</font>&nbsp;Jumlah Bayaran Sebenar&nbsp;&nbsp;(RM)</div></td>
            <td><div align="right">: </div></td>
            
            <td> #if ( $FlagtarikhMohon == 0 )
              <input type="text" size="15" name="txtJumBayaranSebenar" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranSebenar" style="text-transform:uppercase;" value="$Util.formatDecimal($!txtJumBayaranSebenar)" />
              <input type="hidden" size="12" name="txtJumBayaranSebenar" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranSebenar" style="text-transform:uppercase;" readonly class="disabled" value="$!txtJumBayaranSebenar" />
              #end
              
              #if ( $FlagtarikhMohon == 1 )
              <input type="text" size="15" name="txtJumBayaranSebenar" id="txtJumBayaranSebenar" onblur="validateNumber(this,this.value);validateModal(this,this.value,$!txtJumBayaranPusakaSebenar)" onkeyup="validateNumber(this,this.value);" />
              #end </td>
          </tr>
          <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
          <!-- arief add Pengecualian Bayaran OPEN -->
           <tr>
        	<td width="47%" ><div align="left">Pengecualian Bayaran</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	#if($!check_pengecualian == "1")
        		#set($check_pengecualian_mode = "checked")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"checked/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"/>Keseluruhan
        	#elseif ($!check_pengecualian == "2")
        		#set($check_pengecualian_mode = "checked")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"checked/>Keseluruhan
        	#else
        		#set($check_pengecualian_mode = "")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"/>Keseluruhan
        	#end
			</td>
          </tr>
          <!-- arief add Pengecualian Bayaran CLOSE -->
          <!-- arief add textbox Pengecualian Bayaran OPEN-->
          <tr>
        	<tr>
        	<td width="47%" ><div align="left">Catatan Pengecualian</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	<textarea name="catatan_pengecualian" cols="35" rows="3" id="catatan_pengecualian"  >$!catatan_pengecualian</textarea>
        	</td>
          </tr>
          <!-- arief add textbox Pengecualian Bayaran CLOSE-->
          <tr>
            <td valign="top"><div align="left"><font color="red">*</font>&nbsp;No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPerintah" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintah" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left"><font color="red">*</font>&nbsp;Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranPerintah" value="" size="15" id="txdTarikhBayaranPerintah" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranPerintah',false,'dmy');" /></td>
          </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranPerintah.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranPerintah.size()</b> bayaran perintah bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #elseif($getListBayaranPerintah.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #end
      </td>   
         </tr>
        </table>
        </fieldset></td>
      <td width="34%" valign="top"   id="id_cukai" >
      
      <fieldset>
        <legend>Bayaran Cukai</legend>
        <table width="100%" border="0">
          <tr>
            <td width="46%">Jumlah Bayaran (RM)</td>
            <td width="2%"><div align="right" class="style38">:</div></td>
            <td width="52%"><label>
              <input type="text" size="15" name="txtJumBayaranPusaka" id="txtJumBayaranPusaka" value="$!txtJumBayaranPusaka" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onkeyup="validateNumber(this,this.value);" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPusaka" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPusaka" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranPusaka" size="15" id="txdTarikhBayaranPusaka" type="text"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranPusaka',false,'dmy');" /></td>
          </tr>
          <tr>
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranCukai.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranCukai.size()</b> bayaran cukai bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #elseif($getListBayaranCukai.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #end
      </td>   
         </tr>
         
        </table>
        </fieldset>
        <div id="mesej_cukai"></div>
        </td>
      <script type="text/javascript">
		
		var str1  = '$tarikhmati_simati';
		var str2  = "01/11/1991";		
		var dt1   = parseInt(str1.substring(0,2),10);
	    var mon1  = parseInt(str1.substring(3,5),10)-1;
	    var yr1   = parseInt(str1.substring(6,10),10);   
	    var dt2   = parseInt(str2.substring(0,2),10);
	    var mon2  = parseInt(str2.substring(3,5),10)-1;
	    var yr2   = parseInt(str2.substring(6,10),10);   
        var date1 = new Date(yr1, mon1, dt1);
        var date2 = new Date(yr2, mon2, dt2);		
		
		if(date1 > date2)
		{		   
		   document.getElementById('id_cukai').style.display="none";
		   $jquery("#mesej_cukai").html("");
		} 
		else
		{			
			document.getElementById('id_cukai').style.display="";
			  $jquery("#mesej_cukai").html("<span  style='color:red'><blink>Sila pastikan maklumat bayaran cukai diisi jika simati meninggal sebelum 01/11/1991</blink></span>");
		}
		</script>      
      <td width="33%" valign="top"><fieldset>
        <legend>Bayaran Baitulmal</legend>
        <table width="100%" border="0">
          <tr>
            <td width="53%">Jumlah Bayaran (RM)</td>
            <td width="3%"><div align="right">:</div></td>
            <td width="44%"><label>
              <input type="text" size="12" name="txtJumBayaranBaitulmal" id="txtJumBayaranBaitulmal" value="$!txtJumBayaranBaitulmal" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onkeyup="validateNumber(this,this.value);" />
              </label>
            </td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitBaitulmal" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmal" style="text-transform:uppercase;" maxlength="20" />
              </span></label></td>
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><input name="txdTarikhBayaranBaitulmal" size="15" id="txdTarikhBayaranBaitulmal" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranBaitulmal',false,'dmy');" /></td>
          </tr>
          <tr>
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranBaitulmal.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranBaitulmal.size()</b> bayaran baitulmal bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #elseif($getListBayaranBaitulmal.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #end
      </td>   
         </tr>
        </table>
        </fieldset>
        <div id="mesej_cukai"></div></td>
    </tr>
        <tr>
      <td colspan="3" width="100%" >&nbsp;</td>
    </tr>
        <tr>
      <td colspan="3" width="100%" >Catatan Keputusan Perbicaraan  : </td>
    </tr>
        <tr>
      <td colspan="3" width="100%" >
   
      <textarea name="txtCatatanSelesai" id="txtCatatanSelesai" cols="80%" rows="8"></textarea>
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
      <td colspan="3" width="100%" >
      <div  class="disabled" id="word_count5"> </div>
      
          </td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
        <tr>
      <td colspan="3" width="100%" ><div align="center">
      
      #if (($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N") && ($!DoNotSave != "1") && ($userRole != "user_ppk"))
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Selesai('$idpermohonan','$id_perbicaraan');" />
      #end
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
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
      <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onclick="SenaraiBicara('$idpermohonan')" /></td>
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
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="5" onclick="RulerOfTheState('$idpermohonan','$id_perbicaraan','$id_perintah')" $TEMPcheckedMahkamahSyariah >
            Menunggu Keputusan Rujukan Kepada Ruler of The State</td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" onclick="PertelingkahanKolateral('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
            Pertelingkahan Kolateral </td>
    </tr>
        <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi (BORANG J)</td>
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
            <input type="text" size="5" maxlength="2" name="txtTempoh" id="txtTempoh" value="$!tempoh_tunggu_faraid" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);"/>
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
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count6"></div>
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
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count7"></div>
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
       #if($!DoNotSave != "1" && $userRole != "user_ppk")
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Tangguh('$idpermohonan','$id_perbicaraan');" />
       #end
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
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
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="1"  onclick="DocSupportAppear();MahkamahAppear();MahkamahDisappear2()" $TEMPcheckedMahkamahTinggiWasiat> <!-- onclick="MahkamahTinggi('$idpermohonan','$id_perbicaraan')" -->
        Pindah ke Mahkamah Tinggi kerana ada wasiat </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left">
      <div id="MahkamahAppear"  style="display:none;"  > 
      <table width="50%">
      <tr>
    <td>
   
    <fieldset><legend>MAKLUMAT MAHKAMAH</legend>
	<table width="100%">
		<tr>
			<td><font color="red">* </font></td>
			<td>Mahkamah</td>
			<td>:</td>
			<td><select id="socMahkamah" name="socMahkamah" onchange="changeGetAlamatMahkamah()" style="width:300">	
						
						#foreach($listMK in $listMahkamah)
                    		<option value="$listMK.id_pejabat">$listMK.nama_pejabat.toUpperCase()</option>
                        #end
			
			</select>
			</td>
		</tr>
		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input type="text"  disabled name="txtAlamatMahkamah1" id="txtAlamatMahkamah1" value="$!alamatM1" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text"  disabled name="txtAlamatMahkamah2" id="txtAlamatMahkamah2" value="$!alamatM2" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text"  disabled name="txtAlamatMahkamah3" id="txtAlamatMahkamah3" value="$!alamatM3" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input type="text"  disabled name="txtPoskodMahkamah" id="txtPoskodMahkamah" value="$!poskodM" size="6" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<!-- 
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeriMahkamah</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandarMahkamah</td>
    		</tr>
    		 -->
    	
	</table>
	</fieldset>
	</td>
	</tr>
	</table>

   	 </div>
     </td>
     </tr>
    
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="2" $TEMPcheckedTidakHadir3Kali onclick="DocSupportDisappear();MahkamahDisappear();MahkamahDisappear2()" >
        Tidak hadir setelah dipanggil 3 kali </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="3" $TEMPcheckedPermintaanPemohon onclick="DocSupportDisappear();MahkamahDisappear();MahkamahDisappear2()" >
        Atas Permintaan Pemohon </td>
    </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="4" $TEMPcheckedMahkamahTinggi2Juta onclick="DocSupportAppear();MahkamahAppear2();MahkamahDisappear()" >
        Pindah ke Mahkamah Tinggi kerana harta melebihi RM2 juta </td>
    </tr>
    
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left">
      <div id="MahkamahAppear2"  style="display:none;"  > 
      <table width="50%">
      <tr>
    <td>
   
    <fieldset><legend>MAKLUMAT MAHKAMAH</legend>
	<table width="100%">
		<tr>
			<td><font color="red">* </font></td>
			<td>Mahkamah</td>
			<td>:</td>
			<td><select id="socMahkamah" name="socMahkamah" onchange="changeGetAlamatMahkamah()" style="width:300">	
						
						#foreach($listMK in $listMahkamah)
                    		<option value="$listMK.id_pejabat">$listMK.nama_pejabat.toUpperCase()</option>
                        #end
			
			</select>
			</td>
		</tr>
		<tr>
    			<td>&nbsp;</td>
    			<td>Alamat</td>
    			<td>:</td>
    			<td><input type="text"  disabled name="txtAlamatMahkamah1" id="txtAlamatMahkamah1" value="$!alamatM1" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" disabled  name="txtAlamatMahkamah2" id="txtAlamatMahkamah2" value="$!alamatM2" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" disabled  name="txtAlamatMahkamah3" id="txtAlamatMahkamah3" value="$!alamatM3" size="50" maxlength="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Poskod</td>
    			<td>:</td>
    			<td><input type="text" disabled  name="txtPoskodMahkamah" id="txtPoskodMahkamah" value="$!poskodM" size="6" maxlength="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
    		</tr>
    		<!-- 
    		<tr>
    			<td>&nbsp;</td>
    			<td>Negeri</td>
    			<td>:</td>
    			<td>$!selectNegeriMahkamah</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>Bandar</td>
    			<td>:</td>
    			<td>$!selectBandarMahkamah</td>
    		</tr>
    		 -->
    		
    	
	</table>
	</fieldset>
	</td>
	</tr>
	</table>

   	 </div>
     </td>
     </tr>
    <tr align="center">
      <td >&nbsp;</td>
      <td align="left"><input name="flag_batal" id="flag_batal" type="radio" value="5" $TEMPcheckedSebabLainBatal onclick="DocSupportDisappear();MahkamahDisappear();MahkamahDisappear2()">
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
      <td colspan="2" width="100%" ><div  class="disabled" id="word_count8"></div>
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
    <td colspan="2" width="100%">
    
    <div id="dokumensokongan"  style="display:none;"  > 
    <table width="100%">
  <tr>
    <td>
    <fieldset>
    <legend>Dokumen Sokongan</legend>
    <table width="60%" border="0">
    <tr>
     <td width="25%" align ="right" scope="col">Dokumen Sokongan</td>
        <td width="1%" scope="col">:</td>
        <td width="74%" colspan="2" scope="col">
         <input type="text" disabled value=$!namaDoC>&nbsp;
         #if ($namaDoC != '')
         <input type="button" name="cmdUpload" disabled id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #else
         <input type="button" name="cmdUpload" id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #end
         #if ($namaDoC != '')
         <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;
         #end
         
         <!-- <input name="cetak" disabled type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;  -->
         
        
   		 
   		 #if ($namaDoC != '')
   		 <input name="deleteSuppDoc1" type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />
   		 #end
   		 
   		 <!-- <input name="deleteSuppDoc1" disabled type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />  -->
   		 
   		 
       
        </td>
    </tr>
    <tr>
    
    </tr>
    
    </table>
    
    </fieldset> 
     </td>
  </tr>
</table>
    </div>  
    </td>
    </tr>    
    
    <tr>
      <td colspan="2" width="100%" ><div align="center">
      #if($!DoNotSave != "1" && $userRole != "user_ppk")
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Batal('$idpermohonan','$id_perbicaraan');" />
      #end
      	  <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
        </div></td>
    </tr>
  </table>
    </fieldset>
    
 


  #end
  
  
 
  
  <!-- close div $flag == "batal" -->

  
  <!-- close div tabbed panels content group -->

  
  <!-- close div TabbedPanels1 -->

  
  
  
      </td>
  
  </tr>
  
</table>

</td>
</tr>



#end ##EOF if ( $flag == "selesai" || $flag == "tangguh" || $flag == "batal") 
	
	#end
<!---------------------------------------------------END ADD MODE ---------------------------------------------->
#end 

##EOF if ( $idstatus == "18" )
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
    #set ($check_kiv = $dataPerintah1.check_kiv)
    #set ($date_kiv = $dataPerintah1.date_kiv)
    #set ($catatan_kiv = $dataPerintah1.catatan_kiv)
    #set ($justifikasi_pegawai = $dataPerintah1.justifikasi_pegawai)
    
    
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
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>
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
          <input name="txdTarikhPerintah" disabled value="$!tarikh_perintah" size="11" id="txdTarikhPerintah" type="text" readonly class="disabled" />
        </td>
        #end
        
        #if ( $tarikh == "bicara" )
        <td width="18%">Tarikh Perbicaraan Terakhir</td>
        <td width="10%">:&nbsp;
          <input name="txdTarikhPerintah" disabled value="$!tarikh_bicara" size="11" id="txdTarikhPerintah" type="text" readonly class="disabled" />
        </td>
        #end </tr>
      <tr>
        <td width="18%">Keputusan Perbicaraan</td>
        <td colspan="5">:&nbsp;
          <input name="flag_jenis_keputusan" disabled type="radio" value="0" $TEMPcheckedSelesai disabled onclick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" disabled type="radio" value="1" $TEMPcheckedTangguh disabled onclick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" disabled type="radio" value="2" $TEMPcheckedBatal disabled onclick="tab_batal()" />
          Batal</td>
      </tr>
      <!-- Skrin yang membenarkan VIEW sahaja (START)  -->
      #if ( $flag == "selesai" )
       <tr>
       <td></td>
       <td >
       <fieldset>
       <legend>Maklumat KIV (Jika Ada</legend>       
       <table width="100%" border="0">
       <tr>
        <td width="20%">Status KIV</td>
        <td width="1%">:</td>
        <td width="79%">

        #if($!check_kiv == "1")
        #set($check_kiv_mode = "checked")
          <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" disabled checked />KIV
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled />Dokumen telah dikemukakan
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled />Dokumen gagal dikemukakan
		#elseif($!check_kiv == "0")
        #set($check_kiv_mode = "checked")
          <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" disabled  />KIV
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled checked />Dokumen telah dikemukakan
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled />Dokumen gagal dikemukakan
        #elseif($!check_kiv == "3")
        #set($check_kiv_mode = "checked")
          <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" disabled  />KIV
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled  />Dokumen telah dikemukakan
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled checked/>Dokumen gagal dikemukakan
        #else
        #set($check_kiv_mode = "")
        
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onclick="putValue(this.value)" disabled/>KIV
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled />Dokumen telah dikemukakan
		 <input type="radio" name="check_kiv" id="check_doc" value="0" onclick="putValue(this.value)" disabled />Dokumen gagal dikemukakan
        #end
        <!-- <input   type="checkbox" name="check_kiv" id="check_kiv" $check_kiv_mode disabled value="1"   onclick="" /> -->
        
        &nbsp;
        </td>
        </tr>
         <tr>
        <td ><font color="red">*</font>KIV Sehingga</td>
        <td >:</td>
        <td >
        <input  readonly class="disabled" name="date_kiv" type="text" id="date_kiv" value="$!date_kiv"  size="10" maxlength="10" onKeyUp="javascript:validateIC(event,this,this.value,'date_kiv')" onBlur="trans_date(this.value);"  />
<a href="javascript:displayDatePicker('date_kiv',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
        </td>
        </tr>
          <tr>
        <td valign="top"><font color="red">*</font>Catatan KIV</td>
        <td valign="top">:</td>
        <td valign="top">
        <textarea name="catatan_kiv"  readonly class="disabled" cols="35" rows="3" id="catatan_kiv"  >$!catatan_kiv</textarea>
        </td>
        </tr>
        #if($!check_kiv == "3")
         <tr>
        <td valign="top">Justifikasi Pegawai</td>
        <td valign="top">:</td>
        <td valign="top">
        <textarea name="justifikasi_pegawai" readonly class="disabled" cols="35" rows="3" id="justifikasi_pegawai"  >$!justifikasi_pegawai</textarea>
        </td>
        </tr>
        #end
        
        
        <tr>
        	<td valign="top" colspan=3>&nbsp;</td>
        </tr>
        
        
        <tr>
        <td valign="top" colspan=3 align=left style="color:red;"><span class="style2">*</span>Nota : Sekiranya terdapat Maklumat KIV, permohonan ini tidak boleh diteruskan pada proses seterusnya.</td>
        
        </tr>
        
        </table>
        </fieldset>
        </td>
        <td colspan="4">
        </td>
      </tr>
      #end
      <!-- Skrin yang membenarkan VIEW sahaja (END)  -->
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
  <li class="TabbedPanelsTab" onclick="setSelected(0);SelesaiAdd()">SELESAI</li>
  #end
  #if(  $flag == "tangguh" )
  <!-- value selesai 1 -->
  <li class="TabbedPanelsTab" onclick="setSelected(1);TangguhAdd()">TANGGUH</li>
  #end
  #if( $flag == "batal" )
  <!-- value selesai 2 -->
  <li class="TabbedPanelsTab" onclick="setSelected(2);BatalAdd()">BATAL</li>
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
            <input type="text" size="15" name="txtJumHarta" onblur="this.value=this.value.toUpperCase();" id="txtJumHarta" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($txtJumHarta)" />
            </label></td>
        </tr>
        <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
        <tr>
        	<td width="47%" ><div align="left">Jumlah Harta yang Dikenakan Bayaran Perintah &nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumHartaDikenakanBayaranPerintah" onblur="this.value=this.value.toUpperCase();" id="txtJumHartaDikenakanBayaranPerintah" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal(!$txtJumHartaDikenakanBayaranPerintah)" />
              </label></td>
        </tr>
        <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
        <!-- arief add Bayaran Denda Lewat Pendaftaran Open -->
        <tr>
        	<td width="47%" ><div align="left">Bayaran Denda Lewat Pendaftaran&nbsp;&nbsp;&nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumDendaLewat" onblur="this.value=this.value.toUpperCase();" id="jumDendaLewat" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumDendaLewat)" />
              </label></td>
          </tr>
        <!-- arief add Bayaran Denda Lewat Pendaftaran Close -->
        <tr>
          <td><div align="left">Jumlah Bayaran&nbsp;&nbsp;(RM ) </div></td>
          <td><div align="right">:</div></td>
          <td><label>
            <input type="text" size="15" name="txtJumBayaran" id="txtJumBayaran" readonly class="disabled" value="$!Util.formatDecimal($!bayaran_perintah)" />
            </label></td>
        </tr>
        <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
        <tr>
          <td><div align="left">Jumlah Bayaran Sebenar&nbsp;&nbsp;(RM)</div></td>
          <td><div align="right">:</div></td>
          <td><label>
            <input type="text" size="15" name="txtJumBayaranSebenar" id="txtJumBayaranSebenar" readonly class="disabled" value="$!Util.formatDecimal($!bayaran_perintah_sebenar)" />
            </label></td>
        </tr>
        <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
        <!-- arief add Pengecualian Bayaran OPEN -->
         <tr>
        	<td width="47%" ><div align="left">Pengecualian Bayaran</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	#if($!check_pengecualian == "1")
        		#set($check_pengecualian_mode = "checked")
         		<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)" disabled checked/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)" disabled/>Keseluruhan
        	#elseif ($!check_pengecualian == "2")
        		#set($check_pengecualian_mode = "checked")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)" disabled/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)" disabled checked/>Keseluruhan
        	#else
        		#set($check_pengecualian_mode = "")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)" disabled/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)" disabled/>Keseluruhan
        	#end
			</td>
          </tr>
        <!-- arief add Pengecualian Bayaran CLOSE -->
        <!-- arief add textbox Pengecualian Bayaran OPEN-->
          <tr>
        	<tr>
        	<td width="47%" ><div align="left">Catatan Pengecualian</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	<textarea name="catatan_pengecualian" cols="35" rows="3" id="catatan_pengecualian" disabled >$!catatan_pengecualian</textarea>
        	</td>
        </tr>
        <!-- arief add textbox Pengecualian Bayaran CLOSE-->
        <tr>
          <td valign="top">No Resit 
       
        </td>
        
        <td><div align="right">:</div></td>
          <td>
          
          <label><span style="text-transform:uppercase;">
          <input type="text" size="15" name="txtNomborResitPerintah" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintah" style="text-transform:uppercase;" readonly value="$NoResit_perintah" class="disabled" />
          
          </span></label>
          </td>
        </tr>
        <tr>
          <td><div align="left">Tarikh Bayaran</div></td>
          <td><div align="right">:</div></td>
          <td><label>
            <input name="txdTarikhBayaranPerintah" size="15" id="txdTarikhBayaranPerintah" type="text" readonly value="$TarikhBayaran_perintah" class="disabled" />
            </label></td>
        </tr>
        
        <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranPerintah.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranPerintah.size()</b> bayaran perintah bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #elseif($getListBayaranPerintah.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #end
      </td>   
         </tr>
         </table>
      </fieldset>
    </td>
    
    <td width="34%" valign="top"  id="id_cukai">
   
    <fieldset>
        <legend>Bayaran Cukai</legend>
        <table width="100%" border="0">
          <tr>
            <td width="46%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
            <td width="2%"><div align="right" class="style38">:</div></td>
            <td width="52%"><label>
              <input type="text" size="15" name="txtJumBayaranPusaka" value="$!Util.formatDecimal($!bayaran_pusaka)" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranPusaka" style="text-transform:uppercase;" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitPusaka" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPusaka" style="text-transform:uppercase;" value="$!NoResit_pusaka" readonly class="disabled" />
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
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         
         
         
         #if($getListBayaranCukai.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranCukai.size()</b> bayaran cukai bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #elseif($getListBayaranCukai.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #end
      </td>   
         </tr>
         
         
        </table>
        </fieldset>
        <div id="mesej_cukai"></div>
        </td>      
        
                
        <script type="text/javascript">
		
		var str1  = '$tarikhmati_simati';
		var str2  = "01/11/1991";		
		var dt1   = parseInt(str1.substring(0,2),10);
	    var mon1  = parseInt(str1.substring(3,5),10)-1;
	    var yr1   = parseInt(str1.substring(6,10),10);   
	    var dt2   = parseInt(str2.substring(0,2),10);
	    var mon2  = parseInt(str2.substring(3,5),10)-1;
	    var yr2   = parseInt(str2.substring(6,10),10);   
        var date1 = new Date(yr1, mon1, dt1);
        var date2 = new Date(yr2, mon2, dt2);		
		
		if(date1 > date2)
		{		   
		   document.getElementById('id_cukai').style.display="none";
		    $jquery("#mesej_cukai").html("");
		} 
		else
		{			
			document.getElementById('id_cukai').style.display="";
			 $jquery("#mesej_cukai").html("<span  style='color:red'><blink>Sila pastikan maklumat bayaran cukai diisi jika simati meninggal sebelum 01/11/1991</blink></span>");
		}
		</script>   
          
      <td width="33%" valign="top"><fieldset>
        <legend>Bayaran Baitulmal</legend>
        <table width="100%" border="0">
          <tr>
            <td width="53%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
            <td width="3%"><div align="right">:</div></td>
            <td width="44%"><label>
              <input type="text" size="15" name="txtJumBayaranBaitulmal" value="$!Util.formatDecimal($!bayaran_baitulmal)" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranBaitulmal" style="text-transform:uppercase;" readonly class="disabled" />
              </label></td>
          </tr>
          <tr>
            <td><div align="left">No Resit</div></td>
            <td><div align="right">:</div></td>
            <td><label><span style="text-transform:uppercase;">
              <input type="text" size="15" name="txtNomborResitBaitulmal" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmal" style="text-transform:uppercase;" readonly value="$NoResit_baitulmal" class="disabled" />
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
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
       
         #if($getListBayaranBaitulmal.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranBaitulmal.size()</b> bayaran baitulmal bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #elseif($getListBayaranBaitulmal.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #end
      </td>   
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
      <td colspan="3" width="100%" >
      
        <input type="hidden" name="id_perbicaraan2" value="$id_perbicaraan" />
        <textarea name="txtCatatanSelesaiVIEW" id="txtCatatanSelesaiVIEW" cols="80%" rows="8" >$!catatan</textarea>
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
      <td colspan="3" width="100%" ><div  class="disabled" id="word_count5"></div>
      </td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
    </tr>
    <tr>
      <td colspan="3" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />   -->
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembaliSenaraiFail('$noFail');"/>
          <!--<input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$idPermohonanSimati','$idpermohonan','$idstatus')" /> -->
          #end
          
          #if ($flagFromSenaraiPermohonanSek8 == 'yes')
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
          #end
          
          #if ( $flagForView == 'yes' )
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:ForView('$noFail')"/>
          #end
          
          #if($idstatus == "41" || $idstatus == "25" )
            
            
            #if ($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N" && $userRole != "user_ppk")
            <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />
          	#end
          
         	#if( $!check_kiv != "1")
          	<input type="button" name="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$idPermohonanSimati','$idpermohonan','$idstatus')" />
          	#end
          #end 
          
          #if($idstatus == "21" && $check_kiv == "1" && $userRole != "user_ppk")
            <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />
         #end 
          
          
          #if($idstatus == "21" && $userRole != "user_ppk")
                  
            <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_Kemaskini('$idpermohonan','$id_perbicaraan','$id_bayaran_perintah');" />
          <input type="button" name="cmdTeruskan" id="cmdTeruskan" value="Seterusnya" onClick="seterusnya('$idPermohonanSimati','$idpermohonan','$idstatus')" />
           #if($flag_kemaskini_selesai != "yes")
			<script>
            document.getElementById('Kemaskini').style.display = "none";
            </script>
            #end       
            
            
            #if($check_kiv != "1")
			<script>
            document.getElementById('cmdTeruskan').style.display = "none";
            </script>
            #end       
               
          #end 
          
        
          
          
          </div></td>
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
      <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onclick="SenaraiBicara('$idpermohonan')" /></td>
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
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio" value="5" disabled onclick="RulerOfTheState('$idpermohonan','$id_perbicaraan','$id_perintah')" $TEMPcheckedMahkamahSyariah >
        Menunggu Keputusan Rujukan Kepada Ruler of The State </td>
      <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" disabled onclick="PertelingkahanKolateral('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
        Pertelingkahan Kolateral </td>
    </tr>
    <tr align="center">
      <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi (BORANG J)</td>
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
       
        <input type="text" size="5" maxlength="2" name="txtTempoh" value="$!tempoh_tunggu_faraid" onblur="this.value=this.value.toUpperCase();" id="txtTempoh" style="text-transform:uppercase;" readonly class="disabled" />
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
      <td colspan="2" width="100%" ><div align="center"> 
      #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_KemaskiniTangguh('$idpermohonan','$id_perbicaraan');" />      
           #if($flag_kemaskini_selesai != "yes")
			<script>
            document.getElementById('Kemaskini').style.display = "none";
            </script>
            #end          
      
          
          
          
          <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')" />
          #end
          
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembaliSenaraiFail('$noFail');"/>
          #if ( $idstatus == "44" || $idstatus == "173" || $idstatus == "175" || $idstatus == "177" )
          <input type="button" name="cmdNotis" value="Seterusnya" onClick="goNotis('$id_permohonan','$idstatus')" />
          #end          
          #end
      
          #if ($flagFromSenaraiPermohonanSek8 == 'yes')
         
           #if ( $idstatus == "174" )
           
           <fieldset id="tableReport1" >
			<legend><strong>Senarai Laporan</strong></legend>
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
			       <tr>
			        <td><a href="#" class="style2" onClick="javascript:cetakBorangJ('$nofail','$idFail','$id_perbicaraan')"><font color="blue"> Borang J </font></a></td>
			      </tr>           
			    </table>
			</fieldset> 
           #end
           
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
        Pindah ke Mahkamah Tinggi kerana ada wasiat  #if($batalWasiat == '1')($namaMahkamah)#end </td>
    
    					
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
        Pindah ke Mahkamah Tinggi kerana harta melebihi RM2 juta #if($batal2juta == '1')($namaMahkamah)#end</td>
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
    #if($namaMahkamah == '')
    <tr>
      <td colspan="2" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_KemaskiniBatal('$idpermohonan','$id_perbicaraan');" />
           #if($flag_kemaskini_selesai != "yes")
			<script>
            document.getElementById('Kemaskini').style.display = "none";
            </script>
            #end    
          
          
          
          <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembaliSenaraiFail('$noFail');"/>
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
    #end
         
  </table>
  </fieldset>
  
  <fieldset>
    <legend>Dokumen Sokongan</legend>
    <table width="60%" border="0">
    <tr>
     <td width="25%" align ="right" scope="col">Dokumen Sokongan</td>
        <td width="1%" scope="col">:</td>
        <td width="74%" colspan="2" scope="col">
         <input type="text" disabled value=$!namaDoC>&nbsp;
         #if ($namaDoC != '')
         <input type="button" name="cmdUpload" disabled id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #else
         <input type="button" name="cmdUpload" id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #end
         #if ($namaDoC != '')
         <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;
         #end
         
         <!-- <input name="cetak" disabled type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;  -->
         
        
   		 
   		 #if ($namaDoC != '')
   		 <input name="deleteSuppDoc1" type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />
   		 #end
   		 
   		 <!-- <input name="deleteSuppDoc1" disabled type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />  -->
   		 
   		 
       
        </td>
    </tr>

    
    </table>
    
    </fieldset>  
    
     #if($namaMahkamah != '') 
    <table width="100%" border="0">
    <tr>
      <td colspan="2" width="100%" ><div align="center"> #if ( $flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView == '' )
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:Skrin_KemaskiniBatal('$idpermohonan','$id_perbicaraan');" />
           #if($flag_kemaskini_selesai != "yes")
			<script>
            document.getElementById('Kemaskini').style.display = "none";
            </script>
            #end    
          
          
          <input type="button" name="cmdBorangI" id="cmdBorangI" value="Hantar ke Mahkamah Tinggi (Borang I)" onClick="semakMTBorangI('$id_perbicaraan')"/>
          <input type="button" name="cmdKembali"  value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          #end
          
          #if ( $flagFromSenaraiFailSek8 == 'yes' )
          <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembaliSenaraiFail('$noFail');"/>
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
    
    </table>
    #end
  #end
 
  
  </td>
  
  </tr>
  
</table>

</td>
</tr>

        
        
        
        
        
        
#end ## END OF #if ( $mode == "view" )

#end ## END OF #if ($idstatus == "41" || $idstatus == "44" || $idstatus == "47" || $idstatus == "172" || $idstatus == "173" || $idstatus == "174" || $idstatus == "175" || $idstatus == "176" || $idstatus == "177" || $idstatus == "25" || $idstatus == "21" || $idstatus == "64" || $idstatus == "163" || $idstatus == "164" || $idstatus == "165" || $idstatus == "166" || $idstatus == "167" || $idstatus == "180" )
<!--------------------------------------------------- END VIEW MODE ---------------------------------------------->
<!--------------------------------------------------- EDIT MODE ---------------------------------------------->
#if( $mode == "edit" )
<input type="hidden" name="id_perintah" id="id_perintah" value="$id_perintah"/>
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value="$id_perbicaraan"/>
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
            #set ($check_kiv = $dataPerintah1.check_kiv)
            #set ($date_kiv = $dataPerintah1.date_kiv)
            #set ($catatan_kiv = $dataPerintah1.catatan_kiv)
            #set ($justifikasi_pegawai = $dataPerintah1.justifikasi_pegawai)
		#end
<tr>
  <td><fieldset>
    <legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
    <table width="100%"  cellspacing="1" cellpadding="1" border="0">
      <tr>
        <td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
        <td width="28%">:&nbsp;$!selectViewPegawai</td>
        #if ($tarikh == "perintah" )
        <td width="12%"><font color="red">*</font>&nbsp;Tarikh Perintah</td>
        <td width="12%">:&nbsp;
          <input name="txdTarikhPerintahEDIT" disabled value="$!tarikh_bicara" size="10" id="txdTarikhPerintahEDIT" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintahEDIT',false,'dmy');" /></td>
        #end
        
        #if ($tarikh == "bicara" )
        <td width="18%"><font color="red">*</font>&nbsp;Tarikh Perbicaraan &nbsp;&nbsp;&nbsp;Terakhir</td>
        <td width="13%">:&nbsp;
          <input name="txdTarikhPerintahEDIT" disabled value="$!tarikh_bicara" size="10" id="txdTarikhPerintahEDIT" type="text" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPerintahEDIT',false,'dmy');" /> </td>
        #end </tr>
      <tr>
        <td width="17%"><font color="red">*</font>&nbsp;Keputusan Perbicaraan</td>
        <td colspan="5">:&nbsp;
          <input name="flag_jenis_keputusan" disabled type="radio" disabled value="0" $TEMPcheckedSelesai onclick="tab_selesai()" />
          Selesai
          <input name="flag_jenis_keputusan" disabled type="radio" disabled value="1" $TEMPcheckedTangguh onclick="tab_tangguh()" />
          Tangguh
          <input name="flag_jenis_keputusan" disabled type="radio" disabled value="2" $TEMPcheckedBatal onclick="tab_batal()" />
          Batal</td>
      </tr>
      <!--By Mohamad Rosli 21/02/2011-->
      <!-- Skrin yang membenarkan edit (START)  -->
      #if ( $flag == "selesai" )
       <tr>
       <td></td>
       <td >
       <fieldset>
       <legend>Maklumat KIV (Jika Ada)</legend>       
       <table width="100%" border="0">
       <tr>
        <td width="20%"><font color="red">*</font>Status KIV</td>
        <td width="1%">:</td>
        <td width="79%">
       
        #if($!check_kiv == "1")
        #set($check_kiv_mode = "checked")
        <input type="radio" name="check_kiv" id="check_kiv" value="1" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" checked/>KIV
        <input type="radio" name="check_kiv" id="check_doc" value="0" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />Dokumen telah dikemukakan
        <input type="radio" name="check_kiv" id="check_doc" value="3" onClick="javascript:buttonAppearJustifikasiPegawai(this);" />Dokumen gagal dikemukakan
        #elseif($!check_kiv == "0")
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />KIV
         <input type="radio" name="check_kiv" id="check_doc" value="0" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" checked/>Dokumen telah dikemukakan
         <input type="radio" name="check_kiv" id="check_doc" value="3" onClick="javascript:buttonAppearJustifikasiPegawai(this);" />Dokumen gagal dikemukakan
        #set($check_kiv_mode = "")
         #elseif($!check_kiv == "3")
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />KIV
         <input type="radio" name="check_kiv" id="check_doc" value="0" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />Dokumen telah dikemukakan
         <input type="radio" name="check_kiv" id="check_doc" value="3" onClick="javascript:buttonAppearJustifikasiPegawai(this);" checked/>Dokumen gagal dikemukakan
        #else
         <input type="radio" name="check_kiv" id="check_kiv" value="1" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />KIV
         <input type="radio" name="check_kiv" id="check_doc" value="0" onClick="javascript:buttonDisAppearJustifikasiPegawai(this)" />Dokumen telah dikemukakan
         <input type="radio" name="check_kiv" id="check_doc" value="3" onClick="javascript:buttonAppearJustifikasiPegawai(this);" />Dokumen gagal dikemukakan
        #set($check_kiv_mode = "")
        #end
        <!-- <input type="checkbox" name="check_kiv" id="check_kiv" $check_kiv_mode value="1"  onclick="" /> -->
         		
        &nbsp;
        </td>
        </tr>
         <tr>
        <td ><font color="red">*</font>KIV Sehingga</td>
        <td >:</td>
        <td >
        <input name="date_kiv" type="text" id="date_kiv" value="$!date_kiv"  size="10" maxlength="10" onKeyUp="javascript:validateIC(event,this,this.value,'date_kiv')" onBlur="trans_date(this.value);"  />
		<a href="javascript:displayDatePicker('date_kiv',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
        </td>
        </tr>
          <tr>
        <td valign="top"><font color="red">*</font>Catatan KIV</td>
        <td valign="top">:</td>
        <td valign="top">
        <textarea name="catatan_kiv" cols="35" rows="3" id="catatan_kiv"  >$!catatan_kiv</textarea>
        </td>
        </tr>
        #if($!check_kiv == "3")
        <tr>
        <td valign="top"><div id="divbuttonGagalKemukakanDoc1" style="display: block" >Justifikasi Pegawai</div></td>
        <td valign="top"><div id="divbuttonGagalKemukakanDoc2" style="display: block" >:</div></td>
        <td valign="top">
        <div id="divbuttonGagalKemukakanDoc3" style="display: block" ><textarea name="justifikasi_pegawai" cols="35" rows="3" id="justifikasi_pegawai"  >$!justifikasi_pegawai</textarea></div>
        </td>
        </tr>
        #end
        <tr>
        <td valign="top"><div id="divbuttonGagalKemukakanDoc1" style="display: none" >Justifikasi Pegawai</div></td>
        <td valign="top"><div id="divbuttonGagalKemukakanDoc2" style="display: none" >:</div></td>
        <td valign="top">
        <div id="divbuttonGagalKemukakanDoc3" style="display: none" ><textarea name="justifikasi_pegawai" cols="35" rows="3" id="justifikasi_pegawai"  >$!justifikasi_pegawai</textarea></div>
        </td>
        </tr>
        </table>
        </fieldset>
        </td>
        <td colspan="4">
        </td>
      </tr>
      #end
		<!-- Skrin yang membenarkan edit (END)  -->
      
      
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
    <li class="TabbedPanelsTab" onclick="setSelected(0);Selesai()">SELESAI</li>
    #end
    #if(  $flag == "tangguh" )
    <!-- value selesai 1 -->
    <li class="TabbedPanelsTab" onclick="setSelected(1);Tangguh()">TANGGUH</li>
    #end
    #if( $flag == "batal" )
    <!-- value selesai 2 -->
    <li class="TabbedPanelsTab" onclick="setSelected(2);Batal()">BATAL</li>
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
              <input type="text" size="15" name="txtJumHartaEDIT" onblur="this.value=this.value.toUpperCase();" id="txtJumHartaEDIT" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumHarta)" />
              </label></td>
          </tr>
          <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
          <tr>
            <td width="47%" ><div align="left">Jumlah Harta yang Dikenakan Bayaran Perintah &nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumHartaDikenakanBayaranPerintah" onblur="this.value=this.value.toUpperCase();" id="txtJumHartaDikenakanBayaranPerintah" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumHartaDikenakanBayaranPerintah)" />
              </label></td>
          </tr>
          <!-- arief add Jumlah Harta yang Dikenakan Bayaran Perintah (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
          <!-- arief add Bayaran Denda Lewat Pendaftaran Open -->
          <tr>
            <td width="47%" ><div align="left">Bayaran Denda Lewat Pendaftaran&nbsp;&nbsp;&nbsp;(RM)</div></td>
            <td width="2%"><div align="right">:</div></td>
            <td width="51%"><label>
              <input type="text" size="15" name="txtJumDendaLewat" onblur="this.value=this.value.toUpperCase();" id="jumDendaLewat" style="text-transform:uppercase;" readonly class="disabled" value="$Util.formatDecimal($!txtJumDendaLewat)" />
              </label></td>
          </tr>
          <!-- arief add Bayaran Denda Lewat Pendaftaran Close -->
          
          <tr>
            <td><div align="left">Jumlah Bayaran&nbsp;&nbsp;(RM)</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input type="text" size="15" name="txtJumBayaranEDIT" id="txtJumBayaranEDIT" value="$!Util.formatDecimal($!bayaran_perintah)" onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtJumBayaranPusaka)" onkeyup="validateNumber(this,this.value);" />
              <input type="hidden" size="12" name="txtJumBayaranEDIT" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranEDIT" style="text-transform:uppercase;" readonly class="disabled" value="$bayaran_perintah" />
              </label></td>
          </tr>
          <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) OPEN -->
          <tr>
          	<td><div align="left">Jumlah Bayaran Sebenar&nbsp;&nbsp;(RM)</div></td>
          	<td><div align="right">:</div></td>
          	<td><label>
            	<input type="text" size="15" name="txtJumBayaranSebenarEDIT" id="txtJumBayaranSebenarEDIT" value="$!Util.formatDecimal($!bayaran_perintah_sebenar)" onblur="validateNumber(this,this.value);validateModal(this,this.value,$!txtJumBayaranPusakaSebenar)" onkeyup="validateNumber(this,this.value);" />
            	<input type="hidden" size="12" name="txtJumBayaranSebenarEDIT" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranSebenarEDIT" style="text-transform:uppercase;" readonly class="disabled" value="$!bayaran_perintah_sebenar" />
            	</label></td>
        </tr>
        <!-- arief add Jumlah Bayaran Sebenar (atas permintaan Pn.Syaida: 16/6/2020) CLOSE -->
          <!-- arief add Pengecualian Bayaran OPEN -->
          <tr>
        	<td width="47%" ><div align="left">Pengecualian Bayaran</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	#if($!check_pengecualian == "1")
        		#set($check_pengecualian_mode = "checked")
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"checked/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"/>Keseluruhan
        	#elseif ($!check_pengecualian == "2")
        		#set($check_pengecualian_mode = "checked")
        		
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"checked/>Keseluruhan
        	#else
        		#set($check_pengecualian_mode = "")
         		<input type="radio" name="check_pengecualian" id="check_pengecualian" value="1" onclick="putValue(this.value)"/>Sebahagian
				<input type="radio" name="check_pengecualian" id="check_pengecualian" value="2" onclick="putValue(this.value)"/>Keseluruhan
        	#end
			</td>
          </tr>
          <!-- arief add Pengecualian Bayaran CLOSE -->
          <!-- arief add textbox Pengecualian Bayaran OPEN-->
          <tr>
        	<tr>
        	<td width="47%" ><div align="left">Catatan Pengecualian</div></td>
        	<td width="1%"><div align="right">:</div></td>
        	<td width="79%">
        	<textarea name="catatan_pengecualian" cols="35" rows="3" id="catatan_pengecualian"  >$!catatan_pengecualian</textarea>
        	</td>
        </tr>
        <!-- arief add textbox Pengecualian Bayaran CLOSE-->
          <tr>
            <td valign="top">No Resit     
          </td>
          
          <td>
          <div align="right">:</div></td>
          
            <td>
            <label><span style="text-transform:uppercase;">
            <input type="text" size="15" name="txtNomborResitPerintahEDIT" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPerintahEDIT" style="text-transform:uppercase;" value="$!NoResit_perintah"/>
            </span></label>
           
            </td>
            
          </tr>
          <tr>
            <td><div align="left">Tarikh Bayaran</div></td>
            <td><div align="right">:</div></td>
            <td><label>
              <input type="text" size="15" name="txdTarikhBayaranPerintahEDIT" id="txdTarikhBayaranPerintahEDIT" value="$!TarikhBayaran_perintah" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranPerintahEDIT',false,'dmy');" /> </label></td>
          </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranPerintah.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Terdapat <b>$getListBayaranPerintah.size()</b> bayaran perintah bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #elseif($getListBayaranPerintah.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran perintah yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran perintah yang lain.
         #end
      </td>   
         </tr>
        </table>
        </fieldset>
      </td>
      
      <td width="34%" valign="top" id="id_cukai">
     
      <fieldset>
          <legend>Bayaran Cukai</legend>
          <table width="100%" border="0">
            <tr>
              <td width="46%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
              <td width="2%"><div align="right" class="style38">:</div></td>
              <td width="52%"><label>
                <input type="text" size="15" name="txtJumBayaranPusakaEDIT" value="$!bayaran_pusaka" id="txtJumBayaranPusakaEDIT" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" />
                <input type="hidden" size="12" name="txtJumBayaranPusakaEDIT" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranPusakaEDIT" style="text-transform:uppercase;" readonly="readonly" class="disabled" value="$!bayaran_pusaka" />
                </label></td>
            </tr>
            <tr>
              <td><div align="left">No Resit</div></td>
              <td><div align="right">:</div></td>
              <td><label><span style="text-transform:uppercase;">
                <input type="text" size="15" name="txtNomborResitPusakaEDIT" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitPusakaEDIT" style="text-transform:uppercase;" value="$!NoResit_pusaka"/>
                </span></label></td>
            </tr>
            <tr>
              <td><div align="left">Tarikh Bayaran</div></td>
              <td><div align="right">:</div></td>
              <td><label>
                <input type="text" size="15" name="txdTarikhBayaranPusakaEDIT" value="$!TarikhBayaran_pusaka" id="txdTarikhBayaranPusakaEDIT" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranPusakaEDIT',false,'dmy');" /> </label></td>
            </tr>
            <tr>
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
             <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranCukai.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranCukai.size()</b> bayaran cukai bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #elseif($getListBayaranCukai.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran cukai yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran cukai yang lain.
         #end
      </td>   
         </tr>
            
          </table>
          </fieldset>
          <div id="mesej_cukai"></div>
          </td>
        <script type="text/javascript">
		
		var str1  = '$tarikhmati_simati';
		var str2  = "01/11/1991";		
		var dt1   = parseInt(str1.substring(0,2),10);
	    var mon1  = parseInt(str1.substring(3,5),10)-1;
	    var yr1   = parseInt(str1.substring(6,10),10);   
	    var dt2   = parseInt(str2.substring(0,2),10);
	    var mon2  = parseInt(str2.substring(3,5),10)-1;
	    var yr2   = parseInt(str2.substring(6,10),10);   
        var date1 = new Date(yr1, mon1, dt1);
        var date2 = new Date(yr2, mon2, dt2);		
		
		if(date1 > date2)
		{		   
		   document.getElementById('id_cukai').style.display="none";
		    $jquery("#mesej_cukai").html("");
		} 
		else
		{			
			document.getElementById('id_cukai').style.display="";
			 $jquery("#mesej_cukai").html("<span  style='color:red'><blink>Sila pastikan maklumat bayaran cukai diisi jika simati meninggal sebelum 01/11/1991</blink></span>");
		}
		</script> 
        <td width="33%" valign="top"><fieldset>
          <legend>Bayaran Baitulmal</legend>
          <table width="100%" border="0">
            <tr>
              <td width="53%">Jumlah Bayaran&nbsp;&nbsp;(RM)</td>
              <td width="3%"><div align="right">:</div></td>
              <td width="44%"><label>
                <input type="text" size="15" name="txtJumBayaranBaitulmalEDIT" value="$!Util.formatDecimal($!bayaran_baitulmal)" id="txtJumBayaranBaitulmalEDIT" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" />
                <input type="hidden" size="12" name="txtJumBayaranBaitulmalEDIT" onblur="this.value=this.value.toUpperCase();" id="txtJumBayaranBaitulmalEDIT" style="text-transform:uppercase;" readonly="readonly" class="disabled" value="$bayaran_baitulmal" />
                </label></td>
            </tr>
            <tr>
              <td><div align="left">No Resit</div></td>
              <td><div align="right">:</div></td>
              <td><label><span style="text-transform:uppercase;">
                <input type="text" size="15" name="txtNomborResitBaitulmalEDIT" onblur="this.value=this.value.toUpperCase();" id="txtNomborResitBaitulmalEDIT" style="text-transform:uppercase;" value="$!NoResit_baitulmal"/>
                </span></label></td>
            </tr>
            <tr>
              <td><div align="left">Tarikh Bayaran</div></td>
              <td><div align="right">:</div></td>
              <td><label>
                <input type="text" size="15" name="txdTarikhBayaranBaitulmalEDIT" value="$!TarikhBayaran_baitulmal" id="txdTarikhBayaranBaitulmalEDIT" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
                <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhBayaranBaitulmalEDIT',false,'dmy');" /> </label></td>
            </tr>
            <tr>
              <td colspan="3"><input type="text" style="visibility: collapse"  readonly="readonly" class="disabled" /></td>
            </tr>
           <tr>
      <td colspan="4" align="justify"  style="border-top:1px solid #088A4B;">
         #if($getListBayaranBaitulmal.size()>1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Terdapat 
         <b>$getListBayaranBaitulmal.size()</b> bayaran baitulmal bagi permohonan ini. Sila
         klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #elseif($getListBayaranBaitulmal.size()==1)
         Diruangan ini, dipaparkan maklumat bayaran baitulmal yang <strong>pertama</strong>. Untuk bayaran tambahan, sila klik <a href="javascript:paparKemaskiniKemaskiniBayaran('$!id_fail_atheader','$!no_fail_atheader')" >
           <font color="blue">disini</font></a> untuk menambah, menyemak dan mengemaskini bayaran baitulmal yang lain.
         #end
      </td>   
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
        <td colspan="3" width="100%" >
       
        <textarea name="txtCatatanSelesaiEDIT" cols="80%" rows="8" id="txtCatatanSelesaiEDIT">$!catatan</textarea>
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
        <td colspan="3" width="100%" ><div  class="disabled" id="word_count"></div>
        </td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><div align="left"> $!perhatian </div></td>
      </tr>
      <tr>
        <td colspan="3" width="100%" ><div align="center">
        #if($userRole != "user_ppk")
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript: kemaskini_selesai('$idpermohonan','$id_perbicaraan','$id_perintah');" />
        #end 
            <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
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
        <td colspan="2" align="left"><input type="button" name="cmdTangguh" id="cmdTangguh" value="Maklumat Tangguh" onclick="SenaraiBicara('$idpermohonan')" /></td>
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
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="5" onclick="RulerOfTheStateEdit('$idpermohonan')" $TEMPcheckedMahkamahSyariah >
          Menunggu Keputusan Rujukan Kepada Ruler of The State </td>
        <td align="left"><input name="flag_tangguh" id="flag_tangguh" type="radio"  value="6" onclick="PertelingkahanKolateralEdit('$idpermohonan','$id_perbicaraan')" $TEMPcheckedPertelingkahanKolateral >
          Pertelingkahan Kolateral </td>
      </tr>
      <tr align="center">
        <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;atau Mahkamah Tinggi (BORANG J)</td>
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
          <input type="text" size="5" maxlength="2" name="txtTempoh" value="$!tempoh_tunggu_faraid" onblur="this.value=this.value.toUpperCase();" id="txtTempoh" style="text-transform:uppercase;"  />
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
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count2"></div>
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
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count3"></div>
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
        #if($userRole != "user_ppk")
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript: Simpan_Edit_Tangguh('$idpermohonan','$id_perbicaraan');" />
        #end
            <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');"/>
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
        <td align="left"><input name="flag_batal" id="flag_batal" type="radio"  value="1" $TEMPcheckedMahkamahTinggiWasiat onclick="MahkamahTinggiEdit('$idpermohonan','$id_perbicaraan','$id_perintah')" >
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
        <td colspan="2" width="100%" ><div  class="disabled" id="word_count4"></div>
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
        #if($userRole != "user_ppk")
            <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript: Simpan_Edit_Batal('$idpermohonan','$id_perbicaraan');" />
        #end
            <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: kembali_list('$idpermohonan','$id_perbicaraan');" />
          </div></td>
      </tr>
     
      
    
    </table>
    </fieldset>
    
    <fieldset>
    <legend>Dokumen Sokongan</legend>
    <table width="60%" border="0">
    <tr>
     <td width="25%" align ="right" scope="col">Dokumen Sokongan</td>
        <td width="1%" scope="col">:</td>
        <td width="74%" colspan="2" scope="col">
         <input type="text" disabled value=$!namaDoC>&nbsp;
         #if ($namaDoC != '')
         <input type="button" name="cmdUpload" disabled id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #else
         <input type="button" name="cmdUpload" id="cmdUpload" value="Muat naik Dokumen" onclick="uploadSuppDoc('$id_permohonan','$idSimati')"/>&nbsp;
         #end
         #if ($namaDoC != '')
         <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;
         #end
         
         <!-- <input name="cetak" disabled type="button" value="Muat turun Dokumen" onclick="doOpen($idSimati)" />&nbsp;  -->
         
        
   		 
   		 #if ($namaDoC != '')
   		 <input name="deleteSuppDoc1" type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />
   		 #end
   		 
   		 <!-- <input name="deleteSuppDoc1" disabled type="button" value="Padam Dokumen" onclick="deleteSuppDoc()" />  -->
   		 
   		 
       
        </td>
    </tr>
    <tr>
    
    </tr>
    
    </table>
    
    </fieldset>   
    #end

    </td>
    
    </tr>
    
  </table>
  
  
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
        <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratPindahMT('$noFail')"><font color="blue"> Surat Pindah Mahkamah Tinggi</font></a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangI('$noFail')"><font color="blue"> Borang I</font></a></td>
      </tr>
       <tr>
        <td ><a href="#" class="style2" onClick="javascript:buktiPenyampaian('$noFail','$idFail')"><font color="blue"> Bukti Penyampaian (Mahkamah Tinggi)</font></a></td>
      </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<!--END CODE-->


#parse("app/ppk/headerppk_script.jsp") 
<script>
/* $jquery( document ).ready(function() {
	alert(flagForView);
	var CAPAIAN_FAIL_UNIT_LUAR = document.${formName}.CAPAIAN_FAIL_UNIT_LUAR.value;
	
	if(CAPAIAN_FAIL_UNIT_LUAR == "N"){
		$jquery('[name=flag_jenis_keputusan]').attr('disabled',false);
		$jquery('[name=EDITsocPegawaiPengendali]').attr('disabled',false);
	}else{
		//$jquery( '#flag_jenis_keputusan input:radio' ).attr( 'disabled', true );
		$jquery('[name=flag_jenis_keputusan]').attr('disabled',true);
		$jquery('[name=EDITsocPegawaiPengendali]').attr('disabled',true);
		
	}
}); */


	function cetakSuratPindahMT(noFail) {
	

	var flag_batal = $jquery("input[name='flag_batal']:checked").val();
	var namalaporan = "";
	if(flag_batal=="1"){
		namalaporan = "SuratPindahMTII";
	}else{
		namalaporan = "SuratPindahMTPerbicaraan";
	}

	
		//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMT&flagReport=S";
		var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report="+namalaporan+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}

	function cetakBorangI(noFail) {
	    //var url = "../servlet/ekptg.report.ppk.BorangI?nofail="+noFail;	
		var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=BorangIPerbicaraan&flagReport=B";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}

	function buktiPenyampaian(noFail,idfail){
	    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianMT?nofail="+noFail+"&idfail="+idfail+"&template=BuktiPenyampaianMTPerbicaraan";  
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	}
	
	function changeGetAlamatMahkamah() {
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
		document.${formName}.command.value = "tab_batal";
		document.${formName}.command2.value = "changeGetAlamatMahkamah";
		document.${formName}.submit();
	}

function DocSupportAppear(){
	
	

		if((document.${formName}.flag_batal[0].checked == true) || (document.${formName}.flag_batal[3].checked == true))
		{
			
			document.getElementById('dokumensokongan').style.display="block";
		}
		
}

function MahkamahAppear(){
	
	

	if((document.${formName}.flag_batal[0].checked == true) || (document.${formName}.flag_batal[3].checked == true))
	{
		
		document.getElementById('MahkamahAppear').style.display="block";
		document.getElementById('MahkamahAppear2').style.display="none";
	}
	
}

function MahkamahAppear2(){
	
	

	if((document.${formName}.flag_batal[0].checked == true) || (document.${formName}.flag_batal[3].checked == true))
	{
		
		document.getElementById('MahkamahAppear2').style.display="block";
		document.getElementById('MahkamahAppear').style.display="none";
	}
	
}

function DocSupportDisappear(){
	
	

	if((document.${formName}.flag_batal[1].checked == true) || (document.${formName}.flag_batal[2].checked == true) || (document.${formName}.flag_batal[4].checked == true) )
	{
		
		document.getElementById('dokumensokongan').style.display="none";
	}
	
}

function MahkamahDisappear(){
	
	

	if((document.${formName}.flag_batal[1].checked == true) || (document.${formName}.flag_batal[2].checked == true) || (document.${formName}.flag_batal[4].checked == true) )
	{
		
		document.getElementById('MahkamahAppear').style.display="none";
		
	}
	
}

function MahkamahDisappear2(){
	
	

	if((document.${formName}.flag_batal[1].checked == true) || (document.${formName}.flag_batal[2].checked == true) || (document.${formName}.flag_batal[4].checked == true) )
	{
		
		
		document.getElementById('MahkamahAppear2').style.display="none";
	}
	
}

function uploadSuppDoc(id,IdSimati)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.SkrinPopupUploadDokumen?&id_Permohonan="+id+"&IdSimati="+IdSimati+"&id_jenisDoc=99205";
	var hWnd = window.open(url,'printuser','width=350,height=200, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	
}

function doOpen(id) {
	//alert('id : '+id);
    var url = "../servlet/ekptg.view.ppk.DisplayBuktiKematian?id="+id+"&jenisDoc=99205";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteSuppDoc()
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method = "POST";
	document.${formName}.command2.value = "deleteSuppDocMode";
	alert("test");
	document.${formName}.command.value = "tab_batal";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.submit();
	}
	else
		{
		return
		}
	
	
}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

	function cetaksuratBatalBicara(noFail,idFail,id_perbicaraan) {
		var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+escape(noFail)+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan+"&report=suratBatalBicara&flagReport=S";
	
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
	/*var oEditor =FCKeditorAPI.GetInstance('txtCatatanSelesai'); 
	if(oEditor) { 	
		txtCatatanSelesai = oEditor.GetXHTML(true); 		
	} */
	 	
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
	
	if(document.${formName}.check_kiv.value == ""){
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
	}
	
	
	if(document.${formName}.check_kiv.value == "1"){
		if(document.${formName}.date_kiv.value == ""){
			alert("Sila masukkan \"Tarikh KIV Sehingga\" ");
	  		document.${formName}.date_kiv.focus(); 
			return;
		}
	
		if(document.${formName}.catatan_kiv.value == ""){
			alert("Sila masukkan \"Catatan KIV\" ");
	  		document.${formName}.catatan_kiv.focus(); 
			return;
		}
	}
	//alert(document.${formName}.valueKIV.value);
	if(document.${formName}.check_kiv.value == "0"){
		
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
		
	}
	
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
	
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Bayaran tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhBayaranPerintah.focus();
	 	return;	
	}	
	if(date5 < trMOHON){
   		alert("Sila pastikan Tarikh Perintah/Tarikh Perbicaraan Terakhir tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhPerintah.focus();
	 	return;							
	}	
	else{
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
	/* var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanTangguh = oEditor.GetXHTML(true); 		
	} */
	
	var txtPendapatTangguh = null; 
	/* var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtPendapatTangguh = oEditor.GetXHTML(true); 		
	} */
	
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

function Skrin_Kemaskini(idpermohonan,id_perbicaraan,id_bayaran_perintah) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan2.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan";
	document.${formName}.command.value = "Skrin_Kemaskini";
	document.${formName}.submit();	
}

function buttonAppearJustifikasiPegawai(justifikasiPegawai)
{
		
		var dvPassport1 = document.getElementById("divbuttonGagalKemukakanDoc1");
		var dvPassport2 = document.getElementById("divbuttonGagalKemukakanDoc2");
		var dvPassport3 = document.getElementById("divbuttonGagalKemukakanDoc3");
        dvPassport1.style.display = justifikasiPegawai.checked ? "block" : "none";
        dvPassport2.style.display = justifikasiPegawai.checked ? "block" : "none";
        dvPassport3.style.display = justifikasiPegawai.checked ? "block" : "none";

        
}

function buttonDisAppearJustifikasiPegawai(justifikasiPegawai)
{
		
		var dvPassport1 = document.getElementById("divbuttonGagalKemukakanDoc1");
		var dvPassport2 = document.getElementById("divbuttonGagalKemukakanDoc2");
		var dvPassport3 = document.getElementById("divbuttonGagalKemukakanDoc3");
        dvPassport1.style.display = justifikasiPegawai.checked ? "none" : "block";
        dvPassport2.style.display = justifikasiPegawai.checked ? "none" : "block";
        dvPassport3.style.display = justifikasiPegawai.checked ? "none" : "block";

        
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
	var justifikasi_pegawai	 = document.getElementById("justifikasi_pegawai").value; 
	
	if(document.${formName}.check_kiv.value != "3"){
		document.getElementById("justifikasi_pegawai").value = ""; 
	}
		

	if(document.${formName}.check_kiv.value == ""){
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
	}
	
	if(document.${formName}.check_kiv.value == "1"){
		if(document.${formName}.date_kiv.value == ""){
			alert("Sila masukkan \"Tarikh KIV Sehingga\" ");
	  		document.${formName}.date_kiv.focus(); 
			return;
		}
	
		if(document.${formName}.catatan_kiv.value == ""){
			alert("Sila masukkan \"Catatan KIV\" ");
	  		document.${formName}.catatan_kiv.focus(); 
			return;
		}
	}
	if(document.${formName}.check_kiv.value == "2"){
		
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
		
	}
	
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
	/* var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanBatal = oEditor.GetXHTML(true); 		
	} */
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

function semakMTBorangI(id_perbicaraan) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=hantarBorangI&dari=KeputusanPerbicaraan&idPerbicaraan="+id_perbicaraan;
	var hWnd = window.open(url,'Cetak','width=625,height=500, resizable=yes,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


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
 document.getElementById('word_count').innerHTML = (500000 - count) + " Baki Aksara";
 
 if (count > 500000) {
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
 document.getElementById('word_count5').innerHTML = (500000 - count) + " Baki Aksara";
 
 if (count > 500000) {
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

 	function putValue(inputA){
 		$jquery('#valueKIV').val(inputA);
 	}

 	function cetakBorangJ(nofail,idFail,id_perbicaraan) {
 		var url = "../servlet/ekptg.report.ppk.BorangJ?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
 	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
 	    if ((document.window != null) && (!hWnd.opener))
 		hWnd.opener = document.window;
 	    if (hWnd.focus != null) hWnd.focus();	
 	}
 	
//arief add function
function getDiff(idpermohonan,id_perbicaraan){
	content = document.f1.txdTarikhLahirWaris.value;
	if(content != "" && content.substring(6,10).length == 4 && parseInt(content.substring(6,10))>1000)
	{
		  var tarikh_mohon = new Date();
		  var tarikhMohon = new Date(tarikh_mohon.getYear(),tarikh_mohon.getMonth(),tarikh_mohon.getDate());
		  var tarikhMohonYear = tarikh_mohon.getYear();
		  var tarikhMohonMonth = tarikh_mohon.getMonth();
		  var tarikhMohonDate = tarikh_mohon.getDate();

		  var tarikh_mati = new Date(content.substring(6,10),
				  content.substring(3,5),                   
				  content.substring(0,2)                 
		                     );

		  var tarikhMatiYear = tarikh_mati.getYear();
		  var tarikhMatiMonth = tarikh_mati.getMonth()-1;
		  var tarikhMatiDate = tarikh_mati.getDate();
		  var diff = {};
		  var diffString = "";
		  var yearString = "";
		  var monthString = "";
		  var dayString = "";


		  bilTahun = tarikhMohonYear - tarikhMatiYear;
		
		  if (tarikhMohonMonth >= tarikhMatiMonth)
		  var bilBulan = tarikhMohonMonth - tarikhMatiMonth;
		  else {
			  bilTahun--;
		    var bilBulan = 12 + tarikhMohonMonth -tarikhMatiMonth;
		  }

		  if (tarikhMohonDate >= tarikhMatiDate)
		    var bilHari = tarikhMohonDate - tarikhMatiDate;
		  else {
			  bilBulan--;
		    var bilHari = 31 + tarikhMohonDate - tarikhMatiDate;

		    if (monthAge < 0) {
		    	bilBulan = 11;
		    	bilTahun--;
		    }
		  }

		  document.f1.txtBezaTarikhPermohonanDenganTarikhMati.value = bilTahun ;	 			
	
	    }t
}
</script>
