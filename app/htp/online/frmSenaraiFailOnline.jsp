<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" >
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
	<tr>
		<td>
    		<fieldset><legend><b>CARIAN</b></legend>
	        	<table width="100%" align="center" border="0">
				   <tr>
				    <td width="29%"><div align="right">Jenis Pajakan</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectSuburusan</td>
				  </tr>			        	
				  <tr>
				    <td width="29%"><div align="right">Negeri</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socNegeri</td>
				  </tr>
	#if ($flagAdvSearch == 'Y')			      
				  <!--<tr>
				    <td width="29%"><div align="right">Daerah</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socDaerah</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">
				      <div align="right">Bandar/Pekan/Mukim</div>
				    </div></td>
				    <td width="1%">:</td>
				    <td width="70%">$socMukim</td>
				  </tr>-->
				  <tr>
				    <td width="29%"><div align="right">Kementerian</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socKementerian</td>
				  </tr>
				  <tr>
				    <td width="29%"><div align="right">Agensi</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!socAgensi</td>
				  </tr>
	#end
	            	<tr>
		            	<td width="29%" scope="row" align="right">No. Fail</td>
		            	<td width="1%">:</td>	            	
		              	<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!txtNoFail" size="43" maxlength="50" style="text-transform:uppercase;" > 
		            </tr>		            
		            <tr>
		              <td  width="29%" height="24" scope="row" align="right">Tajuk Fail</td>
		              <td width="1%">:</td>	              
		              <td>
		              	<input name="txtTajukFail" id="txtTajukFail" type="text" size="43" value="$!txtTajukFailCarian" maxlength="100" style="text-transform:uppercase;" />
		             	#if ($flagAdvSearch == '')
							<a href="#" title="More" class="style1" onclick="javascript:more()">Buka Carian Terperinci</a> 
						#end
						#if ($flagAdvSearch == 'Y') 
							<a href="#" title="Less" class="style1" onclick="javascript:less()">Tutup Carian Terperinci</a> 
						#end
		             </td>
		            </tr>
	#if ($flagAdvSearch == 'Y')			      
		            <tr>
		              <td  width="29%"  scope="row" align="right">Nama Pemajak</td>
		              <td width="1%">:</td>		              
		              <td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>
		            <tr>
		              <td  width="29%"  scope="row" align="right">No. Hakmilik</td>
		              <td width="1%">:</td>		              
		              <td><input name="txthakmilik" id="txthakmilik" value="$!noHakmilik" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>
		            <tr>
		              <td  width="29%"  scope="row" align="right">No. Lot</td>
		              <td width="1%">:</td>		              
		              <td><input name="txtlot" id="txtlot" value="$!noLot" type="text" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>		                      
            		<!-- <tr>
              			<td width="29%" scope="row" align="right">Tarikh Terima</td>
              			<td width="1%">:</td>
              			<td width="70%">
              				<input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!txdTarikhTerima" onblur="check_date(this)" size="11"/>
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr> -->
				  <tr>
				    <td width="29%"><div align="right">Tarikh Daftar Fail</div></td>
				    <td width="1%">:</td>
				    <td width="70%">
				    	<input type="text" name="txdtarikhdaftarfail" value="$!tarikhDaftarFail" size="11" maxlength="10" onblur="check_date(this)" />
						<a href="javascript:displayDatePicker('txdtarikhdaftarfail',false,'dmy');"> <img src="../img/calendar.gif" alt="" border="0"/></a>
									          
				    </td>
				  </tr>            		
 				   <tr>
				    <td width="29%"><div align="right">Status Pajakan</div></td>
				    <td width="1%">:</td>
				    <td width="70%">$!selectStatus</td>
				  </tr>	           		
	#end
            		<tr>
              			<td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
              				<input type="button" class="stylobutton100" name="cmdCari" id="cmdCari" value="Cari" onclick="javascript:carian()">
<!--                 			<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="javascript:kosongkan()">
 -->                			<input type="reset" class="stylobutton100" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" >
                		</td>
            		</tr>
            		<!--<tr>
              			<td scope="row">&nbsp;</td>
              			<td>&nbsp;</td>
            		</tr>  -->
            		   		
        		</table>
	  		</fieldset>
    	</td>
	</tr>
  
	<tr>
    	<td>
    		<fieldset><legend><b>SENARAI PERMOHONAN</b></legend>
        
        		<table align="center" width="100%"> 
		            <tr>
		              <td colspan="6" scope="row">
		              	<input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/>
		              </td>
		            </tr>
		            <tr>
		              	<td colspan="6" scope="row">
		        		#parse("app/utils/record_paging.jsp")
		            	</td>
		            </tr>
		            <tr class="table_header">
				        <td width="5%" align="center"><b>Bil.</b></td>
				        <td width="18%"><b>No. Fail</b></td>
				        <td width="42%"><b>Tajuk Fail</b></td>
				        <td width="14%"><b>Negeri</b></td>
    					<td width="11%" align="center"><b>Tarikh Daftar Fail</b></td>
				        <td width="10%" align="center"><b>Status</b></td>
		            </tr>
          			#set ($list = "")
          		#if ($SenaraiFail.size() > 0)
          			#foreach ($list in $SenaraiFail)
          				#set ( $cnt = $cnt + 1 )
                		#set( $i = $velocityCount )
                		
                		#if ( ($i % 2) == 0 )
                    		#set( $row = "row2" )
                		#else
                    		#set( $row = "row1" )
                		#end

		          	<tr class="$row" >
		            	<td align="center">$list.bil.</td>
		            	<td><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idPermohonan')" class="style1">$list.noFail</a></td>
		            	<td>$list.tajuk</td>
		            	<td>$list.negeri</td>
		            	<td align="center">$list.tarikhDaftar</td>
		            	<td align="center">$list.status</td>
		          	</tr>
          			#end
          		#else
		          	<tr>
		            	<td class="row1" >&nbsp;</td>
		            	<td class="row1" align="left" colspan="5">Tiada Rekod</td>
		          		<!--  <td class="row1">Tiada Rekod</td>
			            <td class="row1">&nbsp;</td>
			            <td class="row1" align="center">&nbsp;</td>
			            <td class="row1">&nbsp;</td> -->
		          	</tr>
          		#end
        		</table>
        
			</fieldset>
		</td>
	</tr>
</table>
<div id="setSessionIdFail_result"></div>
	<input type="hidden" name="actionPajakan" value="$!actionPajakan"/>
	<input type="hidden" name="idFail" value="$!idFail"/>
	<input type="hidden" name="idPermohonan" value="$!idPermohonan">
	<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
	<input type="hidden" name="mode" value="$!mode"/>


<script>
	//pajakan/fail/index.jsp
	// Senarai fail, butang [Carian]
	function carian_index(){
		if (document.${formName}.flagAdvSearch.value != ''){
			if(document.${formName}.socsuburusancarian.value == -1){
				alert("Sila Pilih Jenis Pajakan");
				return;
			}
		}
		document.${formName}.actionPajakan.value = "carian";
		document.${formName}.submit();
		
	}

/* function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
} */

// Senarai fail, butang [Daftar Permohonan Baru]
	function daftarBaru_index(){
		document.${formName}.actionPajakan.value = "daftarbaru";	
		document.${formName}.submit();
		//doAjaxCall${formName}("");
		
	}
	
	//function papar(idFail,idStatus){
	function papar_index(idFail,idStatus,idPermohonan){
		//alert("idfail : "+idFail+" idstatus : "+idStatus+" idpermohonan : "+idPermohonan);	
		document.${formName}.idFail.value = idFail;
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		if (idStatus == '6' || idStatus == '1' || idStatus == '12' || idStatus == '63'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";
			//Pendaftaran 24/01/2017
			document.${formName}.actionPajakan.value = "papardaftar";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
			
		} else if (idStatus == '65'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView";
			//Pendaftaran 24/01/2017
			document.${formName}.actionPajakan.value = "paparmjm";
			
		} else if (idStatus == '69' || idStatus == '86' || idStatus == '87'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPerjanjianPajakanView";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView";
			//Senarai Fail 24/01/2017
			document.${formName}.actionPajakan.value = "paparperjanjian";
			
		} else if (idStatus == '71'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanBayaranPajakanCukaiTanahView&actionPajakan";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView";
			document.${formName}.actionPajakan.value = "BayaranPajakan";
			
		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
			document.${formName}.actionPajakan.value = "penamatan";
		
		}
	
		document.${formName}.mode.value = "view";
		document.${formName}.idPermohonan.value = idPermohonan;
		//2017/05/05 document.${formName}.submit();
		//
		doAjaxCall${formName}("");
		
	}	
	// Senarai fail, pautan [Buka Carian Terperinci]
	function more_index(){
		//document.${formName}.flagAdvSearch.value = "open";
		document.${formName}.flagAdvSearch.value = "Y";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
			
	}
	// Senarai fail, pautan [Tutup Carian Terperinci]	
	function less_index(){
		document.${formName}.flagAdvSearch.value = "";
		document.${formName}.submit();
		//doAjaxCall${formName}("");
		
	}
/* 	function doChangeKementerian() {
		document.${formName}.mode.value = "changeKementerian";
		//doAjaxCall${formName}("","mode=changeKementerian");
		doAjaxCall${formName}("");

	} */

	//2017/05/05 Skrin/page Senarai Fail || Pendaftaran - pilihan[Kementerian]
	/*function doChangeKementerian() {
		document.${formName}.mode.value = "changeKementerian";
		document.${formName}.submit();
		//doAjaxCall${formName}("doChangeKementerian");

	}*/
	// SKRIN PENDAFTARAN
	// aliran - senarai fail| daftar
	//2017/04/17 Skrin Pendaftaran, butang [Simpan]
	//function simpan() {	
	/*function simpanDaftar() {	
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih Kementerian.');
	  		document.${formName}.socKementerian.focus(); 
			return; 
		}
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Agensi.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Sub Urusan.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		if(document.${formName}.socStatusTanah.value == ""){
			alert('Sila pilih Status Tanah.');
	  		document.${formName}.socStatusTanah.focus(); 
			return; 
		}
		if(document.${formName}.socJenisFail.value == ""){
			alert('Sila pilih Jenis Fail.');
	  		document.${formName}.socJenisFail.focus(); 
			return; 
		}*/
		/*if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila masukkan No. Fail KJP.');
	  		document.${formName}.txtNoFailKJP.focus(); 
			return; 
		}
		if(document.${formName}.tarikhSuratKJP.value == ""){
			alert('Sila masukkan Tarikh Surat KJP.');
	  		document.${formName}.tarikhSuratKJP.focus(); 
			return; 
		}
		
		if(document.${formName}.txtNoFailLain.value == ""){
			alert('Sila masukkan No. Fail Lain.');
	  		document.${formName}.txtNoFailLain.focus(); 
			return; 
		}
		
		if(document.${formName}.tarikhAgihan.value == ""){
			alert('Sila masukkan Tarikh Agihan.');
	  		document.${formName}.tarikhAgihan.focus(); 
			return; 
		}*/
		/*if(document.${formName}.txtTajuk.value == ""){
			alert('Sila masukkan Tajuk.');
	  		document.${formName}.txtTajuk.focus(); 
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "daftarBaru";
			return;
		}
		
		document.${formName}.mode.value = "view";	
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.hitButton.value = "simpan";
		doAjaxCall${formName}("");
		//goToNext();
	} */

 	//2017/04/17 Skrin Pendaftaran, butang [Batal] 
	//function kembali() {	
	/*function batalDaftar() {	
		document.${formName}.actionPajakan.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}*/
	/*
	function goToNext_Lama(){
		document.${formName}.action = "$EkptgUtil.getTabID("Pajakan",$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";	
		document.${formName}.submit();
	}
	
	function goToNext(idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
		document.${formName}.submit();
		
	}
	 */
 	//2017/04/17 Skrin Pendaftaran, butang [Seterusnya]
	function seterusDaftar(idFail){	
		langkah2(idFail);
	}

//TAB DRAF PERJANJIAN I	
		function daftarBaruDraf_index(){
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.mode.value = "newDraf";
			document.${formName}.submit();	
			//
			doAjaxCall${formName}("");
		
		}
		function SDraf_index(id){	
			var today = new Date();
			var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
			var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
			if(document.${formName}.txdTarikhHantarDraf.value == ""){
				alert('Sila masukkan maklumat Tarikh Hantar .');
	  			document.${formName}.txdTarikhHantarDraf.focus(); 
				return; 
			}

	/* 		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
				alert('Sila masukkan maklumat tarikh terima');
	  			document.${formName}.StarikhTerimaDraf.focus(); 
				return; 
			} */	
		
			//for StarikhTerimaDraf
			var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
			
			//for StarikhHantarDraf
			var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
		    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
		    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
			
			var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
			var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);

			if(tarikhHantarDraf > today){
				alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhHantarDraf.focus(); 
				return; 
			}
		
			if(tarikhTerimaDraf > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.StarikhTerimaDraf.focus(); 
				return; 
			} 
			if ( !window.confirm("Adakah Anda Pasti?") ) return;	
			
			var x = create_request_string(document.${formName});
			
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.mode.value = "view";
			document.${formName}.hitButton.value = "saveDraf";
			document.${formName}.method="post";
			document.${formName}.enctype="multipart/form-data";
			document.${formName}.encoding="multipart/form-data";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
			document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
			document.${formName}.submit();	
		
		}

		function batalDraf_index(){
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.mode.value = "view";
			//document.${formName}.submit();	
			//
			doAjaxCall${formName}("");
		
		}
			
		function paparDraf_index(idDraf){
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.idDraf.value = idDraf;
			document.${formName}.mode.value = "viewDraf";
			//document.${formName}.submit();	
			//
			doAjaxCall${formName}("");
			
		}

		function KemaskiniDraf_index(){
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.mode.value = "updateDraf";
			//document.${formName}.submit();	
			//
			doAjaxCall${formName}("");

		}		

		function HapusDraf_index(id){
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.idDraf.value = id;
			document.${formName}.mode.value = "view";
			document.${formName}.hitButton.value = "hapusDraf";
			//document.${formName}.submit();	
			//
			doAjaxCall${formName}("");
		
		}
		
		function batalUpdateDraf_index(){
			//
			document.${formName}.actionPajakan.value = "paparmjm";
			document.${formName}.mode.value = "viewDraf";
			//document.${formName}.submit();	
			//
			doAjaxCall${formName}("");
		
		}	
		
		function SimpanUpdateDraf_index(){		
			if(document.${formName}.txdTarikhHantarDraf.value == ""){
				alert('Sila masukkan Tarikh Hantar.');
		  		document.${formName}.txdTarikhHantarDraf.focus(); 
				return; 
			}
	/* 		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
				alert('Sila masukkan Tarikh Terima.');
		  		document.${formName}.txdTarikhTerimaDraf.focus(); 
				return; 
			} */
			
			var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
			var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
			
			var today = new Date();		
			//for StarikhTerimaDraf
			var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
			
			//for StarikhHantarDraf
			var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
		    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
		    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
			
			var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
			var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);
		
			if(tarikhHantarDraf > today){
				alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhHantarDraf.focus(); 
				return; 
			}
			
			if(tarikhTerimaDraf > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaDraf.focus(); 
				return; 
			}
		
			if ( !window.confirm("Adakah Anda Pasti ?") ){
				document.${formName}.actionPajakan.value = "paparmjm";
				return;
			}
			
			//document.${formName}.mode.value = "viewDraf";
			//document.${formName}.hitButton.value = "saveUpdateDraf";
			//doAjaxCall${formName}("");
			var x = create_request_string(document.${formName});
			alert('modul=${modul}');
			document.${formName}.action = "?_portal_module=${modul}&actionPajakan=paparmjm&selectedTab=2&mode=viewDraf&hitButton=saveUpdateDraf&"+x;
			//document.${formName}.method="post";
			document.${formName}.enctype="multipart/form-data";
			document.${formName}.encoding="multipart/form-data";
			document.${formName}.submit();	
			
		}
		function downloadPerjanjian_index(idPermohonan,idDeraf){
			var url = "../servlet/ekptg.view.htp.pajakan.PajakanDisplayBlob?id="+idPermohonan+"&idderaf="+idDeraf;
		    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		    hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		    
		}	
	
// SKRIN PERMOHONAN
	/** Fungsi pilih sub tab [Tab Ulasan]
	Mukasurat 3 */
	function doChangeTabLower_index(tabId) {
		document.${formName}.selectedTabLower.value = tabId;
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
		
	}
	/** Fungsi pilih tab
	Mukasurat 3 */
	// /fail/index.jsp
	function doChangeTab_index(tabId) {
		//alert("doChangeTab:actionPajakan="+document.${formName}.actionPajakan.value);
		if(document.${formName}.idStatus.value == '6' && ('$!flagFail' == 'N' || '$!flagFail' == 'H')){
			alert('Sila Hantar Pengesahan/ Sahkan permohonan terlebih dahulu.');
			document.${formName}.selectedTab.value = 0;	
			return;
		}else
			document.${formName}.selectedTab.value = tabId;
		
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	// /fail/index.jsp
//Pagging
	// 2018/02/22  
	function langkah1_index(){
		//
		document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	//2017/04/05 Paging 1
	function langkah1_(){
		batalDaftar();	
	}
	//2017/04/05 Paging 2, Capaian selepas pendaftaran
	function langkah2_index(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}
	function langkah3_index(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}

	function langkah4_index(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function langkah5_index(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	function langkah6_index(permohonan,idFail){
		//alert('langkah 6');
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "penamatan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	function langkah7_index(permohonan,idFail){
		//alert('langkah 6');
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "pemantauan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}		
	
</script>
<!-- 2018/02/22 -->
#parse("app/htp/utiliti/javascript/javaScriptPajakanTindakan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPenamatan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanBayaran.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanMJM.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakanPemohon.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakanDaftarHakmilik.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPermohonan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPaging.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanDaftar.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanIndex.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
