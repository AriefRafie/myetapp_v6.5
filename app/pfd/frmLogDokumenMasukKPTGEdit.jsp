
<style type="text/css">
<!--
.style1 {color: #0033FF}
.style5 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style6 {
	color: #000000;
	font-style: italic;
}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>


<input name="idLogDokumenKPTG" type="hidden" value="$!idLogDokumenKPTG"/>
<input name="mode" type="hidden" value="$mode" />
<input name="text" type="text" value="$user_Role" />
&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >LOG DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenKeluar()">LOG DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDariSeksyenLain()">LOG DOKUMEN DARI SEKSYEN LAIN</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent">
    <fieldset>
       <legend>MAKLUMAT LOG DOKUMEN MASUK</legend>
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="71%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" $readOnly $disabled>
        
		#if ($jenis_Dokumen == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>

        #elseif ($jenis_Dokumen == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2" selected="selected">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>
        
        #elseif ($jenis_Dokumen == '3')
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3" selected="selected">DOKUMEN TERPERINGKAT</option>
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>
        
        #end
      
      
      </select>
      <label></label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $disabled/>
Laporan</td>
  </tr>
  #if ($mode == 'New' )
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muatnaik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="txtLampiran" type="file" id="txtLampiran" size="50" /></td>
  </tr>
  #else
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach($paparLampiranLogDokumenMasuk in $SenaraiListLampiranLogDokumenMasuk)
      <table width="100%" border="0">
      <tr>
        <td>$paparLampiranLogDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenMasuk.id_lampiran')" class="style1">$paparLampiranLogDokumenMasuk.nama_fail</a></td>
      </tr>
    </table>
      #end    </td>
  </tr>
  #end
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Diterima_Dihantar" type="text" id="tarikh_Diterima_Dihantar" value="$!tarikh_Diterima_Dihantar" size="10" $readOnly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Diterima_Dihantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" $readOnly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" $readOnly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Daripada Siapa</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="pengirim_Dokumen" cols="41" id="pengirim_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>

  <tr>
    <td width="1%" align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <textarea name="tajuk_Dokumen" cols="41" id="tajuk_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="dirujuk_Kepada" cols="41" id="dirujuk_Kepada" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!dirujuk_Kepada</textarea></td>
  </tr>
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama Ketua Pengarah / Timb. Ketua Pengarah</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socTKP" id="socTKP" $readOnly $disabled>
                  #foreach($listTKP in $SenaraiTKP)
                  	#if($selectidorang == $listTKP.user_id)
                    <option value="$listTKP.user_id" selected="selected">$listTKP.user_name </option>
                    #else
                    <option value="$listTKP.user_id" >$listTKP.user_name </option>
                  	#end
                  #end
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
  #if($user_Role == '(Support)Ketua/Timb Ketua Pengarah')
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')         
    #end  
    </td>
  </tr>
  #else
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')
         <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
         <input name="cmdTambahDokumen2" type="button" value="Tambah" onclick="tambahLogDokumenMasuk()" />
         <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
	#end 
    #if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update1()"/>
   	 	<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batalView()"/>
    	<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'PaparUpdate')
    	<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
    #end	</td>
  </tr>
  #end
</table>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="user_Role" id="user_Role" type="hidden" value="$!user_Role"/>
</fieldset>

#if($user_Role == '(Support)Ketua/Timb Ketua Pengarah')

<fieldset>
 <legend>AGIHAN TUGASAN</legend>
 <input name="cmdTambahMinit" type="button" value="Tambah Agihan Tugasan" onclick = "tambahMinit('$idLogDokumenKPTG')"/>
</fieldset>
#else
#end
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
   </div>
</div>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function tabLogDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=tabLogDokumenKeluarKPTG";
	document.${formName}.submit();
}
function tabLogDokumenDariSeksyenLain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=tabLogDokumenDariSeksyenLainKPTG";
	document.${formName}.submit();
}

function simpanLogDokumen(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=simpanLogDokumenMasukKPTG&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&no_Rujukan_Lain="+document.${formName}.no_Rujukan_Lain.value+"&tajuk_Dokumen="+document.${formName}.tajuk_Dokumen.value+"&no_Rujukan_Lain="+document.${formName}.no_Rujukan_Lain.value+"&pengirim_Dokumen="+document.${formName}.pengirim_Dokumen.value+"&tarikh_Diterima_Dihantar="+document.${formName}.tarikh_Diterima_Dihantar.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&dirujuk_Kepada="+document.${formName}.dirujuk_Kepada.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socTKP="+document.${formName}.socTKP.value;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}

function tambahLogDokumenMasuk(){
	 
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=tabLogDokumenMasukKPTG";
	document.${formName}.submit();

}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=kemaskiniLogDokumenMasuk";
	document.${formName}.submit();
}

function update1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=updateLogDokumenMasuk";
	document.${formName}.submit();
}

function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=";
	document.${formName}.submit();
}

function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob3?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function tambahMinit(id){
	document.${formName}.idLogDokumenKPTG.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action=tambahSeksyen1";
	document.${formName}.submit();

}
</script>
