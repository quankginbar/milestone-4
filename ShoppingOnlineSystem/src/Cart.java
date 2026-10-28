import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public ArrayList<CartItem> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }

    public boolean addProduct(Product product, int quantity) { // add sản phẩm vào giỏ
        if (product == null) {
            throw new IllegalArgumentException("Product does not exist.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (quantity > product.getStock()) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        for (CartItem item : items) { // Kiểm tra xem trong giỏ hàng đã có món hàng đc add ch
            if (item.getProduct().getId().equals(product.getId())) { // Kiểm tra lần lượt giỏ
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getStock()) { 
                    throw new IllegalArgumentException(
                            "Total quantity in cart exceeds available stock.");
                }
                item.setQuantity(newQuantity);
                return true;
            }
        }
// nếu sản phấm không có sẵn trong giỏ hàng thì thêm vào như 1 sản phẩm mới
        items.add(new CartItem(product, quantity));
        return true;
    }
 /**
     * Tính tổng giá trị của toàn bộ sản phẩm trong giỏ hàng.
     *
     * @return Tổng tiền của tất cả các mặt hàng trong giỏ.
     */
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) total += item.getSubtotal();
        return total;
    }
/**
     * Xóa toàn bộ sản phẩm khỏi giỏ hàng.
     * Sau khi gọi method này, giỏ hàng sẽ trống rỗng.
     */
    public void clear() { items.clear(); }
}
