package shop.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.Product;
import shop.entity.Order;
import shop.service.OrderService;
import shop.service.ProducerService;
import shop.service.ProductService;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final ProductService productService;
    private final ProducerService producerService;
    private final OrderService orderService;


    @GetMapping("/product/{product}/order/create")
    public String createOrder(Model model,
                              @PathVariable Product product,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "true") Boolean isDesc) {
        Sort producerSort = Sort.by(isDesc ? Sort.Direction.DESC: Sort.Direction.ASC,sortField);

        model.addAttribute("product", product);
        model.addAttribute("producers", producerService.getAllByProductId(product.getId(), producerSort));
        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);

        return "order-create";
    }

    @PostMapping("/product/{productId}/order/create")
    @ResponseBody
    public Order createOrder(@PathVariable Long productId,
                             @Valid @RequestBody Order order) {

        return orderService.save(order);
    }

    @GetMapping("/order")
    public String orders(Model model,
                         @RequestParam(defaultValue = "id") String sortField,
                         @RequestParam(defaultValue = "true") Boolean isDesc) {

        model.addAttribute("orders", orderService.getAll(Sort.by(isDesc ? Sort.Direction.DESC: Sort.Direction.ASC,sortField)));
        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);

        return "order-page";
    }


    public OrderController(ProductService productService, ProducerService producerService, OrderService orderService) {
        this.productService = productService;
        this.producerService = producerService;
        this.orderService = orderService;
    }
}
