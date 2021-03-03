package sample.inventory;

import org.json.JSONObject;
import sample.inventory.cosmetics.CosmeticItem;

import java.io.*;
import java.util.List;

public class UserData {
    private String filePath;
    private File file;

    public UserData() {
        this.filePath = getClass().getResource("/userdata1.json").getPath();
    }

    private String readJson() {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
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
        JSONObject object = new JSONObject(this.readJson());
        return object.getInt("points");
    }

    private void setPoints(int points) {
        JSONObject object = new JSONObject(this.readJson());
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
        return null;
    }

    public boolean setItems(List<CosmeticItem> items) {
        return false;
    }
}
