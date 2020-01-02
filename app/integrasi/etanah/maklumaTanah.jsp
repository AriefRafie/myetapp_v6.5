<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#if($!modepopup.equals('ya'))
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />
#end
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="modepopup" value='$!modepopup'>
	<input type="hidden" name="txtnoperserahanetanah2" value="$!txtnoperserahanetanah2"  style="text-transform:uppercase;" />
	
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    		<td>&nbsp;</td>
	</tr>
  	<tr>
    		<td>
    			<fieldset><legend><b>HARTA TAK ALIH (ADA HAKMILIK)</b></legend>
        		<table width="100%" align="center" border="0">
          			<tr>
			      		<td width="50%" valign="top" >
			      			<table width="100%" border="0">                                                                                      
                            	<tr>
                               		<td width="1%" valign="top" class="style45">*</td>
                                 	<td width="29%" class="style38">
                                 		<div align="right" class="style44">
                                        	<div align="left">Negeri</div>
                                  		</div>
                                  	</td>
                                 	<td width="1%"><div align="right">:</div></td>
                                    <td width="69%"> </td>
                            	</tr>
                        	</table>                      
			      		</td>
			      		<td width="50%" valign="top" >
							<table width="100%" border="0">
                          		<tr>
                               		<td width="1%" valign="top" class="style45"></td>
                                	<td width="29%" class="style38"><div align="left">Kategori Tanah</div></td>
                                    <td><div align="right">:</div></td>
                                    <td width="70%"> </td>
                            	</tr>
                        	</table>  			      		
			      		</td>
			    	</tr>
  			    	<tr>
			      		<td scope="row"></td>
			      		<td>
			      			<input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
							<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkanSimati()">
						</td>
			    	</tr>
            
        		</table>
			</fieldset>
    		</td>
  	</tr>
  	
 
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
		if(document.${formName}.txtIcSimati.value == ""){
			alert('Sila masukkan " No. Perserahan " terlebih dahulu.');
	  		document.${formName}.txtjumlah.focus(); 
			return; 
		}
		
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("carianhakmilik");
		
	}

	function simpanHakmilik(idn,idd,idm,idj,no,per){
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("simpanhakmilik","idnegeri="+idn+"&iddaerah="+idd+"&idmukim="+idm+"&idjenis="+idj+"&nohakmilik="+no+"&noperserahan="+per);
		
	}
	
	function salinHakmilik(idn,idd,idm,idj,no,per){
		//socNegeriHtaam
		//txtnoperserahanetanah2
		//window.opener.setTanahEtanah2(idn,idd,idm,idj,no,per);
		//document.${formName}.flagAdvSearch.value = "carian";
		//doAjaxCall${formName}("simpanhakmilik","idnegeri="+idn+"&iddaerah="+idd+"&idmukim="+idm+"&idjenis="+idj+"&nohakmilik="+no+"&noperserahan="+per);
		
	}	
</script>