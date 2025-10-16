package com.hng.stagezero.service;
import com.hng.stagezero.dto.CatFactResponse;
import com.hng.stagezero.dto.ProfileResponse;
import com.hng.stagezero.dto.User;
import com.hng.stagezero.exceptions.CatFactUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl  implements  ProfileService {
    private final WebClient catFactClient;

    @Override
    public ProfileResponse getProfile() {
       User user = new User("ibrahim.habeeb2004@gmail.com", "Ibrahim Habeeb Abiola", "Java/Springboot");

       return new ProfileResponse("success", user, this.getCatFact(), Instant.now());



    }

    private String getCatFact() {

        CatFactResponse catFactResponse = catFactClient.get().uri("/").retrieve().bodyToMono(CatFactResponse.class).block();


        if (catFactResponse == null) {
            throw new CatFactUnavailableException("Cat Facts API returned empty response");
        }
        return catFactResponse.getFact();


    }
}
