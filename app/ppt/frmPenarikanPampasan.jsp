
#parse("app/ppt/paging_penarikanbalik.jsp")


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
             #set($id_hakmilik = $list.ID_HAKMILIK)        
         
             #end   
                               
                               
                               <tr >  
                <td colspan="7"> 
                
                #set($txtnorujukan = "")
#set($txtnamanegeri = "")
#set($txtnamadaerah = "")
#set($txtnamajajahan = "")
#set($STATUS_LAPORAN = "")

#foreach($listmaklumat in $maklumat_hakmilik)
#set($id_hakmilik = $listmaklumat.ID_HAKMILIK)
#set($txtnorujukan = $listmaklumat.NO_PT)
#set($txtnamanegeri = $listmaklumat.NAMA_NEGERI)
#set($txtnamadaerah = $listmaklumat.NAMA_DAERAH)
#set($txtnamajajahan = $listmaklumat.NAMA_MUKIM)
#set($STATUS_BAYAR = $listmaklumat.STATUS_BAYAR)
#end

              

 <fieldset id="maklumat_hakmilik">
 <legend>Maklumat Hakmilik</legend>
 <table width="100%" id="$list.BIL"   > 
 <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Lot</td>
    <td width="1%">:</td>
    <td width="70%">$txtnorujukan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Negeri</td>
    <td>:</td>
    <td>$txtnamanegeri</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Daerah/Jajahan</td>
    <td>:</td>
    <td>$txtnamadaerah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bandar/Pekan/Mukim</td>
    <td>:</td>
    <td>$txtnamajajahan</td>
  </tr>
   
  <tr>
    <td>&nbsp;</td>
    <td>Status Penerimaan Bayaran Pampasan</td>
    <td>:</td>
    <td>$STATUS_BAYAR</td>
  </tr>
   #if($STATUS_BAYAR != "SELESAI")
   <tr>
    <td colspan="4"><div>
      <div align="center">
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Selesai Penerimaan Bayaran Daripada Agensi"  onClick="selesai_bayar('$id_hakmilik','$id_pembatalan')">
      </div>
    </div></td>
  
  </tr>
  #end

</table>
 </fieldset>
 </td>
 </tr>
                               
                            
                               
                              
  
  <tr>
    <td>
    
    <fieldset id="senarai">
    <legend>Senarai Pihak Berkepentingan</legend>
    <div>
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Senarai Lot"  onClick="senaraiHakmilik('$id_permohonan','$id_pembatalan')">
      </div>
      <div><a href="javascript:setTable('carian')" id="link_cari"><font color="blue">Carian Pihak Berkepentingan</font></a></div>
    
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
  <tr style="display:none">
    <td></td>
    <td>No. KP Lama</td>
    <td>:</td>
    <td> <input name="txtNoKPLama" type="text" id="txtNoKPLama" size="30" maxlength="12" value="$!txtNoKPLama" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
   <tr  style="display:none">
    <td></td>
    <td>No. Lot</td>
    <td>:</td>
    <td> <input name="txtNoLot" type="text" id="txtNoLot" size="30" maxlength="12" value="$!txtNoLot" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" ></td>
  </tr>
  
    <tr  style="display:none">
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
        <option value="">SILA PILIH MUKIM</option>
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
    <!--
    <div>
    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
    </div>
    -->
    <fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
       
      <!--
        <tr>
        <td><a href="#" class="style2" onClick="PangilTerimaPampasanPB_PB_All('$id_fail','$id_pembatalan','$user_name')"><font color="blue">Surat Makluman Kepada Pihak Berkepentingan untuk hadir menerima cek pampasan</font></a></td>
      </tr> 
     
       <tr>
        <td><a href="#" class="style2" onClick="SuratMaklumanSerahPampasanAP_PB('$id_fail','')"><font color="blue">Surat Makluman Serah Bayar Pampasan Kepada Agensi Pemohon</font></a></td>
      </tr>   
       -->
      
      <tr>
        <td><a href="#" class="style2" onClick="SuratSuruhAPBayar('$id_fail','$id_pembatalan')"><font color="blue">Surat Mohon Agensi Pemohon Bayar Pampasan</font></a></td>
      </tr>     
      
      
      
       
    </table>
</fieldset>
    
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
                      <td width="15%"  align="right">Jumlah Pampasan (RM)</td>
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
  <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" />
  <input type="hidden" name="id_mmkkeputusan" id="id_mmkkeputusan" value="$!id_mmkkeputusan" />
  <input type="hidden" name="id_warta" id="id_warta" value="$!id_warta" />  
  <input type="hidden" name="list_name" id="list_name" value="list_pampasan" />
  <input type="hidden" name="buka_cari" id="buka_cari" value="$!buka_cari"  />
  <input type="hidden" name="id_pb" id="id_pb" value="$!id_pb"  />
  <input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb"  />
  
   <span id="check_pampasan"></span>

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
document.${formName}.command.value = "Pampasan";
document.${formName}.sub_command.value = "SenaraiPB";
document.${formName}.subminor_command.value = "View";	
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
document.${formName}.command.value = "Pampasan";
document.${formName}.sub_command.value = "SenaraiPB";
document.${formName}.subminor_command.value = "View";
document.${formName}.location.value = "link_cari";
document.${formName}.point.value = "link_cari";
document.${formName}.submit();

}

function tuntutan(id_hakmilikpb,id_permohonan,id_pembatalan)
{
  
	document.${formName}.id_hakmilikpb.value = id_hakmilikpb;	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;	

	
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "check_pampasan";
	   target = 'check_pampasan';
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	
	
	
}


function tuntutanX(id_hakmilikpb,id_permohonan,id_pembatalan)
{
             //    alert("XX");
				
				 document.${formName}.command.value = 'Pampasan';  
				// document.${formName}.sub_command.value = 'Tuntutan'; 
				document.${formName}.sub_command.value = 'Penerimaan_Check'; 
				
				 document.${formName}.subminor_command.value = 'View';	
				 document.${formName}.location.value = 'paging'; 
				 document.${formName}.point.value = 'paging'; 
				 document.${formName}.id_hakmilikpb.value = id_hakmilikpb; 
				 document.${formName}.id_permohonan.value = id_permohonan;
				 document.${formName}.id_pembatalan.value = id_pembatalan;
				 document.${formName}.action = ''; 
				 document.${formName}.submit(); 
}


function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		window.location.hash=id;
        goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

	function PangilTerimaPampasanPB_PB_All(id_fail,id_penarikan,nama_pegawai)
	{
    var url = "../servlet/ekptg.report.ppt.PangilTerimaPampasanPB_PB_All?id_fail="+id_fail+"&id_penarikan="+id_penarikan+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}
	
	function SuratMaklumanSerahPampasanAP_PB(id_fail,nama_pegawai)
    {
	/*
    var url = "../servlet/ekptg.report.ppt.SuratMaklumanSerahPampasanAP_PB?id_fail="+id_fail+"&nama_pegawai="+nama_pegawai;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_fail="+id_fail+"&nama_pegawai="+nama_pegawai+"&report=surat_maklum_bayar_AP"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
	}
	
	SuratSuruhAPBayar(id_fail,id_penarikan)
	{
	
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPenarikanBalik?id_fail="+id_fail+"&id_siasatan="+id_siasatan+"&nama_pegawai="+nama_pegawai+"&report=surat_suruh_AP_Bayar"; 
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
	}
	
	function senaraiHakmilik(id_permohonan,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}
	
	


function selesai_bayar(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "SenaraiPB";
	document.${formName}.subminor_command.value = "UpdateStatus";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

	
	
  </script>
