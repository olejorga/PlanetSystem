package model;

import java.util.Arrays;

public class UniverseDataRepository implements UniverseRepository {
    private PlanetSystem[] planetSystems = new PlanetSystem[1];

    public UniverseDataRepository() {
        Star sun = new Star(
                "Sun",
                1,
                1,
                "http://bit.ly/3cVhuZc",
                5777
        );

        Planet[] ourPlanets = {
                new Planet(
                        "Mercury",
                        0.03412549655905556,
                        1.7297154899894627E-4,
                        "http://bit.ly/2TB2Heo",
                        0.387,
                        0.206,
                        88,
                        sun

                ), new Planet(
                "Venus",
                0.08465003077267387,
                0.002564278187565859,
                "http://bit.ly/2W3p4L9",
                0.723,
                0.007,
                225,
                sun

        ), new Planet(
                "Earth",
                0.08911486599899289,
                0.003146469968387777,
                "http://bit.ly/33bvXLZ",
                1,
                0.017,
                365,
                sun

        ), new Planet(
                "Mars",
                0.04741089912158004,
                3.3667017913593256E-4,
                "http://bit.ly/3aGyFvr",
                1.524,
                0.093,
                687,
                sun

        ), new Planet(
                "Jupiter",
                1,
                1,
                "http://bit.ly/2Q0fjK3",
                5.20440,
                0.049,
                4380,
                sun

        ), new Planet(
                "Saturn",
                0.8145247020645666,
                0.2994204425711275,
                "http://bit.ly/2W0sqic",
                9.5826,
                0.057,
                10585,
                sun

        ), new Planet(
                "Uranus",
                0.35475297935433336,
                0.04573761854583773,
                "http://bit.ly/335pbHy",
                19.2184,
                0.046,
                30660,
                sun

        ), new Planet(
                "Neptune",
                0.34440217087226543,
                0.05395152792413066,
                "http://bit.ly/38AyEba",
                30.11,
                0.010,
                60225,
                sun
        )
        };

        planetSystems[0] = new PlanetSystem(
                "Solar system",
                sun,
                ourPlanets,
                "http://bit.ly/333CTus"
        );
    }

    @Override
    public String toString() {
        return "UniverseDataRepository{" +
                "planetSystems=" + Arrays.toString(planetSystems) +
                '}';
    }

    @Override
    public PlanetSystem[] getPlanetSystems() {
        return planetSystems;
    }

    @Override
    public void setPlanetSystems(PlanetSystem[] planetSystems) {
        this.planetSystems = planetSystems;
    }

    @Override
    public PlanetSystem getPlanetSystemByName(String name) {
        for (PlanetSystem system : planetSystems) {
            if (system.getName().equals(name))
                return system;
        }

        return null;
    }

    @Override
    public Planet[] getPlanets(String planetSystemName) {
        return getPlanetSystemByName(planetSystemName).getPlanets();
    }

    @Override
    public Planet getPlanetByName(String planetSystemName, String planetName) {
        Planet[] planets = getPlanetSystemByName(planetSystemName).getPlanets();

        for (Planet planet : planets) {
            if (planet.getName().equals(planetName))
                return planet;
        }

        return null;
    }

    @Override
    public void deletePlanet(String planetSystemName, String planetName) {
        // TODO
    }

    @Override
    public void createPlanet(String planetSystemName, Planet planet) {
        // TODO
    }
}
