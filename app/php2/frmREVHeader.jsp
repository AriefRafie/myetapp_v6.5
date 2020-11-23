<div id="laporan">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($noFail = $beanHeader.noFail)
    #set($tajukFail = $beanHeader.tajukFail)
    #set($tujuan = $beanHeader.tujuan)
    #set($urusan = $beanHeader.urusan)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($noRujukan = $beanHeader.noRujukan)
    #set($tarikhMula = $beanHeader.tarikhMula)
    #set($tarikhTamat = $beanHeader.tarikhTamat)
    #set($deposit = $beanHeader.deposit)
    #set($sewa = $beanHeader.sewa)
    #set($namaPemohon = $beanHeader.namaPemohon)
    #set($noRujukanPenyewa = $beanHeader.noRujukanPenyewa)
    #set($noPengenalan = $beanHeader.noPengenalan)
    #set($alamat1 = $beanHeader.alamat1)
    #set($alamat2 = $beanHeader.alamat2)
    #set($alamat3 = $beanHeader.alamat3)
    #set($poskod = $beanHeader.poskod)
    #set($bandar = $beanHeader.bandar)
    #set($negeri = $beanHeader.negeri)
    #set($noTel = $beanHeader.noTel)
    #set($noFax = $beanHeader.noFax)
    #set($emel = $beanHeader.emel)
    #end
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">NO FAIL</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFail</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">URUSAN</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$urusan</font></td>
        </tr>
        <tr>
          <td align="right">SUBURUSAN</td>
          <td>:</td>
          <td><font color="blue">$subUrusan</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">PERKARA</td>
          <td valign="top">:</td>
          <td rowspan="2" valign="top"><font color="blue">$tajukFail</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td align="right" valign="top">TUJUAN</td>
          <td valign="top">:</td>
          <td valign="top"><font color="blue">$tujuan</font></td>
        </tr>
        <tr>
          <td align="right">NO SIRI PERJANJIAN</td>
          <td>:</td>
          <td><font color="blue">$noRujukan</font></td>
        </tr>
        <tr>
          <td align="right">TARIKH MULA</td>
          <td>:</td>
          <td><font color="blue">$tarikhMula</font></td>
        </tr>
        <tr>
          <td align="right">TARIKH TAMAT</td>
          <td>:</td>
          <td><font color="blue">$tarikhTamat</font></td>
        </tr>
        <tr>
          <td align="right">DEPOSIT (RM)</td>
          <td>:</td>
          <td><font color="blue">$deposit</font></td>
        </tr>
         <tr>
          <td align="right">SEWA (RM)</td>
          <td>:</td>
          <td><font color="blue">$sewa</font></td>
        </tr>
      </table>
    </fieldset></td>
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PENYEWA</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr valign="top">
          <td width="37%" align="right" valign="top">NAMA PENYEWA :</td>
          <td width="63%"><font color="blue" valign="top">$namaPemohon</font></td>
        </tr>
        <tr>
          <td align="right">NO. PENGENALAN/ SSM/ ROS/ SKM :</td>
          <td><font color="blue">$noPengenalan</font></td>
        </tr>
        <tr>
          <td align="right">NO RUJUKAN PENYEWA :</td>
          <td><font color="blue">$noRujukanPenyewa</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">ALAMAT :</td>
          <td><font color="blue" valign="top">$alamat1</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font color="blue" valign="top">$alamat2</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font color="blue" valign="top">$alamat3</font></td>
        </tr>
        <tr>
          <td align="right">POSKOD :</td>
          <td><font color="blue">$poskod</font></td>
        </tr>
        <tr>
          <td align="right"> BANDAR :</td>
          <td><font color="blue">$bandar</font></td>
        </tr>
        <tr>
          <td align="right"> NEGERI :</td>
          <td><font color="blue">$negeri</font></td>
        </tr>
        <tr>
          <td align="right">NO. TEL. :</td>
          <td><font color="blue">$noTel</font></td>
        </tr>
        <tr>
          <td align="right">NO. FAX :</td>
          <td><font color="blue">$noFax</font></td>
        </tr>
        <tr>
          <td align="right">EMEL :</td>
          <td><font color="blue">$emel</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font color="blue">&nbsp;</td>
        </tr>
      </table>
    </fieldset></td>
  </tr>
</table>

<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Permohonan" onclick="printLaporan('laporan','$!tajukLaporan')"/>
</div>
<script type="text/javascript">
function printLaporan(divName,tajuk) {
	$jquery("#"+divName+" :button").hide();

	var head_style = " <head> "+
    " <style> "+
    " @media print { "+
	" 	body { "+
    "  -webkit-print-color-adjust: exact; "+  /*Chrome, Safari */
    "  color-adjust: exact;  "+  /*Firefox*/
    " 	} "+
	"         table { page-break-inside:auto; } "+
  	"		  tr    { page-break-inside:avoid; page-break-after:auto;  } "+
    "   } "+
	"		  td    { border:1px solid black;font-size:75%;  } "+
    " </style> "+
	" </head> ";

	var tajuk = '<div style="width:95%;margin: auto;font-size:100%;" align="center" ><b>'+tajuk.toUpperCase()+'</b></div>'
    var printContents = document.getElementById(divName).innerHTML;
	printContents = printContents.replace("<table ", "<table style=\"border-collapse:collapse;\" ");
	printContents = printContents.replace('class="table_header"',' style="background-color:#d5d1d0;" ');

	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();

    popupWin.document.write('<html><body onload="window.print()"><div style="width:95%;margin: auto;"  >'+head_style+'<br>'+tajuk+'<br>'+printContents+'</div></html>');
	popupWin.document.close();
    return false;
}
</script>
