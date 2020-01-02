<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>TUKARGUNA DAN PENAWARAN</p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">TUKARGUNA</li>
    <li class="TabbedPanelsTab" tabindex="0">PENAWARAN</li>
    <li class="TabbedPanelsTab" tabindex="0">PELEPASAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <table width="1008" border="1">
        <tr>
          <td width="998"><table width="953" border="0" align="center">
              <tr>
                <td colspan="3"><strong>Maklumat Fail</strong></td>
              </tr>
              <tr>
                <td width="320"><div align="right">Kementerian</div></td>
                <td width="8"><div align="center">:</div></td>
                <td width="611"><form id="form2" name="form2" method="post" action="">
                    <label>
                    <input type="text" name="txtKementerian" id="txtKementerian" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Agensi</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form6" name="form6" method="post" action="">
                    <label>
                    <input type="text" name="txtAgensi" id="txtAgensi" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">No. Fail SPHP</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form4" name="form4" method="post" action="">
                    <label>
                    <input type="text" name="txtFailSPHP" id="txtFailSPHP" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Tujuan</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form5" name="form5" method="post" action="">
                    <label>
                    <textarea name="txtTujuan" id="txtTujuan" cols="45" rows="5"></textarea>
                    </label>
                                </form></td>
              </tr>
              <tr>
                <td><div align="right">Bilangan Hakmilik Diserah</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form3" name="form3" method="post" action="">
                  <label>
                  <input type="text" name="txtBilanganDiserah" id="txtBilanganDiserah" />
                  </label>
                </form>                </td>
              </tr>
              <tr>
                <td><div align="right">Bilangan Hakmilik Diterima</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form43" name="form43" method="post" action="">
                  <label>
                    <input type="text" name="txtBilanganDiterima" id="txtBilanganDiterima" />
                    </label>
                </form>                </td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="953" border="0" align="center">
            <tr>
              <td colspan="3"><strong>Maklumat Pelepasan</strong></td>
            </tr>
            <tr>
              <td width="320"><div align="right">No. Hakmilik</div></td>
              <td width="8"><div align="center">:</div></td>
              <td width="611"><form id="form7" name="form2" method="post" action="">
                  <label>
                  <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" />
                  </label>
              </form></td>
            </tr>
            <tr>
              <td><div align="right">Nama Pemohon</div></td>
              <td><div align="center">:</div></td>
              <td><form id="form8" name="form6" method="post" action="">
                  <label>
                  <input type="text" name="txtNamaPemohon" id="txtNamaPemohon" />
                  </label>
              </form></td>
            </tr>
            <tr>
              <td><div align="right">Luas Asal</div></td>
              <td><div align="center">:</div></td>
              <td><form id="form9" name="form4" method="post" action="">
                  <label>
                  <input type="text" name="txtLuasAsal" id="txtLuasAsal" />
                  </label>
              </form></td>
            </tr>
            <tr>
              <td><div align="right">Luas Pelepasan</div></td>
              <td><div align="center">:</div></td>
              <td><form id="form10" name="form5" method="post" action="">
                  <label></label>
                  <label>
                  <input type="text" name="txtLuasPelepasan" id="txtLuasPelepasan" />
                  </label>
              </form></td>
            </tr>

          </table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
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
          </form></td>
        </tr>
      </table>
    </div>
    <div class="TabbedPanelsContent">
      <table width="1008" border="1">
        <tr>
          <td width="998"><table width="953" border="0" align="center">
              <tr>
                <td colspan="3">Maklumat Pemohon</td>
              </tr>
              <tr>
                <td width="320"><div align="right">Kementerian</div></td>
                <td width="8"><div align="center">:</div></td>
                <td width="611"><form id="form11" name="form2" method="post" action="">
                    <label>
                    <input type="text" name="txtKementerian2" id="txtKementerian2" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Agensi</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form12" name="form6" method="post" action="">
                    <label>
                    <input type="text" name="txtAgensi2" id="txtAgensi2" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">No. Fail SPHP</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form15" name="form4" method="post" action="">
                    <label>
                    <input type="text" name="txtFailSPHP2" id="txtFailSPHP2" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Tujuan</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form16" name="form5" method="post" action="">
                    <label>
                    <textarea name="txtTujuan2" id="txtTujuan2" cols="45" rows="5"></textarea>
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Bilangan Hakmilik Diserah</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form17" name="form3" method="post" action="">
                    <label>
                    <input type="text" name="txtBilanganDiserah2" id="txtBilanganDiserah2" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td><div align="right">Bilangan Hakmilik Diterima</div></td>
                <td><div align="center">:</div></td>
                <td><form id="form18" name="form43" method="post" action="">
                    <label>
                    <input type="text" name="txtBilanganDiterima2" id="txtBilanganDiterima2" />
                    </label>
                </form></td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="954" border="0" align="center">
              <tr>
                <td><strong>Senarai Hakmilik Yang Ditawarkan</strong></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td width="474"><div align="center">No. Hakmilik/ Warta</div></td>
                <td width="142">Kementerian Baru</td>
                <td width="9"><div align="center">:</div></td>
                <td width="303"><form id="form19" name="form13" method="post" action="">
                    <label>
                    <input type="text" name="txtNoHakmilik3" id="txtNoHakmilik3" />
                    </label>
                </form></td>
              </tr>
              <tr>
                <td height="23"><form id="form20" name="form10" method="post" action="">
                    <label></label>
                    <div align="center">
                      <input type="text" name="txtDaerah3" id="txtDaerah3" />
                    </div>
                </form></td>
                <td>Agensi Baru</td>
                <td><div align="center">:</div></td>
                <td><form id="form21" name="form14" method="post" action="">
                    <label>
                    <input type="text" name="txtNoStrata3" id="txtNoStrata3" />
                    </label>
                </form></td>
              </tr>
          </table></td>
        </tr>
        
        <tr>
          <td height="43"><form id="form25" name="form1" method="post" action="">
              <label>
              <div align="center">
                <input type="submit" name="btnUpdate2" id="btnUpdate2" value="Kemaskini" />
                <input type="submit" name="btnDelete2" id="btnDelete2" value="Hapus" />
                <input type="submit" name="btnSave2" id="btnSave2" value="Simpan" />
                <input type="submit" name="btnReset2" id="btnReset2" value="Batal" />
                <input type="submit" name="btnBack2" id="btnBack2" value="Kembali" />
              </div>
            </label>
              <label></label>
              <label></label>
              <label></label>
              <label></label>
          </form></td>
        </tr>
      </table>
    </div>
  </div>
</div>
<p>&nbsp;</p>
<p>&nbsp;</p>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
</body>
</html>
