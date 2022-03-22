package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UniverseCSVRepository implements UniverseRepository, UniverseFileRepository {
    private String filePath;
    private PlanetSystem[] planetSystems;

    public UniverseCSVRepository(String filePath) {
        readFile(filePath);
    }

    @Override
    public void readFile(String filePath) {
        ArrayList<PlanetSystem> systems = new ArrayList<>();
        ArrayList<Planet> planets = new ArrayList<>();
        ArrayList<String[]> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String [] columns = line.split(";");

                lines.add(columns);

                planets.add(new Planet(
                        columns[7],
                        Double.parseDouble(columns[8]),
                        Double.parseDouble(columns[9]),
                        columns[13],
                        Double.parseDouble(columns[10]),
                        Double.parseDouble(columns[11]),
                        Double.parseDouble(columns[12]),
                        new Star(
                                columns[2],
                                Double.parseDouble(columns[3]),
                                Double.parseDouble(columns[4]),
                                columns[6],
                                Double.parseDouble(columns[5])
                        )
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            boolean uniqueEntry = false;

            if (i == (lines.size() - 1)) {
                uniqueEntry = true;
            } else {
                if (!lines.get(i)[0].equals(lines.get(i + 1)[0]))
                    uniqueEntry = true;
            }

            if (uniqueEntry) {
                String starName = lines.get(i)[2];
                Star centerStar = new Star();
                ArrayList<Planet> orbitPlanets = new ArrayList<>();

                for (Planet planet : planets) {
                    if (planet.getCentralCelestialBody().getName().equals(starName)) {
                        centerStar = (Star) planet.getCentralCelestialBody();
                        orbitPlanets.add(planet);
                    }
                }

                systems.add(new PlanetSystem(
                        lines.get(i)[0],
                        centerStar,
                        orbitPlanets.toArray(new Planet[0]),
                        lines.get(i)[1]
                ));
            }
        }

        planetSystems = systems.toArray(new PlanetSystem[0]);
    }

    @Override
    public void writeFile(String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            for (PlanetSystem system : planetSystems) {
                for (Planet planet : system.getPlanets()) {
                    bufferedWriter.write(
                            system.getName() + ";" +
                            system.getPictureUrl() + ";" +
                            system.getCenterStar().getName() + ";" +
                            system.getCenterStar().getRadius() + ";" +
                            system.getCenterStar().getMass() + ";" +
                            system.getCenterStar().getEffectiveTemp() + ";" +
                            system.getCenterStar().getPictureUrl() + ";" +
                            planet.getName() + ";" +
                            planet.getRadius() + ";" +
                            planet.getMass() + ";" +
                            planet.getSemiMajorAxis() + ";" +
                            planet.getEccentricity() + ";" +
                            planet.getOrbitalPeriod() + ";" +
                            planet.getPictureUrl()
                    );

                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlanetSystem[] getPlanetSystems() {
        return planetSystems;
    }

    @Override
    public void setPlanetSystems(PlanetSystem[] planetSystems) {
        this.planetSystems = planetSystems;
        writeFile(filePath);
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "UniverseCSVRepository{" +
                "filePath='" + filePath + '\'' +
                ", planetSystems=" + Arrays.toString(planetSystems) +
                '}';
    }
}
