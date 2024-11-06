package site.yacht.backend.domain.deployment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.yacht.backend.domain.deployment.api.request.DeployApplicationRequest;

@Service
@RequiredArgsConstructor
public class DeployService {

    @Value("${cloud.url}")
    private String cloudUrl;

    private final ObjectMapper objectMapper;

    public boolean deploy(DeployApplicationRequest request) {
        // validation

        try {
            // 객체를 JSON 문자열로 변환
            String jsonString = objectMapper.writeValueAsString(request);
            System.out.println("JSON 형식으로 변환된 데이터: " + jsonString);
        } catch (JsonProcessingException e) {
            return false;
        }

//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<String> cloudRequest = new HttpEntity<>("body");
//        ResponseEntity<Void> response = restTemplate.exchange(cloudUrl, HttpMethod.POST, cloudRequest, Void.class);
//        System.out.println(response.getStatusCode());
//
//        return response.getStatusCode() == HttpStatus.OK;
        return true;
    }
}
