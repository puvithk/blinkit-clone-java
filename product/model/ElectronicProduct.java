package product.model;

public class ElectronicProduct extends Product{
    private String serviceCenterDetails;
    private String wattage;
    private String keyFeatures;
    public String getServiceCenterDetails() {
        return serviceCenterDetails;
    }

    public void setServiceCenterDetails(String serviceCenterDetails) {
        this.serviceCenterDetails = serviceCenterDetails;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }


    public String getKeyFeatures() {
        return keyFeatures;
    }

    public void setKeyFeatures(String keyFeatures) {
        this.keyFeatures = keyFeatures;
    }
}

