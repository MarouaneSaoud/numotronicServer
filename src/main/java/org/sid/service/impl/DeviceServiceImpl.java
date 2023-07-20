package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.Reference;
import org.sid.dao.repository.DeviceRepository;
import org.sid.dao.repository.ReferenceRepository;
import org.sid.dto.DeviceToSave;
import org.sid.dto.DeviceToSend;
import org.sid.dto.DevicesFromDTO;
import org.sid.error.TechnicalException;
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
    private ReferenceRepository referenceRepository;

    @Override
    public List<DeviceToSend> devicelist() {
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
            devices.setIMEI((String) deviceMap.get("imei"));
            devices.setReference((String) deviceMap.get("firware"));
            devices.setConfig((String) deviceMap.get("config"));
            devices.setLastSeen((String) deviceMap.get("lastSeen"));
            deviceListDTO.add(devices);
        }
        List<DeviceToSend> listDevices =  new ArrayList<>();
        for (DevicesFromDTO devices : deviceListDTO){
            DeviceToSend device= new DeviceToSend();
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
    public Device addDevice(DeviceToSave deviceToSave) {
        Device device = new Device();
        Reference reference = referenceRepository.findById(deviceToSave.getReference()).orElse(null) ;
        if (reference!=null) {

            device.setImei(deviceToSave.getImei());
            device.setSerialNum(deviceToSave.getSerialNum());
            device.setDescription(deviceToSave.getDescription());
            device.setCreatedAt(new Date());
            device.setReference(reference);

        }
        else {
            throw new TechnicalException("reference null");
        }

        return deviceRepository.save(device);

    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Device findDeviceByImei(Integer imei) {
        return deviceRepository.findDeviceByImei(imei);
    }
}
