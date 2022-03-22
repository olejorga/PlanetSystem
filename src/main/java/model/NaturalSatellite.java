package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A natural orbiting object.
 */
public abstract class NaturalSatellite extends CelestialBody {
    // Instance variables
    protected double semiMajorAxis;
    protected double eccentricity;
    protected double orbitalPeriod;
    protected CelestialBody centralCelestialBody;

    // Constants
    private static final long ONE_AU_IN_KM = 149_597_871;
    protected static final double GRAVITY = 0.00000000006674;

    /**
     * A natural orbiting object.
     *
     * @param name Name of the satellite.
     * @param radius Radius of the satellite.
     * @param mass Mass of the satellite.
     * @param semiMajorAxis Semi-major axis distance.
     * @param eccentricity Eccentricity ellipse shape.
     * @param orbitalPeriod Orbital period in earth days.
     * @param centralCelestialBody Central CelestialBody of the orbit.
     */
    public NaturalSatellite(
            String name, double radius, double mass,
            String pictureUrl, double semiMajorAxis,
            double eccentricity, double orbitalPeriod,
            CelestialBody centralCelestialBody
    ) {
        super(name, radius, mass, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = centralCelestialBody;
    }

    public NaturalSatellite() {
    }

    @Override
    public String toString() {
        return "NaturalSatellite{" +
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
     * Calc distance to central object at an angle.
     *
     * @param radians Angle in radians.
     * @return Distance in km.
     */
    @JsonIgnore
    private double distanceToCentralBody(double radians) {
        double a = semiMajorAxis * (1 - Math.pow(eccentricity, 2));
        double b = 1 + (eccentricity * Math.cos(radians));

        return (a/b) * ONE_AU_IN_KM;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }

    /**
     * Get distance to central object at an angle.
     *
     * @param radians Angle in radians.
     * @return Distance in km.
     */
    @JsonIgnore
    public double getDisToCentralBodyByRad(double radians) {
        return distanceToCentralBody(radians);
    }

    /**
     * Get distance to central object at an angle.
     *
     * @param degrees Angle in radians.
     * @return Distance in km.
     */
    @JsonIgnore
    public double getDisToCentralBodyByDeg(double degrees) {
        double radians = Math.toRadians(degrees);
        return distanceToCentralBody(radians);
    }

    /**
     * Get orbiting velocity of the satellite.
     *
     * @param distance Distance to central body in km.
     * @return Orbiting velocity in km/s
     */
    @JsonIgnore
    public double getOrbitingVelocity(double distance) {
        double v = Math.sqrt((GRAVITY * centralCelestialBody.getMassInKg()) / (distance * 1000));
        return v / 1000;
    }
}
