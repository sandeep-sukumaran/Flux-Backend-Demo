package com.sandeepsukumaran.fluxbackenddemo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessController {

    @GetMapping(value = "/process", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ResponseObject> process() {
        Random random = new Random();
        int n = random.nextInt(8) + 3; // this will give us a random number between 3 and 10

        List<Boolean> statusList = IntStream.range(0, n)
                .mapToObj(i -> false)
                .collect(Collectors.toList());

        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> {
                    int indexToChange = random.nextInt(n);
                    statusList.set(indexToChange, true);
                    ResponseObject responseObject = new ResponseObject();
                    responseObject.setN(n);
                    responseObject.setStatus(new ArrayList<>(statusList));
                    responseObject.setCompleted(!statusList.contains(false));
                    return responseObject;
                })
                .takeUntil(ResponseObject::isCompleted);
    }
}
