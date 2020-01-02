<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<p>TARIKH AKTIVITI</p>
<table width="1008" border="1">
  <tr>
    <td width="998"><table width="953" border="0" align="center">
        <tr>
          <td width="160"><div align="right">Kementerian</div></td>
          <td width="10"><div align="center">:</div></td>
          <td width="306"><form id="form2" name="form2" method="post" action="">
            <label>
              <input type="text" name="txtKementerian" id="txtKementerian" />
              </label>
          </form>          </td>
          <td width="142"><div align="right">Agensi</div></td>
          <td width="9"><div align="center">:</div></td>
          <td width="303"><form id="form6" name="form6" method="post" action="">
            <label>
              <input type="text" name="txtAgensi" id="txtAgensi" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td height="23"><div align="right">No. Fail Seksyen</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form3" name="form3" method="post" action="">
            <label>
              <input type="text" name="txtFailSeksyen" id="txtFailSeksyen" />
              </label>
          </form>          </td>
          <td><div align="right">No. Fail KJP</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form7" name="form7" method="post" action="">
            <label>
              <input type="text" name="txtFailKJP" id="txtFailKJP" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td><div align="right">No. Fail PTG</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form4" name="form4" method="post" action="">
            <label>
              <input type="text" name="txtFailPTG" id="txtFailPTG" />
              </label>
          </form>          </td>
          <td><div align="right">No. Fail PTD</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form8" name="form8" method="post" action="">
            <label>
              <input type="text" name="txtFailPTD" id="txtFailPTD" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td><div align="right">Tajuk</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form5" name="form5" method="post" action="">
            <label>
              <input type="text" name="txtTajuk" id="txtTajuk" />
              </label>
          </form>          </td>
          <td>&nbsp;</td>
          <td><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="954" border="0" align="center">
        <tr>
          <td width="159"><div align="right">Negeri</div></td>
          <td width="10"><div align="center">:</div></td>
          <td width="305"><form id="form9" name="form9" method="post" action="">
            <label>
              <input type="text" name="txtNegeri" id="txtNegeri" />
              </label>
          </form>          </td>
          <td width="142"><div align="right">No. Hakmilik</div></td>
          <td width="9"><div align="center">:</div></td>
          <td width="303"><form id="form13" name="form13" method="post" action="">
            <label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td height="23"><div align="right">Daerah</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form10" name="form10" method="post" action="">
            <label>
              <input type="text" name="txtDaerah" id="txtDaerah" />
              </label>
          </form>          </td>
          <td><div align="right">No. Strata</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form14" name="form14" method="post" action="">
            <label>
              <input type="text" name="txtNoStrata" id="txtNoStrata" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td><div align="right">Bandar/Pekan/Mukim</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form11" name="form11" method="post" action="">
            <label>
              <input type="text" name="txtBandar" id="txtBandar" />
              </label>
          </form>          </td>
          <td><div align="right">Kod Lot/No</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form15" name="form15" method="post" action="">
            <label>
              <input type="text" name="txtNoLot" id="txtNoLot" />
              </label>
          </form>          </td>
        </tr>
        <tr>
          <td><div align="right">Jenis Hakmilik</div></td>
          <td><div align="center">:</div></td>
          <td><form id="form12" name="form12" method="post" action="">
            <label>
              <input type="text" name="txtJenisHakmilik" id="txtJenisHakmilik" />
              </label>
          </form>          </td>
          <td>&nbsp;</td>
          <td><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="954" border="0" align="center">
      
      <tr>
        <td height="26"><strong>PENTADBIRAN</strong></td>
        <td height="26" colspan="9">IMEJAN</td>
        </tr>
      <tr>
        <td width="132"><div align="center">Jenis Binaan</div></td>
        <td width="125"><div align="center">No. Ruj JKR</div></td>
        <td width="93" height="26"><div align="center">Tarikh Binaan</div></td>
        <td colspan="2"><div align="center">Harga (RM)</div></td>
        <td width="85"><div align="center">Unit</div></td>
        <td width="62"><div align="center">Luas</div></td>
        <td width="78"><div align="center">Luas (H)</div></td>
        <td width="206" colspan="2"><div align="center">Catatan</div></td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td height="23">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table></td>
  </tr>
  
  
  <tr>
    <td height="43"><table width="954" border="0" align="center">
      <tr>
        <td colspan="6"><strong>Keluasan Pembanguanan (Hektar)</strong></td>
        </tr>
      <tr>
        <td width="159"><div align="right">Bangunan</div></td>
        <td width="10"><div align="center">:</div></td>
        <td width="305"><form id="form16" name="form9" method="post" action="">
            <label>
            <input type="text" name="txtBangunan" id="txtBangunan" />
            </label>
        </form></td>
        <td colspan="3" rowspan="5"><fieldset>
        <legend>Detail</legend>
        <table width="484" border="0" align="center">
          <tr>
            <td width="294">Luas Asal</td>
            <td width="22"><div align="center">:</div></td>
            <td width="154"><form id="form26" name="form13" method="post" action="">
                <label>
                <input type="text" name="txtLuasAsal2" id="txtLuasAsal2" />
                </label>
            </form></td>
          </tr>
          <tr>
            <td height="23">Jumlah Guna</td>
            <td><div align="center">:</div></td>
            <td><form id="form28" name="form14" method="post" action="">
                <label>
                <input type="text" name="txtJumlahGuna2" id="txtJumlahGuna2" />
                </label>
            </form></td>
          </tr>
          <tr>
            <td>Baki Belum Guna</td>
            <td><div align="center">:</div></td>
            <td><form id="form30" name="form15" method="post" action="">
                <label>
                <input type="text" name="txtBakiBelumGuna2" id="txtBakiBelumGuna2" />
                </label>
            </form></td>
          </tr>
          <tr>
            <td>% Belum Guna</td>
            <td><div align="center">:</div></td>
            <td><form id="form32" name="form24" method="post" action="">
                <label>
                <input type="text" name="txtPeratusBelumGuna2" id="txtPeratusBelumGuna2" />
                </label>
            </form></td>
          </tr>
        </table>
        </fieldset>        </td>
        </tr>
      <tr>
        <td height="23"><div align="right">Jalan</div></td>
        <td><div align="center">:</div></td>
        <td><form id="form18" name="form10" method="post" action="">
            <label>
            <input type="text" name="txtJalan" id="txtJalan" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td><div align="right">Padang</div></td>
        <td><div align="center">:</div></td>
        <td><form id="form20" name="form11" method="post" action="">
            <label>
            <input type="text" name="txtPadang" id="txtPadang" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td><div align="right">Parking</div></td>
        <td><div align="center">:</div></td>
        <td><form id="form22" name="form12" method="post" action="">
            <label>
            <input type="text" name="txtParking" id="txtParking" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td><div align="right">Lain-lain</div></td>
        <td><div align="center">:</div></td>
        <td><form id="form23" name="form23" method="post" action="">
          <label>
            <input type="text" name="txtLain" id="txtLain" />
            </label>
        </form>        </td>
        </tr>
    </table></td>
  </tr>
  
  <tr>
    <td height="43"><form id="form1" name="form1" method="post" action="">
      <label>
        <div align="center">
          <input type="submit" name="btnUpdate" id="btnUpdate" value="Kemaskini" />
          <input type="submit" name="btnDelete" id="btnDelete" value="Hapus" />
          <input type="submit" name="btnSave" id="btnSave" value="Simpan" />
          <input type="submit" name="btnReset" id="btnReset" value="Batal" />
          <input type="submit" name="btnBack" id="btnBack" value="Kembali" />
        </div>
      </label>
      <label></label>
      <label></label>
      <label></label>
      <label></label>
    </form>    </td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>
