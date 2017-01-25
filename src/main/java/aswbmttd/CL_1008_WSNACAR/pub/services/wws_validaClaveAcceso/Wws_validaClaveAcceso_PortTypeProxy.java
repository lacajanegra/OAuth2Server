package aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso;

public class Wws_validaClaveAcceso_PortTypeProxy implements aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType {
  private String _endpoint = null;
  private aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType wws_validaClaveAcceso_PortType = null;
  
  public Wws_validaClaveAcceso_PortTypeProxy() {
    _initWws_validaClaveAcceso_PortTypeProxy();
  }
  
  public Wws_validaClaveAcceso_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWws_validaClaveAcceso_PortTypeProxy();
  }
  
  private void _initWws_validaClaveAcceso_PortTypeProxy() {
    try {
      wws_validaClaveAcceso_PortType = (new aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.WsValidaLocator()).getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port();
      if (wws_validaClaveAcceso_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wws_validaClaveAcceso_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wws_validaClaveAcceso_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wws_validaClaveAcceso_PortType != null)
      ((javax.xml.rpc.Stub)wws_validaClaveAcceso_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType getWws_validaClaveAcceso_PortType() {
    if (wws_validaClaveAcceso_PortType == null)
      _initWws_validaClaveAcceso_PortTypeProxy();
    return wws_validaClaveAcceso_PortType;
  }
  
  public void ws_validaClaveAcceso(java.lang.String rut, java.lang.String dv, java.lang.String clave, javax.xml.rpc.holders.StringHolder validado, aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.holders.ControlHolder control) throws java.rmi.RemoteException{
    if (wws_validaClaveAcceso_PortType == null)
      _initWws_validaClaveAcceso_PortTypeProxy();
    wws_validaClaveAcceso_PortType.ws_validaClaveAcceso(rut, dv, clave, validado, control);
  }
  
  
}