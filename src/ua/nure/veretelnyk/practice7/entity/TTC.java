package ua.nure.veretelnyk.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTC", propOrder = {
    "distance",
    "sightingRange",
    "collar",
    "optics"
})
public class TTC {

    @XmlElement(name = "Distance", required = true)
    protected Distance distance;
    @XmlElement(name = "SightingRange", required = true)
    protected int sightingRange;
    @XmlElement(name = "Collar")
    protected boolean collar;
    @XmlElement(name = "Optics")
    protected boolean optics;


    public Distance getDistance() {
        return distance;
    }
    public void setDistance(Distance value) {
        this.distance = value;
    }


    public int getSightingRange() {
        return sightingRange;
    }
    public void setSightingRange(int value) {
        this.sightingRange = value;
    }


    public boolean isCollar() {
        return collar;
    }
    public void setCollar(boolean value) {
        this.collar = value;
    }

    public boolean isOptics() {
        return optics;
    }
    public void setOptics(boolean value) {
        this.optics = value;
    }

}
