package sample.inventory;

import sample.inventory.cosmetics.CosmeticItem;

import java.io.*;
import java.util.List;

public class UserData {
    private File file;

    public UserData() {
        this.file = new File(getClass().getResource("/userdata.txt").getPath());
        System.out.println(file.getAbsolutePath());
    }

    public int getPoints() {
        int points = -1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
            String line = reader.readLine();
            do {
                if (line.contains("points")) {
                    line = line.substring(line.indexOf(' ') + 1);
                    System.out.println(line);
                    return Integer.parseInt(line);
                }
                line = reader.readLine();
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private void setPoints(int points) {
    }

    public boolean addPoints(int added) {
        return false;
    }

    public boolean removePoints(int removed) {
        return false;
    }

    public List<CosmeticItem> getItems() {
        return null;
    }

    public boolean setItems(List<CosmeticItem> items) {
        return false;
    }
}
