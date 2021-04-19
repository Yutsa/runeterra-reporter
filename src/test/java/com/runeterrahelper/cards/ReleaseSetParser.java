package com.runeterrahelper.cards;

public class ReleaseSetParser {
    public static CardReleaseSet parse(String releaseSet) {
        switch (releaseSet) {
            case "Foundation":
                return CardReleaseSet.FOUNDATION;
            case "Rising Tides":
                return CardReleaseSet.RISING_TIDES;
            case "Call of the Mountain":
                return CardReleaseSet.CALL_OF_THE_MOUNTAIN;
            case "Empire of the Ascended":
                return CardReleaseSet.EMPIRE_OF_THE_ASCENDED;
        }
        return CardReleaseSet.FOUNDATION;
    }
}
