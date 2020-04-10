package shop.controller.api;

import org.springframework.web.bind.annotation.*;
import shop.entity.Product;
import shop.entity.dto.ProductResponse;
import shop.service.ProductService;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    private ProductService productService;

    @GetMapping("/product/{product}")
    ProductResponse getById(@PathVariable Product product) {
        return new ProductResponse(product);
    }

    @GetMapping("/product")
    List<ProductResponse> getAll() {
        List<ProductResponse> products = new ArrayList<>();
        for (Product p : productService.getAll()) {
            products.add(new ProductResponse(p));
        }
        return products;
    }

    @PostMapping("/product")
    ProductResponse create(@Valid @RequestBody Product product) {
        return new ProductResponse(productService.saveProduct(product));
    }

    @PutMapping("/product")
    ProductResponse update(@RequestBody Product product) {
        return new ProductResponse(productService.updateProduct(product));
    }



    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }
}
