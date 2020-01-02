<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td>
    <fieldset><legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" > 
         		<input type="hidden" name="idFail" />
         		<input type="hidden" name="actionPenswastaan" />
                <input type="hidden" name="mode" />
                </td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
              <td width="70%">
              	<input size="11" maxlength="10" type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)">
      <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
            </tr>
            <tr>
              <td scope="row"></td>
              <td>
              	<input type="button" class="stylobutton" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carian()">
                <input type="reset" class="stylobutton" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan"  onClick="javascript:kosongkan()"></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
        </table>
	  </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>SENARAI FAIL</b></legend>
        #parse("app/utils/record_paging.jsp")
        
        <table align="center" width="100%"> 
            <tr>
              <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Fail Baru" onclick="javascript:daftarBaru()"/></td>
            </tr>
            
	<tr class="table_header">
   		<!--<td scope="row" width="4%" align="center"><strong>Bil</strong></td>
     	<td width="20%"><strong>NO FAIL</strong></td>
     	<td width="27%" align="center"><strong>NEGERI</strong></td>
      	<td width="25%" align="center"><strong>TAJUK FAIL</strong></td>
      	<td width="24%" align="center"><strong>STATUS</strong></td> -->
      		<td width="3%"><b>BIL.</b></td>
			<td width="20%"><b>NO FAIL</b></td>
			<td width="37%"><b>TAJUK FAIL</b></td>
			<td width="17%"><b>NEGERI</b></td>
			<td width="23%"><b>STATUS</b></td>
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
            <!--<td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:papar('$list.idFail')" class="style1">$list.noFail</a></td>
            <td class="$row" align="center">$list.negeri</td>
            <td class="$row" align="center">$list.tajuk</td>
            <td class="$row" align="center"></td> -->
			<td class="$row" >$list.bil.</td>
            <td class="$row"><a href="javascript:papar('$list.idFail')" class="style1">$list.noFail</a></td>
            <td class="$row" >$list.tajuk</td>
            <td class="$row" >$list.negeri</td>
            <td class="$row" >$list.status</td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1" align="center">&nbsp;</td>
            <td class="row1">&nbsp;</td>
          </tr>
          #end
        </table>
		</fieldset>
	</td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<script>
function carian(){
	document.${formName}.actionPenswastaan.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionPenswastaan.value = "daftarBaru";
	document.${formName}.mode.value = "new";	
	document.${formName}.submit();
}
function papar(idFail){
	url = "../servlet/ekptg.view.htp.FrmPenswastaan2Servlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.idFail.value = idFail;
	document.${formName}.mode.value = "view";	
	document.${formName}.submit();
}
</script>