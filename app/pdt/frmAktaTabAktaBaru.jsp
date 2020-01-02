
<!-- 
::: skrin 1 -->
 <!--Akta_IDAkta ::::::::: $!Akta_IDAkta-->

<style type="text/css">
<!--
.mandatory {color: #FF0000}
-->
</style>
<div id="mydiv">
        <fieldset>
        <legend>Maklumat Akta</legend>
			<!--<input type="text" id="action" name="action" value="$action" />-->
          <input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" value="$!Akta_IDAkta" />
        <table width="100%" border="0">      
          <tr>
            
            <td width="29%" align="right" valign="top"><span  style="color:red">*</span> No. Akta</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoAkta" type="text" id="txtNoAkta" onblur="this.value=this.value.toUpperCase()" value="$!Akta_NoAkta" size="25" maxlength="25" $RO_General />            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span style="color:red">*</span> Nama Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaAkta" type="text" id="txtNamaAkta" onblur="this.value=this.value.toUpperCase()" value="$!Akta_NamaAkta" size="50" $RO_General />            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Kuatkuasa</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><input type="text" name="txdTarikhKuatkuasa" id="txdTarikhKuatkuasa" value="$!Akta_TarikhKuatkuasa" size="10"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $RO_General />
            <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif" /></a>            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Mansuh</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhMansuh" id="txdTarikhMansuh" value="$!Akta_TarikhMansuh" size="10"  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No. Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNoWarta" type="text" id="txtNoWarta" onkeyup="validateNumber(this,this.value)" onblur="this.value=this.value.toUpperCase();validateNumber(this,this.value)" value="$!Akta_NoWarta" size="25" $RO_General />            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhWarta" id="txdTarikhWarta" value="$!Akta_TarikhWarta" size="10" $RO_General  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>
          <!--
          <tr>
            <td width="29%" align="right" valign="top">Taraf Keselamatan Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Akta_AktaTerbuka />Terbuka
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Akta_AktaSulit />Sulit
            </td>
          </tr>
          -->
          <tr>
            <td width="29%" align="right" valign="top">Bahagian / Urusetia</td>
            <td align="center" valign="top">:</td>
            <td valign="top">$socSeksyen
              <!--<input name="txtSeksyen" type="text" id="txtSeksyen" value="$!Akta_IDSeksyen" size="25" $RO_General />            </td>-->
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No. Fail</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="socNoFail" type="text" id="socNoFail" value="$!Akta_NoFail" size="50" $RO_General />            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Catatan</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
            #if ($action == 'view')
           $!Akta_Catatan
             #else
			<textarea name="txtCatatan_" id="txtCatatan" cols="60" rows="10" onblur="this.value=this.value.toUpperCase()" $RO_General>$!Akta_Catatan</textarea> 
            
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
            <td valign="top"><input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" />
          #if($Akta_Doc!="")
           <a href = "javascript:viewAktaBlob('$Akta_IDAkta')">
     		 <font color="blue"><u>$Akta_Doc</u></font>
             </a>
      	   #end
            </td>
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
              <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!Akta_TarikhDaftar" size="10" $RO_General  onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
              <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>
            #if ($action == 'view')
            	<button type="button" value="Kemaskini" onclick="doKemaskiniAkta()"><font size = "1">Kemaskini</button>
              
			#elseif ($action == 'doUpdate')
				<button type="button" value="Simpan" onclick="doAdd()"><font size = "1">Simpan</button>
				<button type="button" value="Batal" onclick="doCancel()" ><font size = "1">Batal</button>
			#elseif ($action == 'update')
				<button type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveAkta()"><font size = "1">Simpan</button>
			#else
			
			#end 
            <button type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="doBack()" ><font size = "1">Kembali</button>
				
          </tr>
        </table>
        </fieldset>
        <br />
</div>
<script type="text/javascript" src="../app/pdt/akta.js"></script>
<script>
getDisableFieldDiv('mydiv','$action');
</script>