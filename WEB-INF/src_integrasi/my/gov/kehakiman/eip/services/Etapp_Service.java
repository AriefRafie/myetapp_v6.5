/**
 * Etapp_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public interface Etapp_Service extends javax.xml.rpc.Service {
    public java.lang.String getetappSOAPAddress();

    public my.gov.kehakiman.eip.services.Etapp_PortType getetappSOAP() throws javax.xml.rpc.ServiceException;

    public my.gov.kehakiman.eip.services.Etapp_PortType getetappSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
