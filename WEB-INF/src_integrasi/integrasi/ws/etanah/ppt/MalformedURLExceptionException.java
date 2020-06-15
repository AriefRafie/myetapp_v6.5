/**
 * MalformedURLExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package integrasi.ws.etanah.ppt;

import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MalformedURLExceptionE;

public class MalformedURLExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1590751995271L;
    private integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MalformedURLExceptionE faultMessage;

    public MalformedURLExceptionException() {
        super("MalformedURLExceptionException");
    }

    public MalformedURLExceptionException(java.lang.String s) {
        super(s);
    }

    public MalformedURLExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public MalformedURLExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MalformedURLExceptionE msg) {
        faultMessage = msg;
    }

    public integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MalformedURLExceptionE getFaultMessage() {
        return faultMessage;
    }
}
