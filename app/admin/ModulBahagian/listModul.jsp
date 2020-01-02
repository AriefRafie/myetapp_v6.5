#foreach($dj in $checklistModul)                    
    <input type="hidden" id="ID_MODUL" name="ID_MODUL" value="$dj.ID_MODUL" />
    <input #if($dj.ID_MODULBAHAGIAN!='') checked #end type="checkbox"  name="CHECKLIST_" id="CHECKLIST_" value="$dj.ID_MODUL" >
     $dj.NAMA_MODUL 
<br />
#end