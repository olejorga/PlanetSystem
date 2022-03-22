package model;

public interface UniverseRepository {
    PlanetSystem[] getPlanetSystems();
    void setPlanetSystems(PlanetSystem[] planetSystems);
    PlanetSystem getPlanetSystemByName(String name);
    Planet[] getPlanets(String planetSystemName);
    Planet getPlanetByName(String planetSystemName, String planetName);
    void deletePlanet(String planetSystemName, String planetName);
    void createPlanet(String planetSystemName, Planet planet);
}
