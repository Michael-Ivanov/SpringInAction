package miv.study.tacos.web.api;

import miv.study.tacos.Taco;
import miv.study.tacos.jpadatarepository.TacoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

    private TacoJpaRepository tacoJpaRepository;

    @Autowired
    public DesignTacoRestController(TacoJpaRepository tacoJpaRepository) {
        this.tacoJpaRepository = tacoJpaRepository;
    }

    @GetMapping("/recent")
    public List<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

       return tacoJpaRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoJpaRepository.findById(id);
        if (optionalTaco.isPresent()) {
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoJpaRepository.save(taco);
    }
}
