$viewSubFoler.KETERANGAN
<span id="ShowDalamTindakanContent_$viewSubFoler.ID_MESYUARATCONTENT">
#if($viewSubFoler.DALAM_TINDAKAN > 0)
               <br />
			   <font color='red' class="blink">* $viewSubFoler.DALAM_TINDAKAN Menunggu Tindakan Bahagian</font> 
                <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divTindakan$viewSubFoler.ID_MESYUARATCONTENT','emelTindakanContent','FLAG_TINDAKAN_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$viewSubFoler.ID_MESYUARATUTAMA&ID_REFER=$viewSubFoler.ID_REFER&ID_MESYUARATCONTENT=$viewSubFoler.ID_MESYUARATCONTENT&LAYER=$viewSubFoler.LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val()+'&JUMLAH_TINDAKAN=$viewSubFoler.JUMLAH_TINDAKAN');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
#end
</span>
