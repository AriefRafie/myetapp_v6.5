<style type="text/css">
<!--
.style1 {color: #0033FF}
.e {
	color: #F00;
	font-size: 9px;
}
-->
</style>
<input type="hidden" value="$idPermohonan" name="txtidPermohonan" id="txtidPermohonan"/>
<input type="hidden" value="$!idPermohonan" name="idPermohonan" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newDraf' || $mode == 'updateDraf' || $mode == 'viewDraf')
  <tr>
    <td>
    
   	  <fieldset>
            <legend><strong>ULASAN DRAF</strong>
        
            </legend><table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanDraf in $BeanDraf)
              <tr>
                <td width="1%">#if ($mode == 'newDraf' || $mode == 'updateDraf')<font color="#FF0000">*</font>#end</td>
                <td width="28%">Tarikh Hantar Kepada Pemohon</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="$beanDraf.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');" />
                #end                
                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newDraf' || $mode == 'updateDraf')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima Dari Pemohon</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="$beanDraf.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtKeteranganDraf" id="txtKeteranganDraf" cols="50" rows="5"  onkeyup="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanDraf.ulasan</textarea></td>
              </tr>
             
              <tr>
                <td></td>
                <td>Lampiran Perjanjian $!beanDraf.lampiran</td>
                <td>:</td>
                <td>
                
                #if($mode == 'newDraf' || $mode == 'updateDraf')
                	<input id="fileupload" name="fileupload" type="file" size="40" />
                #else
                	
                	#if($beanDraf.lampiran!="0")
                		<a href="javascript:downloadPerjanjian('$idPermohonan','$idDraf')" class="style1">Muat Turun Perjanjian</a><!--('$beanDraf.idDraf')-->
                	#else
                    	<!-- <input id="fileupload" name="fileupload" type="file" size="40" /> -->       	
                	#end
                #end
                </td>
              </tr>
               #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><span class="e">**Pastikan fail yang ingin di upload menggunakan format &quot;.doc atau .pdf&quot;</span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">

                </td>
              </tr>
            </table>
        </fieldset>
        
    </td>
  </tr>
   
         	#if ($mode == 'newDraf' || $mode == 'updateDraf')

			<tr>
	  			<td>
	        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	        	</td>
	     	</tr>
	     	
            #end   
  
  	<tr>
    	<td align="center">
    		
                 #if ($mode == 'newDraf')
                	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SDraf($idPermohonan)" />
                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalDraf()" />
                #elseif ($mode == 'viewDraf')
        		#if (!$!jenisAkses.equals('Readonly'))
                    <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniDraf()" />
                    <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusDraf($idDraf)" />
               	#end
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalDraf()" />
                    
                #elseif ($mode == 'updateDraf')
                  	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateDraf()" />
                    <!-- <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/> -->
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateDraf()" />
                #end    		
    		
      	</td>
  	</tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>
    
    <fieldset>
    <legend><strong>SENARAI DRAF</strong></legend>
    
    	<table align="center" width="100%"> 
            #if ($mode == 'view')
				#if (!$!jenisAkses.equals('Readonly'))	
            <tr>
              <td colspan="7" scope="row"><input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruDraf()"/></td>
            </tr>
				#end            
			#end
            <tr class="table_header">
              <td scope="row" width="5%" align="center">Bil.</td>
              <td width="15%">Tarikh Terima</td>
              <td width="15%" align="center">Tarikh Hantar</td>
              <td width="65%">Ulasan</td>
        </tr>
          #set ($list = "")
          #if ($SenaraiDraf.size() > 0)
          #foreach ($list in $SenaraiDraf)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$list.bil.</td>
            <td class="$row"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.tarikhTerima</a></td>
            <td class="$row" align="center">$list.tarikhHantar</td>
          	<td class="$row" >$list.ulasan</td>
      </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
</table>

