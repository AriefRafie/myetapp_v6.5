<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
<p></p>
<form name="f1" method="post">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<fieldset>
<legend><b>CARIAN</b>
</legend>
<table width="100%" align="center" border="0">

  <tbody>
    <tr>
      <td width="29%" height="24" scope="row" align="right"><div align="right">No. Rujukan <i>Online</i> </div></td>
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
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="search_data()" />
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="cancel()" /></td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;      </td>
    </tr>
  </tbody>
</table>
</fieldset>

<br/>

#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFailOnline"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())

<fieldset>
<legend><b>SENARAI PERMOHONAN <i>ONLINE</i></b></legend>
<table width="100%">
<tr>
     		<td colspan="5" scope="row" align="right">
    			 #if ( $i >= $Senaraifail.size() && $i < $total )
      			 <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
                
      			 #end
                 
               
 	  			  #if (($Senaraifail.size()< $total && $Senaraifail.size() != $total && ($i +25) <= $total))
      			 <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
                
      			 #end
                
    		</td>
    	</tr>
</table>
<table align="center" width="100%"> 
  <tbody>
  	
  
  
    <tr class="table_header">
      <td width="5%"><div align="center">Bil.</div></td>
      <td width="20%">No. Rujukan <i>Online</i></td>
       <td width="20%"><div align="center">No. Fail</div></td>
      <td width="15%"><div align="center">Tarikh Permohonan</div></td>
     
      <td width="30%"><div align="left">Nama Pemohon</div></td>
      <td width="15%"><div align="center">No. KP Pemohon</div></td>
      
     <td width="15%"><div align="center">Cetak Borang P</div></td>
     <td width="15%"><div align="center">Kemaskini Borang P</div></td>
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
   
                         #if($Senaraifail.size()==0)
                         <tr>
				         <td  colspan="6" align="center"><div align="left">Tiada Rekod </div></td>
				         </tr>
                         #else
	                       #foreach($fail in $Senaraifail)
                          
                           #set ($bilno = $fail.bil)
                           
                        <!--   #set($bilno=$bilno+1) -->
                           #if($bilno%2!=0)
          
					      <tr class="row1">
				          <td class="row1"><div align="center">$bilno.</div></td>
				          <td class="row1"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1">
				          $fail.noonline</a></td>
                          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.no_Fail</div></td>
				          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhmohononline</div></td>
				      
				          <td class="row1"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"> 
                        
                          
                        <!--  <a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1"> -->
                        
				          $fail.namapemohon
                          <!-- </a> -->
                          
                          </div></td>
				          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.nokppemohon</div></td>
                         
                           <td class="row1"><div align="center">
                          
                          <!-- 
                     noonline :: $fail.noonline
                     idfailbaru :: $fail.id_Fail
                     idfaildulu :: $fail.id_FailDulu
                     id_permohonan :: $fail.id_Permohonan
                      
                           
                           -->
                           
                           
                           
          <input name="cetak" type="button" value="Cetak Borang P" onclick="cetakBorangP('$fail.noonline','$fail.id_Fail','$fail.id_FailDulu','$fail.id_Permohonan')" />
                           </div></td>
                           
                            <td class="row1">
                        <!--    id_permohonan ::: $fail.id_Permohonan
                            <br />id_simati :::: $fail.id_Simati
                            <br />seksyen ::::: $fail.seksyen
                            <br />id_pemohon ::::: $fail.id_Pemohon
                            <br />subjaket ::::: $fail.no_subjaket
                          -->  
                            <div align="center">
                         
                             <input name="kemaskini" type="button" value="Kemaskini Borang P" onclick="papar('$fail.id_Permohonan','$fail.id_Simati','$fail.seksyen','$fail.id_Pemohon','$fail.no_subjaket')" /> 
                            
                             
                           </div></td>
                          
				          </tr>
                          #else
                          
                          <tr class="row2">
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$bilno.</div></td>
				          <td class="row2"><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1">
				          $fail.noonline</a></td>
                          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.no_Fail</div></td>
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhmohononline</div></td>
				          
				          <td class="row2"><div align="left" style="text-transform:uppercase;" onblur="uppercase()"> 
                          
                        
                      <!--      <a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1"> -->
				          $fail.namapemohon
                          
                          <!--</a>-->
                          </div>
                          
                          </td>
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.nokppemohon</div></td>
                         
                          <td class="row2"><div align="center">
                            <input name="cetak" type="button" value="Cetak Borang P" onclick="cetakBorangP('$fail.noonline','$fail.id_Fail','$fail.id_FailDulu','$fail.id_Permohonan')" />
       
                          </div></td>
                         
                         <td class="row2"><div align="center">
                            
                              <input name="kemaskini" type="button" value="Kemaskini Borang P" onclick="papar('$fail.id_Permohonan','$fail.id_Simati','$fail.seksyen','$fail.id_Pemohon','$fail.no_subjaket')" />
                          
                          </div></td>
                        
				          </tr>
                          
                          #end
                          
                          #end
				        
				          #end
				     
	      
        
  
     
        					
         
        
  </tbody>
   
  </table>
</fieldset>


<input type="hidden" name="command" />
	<input type="hidden" name="idpermohonan"/>
    <input type="hidden" name="idSimati"/>
    <input type="hidden" name="idFlag" />
    <input type="hidden" name="flagno" />
</form>
 
<script>
function cancel() {
document.f1.reset();
document.f1.txtRujukanOnline.value = "";
document.f1.txtNamaPemohon.value = "";
document.f1.txtnokppemohon.value = "";


}
function Tambah() {
	//document.f1.command.value = "tambah";
	//document.f1.idFlag.value = "1";
	//document.f1.flagno.value = "0";
	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8SenaraiSemak";
	document.f1.submit();
}
function search_data(){
	if (document.f1.txtRujukanOnline.value == "" && document.f1.txtNamaPemohon.value == "" && document.f1.txtnokppemohon.value == ""){
		alert("Sila masukkan no rujukan online, nama pemohon atau no kp pemohon yang ingin dicari!");		
		document.f1.txtRujukanOnline.focus(); 
		return; 
	}
	else {
	
	document.f1.command.value = "Carian";
	document.f1.action = "";
	document.f1.submit();
	}
}
function cetak() {
	window.print();
}
function edit_item(id,id2,id3,id4){
input_box=confirm("Sila Pastikan Borang P telah diserahkan sebelum permohonan ini diteruskan.");
		if (input_box == true) 
		{
	document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idpermohonan.value = id;
	document.f1.idSimati.value = id2;
	
	document.f1.flagno.value = id3;
	document.f1.idFlag.value = id4;
	document.f1.submit();
	}
	
	
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
function cetakBorangA(noFailOnline) 
{
    var url = "../servlet/ekptg.report.ppk.BorangAOnline?NoPermohonan="+noFailOnline;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangP(nopermohonan,idfail,idfaillama,idpermohonanbaru) {
    var url="../servlet/ekptg.report.ppk.BorangP?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
	 if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
/*
function cetakBorangP(idfail,idfaillama,idpermohonanbaru) {
    var url="../servlet/ekptg.report.ppk.BorangP?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
	 if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
*/



//arief comment
/**function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {


 var url = "../x/${securityToken}/ekptg.view.ppk.FrmBorangPSek17Online?command=Simati&mode=Simatiview&idPermohonan="+idPermohonan+"&idpermohonan="+idPermohonan+"&idPemohon="+idpemohon+"&idSimati="+idSimati+"&no_subjaket="+(parseInt(RemoveNonNumericX(no_subjaket)) - 1);
 
   var hWnd = window.open(url,'Cetak','width=1000,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

		
}**/

//arief add
function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {


 var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangPOnline?command=Simati&mode=Simatiview&idPermohonan="+idPermohonan+"&idpermohonan="+idPermohonan+"&idPemohon="+idpemohon+"&idSimati="+idSimati+"&no_subjaket="+(parseInt(RemoveNonNumericX(no_subjaket)) - 1);
 
   var hWnd = window.open(url,'Cetak','width=1000,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

		
}

function RemoveNonNumericX( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}



</script>

<!-- <p align="right">CL – 01 – 26 </p>	 -->