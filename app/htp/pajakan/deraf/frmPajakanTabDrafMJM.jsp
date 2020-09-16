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
                <td width="70%"><input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="$!beanDraf.tarikhHantarPTP" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');" />
                #end                
                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newDraf' || $mode == 'updateDraf')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima Dari Pemohon</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="$!beanDraf.tarikhTerimaPTP" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top">
                	<textarea name="txtKeteranganDraf" cols="50" rows="5"  
					onkeyup="textCounter(this.form.txtKeteranganDraf,this.form.remtxtcatatan,250);" 
					onkeydown="textCounter(this.form.txtKeteranganDraf,this.form.remtxtcatatan,250);"	                	
                	class="$classDis" $readOnly>$beanDraf.ulasan</textarea>
                </td>
              </tr>
 							     #if ($mode != 'viewDraf')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
								    </tr>	
								#end	            
              <tr>
                <td></td>
                <td>Lampiran Perjanjian </td>
                <td>:</td>
                <td>
                
                #if($mode == 'newDraf' || $mode == 'updateDraf')
                	<input id="fileupload" name="fileupload" type="file" size="40" />
                #else
                	
                	#if(!$beanDraf.lampirans.equals("TIADA"))
                		<a href="javascript:downloadPerjanjian('$idPermohonan','$idDraf')" class="style1">Muat Turun Draf</a><!--('$beanDraf.idDraf')-->
                	#else
                    	<!-- <input id="fileupload" name="fileupload" type="file" size="40" /> --> $!beanDraf.lampiran      	
                	#end
                #end
                </td>
              </tr>
               #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                #if($mode == 'newDraf' || $mode == 'updateDraf')
                	<span class="e">**Pastikan fail yang ingin di <i>upload</i> menggunakan format &quot;.doc&quot; atau &quot;.pdf&quot;</span>
                #end
                </td>
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
                    <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniDraf()" />
                    <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusDraf($idDraf)" />
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
            <tr>
              <td colspan="7" scope="row"><input class="stylobutton100" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruDraf()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="5%" align="center">Bil.</td>
              <td width="15%">Tarikh Hantar</td>
              <td width="15%" align="center">Tarikh Terima</td>
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
            <td class="$row" align="center"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.bil.</a></td>
            <td class="$row"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.tarikhHantar</a></td>
            <td class="$row" align="center">$list.tarikhTerima</td>
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

