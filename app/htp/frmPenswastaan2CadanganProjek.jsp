<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idHakmilikUrusan" type="hidden" id="idHakmilikUrusan" value="$idHakmilikUrusan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
   #if ($idFail != '')
  <tr>
    <td>
    	#parse("app/htp/frmPenswastaan2Header.jsp")
    </td>
  </tr> 
  <tr>
    <td>&nbsp;</td>
  </tr>
   #if ($mode == 'newHakmilik' || $mode == 'viewHakmilik' || $mode == 'updateHakmilik')
   <tr>
    <td>
    
    <fieldset><legend><strong>MAKLUMAT HAKMILIK</strong></legend>
    
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($beanMaklumatHakmilik in $BeanMaklumatHakmilik)
      <tr>
        <td width="1%">&nbsp;<font color="#FF0000">*</font></td>
        <td width="28%">Negeri</td>
        <td width="1%">:</td>
        <td width="70%">$selectNegeri</td>
      </tr>
      <tr>
        <td>&nbsp;<font color="#FF0000">*</font></td>
        <td>Daerah</td>
        <td>:</td>
        <td>$selectDaerah</td>
      </tr>
      <tr>
        <td>&nbsp;<font color="#FF0000">*</font></td>
        <td>Bandar/Pekan/Mukim</td>
        <td>:</td>
        <td>$selectMukim</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Jenis Lot</td>
        <td>:</td>
        <td>$selectLot</td>
      </tr>
      <tr>
        <td>&nbsp;<font color="#FF0000">*</font></td>
        <td>No. Lot/PT</td>
        <td>:</td>
        <td><input type="text" name="txtNoLot" id="" $readonly class="$inputTextClass" value="$beanMaklumatHakmilik.noLot"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Jenis Hakmilik</td>
        <td>:</td>
        <td>$selectJenisHakmilik</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. Hakmilik</td>
        <td>:</td>
        <td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" $readonly class="$inputTextClass" value="$beanMaklumatHakmilik.noHakmilik"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. Warta</td>
        <td>:</td>
        <td><input type="text" name="txtNoWarta" id="txtNoWarta" $readonly class="$inputTextClass" value="$beanMaklumatHakmilik.noWarta"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Warta</td>
        <td>:</td>
        <td>
        <input type="text" name="tarikhWarta" id="tarikhWarta" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatHakmilik.tarikhWarta"/>
            #if ($mode != 'viewHakmilik')
            <a href="javascript:displayDatePicker('tarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Jenis Luas</td>
        <td>:</td>
        <td>$selectLuas</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Luas</td>
        <td>:</td>
        <td><input type="text" name="txtLuas" id="txtLuas" $readonly class="$inputTextClass" value="$beanMaklumatHakmilik.luas"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Kategori</td>
        <td>:</td>
        <td>$selectKategori</td>
      </tr>
      <tr>
        <td>&nbsp;<font color="#FF0000">*</font></td>
        <td>SubKategori</td>
        <td>:</td>
        <td>$selectSubkategori</td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Syarat Nyata</td>
        <td valign="top">:</td>
        <td valign="top"><textarea name="txtSyarat" id="txtSyarat" cols="50" rows="5" $readonly class="$inputTextClass">$beanMaklumatHakmilik.syarat</textarea></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Sekatan Kepentingan</td>
        <td valign="top">:</td>
        <td valign="top"><textarea name="txtSekatan" id="txtSekatan" cols="50" rows="5" $readonly class="$inputTextClass">$beanMaklumatHakmilik.sekatan</textarea></td>
      </tr>
      #end
      <tr>
        <td>&nbsp;</td>
        <td>Tindakan Lanjut</td>
        <td>:</td>
        <td>
        <select name="socTindakanLanjut" id="socTindakanLanjut" $socDisabled $socClass>
            <option>SILA PILIH</option>
            <option $selected1 value="1">SERAHBALIK TANAH</option>
            <option $selected10 value="10">SERAHBALIK TANAH SEBAHAGIAN</option>
            <option $selected2 value="2">PINDAHMILIK SEMUA</option>
            <option $selected3 value="3">PINDAHMILIK SEBAHAGIAN</option>
            <option $selected4 value="4">PAJAK SEMUA</option>
            <option $selected5 value="5">PAJAK SEBAHAGIAN</option>
            <option $selected6 value="6">TUKARGANTI</option>
            <option $selected7 value="7">PERMOHONAN TANAH</option>
            <option $selected8 value="8">PERMOHONAN RIZAB</option>
            <option $selected9 value="9">PENAWARAN TANAH</option>
            <option $selected11 value="11">PECAH SEMPADAN</option>
          </select>
    
          
        </td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">
        #if ($mode == 'newHakmilik')
           	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanHakmilik()" />
           	<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalHakmilik()" />
                #elseif ($mode == 'viewHakmilik')
            <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniHakmilik()" />
            <input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusHakmilik()" />
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalHakmilik()" />
                    
                #elseif ($mode == 'updateHakmilik')
            <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdateHakmilik()" />
            <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateHakmilik()" />
                #end
        </td>
        </tr>
    </table>

    </fieldset>
    
    </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI HAKMILIK</strong></legend>
    
    	<table align="center" width="100%">
        	#if ($mode == 'view')
            <tr>
              <td colspan="8" scope="row">
              	<input class="stylobutton" name="cmdDaftar2" type="button" value="Pilih Tanah" onclick="pilihTanah('$idPermohonan')" />                
              <input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onClick="daftarBaruHakmilik()">
              </td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil.</strong></td> 
              <td width="15%"><strong>Negeri</strong></td>
              <td width="15%"><strong>Daerah</strong></td>
              <td width="15%"><strong>Bandar/Pekan/Mukim</strong></td>                           
              <td width="10%"><strong>No. Hakmilik</strong></td>
              <td width="10%"><strong>No. Warta</strong></td>
              <td width="10%"><strong>No. Lot/PT</strong></td>
              <td width="15%"><strong>Tindakan Lanjut</strong></td>
            </tr>
          #set ($list = "")
          #if ($SenaraiHakmilik.size() > 0)
          #foreach ($list in $SenaraiHakmilik)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row" align="center"><a href="javascript:paparHakmilik('$list.idHakmilikUrusan')" >$list.bil.</a></td>
            <td class="$row"><a href="javascript:paparHakmilik('$list.idHakmilikUrusan')" class="style1">$list.negeri</a></td>
            <td class="$row">$list.daerah</td>
            <td class="$row">$list.mukim</td>
            <td class="$row">$list.noHakmilik</td> 
            <td class="$row">$list.noWarta</td>           
            <td class="$row">$list.lot</td>
            <td class="$row">
            	$list.tindakanLanjut
                  #if($list.tindakanLanjut == 'PAJAK SEMUA')
          			<a href="javascript:daftarPajakanSemua('$list.idHakmilikUrusan')" class="style1">.</a>
          		#end
            </td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
          </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
</table>

<script>
function pilihTanah(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.htp.FrmPenswastaanPopupSenaraiTanahView?idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah() {
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.submit();
	refreshAgain()
}
function refreshAgain() {
	document.${formName}.submit();
}

function doChangeNegeri(){
	if(document.${formName}.socNegeri.value=="0" || document.${formName}.socNegeri.value=="")
		return;
	doAjaxCall${formName}('doChangeNegeri');
}
function doChangeDaerah(){
	if(document.${formName}.socDaerah.value=="0" || document.${formName}.socDaerah.value=="")
		return;

	doAjaxCall${formName}('doChangeDaerah');
}
function doChangeKategori(){
	doAjaxCall${formName}('doChangeKategori');
}

function daftarBaruHakmilik(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "newHakmilik";
	doAjaxCall${formName}("");
}
function simpanHakmilik(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih Mukim.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan No. Lot/PT.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	if(document.${formName}.socSubkategori.value == ""){
		alert('Sila pilih Sub Kategori.');
  		document.${formName}.socSubkategori.focus(); 
		return; 
	}	
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveHakmilik";
	doAjaxCall${formName}("");
}
function batalHakmilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}
function kemaskiniHakmilik(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "updateHakmilik";
	doAjaxCall${formName}("");
}
function simpanUpdateHakmilik(){
	document.${formName}.mode.value = "viewHakmilik";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdateHakmilik";
	doAjaxCall${formName}("");
}
function batalUpdateHakmilik(){
	document.${formName}.mode.value = "viewHakmilik";
	document.${formName}.actionPenswastaan.value = "papar";
	doAjaxCall${formName}("");
}
function paparHakmilik(id){	
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.idHakmilikUrusan.value = id;
	document.${formName}.mode.value = "viewHakmilik";	
	document.${formName}.submit();
}
function hapusHakmilik(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.hitButton.value = "hapusHakmilik";
	doAjaxCall${formName}("");
}

function daftarPajakanSemua(idHakmilik){
	document.${formName}.action = "$EkptgUtil.getTabID("Pajakan",$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanSenaraiFailView&actionPajakan=daftarBaruSwasta&idHakmilik="+idHakmilik;
	document.${formName}.submit();
}
</script>
