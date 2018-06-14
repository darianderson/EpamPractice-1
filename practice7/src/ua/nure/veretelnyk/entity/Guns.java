package ua.nure.veretelnyk.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
