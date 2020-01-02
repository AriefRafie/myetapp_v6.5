





#set($ModuleName = "${session.getAttribute('_portal_module')}")
#set($showLinkHM="")
#if($ModuleName=="ekptg.view.ppt.FrmSek8LaporanAwalTanah" || $ModuleName=="ekptg.view.ppt.FrmSek8Warta"
	|| $ModuleName=="ekptg.view.ppt.FrmSek8PermintaanUkur")
	#set($showLinkHM="no")
#else
	#set($showLinkHM="yes")
#end
			
            
            
                <div id="senaraiHakmilik_hidden"   style="display:none"></div>   	
             
                <input type="hidden" id="input_to" name = "input_to"  />  
                <input type="hidden" id="input_from" name = "input_from"  />  
                <input type="hidden" id="setLimit" name = "setLimit"  />
                
                 #if($saiz_listTanah > 20)
                <table width="99%"  cellpadding="0" cellspacing="2" border="0">  
                #else
                 <table width="100%"  cellpadding="0" cellspacing="2" border="0">  
                #end                   
                    <tr class="table_header">
                  		<td align="center" width="5%"><b>No</b></td>
                  		<td  width="20%"><b>No.Hakmilik</b></td>
                  		<td  width="5%" align="center"><b>Jumlah PB</b></td>
                  		<td  width="20%"><b>No.LOT/No.PT</b></td>              
                  		<td  width="20%"><b>Mukim/Pekan/Bandar</b></td>
                  		<td  width="15%"><b>Luas Lot Diambil</b></td>
                  		#if($!flag_subjaket!="")<td width="5%"><b>No.Subjaket</b></td>#end
                  		#if($flagSegera=="3")<td width="10%"><b>Pengambilan Segera</b></td>#end
                        
           		 	</tr>
                </table>
                <div id="senaraiHakmilik" ></div>        




<script>
$jquery(document).ready(function () {
	doDivAjaxCall$formname('senaraiHakmilik','getSenaraiHakmilik','');		
	doDivAjaxCall$formname('senaraiHakmilik_hidden','getSenaraiHakmilik_hidden','');		
});
</script>

