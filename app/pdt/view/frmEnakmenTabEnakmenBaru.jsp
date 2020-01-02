<style type="text/css">
<!--
.mandatory {color: #FF0000}
-->
</style>
        <fieldset>
        <legend>MAKLUMAT ENAKMEN
        vvvvvv
        <input type="hidden" id="Enakmen_IDEnakmen" name="Enakmen_IDEnakmen" value="$!Enakmen_IDEnakmen" />
        </legend>
        <table width="100%" border="0">      
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">* </span>No. Enakmen</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoEnakmen" type="text" id="txtNoEnakmen" onblur="this.value=this.value.toUpperCase()" value="$!Enakmen_NoEnakmen" size="25" $RO_General />            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> Nama Enakmen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaEnakmen" type="text" id="txtNamaEnakmen" onblur="this.value=this.value.toUpperCase()" value="$!Enakmen_NamaEnakmen" size="50" $RO_General />            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Kuatkuasa</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><input type="text" name="txdTarikhKuatkuasa" id="txdTarikhKuatkuasa" value="$!Enakmen_TarikhKuatkuasa" size="10"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $RO_General />
            <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif" /></a>  dd/mm/yyyy          </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Mansuh</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhMansuh" id="txdTarikhMansuh" value="$!Enakmen_TarikhMansuh" size="10"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>   dd/mm/yyyy         </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No. Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNoWarta" type="text" id="txtNoWarta" onblur="this.value=this.value.toUpperCase()" value="$!Enakmen_NoWarta" size="25" $RO_General />            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhWarta" id="txdTarikhWarta" value="$!Enakmen_TarikhWarta" size="10" $RO_General  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>     dd/mm/yyyy       </td>
          </tr>
          <!--
          <tr>
            <td width="29%" align="right" valign="top">Taraf Keselamatan Enakmen</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Enakmen_EnakmenTerbuka />Terbuka
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Enakmen_EnakmenSulit />Sulit
            </td>
          </tr>
          -->
          <tr>
            <td width="29%" align="right" valign="top">Bahagian/ Urusetia</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtSeksyen" type="text" id="txtSeksyen" value="$!Enakmen_IDSeksyen" size="25" $RO_General />            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No. Fail</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="socNoFail" type="text" id="socNoFail" value="$!Enakmen_NoFail" size="50" $RO_General />            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Catatan</td>
            <td align="center" valign="top">:</td>
            <td valign="top">                         	
 	    	#if ($mode == 'view')
	    		$!Enakmen_Catatan
	    	#else	
	    	            <textarea name="txtCatatan_" cols="70" rows="10" onblur="this.value=this.value.toUpperCase()" $RO_General>$!Enakmen_Catatan</textarea>
 	      
	                         <script> 
						var area1 = new FCKeditor("txtCatatan_");
						area1.ToolbarSet = 'PFD';
						area1.BasePath = '/${appname}/library/fck/' ;
						area1.Height = 130;
						area1.Width = 530;
						area1.ReplaceTextarea();             	
					</script>					
			#end
             </td>
 
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Muat Naik Dokumen</td>
            <td align="center" valign="top">:</td> 
            <td valign="top"><input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" /></td>
          </tr>
       	  <tr>
            <td width="29%" align="right" valign="top"><i>Tag</i> Dokumen</td>
            <td align="center" valign="top">:</td> 
            <td valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $RO_General>$!tag_Dokumen</textarea>
            	<input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
            </td>
          </tr>          
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Daftar</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!Enakmen_TarikhDaftar" size="10" $RO_General  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>
      	#if ($action == 'view')
       		<input type="button" value="Kemaskini" onclick="doKemaskiniEnakmen()"/>
		#elseif ($action == 'doUpdate')
			<input type="button" value="Simpan" onclick="doAdd()"/>
			<input type="button" value="Batal" onclick="doCancel()" />
		#elseif ($action == 'update')
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveEnakmen()"/>
		#else

		#end 
			<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="doBack()"/></td>
          </tr>
        </table>
        </fieldset>
        <br />

<script type="text/javascript" src="../app/pdt/enakmen.js"></script>