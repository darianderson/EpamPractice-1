package ua.nure.veretelnyk.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gun", propOrder = {
    "model",
    "handy",
    "origin",
    "ttc",
    "material"
})
public class Gun {

    @XmlElement(name = "Model", required = true)
    protected String model;
    @XmlElement(name = "Handy", required = true)
    @XmlSchemaType(name = "string")
    protected Handy handy;
    @XmlElement(name = "Origin", required = true)
    protected String origin;
    @XmlElement(name = "TTC", required = true)
    protected TTC ttc;
    @XmlElement(name = "Material", required = true)
    protected String material;


    public String getModel() {
        return model;
    }
    public void setModel(String value) {
        this.model = value;
    }


    public Handy getHandy() {
        return handy;
    }
    public void setHandy(Handy value) {
        this.handy = value;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String value) {
        this.origin = value;
    }


    public TTC getTTC() {
        return ttc;
    }
    public void setTTC(TTC value) {
        this.ttc = value;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String value) {
        this.material = value;
    }

}
