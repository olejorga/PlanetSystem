package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A system of orbiting planets.
 */
public class PlanetSystem {
    // Instance variables
    private String name;
    private Star centerStar;
    private Planet[] planets;
    private String pictureUrl;

    /**
     * A system of orbiting planets.
     *
     * @param name Name of the system.
     * @param centerStar The systems center star.
     * @param planets Planets orbiting the system.
     */
    public PlanetSystem(String name, Star centerStar, Planet[] planets, String pictureUrl) {
        this.name = name;
        this.centerStar = centerStar;
        this.planets = planets;
        this.pictureUrl = pictureUrl;
    }

    public PlanetSystem() {
    }

    @Override
    public String toString() {
        return "PlanetSystem{" +
                "name='" + name + '\'' +
                ", centerStar=" + centerStar +
                ", planets=" + Arrays.toString(planets) +
                ", pictureUrl=" + pictureUrl +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Star getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public Planet[] getPlanets() {
        return planets;
    }

    public void setPlanets(Planet[] planets) {
        this.planets = planets;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * Get the largest planet in the system.
     * @return The largest Planet object.
     */
    @JsonIgnore
    public Planet getLargestPlanet() {
        // The initial largest planet is the first in the array.
        Planet largest = planets[0];

        // Loop through all planets.
        for (Planet planet : planets) {
            // If the current largest planet is as big as the
            // comparing planet, compare masses.
            if (planet.getRadius() == largest.getRadius()) {
                // And if the current largest planet is smaller
                // than the comparing planet in mass, then the comparing planet
                // is the new largest planet.
                if (planet.getMass() > largest.getMass()) {
                    largest = planet;
                }
            }
            // Or if the current largest planet is smaller
            // than the comparing planet in radius, then the comparing planet
            // is the new largest planet.
            else if (planet.getRadius() > largest.getRadius()) {
                largest = planet;
            }
        }

        // Return the last largest planet.
        return largest;
    }

    /**
     * Get the smallest planet in the system.
     * @return The smallest Planet object.
     */
    @JsonIgnore
    public Planet getSmallestPlanet() {
        // The initial smallest planet is the first in the array.
        Planet smallest = planets[0];

        // Loop through all planets.
        for (Planet planet : planets) {
            // If the current smallest planet is as big as the
            // comparing planet, compare masses.
            if (planet.getRadius() == smallest.getRadius()) {
                // And if the current smallest planet is larger
                // than the comparing planet in mass, then the comparing planet
                // is the new smallest planet.
                if (planet.getMass() < smallest.getMass()) {
                    smallest = planet;
                }
            }
            // Or if the current smallest planet is larger
            // than the comparing planet in radius, then the comparing planet
            // is the new smallest planet.
            else if (planet.getRadius() < smallest.getRadius()) {
                smallest = planet;
            }
        }

        // Return the last smallest planet.
        return smallest;
    }

    /**
     * Get a planet in the system by name.
     *
     * @param name Name of the desired planet.
     * @return The desired planet or null if not found.
     */
    @JsonIgnore
    public Planet getPlanetByName(String name) {
        // Loop through all planets
        for (Planet planet : planets){
            if (planet.getName().equals(name))
                return planet;
        }

        return null;
    }

    /**
     * Sort planets by mass.
     *
     * @return A sorted array of planets.
     */
    @JsonIgnore
    public Planet[] sortPlanetsByMass() {
        Planet[] planetsCopy = Arrays.copyOf(planets, planets.length);

        Arrays.sort(planetsCopy);

        return planetsCopy;
    }

    /**
     * Sort planets by radius.
     *
     * @return A sorted array of planets.
     */
    @JsonIgnore
    public Planet[] sortPlanetsByRadius() {
        Planet[] planetsCopy = Arrays.copyOf(planets, planets.length);

        Arrays.sort(planetsCopy, new Comparator<Planet>() {
            @Override
            public int compare(Planet p1, Planet p2) {
                double radius1 = p1.getRadius();
                double radius2 = p2.getRadius();

                if (radius1 == radius2)
                    return 0;
                else if (radius1 < radius2)
                    return 1;
                else
                    return -1;
            }
        });

        return planetsCopy;
    }

    /**
     * Sort planets by radius.
     *
     * @return A sorted array of planets.
     */
    @JsonIgnore
    public Planet[] sortPlanetsByName() {
        Planet[] planetsCopy = Arrays.copyOf(planets, planets.length);

        Arrays.sort(planetsCopy, new Comparator<Planet>() {
            @Override
            public int compare(Planet p1, Planet p2) {
                String name1 = p1.getName();
                String name2 = p2.getName();

                return name1.compareToIgnoreCase(name2);
            }
        });

        return planetsCopy;
    }
}
