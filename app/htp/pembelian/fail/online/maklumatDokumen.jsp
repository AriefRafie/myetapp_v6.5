<style type="text/css">
<!--
.style1 {color: #0000FF}
.style4 {color: #FF0000; font-style: italic;}
.style5 {color: #000000}
.style3 {color: #FF0000}
.pautanms {color: #0000FF}
-->
</style>

<table width="100%" border="0">	
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>
	
	<fieldset>
	  <legend>MAKLUMAT PERMOHONAN</legend>
	  #parse ("app/htp/pembelian/fail/online/fileInfoOnline.jsp")</fieldset>
	
	</td>


</tr>
   	
   	#if ($mode == "new" && $!SenaraiImej.size()>0 )
	<tr>
    	<td>	  
	       <fieldset>
	        <legend>SENARAI LAMPIRAN</legend>
			#if($!SenaraiImej.size()>10)		    
		     	<div style="width:100%;height:200;overflow:auto"> 
			#end	        
	     	<table>
	        #set ($xx = 0)
	        #foreach ($listlampiran in $SenaraiImej)
	        #set ($xx = $xx + 1)	       	
	        <tr>  
	        <!-- <td width="3%">$!xx</td> -->
	        <td  width="97%" colspan="2"> $!xx.
	 	        <a onClick="javascript:cetakImej('$listlampiran.idGambar')" 
	        		href="#" style="color:#0000FF">$listlampiran.namaFail</a>
	        		&nbsp;&nbsp;
	        	<a href="#" onClick="javascript:deleteDetailImej('$listlampiran.idGambar')">
	  	       	<img border="0" src="../img/delete.gif" /></a>
	        </td>
	       </tr>
	       #end
	       #if ($xx == 0)
	       <tr><td></td><td colspan="2">Tiada Lampiran</td></tr>
	       #end
	       </table>
			#if($!SenaraiImej.size()>10)		    
				</div>
			#end	        
			</fieldset>
    	</td>
   	</tr>	  
	#end
	<!--
	<tr>
    	<td>
			<fieldset><legend>SENARAI IMEJ</legend>		
			#if($!SenaraiImejDist.size()>10)		    
		     	<div style="width:100%;height:200;overflow:auto"> 
			#end	
				<table width="100%" border="0">
	            	<tr class="table_header">
						<td width="3%">Bil.</td>
				   	  	<td width="87%">Ringkasan</td>
				      	<td width="10%">Tindakan</td>
		      		</tr>	
		      		#if($SenaraiImejDist.isEmpty())
		  	        <tr >
						<td width="3%"></td>
				   	  	<td width="97%" colspan=2 align="left">Tiada Rekod</td>
		      		</tr>	    			
		      		#end
		      	#foreach ($senarai in $SenaraiImej)
		  	  		#set( $i = $velocityCount )
		      		#if ( ($i % 2) != 1 )
		      			#set( $row = "row2" )
		      		#else
		      			#set( $row = "row1" )
		 			#end  
		  		
		  			<tr class="$row">
		    			<td>$senarai.bil</td>
		      		#if ($senarai.bil !="")
		      			<td>
		      				<a href="javascript:viewDetailImej('$senarai.idGambar')" class="style1">$senarai.ringkasan ($!senarai.namaFail)</a> 
		      				<!-- <a href="javascript:viewDetailInfo('$senarai.idHakmilik')" class="style1">$senarai.ringkasan</a> -->
		      				<!--<td><a href="javascript:deleteDetailImej('$senarai.idGambar')" class="style1"><div align="left"><img border="0" title ="Hapus Imej" src="../img/hapus.gif"/></div></a>-->
		      			</td>
		      			<td>
		      				<div align="center">
		      					<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick= "deleteDetailImej('$senarai.idGambar')">
		      				</div>
		      			</td>
		      
			  		#else
		      			<td colspan="3">$senarai.ringkasan</td>
		      		#end
		    		
		    		</tr>
				#end
			
				</table>
			#if($!SenaraiImejDist.size()>10)		    
				</div>
			#end					
			</fieldset>
    	</td>
   	</tr>  
   	
#if($mode=="view" || $mode=="kemaskini")
   	<tr>	
    	<td>
			<fieldset><legend>IMEJ</legend>
			<table width="100%" border="0">
			  	<tr>
			    	<td height>
			    		<div align="center">
			      		<p><img src="../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$idGambar" alt="Imej Pelan" border="1" width="250" height="250"/></p>
			      		</div>
			      	</td>
			  	</tr>
			  	<tr>
			    	<td>
			    		<div align="center">
			      			<input type="button" name="btnImejPenuh" id="btnImejPenuh" value="Imej Penuh" onclick="cetakImej($idGambar)" />
			     		</div>
			     	</td>
			  	</tr>			
			</table>
    		</fieldset>
	    </td>
	</tr>
		
#end 	
   	
   	-->
   	<tr>
    	<td>
			<fieldset><legend>MAKLUMAT DOKUMEN</legend>			    
				<table width="100%" border="0">

			  	##if($mode=="new")
			  		<!--	<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
						<td width="30%">
							<div align="right" class="labelinput">
							<div align="left">Bil. Lampiran</div>
							</div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type=text size=2 name=jumlahlampiran $readOnly onBlur="doChangeJumlahLampiran('$!IDJadualLampiran',this,'$!action');" 
	              			value=$!num_files> (<font size=1 color=red>Sila masukkan jumlah lampiran</font>)
						</td>
					</tr> -->
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if ($mode != 'readonly') * #end </span> 
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left">Nama Dokumen</div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<input type="text" name="txtPerihalImej" value="$!txtPerihalImej" size="46" class="$disabled" $readOnly/>
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left">Keterangan</div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<textarea name="txtRingkas" cols="43" rows="5" class="$disabled" id="txtRingkas" $readOnly  style="text-transform:uppercase;">$!txtRingkas</textarea>
						</td>
					</tr>
					
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
						<td valign="top" width="30%">
							<div align="right" class="labelinput">
							<div align="left">Lampiran Dokumen</div>
							</div>
						</td>				      	
						<td valign="top" width="1%">:</td>				        
						<td width="68%">
							##foreach( $i in [1..$num_files] )							
							<input id="fileupload" name="fileupload" type="file" size="54" $readOnly  class="$disabled" /></br>
							##end
							#if ($mode == 'view' || $mode == 'kemaskini')
								$!namafail
							#end
						</td>
					</tr>
							<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory"></span></td>				        
						<td width="30%">
							<div align="right" class="labelinput">
							<div align="left"></div>
							</div>
						</td>				      	
						<td width="1%"></td>				        
						<td width="68%">
							<i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span>
						</td>
					</tr>
				##end
	
	
				#if($mode=="Xview") 
			  		<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left">Tarikh Kemaskini</div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<input name="txdTarikhKemaskiniImej" type="text" id="txdTarikhKemaskiniImej" value="$txdTarikhKemaskiniImej" size="11" maxlength="10" onkeyup="validateNumber(this,this.value);" $readonly class="disabled"/>
						</td>
					</tr>
			  	#end
				</table>
			</fieldset>
    	</td>
   	</tr>
   	
	<tr>
  		<td>
  					
		 <table align="center">
		  <tr>
		    <td colspan="4"  ><div >
		    #if($pageMode=="edit")
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanLampiran($!idHakmilik)">
		    #end

		    #if($mode=="kemaskini")
		      	<input class="stylobutton100" type="button" name="btnUpdate" id="btnUpdate" value="Simpan"  onclick="kemaskiniSimpan($!idDokumen,$!idGambar)" />
		    #end 
		    #if($mode=="view")
		      	<input class="stylobutton100" type="button" name="btnKemaskini" id="btnKemaskini" value="Kemaskini" onclick="kemaskiniDetailImej($idGambar)"/>
		      <!--<input class="stylobutton100" type="button" name="btnReset" id="btnReset" value="Batal" onclick="baru($idHakmilik)"/> -->
		    #end
		      <!-- <input class="stylobutton100" type="button" name="btnBack" id="btnBack" value="Kembali" onclick="kembali($idHakmilik)" />
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
				-->	
				<input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Kembali" onclick="doAjaxCall${formName}('viewLampiran','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())">
		    </div></td>
		  </tr>
		 </table>
		
    	</td>
	</tr>   	
<!--   	   	
   	<tr>
    	<td>

    	</td>
   	</tr>	-->
 
 	
</table>
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>

<script>
	// maklumatDokumen.jsp
	function baru(id){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		document.${formName}.submit();
	}
	
	function simpanDetailImej(id){
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
	}

	function viewDetailImej(id){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&nextAction=viewDetailImej&idGambar="+id;
		document.${formName}.method="POST";
		document.${formName}.command.value="lampiran";
		document.${formName}.submit();
		//doAjaxCall${formName}("lampiran","&nextAction=viewDetailImej&idGambar="+id);
		
	}
	
	function viewDetailInfo(id){
		document.${formName}.command.value = "lampiran";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=kemaskiniDetail&idGambar="+id;
		document.${formName}.submit();
	}
	
	//maklumatDokumen.jsp	
	function kemaskiniDetailImej(id){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&nextAction=kemaskiniDetail&idGambar="+id;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej;
		document.${formName}.method="POST";
		document.${formName}.command.value="lampiran";
		document.${formName}.submit();
	
	}
	
	function kemaskiniSimpan(iddokumen,id){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&nextAction=kemaskinisimpan&idGambar="+id+"&iddokumen="+iddokumen;
		document.${formName}.method="POST";
		document.${formName}.command.value="lampiran";
		document.${formName}.submit();
		
	}
	
	
	function kembali(id){
		document.${formName}.command.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
		document.${formName}.submit();
	}

	function XcetakImej(id){
		var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function XdeleteDetailImej(id){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		document.${formName}.submit();
	}
	
	function XdoChangeJumlahLampiran(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
	 	doAjaxCall${formName}("imej","firstAction=PendaftaranImej&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	}
	
	// selepas upload
	
function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}
function findFail(){
	doAjaxCall${formName}("searchFail");
}
function paparTanah(idhtp){
	doAjaxCall${formName}("detailTanah",'idHakmilikUrusan='+idhtp);
}

	function paparTanahOnline(idhtp){
		doAjaxCall${formName}("detailtanahonline",'idHakmilikUrusan='+idhtp);
	}

function deleteTanah(idhtp,id){
	if ( !window.confirm("Adakah Anda Pasti?")) 
	return;
	doAjaxCall${formName}("deleteMaklumatTanah",'idHakmilikUrusan='+idhtp+'&idPermohonan='+id);
}
	function papar(id,idhtp){
		doAjaxCall${formName}("detail",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
	}
	
	function paparOnline(id,idhtp){
		doAjaxCall${formName}("detailonline",'idPermohonan='+id+'&idHtpPermohonan='+idhtp);
	}

function simpanMaklumatTanah(){

	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih " Mukim " terlebih dahulu.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih " Unit Luas " terlebih dahulu.');
  		document.${formName}.socLuas.focus(); 
		return; 
	}
	if(document.${formName}.txtLuas.value == ""){
		alert('Sila pilih " Luas " terlebih dahulu.');
  		document.${formName}.txtLuas.focus(); 
		return; 
	}
	
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
  		document.${formName}.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila pilih " No. Hakmilik " terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila pilih " No Lot/PT " terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih " Kategori " terlebih dahulu.');
  		document.${formName}.socKategori.focus(); 
		return; 
	}
	
	doAjaxCall${formName}('simpanMaklumatTanah');
}

	function simpanFail(){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih " Kementerian " terlebih dahulu.');
	  		document.${formName}.socKementerian.focus(); 
			return; 
		}
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih " Agensi " terlebih dahulu.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		if(document.${formName}.socSuburusan.value == ""){
			alert('Sila pilih " Urusan " terlebih dahulu.');
	  		document.${formName}.socSuburusan.focus(); 
			return; 
		}
		/*if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
	  		document.${formName}.txtNoFailKJP.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
			alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
	  		document.${formName}.txdTarikhSurKJP.focus(); 
			return; 
		}
		if(document.${formName}.txtNoFailLain.value == ""){
			alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
	  		document.${formName}.txtNoFailLain.focus(); 
			return; 
		}*/
		if(document.${formName}.txdTarikhPermohonan.value == ""){
			alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
	  		document.${formName}.txdTarikhPermohonan.focus(); 
			return; 
		}
		if ( !window.confirm("Anda Pasti?") ) return;
	
		doAjaxCall${formName}('simpan');
	}

function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeDaerah2() {
	doAjaxCall${formName}("doChangeDaerah2");
}
function detailPemilik(id) {
	doAjaxCall${formName}("detailPemilik",'Idpihakberkepentingan='+id);
}
function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}


function simpanPenjual() {

	if(document.${formName}.selectDaerahP.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.selectDaerahP.focus(); 
		return; 
	}
	if(document.${formName}.selectNegeriP.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.selectNegeriP.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanPenjual");
}

function kemaskiniPenjual(){

	doAjaxCall${formName}("kemaskiniPenjual");
}

function updatePenjual(){
	if(document.${formName}.selectDaerahP.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.selectDaerahP.focus(); 
		return; 
	}
	if(document.${formName}.selectNegeriP.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.selectNegeriP.focus(); 
		return; 
	}
	doAjaxCall${formName}("updatePenjual");
}
function penjualPembeli(){
	if(document.${formName}.PenjualSama.value != ""){
		doAjaxCall${formName}("assignPenjual");
	}
}
function step4(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	//document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule";
	//document.${formName}.mode.value = "view";
	//document.${formName}.command.value = "detail";
	//document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.submit();
}

function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
}
function step2(idPermohonan){
	doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
}

	function step2Online(idPermohonan){
		doAjaxCall${formName}('maklumatanahonline','&idPermohonan='+idPermohonan);
	}
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
}

	function step1Online(idPermohonan,idhtp){
		doAjaxCall${formName}("detailonline",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	}
	
function kembaliBangunan(){
	doAjaxCall${formName}("maklumatBangunan");
}
function simpanBangunan(){
	if(document.${formName}.idHakmilikUrusan.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
		return;
	}
	else if(document.${formName}.noPetak.value == ""){
		alert('Sila masukkan" No Petak " terlebih dahulu.');
		return;
	}
	doAjaxCall${formName}("simpanBangunan");
}

function updateBangunan(){
	if(document.${formName}.idHakmilikUrusan.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
		return;
	}
	else if(document.${formName}.noPetak.value == ""){
		alert('Sila masukkan" No Petak " terlebih dahulu.');
		return;
	}
	doAjaxCall${formName}("updateBangunan");
}

function paparBangunan(idBangunan){
	doAjaxCall${formName}("paparBangunan",'&idBangunan='+idBangunan);
}
function deleteBangunan(idBangunan){
	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("deleteBangunan",'&idBangunan='+idBangunan);
}


// Senarai Dokumen/Cetakan

function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

// 2010/07/02 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function simpanPemilik(){
	if(document.${formName}.ddownHakmilik.value == ""){
		alert('Sila pilih " No Hakmilik " terlebih dahulu.');
  		document.${formName}.ddownHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPemaju.value == ""){
		alert('Sila pilih " Nama Pemilik " terlebih dahulu.');
  		document.${formName}.txtNamaPemaju.focus(); 
		return; 
	}
		if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
		if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	doAjaxCall${formName}("simpanPemilik");
}

//STEP 4

//STEP 5

function kiraTahun() {

	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
	var daftar_bulan;
	var daftar_hari;
	var daftar_tahun;
	if(document.${formName}.socTaraf.value == 'P'){
		if(document.${formName}.txdTarikhLuput.value == ''){
			alert('Sila masukkan Tarikh Luput Hakmilik');
			document.${formName}.txdTarikhLuput.focus();
			return;
		
		}else{
			hingga_bulan = document.${formName}.txdTarikhLuput.value.substring(3,5);
			hingga_hari = document.${formName}.txdTarikhLuput.value.substring(0,2);
			hingga_tahun = document.${formName}.txdTarikhLuput.value.substring(6,10);
			var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	
			daftar_bulan = document.${formName}.txdTarikhTerima.value.substring(3,5);
			daftar_hari = document.${formName}.txdTarikhTerima.value.substring(0,2);
			daftar_tahun = document.${formName}.txdTarikhTerima.value.substring(6,10);
			var daftartemp = daftar_bulan+"/"+daftar_hari+"/"+daftar_tahun;
	
			dari_bulan = document.${formName}.txtTarikhPermohonan.value.substring(3,5);
			dari_hari = document.${formName}.txtTarikhPermohonan.value.substring(0,2);
			dari_tahun = document.${formName}.txtTarikhPermohonan.value.substring(6,10);
			var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
			
			var dari = Date.parse(daritemp);
			var hingga = Date.parse(hinggatemp);
		
			var diff_date =  hingga - dari;
			
			var num_years = diff_date/31536000000;
			var num_months = (diff_date % 31536000000)/2628000000;
			var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
			
			var daftar = Date.parse(daftartemp);
			var baki =  hingga - daftar;
			var bakiTahun = baki/31536000000;
			
			document.${formName}.txtTempoh.value = Math.floor(bakiTahun);
		  	document.${formName}.txtTempohbaki.value = Math.floor(num_years);
		}
	}
}

// 18/08/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//18/08/2010 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

//semakan Tarikh semasa null
function validateTarikhSemasaIsNull(inputfield) {
	var today = new Date();	
	if(inputfield.value != ''){
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
}

	function doChangePenjualNegeri(){
			doAjaxCall${formName}("doChangePenjualNegeri");
	}

	function kemaskiniPenjual(){
		doAjaxCall${formName}("kemaskiniPenjual");
	}
	
	function updatePenjual(){
		if(document.${formName}.selectDaerahP.value == ""){
			alert('Sila pilih " Daerah " terlebih dahulu.');
	  		document.${formName}.selectDaerahP.focus(); 
			return; 
		}
		if(document.${formName}.selectNegeriP.value == ""){
			alert('Sila pilih " Negeri " terlebih dahulu.');
	  		document.${formName}.selectNegeriP.focus(); 
			return; 
		}
		doAjaxCall${formName}("updatePenjual");
	}
	
	//STEP 5
	function cetakPengesahan(idpermohonan) {
	
	    //var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPembelianOnline&idpermohonan="+idpermohonan;
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPermohonanOnline&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	
	}
	
	function cetakImej(id){
		//var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function papar_Lampiran(id_lampiran) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id_lampiran;
	    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function deleteDetailImej(id){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		document.${formName}.submit();
	}
	//function paparOnline(id,idhtp){
	//}
	
	function doChangeJumlahLampiran(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
	 	//doAjaxCall${formName}("imej","firstAction=PendaftaranImej);
		doAjaxCall${formName}("update","&idPermohonan="+document.${formName}.txtidPermohonan.value+"&idHtpPermohonan="+document.${formName}.txtidHtpPermohonan.value+"&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	}
	
	function simpanLampiran(id){
		if(document.${formName}.txtPerihalImej.value == ""){
			alert("Sila isi nama dokumen terlebih dahulu");
			return;
		}
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline&command=simpanlampiran&idPermohonan="+document.${formName}.txtidPermohonan.value+"&idHtpPermohonan="+document.${formName}.txtidHtpPermohonan.value+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline"+"&command=simpanlampiran";
		document.${formName}.method="post";
		//document.${formName}.command.value="simpanlampiran";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		//doAjaxCall${formName}("simpanlampiran");
		
	}
	
	function tambahLampiran() {
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.pembelian.SenaraiFailModuleOnline";
			
	}
	
	// dokumenOnline.jsp
	function Pengesahan(){
		doAjaxCall${formName}("pengesahan");
		
	}
	
	
</script>