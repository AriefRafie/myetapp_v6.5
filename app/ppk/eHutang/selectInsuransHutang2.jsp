<select id="insuransHutang" name="insuransHutang" style="width:25%" onChange="doDivAjaxCall3$formname('divMainForm','doChangeInsuransHutang2','')" $!disabled>
    <option value="">SILA PILIH</option>
    <option #if ( $!hutang.insuransHutang == 'Y' ) selected #end value="Y">YA</option>
    <option #if ( $!hutang.insuransHutang == 'T' ) selected #end value="T">TIDAK</option>
</select>