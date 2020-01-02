 #if($listPermohonanTukarPegawaiSejarah.size()>0)	

	<table width='100%' align='center' border='0' cellpadding='3' cellspacing='1' style='margin-top:5px;margin-bottom:5px' class="box_shadow classFade"  >
    
    <tr>
    <td width="2%"></td>
    <td width="96%">
  
    <fieldset><legend>Sejarah Permohonan Pertukaran Pegawai</legend>
        <table border="0" cellspacing="1" cellpadding="3" align="center" style='margin-top:5px;margin-bottom:5px' width="100%"  >    
        <tr class="table_header" >
               <td   align="center" valign="top" >Bil.</td>
               <td   align="left" valign="top">No. Tukar Pegawai</td>
               <td   align="left" valign="top">No. Fail (Bil. Bicara)</td>
               <td   align="left" valign="top">Waktu Bicara</td>
               <td   align="left" valign="top">Status Bicara</td>
               <td   align="left" valign="top">Pegawai Asal</td>
               <td   align="left" valign="top">Pegawai Ganti</td>            
               <td   align="left" valign="top">Keputusan Tukar Pegawai</td>
               <td   align="left" valign="top">Catatan Pemohon</td>
               <td   align="left" valign="top">Catatan Pelulus</td>
              
        </tr>
        #if($listPermohonanTukarPegawaiSejarah.size()>0)	
            #foreach($pr in $listPermohonanTukarPegawaiSejarah)
            <tr  >
                   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_TUKARPEGAWAI</td>
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_FAIL ($pr.BIL_BICARA)</td>
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.TARIKH_BICARA ($pr.MASA_BICARA)               
                   </td>
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.STATUS_BICARA</td>	
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_PEGAWAI_ASAL</td>	
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_PEGAWAI_BARU<br />($pr.NAMA_NEGERI_PEGAWAI_GANTI)</td>	
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.KETERANGAN_STATUS_TUKARPEGAWAI</td>
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.CATATAN_PEMOHON</td>	
                   <td class="$pr.rowCss"  align="left" valign="top" >$pr.CATATAN_PELULUS</td>	
             </tr>	   
            #end
        #else
        <tr >
               <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>		     
        </tr>
        #end
        </table>
        </fieldset>
       
        </td>
        <td width="2%"></td>
        </tr>
        </table>

#end