
	<fieldset>
	<legend><strong>Senarai Hakmilik PPT</strong></legend>
			
    		#if($saiz_listTanah > 10)
                <div style="width:100%;height:220;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
        			<td><b>No.Lot</b></td>
        			<td><b>No.Syit</b></td>
        			<td><b>Daerah</b></td>
            		<td><b>Bandar/Pekan/Mukim</b></td>
            		<td><b>Keluasan</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">$!listTanah.no_lot</td>
                   <td class="$row">$!listTanah.no_syit</td>
                   <td class="$row">$!listTanah.nama_daerah</td>
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                   <input type="hidden" name="idListHM" value="$!listTanah.id_hakmilik">
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 10)
                </div>
            #end
	
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>					
				<input type="button" name="cmdSimpanHM" class="stylobutton" value="Simpan" onClick="javascript:simpanHakmilikPPT('$!idpermohonanPPT','kemaskinipermohonan','simpanHakmilikPPT',0,0);">				
				<input type="button" name="cmdKembali" class="stylobutton" value="Kembali" onClick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0)">
			</td>
		</tr>
	</table>

<p id="Submit">&nbsp;</p>

<input type="hidden" value="$!idpermohonanPPT" name="idpermohonanPPT"/>
<input type="hidden" value="$id_jenistanah" name="id_jenistanah"/>
<input type="hidden" name="id_hakmilik"/>
