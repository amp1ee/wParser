import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import forJson.Deserializator;
import forJson.WthrContainer;
import forJson.Wthr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * export
 * Created by djamp on 28.06.2017.
 */
class JsonExporter {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting().create();

    //private List<Wthr> weather = new ArrayList<>();

    WthrContainer container = new WthrContainer();

    void save(List<Temperatures> tList, List<Icons> iList) {
        try (FileWriter writer = new FileWriter("D://weather.json")) {


            List<String> reg = Deserializator.deserealize("./regions.json");
            for (int i = 0; i< tList.size(); i++) {
                Wthr w = new Wthr();
                w.setCity(reg.get(i));

                Temperatures curTmp = tList.get(i);
                w.setDayTemp(curTmp.getDayT());
                w.setNightTemp(curTmp.getNightT());

                Icons curIco = iList.get(i);
                w.setDayIcon(curIco.getDayIcon());
                w.setNightIcon(curIco.getNightIcon());

                container.getWthr().add(w);
            }

            GSON.toJson(container, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    JsonExporter() {

    }
}