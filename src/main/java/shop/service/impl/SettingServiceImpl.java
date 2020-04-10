package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.entity.Setting;
import shop.repos.SettingRepos;
import shop.service.SettingService;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {

    private final SettingRepos settingRepos;

    @Override
    public List<Setting> getAll() {
        return (List<Setting>) settingRepos.findAll();
    }

    @Override
    public String getValue(String name) {
        return settingRepos.findByName(name).getValue();
    }

    @Override
    public void saveSettings(String key, String value) {
        settingRepos.save(new Setting(key, value));
    }

    @Override
    public void saveSettings(Setting setting) {
        settingRepos.save(setting);
    }

    @Override
    public void saveAllSettings(Iterable<Setting> settings) {
        settingRepos.saveAll(settings);
    }

    public SettingServiceImpl(SettingRepos settingRepos) {
        this.settingRepos = settingRepos;
    }
}
