<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style4 {color: #0000FF}
-->
</style>
</head>

<body>
<form name="f1">
  


<table width="100%" border="0">

<tr>
<td>
 <input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
<input type="hidden" name="eventStatus" value="$EventStatus">
<input type="hidden" name="idTemp" value="$id">

<input type="hidden" name="id" value="$id">
<input type="hidden" name="id1" value="$id1">
<input type="hidden" name="id2" value="$id2">
<input type="hidden" name="id3" value="$id3">
<input type="hidden" name="idha" value="$idha">
<input type="hidden" name="bil" value="$jumList">

 
</td>

</tr>

#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="text"  value="$id"/>
     <input name="idPemohon" type="text"  value="$idPemohon"/>
      <input name="idSimati" type="text"  value="$idSimati"/>
       <input name="idtemp" type="text"  value="$id"/>
#end

  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
     <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="HAview('2','0','0','0')" >HARTA ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
        <div id="TabbedPanels2" class="TabbedPanelsContent">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PemohonView('0','1','0','0')">PEMOHON</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="WarisView('0','2','0','0')">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PentingView('0','3','0','0')">ORANG BERKEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="SaksiView('0','4','0','0')">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PemiutangView('0','5','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PenghutangView('0','6','0','0')">PENGHUTANG</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">
              
            </div>
            
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanelsContent">
              </div>
            </div>
            
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent">
        <div id="TabbedPanels4" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA TAK ALIH (ADA HAKMILIK)</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA TAK ALIH (TIADA HAKMILIK)</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent">
 

      </div>
      <div class="TabbedPanelsContent">
        <table width="100%" border="1">
  <tr>
    <td>
    
<fieldset><legend>NILAI HARTA TAK ALIH</legend>

<table width="100%">
<tr class="table_header">
<td width="30px">Bil</td>
<td width="100px">Negeri</td>
<td width="70px">Daerah</td>
<td width="50px">Mukim</td>
<td width="80px">No Hakmilik /<br> No Perjanjian</td>
<td width="60px">No PT / No Lot</td>
<td width="85px" align="right">Nilai HTA Tarikh Mohon</td>
<td width="85px" align="right">Nilai HTA Tarikh Mati</td>
</tr>
#set ($sumhta = 0)
#set ($sumhtamati = 0)
#set ($cnt = 0)
#set ($i = 0)
#foreach($listhath in $jumppkhta)
#set ($cnt = $cnt + 1)
	#set ($sumhta = $sumhta + $listhath.nilai_tarikhmohon)
	#set ($sumhtamati = $sumhtamati + $listhath.nilai_tarikhmati)
<tr bgcolor="white">
<td>$cnt</td>
<td>$listhath.nama_Negeri</td>
<td>$listhath.nama_Daerah</td>
<td>$listhath.nama_Mukim</td>
<td>$listhath.no_Perjanjian</td>
<td>$listhath.no_Pt</td>
	#if ($EventStatus == 4)
	<input type="hidden" name="idHta" value="$listhath.idhta">
	<input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
	<input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
	<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHtaNilaiTarikhMati" value="$listhath.nilai_tarikhmati" size="20" onblur="validateModal(this,this.value,$listhath.nilai_tarikhmati);"></td>
	<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHtaNilaiTarikhMohon" value="$listhath.nilai_tarikhmohon" size="20" onblur="validateModal(this,this.value,$listhath.nilai_tarikhmohon);"></td>
	#else
	<td align="right">$Util.formatDecimal($listhath.nilai_tarikhmati)</td>
	<td align="right">$Util.formatDecimal($listhath.nilai_tarikhmohon)</td>
	#end
</tr>
#end
<tr>
<td colspan="8">&nbsp;</td>
</tr>
<tr class="table_row">
<td colspan="7"><b>Jumlah Nilai Harta Tak Alih (RM)</b></td>
<td colspan="1" align="right"><b>$Util.formatDecimal($sumhta)</b></td>
</tr>
</table>
</fieldset>
<br>
<fieldset><legend>NILAI HARTA ALIH</legend>

<table width="100%" bordercolor="#333333">
<tr class="table_header">
<td width="50">Bil</td>
<td width="200">Jenis Harta Alih</td>
<td width="478">No Rujukan UPT / No Daftar / No Akaun / No Ahli</td>
<td width="144">No Sijil</td>
<td width="50" align="right">Nilai HA Tarikh Mati</td>
<td width="50" align="right">Nilai HA Tarikh Mohon</td>
</tr>
#set ($id_ha = "&nbsp;")
#set ($nama_Negeri = "&nbsp;")
#set ($nama_Daerah = "&nbsp;")
#set ($jenis = "&nbsp;")
#set ($no_Perjanjian = "&nbsp;")
#set ($sijil = "&nbsp;")
#set ($nilai_tarikhmohon = "0.00")
#set ($nilai_tarikhmati = "0.00")
#set ($sumppkha = "0.00")

#set ($cnt = 0)
#foreach($listha2 in $listHa)
#set ($id_ha = $listha2.id_Ha)
#set ($jenis = $listha2.Keterangan)
#set ($no_Perjanjian = $listha2.noDaftar)
#set ($sijil = $listha2.nosijil)
#set ($nilai_tarikhmohon = $listha2.nilai_tarikhmohon)
#set ($nilai_tarikhmati = $listha2.nilai_tarikhmati)
#set ($cnt = $cnt + 1)
<tr bgcolor="white">
<td>$cnt</td>
<td>$jenis</td>
<td>$no_Perjanjian</td>
<td>$sijil</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHa" value="$listha2.id_Ha">
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHaNilaiTarikhMati" value="$nilai_tarikhmati" size="20" onblur="validateModal(this,this.value,$nilai_tarikhmati);"></td>
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHaNilaiTarikhMohon" value="$nilai_tarikhmohon" size="20" onblur="validateModal(this,this.value,$nilai_tarikhmohon);"></td>
#else
<td width="229" align="right">$Util.formatDecimal($nilai_tarikhmati)</td>
<td width="246" align="right">$Util.formatDecimal($nilai_tarikhmohon)</td>
#end
</tr>
#end
#set ($sumjumlah = 0)
#set ($sumjumlahmati = 0)
#foreach($listha2 in $listHa)
#set ($sumjumlah = $sumjumlah + $listha2.nilai_tarikhmohon)
#set ($sumjumlahmati = $sumjumlahmati + $listha2.nilai_tarikhmati)
#end
<input type="hidden" name="txtJumlahHaTarikhMati" value="$sumjumlahmati">
<input type="hidden" name="txtJumlahHaTarikhMohon" value="$sumhta">


#set ($overalljumlahmati = 0)
#foreach($listOverallmati in $overallmati)
#set ($overalljumlahmati = $overalljumlahmati + $listOverallmati.nilaibesarmati)
#end
<input type="hidden" name="txtJumlahBesarTarikhMati" value="$overalljumlahmati">
<input type="hidden" name="txtJumlahBesarTarikhMohon" value="$overalljumlah">

<tr>
<td colspan="6">&nbsp;</td>
</tr>
<tr class="table_row">
<td colspan="5"><b>Jumlah Nilai Harta Alih (RM) </b></td>
<td align="right"><b>$Util.formatDecimal($sumjumlah)</b></td>
</tr>
</table>
</fieldset>
<br>
<fieldset><legend>NILAI HARTA KESELURUHAN</legend>


<table width="100%">
<tr class="table_header">
<td width="6%">Bil</td>
<td width="64%">Perkara</td>
<td width="30%" align="right">Jumlah Nilai Harta (RM)</td>
</tr>
<tr>
<td>1. </td>
<td>Jumlah Nilai Harta Tak Alih</td>
<td align="right">$Util.formatDecimal($sumhta)</td>
</tr>
<tr>
<td>2. </td>
<td>Jumlah Nilai Harta Alih</td>
<td align="right">$Util.formatDecimal($sumjumlah)</td>
</tr>
<tr>
<td colspan="3">&nbsp;</td>
</tr>
<tr class="table_row">
<td colspan="2"><b>Jumlah Nilai Harta Keseluruhan</b></td>
#set ($overalljumlah = 0)
#foreach($listOverall in $overall)
#set ($overalljumlah = $overalljumlah + $listOverall.nilaibesar)
#end
#if ($overalljumlah == 0)
<td colspan="1" align="right"><b>0.00</b></td>
#else
<td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlah)</b></td>
#end
</tr>
<tr>
<tr>
<td colspan="8">&nbsp;</td>
</tr>
</table>
</fieldset>
#if ($EventStatus == 4)
<p align="center"><input type="button" name="cmdSimpan" value="Simpan" onClick="getNilaiHartaSimpan('3','0','0','0')"><input type="button" name="cmdBatal" value="Batal" onClick="NAview('3','0','0','0')"></p>
#else
	#if ($overalljumlah != 0)
<p align="center"><input type="button" name="cmdkemasini" value="Kemaskini" onClick="getNilaiHartaKemaskini('3','0','0','0')"></p>
	#end
#end
    </td>
  </tr>
</table>
      </div>
    </div>
  </div>    </td>
  </tr>
</table>
</form>

<script>
<!-- TAB -->
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
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}

function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}

function PentingView() {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}

function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
	
}

function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.command.value = "kembali_simati";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}
<!-- NILAI HARTA -->
function getNilaiHartaKemaskini(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="kemaskini_nilai_harta";
	document.f1.eventStatus.value="4";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}
function getNilaiHartaSimpan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="simpan_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}
function getNilaiHartaBatal(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="batal_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}

<!-- HARTA ALIH -->
function getFormHa(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}

function getJenisHa(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}

function getSimpan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="post";
		document.f1.command.value="harta_alih";
		document.f1.mode.value="simpan_harta";
		document.f1.eventStatus.value="1";
		document.f1.tabIdatas.value = tabIdatas;
		document.f1.tabIdtengah.value = tabIdtengah;
		document.f1.tabIdbawah.value = tabIdbawah;
		document.f1.tabIdtepi.value = tabIdtepi;
		document.f1.action="";
		document.f1.submit();
	
	}
}
function edit_hartaalih(id3){
	document.f1.method="post";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="edit_harta";
	document.f1.eventStatus.value="2";
	document.f1.idha.value=id3;
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}
function getKemaskini(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="kemaskini_harta";
	document.f1.eventStatus.value="3";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action="";
	document.f1.submit();
}
function getHapus(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="hapus_harta";
		document.f1.eventStatus.value="1";
		document.f1.tabIdatas.value = tabIdatas;
		document.f1.tabIdtengah.value = tabIdtengah;
		document.f1.tabIdbawah.value = tabIdbawah;
		document.f1.tabIdtepi.value = tabIdtepi;
		document.f1.action="";
		document.f1.submit();
	}
}
function getUpdate(){
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="update_harta";
		document.f1.eventStatus.value="2";
		document.f1.tabIdatas.value = tabIdatas;
		document.f1.tabIdtengah.value = tabIdtengah;
		document.f1.tabIdbawah.value = tabIdbawah;
		document.f1.tabIdtepi.value = tabIdtepi;
		document.f1.action="";
		document.f1.submit();
	
	}
}
function getBatal(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.f1.command.value="harta_alih";
		document.f1.mode.value="batal_harta";
		document.f1.eventStatus.value="1";
		document.f1.tabIdatas.value = tabIdatas;
		document.f1.tabIdtengah.value = tabIdtengah;
		document.f1.tabIdbawah.value = tabIdbawah;
		document.f1.tabIdtepi.value = tabIdtepi;
		document.f1.action="";
		document.f1.submit();
}



<!-- SIMATI -->

</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
</html>
