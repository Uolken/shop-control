package shop.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.Batch;
import shop.entity.Order;
import shop.service.BatchService;
import shop.service.OrderService;

import java.time.LocalDate;

@Controller
public class BatchController {

    private final BatchService batchService;
    private final OrderService orderService;

    @GetMapping("/order/{order}/batch/create")
    public String createBatchFromOrder(
            Model model,
            @PathVariable Order order
    ) {
        model.addAttribute("order", order);
        return "order-confirm-page";
    }

    @PostMapping("/order/{order}/batch/create")
    @ResponseBody
    public Batch createBatchFromOrder(
            @PathVariable Order order,
            @RequestParam String productionDate,
            @RequestParam String expirationDate
    ) {
        Batch batch = new Batch();
        batch.setCount(order.getCountOfProduct());
        batch.setProductionDate(LocalDate.parse(productionDate));
        batch.setExpirationDate(LocalDate.parse(expirationDate));
        batch.setProduct(order.getProduct());

        order.setArriveDate(LocalDate.now());
        order.setStateIsArrived(true);
        orderService.save(order);
        return batchService.save(batch);
    }

    @GetMapping("/batch")
    public String batch(
            Model model,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") Boolean isDesc
    ) {
        Sort sort = Sort.by(isDesc ? Sort.Direction.DESC: Sort.Direction.ASC,sortField);

        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);

        model.addAttribute("batches", batchService.getAll(sort));
        return "batch-list";
    }

    public BatchController(BatchService batchService, OrderService orderService) {
        this.batchService = batchService;
        this.orderService = orderService;
    }
}
