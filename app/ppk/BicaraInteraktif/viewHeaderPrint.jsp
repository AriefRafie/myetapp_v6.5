#if($tajukLaporan != "")
<div  style="font-size:150%;" align="center" ><b>$tajukLaporan</b></div>
<br /><br />
#end

#if($viewPerbicaraan!="")



<table width="100%" align="center" border="0" cellspacing="1" cellpadding="2"  >
<tr>
<td colspan="5" valign="top"  style="border-bottom: 1px solid #000;font-size: 100%;">
<strong>MAKLUMAT PERBICARAAN</strong>
</td>
</tr>
<tr>
<td width="1%"></td><td width="28%"></td><td width="1%"></td><td width="70%"></td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">No. Fail</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize" >$viewPerbicaraan.NO_FAIL</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Status Permohonan</td><td valign="top" style="$fontSize">:</td><td valign="top" class="HeaderFont" style="$fontSize">$viewPerbicaraan.STATUS_PERMOHONAN</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Nama Simati</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize">$viewPerbicaraan.NAMA_SIMATI</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Nama Pemohon</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize">$viewPerbicaraan.NAMA_PEMOHON</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Waktu Bicara</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize" >$viewPerbicaraan.TARIKH_BICARA $viewPerbicaraan.MASA_BICARA</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Bil. Bicara</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize">$viewPerbicaraan.BIL_BICARA</td>
</tr>
<tr>
<td width="1%"></td><td width="28%"></td><td width="1%"></td><td width="70%"></td>
</tr>
#if($viewPerbicaraan.SEKSYEN == "17")
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Tujuan Permohonan</td><td valign="top">:</td><td valign="top" style="$fontSize">

#if($viewPerbicaraan.HARTA_TERTINGGAL == "Y")
HARTA TERTINGGAL DI PERMOHONAN AWAL<br />
#end
#if($viewPerbicaraan.LANTIK_PA  == "Y")
LANTIK PEMEGANG AMANAH<br />
#end
#if($viewPerbicaraan.BATAL_PA  == "Y")
BATAL PEMEGANG AMANAH<br />
#end
#if($viewPerbicaraan.LANTIK_KT  == "Y")
LANTIK PEMEGANG SURAT KUASA TADBIR<br />
#end
#if($viewPerbicaraan.BATAL_KT  == "Y")
BATAL PEMEGANG SURAT KUASA TADBIR<br />
#end
#if($viewPerbicaraan.LAIN_TUJUAN  == "Y")
LAIN-LAIN TUJUAN : viewPerbicaraan.CATATAN_LAIN_TUJUAN<br />
#end
</td>
</tr>
#end
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Peg. Pengendali</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize">

#if($viewPerbicaraan.ID_PEGAWAIBARU != "")
$viewPerbicaraan.NAMA_PEGAWAI_BARU 
#else
$viewPerbicaraan.PEG_PENGENDALI
#end
</td>
</tr>
<tr>
<td valign="top" ></td><td valign="top" style="$fontSize">Keputusan Perbicaraan</td><td valign="top" style="$fontSize">:</td><td valign="top" style="$fontSize">
<input type="hidden" name="jenis_keputusan" id="jenis_keputusan" value="$viewPerbicaraan.FLAG_JENIS_KEPUTUSAN"  />
#set($flagDisable = "")
#if($viewPerbicaraan.FLAG_JENIS_KEPUTUSAN!="")
	#set($flagDisable = "Y")
#end
<input type="hidden" name="flagDisable" id="flagDisable" value="$flagDisable"  />
$viewPerbicaraan.KETERANGAN_KEPUTUSAN
#if($viewPerbicaraan.KETERANGAN_TANGGUH != "")
- $viewPerbicaraan.KETERANGAN_TANGGUH
#elseif($viewPerbicaraan.KETERANGAN_BATAL != "")
- $viewPerbicaraan.KETERANGAN_BATAL
#end

</td>
</tr>
</table>
#end