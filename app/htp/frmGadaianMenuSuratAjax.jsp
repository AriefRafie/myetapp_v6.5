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
    <td colspan="2"><a href="javascript:fGMSA_PaparSurat('$idPermohonan','soc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam SOC </a></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="10%"><div align="right">2)</div></td>
    <td colspan="2"><a href="javascript:fGMSA_PaparSurat('$idPermohonan','soc','peminjam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peminjam SOC </a></td>
    <td>&nbsp;</td>
  </tr>
  #elseif ($idNegerixxx == 2)
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">1)</div></td>
    <td colspan="2"><a href="javascript:fGMSA_PaparSurat('$idPermohonan','doc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam DOC </a></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">2)</div></td>
    <td colspan="2"><a href="javascript:fGMSA_PaparSurat('$idPermohonan','doc','peminjam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peminjam DOC </a></td>
    <td>&nbsp;</td>
  </tr>
  #else
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">1)</div></td>
    <td colspan="2"><a href="javascript:fGMSA_PaparSurat('$idPermohonan','moc','peguam')"> Surat Tindakan Dan Penghantaran Pendua Kepada Peguam MOC </a></td>
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
