package org.neo4j.movies2;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
public class MovieController {
    final MovieService service;



    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/nodes/{label}")
    public int nodeCount(@PathVariable("label") String label){

        return service.nodeCount(label);
    }

//    @GetMapping("/name")
//    public Stream<String>  names(){
//        return service.names();
//    }



    @GetMapping(value = "/name", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> names(){
        return service.rxSong().map(Song::getName).delayElements(Duration.ofMillis(250));
    }




    // Return Everything
    @GetMapping(value = "/songs", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Song> songs(){
        return service.rxSong();
    }


    @GetMapping(value = "/name/{name}")
    public List<Song> byName(@PathVariable("name") String name){
        return service.byName(name);

    }


}
