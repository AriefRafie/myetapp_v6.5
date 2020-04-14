
/**
 * EtappPengambilanServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package integrasi.ws.etanah.melaka_ns.ppt;

    /**
     *  EtappPengambilanServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EtappPengambilanServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EtappPengambilanServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EtappPengambilanServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for insertMaklumatWarta_byObject method
            * override this method for handling normal response from insertMaklumatWarta_byObject operation
            */
           public void receiveResultinsertMaklumatWarta_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatWarta_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertMaklumatWarta_byObject operation
           */
            public void receiveErrorinsertMaklumatWarta_byObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertDokumen_byObject method
            * override this method for handling normal response from insertDokumen_byObject operation
            */
           public void receiveResultinsertDokumen_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDokumen_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertDokumen_byObject operation
           */
            public void receiveErrorinsertDokumen_byObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertMaklumatPengambilan_byObject method
            * override this method for handling normal response from insertMaklumatPengambilan_byObject operation
            */
           public void receiveResultinsertMaklumatPengambilan_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatPengambilan_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertMaklumatPengambilan_byObject operation
           */
            public void receiveErrorinsertMaklumatPengambilan_byObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertDerafMMK_byObject method
            * override this method for handling normal response from insertDerafMMK_byObject operation
            */
           public void receiveResultinsertDerafMMK_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMK_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertDerafMMK_byObject operation
           */
            public void receiveErrorinsertDerafMMK_byObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for uploadDokumen method
            * override this method for handling normal response from uploadDokumen operation
            */
           public void receiveResultuploadDokumen(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.UploadDokumenResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from uploadDokumen operation
           */
            public void receiveErroruploadDokumen(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertHakmilikList_byObject method
            * override this method for handling normal response from insertHakmilikList_byObject operation
            */
           public void receiveResultinsertHakmilikList_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertHakmilikList_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertHakmilikList_byObject operation
           */
            public void receiveErrorinsertHakmilikList_byObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertDerafMMKTajuk_byObject method
            * override this method for handling normal response from insertDerafMMKTajuk_byObject operation
            */
           public void receiveResultinsertDerafMMKTajuk_byObject(
                    integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMKTajuk_byObjectResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertDerafMMKTajuk_byObject operation
           */
            public void receiveErrorinsertDerafMMKTajuk_byObject(java.lang.Exception e) {
            }
                


    }
    