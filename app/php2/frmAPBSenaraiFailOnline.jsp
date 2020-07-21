<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Permohonan Online : </td>
          <td width="70%"><input name="txtNoPermohonan" id="txtNoPermohonan" type="text" value="$txtNoPermohonan" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="actionOnline" />
            <input type="hidden" name="idPemohon" />
            <input type="hidden" name="idUrusan" />
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txtTarikhTerima" id="txtTarikhTerima" value="$txtTarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN ONLINE</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Permohonan</strong></td>
          <td width="30%"><strong>Nama Pemohon/Syarikat</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Kawasan Dipohon</strong></td>
          <td width="10%"><strong>Jenis Permohonan</strong></td>
          <td width="8%"><strong>Jenis Lesen</strong></td>
          <td width="10%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
   	#if ($SenaraiFail.size() > 0)
  		#foreach ($list in $SenaraiFail)
		  	#set( $i = $velocityCount )
		    #if ( ($i % 2) != 1 )
		    	#set( $row = "row2" )
		    #else
		    	#set( $row = "row1" )
		    #end
    
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idPermohonan')" class="style1">$list.noPermohonan</a>
          <br />
          <font class="blink" ><span class="style2">$!list.statusLesen</span></font>
          <font class="blink" ><span class="style2">$!list.statusKelulusanDasar</span></font></td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row">$list.kawasanDipohon</td>
          <td class="$row">PERMOHONAN BARU</td>
          <td class="$row">LESEN PASIR DASAR LAUT</td>
          <td class="$row">$list.status</td>
        </tr>
   		#end
 	
 	#else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
 	#end
      </table>
      </fieldset></td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<script>
function carian(){
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoPermohonan.value = "";
	document.${formName}.txtTarikhTerima.value = "";
	document.${formName}.submit();
}
function papar(idFail,idUrusan,idPermohonan) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idUrusan.value = idUrusan;
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.actionOnline.value = "daftar";
	document.${formName}.submit();
}
</script>
