<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<p>PENDAFTARAN RIZAB</p>
<table width="1008" border="1">
  <tr>
    <td width="998"><table width="953" border="0" align="center">
        <tr>
          <td width="160">Kementerian</td>
          <td width="10"><div align="center">:</div></td>
          <td width="306"><form id="form2" name="form2" method="post" action="">
            <label>
              <input type="text" name="txtKementerian" id="txtKementerian" />
              </label>
          </form>
          </td>
          <td width="142">Agensi</td>
          <td width="9"><div align="center">:</div></td>
          <td width="303"><form id="form6" name="form6" method="post" action="">
            <label>
              <input type="text" name="txtAgensi" id="txtAgensi" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td height="23">No. Fail Seksyen</td>
          <td><div align="center">:</div></td>
          <td><form id="form3" name="form3" method="post" action="">
            <label>
              <input type="text" name="txtFailSeksyen" id="txtFailSeksyen" />
              </label>
          </form>
          </td>
          <td>No. Fail KJP</td>
          <td><div align="center">:</div></td>
          <td><form id="form7" name="form7" method="post" action="">
            <label>
              <input type="text" name="txtFailKJP" id="txtFailKJP" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td>No. Fail PTG</td>
          <td><div align="center">:</div></td>
          <td><form id="form4" name="form4" method="post" action="">
            <label>
              <input type="text" name="txtFailPTG" id="txtFailPTG" />
              </label>
          </form>
          </td>
          <td>No. Fail PTD</td>
          <td><div align="center">:</div></td>
          <td><form id="form8" name="form8" method="post" action="">
            <label>
              <input type="text" name="txtFailPTD" id="txtFailPTD" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td>Tajuk</td>
          <td><div align="center">:</div></td>
          <td><form id="form5" name="form5" method="post" action="">
            <label>
              <input type="text" name="txtTajuk" id="txtTajuk" />
              </label>
          </form>
          </td>
          <td>&nbsp;</td>
          <td><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
        </table></td>
  </tr>
  <tr>
    <td><table width="954" border="0" align="center">
        <tr>
          <td width="159">Negeri</td>
          <td width="10"><div align="center">:</div></td>
          <td width="305"><form id="form9" name="form9" method="post" action="">
            <label>
              <input type="text" name="txtNegeri" id="txtNegeri" />
              </label>
          </form>
          </td>
          <td width="142">No. Hakmilik</td>
          <td width="9"><div align="center">:</div></td>
          <td width="303"><form id="form13" name="form13" method="post" action="">
            <label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td height="23">Daerah</td>
          <td><div align="center">:</div></td>
          <td><form id="form10" name="form10" method="post" action="">
            <label>
              <input type="text" name="txtDaerah" id="txtDaerah" />
              </label>
          </form>
          </td>
          <td>No. Strata</td>
          <td><div align="center">:</div></td>
          <td><form id="form14" name="form14" method="post" action="">
            <label>
              <input type="text" name="txtNoStrata" id="txtNoStrata" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td>Bandar/Pekan/Mukim</td>
          <td><div align="center">:</div></td>
          <td><form id="form11" name="form11" method="post" action="">
            <label>
              <input type="text" name="txtBandar" id="txtBandar" />
              </label>
          </form>
          </td>
          <td>Kod Lot/No</td>
          <td><div align="center">:</div></td>
          <td><form id="form15" name="form15" method="post" action="">
            <label>
              <input type="text" name="txtNoLot" id="txtNoLot" />
              </label>
          </form>
          </td>
        </tr>
        <tr>
          <td>Jenis Hakmilik</td>
          <td><div align="center">:</div></td>
          <td><form id="form12" name="form12" method="post" action="">
            <label>
              <input type="text" name="txtJenisHakmilik" id="txtJenisHakmilik" />
              </label>
          </form>
          </td>
          <td>&nbsp;</td>
          <td><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
            </table></td>
  </tr>
  <tr>
    <td><table width="488" border="0" align="center">
      <tr>
        <td width="159">Tarikh Terima</td>
        <td width="10"><div align="center">:</div></td>
        <td width="305"><form id="form16" name="form9" method="post" action="">
            <label>
            <input type="text" name="txdTarikhTerima" id="txdTarikhTerima" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td height="23">Unit/ Luas</td>
        <td><div align="center">:</div></td>
        <td><form id="form18" name="form10" method="post" action="">
            <label>
            <input type="text" name="txtUnit" id="txtUnit" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td>Luas Bersamaan</td>
        <td><div align="center">:</div></td>
        <td><form id="form20" name="form11" method="post" action="">
            <label>
            <input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" />
            </label>
                </form></td>
        </tr>
      <tr>
        <td>No. Pelan</td>
        <td><div align="center">:</div></td>
        <td><form id="form22" name="form12" method="post" action="">
            <label>
            <input type="text" name="txtNoPelan" id="txtNoPelan" />
            </label>
        </form></td>
        </tr>
      <tr>
        <td>No. PU</td>
        <td><div align="center">:</div></td>
        <td><form id="form17" name="form17" method="post" action="">
          <label>
            <input type="text" name="txtNoPU" id="txtNoPU" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>Lokasi</td>
        <td><div align="center">:</div></td>
        <td><form id="form19" name="form19" method="post" action="">
          <label>
            <input type="text" name="txtLokasi" id="txtLokasi" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>Kegunaan Tanah</td>
        <td><div align="center">:</div></td>
        <td><form id="form21" name="form21" method="post" action="">
          <label>
            <input type="text" name="txtKegunaanTanah" id="txtKegunaanTanah" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>No. Fail Jopa</td>
        <td><div align="center">:</div></td>
        <td><form id="form23" name="form23" method="post" action="">
          <label>
            <input type="text" name="txtNoFailJopa" id="txtNoFailJopa" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>Status Sah</td>
        <td><div align="center">:</div></td>
        <td><form id="form24" name="form24" method="post" action="">
          <label>
            <input type="text" name="txtStatusSah" id="txtStatusSah" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>Pegawai Akhir Kemaskini</td>
        <td><div align="center">:</div></td>
        <td><form id="form25" name="form25" method="post" action="">
          <label>
            <input type="text" name="txtPegawaiAkhir" id="txtPegawaiAkhir" />
            </label>
        </form>        </td>
      </tr>
      <tr>
        <td>Tarikh Akhir Kemaskini</td>
        <td><div align="center">:</div></td>
        <td><form id="form26" name="form26" method="post" action="">
          <label>
            <input type="text" name="txdTarikhAkhir" id="txdTarikhAkhir" />
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
