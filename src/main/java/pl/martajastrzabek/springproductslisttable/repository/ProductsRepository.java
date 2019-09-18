package pl.martajastrzabek.springproductslisttable.repository;

import org.springframework.stereotype.Repository;
import pl.martajastrzabek.springproductslisttable.model.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsRepository {

    private List<Product> productsList = new ArrayList<>();

    public void addProductToList(String name, double price) {
        productsList.add(new Product(name, price));
    }

    public void addProductToList(Product product) {
        productsList.add(product);
    }

    public List<Product> getAllProducts() {
        return productsList;
    }

    public double countSum() {
        double sum = 0;
        for (Product product : productsList) {
            sum += product.getPrice();
        }
        return sum;
    }


}
