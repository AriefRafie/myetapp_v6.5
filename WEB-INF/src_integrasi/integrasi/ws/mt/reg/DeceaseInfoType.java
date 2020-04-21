/**
 * DeceaseInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.mt.reg;


/**
 * Decease Information Name
 */
public class DeceaseInfoType  implements java.io.Serializable {
    private java.lang.String deceaseInfoName;

    private java.lang.String deceaseInfoType;

    /* Identity Type 1 of Decease Possible
     *                 				Value: 1) IC = NRIC 2) PO = Police 3) PP
     *                 				= Passport 4) SO = Army 5) OC = Old
     *                 				Identity Card 6) OT = Others 7) PDC =
     *                 				Presumption of Death Case No. If
     *                 				[DeceaseInfoType1] = 1, set Default
     *                 				Value as IC */
    private java.lang.String deceaseInfoIDType1;

    /* Decease Info ID Type 1 Number. If
     *                 				[DeceaseInfoType1] = IC, include dash
     *                 				- from the New IC No */
    private java.lang.String deceaseInfoIDType1No;

    /* Identity Type 2 of Decease Possible
     *                 				Value: 1) PO = Police 2) PP = Passport
     *                 				3) SO = Army 4) OC = Old Identity Card
     *                 				5) OT = Others 6) PDC = Presumption of
     *                 				Death Case No. */
    private java.lang.String deceaseInfoIDType2;

    /* Decease Info ID Type 2 Number */
    private java.lang.String deceaseInfoIDType2No;

    /* Death Cert Number / Reference Number */
    private java.lang.String deathCertNo;

    /* Gender of Decease Possible Value: 1) F =
     *                 				Female 2) M = Male 3) U = Undefined */
    private java.lang.String deceaseInfoGender;

    /* Decease Info Age */
    private java.lang.String deceaseInfoAge;

    /* Date Of death */
    private java.lang.String dateOfDeath;

    /* Address Line 1 of Decease Info */
    private java.lang.String deceaseInfoAddr1;

    /* Address Line 2 of Decease Info */
    private java.lang.String deceaseInfoAddr2;

    /* Address Line 3 of Decease Info */
    private java.lang.String deceaseInfoAddr3;

    /* Address Postcode of Decease Info */
    private java.lang.String deceaseInfoPostcode;

    /* Address city of Decease Info */
    private java.lang.String deceaseInfoCity;

    /* Address State of Decease Info Possible
     *                 				Value: Refer to appendix State Code */
    private java.lang.String deceaseInfoState;

    /* Address Country of Decease Info.
     * Possible Value: Refer to appendix Country Code */
    private java.lang.String deceaseInfoCountry;

    public DeceaseInfoType() {
    }

    public DeceaseInfoType(
           java.lang.String deceaseInfoName,
           java.lang.String deceaseInfoType,
           java.lang.String deceaseInfoIDType1,
           java.lang.String deceaseInfoIDType1No,
           java.lang.String deceaseInfoIDType2,
           java.lang.String deceaseInfoIDType2No,
           java.lang.String deathCertNo,
           java.lang.String deceaseInfoGender,
           java.lang.String deceaseInfoAge,
           java.lang.String dateOfDeath,
           java.lang.String deceaseInfoAddr1,
           java.lang.String deceaseInfoAddr2,
           java.lang.String deceaseInfoAddr3,
           java.lang.String deceaseInfoPostcode,
           java.lang.String deceaseInfoCity,
           java.lang.String deceaseInfoState,
           java.lang.String deceaseInfoCountry) {
           this.deceaseInfoName = deceaseInfoName;
           this.deceaseInfoType = deceaseInfoType;
           this.deceaseInfoIDType1 = deceaseInfoIDType1;
           this.deceaseInfoIDType1No = deceaseInfoIDType1No;
           this.deceaseInfoIDType2 = deceaseInfoIDType2;
           this.deceaseInfoIDType2No = deceaseInfoIDType2No;
           this.deathCertNo = deathCertNo;
           this.deceaseInfoGender = deceaseInfoGender;
           this.deceaseInfoAge = deceaseInfoAge;
           this.dateOfDeath = dateOfDeath;
           this.deceaseInfoAddr1 = deceaseInfoAddr1;
           this.deceaseInfoAddr2 = deceaseInfoAddr2;
           this.deceaseInfoAddr3 = deceaseInfoAddr3;
           this.deceaseInfoPostcode = deceaseInfoPostcode;
           this.deceaseInfoCity = deceaseInfoCity;
           this.deceaseInfoState = deceaseInfoState;
           this.deceaseInfoCountry = deceaseInfoCountry;
    }


    /**
     * Gets the deceaseInfoName value for this DeceaseInfoType.
     * 
     * @return deceaseInfoName
     */
    public java.lang.String getDeceaseInfoName() {
        return deceaseInfoName;
    }


    /**
     * Sets the deceaseInfoName value for this DeceaseInfoType.
     * 
     * @param deceaseInfoName
     */
    public void setDeceaseInfoName(java.lang.String deceaseInfoName) {
        this.deceaseInfoName = deceaseInfoName;
    }


    /**
     * Gets the deceaseInfoType value for this DeceaseInfoType.
     * 
     * @return deceaseInfoType
     */
    public java.lang.String getDeceaseInfoType() {
        return deceaseInfoType;
    }


    /**
     * Sets the deceaseInfoType value for this DeceaseInfoType.
     * 
     * @param deceaseInfoType
     */
    public void setDeceaseInfoType(java.lang.String deceaseInfoType) {
        this.deceaseInfoType = deceaseInfoType;
    }


    /**
     * Gets the deceaseInfoIDType1 value for this DeceaseInfoType.
     * 
     * @return deceaseInfoIDType1   * Identity Type 1 of Decease Possible
     *                 				Value: 1) IC = NRIC 2) PO = Police 3) PP
     *                 				= Passport 4) SO = Army 5) OC = Old
     *                 				Identity Card 6) OT = Others 7) PDC =
     *                 				Presumption of Death Case No. If
     *                 				[DeceaseInfoType1] = 1, set Default
     *                 				Value as IC
     */
    public java.lang.String getDeceaseInfoIDType1() {
        return deceaseInfoIDType1;
    }


    /**
     * Sets the deceaseInfoIDType1 value for this DeceaseInfoType.
     * 
     * @param deceaseInfoIDType1   * Identity Type 1 of Decease Possible
     *                 				Value: 1) IC = NRIC 2) PO = Police 3) PP
     *                 				= Passport 4) SO = Army 5) OC = Old
     *                 				Identity Card 6) OT = Others 7) PDC =
     *                 				Presumption of Death Case No. If
     *                 				[DeceaseInfoType1] = 1, set Default
     *                 				Value as IC
     */
    public void setDeceaseInfoIDType1(java.lang.String deceaseInfoIDType1) {
        this.deceaseInfoIDType1 = deceaseInfoIDType1;
    }


    /**
     * Gets the deceaseInfoIDType1No value for this DeceaseInfoType.
     * 
     * @return deceaseInfoIDType1No   * Decease Info ID Type 1 Number. If
     *                 				[DeceaseInfoType1] = IC, include dash
     *                 				- from the New IC No
     */
    public java.lang.String getDeceaseInfoIDType1No() {
        return deceaseInfoIDType1No;
    }


    /**
     * Sets the deceaseInfoIDType1No value for this DeceaseInfoType.
     * 
     * @param deceaseInfoIDType1No   * Decease Info ID Type 1 Number. If
     *                 				[DeceaseInfoType1] = IC, include dash
     *                 				- from the New IC No
     */
    public void setDeceaseInfoIDType1No(java.lang.String deceaseInfoIDType1No) {
        this.deceaseInfoIDType1No = deceaseInfoIDType1No;
    }


    /**
     * Gets the deceaseInfoIDType2 value for this DeceaseInfoType.
     * 
     * @return deceaseInfoIDType2   * Identity Type 2 of Decease Possible
     *                 				Value: 1) PO = Police 2) PP = Passport
     *                 				3) SO = Army 4) OC = Old Identity Card
     *                 				5) OT = Others 6) PDC = Presumption of
     *                 				Death Case No.
     */
    public java.lang.String getDeceaseInfoIDType2() {
        return deceaseInfoIDType2;
    }


    /**
     * Sets the deceaseInfoIDType2 value for this DeceaseInfoType.
     * 
     * @param deceaseInfoIDType2   * Identity Type 2 of Decease Possible
     *                 				Value: 1) PO = Police 2) PP = Passport
     *                 				3) SO = Army 4) OC = Old Identity Card
     *                 				5) OT = Others 6) PDC = Presumption of
     *                 				Death Case No.
     */
    public void setDeceaseInfoIDType2(java.lang.String deceaseInfoIDType2) {
        this.deceaseInfoIDType2 = deceaseInfoIDType2;
    }


    /**
     * Gets the deceaseInfoIDType2No value for this DeceaseInfoType.
     * 
     * @return deceaseInfoIDType2No   * Decease Info ID Type 2 Number
     */
    public java.lang.String getDeceaseInfoIDType2No() {
        return deceaseInfoIDType2No;
    }


    /**
     * Sets the deceaseInfoIDType2No value for this DeceaseInfoType.
     * 
     * @param deceaseInfoIDType2No   * Decease Info ID Type 2 Number
     */
    public void setDeceaseInfoIDType2No(java.lang.String deceaseInfoIDType2No) {
        this.deceaseInfoIDType2No = deceaseInfoIDType2No;
    }


    /**
     * Gets the deathCertNo value for this DeceaseInfoType.
     * 
     * @return deathCertNo   * Death Cert Number / Reference Number
     */
    public java.lang.String getDeathCertNo() {
        return deathCertNo;
    }


    /**
     * Sets the deathCertNo value for this DeceaseInfoType.
     * 
     * @param deathCertNo   * Death Cert Number / Reference Number
     */
    public void setDeathCertNo(java.lang.String deathCertNo) {
        this.deathCertNo = deathCertNo;
    }


    /**
     * Gets the deceaseInfoGender value for this DeceaseInfoType.
     * 
     * @return deceaseInfoGender   * Gender of Decease Possible Value: 1) F =
     *                 				Female 2) M = Male 3) U = Undefined
     */
    public java.lang.String getDeceaseInfoGender() {
        return deceaseInfoGender;
    }


    /**
     * Sets the deceaseInfoGender value for this DeceaseInfoType.
     * 
     * @param deceaseInfoGender   * Gender of Decease Possible Value: 1) F =
     *                 				Female 2) M = Male 3) U = Undefined
     */
    public void setDeceaseInfoGender(java.lang.String deceaseInfoGender) {
        this.deceaseInfoGender = deceaseInfoGender;
    }


    /**
     * Gets the deceaseInfoAge value for this DeceaseInfoType.
     * 
     * @return deceaseInfoAge   * Decease Info Age
     */
    public java.lang.String getDeceaseInfoAge() {
        return deceaseInfoAge;
    }


    /**
     * Sets the deceaseInfoAge value for this DeceaseInfoType.
     * 
     * @param deceaseInfoAge   * Decease Info Age
     */
    public void setDeceaseInfoAge(java.lang.String deceaseInfoAge) {
        this.deceaseInfoAge = deceaseInfoAge;
    }


    /**
     * Gets the dateOfDeath value for this DeceaseInfoType.
     * 
     * @return dateOfDeath   * Date Of death
     */
    public java.lang.String getDateOfDeath() {
        return dateOfDeath;
    }


    /**
     * Sets the dateOfDeath value for this DeceaseInfoType.
     * 
     * @param dateOfDeath   * Date Of death
     */
    public void setDateOfDeath(java.lang.String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }


    /**
     * Gets the deceaseInfoAddr1 value for this DeceaseInfoType.
     * 
     * @return deceaseInfoAddr1   * Address Line 1 of Decease Info
     */
    public java.lang.String getDeceaseInfoAddr1() {
        return deceaseInfoAddr1;
    }


    /**
     * Sets the deceaseInfoAddr1 value for this DeceaseInfoType.
     * 
     * @param deceaseInfoAddr1   * Address Line 1 of Decease Info
     */
    public void setDeceaseInfoAddr1(java.lang.String deceaseInfoAddr1) {
        this.deceaseInfoAddr1 = deceaseInfoAddr1;
    }


    /**
     * Gets the deceaseInfoAddr2 value for this DeceaseInfoType.
     * 
     * @return deceaseInfoAddr2   * Address Line 2 of Decease Info
     */
    public java.lang.String getDeceaseInfoAddr2() {
        return deceaseInfoAddr2;
    }


    /**
     * Sets the deceaseInfoAddr2 value for this DeceaseInfoType.
     * 
     * @param deceaseInfoAddr2   * Address Line 2 of Decease Info
     */
    public void setDeceaseInfoAddr2(java.lang.String deceaseInfoAddr2) {
        this.deceaseInfoAddr2 = deceaseInfoAddr2;
    }


    /**
     * Gets the deceaseInfoAddr3 value for this DeceaseInfoType.
     * 
     * @return deceaseInfoAddr3   * Address Line 3 of Decease Info
     */
    public java.lang.String getDeceaseInfoAddr3() {
        return deceaseInfoAddr3;
    }


    /**
     * Sets the deceaseInfoAddr3 value for this DeceaseInfoType.
     * 
     * @param deceaseInfoAddr3   * Address Line 3 of Decease Info
     */
    public void setDeceaseInfoAddr3(java.lang.String deceaseInfoAddr3) {
        this.deceaseInfoAddr3 = deceaseInfoAddr3;
    }


    /**
     * Gets the deceaseInfoPostcode value for this DeceaseInfoType.
     * 
     * @return deceaseInfoPostcode   * Address Postcode of Decease Info
     */
    public java.lang.String getDeceaseInfoPostcode() {
        return deceaseInfoPostcode;
    }


    /**
     * Sets the deceaseInfoPostcode value for this DeceaseInfoType.
     * 
     * @param deceaseInfoPostcode   * Address Postcode of Decease Info
     */
    public void setDeceaseInfoPostcode(java.lang.String deceaseInfoPostcode) {
        this.deceaseInfoPostcode = deceaseInfoPostcode;
    }


    /**
     * Gets the deceaseInfoCity value for this DeceaseInfoType.
     * 
     * @return deceaseInfoCity   * Address city of Decease Info
     */
    public java.lang.String getDeceaseInfoCity() {
        return deceaseInfoCity;
    }


    /**
     * Sets the deceaseInfoCity value for this DeceaseInfoType.
     * 
     * @param deceaseInfoCity   * Address city of Decease Info
     */
    public void setDeceaseInfoCity(java.lang.String deceaseInfoCity) {
        this.deceaseInfoCity = deceaseInfoCity;
    }


    /**
     * Gets the deceaseInfoState value for this DeceaseInfoType.
     * 
     * @return deceaseInfoState   * Address State of Decease Info Possible
     *                 				Value: Refer to appendix State Code
     */
    public java.lang.String getDeceaseInfoState() {
        return deceaseInfoState;
    }


    /**
     * Sets the deceaseInfoState value for this DeceaseInfoType.
     * 
     * @param deceaseInfoState   * Address State of Decease Info Possible
     *                 				Value: Refer to appendix State Code
     */
    public void setDeceaseInfoState(java.lang.String deceaseInfoState) {
        this.deceaseInfoState = deceaseInfoState;
    }


    /**
     * Gets the deceaseInfoCountry value for this DeceaseInfoType.
     * 
     * @return deceaseInfoCountry   * Address Country of Decease Info.
     * Possible Value: Refer to appendix Country Code
     */
    public java.lang.String getDeceaseInfoCountry() {
        return deceaseInfoCountry;
    }


    /**
     * Sets the deceaseInfoCountry value for this DeceaseInfoType.
     * 
     * @param deceaseInfoCountry   * Address Country of Decease Info.
     * Possible Value: Refer to appendix Country Code
     */
    public void setDeceaseInfoCountry(java.lang.String deceaseInfoCountry) {
        this.deceaseInfoCountry = deceaseInfoCountry;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeceaseInfoType)) return false;
        DeceaseInfoType other = (DeceaseInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.deceaseInfoName==null && other.getDeceaseInfoName()==null) || 
             (this.deceaseInfoName!=null &&
              this.deceaseInfoName.equals(other.getDeceaseInfoName()))) &&
            ((this.deceaseInfoType==null && other.getDeceaseInfoType()==null) || 
             (this.deceaseInfoType!=null &&
              this.deceaseInfoType.equals(other.getDeceaseInfoType()))) &&
            ((this.deceaseInfoIDType1==null && other.getDeceaseInfoIDType1()==null) || 
             (this.deceaseInfoIDType1!=null &&
              this.deceaseInfoIDType1.equals(other.getDeceaseInfoIDType1()))) &&
            ((this.deceaseInfoIDType1No==null && other.getDeceaseInfoIDType1No()==null) || 
             (this.deceaseInfoIDType1No!=null &&
              this.deceaseInfoIDType1No.equals(other.getDeceaseInfoIDType1No()))) &&
            ((this.deceaseInfoIDType2==null && other.getDeceaseInfoIDType2()==null) || 
             (this.deceaseInfoIDType2!=null &&
              this.deceaseInfoIDType2.equals(other.getDeceaseInfoIDType2()))) &&
            ((this.deceaseInfoIDType2No==null && other.getDeceaseInfoIDType2No()==null) || 
             (this.deceaseInfoIDType2No!=null &&
              this.deceaseInfoIDType2No.equals(other.getDeceaseInfoIDType2No()))) &&
            ((this.deathCertNo==null && other.getDeathCertNo()==null) || 
             (this.deathCertNo!=null &&
              this.deathCertNo.equals(other.getDeathCertNo()))) &&
            ((this.deceaseInfoGender==null && other.getDeceaseInfoGender()==null) || 
             (this.deceaseInfoGender!=null &&
              this.deceaseInfoGender.equals(other.getDeceaseInfoGender()))) &&
            ((this.deceaseInfoAge==null && other.getDeceaseInfoAge()==null) || 
             (this.deceaseInfoAge!=null &&
              this.deceaseInfoAge.equals(other.getDeceaseInfoAge()))) &&
            ((this.dateOfDeath==null && other.getDateOfDeath()==null) || 
             (this.dateOfDeath!=null &&
              this.dateOfDeath.equals(other.getDateOfDeath()))) &&
            ((this.deceaseInfoAddr1==null && other.getDeceaseInfoAddr1()==null) || 
             (this.deceaseInfoAddr1!=null &&
              this.deceaseInfoAddr1.equals(other.getDeceaseInfoAddr1()))) &&
            ((this.deceaseInfoAddr2==null && other.getDeceaseInfoAddr2()==null) || 
             (this.deceaseInfoAddr2!=null &&
              this.deceaseInfoAddr2.equals(other.getDeceaseInfoAddr2()))) &&
            ((this.deceaseInfoAddr3==null && other.getDeceaseInfoAddr3()==null) || 
             (this.deceaseInfoAddr3!=null &&
              this.deceaseInfoAddr3.equals(other.getDeceaseInfoAddr3()))) &&
            ((this.deceaseInfoPostcode==null && other.getDeceaseInfoPostcode()==null) || 
             (this.deceaseInfoPostcode!=null &&
              this.deceaseInfoPostcode.equals(other.getDeceaseInfoPostcode()))) &&
            ((this.deceaseInfoCity==null && other.getDeceaseInfoCity()==null) || 
             (this.deceaseInfoCity!=null &&
              this.deceaseInfoCity.equals(other.getDeceaseInfoCity()))) &&
            ((this.deceaseInfoState==null && other.getDeceaseInfoState()==null) || 
             (this.deceaseInfoState!=null &&
              this.deceaseInfoState.equals(other.getDeceaseInfoState()))) &&
            ((this.deceaseInfoCountry==null && other.getDeceaseInfoCountry()==null) || 
             (this.deceaseInfoCountry!=null &&
              this.deceaseInfoCountry.equals(other.getDeceaseInfoCountry())));
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
        if (getDeceaseInfoName() != null) {
            _hashCode += getDeceaseInfoName().hashCode();
        }
        if (getDeceaseInfoType() != null) {
            _hashCode += getDeceaseInfoType().hashCode();
        }
        if (getDeceaseInfoIDType1() != null) {
            _hashCode += getDeceaseInfoIDType1().hashCode();
        }
        if (getDeceaseInfoIDType1No() != null) {
            _hashCode += getDeceaseInfoIDType1No().hashCode();
        }
        if (getDeceaseInfoIDType2() != null) {
            _hashCode += getDeceaseInfoIDType2().hashCode();
        }
        if (getDeceaseInfoIDType2No() != null) {
            _hashCode += getDeceaseInfoIDType2No().hashCode();
        }
        if (getDeathCertNo() != null) {
            _hashCode += getDeathCertNo().hashCode();
        }
        if (getDeceaseInfoGender() != null) {
            _hashCode += getDeceaseInfoGender().hashCode();
        }
        if (getDeceaseInfoAge() != null) {
            _hashCode += getDeceaseInfoAge().hashCode();
        }
        if (getDateOfDeath() != null) {
            _hashCode += getDateOfDeath().hashCode();
        }
        if (getDeceaseInfoAddr1() != null) {
            _hashCode += getDeceaseInfoAddr1().hashCode();
        }
        if (getDeceaseInfoAddr2() != null) {
            _hashCode += getDeceaseInfoAddr2().hashCode();
        }
        if (getDeceaseInfoAddr3() != null) {
            _hashCode += getDeceaseInfoAddr3().hashCode();
        }
        if (getDeceaseInfoPostcode() != null) {
            _hashCode += getDeceaseInfoPostcode().hashCode();
        }
        if (getDeceaseInfoCity() != null) {
            _hashCode += getDeceaseInfoCity().hashCode();
        }
        if (getDeceaseInfoState() != null) {
            _hashCode += getDeceaseInfoState().hashCode();
        }
        if (getDeceaseInfoCountry() != null) {
            _hashCode += getDeceaseInfoCountry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeceaseInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "deceaseInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoIDType1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoIDType1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoIDType1No");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoIDType1No"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoIDType2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoIDType2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoIDType2No");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoIDType2No"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deathCertNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeathCertNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoGender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoGender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoAge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateOfDeath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DateOfDeath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoAddr1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoAddr1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoAddr2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoAddr2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoAddr3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoAddr3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoPostcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoPostcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deceaseInfoCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeceaseInfoCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
