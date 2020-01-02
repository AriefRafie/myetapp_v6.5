<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

	
	<tr>
    	<td>
     		<fieldset><legend><strong>SENARAI TANAH PINDAH MILIK</strong></legend>
  
    			<table align="center" width="100%"> 
		            #if ($mode == 'view')
		            #end
		            <tr class="table_header">
		              <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
		              <td width="17%"><strong>Negeri</strong></td>
		              <td width="20%" align="center"><strong>Daerah</strong></td>
		              <td width="20%" align="center"><strong>Bandar/Pekan/Mukim</strong></td>
		              <!--<td width="16%" align="center"><strong>Jenis Hakmilik</strong></td>-->
		              <td width="20%" ><strong>No. Hakmilik</strong></td>
		              <td width="20%" ><strong>No. Warta</strong></td>
		        	</tr>

		        #set ($list = "")
		        #if ($SenaraiTPMilik.size() > 0)
		        	#foreach ($listTPMilik in $SenaraiTPMilik)
		            	#if ($listTPMilik.bil == '')
		                	#set( $row = "row1" )
		            	#elseif (($listTPMilik.bil % 2) != 0)
		                	#set( $row = "row1" )
		            	#else 
		                	#set( $row = "row2" )
		            	#end
			      	<tr>
			            <td class="$row" align="center">$listTPMilik.bil.</td>
			            <td class="$row"><a href="javascript:daftarBaruPemilik('$listTPMilik.idHakmilikurusan')" class="style1">$listTPMilik.negeri</a></td>
			            <td class="$row" align="center">$listTPMilik.daerah</td>
			            <td class="$row" align="center">$listTPMilik.mukim</td>
			            <!-- <td class="$row" align="center">$listTPMilik.jenisHakmilik</td> -->
			            <td class="$row" >$!listTPMilik.kodJenisHakmilik $listTPMilik.noHakmilik</td>
			          	<td class="$row" >$listTPMilik.noWarta</td>
			      	</tr>
          			#end
          		#else
		          	<tr>
		            	<td colspan="6" class="row1">Tiada Rekod</td>
		        	</tr>
		      	#end
        		</table>
        
			</fieldset>
		</td>
	</tr>
	
   #if ($mode == 'newPemilik' || $mode == 'updatePemilik' || $mode == 'viewPemilik')
  	<tr>
    	<td>
    		
    		<fieldset><legend><strong>MAKLUMAT PINDAH MILIK TANAH</strong></legend>
        		<table width="100%" border="0" cellspacing="2" cellpadding="2">          
		          <!-- <tr>
		          	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		           	<td>&nbsp;</td>
		          </tr> -->

				#foreach ($beanPemilikTanah in $BeanPemilik)
	          		<tr>
		                <td>&nbsp;</td>
		                <td>No. Hakmilik / Warta</td>
		                <td>:</td>
		                <td>$selectNohakmilik</td>
	              	</tr>
		            <tr>
		                <td width="1%">&nbsp;</td>
		                <td width="28%">No. Perserahan</td>
		                <td width="1%">:</td>
		                <td width="70%"><input type="text" name="txtNoPerserahan" id="txtNoPerserahan" size="20" value="$beanPemilikTanah.noPerserahan" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
		           	</tr>
              		<tr>
		                <td>&nbsp;</td>
		                <td>Tarikh Daftar</td>
		                <td>:</td>
		                <td><input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" size="10" value="$beanPemilikTanah.tarikhDaftar" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);checkDate();" class="$classDis" $readOnly/>
		                  <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');" />
		              	</td>
	              	</tr>
	              <tr>
	                <td width="1%">&nbsp;<font color="#FF0000">*</font></td>
	                <td width="28%">Nama Penerima</td>
	                <td width="1%">:</td>
	                <td width="70%"><input type="text" name="txtNama" id="txtNama" size="50" value="$beanPemilikTanah.nama" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td valign="top">&nbsp;<font color="#FF0000">*</font></td>
	                <td valign="top">Alamat</td>
	                <td valign="top">:</td>
	                <td valign="top"><input type="text" name="txtAlamat1" id="txtAlamat1" size="50" value="$beanPemilikTanah.alamat1" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>            
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td><input type="text" name="txtAlamat2" id="txtAlamat2" size="50" value="$beanPemilikTanah.alamat2" onBlur="this.value=this.value.toUpperCase();"  class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td><input type="text" name="txtAlamat3" id="txtAlamat3" size="50" value="$beanPemilikTanah.alamat3" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Poskod</td>
	                <td>:</td>
	                <td><input name="txtPoskod" type="text" class="$classDis" id="txtPoskod" value="$beanPemilikTanah.poskod" size="5" maxlength="5" $readOnly onBlur="validatePoskod(this,this.value);"/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Negeri</td>
	                <td>:</td>
	                <td>$selectNegeri</td>
	              </tr>
	              <tr>
	                <td>&nbsp;<font color="#FF0000">*</font></td>
	                <td>Bandar</td>
	                <td>:</td>
	                <td>$selectDaerah</td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>No. Tel</td>
	                <td>:</td>
	                <td><input type="text" name="txtNoTelefon" id="txtNoTelefon" size="14" value="$beanPemilikTanah.tel"  class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>No. Fax</td>
	                <td>:</td>
	                <td><input type="text" name="txtFax" id="txtFax" size="14" value="$beanPemilikTanah.fax" class="$classDis" $readOnly/></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	              </tr>
	              <tr>
	              	<td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td colspan="2">

	                
	                	</td>
	            	</tr>
              	#end
            	</table>
        	</fieldset>
        
    	</td>
  	</tr>
  	<tr>
    	<td align="center">
    		#if (!$!jenisAkses.equals('Readonly'))
    		
	                #if ($mode == 'newPemilik')
	                	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPemilik()" />
	                    <input class="stylobutton"  type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
	                    <input class="stylobutton"  type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPemilik()" />
	                #elseif ($mode == 'viewPemilik')
	                    <input class="stylobutton"  type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPemilik()" />
	                    <input class="stylobutton"  type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusPemilik()" />
	                    <input class="stylobutton"  type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPemilik()" />
	                    
	                #elseif ($mode == 'updatePemilik')
	                    <input class="stylobutton"  type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdatePemilik()" />
	                    <input class="stylobutton"  type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
	                    <input class="stylobutton"  type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePemilik()" />
	                #end 
	                
	     	#end              		
    		</td>
  	</tr> 
  	#end
	
	
	<tr>
    	<td>       		
    		<fieldset><legend><strong>SENARAI PEMILIK</strong></legend> 
    			<table align="center" width="100%"> 

		            <tr class="table_header">
		              <td scope="row" width="3%" align="center"><strong>Bil.</strong></td>
		              <td width="20%"><strong>No. Hakmilik / Warta</strong></td>
		              <td width="77%" ><strong>Nama</strong></td>
		              <!--<td width="21%" align="center"><strong>Jenis Hakmilik</strong></td> -->
		          	</tr>
		        #set ($list = "")
		      	#if ($SenaraiPemilik.size() > 0)
			          	
			    	#foreach ($list in $SenaraiPemilik)
			            #if ($list.bil == '')
			                #set( $row = "row1" )
			            #elseif (($list.bil % 2) != 0)
			                #set( $row = "row1" )
			            #else 
			                #set( $row = "row2" )
			            #end
	      			<tr>
				    	<td class="$row" align="center">
				        	<a href="javascript:paparPemilik('$list.idPihakKepentingan')">$list.bil.</a>
				      	</td>
	            		<td class="$row">
	            				<a href="javascript:paparPemilik('$list.idPihakKepentingan')" class="style1">          
					            #if($list.noHakmilik != "")           
					            	$!list.kodJenisHakmilik $list.noHakmilik
					             
					            #else            	
					                $list.noWarta
					                
					            #end
				            	</a>
	            
	            		</td>
	            		<td class="$row" >$list.nama</td>
	            		<!-- <td class="$row" align="center">$list.jenisHakmilik</td> -->
	          		</tr>
	          		
	          		#end
	          		
          		#else
		        	<tr>
		            	<td colspan="3" class="row1">Tiada Rekod</td>
		          	</tr>
          		#end
        		</table>       
    		</fieldset>  
    
    	</td>
  	</tr>	
	

