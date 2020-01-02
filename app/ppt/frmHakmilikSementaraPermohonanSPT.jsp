<script src="../SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<fieldset>
<strong>
<legend>Daftar Permohonan</legend>
</strong>
<table width="100%">
  <tr>
    <td><fieldset>
    <strong>
    <legend>Maklumat Asas Permohonan</legend>
    </strong>
    <table width="100%">
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="28%" align="left">No. Permohonan</td>
        <td width="1%">:</td>
        <td width="70%">
        <input name="no_permohonan" type="text" class="disabled" id="no_permohonan" value="$noPermohonan" size="38px" readonly="readonly"/>
        </td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="28%" align="left">Urusan</td>
        <td width="1%">:</td>
        <td width="70%"><input name="txtUrusan" type="text" class="disabled" value="03 - PENGAMBILAN SECARA PENDUDUKAN SEMENTARA" size="55" readonly="readonly"/>
       </td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="28%" align="left">Jenis Permohonan</td>
        <td width="1%">:</td>
        <td width="70%"><input name="txtJenisPermohonan" type="text" class="disabled" value="PERMOHONAN KAUNTER" size="25" readonly="readonly" /></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="28%" align="left">Tarikh Permohonan</td>
        <td width="1%">:</td>
        <td width="70%">
        <input name="tarikh_permohonan" id="tarikh_permohonan" size="11px"  type="text" value="$tarikhPohon" class="disabled" readonly="readonly" />
        </td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td width="28%" align="left">Status Permohonan</td>
        <td width="1%">:</td>
        <td width="70%">
        <input type="text" name="status" value="$status" size="28px" class="disabled" readonly="readonly" />
        </td>
      </tr>
    </table>

    </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
    <strong>
    <legend>Maklumat Agensi / KJP</legend>
    </strong>
    <table width="100%">
      <tr>
        <td width="1%"><font color="red">*</font></td>
        <td width="28%">Kementerian</td>
        <td width="1%">:</td>
        <td>$SelectKementerian</td>
      </tr>
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Nama Agensi</td>
        <td width="1%">:</td>
        <td>$SelectAgensi</td>
      </tr>
      <tr>
        <td width="1%"><font color="red">*</font></td>
        <td width="28%">Alamat</td>
        <td width="1%">:</td>
        <td><input type="text" name="alamat1" value="$addAlamat1" id="Alamat1" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50px" $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">&nbsp;</td>
        <td width="1%">&nbsp;</td>
        <td><input type="text" name="alamat2" value="$addAlamat2" id="alamat2" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="50px"  $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">&nbsp;</td>
        <td width="1%">&nbsp;</td>
        <td><input type="text" name="alamat3" value="$addAlamat3" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="alamat3" size="50px"  $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td width="1%"><font color="red">*</font></td>
        <td width="28%">Poskod</td>
        <td width="1%">&nbsp;</td>
        <td><input type="text" name="poskod" size="4px" onkeyup="validatePoskod(this,this.value);"  value="$addPoskod" maxlength="5" id="poskod"  $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td width="1%"><font color="red">*</font></td>
        <td width="28%">Negeri</td>
        <td width="1%">&nbsp;</td>
        <td>$SelectNegeri</td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
    <strong>
    <legend>Maklumat Lengkap Projek</legend>
    </strong>
    <table width="100%">
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Amaun Peruntukan</td>
        <td width="1%" valign="top">:</td>
        <td valign="top"><input name="new_flag_peruntukan" type="radio" value="1" $TEMPcheckedAda #if ($mode=='papar') disabled #end />
          Ada
            <input name="new_flag_peruntukan" type="radio" value="2" $TEMPcheckedTiada #if ($mode=='papar') disabled #end/>
          Tiada</td>
      </tr>
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Pengambilan Segera?</td>
        <td width="1%" valign="top">&nbsp;</td>
        <td valign="top"><input name="new_flag_segera" type="radio" value="1" $TEMPcheckedYa #if ($mode=='papar') disabled #end/>
          Ya
           	  &nbsp;&nbsp;
           	  <input name="new_flag_segera" type="radio" value="2" $TEMPcheckedTidak #if ($mode=='papar') disabled #end/>
       	  Tidak</td>
      </tr>
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Projek Negeri</td>
        <td width="1%" valign="top">:</td>
        <td valign="top">$project_negeri</td>
      </tr>
      <!--<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Jajahan</td>
        <td valign="top">:</td>
        <td valign="top">&nbsp;</td>
      </tr>-->
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Daerah</td>
        <td width="1%" valign="top">:</td>
        <td valign="top">$SelectDaerah</td>
      </tr>
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Bandar/Mukim/Pekan</td>
        <td width="1%" valign="top">:</td>
        <td valign="top">$SelectBandar</td>
      </tr>
      <!--<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Seksyen</td>
        <td valign="top">:</td>
        <td valign="top">&nbsp;</td>
      </tr>-->
      <tr>
        <td width="1%" valign="top"><font color="red">*</font></td>
        <td width="28%" valign="top">Tujuan</td>
        <td width="1%" valign="top">:</td>
        <td valign="top"><textarea name="tujuan"  id="tujuan"  cols="40%" maxlength="400" rows="4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly class="$disabled">$addTujuan</textarea></td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">No. Ruj. Surat KJP</td>
        <td width="1%" valign="top">:</td>
        <td valign="top"><input type="text" name="rujukan_kementerian" id="rujukan_kementerian" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" maxlength="50" value="$addRujukan_kementerian" size="23px"  $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Tarikh Surat</td>
        <td width="1%" valign="top">:</td>
        <td valign="top"><input name="tarikh_surat" value="$addTarikhSurat" size="11px" id="tarikh_surat" type="text"  $readonly class="$disabled"/>
 		#if ($readonly != 'readonly')  <img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_surat',false,'dmy');"> #end</td>
      </tr>
      <tr>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="28%" valign="top">Tarikh Dikehendaki</td>
        <td width="1%" valign="top">:</td>
        <td valign="top"><input name="tarikh_kehendaki" value="$addTarikhHendak" size="11px" id="tarikh_kehendaki" type="text"  $readonly class="$disabled"/>
 		  #if ($readonly != 'readonly') <img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_kehendaki',false,'dmy');"> #end</td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
</table>
#if ($mode != 'baru')
<table width="100%">
<tr>
    <td>
    <fieldset>
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0" >Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" tabindex="0">Senarai Dokumen</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        <fieldset><legend><strong>Maklumat Tanah Terlibat</strong></legend>
        <table width="100%">
          <tr>
            #if($currentStatus=="11")
            <td colspan="5">
              <input type="submit" name="cmdTambahTanah" id="cmdTambahTanah" value="Tambah" onClick="javascript:tambah();">
           </td>
           #else
    		<td>&nbsp;</td>
    	   #end
            </tr>
          <tr class="table_header">
            <td><strong>No</strong></td>
            <td><strong>No. Hakmilik</strong></td>
            <td><strong>No. Lot / No. PT</strong></td>
            <td><strong>Mukim / Pekan / Bandar</strong></td>
            <td><strong>Luas Lot</strong></td>
          </tr>
           		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                      #if ($listTanah.bil == '') 
                        #set ($row = 'row1')
                      #elseif ($listTanah.bil % 2 != 0)
                        #set ($row = 'row1')
                      #else 
                        #set ($row = 'row2')
                      #end 
                    #set($idMaklumat=$listTanah.id_hakmilik)
              <tr>
               	<td class="$row">$listTanah.bil</td>
                        #if ($listTanah.bil != '')
                    	<td class="$row"><a href="javascript:edit_maklumat('$idMaklumat')"><font color="blue">$listTanah.no_hakmilik</font></a></td>
                        #else
                        <td class="$row">$listTanah.no_hakmilik</td>
                        #end
                        <td class="$row">$listTanah.kod_lot $listTanah.no_lot</td>
                        <td class="$row">$listTanah.nama_mukim</td>
                <td class="$row">$listTanah.luas_lot&nbsp;$listTanah.unitluaslot</td>
                     </tr>
                        
                #end    
               #end
                         
        </table>
        </fieldset>
        </div>
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>

			
				<table width="100%">
                	<tr>
                    #if($currentStatus=="11")
                    	<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
                    #else
                    <td>&nbsp;</td>
    	   			#end
                    </tr>
                    <tr class="table_header">
                    	<td><b>No</b></td>
                    	<td width="30%"><b>Nama Dokumen</b></td>
                        <td width="40%"><b>Keterangan</b></td>
                        <td width="30%"><b>Dokumen Sokongan</b></td>
                    </tr>
              
         #if($listD_size!=0)
          
             #foreach($listD in $listDokumen)  
              #set($idDokumen=$listD.id_Dokumen)    
                   
                    #if ($listD.bil == '') 
                        #set ($row = 'row1')
                      #elseif ($listD.bil % 2 != 0)
                        #set ($row = 'row1')
                      #else 
                        #set ($row = 'row2')
                    #end 
         		      
                    <tr>
                    	<td class="$row" align="center">$listD.bil</td>
                    	<td class="$row">$listD.tajuk</td>
                        <td class="$row">$listD.keterangan</td>
                        #if($listD.bil != '')
                       	<td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                        #else
                       	<td class="$row">$listD.nama_Fail</td>
                       #end
                    <tr>
              #end 
             #end                     
                </table>        	
            </fieldset>	
        
        </div>
      </div>
    </div>
    </fieldset>
    </td>
  </tr>
</table>
#end
<table width="100%">
  <tr align="center">
    <td>
    #if($mode == 'baru')
    	<input name="cmdSimpan" type="button" value="Simpan" onKeyPress="add_item()" onClick="add_item()">
        <input name="cmdKembali" type="button" value="Kembali" onClick="batal()">
    #elseif($mode == 'papar')
       #if($currentStatus == '11')
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()">
            <input name="cmdSahkan" type="button" value="Sahkan" onClick="sah('$id_permohonan')">
       #end
      #if($currentStatus == '127')
            <input name="cmdLuluskan" type="button" value="Luluskan" onClick="lulus('$id_permohonan')">
      #end
      <input name="cmdKembali" type="button" value="Kembali" onClick="kembali()">
    #elseif($mode == 'kemaskini')
    	<input name="cmdSimpan" type="button" value="Simpan" onClick="update_item('$id_permohonan')">
        <input name="cmdKembali" type="button" value="Kembali" onClick="batal_update('$id_permohonan')">
    #end
    </td>
  </tr>
</table>
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_hakmilik" value="$idMaklumat">
<input type="hidden" name="id_semak" value="$idSemak">
<input type="hidden" name="id_status" value="$idstatus">
<input type="hidden" name="suburusan" value="$idSuburusan">
<input type="hidden" name="id_senaraiSemak" value="$idSenaraiSemak">
<input type="hidden" name="id_fail" value="$idFail">
<input type="hidden" name="idKementerianA" value="$idKementerianA">
<input type="hidden" name="idNegeriA" value="$idNegeriA">
<input type="hidden" name="mode" value="$mode">
<input type="hidden" name="flag_peruntukan" value="$flag_peruntukan">
<input type="hidden" name="flag_segera" value="$flag_segera">
</fieldset>


<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
<script>
function doChangeidNegeri() {
    doAjaxCall${formName}("doChangeidNegeri");
}
function doChangeidProjekNegeriUpdate() {
    doAjaxCall${formName}("doChangeidProjekNegeriUpdate");
}
function doChangeidKementerian() {
    doAjaxCall${formName}("doChangeidKementerian");
}
function doChangeidKementerianUpdate() {
    doAjaxCall${formName}("doChangeidKementerianUpdate");
}
function doChangeidMukim() {
    doAjaxCall${formName}("doChangeidMukim");
}
function doChangeidMukimUpdate() {
    doAjaxCall${formName}("doChangeidMukimUpdate");
}
function doChangeAlamatAgensi() {
    doAjaxCall${formName}("doChangeAlamatAgensi");
}
function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=tambahDokumen"; 
	document.${formName}.submit();
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function sah(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=sah"; 
	document.${formName}.submit();	
}
function lulus(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=lulus"; 
	document.${formName}.submit();	
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=batal";
	document.${formName}.submit();
}
function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=edit_maklumat";
	document.${formName}.submit();
}
function add_item()
{
	var radioSelected2 = false;
	for (j = 0;  j < ${formName}.new_flag_segera.length;  j++){
	if (${formName}.new_flag_segera[j].checked)
	radioSelected2 = true;
	}
	var radioSelected = false;
	for (i = 0;  i < ${formName}.new_flag_peruntukan.length;  i++){
	if (${formName}.new_flag_peruntukan[i].checked)
	radioSelected = true;
	}
	
	//if(document.${formName}.socSuburusan.value == ""){
	//	alert("Sila pilih \"Urusan\" terlebih dahulu.");
  	//	document.${formName}.socSuburusan.focus(); 
	//	return;
	//}
	if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
		return;
	}
	else if (!radioSelected2){
		alert("Sila pilih \"Pengambilan segera\" terlebih dahulu.");
		return;
	}
	else if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Projek negeri\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	}
	else if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	else if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
	else if(document.${formName}.tarikh_permohonan.value == ""){
		alert("Sila pilih \" tarikh permohonan \" terlebih dahulu.");
  		document.${formName}.tarikh_permohonan.focus(); 
		return;
	}
	else if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	else if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	else if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	}
	else if (document.${formName}.poskod.value != "" && document.${formName}.poskod.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.poskod.focus();
	}
	else if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Mukim/Pekan \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
		return;
	} 
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=simpan";
		document.${formName}.submit();
	}	
}
function update_item(id_permohonan) {

	
	temp1 = parseInt(document.${formName}.poskod.value);

	if (temp1 <10000 || temp1>99999) {
	alert ("Sila masukkan 5 digit poskod");
	document.${formName}.poskod.focus();
	return;
	} 

	if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	if(document.${formName}.flag_peruntukan.value == ""){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
  		document.${formName}.flag_peruntukan.focus(); 
		return;
	}
	if(document.${formName}.flag_segera.value == ""){
		alert("Sila pilih \"Pengambilan segera\" terlebih dahulu.");
  		document.${formName}.flag_segera.focus(); 
		return;
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
	if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Mukim/Pekan \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
		return;
	} 
	else
	{  
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=update"; 
		document.${formName}.submit();	
 	}
}
function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=batal";
	document.${formName}.reset();
	document.${formName}.submit();
}
function kemaskini() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=kemaskini"; 
	document.${formName}.submit();
}
function batal_update(id_permohonan)
{
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=semak";
	document.${formName}.submit();
}
function tambah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=tambah"; 
	document.${formName}.submit();
}
</script>