package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class UniverseJSONRepository extends Thread implements UniverseRepository, UniverseFileRepository {
    private String filePath;
    private PlanetSystem[] planetSystems;
    private HashMap<String, PlanetSystem> planetSystemHashMap = new HashMap<>();

    public UniverseJSONRepository(String filePath) {
        this.filePath = filePath;
        readFile(filePath);
    }

    @Override
    public void run() {
        writeFile(filePath);
    }

    @Override
    public void readFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.planetSystems = objectMapper.readValue(new File(filePath), PlanetSystem[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PlanetSystem system : planetSystems) {
            planetSystemHashMap.put(system.getName(), system);
        }
    }

    @Override
    public void writeFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), planetSystemHashMap.values().toArray(new PlanetSystem[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileAsync() {
        start();
    }

    @Override
    public PlanetSystem[] getPlanetSystems() {
        return planetSystemHashMap.values().toArray(new PlanetSystem[0]);
    }

    @Override
    public void setPlanetSystems(PlanetSystem[] planetSystems) {
        this.planetSystems = planetSystems;

        planetSystemHashMap.clear();

        for (PlanetSystem system : planetSystems) {
            planetSystemHashMap.put(system.getName(), system);
        }
    }

    @Override
    public PlanetSystem getPlanetSystemByName(String name) {
        return planetSystemHashMap.get(name);
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
        PlanetSystem system = getPlanetSystemByName(planetSystemName);
        Planet[] planets = system.getPlanets();
        Planet[] planetsCopy = new Planet[planets.length-1];

        for (int i = 0, z = 0; i < planets.length; i++) {
            if (planets[i].getName().equals(planetName))
                continue;
            else
                planetsCopy[z++] = planets[i];
        }

        system.setPlanets(planetsCopy);

        planetSystemHashMap.put(planetSystemName, system);

        writeFileAsync();
    }

    @Override
    public void createPlanet(String planetSystemName, Planet planet) {
        PlanetSystem system = getPlanetSystemByName(planetSystemName);
        Planet[] planets = system.getPlanets();
        Planet[] planetsCopy = new Planet[planets.length+1];

        for (int i = 0; i < planetsCopy.length; i++) {
            if (i == planets.length)
                planetsCopy[i] = planet;
            else
                planetsCopy[i] = planets[i];
        }

        system.setPlanets(planetsCopy);

        planetSystemHashMap.put(planetSystemName, system);

        writeFileAsync();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "UniverseJSONRepository{" +
                "filePath='" + filePath + '\'' +
                ", planetSystems=" + Arrays.toString(planetSystems) +
                ", planetSystemHashMap=" + planetSystemHashMap +
                '}';
    }
}
