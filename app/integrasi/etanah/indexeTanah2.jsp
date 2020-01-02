<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    		<td>&nbsp;</td>
	</tr>
  	<tr>
    		<td>
    			<fieldset><legend><b>CARIAN</b></legend>
        		<table width="100%" align="center" border="0">
          			<tr>
			      		<td width="30%" height="24" scope="row" align="right">No. Perserahan&nbsp;:&nbsp;</td>
			      		<td width="70%"><input name="txtIcSimati" id="txtIcSimati" type="text" value="$!txtIcSimati" size="20" maxlength="14" style="text-transform:uppercase;" ></td>
			    	</tr>
         <input type="hidden" name="idPermohonanSimati" />
         <input type="hidden" name="idPermohonan" />
         <input type="hidden" name="idStatus" />
         <input type="hidden" name="mode" />
         <input type="hidden" name="actionPerintah" />
            
			    	<tr>
			      		<td scope="row"></td>
			      		<td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
						<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkanSimati()"></td>
			    	</tr>
            
        		</table>
			</fieldset>
    		</td>
  	</tr>
  	
  	<tr>
    		<td>
    			<fieldset><legend><b>SENARAI HAKMILIK</b>&nbsp;&nbsp;<a href="javascript:setTable('hakmiliksimpan')">Terperinci</a></legend>
		        #parse("app/utils/record_paging.jsp")
        
			<table align="center" width="100%"> 
			    <tr class="table_header">
			    	<td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
                       		<td width="15%"><strong>Negeri</strong></td>
			        <td width="15%"><strong>Daerah</strong></td>
			        <td width="20%"><strong>Bandar/Pekan/Mukim</strong></td>
			      	<td width="20%"><strong> Hakmilik</strong></td>
			    	<td width="20%"><strong>No. Lot/PT</strong></td>
			       	<td width="5%">&nbsp;</td>
			       	
             		    </tr>
			  #set ($list = "")
			  #foreach ($list in $SenaraiFail)
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
			    	<td class="$row">$list.keterangan</td>
			    	<td class="$row">$list.keterangan</td>
			    </tr>
			  #end
			  #if($list=='')
			    <tr>
			    	<td class="$row" align="center">&nbsp;</td>
			    	<td class="$row" align="left" colspan=6>Tiada Rekod</td>
				</tr> 
			  #end
			</table>
			</fieldset>
		</td>
  	</tr>
  	
  	
  	
  	 
  	   	<tr>
    		<td>
    			<fieldset id="hakmiliksimpan" style="display:none;"><legend><b>SENARAI HAKMILIK (TELAH DISIMPAN)</b></legend>
		        #parse("app/utils/record_paging.jsp")
        
			<table align="center" width="100%"> 
			    <tr class="table_header">
			    	<td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
                       		<td width="15%"><strong>Negeri</strong></td>
			        <td width="15%"><strong>Daerah</strong></td>
			        <td width="20%"><strong>Bandar/Pekan/Mukim</strong></td>
			      	<td width="20%"><strong> Hakmilik</strong></td>
			    	<td width="20%"><strong>No. Lot/PT</strong></td>
			       	<td width="5%"></td>
			       	
             		    </tr>
			  #set ($list = "")
			  #foreach ($list in $SenaraiFail)
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
			    	<td class="$row">$list.keterangan</td>
			    	<td class="$row">$list.keterangan</td>
			    </tr>
			  #end
			  #if($list=='')
			    <tr>
			    	<td class="$row" align="center">&nbsp;</td>
			    	<td class="$row" align="left" colspan=6>Tiada Rekod</td>
				</tr> 
			  #end
			</table>
			</fieldset>
		</td>
  	</tr>
  	 
  	<!--
    	<tr>
    	<td align="center">
    	      <input type="button" class="stylobutton100" name="cmdKembali" value="Kembali" onClick="javascript:carian();"/>
    	
    	</td>
	</tr> -->
</table>
	<input type="hidden" name="pagejsp" value="senarai">
	<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch" />

<script>


	function kosongkanSimati() {
		document.${formName}.reset();
		//document.${formName}.txtNoFail.value = "";
		//document.${formName}.txtPemohon.value = "";
		//document.${formName}.txtSimati.value = "";
		//document.${formName}.socJenisKp.value = "";
		//document.${formName}.txtIcSimati.value = "";
		//doAjaxCall${formName}("tambahFail");
	
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

	//START 2012/12/25
	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}

	function carian(){
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("tambahFail");
		
	}
	
</script>