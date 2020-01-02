
<!--#parse("app/ppt/paging_siasatan.jsp")-->


#if($readmode == "edit")
#set($disabledmode = "")
#set($readonlymode = "")
#elseif($readmode == "view")
#set($disabledmode = "disabled")
#set($readonlymode = "readonly")
#else
#set($disabledmode = "")
#set($readonlymode = "")
#end



<table width="100%">
  <tr>
    <td> #parse("app/ppt/header_ppt.jsp")</td>
  </tr>
 
 <tr>
 <td>
                  
             #foreach($list in $maklumat_hakmilik_pampasan)
             #set($listNO_PT = $list.NO_PT)
             #set($listNAMA_NEGERI = $list.NAMA_NEGERI)
             #set($listNAMA_DAERAH = $list.NAMA_DAERAH)
             #set($listNAMA_MUKIM = $list.NAMA_MUKIM)
             #set($listJENIS_HAKMILIK = $list.JENIS_HAKMILIK)
             #set($listKOD_JENIS_HAKMILIK = $list.KOD_JENIS_HAKMILIK)
             #set($listLUAS_LOT = $list.LUAS_LOT)
             #set($listLUAS_AMBIL = $list.LUAS_AMBIL)        
         
             #end   
                               <!--
                               
                               <tr >  
                <td colspan="7"> 
              

 <fieldset id="maklumat_hakmilik">
 <legend>Maklumat Hakmilik</legend>
 <table width="100%" id="$list.BIL"   > 
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Lot/PT</td>
    <td width="1%">:</td>
    <td width="70%">$listNO_PT</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td width="1%">:</td>
    <td width="70%">$listNAMA_NEGERI</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah</td>
    <td>:</td>
    <td>$listNAMA_DAERAH</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Mukim</td>
    <td>:</td>
    <td>$listNAMA_MUKIM</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Hakmilik</td>
    <td>:</td>
    <td>$listJENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Kod Hakmilik</td>
    <td>:</td>
    <td>$listKOD_JENIS_HAKMILIK</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$listLUAS_LOT</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$listLUAS_AMBIL</td>
  </tr>

</table>
 </fieldset>
 </td>
 </tr>
                               
                               -->
                               
                              
  
  <tr>
    <td>
    
    <fieldset id="senarai">
    <legend>Senarai Pihak Berkepentingan</legend>
    #if($senarai_pihak_penting_pampasan.size()!=0)
    <a href="javascript:setTable('carian')" id="link_cari"><font color="blue">Carian Pihak Berkepentingan</font></a>
    #end
    <table width="100%" >
     <tr>
    <td colspan="7">
    <fieldset id="carian"style="display:none;" >
   <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Nama</td>
    <td width="1%">:</td>
    <td width="70%"> <input name="txtNamaPB" type="text" id="txtNamaPB" size="50" maxlength="100" value="$!txtNamaPB" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>No. PB</td>
    <td>:</td>
    <td> <input name="txtNoPB" type="text" id="txtNoPB" size="30" maxlength="12" value="$!txtNoPB" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
  <tr>
    <td></td>
    <td>No. KP Lama</td>
    <td>:</td>
    <td> <input name="txtNoKPLama" type="text" id="txtNoKPLama" size="30" maxlength="12" value="$!txtNoKPLama" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
   <tr>
    <td></td>
    <td>No. Lot</td>
    <td>:</td>
    <td> <input name="txtNoLot" type="text" id="txtNoLot" size="30" maxlength="12" value="$!txtNoLot" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
  
    <tr>
    <td>&nbsp;</td>
    <td><div align="left">Bandar/Pekan/Mukim</div></td>
    <td>:</td>
    <td>
      <label>
      
      <select name="socMukim" id="socMukim" class="autoselect">
      
      #if($!socMukim == "")
      <option value="">SILA PILIH MUKIM</option>
      #else
      #foreach($list_mukim_pampasan in $list_mukim_pampasan)
      #if($!socMukim == $list_mukim_pampasan.ID_MUKIM)    
      <option value="$list_mukim_pampasan.ID_MUKIM">$list_mukim_pampasan.KOD_MUKIM - $list_mukim_pampasan.NAMA_MUKIM</option>
      #end
      #end
      #end
        
       #foreach($list_mukim_pampasan in $list_mukim_pampasan)
       #if($!socMukim != $list_mukim_pampasan.ID_MUKIM)    
       <option value="$list_mukim_pampasan.ID_MUKIM">$list_mukim_pampasan.KOD_MUKIM - $list_mukim_pampasan.NAMA_MUKIM</option>
       #end       
       #end
       
       #if($!socMukim!="")
        <option value="">SILA PILIH KEMENTERIAN</option>
        #end
      </select>
      </label>       </td>
  </tr>
  
  <tr>
    <td colspan="4">
    <div align="center">
     
        <label>
        <input type="button" name="button2" id="button2" value="Cari" onClick="cari()">
        </label>
        <label>
          <input type="button" name="Button" id="button" value="Kosongkan" onClick="kosongkan()">
          </label>
      
    </div>    </td>
  </tr>
</table>

    </fieldset>
    </td>
    </tr>
    
    </table>
    
    
      #if($senarai_pihak_penting_pampasan.size()!=0)
    #parse("app/utils/record_paging.jsp")
    #end
    <table width="100%">
    
     
        
   
  
    <tr  >
                  <td >
                 
                  <table width="100%">
                    <tr class="table_header">
                      <td width="2%">Bil</td>
                      <td width="25%">Nama PB</td>
                      <td width="15%">No. PB</td>
                      
                      <td width="15%">No. Lot/PT</td>
                      <td width="18%">Bandar/Pekan/Mukim</td>
                      
                      <td width="10%">Bahagian</td>
                      <td width="15%"  align="right">Jumlah Pampasan</td>
                    </tr>
                  
              #if($senarai_pihak_penting_pampasan.size()!=0)
             #foreach($list in $senarai_pihak_penting_pampasan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
 
                
                
                    <tr >
                      <td class="$row">$list.BIL</td>
                      <td class="$row">
                      <a href="javascript:tuntutan('$list.ID_HAKMILIKPB','$id_permohonan','$id_pembatalan')" title="Maklumat Pampasan"><font color="blue">$list.NAMA_PB</font></a>                      </td>
                      <td class="$row">$list.NO_PB</td>
                      
                      <td class="$row">$list.NO_LOT</td>
                      <td class="$row">$list.NAMA_MUKIM</td>
                      
                      <td class="$row">
                      #if($list.SYER_ATAS!="" && $list.SYER_BAWAH!="")
                      $list.SYER_ATAS / $list.SYER_BAWAH
                      #else
                      
                      #end                      </td>
                      <td class="$row" align="right">
                      #if($list.BAYAR_PAMPASAN != "")
                      $Util.formatDecimal($list.BAYAR_PAMPASAN)
                      #end                      </td>
                    </tr>
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
    </fieldset>    </td>
  </tr>
  <!--
  <tr>
    <td colspan="2">
    <div align="center" >
   
      <label>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="PembatalanAmbilTanahLotUnit()">
      </label>
   
     </div>      </td>
  </tr>
  -->
</table>



  <input type="hidden" name="sub_command" id="sub_command" />
  <input type="hidden" name="subminor_command" id="subminor_command" />
  <input type="hidden" name="location" id="location" />
  <input type="hidden" name="point" id="point" />
  <input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$!id_pembatalan" />
  <input type="hidden" name="screen_name" id="screen_name" value="s2" />
  <input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
  <input type="hidden" name="alert_message" id="alert_message" />
  <input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
  <input type="hidden" name="jumlah_semua" id="jumlah_semua" />
  <input type="hidden" name="id_mmk" id="id_mmk" value="$!id_mmk" />
  <input type="hidden" name="id_mmkkeputusan" id="id_mmkkeputusan" value="$!id_mmkkeputusan" />
  <input type="hidden" name="id_warta" id="id_warta" value="$!id_warta" />  
  <input type="hidden" name="list_name" id="list_name" value="list_pampasan" />
  <input type="hidden" name="buka_cari" id="buka_cari" value="$!buka_cari"  />
  <input type="hidden" name="id_pb" id="id_pb" value="$!id_pb"  />
  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb"  />

<script type="text/javascript">

var readmode = '$readmode';
var buka_cari = '$buka_cari';
var jenis_permohonan = '$jenis_permohonan';

window.onload = submitForm;

   if(buka_cari == "buka")
	{
		document.getElementById('carian').style.display="block";	
			
	}
	else 
	{
		document.getElementById('carian').style.display="none";		
	}
	


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

	




function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		document.${formName}.buka_cari.value = "buka";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
		document.${formName}.buka_cari.value = "tutup";
	}
}

function cari()
{
document.${formName}.location.value = "senarai";
document.${formName}.point.value = "senarai";
document.${formName}.submit();
}

function kosongkan()
{
document.${formName}.txtNamaPB.value = "";
document.${formName}.txtNoPB.value = "";
document.${formName}.txtNoKPLama.value = "";
document.${formName}.txtNoLot.value = "";
document.${formName}.socMukim.value = "";
document.${formName}.location.value = "link_cari";
document.${formName}.point.value = "link_cari";
document.${formName}.submit();

}

function tuntutan(id_hakmilikpb,id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Tuntutan";
	document.${formName}.subminor_command.value = "View";	
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
	document.${formName}.submit();
}
  </script>
