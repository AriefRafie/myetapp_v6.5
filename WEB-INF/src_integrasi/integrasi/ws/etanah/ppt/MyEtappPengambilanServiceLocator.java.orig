/**
 * MyeTaPPPengambilanServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.etanah.ppt;

public class MyEtappPengambilanServiceLocator extends org.apache.axis.client.Service implements MyEtappPengambilanService {

    public MyEtappPengambilanServiceLocator() {
    }


    public MyEtappPengambilanServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyEtappPengambilanServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MyEtappPengambilanPort
    private java.lang.String MyEtappPengambilanPort_address = "http://kjsb.zapto.org:6009/etanahwsa/MyEtappPengambilanService";

    public java.lang.String getMyEtappPengambilanPortAddress() {
        return MyEtappPengambilanPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MyEtappPengambilanPortWSDDServiceName = "MyEtappPengambilanPort";

    public java.lang.String getMyEtappPengambilanPortWSDDServiceName() {
        return MyEtappPengambilanPortWSDDServiceName;
    }

    public void setMyEtappPengambilanPortWSDDServiceName(java.lang.String name) {
        MyEtappPengambilanPortWSDDServiceName = name;
    }

    public MyEtappPengambilan getMyEtappPengambilanPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MyEtappPengambilanPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMyEtappPengambilanPort(endpoint);
    }

    public MyEtappPengambilan getMyEtappPengambilanPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            MyEtappPengambilanPortBindingStub _stub = new MyEtappPengambilanPortBindingStub(portAddress, this);
            _stub.setPortName(getMyEtappPengambilanPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMyEtappPengambilanPortEndpointAddress(java.lang.String address) {
        MyEtappPengambilanPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (MyEtappPengambilan.class.isAssignableFrom(serviceEndpointInterface)) {
                MyEtappPengambilanPortBindingStub _stub = new MyEtappPengambilanPortBindingStub(new java.net.URL(MyEtappPengambilanPort_address), this);
                _stub.setPortName(getMyEtappPengambilanPortWSDDServiceName());
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
        if ("MyEtappPengambilanPort".equals(inputPortName)) {
            return getMyEtappPengambilanPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.etanah/", "MyEtappPengambilanService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.etanah/", "MyEtappPengambilanPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MyEtappPengambilanPort".equals(portName)) {
            setMyEtappPengambilanPortEndpointAddress(address);
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
