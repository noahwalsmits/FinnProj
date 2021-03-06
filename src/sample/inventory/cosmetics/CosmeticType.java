package sample.inventory.cosmetics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class CosmeticType {
    private String name;
    private String imageResource;
    private CosmeticQuality quality;
    private String id;

    public CosmeticType(String name, String imageResource, CosmeticQuality quality, String id) {
        this.name = name;
        this.imageResource = imageResource;
        this.quality = quality;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageResource() {
        return imageResource;
    }

    public CosmeticQuality getQuality() {
        return quality;
    }

    public String getId() {
        return id;
    }

    public static CosmeticType[] getCosmeticTypes() {
        ArrayList<CosmeticType> list = new ArrayList<>();
        JSONObject object = new JSONObject(UserData.readJson(CosmeticType.class.getResource("/cosmetics.json").toString().substring(6)));
        JSONArray cosmetics = object.getJSONArray("cosmetics");

        for (int i = 0; i < cosmetics.length(); i++) {
            JSONObject cosmetic = cosmetics.getJSONObject(i);
            CosmeticQuality quality = null;
            switch (cosmetic.getInt("quality")) {
                case 1:
                    quality = CosmeticQuality.COMMON;
                    break;
                case 2:
                    quality = CosmeticQuality.UNCOMMON;
                    break;
                case 3:
                    quality = CosmeticQuality.RARE;
                    break;
                case 4:
                    quality = CosmeticQuality.MYTHICAL;
                    break;
            }
            list.add(new CosmeticType(cosmetic.getString("name"), cosmetic.getString("graphic"), quality, cosmetic.getString("id")));
        }

        return new CosmeticType[]{
                new CosmeticType("Abstract", "images/cosmetics/abstract.png", CosmeticQuality.COMMON, "abstract"),
                new CosmeticType("Flies", "images/cosmetics/flies.png", CosmeticQuality.COMMON, "flies"),
                new CosmeticType("Frog", "images/cosmetics/frog.png", CosmeticQuality.COMMON, "frog"),
                new CosmeticType("Penguin", "images/cosmetics/penguin.png", CosmeticQuality.COMMON, "penguin"),
                new CosmeticType("Snake", "images/cosmetics/snake.png", CosmeticQuality.COMMON, "snake"),
                new CosmeticType("Jerma", "images/cosmetics/jerma.png", CosmeticQuality.COMMON, "jerma")
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmeticType that = (CosmeticType) o;
        return name.equals(that.name) && imageResource.equals(that.imageResource) && quality == that.quality && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageResource, quality, id);
    }
}
