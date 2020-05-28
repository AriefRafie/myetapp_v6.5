<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>
		<td>
			

			<input name="idHakmilik" type="hidden" value="$idHakmilik" />
			<fieldset><legend>MAKLUMAT HAKMILIK</legend>
			<table width="100%" border="0">
				
			  <tr>
			    <td>
				##parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFailReadOnly.jsp")
			    </td>
			  </tr>
  			<tr>
			    <td>
				#parse("app/htp/rekod/frmPendaftaranMaklumatHakmilikReadOnly.jsp")
			    	</td>
			  </tr>
			</table>
			<!-- End of Auto scroll table -->
			</fieldset>

			<fieldset><legend>SENARAI PERGERAKAN</legend>
			<div style="width:100%;height:200;overflow:auto"> 
				<table border="0" width="100%">
			  		<tr>
			    		<td colspan="7">
			            <input type="button" name="Daftar Pinjaman Dokumen" value="Daftar Pinjaman Dokumen" 
			            onclick="tambahPergerakan()" />
			   			</td>
			  		</tr>
			  <tr class="table_header">
			    <td width="3%">Bil.</td>
			    <td width="45%">Urusan</td>
			    <td width="10%">Tarikh Serahan</td>
			    <!-- <td width="14%">Status</td> -->
			    <td width="30%">Kepada</td>
			    <td width="10%">Tarikh Kembali</td>
			    <td width="2%">&nbsp;</td>
			  </tr>
			  #foreach ($senaraiPergerakan in $SenaraiPergerakan)
			  #set( $i = $velocityCount )
			  #if ( ($i % 2) != 1 )
			  #set( $row = "row2")
			  #else
			  #set( $row = "row1")
			  #end
			  <tr class="$row">
			    <td >$senaraiPergerakan.bil</td>
			    #if($senaraiPergerakan.bil !='')
			    <td ><a href="javascript:viewDetailPergerakan('$senaraiPergerakan.idPergerakan')" class="style1 style1">$senaraiPergerakan.keterangan</a></td>
			    #else
			    <td >$senaraiPergerakan.keterangan</td>
			    #end
			    <td >$senaraiPergerakan.tarikh</td>
			    <!--<td width="14%">$senaraiPergerakan.status</td>-->
			    <td >$senaraiPergerakan.kepada</td>
			    <td >$senaraiPergerakan.tarikhKembali</td>
			    <td>
			   	#if($senaraiPergerakan.idPergerakan !=0)
			    	
			   		<a alt="Hapus" href = "javascript:hapus('$senaraiPergerakan.idPergerakan')">
			   			<img border="0" src="../img/delete.gif" />
			   		</a>
			   	#end
    			</td>
			  </tr>
			 #end

			</table>
			</div> <!-- End of Auto scroll table -->
			</fieldset>

		</td>
	</tr>
			  <tr>
			    <td colspan="7"><div align="center">
			      <input type="button" class="stylobutton100" name="btnBack" id="btnBack" value="Kembali" onclick="kembaliSenarai()"/>
			    </div></td>
			  </tr>	
</table>
<script>
	function XtambahPergerakan(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=tambahPergerakanHakmilik";
		document.${formName}.submit();
	}

	function XviewDetailPergerakan(id_){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparPergerakanHakmilikDetail&idPergerakan="+id_;
		document.${formName}.submit();
	}

	function XkembaliSenarai(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=";
		document.${formName}.submit();
	}

	// Diguna jika pautan dari rizab
	function tambahPergerakan(){
		doAjaxCall${formName}("","firstAction=tambahPergerakanHakmilik");
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPergerakanHakmilik&firstAction=tambahPergerakanHakmilik";
		//document.${formName}.submit();
		
	}
	
	// Diguna jika pautan dari rizab
	function kembaliSenarai(){
		doAjaxCall${formName}("","firstAction=");
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPergerakanHakmilik&firstAction=";
		//document.${formName}.submit();
		
	}

	// Diguna jika pautan dari rizab
	function viewDetailPergerakan(id){
		doAjaxCall${formName}("","firstAction=paparPergerakanHakmilikDetail&idPergerakan="+id);
	}
	
	// Diguna jika pautan dari rizab	
	function hapus(id_) {
	    if ( !window.confirm("Maklumat akan dihapuskan.\nAdakah Anda Pasti?") ) return;	
	    doAjaxCall${formName}("hapus","firstAction=hapus&idPergerakan="+id_);
	    
	}

	// Diguna jika pautan dari rizab		
	function kemaskiniPergerakanDetail(id){
		doAjaxCall${formName}("","firstAction=kemaskiniPergerakanHakmilikDetail&idPergerakan="+id);
	}

	// Diguna jika pautan dari rizab		
	function cetakPergerakan(id){
		var url = "../servlet/ekptg.report.htp.BorangPergerakanHakmilik?idPergerakan="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	// Diguna jika pautan dari rizab
	function kembaliSenaraiPergerakan(){
		doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik");
	}

	// Diguna jika pautan dari rizab
	function updatePergerakanDetail(id){
		//VALIDATAION
		 if(document.${formName}.sorDokumen.checked == false){ 
			alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
			document.${formName}.sorDokumen.focus();
			return; 
		 }
		 if(document.${formName}.txtKeterangan.value == ""){ 
			alert('Sila masukkan " Urusan " terlebih dahulu.'); 
			document.${formName}.txtKeterangan.focus();
			return; 
		 }
		 if(document.${formName}.txtKepada.value == ""){ 
			alert('Sila masukkan " Kepada " terlebih dahulu.'); 
			document.${formName}.txtKepada.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhSerah.value == ""){ 
			alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
			document.${formName}.txdTarikhSerah.focus(); 
			return; 
		 }	
		 if(document.${formName}.socStatusS.value == ""){
			alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
			document.${formName}.socStatusS.focus(); 
			return; 
		 }
		   var str1 = document.${formName}.txdTarikhSerah.value; 
		  var dt1 = parseInt(str1.substring(0,2),10); 
		  var mon1 = parseInt(str1.substring(3,5),10)-1; 
		  var yr1 = parseInt(str1.substring(6,10),10);
		  var tarikhSerah = new Date(yr1, mon1, dt1);
		  var str2 = document.${formName}.txdTarikhKembali.value; 
		  var dt2 = parseInt(str2.substring(0,2),10); 
		  var mon2 = parseInt(str2.substring(3,5),10)-1; 
		  var yr2 = parseInt(str2.substring(6,10),10); 
		  var tarikhKembali = new Date(yr2, mon2, dt2); 
		  var currentDate = new Date(); 
		  if (tarikhSerah > currentDate){ 
		  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
		   	 document.${formName}.txdTarikhSerah.focus(); return; 
		  } 
		  if (tarikhKembali > currentDate){ 
		   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
		   	  document.${formName}.tarikhKembali.focus(); return; 
		  } 
		  if (tarikhSerah > tarikhKembali){ 
		      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
		     document.${formName}.tarikhKembali.focus(); return; 
		  }
		 //END OF VALIDATION
		
			doAjaxCall${formName}("","firstAction=updatePergerakanHakmilikDetail&idPergerakan="+id);

	}

	// Diguna jika pautan dari rizab
	function batalKemaskini(id){
		doAjaxCall${formName}("","firstAction=paparPergerakanHakmilik&idPergerakan="+id);
	}

	function tambahPergerakanDetail(idhakmilik){
		//VALIDATAION
		 if(document.${formName}.sorDokumen.checked == false){ 
			alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
			document.${formName}.sorDokumen.focus();
			return; 
		 }
		 if(document.${formName}.txtKeterangan.value == ""){ 
			alert('Sila masukkan " Urusan " terlebih dahulu.'); 
			document.${formName}.txtKeterangan.focus();
			return; 
		 }
		 if(document.${formName}.txtKepada.value == ""){ 
			alert('Sila masukkan " Kepada " terlebih dahulu.'); 
			document.${formName}.txtKepada.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhSerah.value == ""){ 
			alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
			document.${formName}.txdTarikhSerah.focus(); 
			return; 
		 }	
		 if(document.${formName}.socStatusS.value == ""){
			alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
			document.${formName}.socStatusS.focus(); 
			return; 
		 }
		  var str1 = document.${formName}.txdTarikhSerah.value; 
		  var dt1 = parseInt(str1.substring(0,2),10); 
		  var mon1 = parseInt(str1.substring(3,5),10)-1; 
		  var yr1 = parseInt(str1.substring(6,10),10);
		  var tarikhSerah = new Date(yr1, mon1, dt1);
		  var str2 = document.${formName}.txdTarikhKembali.value; 
		  var dt2 = parseInt(str2.substring(0,2),10); 
		  var mon2 = parseInt(str2.substring(3,5),10)-1; 
		  var yr2 = parseInt(str2.substring(6,10),10); 
		  var tarikhKembali = new Date(yr2, mon2, dt2); 
		  var currentDate = new Date(); 
		  if (tarikhSerah > currentDate){ 
		  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
		   	 document.${formName}.txdTarikhSerah.focus(); return; 
		  } 
		  if (tarikhKembali > currentDate){ 
		   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
		   	  document.${formName}.txdTarikhKembali.focus(); return; 
		  } 
		  if (tarikhSerah > tarikhKembali){ 
		      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
		     document.${formName}.txdTarikhKembali.focus(); return; 
		  }
		 //END OF VALIDATION
		doAjaxCall${formName}("","firstAction=tambahPergerakanHakmilikDetail");

	}
	
</script>