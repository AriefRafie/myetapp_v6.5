<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>
</head>

<body>
<form name="f1">
 <table width="100%" border="0">
<tr>
<td>
<input type="hidden" name="command" value="">
 <input type="hidden" name="mode" value="">
 <input type="text" name="idPermohonan" value="$IdPermohonan">
 <input type="text" name="simpanStatus" value="$SimpanStatus">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/></td>
</tr>
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="HAview('2','0','0','0')" >HARTA ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PemohonView('0','1','0','0')">PEMOHON</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="WarisView('0','2','0','0')">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="SaksiView('0','4','0','0')">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PemiutangView('0','5','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="PenghutangView('0','6','0','0')">PENGHUTANG</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContentVisible"></div>
            
            <div class="TabbedPanelsContentVisible">
              <div id="TabbedPanels3" class="TabbedPanels">
              </div>
            </div>
            <div class="TabbedPanelsContentVisible">
            </div>
            <div class="TabbedPanelsContentVisible">
            </div>
            <div class="TabbedPanelsContentVisible">
            <table width="100%" border="0">
    <tr>
                                         <td>
                                          <fieldset>
                                          <legend>MAKLUMAT SAKSI</legend>
                                          
                                          <table width="100%" border="0">
                              <tr>
                               <td width="50%" valign="top"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$listob.idSimati" >   
                                   <input type="hidden" name="txtIdOb" value="$listob.idOb" >      
                                  <tr>
                                  <td width="29%" ><div align="right"><span class="style38">No KP Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                   
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru2Penting')" value="$listob.nokpbaru1" size="7" maxlength="6" $readmode/>
                                     -<input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(this,this.value,'txtNoKPBaru3Penting')" value="$listob.nokpbaru2" size="1" maxlength="2" $readmode/>
                                     -
                                     <input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onkeyup="javascript:validateIC(this,this.value,'txtNoKPBaru3Penting')" value="$listob.nokpbaru3" size="5" maxlength="4" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">No KP Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPentingU" type="text" id="textfield4" value="$listob.nokplama" size="34" maxlength="9" style="text-transform:uppercase;"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38">Lain-lain KP</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
									 <select name="socJenisKPLainPentingU"  class="mediumselect" $readmode id="socJenisKPLainPentingU">
                                    </select>
									 <label></label></td>
                                </tr>
                                  <tr>
                                    <td><div align="right"><span class="style38"> No KP Lain</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><span class="style36">
                                      <input name="txtNoKPLainPentingU" type="text" id="textfield5" value="$listob.nokplain" size="34" maxlength="9" $readmode  style="text-transform:uppercase;"  />
                                    </span></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><span class="style40">*</span><span class="style38">Nama Saksi</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPentingU" type="text" id="txtNamaOBPentingU" value="$listob.nama_Ob" size="34" style="text-transform:uppercase;"  $readmode/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                  <td><div align="right"><span class="style38">Jantina</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label> 
                                    <select name="socJantinaPentingU" id="select2" class="mediumselect" $readmode>
                                    </select>
                                    </label></td>
                                </tr>
                                         <tr>
                                  <td><div align="right"><span class="style38">Warganegara</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    
                                   
                                    <select name="socWarganegaraPentingU" id="select4" class="mediumselect" $readmode>
                                      
                                   #if($listob.warga=="1")
	                               
                                      
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listob.warga=="2")
	                               
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="1">Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listob.warga=="3")
	                               
                                      <option value="3">Pemastautin Tetap</option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                      <option value="">Sila Pilih Warganegara</option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                  
                                </table></td>
                                <td width="50%" valign="top"><table width="100%" border="0">
                                  <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38">Alamat Terakhir</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1PentingU" type="text" id="txtAlamatTerakhir1PentingU" value="$listob.alamat1" size="34"  $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2PentingU" type="text" id="txtAlamatTerakhir2PentingU" value="$listob.alamat2" size="34" $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3PentingU" type="text" id="txtAlamatTerakhir3PentingU" value="$listob.alamat3" size="34" $readmode style="text-transform:uppercase;"  /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPentingU" type="text" id="txtPoskodPentingU" value="$listob.poskod" size="15" maxlength="5" $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><label>
                                    <input name="txtBandarPentingU" type="text" id="txtBandarPentingU" value="$listob.bandar" size="34" $readmode style="text-transform:uppercase;" />
                                  </label></td>
                                </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri</div></td>
                                   
                                    <td><div align="right" class="style38">:</div></td>
	                               <td>
								<select> 
                                  
                                  </select>                                    </td>
</tr>
                                        
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">No Telepon</div></td>
                                          <td valign="top">:</td>
                                          <td><input name="txtNoTeleponPentingU" type="text" id="txtNoTeleponPentingU" value="$listob.noTel" size="34" $readmode style="text-transform:uppercase;" /></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan</div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtCatatanPentingU" cols="31"  rows="3"  $readmode id="txtCatatanPentingU" style="text-transform:uppercase;" >$listob.catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                                         
                                          </fieldset>
                                         
                                         
                                         </td>
                                       </tr>
                                       <tr>
                            <td width="100%">
                            
                                                     </td>
                          </tr>
                          <tr>
                            <td>  <table width="100%" border="0" align="center">
                                  <tr>
                                  <td align="center">
                              
                              <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onclick="setSelected(0,4,0,0);tambah_simpan_penting()"/>								
                              <input type="button" name="batalpenting" id="cmdSimpan3" value="Batal" onclick="setSelected(0,4,0,0);cancelwaris()"/>                                 
                                  
          <input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onclick="setSelected(0,4,0,0);tambah_penting()"/>
                              
                                      <input type="button" name="batalpentingupdate" id="cmdSimpan3" value="Batal" onclick="setSelected(0,4,0,0);batalpenting()"/>
                                      
                                     <input type="submit" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="setSelected(0,4,0,0);hapus_penting()" />
                                     
                                  
                                     
                                      <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali"  onclick="kembali_simati()" />
                                    
                                    </td>
                                  </tr>
                                  
                                </table></td>
                          </tr>

                          <tr>
                            <td>
                            <input type="hidden" name="idOb" value="" />
                            
                            <fieldset>
                            <legend>SENARAI SAKSI</legend>
                            
                         
                              <table width="100%" >
                                <tr>
                                  <td width="647"><label>
                                    <input type="button" name="cmdPapar" id="cmdPapar" value="Tambah" onclick="setSelected(0,4,0,0);tambah_saksi_baru()"/>
                                  </label>
                                    <label></label></td>
                                </tr>
                                
                               
          
      
                                
                                
                                <tr>
                                  <td>
                                   <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA SAKSI</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
                                    </tr>
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41">$peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div align="center" class="style42" > $listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >$listpenting.nokpbaru</div></td>
                                    </tr>
                                        <tr >
                                  
                                   <td class="row2"><div align="center" class="style41">$peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div align="center" class="style42"> $listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">$listpenting.nokpbaru</div></td>
                                 </tr>
                                  </table>
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">Tiada Rekod</div>
                                      </td>
                                      </tr>
                                   </table> 
                                     
                                                            </td>
                                </tr>
                              </table>  
                              </fieldset>                            </td>
                          </tr>
                    </table>
            </div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContentVisible">
        <div id="TabbedPanels4" class="TabbedPanels">
         
          <div class="TabbedPanelsContentGroup">
          
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContentVisible"></div>
      <div class="TabbedPanelsContentVisible"></div>
    </div>
  </div>    </td>
  </tr>
</table>
</form>

<script>


<!-- TABS -->
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function HtaamViewX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.mode.value = "HtaamviewX";
	document.f1.command.value = "HtaamX";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}

function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.f1.method="post";
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.method="post";
	document.f1.command.value = "pengesahan_permohonan";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.action = "";
	document.f1.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.f1.method="post";
	document.f1.tabIdatas.value = tabIdatas;
	document.f1.tabIdtengah.value = tabIdtengah;
	document.f1.tabIdbawah.value = tabIdbawah;
	document.f1.tabIdtepi.value = tabIdtepi;
	document.f1.command.value = "kembali_simati";
	document.f1.action = "";
	document.f1.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}
<!-- WARIS -->

function kemaskini_penting(){
	document.f1.mode.value = "kemaskini_saksi";
	document.f1.command.value = "Saksi";
	document.f1.action = "";
	document.f1.submit();
}


function simpan_penting(){
	document.f1.mode.value = "simpan_saksi";
	document.f1.command.value = "Saksi";
	document.f1.action = "";	
	document.f1.submit();	
}

function tambah_saksi_baru(){

    document.f1.mode.value = "tambah_saksi_baru";
	document.f1.command.value = "Saksi";	
	document.f1.action = "";
    document.f1.submit();

}

function hapus_penting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "hapus_saksi";
	document.f1.command.value = "Saksi";	
	document.f1.action = "";
	document.f1.submit();
	}
}
function batalpenting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    document.f1.mode.value = "batal_update_saksi";
	document.f1.command.value = "Saksi";	
	document.f1.action = "";
	document.f1.submit();
	}
}
function tambah_simpan_penting(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.f1.txtNamaOBPenting.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBPenting.focus(); 
			return; 
            }
		}
	document.f1.mode.value = "tambah_saksi";
	document.f1.command.value = "Saksi";
	document.f1.action = "";
	document.f1.submit();
		
	}

function tambah_penting(){
	if( document.f1.tambahpenting.value == "Simpan" ) 
	{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_saksi";
	document.f1.command.value = "Saksi";
	
	document.f1.action = "";
	document.f1.submit();
}
	
	}

	if( document.f1.tambahpenting.value == "Kemaskini" ) 
	{
	document.f1.mode.value = "KemaskiniSaksi";
	document.f1.command.value = "Saksi";	
	document.f1.action = "";
	document.f1.submit();
	
	}
}

function edit_item(idOb) 
{

document.f1.action = "";
	document.f1.mode.value = "GetSaksi";
	document.f1.command.value = "Saksi";
	document.f1.idOb.value = idOb;
	document.f1.submit();
}
function PengesahanView(){
	document.f1.method="post";
	document.f1.command.value = "pengesahan_permohonan";
	document.f1.action = "";
	document.f1.submit();
}

</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
</html>
