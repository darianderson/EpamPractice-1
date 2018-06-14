package ua.nure.veretelnyk.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Handy")
@XmlEnum
public enum Handy {

    @XmlEnumValue("one-hand")
    ONE_HAND("one-hand"),
    @XmlEnumValue("two-hand")
    TWO_HAND("two-hand");
    private final String value;

    Handy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Handy fromValue(String v) {
        for (Handy c: Handy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
