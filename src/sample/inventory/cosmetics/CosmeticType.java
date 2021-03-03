package sample.inventory.cosmetics;

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
        return new CosmeticType[]{
                new CosmeticType("Abstract", "images/cosmetics/abstract.png", CosmeticQuality.NORMAL, "abstract"),
                new CosmeticType("Flies", "images/cosmetics/flies.png", CosmeticQuality.NORMAL, "flies"),
                new CosmeticType("Frog", "images/cosmetics/frog.png", CosmeticQuality.NORMAL, "frog"),
                new CosmeticType("Penguin", "images/cosmetics/penguin.png", CosmeticQuality.NORMAL, "penguin"),
                new CosmeticType("Snake", "images/cosmetics/snake.png", CosmeticQuality.NORMAL, "snake"),
                new CosmeticType("Jerma", "images/cosmetics/jerma.png", CosmeticQuality.NORMAL, "jerma")
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
