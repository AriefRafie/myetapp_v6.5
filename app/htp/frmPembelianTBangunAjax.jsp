#set ($NoPetak = "")
#set ($NoBangunan = "")
#set ($NoTingkat = "")
#set ($IdHakmilikurusan = "")
#foreach ( $tbangun in $TBangun )
	#set ($NoPetak = $tbangun.NoPetak)
    #set ($NoBangunan = $tbangun.NoBangunan)
    #set ($NoTingkat = $tbangun.NoTingkat)
    #set ($IdHakmilikurusan = $tbangun.IdHakmilikurusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdHakmilikurusan != "")
	#set ($btnName = "value='Batal'")
#end

<br/>
<fieldset>
<legend>Pendaftaran Tanah & Bangunan</legend>

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%" height="25"><div align="right"><font color="#FF0000">*</font>No. Hakmilik :</div></td>
            <td width="67%">$selectNoHakmilik</td>
          </tr>
          <tr>
            <td><div align="right">No. Bangunan :</div></td>
            <td><input type="text" name="txtNoBangunan" id="txtNoBangunan" size="10" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$NoBangunan" $mode /></td>
          </tr>
        </table></td>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>No. Petak :</div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoPetak" id="txtNoPetak" size="10" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$NoPetak" $mode >
            </label></td>
          </tr>
          <tr>
            <td><div align="right">No. Tingkat :</div></td>
            <td width="67%"><label><input type="text" name="txtNoTingkat" id="txtNoTingkat" maxlength="5" size="10" onkeyup="this.value=this.value.toUpperCase();" value="$NoTingkat" $mode></label></td>
          </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"></td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPTBA_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPTBA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fPTBA_Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPTBA_Kembali()">&nbsp;&nbsp;<input type="button" style="display:none" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fPTBA_seterusnya()"></td>
      </tr>
    </tbody>
  </table>  
  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">


</fieldset>
