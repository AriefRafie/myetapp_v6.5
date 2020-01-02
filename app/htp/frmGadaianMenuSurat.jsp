<table width="100%" border="0">
  <tr>
    <th scope="col" width="10%">&nbsp;</th>
    <th colspan="2" scope="col">Menu Surat</th>
    <th scope="col" width="55%">&nbsp;</th>
    <th scope="col" width="17%">&nbsp;</th>
  </tr>
  #if ($idNegerixxx == 1)
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">1) </div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','soc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam SOC </a></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="10%"><div align="right">2)</div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','soc','peminjam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peminjam SOC </a></td>
    <td>&nbsp;</td>
  </tr>
  #elseif ($idNegerixxx == 2)
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">1)</div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','doc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam DOC </a></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">2)</div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','doc','peminjam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peminjam DOC </a></td>
    <td>&nbsp;</td>
  </tr>
  #else
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">1)</div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','moc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam MOC </a></td>
    <td>&nbsp;</td>
  </tr>
 <!-- <tr>
    <td>&nbsp;</td>
    <td><div align="right">2)</div></td>
    <td colspan="2"><a href="javascript:PaparSurat('$idPermohonan','moc','peminjam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peminjam MOC </a></td>
    <td>&nbsp;</td>
  </tr> -->
  #end
</table>
<form name="srt" method="post">
    <input type="hidden" name="idPermohonan">
    <input type="hidden" name="command">
    <input type="hidden" name="setting">
    <input type="hidden" name="mode">
</form>
<script>
function PaparSurat(idPermohonan, mode, setting) {
	//document.srt.idPermohonan.value = idPermohonan;
	//document.srt.command.value = "Surat";
	//document.srt.setting.value = setting;
	//document.srt.mode.value = mode;
	//document.srt.action = "";
	//document.srt.submit();
	
	var url = "../x/${securityToken}/ekptg.view.htp.GadaianPaparSurat?idPermohonan="+idPermohonan+"&mode="+mode+"&setting="+setting;
	var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
}
</script>