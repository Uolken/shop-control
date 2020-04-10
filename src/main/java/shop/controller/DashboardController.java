package shop.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.service.ProductService;
import shop.service.SettingService;

import java.time.LocalDate;

@Controller
public class DashboardController {

    private final ProductService productService;

    @GetMapping
    public String redirect(){ return "redirect:/dashboard";}

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            @RequestParam(defaultValue = "recommendedDate") String sortField,
                            @RequestParam(defaultValue = "true") Boolean isDesc) {

//        Sort sort = Sort.by(isDesc ? Sort.by(sortField + " DESC NULLS FIRST") : Sort.Order.asc(sortField).with(Sort.NullHandling.NULLS_FIRST));
        PageRequest page = PageRequest.of(0, 100, Sort.by(isDesc ? Sort.Order.asc(sortField).nullsLast() : Sort.Order.desc(sortField).nullsFirst()));
        model.addAttribute("products", productService.getAll(Sort.by(isDesc ? Sort.Order.asc(sortField).nullsLast() : Sort.Order.desc(sortField).nullsFirst())));

        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);
        model.addAttribute("localDate", LocalDate.now());

        return "dashboard";
    }

    public DashboardController(ProductService productService, SettingService settingService) {
        this.productService = productService;
    }
}
