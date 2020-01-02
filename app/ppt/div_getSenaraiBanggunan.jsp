

<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="5%">No</td>
        			<td width="65%">No./Jenis Bangunan</td>
        			
        			<td width="30%">Nilai (RM)</td>
        		</tr>
        		
        		
                #set($nilai_jpph_bg = "")
                #set($id_bangunan_jpph = "")
                
                #if($saiz_bangunan!=0)
                    #foreach($list in $listMaklumatBangunan)
                    #set($nilai_jpph_bg = "nilai_jpph_bg" + $!list.bil)
                    #set($id_bangunan_jpph = "id_bangunan_jpph" + $!list.bil)
                    
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center"  valign="top">$!list.bil</td>
                   <td class="$row"  valign="top">$!list.no_bangunan
                   #if($!list.nama_bangunan != "")
                   <br>Jenis : $!list.nama_bangunan.toUpperCase() - $!list.keterangan_bangunan.toUpperCase()
                   #end
                   </td>
                   <td class="$row"  valign="top">
                   
                   <input type="hidden" id="$id_bangunan_jpph" name="$id_bangunan_jpph" value="$!list.id_bangunan"  />
                   
                   #if($check_edit != "no")
                    <input type="text" $disOtherId $disOtherIdx name="$nilai_jpph_bg" id="$nilai_jpph_bg" 
                   value="$!list.nilaian_jpph" size="12" maxlength="11" style="text-align:right" onkeyup="validateNilai(this,this.value);sum_nilai()" onblur="validateModal(this,this.value,'$!list.nilaian_jpph');sum_nilai()">
                   #else
                   #set($value_temp = "")
                   #if($!list.nilaian_jp!="")                   
                    	#set($value_temp = $!Utils.format2Decimal($!list.nilaian_jpph))
                   #else
                        #set($value_temp = "")
                   #end
                   
                   <input type="text" class='disabled' readonly='readonly' name="$nilai_jpph_bg" id="$nilai_jpph_bg" 
                   value="$value_temp" size="12" maxlength="11" style="text-align:right" >
                   #end
                   </td>
                   
               
               </tr>
               
                    #end
               #else
                    <tr>
                    	<td colspan="3">Tiada rekod</td>
                    </tr>
               #end
               #if($saiz_bangunan!=0)
               #if($check_edit != "no")
               <tr>
            
               <td colspan="3" align="right">
               <input type="button"  name="cmdSimpanNilaianJPPH" value ="Simpan Nilaian Bangunan" onClick="javascript:sum_nilai();simpanNilaian()">
               </td>
               </tr>
               #end
               #end
               
		  </table>
          
          

