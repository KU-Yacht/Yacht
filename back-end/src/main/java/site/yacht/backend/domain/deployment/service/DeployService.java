package site.yacht.backend.domain.deployment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;
import site.yacht.backend.domain.deployment.dto.DeployApplicationRequestToCloud;

import javax.net.ssl.*;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
        String jsonString = "";
        try {
            // 객체를 JSON 문자열로 변환
            jsonString = objectMapper.writeValueAsString(requestToCloud);
            System.out.println("JSON 형식으로 변환된 데이터: " + jsonString);
        } catch (JsonProcessingException e) {
            return false;
        }

        return deployToCloud(jsonString) == HttpStatus.OK.value();
    }

    private int deployToCloud(String body) {
        disableSslVerification();
        try {
            URL url = new URL(cloudUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            try (OutputStream os = new BufferedOutputStream(connection.getOutputStream())) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            return connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("POST request failed with exception: " + e.getMessage());
        }
    }

    private void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
