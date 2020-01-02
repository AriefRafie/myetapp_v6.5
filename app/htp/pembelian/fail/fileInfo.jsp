
<style type="text/css">
<!--
.stylelabel{color: #0000FF}
-->
</style>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
		<td width="50%" align="center" valign="top">
			<table width="100%" border="0">
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
                <td width="1%">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.namaNegeri</div>               	
                </td>
              </tr>
              <tr>
                <td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Kementerian</div></td>
                <td width="1%" valign="top">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.namaKementerian</div>               	
				</td>		
              </tr>
              <tr>
               <td width="35%" valign="top" style="text-transform:uppercase;"><div align="right">Agensi</div></td>
                <td width="1%" valign="top">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.namaAgensi</div>               	
				</td>		
              </tr>
              <tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Urusan</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.namaSuburusan</div>               	
				</td>		
              </tr>
              <tr>
               <td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">Tajuk</div></td>
                <td width="1%" valign="top">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tujuan</div>               	
				</td>		
              </tr> 
        	</table>	
    	</td>
        <td width="50%" align="center" valign="top"><table width="100%" border="0">
        	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.noFail</div> 
                	
                	#if($senaraihakmilikrizab.size()>0)
                	# Senarai Hakmilik/Rizab
                	#end           	
				</td>		
              </tr> 
    		<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail KJP</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.noRujukanKJP</div>               	
				</td>		
              </tr> 
             	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Surat KJP</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tarikhSurat</div>               	
				</td>		
              </tr> 
				<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Lain</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.noRujukanLain</div>               	
				</td>	
			<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Permohonan</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.tarikhTerima</div>               	
				</td>		
              </tr>
				<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Buka Fail</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$!htpPermohonan.permohonan.pfdFail.tarikhDaftarFail</div>               	
				</td>		
              </tr>
        </table>
        </td>
      </tr>

  </table>