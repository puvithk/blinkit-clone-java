package product.model;

public class FoodProduct extends Product{
    private String fssaiLicense;
    private Integer shelfLife;
    private String tasteProfile;
    private String sugarProfile;

    private NutritionalInfo nutritionInfo;

    public String getFssaiLicense() {
        return fssaiLicense;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                super.toString() +
                "| fssaiLicense='" + fssaiLicense + "| \n" +
                "| shelfLife=" + shelfLife + "| \n" +
                "| tasteProfile='" + tasteProfile + "| \n" +
                "| sugarProfile='" + sugarProfile + "| \n" +
                "| nutritionInfo=" + nutritionInfo + "| \n" ;
    }

    public void setFssaiLicense(String fssaiLicense) {
        this.fssaiLicense = fssaiLicense;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getTasteProfile() {
        return tasteProfile;
    }

    public void setTasteProfile(String tasteProfile) {
        this.tasteProfile = tasteProfile;
    }

    public String getSugarProfile() {
        return sugarProfile;
    }

    public void setSugarProfile(String sugarProfile) {
        this.sugarProfile = sugarProfile;
    }

    public NutritionalInfo getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(NutritionalInfo nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }
}
