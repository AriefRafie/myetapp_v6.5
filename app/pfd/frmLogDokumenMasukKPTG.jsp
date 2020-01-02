
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
<input name="idLogDokumenKPTGSekLain" type="hidden" value="$!idLogDokumenKPTGSekLain"/>
<input name="mode" type="hidden" value="$mode" />
&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenKeluar()">DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDariSeksyenlain()">LOG DOKUMEN DARI SEKSYEN LAIN</li>
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
    <td>
    #foreach( $i in [1..5] )
    <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end
    </td>
  </tr>
  #else
  	#if($idLogDokumenKPTGSekLain != '')
<tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>#foreach($paparLampiranLogDokumenTKPKDariSeksyenLain in $SenaraiListLampiranLogDokumenTKPKDariSeksyenLain)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenTKPKDariSeksyenLain.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenTKPKDariSeksyenLain.id_lampiran')" class="style1">$paparLampiranLogDokumenTKPKDariSeksyenLain.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
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
      #end    
      </td>
  </tr>
    #end
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
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value = "$!no_Rujukan_Lain" size="44" $readOnly $disabled/></td>
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
    <td><textarea name="pengirim_Dokumen" cols="41" id="pengirim_Dokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readOnly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>

  <tr>
    <td width="1%" align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
    <textarea name="tajuk_Dokumen" cols="41" id="tajuk_Dokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    </label></td>
  </tr>
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Dirujukkan Kepada</td>
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
  #if($user_Role == '(Support)Timb Ketua Pengarah TKPK')
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')         #end   </td>
  </tr>
  #else
  <tr>
    <td colspan="4" align="center" scope="row">
  #if($paparArahan == 'false')
    #if ($mode == 'View')
         <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
         <input name="cmdHapusDokumen" type="button" value="Hapus" onclick="hapusLogDokumenKPTG('$idLogDokumenKPTG')" />
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
    #end
   #end    </td>
  </tr>
  #end
</table>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="user_Role" id="user_Role" type="hidden" value="$!user_Role"/>
<input name="no_Rujukan_Dokumen" id="no_Rujukan_Dokumen" type="hidden" value="$!no_Rujukan_Dokumen"/>

<br/><br/>
</fieldset $hidden1>
#if($paparArahan == 'true')
    <fieldset>
 <legend>AGIHAN TUGASAN</legend>
 <input name="cmdTambahMinit" type="button" value="Tambah Agihan Tugasan" onclick = "tambahMinit('$idLogDokumenKPTG')"/>
<br><br>
#if($hidden1 == 'false')
<fieldset>
<strong>Seksyen Pertama</strong>
 #set ($PrevPegawaiMengarah = '')
 #foreach ($listMinitArahanPengarah1 in $SenaraiMinitArahanPengarah1)
  #if ($PrevPegawaiMengarah != $listMinitArahanPengarah1.pegawaiMengarah)
    <p><strong>Pegawai Yang Mengarah</strong> : <a href="javascript:edit_item1('$listMinitArahanPengarah1
.idPegawaiMengarah')" class="style1">$listMinitArahanPengarah1.pegawaiMengarah</a></p>
 <p><strong>Arahan</strong> : $listMinitArahanPengarah1.minitArahan    </p>
    <table width="100%" align="center" >
    <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="20%"><strong>No Rujukan Dokumen</strong></td>
    <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
    <td width="20%"><strong>Tarikh</strong></td>
  </tr>
    #set ($PrevPegawaiMengarah = $listMinitArahanPengarah1.pegawaiMengarah)
  #end
   #if ($listMinitArahanPengarah1.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listMinitArahanPengarah1.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listMinitArahanPengarah1.bil</td>
     	#if ($listMinitArahanPengarah1.bil != '') 
        <td class="$row">$!listMinitArahanPengarah1.noRujukanDokumen</a></td>
    	#else
        <td class="$row">$!listMinitArahanPengarah1.noRujukanDokumen</td>
		#end
    <td class="$row">$listMinitArahanPengarah1.pegawaiMenerima</td>
    <td class="$row">$listMinitArahanPengarah1.tarikh</td>
  </tr>
  #end
</table>
</fieldset>
#else
#end
<br>
#if($hidden2 == 'false')
<fieldset $hidden2>
<strong>Seksyen Kedua</strong>
 #set ($PrevPegawaiMengarah = '')
 #foreach ($listMinitArahanPengarah2 in $SenaraiMinitArahanPengarah2)
  #if ($PrevPegawaiMengarah != $listMinitArahanPengarah2.pegawaiMengarah)
    <p><strong>Pegawai Yang Mengarah</strong> : <a href="javascript:edit_item1('$listMinitArahanPengarah2
.idPegawaiMengarah')" class="style1">$listMinitArahanPengarah2.pegawaiMengarah</a></p>
 <p><strong>Arahan</strong> : $listMinitArahanPengarah2.minitArahan    </p>
    <table width="100%" align="center" >
    <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="20%"><strong>No Rujukan Dokumen</strong></td>
    <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
    <td width="20%"><strong>Tarikh</strong></td>
  </tr>
    #set ($PrevPegawaiMengarah = $listMinitArahanPengarah2.pegawaiMengarah)
  #end
   #if ($listMinitArahanPengarah2.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listMinitArahanPengarah2.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listMinitArahanPengarah2.bil</td>
     	#if ($listMinitArahanPengarah2.bil != '') 
        <td class="$row">$!listMinitArahanPengarah2.noRujukanDokumen</a></td>
    	#else
        <td class="$row">$!listMinitArahanPengarah2.noRujukanDokumen</td>
		#end
    <td class="$row">$listMinitArahanPengarah2.pegawaiMenerima</td>
    <td class="$row">$listMinitArahanPengarah2.tarikh</td>
  </tr>
  #end
</table>
</fieldset>
#else
#end
<br>
#if($hidden3 == 'false')
<fieldset $hidden3>
<strong>Seksyen Ketiga</strong>
 #set ($PrevPegawaiMengarah = '')
 #foreach ($listMinitArahanPengarah3 in $SenaraiMinitArahanPengarah3)
  #if ($PrevPegawaiMengarah != $listMinitArahanPengarah3.pegawaiMengarah)
    <p><strong>Pegawai Yang Mengarah</strong> : <a href="javascript:edit_item1('$listMinitArahanPengarah3
.idPegawaiMengarah')" class="style1">$listMinitArahanPengarah3.pegawaiMengarah</a></p>
 <p><strong>Arahan</strong> : $listMinitArahanPengarah3.minitArahan    </p>
    <table width="100%" align="center" >
    <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="20%"><strong>No Rujukan Dokumen</strong></td>
    <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
    <td width="20%"><strong>Tarikh</strong></td>
  </tr>
    #set ($PrevPegawaiMengarah = $listMinitArahanPengarah3.pegawaiMengarah)
  #end
   #if ($listMinitArahanPengarah3.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listMinitArahanPengarah3.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listMinitArahanPengarah3.bil</td>
     	#if ($listMinitArahanPengarah3.bil != '') 
        <td class="$row">$!listMinitArahanPengarah3.noRujukanDokumen</a></td>
    	#else
        <td class="$row">$!listMinitArahanPengarah3.noRujukanDokumen</td>
		#end
    <td class="$row">$listMinitArahanPengarah3.pegawaiMenerima</td>
    <td class="$row">$listMinitArahanPengarah3.tarikh</td>
  </tr>
  #end
</table>
</fieldset>
#else
#end
<br>
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenKeluarKPTG";
	document.${formName}.submit();
}

function tabLogDokumenDariSeksyenlain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenDariSeksyenLainKPTG&pagemode=2";
	document.${formName}.submit();
}

function simpanLogDokumen(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanLogDokumenMasukKPTG&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&tajuk_Dokumen="+escape(document.${formName}.tajuk_Dokumen.value)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&pengirim_Dokumen="+escape(document.${formName}.pengirim_Dokumen.value)+"&tarikh_Diterima_Dihantar="+document.${formName}.tarikh_Diterima_Dihantar.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socTKP="+document.${formName}.socTKP.value;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}

function tambahLogDokumenMasuk(){
	 
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenMasukKPTG";
	document.${formName}.submit();

}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=kemaskiniLogDokumenMasukKPTG";
	document.${formName}.submit();
}

function update1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=updateLogDokumenMasukKPTG";
	document.${formName}.submit();
}

function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=";
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tambahSeksyen1";
	document.${formName}.submit();

}
function hapusLogDokumenKPTG(){
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=hapusLogDokumenMasukKPTG&paparArahan=false";
	document.${formName}.submit();
}
</script>
