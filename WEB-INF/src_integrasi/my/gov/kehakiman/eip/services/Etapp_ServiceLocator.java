/**
 * Etapp_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package my.gov.kehakiman.eip.services;

public class Etapp_ServiceLocator extends org.apache.axis.client.Service implements my.gov.kehakiman.eip.services.Etapp_Service {

    public Etapp_ServiceLocator() {
    }


    public Etapp_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Etapp_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for etappSOAP
    private java.lang.String etappSOAP_address = "http://sit-ws.kehakiman.gov.my/sit/etapp";

    public java.lang.String getetappSOAPAddress() {
        return etappSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String etappSOAPWSDDServiceName = "etappSOAP";

    public java.lang.String getetappSOAPWSDDServiceName() {
        return etappSOAPWSDDServiceName;
    }

    public void setetappSOAPWSDDServiceName(java.lang.String name) {
        etappSOAPWSDDServiceName = name;
    }

    public my.gov.kehakiman.eip.services.Etapp_PortType getetappSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(etappSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getetappSOAP(endpoint);
    }

    public my.gov.kehakiman.eip.services.Etapp_PortType getetappSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            my.gov.kehakiman.eip.services.EtappSOAPStub _stub = new my.gov.kehakiman.eip.services.EtappSOAPStub(portAddress, this);
            _stub.setPortName(getetappSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setetappSOAPEndpointAddress(java.lang.String address) {
        etappSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (my.gov.kehakiman.eip.services.Etapp_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                my.gov.kehakiman.eip.services.EtappSOAPStub _stub = new my.gov.kehakiman.eip.services.EtappSOAPStub(new java.net.URL(etappSOAP_address), this);
                _stub.setPortName(getetappSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("etappSOAP".equals(inputPortName)) {
            return getetappSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "etapp");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "etappSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("etappSOAP".equals(portName)) {
            setetappSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
