/**
 * ExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package integrasi.ws.etanah.ppt;

import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.ExceptionE;

public class ExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1590751995276L;
    private integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.ExceptionE faultMessage;

    public ExceptionException() {
        super("ExceptionException");
    }

    public ExceptionException(java.lang.String s) {
        super(s);
    }

    public ExceptionException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public ExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.ExceptionE msg) {
        faultMessage = msg;
    }

    public integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.ExceptionE getFaultMessage() {
        return faultMessage;
    }
}
