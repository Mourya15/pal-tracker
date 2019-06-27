package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.*;
@RestController
public class WelcomeController {
    private String message;

    public WelcomeController(@Value("${WELCOME_MESSAGE}") String message) {
        this.message = message;
    }

    @GetMapping("/")
    public ResponseEntity<String>
    sayHello() {

        final String uri = "https://pivotal-rabbitmq.sys.pp01.useast.cf.ford.com/api/queues";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        headers.set("Authorization","Basic MGQ0OTBlOGQtN2VlMC00MWM2LTg0YmEtOGU3ZWZiMzMyNzI4OmhhaTE3dmJtdTIwcW9ma2xram92dW5jamE1");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
       // String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<String> response=restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(response);

        return response;
    }

}
