package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * An object in space.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star")
})
public abstract class CelestialBody implements Comparable<CelestialBody> {
    // Instance variables
    protected String name;
    protected double radius;
    protected double mass;
    protected String pictureUrl;

    /**
     * An object in space.
     *
     * @param name Name of the object.
     * @param radius Radius of the object.
     * @param mass Mass of the object.
     * @param pictureUrl Url to an image of the object
     */
    public CelestialBody(String name, double radius, double mass, String pictureUrl) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.pictureUrl = pictureUrl;
    }

    public CelestialBody() {
    }

    @Override
    public String toString() {
        return "celestialBody{" +
                "name='" + name + '\'' +
                ", radius=" + radius +
                ", mass=" + mass +
                ", pictureUrl=" + pictureUrl +
                '}';
    }

    @Override
    public int compareTo(CelestialBody body) {
        double mass1 = this.getMass();
        double mass2 = body.getMass();

        if (mass1 == mass2)
            return 0;
        else if (mass1 < mass2)
            return 1;
        else
            return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @JsonIgnore
    public abstract double getMassInKg();

    @JsonIgnore
    public abstract double getRadiusInKm();
}
