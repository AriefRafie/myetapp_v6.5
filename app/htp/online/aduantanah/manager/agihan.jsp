<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="idComplaint" value="$!complaint.id">
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper1(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT ADUAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="1%">&nbsp;</td>
                      <td width="28%">No. Aduan</td>
                      <td width="1%">:</td>
                      <td width="70%">$!complaint.id </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Status</td>
                      <td>:</td>
                      <td>$!complaint.status</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Nama Pengadu</td>
                      <td>:</td>
                      <td>$!complaint.namaPengadu </td>
                    </tr>
                    <tr>
                      <td >&nbsp;</td>
                      <td >Emel</td>
                      <td>:</td>
                      <td>$!complaint.emailPengadu</td>
                    </tr>
                    <tr>
                      <td >&nbsp;</td>
                      <td>No Telefon</td>
                      <td>:</td>
                      <td>$!complaint.phonePengadu</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Jenis</td>
                      <td>:</td>
                      <td>$!complaint.type.code -  $!complaint.type.description</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>Kandungan</td>
                      <td>:</td>
                      <td>$!complaint.catatan </td>
                    </tr>
                    <tr>
                      <td></td>
                      <td valign="top"> Lampiran </td>
                      <td valign="top"> : </td>
                      <td colspan="4"><table>
                          #foreach($lampiran in $complaint.lampiran)
                          <tr>
                            <td><a href="javascript:papar_Lampiran('$lampiran.id')" class="style1">$lampiran.fileName</a> </td>
                          </tr>
                          #end
                        </table></td>
                    </tr>
                    #foreach ( $tanahs in $tanah )
                    <tr>
		    <td colspan="4" align="left">
				<fieldset>
				  <legend><strong>MAKLUMAT TANAH</strong></legend>
					  <table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
							  <td width="1%">&nbsp;</td>
							  <td width="28%">Negeri</td>
							  <td width="1%">:</td>
							  <td width="70%">$!tanahs.nama_negeritanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td>:</td>
							  <td>$!tanahs.nama_daerahtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td>:</td>
							  <td>$!tanahs.nama_mukimtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Seksyen</td>
							  <td>:</td>
							  <td>$!tanahs.nama_seksyentanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Janis Hakmilik</td>
							  <td>:</td>
							  <td>$!tanahs.nama_hakmilik</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td>:</td>
							  <td>$!tanahs.NO_HAKMILIK</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Jenis PT/ LOT</td>
							  <td>:</td>
							  <td>$!tanahs.nama_lot</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. PT/ LOT</td>
							  <td>:</td>
							  <td>$!tanahs.NO_LOT</td>
							</tr>
					</table>
				</fieldset>
			</td>
		</tr>
		#end
                 </table>
          </div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>AGIHAN ADUAN</strong></legend>
      <table width="100%">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%"></td>
          <td width="1%"></td>
          <td width="70%"></td>
        </tr>
         <tr>
                      <td>&nbsp;</td>
                      <td>Untuk Tindakan</td>
                      <td>:</td>
                      <td>
   						<SELECT name="idCategory" onchange="doChangeTindakan()">
							<option $selected value="0">SILA PILIH</option>
		                    <option $selectedL1 value="1">HQ</option>
		                    <option $selectedL2 value="2">JKPTGN</option>
		                    <option $selectedL3 value="3">PTG</option>
		                    <option $selectedL4 value="4">AGENSI LAIN</option>
                        </SELECT></td>
         </tr>
          <tr>
                      <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Seksyen / Bahagian</td>
                      <td>:</td>
                      <td>$selectTindakan
                      <input type="hidden" name="idTindakan" id="idTindakan" value="$idTindakan" />
                      </td>
          </tr>
          <tr>
                      <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Pegawai</td>
                      <td>:</td>
                      <td>$!selectPegawai
                      </td>
          </tr>

          <tr>
                      <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                      <td valign="top">Arahan / Catatan</td>
                      <td valign="top">:</td>
                      <td valign="top"><textarea rows="10" cols="100" name="ulasan" onblur="this.value=this.value.toUpperCase();">$!vectorResponse.arahan</textarea></td>
                    </tr>
                    <tr>
                      <td colspan="4">&nbsp;</td>
         </tr>

        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td></td>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdSimpanAgih" id="cmdSimpanAgih" value="Simpan & Agih" onclick="simpan()"/>
            <input type="button" name="cmdKembaliAgih" id="cmdKembaliAgih" value="Kembali" onclick="kembaliAgih()" />
            <input type="button" name="cmdEmel" id="cmdEmel" value="Hantar Emel" onclick="parent.location='mailto:?subject=ADUAN ONLINE NO $complaint.id'" />
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>