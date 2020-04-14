<!-- <script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" /> 
-->

<style type="text/css">
<!--
.g {
	color: #F00;
}
.style1 {color: #FF0000}
-->
</style>
#set ($a='1')
<input name="detailMode" type="hidden" >
<input name="idpermohonan" type="hidden" value="$!idpermohonan" /> 
<input name="idfail" type="hidden" value="$!idfail" /> 

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
#parse ("app/htp/permohonan/online/pagingPermohonanOnline.jsp") 
<table width="100%" border="0">
	<tr>
	    <td>
	    	#parse("app/htp/permohonan/online/frmMaklumatPermohonanView.jsp") <!-- editby zul - ubah path fail -->
	    </td>
 	</tr>
   
	<tr>
		<td>
    		<fieldset>
    		<legend> MAKLUMAT TANAH LANJUTAN</legend>##$id_jenistanah
     
	      	<div id="TabbedPanels1" class="TabbedPanels">
	        	<ul class="TabbedPanelsTabGroup">   
	        	#if($!id_jenistanah != "3")       		    
			        <li class="TabbedPanelsTab" title="Maklumat Asas Tanah" tabindex="0" onclick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0,'$idpermohonanlokasi')" >MAKLUMAT ASAS TANAH</li>
			    	
			    	<!-- *******  Start Addby zulfazdli 11/5/2017- for view sahaja Maklumat Notis untuk user online ****** -->
			    	#if($!idNegeriNotis=="13")
			    		<!-- Jika id negeri sama dengan 13 adalah serawak, paparkan tab L & S 80 -->
		        		<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="L & S 80" >L & S 80</li>
		      		#elseif($!idNegeriNotis=="12")
		      			<!-- Jika id negeri sama dengan 12 adalah sabah, paparkan tab SURAT TAWARAN KELULUSAN -->
			        	<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Surat Tawaran Kelulusan " >SURAT TAWARAN KELULUSAN</li>
		      		#else
		      			<!-- Jika id negeri selain 12 dan 13 adalah semenanjung, paparkan maklumat NOTIS 5A KTN -->
			        	<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Notis 5A" >NOTIS 5A KTN</li>
					#end
					<!-- *** End *** -->
					
			    #else
			        <li class="TabbedPanelsTab" title="Maklumat Asas Tanahhhhh" tabindex="0" onclick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0,'$idpermohonanlokasi')" >MAKLUMAT ASAS TANAH</li>
			        <li class="TabbedPanelsTab" title="Borang K" tabindex="0" onclick="javascript:selectTab(1,'kemaskinipermohonan','BorangK',0,'$idpermohonanlokasi')">BORANG K</li>
					
					<!-- ******* Start Addby zulfazdli 11/5/2017 - for view sahaja Maklumat Notis untuk user online ******* -->
					#if($!idNegeriNotis=="13")
						<!-- Jika id negeri sama dengan 13 adalah serawak, paparkan tab L & S 80 -->
			        	<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="L & S 80" >L & S 80</li>
		      		#elseif($!idNegeriNotis=="12")
		      			<!-- Jika id negeri sama dengan 12 adalah sabah, paparkan tab SURAT TAWARAN KELULUSAN -->
			        	<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Surat Tawaran Kelulusan " >SURAT TAWARAN KELULUSAN</li>
		      		#else
		      			<!-- Jika id negeri selain 12 dan 13 adalah semenanjung, paparkan maklumat NOTIS 5A KTN -->
			        	<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Notis 5A" >NOTIS 5A KTN</li>
					#end
					<!-- *** End *** -->
					
			    #end
			        <li class="TabbedPanelsTab" title="Lokasi Tanah" tabindex="0" onclick="javascript:selectTab(2,'kemaskinipermohonan','LokasiTanah',0,'$idpermohonanlokasi')" style="display:none" >Lokasi Tanah</li>
			        <li class="TabbedPanelsTab" title="Lakaran/Pelan Tanah" tabindex="0" onclick="javascript:selectTab(3,'kemaskinipermohonan','LakaranPelan',0,'$idpermohonanlokasi')" style="display:none">Lakaran/Pelan Tanah</li>
			        <li class="TabbedPanelsTab" title="Keputusan/Pembayaran Permohonan" tabindex="0" onclick="javascript:selectTab(4,'kemaskinipermohonan','Pembayaran',0,'$idPermohonan')" style="display:none" >Keputusan/Pembayaran Permohonan</li>
			        <li class="TabbedPanelsTab" title="Notis 5A" tabindex="0" onclick="javascript:selectTab(5,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" style="display:none" >Notis 5A</li>
	      		</ul>
	      
				<div class="TabbedPanelsContentGroup">      
					<div class="TabbedPanelsContent">
						## selectedTab == $selectedTab|| tabmode == $tabmode || id_jenistanah = $id_jenistanah || bilBorangK == $!bilBorangK
						#if($selectedTab == '0')
							#if($tabmode == '0')
								#if($!id_jenistanah=="3")
									#if($!bilBorangK > 0)
										#parse ("app/htp/permohonan/online/frmTerimaPohonMaklumatAsasTanah.jsp")  
									#else
										#parse ("app/htp/permohonan/online/frmTerimaPohonMaklumatAsasTanahPPTOnline.jsp") 
									#end
								#else
									#parse ("app/htp/permohonan/online/frmTerimaPohonMaklumatAsasTanah.jsp")  
								#end
							#elseif($tabmode == '9')
								#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahPPTHakmilik.jsp") 
							#elseif($tabmode == '1')
								#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahTambah.jsp") 
							#elseif($tabmode == '2')
								##mode=Kemaskini
								##parse ("app/htp/permohonan/online/frmTerimaPohonMaklumatAsasTanah.jsp") 
								#parse ("app/htp/permohonan/online/frmTerimaPohonMaklumatAsasTanahTambah.jsp") 
							#else
								#parse ("app/htp/permohonan/online/frmTerimaPohonDetailTanah.jsp") 
							#end
						
						#end
					</div>
					                
					<div class="TabbedPanelsContent" > 
						#if($selectedTab == '1')
							#if($tabmode == '0')
								#parse ("app/htp/permohonan/frmTerimaPohonMaklumatBorangKOnline.jsp")       
							#else
								#parse("app/htp/frmPembelianTBangunAjax.jsp")
							#end
						#end
					</div>
					                
					<div class="TabbedPanelsContent">
						#if($selectedTab == '2')
							#if($tabmode == '0')
								#parse ("app/htp/frmTerimaPohonLokasiTanah.jsp")
							#else
								#parse("app/htp/frmTerimaPohonLokasiTanahTambah.jsp")
							#end
						#end
					</div>
					        
					<div class="TabbedPanelsContent"> 
						#if($selectedTab == '3')
							#if($tabmode == '0')
								#parse ("app/htp/frmTerimaPohonPelan.jsp") 
							#else
								#parse("app/htp/frmTerimaPohonPelanTambah.jsp")
							#end
						#end
					</div>
					         
					<div class="TabbedPanelsContent">
						#if($selectedTab == '4')
							#if($tabmode == '0')
								#parse ("app/htp/frmTerimaPohonPembayaranOnline.jsp") 
							#else
								#parse("app/htp/frmTerimaPohonPembayaranOnline.jsp")
							#end
						#end
					</div>
					
					<!-- ******** Start Addby zulfazdli 11/5/2017 - for view sahaja Maklumat Notis untuk user online ******** -->
					<div class="TabbedPanelsContent"> 
					#if($selectedTab == '5')
		               	#if($tabmode == '0')
		               		<!-- jika click tab akan hantar tabmode 0 dan pergi ke senarai notis --> 
			               	#parse ("app/htp/permohonan/online/frmNotisSenaraiOnline.jsp") 
	              		#elseif($tabmode == 1)
	              			<!-- Bila Click View pada senarai notis akan hantar tabmode 1 pada function Notis5AView file indexOnline.jsp -->
	               			#if($!idNegeriNotis=="13") 
	               				<!-- Jika id negeri sama dengan 13 adalah serawak, paparkan maklumat L & S 80 -->
	                			#parse("app/htp/permohonan/online/frmBayaranNotisViewOnlyOnline13.jsp")
		      				#elseif($!idNegeriNotis=="12")
		      					<!-- Jika id negeri sama dengan 12 adalah Sabah, paparkan maklumat SURAT TAWARAN KELULUSAN -->
	                			#parse("app/htp/permohonan/online/frmBayaranNotisViewOnlyOnline12.jsp")
	                		#else	
	                			<!-- Jika id negeri selain 12 dan 13 adalah semenanjung, paparkan maklumat NOTIS 5A KTN -->
	                   			#parse("app/htp/permohonan/online/frmBayaranNotisViewOnlyOnline.jsp")
	                   		#end
		  	            #end
		            #end
					</div>
					<!-- ******** End Addby zulfazdli ********-->
					
				</div>
				
	    	</div>
    		</fieldset>
		</td>
	</tr>
		
<!-- 	idjawatan ===== $!idjawatan <br/>
	statusLangkah ====== $!statusLangkah <br/>
	semakMode ===== $!semakMode <br/>
	statusSemasa ===== $!statusSemasa<br/> -->
	<!-- BUTTON -->
	<tr>
		<td align="center">
			#if($!semakMode == "update")
				#if(($!idjawatan.equals("20")||$!idjawatan.equals("24")) && $!statusSemasa.equals("-4"))
					<input class="stylobutton" type="button" onclick="hantarPengesahan('$idPermohonan')" value="Hantar Semakan" />
					<!-- <input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Semakan" onclick="doAjaxCall${formName}('simpanpengesahan')" />  -->
				#elseif ($!idjawatan.equals("9") && $!statusSemasa.equals("-3"))
			 		<input class="stylobutton" type="button" onclick="hantarPengesahan('$idPermohonan')" value="Hantar Kelulusan" />
					<!-- <input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Kelulusan" onclick="doAjaxCall${formName}('simpanpengesahan')" /> -->
				#elseif ($!idjawatan.equals("4") && $!statusSemasa.equals("-2"))
					<input class="stylobutton" type="button" onclick="hantarPengesahan('$idPermohonan')" value="Lulus Permohonan" />
					<!-- <input type="button" name="cmdSimpan" id="cmdSimpan" value="Hantar Permohonan" onclick="doAjaxCall${formName}('simpanpengesahan')" /> -->
			     #end
			#else
				<label>$!statusInfo</label>
			#end
			<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
		</td>
	</tr>
	<!-- END BUTTON -->
</table>
<!--$idhakmilikurusan $idpermohonanlokasi-->
<input type="hidden" name="idpermohonan" id="dipermohonan" value=" $idPermohonan" /> 
<input type="hidden" name="idfail" id="idfail" value=" $idfail" />
<input type="hidden" name="idhakmilikurusan" id="idhakmilikurusan" value="$idhakmilikurusan" />

<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
