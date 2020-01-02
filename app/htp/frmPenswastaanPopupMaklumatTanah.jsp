<style type="text/css">
<!--

body {
text-align:center;
font-family:serif;
background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>MAKLUMAT TANAH</strong></legend>
            
        #foreach($beanMaklumatTanah in $BeanMaklumatTanah)
        	#set($idHakmilik = $beanMaklumatTanah.idHakmilik)
            #set($peganganHakmilik = $beanMaklumatTanah.peganganHakmilik)
			#set($jenisLot = $beanMaklumatTanah.jenisLot)
            #set($noLot = $beanMaklumatTanah.noLot)
            #set($luasLot = $beanMaklumatTanah.luasLot)
            #set($jenisHakmilik = $beanMaklumatTanah.jenisHakmilik)
            #set($noHakmilik = $beanMaklumatTanah.noHakmilik)
            #set($noWarta = $beanMaklumatTanah.noWarta)
            #set($tarikhWarta = $beanMaklumatTanah.tarikhWarta)             
            #set($mukim = $beanMaklumatTanah.mukim)
            #set($daerah = $beanMaklumatTanah.daerah)
            #set($negeri = $beanMaklumatTanah.negeri)            
            #set($kategoriTanah = $beanMaklumatTanah.kategoriTanah)
            #set($subKategoriTanah = $beanMaklumatTanah.subKategoriTanah)
            #set($syarat = $beanMaklumatTanah.syarat)
            #set($sekatan = $beanMaklumatTanah.sekatan)
            #set($kementerian = $beanMaklumatTanah.kementerian)
            #set($agensi = $beanMaklumatTanah.agensi)         
        #end
            
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="50%">
                          <table width="100%"  cellpadding="2" cellspacing="2" border="0">
                              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
                              <tr>
                                  <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                                  <td width="63%"><font color="blue">$peganganHakmilik</font><input type="hidden" name="actionPopup"/></td>
                            </tr>
                              <tr>
                                  <td align="right">JENIS LOT :</td>
                                  <td><font color="blue">$jenisLot</font></td>
                            </tr>
                              <tr>
                                  <td align="right">NO. LOT :</td>
                                  <td><font color="blue">$noLot</font></td>
                            </tr>
                            <tr>
                                  <td align="right">LUAS. LOT :</td>
                                  <td><font color="blue">$luasLot</font></td>
                            </tr>
                            <tr>
                                  <td align="right">JENIS HAKMILIK :</td>
                                  <td><font color="blue">$jenisHakmilik</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. HAKMILIK :</td>
                                  <td><font color="blue">$noHakmilik</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. WARTA :</td>
                                  <td><font color="blue">$noWarta</font></td>
                            </tr>
                            <tr>
                                  <td align="right">TARIKH WARTA :</td>
                                  <td><font color="blue">$tarikhWarta</font></td>
                            </tr>
                            <tr>
                                  <td align="right">MUKIM :</td>
                                  <td><font color="blue">$mukim</font></td>
                            </tr>
                            <tr>
                                  <td align="right">DAERAH :</td>
                                  <td><font color="blue">$daerah</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NEGERI :</td>
                                  <td><font color="blue">$negeri</font></td>
                            </tr>
                          </table>                      
                          </td>
                            <td width="50%">
                                <table width="100%"  cellpadding="2" cellspacing="2" border="0">
                                    <tr>
                                        <td width="37%" align="right">KATEGORI TANAH :</td>
                                        <td width="63%"><font color="blue">$kategoriTanah</font></td>
                                  </tr>
                                    <tr>
                                        <td align="right">SUBKATEGORI TANAH :</td>
                                        <td><font color="blue">$subKategoriTanah</font></td>
                                  </tr>	
                                    <tr>
                                        <td align="right">SYARAT NYATA :</td>
                                        <td rowspan="3" valign="top"><font color="blue">$syarat</font></td>
                                  </tr>
                                    <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">SEKATAN KEPENTINGAN :</td>
                                        <td rowspan="3" valign="top"><font color="blue">$sekatan</font></td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">KEMENTERIAN :</td>
                                        <td rowspan="2" valign="top"><font color="blue">$kementerian</font></td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">AGENSI :</td>
                                        <td><font color="blue">$agensi</font></td>
                                  </tr>
                                  #end
                            </table>                      
                        </td>
                    </tr>
            </table>        
        </fieldset>
    </td>
  </tr>
  <tr>
  	<td>
   	<fieldset><legend><strong>TINDAKAN LANJUT</strong></legend>
    
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="30%"><font color="#FF0000">*</font> Tindakan Lanjut</td>
        <td width="70%">: 
          <select name="socTindakanLanjut" id="socTindakanLanjut">
            <option value="">SILA PILIH</option>
            <option value="1">SERAHBALIK TANAH</option>
            <option value="10">SERAHBALIK TANAH SEBAHAGIAN</option>
           	<option value="2">PINDAHMILIK SEMUA</option>
            <option value="3">PINDAHMILIK SEBAHAGIAN</option>
            <option value="4">PAJAK SEMUA</option>
            <option value="5">PAJAK SEBAHAGIAN</option>
            <option value="6">TUKARGANTI</option>
            <option value="7">PERMOHONAN TANAH</option>
            <option value="8">PERMOHONAN RIZAB</option>
            <option value="9">PENAWARAN TANAH</option>
            <option value="11">PECAH SEMPADAN</option>
          </select>
          </td>
      </tr>
    </table>

    </fieldset>
    </td>
  </tr>
  <tr>
  	<td align="center">
    <input type="button" name="cmdPilih" id="cmdPilih" value="Simpan" onClick="simpanTanah('$idHakmilik')">
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>

<script>
function kembali() {	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function simpanTanah(idHakmilik) {
	if(document.${formName}.socTindakanLanjut.value == ""){
		alert('Sila pilih Tindakan Lanjut.');
		document.${formName}.socTindakanLanjut.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
		
	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.hitButton.value = "simpanHakmilik";
	document.${formName}.submit();
	window.opener.refreshFromPilihTanah();
	window.close();
}
</script>

