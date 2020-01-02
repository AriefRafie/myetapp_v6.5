#if($DALAM_TINDAKAN > 0)
               <br />
			   <font color='red' class="blink"><b>* $DALAM_TINDAKAN Menunggu Tindakan Bahagian</b></font>
               <a href="javascript:if(confirm('Emel akan dihantar. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_viewFolderUtama$viewMainFoler.ID_MESYUARATUTAMA','emelTindakanMain','NAMA_FOLDER=$viewMainFoler.TAJUK_MESYUARAT&FLAG_OPENCLOSE=CLOSE&ID_MESYUARATUTAMA=$viewMainFoler.ID_MESYUARATUTAMA&LAYER=1&AUTOLOAD=Y&FlagCari=Y$FlagCari&MAX_LAYER=');}"><img title="Emel"  src="../img/emel.gif" border="0"></a>
 
  #end
