<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 
<br/> 
#parse('app/htp/pajakankecil/utiliti/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
    	<td> 
			<!-- <br/>
			  ##parse('app/htp/frmPajakanKecilPaging.jsp')
			<br/> -->
			#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			  
			  #set ($pagemode = $pageMode)	
			  #set ($cbFlag = '')	
			  #if($pagemode == "2" )
			  #set ($cbFlag = 'disabled')	
			  #end 

		<td>		
	</tr>
	
	<tr>
    	<td> 	
    		
			<fieldset><legend>SEMAKAN PERJANJIAN</legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
			   #set ( $checked = "" )
			   #foreach ( $semak in $senaraiSemakan )
			      #set( $i = $velocityCount )
			    #if ( ($i % 2) == 0 )
			      #set( $row = "row2" )
			    #else
			      #set( $row = "row1" )
			    #end
			  <tr class="$row">
			    <td width="5%">
			    
			      #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
			           #set ( $checked = "checked" )
			      #else
			           #set ( $checked = "" )
			      #end
			      
			     <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $cbFlag>
			    
			    </td>
			    <td width="95%">$semak.keterangan</td>
			  </tr>
			  #end
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>

			  <!--<tr>,p
			    <td colspan="2" align="right">CL - 02 - 0406</td>
			  </tr>-->
			  </table>
			</fieldset>
			<p>
			   	<input type="hidden" name="id_kemaskini" />
			   	<input type="hidden" name="fail" />
			   	<input type="hidden" name="langkah" value="0" />
			    
			 </p>
			 <fieldset id="tablesuratp" style="display:none;">
			<legend>SENARAI SURAT/DOKUMEN</legend>
			<table width="100%" border="0">
  			#if ($!permohonanInfo.idnegeri==12)
			  <tr>
			    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSenaraiSemakPTP12')" class="pautanms">SENARAI SEMAKAN PTP</a></td>
			  </tr>
			  <tr>
			    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilMinitBebas12')" class="pautanms">MINIT BEBAS</a></td>
			  </tr>  
			#else
			  <tr>
			    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSenaraiSemakPTP13')" class="pautanms">SENARAI SEMAKAN PTP</a></td>
			  </tr>
			  <tr>
			    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilMinitBebas13')" class="pautanms">MINIT BEBAS</a></td>
			  </tr> 			
			#end  
			  <tr>
			    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSuratMaklumanTandatanganBorangL')" class="pautanms">SURAT MAKLUMAN TANDATANGAN BORANG L</a></td>
			  </tr>   
			   <tr>
			    <td><a href="javascript:openSuratPegawaiSimpan('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan&langkah=7&iduser=$idUser','urusan','&template=HTPPajakanKecilSuratMaklumanMengembalikanBorangL')" class="pautanms">SURAT MAKLUMAN MENGEMBALI BORANG L</a></td>
			  </tr> 
			  </table>
			</fieldset>
	
		<td>		
	</tr>
	
	<tr>
		<td colspan="2" align="center">
	#if (!$!jenisAkses.equals('Readonly'))
			
		#if($pagemode == "2" )
			<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="semakanPTPKemaskini('$permohonanInfo.idpermohonan')">
		#end
			    
		#if($pagemode == "0" || $pagemode == "3")
			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="semakanPTPSimpan('$permohonanInfo.idpermohonan')">
			#if($pagemode == "0")			
			<input class="stylobutton100" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan">
			#end
		#end
	#end
			<!-- 
			<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalSemakanPKP('$permohonanInfo.idpermohonan')">
			-->
			<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiSuratSkrinSemakan2('tablesuratp');" />
			<!-- Komen pada 11/06/2010
			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Sebelumnya" onclick="backPrePage2('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')">
			<input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti4('$permohonanInfo.idpermohonan')">
			-->
			<!--
			<input class="stylobutton" type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()">
			-->
		</td>
	</tr>
	
		
</table>