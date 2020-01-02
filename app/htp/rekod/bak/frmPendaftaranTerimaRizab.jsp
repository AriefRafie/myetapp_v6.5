
<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
-->
</style>
<input name="action" type="hidden" value="$action" />
<input name="idPermohonan" type="text" value="$idPermohonan" />
<input name="mode" type="hidden" value="$mode" />
<input name="cetak" type="hidden" value="$cetak" />
<fieldset><legend>PENDAFTARAN RIZAB</legend>
#parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFail.jsp")
<table width="99%" border="0">
 <!-- <tr>
    <td>
    	
    </td><fieldset>
    <table width="100%" border="0">
      <tr>
        <td width="25%"><div align="right">Kementerian</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="24%"><span class="style1">$txtNamaKementerian</span></td>
        <td width="23%" ><div align="right">No. Fail KJP</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="26%"><span class="style1">$txtFailKJP</span></td>
      </tr>
      <tr>
        <td><div align="right">Agensi Pengawal</div></td>
        <td><div align="center">:</div></td>
        <td><span class="style1">$txtNamaAgensi</span></td>
        <td><div align="right">No. Fail PTG</div></td>
        <td><div align="center">:</div></td>
        <td><span class="style1">$txtFailPTG</span></td>
      </tr>
      <tr>
        <td><div align="right">No. Fail Seksyen</div></td>
        <td><div align="center">:</div></td>
        <td><span class="style1">$txtNoFailSeksyen</span></td>
        <td><div align="right">No. Fail PTD</div></td>
        <td><div align="center">:</div></td>
        <td>&nbsp;</td>
      </tr>
  
      <tr>
        <td><div align="right">Tajuk</div></td>
        <td><div align="center">:</div></td>
        <td><span class="style1">$txtTajuk</span></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="6"><hr /></td>
      </tr>
</table>
-->
    <table width="100%" border="0" align="center">
      <tr>
        <td><div align="right">Negeri</div></td>
        <td><div align="center">:</div></td>
        <td>$txtNamaNegeri</td>
      </tr>
      <tr>
        <td><div align="right">Daerah</div></td>
        <td><div align="center">:</div></td>
        <td>$txtNamaDaerah</td>
      </tr>
      <tr>
        <td><div align="right">Bandar/Pekan/Mukim</div></td>
        <td><div align="center">:</div></td>
        <td>$txtNamaMukim</td>
      </tr>
      <tr>
        <td><div align="right">Kod</div></td>
        <td><div align="center">:</div></td>
        <td>$selectLot</td>
      </tr>
      <tr>
        <td><div align="right">No Lot</div></td>
        <td><div align="center">:</div></td>
        <td><input type="text" name="txtNoLot" id="txtNoLot" /></td>
      </tr>	
      <tr>
        <td><div align="right">No. Warta</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readonly class="$disabled"/></td>
      </tr>
      <tr>
        <td><div align="right">Tarikh Warta</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="9" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/>
        #if ($mode == 'update') <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style2">dd/mm/yyyy </span> #end</a></td>
      </tr>
      <tr>
        <td width="526"><div align="right">Tarikh Terima</div></td>
        <td width="10"><div align="center">:</div></td>
        <td width="663"><input name="txdTarikhTerima" type="text" id="txdTarikhTerima" onkeyup="validateNumber(this,this.value);" value="$txdTarikhTerima" size="9" $readonly class="$disabled"/>
        #if ($mode == 'update') 
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style2"> dd/mm/yyyy </span>
        #end</td>
      </tr>
      <tr>
        <td><div align="right"><i><font color="#ff0000">* </font></i>Lokasi</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>        </td>
      </tr>
      <tr>
        <td><div align="right"><i><font color="#ff0000">* </font></i>Kegunaan Tanah</div></td>
        <td><div align="center">:</div></td>
        <td><textarea name="txtKegunaanTanah" cols="30" id="txtKegunaanTanah" $readonly="$readonly" class="$disabled" style="text-transform:uppercase;">$txtKegunaanTanah</textarea>        </td>
      </tr>
      <tr>
        <td height="23"><div align="right"> <i><font color="#ff0000">*</font></i> Unit/ Luas</div></td>
        <td><div align="center">:</div></td>
        <td>$selectLuas</td>
      </tr>
      <tr>
        <td><div align="right"><i><font color="#ff0000">*</font></i> Luas</div></td>
        <td><div align="center"></div></td>
        <td><input name="txtLuas" type="text" class="$disabled" id="txtLuas" onkeyup="validateNumber(this,this.value);" value="$txtLuas" size="20" $readonly/></td>
      </tr>
      <tr>
        <td><div align="right">Luas Bersamaan</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtLuasBersamaan2" type="text" id="txtLuasBersamaan2" size="20" readonly class="disabled"/>        </td>
      </tr>
      <tr>
        <td><div align="right">No. Pelan</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="20" $readonly class="$disabled" />        </td>
      </tr>
      <tr>
        <td><div align="right">No. Syit Piawai</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" $readonly class="$disabled" />
          </label>        </td>
      </tr>
      <tr>
        <td><div align="right">No. PU</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="20" $readonly class="$disabled" />        </td>
      </tr><input name="socStatus" type="hidden" id="socStatus" value="TIADA">
      <!--<tr>
        <td><div align="right">Status Sah</div></td>
        <td><div align="center">:</div></td>
        <td> 
        	
         <select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled>
            #if($socStatus == 'D')
          		<option value="">SILA PILIH</option>
            	<option value="D" selected="selected">D - DAFTAR</option>
            	<option value="H">H - HAKMILIK ASAL TIADA</option>
            #elseif($socStatus == 'H')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="H" selected="selected">H - HAKMILIK ASAL TIADA</option>
            #else
                <option value="" selected="selected">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="H">H - HAKMILIK ASAL TIADA</option> 
        	#end
        </select> 
                </td>
      </tr>-->
      <!--<tr>
        <td><div align="right">Pegawai Akhir Kemaskini</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtPegawaiAkhir" type="text" id="txtPegawaiAkhir" size="30" $readonly class="$disabled"/>        </td>
      </tr>
      <tr>
        <td><div align="right">Tarikh Akhir Kemaskini</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="9" $readonly class="disabled"/>
            <a href="javascript:displayDatePicker('txdTarikhKemaskini',false,'dmy');"></td>
      </tr> -->
      <tr>
        <td><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
</table>
<div align="center">
  <p>
  	#if ($mode == 'view')
    <input type="button" class="stylobutton" name="btnUpdate" id="btnUpdate" value="Kemaskini" onclick="kemaskini_detailRizab($idPermohonan)"/>
    #end
    
    #if ($mode == 'update')  
    <input type="button" class="stylobutton" name="btnSaveRizab" id="btnSaveRizab" value="Simpan" onclick="SimpanRizab($idPermohonan);"/>
    <input type="reset" class="stylobutton" name="btnResetRizab" id="btnResetRizab"  value="Batal"/>
    #end
    <input type="button" class="stylobutton" name="btnBackRizab" id="btnBackRizab" value="Kembali" onclick="kembaliRizab()"/>

  </p>
</div>
##parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<script>
/* Azam remark first
function kemaskini_detailRizab(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
function update_detailRizab(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&idHakmilik="+id;
	document.${formName}.submit();
}
function kembaliRizab(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah2&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}
*/
</script>