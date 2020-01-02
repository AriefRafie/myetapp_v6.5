
<input type="hidden" name="idAgensi" id="idAgensi">
<input type="hidden" name="idKementerian" id="idKementerian">


<fieldset style="width:95%;margin:auto">	

	<div style="text-align:center;font-size:12px;font-weight:bold">LAPORAN MAKLUMAT TANAH PESURUHJAYA TANAH PERSEKUTUAN BAGI $!namaKementerian</div>
	
	<br/>
	
	<table style="width:100%;" border="0"> 
		<tr class="table_header">
			<td align="center">BIL</td>
			<td>JABATAN/AGENSI</td>
			<td align="center"> 
				TANAH MILIK<br/>
				<div style="float:left">BILANGAN</div>
				<div style="float:right">KELUASAN (ha)</div>
			</td>
			<td align="center"> 
				TANAH RIZAB<br/>
				<div style="float:left">BILANGAN</div>
				<div style="float:right">KELUASAN (ha)</div>
			</td>
			<td align="center">KESELURUHAN<br/>
				<div style="float:left">BILANGAN</div>
				<div style="float:right">KELUASAN (ha)</div>
			</td>
		</tr>
		
		#if($listDbPTPByKementerianDetail.size()!=0)
			#foreach($list in $listDbPTPByKementerianDetail)
				#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end	
         		
				<tr>
					<td class="$row" align="center">$!list.bil</td>
					<td class="$row">
						<a href="javascript:doViewAgensiDetail('$!list.idAgensi')"><font color="blue">
							$!list.namaAgensi
						</font></a>
					</td>
					<td class="$row">
						<div style="float:left">$!list.bilTanahMilik</div>
						<div style="float:right">$!list.luasTanahMilikF</div>
					</td>
					<td class="$row">
						<div style="float:left">$!list.bilTanahRizab</div>
						<div style="float:right">$!list.luasTanahRizabF</div>
					</td>
					<td class="$row">
						<div style="float:left">$!list.bilTanah</div>
						<div style="float:right">$!list.luasKeseluruhanF</div>
					</td>
					<!-- <td class="$row" align="right">$!list.luasKeseluruhanF</td> -->
				</tr>
			#end
			<tr>
				<td colspan="2" align="right"><b>JUMLAH</b>&nbsp;</td>
				<td style="border-top: 1px solid black;">
					<div style="float:left">$!bilTanahMilikAll</div>
					<div style="float:right">$!totalTanahMilik</div>
				</td>
				<td style="border-top: 1px solid black;">
					<div style="float:left">$!bilTanahRizabAll</div>
					<div style="float:right">$!totalTanahRizab</div>
				</td>
				<td style="border-top: 1px solid black;">
					<div style="float:left">$!bilKeseluruhan</div>
					<div style="float:right">$!totalKeseluruhan</div>
				</td>
				<!-- <td style="border-top: 1px solid black;" align="right">$!totalKeseluruhan</td> -->
			</tr>
		#else
			<tr>
	        	<td colspan="4">Tiada rekod</td>
	        <tr>
		#end
		
	</table>
	
</fieldset>

<br/>

		<table style="width:100%">
			<tr>
				<td style="text-align:center">
					<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="doCetakKementerianDetail('$!idKementerian')" />
					<input type="button" name="cmdCarian" id="cmdCarian" value="Carian" onclick="doCarian()" />
					<input type="button" name="cmdMain" id="cmdMain" value="Kembali ke Muka Utama" onclick="doViewMain()" />
					<input type="button" name="cmdLaporanKementerian" id="cmdLaporanKementerian" value="Laporan Mengikut Kementerian" onclick="doViewReportKementerian()" />
				</td>
			</tr>
		</table>
		
