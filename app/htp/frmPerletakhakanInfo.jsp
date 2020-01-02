<style type="text/css">
<!--
.stylelabel{color: #0000FF}
-->
</style>
<fieldset>
<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td width="50%" align="center" valign="top">
			<table width="100%" border="0">
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
                <td width="1%">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$labelNegeri</div>               	
                </td>
              </tr>
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Kementerian</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelKementerian</div>               	
				</td>		
              </tr>
              <tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Agensi</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelAgensi</div>               	
				</td>		
              </tr>
              <tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tajuk</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelTajuk</div>               	
				</td>		
              </tr> 
        	</table>	
    	</td>
        <td width="50%" align="center" valign="top"><table width="100%" border="0">
        	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelNoFail</div>               	
				</td>		
              </tr> 
    		<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Lain</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelNoFailLain</div>               	
				</td>		
              </tr> 
             	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Surat KJP</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelTarikhSuratKJP</div>               	
				</td>		
              </tr> 
				<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Buka Fail</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$labelTarikhBukaFail</div>               	
				</td>		
              </tr>
        </table>
        </td>
      </tr>
    </tbody>
  </table> 
</fieldset>
<!--
#foreach ( $info in $Info )
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="99%" border="0">
<tr>
                <td width="25%"><div align="right"><strong>Kementerian :</strong></div></td>
                <td width="75%">$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><strong>Agensi :</strong></div></td>
                <td>$selectAgensi</td>
              </tr>
              <tr>
                <td><div align="right"><strong>Sub Urusan :</strong></div></td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Surat KJP :</strong></div></td>
                <td><input type="text" name="txdTarikhBukaFail2" id="txdTarikhBukaFail2" size="15" value="$info.tAgihan" readonly="readonly"></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Tajuk :</strong></div></td>
                <td><label>
                  <textarea name="Tajuk" id="Tajuk" cols="30" rows="3" disabled="disabled">$info.tajuk </textarea>
                </label></td>
              </tr>
        </table></td>
<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="38%"><div align="right"><strong>No. Fail Seksyen :</strong></div></td>
                <td width="62%"><input type="text" name="txtNoFailSek" size="30" value="$info.noFail" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail KJP :</strong></div></td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$info.tSurat" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail PTG :</strong></div></td>
                <td><input type="text" name="txdTarikhSurKJP2" id="txdTarikhSurKJP2" size="15" value="$info.tSurat" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail Lain :</strong></div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$info.noRujukan" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Permohonan :</strong></div></td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="15" value="$info.tPermohonan" readonly="readonly" /></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Buka Fail :</strong></div></td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$info.tAgihan" readonly="readonly"></td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
    </tbody>
  </table>
  #end
  -->