<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}

.style3 {font-size: 12px}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #0000FF}
.style44 {
	font-size: 9px;
	font-style: italic;
	color: #FF0000;
}
.style49 {color: #FF0000}
.style50 {
	font-size: 9px;
	font-style: italic;
}
.style51 {color: #0000FF; font-size: 9px; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style42 {
	color: #0000FF;
}
-->
</style>

#set($readmodeR = "readonly")
#set($readmode = "disabled")
<input type="hidden" value="$id_perbicaraan" name="id_perbicaraan">
<input type="hidden" value="$nofail" name="nofail">



				
				
				
			<div class="TabbedPanelsContent">

 
              	<fieldset>
              	<legend>SENARAI KEHADIRAN (tambah)</legend>
              	
              	<table width="40%" border="0" cellspacing="2" cellpadding="2">
              	<tr >
              	<td width="10%" >Nama
              	</td>
              	<td width="5%" >:&nbsp;<input name="nama" type="text" size="34" maxlength="200">
              	
              	</td>
              	<td width="25%" align="left"></td>
              	</tr>
              	<tr >
              	<td width="10%" >Hubungan
              	</td>
              	<td width="5%" >:&nbsp;<input name="hubungan" type="text" size="34" maxlength="200">
              	
              	</td>
              	<td width="25%" align="left"></td>
              	</tr>
              	<tr >
              	<td width="10%" >Kehadiran
              	</td>
              	<td width="5%" >:&nbsp;<select name="kehadiran" style="width:130">
  										<option value="1">Hadir</option>
  										<option value="0">Tidak hadir</option>
										</select> 
              	
              	</td>
              	<td width="25%" align="left"></td>
              	</tr>
              	
          			
          			<tr>
              			<td >
              			</td>
              			<td><input type="button" name="tambah" value="Tambah" onclick="javascript:TambahKehadiran($id_perbicaraan)">
              			</td>
              			
              			<td >
              			</td>
              		</tr>

              	
              	
              	
              	</table>
              	</fieldset>

            </div>
            
 


         
				
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/> 
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="command">
<input type="hidden" name="id_simati" value="$id_simati" />
<input type="hidden" name="id_Permohonansimati" value="$!id_permohonansimati" />

<script>

function semakWaris1() {
	var bapa=document.${formName}.bapa.value;
	//alert("document.${formName}.BapaSimati = "+document.${formName}.BapaSimati);
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline?bapa="+bapa;
		document.${formName}.command.value = "maklumatSimati";
		document.${formName}.submit();


	
}

function setSelected(tabId) {
	
    document.${formName}.tabId.value = tabId;	
}

function paparSoalan(namaHadir) {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatSimati";
	document.${formName}.namaPenanya.value = namaHadir;
	document.${formName}.submit();
}

function maklumatSimati(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.namaPenanya.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatSimati";
	document.${formName}.submit();
}


function maklumatPerbicaraan(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.questioner.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "papar_bicara";
	document.${formName}.submit();
}

function maklumatPemohon(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatPemohon";
	document.${formName}.submit();
}

function maklumatWaris(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatWaris";
	document.${formName}.submit();
}

function TambahKehadiran(id_permohonan) {
	//alert ("Read Here");
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline?command=maklumatTambahKehadiran";
	document.${formName}.command.value = "maklumatTambahKehadiran";
	
	document.${formName}.submit();
}

</script>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>