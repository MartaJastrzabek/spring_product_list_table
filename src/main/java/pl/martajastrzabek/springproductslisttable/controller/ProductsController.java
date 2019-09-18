package pl.martajastrzabek.springproductslisttable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.martajastrzabek.springproductslisttable.model.Product;
import pl.martajastrzabek.springproductslisttable.repository.ProductsRepository;

import java.util.List;

@Controller
public class ProductsController {

    private ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
        productsRepository.addProductToList("Masło", 7.5);
        productsRepository.addProductToList("Chleb", 3.2);
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("product", new Product());
        return "home";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model) {
        String successMessage = "Produkt dodano do listy.";
        String errorMessage = "Produkt nie został dodany do listy. Spróbuj ponownie.";
        if (product != null && !StringUtils.isEmpty(product.getName())) {
            productsRepository.addProductToList(product);
            model.addAttribute("message", successMessage);
        } else {
            model.addAttribute("message", errorMessage);
        }
        return "home";
    }

    public void createModelAttributes(Model model) {
        List<Product> products = productsRepository.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("sum", productsRepository.countSum());
    }

    @GetMapping("/list")
    public String showProductList(Model model) {
        createModelAttributes(model);
        return "list";
    }

    @GetMapping("/table")
    public String showProductTable(Model model) {
        createModelAttributes(model);
        return "table";
    }

}
