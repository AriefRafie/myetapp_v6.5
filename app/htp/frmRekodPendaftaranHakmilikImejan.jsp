<fieldset>
<legend>PENDAFTARAN HAKMILIK</legend>
#parse("app/htp/frmRekodMaklumatFail.jsp")  
<table width="100%" border="0">
  <tr>
    <td><fieldset>
    <legend>Senarai Imej Pentadbiran</legend>
    <div align="left"></div>
    <table width="973" border="0" align="left">

      <tr class="table_header">
        <td width="132"><div align="left">Jenis Binaan</div></td>
        <td width="125"><div align="left">No. Ruj JKR</div></td>
        <td width="93" height="26"><div align="left">Tarikh Binaan</div></td>
        <td width="139"><div align="left">Harga (RM)</div></td>
        <td width="85"><div align="left">Unit</div></td>
        <td width="62"><div align="left">Luas</div></td>
        <td width="78"><div align="left">Luas (H)</div></td>
        <td width="225"><div align="left">Catatan</div></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td height="23">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
  <tr>
    <td height="43"><fieldset>
    <legend>Keluasan Pembanguanan (Hektar)</legend>
    <table width="954" border="0" align="center">
      <tr>
        <td width="159"><div align="right">Bangunan</div></td>
        <td width="10"><div align="center">:</div></td>
        <td width="305"><label>
          <input type="text" name="txtBangunan" id="txtBangunan" />
          </label>        </td>
        <td colspan="3" rowspan="5"><fieldset>
          <legend>Detail</legend>
          <table width="484" border="0" align="center">
            <tr>
              <td width="179"><div align="right">Luas Asal</div></td>
              <td width="10"><div align="center">:</div></td>
              <td width="281"><label>
                <input type="text" name="txtLuasAsal2" id="txtLuasAsal2" />
                </label>              </td>
            </tr>
            <tr>
              <td height="23"><div align="right">Jumlah Guna</div></td>
              <td><div align="center">:</div></td>
              <td><label>
                <input type="text" name="txtJumlahGuna2" id="txtJumlahGuna2" />
                </label>              </td>
            </tr>
            <tr>
              <td><div align="right">Baki Belum Guna</div></td>
              <td><div align="center">:</div></td>
              <td><label>
                <input type="text" name="txtBakiBelumGuna2" id="txtBakiBelumGuna2" />
                </label>              </td>
            </tr>
            <tr>
              <td><div align="right">% Belum Guna</div></td>
              <td><div align="center">:</div></td>
              <td><label>
                <input type="text" name="txtPeratusBelumGuna2" id="txtPeratusBelumGuna2" />
                </label>              </td>
            </tr>
          </table>
        </fieldset></td>
      </tr>
      <tr>
        <td height="23"><div align="right">Jalan</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input type="text" name="txtJalan" id="txtJalan" />
          </label>        </td>
      </tr>
      <tr>
        <td><div align="right">Padang</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input type="text" name="txtPadang" id="txtPadang" />
          </label>        </td>
      </tr>
      <tr>
        <td><div align="right">Parking</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input type="text" name="txtParking" id="txtParking" />
          </label>        </td>
      </tr>
      <tr>
        <td><div align="right">Lain-lain</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input type="text" name="txtLain" id="txtLain" />
          </label>        </td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
</table>
<div align="center">
  <p>
    <input type="submit" name="btnUpdate" id="btnUpdate" value="Kemaskini" />
    <input type="submit" name="btnDelete" id="btnDelete" value="Hapus" />
    <input type="submit" name="btnSave" id="btnSave" value="Simpan" />
    <input type="submit" name="btnReset" id="btnReset" value="Batal" />
    <input type="submit" name="btnBack" id="btnBack" value="Kembali" />
  </p>
</div>
</fieldset>
</fieldset>

