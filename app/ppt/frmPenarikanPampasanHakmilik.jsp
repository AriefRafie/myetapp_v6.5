

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

#parse("app/ppt/paging_penarikanbalik.jsp")

<table width="100%">
  <tr>
    <td>#parse("app/ppt/header_ppt.jsp")</td>
  </tr>
  
   #if($maklumat_pembatalan.size() > 0)
  #end
  
  
  <tr>
    <td><fieldset>
    <legend>SENARAI LOT PENARIKAN BALIK</legend>
    <table width="100%">
      <tr>
        <td>
             <table width="100%">
              <tr>              
              <td colspan="8">
              
              <table width="100%" >
              <tr>
              <td width="1%">              </td>
              <td align="left" width="59%" valign="top">
              DAERAH : $!nama_daerah              </td>
              <td width="40%" align="right" valign="top">
                #if($senarai_hakmilik_batal.size()!=0)
              No. Lot/PT : 
              
                    <label>
                    <input type="text" name="CariLot" id="CariLot"  />
                    </label>
                 
                      <label>
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Cari" onClick="cari_lot(CariLot.value,'$hakmilik_belumbatal')" />
                      <input type="button" name="ButtonCariLot" id="ButtonCariLot" value="Kosongkan" onClick="cari_lot1(CariLot.value)"  />
                      </label>
                     
                      #if($readmode == "edit")
                      <div id="jumlahlot"  ></div>
                      #end
                  #end                  </td>
              </tr>
              </table>             </td>
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
                      <td width="15%">No. Lot/PT</td>
                      <td width="15%">Hakmilik atau Pendudukan</td>
                      <td width="24%">Bandar/Pekan/Mukim</td>                 
                   
                      <td width="20%">Lebih Kurang Luas yang Hendak Ditarik Balik</td>
                      <td width="23%" >Status Penerimaan Bayaran Pampasan</td>
                      
                                        </tr>
                  
                   #if($senarai_hakmilik_batal.size()!=0)
                   
                    #set($tab_lot = 0)
             #foreach($list in $senarai_hakmilik_batal)        
       
             #set($tab_lot_tarik = $tab_lot+"TAB")
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
                               <tr >  
                <td colspan="8"> 
              
<fieldset id="$list.BIL"  style="visibility:collapse; display:none;">
 <table width="100%" class="$row"   > 
 
 
  #if($jenis_permohonan == "4") 
  <tr>
    <td width="1%"></td>
    <td colspan="3"><a href="javascript:senaraiPB('$list.ID_HAKMILIK','$!id_pembatalan')"><font color="blue">SENARAI PENERIMA PAMPASAN</font></a></td>    
 </tr> 
 #end
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
    <td>No. Hakmilik</td>
    <td>:</td>
    <td> $list.NO_HAKMILIK</td>
  </tr>
  
  
 
  <tr>
    <td>&nbsp;</td>
    <td>Luas Lot</td>
    <td>:</td>
    <td>$list.LUAS_LOT</td>
  </tr>
  <tr style="display:none">
    <td>&nbsp;</td>
    <td>Luas Diambil</td>
    <td>:</td>
    <td>$list.LUAS_AMBIL</td>
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
<fieldset id='$tab_lot_tarik' style="display:none;" >
<table style="background-color:#FFFFFF"  width="100%" >
<tr>
<td width="1%"></td>
<td width="28%">Jenis Luas</td>
<td width="1%"><div align="center">:</div></td>
<td width="70%">      
<!-- checking_validation(this,'socJenisLuas_check','yes','jenis luas','drop'); --> 
    <select name="socJenisLuas" class="autoselect" id="socJenisLuas"  onchange="pilih_jenis_luas(this.value,'$tab_lot','$senarai_hakmilik_batal.size()');check_lot_tarik()">    
    <option value="">SILA PILIH JENIS LUAS</option>        
    #foreach($ln in $list_jenisluas)   
    <option value="$ln.ID_LUAS">$ln.KOD_LUAS - $ln.KETERANGAN</option>
    #end     
    </select>
    #set($id_jenisluas = "$list.ID_PENARIKANHAKMILIK"+"JENISLUAS")
 <div id='$id_jenisluas' style="color:red; background:#FFFFFF; " ></div></td>
</tr>

#set($tr_luasharta = $tab_lot+"tr_luasharta")
#set($luas1 = $tab_lot+"luas1")
#set($luas2 = $tab_lot+"luas2")
#set($luas3 = $tab_lot+"luas3")
#set($tr_luasharta_b = $tab_lot+"tr_luasharta_b")
<!-- txtLuasAsalHtaam1   -->            
                              
                                              <tr id='$tr_luasharta' style="display:none;">
                                              <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td>
                                              <span id='$luas1' style="display:none;">
                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1');check_lot_tarik()"   />
                                              </span>
                                              <span id='$luas2' style="display:none;">
                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2');check_lot_tarik()"   />
                                               </span>
                                               <span id='$luas3' style="display:none;">
                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3"  size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3');check_lot_tarik()"  />
                                                </span>                                             
                                             <span id='$tr_luasharta_b' style="display:none;">                                             
                                               #set($id_convertbutton = "$list.ID_PENARIKANHAKMILIK"+"BUTTON")
                                                <input type="button" name='$id_convertbutton' id='$id_convertbutton' style="display:none"  value="Convert" onClick="ConvertLuasHartaPenarikan('$tab_lot','$senarai_hakmilik_batal.size()','$list.ID_KATEGORITANAH');check_lot_tarik()" />
                                             </span>
                                             #set($id_luasasal = "$list.ID_PENARIKANHAKMILIK"+"LUASASAL")
 												<div id='$id_luasasal' style="color:red; background:#FFFFFF; " ></div>                                             </td>
                                            </tr>
<tr>
<td width="1%"></td>
<td width="28%">Luas Penarikan Lot</td>
<td width="1%">:</td>
<td width="70%"><input name="lot_tarik" id="lot_tarik" type="text" onBlur="validateTarikh(this,this.value);check_lot_tarik()" onKeyUp="validateTarikh(this,this.value);check_lot_tarik()"   value="$list.LUAS_LOT_TARIK_VALUE" size="30" maxlength="10"  >
#if($list.ID_KATEGORITANAH != "")
#if($list.ID_KATEGORITANAH == "2")
#set($meterhektar = "HEKTAR")
<input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
#else
#set($meterhektar = "METERPERSEGI")
<input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
#end
#end
#set($id_alertlot = "$list.ID_PENARIKANHAKMILIK"+"TARIK")                      
<span id='$id_alertlot' style="color:red;"  ></span></td>
</tr>
</table>
</fieldset>                </td> 
                </tr>
                
                
                    <tr id="$list.BIL_DUM" class="$row" >
                      <td  >$list.BIL</td>
                      <td id="$list.NO_PT" >
                         <a class="style1" id="hoverover" style="cursor:default; color:#0000FF" onClick="ShowPopup(this,$list.BIL);" title="Klik untuk maklumat lengkap">$list.NO_PT</a>                      </td>
                      <td >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
                <!--
                      <td >
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
                      <select name=""  style="width:100%;" >
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      
                      #set( $ix = $velocityCount )
                      #if ( ($ix % 2) != 1 )
              		  #set( $rowx = "row2" )
         		      #else
               		  #set( $rowx = "row1" )
         		      #end
                      <option   >
                      $list1.NAMA_PB
                      </option>
                      #end
                      #end
                      </select>
                      #else
                      
                      #end
                   ---------
                      #foreach($list1 in $senarai_pihak_penting)
                      #if($list1.ID_HAKMILIK == $list.ID_HAKMILIK )
                      #set($count_total=$count_total + 1)
                      
                      #if($count > 1 && $count != $count_total) 
                      $list1.NAMA_PB,
                      #elseif($count > 1 && $count == $count_total)
                      $list1.NAMA_PB
                      #elseif($count == 1)
                      $list1.NAMA_PB
                      #end
                                          
                      #end
                      #end  
                  ---------   
                      </td>
                      -->
                      <td >$list.NAMA_MUKIM</td>
                  <!--    <td >$list.LUAS_LOT</td> -->
                    
                           <td >  
                             $list.LUAS_LOT_TARIK
                           #if($readmode == "view")
                           
                      
                    
                      <input name="lot_tarik" id="lot_tarik"  style="width:100%;display:none;" value="$list.LUAS_LOT_TARIK_EDIT" type="hidden" onBlur="validateTarikh(this,this.value);check_lot_tarik()" onKeyUp="validateTarikh(this,this.value);check_lot_tarik()"  >
                       <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_PENARIKANHAKMILIK" type="hidden" >
                      #else
                    
                       <input name="id_lot_tarik" id="id_lot_tarik"  style="width:100%" value="$list.ID_PENARIKANHAKMILIK" type="hidden" >
                      
                      #end   </td>
                       <td >  
                             $list.STATUS_BAYAR
                             </td>
                       
                           #set($tab_lot = $tab_lot + 1)                    </tr>
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
             </td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
</table>



<input type="hidden" name="sub_command" id="sub_command" />
<input type="hidden" name="subminor_command" id="subminor_command" />
<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />
<input type="hidden" name="id_pembatalan" id="id_pembatalan" value="$id_pembatalan" />
<input type="hidden" name="screen_name" id="screen_name" value="s2" />
<input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
<input type="hidden" name="alert_message" id="alert_message" />
<input type="hidden" name="jumlah_dipilih" id="jumlah_dipilih" />
<input type="hidden" name="jumlah_semua" id="jumlah_semua" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" />


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

</script>


#if($jenis_permohonan == "4")


<script>
function papar(id_permohonan,id_pembatalan)
{
	document.${formName}.command.value = "senarai";
	document.${formName}.sub_command.value = "papar";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.location.value = "senarai_penarikan";
	document.${formName}.point.value = "senarai_penarikan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}
function kembali_senarai_depan()
{
	document.${formName}.command.value = "";
	document.${formName}.sub_command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function PembatalanAmbilTanahPT()
{
	document.${formName}.command.value = "PembatalanAmbilTanahPT";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahPT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_lot";
	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}

function PembatalanAmbilTanahLotUnit()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "view_PembatalanAmbilTanahLotUnit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();

}

function tambahDokumen()
{

	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_dokumen";
	document.${formName}.point.value = "txtnamadokumen";	
	document.${formName}.submit();
}

function paparDokumen()
{
	document.${formName}.command.value = "Dokumen";
	document.${formName}.sub_command.value = "papar_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}

function hapusDokumen()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "hapus_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "senarai_dokumen";
	document.${formName}.point.value = "senarai_dokumen";
	document.${formName}.submit();
	}
}

function kemaskini()
{
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
}


function simpan()
{
var c = 0;

if(document.${formName}.validation_field != null  )
{

   if (document.${formName}.validation_field.length == null)
   {	
    
   if(document.${formName}.validation_field.value == "invalid")
   {  
   
   c++;	
   } 
   	
   } 
   
   else 
   {
   
        for (i = 0; i < document.${formName}.validation_field.length; i++)
		{		
			if(document.${formName}.validation_field[i].value == "invalid")
			{
			
               c++;	 
			}  	
        }
    }	
}


if(c>0){
alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
return;
}
else
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
	}
}

function hapus()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "hapus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
}

function batal()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "maklumat_tambahan";
	document.${formName}.point.value = "maklumat_tambahan";
	document.${formName}.submit();
	}
}

function batalkan_lot()
{
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.command.value = "PembatalanAmbilTanahLotUnit";
	document.${formName}.sub_command.value = "batalkan_lot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.location.value = "lot_dibatal";
	document.${formName}.point.value = "lot_dibatal";
	document.${formName}.submit();
	}
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
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function view_Lampiran(id_dokumen) {
    var id_pembatalan = document.${formName}.id_pembatalan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=Dokumen&sub_command=view_Dokumen_Details&id_pembatalan="+id_pembatalan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function jana_surat()
{
    document.${formName}.command.value = "Maklumat_Tambahan";
	document.${formName}.sub_command.value = "view_maklumat_tambahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
//	document.${formName}.location.value = "senarai_lot";
//	document.${formName}.point.value = "senarai_lot";
	document.${formName}.submit();
}

</script>

#end




<script>

function ReadOnly_CheckBox(v)
{
return false;
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



function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 1;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
	//	  DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");	  
	   /*	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   }
	   else
	   {
	    $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini");	  
	   /*	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+"");
		 /*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	 
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	$jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan tarikh "+value_field+" dengan betul");
		/*
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	*/	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	/*
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	*/
	   }
	   else
	   {
	   $jquery("#"+point).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   /*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}



function cari_lot(field,jl)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'bolder';
hp.style.fontStyle = 'italic';
window.location.hash=field;
goTo(field);
}
}
function cari_lot1(field)
{
hp = document.getElementById(field);
if(hp!=null)
{
hp.style.fontWeight = 'normal';
hp.style.fontStyle = 'normal';
}
document.${formName}.CariLot.value="";
}




function doCheckAll(jumlah_lot,belumbatal,sudahbatal){ 
	var valid = "yes"
	var c = 0; 
	var p = 0; 	 
	var jl = jumlah_lot;
	var bb = belumbatal;
	var sb = sudahbatal;
	
	if(jl == 0)
	{
	 $jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat");
	
	/*
	 document.${formName}.alert_message.value  = "Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	}
	else
	{
	
    if (document.${formName}.all.checked == true){
        if (document.${formName}.ids.length == null){		
             document.${formName}.ids.checked = true;
			   document.getElementById(0+"TAB").style.display="none";	 	 
	         document.${formName}.lot_tarik.value="";
			 c++;	 	
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){		
               document.${formName}.ids[i].checked = true;	
			    document.getElementById(i+"TAB").style.display="none";	 	 
	           document.${formName}.lot_tarik[i].value="";
			   c++;	   	
        }
		}	
		
	   document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;	
	   
	   $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+c+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");
	   /*  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	 
		
	  if(c == jl)
	  {	
	   
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  valid = "no";	
	  }
	  else if((parseInt(c)+parseInt(bb)) == jl )
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
      valid = "no";	
	  }
	  else if(c < jl && c != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
	  
	  else if(c == 0 && sb == 0 && jl > 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  valid = "no";	
	  }
	  
	  else if(c == 0 && sb > 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  
			
			
        		
		
    } else {
        if (document.${formName}.ids.length == null){		
            document.${formName}.ids.checked = false;
			  document.getElementById(0+"TAB").style.display="block";	 	 
	      
			p++;			
        } else {
            for (i = 0; i < document.${formName}.ids.length; i++){			
                document.${formName}.ids[i].checked = false;
				  document.getElementById(i+"TAB").style.display="block";	
				p++;			
            }
		}
		
	   document.${formName}.jumlah_dipilih.value  = 0;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;	
	   $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+0+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");
	   /*  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);		
		*/	
			
		 if(p == jl)
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  else if(p < jl && p != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
			
      
    }
	
	if(valid == "no")
	{
	 $jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan");
	 /*
	   document.${formName}.alert_message.value  = "Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	}
	else
	{
	 $jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	/*
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */
	}
	 
	}
	

	
	
	
}



function doUpdateCheckAll(jumlah_lot,belumbatal,sudahbatal){ 

var valid = "";
var c = 0;
var semua = 0;
var jl = jumlah_lot;
var bb = belumbatal;
var sb = sudahbatal;

//alert("jumlah_lot:"+jl+";belumbatal:"+bb+";sudahbatal:"+sb);

if(jl == 0)
	{
	$jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat");
	/*
	 document.${formName}.alert_message.value  = "Sila pastikan pasti permohonan mempunyai senarai lot sebelum pembatalan sebahagian atau keseluruhan dibuat";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */	
	}
	else
	{

if(document.${formName}.ids.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids.length; i++)
	  {
	  semua++;
      if (document.${formName}.ids[i].checked == true)
	  {	 
	  c++
	
	  document.getElementById(i+"TAB").style.display="none";	 	 
	  document.${formName}.lot_tarik[i].value="";
      }
	  else
	  {	 
	  document.getElementById(i+"TAB").style.display="block"; 	 
      }
	  }  
}
else
{
	semua = 1;
	if (document.${formName}.ids.checked == true)
	{	 
	c++;
	document.getElementById(0+"TAB").style.display="none";	 
	document.${formName}.lot_tarik.value="";	
	}
	else
	{	
	
	document.getElementById(0+"TAB").style.display="block";  

    } 	 	
}	 
 
 	$jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+c+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");
	/*
       document.${formName}.jumlah_dipilih.value  = c;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   */

	  if(c==semua)
	  {	  
	  document.${formName}.all.checked = true;	
	  }
	  else
	  {
	  document.${formName}.all.checked = false;	
	  }
	  
	   
	   if(c == jl)
	  {	
	   
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  valid = "no";
	  }
	  else if((parseInt(c)+parseInt(bb)) == jl )
	  {	
	 
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  
	  valid = "no";
	
	  }
	  
	  else if(c < jl && c != 0)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }
	  
	  else if(c == 0 && sb == 0 && jl > 0 )
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = false; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	  
	  valid = "no";
	
	  }
	  
	  else if(c == 0 && sb > 0 && sb < jl)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = false;
	  document.${formName}.sorJenisPembatalan[0].checked = true; 
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = false;
	  document.${formName}.sorJenisPembatalanD[0].checked = true; 
	
	  }

	  
	  else if(c == 0 && sb == jl)
	  {	
	  document.${formName}.sorJenisPembatalan[1].checked = true;
	  document.${formName}.sorJenisPembatalan[0].checked = false;
	  
	  document.${formName}.sorJenisPembatalanD[1].checked = true;
	  document.${formName}.sorJenisPembatalanD[0].checked = false; 
	
	  }
	  
	  
	  if( valid == "no")
	  {
	  $jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan");
	  /*
	   document.${formName}.alert_message.value  = "Sila pastikan pilihan penarikan balik adalah sebahagian atau keseluruhan";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);*/
	   }
	   else
	   {
	    $jquery("#sorJenisPembatalan_check").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
		/*
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "checking_validation";
	   target = "sorJenisPembatalan_check";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   */
	   }	
	
	   }
	   
}

function urusan_pampasan(id_hakmilik)

{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "Senarai";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_hakmilik";
	document.${formName}.point.value = "maklumat_hakmilik";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function LaporanTanah(id_hakmilik,id_pembatalan)
{
	document.${formName}.command.value = "Laporan_Tanah";
	document.${formName}.sub_command.value = "Maklumat_Am";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "maklumat_am";
	document.${formName}.point.value = "maklumat_am";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "";
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

function check_lot_tarik()
{


	  if(document.${formName}.id_lot_tarik.length > 1)
      {     
	  for (i = 0; i < document.${formName}.id_lot_tarik.length; i++)
	  {
	
      if (document.${formName}.lot_tarik[i].value == "" && document.${formName}.ids[i].checked == false)
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila masukkan luas lot");
	  /* 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	  
      }
	  
	  else if(((parseFloat(document.${formName}.lot_tarik[i].value) - parseFloat(document.${formName}.lot_tarik_temp[i].value))  > parseFloat(document.${formName}.lot_ambil[i].value)) && document.${formName}.ids[i].checked == false)
	  {	
	    $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Sila pastikan luas lot kurang daripada luas ambil");
	  /* 
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	 
      }
	  
	  else if((parseFloat(document.${formName}.lot_tarik[i].value) <= 0) && document.${formName}.ids[i].checked == false)
	  {
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas lot lebih daripada 0");
	  /*	
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		    
      }
	  else 
	  {	 
	   $jquery("#"+document.${formName}.id_lot_tarik[i].value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   /*
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   
      }  
	  
	  }  
}
else
{

//alert("LALAL"+document.${formName}.ids.checked );
	if (document.${formName}.lot_tarik.value == "" && document.${formName}.ids.checked == false)
	  {	
	   $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan luas lot");
	   /* 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	  
      }
	  else if(((parseFloat(document.${formName}.lot_tarik.value) - parseFloat(document.${formName}.lot_tarik_temp.value))  > parseFloat(document.${formName}.lot_ambil.value)) && document.${formName}.ids.checked == false)  {
	  
	   $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas lot kurang daripada luas ambil");
	   /*	 
	       document.${formName}.alert_message.value  = "Sila pastikan luas lot kurang daripada luas ambil";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		   	 
      }
	  
	  else if((parseFloat(document.${formName}.lot_tarik.value) <= 0) && document.${formName}.ids.checked == false)
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila pastikan luas lot lebih daripada 0");
	  /*
	 	   document.${formName}.alert_message.value  = "Sila pastikan luas lot lebih daripada 0";	 
	       url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   */
		    
      }
	  else 
	  {	 
	 $jquery("#"+document.${formName}.id_lot_tarik.value+"TARIK").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	 /*
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"TARIK";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	
		   */ 
      } 	
}	 
 
 
 
 
 
 
  if(document.${formName}.id_lot_tarik.length > 1)
      {	
	/// cini, nnti kasi alert
	
	
      var c_alert = 0;	  
	  
	  for (i = 0; i < document.${formName}.socJenisLuas.length; i++)
	  {	
	 
	  var val = document.${formName}.socJenisLuas[i].value;	
	  
	 
      if ((val != "" && val != "0") && document.${formName}.ids[i].checked == false)
	  {	 
	 
	    if(val == 9)
		{		
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 		
		}
		else if(val == 7)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 4)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "" || document.${formName}.txtLuasAsalHtaam3[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}  
		}
		else if(val == 2)
		{
			
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}   
		}
		else if(val == 5)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}     
		}
		else if(val == 3)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 6)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}  
		}
		else if(val == 1)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			}    
		}
		
		else if(val == 8)
		{
			if(document.${formName}.txtLuasAsalHtaam1[i].value == "" || document.${formName}.txtLuasAsalHtaam2[i].value == "" || document.${formName}.txtLuasAsalHtaam3[i].value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik[i].value+"BUTTON").style.display="block";
			} 
		} 	
		
	
      }
	  if (c_alert > 0)
	  {
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan luas lot awal");
	  /*	 
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot awal";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */	   	  
      }
	   else 
	  {	
	  $jquery("#"+document.${formName}.id_lot_tarik[i].value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");/* 	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik[i].value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	 
		   */
      }
	   	
	  }
	  }
	  

	// else if(document.${formName}.socJenisLuas.length == 1)
	  else
      {	
	 
	  var c_alert = 0;
	
	  var val = document.${formName}.socJenisLuas.value;	
	 
      if ((val != "" && val != "0") && document.${formName}.ids.checked == false)
	  {	 
	 	   
	    if(val == 9)
		{		
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  		
		}
		else if(val == 7)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 4)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "" || document.${formName}.txtLuasAsalHtaam3.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 2)
		{
			
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}    
		}
		else if(val == 5)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}     
		}
		else if(val == 3)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}   
		}
		else if(val == 6)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			} 
		}
		else if(val == 1)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "")	
					{
					c_alert ++;
					document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  
		}
		
		else if(val == 8)
		{
			if(document.${formName}.txtLuasAsalHtaam1.value == "" || document.${formName}.txtLuasAsalHtaam2.value == "" || document.${formName}.txtLuasAsalHtaam3.value == "")	
			{
			c_alert ++;
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="none";
			}
			else
			{
			document.getElementById(document.${formName}.id_lot_tarik.value+"BUTTON").style.display="block";
			}  
		}
		
  
      }
	  
	 
	  if (c_alert > 0)
	  {	 
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan luas lot awal");
	  /*
	 	   document.${formName}.alert_message.value  = "Sila masukkan luas lot awal";
		   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);	
		   */	   	  
      }
	   else 
	  {
	  $jquery("#"+document.${formName}.id_lot_tarik.value+"LUASASAL").html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");/*	 	 
		   document.${formName}.alert_message.value  = "";	 
		   actionName = "checking_validation";
		   target = document.${formName}.id_lot_tarik.value+"LUASASAL";
		   doAjaxUpdater(document.${formName}, url, target, actionName);
		   document.${formName}.alert_message.value  = "";	*/ 
      } 	
	  
	  }
	  
	  	   
 
 
 
	  	   

}





function pilih_jenis_luas(jenisluas,index,jumlah_lot)
{

if(jumlah_lot>1)
{


  var val = jenisluas;
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
 
    document.${formName}.txtLuasAsalHtaam1[index].value= "";
    document.${formName}.txtLuasAsalHtaam2[index].value= "";
    document.${formName}.txtLuasAsalHtaam3[index].value= "";
    document.${formName}.lot_tarik[index].value= "";
  	document.getElementById(tr_luas).style.display="none";	
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";
	
	if(val == 9)
{

	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	

}

else if(val == 7)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 4)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}

else if(val == 2)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 5)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 3)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 6)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 1)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 8)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}


else
{
    document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

	
	
	
}
else if(jumlah_lot==1)	
{
  var val = jenisluas;
  var l1 = 0+"luas1";
  var l2 = 0+"luas2";
  var l3 = 0+"luas3";
  var tr_luas = 0+"tr_luasharta";
  var tr_luas_b = 0+"tr_luasharta_b";
    document.${formName}.txtLuasAsalHtaam1.value= "";
    document.${formName}.txtLuasAsalHtaam2.value= "";
    document.${formName}.txtLuasAsalHtaam3.value= "";
    document.${formName}.lot_tarik.value= "";
  	document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	
	
	if(val == 9)
{

	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
	

}

else if(val == 7)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 4)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}

else if(val == 2)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 5)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 3)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 6)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 1)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

else if(val == 8)
{
	document.getElementById(tr_luas).style.display="";
	document.getElementById(tr_luas_b).style.display="";
	document.getElementById(l1).style.display="";  
	document.getElementById(l2).style.display="";  
	document.getElementById(l3).style.display="";  
}


else
{
    document.getElementById(tr_luas).style.display="none";
	document.getElementById(tr_luas_b).style.display="none";
	document.getElementById(l1).style.display="none";  
	document.getElementById(l2).style.display="none";  
	document.getElementById(l3).style.display="none";  
}

	
}



}







function ConvertLuasHartaPenarikan(index,jumlah_lot,kategory)
{

if(jumlah_lot>1)
{


  var val = document.${formName}.socJenisLuas[index].value;
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
	
    if (val=="2"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
              
		if(a=="")
		{
		
		return
		} 
		else
		{
	    var num = parseFloat(a) * 10000; // hektar to meter persegi
		var num1 = parseFloat(a) * 1;   //hektar to hektar         	   
        if(kategory=="2") 
        {       
	//	document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.${formName}.lot_tarik[index].value=num1;	 
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        }		
		}
		     
		
	}
	
	  else if (val=="5"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;   			
		if(a=="")
		{
	
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar             
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	   
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        } 		 
		}
	}
	
	
	 else if (val=="1"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
			
        if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 1000000;  // kilo to meter      
        var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    		
        }
        else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	   
        } 
		
		}
		     
		
	}
	
	else if (val=="3"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar         
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        } 
		
		}
		     
		
	}



// batu nautika, xjumpa lagi
else if (val=="9"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;      
        
		if(a=="")
		{
		
		return
		} 
		else
		{   
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;       
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        }		
		}
	}
	
	// ekar perpuluhan xjumpa lagi
		else if (val=="6"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
       
        
		if(a=="")
		{
		
		return
		} 
		else
		{
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;          
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    		
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")               
        {
        document.${formName}.lot_tarik[index].value=num;	    
        } 		
		}		
	}

	
	
		else if (val=="7"){      
		var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
          
		if(a=="")
		{
			
		return
		}  
		else if(b=="")
		{
				
		return
		}         
		else
		{ 
		
		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;            
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;
        } 		
		}
	}
	
	
else if (val=="8"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
        var c = document.${formName}.txtLuasAsalHtaam3[index].value;
              
		if(a=="")
		{
			
		return
		} 
		else if(b=="")
		{
			
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{  
	    var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;        
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        } 		
		}
		     
		
	}
	 //formula bawah dah dibetolkan
else if (val=="4"){
        var a = document.${formName}.txtLuasAsalHtaam1[index].value;
		var b = document.${formName}.txtLuasAsalHtaam2[index].value;
        var c = document.${formName}.txtLuasAsalHtaam3[index].value;
       
		
        
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
		
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{  
		var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;        
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik[index].value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik[index].value=num;	    
        } 		
		}	     
		
	}
    

}


if(jumlah_lot==1)
{


  var val = document.${formName}.socJenisLuas.value;
  var l1 = index+"luas1";
  var l2 = index+"luas2";
  var l3 = index+"luas3";
  var tr_luas = index+"tr_luasharta";
  var tr_luas_b = index+"tr_luasharta_b";
	
    if (val=="2"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
                  
		if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 10000; // hektar to meter persegi
		var num1 = parseFloat(a) * 1;   //hektar to hektar   	   
        if(kategory=="2") 
        {       
	//	document.getElementById(luasconvert).value=num1.toFixed(4);
	    document.${formName}.lot_tarik.value=num1;	 
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        }		
		}
		     
		
	}
	
	  else if (val=="5"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;   
	   		
		if(a=="")
		{
		
		return
		} 
		else
		{  
		var num = parseFloat(a) * 0.09290304; // kaki persegi to meter persegi
		var num1 = parseFloat(a) * 0.000009290304; // kaki persegi to hektar            
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	   
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        } 		 
		}
	}
	
	
	 else if (val=="1"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		
		 
		
		
        if(a=="")
		{
		return
		} 
		else
		{  
		var num = parseFloat(a) * 1000000;  // kilo to meter      
        var num1 = parseFloat(a) * 100; // kilo meter to hektar        
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    		
        }
        else if(kategory=="1" || kategory=="3" || kategory =="4" || kategory =="5" || kategory =="6")        
        {
        document.${formName}.lot_tarik.value=num;	   
        } 
		
		}
		     
		
	}
	
	else if (val=="3"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
       
        
		if(a=="")
		{
		
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 0.0001;  //meter persegi to hektar        
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        } 
		
		}
		     
		
	}



// cari formula, bawah ni hentam dulu
	else if (val=="9"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{         
		var num = parseFloat(a) * 1; //meter persegi to meter persegi
		var num1 = parseFloat(a) * 1;  //meter persegi to hektar
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        } 
		
		}
		     
		
	}
	
	
		else if (val=="6"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
        
        
		if(a=="")
		{
		
		return
		} 
		else
		{ 
		var num = parseFloat(a) * 1; 
		var num1 = parseFloat(a) * 1;         
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    		
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")               
        {
        document.${formName}.lot_tarik.value=num;	    
        } 		
		}		
	}

	
	//formula bawah ni hentam dulu
		else if (val=="7"){      
		var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
              
		if(a=="")
		{
			
		return
		}  
		else if(b=="")
		{
				
		return
		}         
		else
		{   
		var num = (parseFloat(a) + (parseFloat(b)/1000))*4046.86; 
		var num1 =  (parseFloat(a) + (parseFloat(b)/1000))*0.404686;       
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;
        } 		
		}
	}
	
else if (val=="8"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
        var c = document.${formName}.txtLuasAsalHtaam3.value;
               
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
		
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{ 
		var num = (parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976))*0.711111*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/484) + (parseFloat(c)/30976)))*0.711111*0.404686;       
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        } 		
		}
		     
		
	}
	 //formula bawah ni hentam dulu
else if (val=="4"){
        var a = document.${formName}.txtLuasAsalHtaam1.value;
		var b = document.${formName}.txtLuasAsalHtaam2.value;
        var c = document.${formName}.txtLuasAsalHtaam3.value;
        
		if(a=="")
		{
		
		return
		} 
		else if(b=="")
		{
			
		return
		} 
		else if(c=="")
		{
		
		return
		}        
		else
		{
		var num = (parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160))*4046.86; 
		var num1 = ((parseFloat(a) + (parseFloat(b)/4) + (parseFloat(c)/160)))*0.404686;           
        if(kategory=="2") 
        {       
		document.${formName}.lot_tarik.value=num1;	    	
        }
        else if(kategory=="1" || kategory=="3" || kategory=="4" || kategory=="5" || kategory=="6")        
        {
        document.${formName}.lot_tarik.value=num;	    
        } 		
		}	     
		
	}
    

}

}



function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
 maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}


function senaraiPB(id_hakmilik,id_pembatalan)
{
    document.${formName}.command.value = "Pampasan";
	document.${formName}.sub_command.value = "SenaraiPB";
	document.${formName}.subminor_command.value = "View";
	document.${formName}.location.value = "paging";
	document.${formName}.point.value = "paging";	
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_pembatalan.value = id_pembatalan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal";
	document.${formName}.submit();
}


</script>
