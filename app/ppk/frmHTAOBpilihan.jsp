 
 
 <!--
 <tr>
 <td colspan="7">
 :::::::::::: $display_ob
 </td>
 </tr>
 -->
 
 

 
 <style>

 td.linebawah{

border-left:none;
border-right:none;
margin-bottom: 1px;
border-top:1px solid #C7A317;
}
 </style>
                                
         
                                        #set($set_no = 0)
                                        #foreach($xx in $listOBHTAdulu)                                      
                                        #if($xx.ID_HTA == $listam.idhta )
                                        #set($set_no = $set_no + 1)
                                        #end
                                        #end                                      
                                        #if($set_no > 0) </p>
<tr id="$display_ob" style="display:none" class="$row_color">
                                     <!-- <td colspan="10"  >
                                      :::::::::::::::::: $display_ob
                                      </td>
                                      </tr>
                                      <tr >-->
                                      <td colspan="10" >
                                  <fieldset>
                                      <table width="100%" border="0" >
                                     <!--
                                      #if($listam.ID_JENISPERINTAH != "2" && $!idPerintah != "")
                                         <tr class="$row_color">
                                         <td align="right">                                          
                                         </td>
                                         <td colspan="5">
                                         #set($flag_daftar = "flag_daftar" + $listam.idhta) 
                                         Jenis Pilihan Harta : &nbsp;                                         
                                               #set($flag_daftar_num = 0)
                                               #foreach($lo in $check_pilihan)     
                                               #if($lo.id_hta == $listam.idhta && $lo.id_permohonansimati == $idPermohonanSimati)
                                                    #if($lo.flag_daftar_perintah == "1")
                                                    #set($flag_daftar_num = 1)
                                                    #elseif($lo.flag_daftar_perintah == "2")
                                                    #set($flag_daftar_num = 2)
                                                    #else
                                                    #set($flag_daftar_num = 0)
                                                    #end 
                                               #end     
                                               #end                                          
                                          	
                                           #if($flag_daftar_num == 1)
                                           #set($pil_daftar1 = "checked" )
                                           #set($pil_daftar2 = "" )
                                           #elseif($flag_daftar_num == 2)
                                           #set($pil_daftar1 = "" )
                                           #set($pil_daftar2 = "checked" )
                                           #else
                                           #set($pil_daftar1 = "checked" )
                                           #set($pil_daftar2 = "" )
                                           #end                                      
                                        
                                         <input type="radio" name="$flag_daftar" id="$flag_daftar" $pil_daftar1   value="1"   />Batal Dan Bahagi
                                         <input type="radio" name="$flag_daftar" id="$flag_daftar" $pil_daftar2  value="2"   />Batal Dan Lantik
                                         </td>
                                         </tr>
                                       #else
                                       #set($flag_daftar = "flag_daftar" + $listam.idhta)   
                                       <input type="hidden" name="$flag_daftar" id="$flag_daftar"  value="" />
                                       #end  
                                         -->
                                      
                                      #set($flag_daftar = "flag_daftar" + $listam.idhta)   
                                       <input type="hidden" name="$flag_daftar" id="$flag_daftar"  value="" />
                                       
                                       
                                      <tr class="$row_color">
                                      <td align="right">
                                      <font color="red">*</font>
                                      </td>
                                      <td colspan="5">
                                      
                                      #if($listam.ID_JENISPERINTAH != "2" )
                                       Sila semak pada ruangan <input type="checkbox" disabled="disabled" /> bagi membatalkan <strong>Pentadbir</strong> atau <strong>Pemegang Amanah</strong>
                                       #else
                                        Sila semak pada ruangan <input type="checkbox" disabled="disabled" /> bagi membatalkan <strong>Pentadbir</strong>
                                                                         
                                      #end
                                      </td>
                                      </tr>
                                      
                                      
                                      
                                      #foreach($xx in $listOBHTAdulu)                                      
                                      #if($xx.ID_HTA == $listam.idhta && (($listam.ID_JENISPERINTAH != "2" && ($xx.STATUS_TADBIR == "Y" || $xx.STATUS_TADBIR == "T")) || ($listam.ID_JENISPERINTAH == "2"))   )
                                  <!--    
                                     :::  $xx.STATUS_TADBIR<br />
                                      $listam.ID_JENISPERINTAH
                                      <br />
                                     -->
                                      <tr>
                                      <td width="5%" valign="top">                                      
                                      </td>                                      
                                      <td width="5%" align="right" valign="top">
                                      Nama : &nbsp;
                                      </td>
                                      <td width="20%" align="left" valign="top">
                                      <b>$xx.NAMA_OB</b>
                                      
                                    
                                     
                                      #set($id_obdtl = "id_obdtl"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL) 
                                     
                                       <input type="hidden" name="$id_obdtl" id="$id_obdtl"  value="$xx.ID_PERINTAHHTAOBDTL" />
                                      </td>
                                      <td width="10%" align="right" valign="top">
                                      Bahagian : &nbsp;
                                      </td>
                                      <td width="15%" align="left" valign="top">
                                      $xx.BA / $xx.BB
                                       #set($bahagian_waris_atas = "bahagian_waris_atas"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL) 
                                       <input type="hidden" name="$bahagian_waris_atas" id="$bahagian_waris_atas"  value="$xx.BA" />
                                       
                                       #set($bahagian_waris_bawah = "bahagian_waris_bawah"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL) 
                                       <input type="hidden" name="$bahagian_waris_bawah" id="$bahagian_waris_bawah"  value="$xx.BB" />
                                       
                                       #set($id_waris_batal = "id_waris_batal"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL) 
                                       <input type="hidden" name="$id_waris_batal" id="$id_waris_batal"  value="$xx.ID_PERINTAHHTAOBDTL" />
                                      </td>
                                      #set($id_pilihanhta_temp = "")
                                       	  #set($pil_cek_ob = "" )                                                                           
                                          #set($main_ob = "main_ob" + $listam.idhta)   
                                                                            
                                          #set($tr_display = "tr_display"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL)
                                          
                                           
                                          #foreach($lo in $check_pilihan)    
                                          #if($lo.id_hta == $listam.idhta && $lo.id_permohonansimati == $id_permohonansimati)
                                          #set($id_pilihanhta_temp = $lo.id_pilihanhta)
                                          #end                                         
                                          #end
                                          #set($p_c_ob = 0)
                                          
                                        
                                          
                                          #foreach($lo in $check_pilihan_hta_ob)     
                                          #if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                          #set($p_c_ob = 1)
                                          #end                                         
                                          #end                                          
                                          #if($p_c_ob == 1)
                                          #set($pil_cek_ob = "checked" )
                                          #else
                                          #set($pil_cek_ob = "" )
                                          #end    
                                       <td width="5%" align="left" valign="top">
                                       <!--
                                          ID HTA :: $listam.idhta
                                          id_pilihanhta_temp :::: $id_pilihanhta_temp
                                          <BR />
                                           ob :::::::::  $xx.ID_OB <br />
                                     DTL :::::::::  $xx.ID_PERINTAHHTAOBDTL -->
                                       
                    				  <input type="checkbox" name="$main_ob" id="$main_ob" $pil_cek_ob  value="$xx.ID_PERINTAHHTAOBDTL"  onclick="setPilihanOB('$listam.idhta');setPilihanOB_KS_all('ob','$listam.idhta','$xx.ID_PERINTAHHTAOBDTL');" />
                                      
                                      </td>
                                      <td width="40%" align="left" valign="top">
                                      </td>
                                      </tr>
                                      
                                    
                                      
                                      
                                      
                                      
                                      
                                      
                                      #set($status_tb = "status_tb"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL)
                                      #if($xx.ID_PA1 != "" || $xx.ID_PA2 != "" || $xx.ID_PA3 != "" || $xx.ID_PA4 != "")
                                      <tr id="$tr_display" style="display:none">
                                      <!--
                                      <td colspan="5">
                                      :::::::::::::: $tr_display
                                      </td>
                                      </tr>
                                      
                                      <tr >-->
                                      <td></td>
                                      <td></td>
                                      <td colspan="2">
                                      
                                      
                                      <table width="100%" border="0">
                                      #if($listam.ID_JENISPERINTAH != "2" && $!idPerintah != "")
                                         <tr class="$row_color">
                                         
                                         <td colspan="5">
                                          #set($flag_daftar_ob = "flag_daftar_ob"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL)
                                                                                
                                               #set($flag_daftar_num_ob = 0)
                                               
                                               
                                                #foreach($lo in $check_pilihan_hta_ob)     
                                          		#if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                         		
                                                    #if($lo.flag_daftar_perintah == "1")
                                                    #set($flag_daftar_num_ob = 1)
                                                    #elseif($lo.flag_daftar_perintah == "2")
                                                    #set($flag_daftar_num_ob = 2)
                                                    #else
                                                    #set($flag_daftar_num_ob = 0)
                                                    #end 
                                                 #end
                                                 #end   
                                               
                                               
                                                                              
                                          	
                                           #if($flag_daftar_num_ob == 1)
                                           #set($pil_ob_daftar1 = "checked" )
                                           #set($pil_ob_daftar2 = "" )
                                           #elseif($flag_daftar_num_ob == 2)
                                           #set($pil_ob_daftar1 = "" )
                                           #set($pil_ob_daftar2 = "checked" )
                                           #else
                                           
                                           #if($xx.STATUS_OB == "2")
                                           #set($pil_ob_daftar1 = "" )
                                           #set($pil_ob_daftar2 = "checked" )
                                           #else
                                           #set($pil_ob_daftar1 = "checked" )
                                           #set($pil_ob_daftar2 = "" )
                                           #end                      
                                           
                                           
                                           #end
                                           
                                           
                                           #set($radio_dis  = "")
                                           #if($xx.STATUS_OB == "2")
                                           #set($radio_dis  = "disabled")                                        
                                           #end                      
                                           
                                     
                                        
                                         <input type="radio" name="$flag_daftar_ob" id="$flag_daftar_ob" $pil_ob_daftar1   value="1" $radio_dis   />Batal Dan Bahagi
                                         <input type="radio" name="$flag_daftar_ob" id="$flag_daftar_ob" $pil_ob_daftar2  value="2"   />Batal Dan Lantik
                                         </td>
                                         </tr>
                                           #else
                                            #set($flag_daftar_ob = "flag_daftar_ob"+ $listam.idhta + $xx.ID_PERINTAHHTAOBDTL) 
                                           <input type="hidden" name="$flag_daftar_ob" id="$flag_daftar_ob"  value="" />
                                           #end 
                                          </table>
                                      
                                      
                                      <table width="100%" border="0">
                                      
                                       
                                      
                                      <tr >
                                      
                                     
                                      <td width="80%">
                                  <!--  :::::::::: $status_tb
                                    <br />
                                    $xx.STATUS_TADBIR-->
                                       <input id="$status_tb" name="$status_tb" type="hidden" value="$xx.STATUS_TADBIR"  />
                                       #if($listam.ID_JENISPERINTAH != "2" )
                                       
                                       
                                              #if($xx.STATUS_TADBIR == "Y")
                                              <font color="blue"><b>Pentadbir</b></font>
                                              #elseif($xx.STATUS_TADBIR == "T")
                                               <font color="blue"><b>Pemegang Amanah</b></font>
                                              #else
                                              
                                              #end           
                                      
                                      #else
                                      
                                    
                                      #end
                                      
                                                                 </td>
                                      <td width="20%"></td>
                                   
                                      
                                      </tr>
                                      
                                      #if($xx.ID_PA1 != "")
                                      <tr >
                                      
                                      
                                      <td  width="80%">
                                     
                                          $xx.NAMA_PA1                             
                                      </td>
                                      
                                      <td width="20%">
                                      	  #set($p_c_ob_sub = "")
                                          #set($pil_cek_ob_sub = "" )
                                          #foreach($lo in $check_pilihan_hta_ob)     
                                          #if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                         <!-- 
                                          $xx.ID_PA1
                                          --------                                         
                                          $lo.id_pilihanha
                                          $lo.PA1
                                          $lo.PA2
                                          $lo.PA3
                                          $lo.PA4
                                          -->
                                          #if($lo.PA1 == $xx.ID_PA1 || $lo.PA2 == $xx.ID_PA1 || $lo.PA3 == $xx.ID_PA1 || $lo.PA4 == $xx.ID_PA1)
                                          #set($p_c_ob_sub = 1)
                                          #end
                                          
                                          #end                                         
                                          #end 
                                          
                                                                                   
                                          #if($p_c_ob_sub == 1)
                                          #set($pil_cek_ob_sub = "checked" )
                                          #else
                                          #set($pil_cek_ob_sub = "" )
                                          #end   
                                      
                                      #set($check_ob = "check_ob"+$xx.ID_HTA+$xx.ID_PERINTAHHTAOBDTL)
                                      <input type="checkbox" name="$check_ob" id="$check_ob" $pil_cek_ob_sub  value="$xx.ID_PA1" onclick="setPilihanOB_minor('$listam.idhta','$xx.ID_PERINTAHHTAOBDTL','$xx.ID_PA1')" />
                                      
                                      </td>
                                     <!-- doUpdateCheck_OBMain_sub('$xx.ID_PERINTAHHTAOBDTL','$listam.idhta','$xx.ID_PA1','$xx.STATUS_TADBIR') -->
                                      
                                      </tr>
                                      #end
                                      
                                      #if($xx.ID_PA2 != "")
                                      <tr >
                                      
                                      <td  width="80%">
                                          $xx.NAMA_PA2                            
                                      </td>
                                      
                                      <td width="20%">
                                          #set($p_c_ob_sub = "")
                                          #set($pil_cek_ob_sub = "" )
                                          #foreach($lo in $check_pilihan_hta_ob)     
                                          #if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                          
                                          #if($lo.PA1 == $xx.ID_PA2 || $lo.PA2 == $xx.ID_PA2 || $lo.PA3 == $xx.ID_PA2 || $lo.PA4 == $xx.ID_PA2)
                                          #set($p_c_ob_sub = 1)
                                          #end
                                          
                                          #end                                         
                                          #end 
                                                                                   
                                          #if($p_c_ob_sub == 1)
                                          #set($pil_cek_ob_sub = "checked" )
                                          #else
                                          #set($pil_cek_ob_sub = "" )
                                          #end  
                                          
                                      #set($check_ob = "check_ob"+$xx.ID_HTA+$xx.ID_PERINTAHHTAOBDTL)
                                      <input type="checkbox" name="$check_ob" id="$check_ob" $pil_cek_ob_sub  value="$xx.ID_PA2" onclick="setPilihanOB_minor('$listam.idhta','$xx.ID_PERINTAHHTAOBDTL','$xx.ID_PA2')" />
                                      
                                      </td>
                                      <!-- doUpdateCheck_OBMain_sub('$xx.ID_PERINTAHHTAOBDTL','$listam.idhta','$xx.ID_PA2','$xx.STATUS_TADBIR') -->
                                      
                                      </tr>
                                      #end
                                      
                                      #if($xx.ID_PA3 != "")
                                      <tr >
                                      
                                      <td  width="80%">
                                          $xx.NAMA_PA3                           
                                      </td>
                                      
                                      <td width="20%">
                                      #set($p_c_ob_sub = "")
                                          #set($pil_cek_ob_sub = "" )
                                          #foreach($lo in $check_pilihan_hta_ob)     
                                          #if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                          
                                          #if($lo.PA1 == $xx.ID_PA3 || $lo.PA2 == $xx.ID_PA3 || $lo.PA3 == $xx.ID_PA3 || $lo.PA4 == $xx.ID_PA3)
                                          #set($p_c_ob_sub = 1)
                                          #end
                                          
                                          #end                                         
                                          #end 
                                                                                   
                                          #if($p_c_ob_sub == 1)
                                          #set($pil_cek_ob_sub = "checked" )
                                          #else
                                          #set($pil_cek_ob_sub = "" )
                                          #end  
                                      #set($check_ob = "check_ob"+$xx.ID_HTA+$xx.ID_PERINTAHHTAOBDTL)
                                      <input type="checkbox" name="$check_ob" id="$check_ob" $pil_cek_ob_sub  value="$xx.ID_PA3" onclick="setPilihanOB_minor('$listam.idhta','$xx.ID_PERINTAHHTAOBDTL','$xx.ID_PA3')" />
                                      
                                      </td>
                                     <!--  doUpdateCheck_OBMain_sub('$xx.ID_PERINTAHHTAOBDTL','$listam.idhta','$xx.ID_PA3','$xx.STATUS_TADBIR') -->
                                      
                                      </tr>
                                      #end
                                      
                                      #if($xx.ID_PA4 != "")
                                      <tr >
                                      
                                      <td  width="80%">
                                          $xx.NAMA_PA4                           
                                      </td>
                                      
                                      <td width="20%">
                                       #set($p_c_ob_sub = "")
                                          #set($pil_cek_ob_sub = "" )
                                          #foreach($lo in $check_pilihan_hta_ob)     
                                          #if($lo.id_pilihanhta == $id_pilihanhta_temp  && $lo.ID_PERINTAHHTAOBDTL == $xx.ID_PERINTAHHTAOBDTL && $lo.id_permohonansimati == $id_permohonansimati)
                                          
                                          #if($lo.PA1 == $xx.ID_PA4 || $lo.PA2 == $xx.ID_PA4 || $lo.PA3 == $xx.ID_PA4 || $lo.PA4 == $xx.ID_PA4)
                                          #set($p_c_ob_sub = 1)
                                          #end
                                          
                                          #end                                         
                                          #end 
                                                                                   
                                          #if($p_c_ob_sub == 1)
                                          #set($pil_cek_ob_sub = "checked" )
                                          #else
                                          #set($pil_cek_ob_sub = "" )
                                          #end 
                                      #set($check_ob = "check_ob"+$xx.ID_HTA+$xx.ID_PERINTAHHTAOBDTL)
                                      <input type="checkbox" name="$check_ob" id="$check_ob" $pil_cek_ob_sub  value="$xx.ID_PA4" onclick="setPilihanOB_minor('$listam.idhta','$xx.ID_PERINTAHHTAOBDTL','$xx.ID_PA4')" />
                                      
                                      </td>
                                      <!-- doUpdateCheck_OBMain_sub('$xx.ID_PERINTAHHTAOBDTL','$listam.idhta','$xx.ID_PA4','$xx.STATUS_TADBIR') -->
                                      
                                      </tr>
                                      #end
                                      
                                       <tr >
                                      
                                      
                                     
                                      
                                      <td colspan="5" class="linebawah">&nbsp;</td>
                                    
                                      
                                      
                                      </tr>
                                      </table>
                                      
                                      </td>
                                      <td></td>
                                      <td></td>
                                      </tr>
                                      
                                      #end
                                       #if($p_c_ob == 1)                                         
                                          <script>
										   document.getElementById('$tr_display').style.display="";
										   </script>
                                          #else                                          
                                          <script>
										   document.getElementById('$tr_display').style.display="none";
										   </script>
                                          #end  
                                      
                                      
                                       
                                      #end
                                      #end
                                      
                                      
                                      
                                      </table>
                                   </fieldset> 
                                      </td>
                                      </tr>
                                      #end
                                      
                                 