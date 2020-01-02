<br />
#set($header = "STATISTIK PERANAN PENGGUNA DALAM SISTEM MYETAPP")
<fieldset>

<div id="printableArea_Laporan" >
#set($tot = $listModuleForStat.size() + 1)
#set($tot = 95 / $tot)
<table style="border-collapse:collapse;" width="95%" align="center" cellpadding="2" cellspacing="1">
<thead >
<tr  >
<th colspan="15" align="center" style="font-size:90%;">$header<br><br></th>
</tr>
<tr   class="row2">
<th style="border:1px solid black;" valign="top" width="5%"></th>
#foreach($n in $listModuleForStat)
<th style="border:1px solid black; font-size:65%;" valign="top" align="center" width="$tot%">$n.NAMA_SEKSYEN</th>
#end
</tr>
</thead >

#set($id_seksyen = "")
#set($nama_seksyen = "")
#set($nama_modul = "")
#foreach($rep in $listStatModuleRole)

    #if($rep.MODULE_NAME != $nama_modul)
        #set($nama_modul = $rep.MODULE_NAME)
        #set($jumlah = 0.0)
        <tr  >
            <td style="border :1px solid black; font-size:65%;" class="row2"  valign="top" align="center">
            <b>$nama_modul</b>       
            </td>
            #foreach($xxx in $listModuleForStat)
            <td style="border:1px solid black; font-size:65%; " valign="top" align="right">
                #foreach($repNegeri in $listStatModuleRole)
                    #if($repNegeri.ID_SEKSYEN == $xxx.ID_SEKSYEN && $repNegeri.MODULE_NAME == $nama_modul )
                    $repNegeri.TOTAL_PENGGUNA
                        <!--
                        #if($repNegeri.ID_SEKSYEN == $id_seksyen)
                        <td style="border:1px solid black; font-size:65%; " valign="top" align="right">
                        $repNegeri.TOTAL_PENGGUNA
                        #set($jumlah = $jumlah + $repNegeri.TOTAL_PENGGUNA)               
                        </td>
                        #end
                        -->
                    #end
                #end
            </td>    
            #end
           
        <tr>
    #end
	    
#end
</table>
</div>
<br />



<table border="0" width="95%" align="center" cellpadding="2" cellspacing="1">
<tr>
<td style="border-top:1px solid black;" align="center">
<input type="button" id="BTNPRINTLAPORAN" name="BTNPRINTLAPORAN"  onclick="printLaporan('printableArea_Laporan','$header')" value="Cetak" />
</td>
</tr>
</table>

</fieldset>
