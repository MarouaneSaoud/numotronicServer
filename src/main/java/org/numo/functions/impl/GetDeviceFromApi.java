package org.numo.functions.impl;

import org.numo.dto.device.DevicesFromAPI;
import org.numo.functions.GetDevice;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetDeviceFromApi implements GetDevice {
    @Override
    public List<DevicesFromAPI> AllDevices(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://160.178.179.228:3000/devices";
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});
        Map<String, Object> responseBody = responseEntity.getBody();

        List<Map<String, Object>> devicesList = (List<Map<String, Object>>) responseBody.get("devices");
        List<DevicesFromAPI> devicesFromAPIS = new ArrayList<>();

        for (Map<String, Object> deviceMap : devicesList) {
            DevicesFromAPI devices = new DevicesFromAPI();
            devices.setIMEI((String) deviceMap.get("imei"));
            devices.setFirware((String) deviceMap.get("firmware"));
            devices.setConfig((String) deviceMap.get("config"));
            devices.setLastSeen((String) deviceMap.get("lastSeen"));
            devicesFromAPIS.add(devices);
        }

        return devicesFromAPIS;
    }
}
