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
    	<td>&nbsp;</td>
	</tr>
  <tr>
    <td>
    <fieldset><legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
          <tr>
              <td width="30%" height="24" scope="row" align="right">MyID Simati : </td>
              <td width="70%">$selectJenisKp&nbsp;&nbsp;
                <input name="txtIcSimati" id="txtIcSimati" type="text" value="$txtIcSimati" size="20" maxlength="14" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" > 
         <input type="hidden" name="idPermohonanSimati" />
         <input type="hidden" name="idPermohonan" />
         <input type="hidden" name="idStatus" />
         <input type="hidden" name="mode" />
         <input type="hidden" name="actionPerintah" /></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
              <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
              <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="$txtSimati" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carianSimati()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkanSimati()"></td>
            </tr>
            <!--  <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr> -->
          </tbody>
        </table>
		</fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>SENARAI FAIL</b></legend>
        <!-- TODO ELLY -->
        #parse("app/utils/record_paging.jsp")
        
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="30%"><strong>No. Fail</strong></td>
              <td width="40%"><strong>Nama Simati</strong></td>
              <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
              <td width="15%"><strong>Status</strong></td>
            </tr>
          #set ($list = "")
			#set ( $bil = 0 )			
          #foreach ($list in $SenaraiFail)
				#set ( $bil = $bil + 1 )
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row" align="center">$list.bil.</td>
            #if($list.idPermohonan == '')
            <td class="$row">$list.noFail</td>
            #else
            <td class="$row"><a href="javascript:paparSimati('$list.idPermohonanSimati','$list.idPermohonan','$list.idStatus','$list.idStatusLHDN')" class="style1">$list.noFail</a></td>
            #end
            <td class="$row">$list.namaSimati.toUpperCase()</td>
            <td class="$row" align="center">$list.tarikhMohon</td>
            <td class="$row">$list.keterangan</td>
            </tr>
          #end
          
			  #if($bil==0)
			    <tr>
			    	<td class="$row" align="center">&nbsp;</td>
			    	<td class="$row" align="left" colspan=4>Tiada Rekod</td>
				</tr> 
			  #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
  <!--  <tr>
    	<td align="center">
    	      <input type="button" class="stylobutton100" name="cmdKembali" value="Kembali" onClick="javascript:carian();"/>
    	
    	</td>
	</tr> -->
</table>
	<input type="hidden" name="pagejsp" value="senarai">
	<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch" />

<script>

	function carianSimati(){
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("tambahFail");
		
	}

	function kosongkanSimati() {
		document.${formName}.reset();
		document.${formName}.txtNoFail.value = "";
		document.${formName}.txtPemohon.value = "";
		document.${formName}.txtSimati.value = "";
		document.${formName}.socJenisKp.value = "";
		document.${formName}.txtIcSimati.value = "";
		doAjaxCall${formName}("tambahFail");
	
	}

	function paparSimati(idPermohonanSimati,idPermohonan,idStatus,idx) {
		document.${formName}.actionPerintah.value = "papar";
		if (idStatus == '41' || idStatus == '25'){
			document.${formName}.mode.value = "update";
		} else {
			document.${formName}.mode.value = "viewsimati";
		}
		document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idStatus.value = idStatus;
		doAjaxCall${formName}("paparsimati");
		
	}		

	function kembaliSenaraiPermohonan(id_) {
		//doAjaxCall${formName}("tambahFail");
		doAjaxCall${formName}("");

	}
	
	function simpan(id_){
		if(document.${formName}.txtcukai.value == ""){
			alert('Sila masukkan "Bayaran " terlebih dahulu.');
	  		document.${formName}.txtcukai.focus(); 
			return; 
		}
		if(document.${formName}.txttunggakan.value == ""){
			alert('Sila masukkan " Tunggakan " terlebih dahulu.');
	  		document.${formName}.txttunggakan.focus(); 
			return; 
		}
		if(document.${formName}.txtjumlah.value == ""){
			alert('Sila masukkan " Jumlah Perlu Dibayar " terlebih dahulu.');
	  		document.${formName}.txtjumlah.focus(); 
			return; 
		}
		if(id_ == "")	
			document.${formName}.mode.value = "simpan";
		else
			document.${formName}.mode.value = "update";

		doAjaxCall${formName}("paparsimati","&idmaklumatcukai="+id_);

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
	
</script>