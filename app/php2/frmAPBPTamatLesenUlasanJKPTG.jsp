<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizUlasanJKPTG="1000")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td>   
      <table align="center" width="100%">
         #foreach($beanMaklumatJKPTG in $BeanMaklumatJKPTG)
        <tr>
          <td width="1%" valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Ulasan JKPTG</td>
          <td width="1%" valign="top">:</td>
          <td width="68%" valign="top"><textarea name="ulasanJKPTG" class="$inputTextClass"  $readonly cols="40" rows="5" id="ulasanJKPTG" onKeyUp="textCounter(this.form.ulasanJKPTG,this.form.remLen4,$!saizUlasanJKPTG);" onKeyDown="textCounter(this.form.ulasanJKPTG,this.form.remLen4,$!saizUlasanJKPTG);">$beanMaklumatJKPTG.ulasanJKPTG</textarea></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen4" size="3" maxlength="4" value="$!saizUlasanJKPTG" /></td>
        </tr>
        #end
        #if ($mode == 'update')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> 
          #if ($mode == 'update')
            <input type="button" name="cmdDaftarBaru" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniJKPTG()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBatalJKPTG()"/>
          #end 
          #if ($mode == 'view')
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniJKPTG()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBackToList()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
          #end
        #end
      </table>
    </td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasKerjaPenamatan('$idFail')"> Kertas Kerja Penamatan</a></td>
  </tr>
</table>
</fieldset>