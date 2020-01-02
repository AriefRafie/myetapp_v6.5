  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>

<fieldset>
<legend>MAKLUMAT PILIHAN</legend>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    	<!--<tr>			  
			<td colspan="2" align="center">&nbsp;</td>
      </tr>-->
      <tr >
        <td align="right" width="40%"><div align="right"><strong>Negeri : &nbsp;&nbsp;</strong></div></td>
        <td>$selectNegeri</td>
        <input type="hidden" name="idnegeri" value="$!idnegeri">
      </tr>    
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Daerah : &nbsp;&nbsp;</strong></div></td>
        <td>$selectDaerah</td>
        <input type="hidden" name="iddaerah" value="$!iddaerah">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Bandar/Pekan/Mukim : &nbsp;&nbsp;</strong></div></td>
        <td>$selectMukim</td>
        <input type="hidden" name="idmukim" value="$!idmukim">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Jenis Hakmilik : &nbsp;&nbsp;</strong></div></td>
        <td>$selectJenisHakmilik</td>
        <input type="hidden" name="idjenishakmilik" value="$!idjenishakmilik">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>No. Hakmilik : &nbsp;&nbsp;</strong></div></td>
        <td><input type="text" name="txtNoHakmilik" size="40" onkeyup="validateNumber(this,this.value);" value="$!carianNoHakmilik"/></td>
      </tr>
      <tr>
        <td></td>
        <td><input class=stylobutton name="cari" value="Carian Hakmilik" type="button" onclick="javascript:search_data()">
        <input class=stylobutton value="Kosongkan" onclick="javascript:cancel()" type="button" >        
        </td>
      </tr>
      <tr>			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
    </tbody>
  </table>  
<!--  <input type="text" name="noHakmilik" value="$noHakmilik">		
  <input type="hidden" name="command">
</form>-->
</fieldset>
<fieldset>
<legend>SENARAI CUKAI</legend>

        #parse("app/utils/record_paging.jsp")


<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody>
    <tr>
    	<td colspan="6">&nbsp;&nbsp;&nbsp;</td>
    </tr>
	<tr class="table_header">
	  	<td width="6%"><strong>Bil.</strong></td>
   	  <td width="12%"><strong>Daerah</strong></td>
  	  <td width="18%"><strong>Mukim</strong></td>
   	  <td width="49%"><strong>Kementerian</strong></td>
  	  <td width="15%"><b>No. Hakmilik</b></td>
	</tr>
	#set ($count = 0)
   	#foreach ( $fail in $lists )
  		#set ($count = $count+1)
		#set( $counter = $velocityCount )
		#if ( ($counter % 2) == 0 )
    		#set( $row = "row2" )
		#else
    		#set( $row = "row1" )
		#end
    
  <tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
   	  <td height="25">
		#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
		$cnt	  </td>
   		<td>$fail.nama_daerah</td>
        <td>$fail.nama_mukim</td>
        <td>$fail.nama_kementerian</td>
        <td><a href="javascript:editCukai('$fail.idHakmilikCukai','$fail.idNegeri','$fail.idDaerah','$fail.idMukim','$fail.idKementerian','$fail.no_hakmilik','$fail.idJenisHakmilik')" class="style1" name=>$fail.kod_jenis_hakmilik$fail.no_hakmilik</a></td>
   		</tr> 
	#end
    #if ( $count == 0 )
    	<tr> 
        <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
    	</tr>
   	#end  
	</tbody>
</table>
	<input type="hidden" name="page" value="$page">
    <input type="hidden" name="totalPages" value="$totalPages">
    <input type="hidden" name="startNumber" value="$startNumber">
    <input type="hidden" name="action1" value="$action1">
	<input type="hidden" name="idNegeri">
    <input type="hidden" name="idDaerah">
    <input type="hidden" name="idMukim">
    <input type="hidden" name="idKementerian">
    <input type="hidden" name="noHakmilik">
	<input type="hidden" name="idJenisHakmilik">
    <input type="hidden" name="command1" value="$command1">
  	<input type="hidden" name="pagemode" value="$pagemode">
  	<input type="hidden" name="idHakmilikCukai" />
  	
	 <!-- </form>--->
</fieldset>

<script>
/*function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}*/
function cancel() {
	document.${formName}.reset();
	document.${formName}.command1.value = ""
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";	
	document.${formName}.socNegeri.focus();
	//document.${formName}.action = "";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.command.value = "";
	//document.${formName}.action = "";
	document.${formName}.submit();
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeMukim() {
	doAjaxCall${formName}("doChangeMukim");
}
function doChangeHakmilik() {
	doAjaxCall${formName}("doChangeHakmilik");
}
function editCukai(id,idNegeri,idDaerah,idMukim,idKementerian,noHakmilik,idJenisHakmilik) {
	document.${formName}.idNegeri.value = idNegeri;
	document.${formName}.idDaerah.value = idDaerah;
	document.${formName}.idMukim.value = idMukim;
	document.${formName}.idKementerian.value = idKementerian;
	document.${formName}.noHakmilik.value = noHakmilik;
	document.${formName}.idJenisHakmilik.value = idJenisHakmilik;
	document.${formName}.command1.value = "kemaskiniCukai";
	//document.${formName}.pagemode.value = "viewCukai";	
	document.${formName}.pagemode.value = "viewCukaiV1";	
	document.${formName}.idHakmilikCukai.value = id;	
		
	//document.${formName}.action = "";
	//document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniCukai","idhakmilikcukai="+id);
	doAjaxCall${formName}("kemaskiniCukai");
}

function Kembali1(id) {
	//document.${formName}.action = "";
	//document.${formName}.command1.value = "kemaskiniCukai";
	//document.${formName}.pagemode.value = "viewCukaiV1";
	//doAjaxCall${formName}("senarai","idhakmilikcukai="+id);
	doAjaxCall${formName}("senarai","idhakmilikcukai="+id);
}




//frmCukaiKemaskiniV1.jsp
function Kemaskini() {
	//document.${formName}.action = "";
	document.${formName}.pagemode.value = "editCukai";
	//document.${formName}.command1.value = "kemaskiniCukai";
	//document.${formName}.submit();
	doAjaxCall${formName}("kemaskiniCukai");
}

function Simpan() {

	if(document.${formName}.txtCukaiTerkini.value == "0.00"){
		alert('Sila masukkan " Jumlah Cukai Terkini " terlebih dahulu.');
  		document.${formName}.txtCukaiTerkini.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	//document.${formName}.command1.value = "kemaskiniCukai";
	document.${formName}.pagemode.value = "simpan";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("kemaskiniCukai");
}

//[Kembali]
function Kembali() {
	doAjaxCall${formName}("kembali");
}

function validateNumber(elmnt,content) {
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function addText(field) {
	if(field.value==""){
		field.defaultValue="";}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function Check() {
	var a = parseFloat(document.${formName}.txtCukaiTerkini.value);
	var b = parseFloat(document.${formName}.txtCukaiTaliAir.value);
	var c = parseFloat(document.${formName}.txtCukaiParit.value);
	var x = parseFloat(document.${formName}.cukaiTerkini.value);
	var y = parseFloat(document.${formName}.cukaiTaliAir.value);
	var z = parseFloat(document.${formName}.cukaiParit.value);
	
	if(a > x || a < x ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
	if(b > y || b < y ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
	if(c > z || c < z ){
  		document.${formName}.idHakmilikCukai.value = "0";
	}
}


</script>
