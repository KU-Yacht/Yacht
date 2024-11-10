package site.yacht.backend.domain.deployment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;
import site.yacht.backend.domain.deployment.dto.DeployApplicationRequestToCloud;

@Service
@RequiredArgsConstructor
public class DeployService {

    @Value("${cloud.url}")
    private String cloudUrl;

    private final ObjectMapper objectMapper;
    private final ApplicationRepository applicationRepository;

    public boolean deploy(Long userId, Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(ApplicationNotFoundException::new);
        // validation permit

        DeployApplicationRequestToCloud requestToCloud = new DeployApplicationRequestToCloud(application);
        try {
            // 객체를 JSON 문자열로 변환
            String jsonString = objectMapper.writeValueAsString(requestToCloud);
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
