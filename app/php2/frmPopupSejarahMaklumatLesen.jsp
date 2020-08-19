<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idHakmilikAgensi"/>
<input type="hidden" name="idPemohon"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>CARIAN MAKLUMAT LESEN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td>No Lesen</td>
          <td>:</td>
          <td><input type="text" name="txtCarianNoLesen" id="txtCarianNoLesen" value=""></td>
        </tr>
        <tr>
          <td>No Fail</td>
          <td>:</td>
          <td>
            <input type="text" name="txtCarianNoFail" id="txtCarianNoFail" value=""></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="doCarian()"/>
            <input type="button" name="cmdKosong" id="cmdKosong" value="Kosongkan" onclick="kosongkan()"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI MAKLUMAT LESEN</b></legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="30%"><strong>Nama Syarikat</strong></td>
          <td width="10%"><strong>No Lesen</strong></td>
          <td width="10%"><strong>Kawasan Dipohon</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
	   	#if ($SenaraiFailSyarikat.size() > 0)
	  		#foreach ($list in $SenaraiFailSyarikat)
			  	#set( $i = $velocityCount )
			    #if ( ($i % 2) != 1 )
			    	#set( $row = "row2" )
			    #else
			    	#set( $row = "row1" )
			    #end
	        <tr>
	          <td class="$row" align="center">$list.bil</td>
	          ##<td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a>
	          ##<br />
	          ##<font class="blink" ><span class="style2">$!list.statusLesen</span></font>
	          ##<font class="blink" ><span class="style2">$!list.statusKelulusanDasar</span></font></td>
	          <td class="$row">$list.noFail</td>
	          <td class="$row">$list.namaPemohon</td>
	          <td class="$row">$list.noLesen</td>
	          <td class="$row">$list.kawasanDipohon</td>
	          <td class="$row" align="center">$list.tarikhTerima</td>
	          <td class="$row">$list.status</td>
	        </tr>
	   		#end
	 	
	 	#else
	        <tr>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1">Tiada Rekod</td>
	          <td class="row1" align="center">&nbsp;</td>
	          ##<td class="row1">&nbsp;</td>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1">&nbsp;</td>
	        </tr>
	 	#end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doCarian() {
	doAjaxCall${formName}("doCarian");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtCarianNoLesen.value = "";
	document.${formName}.txtCarianNoFail.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBPopupSejarahMaklumatLesen";
	document.${formName}.submit();
}
function papar(noFail) {	
	document.${formName}.noFail.value = noFail;
	document.${formName}.actionPopup.value = "paparMaklumatLesen";
	document.${formName}.submit();
}
</script>
