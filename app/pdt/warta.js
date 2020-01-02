function doChangeTab(TabID,actions) {
    //document.Fekptg_view_pdt_FrmCariMaklumatTRM.selectedTab.value = TabID;
    //document.Fekptg_view_pdt_FrmCariMaklumatTRM.action.value = actions;
    //doAjaxCallFekptg_view_pdt_FrmCariMaklumatTRM("","action=viewTab");	    
	document.Fekptg_view_pdt_FrmCariMaklumatTRM.selectedTab.value=TabID;
	document.Fekptg_view_pdt_FrmCariMaklumatTRM.action2.value="viewTab";
	document.Fekptg_view_pdt_FrmCariMaklumatTRM.action.value="?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
	document.Fekptg_view_pdt_FrmCariMaklumatTRM.method="POST";
	document.Fekptg_view_pdt_FrmCariMaklumatTRM.submit();
}

