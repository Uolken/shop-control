package shop.controller;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.Sale;
import shop.entity.SaleItem;
import shop.entity.Seller;
import shop.service.BatchService;
import shop.service.SaleService;
import shop.service.SellerService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SaleController {

    private final SaleService saleService;
    private final SellerService sellerService;
    private final BatchService batchService;

    @GetMapping("/sale")
    public String saleList(
            Model model,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") Boolean isDesc,
            Authentication authentication
    ) {
        Seller seller = (Seller) authentication.getPrincipal();

        Sort sort = Sort.by(isDesc ? Sort.Direction.DESC: Sort.Direction.ASC,sortField);
        model.addAttribute("seller", (Seller) authentication.getPrincipal());
        List<Sale> sales = saleService.getAllBySellerId(seller.getId(), sort);
        model.addAttribute("sales", sales);

        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);
        return "sale-list";
    }

    @PostMapping("/sale")
    public String createSale(Authentication authentication) {
        Seller seller = (Seller) authentication.getPrincipal();
        Sale sale = new Sale();
//        seller.getSales().add(sale);
        sale.setDate(LocalDate.now());
        sale.setSeller(seller);

        saleService.save(sale);
        return "redirect:/sale";
    }

    @GetMapping("/sale/{sale}/sale-item/add")
    public String addSaleItem(Model model, @PathVariable Sale sale,
                              @RequestParam(defaultValue = "id") String sortField,
                              @RequestParam(defaultValue = "true") Boolean isDesc) {

        Sort sort = Sort.by(isDesc ? Sort.Direction.DESC: Sort.Direction.ASC,sortField);

        model.addAttribute("sale", sale);
        model.addAttribute("batches", batchService.getNotSpoiled(sort));
        model.addAttribute("sortField", sortField);
        model.addAttribute("isDesc", isDesc);

        return "sale-item-create";
    }

    @PostMapping("/sale/{sale}/sale-item/add")
    @ResponseBody
    public void addSaleItem(@PathVariable Sale sale, @Valid @RequestBody SaleItem saleItem) {
        if (sale == null) throw new IllegalArgumentException();
        sale.getSaleItems().add(saleItem);
        saleService.save(sale);
    }

    public SaleController(SaleService saleService, SellerService sellerService, BatchService batchService) {
        this.saleService = saleService;
        this.sellerService = sellerService;
        this.batchService = batchService;
    }
}
