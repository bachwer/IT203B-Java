package Session05.util;

import Session05.model.MenuItem;

public class Validator {
    public static boolean isPositive(int number){
        return number > 0;
    }
    
    public static boolean isValidString(String value){
        return value != null && !value.trim().isEmpty();
    }
    
    public static void validateMenuItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("MenuItem không được null");
        }
        if (!isValidString(item.getId())) {
            throw new IllegalArgumentException("ID món ăn không được rỗng");
        }
        if (!isValidString(item.getName())) {
            throw new IllegalArgumentException("Tên món ăn không được rỗng");
        }
        if (item.getPrice() <= 0) {
            throw new IllegalArgumentException("Giá món ăn phải lớn hơn 0");
        }
        if (item.getStock() < 0) {
            throw new IllegalArgumentException("Tồn kho không được âm");
        }
    }
}
