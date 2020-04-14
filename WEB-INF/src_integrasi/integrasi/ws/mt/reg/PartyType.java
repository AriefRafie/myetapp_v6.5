/**
 * PartyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package integrasi.ws.mt.reg;

public class PartyType  implements java.io.Serializable {
    /* Unique identifier for this Party. This will be the key for
     * subsequent updates. */
    private java.lang.String partyID;

    /* Party Type ID as per Court Value
     * Possible Value: Refer Appendix Party Type by Case Code */
    private java.lang.String partyTypeID;

    /* Name of Party */
    private java.lang.String partyName;

    /* Identity Type of Party
     * Possible Value:
     * 1)	IC = NRIC
     * 2)	PO = Police
     * 3)	PP = Passport
     * 4)	SO = Army
     * 5)	GA = Government Agency
     * 6)	OC = Old Identity Card
     * 7)	OT = Others */
    private java.lang.String partyIDType;

    /* Identity No of Party. If PartyIDType =
     *         						IC, remove the dash from the IC. */
    private java.lang.String partyIDNo1;

    /* Identity No of Party If PartyIDType !=
     *         						IC */
    private java.lang.String partyIDNo2;

    /* Address Line 1 of Party */
    private java.lang.String partyAddr1;

    /* Address Line 2 of Party */
    private java.lang.String partyAddr2;

    /* Address Line 3 of Party */
    private java.lang.String partyAddr3;

    /* Address Postcode of Party */
    private java.lang.String partyPostcode;

    /* Address city of Party */
    private java.lang.String partyCity;

    /* Address State of Party. Possible Value:
     *         						Refer to appendix State Code */
    private java.lang.String partyState;

    /* Address Country of Party. Possible
     *         						Value: Refer to appendix Country Code */
    private java.lang.String partyCountry;

    /* Age of Party */
    private java.lang.String partyAge;

    /* Race of Party Possible Value: Refer to
     *         						appendix State Code */
    private java.lang.String partyRace;

    /* Nationality of Party Possible Value:
     *         						Refer to appendix Country Code */
    private java.lang.String partyNationality;

    /* Gender of Party Possible Value: 1) F =
     *         						Female 2) M = Male 3) U = Undefined */
    private java.lang.String partyGender;

    /* Party Relationship with Decease
     * Required if Case Code = 31NCvC */
    private java.lang.String partyRelationship;

    public PartyType() {
    }

    public PartyType(
           java.lang.String partyID,
           java.lang.String partyTypeID,
           java.lang.String partyName,
           java.lang.String partyIDType,
           java.lang.String partyIDNo1,
           java.lang.String partyIDNo2,
           java.lang.String partyAddr1,
           java.lang.String partyAddr2,
           java.lang.String partyAddr3,
           java.lang.String partyPostcode,
           java.lang.String partyCity,
           java.lang.String partyState,
           java.lang.String partyCountry,
           java.lang.String partyAge,
           java.lang.String partyRace,
           java.lang.String partyNationality,
           java.lang.String partyGender,
           java.lang.String partyRelationship) {
           this.partyID = partyID;
           this.partyTypeID = partyTypeID;
           this.partyName = partyName;
           this.partyIDType = partyIDType;
           this.partyIDNo1 = partyIDNo1;
           this.partyIDNo2 = partyIDNo2;
           this.partyAddr1 = partyAddr1;
           this.partyAddr2 = partyAddr2;
           this.partyAddr3 = partyAddr3;
           this.partyPostcode = partyPostcode;
           this.partyCity = partyCity;
           this.partyState = partyState;
           this.partyCountry = partyCountry;
           this.partyAge = partyAge;
           this.partyRace = partyRace;
           this.partyNationality = partyNationality;
           this.partyGender = partyGender;
           this.partyRelationship = partyRelationship;
    }


    /**
     * Gets the partyID value for this PartyType.
     * 
     * @return partyID   * Unique identifier for this Party. This will be the key for
     * subsequent updates.
     */
    public java.lang.String getPartyID() {
        return partyID;
    }


    /**
     * Sets the partyID value for this PartyType.
     * 
     * @param partyID   * Unique identifier for this Party. This will be the key for
     * subsequent updates.
     */
    public void setPartyID(java.lang.String partyID) {
        this.partyID = partyID;
    }


    /**
     * Gets the partyTypeID value for this PartyType.
     * 
     * @return partyTypeID   * Party Type ID as per Court Value
     * Possible Value: Refer Appendix Party Type by Case Code
     */
    public java.lang.String getPartyTypeID() {
        return partyTypeID;
    }


    /**
     * Sets the partyTypeID value for this PartyType.
     * 
     * @param partyTypeID   * Party Type ID as per Court Value
     * Possible Value: Refer Appendix Party Type by Case Code
     */
    public void setPartyTypeID(java.lang.String partyTypeID) {
        this.partyTypeID = partyTypeID;
    }


    /**
     * Gets the partyName value for this PartyType.
     * 
     * @return partyName   * Name of Party
     */
    public java.lang.String getPartyName() {
        return partyName;
    }


    /**
     * Sets the partyName value for this PartyType.
     * 
     * @param partyName   * Name of Party
     */
    public void setPartyName(java.lang.String partyName) {
        this.partyName = partyName;
    }


    /**
     * Gets the partyIDType value for this PartyType.
     * 
     * @return partyIDType   * Identity Type of Party
     * Possible Value:
     * 1)	IC = NRIC
     * 2)	PO = Police
     * 3)	PP = Passport
     * 4)	SO = Army
     * 5)	GA = Government Agency
     * 6)	OC = Old Identity Card
     * 7)	OT = Others
     */
    public java.lang.String getPartyIDType() {
        return partyIDType;
    }


    /**
     * Sets the partyIDType value for this PartyType.
     * 
     * @param partyIDType   * Identity Type of Party
     * Possible Value:
     * 1)	IC = NRIC
     * 2)	PO = Police
     * 3)	PP = Passport
     * 4)	SO = Army
     * 5)	GA = Government Agency
     * 6)	OC = Old Identity Card
     * 7)	OT = Others
     */
    public void setPartyIDType(java.lang.String partyIDType) {
        this.partyIDType = partyIDType;
    }


    /**
     * Gets the partyIDNo1 value for this PartyType.
     * 
     * @return partyIDNo1   * Identity No of Party. If PartyIDType =
     *         						IC, remove the dash from the IC.
     */
    public java.lang.String getPartyIDNo1() {
        return partyIDNo1;
    }


    /**
     * Sets the partyIDNo1 value for this PartyType.
     * 
     * @param partyIDNo1   * Identity No of Party. If PartyIDType =
     *         						IC, remove the dash from the IC.
     */
    public void setPartyIDNo1(java.lang.String partyIDNo1) {
        this.partyIDNo1 = partyIDNo1;
    }


    /**
     * Gets the partyIDNo2 value for this PartyType.
     * 
     * @return partyIDNo2   * Identity No of Party If PartyIDType !=
     *         						IC
     */
    public java.lang.String getPartyIDNo2() {
        return partyIDNo2;
    }


    /**
     * Sets the partyIDNo2 value for this PartyType.
     * 
     * @param partyIDNo2   * Identity No of Party If PartyIDType !=
     *         						IC
     */
    public void setPartyIDNo2(java.lang.String partyIDNo2) {
        this.partyIDNo2 = partyIDNo2;
    }


    /**
     * Gets the partyAddr1 value for this PartyType.
     * 
     * @return partyAddr1   * Address Line 1 of Party
     */
    public java.lang.String getPartyAddr1() {
        return partyAddr1;
    }


    /**
     * Sets the partyAddr1 value for this PartyType.
     * 
     * @param partyAddr1   * Address Line 1 of Party
     */
    public void setPartyAddr1(java.lang.String partyAddr1) {
        this.partyAddr1 = partyAddr1;
    }


    /**
     * Gets the partyAddr2 value for this PartyType.
     * 
     * @return partyAddr2   * Address Line 2 of Party
     */
    public java.lang.String getPartyAddr2() {
        return partyAddr2;
    }


    /**
     * Sets the partyAddr2 value for this PartyType.
     * 
     * @param partyAddr2   * Address Line 2 of Party
     */
    public void setPartyAddr2(java.lang.String partyAddr2) {
        this.partyAddr2 = partyAddr2;
    }


    /**
     * Gets the partyAddr3 value for this PartyType.
     * 
     * @return partyAddr3   * Address Line 3 of Party
     */
    public java.lang.String getPartyAddr3() {
        return partyAddr3;
    }


    /**
     * Sets the partyAddr3 value for this PartyType.
     * 
     * @param partyAddr3   * Address Line 3 of Party
     */
    public void setPartyAddr3(java.lang.String partyAddr3) {
        this.partyAddr3 = partyAddr3;
    }


    /**
     * Gets the partyPostcode value for this PartyType.
     * 
     * @return partyPostcode   * Address Postcode of Party
     */
    public java.lang.String getPartyPostcode() {
        return partyPostcode;
    }


    /**
     * Sets the partyPostcode value for this PartyType.
     * 
     * @param partyPostcode   * Address Postcode of Party
     */
    public void setPartyPostcode(java.lang.String partyPostcode) {
        this.partyPostcode = partyPostcode;
    }


    /**
     * Gets the partyCity value for this PartyType.
     * 
     * @return partyCity   * Address city of Party
     */
    public java.lang.String getPartyCity() {
        return partyCity;
    }


    /**
     * Sets the partyCity value for this PartyType.
     * 
     * @param partyCity   * Address city of Party
     */
    public void setPartyCity(java.lang.String partyCity) {
        this.partyCity = partyCity;
    }


    /**
     * Gets the partyState value for this PartyType.
     * 
     * @return partyState   * Address State of Party. Possible Value:
     *         						Refer to appendix State Code
     */
    public java.lang.String getPartyState() {
        return partyState;
    }


    /**
     * Sets the partyState value for this PartyType.
     * 
     * @param partyState   * Address State of Party. Possible Value:
     *         						Refer to appendix State Code
     */
    public void setPartyState(java.lang.String partyState) {
        this.partyState = partyState;
    }


    /**
     * Gets the partyCountry value for this PartyType.
     * 
     * @return partyCountry   * Address Country of Party. Possible
     *         						Value: Refer to appendix Country Code
     */
    public java.lang.String getPartyCountry() {
        return partyCountry;
    }


    /**
     * Sets the partyCountry value for this PartyType.
     * 
     * @param partyCountry   * Address Country of Party. Possible
     *         						Value: Refer to appendix Country Code
     */
    public void setPartyCountry(java.lang.String partyCountry) {
        this.partyCountry = partyCountry;
    }


    /**
     * Gets the partyAge value for this PartyType.
     * 
     * @return partyAge   * Age of Party
     */
    public java.lang.String getPartyAge() {
        return partyAge;
    }


    /**
     * Sets the partyAge value for this PartyType.
     * 
     * @param partyAge   * Age of Party
     */
    public void setPartyAge(java.lang.String partyAge) {
        this.partyAge = partyAge;
    }


    /**
     * Gets the partyRace value for this PartyType.
     * 
     * @return partyRace   * Race of Party Possible Value: Refer to
     *         						appendix State Code
     */
    public java.lang.String getPartyRace() {
        return partyRace;
    }


    /**
     * Sets the partyRace value for this PartyType.
     * 
     * @param partyRace   * Race of Party Possible Value: Refer to
     *         						appendix State Code
     */
    public void setPartyRace(java.lang.String partyRace) {
        this.partyRace = partyRace;
    }


    /**
     * Gets the partyNationality value for this PartyType.
     * 
     * @return partyNationality   * Nationality of Party Possible Value:
     *         						Refer to appendix Country Code
     */
    public java.lang.String getPartyNationality() {
        return partyNationality;
    }


    /**
     * Sets the partyNationality value for this PartyType.
     * 
     * @param partyNationality   * Nationality of Party Possible Value:
     *         						Refer to appendix Country Code
     */
    public void setPartyNationality(java.lang.String partyNationality) {
        this.partyNationality = partyNationality;
    }


    /**
     * Gets the partyGender value for this PartyType.
     * 
     * @return partyGender   * Gender of Party Possible Value: 1) F =
     *         						Female 2) M = Male 3) U = Undefined
     */
    public java.lang.String getPartyGender() {
        return partyGender;
    }


    /**
     * Sets the partyGender value for this PartyType.
     * 
     * @param partyGender   * Gender of Party Possible Value: 1) F =
     *         						Female 2) M = Male 3) U = Undefined
     */
    public void setPartyGender(java.lang.String partyGender) {
        this.partyGender = partyGender;
    }


    /**
     * Gets the partyRelationship value for this PartyType.
     * 
     * @return partyRelationship   * Party Relationship with Decease
     * Required if Case Code = 31NCvC
     */
    public java.lang.String getPartyRelationship() {
        return partyRelationship;
    }


    /**
     * Sets the partyRelationship value for this PartyType.
     * 
     * @param partyRelationship   * Party Relationship with Decease
     * Required if Case Code = 31NCvC
     */
    public void setPartyRelationship(java.lang.String partyRelationship) {
        this.partyRelationship = partyRelationship;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PartyType)) return false;
        PartyType other = (PartyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partyID==null && other.getPartyID()==null) || 
             (this.partyID!=null &&
              this.partyID.equals(other.getPartyID()))) &&
            ((this.partyTypeID==null && other.getPartyTypeID()==null) || 
             (this.partyTypeID!=null &&
              this.partyTypeID.equals(other.getPartyTypeID()))) &&
            ((this.partyName==null && other.getPartyName()==null) || 
             (this.partyName!=null &&
              this.partyName.equals(other.getPartyName()))) &&
            ((this.partyIDType==null && other.getPartyIDType()==null) || 
             (this.partyIDType!=null &&
              this.partyIDType.equals(other.getPartyIDType()))) &&
            ((this.partyIDNo1==null && other.getPartyIDNo1()==null) || 
             (this.partyIDNo1!=null &&
              this.partyIDNo1.equals(other.getPartyIDNo1()))) &&
            ((this.partyIDNo2==null && other.getPartyIDNo2()==null) || 
             (this.partyIDNo2!=null &&
              this.partyIDNo2.equals(other.getPartyIDNo2()))) &&
            ((this.partyAddr1==null && other.getPartyAddr1()==null) || 
             (this.partyAddr1!=null &&
              this.partyAddr1.equals(other.getPartyAddr1()))) &&
            ((this.partyAddr2==null && other.getPartyAddr2()==null) || 
             (this.partyAddr2!=null &&
              this.partyAddr2.equals(other.getPartyAddr2()))) &&
            ((this.partyAddr3==null && other.getPartyAddr3()==null) || 
             (this.partyAddr3!=null &&
              this.partyAddr3.equals(other.getPartyAddr3()))) &&
            ((this.partyPostcode==null && other.getPartyPostcode()==null) || 
             (this.partyPostcode!=null &&
              this.partyPostcode.equals(other.getPartyPostcode()))) &&
            ((this.partyCity==null && other.getPartyCity()==null) || 
             (this.partyCity!=null &&
              this.partyCity.equals(other.getPartyCity()))) &&
            ((this.partyState==null && other.getPartyState()==null) || 
             (this.partyState!=null &&
              this.partyState.equals(other.getPartyState()))) &&
            ((this.partyCountry==null && other.getPartyCountry()==null) || 
             (this.partyCountry!=null &&
              this.partyCountry.equals(other.getPartyCountry()))) &&
            ((this.partyAge==null && other.getPartyAge()==null) || 
             (this.partyAge!=null &&
              this.partyAge.equals(other.getPartyAge()))) &&
            ((this.partyRace==null && other.getPartyRace()==null) || 
             (this.partyRace!=null &&
              this.partyRace.equals(other.getPartyRace()))) &&
            ((this.partyNationality==null && other.getPartyNationality()==null) || 
             (this.partyNationality!=null &&
              this.partyNationality.equals(other.getPartyNationality()))) &&
            ((this.partyGender==null && other.getPartyGender()==null) || 
             (this.partyGender!=null &&
              this.partyGender.equals(other.getPartyGender()))) &&
            ((this.partyRelationship==null && other.getPartyRelationship()==null) || 
             (this.partyRelationship!=null &&
              this.partyRelationship.equals(other.getPartyRelationship())));
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
        if (getPartyID() != null) {
            _hashCode += getPartyID().hashCode();
        }
        if (getPartyTypeID() != null) {
            _hashCode += getPartyTypeID().hashCode();
        }
        if (getPartyName() != null) {
            _hashCode += getPartyName().hashCode();
        }
        if (getPartyIDType() != null) {
            _hashCode += getPartyIDType().hashCode();
        }
        if (getPartyIDNo1() != null) {
            _hashCode += getPartyIDNo1().hashCode();
        }
        if (getPartyIDNo2() != null) {
            _hashCode += getPartyIDNo2().hashCode();
        }
        if (getPartyAddr1() != null) {
            _hashCode += getPartyAddr1().hashCode();
        }
        if (getPartyAddr2() != null) {
            _hashCode += getPartyAddr2().hashCode();
        }
        if (getPartyAddr3() != null) {
            _hashCode += getPartyAddr3().hashCode();
        }
        if (getPartyPostcode() != null) {
            _hashCode += getPartyPostcode().hashCode();
        }
        if (getPartyCity() != null) {
            _hashCode += getPartyCity().hashCode();
        }
        if (getPartyState() != null) {
            _hashCode += getPartyState().hashCode();
        }
        if (getPartyCountry() != null) {
            _hashCode += getPartyCountry().hashCode();
        }
        if (getPartyAge() != null) {
            _hashCode += getPartyAge().hashCode();
        }
        if (getPartyRace() != null) {
            _hashCode += getPartyRace().hashCode();
        }
        if (getPartyNationality() != null) {
            _hashCode += getPartyNationality().hashCode();
        }
        if (getPartyGender() != null) {
            _hashCode += getPartyGender().hashCode();
        }
        if (getPartyRelationship() != null) {
            _hashCode += getPartyRelationship().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PartyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://eip.kehakiman.gov.my/services/", "PartyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyTypeID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyTypeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyIDType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyIDType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyIDNo1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyIDNo1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyIDNo2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyIDNo2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAddr1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAddr1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAddr2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAddr2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAddr3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAddr3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyPostcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyPostcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyAge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyRace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyRace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyNationality");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyNationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyGender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyGender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partyRelationship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartyRelationship"));
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
