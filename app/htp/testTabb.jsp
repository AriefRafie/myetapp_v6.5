<style type="text/css">
<!--
.star {
	color: #F00;
}
#TabbedPanels1 .TabbedPanelsContentGroup .TabbedPanelsContent.TabbedPanelsContentVisible table tr td {
	text-align: left;
	font-weight: bold;
}
#TabbedPanels1 .TabbedPanelsContentGroup .TabbedPanelsContent.TabbedPanelsContentVisible table tr td .TabbedPanelsContent .TabbedPanelsContentGroup {
	font-weight: bold;
}
-->
</style>

<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0">
  <tr>
    <td> 
    #parse("app/htp/frmTerimaPohonInfo.jsp")
    </td>
  </tr>
  <tr>
    <td>
    <fieldset><legend><strong>Maklumat Lanjutan</strong></legend>
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">Maklumat Asas Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0">Maklumat Borang K</li>
        <li class="TabbedPanelsTab" tabindex="0">Lokasi Tanah</li>
        <li class="TabbedPanelsTab" tabindex="0">Lakaran/Pelan Tanah</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <table width="100%" border="0">
            <tr>
              <td width="18%"><span class="star" style="font-weight: bold"><strong>*</strong></span><span style="font-weight: bold"><strong>Negeri :</strong></span></td>
              <td width="29%" colspan="-2">$socNegeri </td>
              <td width="10%" colspan="-2"><span class="star">*</span>No.Syit</td>
              <td width="12%" colspan="-2"><input type="text" name="textfield2" id="textfield2" /></td>
              <td width="18%" colspan="-2"><span class="star">*</span>Luas</td>
              <td width="13%" colspan="-2"><input type="text" name="textfield5" id="textfield5" /></td>
            </tr>
            <tr>
              <td><span class="star">*</span>Daerah:</td>
              <td colspan="-2">$socDaerah</td>
              <td colspan="-2"><span class="star">*</span>No. Pelan</td>
              <td colspan="-2"><input type="text" name="textfield3" id="textfield3" /></td>
              <td colspan="-2"><span class="star">*</span>Luas(H)</td>
              <td colspan="-2"><input type="text" name="textfield6" id="textfield6" /></td>
            </tr>
            <tr>
              <td><span class="star">*</span>Mukim/Bandar/Pekan :</td>
              <td colspan="-2">$socMukim</td>
              <td colspan="-2"><span class="star">*</span>Kod Luas</td>
              <td colspan="-2"><input type="text" name="textfield4" id="textfield4" /></td>
              <td colspan="-2"><span class="star">*</span>Lokasi</td>
              <td colspan="-2"><input type="text" name="textfield7" id="textfield7" /></td>
            </tr>
            <tr>
              <td><span class="star">*</span>Kod Lot :</td>
              <td colspan="-2">$noLot</td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
            </tr>
            <tr>
              <td><span class="star">*</span>Nombor Lot :</td>
              <td colspan="-2"><input type="text" name="textfield9" id="textfield9" /></td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
              <td colspan="-2">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="6"><label>
                <input type="button" name="button2" id="button" value="Simpan" />
              </label>
                <label>
                  <input type="button" name="button3" id="button2" value="Kemaskini" />
                </label>
                <label>
                  <input type="button" name="button4" id="button3" value="Hapus" />
                </label>
                <label>
                  <input type="button" name="button5" id="button4" value="Cetak" />
                </label>
                <label>
                  <input type="submit" name="button6" id="button5" value="Submit" />
                </label></td>
            </tr>
          </table>
          <table width="100%" border="0">
            <tr bgcolor="#996600">
              <td>Daerah:</td>
              <td>Mukim/Bandar/Pekan :</td>
              <td>Kod Lot</td>
              <td>No.Syit</td>
              <td>No. Pelan</td>
              <td>Kod Luas</td>
              <td>Luas</td>
              <td>Luas(H)</td>
              <td>Lokasi</td>
            </tr>
            <tr>
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
          <p id="Submit">&nbsp;</p>
        </div>
        <div class="TabbedPanelsContent">
          <table width="100%" border="1">
            <tr>
              <td>Hakmilik Asal</td>
              <td>Tkh Boran K</td>
              <td>Warta</td>
              <td>Tkh Warta</td>
              <td>Kod Luas </td>
              <td>Luas</td>
              <td>No.Lot</td>
              <td>No. Pt</td>
            </tr>
            <tr>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield2" />
              </label></td>
              <td><label>
                <input type="text" name="textfield3" id="textfield3" />
              </label></td>
              <td><label>
                <input type="text" name="textfield4" id="textfield4" />
              </label></td>
              <td><label>
                <input type="text" name="textfield5" id="textfield5" />
              </label></td>
              <td><label>
                <input type="text" name="textfield6" id="textfield6" />
              </label></td>
              <td><label>
                <input type="text" name="textfield7" id="textfield7" />
              </label></td>
              <td><label>
                <input type="text" name="textfield8" id="textfield8" />
              </label></td>
            </tr>
          </table>
        </div>
        <div class="TabbedPanelsContent">
          <table width="100%" border="1">
            <tr>
              <td width="34%">&nbsp;</td>
              <td width="20%">Jarak Bandar
                <input type="hidden" name="hiddenField" id="hiddenField" /></td>
              <td width="46%">Perihal Bandar</td>
            </tr>
            <tr>
              <td>Jauh Dari Bandar :</td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Jauh Dari LebuhRaya :</td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Jauh Dari Jalan Keretapi :</td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Jauh Dari Sungai / Pantai :</td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
            <tr>
              <td>Sempadan Utara :</td>
              <td colspan="2"><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Sempadan Selatan :</td>
              <td colspan="2"><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Sempadan Timur :</td>
              <td colspan="2"><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Sempadan Barat :</td>
              <td colspan="2"><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
            <tr>
              <td>Keterangan Lain Menganai Tanah :</td>
              <td colspan="2"><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
            </tr>
          </table>
        </div>
        <div class="TabbedPanelsContent">
          <table width="100%" border="1">
            <tr>
              <td width="42%">&nbsp;</td>
              <td width="10%">&nbsp;</td>
              <td width="10%">&nbsp;</td>
              <td width="38%">&nbsp;</td>
            </tr>
            <tr>
              <td>No.Pelan</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>No. Syit Piawai</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>Jumlah Bayaran Proses (RM)</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td rowspan="2"><table width="100%" border="1">
                <tr>
                  <td colspan="2" bgcolor="#CC9933">Charting</td>
                </tr>
                <tr>
                  <td>Charting</td>
                  <td><label>
                    <select name="select" id="select">
                      <option>FLAG CHARING</option>
                    </select>
                  </label></td>
                </tr>
              </table></td>
              <td rowspan="2">&nbsp;</td>
              <td rowspan="2">&nbsp;</td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label>
                <label>
                  <input type="button" name="button" id="button" value="Browser" />
                </label></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><p>Ulasan :</p>
                <label>
                  <textarea name="textarea2" id="textarea2" cols="45" rows="5"></textarea>
                </label></td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>Keterangan Ringkas Imej :
                <label>
                  <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
                </label></td>
            </tr>
          </table>
        </div>
      </div>
    </div></fieldset></td>
  </tr>
</table>
<p>&nbsp;</p>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
</script>
