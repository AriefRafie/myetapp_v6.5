<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if($!pageMode != 'edit')
	<tr>
		<td>
		##parse('app/htp/pembelian/fail/paging.jsp')
		</td>
    </tr>
#end
	<tr>
		<td>&nbsp;
		</td>
    </tr>
    	<tr>
		<td>
			<fieldset><legend>MAKLUMAT FAIL</legend>
				<!--<table>	-->
				<table width="100%">
			   		<tr>
						<td width="50%" align="center" valign="top">
							<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end </span></td>
					        		<td width="30%"><div align="left">Negeri</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socNegeri</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update") <!--*--> #end </span></td>
					        		<td width="30%"><div align="left">Daerah</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socDaerah</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Kementerian</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectKementerian</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update") <!--*--> #end</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectAgensi</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$!selectUrusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%"><div align="left">Sub Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$selectSuburusan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update") <!--*--> #end</span></td>
					        		<td width="30%"><div align="left">Status Tanah</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$statusTanah</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update") <!--*--> #end</span></td>
					        		<td width="30%"><div align="left">Jenis Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socTarafKeselamatan</td>	
					        		<input type="hidden" name="idSuburusan" value="$idSuburusan">				
			              		</tr>
			              		<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">#if($pageMode!="update")*#end</span></td>
					        		<td width="30%" valign="top"><div align="left">Tajuk</div></td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
										<textarea name="txtTajuk" id="txtTajuk" cols="41" rows="5" onblur="this.value=this.value.toUpperCase();" $inputstyleread >$!htpPermohonan.permohonan.getTujuan()</textarea>					        		
					        		</td>					
			              		</tr>		

				       		</table>		
						</td>
        				<td width="50%" align="center" valign="top">
        					<table width="100%" border="0">
        						<tr>
						      		<td width="1%"><span class="labelmandatory">#if($pageMode!="update")*#end
						      		</span></td>
					        		<td width="30%"><div align="left">No. Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<!-- <input type="text" name="txtNoFailSek" size="28" value="$!htpPermohonan.permohonan.pfdFail.noFail" readonly="readonly" $inputstyleread>
					        		-->
					        		#if($pageMode=="update" || $pageMode=="edit")
					        			<input type="text" name="txtNoFailSek" size="40" value="$!htpPermohonan.permohonan.pfdFail.noFail" $inputstyleread onblur="this.value=this.value.toUpperCase();">
					        		#else
					        			<input type="text" name="txtNoFailSek" size="40" value="$!htpPermohonan.permohonan.pfdFail.noFail" $inputstyleread onblur="this.value=this.value.toUpperCase();">					        		
					        		#end
					        		</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailKJP" size="40" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$!htpPermohonan.getNoRujukanKJP()"  $inputstyleread/></td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail UPT</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtnofailupt" size="40" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanUPT()"  $inputstyleread/></td>							              			
		              			</tr>		              			        			
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail PTG</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtnofailptg" size="40" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$!htpPermohonan.getNoRujukanPTG()"  $inputstyleread/></td>							              			
		              			</tr>		
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail PTD</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtnofailptd" size="40" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanPTD()"  $inputstyleread/></td>							              			
		              			</tr>	
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" size="40" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$!htpPermohonan.getNoRujukanLain()"  $inputstyleread/></td>							              			
		              			</tr>		              			
			              		<tr>
						      		<!-- <td width="1%"><span class="labelmandatory">*</span></td> -->
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Surat KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="11" value="$!htpPermohonan.permohonan.getTarikhSurat()" $inputstyleread >
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
						                #else
						                </td>
						                #end
					        		</td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%">
						      			<span class="labelmandatory">#if($pageMode!="update") <!--*--> #end </span>
						      		</td>
					        		<td width="30%"><div align="left">Tarikh Permohonan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
						        		<input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="11" value="$!htpPermohonan.permohonan.getTarikhTerima()" $inputstyleread/>
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
						                #else
						                #end
					                </td>							              			
		              			</tr>	
			              		<tr>
						      		<td width="1%">
						      			<!-- <span class="labelmandatory">#if($pageMode!="update")*#end </span> -->
						      		</td>
					        		<td width="30%"><div align="left">Tarikh Buka Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
						        		<td width="68%">
						        		<input type="text" name="txdtarikhbukafail" size="11" value="$!htpPermohonan.permohonan.pfdFail.getTarikhDaftarFail()" $inputstyleread/>
						                #if($pageMode=="edit")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdtarikhbukafail',false,'dmy');" style="display:$Style2"></td>
						                #elseif($pageMode=="kemas")
						                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdtarikhbukafail',false,'dmy');" style="display:$Style2"></td>
						                #else
						                #end
					                </td>							              			
		              			</tr>				              	
				       		</table>	        				
    					</td>
    				</tr>
				</table>
			</fieldset>
		</td>
    </tr>
			#if($pageMode!="update")
			  <tr>
			    <td colspan="2">
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			    	</td>
			  </tr>
			 #end
	<tr>
		<td align=center>
			#if($pageMode=="edit")
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanFail()">
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
			#elseif($pageMode=="update")
				<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="doAjaxCall${formName}('update')">
                <input class="stylobutton100" type="button"  name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" />
				<!-- <input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatTanah','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"> -->
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
          	#else
                <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="doAjaxCall${formName}('simpanupdate')">
				<!-- <input class="stylobutton100" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="doAjaxCall${formName}('maklumatTanah','&idPermohonan='+$!htpPermohonan.permohonan.getIdPermohonan())"> -->
				<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doAjaxCall${formName}('indexPage')">
			#end    
    	</td>
    </tr>
        <tr>
  	<td colspan=2>
  		 <fieldset id="tabledokumensurat" style="display:none;">
			<legend>SENARAI SURAT/DOKUMEN</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','&template=NoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">KULIT FAIL</a></td>
			  </tr>
			  <tr>
			    <td><a href="javascript:cetakFailDoket('&idpermohonan=$!htpPermohonan.permohonan.getIdPermohonan()','&template=rptNoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">DOKET</a></td>
			  </tr>  
   
			  </table>
			</fieldset>
	  </td>
  </tr>
</table>




<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="pageMode" value="$!pageMode">

<script>
function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step4(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.RundinganPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step3(idPermohonan){
	doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
}
function step2(idPermohonan){
	doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
}
function step1(idPermohonan,idhtp){
	doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

	// Dibuat pada 	:	05/10/2010 
	// Dibuat oleh  :	Mohamad Rosli
	// Dimodifikasi oleh :
	//Fungsi diguna apabila pengguna dari Skrin Permohonan Online
	
	// Dimodifikasi oleh :
	function senaraiDokumenSurat(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	
	// Dimodifikasi oleh :
	function cetakFailDoket(id,temp,urlserv) {
		var param ="";
	    var url = "../servlet/"+urlserv+"?"+id+temp;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function setSelected(tabId,command,mode,tabmode) {
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}

	//PAUTAN DARI ONLINE
	
	//Tab Hakmilik
	function paparTanah(idhtp){
		doAjaxCall${formName}("detailTanah",'idHakmilikUrusan='+idhtp);
	}
	
	function deleteTanah(idhtp,id){
		if ( !window.confirm("Adakah Anda Pasti?")) 
		return;
		doAjaxCall${formName}("deleteMaklumatTanah",'idHakmilikUrusan='+idhtp+'&idPermohonan='+id);
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
		
	
	//Tab Pemilik
	function detailPemilik(id) {
		doAjaxCall${formName}("detailPemilik",'Idpihakberkepentingan='+id);
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("doChangeDaerah");
	}
	
	function doChangeDaerah2() {
		doAjaxCall${formName}("doChangeDaerah2");
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
	
	//Tab Penjual
	function penjualPembeli(){
		if(document.${formName}.PenjualSama.value != ""){
			doAjaxCall${formName}("assignPenjual");
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
	
	//Menu Rundingan Harga
	function tambahDraft(){
		doAjaxCall${formName}("addDraft");
		
	}

	function kembaliDraft(){
		doAjaxCall${formName}("kembaliDraft");
	
	}
	
	function kemaskiniSemakMilik(){
		doAjaxCall${formName}("senaraisemakpmilikkemaskini");
	}
	
	//Perjanjian
	function simpanPerjanjian(){
		if ( document.${formName}.txtTarikhPerjanjian.value == "" ) { 
			alert('Sila masukkan Tarikh Perjanjian terlebih dahulu.');
			document.${formName}.txtTarikhPerjanjian.focus(); 
			return; 
		}
		if(document.${formName}.txtbilUnitBangunan.value == ""){
			document.${formName}.txtbilUnitBangunan.value = 0;
		}
		doAjaxCall${formName}("simpanPerjanjian");
	}
	
	//semakan Tarikh semasa
	function checkDate(inputfield) {
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
	
	
	
	// 2010/07/01 -Pilih Pegawai semakan
	function openSenaraiSemak(urli,tajuk,idpermohonan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=senaraisemak&tajuk="+tajuk+"&idPermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
	//setSelected(tabId,command,mode,tabmode) {
	//2,'senaraisemakview','senaraisemakview',0
	//doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);

	// 2010/07/02 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
	
	
	//end
</script>