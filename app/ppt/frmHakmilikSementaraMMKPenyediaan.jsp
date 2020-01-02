<!--<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css">-->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp") 
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">Penyediaan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSemakan('$idFail','$idPermohonan','$idMMK')">Semakan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan','$idMMK')">Keputusan PBN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <fieldset><legend><strong>Butir-Butir Asas</strong></legend>
    <table width="100%">
  <tr>
    <td width="20%" align="left">Pemohon</td>
    <td width="1%">:</td>
    <td width="29%">$PEMOHON</td>
    <td width="20%" align="left">Bilangan Lot</td>
    <td width="1%">:</td>
    <td width="29%">$BILLOT</td>
  </tr>
  <tr>
    <td align="left">Tujuan</td>
    <td>:</td>
    <td>$TUJUAN</td>
    <td align="left">Luas Tanah yang diperlukan</td>
    <td>:</td>
    <td>$LUASSEWA</td>
  </tr>
  <tr>
    <td align="left">Lot-lot yang terlibat</td>
    <td>:</td>
    <td>$NOLOT</td>
    <td align="left">Lokasi</td>
    <td>:</td>
    <td>$LOKASI</td>
  </tr>
 
</table>

    </fieldset>
    <fieldset>
 
 		
		
		 #if($negeriMMK=="2")
		 	#parse("app/ppt/MMKSementara/frmMMKKedah.jsp")
		 #elseif($negeriMMK=="3")
		 	#parse("app/ppt/MMKSementara/frmMMKKelantan.jsp")
		 #elseif($negeriMMK=="4")
		 	#parse("app/ppt/MMKSementara/frmMMKMelaka.jsp")
		 #elseif($negeriMMK=="5")
		 	#parse("app/ppt/MMKSementara/frmMMKNegeriSembilan.jsp")
		 #elseif($negeriMMK=="6")
		 	#parse("app/ppt/MMKSementara/frmMMKPahang.jsp")
		 #elseif($negeriMMK=="7")
		 	#parse("app/ppt/MMKSementara/frmMMKPulauPinang.jsp")
		 #elseif($negeriMMK=="8")
		 	#parse("app/ppt/MMKSementara/frmMMKPerak.jsp")
		 #elseif($negeriMMK=="9")
		 	#parse("app/ppt/MMKSementara/frmMMKPerlis.jsp")
		 #elseif($negeriMMK=="10")
		 	#parse("app/ppt/MMKSementara/frmMMKSelangor.jsp")
		 #elseif($negeriMMK=="11")
		 	#parse("app/ppt/MMKSementara/frmMMKTerengganu.jsp")
		 #elseif($negeriMMK=="14")
		 	#parse("app/ppt/MMKSementara/frmMMKKL.jsp")
		 #else
		 	#parse("app/ppt/MMKSementara/frmMMKJohor.jsp")
		 #end
		
	
    
    
    </fieldset>
    
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($mode == 'newMMK')
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanPenyediaan('$idPermohonan')" />
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalPenyediaan()" />

    #end
    #if($mode == 'paparMMK')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPenyediaan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
     <!-- <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusPenyediaan()" />-->
    #end
    #if($mode == 'kemaskiniMMK')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updatePenyediaan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalPenyediaan()" />

    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
   
    </td>
    </tr>

</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td>
        
        #if($negeriMMK=="2")
		 
		 #elseif($negeriMMK=="3")
		 	
		 #elseif($negeriMMK=="4")
		 	
		 #elseif($negeriMMK=="5")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKNS('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="6")
		 
		 #elseif($negeriMMK=="7")
		 	
		 #elseif($negeriMMK=="8")
		 	
		 #elseif($negeriMMK=="9")
		 	
		 #elseif($negeriMMK=="10")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKSelangor('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #elseif($negeriMMK=="11")
		 	
		 #elseif($negeriMMK=="14")
		 	<a href="#" class="style2" onClick="javascript:cetakMMKKL('$idFail')"><font color="blue"> Kertas MMK/KM/MB/LC </font></a>
		 #else
		 	
		 #end
        
        
        
        
        
        
        </td>
      </tr>           
    </table>
</fieldset>
<input name="id_fail" type="hidden" value="$idFail" />
<input name="id_permohonan" type="hidden" value="$idPermohonan" />
<input name="idMMK" type="hidden" value="$idMMK" />
<input name="idStatus" type="hidden" value="$idStatus" />
<input name="mode" type="hidden" value="$mode" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
window.onload = submitForm;
//-->
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakMMKNS(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.MMKSementara?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKSelangor(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKSelangor?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function cetakMMKKL(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SementaraMMKKL?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabSemakan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=tabSemakan";
	document.${formName}.submit();
}
function tabKeputusan(id_fail,id_permohonan){
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=tabKeputusan";
	document.${formName}.submit();
}
function simpanPenyediaan(id_permohonan){
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=simpanPenyediaan";
	document.${formName}.submit();
}
function kemaskiniPenyediaan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=kemaskiniPenyediaan";
	document.${formName}.submit();
}
function hapusPenyediaan(){
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=hapusPenyediaan";
	document.${formName}.submit();
}
function updatePenyediaan(){

	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=updatePenyediaan";
	document.${formName}.submit();
}
function batalPenyediaan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=newMMK";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraMMK&action=";
	document.${formName}.submit();
}

</script>