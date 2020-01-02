<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<form name="f1">
<fieldset>
<p></p>
<fieldset>
<legend>Carian 
</legend>
<table width="100%" align="center" border="0">
  <tbody>
    <tr>
      <td scope="row" width="30%" align="right">No Fail : </td>
      <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" size="50" maxlength="50" style="text-transform:uppercase;"> <input type="hidden" name="idFail" /> </td>
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
      <td width="70%"><select name="jenisIc" style="width: 100px;" onChange="doChangeCategory()">
      <option value="0">Sila Pilih</option>
      <option value="1">No. KP Baru</option>
      <option value="2">No. KP Lama</option>
      <option value="3">No. KP Lain</option>
      </select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
        <tr>
      <td scope="row"></td>
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="getCari()">
      <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset">
      </td>
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
<legend>Senarai Fail Keputusan Permohonan</legend>
<table align="center" border="0" width="100%">
  <tbody>
  
    
      <tr >
        <td  colspan="5"  >
         <div align="right">#if ( $i >= $Senaraifail.size() && $i < $total )
            <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
            #end
                 
               
 	  			 #if (($i < $total && $Senaraifail.size() != $total && $i <= $Senaraifail.size()))
                 <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
                
      			 #end
        </div>
        </td>
      
      </tr>
      <tr class="table_header">
      <td scope="row" width="66px">Bil</td>
      <td width="400px">No Fail</td>
      <td width="300px">Nama Simati</td>
      <td width="250px" align="center">Tarikh Mohon</td>
      
      <td width="400px">Status Fail</td>
    </tr>
    #set ($keputusan_Permohonan = "")
    #set ($tarikhDaftar = "")
    #set ($tarikh_Mohon = "")
    #set ($keterangan = "")
    #set ($no_Fail = "")
    #set ($cnt = 0)
    #if ($carix == "2")
    
    #if($Senaraifail.size()>0)
		    #foreach($fail in $Senaraifail)
		    	#set ($i = $velocityCount)
				        #if (($i % 2) == 0)
				       		#set ($row = "row2")
				        #else
				       		#set ($row = "row1")
				        #end
				        
		    	#set ($cnt = $cnt + 1)
		    	#set ($no_Fail = $fail.no_Fail)
		  		#set ($tarikh_Mohon = $fail.tarikhmohon)
		  		#set ($tarikhDaftar = $fail.tarikhDaftar)
		  		#set ($keputusan_Permohonan = $fail.statuskeputusan)
		  		#set ($keterangan = $fail.keterangan)
		  		#set ($namasimati = $fail.namasimati)
		  		#set ($idPermohonan= $fail.id_Permohonan)
                #set ($idPermohonansimati= $fail.id_Permohonansimati)
                
                
             
		    <tr>
		      <td class="$row">$cnt</td>
		      <td class="$row" style="width: 388px;"><a href="javascript:edit_item('$idPermohonan','$tarikh_Mohon','$idPermohonansimati')" class="style1">$no_Fail</a></td>
		      <td class="$row" style="width: 199px;">$namasimati.toUpperCase()</td>
		      <td class="$row" style="width: 199px;" align="center">$tarikh_Mohon</td>
		      
		      <td class="$row">$keterangan</td>
		    </tr>
            #end
            #else
            
            <tr >
				          <td  colspan="5" align="center"><div align="left">Tiada Rekod </div></td>
    </tr>
		    #end
            
           
	#elseif ($carix == "1")
     #if($Senaraifail1.size()>0)
		    #foreach($fail1 in $Senaraifail1)
		    	#set ($i = $velocityCount)
				        #if (($i % 2) == 0)
				       		#set ($row = "row2")
				        #else
				       		#set ($row = "row1")
				        #end
		    	#set ($cnt = $cnt + 1)
		    	#set ($no_Fail = $fail1.no_Fail)
		  		#set ($tarikh_Mohon = $fail1.tarikh_Mohon)
		  		#set ($tarikhDaftar = $fail1.tarikhDaftar)
		  		#set ($keputusan_Permohonan = $fail1.statuskeputusan)
		  		#set ($idPermohonan= $fail1.id_Permohonan)
		  		#set ($namasimati = $fail1.namasimati)
		  		#set ($keterangan = $fail1.keterangan)
                
                
                 #set($idp=$fail1.id_Permohonan)
                     
                     #if($idp!="" )
		    <tr>
		      <td class="$row">$cnt</td>
		      <td class="$row" style="width: 388px;"><a href="javascript:edit_item('$idPermohonan','$tarikh_Mohon','$idPermohonansimati')" class="style1">$no_Fail</a></td>
		      <td class="$row" style="width: 199px;">$namasimati.toUpperCase()</td>
		      <td class="$row" style="width: 199px;" align="center">$tarikh_Mohon</td>
		     
		      <td class="$row">$keterangan</td>
		    </tr>
            
             #else
            
            <tr >
				          <td  colspan="5" align="center"><div align="left">Tiada Rekod </div></td>
    </tr>
            #end
            
            
		    #end
           
             #else
            
            <tr >
				          <td  colspan="5" align="center"><div align="left">Tiada Rekod </div></td>
    </tr>
            #end
    #end
  </tbody>
</table>
</fieldset>
</fieldset>
<input type="hidden" name="command" />
	<input type="hidden" name="idPermohonan" />
    <input type="hidden" name="tarikh_mohon" />
    <input type="hidden" name="idKeputusanPermohonan" />
    <input type="hidden" name="idpermohonansimati" />
    
    
</form>

<script>
function edit_item(id,tm,sm){
	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	if('$!seksyen_kp'=="8")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	}
	else if('$!seksyen_kp'=="17")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	}
	else
	{
	document.f1.action="";
	}
	document.f1.idPermohonan.value = id;
	document.f1.idpermohonansimati.value = sm;
	document.f1.tarikh_mohon.value = tm;
	document.f1.submit();
}
function getCari() {
	if (document.f1.txtNoFail.value == "" && document.f1.txtPemohon.value == "" && document.f1.txtSimati.value == "" && document.f1.txtIcSimati.value == ""){
		alert("Sila masukkan maklumat yang ingin dicari.");
	}
	else {
	document.f1.method="post";
	document.f1.command.value = "getCari";
	if('$!seksyen_kp'=="8")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	}
	else if('$!seksyen_kp'=="17")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	}
	else
	{
	document.f1.action="";
	}
	document.f1.submit();
	}
}

function seterusnya(){    	
	document.f1.command.value = "next";
	if('$!seksyen_kp'=="8")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	}
	else if('$!seksyen_kp'=="17")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	}
	else
	{
	document.f1.action="";
	}
	document.f1.submit();
}
function sebelumnya(){    	
	document.f1.command.value = "previous";
	if('$!seksyen_kp'=="8")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	}
	else if('$!seksyen_kp'=="17")
	{
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17";
	}
	else
	{
	document.f1.action="";
	}
	document.f1.submit();
}
//function doChangeCategory() {
//	doAjaxCall${formName}("doChangeCategory");
//	}
</script>

