<style type="text/css">
<!--
 .mandatory {color: #FF0000}
-->
</style>
<input name="tabId" type="hidden" id="tabId" value="$!tabId"/>
<input name="idEnakmen" type="hidden" id="idEnakmen" value="$!idEnakmen"/>
<input name="checked" type="hidden" value="$!checked"/>
<fieldset>
				  <legend><strong>Maklumat Enakmen</strong></legend>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		    <tr>
			  <td width="29%" scope="row"><span class="mandatory">*</span>No Enakmen</td>
			  <td width="1%" scope="row">:</td>
			  <td width="70%"><label>
			    <input type="text" name="txtNoEnakmen1" value="$txtNoEnakmen" $readOnly/>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row"><span class="mandatory">*</span>Nama Enakmen</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input type="text" name="txtNamaEnakmen1" value="$txtNamaEnakmen" $readOnly/>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Kuatkuasa</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhKuatkuasa1" type="text" id="txtTarikhKuatkuasa1" size="10" value="$txtTarikhKuatkuasa" $readOnly/>
			    <a href="javascript:displayDatePicker('txtTarikhKuatkuasa1',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Mansuh</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhMansuh" type="text" id="txtTarikhMansuh" value="$txtTarikhMansuh" size="10" $readOnly/>
			    <a href="javascript:displayDatePicker('txtTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">No Warta</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readOnly/>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Tarikh Warta</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtTarikhWarta" type="text" id="txtTarikhWarta" value="$txtTarikhWarta" size="10" $readOnly/>
			    <a href="javascript:displayDatePicker('txtTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">Taraf Keselamatan Enakmen</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input type="radio" name="sorTerbuka" id="sorTerbuka" onclick="check_tarafT()" $radioChecked1 $disabled/>
			    Terbuka
			    <input type="radio" name="sorSulit" id="sorSulit" onclick="check_tarafS()" $radioChecked2 $disabled/>
			    Sulit</label></td>
		    </tr>
		    <tr>
			  <td scope="row">Seksyen / Urusetia</td>
			  <td scope="row">:</td>
			  <td><label>
			    $selectSeksyen
			  </label></td>
		    </tr>
		    <tr>
			  <td scope="row">No Fail</td>
			  <td scope="row">:</td>
			  <td><label>$selectFail</label></td>
		    </tr>
		    <tr>
			  <td scope="row">Catatan</td>
			  <td scope="row">:</td>
			  <td><label>
			    <input name="txtCatatan" type="text" id="txtCatatan" value="$txtCatatan" $readOnly/>
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
		      <td scope="row">Tarikh Daftar</td>
		      <td scope="row">:</td>
		      <td><input name="txtTarikhDaftar" type="text" id="txtTarikhDaftar" 
                  value="$txtTarikhDaftar" size="11" maxlength="10" $readOnly /></td>
	        </tr>
		    <tr>
			  <td scope="row">&nbsp;</td>
			  <td scope="row"></td>
			  <td>&nbsp;</td>
		    </tr>
			  <tr>
			    <td scope="row">&nbsp;</td>
			    <td scope="row">&nbsp;</td>
			    <td>#if ($mode == 'update')
                  <label>
                  <input type="button" value="Simpan" onclick="javascript:simpanLama()"/>
                  </label>
#end
                      #if($mode == 'view')
<label>
<input type="button" value="Kemaskini" onclick="javascript:kemaskini();"/>
</label>
<label>
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali()"/>
</label>
<label></label>
#end
                      #if ($mode == 'new')
<label>
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan Baru" onclick="javascript:simpanBaru()"/>
</label>
<label>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
</label>
#end		
                      #if ($mode =='new2')
<label>
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan Penggal" onclick="javascript:simpanPenggalBaru()"/>
</label>
#end </td>
		    </tr>
		</table>
</fieldset>
<script type="text/javascript" src="../app/pdt/enakmen.js"></script>