#parse("app/ppt/HadAksesOnlinePPT.jsp")
<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>




#set($txtSebabPembatalan = "")
#set($sorJenisPembatalan = "")
#set($txtNoRujSurat = "")
#set($txdTarikhTerimaSurat = "")
#set($txdTarikhSurat = "")
#set($txdTarikhPembatalan = "")
#set($txtNoPembatalan = "")
#set($id_pembatalan = "")

#foreach ( $mp in $maklumat_pembatalan )
#set($txtSebabPembatalan = $mp.SEBAB_PEMBATALAN)

#if($mp.JENIS_PEMBATALAN == 1)
#set($sorJenisPembatalan = "sebahagian")
#elseif($mp.JENIS_PEMBATALAN == 2)
#set($sorJenisPembatalan = "keseluruhan")
#else
#set($sorJenisPembatalan = "")
#end

#set($txtNoRujSurat = $mp.NO_RUJUKAN_SURAT)
#set($txdTarikhTerimaSurat = $mp.TARIKH_TERIMA_SURAT)
#set($txdTarikhSurat = $mp.TARIKH_SURAT)
#set($txdTarikhPembatalan = $mp.TARIKH_PEMBATALAN)
#set($txtNoPembatalan = $mp.NO_PEMBATALAN)
#set($id_pembatalan = $mp.ID_PEMBATALAN)
#end

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
<span style="display:none">#parse("app/ppt/paging_penarikanbalik.jsp")</span>
<table width="100%">
  <tr>
    <td>#parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  
  
  
  
  
  <tr>
  <td>
  <fieldset id="senarai_penarikan">

  #if($jenis_permohonan == "4" || $jenis_permohonan == "3")
  <legend>SENARAI PENARIKAN BALIK</legend>
  #end
  
  #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
  <legend>SENARAI PEMBATALAN</legend>
  #end
  
   #if($roleAgensi=="no")
	   #if($hakmilik_belumbatal != 0)
	   <input name="cmdTambahPenarikan" type="button" value="Tambah" onClick="tambahPenarikan()" >
	   #end
   #end
   <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali_senarai_depan()" >
  <table width="100%">
  
   #if($jenis_permohonan == "4" || $jenis_permohonan == "3")
    <tr class="table_header">
    <td width="5%"><div align="center"><b>Bil</b></div></td>
    <td width="20%" ><div align="left"><b>No. Penarikan Balik</b></div></td>
    <td width="15%"><div align="center"><b>Tarikh Terima Surat</b></div></td>
    <td width="15%"><div align="center"><b>Tarik Penarikan Balik</b></div></td>
    <td width="20%"><div align="center"><b>Jenis Penarikan Balik</b></div></td> 
    <td width="25%"><div align="center">
  
		 <b>Status Penarikan Balik</b>
        
    </div></td>  
  </tr>
 
  #end
  
  #if($jenis_permohonan == "2" || $jenis_permohonan == "1")
  
   <tr class="table_header">
    <td width="5%"><div align="center"><b>Bil</b></div></td>
    <td  width="20%" ><div align="left"><b>No. Pembatalan</b></div></td>
    <td width="15%"><div align="center"><b>Tarikh Terima Surat</b></div></td>
    <td width="15%"><div align="center"><b>Tarik Pembatalan</b></div></td>
    <td width="25%"><div align="center"><b>Jenis Pembatalan</b></div></td>  
    <td width="25%"><div align="center"><b>Status Pembatalan</b></div></td>  
  </tr>
  
  #end
  
 
  
    #if($senarai_pembatalan.size() > 0)
  #foreach ( $mp in $senarai_pembatalan )
   #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  <tr class="$row">
    <td ><div align="center">$mp.BIL</div></td>
    <td><div align="left">
   
    <a href="javascript:PembatalanAmbilTanahPT('$mp.ID_PEMBATALAN')"><font color="blue">$mp.NO_PEMBATALAN</font></a>
    
    </div></td>
    <td><div align="center">$mp.TARIKH_TERIMA_SURAT</div></td>
    <td><div align="center">$mp.TARIKH_PEMBATALAN</div></td>
    <td>
      <div align="center">#if($mp.JENIS_PEMBATALAN == 1)
        #set($sorJenisPembatalan = "SEBAHAGIAN LOT")
        #elseif($mp.JENIS_PEMBATALAN == 2)
        #set($sorJenisPembatalan = "KESELURUHAN LOT")
        #else
        #set($sorJenisPembatalan = "")
        #end 
        
        $sorJenisPembatalan        </div></td>
        <td><div align="center">
        #set($flag_online = $mp.FLAG_ONLINE )
        
        
        <span style="color:#0000FF">#if($flag_online == "1")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PENDAFTARAN PERMOHONAN ONLINE (PENARIKAN BALIK)
         #else
         PENDAFTARAN PERMOHONAN ONLINE (PEMBATALAN)
         #end
         #elseif($flag_online == "2")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) TELAH DIHANTAR KE JKPTG      
         #else
         PERMOHONAN ONLINE (PEMBATALAN) TELAH DIHANTAR KE JKPTG      
         #end            
         #elseif($flag_online == "3")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) TELAH DISAHKAN OLEH JKPTG    
         #else
         PERMOHONAN ONLINE (PEMBATALAN) TELAH DISAHKAN OLEH JKPTG    
         #end 
         #elseif($flag_online == "4")
         #if($jenis_permohonan == "4" || $jenis_permohonan == "3") 
		 PERMOHONAN ONLINE (PENARIKAN BALIK) DITOLAK   
         #else
         PERMOHONAN ONLINE (PEMBATALAN) DITOLAK       
         #end 
         #end </span>
        
        </div></td>
  
 
  </tr>
  #end
  #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
</table>

  </fieldset>
  </td>
  </tr>

</table>

<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="screen_name" id="screen_name" value="s1" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
<input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
<input type="hidden" name="alert_message" id="alert_message" />
<input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
<input type="hidden" name="jumlah_semua" id="jumlah_semua" />
<input type="hidden" name="flag_MyInfoPPT" id="flag_MyInfoPPT" value="$flag_MyInfoPPT" />



<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue">Surat Makluman Kepada Agensi Pemohon</font></a></td>
      </tr>           
    </table>
</fieldset>



<script type="text/javascript">




var readmode = '$readmode';
var jenis_permohonan = '$jenis_permohonan';
window.onload = submitForm;

function submitForm() 
{


if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{

var a = '$location';
var b = '$point';
window.location.hash=a;
goTo(b);

}
else
{
window.location.hash='paging';
goTo('paging');

}



}

</script>







#if($jenis_permohonan == "4")
<script>

function kembali_senarai_depan()
{
//flag_MyInfoPPT
//alert('$flag_MyInfoPPT');

if('$flag_MyInfoPPT' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT&no_fail=$no_fail";
document.${formName}.method="POST";
}
else if('$flag_UtilitiKemaskiniFail' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail&txtNoFail=$no_fail";
document.${formName}.method="POST";
}
else
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
}
	document.${formName}.submit();
}
function tambahPenarikan()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";	
	document.${formName}.submit();
}
function PembatalanAmbilTanahPT(id_pembatalan)
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


</script>

#end


#if($jenis_permohonan == "3")
<script>

function kembali_senarai_depan()
{
//flag_MyInfoPPT
//alert('$flag_MyInfoPPT');

if('$flag_MyInfoPPT' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT&no_fail=$no_fail";
document.${formName}.method="POST";
}
else if('$flag_UtilitiKemaskiniFail' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail&txtNoFail=$no_fail";
document.${formName}.method="POST";
}
else
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
}
	document.${formName}.submit();
}
function tambahPenarikan()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";	
	document.${formName}.submit();
}
function PembatalanAmbilTanahPT(id_pembatalan)
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


</script>

#end



#if($jenis_permohonan == "2")
<script>

function kembali_senarai_depan()
{
/*
	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.submit();*/
	
if('$flag_MyInfoPPT' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT&no_fail=$no_fail";
document.${formName}.method="POST";
}
else if('$flag_UtilitiKemaskiniFail' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail&txtNoFail=$no_fail";
document.${formName}.method="POST";
}
else
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
}
	document.${formName}.submit();
	
}
function tambahPenarikan()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";	
	document.${formName}.submit();
}
function PembatalanAmbilTanahPT(id_pembatalan)
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


</script>

#end


#if($jenis_permohonan == "1")
<script>

function kembali_senarai_depan()
{
/*
	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.submit();*/
	
if('$flag_MyInfoPPT' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppt.MyInfoPPT&no_fail=$no_fail";
document.${formName}.method="POST";
}
else if('$flag_UtilitiKemaskiniFail' == "yes")
{
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail&txtNoFail=$no_fail";
document.${formName}.method="POST";
}
else
{

	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
}
	document.${formName}.submit();
	
}
function tambahPenarikan()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";	
	document.${formName}.submit();
}
function PembatalanAmbilTanahPT(id_pembatalan)
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPembatalanInternalOnline";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.submit();
}


</script>

#end



<script>

function doCheckAll1(){    
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll1(){  
var c = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}	 	
}	 
 
	
	
	
	  if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
       
}





</script>



