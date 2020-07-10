<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
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
 
#set($noPengenalanPemohon = "")
#set($hubSimatiPemohon = "")
#set($jenisPengenalan = "")

<body>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI NOTIS PENGELUARAN GERAN</font></legend>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
				<tr>
					<td align="right">No Fail</td>
					<td>:</td>
					<td colspan="2">
						<input type="text" name="noFail" value="$!noFail" readonly="readonly" size="40" style="text-transform:uppercase;"/>
						<!--  #if($!semakPerintah=='ada')
							<br /><font color="#FF0000" size="1"><i>Butiran perintah telah dihantar.</font>
						#end-->
					</td>
				</tr>
				<tr>
					<td align="right">Nama Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaSimati" value="$!namaSimati" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">Nama Lain Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="namaSimatiLain" value="$!namaSimatiLain" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">MyID Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiBaru" value="$!noKPSimatiBaru" size="25" maxlength="14" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">I.C. Lama Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiLama" value="$!noKPSimatiLama" size="25" readonly="readonly" /></td>
				</tr>
				<tr>
					<td align="right">No. Pengenalan Lain Simati</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="noKPSimatiLain" value="$!noKPSimatiLain" size="25" readonly="readonly" /></td>
				</tr>			
				<tr>
					<td align="right">Tarikh Mati</td>
					<td>:</td>
					<td width="20px"><input type="text" name="tarikhMati" value="$!tarikhMati" size="30" maxlength="30" readonly="readonly" /></td>
					<td align="left">
						<img border="0" width="16" height="18" src="../../img/calendar.gif" />
					</td>
				</tr>
				<tr>
					<td align="right">Tarikh Perintah</td>
					<td>:</td>
					<td width="20px"><input type="text" name="tarikhPerintah" value="$!tarikhPerintah" size="30" maxlength="30" readonly="readonly" /></td>
					<td align="left">
						<img border="0" width="16" height="18" src="../../img/calendar.gif" />
					</td>
				</tr>
				<tr>
					<td align="right">Waris Yang Dapat Pembahagian</td>
					<td>:</td>
					<td colspan="2">
						<input type="text" name="namaOBHAPerintah" value="$!namaOBHAPerintah" size="40" style="text-transform:uppercase;" readonly="readonly" />
						#if ($jenisPerintah)
							<font color="#FF0000">( $!jenisPerintah )</font>
						#end
					</td>
                    
				</tr>
                <tr>
					<td align="right">Kod Pejabat</td>
					<td>:</td>
					<!-- #if ($!namaPejabat) #set ($kP=$!kodPejabat) #set ($nP=$!namaPejabat) #else #set ($kP='0') #set ($nP='Tiada Maklumat') #end
					<td colspan="2"><input type="text" name="kodPejabat" value="$!kP" readonly="readonly" size="9"><br /><font color="FF0000"><i> - $!nP</i></font></td> -->
					
					<td colspan="2">
					
					
					<input type="text" name="kodPejabat" value="$!kP" readonly="readonly" size="50"><br /><font color="FF0000"><i> - $!nP</i></font></td> 
				</tr>	
			</table>
		</fieldset>
		<table border="0" cellpadding="1" cellspacing="1" width="100%">
			<!-- <tr>
				<td align="left">
					<font color="#FF0000" size="1"><i>Perhatian</font>
					<font size="1"> : Keputusan semakan yang dihantar ke Mahkamah Tinggi akan mengambil masa sekurang-kurangnya 2 hari.</i></font>
				</td>
			</tr> -->
			<tr>
				<td align="center">
					<input style="cursor: pointer" type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
					<!--  #if ($!semakPerintah == 'tiada')-->
						<input style="cursor: pointer" type="button" name="hantar" id="hantar" value="Hantar" align="left" onClick="javascript:hantarPermohonan()" />
					<!-- #end  -->
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="blueCardId" id="blueCardId" value="$!blueCardId">
		<input type="hidden" name="idPerintah" id="idPerintah" value="$!idPerintah">
		<input type="hidden" name="idpermohonan" id="idPerintah" value="$!idPermohonan">
		<input type="hidden" name="jenisPerintah" id="jenisPerintah" value="$!jenisPerintah">
	</form>
</body>

<script>
function hantarPermohonan() {
	if(document.f1.noFail.value==""){
		alert('Sila pastikan No. Fail telah diisi');
		return;
	} else if(document.f1.namaSimati.value==""){
		alert('Sila pastikan Nama Simati telah diisi');
		return;
	} else if(document.f1.noKPSimatiBaru.value==""){
		alert('Sila pastikan MyId Simati telah diisi');
		return;
	} else if(document.f1.tarikhMati.value==""){
		alert('Sila pastikan Tarikh Mati telah diisi');
		return;
	} else if(document.f1.tarikhPerintah.value==""){
		alert('Sila pastikan Tarikh Perintah telah diisi');
		return;
	} else if(document.f1.namaOBHAPerintah.value==""){
		alert('Sila pastikan Waris Yang Dapat Pembahagian telah diisi');
		return;
	} else if(document.f1.kodPejabat.value==""){
		alert('Kod Pejabat tiada data. Sila pastikan unit pejabat anda ditetapkan dengan betul supaya maklumat dihantar adalah tepat');
		return;
	} else {
		input_box = confirm("Sila pastikan butiran yang dihantar adalah tepat!");
		if (input_box == true) {
			document.f1.method="post";
			document.f1.action="ekptg.view.ppk.FrmIntegrasiPerintah?command=hantarPerintah";
			document.f1.submit();			
		}
	}
}

function tutupTetingkap() {
	window.close();
}
</script>