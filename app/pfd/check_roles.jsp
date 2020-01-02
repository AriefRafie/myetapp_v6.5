#if($portal_role == 'adminpfd' || $portal_role == '(PFD)Penolong Pengarah' || $portal_role == '(PFD)Pengarah' || $portal_role == '(PFD)Pembantu Tadbir' || $portal_role == '(PFD)Pembantu Am Rendah' || $portal_role == '(PFD)User' || $portal_role == '(PFD)Setiausaha')
#set($rolePFD_Dokumen = true)
#else
#set($rolePFD_Dokumen = false)
#end