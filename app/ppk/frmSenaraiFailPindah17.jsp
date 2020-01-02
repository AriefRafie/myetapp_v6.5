<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<form name="f1" method="post">

<fieldset>
<legend>FAIL PINDAH MASUK</legend>
	
<br/>
	<fieldset>
	<legend>SENARAI FAIL YANG DITERIMA DARIPADA LUAR</legend>
    <br/>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
     <tr class="table_header">
       <td width="4%" align="center" style="text-transform:uppercase">No.</td>
       <td width="12%" style="text-transform:uppercase">No Fail</td>
       <td width="16%" style="text-transform:uppercase">Nama SiMati</td>
       <td width="18%" style="text-transform:uppercase">Nama Pemohon</td>
       <td width="22%" style="text-transform:uppercase">Negeri/Daerah Asal</td>
       <td width="20%" style="text-transform:uppercase">Negeri/Daerah Pindah</td>
       <td width="8%" style="text-transform:uppercase">Status</td>
     </tr>
    		
    	#if($listMaklumatMenunggu_size!=0)
           #foreach($list in $listMaklumatMenunggu)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		

     <tr>
       <td class="$row" align="center">$list.bil</td>
       <td class="$row"><a href="javascript:paparTunggu('$list.id_fail','$list.id_permohonan','$list.id_status')"><font color="blue">$list.no_fail</font></a></td>
       <td class="$row">$list.nama_simati.toUpperCase()</td>
       <td class="$row">$list.nama_pemohon.toUpperCase()</td>
       <td class="$row">$list.nama_negerimhn/$list.nama_daerahmhn
       <input type="hidden" name="id_negerimhn" id="id_negerimhn" value="$list.id_negerimhn" />
       <input type="hidden" name="id_daerahmhn" id="id_daerahmhn" value="$list.id_daerahmhn" />
       </td>       
       <td class="$row">$list.nama_negeri/$list.nama_daerah
       <input type="hidden" name="id_negeri" id="id_negeri" value="$list.id_negeri" />
       <input type="hidden" name="id_daerah" id="id_daerah" value="$list.id_daerah" />
       </td>
       <td class="$row">$list.keterangan</td>
     </tr>	
           #end
        #else   	
    		<tr>
    			<td colspan="7">Tiada Rekod</td>
    		</tr>
    	#end
    		
		</table>
	</fieldset>	
</fieldset>
<p></p>
       
<fieldset>
<legend>FAIL PINDAH KELUAR</legend>    
<br/>
	<fieldset>
	<legend>SENARAI FAIL YANG AKAN DIPINDAH KELUAR</legend>
    <br/>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
     <tr class="table_header">
       <td width="4%" align="center" style="text-transform:uppercase">No.</td>
       <td width="12%" style="text-transform:uppercase">No Fail</td>
       <td width="16%" style="text-transform:uppercase">Nama SiMati</td>
       <td width="18%" style="text-transform:uppercase">Nama Pemohon</td>
       <td width="23%" style="text-transform:uppercase">Negeri/Daerah Asal</td>
       <td width="19%" style="text-transform:uppercase">Negeri/Daerah Pindah</td>
       <td width="8%" style="text-transform:uppercase">Status</td>
     </tr>
    		
    	#if($listMenunggu_size!=0)
           #foreach($list in $listMenunggu)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
     <tr>
       <td class="$row" align="center">$list.bil</td>
       <td class="$row">$list.no_fail</td>
       <td class="$row">$list.nama_simati.toUpperCase()</td>
       <td class="$row">$list.nama_pemohon.toUpperCase()</td>
       <td class="$row">$list.nama_negerimhn/$list.nama_daerahmhn
       <input type="hidden" name="id_negerimhn" id="id_negerimhn" value="$list.id_negerimhn" />
       <input type="hidden" name="id_daerahmhn" id="id_daerahmhn" value="$list.id_daerahmhn" />
       </td>       
       <td class="$row">$list.nama_negeri/$list.nama_daerah
       <input type="hidden" name="id_negeri" id="id_negeri" value="$list.id_negeri" />
       <input type="hidden" name="id_daerah" id="id_daerah" value="$list.id_daerah" />
       </td>
       <td class="$row">$list.keterangan</td>
     </tr>	
           #end
        #else   	
    		<tr>
    			<td colspan="7">Tiada Rekod</td>
    		</tr>
    	#end
		</table>
  </fieldset>	   
  <p></p> 
	
<br/>
	<fieldset>
	<legend>SENARAI FAIL YANG TELAH DIPINDAH KELUAR</legend>
    <br/>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
     <tr class="table_header">
       <td width="4%" align="center" style="text-transform:uppercase">No.</td>
       <td width="12%" style="text-transform:uppercase">No Fail</td>
       <td width="16%" style="text-transform:uppercase">Nama SiMati</td>
       <td width="18%" style="text-transform:uppercase">Nama Pemohon</td>
       <td width="23%" style="text-transform:uppercase">Negeri/Daerah Asal</td>
       <td width="19%" style="text-transform:uppercase">Negeri/Daerah Pindah</td>
       <td width="8%" style="text-transform:uppercase">Status</td>
     </tr>
    		
    	#if($listTelahDipindah_size!=0)
           #foreach($list in $listTelahDipindah)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		

     <tr>
       <td class="$row" align="center">$list.bil</td>
       <!-- td class="$row">$list.no_fail</td -->
       <td class="$row"><a href="javascript:paparBatal('$list.id_fail','$list.id_permohonan','$list.id_status')"><font color="blue">$list.no_fail</font></a></td>
       <td class="$row">$list.nama_simati.toUpperCase()</td>
       <td class="$row">$list.nama_pemohon.toUpperCase()</td>
       <td class="$row">$list.nama_negerimhn/$list.nama_daerahmhn
       <input type="hidden" name="id_negerimhn" id="id_negerimhn" value="$list.id_negerimhn" />
       <input type="hidden" name="id_daerahmhn" id="id_daerahmhn" value="$list.id_daerahmhn" />
       </td>       
       <td class="$row">$list.nama_negeri/$list.nama_daerah
       <input type="hidden" name="id_negeri" id="id_negeri" value="$list.id_negeri" />
       <input type="hidden" name="id_daerah" id="id_daerah" value="$list.id_daerah" />
       </td>
       <td class="$row">$list.keterangan</td>
     </tr>	
           #end
        #else   	
    		<tr>
    			<td colspan="7">Tiada Rekod</td>
    		</tr>
    	#end
    		
		</table>
	</fieldset>	
        
<input type="hidden" name="command">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" />

</fieldset>
</form>
<script>
function paparTunggu(id_fail,id_permohonan) {
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.command.value = "paparTunggu";
	document.f1.action = "";
	document.f1.submit();
}
function paparBatal(id_fail,id_permohonan,id_status) {
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_status.value = id_status;
	document.f1.command.value = "paparBatal";
	document.f1.action = "";
	document.f1.submit();
}
function seterusnya(){    	
	document.f1.command.value = "next";
	document.f1.action = "";
	document.f1.submit();
}
function sebelumnya(){    	
	document.f1.command.value = "previous";
	document.f1.action = "";
	document.f1.submit();
}
</script>