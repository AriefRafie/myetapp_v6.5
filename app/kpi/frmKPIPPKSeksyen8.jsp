<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>
<f:view>
  <html>
    <head>
      <meta http-equiv="Content-Type"
            content="text/html; charset=windows-1252"/>
      <title>.: eKPTG :. URUSAN PPK PERMOHONAN SEKSYEN 8 [KPI]</title>
    </head>
    <body><jsp:include page="/layout/incHeader.jsp"/><h:form binding="#{backing_htp_frmKPIPPKSeksyen8.form1}" id="form1">
        <tr:panelBox binding="#{backing_htp_frmKPIPPKSeksyen8.panelBox3}"
                     id="panelBox3" inlineStyle="width: 100%"
                     background="transparent">
          <tr:document title="PPK PERMOHONAN SEKSYEN 8"
                       binding="#{backing_htp_frmKPIPPKSeksyen8.document11}"
                       id="document11" onload="#{backing_htp_frmKPIPPKSeksyen8.onLoad}">
            <tr:panelHeader text="PPK PERMOHONAN SEKSYEN 8"
                            binding="#{backing_htp_frmKPIPPKSeksyen8.panelHeader11}"
                            id="panelHeader11">
              <h:inputHidden binding="#{backing_htp_frmKPIPPKSeksyen8.txtIdPermohonan}"
                             id="txtIdPermohonan"/>
            </tr:panelHeader>
            <tr:panelBox binding="#{backing_htp_frmKPIPPKSeksyen8.panelBox11}"
                         id="panelBox11" inlineStyle="width: 100%"
                         background="transparent">
                <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout1}"
                               id="tableLayout1" width="100%">
                   <%-- <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout1}"
                               id="rowLayout1"> --%>
                        <%-- Format tarikh mula--%>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat1}"
                                  id="cellFormat1" valign="top" halign="right">
                            <tr:panelFormLayout binding="#{backing_htp_frmKPIPPKSeksyen8.panelFormLayout3}"
                                  id="panelFormLayout3" >
                                <tr:inputDate label="Tarikh Mula"
                                    binding="#{backing_htp_frmKPIPPKSeksyen8.txdSuratKJP}" id="txdSuratKJP"  >
                                    <tr:convertDateTime pattern="dd/MM/yyyy" />
                                </tr:inputDate>
                            </tr:panelFormLayout>                                    
                        </trh:cellFormat>
                        
                        <%-- Format tarikh mula--%>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2}"
                                  id="cellFormat2" valign="top" halign="left">
                            <tr:panelFormLayout binding="#{backing_htp_frmKPIPPKSeksyen8.panelFormLayout21}"
                                        id="panelFormLayout21">
                                <tr:inputDate label="Tarikh Akhir"
                                    binding="#{backing_htp_frmKPIPPKSeksyen8.txdBukaFail}"
                                    id="txdBukaFail" >
                                    <tr:convertDateTime pattern="dd/MM/yyyy" />
                                </tr:inputDate>
                                <tr:commandButton text="Teruskan"
                              binding="#{backing_htp_frmKPIPPKSeksyen8.commandButton1}"
                              id="commandButton1" action=""/>   
                            </tr:panelFormLayout> 
                        </trh:cellFormat>
                    <%--</trh:rowLayout>--%>
                </trh:tableLayout>
            </tr:panelBox>              
<%-- end of bahagian Pilihan Tarikh--%>

<%-- Start format for display KPI Result--%>
           <tr:panelBox binding="#{backing_htp_frmKPIPPKSeksyen8.panelBox21}"
                         id="panelBox21" inlineStyle="width: 100%" background="transparent" text="">




             
<%-- End format for display KPI Result--%>
           
                 
                <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat10}"
                    id="cellFormat10"   width="75%">--%>
      
                  <tr:table binding="#{backing_htp_frmKPIPPKSeksyen8.table1}"
                            id="table1" width="100%">
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column11}"
                               id="column11" headerText="AKTIVITI" width="35%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column12}"
                               id="column12" headerText="Sasaran Masa (hari)" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column13}"
                               id="column13" headerText="Bilangan Aktiviti" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column14}"
                               id="column14" headerText="Jumlah Masa Sebenar (hari)" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column15}"
                               id="column15" headerText="Purata Masa (hari)" width="5%"/>
                  <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column16}"
                               id="column16" headerText="Kecekapan" width="5%"/>
                     <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column17}"
                               id="column17" headerText="KEBERKESANAN" width="40%"/> <%----%>
                  </tr:table>
   
               <tr:table binding="#{backing_htp_frmKPIPPKSeksyen8.table2}"
                            id="table2" width="100%"
                         verticalGridVisible="true"
                         horizontalGridVisible="true">
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column21}"
                               id="column21" headerText="A" width="35%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column22}"
                               id="column22" headerText="B" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column23}"
                               id="column23" headerText="C" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column24}"
                               id="column24" headerText="D" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column25}"
                               id="column25" headerText="E=D/C" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column26}"
                               id="column26" headerText="F=B/E(%)" width="5%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column27}"
                               id="column27" headerText="Permohonan Menunggu" width="20%"/>
                    <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column28}"
                               id="column28" headerText="Bilangan" width="20%"/>
                </tr:table> <%----%>
            
                <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout2}"
                    id="tableLayout2" width="100%" borderWidth="1">

                    <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat682}"
                    id="cellFormat682"   width="100%">--%>
      
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout22}"
                                       id="rowLayout22" valign="top" width="100%">
                                       
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat011}"
                                          id="cellFormat011" width="35%" >
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText011}"
                                           id="outputText011" value="Bermula dari PT merekod terima Borang A dan
                                           Dokumen sokongan HINGGA menghantar maklumat serentak kepada JPN & 
                                           eTanah untuk semkan maklumat Pemohonan, Pewaris & Hakmilik Simati"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat012}"
                                          id="cellFormat012" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText012}"
                                           id="outputText012" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat013}"
                                          id="cellFormat013" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText013}"
                                           id="outputText013" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat014}"
                                          id="cellFormat014" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText014}"
                                           id="outputText014" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat015}"
                                          id="cellFormat015" width="5%" valign="middle" halign="center">
                               <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText015}"
                                           id="outputText015" />
                         </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat016}"
                                          id="cellFormat016" width="5%" valign="middle" halign="center">
                               <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText016}"
                                           id="outputText016" />
                         </trh:cellFormat>
                         
                         
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat27}"
                                          id="cellFormat27" width="30%"
                                         rowSpan="6" height="100%" >
                               <%-- <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText27}"
                                           id="outputText27" value="1. Bilangan Perjanjian baru diterima."/>--%>
                                           
                                           
                           <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout3}"
                                        id="tableLayout3" width="100%" borderWidth="0">
                                        
                                      <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout01}"
                                                   id="rowLayout01" width="100%">
                                     <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat631}"
                                                      id="cellFormat631"
                                                     width="100%">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText631}"
                                                       id="outputText631" value="1.Bil Perjanjian baru diterima."/>
                                      </trh:cellFormat>
                                      <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat64}"
                                                      id="cellFormat64" width="30%">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText64}"
                                                       id="outputText64" value="2"/>
                                      </trh:cellFormat>--%>
                                      
                                    </trh:rowLayout>
                                    <%--<tr:spacer width="10" height="20"
                                     binding="#{backing_htp_frmKPIPPKSeksyen8.spacer1}"
                                     id="spacer1"/>--%>                                   
                                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout2}"
                                            id="rowLayout2" width="100%">                                    
                                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat47}"
                                              id="cellFormat47" width="100%" >
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText47}"  
                                                value="2.Bil Perjanjian Baru dihantar Semula kepada KJP untuk pembetulan"/>
                          
                        </trh:cellFormat>
                                             <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat65}"
                                                          id="cellFormat65" width="40%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText65}"
                                                           id="outputText65" value=""/>
                                            </trh:cellFormat>  --%>   
                                    </trh:rowLayout>
                                    
                                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout274}"
                                            id="rowLayout274"width="100%">
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2741}"
                                                          id="cellFormat2741" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2741}"
                                                           id="outputText2741" value="3.Bil Perjanjian dalam Proses:"/>
                                            </trh:cellFormat>
                                           <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2742}"
                                                          id="cellFormat2742" width="40%">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2742}"
                                                           id="outputText2742" value=""/>
                                            </trh:cellFormat>--%>
                                        </trh:rowLayout>
                                 
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout270}"
                                            id="rowLayout270"width="100%">
                                               <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat270}"
                                                       id="cellFormat270" width="100%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText270}"
                                                           id="outputText270" value="a. Menunggu Ulasan dari PKP"/>
                                            </trh:cellFormat>
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat271}"
                                                          id="cellFormat271" width="40%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText271}"
                                                           id="outputText271" value=""/>
                                            </trh:cellFormat>--%>
                                        </trh:rowLayout>
        
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout271}"
                                            id="rowLayout271"width="100%">
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat272}"
                                                          id="cellFormat272" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText272}"
                                                           id="outputText272" value=""/>
                                            </trh:cellFormat>--%>
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat273}"
                                                          id="cellFormat273" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText273}"
                                                           id="outputText273" value="<7 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                                        
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout272}"
                                            id="rowLayout272"width="100%">
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat274}"
                                                          id="cellFormat274" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText274}"
                                                           id="outputText6274" value=""/>
                                            </trh:cellFormat>--%>
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat275}"
                                                          id="cellFormat275" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText275}"
                                                           id="outputText275" value="<8-14 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                      
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout273}"
                                            id="rowLayout273"width="100%">
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat276}"
                                                          id="cellFormat276" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText276}"
                                                           id="outputText276" value=""/>
                                            </trh:cellFormat>--%>
                                           <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat277}"
                                                          id="cellFormat277" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText277}"
                                                           id="outputText277" value=">15 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                                        
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout275}"
                                            id="rowLayout275"width="100%">
                                               <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2751}"
                                                       id="cellFormat2751" width="100%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2751}"
                                                           id="outputText2751"
                                                               value="b. Menunggu pengembalian semula Perjanjian yang                                                             telah disetemkan oleh KJP"/>
                                            </trh:cellFormat>
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2752}"
                                                          id="cellFormat2752" width="40%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2752}"
                                                           id="outputText2752" value=""/>
                                            </trh:cellFormat>--%>
                                        </trh:rowLayout>
        
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout276}"
                                            id="rowLayout276"width="100%">
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2761}"
                                                          id="cellFormat2761" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2761}"
                                                           id="outputText2761" value=""/>
                                            </trh:cellFormat>--%>
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2762}"
                                                          id="cellFormat2762" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2762}"
                                                           id="outputText2762" value="<7 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                                        
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout277}"
                                            id="rowLayout277"width="100%">
                                            <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2771}"
                                                          id="cellFormat2771" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2771}"
                                                           id="outputText2771" value=""/>
                                            </trh:cellFormat>--%>
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2772}"
                                                          id="cellFormat2772" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2772}"
                                                           id="outputText2772" value="<8-14 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                      
                                        <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout278}"
                                            id="rowLayout278"width="100%">
                                           <%--<trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2781}"
                                                          id="cellFormat27811" width="60%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2781}"
                                                           id="outputText2781" value=""/>
                                            </trh:cellFormat>--%>
                                           <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2782}"
                                                          id="cellFormat2782" width="100%" halign="right">
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2782}"
                                                           id="outputText2782" value=">15 Hari"/>
                                            </trh:cellFormat>
                                        </trh:rowLayout>
                                        
        
                                </trh:tableLayout>                                           
                                           
                                           
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat28}"
                                          id="cellFormat28" width="10%" height="100%" rowSpan="6">
                               <%--<tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText28}"
                                           id="outputText28" />--%>
                                           
                                                           
                               <%----%>  
                            <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout4}"
                                id="tableLayout4" width="100%" borderWidth="0">
                                
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout17}"
                                    id="rowLayout17" width="100%" valign="middle" halign="center">
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat64}"
                                        id="cellFormat64" width="100%" height="8%">
                                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText64}"
                                           id="outputText64" />

                                    </trh:cellFormat>
                                </trh:rowLayout>
                                
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout7}"
                                            id="rowLayout7" width="100%" >                                    
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat65}"
                                        id="cellFormat65" width="100%" height="15%" valign="middle" halign="center">
                                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText65}"
                                            id="outputText65" value="0"/>
                                    </trh:cellFormat>
                                </trh:rowLayout>  
                                
                               <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout8}"
                                    id="rowLayout8" width="100%" >
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2742}"
                                        id="cellFormat2742" width="100%" height="8%"  >
                                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2742}"
                                                id="outputText2742" value="X"/>
                                    </trh:cellFormat>
                                </trh:rowLayout>
                                 
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout9}"
                                            id="rowLayout9" width="100%">
                                   <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat271}"
                                                          id="cellFormat271" width="100%" height="8%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText271}"
                                                           id="outputText271" value="X"/>
                                            </trh:cellFormat>
                                </trh:rowLayout>
        
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout10}"
                                            id="rowLayout10" width="100%">
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat272}"
                                                          id="cellFormat272" width="100%" height="8%" valign="middle" halign="center">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText272}"
                                                           id="outputText272" />
                                    </trh:cellFormat>
                                </trh:rowLayout>
                                        
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout11}"
                                            id="rowLayout11" width="100%" >
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat274}"
                                                          id="cellFormat274" width="100%" height="8%" valign="middle" halign="center">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText274}"
                                                           id="outputText6274" />
                                            </trh:cellFormat>
                                
                                </trh:rowLayout>
                      
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout12}"
                                            id="rowLayout12" width="100%">
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat276}"
                                                          id="cellFormat276" width="100%" height="8%" valign="middle" halign="center">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText276}"
                                                           id="outputText276" />
                                            </trh:cellFormat>
                                </trh:rowLayout>
                                        
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout13}"
                                            id="rowLayout13" width="100%">
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2752}"
                                                          id="cellFormat2752" width="100%" height="15%">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2752}"
                                                           id="outputText2752" value="X"/>
                                    </trh:cellFormat>
                                </trh:rowLayout>
        
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout14}"
                                            id="rowLayout14" width="100%">
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2761}"
                                                          id="cellFormat2761" width="100%" height="8%" valign="middle" halign="center">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2761}"
                                                           id="outputText2761" value="9"/>
                                            </trh:cellFormat>
                                </trh:rowLayout>
                                        
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout15}"
                                            id="rowLayout15" width="100%">
                                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2771}"
                                                          id="cellFormat2771" width="100%" height="8%" valign="middle" halign="center">
                                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2771}"
                                                           id="outputText2771" value="10"/>
                                            </trh:cellFormat>
                                </trh:rowLayout>
                      
                                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout16}"
                                            id="rowLayout16"width="100%">
                                    <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat2781}"
                                        id="cellFormat27811" width="100%" height="8%" valign="middle" halign="center">
                                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText2781}"
                                            id="outputText2781" value="11"/>
                                    </trh:cellFormat>
                                </trh:rowLayout>
                                    
                            </trh:tableLayout> 
                                           
                                           
                        </trh:cellFormat>
                    </trh:rowLayout>
                    <%--Nombor X.
                    
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout002}"
                               id="rowLayout002" width="100%">
 
                       <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat21}"
                                          id="cellFormat21" width="35%">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText21}"
                                           id="outputText21" value="Bermula Dari PTUPT Jana Surat Iringan Beserta 
                                                Dokumen HINGGA Di Hantar Kpd JT "/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat22}"
                                          id="cellFormat22" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText22}"
                                           id="outputText22" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat23}"
                                          id="cellFormat23" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText23}"
                                           id="outputText23" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat24}"
                                          id="cellFormat24" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText24}"
                                           id="outputText24"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat25}"
                                          id="cellFormat25" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText25}"
                                           id="outputText25"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat26}"
                                          id="cellFormat26" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText26}"
                                           id="outputText26"/>
                          </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
                    <%--Nombor 2.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout3}"
                               id="rowLayout3" width="100%" valign="top">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat31}"
                                          id="cellFormat31" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText31}"
                                           id="outputText31" value="Menunggu keputusan JPN menyemak maklumat
                                           Pemohon dan Waris"/>
                          </trh:cellFormat>
                    </trh:rowLayout>
              
                     <%--Nombor X.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout4}"
                               id="rowLayout4" width="100%">
 
                       <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat41}"
                                          id="cellFormat41" width="35%">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText41}"
                                           id="outputText41" value="Bermula dari PTUPT Terima Laporan JT HINGGA 
                                                Sediakan Kertas J/K Teknikal Tanah Serta Kertas 
                                                MMK/MB/KM/LC Di Hantar PTG/eTanah/JKPTG"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat42}"
                                          id="cellFormat42" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText42}"
                                           id="outputText42" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat43}"
                                          id="cellFormat43" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText43}"
                                           id="outputText43" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat44}"
                                          id="cellFormat44" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText44}"
                                           id="outputText44"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat45}"
                                          id="cellFormat45" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText45}"
                                           id="outputText45"/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat46}"
                                          id="cellFormat46" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText46}"
                                           id="outputText46"/>
                          </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
                   <%--Nombor 3.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout5}"
                               id="rowLayout5" width="100%" valign="top">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat51}"
                                          id="cellFormat51" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText51}"
                                           id="outputText51" value="Menunggu Keputusan PTD(eTanah) menyemak maklumat Hakmilik Simati"/>
                          </trh:cellFormat>
                    </trh:rowLayout>
                    
                    <%--Nombor 4.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout6}"
                                       id="rowLayout6" width="100%">
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat61}"
                                          id="cellFormat61" width="35%">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText61}"
                                           id="outputText61" value="Bermula Dari PT menerima maklumat pengesahan HINGGA dihantar 
                                           kepada PTD untuk penyediaan Resit/Bil Pembayaran."/>
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat62}"
                                          id="cellFormat62" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText62}"
                                           id="outputText62" />
                          </trh:cellFormat>
                          <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat63}"
                                          id="cellFormat63" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText63}"
                                           id="outputText63" />
                          </trh:cellFormat>
                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat064}"
                                          id="cellFormat064" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText064}"
                                           id="outputText064" />
                            </trh:cellFormat>
                            <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat065}"
                                          id="cellFormat065" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText065}"
                                           id="outputText065" />
                          </trh:cellFormat>
                           <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat066}"
                                          id="cellFormat066" width="5%" valign="middle" halign="center">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText066}"
                                           id="outputText066" />
                          </trh:cellFormat>
                    </trh:rowLayout><%----%>

                   <%--Nombor 5.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout007}"
                               id="rowLayout007" width="100%">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat71}"
                                          id="cellFormat71" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText71}"
                                           id="outputText71" value="Menunggu PTD mendaftar dan menyedia Resit/Bil Pembayaran"/>
                          </trh:cellFormat>
                    </trh:rowLayout>
                    
                   <%--Nombor 6.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout008}"
                                       id="rowLayout008" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat81}"
                                          id="cellFormat81" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText81}"
                                           id="outputText81" value="Bermula dari PT menerima Resit, serta mencetak 
                                           Borang Penilaian HINGGA PPSPP selesai menilai sendiri atau bersama JPPH."/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat82}"
                                          id="cellFormat82" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText82}"
                                   id="outputText82" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat83}"
                                          id="cellFormat83" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText83}"
                                   id="outputText83" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat84}"
                                          id="cellFormat84" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText84}"
                                   id="outputText84" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat85}"
                                          id="cellFormat85" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText85}"
                                   id="outputText55" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat86}"
                                          id="cellFormat86" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText86}"
                                   id="outputText86" />
                        </trh:cellFormat>
                    </trh:rowLayout>

                   <%--Nombor 7.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout009}"
                               id="rowLayout009" width="100%">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat91}"
                                          id="cellFormat91" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText91}"
                                           id="outputText91" value="Menunggu penilaian dari JPPH"/>
                          </trh:cellFormat>
                    </trh:rowLayout>
                    
               <%--Nombor 8.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout010}"
                                       id="rowLayout010" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat101}"
                                          id="cellFormat101" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText101}"
                                           id="outputText101" value="Bermula Dari PT menerima Penilaian dari JPPH serta 
                                           mengisi Borang B HINGGA dihantar ke Mahkamah Tinggi."/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat102}"
                                          id="cellFormat102" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText102}"
                                   id="outputText102" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat103}"
                                          id="cellFormat103" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText103}"
                                   id="outputText103" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat104}"
                                          id="cellFormat104" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText104}"
                                   id="outputText104" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat105}"
                                          id="cellFormat105" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText105}"
                                   id="outputText105" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat106}"
                                          id="cellFormat106" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText106}"
                                   id="outputText106" />
                        </trh:cellFormat>
                    </trh:rowLayout>
                    
                   <%--Nombor 9.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout011}"
                               id="rowLayout011" width="100%">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat111}"
                                          id="cellFormat111" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText111}"
                                           id="outputText111" value="Menunggu Mahkamah Tinggi keluarkan Borang C. "/>
                          </trh:cellFormat>
                    </trh:rowLayout>
                    
               <%--Nombor 10.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout012}"
                                       id="rowLayout012" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat121}"
                                          id="cellFormat121" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText121}"
                                           id="outputText121" value="Bermula Dari PT menerima Penilaian dari PPSPP &
                                           JPPH HINGGA tentukan Bidang Kuasa UPP ATAU pindahkan Fail  ke Mahkamah Tinggi."/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat122}"
                                          id="cellFormat122" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText122}"
                                   id="outputText122" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat123}"
                                          id="cellFormat123" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText123}"
                                   id="outputText123" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat124}"
                                          id="cellFormat124" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText124}"
                                   id="outputText124" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat125}"
                                          id="cellFormat125" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText125}"
                                   id="outputText125" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat126}"
                                          id="cellFormat126" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText126}"
                                   id="outputText126" />
                        </trh:cellFormat>
                    </trh:rowLayout>
                    
               <%--Nombor 11.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout013}"
                                       id="rowLayout013" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat131}"
                                          id="cellFormat131" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText131}"
                                           id="outputText131" value="Bermula Dari PT menerima Borang C disusuli Permohonan
                                           Awal ARB/Kaveat LA Hingga penyediaaan Notis perbicaraan Mahkamah Tinggi."/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat132}"
                                          id="cellFormat132" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText132}"
                                   id="outputText132" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat133}"
                                          id="cellFormat133" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText133}"
                                   id="outputText133" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat134}"
                                          id="cellFormat134" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText134}"
                                   id="outputText134" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat135}"
                                          id="cellFormat135" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText135}"
                                   id="outputText135" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat136}"
                                          id="cellFormat136" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText136}"
                                   id="outputText136" />
                        </trh:cellFormat>
                    </trh:rowLayout>
                    
                   <%--Nombor X.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout014}"
                               id="rowLayout014" width="100%">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat141}"
                                          id="cellFormat141" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText141}"
                                           id="outputText141" value="Menunggu Maklumat Ukuran Dari JUPEM"/>
                          </trh:cellFormat>
                    </trh:rowLayout>
                    --%>
               <%--Nombor 15.--%>
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout015}"
                                       id="rowLayout015" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat151}"
                                          id="cellFormat151" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText151}"
                                           id="outputText151" value="Bermula Dari PT menerima Borang Perintah(E,EE,F,H,HH)
                                           HINGGA Nota Bicara dijilidkan & Penyimpanan Fail."/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat152}"
                                          id="cellFormat152" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText152}"
                                   id="outputText152" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat153}"
                                          id="cellFormat153" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText153}"
                                   id="outputText153" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat154}"
                                          id="cellFormat154" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText154}"
                                   id="outputText154" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat155}"
                                          id="cellFormat155" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText155}"
                                   id="outputText155" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat156}"
                                          id="cellFormat156" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText156}"
                                   id="outputTex1t156" />
                        </trh:cellFormat>
                    </trh:rowLayout>
                    
                   <%--Nombor 16.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout016}"
                               id="rowLayout016" width="100%">
                         <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat161}"
                                          id="cellFormat161" columnSpan="6">
                                <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText161}"
                                           id="outputText161" value="Bermula Dari PPTUPT Buat Kiraan Keluasan  hantar 
                                                kpd Pgh/PP/PPT HINGGA…….. "/>
                          </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
               <%--Nombor 17.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout017}"
                                       id="rowLayout017" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat171}"
                                          id="cellFormat171" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText171}"
                                           id="outputText171" value="Jika Sama:Orang Berkepentingan Terima HM Sambungan 
                                            dan TAMAT"/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat172}"
                                          id="cellFormat172" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText172}"
                                   id="outputText52" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat173}"
                                          id="cellFormat173" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText173}"
                                   id="outputText173" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat174}"
                                          id="cellFormat174" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText174}"
                                   id="outputText174" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat175}"
                                          id="cellFormat175" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText175}"
                                   id="outputText175" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat176}"
                                          id="cellFormat176" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText176}"
                                   id="outputText176" />
                        </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
               <%--Nombor 18.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout18}"
                                       id="rowLayout18" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat181}"
                                          id="cellFormat181" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText181}"
                                           id="outputText181" value="Jika Kurang:URUSAN 03.04 : PROSES PENGAMBILAN TANAH DI BAWAH SEKSYEN 8, APT 1960
                                                Hantar Surat kpd KJP untuk Membuat Bayaran 
                                                Tambahan, Kemaskini Maklumat dan TAMAT"/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat182}"
                                          id="cellFormat182" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText182}"
                                   id="outputText182" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat183}"
                                          id="cellFormat183" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText183}"
                                   id="outputText183" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat184}"
                                          id="cellFormat184" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText184}"
                                   id="outputText184" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat185}"
                                          id="cellFormat185" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText185}"
                                   id="outputText185" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat186}"
                                          id="cellFormat186" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText186}"
                                   id="outputText186" />
                        </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
               <%--Nombor 19.
                    <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout19}"
                                       id="rowLayout19" width="100%">
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat191}"
                                          id="cellFormat191" width="35%">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText191}"
                                           id="outputText191" value="Jika Kurang:Hantar Surat Tuntutan Lebih Bayar Kpd Orang 
                                                Berkepentingan Sedia HM Sambungan & TAMAT"/>
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat192}"
                                          id="cellFormat192" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText192}"
                                   id="outputText192" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat193}"
                                          id="cellFormat193" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText193}"
                                   id="outputText193" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat194}"
                                          id="cellFormat194" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText54}"
                                   id="outputText194" />
                        </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat195}"
                                          id="cellFormat195" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText195}"
                                   id="outputText195" />
                             </trh:cellFormat>
                        <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat196}"
                                          id="cellFormat196" width="5%" valign="middle" halign="center">
                            <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText196}"
                                   id="outputText196" />
                        </trh:cellFormat>
                    </trh:rowLayout>--%>
                    
                </trh:tableLayout>
    

              



 
                
                
   
                
              
              
              
                <%-- <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout6}"
                               id="tableLayout6" width="25%" borderWidth="2">

                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout6}"
                               id="rowLayout6"width="100%">
                 <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat61}"
                                  id="cellFormat61" width="50%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText61}"
                                   id="outputText61" value="5"/>
                  </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat62}"
                                  id="cellFormat62" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText62}"
                                   id="outputText62" />
                  </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat63}"
                                  id="cellFormat63" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText63}"
                                   id="outputText63" />
                  </trh:cellFormat>--%><%--
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat64}"
                                  id="cellFormat64" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText64}"
                                   id="outputText64" />
                    </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat65}"
                                  id="cellFormat65" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText65}"
                                   id="outputText65" />
                </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat66}"
                                  id="cellFormat66" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText66}"
                                   id="outputText66" />
                  </trh:cellFormat>
                             <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat68}"
                                  id="cellFormat68" width="10%">
                        <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText68}"
                                   id="outputText68" />
                  </trh:cellFormat>
                </trh:rowLayout>
                </trh:tableLayout>   --%>                
 
                  
              <%-- 
              <tr:table binding="#{backing_htp_frmKPIPPKSeksyen8.table111}"
                        id="table111" width="100%">
                <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column111}"
                           id="column111" headerText="No." width="5%"/>
                <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column211}"
                           id="column211" headerText="Nama Pemilik" width="75%"/>
                <tr:column binding="#{backing_htp_frmKPIPPKSeksyen8.column311}"
                           id="column311" headerText="No. KP/Polis/Tentera/Syarikat" width="20%"/>
              </tr:table>
              <trh:tableLayout binding="#{backing_htp_frmKPIPPKSeksyen8.tableLayout2}"
                               id="tableLayout2" width="100%" borderWidth="2">
                <trh:rowLayout binding="#{backing_htp_frmKPIPPKSeksyen8.rowLayout21}"
                               id="rowLayout21">
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat31}"
                                  id="cellFormat31" width="5%">
                    <tr:outputText binding="#{backing_htp_frmKPIPPKSeksyen8.outputText1211}"
                                   id="outputText1211" value="1"/>
                  </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat41}"
                                  id="cellFormat41" width="75%">
                    <tr:inputText
                                  binding="#{backing_htp_frmKPIPPKSeksyen8.txtNamaPemilik}"
                                  id="txtNamaPemilik" columns="80"
                                  onchange="javascript:this.value=this.value.toUpperCase();"
                                  readOnly="#{pageFlowScope.mode == 2}" />
                  </trh:cellFormat>
                  <trh:cellFormat binding="#{backing_htp_frmKPIPPKSeksyen8.cellFormat51}"
                                  id="cellFormat51" width="20%">
                    <tr:inputText
                                  binding="#{backing_htp_frmKPIPPKSeksyen8.txtNoKP}"
                                  id="txtNoKP" columns="20"
                                  readOnly="#{pageFlowScope.mode == 2}" />
                  </trh:cellFormat>
                </trh:rowLayout>
              </trh:tableLayout> --%>
              <tr:panelFormLayout binding="#{backing_htp_frmKPIPPKSeksyen8.panelFormLayout1}"
                                  id="panelFormLayout1"
                                  inlineStyle="width: 100%" fieldWidth="70%"
                                  labelWidth="30%">
                 <tr:inputHidden binding="#{backing_htp_frmKPIPPKSeksyen8.txtIdHakUrus}"
                                id="txtIdHakUrus"/>
                <tr:inputHidden binding="#{backing_htp_frmKPIPPKSeksyen8.txtIdPihak}"
                                id="txtIdPihak"/>                             
              </tr:panelFormLayout>
            </tr:panelBox>
            <tr:panelButtonBar binding="#{backing_htp_frmKPIPPKSeksyen8.panelButtonBar11}"
                               id="panelButtonBar11" halign="center">
            <%--<tr:commandButton text="Kembali"
                                binding="#{backing_htp_frmKPIPPKSeksyen8.cmdKembali1}"
                                id="cmdKembali1" action="MaklumatPemilik"/>    --%>                         
              <tr:commandButton text="Kemaskini"
                                binding="#{backing_htp_frmKPIPPKSeksyen8.cmdKemaskini1}"
                                id="cmdKemaskini1"
                                rendered="#{pageFlowScope.mode == 2}" >
                <tr:setActionListener from="#{3}"
                                        to="#{pageFlowScope.mode}"/>
                <tr:setActionListener from="#{backing_htp_frmKPIPPKSeksyen8.txtIdPihak.localValue}"
                                        to="#{pageFlowScope.idPihak}"/>
              </tr:commandButton>
              <tr:commandButton text="Hapus"
                                binding="#{backing_htp_frmKPIPPKSeksyen8.cmdHapus1}"
                                id="cmdHapus1"
                                onclick="if (!confirm(\'Adakah anda pasti untuk teruskan ?\')) return false;"
                                rendered="#{pageFlowScope.mode == 2}"
                                action="#{backing_htp_frmKPIPPKSeksyen8.cmdHapus_action}"/>
              <tr:commandButton text="Simpan"
                                binding="#{backing_htp_frmKPIPPKSeksyen8.cmdSimpan1}"
                                id="cmdSimpan1"
                                onclick="if (!confirm(\'Adakah anda pasti untuk teruskan ?\')) return false;"
                                rendered="#{pageFlowScope.mode == 1 or pageFlowScope.mode == 3}"
                                action="#{backing_htp_frmKPIPPKSeksyen8.cmdSimpan_action}"/>
              <tr:commandButton text="Batal"
                                binding="#{backing_htp_frmKPIPPKSeksyen8.cmdBatal1}"
                                id="cmdBatal1"
                                rendered="#{pageFlowScope.mode == 1 or pageFlowScope.mode == 3}"
                                action="#{backing_htp_frmKPIPPKSeksyen8.cmdBatal_action}"/>

            </tr:panelButtonBar>
           <%----%> 
          </tr:document>
        </tr:panelBox>
      </h:form><jsp:include page="/layout/incFooter.jsp"/></body>
  </html>
</f:view>
<%-- oracle-jdev-comment:auto-binding-backing-bean-name:backing_htp_frmKPIPPKSeksyen8--%>