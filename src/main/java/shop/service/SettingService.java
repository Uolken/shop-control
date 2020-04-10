package shop.service;

import shop.entity.Setting;

import java.util.List;

public interface SettingService {
    List<Setting> getAll();

    String getValue(String name);

    void saveSettings(String name, String value);

    void saveSettings(Setting setting);

    void saveAllSettings(Iterable<Setting> settings);

}
