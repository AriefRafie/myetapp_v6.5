
<input type="hidden"  name="ID_PUPB" id="ID_PUPB" value="$!ID_PUPB"  >
#if($tambahPB=="yes")
<fieldset style="width:50%">



 
<table width="100%" border="0">
<tr align="left">
<td width="2%">
<font  color="red">*</font>
</td>
<td width="30%">
Nama PB
</td>
<td width="1%">
:
</td>
<td width="67%">
 <input type="text"  name="txtNamaPBPU" id="txtNamaPBPU" value="$!txtNamaPBPU"   style="width:100%" maxlength="4000"  >
</td>
</tr>
<tr align="left">
<td >
<font  color="red">*</font>
</td>
<td >
No. PB
</td>
<td >
:
</td>
<td >
 <input type="text"  name="txtNoPBPU" id="txtNoPBPU" value="$!txtNoPBPU"   style="width:30%" maxlength="20"  >
</td>
</tr>
<tr align="left">
<td >
<font  color="red">*</font>
</td>
<td >
Bahagian
</td>
<td >
:
</td>
<td >
 <input type="text"  name="txtSyerAtasPU" id="txtSyerAtasPU" value="$!txtSyerAtasPU"  onkeyup="validateNumber(this,this.value)"   style="width:20%" maxlength="20"  > /
 <input type="text"  name="txtSyerBawahPU" id="txtSyerBawahPU" value="$!txtSyerBawahPU"  onkeyup="validateNumber(this,this.value)"   style="width:20%" maxlength="20"  >
</td>
</tr>
<tr align="left">
<td  >
</td>
<td  >
</td>
<td  >
</td>
<td colspan="2" >
<input type="button" name="cmdSimpanPB" value ="Simpan" onClick="javascript:simpanPBPU()">
#if($!ID_PUPB != "")
<input type="button" name="cmdHapusPB" value ="Hapus" onClick="javascript:hapusPBPU($!ID_PUPB)">
#end
</td>
</tr>
</table>
</fieldset>
#end



<table width="100%" border="0">
<tr align="left">
<td>
<input type="button" name="cmdTambahPB" value ="Tambah" onClick="javascript:tambahPBPU()">
</td>
</tr>
</table>



<table width="50%" border="0"  align="left"  >


<tr>
<td>

<table width="100%" border="0" align="left"  >
<tr class="table_header" >
<td width="5%" align="center"  valign="top" >Bil</td>
<td width="65%" align="left" valign="top" >Nama Tuan Tanah</td>
<td width="30%" align="center" valign="top" >Bahagian</td>
</tr>
</table>

</td>
</tr>	


 #if($saiz_pb!=0)
<tr>
<td>	  
                
             
  				#set($wid_tab = "100%")                 
                 <table width="$wid_tab" border="0" align="left"   >  
                
			    
            		#foreach($listPB in $listMaklumatPB)
              			#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                            
               		<tr>
                   		<td width="5%"  class="$row" align="center" valign="top">$!listPB.bil</td>
                   		<td width="65%" class="$row" align="left" valign="top"><a href="javascript:viewPB('$!listPB.id_pupb')"><font color="blue">$!listPB.nama_pb<br />
                        $!listPB.no_pb</font></a></td>
                   		<td width="30%" class="$row" align="center" valign="top">$!listPB.syer_atas / $!listPB.syer_bawah
                     
                        </td>   
                      
              		</tr>
                   
           			#end
                     </table>
</td>
</tr>
 
   
                    
        		#else
<tr>
<td>
                
                <table width="50%" border="0" align="left"  >  
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
                    </table>
</td>
</tr>                    
        		#end

</table>