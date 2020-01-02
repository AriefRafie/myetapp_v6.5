

#set($ModuleName = "${session.getAttribute('_portal_module')}")
#set($showLinkHM="")
#if($ModuleName=="ekptg.view.ppt.FrmSek8LaporanAwalTanah" || $ModuleName=="ekptg.view.ppt.FrmSek8Warta"
	|| $ModuleName=="ekptg.view.ppt.FrmSek8PermintaanUkur")
	#set($showLinkHM="no")
#else
	#set($showLinkHM="yes")
#end
				#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
                #end
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
                    <tr class="table_header">
                  		<td align="center"><b>No</b></td>
                  		<td><b>No.Hakmilik</b></td>
                  		<td align="center"><b>Jumlah PB</b></td>
                  		<td><b>No.LOT/No.PT</b></td>              
                  		<td><b>Mukim/Pekan/Bandar</b></td>
                  		<td><b>Luas Lot Diambil</b></td>
                  		#if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
                  		#if($flagSegera=="3")<td><b>Pengambilan Segera</b></td>#end
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
                		#if($showLinkHM=="yes" || ($ModuleName=="ekptg.view.ppt.FrmSek8PermintaanUkur" && $listTanah.flagPU == "yes"))
               		 	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a></td>
                		#else
                		<td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                		#end
                		<td class="$row" align="center">$!listTanah.totalPB</td>
                		<td class="$row">$!listTanah.no_lotpt</td>     
                		<td class="$row">$!listTanah.nama_mukim #if($listTanah.seksyen!="")<font style=font-size:10px>Seksyen $listTanah.seksyen</font>#end</td>
                		<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                		#if($!flag_subjaket!="")
                		<td class="$row">Sj.$!listTanah.no_subjaket</td>
                		#end
                		#if($flagSegera=="3")
                		<td class="$row">
                			#if($listTanah.flag_segera_sebahagian=="Y")YA #elseif($listTanah.flag_segera_sebahagian=="N")TIDAK #end 
                		</td> 
                		#end
            		</tr>
                    	#end
                    	
                    #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
                    #end
                    
                </table>   
                
                #if($saiz_listTanah > 5)
                </div>
                #end