package controller;

import model.Planet;
import model.PlanetSystem;
import model.UniverseRepository;
import io.javalin.http.Context;

public class PlanetController {
    private UniverseRepository universeData;

    public PlanetController(UniverseRepository universeData) {
        this.universeData = universeData;
    }

    public void getPlanets(Context ctx) {
        String planetSystemName = ctx.pathParam(":planet-system-id");
        String sortKey = ctx.queryParam("sort_by");

        PlanetSystem planetSystem = universeData.getPlanetSystemByName(planetSystemName);

        planetSystem.getPlanets();
        // sortKey = "name"
        Planet[] planets = switch (sortKey) {
            case "mass" -> planetSystem.sortPlanetsByMass();
            case "radius" -> planetSystem.sortPlanetsByRadius();
            case "num" -> planetSystem.getPlanets();
            default -> planetSystem.sortPlanetsByName();
        };

        ctx.json(planets);
    }

    public void getPlanet(Context ctx) {
        String planetSystemName = ctx.pathParam(":planet-system-id");
        String planetName = ctx.pathParam(":planet-id");

        Planet result = universeData.getPlanetByName(planetSystemName, planetName);

        ctx.json(result);
    }

    public void deletePlanet(Context ctx) {
        String planetSystemName = ctx.pathParam(":planet-system-id");
        String planetName = ctx.pathParam(":planet-id");

        universeData.deletePlanet(planetSystemName, planetName);

        ctx.redirect("/planet-system/"+planetSystemName);
    }

    public void createPlanet(Context ctx) {
        String planetSystemName = ctx.pathParam(":planet-system-id");
        PlanetSystem planetSystem = universeData.getPlanetSystemByName(planetSystemName);

        Planet planet = new Planet(
                ctx.formParam("name"),
                Double.parseDouble(ctx.formParam("radius")),
                Double.parseDouble(ctx.formParam("mass")),
                ctx.formParam("pictureUrl"),
                Double.parseDouble(ctx.formParam("semiMajorAxis")),
                Double.parseDouble(ctx.formParam("eccentricity")),
                Double.parseDouble(ctx.formParam("orbitalPeriod")),
                planetSystem.getCenterStar()
        );

        universeData.createPlanet(planetSystemName, planet);

        ctx.redirect("/planet-system/"+planetSystemName);
    }

    public void updatePlanet(Context ctx) {
        deletePlanet(ctx);
        createPlanet(ctx);
    }
}
