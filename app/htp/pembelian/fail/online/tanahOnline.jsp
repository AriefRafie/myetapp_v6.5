 ##parse('app/htp/pembelian/fail/online/pagingOnline.jsp')
<!-- <fieldset>
<legend><strong>MAKLUMAT PEMBELIAN ONLINE</strong> </legend> -->
 <table width="100%">
<!---->	

#set($perhatian7="<i><font style=font-size:10px>1. Sila pastikan permohonan ini mempunyai hakmilik untuk meneruskan permohonan</font></i>")	
#set($perhatian8="<i><font style=font-size:10px>2. Sekiranya mempunyai pelan untuk dimuatnaik, sila klik pada menu <b>Muat Naik Dokumen</b></font></i>")	
#set($perhatian9="<i><font style=font-size:10px>3. Sila klik butang <b>Seterusnya</b> untuk membuat penghantaran permohonan.</font></i>")			
	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td>

			<fieldset>
				<legend>MAKLUMAT PERMOHONAN</legend>
				#parse ("app/htp/pembelian/fail/online/fileInfoOnline.jsp")
			</fieldset>

		</td>
	</tr>
	<tr>
	<td>
		<table width="100%">
			<tr>
				<td>
					 <div id="TabbedPanels1" class="TabbedPanels">
	            
	             		 <ul class="TabbedPanelsTabGroup">  
	             		 	             		 	
	             		 	<li class="TabbedPanelsTab" title="" tabindex="0" onclick="javascript:setSelected(0,'maklumatanahonline','drafview',0)">MAKLUMAT TANAH</li>          		 	 
	             		 	<li class="TabbedPanelsTab" title="" tabindex="1" onclick="javascript:setSelected(1,'viewpemilikonline','drafview',0)">NAMA PEMILIK</li>
	             		 	<li class="TabbedPanelsTab" title="" tabindex="2" onclick="javascript:setSelected(2,'viewpenjualonline','drafview',0)">NAMA PENJUAL</li>
	             		 	<li class="TabbedPanelsTab" title="" tabindex="3" onclick="javascript:setSelected(3,'rundingan','drafview',0)">RUNDINGAN HARGA</li>
	             		 	<li class="TabbedPanelsTab" title="" tabindex="4" onclick="javascript:setSelected(4,'viewLampiran','drafview',0)">MUAT NAIK DOKUMEN</li>
	             		 	<!-- <li class="TabbedPanelsTab" title="" tabindex="3" onclick="javascript:setSelected(4,'viewsemakan','drafview',0)">Senarai Semakan</li> -->
  	             		 	 
	             		 </ul>
             		  <div class="TabbedPanelsContentGroup">
    
	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '0')
                						
				               		#parse("app/htp/pembelian/fail/online/maklumatTanahOnline.jsp")				                    	
				               		
				               	#end
	               			 </div>
	               			 
	               			  <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '1')
                						
                					##if($URUSAN_TANAH == $!htpPermohonan.permohonan.pfdFail.idSubUrusan || $URUSAN_TANAH1 == $!htpPermohonan.permohonan.pfdFail.idSubUrusan)
               							#parse("app/htp/pembelian/fail/online/pemilikOnline.jsp")              						 	
                  					##else              					
                						##parse("app/htp/pembelian/fail/maklumatBangunan.jsp")                					
                					##end	 
				               		
				               	#end
	               			 </DIV>
	               			 
	               			  <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '2')
	               			 		##if($URUSAN_TANAH == $!htpPermohonan.permohonan.pfdFail.idSubUrusan || $URUSAN_TANAH1 == $!htpPermohonan.permohonan.pfdFail.idSubUrusan)
               							#parse("app/htp/pembelian/fail/online/maklumatPenjualOnline.jsp")              						 	
                  					##else
				                    	##parse("app/htp/pembelian/fail/online/pemilikOnline.jsp")
				               		##end
				               	#end
	               			 </div>
	               			 
	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '3')
	               			 		#parse("app/htp/pembelian/fail/online/rundingan.jsp")              						 	
                  					
				               	#end
	               			 </div>
	               			 
	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '4')
                	
				                    	##parse("app/htp/pembelian/fail/online/maklumatPenjualOnline.jsp")
				                    	
				                    	#parse("app/htp/pembelian/fail/online/dokumenOnline.jsp")
				               		
				               	#end
	               			 </div>

	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '5')
                	
				                    	##parse("app/htp/pembelian/fail/online/dokumenOnline.jsp")
						                #parse("app/htp/pembelian/fail/online/perakuanPembelianOnline.jsp")
		               		
				               	#end
	               			 </div>	               			 
              			
             		 </div>
	          		 
             		 
             		 </div>
   
             		 
             		
				</td>
			
			</tr>

		
		</table>
	
	
	</td>


</tr>
	<tr>	
		<td align=center>
			<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Seterusnya" onclick="Pengesahan()" />
       		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step1Online($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" />
		    	
		</td>	
	</tr>
</table>
<!-- </fieldset> -->
<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>

<script type="text/javascript">
	var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
	
</script>

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
	}}
</script>