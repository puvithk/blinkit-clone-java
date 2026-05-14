package product.model;

public class NutritionalInfo {
    private Long protein;
    private Long carbohydrates;

    public Long getProtein() {
        return protein;
    }

    public void setProtein(Long protein) {
        this.protein = protein;
    }

    public Long getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Long carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Long getTotalSugar() {
        return totalSugar;
    }

    public void setTotalSugar(Long totalSugar) {
        this.totalSugar = totalSugar;
    }

    public Long getAddedSugars() {
        return addedSugars;
    }

    public void setAddedSugars(Long addedSugars) {
        this.addedSugars = addedSugars;
    }

    public Long getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Long totalFat) {
        this.totalFat = totalFat;
    }

    public Long getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Long saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Long getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(Long dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public Long getSodium() {
        return sodium;
    }

    public void setSodium(Long sodium) {
        this.sodium = sodium;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getServeSize() {
        return serveSize;
    }

    public void setServeSize(Long serveSize) {
        this.serveSize = serveSize;
    }

    private Long totalSugar;
    private Long addedSugars;
    private Long totalFat;
    private Long saturatedFat;
    private Long dietaryFiber;
    private Long sodium;
    private Long calories;
    private Long serveSize;


    @Override
    public String toString() {
        return " NutritionalInfo \n" +
                "| protein=" + protein +  "| \n" +
                "| carbohydrates=" + carbohydrates +"| \n" +
                "| totalSugar=" + totalSugar +"| \n" +
                "| addedSugars=" + addedSugars +"| \n" +
                "| totalFat=" + totalFat +"| \n" +
                "| saturatedFat=" + saturatedFat +"| \n" +
                "| dietaryFiber=" + dietaryFiber +"| \n" +
                "| sodium=" + sodium +"| \n" +
                "| calories=" + calories +"| \n" +
                "| serveSize=" + serveSize +  "| \n" ;
    }
}
