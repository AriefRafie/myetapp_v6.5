<script src="../../../../testing/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../../../../testing/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
<style type="text/css">
<!--
.style1 {font-size: 10px}
.style4 {color: #FF0000}
-->
</style>
<form id="form1" name="form1" method="post" action="">
&nbsp;
  <table width="100%" border="1" cellpadding="2">
    <tr>
      <td>
        <fieldset>
          <legend><strong>Maklumat Enakmen</strong></legend>
          <table width="100%">
          <tr>
            <td width="29%" scope="row">No Enakmen Asal</td>
            <td width="1%" scope="row">:</td>
            <td width="70%"><label>
              <input type="hidden" name="hiddenField" id="hiddenField" />
            </label></td>
          </tr>
          <tr>
            <td scope="row">Nama Enakmen Asal</td>
            <td scope="row">:</td>
            <td><input type="hidden" name="hiddenField2" id="hiddenField2" /></td>
          </tr>
          <tr>
            <td scope="row">Seksyen / Urusetia</td>
            <td scope="row">:</td>
            <td><input type="hidden" name="hiddenField3" id="hiddenField3" /></td>
          </tr>
          <tr>
            <td scope="row">No Fail</td>
            <td scope="row">:</td>
            <td><input type="hidden" name="hiddenField4" id="hiddenField4" /></td>
          </tr>
        </table>
        </fieldset>
      </td>
    </tr>
    <tr>
      <td>
        <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Enakmen</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Penggal</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Bahagian</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Bab</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Seksyen</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Subseksyen</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Para</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Subpara</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Jadual Para</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Jadual Subpara</strong></li>
          <li class="style1 TabbedPanelsTab" tabindex="0"><strong>Jadual Sub Subpara</strong></li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          <fieldset>
          <legend><strong>Maklumat Enakmen Pindaan</strong></legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="29%" scope="row"><span class="style4">*</span>No Enakmen Pindaan</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <input type="text" name="txtNoEnakmenPindaan" id="txtNoEnakmenPindaan" />
        </label></td>
      </tr>
      <tr>
        <td scope="row"><span class="style4">*</span>Nama Enakmen Pindaan</td>
        <td scope="row">:</td>
        <td><label>
          <input type="text" name="txtNamaEnakmenPindaan" id="txtNamaEnakmenPindaan" />
        </label></td>
      </tr>
      <tr>
        <td scope="row">Tarikh Kuatkuasa Pindaan</td>
        <td scope="row">:</td>
        <td><label>
          <input name="txtTarikhKuatkuasa" type="text" id="txtTarikhKuatkuasa" size="10" />
          <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
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
          <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Tarikh Perkenaan Di Raja</td>
        <td scope="row">:</td>
        <td><label>
          <input name="txtTarikhPerkenaanDiraja" type="text" id="txtTarikhPerkenaanDiraja" size="10" />
          <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Tarikh Penyiaran Dalam Warta</td>
        <td scope="row">:</td>
        <td><label>
          <input name="txtTarikhPenyiaranDlmWarta" type="text" id="txtTarikhPenyiaranDlmWarta" size="10" />
          <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Taraf Keselamatan Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <input type="radio" name="radio" id="sorTerbuka" value="sorTerbuka" />
          Terbuka
          <input type="radio" name="radio" id="sorSulit" value="sorSulit" />
          Sulit</label></td>
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
          <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
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
                <td width="29%" scope="row">No Enakmen</td>
                <td width="1%" scope="row">:</td>
                <td width="70%"><label>
                  <select name="socNoAkta" id="socNoAkta">
                  </select>
                </label></td>
              </tr>
              <tr>
                <td scope="row">Nama Enakmen</td>
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
                <td><label>
                  <input type="text" name="txtTajukPenggal" id="txtTajukPenggal" />
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
                <td width="3%" scope="row">No</td>
                <td width="21%">No Enakmen</td>
                <td width="29%">Nama Enakmen</td>
                <td width="28%">No Penggal</td>
                <td width="19%">Tajuk Penggal</td>
              </tr>
              <tr>
                <th scope="row">&nbsp;</th>
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
          <legend><strong>Maklumat Bahagian</strong></legend>
          <table width="100%">
      <tr>
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td><label>
          <input type="text" name="txtTajukBahagian" id="txtTajukBahagian" />
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
        <td width="11%">No Enakmen</td>
        <td width="34%">Nama Enakmen</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoAkta2" id="socNoAkta2">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
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
        <td><label>
          <input type="text" name="txtTajukBab" id="txtTajukBab" />
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
        <td width="9%">No Enakmen</td>
        <td width="28%">Nama Enakmen</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="11%">No Enakmen</td>
        <td width="34%">Nama Enakmen</td>
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
           <legend>Maklumat Subseksyen</legend>
          <table width="100%">
      <tr>
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="11%">No Enakmen</td>
        <td width="34%">Nama Enakmen</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="2%" scope="row">No</td>
        <td width="8%">No Enakmen</td>
        <td width="17%">Nama Enakmen</td>
        <td width="9%">Penggal</td>
        <td width="9%">Bahagian</td>
        <td width="10%">Bab</td>
        <td width="10%">Seksyen</td>
        <td width="10%">Subseksyen</td>
        <td width="10%">Para</td>
        <td width="15%">Jadual</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="2%" scope="row">No</td>
        <td width="7%">No Enakmen</td>
        <td width="15%">Nama Enakmen</td>
        <td width="8%">Penggal</td>
        <td width="8%">Bahagian</td>
        <td width="9%">Bab</td>
        <td width="9%">Seksyen</td>
        <td width="9%">Subseksyen</td>
        <td width="9%">Para</td>
        <td width="9%">Subpara</td>
        <td width="15%">Jadual</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
    </table>
    		</fieldset>
    <fieldset>
    <legend><strong>Senarai Jadual Para</strong></legend>
    <p>
      <label>
      <input type="submit" name="cmdTambah3" id="cmdTambah3" value="Tambah" />
      </label>
    </p>
    <table width="100%" cellspacing="0" cellpadding="2">
      <tr class="table_header">
        <td width="4%" scope="row">No</td>
        <td width="11%">No Enakmen</td>
        <td width="34%">Nama Enakmen</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
          <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="11%">No Enakmen</td>
        <td width="34%">Nama Enakmen</td>
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
        <td width="29%" scope="row">No Enakmen</td>
        <td width="1%" scope="row">:</td>
        <td width="70%"><label>
          <select name="socNoEnakmen" id="socNoEnakmen">
          </select>
        </label></td>
      </tr>
      <tr>
        <td scope="row">Nama Enakmen</td>
        <td scope="row">:</td>
        <td><label>
        <select name="socNamaEnakmen" id="socNamaEnakmen">
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
        <td width="9%">No Enakmen</td>
        <td width="28%">Nama Enakmen</td>
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
            <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" />
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
    <td align="right"><strong>CL-06-09</strong></td>
  	</tr>
  </table>
</form>
