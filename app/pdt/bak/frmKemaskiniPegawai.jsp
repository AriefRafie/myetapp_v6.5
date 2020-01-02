<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
&nbsp;
  <fieldset>
  <legend><strong>Maklumat Pegawai</strong></legend>
  <table width="100%" border="0" cellpadding="2">
    <tr>
      <td width="29%">Nama Pegawai</td>
      <td width="1%">:</td>
      <td width="70%">&nbsp;</td>
    </tr>
    <tr>
      <td>Jawatan</td>
      <td>:</td>
      <td><label>
        <input type="text" name="txtJawatan" id="txtJawatan" />
      </label></td>
    </tr>
    <tr align="center">
      <td colspan="3"><label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" />
      </label>
        <label>
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" />
      </label>
        <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" />
      </label></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  </fieldset>
	<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-03</strong></td>
  	</tr>
    </table>  
</form>
</body>
</html>
