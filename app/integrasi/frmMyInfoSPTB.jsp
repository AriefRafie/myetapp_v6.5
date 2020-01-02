

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




#if($open_role == "SPTB_ROLE" || $open_role == "INT_ROLE" || $open_role == "PPT_ROLE") 


<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN SPTB (PENGURUSAN PENGAMBILAN TANAH)</strong></legend>
  
  
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="10%"><strong>NO_HAKMILIK</strong></td>
      <td width="10%"><strong>NO LOT</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td width="10%" align="center"><div align="left"><strong>STATUS</strong></div></td>
    </tr>
#set ($list = '')

#if($ListSPTB.size()>0)

#foreach ($list in $ListSPTB)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
    #if ($list.No != '') 
    
    #if ($list.date30 == '1')
        	#set ($row_color = 'style="color:#FF0000"')
        #elseif ($list.date14 == '1')
        	#set ($row_color = 'style="color:#009900"')
        #elseif ($list.date05 == '1')
        	#set ($row_color = 'style="color:#0000FF"')
        #else
        	#set ($row_color = '')
      	#end
    
    
    <tr>
      <td class="$row" valign="top" align="center" $row_color >$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewSPTB('$list.ID_FAIL','$list.ID_HAKMILIK','$list.ID_SEKSYEN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top" $row_color >$list.NO_PERMOHONAN</td>
      <td class="$row" valign="top" $row_color >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
      <td class="$row" valign="top" $row_color >$list.NO_LOT</td>
       <td class="$row" valign="top" $row_color >$list.NAMA_DAERAH</td>
        <td class="$row" valign="top" $row_color >$list.NAMA_MUKIM</td>
         <td class="$row" valign="top" $row_color  >$list.Status</td>
    </tr> 
   
    
    #end
   
#end

    
    
    #else
    <tr>
      <td colspan="8"  style="text-align:center">Tiada Rekod</td>
    </tr>
    #end
  </table>
  
  
  
  
</fieldset>
#end

#if($open_role == "SPTB_ROLE" || $open_role == "INT_ROLE" || $open_role == "PPK_ROLE") 


<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN SPTB (PEMBAHAGIAN PUSAKA KECIL)</strong></legend>
  
  
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="10%"><strong>NO_HAKMILIK</strong></td>
      <td width="10%"><strong>NO LOT</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td width="10%" align="center"><div align="left"><strong>STATUS</strong></div></td>
    </tr>
#set ($list = '')

#if($ListSPTBPPK.size()>0)

#foreach ($list in $ListSPTBPPK)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
    #if ($list.No != '') 
    
    #if ($list.date30 == '1')
        	#set ($row_color = 'style="color:#FF0000"')
        #elseif ($list.date14 == '1')
        	#set ($row_color = 'style="color:#009900"')
        #elseif ($list.date05 == '1')
        	#set ($row_color = 'style="color:#0000FF"')
        #else
        	#set ($row_color = '')
      	#end
    
    
    <tr>
      <td class="$row" valign="top" align="center" $row_color >$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewSPTB('$list.ID_FAIL','$list.ID_HAKMILIK','$list.ID_SEKSYEN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top" $row_color >$list.NO_PERMOHONAN</td>
      <td class="$row" valign="top" $row_color >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
      <td class="$row" valign="top" $row_color >$list.NO_LOT</td>
       <td class="$row" valign="top" $row_color >$list.NAMA_DAERAH</td>
        <td class="$row" valign="top" $row_color >$list.NAMA_MUKIM</td>
         <td class="$row" valign="top" $row_color  >$list.Status</td>
    </tr> 
   
    
    #end
   
#end

    
    
    #else
    <tr>
      <td colspan="8"  style="text-align:center">Tiada Rekod</td>
    </tr>
    #end
  </table>
  
  
  
  
</fieldset>

#end


#if($open_role == "SPTB_ROLE" || $open_role == "INT_ROLE" || $open_role == "PPT_ROLE") 



#if ($isSPTBUser == 'false')
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN SPTB YANG TELAH SELESAI  (PENGURUSAN PENGAMBILAN TANAH)</strong></legend>
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="10%"><strong>NO_HAKMILIK</strong></td>
      <td width="10%"><strong>NO LOT</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td width="10%" align="center"><div align="left"><strong>STATUS</strong></div></td>
    </tr>
#set ($list = '')

#if($ListSPTBSelesai.size()>0)

#foreach ($list in $ListSPTBSelesai)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
    #if ($list.No != '') 
    
   #if ($list.Status.toUpper != 'SELESAI')
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
            #else
                #set ($row_color = '')
            #end
        #end
    
    
    <tr>
      <td class="$row" valign="top" align="center" $row_color >$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewSPTB('$list.ID_FAIL','$list.ID_HAKMILIK','$list.ID_SEKSYEN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top" $row_color >$list.NO_PERMOHONAN</td>
      <td class="$row" valign="top" $row_color >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
      <td class="$row" valign="top" $row_color >$list.NO_LOT</td>
       <td class="$row" valign="top" $row_color >$list.NAMA_DAERAH</td>
        <td class="$row" valign="top" $row_color >$list.NAMA_MUKIM</td>
         <td class="$row" valign="top" $row_color  >$list.Status</td>
    </tr> 
   
    #end
   
#end


 #else
    <tr>
      <td colspan="8"  style="text-align:center">Tiada Rekod</td>
      </tr>
      #end


  </table>
  
  
  
  
  
</fieldset>
#end

#end


#if($open_role == "SPTB_ROLE" || $open_role == "INT_ROLE" || $open_role == "PPK_ROLE") 

#if ($isSPTBUser == 'false')
<br />
<fieldset>
  <legend><strong>SENARAI FAIL BAGI SEMAKAN SPTB YANG TELAH SELESAI  (PEMBAHAGIAN PUSAKA KECIL)</strong></legend>
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="20%"><strong>NO FAIL</strong></td>
      <td width="15%"><strong>NO PERMOHONAN</strong></td>
      <td width="10%"><strong>NO_HAKMILIK</strong></td>
      <td width="10%"><strong>NO LOT</strong></td>
      <td width="15%"><strong>DAERAH</strong></td>
      <td width="15%"><strong>BANDAR/PEKAN/MUKIM</strong></td>
      <td width="10%" align="center"><div align="left"><strong>STATUS</strong></div></td>
    </tr>
#set ($list = '')

#if($ListSPTBSelesaiPPK.size()>0)

#foreach ($list in $ListSPTBSelesaiPPK)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    
    #if ($list.No != '') 
    
   #if ($list.Status.toUpper != 'SELESAI')
            #if ($list.date30 == '1')
                #set ($row_color = 'style="color:#FF0000"')
            #elseif ($list.date14 == '1')
                #set ($row_color = 'style="color:#009900"')
            #elseif ($list.date05 == '1')
                #set ($row_color = 'style="color:#0000FF"')
            #else
                #set ($row_color = '')
            #end
        #end
    
    
    <tr>
      <td class="$row" valign="top" align="center" $row_color >$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewSPTB('$list.ID_FAIL','$list.ID_HAKMILIK','$list.ID_SEKSYEN')" style="color:#0000FF">$list.NO_FAIL</a></td>
      <td class="$row" valign="top" $row_color >$list.NO_PERMOHONAN</td>
      <td class="$row" valign="top" $row_color >$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
      <td class="$row" valign="top" $row_color >$list.NO_LOT</td>
       <td class="$row" valign="top" $row_color >$list.NAMA_DAERAH</td>
        <td class="$row" valign="top" $row_color >$list.NAMA_MUKIM</td>
         <td class="$row" valign="top" $row_color  >$list.Status</td>
    </tr> 
   
    #end
   
#end


 #else
    <tr>
      <td colspan="8"  style="text-align:center">Tiada Rekod</td>
      </tr>
      #end


  </table>
  
  
  
  
  
</fieldset>
#end
#end

<input type="hidden" id="ID_PEMOHON" name="ID_PEMOHON" />
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK"  />
<input type="hidden" id="ID_FAIL" name="ID_FAIL"  />
<input type="hidden" id="ID_SEKSYEN" name="ID_SEKSYEN"  />

<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
 

function viewSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {
	document.${formName}.action = "$EkptgUtil.getTabID("SPTB",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewSPTB&action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	document.${formName}.submit();
}
</script>