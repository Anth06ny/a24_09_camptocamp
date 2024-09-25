package org.example.a24_09_camptocamp.controllers;

import org.example.a24_09_camptocamp.model.beans.MovieBean;
import org.example.a24_09_camptocamp.model.beans.StudentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class MyRestController {


    //Loadbalancer sans WebFlux
    @Autowired
    private RestTemplate restTemplate;

    //WebFlux
    @Autowired//Laisse à Spring le remplissage
    @Qualifier("moviesAPIClient")
    private WebClient moviesAPIClient;

    //WebFlux + LoadBalancer
    @Autowired//Laisse à Spring le remplissage
    @Qualifier("moviesAPIClientWithLoadBalancing")
    private WebClient moviesAPIClientWithLoadBalancing;


    //http://localhost:8080/directAccessReactive
    @GetMapping("/directAccessReactive")
    public Mono<MovieBean> directAccessReactive() {
        System.out.println("/directAccessReactive ");

        //Synchrone
        //return moviesAPIClient.get().uri("1").retrieve().bodyToMono(Movie.class).block();
        //Asynchrone
        return moviesAPIClient.get().uri("1").retrieve().bodyToMono(MovieBean.class);
    }

    //http://localhost:8080/eurekaAccess
    @GetMapping("/eurekaAccess")
    public MovieBean eurekaAccess() {
        System.out.println("/eurekaAccess");

        String url = "http://movies/movies"; // Utilise le nom du micro-service sur Eureka

        //Récéption
        return restTemplate.getForObject(url + "/1", MovieBean.class);
    }

    //http://localhost:9090/eurekaAccessReactive
    @GetMapping("/eurekaAccessReactive")
    public Mono<MovieBean> eurekaAccessReactive() {
        System.out.println("/eurekaAccessReactive");

        return moviesAPIClientWithLoadBalancing.get().uri("1").retrieve().bodyToMono(MovieBean.class);
    }

    //http://localhost:8080/testPublic
    @GetMapping("/testPublic")
    public String testPublic() {
        System.out.println("/testPublic");
        return "Hello public";
    }

    //http://localhost:8080/testPrivate
    @GetMapping("/testPrivate")
    public String testPrivate() {
        System.out.println("/testPrivate");
        return "Hello private";
    }

    //http://localhost:8080/testPrivateAdmin
    @GetMapping("/testPrivateAdmin")
    public String testPrivateAdmin() {
        System.out.println("/testPrivateAdmin");
        return "Hello private Admin";
    }

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto", "note": 12}
    @PostMapping("/receiveStudent")
    public void receiveStudent(@RequestBody StudentBean student) {
        System.out.println("/receiveStudent : " + student.getName() + " : " + student.getNote());

        //traitement, mettre en base…
        //Retourner d'autres données
    }


    //http://localhost:8080/max?p2=6
    //http://localhost:8080/max?p1=5
    @GetMapping("/max")
    public int max(String p1, String p2) {
        System.out.println("/max");

        Integer p1Null = null;
        try {
            p1Null = Integer.valueOf(p1);
        }
        catch(Exception e) {

        }

        Integer p2Null = null;
        try {
            p2Null = Integer.valueOf(p2);
        }
        catch(Exception e) {

        }

        if(p1Null == null) {
            return p2Null;
        }
        else if(p2Null == null){
            return p1Null;
        }

        return Math.max(p1Null,p2Null);
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    public String testMethode() {
        System.out.println("/test");

        return "helloWorld";
    }
}
