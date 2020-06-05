<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>




#foreach($listIDSimati in $IDSimati)
    		 #set( $IDSimati = $listIDSimati.ID_SIMATI)
#end
<br>
<br>
<body onLoad="ResetScrollPosition();" >
<input type="hidden" name="idsimati" value="$!IDSimati">
<fieldset><legend><b>
 <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>Baru</blink></font></b>
                             </label>&nbsp;
Semak Maklumat Pemohon</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">MyID Simati / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
              
                </td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemakSub" id="cmdSemakSub" value="Semak" type="button" onClick="javascript:search_main_data_sub()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan_sub()"></td>
    		</tr>
    		
		</table>
        #if($view_list_fail == "yes")
      
        <br>
        <fieldset>
        <table width="97%"  cellpadding="1" cellspacing="2" border="0">
<tr>
                <td class="table_header" width="5%" style="display:none"><b></b></td>
      		    <td class="table_header" width="10%" style="display:none"><b>ID FAIL</b></td>
                <td class="table_header" width="20%"><b>NO FAIL</b></td>
                <td class="table_header" width="20%"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="20%"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="10%"><b>TARIKH MOHON</b></td>
                <td class="table_header" width="15%"><b>STATUS SEMASA</b></td>
                
                          
    		</tr>
    		
            #if($list_fail.size() > 0)
            
    		#foreach($list3 in $list_fail)
    			#set( $id_status = $list3.ID_STATUS )
    			
    		#end
    		
    		#foreach($list in $list_fail)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
               
               
               #set($tr_id = "tr_id"+$i)
               
              <!-- onMouseOver="tr_id_up('$tr_id','$row')" onMouseOut="tr_id_out('$tr_id','$row')" -->
         
         #if($list.ID_FAIL  == $id_fail_carian)
         #set( $row = "tr_class" )
         #end
            <tr id="$tr_id" class="$row"    >
                <td style="display:none" >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td style="display:none" >  
                  $list.ID_FAIL   
                </td>
                
                <td> 
                 
                
                 <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="blue"> $list.NO_FAIL   </font>
         #else
         <font color="blue"> $list.NO_FAIL   </font>
         #end  
         
              </a>   
                </td>
                
                <td>
               $list.NAMA_SIMATI 
                </td>
                
                <td>
                $list.NAMA_PEMOHON 
                </td>
                
                <td>
               $list.TARIKH_MOHON
                </td>
                
                 <td>
               $list.NAMA_STATUS
                </td>
                          
    		</tr>
         
            #end
            #else
            
            <tr class="row">
      			
      		    <td class="row" colspan="10" > 
                Tiada Rekod              
                </td>
               
                          
    		</tr>
            
            #end
            </table>
        </fieldset>
        #end
        
     
     #if($!view_list_myid == "yes")
     
     
      #if($!getPemohonData.size()>0) 
     <table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >
    
    <td >  
    
   
   <fieldset>
   <legend>MAKLUMAT PEMOHON</legend>
   <table width="100%" border="0" align="center">
       <tr>
         <td valign="top" width="50%"><div align="center">
           <table width="100%" border="0">
             <tr id="tr_nofail">
               <td width="30%" style="text-transform:uppercase;" valign="top"><div align="right"> Nama Pemohon</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
               <td width="69%" style="text-transform:uppercase;" valign="top"><font color="blue">$!getPemohonData.NAMA_PEMOHON</font></td>
             </tr>
             #if($!getPemohonData.NO_KP_BARU != "")
             <tr>
               <td  style="text-transform:uppercase;" valign="top"><div align="right">MyId Baru</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
               <td style="text-transform:uppercase;" valign="top"><font color="blue">$!getPemohonData.NO_KP_BARU</font></td>
             </tr>
             #end
             
             #if( $!getPemohonData.NO_KP_LAMA != "")
             <tr>
               <td style="text-transform:uppercase;" valign="top"><div align="right">MyId Lama</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
               <td style="text-transform:uppercase;" valign="top" ><font color="blue"> $!getPemohonData.NO_KP_LAMA </font></td>
             </tr>
             #end
             
             #if( $!getPemohonData.UMUR != "")
             <tr>
               <td style="text-transform:uppercase;" valign="top"><div align="right">Umur</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
               <td style="text-transform:uppercase;" valign="top" ><font color="blue"> $!getPemohonData.UMUR </font></td>
             </tr>
             #end
             
             #if( $!getPemohonData.AGAMA != "")
             <tr>
               <td style="text-transform:uppercase;" valign="top"><div align="right">Agama</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
               <td style="text-transform:uppercase;" valign="top" ><font color="blue"> $!getPemohonData.AGAMA </font></td>
             </tr>
             #end
             
             #if( $!getPemohonData.JANTINA != "")
             <tr>
               <td style="text-transform:uppercase;" valign="top"><div align="right">Jantina</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
               <td style="text-transform:uppercase;" valign="top" ><font color="blue"> $!getPemohonData.JANTINA </font></td>
             </tr>
             #end
             
             #if( $!getPemohonData.WARGA != "")
             <tr>
               <td style="text-transform:uppercase;" valign="top"><div align="right">Status Warganegara</div></td>
               <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
               <td style="text-transform:uppercase;" valign="top" ><font color="blue"> $!getPemohonData.WARGA </font></td>
             </tr>
             #end
           </table>
         </div></td>
         <td width="50%" valign="top"><table width="100%" border="0">
           <tr>
             <td width="20%" style="text-transform:uppercase;" valign="top" ><div align="right">Alamat Tetap</div></td>
             <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
             <td width="79%" style="text-transform:uppercase;" valign="top" ><font color="blue"> #if($!getPemohonData.ALAMAT_1 != "")
               $!getPemohonData.ALAMAT_1 <br>
               #end
               #if($!getPemohonData.ALAMAT_2 != "")
               $!getPemohonData.ALAMAT_2 <br>
               #end
               #if($!getPemohonData.ALAMAT_3 != "")
               $!getPemohonData.ALAMAT_3 <br>
               #end
               #if($!getPemohonData.POSKOD != "")
               $!getPemohonData.POSKOD <br>
               #end
               #if($!getPemohonData.BANDAR != "")
               $!getPemohonData.BANDAR <br>
               #end
               #if($!getPemohonData.NEGERI != "")
               $!getPemohonData.NEGERI
               #end </font></td>
           </tr>
         </table></td>
       </tr>
     </table>
   </fieldset>
   

</td>
</tr>
</table>

#end
     
     #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >
    
    <td >  
    
   
    #parse("app/ppk/headerppk.jsp")
   

</td>
</tr>
</table>
     #end
      
     #if($!list_tukar_pemohon.size()>0)
     
<br>
     <fieldset>
     <legend>SENARAI WARIS DAN ORANG BERKEPENTINGAN </legend>
     <table width="100%"  cellpadding="1" cellspacing="2" border="0">
     <tr>
 <td colspan="10">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pengguna</b> menukar <b>Pemohon</b> yang asal ke pemohon yang lain.</i>
 <br>
 <i>Dibawah adalah senarai waris dan pihak berkepentingan yang boleh menjadi pemohon kepada permohonan ini.</i>
 </font>
  <br>
  <br>
 </td>
 </tr>
 
         <tr >
                <td class="table_header" width="2%" valign="top"><b>BIL</b></td>   		   
                <td class="table_header" width="30%" valign="top"><b>NAMA WARIS</b></td>
                <td class="table_header" width="10%" valign="top"><b>MyId Baru</b></td>
                <td class="table_header" width="10%" valign="top"><b>MyId Lama</b></td>
                
                <td class="table_header" width="23%" valign="top"><b>JENIS KEPENTINGAN</b></td>
                <td class="table_header" width="15%" valign="top"><b>STATUS WARIS</b></td>
                <td class="table_header" width="10%" valign="top" align="center"><b>
               <!--  <input name="cmdSimpanKPOb" id="cmdSimpanKPOb" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPOb')">  -->
          </b></td>              
                          
    		</tr>
           
    		
    		#foreach($list in $list_tukar_pemohon)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
         #set($id_ob = "id_ob"+$list.BIL)   
         #set($id_pemohon_ob = "id_pemohon_ob"+$list.BIL)
         #set($no_kp_baru_ob = "no_kp_baru_ob"+$list.BIL)    
         #set($no_kp_lama_ob = "no_kp_lama_ob"+$list.BIL) 
         #set($no_kp_lain_ob = "no_kp_lain_ob"+$list.BIL)  
         #set($alert_baru_ob = "alert_baru_ob"+$list.BIL) 
         
         #set($alert_lama_ob = "alert_lama_ob"+$list.BIL)  
         
        
         <tr  class="$row"    >
         <td valign="top"  >  
         $list.BIL
         </td>
         <td valign="top"  >  
         <a href="javascript:paparFail('$list.ID_OB')"  >   
         </a>     
         <font color="blue"><b>$list.NAMA_OB</b></font>  
        <!--          $list.ID_OB -->
         <input type="hidden" name="$id_ob" id="$id_ob" value="$list.ID_OB"   >
         <input type="hidden" name="$id_pemohon_ob" id="$id_pemohon_ob" value="$list.ID_PEMOHON"   >
           
           </td>
            <td valign="top"  >  
         
         #if($id_pemohon_checked == $list.ID_PEMOHON)
         #set($sama_kp_baru = $no_kp_baru_ob)
         #set($sama_kp_lama = $no_kp_lama_ob) 
         #set($sama_kp_lain = $no_kp_lain_ob)  
         #set($sama_kp_alert = $alert_baru_ob)         
         #else
         #set($sama_kp_baru = "")
         #set($sama_kp_lama = "")
         #set($sama_kp_lain = "")   
         #set($sama_kp_alert = "")       
         #end
        <!-- :::: $sama_kp_baru -->
          $list.NO_KP_BARU
       
        
      <!--  ::: $alert_baru_ob    -->   
         
           </td>
           
            <td valign="top" > 
      $list.NO_KP_LAMA
       
        
          
            <td valign="top"  >  
         $list.TARAF
         </td>
          
          <td valign="top"  >  
         $list.NAMA_STATUS_OB
         </td>
         
          <td valign="top"  align="center" >  
        <input type="radio" id="id_ob_pemohon" name="id_ob_pemohon" value="$list.ID_OB" >
         </td>
          </tr>
       #end
           </table>
     </fieldset> 
     #end   
      
     #end
     
     
     
<!-- Salnizam edit Tambah Dokumen Sokongan Starts -->
#if($!list_tukar_pemohon.size()>0)
<br>
     <fieldset>
     <legend>SEBAB PENGGANTIAN </legend>
      
     <table width="100%"  cellpadding="1" cellspacing="2" border="0">
     <tr>
     
     <td><input type="radio" name="sebab" value="kematian">
     <input type="hidden" name="id_permohonansimati" id="id_permohonansimati" value="$!getPemohonData2.ID_PERMOHONANSIMATI"/> 
     </td>
     <td> KEMATIAN </td>
     <td></td>
     </tr>
     <tr>
     <td>
     <input type="radio" name="sebab" value="kesihatan"> 
     </td>
     <td>MASALAH KESIHATAN</td>
     <td></td>
     </tr>
     <tr>
     <td></td>
     <td>MUATNAIK DOKUMEN SOKONGAN : <input id="fileupload" name="fileupload" type="file" size="40"> </td>
     <td>
      </td>
      </tr>
      #if($id_status != "21")
      <tr align="center">
<td></td>
<td>  
      <input name="cmdSimpanKPOb" id="cmdSimpanKPOb" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPOb')">
</td>
     </tr>
     #else
     <tr align="center">
<td></td>
<td>  
      <font color="red"><b>Fail ini berstatus selesai. Pertukaran pemohon tidak dibenarkan.</b></font>
</td>
     </tr>
     
     #end
     </table>
          </fieldset> 
     #end
<!--  Salnizam edit Ends -->
        

<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" /> 
<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />  
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input type="hidden" name="sama_kp_baru" id="sama_kp_baru" value="$!sama_kp_baru"   >
<input type="hidden" name="sama_kp_lama" id="sama_kp_lama" value="$!sama_kp_lama"   >
<input type="hidden" name="sama_kp_lain" id="sama_kp_lain" value="$!sama_kp_lain"   >
<input type="hidden" name="sama_kp_alert" id="sama_kp_alert" value="$!sama_kp_alert"   >



#parse("app/ppk/headerppk_script.jsp")

#if($!list_tukar_pemohon.size()>0)	
<script >
check_format_kp_baru('ob','$!list_tukar_pemohon.size()');
check_format_kp_lama('ob','$!list_tukar_pemohon.size()');
</script>
#end

#if($!papar_list_pemohon.size()>0)	
<script >
check_format_kp_baru('pemohon','$!papar_list_pemohon.size()');
check_format_kp_lama('pemohon','$!papar_list_pemohon.size()');
</script>
#end

#if($!papar_list_simati.size()>0)	
<script >
check_format_kp_baru('simati','$!papar_list_simati.size()');
check_format_kp_lama('simati','$!papar_list_simati.size()');
</script>
#end

   
<script >


	
function search_main_data_sub()
{
	SaveScrollXY();  	
	document.${formName}.action = "?_portal_module=ekptg.view.online.FrmTukarPemohon";   
	document.${formName}.command.value = "cariMainSub";
    document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
}
function kosongkan_sub() {
SaveScrollXY();        
document.${formName}.action = "?_portal_module=ekptg.view.online.FrmTukarPemohon";   
document.${formName}.txtNoFailSub.focus();
//doAjaxCall${formName}("kosongkan");
document.${formName}.command.value = "kosongkan";
    document.${formName}.submit();
}
function paparFail(id_fail)
{
document.${formName}.action = "?_portal_module=ekptg.view.online.FrmTukarPemohon";   
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;

//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";

    document.${formName}.submit();
}

function simpanSub(id_fail,nama_butang)
{
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
	if(document.${formName}.fileupload.value == ""){
		alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
  		document.${formName}.fileupload.focus(); 
		return;
	}
	if (document.${formName}.sebab.value == "") 
		{
		alert("Sila masukkan Sebab Penggantian terlebih dahulu."); 
		return;
		}
	else 
	{
		//myLogger.info("id_permohonansimati_atheader = " + id_permohonansimati_atheader);
	input_box = confirm("Adakah anda pasti?");	
	if (input_box == true) {
		//data
		//var name = document.${formName}.NAMA_DOKUMEN_ADD.value;
		//var keterangan = document.${formName}.DETAIL_DOKUMEN_ADD.value;
		//alert("Baca sini 1");
		
		var command = "&command=simpanSub";
		var sebab2 = document.${formName}.sebab.value;
		var id_ob_pemohon = document.${formName}.id_ob_pemohon.value;
		var id_permohonansimati_atheader = document.${formName}.id_permohonansimati.value;
		var data = "&id_fail_carian="+id_fail+"&txtNoFailSub="+txtNoFailSub.value+"&id_ob_pemohon="+id_ob_pemohon+"&id_permohonansimati_atheader="+id_permohonansimati_atheader+"&sebab="+sebab2;
		//myLogger.info("id_fail_carian = " + id_fail);
		
		//alert("Baca sini id_fail_carian --------" + id_fail + "txtNoFailSub = "+txtNoFailSub.value+" id_ob_pemohon= "+id_ob_pemohon+" id_permohonansimati_atheader= "+id_permohonansimati_atheader+" sebab = "+sebab2);
		var actionItem = (command+data);

		
		SaveScrollXY();        
		document.${formName}.id_fail_carian.value = id_fail;
		//doAjaxCall${formName}("simpanSub");
		//alert("Baca sini 1" + actionItem);
		document.${formName}.enctype = "multipart/form-data";
		document.${formName}.encoding = "multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.online.FrmTukarPemohon"+actionItem;   
		//document.${formName}.command.value = "simpanSub";
		//alert("Baca sini2" + command);
		document.${formName}.submit();
	
		document.getElementById(nama_butang).value = "Sila Tunggu...";
	}
		}
}

<!-- Salnizam edit starts -->
/**
function uploadFail(id_fail,nama_butang, id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,flag_skrin,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh){	
	//alert("CECK :::"+Validate());
		var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
		if(document.${formName}.NAMA_DOKUMEN_ADD.value == ""){
			alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu.");
	  		document.${formName}.NAMA_DOKUMEN_ADD.focus(); 
			return;
		}
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih \"Dokumen\" yang hendak dimuat naik terlebih dahulu.");
	  		document.${formName}.fileupload.focus(); 
			return;
		}
		if(Validate() == false && flag_skrin=="LaporanTanahTerperinciImej")
		{
			alert("Format fail yang dimuatnaik adalah tidak dibenarkan, Sila muatnaik fail berformat imej  seperti " + _validFileExtensions.join(", ") + "sahaja.");
	        return;
		}
		else{
			
			if ( !window.confirm("Adakah Anda Pasti?")) return;

			//token for dopost
			var ft = document.${formName}.form_token_pop.value;
			var token = "&form_token_pop="+ft;

			//data
			var name = document.${formName}.NAMA_DOKUMEN_ADD.value;
			var keterangan = document.${formName}.DETAIL_DOKUMEN_ADD.value;

			var command = "&command=uploadFile";
			
			var data = "&id_permohonan="+id_permohonan+"&id_pembatalan="+id_pembatalan+"&id_penarikanbalik="+id_penarikanbalik+"&id_bantahan="+id_bantahan+"&id_permintaanukur="+id_permintaanukur+"&id_award="+id_award+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin+"&nama_dokumen="+name+"&keterangan="+keterangan+"&id_tanah="+id_tanah+"&id_notisawam="+id_notisawam+"&id_buktipenyampaian="+id_buktipenyampaian+"&id_borangh="+id_borangh;

			var actionItem = (command+data+token);
			
			document.${formName}.enctype = "multipart/form-data";
			document.${formName}.encoding = "multipart/form-data";

			document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupUploadDokumen"+actionItem;
			document.${formName}.submit();		
		}
	}
	
<!-- Salnizam edit ends -->

*/
function SaveScrollXY() {
document.${formName}.ScrollX.value = document.body.scrollLeft;
document.${formName}.ScrollY.value = document.body.scrollTop;
}

function ResetScrollPosition() {
var hidx, hidy;
hidx = '$ScrollX';
hidy = '$ScrollY';
                        
if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
}


function check_format_kp_baru(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_baru_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_baru_'+jenis_kp+saizint);
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_baru_'+jenis_kp+i,'baru');
	  //alert("no_kp_ob > 1 ("+i+"):"+field.value)	
	  //alert("no_kp_ob > 1 ("+i+"):"+'alert_baru_'+jenis_kp+i)	
	  //alert("alert_baru_"+jenis_kp+i);
	  check_format_alert(field.value,'alert_baru_'+jenis_kp+i,'baru');
	  //$jquery("#alert_baru_"+jenis_kp+i).html("<font color = 'red'>"+"alert_baru_"+jenis_kp+i+"</font>");
	  } 	 	   
}
}

function check_format_kp_lama(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_lama_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_lama_'+jenis_kp+saizint,'lama');
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_lama_'+jenis_kp+i);	 
	  check_format_alert(field.value,'alert_lama_'+jenis_kp+i,'lama');	  
	  } 	 	   
}
}
  
function check_format_alert(value,div_id,jenis)
{
	
if(jenis == "baru")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 12)
	{
	ayat_alert = "Myid Tidak Lengkap";	
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}


if(jenis == "lama")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 8 && value.length != 7)
	{
		if(value!="TDK")
		{	
		ayat_alert = "Myid Tidak Lengkap";	
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
		}
		else
		{
		ayat_alert = "";
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
		}
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}

}

function check_format_alert_field(fil,div_id,jenis)
{
	
if(jenis=="baru")
{
	if(fil.value!="")
	{
		if(fil.value.length != 12)
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}
}

if(jenis=="lama")
{
	
	if(fil.value!="")
	{
		if(fil.value.length != 8 && fil.value.length != 7)
		{
			if(fil.value!="TDK")
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");	
			}else
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
			}
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
	}
}

	
}


function check_myid_temp(id)
{

	return $jquery("#"+id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
}

function sama_pemohon(field_pemohon_baru,field_pemohon_lama,field_pemohon_lain)
{
	
	var field_kp_alert = document.getElementById('sama_kp_alert').value;
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_pemohon_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_pemohon_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_pemohon_lain).value;
	
	if(field_ob_kp_baru!="")
	{
	document.getElementById(field_ob_kp_baru).value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById(field_ob_kp_baru),field_kp_alert)
	}
	
	if(field_ob_kp_lama!="")
	{
	document.getElementById(field_ob_kp_lama).value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	if(field_ob_kp_lain!="")
	{
	document.getElementById(field_ob_kp_lain).value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
}


function sama_ob()
{
	
	
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_ob_kp_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_ob_kp_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_ob_kp_lain).value;
	
	
	if(field_pemohon_baru_temp!="")
	{
	document.getElementById('no_kp_baru_pemohon1').value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById('no_kp_baru_pemohon1'),'alert_baru_pemohon1')
	}
	
	if(field_pemohon_lama_temp!="")
	{
	document.getElementById('no_kp_lama_pemohon1').value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	
	if(field_pemohon_lain_temp!="")
	{
	document.getElementById('no_kp_lain_pemohon1').value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
	
}

function check_myid_simati(action,div_alert)
{	
	/*url = "../servlet/ekptg.view.ppk.PendaftaranCheck";	
	actionName = action;
	target = div_alert;
	doAjaxUpdater(document.${formName}, url, target, actionName);*/
}


</script>