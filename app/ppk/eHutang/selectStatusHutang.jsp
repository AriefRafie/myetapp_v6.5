<select id="statusHutang" name="statusHutang" style="width:50%" onChange="doDivAjaxCall3$formname('divMainForm','doChangeStatusHutang','')" $!disabled>
    <option value="">SILA PILIH</option>
    <option #if ( $!hutang.statusHutang == 'Y' ) selected #end value="Y">SELESAI</option>
    <option #if ( $!hutang.statusHutang == 'T' ) selected #end value="T">BELUM SELESAI</option>
</select>