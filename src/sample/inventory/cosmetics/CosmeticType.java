package sample.inventory.cosmetics;

import java.util.Objects;

public class CosmeticType {
    private String name;
    private String imageResource;
    private CosmeticQuality quality;

    public CosmeticType(String name, String imageResource, CosmeticQuality quality) {
        this.name = name;
        this.imageResource = imageResource;
        this.quality = quality;
    }

    //create item

    public String getName() {
        return name;
    }

    public String getImageResource() {
        return imageResource;
    }

    public CosmeticQuality getQuality() {
        return quality;
    }

    public static CosmeticType[] getCosmeticTypes() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmeticType that = (CosmeticType) o;
        return name.equals(that.name) && imageResource.equals(that.imageResource) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageResource, quality);
    }
}
