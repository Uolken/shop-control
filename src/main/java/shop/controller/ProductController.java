package shop.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.entity.Product;
import shop.service.*;

import java.time.LocalDate;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProducerService producerService;
    private final OrderService orderService;
    private final BatchService batchService;
    private final SettingService settingService;

    @GetMapping("/product/create")
    public String main(Model model) {
        model.addAttribute("metrics", productService.getMetrics());
        model.addAttribute("recommendedDate", LocalDate.now().plusDays(Long.parseLong(settingService.getValue("recommendedDays"))));

        return "product-create";
    }

    @GetMapping("/product/{product}")
    public String productPage(Model model,
                              @PathVariable Product product,
                              @RequestParam(defaultValue = "name") String producerSortField,
                              @RequestParam(defaultValue = "true") Boolean producerIsDesc,
                              @RequestParam(defaultValue = "id") String orderSortField,
                              @RequestParam(defaultValue = "true") Boolean orderIsDesc,
                              @RequestParam(defaultValue = "id") String batchSortField,
                              @RequestParam(defaultValue = "true") Boolean batchIsDesc
    ) {
        model.addAttribute("recommendedDays", settingService.getValue("recommendedDays"));
        model.addAttribute("product", product);
        model.addAttribute("metrics", productService.getMetrics());
        model.addAttribute("producers", producerService.getAllByProductId(product.getId(), Sort.by(producerIsDesc ? Sort.Direction.DESC : Sort.Direction.ASC, producerSortField)));
        model.addAttribute("orders", orderService.getAllByProductId(product.getId(), Sort.by(orderIsDesc ? Sort.Direction.DESC : Sort.Direction.ASC, orderSortField)));
        model.addAttribute("batches", batchService.getAllByProductId(product.getId(), Sort.by(batchIsDesc ? Sort.Direction.DESC : Sort.Direction.ASC, batchSortField)));

        model.addAttribute("producerSortField", producerSortField);
        model.addAttribute("producerIsDesc", producerIsDesc);
        model.addAttribute("orderSortField", orderSortField);
        model.addAttribute("orderIsDesc", orderIsDesc);
        model.addAttribute("batchSortField", batchSortField);
        model.addAttribute("batchIsDesc", batchIsDesc);
        return "product-page";
    }

    public ProductController(ProductService productService, ProducerService producerService, OrderService orderService, BatchService batchService, SettingService settingService) {
        this.productService = productService;
        this.producerService = producerService;
        this.orderService = orderService;
        this.batchService = batchService;
        this.settingService = settingService;
    }
}
