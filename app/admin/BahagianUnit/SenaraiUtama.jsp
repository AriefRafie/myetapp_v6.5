<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>BAHAGIAN </strong>
		
		<!-- 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		 -->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_TambahBahagianHQ').style.display=''; doDivAjaxCall$formname('div_TambahBahagianHQ','editBahagian','ID_SEKSYEN=');">
		<img title="Tambah Bahagian" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_HQ" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_TambahBahagianHQ">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
		<td id="defaultBahagianHQ">	
			<table border="1" cellpadding="1" cellspacing="0" width="96%" align="center" > 
	#if($listBahagianHQ.size()>0)
    
    <script> 
		
		  var defaultBhgn =  document.getElementById('defaultBahagianHQ');
		  defaultBhgn.style.display = "none";
		  
		  var div_TambahBahagianHQ =  document.getElementById('div_TambahBahagianHQ');
		  div_TambahBahagianHQ.style.display = "none";
		  
		</script>
        
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_BhgnHQ.jsp")
		 <!--  #parse("app/utils/record_paging.jsp") -->
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Nama Bahagian/Unit</td>
         <!--   <td   align="left" valign="top">Negeri</td> -->
           <td   align="left" valign="top">Status</td>
           <td   align="left" valign="top">Catatan</td>
           <td   align="left" valign="top">Tindakan</td>
	</tr>
	#if($listBahagianHQ.size()>0)
	#foreach($cariBahagianHQ in $listBahagianHQ)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" >$cariBahagianHQ.BIL. </td>
			   <td  align="left" valign="top">$cariBahagianHQ.NAMA_SEKSYEN ($cariBahagianHQ.KOD_SEKSYEN)</td>
			  <!--  <td  align="left" valign="top">??</td> -->
			   <td  align="left" valign="top">$cariBahagianHQ.FLAG_AKTIF</td>
			   <td align="left" valign="top">$cariBahagianHQ.CATATAN</td>
         
              <td align ="center" valign="top"><a href="javascript:doDivAjaxCall$formname('div_viewBahagianHQ$cariBahagianHQ.ID_SEKSYEN','viewDetailsBahagianHQ','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN');">
              <img src="../img/edit.gif" border="0"></a>
			<!--<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>-->
</td>  
		</tr>
		<tr  id="div_viewBahagianHQ$cariBahagianHQ.ID_SEKSYEN">
				<td align="left" valign="top" colspan="14">
		
		</td>
		</tr>
		#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
		
			
		</td>
		</tr>			
		</table>
		<!-- close CT -->
       
        
		<br>
		
	<div id="div_SenaraiBahagianHQ" >
		#if($FlagCari=="Y")
		<script> 
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_SenaraiBahagianHQ','showSenaraiBahagian','FlagCari=$FlagCari&carianBahagianHQ='+$jquery('#carianUmum').val());			 	  
		  });
		</script>
		#end
		</div>	
        
 <div id="SenaraiForPrint">
 </div>
	</td>
	</tr>
	
<!--	<tr>
	<td>
	<br>

		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_main">
		</td>
		<td width="58%" class="underline_td_main">
		<strong>BAHAGIAN (NEGERI)</strong>		
		
		<div  id="span_LINK_CT_CARIAN_Negeri">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_NEGERI','showCarianBahagian','TYPE=NEGERI');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Negeri"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<a href="javascript:document.getElementById('div_TambahBahagianNegeri').style.display=''; doDivAjaxCall$formname('div_TambahBahagianNegeri','editBahagianNegeri','ID_SEKSYEN=');">
		<img title="Tambah Bahagian Negeri" src="../img/plus3.gif" border="0"></a>
		</td>
		</tr>
		</table>
		<br>
		
		
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">				
		<tr   >
		<td colspan="4" valign="top" id="div_CT_CARIAN_NEGERI">		
		</td>
		</tr>		
        
        <tr>
        <td colspan="4" id="div_TambahBahagianNegeri">		
        <table width="100%"  style="display:none">
        <tr >
        </tr>
        </table>
        </td>
        </tr>
        
        <tr>
		<td id="defaultBahagianNegeri">	
			<table border="1" cellpadding="1" cellspacing="0" width="96%" align="center" > 
	#if($listBahagianHQ.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_UnitHQ.jsp")
		
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Nama Bahagian/Unit</td>
           <td   align="left" valign="top">Negeri</td>
           <td   align="left" valign="top">Status</td>
           <td   align="left" valign="top">Catatan</td>
           <td   align="left" valign="top">Tindakan</td>
	</tr>
	#if($listBahagianHQ.size()>0)
	#foreach($cariBahagianHQ in $listBahagianHQ)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" >$cariBahagianHQ.BIL. </td>
			   <td  align="left" valign="top">$cariBahagianHQ.NAMA_SEKSYEN ($cariBahagianHQ.KOD_SEKSYEN)</td>
			   <td  align="left" valign="top">??</td>
			   <td  align="left" valign="top">$cariBahagianHQ.FLAG_AKTIF</td>
			   <td align="left" valign="top">$cariBahagianHQ.CATATAN</td>
         
              <td align ="center" valign="top"><a href="javascript:doDivAjaxCall$formname('div_viewBahagianHQ$cariBahagianHQ.ID_SEKSYEN','viewDetailsBahagianHQ','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN');">
              <img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>
</td>  
		</tr>
		<tr  id="div_viewBahagianHQ$cariBahagianHQ.ID_SEKSYEN">
				<td align="left" valign="top" colspan="14">
		
		</td>
		</tr>
		#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
		
			
		</td>
		</tr>		
        
        	
		</table>
		
		<br>
		
		<div id="div_SenaraiBahagianNEGERI" >
		#if($FlagCari=="Y")
		<script> 
		//alert("FlagCari ::" $FlagCari);
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_SenaraiBahagianNEGERI','showSenaraiBahagian','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
		</script>
		#end
		</div>	

		
	</td>
	</tr>	-->	

	
</table>
