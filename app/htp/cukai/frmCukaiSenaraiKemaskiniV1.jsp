<!--frmCukaiSenaraiKemaskiniV1.jsp-->
  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			
			<fieldset><legend>MAKLUMAT PILIHAN</legend>
				<table border="0" width="100%">
			        <tr>
					    <td width="29%"><div align="right">Negeri</div></td>
					    <td width="1%">:</td>
					    <td width="70%">$selectNegeri</td>
					    <input type="hidden" name="idnegeri" value="$!idnegeri">
					</tr>
			        <tr>
					    <td width="29%"><div align="right">Daerah</div></td>
					    <td width="1%">:</td>
					    <td width="70%">$selectDaerah</td>
					    <input type="hidden" name="iddaerah" value="$!iddaerah">
					</tr>
			        <tr>
					    <td width="29%"><div align="right">Bandar/Pekan/Mukim</div></td>
					    <td width="1%">:</td>
					    <td width="70%">$selectMukim</td>
					    <input type="hidden" name="idmukim" value="$!idmukim">
					</tr>
			        <tr>
					    <td width="29%"><div align="right">Jenis Hakmilik</div></td>
					    <td width="1%">:</td>
					    <td width="70%">$selectJenisHakmilik</td>
					    <input type="hidden" name="idjenishakmilik" value="$!idjenishakmilik">
					</tr>
					<tr>
					    <td width="29%"><div align="right">No. Hakmilik </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtNoHakmilik" size="44" value="$!carianNoHakmilik"/></td>
					</tr>
		        	<tr>
					    <td width="29%"><div align="right">No. Lot/PT </div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input type="text" name="txtNoLot" size="44" value="$!carianNoLot"/></td>
					</tr>
					
					<tr>
				    <td width="29%"><div align="right">
				      <div align="right">Tahun</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">
					#set ( $selected = "" )
					<select class="autoselect" name="tahuncukai">
				   		<option value="0" $selected>SILA PILIH</option>
				   		#set ( $ints = $!tahuncukai + 2 )
				   		#foreach ( $y in [2007..$ints] )
				   		#if ( $y == $tahunparam)
				   			#set ( $selected = "selected" )
				   		#else
				   			#set ( $selected = "" )
				   		#end
				   		<option value="$y" $selected>$y</option>
				   		#end
					</select>				
			    
				    </td>
				  </tr>
					<tr>
						<td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
					    	<input class="stylobutton100"  name="cari" value="Carian" type="button" onclick="javascript:search_data()"/>
					      	<input class="stylobutton100"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" /></td>
					</tr>
			      	<!-- 
			      		<tr>			  
						<td colspan="2" align="center">&nbsp;</td>
					</tr> -->
			  	</table>  
			</fieldset>
		</td>
	</tr>

	<tr>		
		<td>
			<fieldset><legend>SENARAI CUKAI</legend>
			
			        #parse("app/utils/record_paging.jsp")		
			
				<table border="0"  width="100%">
				    <tr>
				    	<td colspan="6">&nbsp;&nbsp;&nbsp;</td>
				    </tr>
					<tr class="table_header">
						<td width="5%"><strong>Bil.</strong></td>
				   	  	<td width="15%"><strong>Negeri</strong></td>
				   	  	<td width="15%"><strong>Daerah</strong></td>
				  	  	<td width="15%"><strong>Bandar/Pekan/Mukim</strong></td>
				   	  	<td width="35%"><strong>Kementerian</strong></td>
				  	  	<td width="15%"><b>Jenis dan No. Hakmilik</b></td>
					</tr>
					#set ($count = 0)
				   	##foreach ( $fail in $lists )
				   	#foreach ( $fail in $SenaraiFail )
				   	
				  		#set ($count = $count+1)
						#set( $counter = $velocityCount )
						#if ( ($counter % 2) == 0 )
				    		#set( $row = "row2" )
						#else
				    		#set( $row = "row1" )
						#end
				    
				  	<!-- <tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'"> -->
				  	<tr class="$row">
				   		<td height="25">
						#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
						<a href="javascript:editCukai('$fail.idHakmilikCukai','$fail.idNegeri','$fail.idDaerah','$fail.idMukim','$fail.idKementerian','$fail.no_hakmilik','$fail.idJenisHakmilik')" >$cnt.</a></td>
				   		<td><a href="javascript:editCukai('$fail.idHakmilikCukai','$fail.idNegeri','$fail.idDaerah','$fail.idMukim','$fail.idKementerian','$fail.no_hakmilik','$fail.idJenisHakmilik')" class="style1" >$fail.nama_negeri</a></td>
				   		<td>$fail.nama_daerah</td>
				        <td>$fail.nama_mukim</td>
				        <td>$fail.nama_kementerian</td>
				        <td>$fail.kod_jenis_hakmilik$fail.no_hakmilik</td>
				   	</tr> 
					#end
				    #if ( $count == 0 )
				    <tr> 
				        <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
				    </tr>
				   	#end  
					
				</table>
					<input type="hidden" name="page_" value="$page">
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
			  	
			</fieldset>
		</td>
	</tr>	
	
</table>

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
		//document.${formName}.command.value = "";
			//document.${formName}.action = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("doChange");
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
	
	/* BUG FIX. 
	 * 25/11/2014
	 * Syaz
	 * Comment this javascript Leh error.
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
	*/
	
}

	//semakan Tarikh semasa
	function validateTarikhSemasa(inputfield) {
		var today = new Date();	
		dari_bulan = inputfield.value.substring(3,5);
		dari_hari = inputfield.value.substring(0,2);
		dari_tahun = inputfield.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
		var myDate = Date.parse(daritemp);
	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		inputfield.value="";
	  		inputfield.focus();
	 		return;
	 	}
	
	}
	
		function kirakira(i){
		var terkini = checkNumericReturn(document.${formName}.txtCukaiTerkini.value) * 1;
		var taliair = checkNumericReturn(document.${formName}.txtCukaiTaliAir.value) * 1;
		var parit = checkNumericReturn(document.${formName}.txtCukaiParit.value) * 1;
		var tunggakan = checkNumericReturn(document.${formName}.txtTunggakkan.value) * 1;
		var denda = checkNumericReturn(document.${formName}.txtDenda.value) * 1;
		var lain = checkNumericReturn(document.${formName}.txtBayaranLain.value) * 1;
		var pengurangan = checkNumericReturn(document.${formName}.txtPengurangan.value) * 1;
		var pengecualian = checkNumericReturn(document.${formName}.txtPengecualian.value) * 1;
		var lebihan = checkNumericReturn(document.${formName}.txtLebihan.value) * 1;

		var perluBayar = terkini + taliair + parit + tunggakan + denda + lain + pengurangan;
		var tolak = pengecualian + lebihan;
		var jumBayaran = perluBayar - tolak;
		document.${formname}.txtKenaBayar.value = jumBayaran.toFixed(2);
		
	}
	
	function checkNumeric(objName){
    	var lstLetters = objName;
    	var lstReplace = lstLetters.replace(/\,/g,'');

  	}
  	
  	function checkNumericReturn(objName){
    	var lstLetters = objName;
    	var lstReplace = lstLetters.replace(/\,/g,'');
    	return lstReplace;
 
  	}
</script>
