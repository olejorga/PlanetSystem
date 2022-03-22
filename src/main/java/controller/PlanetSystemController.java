package controller;

import model.PlanetSystem;
import io.javalin.http.Context;
import model.UniverseRepository;

public class PlanetSystemController {
    private UniverseRepository universeData;

    public PlanetSystemController(UniverseRepository universeData) {
        this.universeData = universeData;
    }

    public void getPlanetSystems(Context ctx) {
        ctx.json(universeData.getPlanetSystems());
    }

    public void getPlanetSystem(Context ctx) {
        String planetSystemName = ctx.pathParam(":planet-system-id");

        PlanetSystem result = universeData.getPlanetSystemByName(planetSystemName);

        ctx.json(result);
    }
}
