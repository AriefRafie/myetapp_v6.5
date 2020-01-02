#if ($result == "yes")

<div class=error>Fail telah wujud. Sila buat carian di Senarai Fail.</div>
#else

<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #0000FF}
.style43 {color: #000000}
.style46 {font-size: 9px; font-style: italic; color: #FF0000; }
-->
</style>
<body onLoad="ResetScrollPosition();">
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="tabFailSeksyenLama()">FAIL LAMA</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tabFailSeksyenBaru()">FAIL BARU</li>
    <li class="TabbedPanelsTab" tabindex="0">FAIL ARKIB</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent"></div>
	 <div class="TabbedPanelsContent">
     <fieldset>
			<legend>MAKLUMAT FAIL ARKIB</legend>
            #if($hapus == 'true')
  			<div class="success">Fail telah dihapus.</div>
 			#end
       	<table width="100%">
          <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">&nbsp;</td>
          <td valign="top" scope="row"><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
		 <tr>
          <td class="style40" scope="row"> <div align="center" class="style3 style1"></div></td>
          <td scope="row"><span class="style43">No Fail Arkib</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><label>
          	 #if($mode =='baru')
            <input name="txtNoFailArkib" type="text" id="txtNoFailArkib" value="$!noFailArkib" size="28" $disabledNoFail $readonlyNoFail/>
            <input type="button" name="cmdPilihFailArkib" id="cmdPilihFailArkib" value="Pilih Kod Arkib" onClick="pilihfailarkib()"/>            
            #elseif($mode =='papar' || $mode == '' || $mode == 'paparSemua' || $mode == 'kemaskini') $!noFailArkib #end
           
          </label></td>
        </tr>
        <tr>
          <td class="style40" scope="row"> <div align="center" class="style3 style1"></div></td>
          <td scope="row"><span class="style43">No Fail Lama</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
           #if($mode =='baru' || $mode == 'kemaskini')
          <input name="txtNoFailLama" type="text" id="txtNoFailLama" value="$!noFailLama" size="28" $disabledNoFail $readonlyNoFail/>
           #elseif($mode =='papar' || $mode == '' || $mode == 'paparSemua') $!noFailLama 
           #end
           #if($mode =='baru' || $mode == 'kemaskini')
          	
            <input type="button" name="cmdPilihFailLama" id="cmdPilihFailLama" value="Pilih No Fail Lama" onClick="pilihfaillama()"/>  
           
           #end
          </td>
        </tr>          
        <tr>
          <td width="2%" align="left" valign="top" class="style40" scope="row"><div align="center" class="style3 style1">
            <div align="center"></div>
          </div></td>
          <td width="27%" align="left" valign="top" scope="row"><div align="left" class="style43">Tajuk Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td width="70%">	
                	$!tajukFailC    			</td>
        </tr>
        <tr>
          <td class="style40" scope="row"><div align="center" class="style3 style1">
            <div align="center"></div>
          </div></td>
          <td scope="row"><div align="left" class="style43">#if($idNegeri == '16')Seksyen #else Seksyen / Negeri #end</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
           $!seksyen</td>
        </tr>
        <tr>
          <td class="style40" scope="row"></td>
          <td scope="row"><div align="left" class="style43">Urusan</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!namaUrusan</td>
        </tr>
       
            <tr>
              <td scope="row"><div align="center"></div></td>
              <td scope="row"><div align="left">Sub Urusan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>$!namaSuburusan</td>
            </tr>
            
          
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Sub-Sub Urusan</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$namaSubSuburusan</td>
        </tr>
         
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Sub Sub Suburusan</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$namaSubSubSuburusan</td>
        </tr>     
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Aktiviti</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
      <label>
      <input type="radio" name="radio" id="sorAktiviti" value="sorAktivitiTiada" onclick = "aktivitiTiada()" $radioCheck1 $readOnly class="$disable" $disable />
      Tiada</label> 
      <label>
      <input type="radio" name="radio" id="sorAktiviti" value="sorAktivitiAda" onclick = "aktivitiAda()" $radioCheck2 $readOnly  class="$disable" $disable/>
      Ada</label></td>
        </tr>
        <tr>
          <td class="style2" scope="row"><div align="center" class="style3 style1">
            <div align="center">*</div>
          </div></td>
          <td scope="row"><div align="left">Taraf Keselamatan</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
          #if($mode == 'baru')
           $selectTarafC
          #elseif ($mode == 'papar' || $mode == '' || $mode == 'paparSemua' || $mode == 'kemaskini') 
           $!taraf
          #end           </td>
        </tr>
        <tr>
          <td class="style2" scope="row"><div align="center" class="style3 style1">
            <div align="center">*</div>
          </div></td>
          <td scope="row"><div align="left">Status Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
           #if($mode == 'baru')
           $selectStatusC 
          #elseif ($mode == 'papar' || $mode == '' || $mode == 'paparSemua' || $mode == 'kemaskini') 
           $!status
          #end                   </td>
        </tr>
        <tr>
          <td class="style2" scope="row"><div align="center" class="style3 style1">
            <div align="center"></div>
          </div></td>
          <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
           #if($mode == 'baru' || $mode == 'kemaskini')
           $selectLokasiFailC 
          #elseif ($mode == 'papar' || $mode == '' || $mode == 'paparSemua') 
           $!lokasi
          #end          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Kabinet</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
          #if ($mode == 'baru' || $mode == 'kemaskini')
          <input name="txtKabinetC" type="text" id="txtKabinetC" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!kabinetC" size="44" $disabled $readonly/ />
          #elseif ($mode == 'papar' || $mode == '' || $mode == 'paparSemua')
          	$!kabinetC
          #end          </td>
        </tr>
        <tr>
          <td class="style2" scope="row"><div align="center" class="style3 style1">
            <div align="center">*</div>
          </div></td>
          <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!tarikhDaftarC</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td class="style2" scope="row"><div align="left"></div></td>
          <td valign="top" scope="row"><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" class="style2" scope="row"><span class="style6 style45 style69 style47 style5"><em><span class="style3 style1">Perhatian </span><span class="style1">:</span> Pastikan label bertanda <span class="style3 style1">*</span> wajib diisi.</em></span></td>
          </tr>
              <tr>    </tr>
    <tr>
    <td colspan="4" align="center"> #if($mode == 'baru')
 	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan()"/>
 	       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()"/>
 	       <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    
 	    #elseif($mode == 'papar')
 	    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini('$!idFail')"/>
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak('$!idFail')" />
 	    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus1('$!idFail')" />
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    
 	    
 	    #elseif($mode == 'kemaskini')
 	    <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="update1('$!idFail')"/>
 	    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batalView()"/>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    #else
 	    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>

 	    #end 	 </td>
  </tr>
      </table>    
	   </fieldset>
     </div>
  </div>
</div>
</fieldset>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="action1" id="action1" value="$action1" />
<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
<input type="hidden" name="modeAktiviti" id="modeAktiviti" value="$modeAktiviti"/>
<input type="hidden" name="command1" id="command1"/>
<input type="hidden" name="aktivitiValue" id="aktivitiValue" value="$aktivitiValue"/>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input type="hidden" id="ScrollX" name='ScrollX'  />
<input type="hidden" id="ScrollY" name='ScrollY'  />
<input type="hidden" name="id_FailArkib" id="id_FailArkib" value="$!id_FailArkib" /> 
<input type="hidden" name="id_FailLama" id="id_FailLama" value="$!id_FailLama" /> 
<input type="hidden" name="idUrusan" id="idUrusan" value="$idUrusan" /> 
<input type="hidden" name="idSuburusan" id="idSuburusan" value="$idSuburusan" /> 
<input type="hidden" name="idSubSuburusan" id="idSubSuburusan" value="$idSubSuburusan" /> 
<input type="hidden" name="idSubSubSuburusan" id="idSubSubSuburusan" value="$idSubSubSuburusan" /> 
<input type="hidden" name="txtTajukFailC" id="txtTajukFailC" value="$tajukFailC" /> 
<input type="hidden" name="id_pejabatjkptg" id="id_pejabatjkptg" value="$id_pejabatjkptg" />
#end

</body>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
</script>
<script>
function batalView(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=papar";
	document.${formName}.submit();
}

function batal(){

	document.${formName}.reset();
	document.${formName}.txtTajukFailC.value = "";
	document.${formName}.socLokasiFailC.value = "";
	document.${formName}.txtKabinetC.value = "";

	
	return;
	
}
function cetak(idFail) {
        var url = "../servlet/ekptg.report.pfd.PFDFail?reportType=PDF&KULITFAIL_ID="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}
function hapus1(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=hapus";
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}
function update1(idFail){
if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=updateFailSeksyenArkib";
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}

function kemaskini(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=kemaskiniFailArkib";
	document.${formName}.idFail.value = idFail;

	document.${formName}.submit();

}
function kembali(){

	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=";
	document.${formName}.submit();
}
function simpan(){
	
		
		if (document.${formName}.socTarafC.value == ""){
				alert('Sila masukkan " Taraf Keselamatan " terlebih dahulu.');
				document.${formName}.socTarafC.focus();
				return;
		}  
		

		
		if (document.${formName}.socStatusC.value == ""){
				alert('Sila masukkan " Status Fail " terlebih dahulu.');
				document.${formName}.socStatusC.focus();
				return;
		}  
		

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=simpanFailArkib";
	document.${formName}.submit();
}
function tabFailSeksyenLama(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenLama&modeAktiviti=";
	document.${formName}.submit();
}
function tabFailSeksyenBaru(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenBaru&modeAktiviti=1";
	document.${formName}.submit();
}

function tabFailSubjaket(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSubjaket";
	document.${formName}.submit();
}
function doChangeSeksyenC() {
	document.${formName}.action1.value = "tabFailSeksyenArkib";
 	doAjaxCall${formName}("doChangeSeksyenB");
}
function doChangeUrusanC() {
	document.${formName}.action1.value = "tabFailSeksyenArkib";
 	doAjaxCall${formName}("doChangeUrusanC","modeAktiviti=ada");
}
function doChangeSubUrusanC() {
	document.${formName}.action1.value = "tabFailSeksyenArkib";
 	doAjaxCall${formName}("doChangeSubUrusanC");
}
function aktivitiTiada(){
 	document.${formName}.modeAktiviti.value = "tiada"
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenArkib";
	document.${formName}.submit();

}
function aktivitiAda(){
	SaveScrollXY();        
 	document.${formName}.modeAktiviti.value = "ada";
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenArkib";
	document.${formName}.submit();
} 
function getSubUrusan(){
	
	
	SaveScrollXY();        
	document.${formName}.command1.value = "getSubUrusan";
	document.${formName}.action = "";
	document.${formName}.submit();
	
	
}
function getSubSubUrusan(){
	
	
	SaveScrollXY();        
	document.${formName}.command1.value = "getSubSubUrusan";
	document.${formName}.action = "";
	document.${formName}.submit();
	
	
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }


function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
   hidy = '$ScrollY';
                        
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
  }
function pilihfailarkib() {
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmPopupSenaraiFailArkib";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function pilihfaillama() {
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmPopupSenaraiFailAll";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihFailArkib(id_FailArkib) {
	document.${formName}.id_FailArkib.value = id_FailArkib;
	document.${formName}.submit();
	
}
function refreshFromPilihFailLama(id_FailLama) {
	document.${formName}.id_FailLama.value = id_FailLama;
	document.${formName}.submit();
	
}
</script>
