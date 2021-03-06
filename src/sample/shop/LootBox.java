package sample.shop;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.inventory.cosmetics.CosmeticType;
import sample.inventory.cosmetics.UserData;

import java.util.ArrayList;
import java.util.Random;

public class LootBox {
    private String name;
    private int price;
    private String imageResource;
    private CosmeticType[] drops;

    public LootBox(String name, int price, String imageResource, CosmeticType[] drops) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.drops = drops;
    }

    public CosmeticType open() {
        ArrayList<CosmeticType> list = new ArrayList<>();
        for (CosmeticType cosmeticType : this.drops) {
            switch (cosmeticType.getQuality()) {
                case COMMON:
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    break;
                case UNCOMMON:
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    break;
                case RARE:
                    list.add(cosmeticType);
                    list.add(cosmeticType);
                    break;
                case MYTHICAL:
                    list.add(cosmeticType);
                    break;
            }
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageResource() {
        return imageResource;
    }

    public static LootBox[] getLootCrates() {
        ArrayList<LootBox> list = new ArrayList<>();
        JSONObject object = new JSONObject(UserData.readJson(CosmeticType.class.getResource("/cosmetics.json").toString().substring(6)));
        JSONArray boxes = object.getJSONArray("boxes");
        CosmeticType[] cosmeticTypes = CosmeticType.getCosmeticTypes();

        for (int i = 0; i < boxes.length(); i++) {
            JSONObject box = boxes.getJSONObject(i);

            JSONArray itemsArray = box.getJSONArray("items");
            CosmeticType[] items = new CosmeticType[itemsArray.length()];
            for (int j = 0; j < itemsArray.length(); j++) {
                String itemId = itemsArray.getString(j);
                for (CosmeticType cosmeticType : cosmeticTypes) {
                    if (itemId.equals(cosmeticType.getId())) {
                        items[j] = cosmeticType;
                    }
                }
            }

            list.add(new LootBox(box.getString("name"), box.getInt("price"), box.getString("graphic"), items));
        }

        return list.toArray(new LootBox[list.size()]);
    }
}
