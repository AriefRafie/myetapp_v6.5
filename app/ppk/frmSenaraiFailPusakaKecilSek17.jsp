<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
<p></p>
<form id="form1" name="f1" method="post">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<fieldset><legend>
<b  >Carian</b>
</legend>
<table width="100%" align="center" border="0">
  <tbody>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No Fail : </td>
      <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
      <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
      <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No.KP Simati : </td>
      <td width="70%"><select name="jenisIc" style="width: 100px;text-transform:uppercase;" >
      <option value="0">Sila Pilih</option>
      <option value="1">No. KP Baru</option>
      <option value="2">No. KP Lama</option>
      <option value="3">No. KP Lain</option>
      </select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td scope="row"></td>
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()">
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:cancel()"></td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </tbody>
</table>
</fieldset>



#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())



<fieldset>
<legend><b>Senarai Fail Pusaka Kecil</b></legend>

<!--
<p align="left"><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button"  onclick="Tambah()">
</p>
-->
<table align="center" width="100%"> 
  <tbody>
  
  <tr>
     	
    		<td colspan="5" scope="row" align="right">
    			 #if ( $i >= $Senaraifail.size() && $i < $total )
      			 <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
                
      			 #end
                 
               
 	  			 #if (($i < $total && $Senaraifail.size() != $total && $i <= $Senaraifail.size()))
      			 <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
                
      			 #end
                
    		</td>
   	  </tr>
  
    <tr class="table_header">
      <td scope="row" width="5%">Bil</td>
      <td width="20%">No Fail</td>
      <td width="35%">Nama Simati</td>
      <td width="15%">Tarikh Mohon</td>
      <td width="25%">Status Fail</td>
    
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
    #set ($bil = "")
    #if ($carix == "2")
    #set ($cnt = 0)
    
    
    
  
    
	       #foreach($fail in $Senaraifail1)
       
	           #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($noFail = $fail.no_Fail)
				   #set ($bil = $fail.bil)
				   #set ($tarikhDaftar = $fail.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail.tarikh_Mohon)
				   #set ($keterangan = $fail.keterangan)
				   #set ($namasimati = $fail.namasimati)
                   
                   
					   #if ($CountList1 > 0 )
					   #set ($cnt = $cnt + 1)
                       
                       #set($idp=$fail.id_Permohonan)
                     
                     #if($idp!="" )
                     
                     
                     
                     
                     
                     
					   <tr >
				          <td class="$row">$bil</td>
				         <td class="$row"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_simati','$flagno','$idFlag','$SimpanStatus')" class="style1">$noFail
				          </a></td>
				          <td class="$row">$namasimati.toUpperCase()</td>
				          <td class="$row">$tarikh_Mohon</td>
				          <td class="$row">$keterangan</td>
                       
    </tr>
    #else
    <tr >
				          <td  colspan="5" align="center"><div align="left">Tiada Rekod </div></td>
    </tr>
    #end
				        #else
				        <tr >
				          <td  colspan="5" align="center"><div align="left">Tiada Rekod </div></td>
    </tr>
				         #end
	        #end
        
  
        
   #elseif ($carix == "1")
   		#set ($cnt2 = 0)
        
        #if($Senaraifail.size() > 0)
	         #foreach($fail1 in $Senaraifail)
	         
	         #set ($i = $velocityCount)
		        #if (($i % 2) == 0)
		       		#set ($row = "row2")
		        #else
		       		#set ($row = "row1")
		        #end
				   #set ($bil = $fail1.bil)
				   #set ($noFail = $fail1.no_Fail)
				   #set ($tarikhDaftar = $fail1.tarikhDaftar)
				   #set ($tarikh_Mohon = $fail1.tarikh_Mohon)
				   #set ($keterangan = $fail1.keterangan)
				   #set ($namasimati = $fail1.namasimati)
                  
				   			#if ($CountList > 0)
				   			#set ($cnt2 = $cnt2 + 1)
                            
                            
        <tr >
          <td class="$row">$bil</td>
       <td class="$row"><a href="javascript:edit_item('$fail1.id_Permohonan','$fail1.id_simati','$flagno','$idFlag','$SimpanStatus')" class="style1">$noFail
           </a></td>            
           
          <td class="$row">$namasimati.toUpperCase()</td>
          <td class="$row">$tarikh_Mohon</td>
          <td class="$row">$keterangan</td>
         
    </tr>
    #end
        				
   			#end
   #end
   
   
   
   	#end
    #if($Senaraifail.size() == 0)
        <tr >
          <td  colspan="5" align="center"><div align="left">Tiada Rekod</div></td>
    </tr>
        					#end
   
   
  </tbody>
</table>
</fieldset>










    <input type="hidden" name="command" />
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati" />
     <input type="hidden" name="idPemohon" />
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />

</form>
<script>
function tab(id,id2,id3)
{
    document.f1.command.value = "tab";	
	document.f1.action = "";
	
	document.f1.idpermohonan.value = id;
	document.f1.idSimati.value = id2;
	document.f1.idPemohon.value = id3;
	
	document.f1.submit();

}



function cancel() {
document.f1.reset();
document.f1.txtNoFail.value = "";
document.f1.txtNoFail.focus();
}
function Tambah() {
	//document.f1.command.value = "tambah";
	//document.f1.idFlag.value = "1";
	//document.f1.flagno.value = "0";
	document.f1.method="post";
	document.f1.action = "?_portal_module=FrmPrmhnnSek8SenaraiSemakInternal";
	document.f1.submit();
}
function search_data(){
	if (document.f1.txtNoFail.value == "" && document.f1.txtPemohon.value == "" && document.f1.txtSimati.value == "" && document.f1.txtIcSimati.value == ""){
		alert("Sila masukkan maklumat yang ingin dicari.");
	}
	else {
	document.f1.method="post";
	document.f1.command.value = "Cari";
	document.f1.action = "";
	document.f1.submit();
	}
}
function cetak() {
	window.print();
}

function edit_item(id,id2){
	document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idpermohonan.value = id;
	document.f1.idSimati.value = id2;
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

