<style>
.style1 {
	font-size: 16px;
	font-weight: bold;
}
</style>

<div id="BorangPengesahanPenyelia_$internalType$viewPengguna.ID_PERMOHONAN" >
<p class="style1">Maklumat Pejabat </p>
     
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <th width="5%"  colspan bgcolor="#666666" "5"> </th>
    
    <th width="100%"> 
    <p align="right"> $viewPengguna.BAHAGIAN 
    <br> $viewPengguna.NAMA_UNIT
    <br> $viewPejabatJKPTG.ALAMAT1 $viewPejabatJKPTG.ALAMAT2 $viewPejabatJKPTG.ALAMAT3 $viewPejabatJKPTG.POSKOD $viewPejabatJKPTG.BANDAR
    <br> $viewPejabatJKPTG.NEGERI
    <br> No.Tel : $viewPejabatJKPTG.NO_TEL
    <br> No. Faks : $viewPejabatJKPTG.NO_FAX 
    <br>
    </p>
    </th> 
    
  </tr>
 
</table>
 
 <p class="style1">Maklumat Pengguna </p>
 
  <table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
  <th width="100%">
  
  <table width="90%" align="left" >
			<tr>
				<td valign="top" >				
				</td>			
			  <td valign="top" colspan="3"  >
			
				</td>
			</tr>
           <!--<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Tarikh Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_PENDAFTARAN
				</td>
			</tr>-->
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				No KP (ID Pengguna)
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
			<!--<tr>
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
			</tr>-->
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
			<!--#if($viewPejabatJKPTG.size() > 0)
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
			#end-->
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

		</table>

  
  </th> 
    
  </tr>
 
</table>

<p class="style1"> Senarai Peranan : </p>

<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
  <th width="100%" align="left">
  Sila Rujuk Lampiran A <br>
  </th> 
    
  </tr>
 
</table>

<p> Sebelum menandatangani dokumen ini, sila pastikan maklumat yang telah diisi adalah SAH. </p>
            
<table id="main_table" style="width:80%" border="0">
	<tbody>
		<tr>
		  <td width="50%" valign="top">
            <table width="95%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
				
           <tr>
                 <td width="5%"  bgcolor="#666666" "8"></td>
                 
            <td>
            Disahkan Oleh :
            <br />
            <br />
            <br />
            <br />
            .............................
            <br />
            Nama : 
            <br />
            Jawatan : 
            <br />
            Tarikh : 
            <br />
            Cop Rasmi : 
            <br />
            <br />
            <br />
            <br />
                    </td>
                </tr>
              </table>
		  </td>
            
			<td width="50%" valign="top">
            	 <table width="95%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" align="right">
                <tr>
                 <td width="5%" bgcolor="#666666" "8"> </td>
            <td>
            Dimasukkan Oleh : <br />
            <br />
            <br />
            <br />
            .............................
            <br />
            Nama : 
            <br />
            Jawatan : 
            <br />
            Tarikh : 
            <br />
            Cop Rasmi : 
            <br />
            <br />
            <br />
            <br />
                    </td>
                </tr>
                </table>
		  </td>
		</tr>
	</tbody>
</table>
		
</div>

<div id="div_LampiranAPrint" >
#parse("app/admin/UV3/PrintRolePemohon.jsp")
</div>

<script type="text/javascript">
$jquery(document).ready(function () {
	printHideDivAll('BorangPengesahanPenyelia_$internalType$ID_PERMOHONAN','div_LampiranAPrint');
	var SenaraiForPrint =  document.getElementById('BorangPengesahanPenyelia_$internalType$ID_PERMOHONAN');
		SenaraiForPrint.style.display = "none";
		});
</script>
    