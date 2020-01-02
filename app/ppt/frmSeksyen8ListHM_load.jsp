
                           #if($saiz_listTanah > 20)
              <!--  <div id="div_tinggi" ></div>
                <div id="div_position" ></div>
                <div id="div_scroll" ></div>
                -->
                <div id="listHakmilik"   style="width:100%;height:300;overflow:auto;" > 
                #end

              
                  <table width="100%"  cellpadding="0" cellspacing="2" border="0">        
                    
   					#if($saiz_listTanah!=0)
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
                      
                        <span id="div_nexthakmilik"></span> 
                         
                       
                        
                #if($saiz_listTanah > 20)
                </div>
                #end
                    	
                    #else
                     <table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
                      </table>
                    #end
                        
                       
             
                    
                 
                
              
             
  



        
<script> 

$jquery("#div_nexthakmilik").html(document.getElementById('div_nexthakmilik').innerHTML+"<div id=\"next_senaraiHakmilik"+document.${formName}.setLimit.value+"\" ></div>");	


function load_next()
{
doDivAjaxCall$formname('senaraiHakmilik_hidden','getSenaraiHakmiliknext_hidden','');
doDivAjaxCall$formname('next_senaraiHakmilik'+document.${formName}.setLimit.value,'getSenaraiHakmilik_next','');
}


$jquery('#listHakmilik').scroll(function(){ 

  var elementHeight = document.${formName}.setLimit.value; 
  var scrollPosition = $jquery('#listHakmilik').height() + $jquery('#listHakmilik').scrollTop(); 
  $jquery("#div_position").html("position :"+scrollPosition);	 
  $jquery("#div_tinggi").html("tinggi :"+$jquery('#listHakmilik').height());	
  $jquery("#div_scroll").html("scroll top :"+$jquery('#listHakmilik').scrollTop());	

 if(scrollPosition >= elementHeight)
 {
	 load_next();
 }
  
}); 



</script>