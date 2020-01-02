<br>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI NEGERI </strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_HQ"></span>
		</td>
		<td width="2%" class="underline_td_main">
		<!--<a href="javascript:document.getElementById('div_TambahNegeri').style.display=''; doDivAjaxCall$formname('div_TambahNegeri','editNegeri','ID_NEGERI=');">
		<img title="Tambah Bahagian" src="../img/plus3.gif" border="0"></a>-->
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
<td colspan="4" id="div_TambahNegeri">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiNegeri">
		<td id="defaultNegeri">	
			<table border="0" cellspacing="1" cellpadding="1" width="98%" align="center" > 
	#if($listNegeri.size()>0)
	
	<script> 
		
		/*  var defaultNegeri =  document.getElementById('defaultNegeri');
		  defaultNegeri.style.display = "none";*/
		  
		  var div_TambahNegeri =  document.getElementById('div_TambahNegeri');
		  div_TambahNegeri.style.display = "none";
		  
		</script>
	
<!--	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_BhgnHQ.jsp")
		   </td>
	</tr>-->
	#end 
   
	<tr class="table_header" >
		   <td width="9%"   align="center" valign="top">Kod</td>
	      <td width="83%"   align="left" valign="top">Negeri</td>
          <!--<td width="8%"   align="left" valign="top">Tindakan</td>-->
	</tr>
	#if($listNegeri.size()>0)
	#foreach($senaraiNegeri in $listNegeri)
	<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
	   <td   align="center" valign="top" class="$senaraiNegeri.rowCss">($senaraiNegeri.KOD_NEGERI)</td>
	   <td  align="left" valign="top" class="$senaraiNegeri.rowCss"><a href="#" onclick="sendValueNegeri(this,'$senaraiNegeri.NAMA_NEGERI','namaNegeri');doDivAjaxCall$formname('div_viewDaerahByNegeri','showSenaraiDaerah','cetakReport=$cetakReport&FlagCetak=$FlagCetak&ID_NEGERI=$senaraiNegeri.ID_NEGERI&NAMA_DAERAH=&NAMA_MUKIM=');"><u>$senaraiNegeri.NAMA_NEGERI</u></a> <br /></td>
    </tr>

    <!-- <tr id="div_viewDaerahByNegeri$senaraiNegeri.ID_NEGERI">
     <td align="left" valign="top" colspan="5">
     </td>
     </tr>-->

	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
		
			
		</td>
        </div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
	
    
    	<tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI DAERAH <div id="namaNegeri"></div></strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Daerah"></span>
		</td>
		<td width="2%" class="underline_td_main">
        <a href="javascript:document.getElementById('div_viewDaerah').style.display=''; doDivAjaxCall$formname('div_viewDaerah','editDaerah','ID_NEGERI=$ID_NEGERI&ID_DAERAH=');"><img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_DAERAH" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_viewDaerah">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiDaerah">
		<td id="div_viewDaerahByNegeri">	
			<table border="0" cellspacing="1" cellpadding="1" width="98%" align="center" > 
	#if($listDaerah.size()>0)
	
	<script> 
		
		/*  var defaultNegeri =  document.getElementById('defaultNegeri');
		  defaultNegeri.style.display = "none";*/
		  
		  var div_TambahDaerah =  document.getElementById('div_viewDaerah');
		  div_TambahDaerah.style.display = "none";
		  
		</script>
	
<!--	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_BhgnHQ.jsp")
		   </td>
	</tr>-->
	#end 
   
	<tr class="table_header" >
		   <td width="9%"   align="center" valign="top">Kod</td>
	      <td width="83%"   align="left" valign="top">Daerah</td>
          <!--<td width="8%"   align="left" valign="top">Tindakan</td>-->
	</tr>
	#if($listDaerah.size()>0)
	#foreach($senaraiDaerah in $listDaerah)
	<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
	   <td   align="center" valign="top" class="$senaraiDaerah.rowCss">($senaraiDaerah.KOD_DAERAH)</td>
	   <td  align="left" valign="top" class="$senaraiDaerah.rowCss"><a href="#" onclick="doDivAjaxCall$formname('div_viewMukimByDaerah','showSenaraiMukim','cetakReport=$cetakReport&FlagCetak=$FlagCetak&ID_DAERAH=$senaraiDaerah.ID_DAERAH&NAMA_MUKIM=');"><u>$senaraiDaerah.NAMA_DAERAH</u></a> <br /></td>
    </tr>

	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
		
			
		</td>
        </div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
    
    
    <tr>
	<td>
	
		
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		
		<td width="58%" class="underline_td_main">
		<strong>SENARAI MUKIM </strong>
		
		<!--open CT 
		<div  id="span_LINK_CT_CARIAN_HQ">
		<a href="#" onClick="doDivAjaxCall$formname('div_CT_CARIAN_HQ','showCarianBahagian','TYPE=HQ');">
		<font color="white"><u>Carian >></u></font></a>
		</div>
		close CT 
		-->
		
		</td>
		<td width="38%" class="underline_td_main" align="right" valign="top" >
		<span id="showUserCount_Daerah"></span>
		</td>
		<td width="2%" class="underline_td_main">
        <a href="javascript:document.getElementById('div_viewDaerah').style.display=''; doDivAjaxCall$formname('div_viewMukim','editMukim','ID_DAERAH=&ID_MUKIM=');"><img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>
		</td>
		</tr>
		</table>
		<br>
		
		<!-- open CT -->
		<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">		
		<tr>
		<td colspan="4" valign="top" id="div_CT_CARIAN_DAERAH" >
		</td>
		</tr>	
<tr>
<td colspan="4" id="div_viewDaerah">		
<table width="100%"  style="display:none">
<tr >
</tr>
</table>
</td>
</tr>			
		<tr>
        <div id="SenaraiMukim">
		<td id="div_viewMukimByDaerah">	
			<table border="0" cellspacing="1" cellpadding="1" width="98%" align="center" > 
	#if($listMukim.size()>0)
	
	<script> 
		
		/*  var defaultNegeri =  document.getElementById('defaultNegeri');
		  defaultNegeri.style.display = "none";*/
		  
		  var div_TambahMukim =  document.getElementById('div_viewMukim');
		  div_TambahMukim.style.display = "none";
		  
		</script>
	
<!--	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_BhgnHQ.jsp")
		   </td>
	</tr>-->
	#end 
   
	<tr class="table_header" >
		   <td width="9%"   align="center" valign="top">Kod</td>
	      <td width="83%"   align="left" valign="top">Mukim</td>
          <!--<td width="8%"   align="left" valign="top">Tindakan</td>-->
	</tr>
	#if($listMukim.size()>0)
	#foreach($senaraiMukim in $listMukim)
	<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
	   <td   align="center" valign="top" class="$senaraiMukim.rowCss">($senaraiMukim.KOD_MUKIM)</td>
	   <td  align="left" valign="top" class="$senaraiMukim.rowCss">$senaraiMukim.NAMA_MUKIM<br /></td>
    </tr>

	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
		
			
		</td>
        </div>
		</tr>			
		</table>
		<!-- close CT -->
		<br>
		
	

	</td>
	</tr>
</table>
