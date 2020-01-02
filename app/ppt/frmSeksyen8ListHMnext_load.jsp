  <table width="100%"  cellpadding="0" cellspacing="2" border="0">        
                    
   					
                    	#foreach($listTanah in $listMaklumatTanah)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    	
                    	
                    <tr>
                		<td width="5%" class="$row" align="center">$!listTanah.rn</td>
                		#if($showLinkHM=="yes" || ($ModuleName=="ekptg.view.ppt.FrmSek8PermintaanUkur" && $listTanah.flagPU == "yes"))
               		 	<td width="20%" class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a></td>
                		#else
                		<td width="20%" class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                		#end
                		<td width="5%" class="$row" align="center">$!listTanah.totalPB</td>
                		<td width="20%" class="$row">$!listTanah.no_lotpt</td>     
                		<td width="20%" class="$row" >$!listTanah.nama_mukim 
                        #if($listTanah.seksyen!="")
                        <font style='font-size:10px' >Seksyen $listTanah.seksyen</font>
                        #end</td>
                		<td width="15%" class="$row" >$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                		#if($!flag_subjaket!="")
                		<td width="5%" class="$row">Sj.$!listTanah.no_subjaket</td>
                		#end
                		#if($flagSegera=="3")
                		<td width="10%" class="$row">
                			#if($listTanah.flag_segera_sebahagian=="Y")YA #elseif($listTanah.flag_segera_sebahagian=="N")TIDAK #end 
                		</td> 
                		#end
            		</tr>
    #end
    
                        </table> 
                        
                        
                        <script> 

$jquery("#div_nexthakmilik").html(document.getElementById('div_nexthakmilik').innerHTML+"<div id=\"next_senaraiHakmilik"+document.${formName}.setLimit.value+"\" ></div>");	




</script>