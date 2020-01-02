<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />
<style type="text/css">
<!-- 
input[readonly]{
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>


#foreach($list in $listSemak)
 	#set($NO_FAIL=$list.noFail)
 	#set($negeri=$list.pmNama_negeri)
 	#set($daerah=$list.namadaerah)
 	#set($unit=$list.namaPejabat)
 	#set($seksyen=$list.seksyen)
 	#set($statusFail=$list.keterangan)
 	#set($tarikhMohon=$list.tarikhMohon)
 	#set($namaSimati=$list.namaSimati)
 	#set($namaSipemohon=$list.namaPemohon)
#end


<body>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI PERMOHONAN</font></legend>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
			
			
			<input type="hidden" name="NO_FAIL" id="NO_FAIL" value='$!NO_FAIL'/> 
			<input type="hidden" name="id_perbicaraan" id="id_perbicaraan" value='$!id_perbicaraan'/> 
			<input type="hidden" name="id_fail" id="id_fail" value='$!id_fail'/> 
				 <tr>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
                <td width="36%" valign="top">NO FAIL</td>
                <td width="1%" valign="top">:&nbsp;</td>
                <td width="63%" valign="top"><font color="blue">$NO_FAIL</font></td>
                <!-- <td width="63%" valign="top"><a href="javascript:kembaliSenaraiFail('$NO_FAIL')"><font color="blue"><b>$NO_FAIL</b></font></a></td> --> 
              </tr>
                <tr>
                <td valign="top">NEGERI</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$negeri</font></td>
              </tr>
                <tr>
                <td valign="top">DAERAH / JAJAHAN</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$daerah.toUpperCase()</font></td>
              </tr>
                <tr>
                <td valign="top">UNIT</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$unit.toUpperCase()</font></td>
              </tr>
              </table></td>
            <td width="50%" valign="top"><table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
                <td width="34%" valign="top">STATUS FAIL</td>
                <td width="1%" valign="top">:&nbsp;</td>
                <td width="65%" valign="top"><font color="blue">$statusFail.toUpperCase()</font></td>
              </tr>
                <tr>
                <td valign="top">SEKSYEN</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$seksyen.toUpperCase()</font></td>
              </tr>
                <tr>
                <td valign="top">TARIKH MOHON</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$tarikhMohon</font></td>
              </tr>
                <tr>
                <td valign="top">NAMA PEMOHON</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$namaSipemohon.toUpperCase()</font></td>
              </tr>
                <tr>
                <td valign="top">NAMA SIMATI</td>
                <td valign="top">:&nbsp;</td>
                <td valign="top"><font color="blue">$namaSimati.toUpperCase()</font></td>
              </tr>
              </table></td>
          </tr>
    </table>
    
    	
		</fieldset>
    
    <fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">MAKLUMAT NOTIS PERBICARAAN</font></legend>
    <table width="100%"  cellpadding="1" cellspacing="1" border="0">
                <tr>
              <td width="1%">&nbsp;</td>
              <td width="20%">Bil.Bicara</td>
              <td width="79%">:&nbsp;
              $bil_bicara
                    <!-- <input type="text" class="disabled" readonly name="TGBilBicara" value="$previousBil" size="4" /> -->
                   
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Bicara</td>
              <td>:&nbsp;
                   $tarikh_bicara
                    <!-- <input type="text" name="txdTarikhBicara" id="txdTarikhBicara" value="$tarikh_bicara" size="11" maxlength="10" readonly /> -->
                   </td>
            </tr>
           
            <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tarikh Notis</td>
              <td>:&nbsp;
                   $tarikh_notis
                   <!--  <input type="text" size="11" name="txdTarikhNotis" id="txdTarikhNotis" maxlength="10" value="$tarikh_notis" readonly /> -->
                  </td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Masa Bicara</td>
              <td>:&nbsp;
              
              $masa_bicara&nbsp;&nbsp;$jenisMasa
              
                 <!--    <input type="text" name="txtMasaBicara" id="txtMasaBicara" value="$masa_bicara"  maxlength="4" size="4" /> -->
                    
                   </td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Tempat Bicara</td>
              <td>:&nbsp;
                    <input type="radio" id="pKptg" $checkP1 checked name="jenisPejabat" value="1"  disabled >
                    Cawangan JKPTG
                    &nbsp;&nbsp;
                    <input type="radio" $checkP2 id="pTanah" name="jenisPejabat" value="2"  disabled>
                    Pejabat Tanah
                    &nbsp;&nbsp;
                    <input type="radio" $checkP3 id="pLain" name="jenisPejabat" value="0" disabled>
                    Lain - lain tempat</td>
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;&nbsp;$selectBicara</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Alamat</td>
              <td>:&nbsp;
              $alamat1
                <!--     <input type="text" $!addressReadonlyClass $!addressReadonly size="52" name="TGAlamatBicara1" id="TGAlamatBicara1" value="$alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;
              $alamat2
                    <!-- <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara2" id="TGAlamatBicara2" value="$alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td><font color="white">:</font>&nbsp;
              $alamat3
                    <!-- <input $!addressReadonly $!addressReadonlyClass type="text" size="52" name="TGAlamatBicara3" id="TGAlamatBicara3" value="$alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> -->
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Poskod</td>
              <td>:&nbsp;
              $poskod
                   <!--  <input type="text" $!addressReadonlyClass $!addressReadonly onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" name="TGPoskod" id="TGPoskod" maxlength="5" size="5" value="$poskod" /></td -->
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Negeri</td>
              <td>:&nbsp;&nbsp;$!selectNegeri</td>
            </tr>
                <tr>
              <td valign="top"><font color="red">*</font></td>
              <td>Pegawai Pengendali</td>
              <td>:&nbsp;&nbsp;$!peg_pengendali</td>
            </tr>
              </table>
			</fieldset>	
			
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
			<tr><td align=center colspan=3>
			&nbsp;
			
			</table>
			
			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
				<tr><td align=center colspan=3><input type="button" name="hantar" id="hantar" value="Tandatangan" align="left" onclick="openPopupPNB('$!NO_FAIL','$!id_perbicaraan','$!id_fail')" /></td></tr>
		<!-- 	<tr><td align=center colspan=3><input type="button" name="hantar" id="hantar" value="Tandatangan" align="left" onclick="sendToDigitalSign();" /></td></tr> -->
			
			</table>
	
	</form>
</body>

<script>

function openPopupPNB(NO_FAIL,id_perbicaraan,idfail){
	
	input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
	if (input_box == true) {
	
		try {
		
			window.opener.cetakBorangD_X(NO_FAIL,id_perbicaraan,idfail);
		}
		catch (err) {}
	   	window.close();	
	    return false;
    
	}
}

function sendToDigitalSign() {
	
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiDGCert?command=sahTandatangan";
			document.f1.submit();			
		}
	
}


</script>