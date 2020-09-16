package my.gov.kehakiman.eip.services;

public class CivilRegisterCaseProxy implements my.gov.kehakiman.eip.services.CivilRegisterCase_PortType {
  private String _endpoint = null;
  private my.gov.kehakiman.eip.services.CivilRegisterCase_PortType civilRegisterCase_PortType = null;
  
  public CivilRegisterCaseProxy() {
    _initCivilRegisterCaseProxy();
  }
  
  public CivilRegisterCaseProxy(String endpoint) {
    _endpoint = endpoint;
    _initCivilRegisterCaseProxy();
  }
  
  private void _initCivilRegisterCaseProxy() {
    try {
      civilRegisterCase_PortType = (new my.gov.kehakiman.eip.services.CivilRegisterCase_ServiceLocator()).getCivilRegisterCaseSOAP();
      if (civilRegisterCase_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)civilRegisterCase_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)civilRegisterCase_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (civilRegisterCase_PortType != null)
      ((javax.xml.rpc.Stub)civilRegisterCase_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public my.gov.kehakiman.eip.services.CivilRegisterCase_PortType getCivilRegisterCase_PortType() {
    if (civilRegisterCase_PortType == null)
      _initCivilRegisterCaseProxy();
    return civilRegisterCase_PortType;
  }
  
  public my.gov.kehakiman.eip.services.Civilregistercaseresponse civilRegisterCase(my.gov.kehakiman.eip.services.Civilregistercaserequest parameters) throws java.rmi.RemoteException{
    if (civilRegisterCase_PortType == null)
      _initCivilRegisterCaseProxy();
    return civilRegisterCase_PortType.civilRegisterCase(parameters);
  }
  
  
}