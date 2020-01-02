<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<p>MAKLUMAT AKTIVITI</p>
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
        <td width="160" height="26">No. Fail Seksyen</td>
        <td colspan="2"><div align="center">Kepada</div></td>
        <td width="84">Kod Luas</td>
        <td width="370" colspan="2"><div align="center">Luas</div></td>
        </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">&nbsp;</td>
      </tr>

    </table></td>
  </tr>
  <tr>
    <td height="43"><table width="954" border="0" align="center">
      <tr>
        <td width="160" height="26">No. Fail Seksyen</td>
        <td colspan="2"><div align="center">Tarikh Buka Surat</div></td>
        <td>Jenis Pencerobohan
          <div align="center"></div></td>
        </tr>
      <tr>
        <td height="23">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
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
