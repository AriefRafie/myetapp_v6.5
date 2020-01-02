<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<form action="" method="get">
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Permohonan</font></strong></li>
    <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Harta Tak Alih</font></strong></li>
    <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Harta Alih</font></strong></li>
    <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Nilaian Harta</font></strong></li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="1" ><strong><font size="1">Simati</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Pemohon</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Waris</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Orang Berkepentingan</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Saksi</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Pemiutang</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Penghutang</font></strong></li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          
            <table height="425" width="100%"
 border="0"
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;">
              <tbody>
              #set ($id = "")
    #foreach($Simati in $listSimati)
    #set ($id = $Simati.idPermohonan)
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No. KP Baru</strong></td>
                  <td style="width: 330px;"><input name="txtNoKpBaru1" id="txtNoKpBaru1" style="width: 80px;" type="text" value="$Simati.noKpBaru1" />
                    -
                    <input name="txtNoKpBaru2" id="txtNoKpBaru2" style="width: 30px;" type="text"  value="$Simati.noKpBaru2"/>
                    -
                  <input name="txtNoKpBaru3" id="txtNoKpBaru3"  style="width: 60px;" type="text"  value="$Simati.noKpBaru3"/></td><td style="width: 150px;"><strong>No. Sijil Mati</strong></td>
                  <td style="width: 338px;"><input type="text" name="txtNoSijil" id="txtNoSijil" value="$Simati.noSijilMati" /></td>
                </tr>
                <tr>
                  <td style="" scope="row"><strong>No. KP Lama </strong></td>
                  <td><input
 name="txtNoKpLama" id="txtNoKpLama" readonly="readonly" type="text"  width="200px"  value="$Simati.noKpLama" /></td>
                  <td style="width: 150px;"><strong>Tarikh Mati</strong></td>
                  <td style="width: 338px;"><input
 name="txdTarikhMati" id="txdTarikhMati" type="text" value="$Simati.tarikhMati" />
                    <input
 name="button5" id="button5" value="Kalendar"
 onclick="displayDatePicker('txdTarikhMati',false,'dmy');"
 type="submit" /></td>
                </tr>
                <tr>
                  <td><strong>Lain - lain KP</strong></td>
                  <td><select name="socJenisKp" id="socSeksyen7"
 style="width: 150px;">
                    <option>&lt;-- Pilih --&gt; </option>
                    <option value="1">NO. PASPORT</option>
                    <option value="2">NO. TENTERA</option>
                    <option value="3">NO. POLIS</option>
                  </select>
                    <input name="txtNoKpLain" id="txtNoKpLain" style="width: 120px;" type="text"  value="$Simati.noKpLain"/></td>
                  <td><span style="width: 150px;"><strong>Waktu Kematian</strong></span></td>
                  <td><span style="width: 338px;">
                    <input type="text" name="txtWaktuMati" id="txtWaktuMati" width="60%" value="$Simati.masaMati" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Nama Simati</strong></td>
                  <td><input type="text" name="txtNamaSimati" id="txtNamaSimati" width="300px" value="$Simati.namaSimati"/></td>
                  <td><strong>Sebab Kematian</strong></td>
                  <td><label>
                    <input type="text" name="textfield11" id="textfield11" width="300px" value="$Simati.sebabMati" />
                  </label></td>
                </tr>
                <tr>
                  <td><strong>Nama Lain </strong></td>
                  <td><input type="text" name="txtNamaLain" id="txtNamaLain"  width="300px" value="$Simati.namaLain"/></td>
                  <td><strong>Tempat Mati</strong></td>
                  <td><textarea name="txtTempatMati" id="txtTempatMati" cols="45" rows="2" value="$Simati.tempatMati"></textarea></td>
                </tr>
                <tr>
                  <td><strong>Jantina</strong></td>
                  <td><select name="socJantina" id="socJantina">
                    <option value="1">LELAKI</option>
                    <option value="2">PEREMPUAN</option>
                   <!-- <option selected="selected">&lt;--Pilih--&gt;</option>-->
                    
                  </select></td>
                  <td><span style="width: 150px;"><strong>Alamat</strong></span></td>
                  <td><span style="width: 338px;">
                    <input
 name="txtAlamat1" id="txtAlamat1" type="text"  width="300px" value="$Simati.alamat1"/>
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Agama</strong></td>
                  <td><select name="socAgama" id="socSeksyen10">
                    <option>&lt;--Pilih--&gt;</option>
                    <option value="1">ISLAM</option>
                    <option value="0">BUKAN ISLAM</option>
                                    </select></td>
                  <td>&nbsp;</td>
                  <td><label><span style="width: 338px;">
                    <input
 name="txtAlamat2" id="txtAlamat2" type="text"  width="300px" value="$Simati.alamat2"/>
                  </span></label></td>
                </tr>
                <tr>
                  <td><strong>Warganegara</strong></td>
                  <td><select name="socWarga" id="socSeksyen11">
                    <option>&lt;--Pilih--&gt;</option>
                    <option value="1">WARGANEGARA</option>
                    <option value="0">BUKAN WARGANEGARA</option>
                  </select></td>
                  <td>&nbsp;</td>
                  <td><span style="width: 338px;">
                    <input
 name="txtAlamat3" id="txtAlamat3" type="text"  width="300px" value="$Simati.alamat3"/>
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Umur</strong></td>
                  <td><input type="text" name="txtUmur" id="txtUmur" width="30%"  value="$Simati.umur"/>                    
                  tahun</td>
                  <td><strong>Poskod</strong></td>
                  <td><input
 name="txtPoskod" id="txtPoskod"
 type="text"  value="$Simati.poskod"/></td>
                </tr>
                <tr>
                  <td><strong>Bukti Kematian</strong></td>
                  <td><select name="socSeksyen7" id="socSeksyen12"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Bandar</strong></td>
                  <td><span style="width: 338px;">
                    <input
 name="txtBandar" id="txtBandar" type="text"  width="300px" value="$Simati.bandar"/>
                  </span></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>Negeri</strong></td>
                  <td> <!--<select name="socSeksyen11" id="socSeksyen9">
                    <option>&lt;--Pilih--&gt;</option>
                  </select>-->$selectNegeri</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>Catatan</strong></td>
                  <td><label>
                    <textarea name="txtCatatan" id="textarea2" cols="45" rows="5" value="$Simati.catatan" ></textarea>
                  </label></td>
                </tr>
                #end
              </tbody> 
            </table>
            <table width="100%" border="0">
  <tr>
    <th scope="col"><label>
      <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" />
      <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" />
      <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" />
      <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
      <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" />
    </label></th>
  </tr>
</table>

          </div>
          <div class="TabbedPanelsContent">
              <div id="TabbedPanels5" class="TabbedPanels">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Pemohon </font></strong> </li>
                  <li class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Peguam </font></strong> </li>
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent">
                    <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 100%; height: 154px;"
 border="0">
                      <tbody>
 #set ($id = "")
    #foreach($Pemohon in $listPemohon)
    #set ($id = $Pemohon.idPermohonan)
                  <tr>
                          <td style="width: 150px;" scope="row"><strong>No. KP Baru</strong></td>
                          <td style="width: 330px;"><input name="txtNoKpBaru1" id="txtNoKpBaru1" style="width: 80px;" type="text"  value="$Pemohon.noKpBaru1"/>
                            
                            -
                            <input name="txtNoKpBaru2" id="txtNoKpBaru2" style="width: 30px;" type="text" value="$Pemohon.noKpBaru2"/>
                            -
                            <input name="txtNoKpBaru3" id="txtNoKpBaru3"  style="width: 60px;" type="text"  value="$Pemohon.noKpBaru3"/></td>
                          <td style="width: 150px;"><strong>Alamat</strong></td>
                          <td style="width: 338px;"><input type="text" name="txtAlamat1" id="txtAlamat1"  width="300px" value="$Pemohon.alamat1"/></td>
                        </tr>
                        <tr>
                          <td style="" scope="row"><strong>No. KP Lama</strong></td>
                          <td><label><span style="width: 330px;">
                            <select name="socJenisKp2" id="socSeksyen8"
 style="width: 150px;">
                              <option>&lt;-- Pilih --&gt; </option>
                              <option value="1">NO. PASPORT</option>
                              <option value="2">NO.TENTERA</option>
                              <option value="3">NO. POLIS</option>
                            </select>
                          </span>
                            <input type="text" name="txtNoKpLama" id="txtNoKpLama" value="$Pemohon.noKpLain"/>
                          </label></td>
                          <td style="width: 150px;">&nbsp;</td>
                          <td style="width: 338px;"><input type="text" name="txtAlamat2" id="txtAlamat2"  width="300px" value="$Pemohon.alamat2"/></td>
                        </tr>
                        <tr>
                          <td><strong>Lain - lain KP</strong></td>
                          <td><input type="text" name="txtNoKpLain" id="txtNoKpLain" value="$Pemohon.noKpLama"/></td>
                          <td>&nbsp;</td>
                          <td><input type="text" name="txtAlamat3" id="txtAlamat3"  width="300px" value="$Pemohon.alamat3"/></td>
                        </tr>
                        <tr>
                          <td><strong>Nama Pemohon</strong></td>
                          <td><input type="text" name="txtNamaPemohon" id="txtNamaPemohon"  width="300px" value="$Pemohon.namaPemohon"/></td>
                          <td><strong>Poskod </strong></td>
                          <td><span style="width: 330px;">
                            <input name="txtPoskod" id="txtPoskod" style="width: 80px;" type="text" value="$Pemohon.poskod"/>
                          </span></td>
                        </tr>
                        <tr>
                          <td><strong>Jantina</strong></td>
                          <td><select name="socJantina2" id="socJantina2"
 style="width: 150px;">
                            <option>&lt;-- Pilih --&gt; </option>
                            <option value="1" selected = "selected">LELAKI</option>
                            <option value="2">PEREMPUAN</option>
                          </select></td>
                          <td><strong>Bandar</strong></td>
                          <td><input type="text" name="txtBandar" id="txtBandar"  width="300px" value="$Pemohon.bandar"/></td>
                        </tr>
                        <tr>
                          <td><strong>Agama </strong></td>
                          <td><select name="socAgama2" id="socSeksyen20"
 style="width: 120px;">
                            <option>&lt;-- Pilih --&gt; </option>
                            <option value="1" selected = "selected">ISLAM</option>
                            <option value="0">BUKAN ISLAM</option>
                          </select></td>
                          <td><strong>Negeri</strong></td>
                          <td><!--<select name="socSeksyen14" id="socSeksyen22"
 style="width: 100px;">
                              <option> &lt;-- Pilih --&gt; </option>
                          </select>-->$selectNegeri</td>
                        </tr>
                        <tr>
                          <td><strong>Warganegara</strong></td>
                          <td><select name="socWarganegara" id="socSeksyen19"
 style="width: 170px;">
                            <option>&lt;-- Pilih --&gt; </option>
                            <option value="1" selected = "selected">WARGANEGARA</option>
                            <option value="0">BUKAN WARGANEGARA</option>
                                                    </select>                            </td>
                          <td><strong>No. Telefon</strong></td>
                          <td><input type="text" name="txtNoTel" id="txtNoTel" value="$Pemohon.noTel"/></td>
                        </tr>
                        <tr>
                          <td><strong>Taraf Kepentingan</strong></td>
                          <td><select name="socSeksyen15" id="socSeksyen23"
 style="width: 100px;">
                            <option> &lt;-- Pilih --&gt; </option>
                          </select></td>
                          <td><strong>No. Telefon Bimbit</strong></td>
                          <td><input type="text" name="txtNoTelBimbit" id="txtNoTelBimbit" value="$Pemohon.noHp"/></td>
                        </tr>
                        <tr>
                          <td><strong>Talian Persaudaraan</strong></td>
                          <td><select name="socSeksyen11" id="socSeksyen24"
 style="width: 100px;">
                            <option> &lt;-- Pilih --&gt; </option>
                          </select></td>
                          <td><strong>No. Faks</strong></td>
                          <td><input type="text" name="txtNoFaks" id="txtNoFaks" value="$Pemohon.noFax"/></td>
                        </tr>
                        <tr>
                          <td><strong>Umur</strong></td>
                          <td><span style="width: 330px;">
                            <input name="txtUmur" id="txtUmur" style="width: 30px;" type="text" value="$Pemohon.umur" />
                          </span>tahun</td>
                          <td><strong>Emel</strong></td>
                          <td><input type="text" name="txtEmel" id="txtEmel"  width="300px" value="$Pemohon.emel"/></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                          <td><strong>Catatan</strong></td>
                          <td><label>
                            <textarea name="txtCatatan" id="txtCatatan" cols="45" rows="5" value="$Pemohon.catatan"></textarea>
                          </label></td>
                        </tr>
                        #end
                      </tbody>
                    </table>
                    <table width="100%" border="0">
  <tr>
    <th scope="col"><label>
      <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" />
      <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" />
      <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" />
      <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
      <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" />
    </label></th>
  </tr>
</table>

                  </div>
                  <div class="TabbedPanelsContent">
                  <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;"
 border="0">
                <tbody>
                #set ($id2 = "")
    #foreach($Peguam in $listPeguam)
    #set ($id2 = $Peguam.idPemohon)
                  <tr>
                    <td style="width: 150px;" scope="row"><strong>Nama Firma</strong></td>
                    <td style="width: 330px;"><input
 name="txtNFirma" id="txtNamaFirma" type="text" width="300px"  value="$Peguam.namaFirma"/>
                    </td>
                    <td style="width: 150px;"><strong>Poskod</strong></td>
                    <td style="width: 338px;"><input
 name="txtPoskod" id="txtPoskod" type="text" value="$Peguam.poskod" />
                    </td>
                  </tr>
                  <tr>
                    <td style="" scope="row"><strong> No. Rujukan</strong></td>
                    <td><input
 name="txtNoRujukan" id="txtNoRujukan" type="text" value="$Peguam.noRujukanFirma" />
                    </td>
                    <td style="width: 150px;"><strong>Bandar</strong></td>
                    <td style="width: 338px;"><span style="width: 330px;">
                      <input
 name="txtBandar" id="txtBandar" type="text" width="300px"  value="$Peguam.bandar"/>
                    </span></td>
                  </tr>
                  <tr>
                    <td><strong>Alamat</strong></td>
                    <td><span style="width: 330px;">
                      <input
 name="txtAlamat1" id="txtAlamat1" type="text" width="300px"  value="$Peguam.alamat1"/>
                    </span></td>
                    <td><strong>Negeri</strong></td>
                    <td><!--<select name="socSeksyen12" id="socSeksyen18">
                      <option>&lt;--Pilih--&gt;</option>
                    </select>-->$selectNegeri</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td><span style="width: 330px;">
                      <input
 name="txtAlamat2" id="txtAlamat2" type="text" width="300px"  value="$Peguam.alamat2"/>
                    </span></td>
                    <td><strong>No Telefon</strong></td>
                    <td><input
 name="txtNoTel" id="txtNoTel" type="text" value="$Peguam.noTel"/>
                    </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td><span style="width: 330px;">
                      <input
 name="txtAlamat3" id="txtAlamat3" type="text" width="300px"  value="$Peguam.alamat3"/>
                    </span></td>
                    <td><strong>No. Faks</strong></td>
                    <td><input
 name="txtNoFax" id="txtNoFax" type="text" value="$Peguam.noFax"/></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><strong>Emel</strong></td>
                    <td><span style="width: 330px;">
                      <input name="txtEmel" id="txtEmel" type="text" width="300px" value="$Peguam.emel"/>
                    </span></td>
                  </tr>
                  #end
                </tbody>
              </table>
              <table width="100%" border="0">
  <tr>
    <th scope="col"><label>
      <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" />
      <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" />
      <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" />
      <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
      <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" />
    </label></th>
  </tr>
</table>

              </div>
                </div>
              </div>
              <div class="TabbedPanelsContentGroup"></div>
            </div>
          <div class="TabbedPanelsContent"><fieldset>
<legend>Senarai Waris</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah Waris" type="submit" onclick="TambahWaris()" ></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>
<table align="center" border="0" width="100%">
  <tbody>
    <tr>
      <td style="width: 72px;" scope="row"><strong>NoBil</strong></td>
      <td style="text-align: center; width: 213px;"><strong><big>Nama</big></strong></td>
      <td style="text-align: center; width: 372px;"><strong><big>No. KP/ No. KP
Lama/ No. KP Lain</big></strong></td>
      <td style="text-align: center; width: 112px;"><strong><big>Umur</big></strong></td>
      <td style="text-align: center; width: 230px;"><strong><big>Talian
Persaudaraan</big></strong></td>
      <td style="width: 166px;"><strong>Status</strong></td>
    </tr>
    #set ($id = "")
    #foreach($fail in $listWaris)
    #set ($id = $fail.idPermohonan)
    <tr>
      <td style="width: 72px;" scope="row"><a href="javascript:edit_item('$fail.id_Permohonan')" class="style1">$fail.bil</a></td>
      <td style="width: 213px;">$fail.nama_Ob</td>
      <td style="width: 372px;">$fail.no_Kp_Baru</td>
      <td style="width: 112px;">$fail.umur</td>
      <td style="width: 230px;">$fail.keterangan</td>
      <td style="width: 166px;">$fail.status_Hidup</td>
    </tr>
    #end
  </tbody>
</table>
</fieldset></div>
          <div class="TabbedPanelsContent">
            <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;"
 border="0">
              <tbody>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
                  <td style="width: 330px;"><input name="txtNoFail7" id="txtNoFail10" style="width: 80px;" type="text" />
                    -
                    <input name="txtNoFail7" id="txtNoFail10" style="width: 30px;" type="text" />
                    -
                    <input name="txtNoFail7" id="txtNoFail10"  style="width: 60px;" type="text" /></td>
                  <td style="width: 150px;"><strong>No.Surat Beranak</strong></td>
                  <td style="width: 338px;"><input
 name="txtNamaPemohon4" id="txtNamaPemohon5"
 type="text" /></td>
                </tr>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No. KP Lama</strong></td>
                  <td style="width: 330px;"><input
 name="txtNegeri3" id="txtNegeri3" type="text" /></td>
                  <td style="width: 150px;"><strong>Alamat</strong></td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail9" id="txtNoFail11" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="" scope="row"><strong>Lain- lain KP</strong></td>
                  <td><select name="socKpLain" id="socKpLain">
                      <option> &lt;--Pilih--&gt;</option>
                    </select>
                      <input name="txtNoFail7" id="txtNoFail12" style="width: 120px;" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail8" id="txtNoFail13" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Nama OB</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail7" id="txtNoFail14" type="text" width="300px" />
                  </span></td>
                  <td>&nbsp;</td>
                  <td><span style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail7" id="txtNoFail15" type="text" width="300px" />
                  </span></span></td>
                </tr>
                <tr>
                  <td><strong>Status OB</strong></td>
                  <td><select name="socSeksyen4" id="socSeksyen4"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Poskod </strong></td>
                  <td><input
 name="txtNamaPemohon4" id="txtNamaPemohon6"
 type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Taraf Kepentingan</strong></td>
                  <td><select name="socSeksyen5" id="socSeksyen5"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Bandar</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail7" id="txtNoFail16" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Jantina</strong></td>
                  <td><select name="socSeksyen3" id="socSeksyen3"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Negeri</strong></td>
                  <td><select name="socSeksyen6" id="socSeksyen6"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                </tr>
                <tr>
                  <td><strong>Agama </strong></td>
                  <td><select name="socSeksyen" id="socSeksyen"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>No. Telefon</strong></td>
                  <td><input
 name="txtNamaPemohon4" id="txtNamaPemohon7"  type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Warganegara</strong></td>
                  <td><select name="socSeksyen2" id="socSeksyen2"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>No. Telefon Bimbit</strong></td>
                  <td><input
 name="txtNamaSimati3" id="txtNamaSimati3" type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Tarikh Lahir</strong></td>
                  <td><input name="txtTarikhDaftar2" id="txtTarikhDaftar2" type="text" />
                    &nbsp;
                    <input name="button2" id="button2" value="Kalendar" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" type="submit" /></td>
                  <td><strong>Catatan</strong></td>
                  <td><textarea name="textarea" id="textarea" cols="45" rows="5"></textarea></td>
                </tr>
              </tbody>
            </table>
    <table width="100%" border="0">
  <tr>
    <th scope="col"><input name="Kemaskini" type="submit" value="Kemaskini" />
        <label>
        <input type="submit" name="button" id="button" value="Tambah" />
        <input type="submit" name="button3" id="button3" value="Simpan" />
        <input type="submit" name="button4" id="button4" value="Batal" />
        <input type="submit" name="button2" id="button2" value="Kembali" />
      </label></th>
  </tr>
</table> 
<br /> 
<fieldset>
<legend>Senarai Orang Berkepentingan</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <th style="width: 72px;" scope="row">No.
Bil</th>
      <td style="text-align: center; width: 213px;"><strong><big>Nama OB</big></strong></td>
      <td style="text-align: center; width: 372px;"><strong><big>No. KP/ No. KP
Lama/ No. KP Lain</big></strong></td>
      <th style="width: 166px;"><strong>Status OB</strong></th>
    </tr>
    <tr>
      <th style="width: 72px;" scope="row">&nbsp;</th>
      <td style="width: 213px;">&nbsp;</td>
      <td style="width: 372px;">&nbsp;</td>
      <td style="width: 166px;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>      
          </div>
          <div class="TabbedPanelsContent">
            <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 100%; height: 154px;"
 border="0">
              <tbody>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
                  <td style="width: 330px;"><input name="txtNoFail10" id="txtNoFail17" style="width: 80px;" type="text" />
                    -
                    <input name="txtNoFail10" id="txtNoFail17" style="width: 30px;" type="text" />
                    -
                    <input name="txtNoFail10" id="txtNoFail17"  style="width: 60px;" type="text" /></td>
                  <td style="width: 150px;"><strong>Alamat</strong></td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail10" id="txtNoFail18" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No. KP Lama/ No. Syarikat</strong></td>
                  <td style="width: 330px;"><input
 name="txtNegeri4" id="txtNegeri4" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail10" id="txtNoFail19" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="" scope="row"><strong>Lain- lain KP</strong></td>
                  <td><select name="socKpLain2" id="socKpLain2">
                      <option> &lt;--Pilih--&gt;</option>
                    </select>
                      <input name="txtNoFail10" id="txtNoFail20" style="width: 120px;" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail10" id="txtNoFail21" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Nama Saksi</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail10" id="txtNoFail22" type="text" width="300px" />
                  </span></td>
                  <td><strong>Poskod </strong></td>
                  <td><input
 name="txtNamaPemohon5" id="txtNamaPemohon8"
 type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Jantina</strong></td>
                  <td><select name="socSeksyen8" id="socSeksyen13"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Bandar</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail10" id="txtNoFail23" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Warganegara</strong></td>
                  <td><select name="socSeksyen8" id="socSeksyen14"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                  <td><strong>Negeri</strong></td>
                  <td><select name="socSeksyen8" id="socSeksyen15"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>No. Telefon</strong></td>
                  <td><input
 name="txtNamaPemohon5" id="txtNamaPemohon9"  type="text" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>No. Telefon Bimbit</strong></td>
                  <td><input
 name="txtNamaSimati4" id="txtNamaSimati4" type="text" /></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>Catatan</strong></td>
                  <td><textarea name="textarea3" id="textarea3" cols="45" rows="5"></textarea></td>
                </tr>
              </tbody>
            </table>
<table width="100%" border="0">
  <tr>
    <th scope="col"><input name="Kemaskini" type="submit" value="Kemaskini" />
        <label>
        <input type="submit" name="button" id="button" value="Tambah" />
        <input type="submit" name="button3" id="button3" value="Simpan" />
        <input type="submit" name="button4" id="button4" value="Batal" />
        <input type="submit" name="button2" id="button2" value="Kembali" />
      </label></th>
  </tr>
</table>
<br />
 <fieldset>
<legend>Senarai Saksi</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <th style="width: 72px;" scope="row">No.
Bil</th>
      <td style="text-align: center; width: 213px;"><strong><big>Nama Saksi</big></strong></td>
      <td style="text-align: center; width: 372px;"><strong><big>No. KP/ No. KP
Lama/ No. KP Lain</big></strong></td>
    </tr>
    <tr>
      <th style="width: 72px;" scope="row">&nbsp;</th>
      <td style="width: 213px;">&nbsp;</td>
      <td style="width: 372px;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>           
          </div>
          <div class="TabbedPanelsContent">
            <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;"
 border="0">
              <tbody>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
                  <td style="width: 330px;"><input name="txtNoFail11" id="txtNoFail24" style="width: 80px;" type="text" />
                    -
                    <input name="txtNoFail11" id="txtNoFail24" style="width: 30px;" type="text" />
                    -
                  <input name="txtNoFail11" id="txtNoFail24"  style="width: 60px;" type="text" /></td>
                  <td style="width: 150px;"><strong>Alamat</strong></td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail25" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No. Syarikat</strong></td>
                  <td style="width: 330px;"><input
 name="txtNegeri5" id="txtNegeri5" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail26" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="" scope="row"><strong>Lain- lain KP</strong></td>
                  <td><select name="socKpLain3" id="socKpLain3">
                      <option> &lt;--Pilih--&gt;</option>
                    </select>
                    <input name="txtNoFail11" id="txtNoFail27" style="width: 120px;" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail28" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>Nama Pemiutang</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail29" type="text" width="300px" />
                  </span></td>
                  <td><strong>Poskod </strong></td>
                  <td><input
 name="txtNamaPemohon6" id="txtNamaPemohon10"
 type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Nilai Hutang</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail30" type="text" width="200px" />
                  </span></td>
                  <td><strong>Bandar</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail31" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>No. Akaun</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail11" id="txtNoFail32" type="text" width="200px" />
                  </span></td>
                  <td><strong>Negeri</strong></td>
                  <td><select name="socSeksyen9" id="socSeksyen16"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>Butiran Hutang</strong></td>
                  <td><textarea name="textarea4" id="textarea4" cols="45" rows="5"></textarea></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><strong>Rekod</strong></td>
                  <td>&nbsp;</td>
                </tr>
              </tbody>
            </table>
            <table width="100%" border="0">
  <tr>
    <th scope="col"><input name="Kemaskini" type="submit" value="Kemaskini" />
        <label>
        <input type="submit" name="button" id="button" value="Tambah" />
        <input type="submit" name="button3" id="button3" value="Simpan" />
        <input type="submit" name="button4" id="button4" value="Batal" />
        <input type="submit" name="button2" id="button2" value="Kembali" />
      </label></th>
  </tr>
</table>
<br>
<fieldset>
<legend>Senarai Pemiutang</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <th style="width: 5%;" scope="row">No.
Bil</th>
      <td style="text-align: center; width: 20%;"><strong><big>Nama Pemiutang</big></strong></td>
      <td style="text-align: center; width: 15%;"><strong><big>No. KP/ No. KP
Lama/ No. KP Lain</big></strong></td>
      <td style="text-align: center; width: 10%;"><strong><big>No. Syarikat</big></strong></td>
      <td style="text-align: center; width: 10%;"><strong><big>No. Akaun</big></strong></td>
      <th style="width: 10%;">Nilai Hutang (RM)</th>
      <th style="width: 20%;"><strong>Butiran Hutang</strong></th>
    </tr>
    <tr>
      <th style="width: 5%;" scope="row">&nbsp;</th>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 15%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>
          </div>
          <div class="TabbedPanelsContent">
            <table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;"
 border="0">
              <tbody>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>Jenis Hutang</strong></td>
                  <td style="width: 330px;"><select name="socKpLain4" id="socKpLain4">
                      <option> &lt;--Pilih--&gt;</option>
                  </select></td>
                  <td style="width: 150px;"><strong>Alamat</strong></td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail33" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
                  <td style="width: 330px;"><input name="txtNoFail12" id="txtNoFail34" style="width: 80px;" type="text" />
                    -
                    <input name="txtNoFail12" id="txtNoFail34" style="width: 30px;" type="text" />
                    -
                  <input name="txtNoFail12" id="txtNoFail34"  style="width: 60px;" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail35" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="width: 150px;" scope="row"><strong>No. KP Lama / No. Syarikat</strong></td>
                  <td style="width: 330px;"><input
 name="txtNegeri6" id="txtNegeri6" type="text" /></td>
                  <td style="width: 150px;">&nbsp;</td>
                  <td style="width: 338px;"><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail36" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td style="" scope="row"><strong>Lain- lain KP</strong></td>
                  <td><select name="socKpLain4" id="socKpLain5">
                      <option> &lt;--Pilih--&gt;</option>
                    </select>
                    <input name="txtNoFail12" id="txtNoFail37" style="width: 120px;" type="text" /></td>
                  <td style="width: 150px;"><strong>Poskod </strong></td>
                  <td style="width: 338px;"><input
 name="txtNamaPemohon7" id="txtNamaPemohon11"
 type="text" /></td>
                </tr>
                <tr>
                  <td><strong>Nama Penghutang / Agensi</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail38" type="text" width="300px" />
                  </span></td>
                  <td><strong>Bandar</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail39" type="text" width="300px" />
                  </span></td>
                </tr>
                <tr>
                  <td><strong>No. Akaun</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail40" type="text" width="200px" />
                  </span></td>
                  <td><strong>Negeri</strong></td>
                  <td><select name="socSeksyen10" id="socSeksyen17"
 style="width: 100px;">
                      <option> &lt;-- Pilih --&gt; </option>
                  </select></td>
                </tr>
                <tr>
                  <td><strong>Jumlah Hutang (RM)</strong></td>
                  <td><span style="width: 330px;">
                    <input
 name="txtNoFail12" id="txtNoFail41" type="text" width="200px" />
                  </span></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td><strong>Butiran Hutang</strong></td>
                  <td><textarea name="textarea5" id="textarea5" cols="45" rows="5"></textarea></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </tbody>
            </table>
            <table width="100%" border="0">
  <tr>
    <th scope="col"><input name="Kemaskini" type="submit" value="Kemaskini" />
        <label>
        <input type="submit" name="button" id="button" value="Tambah" />
        <input type="submit" name="button3" id="button3" value="Simpan" />
        <input type="submit" name="button4" id="button4" value="Batal" />
        <input type="submit" name="button2" id="button2" value="Kembali" />
      </label></th>
  </tr>
</table>
<fieldset>
<legend>Senarai Pemiutang</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <td width="5%" style="width: 72px;" scope="row"><strong>No.
Bil</strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong><big>Nama Penghutang</big></strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong>Jenis Penghutang</strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong><big>No. KP Baru/ (No. KP
Lama/ No. Syarikat) / No. KP Lain</big></strong></td>
      <td width="10%" style="text-align: center; width: 10;"><strong><big>Jumlah Hutang</big></strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong><big>Butiran Hutang</big></strong></td>
    </tr>
    <tr>
      <td style="width: 10%;" scope="row">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>
          </div>
        </div>
      </div>
    </div>
    <div class="TabbedPanelsContent">
      <div id="TabbedPanels4" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0"><strong><font size="1">Harta Tak Alih (Ada Hakmilik)</font></strong></li>
          <li class="TabbedPanelsTab" tabindex="0"><strong><font size="1">Harta Tak Alih (Tiada Hakmilik)</font></strong></li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"><fieldset>
<legend>Senarai Harta Tak Alih (Ada Hakmilik)</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>
<table align="center" border="0" width="100%">
  <tbody>
    <tr>
      <td style="width: 39px;" scope="row"><strong>No.
Bil</strong></td>
      <td style="text-align: center; width: 85px;"><big><strong>Negeri</strong></big></td>
      <td style="text-align: center;"><big><strong>Daerah</strong></big></td>
      <td style="text-align: center;"><big><strong>Mukim</strong></big></td>
      <td style="text-align: center;"><big><strong>No. Hakmilik</strong></big></td>
      <td style="text-align: center;"><big><strong>No. Pt / No. Lot</strong></big></td>
      <td style="text-align: center;"><big><strong>Bahagian Simati (A)</strong></big></td>
      <td width="153"><strong>Bahagian Simati (B)</strong></td>
    </tr>
  #set ($id = "")
    #foreach($fail in $listHta)
    #set ($id = $fail.idPermohonan)   
    <tr>
      <td style="width: 39px;" scope="row"><a href="javascript:edit_item('$fail.id_Permohonan')" class="style1">$fail.bil</a></td>
      <td style="width: 85px;">$fail.nama_Negeri</td>
      <td>$fail.nama_Daerah</td>
      <td>$fail.nama_Daerah</td>
      <td>$fail.no_Hakmilik</td>
      <td>$fail.no_Pt</td>
      <td>$fail.ba_Simati</td>
      <td>$fail.bb_Simati</td>
    </tr>
    #end
  </tbody>
</table>
</fieldset></div>
          <div class="TabbedPanelsContent"><fieldset>
<legend>Senarai Harta Tak Alih
(Tiada Hakmilik)</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>
<table align="center" border="0" width="100%">
  <tbody>
    <tr>
      <td style="width: 39px;" scope="row"><strong>No.
Bil</strong></td>
      <td style="text-align: center; width: 85px;"><strong><big>Negeri</big></strong></td>
      <td style="text-align: center;"><strong><big>Daerah</big></strong></td>
      <td style="text-align: center;"><strong><big>Mukim</big></strong></td>
      <td style="text-align: center;"><strong><big>No. Perjanjian/ ROH</big></strong></td>
      <th width="218">Bahagian
Simati (A)</th>
      <th width="153">Bahagian
Simati (B)</th>
    </tr>
 #set ($id = "")
    #foreach($fail in $listHtath)
    #set ($id = $fail.idPermohonan)      
    <tr>
      <td style="width: 39px;" scope="row"><a href="javascript:edit_item('$fail.id_Permohonan')" class="style1">$fail.bil</a></td>
      <td style="width: 85px;">$fail.nama_Negeri</td>
      <td>$fail.nama_Daerah</td>
      <td>$fail.nama_Mukim</td>
      <td>$fail.no_Perjanjian</td>
      <td>$fail.ba_Simati</td>
      <td>$fail.bb_Simati</td>
    </tr>
    #end
  </tbody>
</table>
</fieldset></div>
        </div>
      </div>
    </div>
    <div class="TabbedPanelsContent"><fieldset>
<legend>Senarai Harta Alih </legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>
<table align="center" border="0" width="100%">
  <tbody>
    <tr>
      <td width="123" style="width: 39px;" scope="row">No.
Bil</td>
      <td width="618">Jenis Harta Alih</th>
      <td width="440">No Rujukan UPT / No Daftar / No Akaun / No Ahli</td>
    </tr>
     #set ($id = "")
    #foreach($fail in $listHa)
    #set ($id = $fail.idPermohonan)    
    <tr>
      <td style="width: 39px;" scope="row"><a href="javascript:edit_item('$fail.id_Permohonan')" class="style1">$fail.bil</a></td>
      <td>$fail.id_Jenisha</td>
      <td>$fail.no_Daftar</td>
    </tr>
    #end
  </tbody>
</table>
<p>&nbsp;</p>
</fieldset></div>
    <div class="TabbedPanelsContent"><fieldset>
<legend>Maklumat Harta Tak Alih</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <td width="5%" style="width: 72px;" scope="row"><strong>No.Bil</strong></td>
      <td width="10%" style="text-align: center; width: 10%;"><strong><big>Negeri</big></strong></td>
      <td width="10%" style="text-align: center; width: 10%;"><strong>Daerah </strong></td>
      <td width="10%" style="text-align: center; width: 10%;"><strong>Mukim</strong></td>
      <td width="15%" style="text-align: center; width: 20%;"><strong><big>No. Hakmilik / No. Perjanjian</big></strong></td>
      <td width="10%" style="text-align: center; width: 10%;"><strong><big>No. PT / No. Lot</big></strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong>Nilai HTA Tarikh Mohon</strong></td>
      <td width="20%" style="text-align: center; width: 20%;"><strong><big>Nilai HTA Tarikh Mati</big></strong></td>
    </tr>
    <tr>
      <td height="27" style="width: 10%;" scope="row">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
      <td style="width: 20%;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>
<table width="100%" border="0">
  <tr>
    <td scope="col" ><strong>Jumlah Nilai Harta Tak Alih(RM)</strong>  
      <label>
      <input type="text" name="textfield" id="textfield" />
      </label></td>
  </tr>
</table>
<br />
<fieldset>
<legend>Maklumat Harta Tak Alih</legend>
<label><input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="submit"></label><label><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit"></label>

<table align="center" border="1" width="100%">
  <tbody>
    <tr>
      <td width="5%" style="width: 72px;" scope="row"><strong>No.Bil</strong></td>
      <td width="15%" style="text-align: center; width: 15%;"><strong><big>Negeri</big></strong></td>
      <td width="15" style="text-align: center; width: 15%;"><strong>Daerah</strong></td>
      <td width="15" style="text-align: center; width: 15%;"><strong>Jenis Harta</strong></td>
      <td width="30" style="text-align: center; width: 30%;"><strong><big>Rujukan UPT / No. Daftar / No. Akaun / No. Ahli</big></strong></td>
      <td width="15" style="text-align: center; width: 15%;"><strong><big>No. Sijil</big></strong></td>
      <td width="10" style="text-align: center; width: 10%;"><strong>Nilai Tarikh Mohon</strong></td>
      <td width="10" style="text-align: center; width: 10%;"><strong>Nilai Tarikh Mati</strong></td>
    </tr>
    <tr>
      <td height="27" style="width: 5%;" scope="row">&nbsp;</td>
      <td style="width: 15%;">&nbsp;</td>
      <td style="width: 15%;">&nbsp;</td>
      <td style="width: 15%;">&nbsp;</td>
      <td style="width: 30%;">&nbsp;</td>
      <td style="width: 15%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
      <td style="width: 10%;">&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>
<table width="100%" border="0">
  <tr>
    <td height="26" scope="col" width="50%" ><strong>Jumlah Nilai Harta  Alih (RM)</strong>  
      <label>
      <input type="text" name="textfield" id="textfield" />
      </label></td>
    <td scope="col"  width="50%"><strong>Jumlah Nilai Harta Keseluruhan (RM)</strong> 
      <input type="text" name="textfield2" id="textfield2" /></td>
  </tr>
</table></div>
  </div>
</div>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2");
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4");
//-->
</script>
<input type="hidden" name="command" />
<input type="hidden" name="idPermohonan" value="$id" />
<input type="hidden" name="idPemohon" value="$id2" />
<input type="hidden" name="idOb" value="$id3" />
</form>
<script type="text/javascript">
<!--
var TabbedPanels5 = new Spry.Widget.TabbedPanels("TabbedPanels5");
//-->
</script>
<script>
function cancel() {
document.f1.reset();
document.f1.txtNoFail.value = "";
document.f1.txtNoFail.focus();
}

function search_data(){
	document.f1.command.value = "";
	//document.f1.nama_fail.value = key;
	document.f1.action = "";
	document.f1.submit();
}
function Pemohon(){
	document.f1.command.value = "pemohon";
	document.f1.action = "";
	document.f1.submit();
}
function Peguam(){
	document.f1.command.value = "peguam";
	document.f1.action = "";
	document.f1.submit();
}
function TambahWaris() {
	document.f1.command.value = "tambahWaris";
	document.f1.action = "";
	document.f1.submit();
}

function view_item(id){
	document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idSimati.value = id;
	document.f1.submit();
}

function Seterusnya(){
	document.f1.command.value = "seterusnya";
	document.f1.action = "";
	document.f1.submit();
}
</script>
