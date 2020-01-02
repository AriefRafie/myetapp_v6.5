
#if ($clearForm == "yes")
	#set ($carianNamaPB = "")
    #set ($carianNoLot = "")
    #set ($carianNamaAP = "")
    #set ($carianNoHakmilik = "")
#end

<fieldset>
	<legend>Urusan Pampasan</legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<!---------------------------------------- SENARAI PIHAK BERKEPENTINGAN ------------------------------> 
<br />
<fieldset>
<legend>Senarai Pihak Berkepentingan</legend>

			<table width="100%" border="0">   
                	<tr>
                	  <td width="83%" align="left"><div align="right">Carian Nama PB :&nbsp;
               	        <input type="text" name="carianNamaPB" id="carianNamaPB" value="$!carianNamaPB" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
              	    </div></td>
                    	<td width="17%" align="left"><div align="right"></div></td>
                   	</tr>
                	<tr>
                	  <td align="left"><div align="right">Carian No.LOT/No.PT :&nbsp;
               	        <input type="text" name="carianNoLot" id="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
              	    </div></td>
                    	<td align="left"><div align="right"><a href="javascript:cariNamaPB('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></div></td>
                   	</tr>                    
    		</table>
<br />


#parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">       
        <tr class="table_header">
          <td width="3%" scope="row" align="center" style="text-transform:uppercase">Bil</td>
          <td width="30%" style="text-transform:uppercase">Nama PB</td>
          <td width="8%" align="center" style="text-transform:uppercase">No PB</td>
          <td width="13%" align="center" style="text-transform:uppercase">No Lot/PT</td>
          <td width="15%" style="text-transform:uppercase">Pampasan Tambahan</td>
           <td width="15%" style="text-transform:uppercase">Status Bayaran</td>
        </tr>
        
        
      #if($list_size!=0)     
       #foreach($senarai in $getSenaraiPB)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        
        <tr>                                    
            <td class="$row" align="center" >$senarai.bil</td><input type="hidden" name="id_bantahan" id="id_bantahan" value="$senarai.id_bantahan" />               
            <td class="$row"><a href="javascript:papar('$senarai.id_hakmilik','$senarai.id_hakmilikpb','$senarai.id_pihakberkepentingan','$senarai.status_bantahan','$senarai.id_permohonan','$senarai.id_bantahan')"><font color="blue">$senarai.nama_pb</font></a></td>           	
            <td class="$row">$senarai.no_pb</td>
            <td class="$row" align="center">$senarai.no_lot</td>
            <td class="$row">RM &nbsp;$!Util.formatDecimal($!senarai.amaun_award)</td>
            <td width="14%" class="$row">
            #if ($!senarai.flag_bayar_bantahan == "1")
              <div align="center"><img src="../img/validyes.png" alt="Sudah Terima Bayaran" width="18" height="18" border="0" /></a></div>
            #else
              <div align="center"><img src="../img/validno.png" alt="Belum Terima Bayaran" width="18" height="18" border="0" /></a></div>
            #end
            </td>
        </tr>	
        
      #end   
      	 <tr>
          	<td colspan="6">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="7">Tiada rekod</td>
          <tr>  
   	  #end          
               
      </table>    

</fieldset>
</br>
<!------------------------------------- END SENARAI PIHAK BERKEPENTINGAN -----------------------------> 


<!---------------------------------------- SENARAI AGENSI PEMOHON ------------------------------> 
<br />
<fieldset>
<legend>Senarai Agensi Pemohon</legend>

        <table width="100%" border="0">   
        	<tr>
            	<td width="83%" align="left"><div align="right">Carian Nama Agensi :&nbsp;
                    <input type="text" name="carianNamaAP" id="carianNamaAP" value="$!carianNamaAP" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
                </div>
                </td>
                <td width="17%" align="left"><div align="right"></div></td>
            </tr>
            <tr>
            <td align="left"><div align="right">Carian No Hakmilik :&nbsp;
                    <input type="text" name="carianNoHakmilik" id="carianNoHakmilik" value="$!carianNoHakmilik" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
                </div></td>
                    <td align="left"><div align="right"><a href="javascript:cariNamaAP('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></div></td>
                </tr>                    
        </table>
<br />


#parse("app/utils/record_paging.jsp")
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">       
        <tr class="table_header">
          <td width="3%" scope="row" align="center" style="text-transform:uppercase">Bil</td>
          <td width="30%" style="text-transform:uppercase">Nama Agensi</td>
          <td width="13%" align="center" style="text-transform:uppercase">No Hakmilik</td>
          <td width="15%" style="text-transform:uppercase">Pampasan Dipulangkan</td>
           <td width="15%" style="text-transform:uppercase">Status Bayaran</td>
        </tr>
        
        
      #if($listF_size!=0)     
       #foreach($senaraiAP in $getSenaraiAP)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end    
        
        <tr>                                      
            <td class="$row" align="center" >$senaraiAP.bil</td>             
            <td class="$row"><a href="javascript:papar_agensipemohon('$senaraiAP.id_hakmilik','$senaraiAP.status_bantahan_ap','$senaraiAP.id_permohonan','$senaraiAP.id_bantahan')"><font color="blue">$senaraiAP.nama_agensi</font></a></td>           	
            <td class="$row" align="center">$senaraiAP.no_hakmilik</td>
            <td class="$row">RM &nbsp;$!Util.formatDecimal($!senaraiAP.amaun_award)</td>
            <td width="14%" class="$row">
            #if ($!senaraiAP.flag_bayar_bantahan == "1")
              <div align="center"><img src="../img/validyes.png" alt="Sudah Terima Bayaran" width="18" height="18" border="0" /></a></div>
            #else
              <div align="center"><img src="../img/validno.png" alt="Belum Terima Bayaran" width="18" height="18" border="0" /></a></div>
            #end
            </td>
        </tr>	
        
      #end   
      	 <tr>
          	<td colspan="6">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="7">Tiada rekod</td>
          <tr>  
   	  #end          
               
      </table>    

</fieldset>
</br>
<!------------------------------------- END SENARAI AGENSI PEMOHON -----------------------------> 



<div align="center">           
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali_listMohon()" />   
</div> 

</fieldset>

<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_status" id="id_status" value="$id_status" /> 
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" />
<input type="hidden" name="status_bantahan" id="status_bantahan" />
<input type="hidden" name="status_bantahan_ap" id="status_bantahan_ap" />


<script>
function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function papar(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan,id_permohonan){

	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.status_bantahan.value = status_bantahan;		
	document.${formName}.id_permohonan.value = id_permohonan;		
	
	if ( (status_bantahan == '184') || (status_bantahan == '186') || (status_bantahan == '187') ){		
		document.${formName}.command.value = "checkingCaraBayar";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();		
	}
	
	else{
		alert("Tiada Urusan Pampasan.")
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit()
}

function papar_agensipemohon(id_hakmilik,status_bantahan_ap,id_bantahan){

	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.status_bantahan_ap.value = status_bantahan_ap;		
	document.${formName}.id_bantahan.value = id_bantahan;		
	
	if ( (status_bantahan_ap == '201') || (status_bantahan_ap == '204') || (status_bantahan_ap == '205') ){		
		document.${formName}.command.value = "checkingCaraBayarAgensiPemohon";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();		
	}
	
	else{
		alert("Tiada Urusan Pampasan.")
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit()
}

function kembali_listMohon(){
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function cariNamaPB(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "cariNamaPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function kosongkanLOT(id_permohonan) {
	document.${formName}.carianNamaPB.value = "";
	document.${formName}.carianNamaAP.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "UrusanPampasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function cariNamaAP(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "cariNamaAP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}
</script>