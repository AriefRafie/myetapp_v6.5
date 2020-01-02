<script src="../../../../testing/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../../../../testing/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />	
	<style type="text/css">
	<!--
	.style4 {color: #FF0000}
.style1 {color: #0000FF}
	-->
	</style>
    <style type="text/css">
<!--
.style2 {font-size: smaller}
.style3 {font-size: 11.5px}
.style4 {color: #FF0000}
-->
</style>

<input name="action" type="hidden" value="$action" />
<input name="idAkta" type="hidden" id="idAkta" value="$idAkta"/>
<input name="tabId" type="hidden" id="tabId" value="$tabId"/>
<input name="hitbutton" type="hidden" value="$hitbutton" />
&nbsp;
  <table width="100%" border="1" cellpadding="2">
    <tr>
      <td>
        <fieldset>
          <legend><strong>Maklumat Akta</strong></legend>
          <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta Asal</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>$noAkta</label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta Asal</td>
            <td scope="row">:</td>
            <td>$namaAkta</td>
          </tr>
          <!--
          <tr>
            <td scope="row">Seksyen / Urusetia</td>
            <td scope="row">:</td>
            <td>$seksyen</td>
          </tr>
          -->
          <tr>
            <td scope="row">No Fail</td>
            <td scope="row">:</td>
            <td>$noFail</td>
          </tr>
        </table>
        </fieldset>
      </td>
    </tr>
    <tr>
      <td>
        <div id="TabbedPanels1" class="TabbedPanels">
            <ul class="TabbedPanelsTabGroup">
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(0)";><strong>Akta</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(1)";><strong>Penggal</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(2)";><strong>Bahagian</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(3)";><strong>Bab</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(4)";><strong>Seksyen</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(5)";><strong>Subseksyen</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(6)";><strong>Para</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(7)";><strong>Subpara</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(8)";><strong>Jadual Para</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(9)";><strong>Jadual Subpara</strong></li>
              <li class="style3 TabbedPanelsTab" tabindex="0" onClick="javascript:setSelected(10)";><strong>Jadual Sub Subpara</strong></li>
            </ul>
            <div class="TabbedPanelsContentGroup">
              <div class="TabbedPanelsContent">
              <fieldset>
              <legend><strong>Maklumat Akta Pindaan</strong></legend>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="29%" scope="row"><span class="style4">*</span>No Akta Pindaan</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <input name="txtNoAktaPindaan" type="text" id="txtNoAktaPindaan" value="" />
            </label></td>
          </tr>
          <tr>
            <td scope="row"><span class="style4">*</span>Nama Akta Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNamaAktaPindaan" id="txtNamaAktaPindaan" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tarikh Kuatkuasa Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" size="10" />
              <a href="javascript:displayDatePicker('txtTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </label></td>
          </tr>
          <tr>
            <td scope="row">No Warta</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNoWarta" id="txtNoWarta" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tarikh Warta</td>
            <td scope="row">:</td>
            <td><label>
              <input name="txtTarikhWarta" type="text" id="txtTarikhWarta" size="10" />
              <a href="javascript:displayDatePicker('txtTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tarikh Perkenaan Di Raja</td>
            <td scope="row">:</td>
            <td><label>
              <input name="txtTarikhPerkenaanDiraja" type="text" id="txtTarikhPerkenaanDiraja" size="10" />
              <a href="javascript:displayDatePicker('txtTarikhPerkenaanDiraja',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tarikh Penyiaran Dalam Warta</td>
            <td scope="row">:</td>
            <td><label>
              <input name="txtTarikhPenyiaranDlmWarta" type="text" id="txtTarikhPenyiaranDlmWarta" size="10" />
              <a href="javascript:displayDatePicker('txtTarikhPenyiaranDlmWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Taraf Keselamatan Akta</td>
            <td scope="row">:</td>
            <td><label>
              <input type="radio" name="radio" id="sorTerbuka" value="sorTerbuka" $radioChecked1 $disabled />
              Terbuka
              <input type="radio" name="radio" id="sorSulit" value="sorSulit" $radioChecked2 $disabled />
              Sulit</label></td>
          </tr>
          <tr>
            <td height="20" scope="row">No Fail</td>
            <td scope="row">:</td>
            <td><label>$selectFail</label></td>
          </tr>
          
          <tr>
            <td scope="row">No Memorandum Jemaah Menteri</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNoMemorandumMenteri" id="txtNoMemorandumMenteri" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Keputusan Jemaah Menteri</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtKptsnJemaahMenteri" id="txtKptsnJemaahMenteri" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Keputusan Majlis Tanah Negara</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtKptsnMajlisTanah" id="txtKptsnMajlisTanah" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Catatan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtCatatan" id="txtCatatan" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Lampiran</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtLampiran" id="txtLampiran" />
              <input type="submit" name="cmdBrowse" id="cmdBrowse" value="Browse" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tarikh Daftar</td>
            <td scope="row">:</td>
            <td><label>
              <input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" size="10" />
              <a href="javascript:displayDatePicker('txtTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset><legend><strong>Senarai Pegawai</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="96%" scope="row">Nama Pegawai</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <th scope="row">&nbsp;</th>
          </tr>
        </table>
        </fieldset>
        </div>
              <div class="TabbedPanelsContent">
              <fieldset>
              <legend><strong>Maklumat Penggal</strong></legend>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="29%" scope="row">No Akta</td>
                    <td width="1%" scope="row">:</td>
                    <td width="70%"><label>
                      <select name="socNoAkta" id="socNoAkta">
                      </select>
                    </label></td>
                  </tr>
                  <tr>
                    <td scope="row">Nama Akta</td>
                    <td scope="row">:</td>
                    <td><label>
                      <select name="socNamaAkta" id="socNamaAkta">
                      </select>
                    </label></td>
                  </tr>
                  <tr>
                    <td scope="row">No Penggal</td>
                    <td scope="row">:</td>
                    <td><label>
                      <input type="text" name="txtNoPenggal" id="txtNoPenggal" />
                    </label></td>
                  </tr>
                  <tr>
                    <td scope="row">Tajuk Penggal</td>
                    <td scope="row">:</td>
                    <td><input type="text" name="txtTajukPenggal" id="txtTajukPenggal" /></td>
                  </tr>
                  <tr>
                    <td scope="row">Justifikasi Pindaan</td>
                    <td scope="row">:</td>
                    <td><label>
                      <input type="text" name="txtJustifikasiPenggal" id="txtJustifikasiPenggal" />
                    </label></td>
                  </tr>
                </table>
                </fieldset>
                <fieldset><legend><strong>Senarai Penggal</strong></legend>
                <p>
                  <label>
                  <input type="submit" name="cmdTambah2" id="cmdTambah2" value="Tambah" />
                  </label>
                </p>
                <table width="100%" cellspacing="0" cellpadding="2">
                  <tr class="table_header">
                    <td width="5%" scope="row">No</td>
                    <td width="47%">Nama Akta</td>
                    <td width="25%">No Penggal</td>
                    <td width="25%">Tajuk Penggal</td>
                  </tr>
                   #set ($penggal = '')
   				   #foreach ($penggal in $Penggal)
    			   #if ($penggal.no == '') 
                   #set ($row = 'row1')
    			   #elseif ($penggal.no % 2 != 0)
                   #set ($row = 'row1')
                   #else 
                   #set ($row = 'row2')
                   #end
                  <tr>
                     <td height="20" class="$row">$penggal.no</td>
                     #if($penggal.no != '')
					 <td height="20" class="$row"><a href="javascript:edit_itemPenggal('$penggal.idPenggal')" class="style1">$penggal.noPenggal</a></td>
                     #else
                     <td height="20" class="$row">$penggal.noPenggal</td>
                     #end
                     <td height="20" class="$row">$penggal.tajukPenggal</td>
                  </tr>
                  #end
                </table>
                </fieldset>
              </div>
              <div class="TabbedPanelsContent">
              <fieldset>
              <legend><strong>Maklumat Bahagian</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">No Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNoBahagian" id="txtNoBahagian" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tajuk Bahagian</td>
            <td scope="row">:</td>
            <td><input type="text" name="txtTajukBahagian" id="txtTajukBahagian" /></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiBahagian" id="txtJustifikasiBahagian" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset><legend><strong>Senarai Bahagian</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Penggal</td>
            <td width="16%">No Bahagian</td>
            <td width="19%">Tajuk Bahagian</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Bab</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBahagian" id="socBahagian">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">No Bab</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNoBab" id="txtNoBab" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Tajuk Bab</td>
            <td scope="row">:</td>
            <td><input type="text" name="txtTajukBab" id="txtTajukBab" /></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiBab" id="txtJustifikasiBab" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Bab</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="3%" scope="row">No</td>
            <td width="9%">No Akta</td>
            <td width="28%">Nama Akta</td>
            <td width="13%">Penggal</td>
            <td width="13%">Bahagian</td>
            <td width="13%">No Bab</td>
            <td width="21%">Tajuk Bab</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Seksyen</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBahagian" id="socBahagian">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bab</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBab" id="socBab">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Seksyen</td>
            <td scope="row">:</td>
            <td><input type="text" name="txtSeksyen2" id="txtSeksyen2" /></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiSeksyen" id="txtJustifikasiSeksyen" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Seksyen</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Penggal</td>
            <td width="16%">Bahagian</td>
            <td width="19%">Bab</td>
            <td width="19%">Seksyen</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Subseksyen</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBahagian" id="socBahagian">
              </select>
        
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bab</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBab" id="socBab">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Seksyen</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSeksyen" id="txtSeksyen" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Subseksyen</td>
            <td scope="row">:</td>
            <td><label>
            <input type="text" name="txtSubseksyen2" id="txtSubseksyen2" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiSubSeksyen" id="txtJustifikasiSubSeksyen" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Subseksyen</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Penggal</td>
            <td width="16%">Bahagian</td>
            <td width="19%">Bab</td>
            <td width="19%">Seksyen</td>
            <td width="19%">Subseksyen</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Para</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBahagian" id="socBahagian">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bab</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBab" id="socBab">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Seksyen</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSeksyen" id="txtSeksyen" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Subseksyen</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSubseksyen" id="txtSubseksyen" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Para</td>
            <td scope="row">:</td>
            <td scope="row"><label>
              <input type="text" name="txtPara" id="txtPara" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Jadual</td>
            <td scope="row">:</td>
            <td scope="row"><label>
              <input type="text" name="txtJadual" id="txtJadual" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiPara" id="txtJustifikasiPara" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Para</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Penggal</td>
            <td width="16%">Bahagian</td>
            <td width="19%">Bab</td>
            <td width="19%">Seksyen</td>
            <td width="19%">Subseksyen</td>
            <td width="19%">Para</td>
            <td width="19%">Jadual</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Subpara</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Penggal</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPenggal" id="socPenggal">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bahagian</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBahagian" id="socBahagian">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Bab</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socBab" id="socBab">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Seksyen</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSeksyen" id="txtSeksyen" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Subseksyen</td>
            <td scope="row">:</td>
            <td><label>
            <input type="text" name="txtSubseksyen" id="txtSubseksyen" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Para</td>
            <td scope="row">:</td>
            <td scope="row"><label>
              <input type="text" name="txtPara" id="txtPara" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Jadual</td>
            <td scope="row">:</td>
            <td scope="row"><label>
              <input type="text" name="txtJadual" id="txtJadual" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiSubpara" id="txtJustifikasiSubpara" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Subpara</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
        
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Penggal</td>
            <td width="16%">Bahagian</td>
            <td width="19%">Bab</td>
            <td width="19%">Seksyen</td>
            <td width="19%">Subseksyen</td>
            <td width="19%">Para</td>
            <td width="19%">Subpara</td>
            <td width="19%">Jadual</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Jadual Para</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Jadual</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socJadual" id="socJadual">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Jadual</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtNamaJadual" id="txtNamaJadual" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Para</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtPara" id="txtPara" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiJadualPara" id="txtJustifikasiJadualPara" />
            </label></td>
          </tr>          
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Jadual Par</strong>a</legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Jadual</td>
            <td width="16%">Nama Jadual</td>
            <td width="19%">Para</td>
            </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
        </table>
        </fieldset>
              </div>
              <div class="TabbedPanelsContent">
              <fieldset>
              <legend><strong>Maklumat Jadual Subpara</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Jadual</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socJadual" id="socJadual">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Para</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socJadualPara" id="socJadualPara">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Subpara</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSubpara" id="txtSubpara" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiJadualSubpara" id="txtJustifikasiJadualSubpara" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Jadual Subpara</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="4%" scope="row">No</td>
            <td width="11%">No Akta</td>
            <td width="34%">Nama Akta</td>
            <td width="16%">Jadual</td>
            <td width="16%">Para</td>
            <td width="19%">Subpara</td>
            </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
        </table>
        </fieldset>
              </div>
               <div class="TabbedPanelsContent">
               <fieldset>
               <legend><strong>Maklumat Jadual Sub Subpara</strong></legend>
              <table width="100%">
          <tr>
            <td width="29%" scope="row">No Akta</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <select name="socNoAkta2" id="socNoAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Akta</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socNamaAkta2" id="socNamaAkta2">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Jadual</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socJadual" id="socJadual">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Para</td>
            <td scope="row">:</td>
            <td><label>
              <select name="socPara" id="socPara">
              </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Subpara</td>
            <td scope="row">:</td>
            <td><label>
            <select name="socJadualSubpara" id="socJadualSubpara">
            </select>
            </label></td>
          </tr>
          <tr>
            <td scope="row">Sub Subpara</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtSubSubpara" id="txtSubSubpara" />
            </label></td>
          </tr>
           <tr>
            <td scope="row">Justifikasi Pindaan</td>
            <td scope="row">:</td>
            <td><label>
              <input type="text" name="txtJustifikasiJadualSubSubpara" id="txtJustifikasiJadualSubSubpara" />
            </label></td>
          </tr>
        </table>
        		</fieldset>
        <fieldset>
        <legend><strong>Senarai Jadual Sub Subpara</strong></legend>
        <p>
          <label>
          <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
          </label>
        </p>
        <table width="100%" cellspacing="0" cellpadding="2">
          <tr class="table_header">
            <td width="3%" scope="row">No</td>
            <td width="9%">No Akta</td>
            <td width="28%">Nama Akta</td>
            <td width="13%">Jadual</td>
            <td width="13%">Para</td>
            <td width="12%">Subpara</td>
            <td width="22%">Sub Subpara</td>
          </tr>
          <tr>
            <th scope="row">&nbsp;</th>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
              </div>
            </div>
        </div>
          
      </td>
    </tr>
    <tr align="center">
      <td>
 		<label>
            <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" />
        </label>
        <label>
            <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBaru()"/>
        </label>
        <label>
            <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
        </label>
         <label>
            <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" />
        </label>
          <label>
             <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" />
          </label>  
      </td>
    </tr>
  </table>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-05</strong></td>
  	</tr>
  </table>
  
  	<script type="text/javascript">
	<!--
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$tabId});
	//-->
	</script>
    
<script>
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;
	document.${formName}.submit();
}
function simpanBaru(){
	document.${formName}.action.value = "";
	document.${formName}.hitbutton.value = "simpan";
	document.${formName}.submit();
}
function edit_itemPenggal(id)
{
	document.${formName}.action.value = "papar";
	document.${formName}.idPenggal.value = id;
	document.${formName}.hitbutton.value = "paparPenggal";
	document.${formName}.submit();
}
</script>