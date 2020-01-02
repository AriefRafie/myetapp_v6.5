<style type="text/css">
<!--
.style1 {color: #0033FF}
.style5 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style6 {
	color: #000000;
	font-style: italic;
}
-->
</style>


<input name="idFail" type="hidden" value="$idFail" />
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idLogDokumen" type="hidden" value="$idLogDokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtJumDok" type="hidden" id="txtJumDok" value = "$jumlah_Dokumen"/>
&nbsp;

<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenMasuk()">LOG DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenKeluar()" >LOG DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDariSeksyenLain()">LOG DOKUMEN DARI SEKSYEN LAIN</li>
    <li class="TabbedPanelsTab" tabindex="0">LOG DOKUMEN DALAMAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
<fieldset>
       <legend>MAKLUMAT LOG DOKUMEN DALAMAN</legend>
    <table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%"><select name="socJenisDokumen" id="socJenisDokumen" $readonly $disabled>
      
        
		#if ($jenis_Dokumen == '2')
        
        
      <option value="0">SILA PILIH</option>
      <option value="2" selected="selected">MEMO</option>
      <option value="3">E-MAIL</option>
      
       
        
        #elseif ($jenis_Dokumen == '3')
        
        
      <option value="0">SILA PILIH</option>
      <option value="2">MEMO</option>
      <option value="3" selected="selected">E-MAIL</option>
      
              
                
        #else
        
        
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="2">MEMO</option>
      <option value="3">E-MAIL</option>
      
              
        #end
      
      
      
    </select>    </td>
  </tr>
  #if($mode == 'New')
  #end

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $readOnly $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $readOnly $disabled/>
Laporan<input type="checkbox" name="c3" value="1" $checkedc3  $readOnly $disabled/>
CD</td>
  </tr>
 #if($mode == 'NewLog' )
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>#foreach($paparLampiranLogDokumenKeluar in $SenaraiListLampiranLogDokumenKeluar)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenKeluar.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenKeluar.id_lampiran')" class="style1">$paparLampiranLogDokumenKeluar.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
  </tr>
  #elseif($mode == 'New' )
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="txtLampiran" type="file" id="txtLampiran" size="50" /></td>
  </tr>
  #elseif($mode == 'Update' || $mode == 'PaparUpdate' || $mode == 'View')
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>#foreach($paparLampiranLogDokumenKeluarPapar in $SenaraiListLampiranLogDokumenKeluarPapar)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenKeluarPapar.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenKeluarPapar.id_lampiran')" class="style1">$paparLampiranLogDokumenKeluarPapar.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
  </tr>
    #else
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tag Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea id="tag_dokumen" name="tag_dokumen" cols="70" rows="6" $readOnly $disabled>$!tag_Dokumen</textarea>
    <font color="#ff0000"><i> Keyword</i> yang dicadangkan untuk carian</font>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
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
    <td><select name="socPegawai" id="socPegawai" $readOnly $disabled>
        
    			#if ($mode == 'New' || $mode == 'PaparUpdate')
                    
        <option value="" >SILA PILIH</option>
        
                #end
                  #foreach($listPegawaiKeluar in $SenaraiPegawai)
                  	#if($selectidorang == $listPegawaiKeluar.user_id)
                    
        <option value="$listPegawaiKeluar.user_id" selected="selected">$listPegawaiKeluar.user_name </option>
        
                    #else
                    
        <option value="$listPegawaiKeluar.user_id" >$listPegawaiKeluar.user_name </option>
        
                 	 #end
                  #end
        
      </select>      </td>
  </tr>

  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
     #if($readOnly == "")
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!tajuk_Dokumen</textarea>
       <script> 
								              	var area4 = new FCKeditor("tajuk_Dokumen");
												area4.ToolbarSet = 'PFD';
									      		area4.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area4.ReplaceTextarea();             	
								              </script>
      #else
       $!tajuk_Dokumen
      #end    </td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Nama Penerima</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="penerima_Dokumen" cols="70" rows="6" id="penerima_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!penerima_Dokumen</textarea></td>
  </tr>
    
  
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama PT Fail</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPT" id="socPT" $readOnlyTH $disabledTH>
    			#if ($mode == 'New' || $mode == 'PaparUpdate')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPT in $SenaraiPTFail)
                  	#if($selectPTFail == $listPT.user_id)
                    <option value="$listPT.user_id" selected="selected">$listPT.user_name </option>
                    #else
                    <option value="$listPT.user_id" >$listPT.user_name </option>
                  	#end
                  #end
        </select></td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')
         <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini('$idLogDokumen')"/>
         <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
	#end 
    #if($mode == 'NewLog')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
	#end 
    #if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update1()"/>
        <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'PaparUpdate')
    	<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
    #end	</td>
  </tr>
</table>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
</fieldset>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
//-->
function hapus(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=hapusLogDokumenKeluar&idLogDokumen="+id;
	document.${formName}.submit();
}
function update1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=updateLogDokumenKeluar";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=cari";
	document.${formName}.submit();
}
function kemaskini(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=kemaskiniLogDokumenKeluar&idLogDokumen="+id;
	document.${formName}.submit();
}
function tabLogDokumenMasuk(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenMasuk&pagemode=1";
	document.${formName}.submit();
}
function tabLogDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenKeluar";
	document.${formName}.submit();
}
function tabLogDokumenDariSeksyenLain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDariSeksyenLain";
	document.${formName}.submit();
}
function simpanLogDokumen(){
		
		               
                
    if (document.${formName}.socPT.value == ""){
		alert('Sila masukkan " Nama PT " terlebih dahulu.');
		document.${formName}.socPT.focus();
		return;
	}

	else{
		if ( !window.confirm("Anda Pasti?") ) return;

		document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=simpanLogDokumenDalaman";
		document.${formName}.submit();
	}
	
}
function updateLogDokumen(){

	if (document.${formName}.socJenisPenghantaran.value == ""){
		alert('Sila masukkan " Jenis Penghantaran " terlebih dahulu.');
		document.${formName}.socJenisPenghantaran.focus();
		return;
	}
	
	if (document.${formName}.tarikh_Dokumen_Dihantar.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Dihantar " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen_Dihantar.focus();
		return;
	}
		if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=updateLogDokumenKeluar";
	document.${formName}.submit();
}
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob2?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
</script>