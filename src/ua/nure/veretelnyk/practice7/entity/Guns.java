package ua.nure.veretelnyk.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "guns"
})
@XmlRootElement(name = "Guns")
public class Guns {

    @XmlElement(name = "Gun", required = true)
    protected List<Gun> guns;

    public List<Gun> getGuns() {
        if (guns == null) {
            guns = new ArrayList<>();
        }
        return this.guns;
    }

}
