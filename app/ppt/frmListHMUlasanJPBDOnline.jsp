##parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<center>

<!-- ::::: $!headerppk -->
<style type="text/css">



#sddmheader
{	margin: 0;
	padding: 0;
	z-index: 30}

#sddmheader li
{	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
	
	}

#sddmheader li a
{	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddmheader li a:hover
{	
background: #E0F2F7;
}

#sddmheader div
{	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;	
	border: 1px solid #5970B2;
	z-index:2;
	}

	#sddmheader div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 2.5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 11px arial;
		z-index:2;
		}

	#sddmheader div a:hover
	{	background: #49A3FF;
		color: #FFFFFF;
		}

</style>

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
	<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>

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
            <td>
            
              <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!nofail</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >   
          
            
           #foreach ($list in $list_sub_header)
         <a href="javascript:paparPerjalananFail('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','${session.getAttribute('_portal_role')}')" >$list.STATUS</a>
            #end
           
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
            
            
            </td>
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
         <tr>
         </tr>
          <!--<tr>
            <td width="25%" valign="top"></td>
            <td width="1%" valign="top"></td>
            <td width="40%" valign="top"></td>
            <td width="34%" valign="top" align="right"><input type="button" id="cmdBatal" name="cmdBatal" value="Capaian Sistem Imbasan Dokumen" onclick="javascript:arkibWindow('$nofail')"/></td>
         </tr> -->
         
         #if($flag_semakan_online=="2" || $flag_semakan_online=="3")
         <tr>
           <td valign="top">TARIKH PERMOHONAN KJP</td>
           <td valign="top">:</td>
           <td valign="top"><input name="tarikh_permohonan_kjp" $disability $disabilityx value="$!dateToday" size="11" id="tarikh_permohonan_kjp" type="text" onblur="check_date(this)" />
             $!frmtdate</td>
           <td valign="top" align="right"></td>
         </tr>
         #end
            
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
/*<!--
function arkibWindow(noFail){
	//alert(12);
		//var url =  "http://www.etapp.gov.my/";
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PPT&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
}-->*/

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


##parse("app/ppt/frmPPTHeader_script.jsp")

	##parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>No.LOT/No.PT</b></td>                
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td>
            		<td><b>Pegawai</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <!-- <td class="$row"><a href="javascript:viewMaklumat('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a></td> -->
                	<td class="$row">$!listTanah.no_hakmilik</td>  
                	<td class="$row">$!listTanah.no_lotpt</td>  
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>							
                   <td class="$row">$!listTanah.nama_pegawai</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
				<input type="button" name="cmdINTJPBD" id="cmdINTJPBD" value="Ulasan JPBD" onclick="javascript:intNilaianJPBD('$!id_permohonan')"/>
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
function viewMaklumat(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
function intNilaianJPBD(idpermohonan){
	alert('baca intNilaianJPBD===='+idpermohonan);
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewBorangLampiranA1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUlasanJPBDOnline";
	document.${formName}.submit();
}
/* function intNilaianJPBD(id_permohonan) {
	// shiqa 17092020
	// to cater for integration
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.FrmMaklumatUlasanJPBDOnline&action2=viewBorangLampiranA1&ID_PERMOHONAN=" + id_permohonan;
	document.${formName}.method = "POST";
	document.${formName}.submit();
} */
</script>