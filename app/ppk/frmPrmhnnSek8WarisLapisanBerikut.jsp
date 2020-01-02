<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<body>
<table
 style="text-align: left; margin-left: auto; margin-right: auto; width: 1214px; height: 154px;"
 border="0">
  <tbody>
    <tr>
      <td style="width: 150px;" scope="row"><strong>Nama Waris Sudah Meninggal Dunia</strong></td>
      <td style="width: 330px;"><select name="socSeksyen5" id="socSeksyen5"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
      <td style="width: 150px;"><strong>Sudah Meninggal Dunia</strong></td>
      <td style="width: 338px;"><input type="checkbox" name="checkbox" id="checkbox" /></td>
    </tr>
    <tr>
      <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
      <td style="width: 330px;"><input name="txtNoFail" id="txtNoFail2" style="width: 80px;" type="text" />
-
  <input name="txtNoFail" id="txtNoFail2" style="width: 30px;" type="text" />
-
<input name="txtNoFail" id="txtNoFail"  style="width: 60px;" type="text" /></td>
      <td style="width: 150px;"><strong>Tarikh Mati</strong></td>
      <td style="width: 338px;"><input name="txtTarikhDaftar" id="txtTarikhDaftar" type="text" />
&nbsp;
<input name="button" id="button" value="Kalendar" onclick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" type="submit" /></td>
    </tr>
    <tr>
      <td style="" scope="row"><strong> No. KP Lama</strong></td>
      <td><input
 name="txtNegeri" id="txtNegeri" type="text" />      </td>
      <td style="width: 150px;"><strong>Waktu Kematian</strong></td>
      <td style="width: 338px;"><input
 name="txtNamaPemohon3" id="txtNamaPemohon3" type="text" width="60%" /></td>
    </tr>
    <tr>
      <td><strong>Lain- lain KP</strong></td>
      <td><select name="socKpLain" id="socKpLain">
        <option> &lt;--Pilih--&gt;</option>
      </select>
        <input name="txtNoFail2" id="txtNoFail4" style="width: 120px;" type="text" /></td>
      <td><span style="width: 150px;"><strong>Alamat</strong></span></td>
      <td><span style="width: 338px;"><span style="width: 330px;">
        <input
 name="txtNoFail9" id="txtNoFail9" type="text" width="300px" />
      </span></span></td>
    </tr>
    <tr>
      <td><strong>Nama Waris </strong></td>
      <td><span style="width: 330px;">
        <input
 name="txtNoFail5" id="txtNoFail5" type="text" width="300px" />
      </span></td>
      <td>&nbsp;</td>
      <td><span style="width: 330px;">
        <input
 name="txtNoFail8" id="txtNoFail8" type="text" width="300px" />
      </span></td>
    </tr>
    <tr>
      <td><strong>Jantina</strong></td>
      <td><select name="socSeksyen3" id="socSeksyen3"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
      <td>&nbsp;</td>
      <td><span style="width: 330px;">
        <input
 name="txtNoFail7" id="txtNoFail7" type="text" width="300px" />
      </span></td>
    </tr>
    <tr>
      <td><strong>Agama </strong></td>
      <td><select name="socSeksyen" id="socSeksyen"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
      <td><strong>Poskod </strong></td>
      <td><input
 name="txtNamaPemohon2" id="txtNamaPemohon2"
 type="text" /></td>
    </tr>
    <tr>
      <td><strong>Warganegara</strong></td>
      <td><select name="socSeksyen2" id="socSeksyen2"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
      <td><strong>Bandar</strong></td>
      <td><span style="width: 330px;">
        <input
 name="txtNoFail3" id="txtNoFail3" type="text" width="300px" />
      </span></td>
    </tr>
    <tr>
      <td><strong>Tarikh Lahir</strong></td>
      <td><input name="txtTarikhDaftar2" id="txtTarikhDaftar2" type="text" />
        &nbsp;
        <input name="button2" id="button2" value="Kalendar" onclick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" type="submit" /></td>
      <td><strong>Negeri</strong></td>
      <td><select name="socSeksyen6" id="socSeksyen6"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
    </tr>
    <tr>
      <td><strong>No.Surat Beranak</strong></td>
      <td><input
 name="txtNamaPemohon4" id="txtNamaPemohon4"
 type="text" /></td>
      <td><strong>No. Telefon</strong></td>
      <td><input
 name="txtNamaPemohon" id="txtNamaPemohon"  type="text" /></td>
    </tr>
    <tr>
      <td><strong>Talian Persaudaraan</strong></td>
      <td><select name="socSeksyen4" id="socSeksyen4"
 style="width: 100px;">
        <option> &lt;-- Pilih --&gt; </option>
      </select></td>
      <td><strong>No. Telefon Bimbit</strong></td>
      <td><input
 name="txtNamaSimati" id="txtNamaSimati" type="text" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><label></label></td>
      <td><strong>Catatan</strong></td>
      <td><label>
        <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
      </label></td>
    </tr>
  </tbody>
</table>
</body>
</html>
