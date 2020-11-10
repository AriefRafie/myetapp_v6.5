<input type="hidden" name="modul" value="$!modul">

#if($modul != "ekptg.view.ppt.FrmSiasatanOnline")
#parse("app/ppt/Sek8Paging.jsp")
#end

<table width="100%">
  <tr>
    <td> #parse("app/ppt/header_1_ppt.jsp")</td>
  </tr>
  <tr>
    <td>
<br />
<fieldset>
<legend>SENARAI LOT PERMOHONAN </legend>
 <table width="100%" border="0"> 
    <tr >
    <td align="left">
     #if($modul == "ekptg.view.ppt.FrmSiasatanOnline")
    <a href="javascript:popupCarianHakmilik('$id_permohonan','siasatan_online')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
   #else
    <a href="javascript:popupCarianHakmilik('$id_permohonan','senarai_siasatan')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
   #end </td>
    </tr>
    </table>

             <table width="100%" style="display:none">
              <tr>              
              <td colspan="8">
              
              <table width="100%" border="0">
              <tr>
              <td width="1%">
              </td>
              <td align="left" width="30%" valign="top">&nbsp;</td>
              <td width="69%" align="right" valign="top">
            
              Carian No.LOT<b>/</b>No.PT<b>/</b>Nama Pihak Berkepentingan : 
              
              <label>
                    <input type="text" size="25" name="CariLot" id="CariLot" value="$!CariLot" />
                    </label>
                 
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="cari_lot('$!id_permohonan')" />
                      <input type="button" name="kosongkan" id="kosongkan" value="Kosongkan" onClick="cari_lot1('$!id_permohonan')"  />
                      </label>
                     
                      #if($readmode == "edit")
                      <div id="jumlahlot"  ></div>
                      #end
               
                </td>
              </tr>
              </table>
              
              
             </td>
             </tr>
                <tr  >
                  <td >
                  #if($maklumat_pembatalan.size() > 0)
                  #if($senarai_hakmilik_batal.size()!=0)
                  <!--
                    <input name="cmdPilihPembatalanLot" id="cmdPilihPembatalanLot" type="button" value="Batal Lot Pembatalan" onClick="batalkan_lot()">
                    -->
                    
                    #end
                    #end
                  <table width="100%">
                    <tr class="table_header">
                      <td width="3%">Bil</td>
                      <td width="8%">No. Lot/PT</td>
                      <td width="10%">No. Subjaket</td>
                      <td width="17%">Hakmilik atau Pendudukan</td>                      
                   <!--   <td width="15%">Luas Lot</td> -->
                      <td width="30%">Mukim/Pekan/Bandar</td>
                       <td width="15%">Luas Lot Diambil</td>
                      <td width="17%"><div align="center">Siasatan</div></td>
                    </tr>
                  
                   #if($senarai_hakmilik_batal.size()!=0)
             #foreach($list in $senarai_hakmilik_batal)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
                               <tr >  
                <td colspan="7"> 
              

 <fieldset  id="$list.BIL"style="visibility:collapse; display:none;" class="$row">
 <table width="100%"   > 
 
 

  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_PT</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$list.NAMA_NEGERI</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$list.NAMA_DAERAH</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$list.NAMA_MUKIM</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$list.JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$list.KOD_JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
  </tr>
   <tr style="display:none">
    <td>&nbsp;</td>
    <td>Luas Ditarik Balik</td>
    <td>:</td>
    <td>$list.LUAS_LOT_TARIK</td>
  </tr>
  
  <!--
  <tr>
    <td>&nbsp;</td>
    <td>Pihak Kepentingan</td>
    <td>:</td>
    <td>
        #set($count = 0) 
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )     
                      #set($count=$count + 1) 
                      #end
                      #end
                      
                      #set($count_total = 0) 
                      
                      #if($count == 1)
                      #foreach($list1 in $senarai_pihak_penting)
                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      $list1.NAMA_PB
                      #end
                      #end
                      #elseif($count > 1)
                      
                      
                      
                      <select name=""  class="autoselect" >
                      #foreach($list1 in $senarai_pihak_penting)                      
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )  
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end                        
                      <option  >
                      $list1.NAMA_PB  
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end    </td>
  </tr>
  -->
</table>
 </fieldset>
                 </td> 
                </tr>
                
                
                    <tr id="$list.BIL_DUM" class="$row" >
                      <td  >$list.BIL</td>
                      <td id="$list.NO_PT" >
                         <a class="style1" id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,'$list.BIL');" title="Klik untuk maklumat lengkap">$list.NO_PT</a></td>
                      <td>#if($list.NO_SUBJAKET!="")Sj.$list.NO_SUBJAKET #else &nbsp; #end</td>
                      <td >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
                    
                 <!--     <td >$list.LUAS_LOT</td> -->
                      <td >$list.NAMA_MUKIM</td>
                        <td > 
                          $list.LUAS_AMBIL 
                             </td>
                      
                      
                      <td >
            
  #set($ada_tak = "")
  #set($id_hakmilik_tak = $list.ID_HAKMILIK) 
  #set($id_siasatan_tak = "") 
            
  #if($list_siasatan_penarikan.size() > 0)
  #foreach($list4 in $list_siasatan_penarikan)     
  #if($list4.ID_HAKMILIK == $list.ID_HAKMILIK)
  
  #set($ada_tak = "yes") 
  
  #set($id_hakmilik_tak = $list4.ID_HAKMILIK) 
  #set($id_siasatan_tak = $list4.ID_SIASATAN) 
  
  #end
  #end  
  #end   
             
       
       #if($ada_tak == "yes")      
             
                         <table width="100%"  >
<tr>
<td colspan="3">
 <div align="left"><a href="javascript:papar('$id_siasatan_tak','$id_hakmilik_tak')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">MAKLUMAT  SIASATAN</font></a></div></td>
</tr>

<tr>
<td colspan="3">
 <div align="left"><a href="javascript:kehadiran('$id_siasatan_tak')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div></td>
</tr>


<tr>
<td colspan="3">
 <div align="left"><a href="javascript:maklumatsiasatan('$id_siasatan_tak')" title="Memaparkan secara lengkap nota siasatan"><font color="blue">NOTA SIASATAN </font></a></div></td>
</tr>
</table> 
               #else      
            
           <div align="left"><a href="javascript:UrusanSiasatanSingle('$id_hakmilik_tak','')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">MAKLUMAT SIASATAN</font></a></div>
                
                #end    
                     
                     
                      </td>                   </tr>
              #end
              
              #else
              <tr>
              <td colspan="8">
              Tiada Rekod              </td>
              </tr>
              #end
                  </table></td>
                </tr>
              </table>
       
</fieldset>
</td>
</tr>




<tr  style="display:none">
<td>

<fieldset id="senarai_siasatan">
<legend>SENARAI SIASATAN</legend>
<table width="100%" >
  <tr class="table_header">
  	<td width="4%">Bil</td>
    <td width="14%">No. Kes </td>
    <td width="14%">No.Lot/PT</td>
    <td width="10%"><div align="center">Bil Siasatan</div></td>
    <td  width="20%"><div align="center">Tarikh/Masa Siasatan</div></td>
    <td  width="25%">Tempat Siasatan</td>
    <td  width="15%">Status</td>
     
  
   #if($list_siasatan_penarikan.size() > 0)
  #foreach($list in $list_siasatan_penarikan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
 

 
 
 <tr >
 <td colspan="8">
<fieldset id="$list.BILTEMP" class="$row" style="visibility:collapse; display:none;">
<table width="100%"  >
<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:papar('$list.ID_SIASATAN','$list.ID_HAKMILIK')" title="Memaparkan secara lengkap maklumat set siasatan"><font color="blue">MAKLUMAT SET SIASATAN</font></a></div></td>
</tr>

<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:kehadiran('$list.ID_SIASATAN')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div></td>
</tr>


<tr>
<td width="1%"></td>
<td colspan="3">
 <div align="left"><a href="javascript:maklumatsiasatan('$list.ID_SIASATAN')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">NOTA SIASATAN TARIK BALIK</font></a></div></td>
</tr>
<tr>
    <td width="1%"></td>
    <td width="28%">No. Kes</td>
    <td width="1%">:</td>
    <td width="70%">$list.NO_KES</td>
  </tr>
  <tr>
    <td></td>
    <td>No. Siasatan</td>
    <td>:</td>
    <td>$list.NO_SIASATAN</td>
  </tr>
   <tr>
    <td></td>
    <td>Tempat Siasatan</td>
    <td>:</td>
    <td>$list.TEMPAT    </td>
  </tr>
 
 <tr>
    <td></td>
    <td>Alamat</td>
    <td>:</td>
    <td>
    $list.ALAMAT1    </td>
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$list.ALAMAT2</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>:</td>
    <td>$list.ALAMAT3</td>
  </tr>
  <tr>
    <td></td>
    <td>Poskod</td>
    <td>:</td>
    <td>$list.POSKOD    </td>
  </tr>
  <tr>
    <td></td>
    <td>Bandar</td>
    <td>:</td>
    <td>
    
     #if($list.ID_BANDAR=="")
     #set($BandarSiasatanB="")                             
     #else                             
     #foreach($lb in $list_bandar_all)      
     #if($list.ID_BANDAR==$lb.ID_BANDAR)                                      
     #set($BandarSiasatanB="$lb.KOD_BANDAR - $lb.NAMA_BANDAR")
     #end
     #end                            
     #end      
    $BandarSiasatanB    </td>
  </tr>
  <tr>
    <td></td>
    <td>Negeri</td>
    <td>:</td>
    <td>
    
     
     #if($list.ID_NEGERI=="")
     #set($NegeriSiasatanN="")                             
     #else                             
     #foreach($ln in $list_negeri)      
     #if($list.ID_NEGERI==$ln.ID_NEGERI)                                      
     #set($NegeriSiasatanN="$ln.KOD_NEGERI - $ln.NAMA_NEGERI")
     #end
     #end                            
     #end      
    $NegeriSiasatanN          </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Status Siasatan</td>
    <td>:</td>
    <td>
    
  
     #if($list.STATUS_SIASATAN=="")
     #set($StatusSiasatanS="")                             
     #else
     #if($list.STATUS_SIASATAN=="1")
     #set($StatusSiasatanS="SIASATAN PERMULAAN")    
     #elseif($list.STATUS_SIASATAN=="2") 
     #set($StatusSiasatanS="DITUNDA SEBELUM SIASATAN")    
     #elseif($list.STATUS_SIASATAN=="3") 
     #set($StatusSiasatanS="DIBATALKAN")    
     #elseif($list.STATUS_SIASATAN=="4") 
     #set($StatusSiasatanS="ULANG SIASATAN")    
     #elseif($list.STATUS_SIASATAN=="5") 
     #set($StatusSiasatanS="SAMBUNG SIASATAN")   
     
     #elseif($list.STATUS_SIASATAN=="7")                             
     #set($StatusSiasatanS="TANGGUH SIASATAN")
      
     #elseif($list.STATUS_SIASATAN=="6")                             
     #set($StatusSiasatanS="SELESAI")                
     #end 
     #end
          $StatusSiasatanS    </td>
  </tr>
  <tr>
    <td></td>
    <td>Bil. Siasatan</td>
    <td>:</td>
    <td>
    $list.BIL_SIASATAN        </td>
  </tr>
  <tr>
    <td></td>
    <td>Tarikh Siasatan</td>
    <td>:</td>
    <td>
    $list.TARIKH_SIASATAN        </td>
  </tr>
  <tr>
    <td></td>
    <td>Masa Siasatan</td>
    <td>:</td>
    <td>
                               
                                  #set($waktuW = "")
                                  #if( $list.JENIS_WAKTU_SIASATAN == 1)
                                  #set($waktuW = "PAGI")  
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 2)
                                  #set($waktuW = "TENGAHARI")                                   
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 3)
                                  #set($waktuW = "PETANG")      
                                  #elseif($list.JENIS_WAKTU_SIASATAN == 4)
                                  #set($waktuW = "MALAM")                                   
                                  #else
                                  #set($waktu = "")
                                  #end             
                                  
                               $list.MASA_SIASATAN  $waktuW                                  </td>
  </tr>
  <tr>
    <td valign="top"></td>
    <td>Tarikh Akhir Tampal Notis Awam</td>
    <td valign="top">:</td>
    <td>    
   
$list.TARIKH_AKHIR_TAMPAL     </td>
  </tr>
  <tr>
    <td valign="top"></td>
    <td valign="top">Alasan Tangguh / Batal</td>
    <td valign="top">:</td>
    <td><div style="width:60%">$list.ALASAN_TANGGUH</div></td>
  </tr>
</table>
</fieldset>
 
 </td>
 </tr>

  <tr class="$row">
    <td>$list.BIL</td>
    <td >
     <div align="left"><a href="javascript:ShowPopupSiasatan('$list.BILTEMP');" title="Klik untuk maklumat lengkap"><font color="blue">$list.NO_KES</font></a></div>     </td>
    <td> $list.NO_LOT</td>
    <td><div align="center">$list.BIL_SIASATAN</div></td>
    <td>
    
     
         
      <div align="center">#if( $list.JENIS_WAKTU_SIASATAN == 1) 
        #set($waktuS = "PAGI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 2) 
        #set($waktuS = "TENGAHARI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 3) 
        #set($waktuS = "PETANG") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 4) 
        #set($waktuS = "MALAM") 
        #else #set($waktuS = "") 
        #end
        
    $list.TARIKH_SIASATAN    $list.MASA_SIASATAN  $waktuS </div>  
        <!--
        
      <div align="center">
      
      
      #if( $list.JENIS_WAKTU_SIASATAN == 1) 
        #set($waktuS = "PAGI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 2) 
        #set($waktuS = "TENGAHARI") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 3) 
        #set($waktuS = "PETANG") 
        #elseif($list.JENIS_WAKTU_SIASATAN == 4) 
        #set($waktuS = "MALAM") 
        #else #set($waktuS = "") 
        #end
        
        $list.MASA_SIASATAN  $waktuS </div>
        
         -->
        </td>
    <td>$list.TEMPAT</td>
    <td>
    
    #if($list.STATUS_SIASATAN =="1") 
    #set($StatusSiasatanS="SIASATAN PERMULAAN") 
    #elseif($list.STATUS_SIASATAN =="2") 
    #set($StatusSiasatanS="DITUNDA SEBELUM SIASATAN") 
    #elseif($list.STATUS_SIASATAN =="3") 
    #set($StatusSiasatanS="DIBATALKAN") 
    #elseif($list.STATUS_SIASATAN =="4") 
    #set($StatusSiasatanS="ULANG SIASATAN") 
    #elseif($list.STATUS_SIASATAN =="5") 
    #set($StatusSiasatanS="SAMBUNG SIASATAN") 
    
    #elseif($list.STATUS_SIASATAN=="7")                             
    #set($StatusSiasatanS="TANGGUH SIASATAN")
    
    #elseif($list.STATUS_SIASATAN =="6") 
    #set($StatusSiasatanS="SELESAI") 
    #else
    #set($StatusSiasatanS="") 
    #end 
    
    $!StatusSiasatanS    
    
   
    
    </td>
    
  
      </tr>
   #end
  #else
  <tr>  
    <td colspan="8">Tiada Rekod</td>    
  </tr>
  #end
</table>

</fieldset>
</td>
</tr>














<!--
<tr>
<td>
<br />
<fieldset><legend>Senarai Siasatan</legend>

<input name="" type="button" value="Tambah">
<input name="" type="button" value="Papar">
<input name="" type="button" value="Hapus">

<table width="100%">
  <tr class="table_header">
  	<td width="9%">Bil. </td>
    <td width="9%">No. Kes </td>
    <td width="16%">No. Siasatan</td>
    <td width="22%">Tarikh Siasatan</td>
    <td  width="22%">Masa Siasatan</td>
    <td  width="20%">Tempat Siasatan</td>
    <td  width="11%">Status</td>
  </tr>
  
 
  <tr class="$row">
    <td height="21">$list.BIL</td>
    <td height="21">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</fieldset>
</td>
</tr>
-->
</table>
<!-- yati buat condition utk control view online siasatan dan internal -->
#if($modul == "ekptg.view.ppt.FrmSiasatanOnline")
  <input type="hidden" name="sub_command" id="sub_command" />
   <input type="hidden" name="subminor_command" id="subminor_command" /> 
   #end
   <input type="hidden" name="ScreenLocation" id="ScreenLocation" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$!id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
  <input type="hidden" name="id_tanahumum" id="id_tanahumum" value="$!id_tanahumum" />
   <input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />
  
  <script type="text/javascript">

var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{

if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}



}


function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function cari_lot(id_permohonan)
{
	/*
	hp = document.getElementById(field);
	if(hp!=null){
	hp.style.fontWeight = 'bolder';
	hp.style.fontStyle = 'italic';
	window.location.hash=field;
	goTo(field);
	}*/
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.paging.value = "16";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}
function cari_lot1(id_permohonan)
{
	document.${formName}.CariLot.value="";
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.paging.value = "16";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}

function ShowPopup(hoveritem,tab)
{
//alert("hp.style.display :"+hp.style.display+";hp.style.visibility :"+hp.style.visibility);
hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}




function UrusanSiasatanSingle(id_hakmilik,id_pembatalan)
{


    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";
    document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
	

}


function ShowPopupSiasatan(tab)
{

hp = document.getElementById(tab);
//hp.style.display=="none" &&
	if( hp.style.visibility=="collapse" && hp.style.display=="none"){
		hp.style.display = "block";
		hp.style.visibility = "visible";
	}
	//hp.style.display=="block" &&
	else if( hp.style.visibility=="visible" && hp.style.display=="block"){
		hp.style.display = "none";
		hp.style.visibility = "collapse";
	}
	

}

function papar(id_siasatan,id_hakmilik)
{
	alert("masuk");
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}

function papar1(id_siasatan,id_hakmilik)
{
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "RecordSiasatan";
	document.${formName}.subminor_command.value = "Papar";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSiasatanOnline";
	document.${formName}.id_siasatan.value = id_siasatan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();
	
}
function kehadiran(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Kehadiran";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "senarai_siasatan";
	document.${formName}.point.value = "senarai_siasatan";
	document.${formName}.submit();

}

function maklumatsiasatan(id_siasatan)
{

	
	document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "TuanTanah";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.id_siasatan.value = id_siasatan;	
	document.${formName}.location.value = "maklumat_siasatan";
	document.${formName}.point.value = "maklumat_siasatan";
	document.${formName}.submit();

}

function screen5(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Siasatan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Siasatan";
	document.${formName}.submit();
}


</script>
