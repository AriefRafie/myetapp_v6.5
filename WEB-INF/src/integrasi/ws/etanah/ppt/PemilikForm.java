/**
 * PemilikForm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.etanah.ppt;

public class PemilikForm  implements java.io.Serializable {
    private java.lang.String BA;

    private java.lang.String BB;

    private java.lang.String idJenisPengenalan;

    private java.lang.String jenisPB;

    private java.lang.String namaPemilik;

    private java.lang.String noPengenalan;

    public PemilikForm() {
    }

    public PemilikForm(
           java.lang.String BA,
           java.lang.String BB,
           java.lang.String idJenisPengenalan,
           java.lang.String jenisPB,
           java.lang.String namaPemilik,
           java.lang.String noPengenalan) {
           this.BA = BA;
           this.BB = BB;
           this.idJenisPengenalan = idJenisPengenalan;
           this.jenisPB = jenisPB;
           this.namaPemilik = namaPemilik;
           this.noPengenalan = noPengenalan;
    }


    /**
     * Gets the BA value for this PemilikForm.
     * 
     * @return BA
     */
    public java.lang.String getBA() {
        return BA;
    }


    /**
     * Sets the BA value for this PemilikForm.
     * 
     * @param BA
     */
    public void setBA(java.lang.String BA) {
        this.BA = BA;
    }


    /**
     * Gets the BB value for this PemilikForm.
     * 
     * @return BB
     */
    public java.lang.String getBB() {
        return BB;
    }


    /**
     * Sets the BB value for this PemilikForm.
     * 
     * @param BB
     */
    public void setBB(java.lang.String BB) {
        this.BB = BB;
    }


    /**
     * Gets the idJenisPengenalan value for this PemilikForm.
     * 
     * @return idJenisPengenalan
     */
    public java.lang.String getIdJenisPengenalan() {
        return idJenisPengenalan;
    }


    /**
     * Sets the idJenisPengenalan value for this PemilikForm.
     * 
     * @param idJenisPengenalan
     */
    public void setIdJenisPengenalan(java.lang.String idJenisPengenalan) {
        this.idJenisPengenalan = idJenisPengenalan;
    }


    /**
     * Gets the jenisPB value for this PemilikForm.
     * 
     * @return jenisPB
     */
    public java.lang.String getJenisPB() {
        return jenisPB;
    }


    /**
     * Sets the jenisPB value for this PemilikForm.
     * 
     * @param jenisPB
     */
    public void setJenisPB(java.lang.String jenisPB) {
        this.jenisPB = jenisPB;
    }


    /**
     * Gets the namaPemilik value for this PemilikForm.
     * 
     * @return namaPemilik
     */
    public java.lang.String getNamaPemilik() {
        return namaPemilik;
    }


    /**
     * Sets the namaPemilik value for this PemilikForm.
     * 
     * @param namaPemilik
     */
    public void setNamaPemilik(java.lang.String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }


    /**
     * Gets the noPengenalan value for this PemilikForm.
     * 
     * @return noPengenalan
     */
    public java.lang.String getNoPengenalan() {
        return noPengenalan;
    }


    /**
     * Sets the noPengenalan value for this PemilikForm.
     * 
     * @param noPengenalan
     */
    public void setNoPengenalan(java.lang.String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PemilikForm)) return false;
        PemilikForm other = (PemilikForm) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BA==null && other.getBA()==null) || 
             (this.BA!=null &&
              this.BA.equals(other.getBA()))) &&
            ((this.BB==null && other.getBB()==null) || 
             (this.BB!=null &&
              this.BB.equals(other.getBB()))) &&
            ((this.idJenisPengenalan==null && other.getIdJenisPengenalan()==null) || 
             (this.idJenisPengenalan!=null &&
              this.idJenisPengenalan.equals(other.getIdJenisPengenalan()))) &&
            ((this.jenisPB==null && other.getJenisPB()==null) || 
             (this.jenisPB!=null &&
              this.jenisPB.equals(other.getJenisPB()))) &&
            ((this.namaPemilik==null && other.getNamaPemilik()==null) || 
             (this.namaPemilik!=null &&
              this.namaPemilik.equals(other.getNamaPemilik()))) &&
            ((this.noPengenalan==null && other.getNoPengenalan()==null) || 
             (this.noPengenalan!=null &&
              this.noPengenalan.equals(other.getNoPengenalan())));
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
        if (getBA() != null) {
            _hashCode += getBA().hashCode();
        }
        if (getBB() != null) {
            _hashCode += getBB().hashCode();
        }
        if (getIdJenisPengenalan() != null) {
            _hashCode += getIdJenisPengenalan().hashCode();
        }
        if (getJenisPB() != null) {
            _hashCode += getJenisPB().hashCode();
        }
        if (getNamaPemilik() != null) {
            _hashCode += getNamaPemilik().hashCode();
        }
        if (getNoPengenalan() != null) {
            _hashCode += getNoPengenalan().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PemilikForm.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.etanah/", "pemilikForm"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idJenisPengenalan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idJenisPengenalan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jenisPB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jenisPB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("namaPemilik");
        elemField.setXmlName(new javax.xml.namespace.QName("", "namaPemilik"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noPengenalan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noPengenalan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
