<style type="text/css">
<!--
	.pautanms{color: #0000FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
			#parse ("app/htp/gadaian/frmGadaianInfoAjax.jsp")
			</fieldset>
		</td>
    </tr>

	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT HAKMILIK</strong></legend>
				<table width="100%" >
					
		     		<tr>		     			
		            	<td colspan="7" width="100%" valign="top">
   							<input type="button" class="stylobutton100" name="add" value="Tambah" onclick="javascript:cmdDaftarHakmilik()" />
         					<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliPermohonan()">
        				</td>
        			</tr>
        			<tr>  
        					<td colspan="8">        						
								#parse("app/utils/record_paging.jsp")
					        </td> 
			        </tr>     			
				      <tr class="table_header">
				        <td width="3%">Bil.</td>
		        		<td width="15%">Negeri</td>
		        		<td width="15%">Daerah</td>
		        		<td width="15%">Bandar/ Pekan/ Mukim</td>
		        		<td width="10%">No.Hakmilik</td>
		        		<td width="10%">No. Lot/PT</td>
				        <td width="27%">Nama Pemilik</td>
				        <td width="5%">Proses</td>
				      </tr>
		      		#set ($count = 0)
		      		#foreach ( $!mo in $!mt)
		      		#set ($count = $count+1)
		      		#set( $i = $velocityCount )
			          #if ( ($i % 2) != 1 )
			               #set( $row = "row2" )
			          #else
			               #set( $row = "row1" )
			          #end
				      <tr>
				        <td class="$row">$!count.</td>
				        <td class="$row">$mo.getNamanegeri()</td>
				        <td class="$row"><a href="javascript:viewHakmilik('$!mo.getIdhakmilikurusan()')" class="pautanms">$mo.getNamadaerah()</a></td>
				        <td class="$row">$mo.getNamamukim()</td>
				        <td class="$row">$!mo.getKodjenishakmilik()$!mo.nohakmilik</td>
				        <td class="$row">$!mo.getKeteranganLot()$!mo.getNolot()</td>
				        <td class="$row">$!mo.getPihaks().elementAt(0).getNama()</td>
				   		<td class="$row">
       						<!-- <input type="button" class="stylobutton100" name="cmdSeterusnya" value="Seterusnya" 
       						onclick="javascript:cmdSenaraiHakmilik('$permohonan.idPermohonan')"> 	
       								<input type="button" class="stylobutton100" name="cmdSeterusnya" value="Seterusnya" 
       						onclick="javascript:cmdSenaraiHakmilik('$!mo.getIdhakmilikurusan()')"> 
       						-->
       						<input type="button" class="stylobutton100" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya('$!mo.getIdhakmilikurusan()')">
       						      
		        		</td>
				      </tr>
		     		 #end
			      #if ($count == 0)
			      <tr>
			        <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
			      </tr>
			      #end

			
				</table>
			</fieldset>		
		</td>
   <!-- </tr>
    	<tr>		
		<td align="center" >		
			<input type="button" class="stylobutton100" name="kembali" value="Kembali"  onclick="javascript:cmdKembaliSenaraiHakmilik()">       				
	    </td>
	</tr> -->
    
	#if($SOC == 'SOC')
	<tr>
		<td>
			<fieldset><legend>PELEPASAN GADAIAN</legend>
				<table width="100%" border="0">
					<tr>
				    	<td width="31%">Tarikh Pelepasan Gadaian</td>
				    	<td width="1%">:</td>
				    	<td width="68%">
				    		<input type="text" name="txdTarikhLepasGadai" id="txdTarikhLepasGadai" size="15" value="$TarikhLepasGadai" $classDis onblur="check_date(this)" />
				      		<img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhLepasGadai',false,'dmy');" />
				      	</td>
					</tr>
				</table>
			</fieldset>		
		</td>
    </tr>    
	#end
    

    
   	#if ($pagemode == 'baru' || $pagemode == 'kemaskini')
    <!-- <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
    </tr> -->
	#end    
	
	<tr>
		<td colspan="2" align="center">
        
		##if($pagemode == 'baru' || $pagemode == 'kemaskini')
<!--
        <input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">
        <input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">
        ##elseif($pagemode == 'simpan')
        <input type="button" class="stylobutton" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">
        <input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        -->
        ##else
        
        <!-- <input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
          
          -->
       
        ##end     
        </td>     
	</tr>   
</table>		

  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
  <input type="hidden" name="namaskrin" value="hakmilik">

  
  

