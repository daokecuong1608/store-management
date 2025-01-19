package com.sapo.store_management.service.img;

import com.sapo.store_management.dto.image.ImgBBResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class ImgBBService {

    @Value("${imgbb.api.key}")
    private String apiKey;

    @Value("${imgbb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ImgBBResponse uploadImageToImgBB(MultipartFile file) {
        try {
            // Encode image to Base64
            String encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
//            System.out.println("ImgBBService: Base64 Encoded Image: " + encodedImage);

            // Prepare request body as MultiValueMap
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("image", encodedImage);  // Add base64 encoded image

            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Create HttpEntity for sending
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

            // Construct URL with API key
            String url = apiUrl + "?key=" + apiKey;
            System.out.println("ImgBBService: Sending POST request to ImgBB URL: " + url);

            // Send the request
            ResponseEntity<ImgBBResponse> response = restTemplate.postForEntity(url, entity, ImgBBResponse.class);

            System.out.println("ImgBBResponse : " + response);

            // Handle the response
            if (response.getBody() != null && response.getBody().getData() != null) {
                return response.getBody();
            }

            System.out.println("ImgBBService: No valid URL found in response.");
        } catch (Exception e) {
            System.out.println("ImgBBService: Error - " + e.getMessage());
        }
        throw new RuntimeException("Image upload failed");
    }
}
