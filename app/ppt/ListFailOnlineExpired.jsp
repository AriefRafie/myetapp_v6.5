

#set($ModuleName = "${session.getAttribute('_portal_module')}")

#if($listFailExpired.size()!=0 && (($typeList=="online" && $ModuleName=="ekptg.view.ppt.FrmPermohonanUPTOnline") || ($typeList=="internal" && $ModuleName=="ekptg.view.ppt.MyInfoPPT")))
<div class="warning" align="left">
	Terdapat <b>$!listFailExpired.size()</b> fail permohonan yang hampir/telah tamat tempoh pendaftaran di kaunter. 
	<a href="javascript:setTable('tableReport1')"><font color="blue"><b>Klik sini</b></font></a>
	untuk papar fail.     
</div>  

<fieldset id="tableReport1" style="display:none;">
	<table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
        <tr class="table_header">
        	<td align="center" width="3%"><b>No</b></td>
        	#if($typeList=="internal")
        	<td width="23%"><b>KEMENTERIAN</b></td> 
        	<td width="17%"><b>NO.RUJUKAN ONLINE</b></td> 
        	<td width="17%"><b>URUSAN</b></td> 
        	<td width="20%"><b>BILANGAN HARI DARI PERMOHONAN DIDAFTAR</b></td>	
        	<td width="20%"><b>TINDAKAN</b></td>
        	#else
        	<td><b>No.Rujukan Online</b></td> 
        	<td><b>Urusan</b></td> 
        	<td><b>Bilangan hari dari permohonan dimasukkan</b></td>	
        	#end	
        </tr>
        
      #if($listFailExpired.size()!=0)
           	#foreach($listExp in $listFailExpired)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end


        	<tr>
        		<td class="$row" align="center">$listExp.bil</td>
        		#if($typeList=="internal")
        		<td class="$row">$listExp.nama_kementerian</td>
        		<td class="$row"><a href="javascript:papar('$!listExp.id_permohonan','$!listExp.id_status','$!listExp.id_fail','$!listExp.tarikh_permohonan','$!listExp.flag_jenispermohonan','$!listExp.id_suburusan','$!listExp.flag_segera','$!{session.getAttribute('_portal_role')}')"><font color="blue">$!listExp.no_permohonan_online</font></a></td> 
        		#else
        		<td class="$row"><a href="javascript:semakPendaftaran('$!listExp.id_permohonan')"><font color="blue">$!listExp.no_permohonan_online</font></a></td> 
        		#end
        		<td class="$row">$listExp.suburusan</td>
            	<td class="$row">$listExp.bilHari</td>
            	#if($typeList=="internal")
            	<td class="$row">
            	<!-- <input name="cmdEmel" id="cmdEmel" value="Hantar Emel" type="button" onclick="javascript:emelKJP('$!listExp.id_permohonan')"> -->
            	<input name="cmdHapus" id="cmdHapus" value="Hapus" type="button" onclick="javascript:hapusKJP('$!listExp.id_fail')">
            	</td>
            	#end
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>     
#end

#if($listPenarikanExpired.size()!=0 && (($typeList=="online" && $ModuleName=="ekptg.view.ppt.FrmPenarikanBalikInternalOnline") || ($typeList=="internal" && $ModuleName=="ekptg.view.ppt.MyInfoPPT")))
	<div class="warning" align="left">
		Terdapat <b>$!listPenarikanExpired.size()</b> fail Penarikan Balik yang hampir/telah tamat tempoh. 
		<a href="javascript:setTable('tableReport2')"><font color="blue"><b>Klik sini</b></font></a>
		untuk papar fail.     
	</div> 
	
	<fieldset id="tableReport2" style="display:none;">
	<table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
        <tr class="table_header">
        	<td align="center" width="3%"><b>No</b></td>
        	#if($typeList=="internal")
        	<td width="23%"><b>KEMENTERIAN</b></td> 
        	<td width="17%"><b>NO FAIL JKPTG</b></td> 
        	<td width="17%"><b>NO.PENARIKAN BALIK</b></td> 
        	<td width="20%"><b>BILANGAN HARI DARI PERMOHONAN DIDAFTAR</b></td>	
        	<td width="20%"><b>TINDAKAN</b></td>
        	#else
        	<td><b>No Fail JKPTG</b></td> 
        	<td><b>No.Penarikan Balik</b></td> 
        	<td><b>Bilangan hari dari permohonan dimasukkan</b></td>	
        	#end	
        </tr>
        
      #if($listPenarikanExpired.size()!=0)
           	#foreach($listpbkExp in $listPenarikanExpired)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end

        	<tr>
        		<td class="$row" align="center">$listpbkExp.bil</td>
        		#if($typeList=="internal")
        		<td class="$row">$listpbkExp.nama_kementerian</td>
        		<td class="$row"><a href="javascript:papar('$!listExp.id_permohonan','$!listExp.id_status','$!listExp.id_fail','$!listExp.tarikh_permohonan','$!listExp.flag_jenispermohonan','$!listExp.id_suburusan','$!listExp.flag_segera','$!{session.getAttribute('_portal_role')}')"><font color="blue">$!listpbkExp.no_fail</font></a></td> 
        		#else
        		<td class="$row"><a href="javascript:papar('$!listpbkExp.id_permohonan','')"><font color="blue">$!listpbkExp.no_fail</font></a></td> 
        		#end       		
        		<td class="$row">$listpbkExp.no_penarikanbalik</td>
            	<td class="$row">$listpbkExp.bilHari</td>
            	#if($typeList=="internal")
            	<td class="$row">
            	<input name="cmdHapus" id="cmdHapus" value="Hapus" type="button" onclick="javascript:hapusPenarikan('$!listpbkExp.id_penarikanbalik')">
            	</td>
            	#end
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>   
#end


#if($listPembatalanExpired.size()!=0 && (($typeList=="online" && $ModuleName=="ekptg.view.ppt.FrmPembatalanInternalOnline") || ($typeList=="internal" && $ModuleName=="ekptg.view.ppt.MyInfoPPT")))
	<div class="warning" align="left">
		Terdapat <b>$!listPembatalanExpired.size()</b> fail Pembatalan yang hampir/telah tamat tempoh. 
		<a href="javascript:setTable('tableReport3')"><font color="blue"><b>Klik sini</b></font></a>
		untuk papar fail.     
	</div> 
	
	<fieldset id="tableReport3" style="display:none;">
	<table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
        <tr class="table_header">
        	<td align="center" width="3%"><b>No</b></td>
        	#if($typeList=="internal")
        	<td width="23%"><b>KEMENTERIAN</b></td> 
        	<td width="17%"><b>NO FAIL JKPTG</b></td> 
        	<td width="17%"><b>NO.PEMBATALAN</b></td> 
        	<td width="20%"><b>BILANGAN HARI DARI PERMOHONAN DIDAFTAR</b></td>	
        	<td width="20%"><b>TINDAKAN</b></td>
        	#else
        	<td><b>No Fail JKPTG</b></td> 
        	<td><b>No.Pembatalan</b></td> 
        	<td><b>Bilangan hari dari permohonan dimasukkan</b></td>	
        	#end	
        </tr>
        
      #if($listPembatalanExpired.size()!=0)
           	#foreach($listpemExp in $listPembatalanExpired)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end

        	<tr>
        		<td class="$row" align="center">$listpemExp.bil</td>
        		#if($typeList=="internal")
        		<td class="$row">$listpemExp.nama_kementerian</td>
        		<td class="$row"><a href="javascript:papar('$!listpemExp.id_permohonan','$!listpemExp.id_status','$!listpemExp.id_fail','$!listpemExp.tarikh_permohonan','$!listpemExp.flag_jenispermohonan','$!listpemExp.id_suburusan','$!listpemExp.flag_segera','$!{session.getAttribute('_portal_role')}')"><font color="blue">$!listpemExp.no_fail</font></a></td> 
        		#else
        		<td class="$row"><a href="javascript:papar('$!listpemExp.id_permohonan','')"><font color="blue">$!listpemExp.no_fail</font></a></td> 
        		#end       		
        		<td class="$row">$listpemExp.no_pembatalan</td>
            	<td class="$row">$listpemExp.bilHari</td>
            	#if($typeList=="internal")
            	<td class="$row">
            	<input name="cmdHapus" id="cmdHapus" value="Hapus" type="button" onclick="javascript:hapusPembatalan('$!listpemExp.id_pembatalan')">
            	</td>
            	#end
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>   
#end


#if($listPenarikanExpired.size()!=0 && $listFailExpired.size()!=0 && $listPembatalanExpired.size()!=0)
<br/>
#end 


<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>