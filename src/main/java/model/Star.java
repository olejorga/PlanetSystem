package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A star object in space.
 */
public class Star extends CelestialBody {
    // Instance variables
    private double effectiveTemp;

    // Constants
    private static final double ONE_MSUN_IN_KG = 1.98892E30;
    private static final long ONE_RSUN_IN_KM = 695_700;

    /**
     * A star object in space.
     *
     * @param name Name of the star.
     * @param radius Radius in solar-radius (Msun).
     * @param mass Mass in solar-mass (Rsun).
     * @param effectiveTemp Temperature in kelvin.
     */
    public Star(String name, double radius, double mass, String pictureUrl, double effectiveTemp) {
        super(name, radius, mass, pictureUrl);
        this.effectiveTemp = effectiveTemp;
    }

    public Star() {
    }

    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", radius=" + radius +
                ", mass=" + mass +
                ", effectiveTemp=" + effectiveTemp +
                ", pictureUrl=" + pictureUrl +
                '}';
    }

    /**
     * Get the radius of the star in km,
     * based on radius in Rsun and 1 Rsun in km.
     *
     * @return Radius in km.
     */
    @JsonIgnore
    public double getRadiusInKm() {
        return radius * ONE_RSUN_IN_KM;
    }

    /**
     * Get the mass of the star in kg,
     * based on mass in Msun and 1 Msun in kg.
     *
     * @return Mass in kg.
     */
    @Override
    @JsonIgnore
    public double getMassInKg() {
        return mass * ONE_MSUN_IN_KG;
    }

    public double getEffectiveTemp() {
        return effectiveTemp;
    }

    public void setEffectiveTemp(double effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}
