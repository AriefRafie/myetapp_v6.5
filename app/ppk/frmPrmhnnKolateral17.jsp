<script>
$jquery(document).ready(function(){
$jquery('#txtMasaBicara').timepickr({
format24: '{h:02.d}{m:02.d}',
convention: 24,
resetOnBlur:false 
});
});
</script>

#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $idstatus = $data.get("id_status") ) 
#foreach($list in$listSemak) 
	#set($nofail=$list.noFail) 
	#set($negeri=$list.pmNama_negeri)
	#set($daerah=$list.namadaerah) 
	#set($unit=$list.namaPejabat)	
	#set($seksyen=$list.seksyen) 
	#set($statusFail=$list.keterangan)
	#set($tarikhMohon=$list.tarikhMohon) 
	#set($namaSimati=$list.namaSimati)
	#set($namaSipemohon=$list.namaPemohon) 
	#set($idFail=$list.idFail)
	#set($id_permohonan=$list.idPermohonan)
<!-- case utk butang Seterusnya ke Notis -->
<input type="hidden" name="id_permohonan" value="$id_permohonan">
#end

<input type="hidden" name="idpermohonan" id="idpermohonan"
	value="$idpermohonan" />
<input type="hidden" name="id_perbicaraan" id="id_perbicaraan"
	value="$id_perbicaraan" />
<input type="hidden" name="sorReason" id="sorReason" value="6" />
<!-- value = 6 [pilihan radio button adalah Pertelingkahan Kolateral] -->
<input type="hidden" readonly="readonly" name="idsuburusanstatusfail"
	id="idsuburusanstatusfail" value="$idsuburusanstatusfail" />
<input type="hidden" name="idFail" value="$idFail">
<input type="hidden" name="nofail" value="$nofail">
<input name="id_status" id="id_status" type="hidden" value="$idstatus" />
<input type="hidden" name="form_token"
	value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="tabId" id="tabId" value="$selectedTab" />
<input type="hidden" name="tarikhMohon" id="tarikhMohon"
	value="$tarikhMohon">
#if($!headerppk.size()>0) #parse("app/ppk/headerppk.jsp") #end

<fieldset id="header_lama" style="display: none"><legend>MAKLUMAT
PERMOHONAN</legend>
<table width="100%">
	<tr>
		<td width="50%">
		<table width="101%" cellpadding="1" cellspacing="1" border="0">
			<tr>
				<td width="33%" style="text-transform: uppercase">
				<div align="right">No. Fail</div>
				</td>
				<td width="2%">:</td>
				<td width="65%"><font color="blue">$nofail</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase">Negeri</div>
				</td>
				<td>:</td>
				<td><font color="blue">$negeri.toUpperCase()</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase">Daerah /
				Jajahan</div>
				</td>
				<td>:</td>
				<td><font color="blue">$daerah.toUpperCase()</font></td>
			</tr>
			<tr>
				<td valign="top">
				<div align="right" style="text-transform: uppercase">Unit</div>
				</td>
				<td valign="top">:</td>
				<td><font color="blue">$unit.toUpperCase()</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase"></div>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>

		<td width="50%">
		<table width="100%" cellpadding="1" cellspacing="1" border="0">
			<tr>
				<td valign="top">
				<div align="right" style="text-transform: uppercase">Status
				Fail</div>
				</td>
				<td width="2%" valign="top">:</td>
				<td width="60%"><font color="blue">$statusFail.toUpperCase()</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase">Seksyen</div>
				</td>
				<td>:</td>
				<td><font color="blue">$seksyen.toUpperCase()</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase">Tarikh
				Mohon</div>
				</td>
				<td>:</td>
				<td><font color="blue">$tarikhMohon</font></td>
			</tr>
			<tr>
				<td>
				<div align="right" style="text-transform: uppercase">Nama
				Simati</div>
				</td>
				<td>:</td>
				<td><font color="blue">$namaSimati.toUpperCase()</font></td>
			</tr>
			<tr>
				<td>
				<div align="right"><span style="text-transform: uppercase">Nama
				Pemohon</span></div>
				</td>
				<td>:</td>
				<td><font color="blue">$namaSipemohon.toUpperCase()</font></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</fieldset>
#if($!headerppk.size()>0) #else
<script type="text/javascript">
if(document.getElementById("header_lama").style.display=="none")
{
document.getElementById("header_lama").style.display="block";
}
</script>
#end 
#foreach($data in $dataPerbicaraan) 
	#set ($idPemohon = $data.idPemohon) 
	#set ($id_perbicaraan = $data.id_perbicaraan) 
	#set($tarikhBicara = $data.tarikh_bicara) 
	#set ($idUnitpsk = $data.id_unitpsk) 
	#set ($namaPegawai = $data.nama_pegawai) 
	#set($txtMasaBicara = $data.masa_bicara) 
	
#end

<p></p>

#if ( $addMode == "yes" )

<fieldset><legend>PERTELINGKAHAN KOLATERAL</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td colspan="3"><input type="button" name="cmdTangguhKolateral"
			id="cmdTangguhKolateral" value="Senarai Tangguh Kolateral"
			onclick="SenaraiBicaraKolateral('$idpermohonan')" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Tarikh Perakuan Kolateral</td>
		<td>:</td>
		<td><input type="text" name="txdTarikhPerakuanAdd"
			value="$!txdTarikhPerakuanAdd" size="11" id="txdTarikhPerakuanAdd"
			onblur="check_date(this);getTarikhBicara()"
			onkeyup="validateNumber(this,this.value);" /> <img
			src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhPerakuanAdd',false,'dmy');" /></td>
	</tr>
	<tr>
		<td width="25%"><font color="red">*</font>&nbsp;Tarikh Bicara</td>
		<td width="1%">:</td>
		<td width="74%"><input type="text" name="txdTarikhBicara"
			id="txdTarikhBicara" value="$!tarikhBicara" size="11"
			onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
		<img src="../img/calendar.gif"
			onclick="displayDatePicker('txdTarikhBicara',false,'dmy');" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Masa Bicara</td>
		<td>:</td>
		<td><input type="text" name="txtMasaBicara" id="txtMasaBicara"
			value="$!txtMasaBicara" onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="4" size="4" />
		&nbsp;<font color="blue"><em>format 24 jam (HHMM)</em></font></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Tempat Bicara</td>
		<td>:</td>
		<td>$selectBicara<input name="id_pejabatjkptg"
			id="id_pejabatjkptg" type="hidden" value="$id_pejabatjkptg" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Alamat</td>
		<td>:</td>
		<td><input type="text" size="52" name="txtAlamatBicara1"
			id="txtAlamatBicara1" value="$!alamat1" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara2"
			id="txtAlamatBicara2" value="$!alamat2" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara3"
			id="txtAlamatBicara3" value="$!alamat3" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Poskod</td>
		<td>:</td>
		<td><input type="text" name="txtPoskod" id="txtPoskod"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="5" size="5"
			value="$!poskod" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Negeri</td>
		<td>:</td>
		<td>$selectNegeri<input name="id_negeri" id="id_negeri"
			type="hidden" value="$id_negeri" /></td>
	</tr>
	<tr>
		<td valign="top"><font color="red">*</font>&nbsp;Pegawai
		Pengendali</td>
		<td valign="top">:</td>
		<td valign="top">$selectPegawai</td>
	</tr>
	<tr>
		<td width="25%" valign="top">&nbsp;&nbsp;&nbsp;Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="74%" valign="top"><textarea name="txtCatatanAdd"
			cols="80%" rows="8" id="txtCatatanAdd">$!txtCatatanAdd</textarea></td>
	</tr>
	<tr>
		<td colspan="3" valign="top">
		<div class="disabled" id="word_count"></div>
		</td>
	</tr>

	<!-- ADD MODE -->
	<script> 
            var area = new FCKeditor("txtCatatanAdd");
            area.BasePath = '/${appname}/library/fck/' ;
            area.ReplaceTextarea();             	
          </script>

	<tr>
		<td valign="top">&nbsp;</td>
		<td valign="top">&nbsp;</td>
		<td valign="top">&nbsp;</td>
	</tr>
</table>
</fieldset>

<p></p>
<fieldset><legend><strong>PIHAK YANG
MENENTANG (PLANTIF)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="3%" style="text-transform: uppercase"></td>
		<!-- <td width="6%"><b>No.</b></td>-->
		<td width="36%" style="text-transform: uppercase">Nama</td>
		<td width="29%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="32%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $senarai in $dataListWaris ) 
		#if ($senarai.bil == '') 
			#set($row = 'row1') 
		#elseif ($senarai.bil % 2 != 0) 
			#set ($row = 'row1')
		#else 
			#set ($row = 'row2') 
	#end

	<tr>
		<td class="$row"><input type="checkbox" name="cbsemaksP"
			value="$senarai.id_ob" onClick="checkit1()"></td>
		<!--<td class="$row">$senarai.bil</td>-->
		<td class="$row">$senarai.nama_ob.toUpperCase()</td>
		<td class="$row">$senarai.no_kp_baru</td>
		<td class="$row">$senarai.keterangan</td>
	</tr>
	#end
</table>
</fieldset>

<p></p>
<fieldset><legend><strong>PIHAK YANG
DITENTANG (DEFENDEN)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="3%" style="text-transform: uppercase"></td>
		<!-- <td width="6%"><b>No.</b></td>-->
		<td width="36%" style="text-transform: uppercase">Nama</td>
		<td width="29%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="32%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $senarai in $dataListWaris ) 
		#if ($senarai.bil == '') 
			#set ($row = 'row1') 
		#elseif ($senarai.bil % 2 != 0) 
			#set ($row = 'row1')
		#else 
			#set ($row = 'row2') 
		#end

	<tr>
		<td class="$row"><input type="checkbox" name="cbsemaksD"
			value="$senarai.id_ob" onClick="checkit2()"></td>
		<!-- <td class="$row">$senarai.bil</td>-->
		<td class="$row">$senarai.nama_ob.toUpperCase()</td>
		<td class="$row">$senarai.no_kp_baru</td>
		<td class="$row">$senarai.keterangan</td>
	</tr>
	#end
</table>
</fieldset>
<p></p>
<table width="100%">
	<tr>
		<td>
		<div align="center"><input type="button" name="Simpan"
			id="Simpan" value="Simpan"
			onclick="javascript:Simpan_AddKolateral();" /> 
		#if ( $idstatus == "18" ) 
			<input name="cmdKembali" type="button" value="Kembali"
				onclick="javascript: kembali_skrin2TangguhKolAdd('$idpermohonan','$id_perbicaraan');" />
		#end 
		#if ( $idstatus == "44" || $idstatus == "172"|| $idstatus == "173" 
				|| $idstatus == "174" || $idstatus == "175" || $idstatus ==	"176" || $idstatus == "177" ) 
			<input name="cmdKembali" type="button" value="Kembali"
			onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
		#end
		</div>
		</td>
	</tr>
</table>
#end
<!-- close for add mode -->



#if ( $viewMode == "yes" ) 
#if ( $flag == "onChange" ) 
#foreach($info in $dataPerintahKolateral) 
	#set ($id_unitpsk = $info.id_unitpsk) 
	#set ($tarikh_perintah = $info.tarikh_perintah) 
	#set ($flag_jenis_keputusan = $info.flag_jenis_keputusan) 
	#set ($catatan = $info.catatan) 
	#set ($flag_tangguh = $info.flag_tangguh) 
	#set ($flag_batal = $info.flag_batal) 
	#set ($sebab_batal = $info.sebab_batal) 
	#set ($sebab_tangguh = $info.sebab_tangguh) 
	#set ($keputusan_mahkamah = $info.keputusan_mahkamah) 
	#set ($id_pejabatmahkamah = $info.id_pejabatmahkamah) 
	#set ($alamat1 = $info.alamat1) 
	#set ($alamat2 = $info.alamat2) 
	#set ($alamat3 = $info.alamat3) 
	#set ($poskod = $info.poskod) 
	#set ($id_negeri = $info.id_negeri) 
	#set ($tarikh_bicara = $info.tarikh_bicara) 
	#set ($masa_bicara = $info.masa_bicara) 
	#set ($sebab_pertelingkahan = $info.sebab_pertelingkahan) 
	#set ($id_kolateralmst = $info.id_kolateralmst) 
	#set ($tarikh_perakuan = $info.tarikh_perakuan) 

#end 

#end

<input type="hidden" name="id_kolateralmst" value="$id_kolateralmst">

<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(0);permohonan()">PERMOHONAN</li>
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(1);keputusan()">KEPUTUSAN</li>
</ul>



<div class="TabbedPanelsContentGroup">

<div class="TabbedPanelsContent">

<fieldset><legend>PERTELINGKAHAN KOLATERAL</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td colspan="3"><input type="button" name="cmdTangguhKolateral"
			id="cmdTangguhKolateral" value="Senarai Tangguh Kolateral"
			onclick="SenaraiBicaraKolateral('$idpermohonan')" /></td>
	</tr>
	<tr>
		<td>Tarikh Perakuan Kolateral</td>
		<td>:</td>
		<td><input name="txdTarikhPerakuanAdd" value="$!tarikh_perakuan"
			size="11" id="txdTarikhPerakuan" type="text" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>Pegawai Pengendali</td>
		<td>:</td>
		<td>$selectViewPegawai</td>
	</tr>
	<tr>
		<td width="24%">Tarikh Bicara</td>
		<td width="1%">:</td>
		<td width="75%"><input type="text" name="txdTarikhBicara"
			id="txdTarikhBicara" value="$!tarikh_perintah" size="11" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>Masa Bicara</td>
		<td>:</td>
		<td><input type="text" name="txtMasaBicara" value="$!masa_bicara"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="4" size="4"
			readonly class="disabled" /> <em>&nbsp;<font color="blue">format
		24 jam (HHMM)</font></em></td>
	</tr>
	<tr>
		<td>Tempat Bicara</td>
		<td>:</td>
		<td>$selectViewBicara <input name="id_pejabatjkptg"
			id="id_pejabatjkptg" type="hidden" value="$id_pejabatmahkamah" /></td>
	</tr>
	<tr>
		<td>Alamat</td>
		<td>:</td>
		<td><input type="text" size="52" name="txtAlamatBicara1"
			id="txtAlamatBicara1" value="$!alamat1" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara2"
			id="txtAlamatBicara2" value="$!alamat2" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara3"
			id="txtAlamatBicara3" value="$!alamat3" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>Poskod</td>
		<td>:</td>
		<td><input type="text" name="txtPoskod" id="txtPoskod"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="5" size="5"
			value="$!poskod" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td>Negeri</td>
		<td>:</td>
		<td>$selectNegeri</td>
	</tr>
	<tr>
		<td width="24%" valign="top">Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="75%" valign="top"><textarea name="txtCatatanAdd"
			cols="80%" rows="8" id="txtCatatanAdd">$!sebab_pertelingkahan</textarea>
		</td>
	</tr>

	<!-- VIEW MODE -->
	<script> 			  
              	var area = new FCKeditor("txtCatatanAdd");
	      	area.BasePath = '/${appname}/library/fck/' ;
		area.ReplaceTextarea(); 
				
				//syntact utk disabledkan fckEditor
			  	var oEditor = FCKeditorAPI.GetInstance('txtCatatanAdd');				
				function FCKeditor_OnComplete( oEditor ){				
					oEditor.EditorDocument.body.contentEditable='false';
					oEditor.EditorDocument.designMode='off';
					oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
				}				
			  </script>

</table>
</fieldset>

#foreach($infoPlanitif in $dataWarisPlanitif) 
	#set ($id_ob = $infoPlanitif.id_ob) 
	#set ($nama_ob = $infoPlanitif.nama_ob) 
	#set ($no_kp_baru = $infoPlanitif.no_kp_baru) 
	#set ($id_saudara = $infoPlanitif.id_saudara) 
	#set ($keterangan = $infoPlanitif.keterangan)
    #set ($jenis_ob = $infoPlanitif.jenis_ob) 
    
#end

<p></p>
<fieldset><legend><strong>PIHAK YANG
MENENTANG (PLANTIF)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="8%" style="text-transform: uppercase">
		<div align="center">No.</div>
		</td>
		<td width="31%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $infoPlanitif in $dataWarisPlanitif ) 
		#if ($infoPlanitif.bil == '') 
			#set ($row = 'row1') 
		#elseif ($infoPlanitif.bil % 2 != 0) 
			#set ($row = 'row1') 
		#else 
			#set ($row = 'row2') 
	
	#end

	<tr>
		<td class="$row">$infoPlanitif.bil</td>
		<td class="$row">$infoPlanitif.nama_ob.toUpperCase()</td>
		<td class="$row">$infoPlanitif.no_kp_baru</td>
		<td class="$row">$infoPlanitif.keterangan</td>
	</tr>
	#end
</table>
</fieldset>

#foreach($infoDefendan in $dataWarisDefendan) 
	#set ($id_ob = $infoDefendan.id_ob) 
	#set ($nama_ob = $infoDefendan.nama_ob) 
	#set ($no_kp_baru = $infoDefendan.no_kp_baru) 
	#set ($id_saudara = $infoDefendan.id_saudara) 
	#set ($keterangan = $infoDefendan.keterangan)
	#set ($jenis_ob = $infoDefendan.jenis_ob) 

#end

<p></p>
<fieldset><legend><strong>PIHAK YANG
DITENTANG (DEFENDEN)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="7%" style="text-transform: uppercase">
		<div align="center">No.</div>
		</td>
		<td width="32%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $infoDefendan in $dataWarisDefendan ) 
		#if ($infoDefendan.bil == '') 
			#set ($row = 'row1') 
		#elseif ($infoDefendan.bil % 2 != 0) 
			#set ($row = 'row1') 
		#else 
			#set ($row = 'row2') 
	
	#end

	<tr>
		<td class="$row">$infoDefendan.bil</td>
		<td class="$row">$infoDefendan.nama_ob.toUpperCase()</td>
		<td class="$row">$infoDefendan.no_kp_baru</td>
		<td class="$row">$infoDefendan.keterangan</td>
	</tr>
	#end
</table>
</fieldset>
<p></p>
<table width="100%">
	<tr>
		<td>
		<div align="center"><input type="button" name="Kemaskini"
			id="Kemaskini" value="Kemaskini"
			onclick="javascript:Skrin_EditKolateral('$idpermohonan','$id_perbicaraan');" />
		<input name="cmdBatal" type="button" value="Kembali"
			onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
		<input type="button" name="button" id="button" value="Cetak"
			onClick="javascript:setTable('tableReport1')" /></div>
		</td>
	</tr>
</table>



</div>
<!-- close for 1st div class="TabbedPanelsContent" -->

#foreach($infoKeputusan in $dataKeputusan) 
	#set ($idBayaran = $infoKeputusan.id_bayaran) 
	#set ($tarikhBayaranKptsan = $infoKeputusan.tarikh_bayaran) 
	#set ($jumlahBayaranKptsan = $infoKeputusan.jumlah_bayaran) 
	#set ($noResitKptsan = $infoKeputusan.no_resit) 

#end 
#foreach($infoKeputusanBayaran in $dataKeputusanBayaran) 
	#set ($catatanKeputusan = $infoKeputusanBayaran.catatan_keputusan) 
#end 
#if ( $tab == "keputusan")
<div class="TabbedPanelsContent">
<fieldset><legend>KEPUTUSAN</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td width="31%">Bayaran Perintah</td>
		<td width="1%">:</td>
		<td colspan="2">RM &nbsp;<input type="text" size="11"
			name="txtBayaranPerintahKol" id="txtBayaranPerintahKol" value="30.00"
			maxlength="12" readonly class="disabled"
			onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtBayaranPerintahKol)"
			onkeyup="validateNumber(this,this.value);" /></td>
	</tr>
	<tr>
		<td>No. Resit</td>
		<td>:</td>
		<td colspan="2"><span style="text-transform: uppercase;">
		<input type="text" size="15" name="txtNomborResitPerintahKol"
			onblur="this.value=this.value.toUpperCase();"
			id="txtNomborResitPerintahKol" value="$!noResitKptsan" readonly
			class="disabled" style="text-transform: uppercase;" maxlength="20" />
		</span></td>
	</tr>
	<tr>
		<td width="31%" valign="top">Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="68%"><textarea name="txtCatatanKeputusan" cols="80%"
			rows="8" id="txtCatatanKeputusan">$!catatanKeputusan</textarea></td>
	</tr>

	<!-- VIEW MODE -->
	<script> 
              	var area = new FCKeditor("txtCatatanKeputusan");
	      		area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();   
				
		//syntact utk disabledkan fckEditor
		var oEditor = FCKeditorAPI.GetInstance('txtCatatanKeputusan');				
		function FCKeditor_OnComplete( oEditor ){				
			oEditor.EditorDocument.body.contentEditable='false';
			oEditor.EditorDocument.designMode='off';
			oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;'  
		}				
              </script>

</table>
<p></p>
<table width="100%">
	<tr>
		<td>
		<div align="center"><input type="button" name="Kemaskini"
			id="Kemaskini" value="Kemaskini"
			onclick="javascript:Skrin_EditKeputusanKolateral('$idpermohonan','$id_perbicaraan');" />
		<input name="cmdKembali" type="button" value="Kembali"
			onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
		<input type="button" name="button" id="button" value="Cetak"
			onClick="javascript:setTable('tableReport1')" /> #if ( $idstatus ==
		"173" ) <input type="button" name="cmdNotis" value="Seterusnya"
			onClick="goNotis('$id_permohonan','$id_status')" /> #end</div>
		</td>
	</tr>
</table>

</fieldset>
</div>
<!-- close for 2nd div class="TabbedPanelsContent" --> #end <!-- close for tab=keputusan -->

</div>
<!-- close for master tab --> #end <!-- close for view mode --> 
#if ($viewEditMode == "yes" ) 
	#if ( $flag == "onChange" ) 
		#foreach($info in $dataPerintahKolateral) 
			#set ($id_unitpsk = $info.id_unitpsk) 
			#set ($tarikh_perintah = $info.tarikh_perintah) 
			#set ($flag_jenis_keputusan = $info.flag_jenis_keputusan) 
			#set ($catatan = $info.catatan) 
			#set ($flag_tangguh = $info.flag_tangguh) 
			#set ($flag_batal = $info.flag_batal) 
			#set ($sebab_batal = $info.sebab_batal) 
			#set ($sebab_tangguh = $info.sebab_tangguh) 
			#set ($keputusan_mahkamah = $info.keputusan_mahkamah) 
			#set ($id_pejabatmahkamah = $info.id_pejabatmahkamah) 
			#set ($tarikh_bicara = $info.tarikh_bicara)
			#set ($masa_bicara = $info.masa_bicara) 
			#set ($sebab_pertelingkahan = $info.sebab_pertelingkahan) 
		
		#end 
	
	#end


<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(0);permohonan()">PERMOHONAN</li>
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(1);keputusan()">KEPUTUSAN</li>
</ul>

<div class="TabbedPanelsContentGroup">


<div class="TabbedPanelsContent">

<fieldset><legend>PERTELINGKAHAN KOLATERAL</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td colspan="3"><input type="button" name="cmdTangguhKolateral"
			id="cmdTangguhKolateral" value="Senarai Tangguh Kolateral"
			onclick="SenaraiBicaraKolateral('$idpermohonan')" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Tarikh Perakuan Kolateral</td>
		<td>:</td>
		<td><input name="txdTarikhPerakuanAdd" value="$!tarikh_bicara"
			size="11" id="txdTarikhPerakuan" type="text" readonly
			class="disabled" /> <img src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhPerakuanAdd',false,'dmy');" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
		<td>:</td>
		<td>$selectViewPegawai</td>
	</tr>
	<tr>
		<td width="24%"><font color="red">*</font>&nbsp;Tarikh Bicara</td>
		<td width="1%">:</td>
		<td width="75%"><input type="text" name="txdTarikhBicara"
			id="txdTarikhBicara" value="$!tarikh_perintah" size="11" readonly
			class="disabled" /> <img src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhBicara',false,'dmy');" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Masa Bicara</td>
		<td>:</td>
		<td><input type="text" name="txtMasaBicara" value="$!masa_bicara"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="4" size="4" />
		&nbsp;<em>&nbsp;<font color="blue">format 24 jam (HHMM)</font></em></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Tempat Bicara</td>
		<td>:</td>
		<td>$selectViewBicara <input name="id_pejabatjkptg"
			id="id_pejabatjkptg" type="hidden" value="$id_pejabatmahkamah" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Alamat</td>
		<td>:</td>
		<td><input type="text" size="52" name="txtAlamatBicara1"
			id="txtAlamatBicara1" value="$!alamat1" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara2"
			id="txtAlamatBicara2" value="$!alamat2" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara3"
			id="txtAlamatBicara3" value="$!alamat3" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Poskod</td>
		<td>:</td>
		<td><input type="text" name="txtPoskod" id="txtPoskod"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="5" size="5"
			value="$!poskod" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Negeri</td>
		<td>:</td>
		<td>$selectNegeri</td>
	</tr>
	<tr>
		<td width="24%" valign="top">&nbsp;&nbsp;&nbsp;Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="75%" valign="top"><textarea name="txtCatatanAdd"
			cols="80%" rows="8" id="txtCatatanAdd">$!sebab_pertelingkahan</textarea>
		</td>
	</tr>
	<script> 
            var area = new FCKeditor("txtCatatanAdd");
            area.BasePath = '/${appname}/library/fck/' ;
            area.ReplaceTextarea(); 
                        
            //syntact utk disabledkan fckEditor
            var oEditor = FCKeditorAPI.GetInstance('txtCatatanAdd');	
                    
            function FCKeditor_OnComplete( oEditor ){
                oEditor.EditorDocument.body.contentEditable='false';
                oEditor.EditorDocument.designMode='off';
                oEditor.EditorDocument.body.style.cssText += 'color: #322805; background-color: #F2F2EE;' 
            }				
          </script>

</table>
</fieldset>

#foreach($infoUpdate in $dataWarisUpdate) 
	#set ($id_ob = $infoUpdate.id_ob) 
	#set ($nama_ob = $infoUpdate.nama_ob) 
	#set ($no_kp_baru = $infoUpdate.no_kp_baru) 
	#set ($id_saudara = $infoUpdate.id_saudara) 
	#set ($keterangan = $infoUpdate.keterangan) 
	#set ($jenis_ob = $infoUpdate.jenis_ob) 
	
#end

<p></p>
<fieldset><legend><strong>PIHAK YANG
MENENTANG (PLANTIF)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="3%" style="text-transform: uppercase">Pilih</td>
		<td width="8%" style="text-transform: uppercase">No.</td>
		<td width="31%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $infoUpdate in $dataWarisUpdate ) 
		#if ($infoUpdate.bil == '') 
			#set ($row = 'row1') 
		#elseif ($infoUpdate.bil % 2 != 0) 
			#set ($row	= 'row1') 
		#else 
			#set ($row = 'row2') 
		#end

	<tr>
		<td class="$row"><input type="checkbox" name="cbsemaksP"
			value="$infoUpdate.id_ob" onClick="checkit1()"> <input
			type="hidden" value="$infoUpdate.id_ob" size="5" /></td>
		<td class="$row">$infoUpdate.bil</td>
		<td class="$row">$infoUpdate.nama_ob.toUpperCase()</td>
		<td class="$row">$infoUpdate.no_kp_baru</td>
		<td class="$row">$infoUpdate.keterangan</td>
	</tr>
	#end
</table>
</fieldset>

#foreach($infoUpdate in $dataWarisUpdate) 
	#set ($id_ob = $infoUpdate.id_ob) 
	#set ($nama_ob = $infoUpdate.nama_ob) 
	#set ($no_kp_baru = $infoUpdate.no_kp_baru) 
	#set ($id_saudara = $infoUpdate.id_saudara) 
	#set ($keterangan = $infoUpdate.keterangan) 
	#set ($jenis_ob = $infoUpdate.jenis_ob)
	
#end

<p></p>
<fieldset><legend><strong>PIHAK YANG
DITENTANG (DEFENDEN)</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="3%" style="text-transform: uppercase">Pilih</td>
		<td width="8%" style="text-transform: uppercase">No.</td>
		<td width="31%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>
	#foreach ( $infoUpdate in $dataWarisUpdate ) 
		#if ($infoUpdate.bil == '') 
			#set ($row = 'row1') 
		#elseif ($infoUpdate.bil % 2 != 0) 
			#set ($row = 'row1') 
		#else 
			#set ($row = 'row2') 
		#end

	<tr>
		<td class="$row"><input type="checkbox" name="cbsemaksD"
			value="$infoUpdate.id_ob" onClick="checkit1()"></td>
		<td class="$row">$infoUpdate.bil</td>
		<td class="$row">$infoUpdate.nama_ob.toUpperCase()</td>
		<td class="$row">$infoUpdate.no_kp_baru</td>
		<td class="$row">$infoUpdate.keterangan</td>
	</tr>
	#end
</table>
</fieldset>
<p></p>

<table width="100%">
	<tr>
		<td>
		<div align="center"><input type="button" name="Simpan"
			id="Simpan" value="Simpan"
			onclick="javascript:Skrin_EditKolateral('$idpermohonan','$id_perbicaraan');" />
		<input name="cmdBatal" type="button" value="Kembali"
			onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
		<input type="button" name="button" id="button" value="Cetak"
			onClick="javascript:setTable('tableReport1')" /></div>
		</td>
	</tr>
</table>
</div>
<!-- close for 1st tab --> 
#foreach($infoKeputusan in $dataKeputusan)
	#set ($idBayaran = $infoKeputusan.id_bayaran) 
	#set ($tarikhBayaranKptsan = $infoKeputusan.tarikh_bayaran) 
	#set ($jumlahBayaranKptsan = $infoKeputusan.jumlah_bayaran) 
	#set ($noResitKptsan = $infoKeputusan.no_resit) 

#end 

#foreach($infoKeputusanBayaran in $dataKeputusanBayaran) 
	#set ($catatanKeputusan = $infoKeputusanBayaran.catatan_keputusan) 
#end


<div class="TabbedPanelsContent">
<fieldset><legend>KEPUTUSAN</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td>Keputusan</td>
		<td>:</td>
		<td><input name="txdTarikhHantarAdd"
			value="$!tarikh_hantar_borang" size="11" id="txdTarikhHantarAdd"
			type="text" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td>Bayaran Perintah</td>
		<td>:</td>
		<td><input name="txdTarikhTerimaAdd"
			value="$!tarikh_terima_borang" size="11" id="txdTarikhTerimaAdd"
			type="text" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td width="31%" valign="top">Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="68%"><textarea name="txtCatatanAdd" cols="73%"
			rows="7" id="txtCatatanAdd"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled">$catatan5</textarea></td>
	</tr>
</table>
</fieldset>



</fieldset>

</div>
<!-- close for 2nd tab --></div>
<!-- close master tab --></div>
#end <!-- close vieweditmode --> #if ( $editMode == "yes" )

<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(0);permohonan()">PERMOHONAN</li>
	<li class="TabbedPanelsTab" tabindex="0"
		onclick="setSelected(1);keputusan()">KEPUTUSAN</li>
</ul>

<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">

<fieldset><legend>PERTELINGKAHAN KOLATERAL</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td><font color="red">*</font>&nbsp;Tarikh Perakuan Kolateral</td>
		<td>:</td>
		<td><input name="txdTarikhPerakuanAdd" value="$!tarikh_perakuan"
			size="11" id="txdTarikhPerakuanAdd" type="text" readonly
			class="disabled" /> <img src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhPerakuanAdd',false,'dmy');" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Pegawai Pengendali</td>
		<td>:</td>
		<td>$selectViewPegawai</td>
	</tr>
	<tr>
		<td width="25%"><font color="red">*</font>&nbsp;Tarikh Bicara</td>
		<td width="1%">:</td>
		<td width="74%"><input type="text" name="txdTarikhBicara"
			id="txdTarikhBicara" value="$!tarikh_perintah" size="11" readonly
			class="disabled" /> <img src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhBicara',false,'dmy');" /></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Masa Bicara</td>
		<td>:</td>
		<td><input type="text" name="txtMasaBicara" value="$!masa_bicara"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="4" size="4"
			readonly class="disabled" /> <em>&nbsp;<font color="blue">format
		24 jam (HHMM)</font></em></td>
	</tr>
	<tr>
		<td><font color="red">*</font>&nbsp;Tempat Bicara</td>
		<td>:</td>
		<td>$selectBicara <input name="id_pejabatjkptg"
			id="id_pejabatjkptg" type="hidden" value="$id_pejabatmahkamah" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Alamat</td>
		<td>:</td>
		<td><input type="text" size="52" name="txtAlamatBicara1"
			id="txtAlamatBicara1" value="$!alamat1" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara2"
			id="txtAlamatBicara2" value="$!alamat2" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><input type="text" size="52" name="txtAlamatBicara3"
			id="txtAlamatBicara3" value="$!alamat3" maxlength="80"
			style="text-transform: uppercase;"
			onblur="this.value=this.value.toUpperCase();" readonly
			class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Poskod</td>
		<td>:</td>
		<td><input type="text" name="txtPoskod" id="txtPoskod"
			onblur="validateNumber(this,this.value);"
			onkeyup="validateNumber(this,this.value);" maxlength="5" size="5"
			value="$!poskod" readonly class="disabled" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;Negeri</td>
		<td>:</td>
		<td>$selectNegeri</td>
	</tr>
	<tr>
		<td width="25%" valign="top">&nbsp;&nbsp;&nbsp;Catatan</td>
		<td width="1%" valign="top">:</td>
		<td width="74%" valign="top"><textarea name="txtCatatanAdd"
			cols="80%" rows="8" id="txtCatatanAdd">$!sebab_pertelingkahan</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="3" valign="top">
		<div class="disabled" id="word_count"></div>
		</td>
	</tr>

	<script> 
		var area = new FCKeditor("txtCatatanAdd");
		area.BasePath = '/${appname}/library/fck/' ;
		area.ReplaceTextarea();             	
      </script>

	<tr></tr>
</table>
</fieldset>

<p></p>
<fieldset><legend><strong>PIHAK YANG
MENENTANG (PLANTIF)</strong></legend>
<p></p>
<legend><strong>SENARAI WARIS</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="8%" style="text-transform: uppercase">No.</td>
		<td width="31%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>

	#foreach ( $senarai in $OBList ) 
		#if ($senarai.bil == '') 
			#set ($row = 'row1') 
		#elseif ($senarai.bil % 2 != 0) 
			#set ($row = 'row1') 
		#else 
			#set ($row = 'row2') 
		#end

	<tr>

		#if ( $senarai.jenis_ob == 'PL' ) 
			#set ( $checked = "checked" ) 
		#else
			#set ( $checked = "" ) 
		#end


		<td class="$row"><input type="checkbox" name="cbsemaksP" $checked
			value="$senarai.id_ob" onClick="checkit1edit()"></td>
		<!--<td class="$row">$senarai.bil</td>-->
		<td class="$row">$senarai.nama_ob.toUpperCase()</td>
		<td class="$row">$senarai.no_kp_baru</td>
		<td class="$row">$senarai.keterangan</td>
	</tr>
	#end
</table>
</fieldset>

<p></p>
<fieldset><legend><strong>PIHAK YANG
DITENTANG (DEFENDEN)</strong></legend>
<p></p>
<legend><strong>SENARAI WARIS</strong></legend>
<p></p>
<table width="100%" border="0" cellspacing="2">
	<tr class="table_header">
		<td width="8%" style="text-transform: uppercase">No.</td>
		<td width="31%" style="text-transform: uppercase">Nama</td>
		<td width="31%" style="text-transform: uppercase">No. KP Baru</td>
		<td width="30%" style="text-transform: uppercase">Talian dengan
		SiMati</td>
	</tr>

	#foreach ( $senarai in $OBList ) 
		#if ($senarai.bil == '') 
			#set ($row ='row1') 
		#elseif ($senarai.bil % 2 != 0) 
			#set ($row = 'row1') 
		#else 
			#set($row = 'row2') 
	#end

	<tr>

		#if ( $senarai.jenis_ob == 'DF' ) 
			#set ( $checked = "checked" ) 
		#else
			#set ( $checked = "" ) 
		#end

		<td class="$row"><input type="checkbox" name="cbsemaksD" $checked
			value="$senarai.id_ob" onClick="checkit2edit()"></td>
		<!--<td class="$row">$senarai.bil</td>-->
		<td class="$row">$senarai.nama_ob.toUpperCase()</td>
		<td class="$row">$senarai.no_kp_baru</td>
		<td class="$row">$senarai.keterangan</td>
	</tr>
	#end
</table>
</fieldset>
<p></p>
<div align="center"><input type="button" name="cmdSimpan"
	id="Simpan" value="Simpan"
	onclick="javascript:simpan_editkolateral('$idpermohonan','$id_perbicaraan');" />
<input name="cmdKembali" type="button" value="Kembali"
	onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
</div>

</div>
##end 
#foreach($infoKeputusan in $dataKeputusan) 
	#set ($idBayaran = $infoKeputusan.id_bayaran) 
	#set ($tarikhBayaranKptsan = $infoKeputusan.tarikh_bayaran) 
	#set ($jumlahBayaranKptsan = $infoKeputusan.jumlah_bayaran) 
	#set ($noResitKptsan = $infoKeputusan.no_resit) 
#end 

#foreach($infoKeputusanBayaran in $dataKeputusanBayaran) 
	#set ($catatanKeputusan = $infoKeputusanBayaran.catatan_keputusan) 
#end

<div class="TabbedPanelsContent">
<fieldset><legend>KEPUTUSAN</legend>
<table width="100%" cellspacing="1" cellpadding="1" border="0">
	<tr>
		<td>Bayaran Perintah (RM)</td>
		<td>:</td>
		<td colspan="2">RM&nbsp;<input type="text" size="6"
			name="txtBayaranPerintahKol" id="txtBayaranPerintahKol" value="30.00"
			maxlength="12" readonly class="disabled"
			onblur="validateNumber(this,this.value);validateModal(this,this.value,$txtBayaranPerintahKol)"
			onkeyup="validateNumber(this,this.value);" /></td>
	</tr>
	<tr>
		<td>No. Resit</td>
		<td>:</td>
		<td colspan="2"><span style="text-transform: uppercase;">
		<input type="text" size="15" name="txtNomborResitPerintahKol"
			onblur="this.value=this.value.toUpperCase();"
			id="txtNomborResitPerintahKol" value="$!noResitKptsan"
			style="text-transform: uppercase;" maxlength="20" /> </span></td>
	</tr>
	<tr>
		<td>Tarikh Bayaran</td>
		<td>:</td>
		<td colspan="2"><input name="txdTarikhBayaranPerintahKol"
			value="$!tarikhBayaranKptsan" size="11"
			id="txdTarikhBayaranPerintahKol" type="text"
			onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
		<img src="../img/calendar.gif" alt=""
			onclick="displayDatePicker('txdTarikhBayaranPerintahKol',false,'dmy');" /></td>
	</tr>
	<tr>
		<td width="22%" valign="top">Keputusan</td>
		<td width="1%" valign="top">:</td>
		<td colspan="2"><textarea name="txtCatatanKeputusan" cols="80%"
			rows="8" id="txtCatatanKeputusan">$!catatanKeputusan</textarea></td>
	</tr>
	<tr>
		<td colspan="3" valign="top">
		<div class="disabled" id="word_count"></div>
		</td>
	</tr>

	<!-- EDIT MODE -->
	<script> 
				var area = new FCKeditor("txtCatatanKeputusan");
				area.BasePath = '/${appname}/library/fck/' ;
				area.ReplaceTextarea();             	
              </script>

</table>
<p></p>
<table width="100%">
	<tr>
		<td>
		<div align="center">#if($idstatus == "172") <input type="button"
			name="Simpan" id="Simpan" value="Simpan"
			onclick="javascript:simpanKeputusanKolateral('$idpermohonan','$id_perbicaraan');" />
		#end #if($idstatus == "173") <input type="button" name="Simpan"
			id="Simpan" value="Simpan"
			onclick="javascript:simpanEditKeputusanKolateral('$idpermohonan','$id_perbicaraan');" />
		#end <input name="cmdKembali" type="button" value="Kembali"
			onclick="javascript: kembali_skrin2TangguhKolEdit('$idpermohonan','$id_perbicaraan');" />
		</div>
		</td>
	</tr>
</table>
</fieldset>
</div>


</div>
</div>
#end <br />
<fieldset id="tableReport1" style="display: none;"><legend><strong>Senarai
Laporan</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td><a href="#" class="style2"
			onClick="javascript:cetakBorangL('$nofail',$idFail,'$id_perbicaraan')"><font
			color="blue">Borang L</font></a></td>
	</tr>
	<tr>
		<td><a href="#" class="style2"
			onClick="javascript:cetakBorangM('$nofail',$idFail,'$id_perbicaraan')"><font
			color="blue">Borang M</font></a></td>
	</tr>
	<tr>
		<td><a href="#" class="style2"
			onClick="javascript:cetakBorangN('$nofail',$idFail,'$id_perbicaraan')"><font
			color="blue">Borang N</font></a></td>
	</tr>
</table>
</fieldset>
#parse("app/ppk/headerppk_script.jsp") <script>

function goNotis(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function simpanEditKeputusanKolateral(idpermohonan,id_perbicaraan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;	
	document.${formName}.method = "POST";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";	
	document.${formName}.command.value = "simpanEditKeputusanKolateral";
	document.${formName}.submit();
}

function simpanKeputusanKolateral(idpermohonan,id_perbicaraan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;	
	document.${formName}.method = "POST";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";	
	document.${formName}.command.value = "simpanKeputusanKolateral";
	document.${formName}.submit();
}

function Skrin_EditKeputusanKolateral(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";	
	document.${formName}.command.value = "Skrin_EditKeputusanKolateral";
	document.${formName}.submit();
}

function permohonan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";	
	document.${formName}.command.value = "permohonanKolateral";
	document.${formName}.submit();
}
function keputusan() {
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.command.value = "keputusanKolateral";
	document.${formName}.submit();
}

function SenaraiBicaraKolateral(idpermohonan,command) {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8SenaraiTangguhKolateral?idpermohonan="+idpermohonan+"&command="+command;
	var hWnd = window.open(url,'displayfile','width=1000,height=600, resizable=yes,scrollbars=yes');	
}


function setTable(nofail){
	if(document.getElementById(nofail).style.display=="none"){
		document.getElementById(nofail).style.display="block";
	}
	else if(document.getElementById(nofail).style.display=="block"){
		document.getElementById(nofail).style.display="none";
	}
}

function cetakBorangL(nofail,idFail,id_perbicaraan) {
    var url = "../servlet/ekptg.report.ppk.BorangL?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

function cetakBorangM(nofail,idFail,id_perbicaraan) {
    var url = "../servlet/ekptg.report.ppk.BorangM?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();		
}

function cetakBorangN(nofail,idFail,id_perbicaraan) {
    var url = "../servlet/ekptg.report.ppk.BorangN?noFail="+nofail+"&idfail="+idFail+"&idperbicaraan="+id_perbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function checkit1(){		
	for (var i = 0;  i < document.${formName}.cbsemaksD.length;  i++){
		document.${formName}.cbsemaksD[i].disabled=false;
		if ( document.${formName}.cbsemaksP[i].checked == true ){
			document.${formName}.cbsemaksD[i].disabled = true;
		}
	}		
	
}	

function checkit2(){
	for (i = 0;  i < document.${formName}.cbsemaksP.length;  i++){			
		document.${formName}.cbsemaksP[i].disabled=false;
		if ( document.${formName}.cbsemaksD[i].checked == true ){
			 document.${formName}.cbsemaksP[i].disabled = true;
		}
	}	
}

function checkit1edit(){
	for (i = 0;  i < document.${formName}.cbsemaksD.length;  i++){
		document.${formName}.cbsemaksD[i].disabled=false;
		if ( document.${formName}.cbsemaksP[i].checked == true ){
			document.${formName}.cbsemaksD[i].disabled = true;
		}	
	}
}

function checkit2edit(){
	for (i = 0;  i < document.${formName}.cbsemaksP.length;  i++){
		document.${formName}.cbsemaksP[i].disabled=false;
		if ( document.${formName}.cbsemaksD[i].checked == true ){
			document.${formName}.cbsemaksP[i].disabled = true;
		}	
	}
}	

/*
function checkit1(){
	document.${formName}.cbsemaksD[0].disabled=false;
	if (document.${formName}.cbsemaksP[0].checked == true){
		document.${formName}.cbsemaksD[0].disabled = true;		 
	}
	document.${formName}.cbsemaksD[1].disabled=false;
	if (document.${formName}.cbsemaksP[1].checked == true){
		document.${formName}.cbsemaksD[1].disabled = true;
	}
	document.${formName}.cbsemaksD[2].disabled=false;
	if (document.${formName}.cbsemaksP[2].checked == true){
		document.${formName}.cbsemaksD[2].disabled = true;
	}
	document.${formName}.cbsemaksD[3].disabled=false;
	if (document.${formName}.cbsemaksP[3].checked == true){
		document.${formName}.cbsemaksD[3].disabled = true;
	}	
	document.${formName}.cbsemaksD[4].disabled=false;
	if (document.${formName}.cbsemaksP[4].checked == true){
		document.${formName}.cbsemaksD[4].disabled = true;
	}
	document.${formName}.cbsemaksD[5].disabled=false;
	if (document.${formName}.cbsemaksP[5].checked == true){
		document.${formName}.cbsemaksD[5].disabled = true;
	}	
	document.${formName}.cbsemaksD[6].disabled=false;
	if (document.${formName}.cbsemaksP[6].checked == true){
		document.${formName}.cbsemaksD[6].disabled = true;
	}
	document.${formName}.cbsemaksD[7].disabled=false;
	if (document.${formName}.cbsemaksP[7].checked == true){
		document.${formName}.cbsemaksD[7].disabled = true;
	}	
	document.${formName}.cbsemaksD[8].disabled=false;
	if (document.${formName}.cbsemaksP[8].checked == true){
		document.${formName}.cbsemaksD[8].disabled = true;
	}
	document.${formName}.cbsemaksD[9].disabled=false;
	if (document.${formName}.cbsemaksP[9].checked == true){
		document.${formName}.cbsemaksD[9].disabled = true;
	}
	document.${formName}.cbsemaksD[10].disabled=false;
	if (document.${formName}.cbsemaksP[10].checked == true){
		document.${formName}.cbsemaksD[10].disabled = true;
	}						
}


function checkit2(){
	document.${formName}.cbsemaksP[0].disabled=false;
	if (document.${formName}.cbsemaksD[0].checked == true){
		document.${formName}.cbsemaksP[0].disabled = true;		 
	}
	document.${formName}.cbsemaksP[1].disabled=false;
	if (document.${formName}.cbsemaksD[1].checked == true){
		document.${formName}.cbsemaksP[1].disabled = true;
	}
	document.${formName}.cbsemaksP[2].disabled=false;
	if (document.${formName}.cbsemaksD[2].checked == true){
		document.${formName}.cbsemaksP[2].disabled = true;
	}
	document.${formName}.cbsemaksP[3].disabled=false;
	if (document.${formName}.cbsemaksD[3].checked == true){
		document.${formName}.cbsemaksP[3].disabled = true;
	}	
	document.${formName}.cbsemaksP[4].disabled=false;
	if (document.${formName}.cbsemaksD[4].checked == true){
		document.${formName}.cbsemaksP[4].disabled = true;
	}
	document.${formName}.cbsemaksP[5].disabled=false;
	if (document.${formName}.cbsemaksD[5].checked == true){
		document.${formName}.cbsemaksP[5].disabled = true;
	}	
	document.${formName}.cbsemaksP[6].disabled=false;
	if (document.${formName}.cbsemaksD[6].checked == true){
		document.${formName}.cbsemaksP[6].disabled = true;
	}
	document.${formName}.cbsemaksP[7].disabled=false;
	if (document.${formName}.cbsemaksD[7].checked == true){
		document.${formName}.cbsemaksP[7].disabled = true;
	}	
	document.${formName}.cbsemaksP[8].disabled=false;
	if (document.${formName}.cbsemaksD[8].checked == true){
		document.${formName}.cbsemaksP[8].disabled = true;
	}
	document.${formName}.cbsemaksP[9].disabled=false;
	if (document.${formName}.cbsemaksD[9].checked == true){
		document.${formName}.cbsemaksP[9].disabled = true;
	}
	document.${formName}.cbsemaksP[10].disabled=false;
	if (document.${formName}.cbsemaksD[10].checked == true){
		document.${formName}.cbsemaksP[10].disabled = true;
	}						
}

function checkit1edit(){
	document.${formName}.cbsemaksD[0].disabled=false;
	if (document.${formName}.cbsemaksP[0].checked == true ){
		document.${formName}.cbsemaksD[0].disabled = true;		 
	}
	document.${formName}.cbsemaksD[1].disabled=false;
	if (document.${formName}.cbsemaksP[1].checked == true){
		document.${formName}.cbsemaksD[1].disabled = true;
	}
	document.${formName}.cbsemaksD[2].disabled=false;
	if (document.${formName}.cbsemaksP[2].checked == true){
		document.${formName}.cbsemaksD[2].disabled = true;
	}
	document.${formName}.cbsemaksD[3].disabled=false;
	if (document.${formName}.cbsemaksP[3].checked == true){
		document.${formName}.cbsemaksD[3].disabled = true;
	}	
	document.${formName}.cbsemaksD[4].disabled=false;
	if (document.${formName}.cbsemaksP[4].checked == true){
		document.${formName}.cbsemaksD[4].disabled = true;
	}
	document.${formName}.cbsemaksD[5].disabled=false;
	if (document.${formName}.cbsemaksP[5].checked == true){
		document.${formName}.cbsemaksD[5].disabled = true;
	}	
	document.${formName}.cbsemaksD[6].disabled=false;
	if (document.${formName}.cbsemaksP[6].checked == true){
		document.${formName}.cbsemaksD[6].disabled = true;
	}
	document.${formName}.cbsemaksD[7].disabled=false;
	if (document.${formName}.cbsemaksP[7].checked == true){
		document.${formName}.cbsemaksD[7].disabled = true;
	}	
	document.${formName}.cbsemaksD[8].disabled=false;
	if (document.${formName}.cbsemaksP[8].checked == false){
		document.${formName}.cbsemaksD[8].disabled = true;
	}
	document.${formName}.cbsemaksD[9].disabled=false;
	if (document.${formName}.cbsemaksP[9].checked == true){
		document.${formName}.cbsemaksD[9].disabled = true;
	}
	document.${formName}.cbsemaksD[10].disabled=false;
	if (document.${formName}.cbsemaksP[10].checked == true){
		document.${formName}.cbsemaksD[10].disabled = true;
	}						
}

function checkit2edit(){
	document.${formName}.cbsemaksP[0].disabled=false;
	if (document.${formName}.cbsemaksD[0].checked == true){
		document.${formName}.cbsemaksP[0].disabled = true;		 
	}
	document.${formName}.cbsemaksP[1].disabled=false;
	if (document.${formName}.cbsemaksD[1].checked == true){
		document.${formName}.cbsemaksP[1].disabled = true;
	}
	document.${formName}.cbsemaksP[2].disabled=false;
	if (document.${formName}.cbsemaksD[2].checked == true){
		document.${formName}.cbsemaksP[2].disabled = true;
	}
	document.${formName}.cbsemaksP[3].disabled=false;
	if (document.${formName}.cbsemaksD[3].checked == true){
		document.${formName}.cbsemaksP[3].disabled = true;
	}	
	document.${formName}.cbsemaksP[4].disabled=false;
	if (document.${formName}.cbsemaksD[4].checked == true){
		document.${formName}.cbsemaksP[4].disabled = true;
	}
	document.${formName}.cbsemaksP[5].disabled=false;
	if (document.${formName}.cbsemaksD[5].checked == true){
		document.${formName}.cbsemaksP[5].disabled = true;
	}	
	document.${formName}.cbsemaksP[6].disabled=false;
	if (document.${formName}.cbsemaksD[6].checked == true){
		document.${formName}.cbsemaksP[6].disabled = true;
	}
	document.${formName}.cbsemaksP[7].disabled=false;
	if (document.${formName}.cbsemaksD[7].checked == true){
		document.${formName}.cbsemaksP[7].disabled = true;
	}	
	document.${formName}.cbsemaksP[8].disabled=false;
	if (document.${formName}.cbsemaksD[8].checked == true){
		document.${formName}.cbsemaksP[8].disabled = true;
	}
	document.${formName}.cbsemaksP[9].disabled=false;
	if (document.${formName}.cbsemaksD[9].checked == true){
		document.${formName}.cbsemaksP[9].disabled = true;
	}
	document.${formName}.cbsemaksP[10].disabled=false;
	if (document.${formName}.cbsemaksD[10].checked == true){
		document.${formName}.cbsemaksP[10].disabled = true;
	}						
}
*/
*
function doChangeidTempatBicara() {
	document.${formName}.command.value = "doChangeidTempatBicara";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();	
}

function doChangeidTempatBicaraUpdate() {
	document.${formName}.command.value = "doChangeidTempatBicaraUpdate";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17"; 
	document.${formName}.submit();	
}
function kembali_skrin2Tangguh(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2Tangguh";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function kembali_skrin2TangguhKolAdd(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2TangguhKolAdd";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function kembali_skrin2TangguhKolEdit(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "kembali_skrin2TangguhKolEdit";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}
function Skrin_EditKolateral(idpermohonan,id_perbicaraan) {
	document.${formName}.idpermohonan.value = idpermohonan;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.command.value = "Skrin_EditKolateral";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	document.${formName}.submit();
}


function simpan_editkolateral(idpermohonan,id_perbicaraan)	{

	if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih \"Tempat Bicara\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}	
	if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Pengendali\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	var radioSelected = false;
	for (i = 0;  i < ${formName}.cbsemaksP.length;  i++){
	if (${formName}.cbsemaksP[i].checked)
	radioSelected = true;
	}
	if (!radioSelected){
	alert("Sila pilih \"Sila Pilih Pihak Yang Menentang (Plantif)\" terlebih dahulu.");
	return (false); 
	}

	var radioSelected2 = false;
	for (j = 0;  j < ${formName}.cbsemaksD.length;  j++){
	if (${formName}.cbsemaksD[j].checked)
	radioSelected2 = true;
	}
	if (!radioSelected2){
	alert("Sila pilih \"Sila Pilih Pihak Yang Ditentang (Defendan)\" terlebih dahulu.");
	return (false);
	}		
 	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.idpermohonan.value = idpermohonan;
		document.${formName}.id_perbicaraan.value = id_perbicaraan;
		document.${formName}.command.value = "simpan_editkolateral";
		document.${formName}.method = "POST";
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
		document.${formName}.submit();
	}	
}

function Simpan_AddKolateral()	{

	var txtCatatanAdd = null; 
	var oEditor = FCKeditorAPI.GetInstance('message') ;	
	if(oEditor) { 	
		txtCatatanAdd = oEditor.GetXHTML(true); 		
	}

	var currentTime = new Date()
	var str1  = document.getElementById("txdTarikhPerakuanAdd").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);
	
	var str2  = document.getElementById("txdTarikhBicara").value;   
    var dt2   = parseInt(str2.substring(0,2),10);
    var mon2  = parseInt(str2.substring(3,5),10);
    var yr2   = parseInt(str2.substring(6,10),10);   
    var date2 = new Date(yr2, mon2, dt2);	
		
	var str4  = document.getElementById("tarikhMohon").value;
	var dt4   = parseInt(str4.substring(0,2),10);
   	var mon4  = parseInt(str4.substring(3,5),10);
   	var yr4   = parseInt(str4.substring(6,10),10);	
	var trMOHON = new Date(yr4, mon4, dt4);

	if(document.${formName}.txdTarikhPerakuanAdd.value == ""){
		alert("Sila pilih \"Tarikh Perakuan Kolateral\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerakuanAdd.focus(); 
		return;
	}
	if(date1 < trMOHON){
   		alert("Sila pastikan Tarikh Perakuan Kolateral tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhPerakuanAdd.focus();
	 	return;	
	}	
	if(document.${formName}.txdTarikhBicara.value == ""){
		alert("Sila pilih \"Tarikh Bicara\" terlebih dahulu.");
  		document.${formName}.txdTarikhBicara.focus(); 
		return;
	}	
	if(date2 < trMOHON){
   		alert("Sila pastikan Tarikh Bicara tidak kurang dari Tarikh Mohon.");
	 	document.${formName}.txdTarikhBicara.focus();
	 	return;	
	}			
	if(document.${formName}.txtMasaBicara.value == ""){
		alert("Sila pilih \"Masa Bicara\" terlebih dahulu.");
  		document.${formName}.txtMasaBicara.focus(); 
		return;
	}	
	if(document.${formName}.socTempatBicara.value == ""){
		alert("Sila pilih \"Tempat Bicara\" terlebih dahulu.");
  		document.${formName}.socTempatBicara.focus(); 
		return;
	}	
	else if(document.${formName}.txtMasaBicara.value == ""){
		alert("Sila masukkan \"Masa Bicara\" terlebih dahulu.");
  		document.${formName}.txtMasaBicara.focus(); 
		return;
	}
	else if (document.${formName}.txtMasaBicara.value != "" && document.${formName}.txtMasaBicara.value.length < 4) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus(); 
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) > 2) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		txtMasaBicara.focus();
		document.${formName}.txtMasaBicara.focus(); 
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) < 8)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 0 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 8) && (document.${formName}.txtMasaBicara.value.charAt(2) < 3)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) > 6)) {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) > 3))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1 ) && (document.${formName}.txtMasaBicara.value.charAt(1) == 6) && (document.${formName}.txtMasaBicara.value.charAt(2) == 3) && (document.${formName}.txtMasaBicara.value.charAt(3) > 0))  {
		alert("Sila masukkan \"Masa Bicara\" dari 0830 hingga 1630");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 1) && (document.${formName}.txtMasaBicara.value.charAt(1) >9 ) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}	
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(0) == 2) && (document.${formName}.txtMasaBicara.value.charAt(1) > 3) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	} 
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(2) > 5) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}
	else if (document.${formName}.txtMasaBicara.value != "" && (document.${formName}.txtMasaBicara.value.charAt(3) > 9) ) {
		alert("Sila masukkan \"Masa Bicara\" dengan format yang betul.");
		document.${formName}.txtMasaBicara.focus();
		return; 
	}	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.cbsemaksP.length;  i++){
	if (${formName}.cbsemaksP[i].checked)
	radioSelected = true;
	}
	if (!radioSelected){
	alert("Sila pilih \"Sila Pilih Pihak Yang Menentang (Plantif)\" terlebih dahulu.");
	return (false); 
	}

	var radioSelected = false;
	if (document.${formName}.cbsemaksP != null && document.${formName}.cbsemaksP != 'undefined'){
		if (${formName}.cbsemaksP.length > 1) {
			for (i = 0;  i < ${formName}.cbsemaksP.length;  i++){
		  		if (${formName}.cbsemaksP[i].checked)
		  			radioSelected = true;
		  	}
		} else {
			if (${formName}.cbsemaksP.checked)
	  			radioSelected = true;
		}
	 }  

	 if (!radioSelected){
	 	alert("Sila pilih \"Sila Pilih Pihak Yang Menentang (Plantif)\" terlebih dahulu.");
	 	return (false); 
	 }

	 var radioSelected2 = false; 
	 if (document.${formName}.cbsemaksD != null && document.${formName}.cbsemaksD != 'undefined'){
		if (${formName}.cbsemaksD.length > 1) {
			for (i = 0;  i < ${formName}.cbsemaksD.length;  i++){
		  		if (${formName}.cbsemaksD[i].checked)
		  			radioSelected2 = true;
		  	}
		} else {
			if (${formName}.cbsemaksD.checked)
				radioSelected2 = true;
		}
	}  

	 if (!radioSelected2){
	 	alert("Sila pilih \"Sila Pilih Pihak Yang Ditentang (Defendan)\" terlebih dahulu.");
	 	return (false);
	 } else {
	 	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	  	document.${formName}.method = "POST";
	  	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
	  	document.${formName}.command.value = "Simpan_AddKolateral";
	  	document.${formName}.submit();
	 }	
}

function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}

function add_item()	<!-- tak guna pon -->
{
	if(document.${formName}.socSuburusan.value == ""){
		alert("Sila pilih \"Urusan\" terlebih dahulu.");
  		document.${formName}.socSuburusan.focus(); 
		return;
	}
	if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	var radioSelected = false;
	for (i = 0;  i < ${formName}.cbsemaksP.length;  i++){
	if (${formName}.cbsemaksP[i].checked)
	radioSelected = true;
	}
	if (!radioSelected){
	alert("Sila pilih \"Pihak Yang Menentang (Plantif)\" terlebih dahulu.");
	return (false); 
	}

	var radioSelected2 = false;
	for (j = 0;  j < ${formName}.cbsemaksD.length;  j++){
	if (${formName}.cbsemaksD[j].checked)
	radioSelected2 = true;
	}
	if (!radioSelected2){
	alert("Sila pilih \"Pihak Yang Ditentang (Defendan)\" terlebih dahulu.");
	return (false);
	}
	
	if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Projek negeri\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	}
	if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
	if(document.${formName}.tarikh_permohonan.value == ""){
		alert("Sila pilih \" tarikh permohonan \" terlebih dahulu.");
  		document.${formName}.tarikh_permohonan.focus(); 
		return;
	}
	if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	}
	temp = parseInt(document.${formName}.poskod.value);

	if (temp <10000 || temp>99999) {
	alert ("Sila masukkan 5 digit poskod");
	//document.${formName}.poskod.value = "";
	document.${formName}.poskod.focus();
	return;
	}
	if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Mukim/Pekan \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
		return;
	} 
	
 	else
	{
		if ( !window.confirm("Adakah Anda Pasti?  Sila klik permohonan tadi untuk dihantar ke Seksyen Pengambilan Tanah") ) return;
		document.${formName}.command.value = "simpan";
		document.${formName}.method = "POST";
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17";
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
<!-- Begin
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
// End -->

function getTarikhBicara() {

	var TB  = document.getElementById("txdTarikhPerakuanAdd").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10)-15;
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10);

	var myDate=new Date(yr1, mon1, dt1);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhBicara = "";
	if(month>=10){
		if(day>=10){
			tarikhBicara = day + "/" + month + "/" + year;	
		}else{
			tarikhBicara = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhBicara = day + "/0" + month + "/" + year;	
		}else{
			tarikhBicara = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	
	
	if(a!="" && b!="" && c!=""){
	document.getElementById("txdTarikhBicara").value = tarikhBicara ;
	}else{
	document.getElementById("txdTarikhBicara").value = "" ;
	}
}

</script> <script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>