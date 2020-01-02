<style type="text/css">
<!--
 .mandatory {color: #FF0000}
-->
</style>
<input type="hidden" id="action" name="action" value="$action" />
<input type="hidden" id="mode" name="mode" value="$mode" />

<input name="tabId" type="hidden" id="tabId" value="$!tabId"/>
<input name="idEnakmen" type="hidden" id="idEnakmen" value="$!idEnakmen"/>
<input name="idEnakmenPinda" type="hidden" id="idEnakmenPinda" value="$!idEnakmenPinda"/>

<div id="mydiv">
<fieldset>
				  <legend><strong>Maklumat Enakmen Pindaan</strong></legend>
		          <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
			  <td width="29%" scope="row"><span class="mandatory">*</span>No. Enakmen Asal</td>
			  <td width="1%" scope="row">:</td>
			  <td width="70%"><label>
			    <input type="text" name="txtNoEnakmenAsal" value="$txtNoEnakmenAsal" $readOnly id="txtNoEnakmenAsal"/>
			  </label></td>
		    </tr>
		    <tr>
              <td scope="row"><span class="mandatory">*</span>No. Enakmen Pindaan</td>
		      <td scope="row">:</td>
		      <td><label>
                <input type="text" name="txtNoEnakmen1" value="$txtNoEnakmen" $readOnly id="txtNoEnakmen1"/>
              </label></td>
		      </tr>
		    <tr>
			  <td scope="row"><span class="mandatory">*</span>Nama Enakmen Pindaan</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input type="text" name="txtNamaEnakmen1" value="$txtNamaEnakmen" $readOnly/>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Kuatkuasa</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhKuatkuasa1" type="text" id="txtTarikhKuatkuasa1" size="10" value="$txtTarikhKuatkuasa" $readOnly onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" />
			    <a href="javascript:displayDatePicker('txtTarikhKuatkuasa1',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Mansuh</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhMansuh" type="text" id="txtTarikhMansuh" value="$txtTarikhMansuh" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly/>
			    <a href="javascript:displayDatePicker('txtTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">No. Warta</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readOnly/>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Warta</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhWarta" type="text" id="txtTarikhWarta" value="$txtTarikhWarta" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly/>
			    <a href="javascript:displayDatePicker('txtTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
            <!--
		    <tr>
			  <td scope="row">Taraf Keselamatan Enakmen</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input type="radio" name="sorTerbuka" id="sorTerbuka" onclick="check_tarafT()" $radioChecked1 $disabled/>
			    Terbuka
			    <input type="radio" name="sorSulit" id="sorSulit" onclick="check_tarafS()" $radioChecked2 $disabled/>
			    Sulit</label></td>
		    </tr>
            -->
            <!--
		    <tr>
			  <td scope="row">Seksyen / Urusetia</td>
			  <td scope="row">:</td>
			  <td><label>
			    $selectSeksyen
			  </label></td>
		    </tr>
            -->
		    <tr>
			  <td scope="row">No. Fail</td>
			  <td scope="row">:</td>
			  <td><label>
			  <input name="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="100" $readOnly id="txtNoFail"/>
			  </label></td>
		    </tr>
		    <tr>
			  <td valign="top" scope="row">Catatan</td>
			  <td valign="top" scope="row">:</td>
			  <td><label>
			    <input name="XtxtCatatan" type="hidden" value="$txtCatatan" $readOnly/>
	#if ($mode == 'view')
		$!txtCatatan
	#else	
	      <textarea name="txtCatatan_" cols="41" onkeyup="this.value=this.value.toUpperCase();" $readOnly>$!txtCatatan</textarea>
	 	<script> 
	 		
			var area1 = new FCKeditor("txtCatatan_");
			area1.ToolbarSet = 'PFD';
			area1.BasePath = '/${appname}/library/fck/' ;
			area1.Height = 130;
			area1.Width = 530;
			area1.ReplaceTextarea();    
			         	
		</script>
	#end			    
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Lampiran</td>
			  <td scope="row">:</td>
			  <td><label>
              <input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" />
              </label></td>
		    </tr>
       	  <tr>
            <td width="29%" valign="top"><i>Tag</i> Dokumen</td>
            <td valign="top">:</td> 
            <td valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $readOnly>$!tag_Dokumen</textarea>
            	<input name="id_tagdokumen" type="hidden" value="$!id_tagdokumen"/>
            </td>
          </tr>		    
		    <tr>
		      <td scope="row">Tarikh Daftar</td>
		      <td scope="row">:</td>
		      <td><input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" 
                  value="$txtTarikhDaftar" size="11" maxlength="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly /></td>
		    </tr>
		    <tr>
			  <td scope="row">&nbsp;</td>
			  <td scope="row"></td>
			  <td>&nbsp;</td>
		    </tr>
			  <tr>
			    <td scope="row">&nbsp;</td>
			    <td scope="row">&nbsp;</td>
			    <td>
				#if ($mode == 'update')
                  <label>
                  <input type="button" value="Simpan" onclick="javascript:simpanBaru()"/>
                  </label>
				#end
             	#if($mode == 'view')
					<label>
					<button type="button" value="Kemaskini" onclick="javascript:kemaskini();"><font size = "1">Kemaskini</font></button>
					</label>
					<label>
					<button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()"><font size = "1">Kembali</font></button>
					</label>
				#end
              	#if ($mode == 'new')
					<label>
					<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBaru()"/>
					</label>
					<label>
					<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
					</label>
				#end 
				</td>
		    </tr>
		</table>
</fieldset>
</div>
<script type="text/javascript" src="../app/pdt/enakmenpinda.js"></script>
<script>
getDisableFieldDiv('mydiv','$mode');
</script>