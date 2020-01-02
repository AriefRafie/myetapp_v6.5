<tr id="div_rowFolderUtama$viewMainFoler.ID_MESYUARATUTAMA">
        	   <td class="$rowCss"  align="center" valign="top" >$BIL</td> 
			   <td class="$rowCss"  align="left" valign="top">  	
               #set($span1 = "span1listMesyuaratUtama"+$BIL)		   
			   <span id="$span1" > 
			   <span onClick="doDivAjaxCall$formname('div_viewFolderUtama$viewMainFoler.ID_MESYUARATUTAMA','showAllFolder','NAMA_FOLDER=$viewMainFoler.TAJUK_MESYUARAT&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$viewMainFoler.ID_MESYUARATUTAMA').val()+'&ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y$FlagCari&MAX_LAYER=$viewMainFoler.MAX_LAYER');" >
			   <span class="font_tajuk_utama" ><u><b>$viewMainFoler.TAJUK_MESYUARAT</b></u></span>
			   </span>
			   </span>
               <span id="ShowDalamTindakanMain_$viewMainFoler.ID_MESYUARATUTAMA">
               #if($viewMainFoler.DALAM_TINDAKAN > 0)
               <br />
			   <font color='red' class="blink"><b>* $viewMainFoler.DALAM_TINDAKAN Menunggu Tindakan Bahagian</b></font> 
               
                 <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_viewFolderUtama$viewMainFoler.ID_MESYUARATUTAMA','emelTindakanMain','NAMA_FOLDER=$viewMainFoler.TAJUK_MESYUARAT&FLAG_OPENCLOSE=CLOSE&ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y$FlagCari&MAX_LAYER=$viewMainFoler.MAX_LAYER');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
               
               #end
               </span>
			   
			   <input type="hidden" id="countFolder_$viewMainFoler.ID_MESYUARATUTAMA" name="countFolder_$viewMainFoler.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActFolder_$viewMainFoler.ID_MESYUARATUTAMA" name="countActFolder_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.TOTAL_FOLDER"  >
			   <input type="hidden" id="countTindakan_$viewMainFoler.ID_MESYUARATUTAMA" name="countTindakan_$viewMainFoler.ID_MESYUARATUTAMA" value = "0"  >
			   <input type="hidden" id="countActLampiran_$viewMainFoler.ID_MESYUARATUTAMA" name="countActLampiran_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.TOTAL_TINDAKAN"  >
			   <input type="hidden" id="HID_OPENCLOSE_$viewMainFoler.ID_MESYUARATUTAMA" name="HID_OPENCLOSE_$viewMainFoler.ID_MESYUARATUTAMA" value = "$HID_OPENCLOSE"  >
			   <input type="hidden" id="HID_MAXLAYER_$viewMainFoler.ID_MESYUARATUTAMA" name="HID_MAXLAYER_$viewMainFoler.ID_MESYUARATUTAMA" value = "$viewMainFoler.MAX_LAYER"  >
			  
			  
			   </td>  
               
           <td  class="$rowCss" align="center" valign="top" >$viewMainFoler.TAHUN</td>
           <td  class="$rowCss" align="center" valign="top" >$viewMainFoler.BILANGAN</td> 
           <td  class="$rowCss" align="center" valign="top" >$viewMainFoler.STATUS_MESYUARAT</td> 
           <td  class="$rowCss" align="left" valign="top" >
           
           <table width="100%" cellpadding="0" cellspacing="0" >
            <tr>
            <td width="5%" align="left" valign="top">PIC</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="94%" align="left" valign="top">
            $viewMainFoler.PIC
            </td>
            </tr>
            #if($viewMainFoler.TEL_PIC != "")
            <tr>
            <td align="left" valign="top">Tel</td>
            <td  align="center" valign="top">:</td>
            <td  align="left" valign="top">
            $viewMainFoler.TEL_PIC
            </td>
            </tr>
            #end
             #if($viewMainFoler.EMEL_PIC != "")
            <tr>
            <td align="left" valign="top">Emel</td>
            <td  align="center" valign="top">:</td>
            <td  align="left" valign="top">
            <span id="listEMELPIC$viewMainFoler.ID_MESYUARATUTAMA"  style="width:100%" >   
                $viewMainFoler.EMEL_PIC              
           </span>
           <script>
		   	 hideByTag('listEMELPIC$viewMainFoler.ID_MESYUARATUTAMA','strong');
		   </script>
            </td>
            </tr>
            #end
            </table>
           
           
           
         
           
          
           </td> 
           <td class="$rowCss"  align="center" valign="top" >
			
			   <a href="javascript:doDivAjaxCall$formname('div_rowFolderUtama$viewMainFoler.ID_MESYUARATUTAMA','editMainDir','rowCss=$rowCss&ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&BIL=$BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	    	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','deleteFolderUtama','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&FlagCari=N&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus Direktori"  src="../img/hapus.gif" border="0"></a>
               
               
                  #if($viewMainFoler.FLAG_AKTIF == "T" && $viewMainFoler.TOTALBILANGAN == $viewMainFoler.BILANGAN && $viewMainFoler.TOTALTAHUN == $viewMainFoler.TAHUN)
               
               <span class="font_tajuk_sub" style="cursor:pointer" >
	<u onClick="if(confirm('Maklumat mesyuarat akan disalin untuk merekod maklumat mesyuarat bilangan seterusnya. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','salinRekod','ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&FlagCari=N&carianTerperinci='+$jquery('#carianTerperinci').val());}">[Salin]</u>
	</span>
	
	    		#end
           
	    
               </td>
                
		</tr>
		