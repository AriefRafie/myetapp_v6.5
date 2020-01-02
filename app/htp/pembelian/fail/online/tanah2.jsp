<!-- 
##parse('app/htp/pembelian/fail/paging2.jsp')	
-->
<!--<fieldset>
<legend><strong>MAKLUMAT PEMBELIAN</strong> </legend> -->
<table width="100%">
	<tr>
		<td>&nbsp;</td>
	</tr>
<tr>
	<td>
	
	<fieldset><legend>MAKLUMAT PERMOHONAN</legend>
	#parse ("app/htp/pembelian/fail/fileInfo.jsp")
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
	             		 	
	             		 	
	             		 	 <li class="TabbedPanelsTab" title="" tabindex="0" onclick="javascript:setSelected(0,'maklumatTanah','drafview',0)">MAKLUMAT TANAH</li>
	             		 	 ##if($URUSAN_BANGUNAN == $!htpPermohonan.permohonan.pfdFail.kodSuburusan )
	             		 	 #if($URUSAN_TANAH == $!htpPermohonan.permohonan.pfdFail.idSubUrusan || $URUSAN_TANAH1 == $!htpPermohonan.permohonan.pfdFail.idSubUrusan)
           		 	 			<li class="TabbedPanelsTab" title="" tabindex="1" onclick="javascript:setSelected(1,'viewPemilik','drafview',0)">NAMA PEMILIK</li>
	             		 	 	<li class="TabbedPanelsTab" title="" tabindex="2" onclick="javascript:setSelected(2,'viewPenjual','drafview',0)">NAMA PENJUAL</li>
	             		 	 
	             		 	 #else
	             		 	 	<li class="TabbedPanelsTab" title="" tabindex="1" onclick="javascript:setSelected(1,'maklumatBangunan','drafview',0)">MAKLUMAT BANGUNAN</li>
	             		 	 	<li class="TabbedPanelsTab" title="" tabindex="2" onclick="javascript:setSelected(2,'viewPemilik','drafview',0)">NAMA PEMILIK</li>
	             		 	 	<li class="TabbedPanelsTab" title="" tabindex="3" onclick="javascript:setSelected(3,'viewPenjual','drafview',0)">NAMA PENJUAL</li>
  	             		 	 #end
	             		 </ul>
             		  <div class="TabbedPanelsContentGroup">
    
	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '0')
                						
				                    		#parse("app/htp/pembelian/fail/maklumatTanah2.jsp")
				                    	
				               		
				               	#end
	               			 </DIV>
	               			 
	               			  <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '1')
                						
                					#if($URUSAN_TANAH == $!htpPermohonan.permohonan.pfdFail.idSubUrusan || $URUSAN_TANAH1 == $!htpPermohonan.permohonan.pfdFail.idSubUrusan)
               							#parse("app/htp/pembelian/fail/pemilik2.jsp")              						 	
                  					#else
               					
                						#parse("app/htp/pembelian/fail/maklumatBangunan2.jsp")                					
                					#end	 
				               		
				               	#end
	               			 </DIV>
	               			 
	               			  <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '2')
	               			 			#if($URUSAN_TANAH == $!htpPermohonan.permohonan.pfdFail.idSubUrusan || $URUSAN_TANAH1 == $!htpPermohonan.permohonan.pfdFail.idSubUrusan)
               							#parse("app/htp/pembelian/fail/maklumatPenjual2.jsp")              						 	
                  					#else
                	
				                    	#parse("app/htp/pembelian/fail/pemilik2.jsp")
				               		#end
				               	#end
	               			 </DIV>
	               			 
	               			 <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '3')
                	
				                    	#parse("app/htp/pembelian/fail/maklumatPenjual2.jsp")
				               		
				               	#end
	               			 </DIV>
	               			 
               			</DIV>
             		 </div>
             		 
             		 
             		
				</td>
			
			</tr>
		
		</table>
	
	
	</td>


</tr>
	<tr>	
		<td align=center>
       		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step1($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" />	    	
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