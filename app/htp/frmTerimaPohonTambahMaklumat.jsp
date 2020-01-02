<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" id="AsasTanah" tabindex="0" onclick="Asastanah();">Maklumat Asas TAnah</li>
    <li class="TabbedPanelsTab" tabindex="0">Maklumat Borang K</li>
    <li class="TabbedPanelsTab" tabindex="0">Lokasi Tanah</li>
    <li class="TabbedPanelsTab" tabindex="0">Lakaran/Pelan Tanah</li>
</ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent" spry:content="frmTerimaPohonMaklumatAsasTanah.jsp">
      <table border="0">
        <tr>
          <td width="158">Daerah:</td>
          <td width="161">$socDaerah</td>
          <td width="833" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td>Mukim/Bandar/Pekan :</td>
          <td>$socMukim</td>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><label>
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
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td height="126" colspan="4"><table border="1">
            <tr>
              <td>Kod Lot</td>
              <td>Nombor</td>
              <td>No.Syit</td>
              <td>No. Pelan</td>
              <td>Kod Luas</td>
              <td>Luas</td>
              <td>Luas(H)</td>
              <td>Lokasi</td>
              <td>Tambah Lot</td>
            </tr>
            <tr>
              <td><label>
                <select name="select3" id="select3">
                </select>
              </label></td>
              <td><label>
                <input type="text" name="textfield" id="textfield" />
              </label></td>
              <td><label>
                <input type="text" name="textfield2" id="textfield2" />
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
                <input type="button" name="button" id="s" value="[+]" />
              </label></td>
            </tr>
          </table></td>
        </tr>
      </table>
      <p id="Submit">&nbsp;</p>
    </div>
    <div class="TabbedPanelsContent"><table width="100%" border="1">
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
    <td>
      <label>
        <input type="text" name="textfield" id="textfield" />
      </label>
    </td>
    <td>
      <label>
        <input type="text" name="textfield2" id="textfield2" />
      </label>
  </td>
    <td>
      <label>
        <input type="text" name="textfield3" id="textfield3" />
      </label>
    </td>
    <td>
      <label>
        <input type="text" name="textfield4" id="textfield4" />
      </label>
 </td>
    <td>
      <label>
        <input type="text" name="textfield5" id="textfield5" />
      </label>
    </td>
    <td>
      <label>
        <input type="text" name="textfield6" id="textfield6" />
      </label>
  </td>
    <td>
      <label>
        <input type="text" name="textfield7" id="textfield7" />
      </label>
   </td>
    <td>
      <label>
        <input type="text" name="textfield8" id="textfield8" />
      </label>
    </td>
  </tr>
</table>
</div>
    <div class="TabbedPanelsContent"><table width="100%" border="1">
  <tr>
    <td width="34%">&nbsp;</td>
    <td width="20%">Jarak Bandar
      <form name="form1" method="post" action="">
        <input type="hidden" name="hiddenField" id="hiddenField">
    </form></td>
    <td width="46%">Perihal Bandar</td>
  </tr>
  <tr>
    <td>Jauh Dari Bandar :</td>
    <td>
      <label>
        <input type="text" name="textfield" id="textfield">
      </label>
    </td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Jauh Dari LebuhRaya :</td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Jauh Dari Jalan Keretapi :</td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Jauh Dari Sungai / Pantai :</td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
    <td><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>Sempadan Utara :</td>
    <td colspan="2"><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Sempadan Selatan :</td>
    <td colspan="2"><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Sempadan Timur :</td>
    <td colspan="2"><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Sempadan Barat :</td>
    <td colspan="2"><label>
        <input type="text" name="textfield" id="textfield">
      </label></td>
  </tr>
  <tr>
    <td>Keterangan Lain Menganai Tanah :</td>
    <td colspan="2"><label>
        <input type="text" name="textfield" id="textfield">
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
        <td>
          <label>
            <select name="select" id="select">
              <option>FLAG CHARING</option>
            </select>
          </label>
   </td>
      </tr>
    </table></td>
    <td rowspan="2">&nbsp;</td>
    <td rowspan="2">&nbsp;</td>
    <td>
      <label>
        <input type="text" name="textfield" id="textfield">
      </label>
      <label>
        <input type="button" name="button" id="button" value="Browser">
      </label>
 </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><p>Ulasan :</p>   
  <label>
    <textarea name="textarea2" id="textarea2" cols="45" rows="5"></textarea>
  </label>
   </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>Keterangan Ringkas Imej :
      
        <label>
          <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
        </label>
  </td>
  </tr>
</table>
</div>
</div>
</div>
<p>&nbsp;</p>
<script type="text/javascript">

function Asastanah(){	
	//alert(i);
	//document.${formName}.cmdSimpan.value = i;
	//document.${formName}.mode.value = "SimpanPermohonan";
	//document.${formName}.method = "post";
	doAjaxCall${formName}("AsasTanah");
}

<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:0});
//-->
</script>
