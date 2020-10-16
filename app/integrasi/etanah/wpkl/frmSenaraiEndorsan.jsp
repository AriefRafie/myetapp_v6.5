<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
    <input type="hidden" name="idFail" value="$!idFail"/>
    <input type="hidden" name="idWarta" value="$!idWarta"/>
    <input type="hidden" name="idPenarikanBalik" value="$!idPenarikanBalik"/>
    <input type="hidden" name="idPermohonan" value="$!idPermohonan"/>
    <input type="hidden" name="idHakmilik" value="$!idHakmilik"/>

</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td>
    <fieldset><legend><b>Carian</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            </tr>            
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table>
		</fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>Senarai Fail</b></legend>
        #parse("app/utils/record_paging.jsp")
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="15%"><strong>No. Fail</strong></td>
              <td width="20%"><strong>Nama Kementerian</strong></td>
              <td width="20%"align="center"><strong>Nama Projek</strong></td>
              <td width="7%"align="center"><strong>No Warta</strong></td>
              <td width="10%"align="center"><strong>Id Hakmilik</strong></td>
              <td width="7%"align="center"><strong>Jenis Urusan</strong></td>
              <td width="7%"align="center"><strong>Tarikh Endorsan</strong></td>
            </tr>
          #set ($list = "")
          #if ($SenaraiFail.size() > 0)
          #foreach ($list in $SenaraiFail)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row" align="center">$list.bil</td>
            <!--<td class="$row">$list.noFail</td>-->
               #set($jenisSrkin = "")
   				#if($!list.urusan == "D")         
  					#set($jenisSrkin = "WartaS8")
  				#elseif($!list.urusan == "K")         
  					#set($jenisSrkin = "BorangK")
 				#elseif($!list.urusan == "PD")         
  					#set($jenisSrkin = "TarikBalik")
                 #end   
             
             
             #if($!list.urusan == "D")      
            <td class="$row" style="text-transform:uppercase;"><a href="javascript:popupEtanahWPKLBorangD('$list.idFail','$list.idPermohonan','$list.idWarta','$!jenisSrkin')" class="style1"><font color='blue'>$list.noFail</font></a></td>
            #elseif($!list.urusan == "K")
            <td class="$row" style="text-transform:uppercase;"><a href="javascript:popupEtanahWPKLBorangK('$list.idFail','$list.idPermohonan','$!jenisSrkin','$!list.idPPTHakmilik')" class="style1"><font color='blue'>$list.noFail</font></a></td>
            #elseif($!list.urusan == "PD")
            <td class="$row" style="text-transform:uppercase;"><a href="javascript:popupEtanahWPKLPD('$idFail','$idPermohonan','$!idPenarikanBalik','$!jenisSrkin')" class="style1"><font color='blue'>$list.noFail</font></a></td>
            #end
            <td class="$row">$list.namaKementerian.toUpperCase()</td>
            <td class="$row">$list.namaProjek.toUpperCase()</td> 
            <td class="$row">$list.noWarta</td>
            <td class="$row">$list.idHakmilik</td>
            #if($list.urusan=='D')
            <td class="$row">BORANG D</td>
            #elseif($list.urusan=='K')
            <td class="$row">BORANG K</td>
            #elseif($list.urusan=='PD')
            <td class="$row">PENARIKAN BALIK</td>
            #end
            <td class="$row">$list.tarikhEndors</td>       
            </tr>
          #end
          #else
          <tr>
            <td class="$row" align="center">&nbsp;</td>
            <td colspan="4" class="$row">Tiada Rekod</td>
            </tr>
          #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>

<script>
function carian(){
	document.${formName}.actionPerintah.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txtSimati.value = "";
	document.${formName}.socJenisKp.value = "";
	document.${formName}.txtIcSimati.value = "";
	document.${formName}.submit();
}

function papar(idFail) {
	var url = "../x/${securityToken}/ekptg.view.ppk.integrasi.FrmPopupHistory?idFail="+idFail;
    	var hWnd = window.open(url,'printuser','width=600,height=300, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
       		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
}

function popupEtanahWPKLBorangD(idFail, idPermohonan, idPPTWarta, jenisSkrin) {
	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL?idFail="+idFail+"&idPermohonan="+idPermohonan+"&idPPTWarta="+idPPTWarta+"&jenisSkrin="+jenisSkrin;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function popupEtanahWPKLBorangK(idFail, idPermohonan, jenisSkrin, idPPTHakmilik) {
	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL?idFail="+idFail+"&idPermohonan="+idPermohonan+"&jenisSkrin="+jenisSkrin+"&idPPTHakmilik="+idPPTHakmilik;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function popupEtanahWPKLPD(idFail, idPermohonan, idPenarikanBalik, jenisSkrin) {
	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL?idFail="+idFail+"&idPermohonan="+idPermohonan+"&idPPTPenarikanBalik="+idPenarikanBalik+"&jenisSkrin="+jenisSkrin;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}


</script>