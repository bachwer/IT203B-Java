package Session05.repository;

import Session05.model.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuRepository {

    private List<MenuItem> menuItems = new ArrayList<>();

    // Thêm món vào menu
    public void add(MenuItem item) {
        menuItems.add(item);
    }

    // Lấy toàn bộ menu
    public List<MenuItem> findAll() {
        return menuItems;
    }

    // Tìm món theo ID
    public Optional<MenuItem> findById(String id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    // Xóa món theo ID
    public boolean deleteById(String id) {
        return menuItems.removeIf(item -> item.getId().equals(id));
    }

    // Cập nhật món
    public void update(MenuItem updatedItem) {
        findById(updatedItem.getId()).ifPresent(item -> {
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());
            item.setStock(updatedItem.getStock());
        });
    }
}