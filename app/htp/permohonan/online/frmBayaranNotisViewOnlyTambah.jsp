		<fieldset><legend><strong>SENARAI NOTIS 5A KTN</strong></legend>
		
			<table width="100%" border="0">
			
  	  			<tr>
    				<td colspan="10">
    					<input class="stylobutton100" type="button" name="btnTambahSenaraiNotis" id="btnTambahSenaraiNotis" value="Tambah" title="Tambah" onclick="TambahSenaraiNotis(5,1,'kemaskinipermohonan','SimpanNotis')"/>
  					</td> 
  				</tr>
  			
  			<!--	<tr>	 		
    				<td width="70%" align="right">
    					Carian No.Fail :&nbsp;<input type="text" name="carianFail" value="$!carianFail" maxlength="50" size="35" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0)">&nbsp;<u>CARI</u></a>
    				</td>
    			</tr> -->
    		</table>
  	
	 	#if($list_sizePPT > 5)
        	<div style="width:100%;height:115;overflow:auto"> 
    	#end	
		
		 	<table width="100%" cellspacing="2" cellpadding="1" border="0">
  	<!-- <tr >
    	<td colspan="4">
    		<input class="stylobutton" type="button" name="TambahNotis5A" id="TambahNotis5A" value="Tambah" onclick="TambahNotis()">
    	</td>

    </tr>
	-->
   
		<!--
     		<table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
        	<tr class="table_header">
        		<td width="3%" align="center"><b>Bil.</b></td>
        		<td width="20%"><b>No.Fail Permohonan</b></td> 
        		<td width="77%"><b>Kementerian</b></td>
        	</tr>
        
      	#if($list_sizePPT!=0)
           	#foreach($listPPT in $listPermohonanPPT)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        	<tr>
        		<td class="$row" align="center">$!listPPT.bil</td>
        		<td class="$row"><a href="javascript:viewListHakmilik('$!listPPT.id_permohonan','kemaskinipermohonan','listHakmilik',0,9)"><font color="blue">$!listPPT.no_fail</font></a></td> 
        		<td class="$row">$!listPPT.nama_kementerian</td>
        	</tr>
        	#end
     	#else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     	#end
        
    	</table> 
    	-->
    	#if($list_sizePPT > 5)
        	  </div>
    	#end
     
		</fieldset>

	<p id="Submit">&nbsp;</p>
	<input type="hidden" value="$id_jenistanah" name="id_jenistanah"/>