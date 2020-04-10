package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.service.MailService;
import shop.service.SettingService;

@Controller
public class MainController {

    private final SettingService settingService;
    private final MailService mailService;

    @GetMapping("/mail")
    @ResponseBody
    public String productPage() {
        mailService.sendSimpleMessage();
        return "product-page";
    }

    public MainController(SettingService settingService, MailService mailService) {
        this.settingService = settingService;
        this.mailService = mailService;
    }
}
