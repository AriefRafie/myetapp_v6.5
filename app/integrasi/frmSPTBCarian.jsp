#if ($deleteSPTB == 'true')
<div class="success">Data telah berjaya dihapuskan.</div>
<br />
#end
<fieldset>
  <legend><strong>CARIAN MAKLUMAT PEMOHON</strong>
  </legend><table width="100%">
   <tr>
      <td align="right" valign="top" scope="row">Seksyen</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">
 
   <!-- PPT ROLE
   (PPT)KetuaPenolongPengarah
   (PPT)KetuaPenolongPengarahUnit
   (PPT)PelukisPelan
   (PPT)PembantuTadbirSekyenPengambilanTanah
   (PPT)PembantuTadbirUnitPengambilanTanah
   (PPT)Pengarah
   (PPT)PengarahTanahdanGalian
   (PPT)PengarahUnit
   (PPT)PenghantarNotis
   (PPT)PenolongPegawai TanahSeksyenPengambilanTanah
   (PPT)PenolongPegawaiTanahUnitPengambilanTanah
   (PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT17)
   (PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT27)
   (PPT)PenolongPengarah
   (PPT)PenolongPengarahUnit
   (PPT)PentadbirTanahdanDaerah
   adminppt
    -->
   
   <!-- PPK ROLE
   adminppk
   user_ppk   
   -->
   
    <!-- SPTB ROLE
   sptb 
   -->
   
   
    
   
   
   
   
   
   
   
   
   
   
   
        #set($open_role = "")  
   
      
      	#if($_portal_role == "(PPT)KetuaPenolongPengarah" 
        || $_portal_role == "(PPT)KetuaPenolongPengarahUnit"
        || $_portal_role == "(PPT)PelukisPelan"
        || $_portal_role == "(PPT)PembantuTadbirSekyenPengambilanTanah"
        || $_portal_role == "(PPT)PembantuTadbirUnitPengambilanTanah"
        || $_portal_role == "(PPT)Pengarah"
        || $_portal_role == "(PPT)PengarahTanahdanGalian"
        || $_portal_role == "(PPT)PengarahUnit"
        || $_portal_role == "(PPT)PenghantarNotis"
        || $_portal_role == "(PPT)PenolongPegawai TanahSeksyenPengambilanTanah"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT17)"
        || $_portal_role == "(PPT)PenolongPegawaiTanahUnitPengambilanTanah(NT27)"
        || $_portal_role == "(PPT)PenolongPengarah"
        || $_portal_role == "(PPT)PenolongPengarahUnit"
        || $_portal_role == "(PPT)PentadbirTanahdanDaerah"
        || $_portal_role == "adminppt")
        #set($open_role = "PPT_ROLE")
        #end
        
        #if($_portal_role == "adminppk" || $_portal_role == "user_ppk")
        #set($open_role = "PPK_ROLE")
        #end
        
        #if($_portal_role == "sptb")
        #set($open_role = "SPTB_ROLE")
        #end
        
        #if($_portal_role == "adminint")
        #set($open_role = "INT_ROLE")
        #end
    
      
     #if($_portal_role == "adminint" || $_portal_role == "sptb") 
     #if ($READONLY_JKPTG == ' ') 
       <input name="seksyen" type="text" id="seksyen" value="$!seksyen" $READONLY_JKPTG />     
     #else     
           <select name="seksyen" id="seksyen" style="width:320"  $READONLY_JKPTG   >                                  
                                  #if( $seksyen == 1)                                  
                                  <option value="1"  >SEKSYEN PENGAMBILAN TANAH</option>
                                  <option value="2" >SEKSYEN PEMBAHAGIAN PESAKA KECIL</option>                                                            
                                  <option value="">SILA PILIH</option>                                    
                                  #elseif($seksyen == 2)
                                  <option value="2" >SEKSYEN PEMBAHAGIAN PESAKA KECIL</option>
                                  <option value="1" >SEKSYEN PENGAMBILAN TANAH</option>
                                  <option value="">SILA PILIH</option>                                  
                                  #else
                                  <option value="">SILA PILIH</option>                                 
                                  <option value="1"  >SEKSYEN PENGAMBILAN TANAH</option>                                 
                                  <option value="2"  >SEKSYEN PEMBAHAGIAN PESAKA KECIL</option>                                                                  
                                  #end 
                                  </select>
      #end
      #elseif($open_role == "PPT_ROLE")
       <select name="seksyen" id="seksyen" style="width:320"     > 
       <option value="1"  >SEKSYEN PENGAMBILAN TANAH</option>      
       </select>
       #elseif($open_role == "PPK_ROLE")
       <select name="seksyen" id="seksyen" style="width:320"     > 
       <option value="1"  >SEKSYEN PEMBAHAGIAN PESAKA KECIL</option>      
       </select>
       #else
       
      #end
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Fail</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Permohonan</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_NoPermohonan" type="text" id="Carian_NoPermohonan" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoPermohonan" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row">No Hakmilik</td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_Nohakmilik" type="text" id="Carian_Nohakmilik" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_Nohakmilik" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row">No Lot</td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_Nolot" type="text" id="Carian_Nolot" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_Nolot" size="50" maxlength="255" $READONLY_JKPTG /></td>
    </tr>
#if ($READONLY_JKPTG == '')    
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
        <input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchSPTB();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptySPTB();" />
      </td>
    </tr>
#end    
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI DATA</strong></legend>
#if ($READONLY_JKPTG == '')  
	#parse("app/utils/record_paging.jsp")
#end
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="10%"><strong>NO_HAKMILIK</strong></td>
      <td width="10%"><strong>NO LOT</strong></td>
      <td width="20%"><strong>DAERAH</strong></td>
      <td width="20%"><strong>BANDAR/PEKAN/MUKIM</strong></td>
    </tr>
#set ($list = '')

#if($ListPermohonan.size()>0)
#foreach ($list in $ListPermohonan)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
    #if ($list.No != '') 
    <tr>
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewSPTB('$list.ID_FAIL','$list.ID_HAKMILIK','$list.ID_SEKSYEN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top">$list.NO_PERMOHONAN</td>
      <td class="$row" valign="top">$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
      <td class="$row" valign="top">$list.NO_LOT</td>
       <td class="$row" valign="top">$list.NAMA_DAERAH</td>
        <td class="$row" valign="top">$list.NAMA_MUKIM</td>
    </tr> 
    
    #end
   
#end

#else
    <tr>
      <td colspan="7"  style="text-align:center">Tiada Rekod</td>
      </tr>
      
      #end
    
  </table>
</fieldset>
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" />
<input type="hidden" id="ID_FAIL" name="ID_FAIL" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script type="text/javascript">
  function viewSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {	
      document.${formName}.action = "?action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function searchSPTB() {
      
	  if(document.${formName}.seksyen.value == '')
	  {
	  alert('Sila pastikan seksyen telah dipilih.');
		  document.${formName}.seksyen.focus();
		  return;
	  }
	  else
	  if (document.${formName}.Carian_NoFail.value == '' && document.${formName}.Carian_NoPermohonan.value == '' && document.${formName}.Carian_Nohakmilik.value == '' && document.${formName}.Carian_Nolot.value == '') {
		  alert('Sila pastikan salah satu medan carian diisi.');
		  document.${formName}.Carian_NoFail.focus();
		  return;
	  }
      document.${formName}.action = "?action2=searchSPTB";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function emptySPTB() {
      document.${formName}.action = "?action2=";
	  document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>