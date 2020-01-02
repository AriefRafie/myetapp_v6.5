<script type="text/javascript">
    window.onload = function () {
    var webService = "http://www.telize.com/geoip";
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = webService+"?callback=MyIP";
        document.getElementsByTagName("head")[0].appendChild(script);
    };
    function MyIP(response) {
	alert('masuk sini');
        document.getElementById("ipaddress").innerHTML = "Your IP Address is " + response.ip;
    }
</script>


<script>
//alert('div_rowPengguna$internalType$viewPengguna.ID_PERMOHONAN');
if( $jquery('#'+'div_rowPengguna$internalType$viewPengguna.ID_PERMOHONAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPengguna.ID_PERMOHONAN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN">
<td align="left" valign="top" colspan="14">
 	<fieldset>
	<legend>
	<!-- Maklumat '$viewPengguna.USER_NAME' --> $viewPengguna.NAMA
	</legend>
	
	#if($SuccessMesej!="")
	<div class="info" id="div_Success$internalType$viewPengguna.ID_PERMOHONAN">
		#if($SuccessMesej=="Insert")
		
		Maklumat Pengguna Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
		Maklumat Pengguna Berjaya Dikemaskini
		
		#end
	</div>
	<script >
	$jquery("#div_Success$internalType$viewPengguna.ID_PERMOHONAN").show().delay(3000).fadeOut();
	</script>
	#end
	<div id="printableArea_$internalType$viewPengguna.ID_PERMOHONAN">
	<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Tarikh Permohonan  <!--ip user :: $ipUser--><!--<span id = "ipaddress">x</span>-->
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_PENDAFTARAN
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				MyID
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NOKP1 - $viewPengguna.NOKP2 - $viewPengguna.NOKP3
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Nama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.JAWATAN
				</td>
			</tr>
            
            <tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Gred
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.GRED
				</td>
			</tr>
            
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
				$viewPengguna.NEGERI
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.BAHAGIAN
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_UNIT
				</td>
			</tr>
			#if($viewPejabatJKPTG.size() > 0)
			<tr id="div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
			
					#if($viewPejabatJKPTG.ALAMAT1 != "")
					$viewPejabatJKPTG.ALAMAT1<br>
					#end
					#if($viewPejabatJKPTG.ALAMAT2 != "")
						$viewPejabatJKPTG.ALAMAT2<br>
					#end
					#if($viewPejabatJKPTG.ALAMAT3 != "")
						$viewPejabatJKPTG.ALAMAT3<br>
					#end
					#if($viewPejabatJKPTG.POSKOD != "")
						$viewPejabatJKPTG.POSKOD &nbsp;						
					#end
					#if($viewPejabatJKPTG.BANDAR != "")
						$viewPejabatJKPTG.BANDAR<br>
					#end
					#if($viewPejabatJKPTG.NEGERI != "")
						$viewPejabatJKPTG.NEGERI<br>
					#end
					
					#if($viewPejabatJKPTG.NO_TEL != "")
						No. Tel : $viewPejabatJKPTG.NO_TEL<br>
					#end
					#if($viewPejabatJKPTG.NO_FAX != "")
						No. Fax : $viewPejabatJKPTG.NO_FAX<br>
					#end
				
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.EMEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				No. Telefon Bimbit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NO_HP				
				</td>
			</tr>
			#if($viewPengguna.PEGAWAIPENYELIA!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Penyelia
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.PEGAWAIPENYELIA			
				</td>
			</tr>
			#end
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Status Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengguna.FLAG_STATUS=="1")
				PERMOHONAN BARU
				#elseif($viewPengguna.FLAG_STATUS=="2")
				DITOLAK
				#elseif($viewPengguna.FLAG_STATUS=="3")
				DITERIMA
				#end		
				</td>
			</tr>
			
			#if($viewPengguna.CATATAN!="")
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.CATATAN			
				</td>
			</tr>
			#end
			#if($viewPengguna.NAMA_DOC!="")
			<tr>
			<td valign="top" align="center" >			
			</td>			
			<td valign="top" align="left" >
			Lampiran
			</td>
			<td valign="top"  >
			:
			</td>
			<td valign="top" align="left" >		
<a href="javascript:paparDocPIP('$viewPengguna.ID_PERMOHONAN')"><font color="blue"><u>$viewPengguna.NAMA_DOC</u></font></a>
			</td>
			</tr>
			#end
			
			#if($viewPengguna.PENYEMAK!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Pengawai Pendaftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.PENYEMAK			
				</td>
			</tr>
			#end
			
			#if($viewPengguna.TARIKH_KEPUTUSAN!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				#if($viewPengguna.FLAG_STATUS=="3")
					Tarikh Pendaftaran ID Pengguna
				#else
					Tarikh Keputusan
				#end
				
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_KEPUTUSAN			
				</td>
			</tr>
			#end
			
			#if($viewPengguna.ID_TABLEUSERS!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				USER_ID (USERS) 				
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.ID_TABLEUSERS			
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
				#if($viewPengguna.ID_TABLEUSERS =="")
				<input type="button" id="BTNEDIT$internalType$viewPengguna.ID_PERMOHONAN" name="BTNEDIT$internalType$viewPengguna.ID_PERMOHONAN"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN','edit_PenggunaMOHON','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType');" value="Kemaskini" > 
	   			#end
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.ID_PERMOHONAN" name="BTNCLOSE$internalType$viewPengguna.ID_PERMOHONAN" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN','close_PenggunaMOHON','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType');" value="Tutup" > 
	   			<!-- 
	   			<input type="button" id="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" name="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" onclick="printDiv('printableArea_$internalType$viewPengguna.ID_PERMOHONAN','$internalType','$viewPengguna.ID_PERMOHONAN')" value="Cetak" />
	   			-->
	   			<input type="button" id="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" name="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" onclick="doDivAjaxCall$formname('BorangPengesahanPenyelia_$internalType$viewPengguna.ID_PERMOHONAN','cetakBorangPengesahan','USER_ID=&internalType=$internalType&ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN');" value="Cetak Borang Pengesahan" />
               <!-- printHideDiv('BorangPengesahanPenyelia_$internalType$viewPengguna.ID_PERMOHONAN','$internalType','$viewPengguna.ID_PERMOHONAN')"-->
              <!-- <input type="button" id="BTNPRINT2$internalType$viewPengguna.ID_PERMOHONAN" name="BTNPRINT2$internalType$viewPengguna.ID_PERMOHONAN" onclick="doDivAjaxCall$formname('LampiranA_$internalType$viewPengguna.ID_PERMOHONAN','cetakLampiranA','USER_ID=&internalType=$internalType&ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN');" value="LAMPIRAN A" />
	   			-->
	   			#if($viewPengguna.ID_TABLEUSERS =="")
	   			  #if($viewPengguna.FLAG_STATUS == "3")			   
					  #if($viewPengguna.ID_NEGERI=="16")
					  <input type="button" id="BTNDAFTAR$internalType$viewPengguna.ID_PERMOHONAN" name="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" onclick="document.getElementById('div_viewPenggunaHQ').style.display=''; doDivAjaxCall$formname('div_viewPenggunaHQ','edit_PenggunaInternal','USER_ID=&internalType=HQ&ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN');" value="Daftar Pengguna" />
		   			  #else
					  <input type="button" id="BTNDAFTAR$internalType$viewPengguna.ID_PERMOHONAN" name="BTNPRINT$internalType$viewPengguna.ID_PERMOHONAN" onclick="document.getElementById('div_viewPenggunaNegeri').style.display=''; doDivAjaxCall$formname('div_viewPenggunaNegeri','edit_PenggunaInternal','USER_ID=&internalType=Negeri&ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN');" value="Daftar Pengguna" />
		   			  #end	   		   
	   		      #end
	   		     #end
	   			
	   			</td>
			</tr>
			
			
		</table>
		
		
		</div>
		
		<div id="BorangPengesahanPenyelia_$internalType$viewPengguna.ID_PERMOHONAN" style="display:none">
		
		</div>
        
        <div id="LampiranA_$internalType$viewPengguna.ID_PERMOHONAN" style="display:none">
        </div>
	</fieldset>
	
	<br>
</td>		
</tr>
