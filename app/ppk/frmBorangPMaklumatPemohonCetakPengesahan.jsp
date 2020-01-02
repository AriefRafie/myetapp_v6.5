
#foreach ($fail in $DetailPemohon)
	#set ($idPemohon = $fail.id_pemohon)
	#set ($tarikhMohon = $fail.tarikhmohon)
	#set ($icPemohon = $fail.noKpBaruPemohon)
	#set ($nokpbarusimati = $fail.noKpBaru)
    #set ($nokplamasimati = $fail.noKpLama)
    #set ($nokplainsimati = $fail.noKpLain)    
	#set ($namaPemohon = $fail.namaPemohon)
	#set ($noPermohonanOnline = $fail.noPermohonan)
	#set ($nofail = $fail.nofaillama)
    #set ($idfaillama = $fail.idfaillama)
	#set ($daerah = $fail.namadaerah)
    #set ($nosubjaket = $fail.nosubjaket)
	#set ($negeri = $fail.namanegeri)
	#set ($pemohon = $fail.namaPemohon)
    #set ($hartatinggal = $fail.hartatinggal)
    #set ($batalpentadbir = $fail.batalpentadbir)
    #set ($batalamanah = $fail.batalamanah)
    #set ($lantikamanah = $fail.lantikamanah)
    #set ($lantikpentadbir = $fail.lantikpentadbir)
    #set ($perintahlama = $fail.perintahlama)
    #set ($perintahbaru = $fail.perintahbaru)
    #set ($idPermohonan = $fail.idPermohonan)
    #set ($no_kp_baru_sm = $fail.no_kp_baru_sm)
    #set ($no_kp_lain_sm = $fail.no_kp_lain_sm)
    #set ($no_kp_lama_sm = $fail.no_kp_lama_sm)    
    
    
    
#end
<input type="hidden" name="idPemohon" value="$idPemohon">
<input type="hidden" name="noPermohonanOnline" value="$noPermohonanOnline">
<input type="hidden" name="idfaillama" value="$idfaillama">
<input type="hidden" name="idPermohonan" value="$idPermohonan">
<input type="hidden" name="idPermohonanBaru" id="idPermohonanBaru" value="$idPermohonanBaru">
<input type="hidden" name="idPermohonanSimati" value="$idpermohonansimati">
<input type="hidden" name="idSimati" value="$idSimati">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset>
<table width="100%">
<tr>
<td colspan="2" align="center"><font color="red"><b>PERHATIAN</b></font><br><br>
Sila gunakan MyID atau No. Rujukan Online untuk mengemaskini permohonan anda.</td>
</tr>
<tr>
<td height="20px" colspan="2">&nbsp;</td>
</tr>
<tr>
<td align="right" width="30%">Nama : </td>
<td width="70%"><b>$!namaPemohon.toUpperCase()</b></td>
</tr>
<tr>
<td align="right">MyID Pemohon : </td>
<td><b>
#if ($!nokpbarusimati!="")
$!nokpbarusimati 
#elseif ($!nokplamasimati!="")
$!nokplamasimati.toUpperCase()
#elseif ($!nokplamasimati!="")
$!nokplainsimati.toUpperCase()
#end 
</b></td>
</tr>
<tr>
<td align="right">MyID Simati : </td>
<td><b>
#if ($!no_kp_baru_sm!="")
$!no_kp_baru_sm 
#elseif ($!no_kp_lama_sm!="")
$!no_kp_lama_sm.toUpperCase()
#elseif ($!no_kp_lain_sm!="")
$!no_kp_lain_sm.toUpperCase()
#end  
</b></td>
</tr>
<tr>
<td align="right">No Rujukan Online: </td>
<td><b>$!noPermohonanOnline.toUpperCase()</b></td>
</tr>
<tr>
<td align="right">Tarikh Mohon: </td>
<td><b>$!tarikhMohon</b></td>
</tr>
<tr>
	<td colspan="2">
		<font color="red">
		PERHATIAN :<br>
		Sila lengkapkan maklumat permohonan Tuan/Puan dengan menekan butang [Borang P] di sebelah.
		Sila gunakan MyID atau No. Rujukan Online untuk mengemaskini permohonan anda.
		</font>
	</td>

</tr>
<tr>
<td align="right" height="50px" colspan="2">
<!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSlip('$noPermohonanOnline')"> -->
<input type="button" name="cmdSeterusnya" value="Borang P" onClick="getSeterusnyaP()">
 <!-- <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onClick="kembali()">-->
  </td>
</tr>
</table>

<input type="hidden" name="action"/>
<input type="hidden" name="mode"/>

<script>
function cetakSlip(noPermohonanOnline) {
    var url = "../servlet/ekptg.report.ppk.PermohonanOnline?NoPermohonan="+noPermohonanOnline;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    /*if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
}

function getSeterusnyaP() {
	document.${formname}.method="post";
	document.${formName}.mode.value ="Htaamview";
	document.${formname}.action="?_portal_module=ekptg.view.ppk.FrmPrmhnnStatusPengunaOnline";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

</script>