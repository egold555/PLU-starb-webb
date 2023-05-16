package webb.shared.models.title;

public enum TITLES {
    SPACE_CADET("Space Cadet"),
    STARLIGHT_SENTINEL("Starlight Sentinel"),
    STAR_FIGHTER("Starfighter"),
    STAR_COMMANDER("Star Commander"),
    SOLAR_PROTECTOR("Solar Protector"),
    STAR_LORD("Star Lord"),
    GALACTIC_HERO("Galactic Hero"),
    COSMIC_CHAMPION("Cosmic Champion");

    private final String title;

    TITLES(String s) {
        title = s;
   }

    public boolean equals(String otherTitle) {
        return title.equals(otherTitle);
    }

    public String toString() {
        return this.title;
    }
}
