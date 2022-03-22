package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A planet object in space.
 */
public class Planet extends NaturalSatellite {
    // Constants
    private static final double ONE_MJUP_IN_KG = 1.898E27;
    private static final long ONE_RJUP_IN_KM = 71_492;
    private static final double ONE_MEARTH_IN_KG = 5.972E24;
    private static final long ONE_REARTH_IN_KM = 6_371;

    /**
     * A planet object in space.
     *
     * @param name Name of the planet.
     * @param radius Radius in jupiter-radius (Rjup).
     * @param mass Mass in jupiter-mass (Mjup).
     * @param semiMajorAxis Semi-major axis distance.
     * @param eccentricity Eccentricity ellipse shape.
     * @param orbitalPeriod Orbital period in earth days.
     * @param centralCelestialBody Central CelestialBody of the orbit.
     */
    public Planet(
            String name, double radius, double mass,
            String pictureUrl, double semiMajorAxis,
            double eccentricity, double orbitalPeriod,
            CelestialBody centralCelestialBody
    ) {
        super(name, radius, mass, pictureUrl, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    public Planet() {
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", radius=" + radius +
                ", mass=" + mass +
                ", semiMajorAxis=" + semiMajorAxis +
                ", eccentricity=" + eccentricity +
                ", orbitalPeriod=" + orbitalPeriod +
                ", centralCelestialBody=" + centralCelestialBody +
                ", pictureUrl=" + pictureUrl +
                '}';
    }

    /**
     * Get the radius of the planet in km,
     * based on radius in Rjup and 1 Rjup in km.
     *
     * @return Radius in km.
     */
    @Override
    @JsonIgnore
    public double getRadiusInKm() {
        return radius * ONE_RJUP_IN_KM;
    }

    /**
     * Get the radius of the planet in Rearth,
     * based on getRadiusInKm() and 1 Rearth in km.
     *
     * @return Radius in Rearth.
     */
    @JsonIgnore
    public double getRadiusInRearth() {
        return getRadiusInKm() / ONE_REARTH_IN_KM;
    }

    /**
     * Get the mass of the planet in kg,
     * based on mass in Mjup and 1 Mjup in kg.
     *
     * @return Mass in kg.
     */
    @Override
    @JsonIgnore
    public double getMassInKg() {
        return mass * ONE_MJUP_IN_KG;
    }

    /**
     * Get the mass of the planet in Mearth,
     * based on getMassInKg() and 1 Mearth in kg.
     *
     * @return Mass in Mearth.
     */
    @JsonIgnore
    public double getMassInMearth() {
        return getMassInKg() / ONE_MEARTH_IN_KG;
    }

    /**
     * Get the surface gravity of the planet in m/s^2,
     * based on the radius in meters, radius in kg and
     * the gravity constant.
     *
     * @return Gravity in m/s^2.
     */
    @JsonIgnore
    public double getSurfaceGravity() {
        double radiusInMeters = getRadiusInKm() * 1000;
        return (GRAVITY * getMassInKg()) / Math.pow(radiusInMeters, 2);
    }
}
