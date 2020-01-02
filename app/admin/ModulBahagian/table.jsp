

<div id="printableArea_Laporan" >
#set($tot = $listTableModul.size() + 1)
#set($tot = 90 / $tot)
<table style="border-collapse:collapse;" width="98%" align="center" cellpadding="2" cellspacing="1">
<thead >
<tr class=""  >
<th colspan="30" align="center" style="font-size:100%;">Statistik Peranan Pengguna<br></th>
</tr>
<tr   class="row2" >
<th style="border:1px solid black;font-size:65%;background-color:#E8B6B7;" valign="top" colspan="2"><b>MODUL</b></th>
#foreach($n in $listTableModul)
<th style="border:1px solid black; font-size:65%;background-color:#E8B6B7;"  valign="top" align="center" width="$tot%"><b>$n.KOD_MODUL</b></th>
#end
</tr>
<tr   class="row2">
<th style="border:1px solid black;  font-size:65%;background-color:#E8B6B7;"  valign="top" colspan="2"><b>JUMLAH PERANAN</b></th>
#foreach($n in $listTableModul)
<th style="border:1px solid black; font-size:65%;background-color:#E8B6B7;"  valign="top" align="center" width="$tot%"><b>$n.TOTAL_ROLE</b></th>
#end
</tr>
<tr   class="row2">
<th style="border:1px solid black;  font-size:65%;background-color:#E8B6B7;"   valign="top" ><b>BAHAGIAN</b></th>
<th style="border:1px solid black;  font-size:65%;background-color:#E8B6B7;"   valign="top" ><b>JUMLAH PENGGUNA</b></th>
<th style="border:1px solid black; font-size:65%;background-color:#E8B6B7;"   valign="top" align="center" width="$tot%" colspan="$listTableModul.size()"></th>
</tr>
</thead >
#foreach($rep in $listTableBahagian)
	
     <tr>
    	<td style="border :1px solid black; font-size:65%;background-color:#E8B6B7;"      valign="top" align="center" width="5%">
        <b>$rep.KOD_SEKSYEN</b>    
    	</td>
        <td style="border :1px solid black; font-size:65%;background-color:#E8B6B7;"     valign="top" align="center" width="5%">
        <b>$rep.TOTAL_PENGGUNA</b>    
    	</td>
        #foreach($mod in $listTableModul)
             <td style="border:1px solid black; font-size:65%;" class="row"  valign="top" align="center">
            	 #foreach($bahagianmod in $listTableBahagianModul)
                 
                     #if($bahagianmod.ID_MODUL == $mod.ID_MODUL && $bahagianmod.ID_BAHAGIAN == $rep.ID_SEKSYEN)
                     <img title="Kemaskini" src="../img/validyes.png" border="0">
                     #end
                 
                 #end
                
             </td>              
        #end
     </tr>
    
#end
</table>
</div>
<table style="border-collapse:collapse;" width="98%" align="center" cellpadding="2" cellspacing="1">
<tr>
<td align="center">
<input type="button" id="BTNPRINTLAPORAN" name="BTNPRINTLAPORAN" 
                    onclick="printLaporan('printableArea_Laporan','Statistik Peranan Pengguna')" 
                    value="Cetak" />
                    </td>
                    </tr>
                    </table>
                    