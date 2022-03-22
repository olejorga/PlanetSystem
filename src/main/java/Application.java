import controller.PlanetController;
import controller.PlanetSystemController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import model.UniverseJSONRepository;
import org.jetbrains.annotations.NotNull;

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        app.config.enableWebjars();

        app.get("/", ctx -> ctx.redirect("/planet-system"));

        app.get("/planet-system/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));

        app.get("/planet-system/:planet-system-id/planets/create", new VueComponent("planet-create"));

        app.get("/planet-system", new VueComponent("planet-system-overview"));

        app.get("/planet-system/:planet-system-id", new VueComponent("planet-system-detail"));

        app.get("/planet-system/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));

        //UniverseDataRepository universeData = new UniverseDataRepository();
        UniverseJSONRepository universeData = new UniverseJSONRepository("src/main/resources/data/planet_systems.json");
        PlanetSystemController planetSystemController = new PlanetSystemController(universeData);
        PlanetController planetController = new PlanetController(universeData);

        app.post("/api/planet-system/:planet-system-id/planets/create", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetController.createPlanet(ctx);
            }
        });

        app.post("/api/planet-system/:planet-system-id/planets/:planet-id/update", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetController.updatePlanet(ctx);
            }
        });

        app.get("/api/planet-system", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getPlanetSystems(ctx);
            }
        });

        app.get("/api/planet-system/:planet-system-id", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getPlanetSystem(ctx);
            }
        });

        app.get("/api/planet-system/:planet-system-id/planets", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetController.getPlanets(ctx);
            }
        });

        app.get("/api/planet-system/:planet-system-id/planets/:planet-id", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetController.getPlanet(ctx);
            }
        });

        app.get("/api/planet-system/:planet-system-id/planets/:planet-id/delete", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetController.deletePlanet(ctx);
            }
        });
    }
}
