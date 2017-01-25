/**
 * WsValidaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso;

@SuppressWarnings("serial")
public class WsValidaLocator extends org.apache.axis.client.Service implements aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.WsValida {

    public WsValidaLocator() {
    }


    public WsValidaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsValidaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port
    private java.lang.String CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port_address = "http://aswbmttc:5555/ws/CL_1008_WSNACAR.pub.ws.claveAcceso:wsValida/CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port";

    public java.lang.String getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortAddress() {
        return CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName = "CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port";

    public java.lang.String getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName() {
        return CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName;
    }

    public void setCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName(java.lang.String name) {
        CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName = name;
    }

    public aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port(endpoint);
    }

    public aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_BinderStub _stub = new aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_BinderStub(portAddress, this);
            _stub.setPortName(getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortEndpointAddress(java.lang.String address) {
        CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.Wws_validaClaveAcceso_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_BinderStub _stub = new aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso.CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_BinderStub(new java.net.URL(CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port_address), this);
                _stub.setPortName(getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortWSDDServiceName());
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
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port".equals(inputPortName)) {
            return getCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://aswbmttd/CL_1008_WSNACAR/pub/services/wws_validaClaveAcceso", "wsValida");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://aswbmttd/CL_1008_WSNACAR/pub/services/wws_validaClaveAcceso", "CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_Port".equals(portName)) {
            setCL_1008_WSNACAR_pub_services_wws_validaClaveAcceso_PortEndpointAddress(address);
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
