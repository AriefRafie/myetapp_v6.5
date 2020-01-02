
<input type="hidden" name="idHakmilik" id="idHakmilik">
<input type="hidden" name="idAgensi" id="idAgensi">


<fieldset style="width:95%;margin:auto">	

	<div style="text-align:center;font-size:12px;font-weight:bold">
		LAPORAN MAKLUMAT TANAH PESURUHJAYA TANAH PERSEKUTUAN BAGI $!namaAgensi.toUpperCase() , $!namaKementerian.toUpperCase() </div>
	
	<br/>
	
	#if($listDbPTPByAgensiDetail.size() > 20)
      <div style="width:100%;height:500;overflow:auto"> 
    #end 
    
	<table style="width:100%;" border="0"> 
		<tr class="table_header">
			<td align="center">BIL</td>
			<td>NO.HAKMILIK/WARTA</td>
			<td>NO.LOT</td>
			<td>MUKIM</td>
			<td>DAERAH</td>
			<td>MILIK/RIZAB</td>
			<td>CUKAI TAHUNAN</td>
			<td>KELUASAN (ha)</td>
		</tr>
		
		#if($listDbPTPByAgensiDetail.size()!=0)
			#foreach($list in $listDbPTPByAgensiDetail)
				#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end	
         		
				<tr>
					<td class="$row" align="center">$!list.bil</td>
					<td class="$row">$!list.no_hakmilik</td>
					<td class="$row">
						<a href="javascript:doViewLot('$!list.id_hakmilik','$!list.jenis_tanah')"><font color="blue">
							$!list.jenis_lot $!list.no_lot
						</font></a>
					</td>
					<td class="$row">$!list.nama_mukim</td>
					<td class="$row">$!list.nama_daerah</td>
					<td class="$row">$!list.jenis_tanah</td>
					<td class="$row" align="right">RM $!list.cukaiF</td>
					<td class="$row" align="right">$!list.luasF</td>
				</tr>
			#end
			<tr>
				<td colspan="7" align="right"><b>JUMLAH</b>&nbsp;</td>
				<td style="border-top: 1px solid black;" align="right">$!totalLuas</td>
			</tr>
		#else
			<tr>
	        	<td colspan="4">Tiada rekod</td>
	        <tr>
		#end
		
	</table>
	
	#if($listDbPTPByAgensiDetail.size() > 20)
		</div>
	#end
	
</fieldset>

<br/>

		<table style="width:100%">
			<tr>
				<td style="text-align:center">
					<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="doCetakAgensiDetail('$!idAgensi')" />
					<input type="button" name="cmdCarian" id="cmdCarian" value="Carian" onclick="doCarian()" />
					<input type="button" name="cmdMain" id="cmdMain" value="Kembali ke Muka Utama" onclick="doViewMain()" />
					<input type="button" name="cmdLaporanKementerian" id="cmdLaporanKementerian" value="Laporan Mengikut Kementerian" onclick="doViewReportKementerian()" />
				</td>
			</tr>
		</table>
