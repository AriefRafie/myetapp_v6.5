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
	             		 	<!-- <li class="TabbedPanelsTab" title="" tabindex="3" onclick="javascript:setSelected(3,'rundingan','drafview',0)">RUNDINGAN HARGA</li> -->
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
               							<style type="text/css">
<!--
.r {
	color: #00C;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
		<td>
<fieldset>
<legend><strong>SENARAI HAKMILIK</strong></legend>
<table width="100%">
<tr>
	<tr class="table_header">
	<!-- 
		<td scope="col">Bil.</td>
					<td scope="col">Jenis Hakmilik</td>
					<td scope="col">No. Hakmilik</td>
                  	<td scope="col">Kod Lot</td>
					<td scope="col">No. Lot/PT</td>
					<td scope="col">Kod Luas</td>
					<td scope="col">Luas</td>
					<td scope="col">No. Pelan</td>
					<td scope="col">Tarikh Mula</td>
					<td scope="col">Tarikh Luput</td>
			<td scope="col">&nbsp;</td> -->
		<td scope="col" width="3%">Bil.</td>
		<td scope="col" width="20%">No. Hakmilik</td>
		<td scope="col" width="17%">No. Lot/PT</td>
		<td scope="col" width="20%">Luas(H)</td>
		<td scope="col" width="20%">No. Pelan</td>
		<td scope="col" width="10%">Tarikh Mula</td>
		<td scope="col" width="10%">Tarikh Luput</td>
		<!--<td scope="col" width="0%">&nbsp;</td>		-->	
	</tr>
	
	#set ( $cnt = 0 )
    #foreach($mo in $mt)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '')
    #set( $row = "row1" )
    #elseif (($senarai.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
		<tr >
		  <td class="$row">$cnt.</td>
		  <!-- <td class="$row">$!mo.getJenisKeterangan()</td> -->
		  <td class="$row">$!mo.getKodjenishakmilik()$!mo.nohakmilik</td>
		  <!--<td class="$row">$!mo.getKeteranganLot()</td>-->
		  <td class="$row">$!mo.getKeteranganLot()$!mo.getNolot()</td>
		  <!--<td class="$row">$!mo.getLuasKeterangan()</td>-->
		  <td class="$row">$!mo.getLuas() </td>
		  <td class="$row">$!mo.getNoPlan()</td>
		  <td class="$row">$!mo.getTarikhMula()</td>
		  <td class="$row">$!mo.getTarikhLuput()</td>
		  <!--<td class="$row"></td> -->
    </tr>
     #end


</table>
</fieldset>
<br />
#if($mode != "0")
##else
#parse("app/htp/pembelian/fail/online/maklumatPemilikOnline.jsp") 
#end
<br />
<fieldset>
<legend><strong>SENARAI PEMILIK</strong></legend>
<table width="100%">
	#set($portal_role = "${session.getAttribute('myrole')}")
	##if($buttonMode != "2")
	#if ($portal_role!='online_kjpagensi')
		<TR>
			<TD colspan="4">
				 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahPemilik')">
            </TD>
        </TR>
    #end
  	##end
          <tr class="table_header">
              <td width="3%" scope="col">Bil.</td>
              <td width="15%" scope="col">No. Hakmilik</td>
              <td width="77%" scope="col">Nama Pemilik</td>
              <td width="5%" scope="col">&nbsp;</td>
         </tr>
         #set ( $cnt = 0 )
   		 #foreach($pk in $pp)
    	 #set ( $cnt = $cnt + 1 )
    	 #if ($senarai.bil == '')
    	 #set( $row = "row1" )
    	 #elseif (($senarai.bil % 2) != 0)
    	 #set( $row = "row1" )
    	 #else 
    	 #set( $row = "row2" )
    	 #end
<tr>
            <td scope="col">$!cnt.</td>
            <td scope="col"><a href="javascript:detailPemilik('$!pk.getIdpihakberkepentingan()')" class="r"> $!pk.getNoHakmilik()</a></td>
            <td scope="col">$!pk.getNama() </td>
            <td scope="col">
            	<input type="button" name="btnDelete" value="Hapus" onclick="doAjaxCall${formName}('deletePemilik')">
            	<input type="hidden" name="Idpihakberkepentingan" value="$!pk.getIdpihakberkepentingan()" />
            </td>
          </tr>
           #end
</table>
</fieldset>

 		  </td>
    </tr>
 	#if($buttonMode != "2")
  	#end 	
  <tr>
  
		    	<td >

        	<table width="100%" border="0">
          		<tr>
          			<td width="8%"><font color=red style=font-size:10px>Perhatian :</font></td>
          			<td width="92%">$!perhatian7</td>
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        		</tr>
        		<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian9</td>
        		</tr>
			</table>    	
		    	</td>
		    
	 </tr>
	  
</table>
<input type="hidden" name="Idpihakberkepentingan" value="$!pemilik.getIdpihakberkepentingan()" />
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
               							##parse("app/htp/pembelian/fail/online/pemilikOnline.jsp")              						 	
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
	               			 
	               			 <!-- <div class="TabbedPanelsContent">
	               			 	#if($selectedTab == '3')
	               			 		#parse("app/htp/pembelian/fail/online/rundingan.jsp")              						 	
                  					
				               	#end
	               			 </div> -->
	               			 
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
		
		#if($saiz_listTanah > 0 && $saiz_listPB > 0 && $saiz_listPenjual > 0 && $saiz_listLampiran > 0)
			<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Seterusnya" onclick="Pengesahan()" />
			<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step1Online($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" />
		#else
			<b><font color="red"><span class="blink">Sila lengkapkan kesemua maklumat terlebih dahulu.</b></font></span>
       		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="step1Online($!htpPermohonan.permohonan.getIdPermohonan(),$!htpPermohonan.getIdHtpPermohonan())" />
		#end 	
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