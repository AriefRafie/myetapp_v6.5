<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<p>&nbsp;</p>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="actionPajakan" id="actionPajakan" value="$!actionPajakan"/>
  <input type="hidden" name="mode" id="mode" value="$!mode"/>
  <input type="hidden" name="hitButton" id="hitButton" value="$!hitButton"/> 
  <input type="hidden" name="idFail" id="idFail" value="$!idFail"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$!idStatus"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	##if($!pageMode != 'edit')
	<tr>
		<td>
		##parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr> 
	##end
  <tr>
    <td colspan="2">
    
    <fieldset>
    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
    
   		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
         <tr>
          	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Negeri</td>
            <td width="1%">:</td>
            <td width="70%">$selectNegeri</td>
         </tr>
         <tr>
          	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Kementerian</td>
            <td>:</td>
            <td>$selectKementerian</td>
         </tr>
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Agensi</td>
            <td>:</td>
            <td>$selectAgensi</td>
         </tr>
         <tr>
         	<td>&nbsp;</td>
            <td>Urusan</td>
            <td>:</td>
            <td>882 - PAJAKAN TANAH</td>
         </tr>
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Sub Urusan</td>
            <td>:</td>
            <td>$selectSuburusan</td>
         </tr>
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td>Status Tanah</td>
            <td>:</td>
            <td>$selectStatusTanah</td>
         </tr>
         <tr>
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
         	<td>Jenis Fail</td>
            <td>:</td>
            <td>$selectJenisFail</td>
         </tr>
         <tr>
         	<td width="1%">&nbsp;</td>
            <td width="28%">No. Fail Jabatan</td>
            <td width="1%">:</td>
            <td width="70%"><input type="text" name="txtNoFail" id="txtNoFail" size="43" disabled="disabled" class="disabled" value="$beanMaklumatPermohonan.noFail"/></td>
         </tr>
         <tr>
         	<td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
         	</td>
            <td>No. Fail KJP</td>
            <td>:</td>
            <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" size="43" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFailKJP" onblur="this.value=this.value.toUpperCase();" /></td>
         </tr>
         <tr>
         	<td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
         	</td>
            <td valign="top">Tarikh Surat KJP</td>
            <td>:</td>
            <td><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            </td>
         </tr>
         <tr>
         <td style="visibility:hidden">#if ($mode != 'view')<span class="style1">*</span>#end </td>
         <td>No. Fail Lain /  Pemohon</td>
         <td>:</td>
         <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="43" value="$beanMaklumatPermohonan.noFailLain" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
         </tr>
         <tr>
          <td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
          	</td>
        	<td>Tarikh Surat Pemohon</td>
            <td>:</td>
            <td>            	
            	<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
				#if ($mode != 'view') 
					<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/> 
				#end 
			</td>         
         </tr>
         <tr style="display:none">
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tarikh Agihan</td>
            <td>:</td>
            <td><input type="text" name="tarikhAgihan" id="tarikhAgihan" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhAgihan"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr>
         <tr>
         	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
           <td valign="top">Tajuk</td>
            <td valign="top">:</td>
            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="41" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea></td>
         </tr>
         #end
        </table>
    </fieldset>    
    </td>
  </tr>
<!--  <tr>
    <td colspan="2">&nbsp;</td>
  </tr> -->
  	#if ($mode != 'view')
  	<tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
 	</tr>
  	#end
	
	<tr>
    	<td align="center">
    	<!-- <td width="30%">&nbsp;</td>
    	<td width="70%"> -->
    	#if ($mode == 'new')
    		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanDaftar()"/>
    		<input type="button" class="stylobutton100" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalDaftar()"/>
    	#else
    		<input type="button" class="stylobutton100" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="seterusDaftar($!idFail)"/>
    	#end
    	</td>
	</tr>

</table>


##parse("app/htp/utiliti/javascript/javaScriptPajakanDaftar.jsp")
