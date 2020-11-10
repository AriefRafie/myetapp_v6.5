<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>





<link href="../../styles.css" rel="stylesheet" type="text/css">

<!--
<p>
   <input name="report" type="hidden" id="report" value="$report"/>
   <input name="flagReport" type="hidden" id="flagReport" value="$flagReport"/>
   <input name="idperbicaraan" type="hidden" id="idperbicaraan" value="$id_perbicaraan"/>
   <input name="idhta" type="hidden" id="idhta" value="$idhta"/>
   <input name="daerahrayuan" type="hidden" id="daerahrayuan" value="$daerahrayuan"/>
   <input name="negerirayuan" type="hidden" id="negerirayuan" value="$negerirayuan"/>
   <input name="idobminor" type="hidden" id="idobminor" value="$idobminor"/>
   <input name="idfail" type="hidden" id="idfail" value="$idfail"/> 
   <input name="idsimati" type="hidden" id="idsimati" value="$idsimati"/> 
   <input name="idpejabatjkptg" type="hidden" id="idpejabatjkptg" value="$idpejabatjkptg"/> 
   <input name="idPegawai" type="hidden" id="idPegawai" value="$id_Pegawai"/>  
</p>
-->

 #if($report == 'mmk_selangor' || $report == 'surat_ap_siasatan' || $report == 'surat_pb_siasatan' || $report == 'surat_pemilik_siasatan'
 || $report == 'surat_JPPH_siasatan' || $report == 'surat_PTG_siasatan' || $report == 'surat_PTD_siasatan' || $report == 'surat_suruh_AP_Bayar')
#parse("app/ppt/tindakanPegawaiSignPPT.jsp")
#end



<input name="id_tanah" type="hidden" id="id_tanah" value="$id_tanah"/>
<input name="id_fail" type="hidden" id="id_fail" value="$id_fail"/>
<input name="id_siasatan" type="hidden" id="id_siasatan" value="$!id_siasatan"/>
<input name="id_penarikan" type="hidden" id="id_penarikan" value="$!id_penarikan"/>
<input name="pemilik" type="hidden" id="pemilik" value="$!pemilik"/>
<input name="no_fail" type="hidden" id="no_fail" value="$!no_fail"/>
<input name="id_mmk" type="hidden" id="id_mmk" value="$!id_mmk"/>


<input name="report" type="hidden" id="report" value="$report"/>
<input name="id_permohonan" type="hidden" id="id_permohonan" value="$id_permohonan"/>
<input name="no_rujukan_ptg" type="hidden" id="no_rujukan_ptg" value="$!no_rujukan_ptg"/> 
<input name="no_rujukan_ptd" type="hidden" id="no_rujukan_ptd" value="$!no_rujukan_ptd"/> 
<input name="no_rujukan_upt" type="hidden" id="no_rujukan_upt" value="$!no_rujukan_upt"/> 
<input name="selectNoFail" type="hidden" id="selectNoFail" value="$!selectNoFail"/>

<input name="nama_daerah" type="hidden" id="nama_daerah" value="$!nama_daerah"/>
<input name="senarai_mukim" type="hidden" id="senarai_mukim" value="$!senarai_mukim"/>
<input name="luas_lot" type="hidden" id="luas_lot" value="$!luas_lot"/>
<input name="senarai_lot" type="hidden" id="senarai_lot" value="$!senarai_lot"/>
<input name="id_bayaran" type="hidden" id="id_bayaran" value="$!id_bayaran"/>

<input name="token" type="hidden" id="token" value="$token"/>
<input type="hidden" name="userlogin" value='$!{session.getAttribute("_ekptg_user_id")}'>
<input name="ic_login" type="hidden" id="ic_login" value="$!ic_login"/>
<input name="id_jawatan1" type="hidden" id="id_jawatan1" value="$!id_jawatan1"/>
<input name="jawatan1" type="hidden" id="jawatan1" value="$!jawatan1"/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    #if(($token != "" && ($report == 'mmk_selangor' || $report == 'surat_ap_siasatan' || $report == 'surat_pb_siasatan' || $report == 'surat_pemilik_siasatan'
    || $report == 'surat_JPPH_siasatan' || $report == 'surat_PTG_siasatan' || $report == 'surat_PTD_siasatan' || $report == 'surat_suruh_AP_Bayar') )
	|| ($token == "" && ($report !='mmk_selangor' && $report != 'surat_ap_siasatan' && $report != 'surat_pb_siasatan' && $report != 'surat_pemilik_siasatan'
	&& $report != 'surat_JPPH_siasatan' && $report != 'surat_PTG_siasatan' && $report != 'surat_PTD_siasatan' && $report != 'surat_suruh_AP_Bayar')))
    	<fieldset><legend><strong>Cetakan Laporan</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              
               
              <tr>
                <td width="30%">  <input type="hidden" id="id_jawatan" name="id_jawatan" value="$!id_jawatan" /></td>
                <td width="70%">&nbsp;</td>
              </tr>
              
              <!-- RADIO BUTTON SELECT FILE -->
             
              
              <tr>
                <td valign="top">No. Fail
                </td>
                <td valign="top" >
              
                :
                <select name="sorSelectNoFail" id="sorSelectNoFail" class="autoselect">
      		
		      			#if($sorSelectNoFail=="1")
						<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
                        <option value="5">TIADA NO FAIL</option>
						<option value="">SILA PILIH</option>
		      			#elseif($sorSelectNoFail=="2")
		      			<option value="2">NO. RUJUKAN PTG</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
                        <option value="5">TIADA NO FAIL</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="3")
		 				<option value="3">NO. RUJUKAN PTD</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="4">NO. RUJUKAN UPT</option>
                        <option value="5">TIADA NO FAIL</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="4")
		 				<option value="4">NO. RUJUKAN UPT</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
                        <option value="5">TIADA NO FAIL</option>
						<option value="">SILA PILIH</option>
                        #elseif($sorSelectNoFail=="5")
                        <option value="5">TIADA NO FAIL</option>
		 				<option value="4">NO. RUJUKAN UPT</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>                       
						<option value="">SILA PILIH</option>
						#else
		      			<option value="">SILA PILIH</option>
		      			<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
                        <option value="5">TIADA NO FAIL</option>
		      			#end
		      			
					</select></td>
              </tr>
              
            
              #if($report != 'mmk_selangor' && $report != 'surat_ap_siasatan' && $report != 'surat_pb_siasatan' && $report != 'surat_pemilik_siasatan'
              && $report != 'surat_JPPH_siasatan' && $report != 'surat_PTG_siasatan' && $report != 'surat_PTD_siasatan' && $report != 'surat_suruh_AP_Bayar')
              <tr>
                <td>No. Fail Permohonan (JKPTG)</td>
                <td>:$!no_fail.toUpperCase()</td>
              </tr>
              
              #if($no_rujukan_ptg!="" && $no_rujukan_ptg!="-")
              <tr>
                
                <td>No. Rujukan PTG</td>
                <td>:$!no_rujukan_ptg.toUpperCase()</td>
              </tr>
              #end
              
              #if($no_rujukan_ptd!="" && $no_rujukan_ptd!="-")
              <tr>
              
                <td>No. Rujukan PTD</td>
                <td>:$!no_rujukan_ptd.toUpperCase()</td>
              </tr>
              #end
              
              #if($no_rujukan_upt!="" && $no_rujukan_upt!="-")
              <tr>
                <td>No. Rujukan UPT</td>
                <td>:$!no_rujukan_upt.toUpperCase()</td>
              </tr>
             #end
             #end
              
              
               #if( $report == 'mmk_Perak' || $report == 'mmk_Kedah' || $report == 'mmk_mb_selangor' || $report == 'mmk_N9'  || $report == 'jadual_mmk_N9')
              <tr>
      
        <td width="30%" valign="top">
       Nama Menteri Besar        </td>
        
             
        <td width="70%">:
     <input name="nama_menteri" type="text" id="nama_menteri" size="60" maxlength="300"  value="$!nama_menteri" />        </td>
      </tr>
      #end  
      
      
        #if($report == 'mmk_Johor' || $report == 'mmk_Kelantan' || $report == 'mmk_Penang' || $report == 'mmk_Pahang' || $report == 'mmk_Kedah' || $report == 'mmk_Perak' || $report == 'mmk_mb_selangor' || $report == 'mmk_mb_selangor_jadual'  || $report == 'mmk_jadual_kl' || $report == 'mmk_melaka' || $report == 'mmk_N9')
              <tr>
      
        <td width="30%" valign="top">
        
       #if($report == 'mmk_Johor')
       Nama Pengarah
       #elseif($report == 'mmk_Kelantan')
       Disediakan Oleh
       #else
       Nama Pengarah Tanah Dan Galian
       
       #end       </td>       
             
        <td width="70%">:
     <input name="nama_pegawai" type="text" id="nama_pegawai" size="60" maxlength="300" value="$!nama_pegawai" />        </td>
      </tr>
      #end    
      
    #if($report == 'mmk_Kedah' || $report == 'mmk_Kelantan')
    
    <tr>
      
        <td width="30%" valign="top">
       Jawatan        </td>        
             
        <td width="70%">:
     <input name="nama_jawatan" type="text" id="nama_jawatan" size="60" maxlength="300"  value="$!nama_jawatan" />        </td>
      </tr>
      #end   
        
          #if($report != 'mmk_Johor' && $report != 'mmk_Terengganu' && $report != 'mmk_Penang' && $report != 'mmk_Kelantan' 
          && $report != 'mmk_Pahang' && $report != 'mmk_Perlis' && $report != 'mmk_Perak' && $report != 'mmk_Kedah' && $report != 'mmk_mb_selangor'
          && $report != 'mmk_mb_selangor_jadual' && $report != 'mmk_jadual_kl' && $report != 'mmk_kl' && $report != 'mmk_melaka' 
          && $report != 'mmk_N9' && $report != 'jadual_mmk_N9' && $report != 'mmk_selangor' && $report != 'surat_ap_siasatan' && $report != 'surat_pb_siasatan'
          && $report != 'surat_pemilik_siasatan' && $report != 'surat_JPPH_siasatan' && $report != 'surat_PTG_siasatan' && $report != 'surat_PTD_siasatan'
          && $report != 'surat_suruh_AP_Bayar')    
              <tr>
      
        <td width="30%" valign="top">
        #if($report != 'mmk_selangor' )
        Nama Pegawai
        #else
        Nama Pengarah
        #end        </td>
       
        <td width="70%">:
 
   
     <select name="nama_pegawai1" class="autoselect" id="nama_pegawai1"  onchange="getJawatan()" >  
  
    #if($!nama_pegawai1 != "")
    
    #if($report == 'mmk_selangor' || $report == 'mmk_mb_selangor')
    #foreach($ln1 in $list_pegawai1)  
    #if($!nama_pegawai1 == $ln1.ID_PEGAWAI && $ln1.ID_JAWATAN == '4')
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option>                                     
    #end 
    #end 
    #else
    #foreach($ln1 in $list_pegawai1)  
    #if($!nama_pegawai1 == $ln1.ID_PEGAWAI)
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option>                                     
    #end 
    #end 
    #end
    
    
    
    #if($report == 'mmk_selangor' || $report == 'mmk_mb_selangor')
    #foreach($ln1 in $list_pegawai1)  
    #if($!nama_pegawai1 != $ln1.ID_PEGAWAI && $ln1.ID_JAWATAN == '4')
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option> 
    #end
    #end
    #else    
    #foreach($ln1 in $list_pegawai1) 
    #if($!nama_pegawai1 != $ln1.ID_PEGAWAI)
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option>                                     
    #end      
    #end    
    #end
    
    
     #if($report != 'mmk_selangor' && $report != 'mmk_mb_selangor')
     <option value=""> SILA PILIH NAMA PEGAWAI</option>        
     #else
     <option value="">SILA PILIH NAMA PENGARAH</option>       
     #end
    
     #else
   
     #if($report != 'mmk_selangor' && $report != 'mmk_mb_selangor')
     <option value=""> SILA PILIH NAMA PEGAWAI</option>        
     #else
     <option value="">SILA PILIH NAMA PENGARAH</option>       
     #end    
     
     
    #if($report == 'mmk_selangor' || $report == 'mmk_mb_selangor')
    #foreach($ln1 in $list_pegawai1)  
    #if($ln1.ID_JAWATAN == '4')
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option>
    #end
    #end
    #else 
    #foreach($ln1 in $list_pegawai1) 
    <option value="$ln1.ID_PEGAWAI">$ln1.NAMA_PEGAWAI</option>
    #end
    #end
    
    #end
    
    </select>    </td>
      </tr>
      #end
      
      
      
      
    #if($report == 'mmk_Kelantan' || $report == 'mmk_Terengganu' )   
      <tr>
      
        <td width="30%" valign="top">
       Pengarah Tanah Dan Galian        </td>
        
             
        <td width="70%">:
     <input name="pengarah_ptg" type="text" id="pengarah_ptg" size="60" maxlength="300"  value="$!pengarah_ptg" />        </td>
      </tr>
      
    #end  
    
    #if($report == 'mmk_Kelantan' )   
      <tr>
      
        <td width="30%" valign="top">
       Pentadbir Tanah Dan Jajahan        </td>
        
             
        <td width="70%">:
     <input name="ptd" type="text" id="ptd" size="60" maxlength="300"  value="$!ptd" />        </td>
      </tr>
      
    #end  
      
      
      
     #if($report == 'mmk_Penang' || $report == 'mmk_Terengganu' || $report == 'mmk_Perlis' || $report == 'mmk_Pahang'  || $report == 'mmk_Kedah' || $report == 'mmk_Johor'  || $report == 'mmk_selangor' || $report == 'mmk_mb_selangor' || $report == 'mmk_mb_selangor_jadual' || $report == 'akuan_penerimaan' || $report == 'akuan_penerimaan_all' || $report == 'mmk_jadual_kl'  || $report == 'mmk_kl' || $report == 'mmk_melaka' || $report == 'mmk_N9' || $report == 'jadual_mmk_N9')      
      <tr>      
        <td width="30%" valign="top">
      Bil. Dokumen </td>            
        <td width="70%">:
     <input name="bil_surat" type="text" id="bil_surat" size="10" maxlength="100" value="$!bil_surat" />        </td>
      </tr>
      #end
      
        #if($report == 'mmk_Penang' || $report == 'mmk_Kelantan')
      
      <tr>      
        <td width="30%" valign="top">
        
          #if($report == 'mmk_Kelantan')
      Kertas Bil.
      #else
       Bil. Kertas
      #end      </td>            
        <td width="70%">:
     <input name="bil_kertas" type="text" id="bil_kertas" size="10" maxlength="100" value="$!bil_kertas" />        </td>
      </tr>
      
      #end
      
      
      
          #if($report == 'surat_serah_bayar_AP')
      
      <tr>      
        <td width="30%" valign="top">
        
         
      Bil. Surat      </td>            
        <td width="70%">:
     <input name="bil_surat" type="text" id="bil_surat" size="10" maxlength="100" value="$!bil_surat" />        </td>
      </tr>
      
      <tr>      
        <td width="30%" valign="top">
      Tarikh Surat</td>            
        <td width="70%">:
      <input name="tarikh_surat" type="text" id="tarikh_surat" size="10" maxlength="10"    value="$!tarikh_surat"  />  
     
   
         <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>        </td>
      </tr>
      #end
      
      
      #if($report == 'surat_JPPH_siasatan')
      
       <tr>
      
        <td width="30%" valign="top">Pejabat JPPH</td>
       
        <td width="70%">:
  
    
    
    
    <select name="id_pejabat" class="autoselect" id="id_pejabat"  >  
    #if($!id_pejabat != "")
    
    #foreach($ln in $list_jpph)   
    #if($!id_pejabat==$ln.ID_PEJABAT)
    <option value="$ln.ID_PEJABAT">$ln.NAMA_PEJABAT , $ln.NAMA_DAERAH</option>                                     
    #end 
    #end 
   
    #foreach($ln in $list_jpph)   
    #if($!id_pejabat!=$ln.ID_PEJABAT)
    <option value="$ln.ID_PEJABAT">$ln.NAMA_PEJABAT , $ln.NAMA_DAERAH</option>                                     
    #end      
    #end
    <option value="">SILA PILIH PEJABAT</option> 
    
    #else
   
    <option value="">SILA PILIH PEJABAT</option>        
    #foreach($ln in $list_jpph)   
    <option value="$ln.ID_PEJABAT">$ln.NAMA_PEJABAT , $ln.NAMA_DAERAH</option>   
    #end
    
    #end
    
    </select>    </td>
      </tr>
           #end
              <tr>
                <td>&nbsp;</td>
                <td>
                
                
                
                
               
                
                
                   #if($report == 'mmk_jadual_kl')
                    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkKL_1('$id_penarikan')"> 
                   #end
                
                
                
                
                   #if($report == 'surat_suruh_AP_Bayar')
                       
                     <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_suruh_AP_Bayar('$id_fail','$id_siasatan','$!username','$!ic_login')">
                   #end
                
                
                   #if($report == 'akuan_penerimaan')
                       <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:Akuan_Penerimaan('$id_bayaran')">
                   #end
                   
                   #if($report == 'akuan_penerimaan_all')
                       <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:AkuanPenerimaanKeseluruhan('$id_hakmilikpb','$id_penarikan')">
                   #end
                
                
                  #if($report == 'surat_serah_bayar_AP')
                  
                
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript: SuratMaklumanSerahPampasanAP_PB('$id_fail','$id_pembatalan','$id_bayaran')">
                #end
                
         
                
                 #if($report == 'surat_pangil_bayar_PB')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:PangilTerimaPampasanPB_PB('$id_fail','$id_pembatalan','$id_bayaran')">
                #end
                
                #if($report == 'mmk_N9')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkN9('$id_mmk','$id_fail','$no_fail')">
                #end
                
                
                  #if($report == 'mmk_Kedah')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkKedah('$id_mmk','$id_fail','$no_fail')">
                #end
                
                
                 #if($report == 'mmk_Penang')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkPenang('$id_mmk','$id_fail','$no_fail')">
                #end
                
                 #if($report == 'mmk_Pahang')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkPahang('$id_mmk','$id_fail','$no_fail')">
                #end
                
                 #if($report == 'mmk_Kelantan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkKelantan('$id_mmk','$id_fail','$no_fail')">
                #end
                
                #if($report == 'mmk_Terengganu')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkTerengganu('$id_mmk','$id_fail','$no_fail')">
                #end
                
                #if($report == 'mmk_Perlis')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkPerlis('$id_mmk','$id_fail','$no_fail')">
                #end
                
                  #if($report == 'mmk_Perak')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkPerak('$id_mmk','$id_fail','$no_fail')">
                #end
                
                #if($report == 'jadual_mmk_N9')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkN9_1('$id_penarikan')">
                #end
                
                
                #if($report == 'mmk_selangor')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkSelangor1('$id_mmk','$nama_daerah','$id_fail','$no_fail','$!username','$!ic_login')">
                #end
                
                #if($report == 'mmk_Johor')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkJohor('$id_mmk','$nama_daerah','$id_fail','$no_fail')">
                #end
                
                #if($report == 'mmk_melaka')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkMelaka('$id_mmk','$id_fail','$no_fail')">
                #end
                
                 #if($report == 'mmk_mb_selangor')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkSelangor2('$id_mmk','$nama_mukim_mukim','$no_lot_mmk ','$overallluas')">
                #end
                
                
                  #if($report == 'mmk_mb_selangor_jadual')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:mmkSelangor3('$id_penarikan')">
                #end
                
                #if($report == 'laporan_tanah')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLaporanTanah('$id_tanah')">
                #end
                
                #if($report == 'surat_JPPH_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_JPPH('$id_siasatan','$pemilik','$!username','$!ic_login','$!id_jawatan1')">
                #end
                
                 #if($report == 'surat_ap_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_AP1('$id_siasatan','$pemilik','$!username','$!ic_login','$!id_jawatan1')">
                #end
                
                 #if($report == 'surat_PTG_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_PTG('$id_siasatan','$pemilik','$!username','$!ic_login','$!id_jawatan1')">
                #end
                
                 #if($report == 'surat_PTD_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_PTD('$id_siasatan','$pemilik','$!username','$!ic_login','$!id_jawatan1')">
                #end
                
                 #if($report == 'surat_pb_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_PB('$id_fail','$id_siasatan','$pemilik','$id_penarikan','$!username','$!ic_login','$!id_jawatan1')">
                #end
                
                 #if($report == 'surat_pemilik_siasatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:surat_pangil_PEMILIK('$id_fail','$id_siasatan','$pemilik','$id_penarikan','$!username','$!ic_login','$!id_jawatan1')">
                #end
                 
                <input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">                </td>
              </tr>
            </table>
      </fieldset>
      #end
 
      #if($report != 'mmk_Johor' && $report != 'mmk_Penang' && $report != 'mmk_Kelantan' && $report != 'mmk_Pahang' && $report != 'mmk_Perak' && $report != 'mmk_Kedah' && $report != 'mmk_mb_selangor' && $report != 'mmk_mb_selangor_jadual' && $report != 'mmk_jadual_kl' && $report != 'mmk_melaka' && $report != 'mmk_N9' )
      <input type="hidden" id="nama_pegawai" name="nama_pegawai" value="$!nama_pegawai" />
     
      #end
      
      
     
    </td>
  </tr>
</table>


<script>

if('$report' == 'mmk_kl')
{                      
mmkKL('$id_mmk','$bil_lot','$luas_lot','$id_fail','$no_fail');
}

function pasvalue(val)
{
   document.${formName}.nama_pegawai.value = val;

}

function getJawatan()
{

document.${formName}.command.value = "getJawatan";
document.${formName}.submit();

}

function keluar() {
	window.close();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	} else {
		if (content.length < 2){
			document.${formName}.txtBilDokumen.value = "0" + content;
		}
	}
}
function RemoveNonNumeric( strString ){
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function cetakLaporanTanah(id_tanah) {
window.close();

        var nofail = "";
		var valType = document.${formName}.sorSelectNoFail.value;
			
 	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

 var url = "../../servlet/ekptg.report.ppt.LaporanTanahPB?id_tanah="+id_tanah+"&nama_pegawai="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();





}

function surat_pangil_AP1(id_siasatan,pemilik,username,ic_login,id_jawatan)
{
 window.close();
 
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
 
    var url = "../../servlet/ekptg.report.ppt.AP_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+username+"&id_jawatan="+id_jawatan+"&pemilik="+pemilik+"&no_fail="+nofail+"&userlogin="+ic_login;    
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

/*
  var url = "../servlet/ekptg.report.ppt.AP_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai=razman&pemilik="+pemilik;  
      var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
	*/

}


function surat_pangil_PB(id_fail,id_siasatan,pemilik,id_penarikan,username,ic_login,id_jawatan)
{
window.close();

 var nofail = "";
 var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

    var url = "../../servlet/ekptg.report.ppt.PB_HadirSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+username+"&pemilik="+pemilik+"&id_jawatan="+id_jawatan+"&no_fail="+nofail+"&userlogin="+ic_login;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}




function Akuan_Penerimaan(id_bayaran)
{

    window.close();
	
  var nofail = "";
  var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
	
    var url = "../../servlet/ekptg.report.ppt.AkuanPenerimaan?ID_BAYARAN="+id_bayaran+"&NAMA_PEGAWAI="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

function AkuanPenerimaanKeseluruhan(id_hakmilikpb,id_penarikan)
{

    window.close();
	
	 var nofail = "";	
	 var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	
    var url = "../../servlet/ekptg.report.ppt.AkuanPenerimaan_All?ID_HAKMILIKPB="+id_hakmilikpb+"&ID_PENARIKANBALIK="+id_penarikan+"&NAMA_PEGAWAI="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

function surat_pangil_PEMILIK(id_fail,id_siasatan,pemilik,id_penarikan,username,ic_login,id_jawatan)
{
//window.close();

 var nofail = "";
 var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

    var url = "../../servlet/ekptg.report.ppt.PEMILIK_HadirSiasatan_PB?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&id_penarikan="+id_penarikan+"&nama_pegawai="+username+"&pemilik="+pemilik+"&id_jawatan="+id_jawatan+"&no_fail="+nofail+"&userlogin="+ic_login;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}

function surat_pangil_PTG(id_siasatan,pemilik,username,ic_login,id_jawatan)
{
//window.close();

 var nofail = "";	
 var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

    var url = "../../servlet/ekptg.report.ppt.PTG_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+username+"&pemilik="+pemilik+"&id_jawatan="+id_jawatan+"&no_fail="+nofail+"&userlogin="+ic_login;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function surat_pangil_PTD(id_siasatan,pemilik,username,ic_login,id_jawatan)
{
//window.close();
var valType = document.${formName}.sorSelectNoFail.value;

 var nofail = "";		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

    var url = "../../servlet/ekptg.report.ppt.PTD_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+username+"&pemilik="+pemilik+"&id_jawatan="+id_jawatan+"&no_fail="+nofail+"&userlogin="+ic_login;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function surat_pangil_JPPH(id_siasatan,pemilik,username,ic_login,id_jawatan)
{
   //window.close();
   
    var nofail = "";
	var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
   
    var url = "../../servlet/ekptg.report.ppt.JPPH_HadirSiasatan_PB?id_siasatan="+id_siasatan+"&nama_pegawai="+username+"&pemilik="+pemilik+"&id_pejabat="+document.${formName}.id_pejabat.value+"&id_jawatan="+id_jawatan+"&no_fail="+nofail+"&userlogin="+ic_login;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}




function mmkSelangor1(id_mmk,nama_daerah1,id_fail,no_fail,username,ic_login)
{

window.close();

           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
		   
		   
		    var nofail = "";
			var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	   
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor?id_mmk="+id_mmk+"&nama_pengarah="+username+"&tarikh_surat="+currentDate+"&nama_daerah="+nama_daerah1+"&id_fail="+id_fail+"&no_fail="+no_fail+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&userlogin="+ic_login; 
 
  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();



}

function mmkJohor(id_mmk,nama_daerah1,id_fail,no_fail)
{

window.close();

           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
		   
		    var nofail = "";	
			var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	   
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikJohor?id_mmk="+id_mmk+"&nama_pengarah="+document.${formName}.nama_pegawai.value+"&tarikh_surat="+currentDate+"&nama_daerah="+nama_daerah1+"&id_fail="+id_fail+"&no_fail="+no_fail+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail; 
 
  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();



}


function mmkSelangor2(id_mmk,nama_mukim_mukim,no_lot_mmk,overallluas)
{

window.close();




           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
		   
		    var nofail = "";	
			var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	


   
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor2?id_mmk="+id_mmk+"&nama_pengarah="+document.${formName}.nama_pegawai.value+"&nama_menteri="+document.${formName}.nama_menteri.value+"&luas_lot="+overallluas+"&senarai_mukim="+nama_mukim_mukim+"&senarai_lot="+no_lot_mmk+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail; 
 
 
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	


}


function surat_suruh_AP_Bayar(id_fail,id_siasatan,username,ic_login)
{

 var nofail = "";		
 var valType = document.${formName}.sorSelectNoFail.value;
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}

	var url = "../../servlet/ekptg.report.ppt.SuratAPBayar?id_fail="+id_fail+"&ID_SIASATAN="+id_siasatan+"&namaPegawai="+username+"&id_jawatan="+document.${formName}.id_jawatan.value+"&no_fail="+nofail+"&userlogin="+ic_login;   
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
	}


function SuratMaklumanSerahPampasanAP_PB(id_fail,id_penarikan,id_bayaran)
    {
	window.close();
	
	 var nofail = "";	
	 var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	
    var url = "../../servlet/ekptg.report.ppt.SuratMaklumanSerahPampasanAP_PB?id_fail="+id_fail+"&nama_pegawai="+document.${formName}.nama_pegawai.value+"&id_penarikan="+id_penarikan+"&idBayaran="+id_bayaran+"&bilSurat="+document.${formName}.bil_surat.value+"&tarikhSurat="+document.${formName}.tarikh_surat.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	
	}
	
	function PangilTerimaPampasanPB_PB(id_fail,id_penarikan,id_bayaran)
{
   window.close();
   
    var nofail = "";	
	var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
   
   var url = "../../servlet/ekptg.report.ppt.PangilTerimaPampasanPB_PB?id_fail="+id_fail+"&id_penarikan="+id_penarikan+"&ID_BAYARAN="+id_bayaran+"&NAMA_PEGAWAI="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();




}





                  
				
                   

function mmkKL(id_mmk,bil_lot,luas_lot,id_fail,no_fail)
{
  window.close();
  
   var nofail = "";	
   var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikKL?id_mmk="+id_mmk+"&bil_lot="+bil_lot+"&luas_lot="+luas_lot+"&id_fail="+id_fail+"&no_fail="+no_fail+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function mmkMelaka(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikMelaka?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&pengarah_ptg="+document.${formName}.nama_pegawai.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}



function mmkN9(id_mmk,id_fail,no_fail)
{
  window.close();
     
	 var nofail = "";
	 var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikN9?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&pengarah_ptg="+document.${formName}.nama_pegawai.value+"&nama_mb="+document.${formName}.nama_menteri.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function mmkKedah(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikKedah?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&pengarah_ptg="+document.${formName}.nama_pegawai.value+"&nama_jawatan="+document.${formName}.nama_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function mmkPenang(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikPenang?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&namaPegawai="+document.${formName}.nama_pegawai.value+"&kertasBil="+document.${formName}.bil_kertas.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function mmkPahang(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikPahang?idMMK="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&pengarah_ptg="+document.${formName}.nama_pegawai.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function mmkKelantan(id_mmk,id_fail,no_fail)
{
  window.close();
  
    var nofail = "";
	var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikKelantan?idMMK="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&namaPegawai="+document.${formName}.nama_pegawai.value+"&jawatanPegawai="+document.${formName}.nama_jawatan.value+"&bilKertas="+document.${formName}.bil_kertas.value+"&namaPengarah="+document.${formName}.pengarah_ptg.value+"&namaPTD="+document.${formName}.ptd.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function mmkTerengganu(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikTerengganu?idMMK="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&namaPegawai="+document.${formName}.pengarah_ptg.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function mmkPerlis(id_mmk,id_fail,no_fail)
{
  window.close();
  
  var nofail = "";
  var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikPerlis?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}


function mmkPerak(id_mmk,id_fail,no_fail)
{
  window.close();
       var nofail = "";	
	   var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikPerak?id_mmk="+id_mmk+"&id_fail="+id_fail+"&no_fail="+no_fail+"&namaPegawai="+document.${formName}.nama_pegawai.value+"&namaMenteri="+document.${formName}.nama_menteri.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}



function mmkN9_1(id_penarikan)
{
  window.close();
  
  var nofail = "";	
  var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikJadualN9?id_penarikan="+id_penarikan+"&nama_mb="+document.${formName}.nama_menteri.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}




function mmkKL_1(id_penarikan)
{
  window.close();
  
        var nofail = "";	
		var valType = document.${formName}.sorSelectNoFail.value;	
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
     
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikJadual?id_penarikan="+id_penarikan+"&nama_pengarah="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}




function mmkSelangor3(id_penarikan)
{
  window.close();
           var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
		   
		var nofail = "";
		var valType = document.${formName}.sorSelectNoFail.value;		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else if(valType=="5"){
			nofail = "";
		}else{
			nofail = document.${formName}.no_fail.value;
		}
	  
    var url = "../../servlet/ekptg.report.ppt.MmkPenarikanBalikSelangor3?id_penarikan="+id_penarikan+"&nama_pengarah="+document.${formName}.nama_pegawai.value+"&id_jawatan="+document.${formName}.id_jawatan.value+"&bilSurat="+document.${formName}.bil_surat.value+"&no_fail="+nofail;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}


function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}



</script>

