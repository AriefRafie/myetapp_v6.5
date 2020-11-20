
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<br/>


<fieldset>
<legend><b>SENARAI BORANG C YANG DITERIMA (<font color="#FFFF00">SUDAH MENDAPAT MAKLUMBALAS</font>)</b></legend>

  #if($Senaraifail.size()>50)
  <div style="height:1000;overflow:auto" >
  #end
  
<table align="center" width="100%"> 
  	
  
  
    <tr class="table_header">
      <td width="5%"><div align="center">Bil</div></td>
      <td width="20%"><div align="center">No Fail</div></td>
      <td width="15%"><div align="center">Tarikh Terima Borang C</div></td>
      <td width="30%"><div align="center">Keputusan Borang C</div></td>
      <td width="15%"><div align="center">Semak Borang C</div></td>
    </tr>
   
                         #if($Senaraifail.size()==0)
                         <tr>
				         <td  colspan="6" align="center"><div align="left">Tiada Rekod </div></td>
				         </tr>
                         #else

	                       #foreach($fail in $Senaraifail)
                           
                        <!--   #set($bilno=$bilno+1) -->
                           #if($fail.bil % 2!=0)
          
					      <tr class="row1">
				          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.bil</div></td>
                          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.noPetisyen</div></td>
				          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhJanaBrgC</div></td>
				          <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">#if ($fail.kepBrgC=="WH") PUTIH #elseif ($fail.kepBrgC=="YL") KUNING #end</div></td>
                           <td class="row1"><div align="center">
                             <input name="cetak" type="button" value="Semak Borang C" onclick="javascript:semakBorangC('$fail.noPetisyen')" />
                           </div></td>
				          </tr>
                          #else
                          
                          <tr class="row2">
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.bil</div></td>
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.noPetisyen</div></td>
                          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhJanaBrgC</div></td>
				          <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">#if ($fail.kepBrgC=="WH") PUTIH #elseif ($fail.kepBrgC=="YL") KUNING #end</div></td>
                          <td class="row2"><div align="center">
                            <input name="cetak" type="button" value="Semak Borang C" onclick="javascript:semakBorangC('$fail.noPetisyen')" />
                          </div></td>
				          </tr>
                          
                          #end
                          
                          #end
				        
				          #end
				     
	      
        
  
     
        					
   
  </table>
   #if($Senaraifail.size()>50)
  </div >
  #end
</fieldset>


<input type="hidden" name="command" />
    
 
<script>

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
function semakBorangC(x) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmMTBorangC?noFail="+x+"&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=700, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
   
}


</script>