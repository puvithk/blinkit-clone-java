package mockData;

import cart.model.Cart;
import inventory.model.WareHouse;
import inventory.model.WareHouseInventory;
import order.model.Order;
import payment.model.Payment;
import product.model.ElectronicProduct;
import product.model.FoodProduct;
import product.model.NutritionalInfo;
import product.model.Product;
import product.model.category.MainCategory;
import product.model.category.SubCategory;
import user.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// This is not ideal for production environment


public class MockData {
    // All products list
    public static List<Product> products = new ArrayList<>();
    // Order list
    public static List<Order> orderList = new ArrayList<>();


    // Payment list
    public static List<Payment> paymentList = new ArrayList<>();
    // All Warehouse list
    public  static  List<WareHouse> wareHouseList = new ArrayList<>(    List.of(
            new WareHouse(1,"Anvish Pvt" , "575022") ,
            new WareHouse(2,"Manuvith Pvt" , "575056")
    ));
    // List of users
    public static List<User> users = new ArrayList<>(List.of(
            new User(1,"puvithkumar" , "puvith@gmail.com" , LocalDate.now() , "+919964012373" ),
            new User(2,"puvithkumar" , "puvith@gmail.com" , LocalDate.now() , "+919964038153" )

    ));

    // List of Warehouse inventory
    public static List<WareHouseInventory> wareHouseInventories = new ArrayList<>();

  public static final List<Cart>  carts = new ArrayList<>(List.of());
    public List<Product> productsData() {
        MainCategory foodCategory = new MainCategory();
        foodCategory.setId(1);
        foodCategory.setName("Food");

        MainCategory electronicsCategory = new MainCategory();
        electronicsCategory.setId(2);
        electronicsCategory.setName("Electronics");

        SubCategory biscuitsCategory = new SubCategory();
        biscuitsCategory.setId(101);
        biscuitsCategory.setName("Biscuits");
        biscuitsCategory.setMainCategory(foodCategory);

        SubCategory chocolatesCategory = new SubCategory();
        chocolatesCategory.setId(102);
        chocolatesCategory.setName("Chocolates");
        chocolatesCategory.setMainCategory(foodCategory);

        SubCategory headphonesCategory = new SubCategory();
        headphonesCategory.setId(201);
        headphonesCategory.setName("Headphones");
        headphonesCategory.setMainCategory(electronicsCategory);

        SubCategory kitchenCategory = new SubCategory();
        kitchenCategory.setId(202);
        kitchenCategory.setName("Kitchen Appliances");
        kitchenCategory.setMainCategory(electronicsCategory);

        // =========================
        // Food Product 1
        // =========================

        NutritionalInfo biscuitNutrition = new NutritionalInfo();
        biscuitNutrition.setCalories(480L);
        biscuitNutrition.setProtein(6L);
        biscuitNutrition.setCarbohydrates(65L);
        biscuitNutrition.setTotalSugar(20L);
        biscuitNutrition.setAddedSugars(15L);
        biscuitNutrition.setTotalFat(18L);
        biscuitNutrition.setSaturatedFat(8L);
        biscuitNutrition.setDietaryFiber(4L);
        biscuitNutrition.setSodium(220L);
        biscuitNutrition.setServeSize(100L);

        FoodProduct biscuit = new FoodProduct();
        biscuit.setId(1);
        biscuit.setProductTitle("Choco Cream Biscuits");
        biscuit.setDescription("Crunchy chocolate cream biscuits");
        biscuit.setImageUrl("https://example.com/biscuit.jpg");
        biscuit.setUnit(2);
        biscuit.setDisclaimer("Store in cool and dry place");
        biscuit.setCountryOfOrigin("India");
        biscuit.setManufacturerName("ABC Foods Pvt Ltd");
        biscuit.setManufacturerAddress("Bangalore, Karnataka");
        biscuit.setMarketerNameAddress("ABC Retail, Bangalore");
        biscuit.setReturnPolicy("No Return");
        biscuit.setCategory(biscuitsCategory);
        biscuit.setPrice(10.0);

        biscuit.setFssaiLicense("FSSAI123456789");
        biscuit.setShelfLife(6);
        biscuit.setTasteProfile("Sweet");
        biscuit.setSugarProfile("Medium Sugar");
        biscuit.setNutritionInfo(biscuitNutrition);

        products.add(biscuit);

        // =========================
        // Food Product 2
        // =========================

        NutritionalInfo chocolateNutrition = new NutritionalInfo();
        chocolateNutrition.setCalories(530L);
        chocolateNutrition.setProtein(5L);
        chocolateNutrition.setCarbohydrates(58L);
        chocolateNutrition.setTotalSugar(40L);
        chocolateNutrition.setAddedSugars(35L);
        chocolateNutrition.setTotalFat(30L);
        chocolateNutrition.setSaturatedFat(16L);
        chocolateNutrition.setDietaryFiber(3L);
        chocolateNutrition.setSodium(120L);
        chocolateNutrition.setServeSize(100L);

        FoodProduct chocolate = new FoodProduct();
        chocolate.setId(2);
        chocolate.setProductTitle("Dark Fantasy Chocolate");
        chocolate.setDescription("Premium dark chocolate cubes");
        chocolate.setImageUrl("https://example.com/chocolate.jpg");
        chocolate.setUnit(1);
        chocolate.setDisclaimer("Contains milk and nuts");
        chocolate.setCountryOfOrigin("India");
        chocolate.setManufacturerName("SweetBite Foods");
        chocolate.setManufacturerAddress("Mumbai, Maharashtra");
        chocolate.setMarketerNameAddress("SweetBite Retail, Mumbai");
        chocolate.setReturnPolicy("No Return");
        chocolate.setCategory(chocolatesCategory);

        chocolate.setFssaiLicense("FSSAI987654321");
        chocolate.setShelfLife(12);
        chocolate.setTasteProfile("Bitter Sweet");
        chocolate.setSugarProfile("High Sugar");
        chocolate.setNutritionInfo(chocolateNutrition);
        chocolate.setPrice(30.0);

        products.add(chocolate);

        // =========================
        // Electronic Product 1
        // =========================

        ElectronicProduct headphone = new ElectronicProduct();
        headphone.setId(3);
        headphone.setProductTitle("Noise Cancelling Headphones");
        headphone.setDescription("Wireless over-ear headphones");
        headphone.setImageUrl("https://example.com/headphone.jpg");
        headphone.setUnit(1);
        headphone.setDisclaimer("Avoid water exposure");
        headphone.setCountryOfOrigin("China");
        headphone.setManufacturerName("SoundMax");
        headphone.setManufacturerAddress("Shenzhen, China");
        headphone.setMarketerNameAddress("SoundMax India Pvt Ltd");
        headphone.setReturnPolicy("7 Days Return");
        headphone.setCategory(headphonesCategory);

        headphone.setServiceCenterDetails("Available in all metro cities");
        headphone.setWattage("20W");
        headphone.setKeyFeatures("Bluetooth 5.3, ANC, 40hr Battery");
        headphone.setPrice(120.0);
        products.add(headphone);

        // =========================
        // Electronic Product 2
        // =========================

        ElectronicProduct mixer = new ElectronicProduct();
        mixer.setId(4);
        mixer.setProductTitle("Kitchen Mixer Grinder");
        mixer.setDescription("750W powerful mixer grinder");
        mixer.setImageUrl("https://example.com/mixer.jpg");
        mixer.setUnit(1);
        mixer.setDisclaimer("Do not overload motor");
        mixer.setCountryOfOrigin("India");
        mixer.setManufacturerName("HomeTech Appliances");
        mixer.setManufacturerAddress("Chennai, Tamil Nadu");
        mixer.setMarketerNameAddress("HomeTech India");
        mixer.setReturnPolicy("10 Days Replacement");
        mixer.setCategory(kitchenCategory);

        mixer.setServiceCenterDetails("Toll Free: 1800-123-456");
        mixer.setWattage("750W");
        mixer.setKeyFeatures("3 Jars, Copper Motor, Shock Proof");
        mixer.setPrice(152.0);
        products.add(mixer);
        return products;
    }

    // Function to get the warehouse inventory data
    public List<WareHouseInventory> getWareHouseInventories(List<Product> productList, List<WareHouse> wareHouseList) {

        List<WareHouseInventory> wareHouseInventoryList = new ArrayList<>();

        // Warehouse 1 inventory
        wareHouseInventoryList.add(
                new WareHouseInventory(
                        1,
                        wareHouseList.get(0), // Anvish Pvt
                        productList.get(0),   // Choco Cream Biscuits
                        120
                )
        );

        wareHouseInventoryList.add(
                new WareHouseInventory(
                        2,
                        wareHouseList.get(0),
                        productList.get(1),   // Dark Fantasy Chocolate
                        80
                )
        );

        wareHouseInventoryList.add(
                new WareHouseInventory(
                        3,
                        wareHouseList.get(0),
                        productList.get(2),   // Headphones
                        25
                )
        );

        // Warehouse 2 inventory
        wareHouseInventoryList.add(
                new WareHouseInventory(
                        4,
                        wareHouseList.get(1), // Manuvith Pvt
                        productList.get(0),   // Biscuits
                        200
                )
        );

        wareHouseInventoryList.add(
                new WareHouseInventory(
                        5,
                        wareHouseList.get(1),
                        productList.get(2),   // Headphones
                        15
                )
        );

        wareHouseInventoryList.add(
                new WareHouseInventory(
                        6,
                        wareHouseList.get(1),
                        productList.get(3),   // Mixer Grinder
                        40
                )
        );

        return wareHouseInventoryList;
    }


    private static MockData mockData;
    private MockData(){
        products =  productsData();
        wareHouseInventories = getWareHouseInventories(products , wareHouseList);
    }

    public static MockData getObject(){
        if(mockData==null){
            return new MockData();
        }else {
            return mockData;
        }
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        MockData.products = products;
    }

    public static List<WareHouse> getWareHouseList() {
        return wareHouseList;
    }

    public static void setWareHouseList(List<WareHouse> wareHouseList) {
        MockData.wareHouseList = wareHouseList;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        MockData.users = users;
    }

    public static List<WareHouseInventory> getWareHouseInventories() {
        return wareHouseInventories;
    }

    public static void setWareHouseInventories(List<WareHouseInventory> wareHouseInventories) {
        MockData.wareHouseInventories = wareHouseInventories;
    }

    public static MockData getMockData() {
        return mockData;
    }

    public static void setMockData(MockData mockData) {
        MockData.mockData = mockData;
    }
}


