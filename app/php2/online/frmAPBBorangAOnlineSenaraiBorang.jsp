<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode" />
  <input name="idBorangA" type="hidden" id="idBorangA" value="$idBorangA" />
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="idJadualKeduaLesen" type="hidden" id="idJadualKeduaLesen" value="$idJadualKeduaLesen"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idfail" type="hidden" id="idfail" value="$idFail"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      
      <table width="100%" align="center" border="0">
        <tr>
          <td></td>
          <td width="30%" height="24" scope="row" align="right">Nama Pemegang Lesen </td>
          <td> :</td>
          <td width="70%">$namaPemohon</td>
        </tr>
        <tr>
          <td></td>
          <td width="30%" height="24" scope="row" align="right"> No. Lesen  </td>
          <td>:</td>
          <td width="70%">$noLesen</td>
        </tr>
      <!--  <tr>
              <td width="8%">&nbsp;</td>
              <td width="22%" align="right">Bulan</td>
              <td width="1%">:</td>
              <td width="69%">$selectBulanList</td>
          </tr>  
        <tr>
          <td></td>
          <td height="24" scope="row" align="right">Tahun </td>
          <td>:</td>
          <td><input name="txtTahun2" type="text" id="txtTahun2" value="$tahun" size="10" onkeyup="validateNumber(this,this.value);"></td>
        </tr>
          
        <tr>
          <td></td>
          <td scope="row"></td>
          <td>&nbsp;</td>
                 </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
          <td></td>
          <td></td>
        </tr>
         --> 
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI MAKLUMAT PENGAMBILAN PASIR</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarMaklumatPasir('$idJadualKeduaLesen')"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="9%" align="center"><strong>Bil</strong></td>
          <td width="42%"><strong>Bulan</strong></td>
          <td width="49%"><strong>Tahun</strong></td>
        </tr>
        #set ($listBorangA = "")
        #if ($SenaraiFailBorangA.size() > 0)
        #foreach ($listBorangA in $SenaraiFailBorangA)
        #if ($listBorangA.bil == '')
        #set( $row = "row1" )
        #elseif (($listBorangA.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$listBorangA.bil</td>
          <td class="$row"><a href="javascript:paparMaklumatPasir('$listBorangA.idBorangA','$idJadualKeduaLesen')" class="style1">$listBorangA.bulan</a></td>
          <td class="$row">$listBorangA.tahun</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.submit();
}
function kosongkan1() {
	document.${formName}.reset();
	document.${formName}.socBulanList.value = "";
	document.${formName}.txtTahun2.value = "";
	document.${formName}.submit();
}
function paparMaklumatPasir(idBorangA,idJadualKeduaLesen) {

	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.mode.value = "view";
	document.${formName}.idJadualKeduaLesen.value = idJadualKeduaLesen;
	document.${formName}.idBorangA.value = idBorangA;
	document.${formName}.submit();
}
function daftarMaklumatPasir(idJadualKeduaLesen){
	
	document.${formName}.actionOnline.value = "doMaklumatPasir";
	document.${formName}.idJadualKeduaLesen.value = idJadualKeduaLesen;	
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

</script>
