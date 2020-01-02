<strong><center><font size="3pt">Maklumat Strata</font></center></strong>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="hitButton">
  <input type="hidden" name="idStrata">
  <input type="hidden" name="listIdStrataDel">
</p>

<fieldset>
<legend>
<strong>Carian</strong></legend>
<table width="100%">
	<tr>
		<td>
			<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">Sila masukkan maklumat carian</font></p>
			<table width="100%" border="0" align="center">
				<tr>
				   <td scope="row" align="right">Negeri</td>
				   <td width="1%" scope="row">:</td>
				   <td>$selectNegeriD dan</td>
				</tr>
				<tr>
				 	<td width="29%" scope="row" align="right">No Hakmilik Strata</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNoStrata" type="text" id="paramNoStrata" value="$!paramNoStrata" size="40" /> atau</td>
				 </tr>
				<tr>
				 	<td width="29%" scope="row" align="right">No Lot</td>
				 	<td width="1%" scope="row">:</td>
				 	<td>$selectKodLot <input name="paramNoLot" type="text" id="paramNoLot" value="$!paramNoLot" size="10" /> atau</td>
				 </tr>
			  	 <tr>
				 	<td width="29%" scope="row" align="right">No CF</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNoCF" type="text" id="paramNoCF" value="$!paramNoCF" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Pemaju</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaPemaju" type="text" id="paramNamaPemaju" value="$!paramNamaPemaju" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Skim</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaSkim" type="text" id="paramNamaSkim" value="$!paramNamaSkim" size="40" /></td>
				 </tr>
			  	<tr>
				    <td colspan="2" scope="row">
				      <label></label></td>
				    <td>
				      <label>
				        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
				        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
				        </label>    
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
</table>
</fieldset>
<fieldset>
<legend><strong>Senarai Pembangunan Strata</strong></legend>
	#parse("app/utils/record_paging.jsp")
<table width="100%">
	<p><font color="red">Tanda pada Pilih untuk menghapuskan skim yang tidak berkenaan.</font><br />
	<font color="red">Klik pada pautan Nama Pemilik untuk melihat maklumat terperinci.</font></p>
	<tr>
		<td><!--#parse("app/utils/record_paging.jsp")-->
	      <label>
	        <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="javascript:tambah()"/>
	        #if($current_role == '(Strata) KPP' || $current_role == '(Strata) Pengarah' || $current_role == '(Strata) Penolong Pengarah')
            <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapus()" />
            #end
	       </label>    
	     </td>		  
	</tr>
	<tr>
		<td>
			<table width="100%" cellspacing="2" cellpadding="1" border="0">
				  <tr class="table_header">
                  #if($current_role == '(Strata) KPP' || $current_role == '(Strata) Pengarah' || $current_role == '(Strata) Penolong Pengarah')
				  	<td width="2%" align="center">Pilih</td>
                    #end
				  	<td width="2%" align="center">No.</td>
				  	<td width="5%"align="center">Negeri</td>
                    <td width="10%"align="center">No. Fail Majlis</td>	
				  	<td width="10%"align="center">No. dan Tarikh CF/CCC/No.Kelulusan Khas</td>	
					<td width="5%" align="center">No. Hakmilik</td>
					<td width="15%" align="center">Nama Pemaju</td>
				  	<td width="15%" align="center">Nama Skim</td>
				  	<td width="5%" align="center">No.PT/Lot</td>
				  	<td width="5%" align="center">Status Permohonan Strata</td>
				  </tr>
				  #if($SenaraiFail.size() > 0)
					#foreach ($result in $SenaraiFail )
					#set( $counter = $velocityCount )
					#if ( ($counter % 2) == 0 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end
					
				<tr>
                #if($current_role == '(Strata) KPP' || $current_role == '(Strata) Pengarah' || $current_role == '(Strata) Penolong Pengarah')
					  <td align="center">
	                    <input class="cb" type="checkbox" name="cbsemaks" value="$result.Id" $checked $modeSoc>
					  </td>
                      #end
					  <td class="$row" align="center">
					  #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
					  $!cnt</td>
                      #if($result.Negeri == '')
					  <td class="$row" align="center" style="text-transform:uppercase;">$!result.NegeriHm</td>
                    	#else <!--if($result.NegeriHm == '')-->
					  <td class="$row" align="center" style="text-transform:uppercase;">$!result.Negeri</td>
					  #end
                      <td class="$row" align="center" style="text-transform:uppercase;">$!result.NoFail</td>
					  <td class="$row" align="center">
					  	#if($result.flagCf=='Y')
							$!result.cf
						#else
							$!result.khas
						#end
					  </td>
					  #if($result.noHakmilik == '')
                      <td class="$row" align="center">-</td>
                      #else
					  <td class="$row" align="center" style="text-transform:uppercase;">$result.noHakmilik</td>
                      #end
                      #if($result.NamaPemaju == '')
                      <td class="$row" ><a href="javascript:papar('$result.Id')" class="style1"><font color='blue'>TIADA MAKLUMAT PEMAJU</font></a></td>
                      #else
					  <td class="$row" style="text-transform:uppercase;"><a href="javascript:papar('$result.Id')" class="style1"><font color='blue'>$result.NamaPemaju</font></a></td>
                      #end
					  <td class="$row" style="text-transform:uppercase;">$result.NamaSkim</td>
					  <td class="$row" style="text-transform:uppercase;">$result.KodLot $result.NoLot</td>
					  <td class="$row" style="text-transform:uppercase;">$result.Status</td>
				</tr>
				#end
				#else
					<tr>
						<td colspan="6">Rekod Tidak Dijumpai</td>
					</tr>
				#end
			</table>
		</td>
	</tr>
</table>
<!--<div align="center">       
	<input type="submit" name="cmdCetakLaporan" id="cmdCetakLaporan" value="Cetak Laporan" onclick="viewCetak()"/>
</div>-->
</fieldset>
<input type="hidden" name="mode" />

<script type="text/javascript">
function carian(){
// 	document.${formName}.action.value = "cari";
// 	document.${formName}.submit();
	document.${formName}.hitButton.value = "cari";
	doAjaxCall${formName}("doCarian");
}

//function doChanges() {
//	doAjaxCall${formName}("doChanges");
//}

function tambah(){
	document.${formName}.hitButton.value = "tambah";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}

function hapus(){
	var ids = '';
	var exist = false;
	input_box = confirm("Adakah anda pasti untuk Hapus?");
	if (input_box == true) {
		field_ = document.${formName}.cbsemaks;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				if(ids.length > 0){
					ids = ids + ',' +field_[i].value;
				}else if(ids.length == 0){
					ids = ids + field_[i].value;
				}
	 			//document.${formName}.socJenisTanah.value = field_[i].value;
				exist = true;
			}
		}
		if(exist){
			document.${formName}.listIdStrataDel.value = ids;
			document.${formName}.hitButton.value = "hapus";
			document.${formName}.submit();
		}
/*		else{
			alert("Sila pilih rekod untuk dihapuskan")
		}*/
	}
}

function papar(idStrata) {
	document.${formName}.idStrata.value = idStrata;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "papar";
	document.${formName}.submit();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.paramNoLot.value = "";
	document.${formName}.paramNoCF.value = "";
	document.${formName}.paramNamaPemilik.value = "";
	document.${formName}.paramNamaSkim.value = "";
	document.${formName}.hitButton.value = "kosongkan";
	document.${formName}.submit();
}

function viewCetak() {
	
	var paramNegeri = document.${formName}.paramNegeri.value;
	var paramKodLot = document.${formName}.paramKodLot.value;
	var paramNoLot = document.${formName}.paramNoLot.value;
	var paramNoCF = document.${formName}.paramNoCF.value;
	var paramNamaPemilik = document.${formName}.paramNamaPemilik.value;
	var paramNamaSkim = document.${formName}.paramNamaSkim.value;
	
	var url = "../x/${securityToken}/ekptg.view.str.FrmPopupMaklumatStrataCetak?paramNegeri="+paramNegeri+"&paramKodLot="+paramKodLot+"&paramNoLot="+paramNoLot+"&paramNoCF="+paramNoCF+"&paramNamaPemilik="+paramNamaPemilik+"&paramNamaSkim="+paramNamaSkim;
    var hWnd = window.open(url,'printuser','width=300,height=180, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
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

</script>
