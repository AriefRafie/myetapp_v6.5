<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizUlasanMenteri="1000")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td>     
      <table align="center" width="100%">
         #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
           <tr>
             <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
             <td valign="top">Tarikh Keputusan</td>
             <td valign="top">:</td>
             <td valign="top"><input name="tarikhKeputusan" type="text" class="$inputTextClass"  id="tarikhKeputusan" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly onBlur="check_date(this);"/>
               <a href="javascript:displayDatePicker('tarikhKeputusan',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end </td>
           </tr>
           <tr>
             <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
             <td valign="top">Keputusan</td>
             <td valign="top">:</td>
             <td valign="top"><select name="socKeputusan" id="socKeputusan" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass >        
        	     #if($beanMaklumatKeputusan.socKeputusan=='1')
                   <option value="">SILA PILIH</option>
                   <option value="1" selected="selected">1 - LULUS</option>
                   <option value="2">2 - TOLAK</option>
                #elseif ($beanMaklumatKeputusan.socKeputusan=='2')
                   <option value="">SILA PILIH</option>
                   <option value="1">1 - LULUS</option>
                   <option value="2" selected="selected">2 - TOLAK</option>              
                #elseif ($beanMaklumatKeputusan.socKeputusan=='')
                   <option value="" selected="selected">SILA PILIH</option>
                   <option value="1">1 - LULUS</option>
                   <option value="2">2 - TOLAK</option>
                #end                    
             </select>             </td>
           </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode == 'update')<span class="style1"></span>#end</td>
          <td width="30%" valign="top">Ulasan Menteri</td>
          <td width="1%" valign="top">:</td>
          <td width="68%" valign="top"><textarea name="ulasanMenteri" class="$inputTextClass"  $readonly cols="40" rows="5" id="ulasanMenteri" onKeyUp="textCounter(this.form.ulasanMenteri,this.form.remLen3,$!saizUlasanMenteri);" onKeyDown="textCounter(this.form.ulasanMenteri,this.form.remLen3,$!saizUlasanMenteri);">$beanMaklumatKeputusan.ulasanMenteri</textarea></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!saizUlasanMenteri" /></td>
        </tr>
        
        #end
        
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1"></span>#end</td>
          <td valign="top">Status Lesen</td>
          <td valign="top">:</td>
          <td valign="top"><select name="socFlagLesen" id="socFlagLesen" style="width:160px;" $readonly class="$inputTextClass" $inputTextClass >
                    
        	     #if($beanMaklumatKeputusan.socFlagLesen=='1')
                   
            <option value="">SILA PILIH</option>
            <option value="1" selected="selected">1 - SUDAH DIHANTAR</option>
            <option value="2">2 - BELUM DIHANTAR</option>
            
                #elseif ($beanMaklumatKeputusan.socFlagLesen=='2')
                   
            <option value="">SILA PILIH</option>
            <option value="1">1 - SUDAH DIHANTAR</option>
            <option value="2" selected="selected">2 - BELUM DIHANTAR</option>
                          
                #elseif ($beanMaklumatKeputusan.socFlagLesen=='')
                   
            <option value="" selected="selected">SILA PILIH</option>
            <option value="1">1 - SUDAH DIHANTAR</option>
            <option value="2">2 - BELUM DIHANTAR</option>
            
                #end                    
             
          </select>
          </td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1"></span>#end</td>
          <td valign="top">Status Tunggakan</td>
          <td valign="top">:</td>
          <td valign="top"><select name="socFlagTunggakan" id="socFlagTunggakan" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass >
            
                    
        	     #if($beanMaklumatKeputusan.socFlagTunggakan=='1')
                   
            
            <option value="">SILA PILIH</option>
            <option value="1" selected="selected">1 - YA</option>
            <option value="2">2 - TIDAK</option>
            
            
                #elseif ($beanMaklumatKeputusan.socFlagTunggakan=='2')
                   
            
            <option value="">SILA PILIH</option>
            <option value="1">1 - YA</option>
            <option value="2" selected="selected">2 - TIDAK</option>
            
                          
                #elseif ($beanMaklumatKeputusan.socFlagTunggakan=='')
                   
            
            <option value="" selected="selected">SILA PILIH</option>
            <option value="1">1 - YA</option>
            <option value="2">2 - TIDAK</option>
            
            
                #end                    
             
          
          </select>
          </td>
        </tr>
        
        #if ($mode == 'update')
        <tr>
          <td colspan="4" valign="bottom">&nbsp;</td>
        </tr>
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
            <input type="button" name="cmdDaftarBaru" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniMaklumatKeputusan()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBatalKeputusan()"/>
          #end 
          #if ($mode == 'view')
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniKeputusan()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBackToList()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport2')"/>
            #if($idStatus != '1610244')
            <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="seterusnya()"/>
          	#end
          #end
        #end
      </table>
    </td>
  </tr>
  #end
</table>
<fieldset id="tableReport2" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
    <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratPenamatan('$idFail')"> Surat Penamatan Lesen </a></td>
  </tr>
</table>
</fieldset>