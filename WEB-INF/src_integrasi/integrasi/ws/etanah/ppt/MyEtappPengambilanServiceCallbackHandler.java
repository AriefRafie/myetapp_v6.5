/**
 * MyEtappPengambilanServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package integrasi.ws.etanah.ppt;


/**
 *  MyEtappPengambilanServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class MyEtappPengambilanServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public MyEtappPengambilanServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public MyEtappPengambilanServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for endorsBorangK method
     * override this method for handling normal response from endorsBorangK operation
     */
    public void receiveResultendorsBorangK(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangKResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from endorsBorangK operation
     */
    public void receiveErrorendorsBorangK(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for hakmilikDetailByCarianResit method
     * override this method for handling normal response from hakmilikDetailByCarianResit operation
     */
    public void receiveResulthakmilikDetailByCarianResit(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.HakmilikDetailByCarianResitResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from hakmilikDetailByCarianResit operation
     */
    public void receiveErrorhakmilikDetailByCarianResit(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for endorsBorangDMaklumatWarta method
     * override this method for handling normal response from endorsBorangDMaklumatWarta operation
     */
    public void receiveResultendorsBorangDMaklumatWarta(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.EndorsBorangDMaklumatWartaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from endorsBorangDMaklumatWarta operation
     */
    public void receiveErrorendorsBorangDMaklumatWarta(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for maklumanPenghantaranPU method
     * override this method for handling normal response from maklumanPenghantaranPU operation
     */
    public void receiveResultmaklumanPenghantaranPU(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumanPenghantaranPUResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from maklumanPenghantaranPU operation
     */
    public void receiveErrormaklumanPenghantaranPU(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for daftarPermohonanSek8 method
     * override this method for handling normal response from daftarPermohonanSek8 operation
     */
    public void receiveResultdaftarPermohonanSek8(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8ResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from daftarPermohonanSek8 operation
     */
    public void receiveErrordaftarPermohonanSek8(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for sijilPembebasanUkur method
     * override this method for handling normal response from sijilPembebasanUkur operation
     */
    public void receiveResultsijilPembebasanUkur(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.SijilPembebasanUkurResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from sijilPembebasanUkur operation
     */
    public void receiveErrorsijilPembebasanUkur(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for daftarPermohonanBorangAMMk method
     * override this method for handling normal response from daftarPermohonanBorangAMMk operation
     */
    public void receiveResultdaftarPermohonanBorangAMMk(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMkResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from daftarPermohonanBorangAMMk operation
     */
    public void receiveErrordaftarPermohonanBorangAMMk(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for borangCdanMMK method
     * override this method for handling normal response from borangCdanMMK operation
     */
    public void receiveResultborangCdanMMK(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMKResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from borangCdanMMK operation
     */
    public void receiveErrorborangCdanMMK(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for maklumatWartaborangB method
     * override this method for handling normal response from maklumatWartaborangB operation
     */
    public void receiveResultmaklumatWartaborangB(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatWartaborangBResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from maklumatWartaborangB operation
     */
    public void receiveErrormaklumatWartaborangB(java.lang.Exception e) {
    }
}
