package sample.inventory.cosmetics;

import org.json.JSONObject;
import sample.inventory.cosmetics.CosmeticItem;
import sample.inventory.cosmetics.CosmeticType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String filePath;

    public UserData() {
        this.filePath = "userdata/userdata.json";
    }

    public static String readJson(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public int getPoints() {
        JSONObject object = new JSONObject(readJson(this.filePath));
        return object.getInt("points");
    }

    private void setPoints(int points) {
        JSONObject object = new JSONObject(readJson(this.filePath));
        object.put("points", points);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            writer.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean changePoints(int change) {
        if (-change > getPoints()) {
            return false;
        }
        setPoints(getPoints() + change);
        return true;
    }

    public List<CosmeticItem> getItems() {
        JSONObject object = new JSONObject(readJson(this.filePath));
        JSONObject items = object.getJSONObject("items");

        List<CosmeticItem> itemList = new ArrayList<>();
        CosmeticType[] cosmeticTypes = CosmeticType.getCosmeticTypes();
        for (String key : items.keySet()) {
            for (CosmeticType cosmeticType : cosmeticTypes) {
                if (key.equals(cosmeticType.getId())) {
                    itemList.add(new CosmeticItem(cosmeticType, items.getInt(key)));
                }
            }
        }
        return itemList;
    }

    public void setItems(List<CosmeticItem> items) {
        JSONObject object = new JSONObject(readJson(this.filePath));
        JSONObject jsonItems = object.getJSONObject("items");

        for (CosmeticItem cosmeticItem : items) {
            jsonItems.put(cosmeticItem.getType().getId(), cosmeticItem.getQuantity());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            writer.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
