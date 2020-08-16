#if ($!command == 'simpanUlasan')
	#if ($!flagStatus == 'Y')
    	<div class="success">MAKLUMAT BERJAYA DISIMPAN.</div>
    #else
    	<div class="error">MAKLUMAT TIDAK BERJAYA DISIMPAN.</div>
    #end
#end 

#if ($!command == 'hantarUlasan')
	#if ($!flagStatus == 'Y')
    	<div class="success">MAKLUMAT BERJAYA DIHANTAR.</div>
    #else
    	<div class="error">MAKLUMAT TIDAK BERJAYA DIHANTAR.</div>
    #end
#end 

#if ($!command == 'refreshDokumenMuatNaik')
	#if ($!flagStatus == 'Y')
    	<div class="success">LAMPIRAN BERJAYA DIMUATNAIK.</div>
    #else
    	<div class="error">LAMPIRAN TIDAK BERJAYA DIMUATNAIK.</div>
    #end
#end 