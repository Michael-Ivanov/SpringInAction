package miv.study.tacos.web.api;

import miv.study.tacos.Taco;
import miv.study.tacos.jpadatarepository.TacoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RepositoryRestController
public class RecentTacosRestController {

    private TacoJpaRepository tacoJpaRepository;

    @Autowired
    public RecentTacosRestController(TacoJpaRepository tacoJpaRepository) {
        this.tacoJpaRepository = tacoJpaRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Taco>>> recentTacos() {

        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        List<Taco> tacoList = tacoJpaRepository.findAll(pageRequest).getContent();

        CollectionModel<EntityModel<Taco>> tacoResources = CollectionModel.wrap(tacoList);
        tacoResources.add(WebMvcLinkBuilder.linkTo(RecentTacosRestController.class)
                .slash("recent")
                .withRel("recents"));

        return new ResponseEntity<>(tacoResources, HttpStatus.OK);
    }
}
