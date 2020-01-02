<style>
.style1 {
	font-size: 16px;
	font-weight: bold;
}
.style3 {font-family: "Times New Roman", Times, serif}
</style>

<p>Maklumat Pejabat </p>
<div id="BorangPendaftaranPengguna_$internalType$viewPengguna.USER_ID" >
     
<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">

 #if($internalType == "HQ" || $internalType == "Negeri")
  <tr>
 <th width="5%"  colspan bgcolor="#666666" "5"> </th>
    <th width="100%"> 
    <p align="right"> 
    $viewPengguna.NAMA_BAHAGIAN 
    <br>$viewPengguna.NAMA_PEJABAT 
    <br> $viewPengguna.ALAMAT1_PEJ $viewPengguna.ALAMAT2_PEJ $viewPengguna.ALAMAT3_PEJ $viewPengguna.POSKOD_PEJ $viewPengguna.NAMA_BANDAR
    <br> $viewPengguna.NEGERI_PEJ
    <br> No.Tel : $viewPengguna.NO_TEL_PEJ
    <br> No. Faks : $viewPengguna.NO_FAX_PEJ 
    <br>
     </p>
     </th>
       </tr>
     #elseif ($internalType == "KJP")
      <tr>
     <th width="5%"  colspan bgcolor="#666666" "5"> </th>
      <th width="100%"> 
     <p align="right"> 
     $viewPengguna.NAMA_KEMENTERIAN 
    <br>$viewPengguna.NAMA_AGENSI 
    <br> $viewPengguna.ALAMAT1 $viewPengguna.ALAMAT2 $viewPengguna.ALAMAT3 $viewPengguna.POSKOD 
    <br> $viewPengguna.NEGERI_KEMENTERIAN
  <!--  <br> No.Tel : $viewPengguna.NO_TEL_PEJ
    <br> No. Faks : $viewPengguna.NO_FAX_PEJ -->
    <br>
     </p>
     </th>
       </tr>
     #elseif ($internalType == "INT")
      <tr>
     <th width="5%"  colspan bgcolor="#666666" "5"> </th>
      <th width="100%"> 
     <p align="right"> 
     $viewPengguna.NAMA_PEJABAT 
    <!--<br>$viewPengguna.NAMA_AGENSI -->
    <!--<br> $viewPengguna.ALAMAT1 $viewPengguna.ALAMAT2 $viewPengguna.ALAMAT3 $viewPengguna.POSKOD -->
    <br> $viewPengguna.NEGERI_UI
  <!--  <br> No.Tel : $viewPengguna.NO_TEL_PEJ
    <br> No. Faks : $viewPengguna.NO_FAX_PEJ -->
    <br>
     </p>
     </th>
       </tr>
     #end
 
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
				$viewPengguna.USER_LOGIN
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
				$viewPengguna.USER_NAME
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
                #if($internalType == "HQ" || $internalType == "Negeri" || $internalType == "INT")
				$viewPengguna.JAWATAN
                #elseif ($internalType == "KJP")
                $viewPengguna.NAMA_JAWATAN
                #end
				</td>
			</tr>
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
				No. Telefon				</td>
			  <td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($internalType == "HQ" || $internalType == "Negeri")
				$viewPengguna.NO_TEL_PEJ
                #elseif ($internalType == "KJP")
                -
                #end		
				</td>
			</tr>

		</table>

  
  </th> 
    
  </tr>
 
</table>

<p class="style1"> Senarai Peranan  </p>

<table style="width:80%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
  <tr>
 
  <th width="5%" colspan bgcolor="#666666" "5"> </th>
  <th width="100%">
  
   <br />
   <p align="left">Peranan Utama :  #if($internalType == "HQ" || $internalType == "Negeri") $viewPengguna.ROLE_MAIN_DETAILS
                #elseif ($internalType == "KJP")
                $viewPengguna.NAMA_ROLE
                #elseif ($internalType == "INT")
                $viewPengguna.NAMA_ROLE
                #else -
                #end	</p>
<br />


#if($listAdditionalRoleByUserLogin.size()>0)
<p align="left">Peranan Tambahan : </p>
  <table width="90%" align="left" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" >
			<tr>
				<td valign="top" >				
				</td>			
			  <td valign="top" colspan="3"  >
			
				</td>
			</tr>
            
            
             <tr>		
				<td valign="top" colspan="2">
				Senarai Peranan
				</td>
			</tr>
            
            #foreach($ar in $listAdditionalRoleByUserLogin)
			#if($ar.LAYER == "1")
			<tr>
			<td colspan="2"><b>$ar.KOD</b></td>
		</tr>
			#end
			#if($ar.LAYER == "2")
			<tr>
            <td align="left"   valign="top" ></td>
			<td align="left"   valign="top" >$ar.KETERANGAN</td>
            </tr>
			#end
#end
		</table>
#else 
<p align="left">Peranan Tambahan : - </p>
<br />
#end
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
                 <th width="5%" colspan bgcolor="#666666" "8"></th>
                 
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
                 <th width="5%" colspan bgcolor="#666666" "8"> </th>
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


<script>
$jquery(document).ready(function () {
	
		printHideDiv2('BorangPendaftaranPengguna_$internalType$USER_ID','');
		
		var SenaraiForPrint =  document.getElementById('BorangPendaftaranPengguna_$internalType$USER_ID');
		SenaraiForPrint.style.display = "none";
		
	});
</script>