/**
 * Control.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package aswbmttd.CL_1008_WSNACAR.pub.services.wws_validaClaveAcceso;

@SuppressWarnings("serial")
public class Control  implements java.io.Serializable {
    private java.lang.String codigo;

    private java.lang.String codigo_error;

    private java.lang.String mensaje;

    public Control() {
    }

    public Control(
           java.lang.String codigo,
           java.lang.String codigo_error,
           java.lang.String mensaje) {
           this.codigo = codigo;
           this.codigo_error = codigo_error;
           this.mensaje = mensaje;
    }


    /**
     * Gets the codigo value for this Control.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Control.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the codigo_error value for this Control.
     * 
     * @return codigo_error
     */
    public java.lang.String getCodigo_error() {
        return codigo_error;
    }


    /**
     * Sets the codigo_error value for this Control.
     * 
     * @param codigo_error
     */
    public void setCodigo_error(java.lang.String codigo_error) {
        this.codigo_error = codigo_error;
    }


    /**
     * Gets the mensaje value for this Control.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this Control.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    @SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Control)) return false;
        Control other = (Control) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.codigo_error==null && other.getCodigo_error()==null) || 
             (this.codigo_error!=null &&
              this.codigo_error.equals(other.getCodigo_error()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getCodigo_error() != null) {
            _hashCode += getCodigo_error().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Control.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://aswbmttd/CL_1008_WSNACAR/pub/services/wws_validaClaveAcceso", "control"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo_error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigo_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    @SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
