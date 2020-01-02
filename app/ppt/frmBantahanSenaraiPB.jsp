<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

#if ($clearForm == "yes")
	#set ($carianNamaPB = "")
    #set ($carianNoLot = "")
#end

#foreach ( $senarai in $Header )
	#set ($id_fail=$senarai.id_fail)
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan)
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($id_permohonan=$senarai.id_permohonan)
    #set ($id_status=$senarai.id_status)
    <input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan"/>
    <input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
#end 

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")

<br/>
<!---------------------------------------- SENARAI PIHAK BERKEPENTINGAN ------------------------------> 

<fieldset>
<legend>Senarai Pihak Berkepentingan</legend>

<a href="javascript:popupCarianPB('$id_permohonan','bantahan_mahkamah','$id_hakmilikpb')"><font color="blue">>> SKRIN CAPAIAN PIHAK BERKEPENTINGAN</font></a>	



<!--

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
          <td width="13%" align="left" style="text-transform:uppercase">No Lot/PT</td>
          <td width="15%" style="text-transform:uppercase">Status Bantahan</td>
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
            <td class="$row" align="center" >$senarai.bil</td>                   
			<td class="$row"><a href="javascript:papar('$!senarai.id_hakmilik','$!senarai.id_hakmilikpb','$!senarai.id_pihakberkepentingan','$!senarai.status_bantahan','$!senarai.id_permohonan')"><font color="blue">$senarai.nama_pb</font></a></td>                 	
          <td class="$row" align="left">$senarai.no_lotpt</td>
          
          #if ($senarai.flag_online == '2')
          <td width="14%" class="$row style1">$senarai.keteranganStatusBantahan (Permohonan Online)</td>
          #else
          <td width="14%" class="$row">$senarai.keteranganStatusBantahan</td>
          #end
          
          
        
  </tr>	
        
      #end   
      	 <tr>
          	<td colspan="6"></td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="7">Tiada rekod</td>
          <tr>  
   	  #end          
      </table>    
-->
</fieldset>
</br>
<!------------------------------------- END SENARAI PIHAK BERKEPENTINGAN -----------------------------> 

<div align="center">           
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembaliListBantahan()" />   
</div> 


<input type="hidden" name="id_status" id="id_status" value='$!id_status' />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value='$!id_pihakberkepentingan' />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value='$!id_hakmilik' />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value='$!id_hakmilikpb' />
<input type="hidden" name="status_bantahan" id="status_bantahan" value='$!status_bantahan' />

<script>
function popupCarianPB(id_permohonan,flag_skrin,id_hakmilikpb)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&id_hakmilikpb="+id_hakmilikpb;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function bantahan_agensi(){
	document.${formName}.command.value = "bantahan_agensi";	
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();	
}

function papar(id_hakmilik,id_hakmilikpb,id_pihakberkepentingan,status_bantahan) {
	
	
	
	if (status_bantahan == '72' ){
		document.${formName}.command.value = "DaftarBantahan";
		document.${formName}.submit();		
	}
	else if (status_bantahan == '181' ){
		document.${formName}.command.value = "dalamProses";
		document.${formName}.submit();	
	}
	else if (status_bantahan == '183' ){
		document.${formName}.command.value = "urusanDeposit";
		document.${formName}.submit();	
	}	
	else if (status_bantahan == '184' ){
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}	
	else if (status_bantahan == '185') {
		document.${formName}.command.value = "tarikBalikBantahan";
		document.${formName}.submit();	
	}	
	else if (status_bantahan == '220') {
		document.${formName}.command.value = "batalBantahan";
		document.${formName}.submit();	
	}	
	else if ((status_bantahan == '187' ) || (status_bantahan == '186')) {
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}
	else if (status_bantahan == '1610248') {
		document.${formName}.command.value = "borangO";
		document.${formName}.submit();	
	}	
	else{
		//alert("Sila Hubungi Sistem Admin")
		document.${formName}.command.value = "DaftarBantahan";
	}

	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.status_bantahan.value = status_bantahan;	
	
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();	
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function kembaliListBantahan(){
	document.${formName}.command.value = "kembaliListBantahan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function cariNamaPB(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "cariNamaPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function kosongkanLOT(id_permohonan) {
	document.${formName}.carianNamaPB.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "papar_pb";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>