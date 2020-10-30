
<style type="text/css">
<!--
.style1 {color: #0033FF} 
-->
</style> 
<input type="hidden" name="command" />
	<input type="hidden" name="subcommand" value="$!onlineLebih"/>
	  
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />
    
   	<input type="hidden" name="idpermohonan" id="idpermohonan">    
    <input type="hidden" name="idPermohonan" id="idPermohonan">  
    <input type="hidden" name="idSimati" id="idSimati">
    <input type="hidden" name="idPemohon" id="idPemohon">
    <input name="no_subjaket" type="hidden" id="no_subjaket" />
    <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td>
<fieldset>
<legend><b>CARIAN</b>
</legend>
<table width="100%" align="center" border="0">

  <tbody>
    <tr>
      <td width="29%" height="24" scope="row" align="right"><div align="right">No. Rujukan <i>Online</i></div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="70%"><input name="txtRujukanOnline" id="txtRujukanOnline" type="text" size="50" maxlength="50" value="$noFail" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right">Nama Pemohon</div></td>
      <td><div align="center">:</div></td>
      <td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" size="50" maxlength="50" value="$namapemohon" style="text-transform:uppercase;" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right">No. KP Pemohon</div></td>
      <td><div align="center">:</div></td>
      <td><input name="txtnokppemohon" id="txtnokppemohon" type="text" size="50" maxlength="12" value="$nokppemohon" style="text-transform:uppercase;" /></td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
      <td>
      	<input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="search_data()" />
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="cancel()" />
   	
	#set ($paparan = "")
   	#if ( !$!onlineLebih.equals("lebih"))
 		#set ($paparan = " << 21 HARI")
    	<input type="button" name="cmdlebih" value=">> 21 Hari" onclick = "lebihHari()" />
    #else
  		#set ($paparan = " >> 21 HARI")
      	<input type="button" name="cmdkurang" value="<< 21 Hari" onclick = "kurangHari()" />    
    #end
        </td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;      </td>
    </tr>
  </tbody>
</table>
</fieldset>


</td>
</tr>
<tr>
    <td>
<fieldset>
<legend><b>SENARAI PERMOHONAN <i>ONLINE</i> $!paparan</b></legend>


#parse("app/utils/record_paging.jsp")
        
<table align="center" width="100%"> 
  	
  <tbody>
  
    <tr class="table_header">
      <td scope="row" width="5%" align="center"><div align="center">Bil.</div></td>
      <td width="15%">No. Rujukan <i>Online</i></td>
      <td width="20%"><div align="center">No. Fail</div></td>
      <td width="10%"><div align="center">Tarikh Permohonan</div></td>
     
      <td width="20%"><div align="left">Nama Pemohon</div></td>
      <td width="10%"><div align="center">No. KP Pemohon</div></td>
      <td width="10%"><div align="center">Cetak Borang A</div></td>
	  <td width="10%"><div align="center">Kemaskini Borang A</div></td>
    </tr>
     #set ($noFail = "")
     
   #set ($tarikhDaftar = "")
   #set ($tarikh_Mohon = "")
   #set ($keterangan = "")
   #set ($noFail1 = "")
   #set ($tarikhDaftar1 = "")
   #set ($tarikh_Mohon1 = "")
   #set ($keterangan1 = "")
   #set ($SimpanStatus = 2)
   #set ($flagno = 3)
   #set ($idFlag = 2)
   
   
                        
                         #set ($fail = "")
                         #set ($bil2 = 0)
          				 #foreach ($fail in $SenaraifailOnline)
          				 	
	                      	#if ($fail.bil == '')
                				#set( $row = "row1" )
            				#elseif (($fail.bil % 2) != 0)
               					#set( $row = "row1" )
           					#else 
                				#set( $row = "row2" )
            				#end
          
					      <tr >
				          <td class="$row"><div align="center">$fail.bil</div></td>
				          <td class="$row"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1">$fail.noonline</a></td>
                          <td class="$row"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.no_Fail</div></td>
				          <td class="$row"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhmohononline</div></td>
				      
				          <td class="$row"><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$fail.namapemohon</div></td>
				          <td class="$row"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.nokppemohon</div></td>
                           <td class="$row"><div align="center">
                             <!-- <input name="cetak" type="button" value="Cetak Borang A" onclick="cetakBorangA('$fail.id_Permohonan')" /> -->
                             #if($fail.id_jenisdokumen == "99211")
                             	<input name="cetak" type="button" value="Cetak Borang A" onclick="paparLampiran('$fail.id_dokumen')" />
                             #end
                           </div></td>
						   <td class="$row"><div align="center">
                           #if($fail.id_jenisdokumen == "99211") 
                             <input name="kemaskini" type="button" value="Kemaskini Borang A" onclick="papar('$fail.id_Permohonan','$fail.id_Simati','$fail.seksyen','$fail.id_Pemohon','$fail.no_subjaket')" /> 
                           #end  
                             
                           </div></td>
				          </tr>

                          
                          #end
   </tbody>
  </table>
</fieldset>
</td>
</tr>
</table>


	
       

 
<script>
	function cancel() {
		document.${formName}.reset();
		document.${formName}.txtRujukanOnline.value = "";
		document.${formName}.txtNamaPemohon.value = "";
		document.${formName}.txtnokppemohon.value = "";
	
	}
	
	function Tambah() {
		//document.${formName}.command.value = "tambah";
		//document.${formName}.idFlag.value = "1";
		//document.${formName}.flagno.value = "0";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8SenaraiSemak";
		document.${formName}.submit();

	}
	
	function search_data(){
		if (document.${formName}.txtRujukanOnline.value == "" && document.${formName}.txtNamaPemohon.value == "" && document.${formName}.txtnokppemohon.value == ""){
			alert("Sila masukkan no. rujukan online, nama pemohon atau no. kp pemohon yang ingin dicari!");		
			document.${formName}.txtRujukanOnline.focus(); 
			return; 

		}else {
			document.${formName}.command.value = "Carian";
			document.${formName}.action = "";
			document.${formName}.submit();
		
		}
		
	}
	
	function cetak() {
		window.print();
	}
	
	function edit_item(id,id2,id3,id4){
	
		input_box=confirm("Sila Pastikan Borang A telah diserahkan sebelum permohonan ini diteruskan.");
		if (input_box == true) {
			document.${formName}.command.value = "papar";
			document.${formName}.action = "";
			document.${formName}.idpermohonan.value = id;
			document.${formName}.idSimati.value = id2;	
			document.${formName}.flagno.value = id3;
			document.${formName}.idFlag.value = id4;
			document.${formName}.submit();
		
		}		
		
	}
	
	function seterusnya(){    	
		document.${formName}.command.value = "next";
		document.${formName}.action = "";
		document.${formName}.submit();
		
	}
	
	function sebelumnya(){    	
		document.${formName}.command.value = "previous";
		document.${formName}.action = "";
		document.${formName}.submit();
		
	}
	
	function cetakBorangA(id_Permohonan) {
	    var url = "../servlet/ekptg.report.ppk.BorangAOnline?idPermohonan="+id_Permohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
	function paparLampiran(id_Dokumen) {
	    var url = "../servlet/ekptg.view.ppk.util.LampiranByBlob?iDokumen="+id_Dokumen+"&tablename=simati";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		
	    if (hWnd.focus != null) hWnd.focus();
	    
	}

	function editBorangA(noFailOnline) {
	    var url = "../x/${securityToken}/ekptg.view.ppk.FrmBorangAEdit?command=check_kp&typez=online&nopermohonan="+noFailOnline;
	  // var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon&command=tab";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		
	    if (hWnd.focus != null) hWnd.focus();
	    
	}

	function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {
 		var token = document.${formName}.form_token.value;
 		var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon?command=Simati&mode=Simatiview&idPermohonan="+idPermohonan+"&idpermohonan="+idPermohonan+"&idPemohon="+idpemohon+"&idSimati="+idSimati+"&form_token"+token;
   		var hWnd = window.open(url,'Cetak','width=1000,height=600, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;

    	if (hWnd.focus != null) hWnd.focus();
	
		/*	
			document.${formName}.command.value = "Simati";
			document.${formName}.command.mode = "Simatiview";
			document.${formName}.action = "";
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.idSimati.value = id2;
			
			document.${formName}.flagno.value = id3;
			document.${formName}.idFlag.value = id4;
			document.${formName}.submit();
			
		*/
				
	}

	function lebihHari() {
		document.${formName}.command.value = "lebih";
		document.${formName}.subcommand.value = "lebih";
		document.${formName}.action = "";
		document.${formName}.submit();

	}

	function kurangHari() {
		document.${formName}.command.value = "";
		document.${formName}.subcommand.value = "";
		document.${formName}.action = "";
		document.${formName}.submit();

	}
	
	
</script>

<div align="right">CL - 01 - 26</div>	