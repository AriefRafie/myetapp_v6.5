
<body>
#if ($set_data == "no")
#foreach ($dataPeguam in $listPeguam)  
	#set ($namafirma = $dataPeguam.namaFirma)
	#set ($rujukfirma = $dataPeguam.noRujukanFirma)
	#set ($add1 = $dataPeguam.alamat1)
	#set ($add2 = $dataPeguam.alamat2)
	#set ($add3 = $dataPeguam.alamat3)
	#set ($bandar = $dataPeguam.bandar)
	#set ($poskod = $dataPeguam.poskod)
	#set ($negeriId = $dataPeguam.idnegeri)
	#set ($notel = $dataPeguam.noTel)
	#set ($email = $dataPeguam.emel)
	#set ($faxno = $dataPeguam.noFax)
	#set ($idpeguam = $dataPeguam.idPeguam)
#end
#end

#if ($KemaskiniPeguamStatus == 1)
	#set ($readmode = "readonly class=disabled")
	#set ($setmode = "disabled")
#else
	#set ($readmode = "")
	#set ($setmode = "")
#end
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
<table width="100%" border="0">

<tr>
<td>
<input type="hidden" name="hitButt">
<input type="hidden" name="mode">
<input type="hidden" name="action">
<input type="hidden" name="idPermohonan" value="$IdPermohonan">
<input type="hidden" name="simpanStatus" value="$SimpanStatus">
<input type="hidden" name="idPemohon" value="$IdPemohon" />
<input type="hidden" name="idPeguam" value="$idpeguam" />
<input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
<input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
<input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
<input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
</td>
</tr>
<tr>
<td>
</td>
</tr>
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HAview('2','0','0','0')" >HARTA ALIH</li>
      #set ($hidden = "")
      #if ($hidden == "0")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      #end
      #if($hideTabPengesahan != "hide")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
      #end
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
           
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="WarisView('0','2','0','0')">WARIS</li>
       
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
            
            #set ($hidden = "")
      		#if ($hidden == "0")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SaksiView('0','4','0','0')">SAKSI</li>
            #end
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemiutangView('0','5','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PenghutangView('0','6','0','0')">PENGHUTANG</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PeguamView('0','1','1','0')">PEGUAM</li>
                  
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent" >      </div>
                   <div class="TabbedPanelsContent" >
                   <table width="97%" border="0">
                    
  <tr>
    <td>
    <fieldset><legend>MAKLUMAT PEGUAM</legend>
   <table width="100%">
      <tr>
        <td width="50%" valign="top">
        
        <table width="100%">
            <tr>
              <td width="49%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Firma</div></td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="50%">
                <input name="txtNamaFirmaPeguam" type="text" id="txtNamaFirmaPeguam" size="34" maxlength="35" value="$!namafirma" style="text-transform:uppercase;" $readmode />              </td>
            </tr>
            <tr>
              <td><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;No Rujukan</div></td>
              <td><div align="center" class="style38">:</div></td>
              <td>
                <input name="txtNoRujukanFirma" type="text" id="txtNoRujukanFirma" size="34"  value="$!rujukfirma" style="text-transform:uppercase;" maxlength="34" $readmode />              </td>
            </tr>
            <tr>
              <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat</div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat1Peguam" type="text" id="txtAlamat1Peguam" size="34" value="$!add1" style="text-transform:uppercase;" maxlength="34" $readmode />
              </label></td>
            </tr>
            <tr>
              <td class="style38"><div align="right"><span class="style3"></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat2Peguam" type="text" id="txtAlamat2Peguam" size="34" value="$!add2" style="text-transform:uppercase;" maxlength="34" $readmode />
              </label></td>
            </tr>
            <tr>
              <td class="style38"><div align="right"><span class="style3"></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><input name="txtAlamat3Peguam" type="text" id="txtAlamat3Peguam" size="34" value="$!add3" style="text-transform:uppercase;" maxlength="34" $readmode /></td>
            </tr>
        </table></td>
        <td width="639"><table width="100%" border="0">
                                      <tr> 
                                        <td width="49%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                                        <td width="1%"><div align="center" class="style38">:</div></td>
                                        <td width="50%"><label> 
                                          <input name="txtPoskodPeguam" type="text" id="txtPoskodPeguam" size="5" maxlength="5" value="$!poskod" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode onBlur="validLength()"/>
                                          </label></td>
                                      </tr>
                                      <tr>
                                      <td class="style38"><div align="right" class="style38">Negeri</div></td>
                                      <td><div align="center" class="style38">:</div></td>
                                      <td>$selectNegeriTetap                                        </td>
                                    </tr>
                                    <tr>
                                      <td><div align="right" class="style38">Bandar</div></td>
                                      <td><div align="center" class="style38">:</div></td>
                                      <td>$selectBandarTetap</td>
                                    </tr>
                                      <tr> 
                                        <td><div align="right" class="style38">No 
                                            Telefon</div></td>
                                        <td><div align="center" class="style38">:</div></td>
                                        <td><label> 
                                          <input name="txtNomborTelefonPeguam" type="text" id="txtNomborTelefonPeguam" size="12" maxlength="12" value="$!notel" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode />
                                        &nbsp;<span class="style52"><i>format : 031234567</i></span> </label></td>
                                      </tr>
                                      <tr> 
                                        <td><div align="right" class="style38">No 
                                            Faks</div></td>
                                        <td><div align="center" class="style38">:</div></td>
                                        <td><label> 
                                          <input name="txtNomborFaksPeguam" type="text" id="txtNomborFaksPeguam" size="12" maxlength="12" value="$!faxno" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode />
                                        &nbsp;<span class="style52"><i>format : 0121234567</i></span> </label></td>
                                      </tr>
                                      <tr> 
                                        <td><div align="right" class="style38"> 
                                            <div align="center" class="style38"> 
                                              <div align="right">Emel</div>
                                            </div>
                                          </div></td>
                                        <td><div align="center" class="style38">:</div></td>
                                        <td><label> 
                                          <input name="txtEmelPeguam" type="text" id="txtEmelPeguam" size="34" maxlength="30" value="$!email" $readmode />
                                          </label></td>
                                      </tr>
                                    </table>        </td>
      </tr>
	  <tr>
	                              <td colspan="2" valign="bottom">					</td>
	  </tr>
	  <tr>
	    <td colspan="2"valign="bottom"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i></td>
	    </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  <tr>
    <td></td>
  </tr>
  <tr>
    <td align="center">
     <table width="100%" border="0" align="center">
        <tr>
          <td align="center">
     		#if ($idstatus=="150" || $idstatus=="171")
                #if ($KemaskiniPeguamStatus == 0)
                    <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onClick="simpan_peguam('0','1','1','0')"/>
                    <input type="reset" name="cmdBatal" id="cmdBatal" value="Batal" onClick="PeguamView('0','1','1','0')"/>
                #else
                    #if ($buttonSimpan == "yes")
                    <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onClick="simpan_peguam('0','1','1','0')"/>
                    #end
                    #if ($buttonKemaskini == "yes")
                    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini_peguam('0','1','1','0')"/>
                    #end
                    <input type="button" name="cmdPadam" id="cmdPadam" value="Hapus" onClick="hapus_peguam('0','1','1','0')"/>
                    <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="PeguamView('0','1','1','0')"/>
                #end
            #end
            </td>
        </tr>
      </table>
      
      <br>
      <fieldset><legend>SENARAI PEGUAM</legend>
      <table width="100%">
      <tr class="table_header">
      <td width="10%">NO</td>
      <td width="20%">N0 RUJUKAN</td>
	  <td width="70%">NAMA FIRMA</td>
      </tr>
      #set($peno=0)
      #if ($cntList != "0")
	      #foreach ($list in $ListPeguam)
	      #set($peno=$peno+1)
	      #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
	      <tr>
	      <td class="$row" width="10%" style="text-transform:uppercase;">$!peno</td>
	      <td class="$row" width="20%" style="text-transform:uppercase;">$!list.noRujukanFirma</td>
		  <td class="$row" width="70%" style="text-transform:uppercase;"><a href="javascript:edit_item('$list.idPeguam')"><span class="style40">$!list.namaPeguam</span></a></td>
	      </tr>
	      #end
      #else
      <tr bgcolor="#FFFFFF">
      <td colspan="3">Tiada Rekod</td>
      </tr>
      #end
      </table>
      </fieldset>
      </td>
  </tr>
</table>

 </div>
                </div>
              </div>
            </div>
            
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContentVisible">
        <div id="TabbedPanels4" class="TabbedPanelsContentVisible">
          
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    </div>
  </div>    </td>
  </tr>
</table>
</fieldset>
</fieldset>
<script>
function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value.value="";
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";	
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value = tabIdatas;
    document.${formName}.tabIdtengah.value = tabIdtengah;
    document.${formName}.tabIdbawah.value = tabIdbawah;	
	document.${formName}.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.${formName}.reset();
document.${formName}.txtNoKPBaru1Waris.focus();
}
}
<!-- PEGUAM -->
function kemaskini_peguam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="POST";
	document.${formName}.mode.value = "kemaskini_peguam";
	doAjaxCall${formName}("Peguam");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
}

function BatalPeguam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="POST";
	document.${formName}.mode.value = "batal_peguam";
	doAjaxCall${formName}("Peguam");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
	}
	
}
function simpan_peguam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmelPeguam.value;	

	if (document.${formName}.txtNamaFirmaPeguam.value == ""){
		alert("Sila masukkan Nama Firma");
		txtNamaFirmaPeguam.focus();
	}
	else if (document.${formName}.txtNoRujukanFirma.value == ""){
		alert("Sila masukkan No Rujukan Firma");
		txtNoRujukanFirma.focus();
	}
	else if (document.${formName}.txtPoskodPeguam.value!="" && document.${formName}.txtPoskodPeguam.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPeguam.focus();
	}
	else if(!em.match(emailExp) && em!=""){
		alert("Alamat email tidak sah!");		
		document.${formName}.txtEmelPeguam.focus();
	}
	else{
			input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
			document.${formName}.method="POST";
			doAjaxCall${formName}("Peguam");
			document.${formName}.mode.value = "simpan_peguam";
			document.${formName}.tabIdatas.value = tabIdatas;
			document.${formName}.tabIdtengah.value = tabIdtengah;
			document.${formName}.tabIdbawah.value = tabIdbawah;
			document.${formName}.tabIdtepi.value = tabIdtepi;
			document.${formName}.action.value = "";
			document.${formName}.submit();
			}
	}
}

function validLength(){
	if (document.${formName}.txtPoskodPeguam.value!="" && document.${formName}.txtPoskodPeguam.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPeguam.focus();
	}
}

function PeguamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="POST";
	document.${formName}.mode.value = "Peguamview";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	doAjaxCall${formName}("Peguam");
	document.${formName}.submit();
}

function onChangeBandarTetap(){
	document.${formName}.method="post";
	doAjaxCall${formName}("Peguam");
	document.${formName}.mode.value = "onChangeBandarTetap";
	document.${formName}.tabIdatas.value = 0;
	document.${formName}.tabIdtengah.value = 1;
	document.${formName}.tabIdbawah.value = 1;
	document.${formName}.tabIdtepi.value = 0;
	document.${formName}.submit();
}

function edit_item(val){
	document.${formName}.method="post";
	document.${formName}.idPeguam.value=val;
	document.${formName}.mode.value="edit_peguam";
	doAjaxCall${formName}("Peguam");
	document.${formName}.tabIdatas.value="0";
	document.${formName}.tabIdtengah.value="1";
	document.${formName}.tabIdbawah.value="1";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function hapus_peguam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="hapus_peguam";
	doAjaxCall${formName}("Peguam");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
</script>
<script type="text/javascript">
var TabbedPanels1=new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2=new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3=new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4=new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});
</script>
</body>

