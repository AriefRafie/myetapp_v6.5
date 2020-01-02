
#if($!div_maklumatPenyewaan_open == "Y")

	#foreach($data in $dataPHPPenyewaan)
		#set($dbNofail = $data.NO_FAIL)
		#set($dbNamaPemohon = $data.NAMA_PEMOHON)
		#set($dbNoSiri = $data.NO_SIRI)
		#set($dbTarikhMulaPerjanjian = $data.TARIKH_MULA_PERJANJIAN)
		#set($dbTarikhTamatPerjanjian = $data.TARIKH_TAMAT_PERJANJIAN)
		#set($dbTempoh = $data.TEMPOH)
		#set($dbKadarSewa = $data.KADAR_SEWA)
		#set($dbTujuan = $data.TUJUAN)
	#end
		
<table width="100%">
	<tr>
		<td width="50%" valign="top">
			<table width="100%" border="0">
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">No. Fail</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbNofail
					</td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Nama Penyewa</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbNamaPemohon</td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Tujuan</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbTujuan</td>
				</tr>
			</table>
		</td>

		<td valign="top">
			<table width="100%">
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">No. Siri Perjanjian</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbNoSiri</td>
				</tr>

				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Tarikh Mula</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbTarikhMulaPerjanjian</td>
				</tr>

				<tr>
					<td width="1%"></td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Tarikh Tamat</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbTarikhTamatPerjanjian</td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Tempoh</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbTempoh #if($!dbTempoh!="")bulan #end</td>
				</tr>
				<tr>
					<td width="1%">&nbsp;</td>
					<td width="30%">
						<div align="left">
							<span class="labelinput">Kadar Sewa (RM)</span>
						</div>
					</td>
					<td width="1%" class="labelinput">:</td>
					<td width="68%" class="pautanms1">$!dbKadarSewa</td>
				</tr>

			</table>
		</td>
	</tr>
</table>
#end
