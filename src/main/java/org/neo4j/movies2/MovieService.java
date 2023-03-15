package org.neo4j.movies2;

import org.neo4j.driver.Driver;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.ReactiveNeo4jTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MovieService {


    final Driver driver;
    final Neo4jTemplate template;

    final ReactiveNeo4jTemplate rxTemplate;

    final MovieRepository  repository;


    public MovieService(Driver driver, Neo4jTemplate template, ReactiveNeo4jTemplate rxTemplate, MovieRepository repository){
        this.driver = driver;
        this.template = template;
        this.rxTemplate = rxTemplate;
        this.repository = repository;
    }
    public int nodeCount(String label) {
        return driver.session().run(String.format("MATCH (n:`%s`) RETURN count(*) as nodes",label)).single().get("nodes").asInt();
    }

    public Stream<String> names(){
        return template.findAll(Song.class).stream().map(Song::getName);
    }


    public Flux<Song> rxSong(){
        return rxTemplate.findAll(Song.class);

    }

    public List<Song> byName(String name){
        Song song = new Song();
        song.name = name;
        return repository.findAll(Example.of(song));

    }





}
