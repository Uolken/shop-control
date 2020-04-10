package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.entity.Setting;
import shop.service.SettingService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SettingsController {

    private final SettingService settingService;

    @GetMapping("/settings")
    public String settings(Model model) {
        Map<String, String> settings = settingService.getAll().stream().collect(Collectors.toConcurrentMap(Setting::getName, Setting::getValue));
        model.addAttribute("settings", settings);
        return "settings-page";
    }

    @PutMapping("/settings")
    @ResponseBody
    public void update(@RequestBody List<Setting> settings) {
        settingService.saveAllSettings(settings);
    }


    public SettingsController(SettingService settingService) {
        this.settingService = settingService;
    }
}
