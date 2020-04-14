
package integrasi.ws.gis;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spatialData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spatialData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="UPI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LAT" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="LONG" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spatialData", propOrder = {

})
public class SpatialData {

    @XmlElement(name = "UPI", required = true)
    protected String upi;
    @XmlElement(name = "LAT", required = true)
    protected BigDecimal lat;
    @XmlElement(name = "LONG", required = true)
    protected BigDecimal _long;

    /**
     * Gets the value of the upi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPI() {
        return upi;
    }

    /**
     * Sets the value of the upi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPI(String value) {
        this.upi = value;
    }

    /**
     * Gets the value of the lat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLAT() {
        return lat;
    }

    /**
     * Sets the value of the lat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLAT(BigDecimal value) {
        this.lat = value;
    }

    /**
     * Gets the value of the long property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLONG() {
        return _long;
    }

    /**
     * Sets the value of the long property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLONG(BigDecimal value) {
        this._long = value;
    }

}
