<style type="text/css">

.style1 {
	color: #0033FF
}

</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input type="hidden" name="actionPenyewaan" />
  <input type="hidden" name="idFail" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idPemohon" />
  <input type="hidden" id="namamodul" name="namamodul"  >
  <input type="hidden" id="namatab" name="namatab"  >
</p>

<!-- Carian -->
#parse("app/ppt/frmPampasanOnlineCarian.jsp")

<br/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>Senarai Pampasan</b></legend>
      <table align="center" width="100%">
        
		<tr class="table_header">
          <td scope="row"  align="center"><strong>Bil</strong></td>
          <td ><strong>No EFT / No Cek</strong></td>
          <td ><strong>Nama Projek</strong></td>
          <td  align="left"><strong>No Fail</strong></td>
          <td  align="left"><strong>Kaedah Pembayaran</strong></td>
        </tr>
        
        #set ($list = "")
        #if ($listSuratAgensiOnline.size() > 0)
        #foreach ($list in $listSuratAgensiOnline)
        
        #if ($list.bil == '')
        	#set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        	#set( $row = "row1" )
        #else 
        	#set( $row = "row2" )
        #end
        
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a id="hoverover" style="cursor:pointer; color:#0000FF" onClick="ShowPopup(this,$list.bil);"  title="Klik untuk maklumat lengkap">
          $list.NO_RUJUKAN
          </a></td>
          <td class="$row">$list.PROJEK</td>
          
          <td class="$row" align="left">$list.NO_FAIL</td>
          <td class="$row" align="left">
          #if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
		 				Cek
		 			#elseif($list.CARA_BAYAR == "2")
		 				EFT
		 			#end
          </td>
        </tr>
        
        <tr>
        <td colspan="7">
		<fieldset id="$list.bil" style="visibility:collapse; display:none;">
 			<legend><strong>
 			#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 				Maklumat Penerimaan Cek
 			#elseif($list.CARA_BAYAR == "2")
 				Maklumat EFT
 			#end
 			
 			</strong></legend>
 				<table width="100%" border="0">
 					<tr>
 						<td>&nbsp;</td>
 						<td>Kaedah Pembayaran</td>
 						<td>:</td>
 						<td>
 					#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
		 				Cek
		 			#elseif($list.CARA_BAYAR == "2")
		 				EFT
		 			#end
 						</td>
 					</tr>
 					<!-- <tr>
 						<td width="1%"></td>
 						<td width="25%">Nama PB</td>
 						<td width="1%">:</td>
 						<td width="73%">$list.nama_pb</td>
 					</tr>
 				
 					<tr>
 						<td>&nbsp;</td>
 						<td>No. PB</td>
 						<td>:</td>
 						<td>
 						$list.no_pb
 						</td>
 					</tr> -->
 					<tr>
 						<td width="1%"></td>
 						<td width="25%">No. Hakmilik</td>
 						<td width="1%">:</td>
 						<td width="73%">$list.NO_HAKMILIK</td>
 					</tr>
 				
 					<tr>
 						<td>&nbsp;</td>
 						<td>No. Lot</td>
 						<td>:</td>
 						<td>
 						$list.NO_LOT
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>
 						Tarikh Akhir Bayar Pampasan
 						</td>
 						<td>:</td>
 						<td>
 						$list.TARIKH_AKHIR_BAYARAGENSI
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>Bil. Hari Lewat</td>
 						<td>:</td>
 						<td>
 						$list.BIL_HARI_LEWAT
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>Caj Bayaran Lewat</td>
 						<td>:</td>
                       <td>
 						RM $list.DENDA_LEWAT
                       </td>
 					</tr>
 					<!-- <tr>
 						<td>&nbsp;</td>
 						<td>Jenis Pampasan</td>
 						<td>:</td>
 						<td>
 						#if($list.jenis_award=="1")
 						PAMPASAN PENTADBIR TANAH
 						#elseif($list.jenis_award=="2")
 						PAMPASAN BANTAHAN MAHKAMAH
 						#elseif($list.jenis_award=="3")
 						PAMPASAN AKIBAT PU
 						#end
 						</td>
 					</tr> -->
 					<tr>
 						<td>&nbsp;</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 						Status Serah Cek
 						#elseif($list.CARA_BAYAR == "2")
 						No Rujukan Surat
 						#end
 						</td>
 						<td>:</td>
 						<td> 						
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 							#if($list.FLAG_SERAH_CEK == "1")
	 						DISERAHKAN
	 						#elseif($list.FLAG_SERAH_CEK == "2")
	 						TIDAK DISERAHKAN
	 						#end
 						#elseif($list.CARA_BAYAR == "2")
 						$list.NO_RUJUKAN_SURATEFT
 						#end
 						 						
 						</td>
 					</tr>
 					<tr>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 						&nbsp;
 						#end</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 						Penama Cek
 						#end
 						</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 						:
 						#end</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
 						$list.PENERIMA_CEK
 						#end
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				No. Cek
			 			#elseif($list.CARA_BAYAR == "2")
			 				No. EFT
			 			#end
 						</td>
 						<td>:</td>
 						<td>
 						$list.NO_RUJUKAN
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>Amaun</td>
 						<td>:</td>
 						<td>
 						RM $list.AMAUN_BAYARAN
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				Tarikh Cek
			 			#elseif($list.CARA_BAYAR == "2")
			 				Tarikh EFT
			 			#end
 						</td>
 						<td>:</td>
 						<td>
 						$list.TARIKH_TERIMA_EFTCEK
 						</td>
 					</tr>
 					<tr>
 						<td>&nbsp;</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				Tarikh Ambil Cek
			 			#elseif($list.CARA_BAYAR == "2")
			 				Tarikh Terima Surat
			 			#end
 						</td>
 						<td>:</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				$list.TARIKH_AMBIL_CEK
			 			#elseif($list.CARA_BAYAR == "2")
			 				$list.TARIKH_SURAT_EFT
			 			#end
 						</td>
 					</tr>
 					<tr>
					<td>&nbsp;</td>
					<td>
					#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
		 				Masa Ambil Cek
		 			#elseif($list.CARA_BAYAR == "2")
		 				No Baucer
		 			#end
					</td>
					<td>:</td>
					<td>
					#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
		 				$list.MASA_AMBIL_CEK
						#if($list.JENIS_WAKTU_AMBIL_CEK=="1")
						PAGI								
	                 	#elseif($list.JENIS_WAKTU_AMBIL_CEK=="2")
						TENGAH HARI
	                 	#elseif($list.JENIS_WAKTU_AMBIL_CEK=="3")
						PETANG                  			
						#end
		 			#elseif($list.CARA_BAYAR == "2")
		 				$list.NO_BAUCER
		 			#end
                   				
              </td>
				</tr>
				<tr>
 						<td>&nbsp;</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				Tempat Ambil
			 			#elseif($list.CARA_BAYAR == "2")
			 				Catatan
			 			#end
 						</td>
 						<td>:</td>
 						<td>
 						#if($list.CARA_BAYAR == "1" || $list.CARA_BAYAR == "")
			 				$list.TEMPAT_AMBIL
			 			#elseif($list.CARA_BAYAR == "2")
			 				$list.PERIHAL
			 			#end
 						</td>
 					</tr>
 				</table>
 				</fieldset>
		</td>
		</tr>
        #end
        #else
        
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function ShowPopup(hoveritem,tab)
{
	var hp = document.getElementById(tab);
	//alert("hp.style.display :"+hp.style.display+";hp.style.visibility:"+hp.style.visibility);
	//hp.style.display=="none" &&
    if( hp.style.visibility=="collapse" && hp.style.display=="none"){
        hp.style.display = "block";
        hp.style.visibility = "visible";
    }
    //hp.style.display=="block" &&
    else if( hp.style.visibility=="visible" && hp.style.display=="block"){
        hp.style.display = "none";
        hp.style.visibility = "collapse";
    }
}
function findKosongkan() {
	document.${formName}.findNoFail.value = "";
	document.${formName}.findNamaProjek.value = "";
	document.${formName}.findNoCekEFT.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function carian() {
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.namamodul.value = "";
	document.${formName}.namatab.value = "";
    document.${formName}.action = "?myrole=online";
    //document.${formName}.namatab.value = "Menu";
    document.${formName}.namamodul.value = "ekptg.view.online.FrmOnlineMenuUtama";
    document.${formName}.submit();
}
</script>