
#if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
     
<style type="text/css">
<!--
.styleXX {color: #0000FF}
-->
</style>
<p align="right">
  #foreach ($countdunia in $count_dunia)
              #set($nofaildunia = $countdunia.no_fail_dunia)
              
              #end
            
  <div align="right">Bil Fail Keseluruhan : <span class="styleXX">$!nofaildunia</span></div>
  </p>
#end