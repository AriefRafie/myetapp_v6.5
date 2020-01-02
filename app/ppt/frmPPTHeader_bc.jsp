
<!-- Portal Role -->
#set($HeaderPortal_role = "${session.getAttribute('_portal_role')}")
#set($ModuleName = "${session.getAttribute('_portal_module')}")

#set($totalHakmilikBorangK = 0)
#set($totalHakmilik = 0)






#foreach($header in $dataHeader)
	#set($id_projekNegeri = $header.id_projekNegeri)
	#set($nofail=$header.no_fail)
	#set($nopermohonan=$header.no_permohonan)
	#set($urusan=$header.nama_suburusan)
	#set($tarikhpermohonan=$header.tarikh_permohonan)
	#set($statuspermohonan=$header.status)
	#set($kementerian=$header.nama_kementerian)
	#set($agensi=$header.nama_agensi)
	#set($projeknegeri=$header.projek_negeri)
	#set($projekdaerah=$header.projek_daerah)
	#set($amaunperuntukan=$header.peruntukan)
	#set($pengambilansegera=$header.segera)
	#set($tujuanpengambilan=$header.tujuan)
	#set($tarikhkehendaki=$header.tarikh_kehendaki)
	#set($norujukansurat=$header.no_rujukan_surat)
	#set($norujukanptd=$header.no_rujukan_ptd)
	#set($norujukanptg=$header.no_rujukan_ptg)
	#set($norujukanupt=$header.no_rujukan_upt)
	#set($statusSah=$header.id_status)
    
    #set($nama_projek_tujuan=$header.tujuan)
    
    #set ($ID_NEGERIPROJEK=$header.id_projekNegeri) 
    
    
	
	#set($totalHakmilikBorangK=$header.totalHakmilikBorangK)
	#set($totalHakmilik=$header.totalHakmilik)
	#set($id_suburusanHEADER=$header.id_suburusan)
	#set($id_statusHEADER=$header.id_status)
#end

	<fieldset>
	<legend><strong>Maklumat Permohonan</strong></legend>

	#if($HeaderPortal_role!="online_kjp")
	#if($id_suburusanHEADER=="52")

		#if(($totalHakmilik - $totalHakmilikBorangK == 0) && $totalHakmilik != 0)
		<table width="100%" border="0" >
			<tr>
				<td align="center" bgcolor="#7D79E6"><font size="4"><b>:: PERMOHONAN SELESAI ::</b></font></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>
		#end
	
	#elseif($id_suburusanHEADER=="51")
	
		#if($id_statusHEADER=="52")
		<table width="100%" border="0" >
			<tr>
				<td align="center" bgcolor="#FF8040"><font size="4"><b>:: PERMOHONAN SELESAI ::</b></font></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>
		#end
		
	#end
	#end
	
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
	<!-- #if($nopermohonan!="")
		<tr>
        	<td>BIL. PERMOHONAN</td>
        	<td>:</td>
        	<td><font color="blue">$!nopermohonan</font></td>
        	<td>&nbsp;</td>
        </tr> 	
        #end -->
		
        
        #if($nofail!="")	
		<tr>
            <td>NO. FAIL PERMOHONAN</td>
            <td>:</td>
            <td><font color="blue">$!nofail</font></td>
            <td>&nbsp;</td>
        </tr> 
        #end
        
        #if($norujukanptd!="")
        <tr>
           	<td>NO. RUJUKAN PTD</td>
           	<td>:</td>
          	<td><font color="blue">$!norujukanptd</font></td>
          	<td>&nbsp;</td>
        </tr>
        #end
        
        #if($norujukanptg!="")
        <tr>
           	<td>NO. RUJUKAN PTG</td>
           	<td>:</td>
           	<td><font color="blue">$!norujukanptg</font></td>
           	<td>&nbsp;</td>
        </tr>
        #end
        
        #if($norujukanupt!="")
        <tr>
           	<td>NO. RUJUKAN UPT</td>
           	<td>:</td>
           	<td><font color="blue">$!norujukanupt</font></td>
           	<td>&nbsp;</td>
        </tr>
        #end
        
         <tr>
            <td width="25%" valign="top">KEMENTERIAN</td>
            <td width="1%" valign="top">:</td>
            <td width="40%" valign="top"><font color="blue">$!kementerian.toUpperCase()</font></td>
            <td width="34%" valign="top" align="right"><a href="javascript:setTable('showHeader1')"><font color="blue"><b>&raquo;Lihat terperinci</b></font></a></td>
         </tr>  
    </table>
    </fieldset>     
         
         
    <fieldset id="showHeader1" style="display:none">
	<table width="100%" border="0" cellspacing="2" cellpadding="2">       
         <tr>
           	<td width="25%" valign="top">NAMA AGENSI</td>
           	<td width="1%" valign="top">:</td>
           	<td width="40%" valign="top"><font color="blue">$!agensi.toUpperCase()</font></td>
           	<td width="34%" valign="top">&nbsp;</td>
         </tr>
         <tr>
            <td>NEGERI</td>
            <td>:</td>
            <td><font color="blue">$!projeknegeri.toUpperCase()</font></td>
            <td>&nbsp;</td>
         </tr>
         <tr>
            <td>DAERAH / JAJAHAN</td>
            <td>:</td>
            <td><font color="blue">$!projekdaerah.toUpperCase()</font></td>
            <td>&nbsp;</td>
         </tr>   
         
        <tr>
           	<td>NO. RUJUKAN KEMENTERIAN</td>
           	<td>:</td>
           	<td><font color="blue">$!norujukansurat</font></td>
           	<td>&nbsp;</td>
        </tr>
        
        <tr><td colspan="4">&nbsp;</td></tr>
        
        <tr>
            <td valign="top">TUJUAN PENGAMBILAN</td>
            <td valign="top">:</td>
            <td valign="top"><font color="blue">$!tujuanpengambilan.toUpperCase()</font></td>
            <td valign="top">&nbsp;</td>
         </tr>
        <tr>
        	<td>URUSAN</td>
        	<td>:</td>
        	<td><font color="blue">$!urusan.toUpperCase()</font></td>
        	<td>&nbsp;</td>
        </tr> 
        <tr>
            <td>AMAUN PERUNTUKAN</td>
            <td>:</td>
            <td><font color="blue">$!amaunperuntukan.toUpperCase()</font></td>
            <td>&nbsp;</td>
         </tr>
         
         #if($pengambilansegera != "")
         <tr>
            <td>PENGAMBILAN SEGERA</td>
            <td>:</td>
            <td><font color="blue">$!pengambilansegera.toUpperCase()</font></td>
            <td>&nbsp;</td>
         </tr>
         #end
        <tr>
        	<td>TARIKH PERMOHONAN DI TERIMA</td>
        	<td>:</td>
        	<td><font color="blue">$!tarikhpermohonan</font></td>
        	<td>&nbsp;</td>
        </tr>  
        <tr>
           	<td>TARIKH DIKEHENDAKI</td>
           	<td>:</td>
           	<td><font color="blue">$!tarikhkehendaki</font></td>
           	<td>&nbsp;</td>
         </tr>
        #if($statusSah!="127" && $statusSah!="148" )
        <tr>
        	<td>STATUS PERMOHONAN</td>
        	<td>:</td>
        	<td><font color="blue">$!statuspermohonan.toUpperCase()</font></td>
        	<td>&nbsp;</td>
        </tr> 
        #end
        
        <tr>
           	<td>JUMLAH LOT</td>
           	<td>:</td>
           	<td><font color="blue">$!totalHakmilik #if($totalHakmilikBorangK > 0)( $!totalHakmilikBorangK TELAH SELESAI )#end</font></td>
           	#if($ModuleName=="ekptg.view.ppt.FrmAgihanTugasSek8" && ($HeaderPortal_role=="(PPT)KetuaPenolongPengarahUnit" || $HeaderPortal_role=="(PPT)PengarahUnit" || $HeaderPortal_role=="adminppt" ))
           	<td align="right"><a href="javascript:setTable('showHeader2')"><font color="blue"><b>&raquo;Lihat Laporan Bulanan Siasatan</b></font></a></td>
           	#else
           	<td>&nbsp;</td>
           	#end
         </tr>
         
    </table>
    
</fieldset>

#if($ModuleName=="ekptg.view.ppt.FrmAgihanTugasSek8")
<fieldset id="showHeader2" style="display:none">
	
	<table width="100%" border="0">
    	
    	<tr>
		    <td width="1%"><font color="red">*</font></td>
		    <td width="24%">Nama Pejabat JKPTG</td>
		    <td width="1%">:</td>
		    <td width="74%">$!selectPejabatHeader</td>
		</tr>
		 
		<tr>
		    <td>&nbsp;</td>
		    <td>Daerah</td>
		    <td>:</td>
		    <td>$!selectDaerahHeader</td>
		</tr>
		   
		<tr>
		    <td><font color="red">*</font></td>
		    <td>Tahun</td>
		    <td>:</td>
		    <td>$!selectTahunHeader</td>
		</tr>
		  
		<tr>
		    <td><font color="red">*</font></td>
		    <td>Bulan (sehingga)</td>
		    <td>:</td>
		    <td>$!selectBulanHeader</td>
		</tr>
		 
		<tr>
		  	<td colspan="3">&nbsp;</td>
			<td>
				<input type="button" name="cmdCetakHeader" id="cmdCetakHeader" value="Cetak" onClick="cetakLaporanSiasatan()">
				<input type="button" name="cmdResetValueLaporan" id="cmdResetValueLaporan" value="Kosongkan" onClick="resetValueLaporan()">
			</td>
		</tr>
		 
    </table>
    
</fieldset>
#end
  
<script>  
function resetValueLaporan(){
	document.getElementById("socPejabatHeader").value = ""; 
	document.getElementById("socDaerahHeader").value = "000"; 
	document.getElementById("socTahunHeader").value = ""; 
	document.getElementById("socBulanHeader").value = "";
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="";
	}
	else if(document.getElementById(id).style.display==""){
		document.getElementById(id).style.display="none";
	}
}
</script>