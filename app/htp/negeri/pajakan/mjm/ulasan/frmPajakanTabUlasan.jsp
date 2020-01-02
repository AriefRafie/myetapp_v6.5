 <!--frmPajakanTabUlasan.jsp-->
<!-- CL-02-026 -->
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>         
          
            <table width="100%" border="0"  cellspacing="2" cellpadding="2">
             #if ($mode == 'newKJP' || $mode == 'updateKJP' || $mode == 'viewKJP')
              <tr>
                <td>
				  <fieldset><legend><strong>ULASAN KJP</strong></legend>
                	<table width="100%" border="0">
                ##foreach ($beanUlasanKJP in $BeanUlasanKJP)
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">No. Rujukan KJP</td>
			            <td width="1%">:</td>
			            <td width="80%"><input type="text" name="txtNoRujukanKJP" size="30" maxlength="39" value="$!ulasan.getNo()" $readOnly class="$classDis" onBlur="this.value=this.value.toUpperCase();"/></td>
			      	</tr>
                	<tr>
			            <td width="1%">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">Tarikh Hantar Kepada KJP</td>
			            <td width="1%">:</td>
			            <td width="80%" valign="center">
			            	<input type="text" name="txdTarikhHantarKJP" size="10" maxlength="30" value="$!ulasan.getTarikhHantarTxt()" $readOnly class="$classDis" onBlur="this.value=this.value.toUpperCase();check_date(this);"/>
			            	<!-- <input type="text" name="txdTarikhHantarKJPX" size="11" maxlength="30" value="$ulasan.getTarikhHantarTxt()" /> -->
			                #if ($mode == 'newKJP' || $mode == 'updateKJP')
			                	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarKJP',false,'dmy');">
                    		#end
			            </td>
			      	</tr>
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">Tarikh Terima Dari KJP</td>
			            <td width="1%">:</td>
			            <td width="80%">
			            	<input type="text" name="txdTarikhTerimaKJP" size="10" maxlength="30" value="$!ulasan.getTarikhTerimaTxt()" $readOnly class="$classDis" onBlur="this.value=this.value.toUpperCase();check_date(this);"/>
			                #if ($mode == 'newKJP' || $mode == 'updateKJP')
			                	<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaKJP',false,'dmy');">
                    		#end 
			            </td>
			      	</tr>	
                	<tr>
			            <td width="1%">##if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
			            <td width="18%">Keputusan</td>
			            <td width="1%">:</td>
			            <td width="80%">
                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis style="width:200">
						
                        #if($ulasan.getKeputusan() == 'S')
							<option value="">SILA PILIH</option>
							<option value="S" selected="selected">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>
                        
                        #elseif($ulasan.getKeputusan() == 'TS')
 							<option value="">SILA PILIH</option>
                        	<option value="S" >SETUJU</option>
							<option value="TS" selected="selected" >TIDAK SETUJU</option>
                      
                        #else
   							<option value="">SILA PILIH</option>
                        	<option value="S">SETUJU</option>
							<option value="TS">TIDAK SETUJU</option>
							                      
                        #end
                        
                    </select>
			            </td>
			      	</tr>			      	
                	<tr>
			            <td width="1%"></td>
			            <td width="18%" valign="top">Ulasan</td>
			            <td width="1%" valign="top">:</td>
			            <td width="80%" >
            <textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$!ulasan.getUlasan()</textarea>
			            </td>
			      	</tr>			      			      	

                  
                  ##end
                  <tr>
                  	<td colspan="4">&nbsp;</td>
                  <tr>
                  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>
         </td>
              </table>
           		</fieldset>
                </td>
              </tr>
              
        	#if ($mode == 'newKJP' || $mode == 'updateKJP')

			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
	     	
            #end   
              
              <tr align="center">
                <td>
           	#if ($mode == 'newKJP')
                      <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUlasanKJP()" />
                      <input class="stylobutton100" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan"/>
                      <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUlasanKJP()" />
			#elseif ($mode == 'viewKJP')
				<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniUlasanKJP()" />
				<input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusKJP()" />
				<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalUlasanKJP()" />
			#elseif ($mode == 'updateKJP')
				<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateKJP()" />
				<input class="stylobutton100" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan"/>
				<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateKJP()" />
			#end                 	
                </td>
              </tr>              
              <!--<tr>
                <td>&nbsp;</td>
              </tr> -->
              
              #end
              
              <tr>
                <td>
                
               	  <fieldset>
    <legend><strong>SENARAI ULASAN KJP</strong></legend>
    <table align="center" width="100%"> 
            #if ($mode == 'view')
            	#if (!$!jenisAkses.equals('Readonly'))
            <tr>
              <td colspan="6" scope="row">
              	<input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruKJP()"/>
              </td>
            </tr>
            	#end
            #end
            <tr class="table_header">
              <td width="3%" scope="row" align="center">Bil.</td>
              <td width="15%">No. Rujukan KJP</td>
              <td width="12%" align="center">Tarikh Hantar</td>
              <td width="12%" align="center">Tarikh Terima</td>
              <td width="15%"align="center">Keputusan</td>
              <!--<td align="center">Hapus</td> -->
             	<td width="43%" align="left">Ulasan</td>
            </tr>
          #set ($list = "")
          #if ($SenaraiUlasanKJP.size() > 0)
          #foreach ($list in $SenaraiUlasanKJP)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
    	<tr>
            <td class="$row" align="center" ><a href="javascript:paparKJP('$list.idUlasanKJP')" class="style1">$!list.bil.</a></td>
            <td class="$row" ><a href="javascript:paparKJP('$list.idUlasanKJP')" class="style1">$!list.noRujukan</a></td>
            <td class="$row" align="center" >$!list.tarikhHantar</td>
          <td class="$row" align="center" >$!list.tarikhTerima</td>
          <td align="center" class="$row">$!list.statusName</td>
          <!-- <td align="center" class="$row"><a href="javascript: HapusKJP('$list.idUlasanKJP')"><img src="../img/delete.gif" border="0"></a></td> -->
           <td align="left" class="$row">$!list.ulasanKJP</td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1" colspan="5">Tiada Rekod</td>
            <!-- <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td width="29%" class="row1">&nbsp;</td>
            <td width="15%" class="row1">&nbsp;</td> -->
            </tr>
          #end
        </table>
        
    </fieldset>
                
                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table>
            <p>&nbsp;</p>
<!-- </fieldset> -->

