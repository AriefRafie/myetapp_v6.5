<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>
<fieldset> 
	<legend>Carian</legend>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
			    <td width="29%"><div align="right">Negeri</div></td>
			    <td width="1%">:</td>
			    <td width="70%">$socNegeri</td>
		  	</tr>
		  	<tr>
			    <td width="29%"><div align="right">No. Fail</div></td>
			    <td width="1%">:</td>
			    <td width="70%"><input name="noFail" type="text" size="43" maxlength="400" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
		  	</tr>
		  	<tr>
			    <td width="29%"><div align="right">Tajuk Fail</div></td>
			    <td width="1%">:</td>
			    <td width="70%"><input name="namaFail" type="text" size="43" maxlength="300" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
		  	</tr>
		  <tr>
		    <td width="29%">&nbsp;</td>
		    <td width="1%">&nbsp;</td>
		    <td width="70%">
		    	<input class="stylobutton100" name="cari" value="Cari" type="button" onclick="javascript:findFail()" />
		      	<input class="stylobutton100" name="cmdKosongkan" value="Kosongkan" type="reset" /></td>
		  </tr>		  	
	  
	  </table>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
<table width="100%" class="header">
<tr>
  <td><table width="100%" class="header">
    
    <tr>
      <td colspan="5">#parse("app/utils/record_paging.jsp")</td>
       </tr>
    <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="20%"><b>No. Fail</b></td>
        <td width="37%"><b>Tajuk Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="23%"><b>Status</b></td> 
    </tr>
    #set ($list = "")
    #set ( $cnt = 0 )	
    #if ($lists.size() > 0)
    #foreach ($senarai in $lists)
    #set ( $cnt = $cnt + 1 )
    #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
    	#set( $row = "row2" )
    #else
    	#set( $row = "row1" )
    #end
    #set($s=$!senarai.bil)
    #set($a=$!senarai.permohonan.getNamaNegeri())
    <tr>
      <td class="$row">$cnt.</td>
      <td class="$row"><a href="javascript:papar('$!senarai.permohonan.getIdPermohonan()','$!senarai.getIdHtpPermohonan()')" class="style1">$!senarai.permohonan.pfdFail.getNoFail()</a> </td>
      <td class="$row">$!senarai.permohonan.getTujuan()</td>
      <td class="$row">$a</td>
      <td class="$row">$!senarai.statusPermohonan</td>
    </tr>
    #end
    #else
  <tr>
    <td colspan="4" class="row1">Tiada rekod.</td>
  </tr>
    #end
  </table></td>
</tr>
</table>
</fieldset>
		</td>
	<tr/>
</table>

<script>
function findFail(){
	doAjaxCall${formName}("searchFail");
}
function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}
function papar(id,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
}
function simpanRundingan(){
	if(document.${formName}.keputusan.value == ""){
		alert('Sila pilih " Keputusan " terlebih dahulu.');
  		document.${formName}.keputusan.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanRundingan");
}
function updateRundingan(){
	doAjaxCall${formName}("updateRundingan");
}
function kemaskiniRundingan(){
	doAjaxCall${formName}("kemaskiniRundingan");
}

	function textCounter(field, countfield, maxlimit) {
		if (field.value.length > maxlimit) // if too long...trim it!
			field.value = field.value.substring(0, maxlimit);
			// otherwise, update 'Baki Aksara' counter
		else 
			countfield.value = maxlimit - field.value.length;
	}
	
	function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	
	}

//paging number

function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=perakuanPembelian&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step2(idPermohonan){
	//doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=maklumatTanah&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step1(idPermohonan,idhtp){
	//doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

</script>