package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.Order;
import shop.entity.Producer;
import shop.entity.Product;
import shop.service.ProducerService;

import javax.validation.Valid;

@Controller
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/product/{product}/producer/create")
    public String createPage(
            Model model,
            @PathVariable Product product
    ) {
        model.addAttribute("product", product);
        return "producer-create";
    }

    @PostMapping("/product/{productId}/producer/create")
    @ResponseBody
    public Producer createOrder(@PathVariable Long productId,
                             @Valid @RequestBody Producer producer) {
        return producerService.save(producer);
    }


    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }
}
