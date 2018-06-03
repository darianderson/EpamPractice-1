package ua.nure.veretelnyk.practice7.util;

import ua.nure.veretelnyk.practice7.entity.Gun;
import ua.nure.veretelnyk.practice7.entity.Guns;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {
    public static final Comparator<Gun> SORT_GUNS_BY_MODEL =
            Comparator.comparing(Gun::getModel);


    public static final Comparator<Gun> SORT_GUNS_BY_ORIGIN =
            Comparator.comparing(Gun::getOrigin);

    public static final Comparator<Gun> SORT_GUNS_BY_SIGHTING_RANGE =
            Comparator.comparingInt(o -> o.getTTC().getDistance().getDistance());

    public static final void sortGunsByModel(Guns guns){
        Collections.sort(guns.getGuns(), SORT_GUNS_BY_MODEL);
    }

    public static final void sortGunsByOrigin(Guns guns){
        Collections.sort(guns.getGuns(), SORT_GUNS_BY_ORIGIN);
    }

    public static final void sortGunsBySightingRange(Guns guns){
        Collections.sort(guns.getGuns(), SORT_GUNS_BY_SIGHTING_RANGE);
    }
}
