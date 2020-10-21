<style type="text/css">
<!--
.style40 {color: #0000FF}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<body>
#set ($nofail="")
#set ($namadaerah = "")
#set ($namanegeri = "")
#set ($namaPejabat = "")
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    #set ($nofail = $list.noFail)
    #set ($idfail = $list.idFail)
    #set ($namadaerah = $list.namadaerah)
    #set ($namanegeri = $list.namanegeri)
    #set ($namaPejabat = $list.nama_pejabat)
	#set ($alamat1 = $list.alamat_1)
    #set ($keterangan = $list.keterangan)
    #set ($seksyen = $list.seksyen)
    #set ($tarikhMohon = $list.tarikhMohon)
    #set ($namaPemohon = $list.namaPemohon)
    #set ($namasimati = $list.namaSimati)
    <input name="idPermohonan" type="hidden" value="$id"/>
     <input name="idpermohonan" type="hidden" value="$id"/>
#end
<tr>
<td>
#if($!headerppk.size()>0)
#parse("app/ppk/headerppk.jsp")
#end

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset style="display:none">
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style40">$!nofail</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Negeri :</div></td>
          <td style="text-transform:uppercase;"><div align="left" class="style40">$!namanegeri</div></td>
        </tr>
        <tr>
          <td valign="top" style="text-transform:uppercase;"><div align="right">Daerah / Jajahan :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namadaerah</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Unit :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$namaPejabat &nbsp; $!alamat1</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Status Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style40">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style40">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style40">$!namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
        
      </table>
    </div></td>
  </tr>
</table>

</fieldset>
</td>
</tr>
#foreach ($listDataNegeriDaerah in $listNegeriDaerah)
#set ($namanegeri = $listDataNegeriDaerah.namanegeri)
#set ($namadaerah = $listDataNegeriDaerah.namadaerah)
#end
 <input type="hidden" name="action">
 <input type="hidden" name="hitButt" >
 <input type="hidden" name="mode" >
  <input type="hidden" name="idPermohonan" value="$idPermohonan">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
#foreach ($listKeputusan in $listBkeData)
	#set ($keputusanpindah = $listKeputusan.keputusanpegawai)
	#set ($perintah = $listKeputusan.jenisperintah)
	#set ($pengendali = $listKeputusan.idunitpsk)
	#set ($catatan = $listKeputusan.catatanpegawai)
	#set ($keputusanpegawai = $listKeputusan.keputusanpegawai)
    #set ($keputusankptg = $listKeputusan.keputusankptg)
#end     
#if ($keputusanpindah == "L")
	#set ($checked1 = "checked")
    #set ($checked2 = "")
#elseif ($keputusanpindah == "G")
	#set ($checked1 = "")
	#set ($checked2 = "checked")
#end
#if ($perintah == "PT")
	#set ($checkeda = "checked")
    #set ($checkedb = "")
    #set ($checkedc = "")
#end


  <table width="100%" border="0">
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
		  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonBkeView('0','0','0','0')">PERMOHONAN BKE</li>
          #if ($tarikhmohon!="")
          <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="KeputusanView('1','0','0','0')">KEBENARAN</li>
          #end
          #if ($keputusanpegawai == "L")
          <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="KptgView('2','0','0','0')">KEPUTUSAN PPP</li> <!-- arief ubah KPTG/PTG kepada PPP-->
          #end
          </ul>
          <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent">
          <table width="100%">
          <tr>
          <td width="30%" align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Permohonan Keputusan Pindah :</td>
          <td width="70%"><input type="radio" name="sorkeputusan" value="L" $readmode $checked1> MEMBENARKAN &nbsp;&nbsp;<input type="radio" name="sorkeputusan" value="G" $readmode $checked2> TIDAK MEMBENARKAN</td>
          </tr>
          <tr>
          <td width="30%" align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Dikeluarkan Oleh :</td>
          <!-- ARIEF COMMENT <td width="70%"><input type="radio" name="sorpegawai" value="PT" $readmode $checkeda> PENTADBIR TANAH &nbsp;&nbsp;<input type="radio" name="sorpegawai" value="PD" $readmode $checkedb> PEGAWAI DAERAH <input type="radio" name="sorpegawai" value="PTS" $readmode $checkedc> PENTADBIR DAERAH SARAWAK</td> -->
		  <td width="70%"><input type="radio" name="sorpegawai" value="PT" $readmode $checkeda checked> PEGAWAI PUSAKA </td>
          </tr>
          <tr>
          <td width="30%" align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Pengawai Pengendali :</td>
          <td width="70%"><select name="socPengendali" style="width: 280px;" $readmode>
          #if ($pengendali != "")
	          #set ($nama = "")
	          #set ($jawatan = "")
	          #set ($selectPengendali = "")
	          #foreach ($listpegawai in $listPegawai)
		          #set ($nama = $listpegawai.namapegawai)
		          #set ($jawatan = $listpegawai.jawatan)
		          	 #if ($listpegawai.idunitpsk == $pengendali)
		          		#set ($selectPengendali = "selected")
	          		<option value="$listpegawai.idunitpsk" $selectPengendali>$!nama - $!jawatan</option>
	          		 #end
	          #end
	          	<option>SILA PILIH</option>
	          #foreach ($listpegawai in $listPegawai)
		          #set ($nama = $listpegawai.namapegawai)
		          #set ($jawatan = $listpegawai.jawatan)
	          		<option value="$listpegawai.idunitpsk">$!nama - $!jawatan</option>
	          #end
	       #else
	       		<option>SILA PILIH</option>
	       	  #foreach ($listpegawai in $listPegawai)
		          #set ($nama = $listpegawai.namapegawai)
		          #set ($jawatan = $listpegawai.jawatan)
	          		<option value="$listpegawai.idunitpsk">$!nama - $!jawatan</option>
	          #end
	       #end
          </select>
          #foreach ($listpegawai in $listPegawai)
          		#if ($listpegawai.idunitpsk == $pengendali)
          			<input type="hidden" name="txtIdNegeriPsk" value="$listpegawai.idnegeri">
          		#end
          #end
          </td>
          </tr>
          <tr>
          <td width="30%" align="right" valign="top">Catatan Pegawai :</td>
          <td width="70%"><textarea name="txtCatatanPegawai" cols="40" $readmode style="text-transform:uppercase;">$!catatan</textarea></td>
          </tr>
		  <tr>
		<td colspan="2" height="50px" valign="bottom"><i><font color="#ff0000">*</font> &nbsp;Sila pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
    	</tr>
          <tr>
          <td></td>
          <td valign="bottom" height="50px">
          #if ($keputusanpegawai=="")
              #if ($btnKemaskini == "yes")
                <input type="button" name="cmdKemaskini" value="Kemaskini" onClick="kemaskini_keputusan('1','0','0','0')">
              #end
              #if ($btnSimpan == "yes")	
                <input type="button" name="cmdSimpan" value="Simpan" onClick="simpan_keputusan('1','0','0','0')">
              #end
              #if ($btnBatal == "yes")
                <input type="reset" name="cmdBatal" value="Batal">
              #end
          #end
          #if ($keputusanpegawai!="")
            	#if ($btnCetak == "yes")
                <input type="button" name="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')">
          		#end
         	#end
		  #if ($btnKembali == "yes")
		  	<input type="button" name="cmdKembali" value="Kembali" onClick="kembali_keputusan()">
		  #end
		  <br>
		  </td>
          </tr>
          </table>
          </div>
          #if ($keputusanpegawai == "x")
          <div class="TabbedPanelsContent"></div>
          #end
          </div>
      </div></td>
    </tr>
  </table>
  <fieldset id="tableReport" style="display:none;">
  <legend><strong>Senarai Cetakan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      #if ($keputusanpegawai=="L")
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratLulusPrmhnnBKE('$nofail','$idfail','$pengendali')"><span class="style40">Surat Pindah Fail (Borang R)</span></a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakIringanBorangQ('$nofail','$idfail','$pengendali')"><span class="style40">Surat Iringan Borang Q</span></a></td>
      </tr>
       <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangR('$idfail')"><span class="style40">Borang R</span></a></td>
      </tr>
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakBorangQ('$idfail')"><span class="style40">Borang Q</span></a></td>
      </tr>
	  #end
	  #if ($keputusanpegawai=="G")
      <tr>
        <td ><a href="#" class="style2" onClick="javascript:cetakSuratTolakPrmhnnBKE('$nofail','$idfail','$pengendali')"><span class="style40">Surat Tolak Permohonan BKE</span></a></td>
      </tr>
      #end
    </table>
</fieldset>
  #parse("app/ppk/headerppk_script.jsp")
<script type="text/javascript">
function PemohonBkeView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Permohonanview";
	document.${formName}.hitButt.value="Permohonan";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function KeputusanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Keputusanview";
	document.${formName}.hitButt.value="Keputusan";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function KptgView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Kptgview";
	document.${formName}.hitButt.value="Kptg";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function simpan_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	if (document.${formName}.sorkeputusan[0].checked == false && document.${formName}.sorkeputusan[1].checked == false){
		alert("Sila pilih Keputusan Pindah");
	}
	else if (document.${formName}.sorpegawai[0].checked == false && document.${formName}.sorpegawai[1].checked == false && document.${formName}.sorpegawai[2].checked == false){
		alert("Sila pilih Dikeluarkan Oleh");
	}
	else if (document.${formName}.sorkeputusan[0].checked == false && document.${formName}.sorkeputusan[1].checked == false){
		alert("Sila pilih Keputusan Pindah");
	}
	else{	
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value="Keputusansimpan";
			document.${formName}.hitButt.value="Keputusan";
			document.${formName}.tabIdatas.value=tabIdatas;
			document.${formName}.tabIdtengah.value=tabIdtengah;
			document.${formName}.tabIdbawah.value=tabIdbawah;
			document.${formName}.tabIdtepi.value=tabIdtepi;
			document.${formName}.action.value="";
			document.${formName}.submit();
		}
	}
}

function kemaskini_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="Keputusankemaskini";
		document.${formName}.hitButt.value="Keputusan";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		document.${formName}.submit();
}

function batal_keputusan(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Keputusanbatal";
	document.${formName}.hitButt.value="Keputusan";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function kembali_keputusan() {
	document.${formName}.method="post";
	document.${formName}.hitButt.value="Kembali";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function cetakSuratLulusPrmhnnBKE(noFail,idfail,idpegawai) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratLulusPrmhnnBKE&flagReport=S&idpegawai="+idpegawai;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratTolakPrmhnnBKE(noFail,idfail,idpegawai) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratTolakPrmhnnBKE&flagReport=S&idpegawai="+idpegawai;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangQ(idfail) {
    var url = "../servlet/ekptg.report.ppk.BorangQ?idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakIringanBorangQ(noFail,idfail,idpegawai) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratIringanBorangQ&flagReport=S&idpegawai="+idpegawai; 
 	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangR(idfail) {
    var url = "../servlet/ekptg.report.ppk.BorangR?idfail="+idfail; 
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
//-->
</script>

</body>

