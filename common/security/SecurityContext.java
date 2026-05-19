package common.security;

public class SecurityContext {
    private Integer userId ;
    private String username;
    private String role ;
    private Integer warehouseId ;
    private static SecurityContext context = null;
    // Make it private so that object cannot be created
    private SecurityContext(){

    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString() {
        return "SecurityContext{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static SecurityContext getContext(){
        if(context == null){
            context = new SecurityContext();
            return context;
        }else {
            return context;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
