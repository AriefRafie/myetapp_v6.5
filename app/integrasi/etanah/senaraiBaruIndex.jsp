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
			      		<td width="70%"><input name="txtIcSimati" id="txtIcSimati" type="text" value="$!txtIcSimati" size="20" maxlength="20" style="text-transform:uppercase;" ></td>
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
    			<fieldset><legend><b>SENARAI FAIL</b>&nbsp;&nbsp;<a href="javascript:setTable('hakmiliksimpan');" onClick="javascript:getSenaraiPerintah()" class="style1">PERINTAH</a></legend>
		        #parse("app/utils/record_paging.jsp")
        
			<table align="center" width="100%"> 
			    <tr class="table_header">
			    	<td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
                  	<!--  <td width="20%"><strong>No. Fail</strong></td>
			        <td width="25%"><strong>Nama Simati</strong></td>
			        <td width="25%"><strong>Nama Pemohon</strong></td>
			      	<td width="12%"><strong>Tarikh Mohon</strong></td>
			    	<td width="13%"><strong>Tarikh Keputusan</strong></td> -->
			    	<td width="15%"><strong>No. Perserahan</strong></td>
			    	<td width="20%"><strong>No. Fail</strong></td>
			    	<td width="30%"><strong>Nama Simati</strong></td>
			    	<td width="12%"><strong>Tarikh Mohon</strong></td>
			    	<td width="13%"><strong>Tarikh Keputusan</strong></td>	
			       	<td width="5%">&nbsp;</td>
			       	
             		    </tr>
  			#set ( $bil = 0 )			
			  #foreach ($list in $SenaraiFail)
  				#set ( $bil = $bil + 1 )
			    #if ($bil == 0)
					#set( $row = "row1" )
			    #elseif (($bil % 2) != 0)
					#set( $row = "row1" )
			    #else 
					#set( $row = "row2" )
			    #end
			    <tr>
			    	<td class="$row" align="center">$bil. <!-- $!list.getPeganganHakmilik()--> </td>
			    	<td class="$row"><a href="#" onClick="javascript:paparTerperinci()">$!list.get("noPerserahan")</a></td>
			    	<td class="$row">$!list.get("noFail")</td>
			    	<td class="$row">$!list.get("namaSimati")</td>
			    	<td class="$row">$!list.get("tarikhMohon")</td>
			    	<td class="$row">$!list.get("tarikhKeputusan")</td>
			    	<td class="$row">
			    	#if ($!list.get("iSimpan").equals('0'))
			  			<a href="#" onClick="javascript:simpanPerintah()">
	  	       				<img border="0" src="../img/archive.png" />
	  	       			</a>
	  	       		#end
			    	</td>
			    </tr>
			  #end
			  #if($bil==0)
			    <tr>
			    	<td class="$row" align="center">&nbsp;</td>
			    	<td class="$row" align="left" colspan=6>Tiada Rekod</td>
				</tr> 
			  #end
			</table>
			</fieldset>
		</td>
  	</tr>
  	
  	
  	#if($mode=='terperinci')
  	 
  	   	<tr>
    		<td>
    		<fieldset id="hakmiliksimpan"> <legend><b>SENARAI PERINTAH</b></legend>
    			<!--
    			<fieldset id="hakmiliksimpan" style="display:none;"><legend><b>SENARAI FAIL PERINTAH (TELAH DISIMPAN)</b></legend>
		        ##parse("app/htp/utiliti/record_paging.jsp")
        		-->
			<table align="center" width="100%"> 
			    <tr class="table_header">
			    	<td scope="row" width="5%" align="center"><strong>Bil.</strong></td>
                  	<td width="20%"><strong>No. Fail</strong></td>
			        <td width="20%"><strong>Nama OB</strong></td>
			        <td width="15%"><strong>MyID/MyCoID</strong></td>
			        <td width="30%"><strong>ALAMAT</strong></td>
			      	<td width="4%"><strong>JENIS</strong></td>
			    	<td width="3%"><strong>BA</strong></td>
			       	<td width="3%"><strong>BB</strong></td>
			       	
             		    </tr>
  			#set ( $bil1 = 0 )			
			#foreach ($list1 in $SenaraiFail1)
  				#set ( $bil1 = $bil1 + 1 )
				#if ($bil1 == 0)
					#set( $row = "row1" )
			    #elseif (($bil1 % 2) != 0)
					#set( $row = "row1" )
			    #else 
					#set( $row = "row2" )
			    #end
			    <tr>
			    	<td class="$row" align="center">$bil1.</td>
			    	<td class="$row">$list1.get("noFail")</td>
			    	<td class="$row">$!list1.get("nama")</td>
			    	<td class="$row">$!list1.get("noPengenalan")</td>
			    	<td class="$row">$!list1.get("alamat1")$!list1.get("alamat2")$!list1.get("alamat2")$!list1.get("alamat4")</td>
			    	<td class="$row">$!list1.get("jenisPerintah")</td>
			    	<td class="$row">$!list1.get("ba")</td>
			    	<td class="$row">$!list1.get("bb")</td>
			    </tr>
			  #end
			  #if($bil1==0)
			    <tr>
			    	<td class="$row" align="center">&nbsp;</td>
			    	<td class="$row" align="left" colspan=6>Tiada Rekod</td>
				</tr> 
			  #end
			</table>
			</fieldset>
		</td>
  	</tr>
	#end
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

	function carian(){
		if(document.${formName}.txtIcSimati.value == ""){
			alert('Sila masukkan "No. Perserahan " terlebih dahulu.');
	  		document.${formName}.txtIcSimati.focus(); 
			return; 
		}
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("carian");
	}
	
	function paparTerperinci(){
		doAjaxCall${formName}("paparterperinci");
		
	}
	
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
		}else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
		
	}

	function getSenaraiPerintah(){
		doAjaxCall${formName}("paparterperinci");
	}

	function simpanPerintah(){
		document.${formName}.flagAdvSearch.value = "carian";
		doAjaxCall${formName}("simpanperintah");
		
	}
	
	
</script>