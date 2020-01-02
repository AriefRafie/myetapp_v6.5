<script>
document.getElementById("div_viewAduan_$internalType$ID_ESADUAN").style.display="";
if( $jquery('#'+'div_mainRowAduan_$internalType$ID_ESADUAN').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_mainRowAduan_$internalType$ID_ESADUAN').offset().top);
}

</script>

<tr >
<td align="left" valign="top" colspan="9">
<fieldset>
<legend>NO. LOG : $viewPengunaPLA.LOG_ADUAN</legend>

<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Pengadu	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.PENGADU	
				</td>
			</tr>
			#if($viewPengunaPLA.NAMA_PEJABAT!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Pejabat Pengadu	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.NAMA_PEJABAT	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.NEGERI_PENGADU!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.NEGERI_PENGADU	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.LOG_ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Log	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.LOG_ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.NAMA_SUMBER!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Sumber Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.NAMA_SUMBER	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.JENIS_ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.JENIS_ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.TARIKH_ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Hantar Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.TARIKH_ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.NAMA_SKRIN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Skrin	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.NAMA_SKRIN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keterangan Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.STATUS!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status Semasa Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.STATUS	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.TARIKH_TERIMA_ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Terima Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.TARIKH_TERIMA_ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.TARIKH_SELESAI_ADUAN!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Selesai Aduan	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.TARIKH_SELESAI_ADUAN	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.ULASAN_TEKNIKAL!="")
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jutifikasi Teknikal	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengunaPLA.ULASAN_TEKNIKAL	
				</td>
			</tr>
			#end
			
			#if($viewPengunaPLA.FLAG_MASALAH_DB=="Y" || 
			$viewPengunaPLA.FLAG_MASALAH_FLOW=="Y" ||
			$viewPengunaPLA.FLAG_MASALAH_HW=="Y" ||
			$viewPengunaPLA.FLAG_MASALAH_PENAMBAHAN=="Y" ||
			$viewPengunaPLA.FLAG_MASALAH_REPORT=="Y" ||
			$viewPengunaPLA.FLAG_MASALAH_SKRIN=="Y" ||
			$viewPengunaPLA.FLAG_MASALAH_PERTANYAAN=="Y" )
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Klasifikasi Teknikal	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengunaPLA.FLAG_MASALAH_PENAMBAHAN=="Y")
				- PENAMBAHAN BAHARU<BR>
				#end
				#if($viewPengunaPLA.FLAG_MASALAH_PERTANYAAN=="Y")
				- PERTANYAAN<BR>
				#end
				#if($viewPengunaPLA.FLAG_MASALAH_DB=="Y")
				- ISU PENGKALAN DATA<BR>
				#end
				#if($viewPengunaPLA.FLAG_MASALAH_FLOW=="Y")
				- ISU PROSES FLOW<BR>
				#end
				#if($viewPengunaPLA.FLAG_MASALAH_HW=="Y")
				- ISU HARDWARE<BR>
				#end				
				#if($viewPengunaPLA.FLAG_MASALAH_REPORT=="Y")
				- ISU REPORTING (JASPER)<BR>
				#end
				#if($viewPengunaPLA.FLAG_MASALAH_SKRIN=="Y")
				- ERROR / BUGS PADA SKRIN
				#end
				
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
					
				</td>
				<td valign="top" >
				
				</td>
				<td valign="top" >
				<input type="button" id="BTNCLOSE$internalType$viewPengunaPLA.ID_ESADUAN" name="BTNCLOSE$internalType$viewPengunaPLA.ID_ESADUAN" 
				onClick="doDivAjaxCall$formname('div_viewAduan_$internalType$viewPengunaPLA.ID_ESADUAN','close_viewPla','internalType=$internalType&ID_ESADUAN=$viewPengunaPLA.ID_ESADUAN');" value="Tutup" > 
	   		
				</td>
			</tr>
		
			
				
			
			
</table>
</fieldset>
<BR>
</td>
</tr>
