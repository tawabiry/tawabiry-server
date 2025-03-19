package com.que.que.Settings;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    public static boolean isMaintenanceMode = false;
    public static boolean isComingSoonMode = false;
    public static boolean isWarning = false;
    private long maintenanceDuration = 10; // in minutes

    public Map<String, Object> getSettings() {
        Map<String, Object> settings = Map.of(
                "isMaintenanceMode", isMaintenanceMode,
                "isComingSoonMode", isComingSoonMode,
                "maintenanceDuration", maintenanceDuration);

        return settings;
    }

    public Map<String, Object> updateSettings(boolean isMaintenanceMode, boolean isComingSoonMode,
            long maintenanceDuration) {

        if (isMaintenanceMode && isComingSoonMode) {
            throw new IllegalArgumentException("Cannot set both maintenance and coming soon mode at the same time.");
        }

        if (isMaintenanceMode) {
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    SettingsService.isMaintenanceMode = true;
                    SettingsService.isWarning = false;
                }
            }, 60 * 60 * 1000);
            SettingsService.isWarning = true;
        } else {
            SettingsService.isMaintenanceMode = isMaintenanceMode;
            SettingsService.isWarning = false;
        }

        SettingsService.isComingSoonMode = isComingSoonMode;
        this.maintenanceDuration = maintenanceDuration;

        Map<String, Object> settings = Map.of(
                "isMaintenanceMode", isMaintenanceMode,
                "isComingSoonMode", isComingSoonMode,
                "maintenanceDuration", maintenanceDuration,
                "isWarning", SettingsService.isWarning);

        return settings;
    }

    public Map<String, Object> getWarning() {
        Map<String, Object> warning = Map.of("isWarning", SettingsService.isWarning);
        return warning;
    }
}
