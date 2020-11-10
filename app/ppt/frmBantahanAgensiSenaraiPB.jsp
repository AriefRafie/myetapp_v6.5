<input type="hidden" name="modul" value="$!modul">

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
    #set ($id_fail=$senarai.id_fail)
    #set ($id_permohonan=$senarai.id_permohonan)
    #set ($id_status=$senarai.id_status)
    <input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
    <input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
#end 

<fieldset>
	<legend>Maklumat Permohonan</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>


<!---------------------------------------- SENARAI PIHAK BERKEPENTINGAN ------------------------------> 

<fieldset>
<legend>Senarai Hakmilik</legend>

#if($modul == "ekptg.view.ppt.FrmRayuanBantahanOnline")
<a href="javascript:popupCarianHakmilik('$id_permohonan','bantahan_online')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>	
#else
<a href="javascript:popupCarianHakmilik('$id_permohonan','bantahan_mahkamah')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>	
#end
<!--

        <table width="100%" border="0">   
           <tr>
              <td width="83%" align="left"><div align="right">Carian No Hakmilik :&nbsp;
                  <input type="text" name="carianNoHakmilik" id="carianNoHakmilik" value="$!carianNoHakmilik" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
                </div>
              </td>
              <td width="17%" align="left"><div align="right"></div></td>
           </tr>
           <tr>
              <td align="left"><div align="right">Carian No.LOT/No.PT :&nbsp;
              <input type="text" name="carianNoLot" id="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" />
                </div>
              </td>
              <td align="left"><div align="right"><a href="javascript:cariNoHakmilik('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></div>
              </td>
           </tr>                    
        </table>
<br />

#parse("app/utils/record_paging.jsp")

<table width="100%"  cellpadding="1" cellspacing="2" border="0">       
        <tr class="table_header">
          <td width="8%" scope="row" align="center" style="text-transform:uppercase">Bil</td>
          <td width="43%" style="text-transform:uppercase">No Lot</td>
          <td width="20%" align="left" style="text-transform:uppercase">No Hakmilik</td>
          <td width="29%" style="text-transform:uppercase">Status Bantahan</td>
        </tr>
        
        
      #if($list_size!=0)     
       #foreach($senarai in $getSenaraiNoLot)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        
        <tr>                                      
          <td class="$row" align="center" >$senarai.bil</td>                  
		  <td class="$row"><a href="javascript:paparByAgensi('$senarai.id_hakmilik','$senarai.status_bantahan_ap','$senarai.id_permohonan')"><font color="blue">$senarai.no_lotpt</font></a></td>                 	
          <td class="$row" align="left">$senarai.no_hakmilik</td>
          
          
          #if ( ($senarai.flag_online == '1') || ($senarai.flag_online == '2') )
          <td width="29%" class="$row style1">$senarai.keteranganStatusBantahan (Permohonan Online)</td>
          #else
          <td width="29%" class="$row">$senarai.keteranganStatusBantahan</td>
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

</fieldset>

<input type="hidden" name="id_status" id="id_status" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" />
<input type="hidden" name="id_permohonan" id="id_permohonan" />
<input type="hidden" name="status_bantahan_ap" id="status_bantahan_ap" />

<script>

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function paparByAgensi(id_hakmilik,status_bantahan_ap) {

	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.status_bantahan_ap.value = status_bantahan_ap;	
	
	if (status_bantahan_ap == '72' ){
		document.${formName}.command.value = "DaftarBantahanAP";
		document.${formName}.submit();		
	}
	else if (status_bantahan_ap == '199' ){
		document.${formName}.command.value = "dalamProses";
		document.${formName}.submit();	
	}
	else if (status_bantahan_ap == '200' ){
		document.${formName}.command.value = "urusanDeposit";
		document.${formName}.submit();	
	}	
	else if (status_bantahan_ap == '201' ){
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}	
	else if (status_bantahan_ap == '202') {
		document.${formName}.command.value = "tarikBalikBantahan";
		document.${formName}.submit();	
	}	
	else if ((status_bantahan_ap == '187' ) || (status_bantahan_ap == '186')) {
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.submit();	
	}		
	else if (status_bantahan_ap == '1610249') {
		document.${formName}.command.value = "borangO";
		document.${formName}.submit();	
	}	
	else{

		document.${formName}.command.value = "DaftarBantahanAP";
		document.${formName}.submit();	
	}

	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function cariNoHakmilik(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "cariNoHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function kosongkanLOT(id_permohonan) {
	document.${formName}.carianNoHakmilik.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "bantahan_agensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
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

function papar_pb() {
	document.${formName}.command.value = "papar_pb";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
</script>