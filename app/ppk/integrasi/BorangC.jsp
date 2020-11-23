<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style type="text/css">
<!-- 
input[readonly]{
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->

.borderFull {
	border-top: 1px solid #696969;
	border-bottom: 1px solid #696969;
	border-right: 1px solid #696969;
	border-left: 1px solid #696969;
}

.borderTop {
	border-top: 1px solid #696969;
}

.borderTopLeft {
	border-top: 1px solid #696969;
	border-left: 1px solid #696969;
}

.borderTopBottom {
	border-top: 1px solid #696969;
	border-bottom: 1px solid #696969;
}

.borderTopRight {
	border-top: 1px solid #696969;
	border-right: 1px solid #696969;
}

.borderTopLeftBottom {
	border-top: 1px solid #696969;
	border-bottom: 1px solid #696969;
	border-left: 1px solid #696969;
}

.borderTopRightBottom {
	border-top: 1px solid #696969;
	border-bottom: 1px solid #696969;
	border-right: 1px solid #696969;
}

.borderRight {
	border-right: 1px solid #696969;
}

.borderRightBottom {
	border-right: 1px solid #696969;
	border-bottom: 1px solid #696969;
}

.borderRightLeft {
	border-right: 1px solid #696969;
	border-left: 1px solid #696969;
}

.borderLeft {
	border-left: 1px solid #696969;
}

.borderLeftBottom {
	border-left: 1px solid #696969;
	border-bottom: 1px solid #696969;
}

.borderBottom {
	border-bottom: 1px solid #696969;
}
</style>
 
<body>
	<form name="f1">
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI BORANG C</font></legend>
			#if ( $kepBrgC != "" )
				<table border="0" cellpadding="3" cellspacing="0" align="center">
					<tr>
						<td class="borderTopLeftBottom" align="right">Keputusan Borang C</td>
						<td class="borderTopBottom">:</td>
						<td class="borderTopRightBottom">#if ( $kepBrgC == "WH" ) PUTIH #else KUNING #end</td>
					</tr>
					<tr>
						<td class="borderLeftBottom" align="right">Tarikh Jana Borang C oleh Mahkamah Tinggi</td>
						<td class="borderBottom">:</td>
						#if ( $trkhJanaBrgC )
							#set ( $dateJanaBrgC = $dateFormat.format($trkhJanaBrgC) )
						#end
						<td class="borderRightBottom">$dateJanaBrgC</td>
					</tr>
					
					<tr>
						<td class="borderLeftBottom" align="right">Muat Turun Borang C</td>
						<td class="borderBottom">:</td>
						<!-- <td class="borderRightBottom"><a href="http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode=$extrctKod"><font color="#0000FF"><b>Klik sini</b></font></a></td> -->
						<td class="borderRightBottom"><a href="#" onclick="checkLampiran('$extrctKod','$ID_KAD_BIRU');"><font color="#0000FF"><b>Klik sini</b></font></a></td>
					</tr>
					
					<tr>
						<td class="borderTopLeftBottom" align="right">Catatan</td>
						<td class="borderTopBottom">:</td>
						<td class="borderTopRightBottom">$!catatan</td>
					</tr>
					
				</table>
			#else
				<table border="0" cellpadding="3" cellspacing="0" align="center" width="100%">
					<tr>
						<td class="borderFull" align="center">Borang C Masih Belum Diterima</td>
					</tr>
				</table>				
			#end
			#foreach ($f in $MTFail)
				<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BORANG C - ADA PERMOHONAN AWAL</font></legend>
					<table border="0" cellpadding="3" cellspacing="0" align="center">
						<tr>
							<td class="borderTopLeftBottom" align="right">No Fail Awal</td>
							<td class="borderTopBottom">:</td>
							<td class="borderTopRightBottom">$!f.noFailAwal</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pemohon Awal</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">#if ($f.namaPemohonAwal != "") $f.namaPemohonAwal #else &nbsp; #end</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pejabat / Agensi</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!f.namaPejAgensi</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Tarikh Permohonan Awal</td>
							<td class="borderBottom">:</td>
							#if ( $f.tarikhPermohonanAwal )
								#set ( $datePermohonanAwal = $dateFormat.format($f.tarikhPermohonanAwal) )
							#end
							<td class="borderRightBottom">$!datePermohonanAwal</td>
						</tr>
						
						<tr>
							<td class="borderLeftBottom" align="right">Catatan</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!f.catatan</td>
						</tr>
						
					</table>
				</fieldset>	
			#end
			#foreach ($kv in $MTKaveat)
				<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BORANG C - ADA KAVEAT</font></legend>
					<table border="0" cellpadding="3" cellspacing="0" align="center">
						<tr>
							<td class="borderTopLeftBottom" align="right">No Kaveat</td>
							<td class="borderTopBottom">:</td>
							<td class="borderTopRightBottom">$!kv.noKaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Tarikh Kaveat</td>
							<td class="borderBottom">:</td>
							#if ( $kv.tarikhKaveat )
								#set ( $dateKaveat = $dateFormat.format($kv.tarikhKaveat) )
							#end
							<td class="borderRightBottom">$!dateKaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pengkaveat</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!kv.namaPengkaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Firma</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!kv.namaFirma</td>
						</tr>
					</table>
				</fieldset>	
			#end
		</fieldset>
	<!-- 
		<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN TERPERINCI BORANG C</font></legend>
			#foreach ($k in $keputusan)
			<table border="0" cellpadding="3" cellspacing="0" align="center">
				<tr>
					<td class="borderTopLeftBottom" align="right">Keputusan Borang C</td>
					<td class="borderTopBottom">:</td>
					<td class="borderTopRightBottom">#if ( $k.kepBrgC == "WH" ) PUTIH #else KUNING #end</td>
				</tr>
				<tr>
					<td class="borderLeftBottom" align="right">Tarikh Jana Borang C oleh Mahkamah Tinggi</td>
					<td class="borderBottom">:</td>
					#if ( $k.tarikhJanaBrgC )
						#set ( $dateJanaBrgC = $dateFormat.format($k.tarikhJanaBrgC) )
					#end
					<td class="borderRightBottom">$dateJanaBrgC</td>
				</tr>
				<tr>
					<td class="borderLeftBottom" align="right">Muat Turun Borang C</td>
					<td class="borderBottom">:</td>
					<td class="borderRightBottom"><a href="http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode=$k.brgCExtractKod"><font color="#0000FF"><b>Klik sini</b></font></a></td>
				</tr>
			</table>
			#end
			#foreach ($f in $fail)
				<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BORANG C - ADA PERMOHONAN AWAL</font></legend>
					<table border="0" cellpadding="3" cellspacing="0" align="center">
						<tr>
							<td class="borderTopLeftBottom" align="right">No Fail Awal</td>
							<td class="borderTopBottom">:</td>
							<td class="borderTopRightBottom">$!f.noFail</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pemohon Awal</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">#if ($f.namaPemohonAwal) $f.namaPemohonAwal #else &nbsp; #end</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pejabat / Agensi</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!f.namaPejAgensi</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Tarikh Permohonan Awal</td>
							<td class="borderBottom">:</td>
							#if ( $f.tarikhPermohonanAwal )
								#set ( $datePermohonanAwal = $dateFormat.format($f.tarikhPermohonanAwal) )
							#end
							<td class="borderRightBottom">$!datePermohonanAwal</td>
						</tr>
					</table>
				</fieldset>	
			#end
			#foreach ($kv in $kaveat)
				<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BORANG C - ADA KAVEAT</font></legend>
					<table border="0" cellpadding="3" cellspacing="0" align="center">
						<tr>
							<td class="borderTopLeftBottom" align="right">No Kaveat</td>
							<td class="borderTopBottom">:</td>
							<td class="borderTopRightBottom">$!kv.noKaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Tarikh Kaveat</td>
							<td class="borderBottom">:</td>
							#if ( $kv.tarikhKaveat )
								#set ( $dateKaveat = $dateFormat.format($kv.tarikhKaveat) )
							#end
							<td class="borderRightBottom">$!dateKaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Pengkaveat</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!kv.namaPengkaveat</td>
						</tr>
						<tr>
							<td class="borderLeftBottom" align="right">Nama Firma</td>
							<td class="borderBottom">:</td>
							<td class="borderRightBottom">$!kv.namaFirma</td>
						</tr>
					</table>
				</fieldset>	
			#end
		</fieldset>
		 -->
	</form>
	
	</body>
	<script>
	
	function checkLampiran(extrctKod,ID_KAD_BIRU){
		
		if(extrctKod==''){
			
			//open new PDF
			  doOpen(ID_KAD_BIRU) ;
			    
		}else{
			
			
			//open old URL from MT
			 var url = "http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode="+extrctKod;
			    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
			    if ((document.window != null) && (!hWnd.opener))
			    hWnd.opener = document.window;
			    if (hWnd.focus != null) hWnd.focus();
			  
		}
	}

	function doOpen(id) {
		
		
		
		
	    var url = "../../servlet/ekptg.view.ppk.DisplayBlobKeputusanBorangC?id="+id;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	</script>

