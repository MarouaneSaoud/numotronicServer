package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.repository.DeviceRepository;
import org.sid.dto.DevicesFromDTO;
import org.sid.model.StatusDevice;
import org.sid.service.DeviceService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    @Override
    public List<Device> devicelist() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH-mm");

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.thingspeak.com/channels/1053969/fields/1/last?api_key=TW9N2WQSI0AGG912";
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});
        Map<String, Object> responseBody = responseEntity.getBody();

        List<Map<String, Object>> devicesList = (List<Map<String, Object>>) responseBody.get("devices");
        List<DevicesFromDTO> deviceListDTO = new ArrayList<>();

        for (Map<String, Object> deviceMap : devicesList) {
            DevicesFromDTO devices = new DevicesFromDTO();
            devices.setIMEI((String) deviceMap.get("IMEI"));
            devices.setReference((String) deviceMap.get("reference"));
            devices.setConfig((String) deviceMap.get("config"));
            devices.setLastSeen((String) deviceMap.get("lastSeen"));
            deviceListDTO.add(devices);
        }
        List<Device> listDevices =  new ArrayList<>();
        for (DevicesFromDTO devices : deviceListDTO){
            Device device= new Device();
            device.setImei(devices.getIMEI());
            device.setTime(devices.getLastSeen());
            device.setFirmware(devices.getReference());
            device.setConfiguration(devices.getConfig());
            try {
                Date lastSeenDate = sdf.parse(devices.getLastSeen());
                calendar.setTime(lastSeenDate);
                calendar.add(Calendar.HOUR_OF_DAY, 6); // Ajouter 6 heures à la dernière date vue

                if (currentDate.before(calendar.getTime())) {
                    device.setStatusDevice(StatusDevice.ONLINE);
                } else if(currentDate.after(calendar.getTime()))  {
                    device.setStatusDevice(StatusDevice.OFFLINE);
                }else {
                    device.setStatusDevice(StatusDevice.INACTIF);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listDevices.add(device);

        }
        return listDevices;
    }

    @Override
    public Device AddDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
