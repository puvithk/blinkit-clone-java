package product.model;

import product.model.category.SubCategory;


public abstract class Product {
    private Integer id;
    private String imageUrl;
    private String productTitle;
    private String description;

    private Integer unit;
    private String disclaimer;
    private String countryOfOrigin;
    private String manufacturerName;
    private String manufacturerAddress;
    private String marketerNameAddress;
    private String returnPolicy;
    private SubCategory category ;
    private Double price ;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
// Food product

    public SubCategory getCategory() {
        return category;
    }

    public void setCategory(SubCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }

    public String getMarketerNameAddress() {
        return marketerNameAddress;
    }

    public void setMarketerNameAddress(String marketerNameAddress) {
        this.marketerNameAddress = marketerNameAddress;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }
    // Key information
    // Nutrition info
   // In Food prodcuts // info ->
    // Description ,
    // unit ,
    // FSSAI License ,
    // shelf life , taste profile , Disclaimer ,  Customer care details , country of origin , manufacture name and addtress , Marketers name and address , return policy , sugar profile

    @Override
    public String toString() {
        return  "\n Product INFO \n| id=" + id + "|" +
                "| imageUrl='" + imageUrl + "| " +
                "| productTitle='" + productTitle + "| " +
                "| description='" + description + "| \n" +
                "| unit=" + unit +"| "+
                "| disclaimer='" + disclaimer + "| " +
                "| countryOfOrigin='" + countryOfOrigin + "| " +
                "| manufacturerName='" + manufacturerName + "| \n" +
                "| manufacturerAddress='" + manufacturerAddress + "| " +
                "| marketerNameAddress='" + marketerNameAddress + "| "+
                "| returnPolicy='" + returnPolicy + "| " +
                "| category=" + category + "| \n" ;
    }


    // In electroince - description , service cenetrd etails , discalimer , cuontry of origin , maunufature name and address , return policy  , unit , wattages , key fratures
    // Create a base product class
}
