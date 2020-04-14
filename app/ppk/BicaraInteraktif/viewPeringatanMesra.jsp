#set($checkXLengkap = "")
#if($totalHM > 0 && $totalKeterangan < $totalHM)
    <div ><h5><span class="blink red" ><i>Peringatan : </i></span>
    <span class="red" >Masih terdapat $totalBezaHM daripada $totalHM keterangan kehadiran 'waris' yang belum dicatat</span>
    </h5></div>	
    #set($checkXLengkap = "Y")
#end

#if($totalTH > 0 && $totalKeteranganTH < $totalTH)
    <div ><h5><span class="blink red" ><i>Peringatan : </i></span>
    <span class="red" >Masih terdapat $totalBezaTH daripada $totalTH keterangan kehadiran 'turut hadir' yang belum dicatat</span>
    </h5></div>	
    #set($checkXLengkap = "Y")
#end
<input type="hidden" id="checkCatatankeputusan" name="checkCatatankeputusan" value = "$checkXLengkap" >