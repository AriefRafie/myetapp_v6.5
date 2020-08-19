<!--frmCukaiPenyata.jsp -->
<!-- CL-02-019 -->
<!--frmCukaiBaucer.jsp -->
<!-- CL-02-020 -->
<style type="text/css">
<!--
	.style1 {color: #0033FF}
	.pautanms {color: #0000FF}
-->
</style>
    <!-- $senaraiLangkah <br> -->
    
<table border="0" width="98%">
	<tr>
		<td align="center">&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<fieldset>
			#parse ("app/htp/6.0/cukai/frmCukaiInfo.jsp")
			</fieldset>		
		</td>
	</tr>
	
	<tr>
		<td> ##$selectedTab
			<fieldset>
				<div id="TabbedPanels1" class="TabbedPanels">
					<ul class="TabbedPanelsTabGroup">
				   		<li class="TabbedPanelsTab" tabindex="1" title="Penyata" onclick="setSelected(0);PenyataView()">PENYATA CUKAI</li>
				    	<li class="TabbedPanelsTab" tabindex="2" title="Baucer" onclick="setSelected(1);BaucerView()">BAUCER</li>
				    	<li class="TabbedPanelsTab" tabindex="3" title="Bayaran" onclick="setSelected(2);SeterusBaucer('$idNegeri','$idPeringkatbayaran')">BAYARAN</li>
				   	</ul>
	  				<div class="TabbedPanelsContentGroup">
	    				<div class="TabbedPanelsContent">
	    					<fieldset>
	    					
	    					<table border="0" cellpadding="1" cellspacing="1" width="100%">	    						
					    		<tr class="table_header">
	    							  <td width="3%" align="center"><strong>Bil.</strong></td>
					        		  <td width="47%" align="center"><strong>Daerah</strong></td>
					   				  <td width="25%" align="center"><strong>Jumlah Hakmilik</strong></td>
					   				  <!--
					       			  <td width="15%" align="center"><strong>Lebihan (RM)</strong></td>
					       			  <td width="14%" align="center"><strong>Denda (RM)</strong></td>
					       			  <td width="14%" align="center"><b>Lain-lain <strong>(RM)</strong></b></td>
					       			    -->
					   			      <td width="25%" align="center"><strong>Cukai Semasa (RM)</strong></td>    
			   			  		</tr>
			   			  		
			   			 	#set ($count = 0)
		                	#set ($sumCukai2 = 0)
		                	#set ($sumIdHakmilik2 = 0)
		                	#foreach ($fail in $Penyata)
		                		#set ($count = $count+1)
		                  		#set( $i = $velocityCount )
		                  		#if ( ($i % 2) != 1 )
		                    		#set( $row = "row2" )
		                  		#else
		                       		#set( $row = "row1" )
		                  		#end
      			
	      						<tr>
	  			        			<td class="$row" >$count.</td>
	  			        			<td class="$row" >$fail.nama_daerah</td>
				        			<td class="$row"><div align="center">$fail.sumIdHakmilik</div></td>
				        			<!--
				        			<td class="$row"><div align="center">$fail.lebihan</div></td>
				                    <td class="$row"><div align="center">$fail.sumDenda</div></td>
				        			<td class="$row"><div align="center">$fail.sumBayaran_lain</div></td>
				        			  -->
				      			    <td class="$row"><div align="right">$fail.sumCukaiFormat</div></td>
				      			   <!--<td class="$row"><div align="center">$fail.sumAmaunBaucer</div></td>-->
	      						</tr>
      						
                				<input type="hidden" name="idDaerah" value="$fail.idDaerah">
            	
            					#set ($sumCukai2 = $sumCukai2 + $fail.sumCukai)
                				#set ($sumIdHakmilik2 = $fail.sumIdHakmilik +$sumIdHakmilik2)
                			#end
                			
                			#set ($sumAllHakmilik = 0)
			                #foreach ($fail in $Penyata)
				                #if ( $fail.sumAllHakmilik == 0 )
									#set ($sumAllHakmilik = 0)
				                #else	
				                    #set ($sumAllHakmilik = $sumAllHakmilik + $fail.sumAllHakmilik)
								#end
			                #end
                
			                #set ($sumAllCukai = 0)
			                #foreach ($fail in $Penyata)
				                #if ( $fail.sumAllCukai == 0 )
									#set ($sumAllCukai = 0)
				                #else
									#set ($sumAllCukai = $sumAllCukai + $fail.sumAllCukai)
								#end
			                #end
		                
			                	<tr></tr>
		                  	#if ($count == 0)
			                  	<tr> 
			                    	<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
			                  	</tr>
		                  	#end  			   
 			                  	<tr>
				        			<td colspan='4'  bgcolor='#000000' style='border-top-style:solid; border-top-width:thin;  border-top-color:#000000;' ></td>
				      			</tr> 
				        		<tr>
				        			<td><div align="center"></div></td>
				        			<td><div align="center"></div></td>
				        			<td><div align="center">Jumlah = $sumIdHakmilik2</div></td>
				        			<td colspan="1"><div align="right">Jumlah = $Util.formatDecimal($sumCukai2)</div></td>      			    
				      			   <!--<td class="$row"><div align="center">$fail.sumAmaunBaucer</div></td>-->
				      			</tr> 
			      			
				      			
					    	</table>
	    					</fieldset>
	    					#if($!statusPeringkatBayar)	    					
	    					<table border="0" width="100%">	    						
					    		
		               	 		<tr>
									<td colspan="7" align="center">
									#if($!cukaiUtama!=0)
				                    	<input class="stylobutton100" type="button" name="cmdKemaskiniPenyata" id="cmdKemaskiniPenyata" value="Sahkan" onclick="KemaskiniPenyata()" >
				                    #end
									#if($!cukaiUtama==0)
				                    	<input class="stylobutton100" type="button" name="cmdSimpanPenyata" id="cmdSimpanPenyata" value="Simpan" onclick="SimpanPenyata()" >
				                    #end
				                    	<input class="stylobutton100" type="button" name="cmdKembaliPenyata" id="cmdKembaliPenyata" value="Kembali" onClick="KembaliPenyata()">
									#if($!cukaiUtama!=0)
				             			<input class="stylobutton100" type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:cetakPenyata('$tahun_cukai','$idNegeri')" />
				                    #end
				                     	<!--
				                     	<input class="stylobutton" type="reset" name="cmdBatalPenyata" id="cmdBatalPenyata" $btnName onclick="BatalPenyata()" style="display:none" >
				                     	<input class="stylobutton" type="button" name="cmdSeterusPenyata" id="cmdSeterusPenyata" value="Seterus" style="display:none" >
				                   		-->
				                     </td>
			                	</tr>					      			
				      			
					    	</table>
					    	#end
						</div>
						
						<div class="TabbedPanelsContent">
							#if($!selectedTab.equals('1'))
								
								#if($!pagemode.equals('baru')||$!pagemode.equals('view')||$!pagemode.equals('kemaskinibaucer'))
									#parse("app/htp/cukai/frmCukaiTambahBaucer.jsp")
									
								#else
								
							<fieldset>							    					
	    					<table border="0" cellpadding="2" cellspacing="2" width="100%">
					    		<tr>
					    			<td colspan="8">
									#if ( $idPeringkatbayaran == 0 )
										<input class="stylobutton100" name="cmdTambah" value="Tambah Baucer" id="cmdTambah" type="button" disabled="disabled" >
			    					#else	
			    						<input class="stylobutton100" name="cmdTambah" value="Tambah Baucer" id="cmdTambah" type="button" onClick="javascript:TambahBaucer()">
									#end					    				
			    					</td>
		    					</tr>	
		    					<tr class="table_header">
			        		  		<td width="3%" align="center"><strong>Bil.</strong></td>
			        				<td width="13%" align="center"><strong>Negeri/Daerah</strong></td>
			   				  		<td width="12%" align="center"><strong>Tarikh Baucer</strong></td>
			       			  		<td width="14%" align="center"><strong>No. Baucer</strong></td>
			       			  		<td width="14%" align="center"><strong>Tarikh Cek/FT</strong></td>
			       			  		<td width="14%" align="center"><strong>No. Cek/FT</strong></td>
			   			      		<td width="20%" align="center"><strong>Amaun Cek/FT (RM)</strong></td>
			                  		<td width="10%" align="center"><strong></strong></td>
				     			</tr>   
				     		#set ($count = 0)
      						#foreach ( $fail in $Baucer )
                				#set ($count = $count+1)
                  				#set( $i = $velocityCount )
                  				#if ( ($i % 2) != 1 )
		                       		#set( $row = "row2" )
		                  		#else
		                       		#set( $row = "row1" )
		                  		#end
			      				<tr>
			  			        	<td class="$row" ><a href="javascript:EditBaucer('$fail.id_baucer')" class="" >$count.</a></td>
				        			<td class="$row" height="25"><div align="center"><a href="javascript:EditBaucer('$fail.id_baucer')" class="pautanms" >$fail.nama_daerah</a></div></td>
				        			<td class="$row"><div align="center">$fail.tkh_baucer</div></td>
				        			<td class="$row"><div align="center">$fail.no_baucer</div></td>
				        			<td class="$row"><div align="center">$fail.tkh_resit</div></td>
				        			<td class="$row"><div align="center">$fail.no_resit</div></td>
				      			    <td class="$row"><div align="center">$fail.amaun_baucer</div></td>
				                    <td class="$row"><div align="center">
				                    	<!--<input name="cmdEdit" value="Edit" id="cmdEdit" class="stylobutton" type="button" onClick="javascript:EditBaucer('$fail.id_baucer')">-->
				                    	<!-- <input name="cmdCetak" value="Cetak" id="cmdCetak" class="stylobutton" type="button" onClick="javascript:CetakBaucer('$fail.id_baucer')"> -->
				                    	<input name="cmdCetak" value="Cetak" id="cmdCetak" class="stylobutton_" type="button" onClick="javascript:senaraiSurat('tablesurat','$fail.id_baucer');">
				                    </td>
			   			    	</tr>       		 
      						#end
	                
		                	#if ($count == 0)
			                	<tr> 
			                    	<td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
			                	</tr>
		              		#end
				     			 						
					    	</table>	    						
							</fieldset>		
							
							<table border="0" cellpadding="0" cellspacing="0" width="100%">	    						
					    		
		               	 		<tr>
									<td colspan="7" align="center">
			                    		<input class="stylobutton100" type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="KemaskiniBaucer()" style="display:none">
			                        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="SimpanBaucer()" style="display:none">
			                        	<input class="stylobutton100" type="button" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="SeterusBaucer('$idNegeri','$idPeringkatbayaran')">
			                        	<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="BatalBaucer()" style="display:none">
			                        	<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliBaucer()">
				                     </td>
			                	</tr>					      			
				      			
					    	</table>	
					    	
					    		#end
					    	
					    	#end			
						</div>
						
						<div class="TabbedPanelsContent">
							#if($!selectedTab.equals('2'))
								#if($!pagemode.equals('baru')||$!pagemode.equals('viewBay')||$!pagemode.equals('view')||$!pagemode.equals('kemaskinibayaran'))
									#parse("app/htp/cukai/frmCukaiTambahBayaran.jsp")
									
								#else
								#parse("app/htp/cukai/frmCukaiBayaranSenarai.jsp")
								#end
							#end
						</div>
  					</div>
				</div>
			</fieldset>				
		</td>
	</tr>	
	
	<tr>
		<td>
			<fieldset id="tablesurat" style="display:none;"><legend>SENARAI DOKUMEN/SURAT</legend>
				<table width="100%" border="0">
			  		<tr>
			    		<td><a href="javascript:CetakBaucer(document.${formName}.idBaucer.value)" class="pautanms">BAUCAR</a></td>
			  		</tr>
			  		<tr>
			    		<td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=43&tahun='+document.${formName}.tahun_cukai.value+'&idpermohonan='+document.${formName}.idPermohonan.value+'&idbaucer='+document.${formName}.idBaucer.value,'negeri','&template=HTPCukaiMemo')" class="pautanms">MEMO DERAF BAUCAR</a></td>
			  		</tr>  
				</table>
			</fieldset>			
		</td>
	</tr>	
</table>
	<input type="hidden" name="sumIdHakmilik" value="$!sumIdHakmilik2">
    <input type="hidden" name="sumCukai" value="$!sumCukai2" >
   	<input type="hidden" name="tabId" id="tabId" value="$selectedTab"/>
	<input type="hidden" name="idFail">
	<input type="hidden" name="noFail">
	<input type="hidden" name="idPermohonan" value="$idPermohonan">
	<input type="hidden" name="idNegeri" value="$idNegeri">
	<input type="hidden" name="negeri" value="$negeri">
	<input type="hidden" name="nama_daerah" >
	<input type="hidden" name="fail" >
	<input type="hidden" name="tahun_cukai" value="$tahun_cukai">
	<input type="hidden" name="peringkat_bayaran" value="$statusDisplay" >
	<input type="hidden" name="socbayaran" value="$idbayaran" >
	<input type="hidden" name="command1" value="$command1">
	<input type="hidden" name="pagemode" value="$pagemode">
	<input type="hidden" name="style1" value="$Style1">
	<input type="hidden" name="style2" value="$Style2">
	<input type="hidden" name="socbayaran" value="$peringkat_bayaran" >
	<input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran" >
	<input type="hidden" name="idNegeri" value="$idNegeri">
	<input type="hidden" name="Xidbaucar" value="$idbaucer">
	<input type="hidden" name="idBaucer" value="$!idbaucer">
    
<script type="text/javascript">
	
	function selectBayaran(negeri,idNegeri,idPermohonan) {
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.command.value = "penyata";
		document.${formName}.pagemode.value = "penyataview";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.negeri.value = negeri;
		document.${formName}.idNegeri.value = idNegeri;
		
		var strString = document.${formName}.socbayaran;
		for( intIndex = 0; intIndex < strString.length; intIndex++ ){
	 		if(strString[intIndex].selected=true && strString[intIndex].value !=0){
	 			document.${formName}.peringkat_bayaran.value = strString[intIndex].value; 			
	 		}
	 		
	    }
		document.${formName}.action = "";
		document.${formName}.submit();		
		//doAjaxCall${formName}("cukaiperingkatbayar");			
		
	}
	
	function senaraiCukai(negeri,idNegeri,idPermohonan,idFail,peringkatBayaran) {
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.command.value = "penyata";
		document.${formName}.pagemode.value = "penyataview";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.negeri.value = negeri;
		document.${formName}.idNegeri.value = idNegeri;
		document.${formName}.fail.value = idFail;
		document.${formName}.peringkat_bayaran.value = peringkatBayaran;
		document.${formName}.action = "";
		document.${formName}.submit();
		//doAjaxCall${formName}("cukaiperingkatbayar");			
		
	}
	//Tamat frmCukaiPeringkatBayaran
	<!--
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1" ,{defaultTab:$selectedTab});
	//-->
	
	//Mula frmCukaiPenyata
		
	function setSelected(tabId) {
	    document.${formName}.tabId.value = tabId;
	}
	
	
	function simpanPenyata(){
		var mycb = new Array();
		var mycukaijumlah = new Array();
		var bilangan = document.${formName}.bilangan.value; 
		var cb = "";

		if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("simpanpenyatacukai","bil="+bilangan);
	
	}
	function PenyataView() {
		document.${formName}.action = "";
		document.${formName}.pagemode.value = "penyataview";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		//document.${formName}.submit();
		
		doAjaxCall${formName}("penyata");			
	
	}
	
	function BaucerView() {
		document.${formName}.action = "";
		document.${formName}.pagemode.value = "baucerview";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		//document.${formName}.submit();
		
		doAjaxCall${formName}("baucer");			
		
	}

	//12/10/2010
	function SeterusBaucer(idNegeri,idPeringkatbayaran) {
		//document.${formName}.action = "";
		//document.${formName}.pagemode.value = "cukaiBayaran";
		document.${formName}.pagemode.value = "bayaranview";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.idNegeri.value = idNegeri;
		document.${formName}.idPeringkatbayaran.value = idPeringkatbayaran;
		//document.${formName}.submit();
		doAjaxCall${formName}("bayaran");			

	}
			
	//21/10/2010
	function doChangeYear(negeri,idNegeri,idPermohonan){
		document.${formName}.tahun_cukai.value = document.${formName}.Form_tahun.value;
	
		//document.${formName}.command.value = "cukaiperingkatbayar";
		//document.${formName}.pagemode.value = "penyataview";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.negeri.value = negeri;
		document.${formName}.idNegeri.value = idNegeri;

		//document.${formName}.action = "";
		//document.${formName}.submit();
		//doAjaxCall${formName}("cukaiperingkatbayar");	
		doAjaxCall${formName}(document.${formName}.command1.value);	
	
	}
	
	function KemaskiniPenyata() {
		if(document.${formName}.sumCukai.value == 0){
			alert('Tiada Rekod Untuk Di Simpan');
			return;
		}	
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.pagemode.value = "simpanPenyata";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		//document.${formName}.action = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("penyata");	
		
	}
	
	function KembaliPenyata() {
		document.${formName}.action = "";
		//document.${formName}.command.value = "";
		//document.${formName}.submit();
		
		doAjaxCall${formName}("");	
	
	}
	
	//rosli 2010/04/19
	function cetakPenyata(tahun,idnegeri){
		var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenarai";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	//Tamat frmCukaiPenyata
	
	//Mula baucer
	
	function TambahBaucer() {
		document.${formName}.action = "";
		//document.${formName}.command.value = "tambahBaucer";
		document.${formName}.pagemode.value = "baru";
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");	
		
	}
	
	function EditBaucer(idBaucer) {
		document.${formName}.action = "";
		//document.${formName}.command.value = "tambahBaucer";
		document.${formName}.pagemode.value = "view";
		document.${formName}.idBaucer.value = idBaucer;
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");	

	}
	
	function kemaskiniBaucer() {
		document.${formName}.action = "";
		//document.${formName}.command.value = "tambahBaucer";
		document.${formName}.pagemode.value = "kemaskinibaucer";
		//document.${formName}.idBaucer.value = idBaucer;
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");	

	}
		
	function hapusBaucer(){	
		if(document.${formName}.bil.value != "0.0"){
				alert('Sila pastikan " Maklumat Bayaran " dihapus terlebih dahulu.');
				return; 
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.pagemode.value = "hapusbaucer";
		//document.${formName}.idBayaranCukai.value = i;
		doAjaxCall${formName}("baucer");			
	
	}
	
	function KembaliBaucer() {
		//document.${formName}.action = "";
		document.${formName}.command.value = "";
		//document.${formName}.submit();
		
		doAjaxCall${formName}("");	
		
	}
	
	// 2010/06/09 -Senarai Dokumen/Surat
	function senaraiSurat(id,baucar){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
		document.${formName}.idBaucer.value = baucar;
	}
	
	//rosli 2010/04/30
	function CetakBaucer(idbaucer){
		var url = "../servlet/ekptg.report.htp.SuratHTPCukai?idbaucer="+idbaucer+"&template=HTPCukaiBaucer";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	// 2010/06/09 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTPCukai?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
	
	function kembaliDariTambahBaucer() {
		document.${formName}.action = "";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.pagemode.value = "baucerview";
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");	
	
	}
	
	
	function doChangeDaerah() {
		doAjaxCall${formName}("baucer","pagemode=baru");
	}
	
	function simpanBaucer() {
		if(document.${formName}.pagemode.value == "baru"){
			if(document.${formName}.socDaerah.value == ""){
				alert('Sila pilih " Daerah " terlebih dahulu.');
	  			document.${formName}.socDaerah.focus(); 
				return; }
		}
		if(document.${formName}.txdTarikhBaucer.value == ""){
			alert('Sila masukkan " Tarikh Baucer " terlebih dahulu.');
	  		document.${formName}.txdTarikhBaucer.focus(); 
			return; 
		}	
		if(document.${formName}.txtNoBaucer.value == ""){
			alert('Sila masukkan " No Baucer " terlebih dahulu.');
	  		document.${formName}.txtNoBaucer.focus(); 
			return; 
		}
		
		if(document.${formName}.txtAmaunBaucer.value == ""){
			alert('Sila masukkan " Amaun Baucer " terlebih dahulu.');
	  		document.${formName}.txtAmaunBaucer.focus(); 
			return; 
		}
	/*	if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
	  		document.${formName}.txdTarikhTerima.focus(); 
			return; 
		}
	*/
		if ( !window.confirm("Anda Pasti?") ) return;
		//document.${formName}.method = "post";
		document.${formName}.action = "";
		//document.${formName}.command.value = "tambahBaucer";
		document.${formName}.pagemode.value = "simpanB";	
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");
	
	}
	
	//Tamat baucer
	
	//Mula bayaran
	
	function TambahBayaran() {
		/* document.${formName}.action = "";
		document.${formName}.command.value = "tambahBayaran";*/
		document.${formName}.pagemode.value = "baru";
		//document.${formName}.submit(); 
		doAjaxCall${formName}("bayaran");			
		
	}
	
	function kembaliBayaran() {
		document.${formName}.action = "";
		//document.${formName}.command.value = "cukaiperingkatbayar";
		document.${formName}.pagemode.value = "baucerview";
		//document.${formName}.submit();
		doAjaxCall${formName}("baucer");			

	}

	function kembaliDariTambahBayaran() {
		document.${formName}.action = "";
		document.${formName}.pagemode.value = "bayaranview";
		doAjaxCall${formName}("bayaran");	
				
	}
	
	function simpanBayaran() {
		if(document.${formName}.pagemode.value == "baru"){
			if(document.${formName}.socBaucer.value == ""){
				alert('Sila pilih " Baucer " terlebih dahulu.');
	  			document.${formName}.socBaucer.focus(); 
				return; }
		}
		if(document.${formName}.txdTarikhBayaran.value == ""){
			alert('Sila masukkan " Tarikh Bayaran " terlebih dahulu.');
	  		document.${formName}.txdTarikhBayaran.focus(); 
			return; 
		}
		if(document.${formName}.txtNamaBank.value == ""){
			alert('Sila masukkan " Nama Bank " terlebih dahulu.');
	  		document.${formName}.txtNamaBank.focus(); 
			return; 
		}
		if(document.${formName}.txtAmaun.value == ""){
			alert('Sila masukkan " Amaun " terlebih dahulu.');
	  		document.${formName}.txtAmaun.focus(); 
			return; 
		}
		if(document.${formName}.txtNoRujBayaran.value == ""){
			alert('Sila masukkan " No Rujukan Bayaran " terlebih dahulu.');
	  		document.${formName}.txtNoRujBayaran.focus(); 
			return; 
		}
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.action = "";
		document.${formName}.pagemode.value = "simpanBay";	
		doAjaxCall${formName}("bayaran");	
		
	}
	
		
	function EditBayaran(idBayaranCukai,idBaucer) {
		document.${formName}.pagemode.value = "viewBay";
		document.${formName}.idBayaranCukai.value = idBayaranCukai;
		document.${formName}.idBaucer.value = idBaucer;
		doAjaxCall${formName}("bayaran");	

	}
	
	function kemaskini(i){	
		document.${formName}.pagemode.value = "kemaskinibayaran";
		document.${formName}.idBayaranCukai.value = i;
		doAjaxCall${formName}("bayaran");			
	
	}
	
	// Simpan,Kemaskini Bayaran
	function simpan(i,j){
		document.${formName}.pagemode.value = "kemaskinisimpanbayaran";	
		document.${formName}.idBayaranCukai.value = i;
		document.${formName}.idBaucer.value = j;
		doAjaxCall${formName}("bayaran");			

	}
	
	function hapusBayaran(i){	
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.pagemode.value = "hapusbayaran";
		document.${formName}.idBayaranCukai.value = i;
		doAjaxCall${formName}("bayaran");			
	
	}
	
	//rosli 2010/04/19
	function CetakBayaran(tahun,idnegeri){
		var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenaraiBaucer";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function KembaliBayaran() {
		document.${formName}.action = "";
		document.${formName}.pagemode.value = "baucerview";
		doAjaxCall${formName}("bayaran");
					
	}
	//Tamat bayaran
	
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
	
	function RemoveNonNumeric( strString ){
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
	
	function addText(field) {
		if(field.value==""){
			field.defaultValue="";	}
	}
	
	
	
</script>
    