package cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.domain.Fruit;
import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.repository.FruitRepository;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController


@RequestMapping("/fruita")
public class FruitController {

	@Autowired
	FruitRepository fruitRepository;

	@PostMapping("/add")
	public ResponseEntity<Fruit> add(@RequestBody Fruit fruit) {
		try {
			Fruit _fruit = fruitRepository.save(new Fruit(fruit.getNombre(), fruit.getQuantitatQuilos()));
			return new ResponseEntity<>(_fruit, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Fruit> update(@PathVariable("id") int id, @RequestBody Fruit fruit) {
		Optional<Fruit> fruitData = fruitRepository.findById(id);

		if (fruitData.isPresent()) {
			Fruit _fruit = fruitData.get();
			_fruit.setNombre(fruit.getNombre());
			_fruit.setQuantitatQuilos(fruit.getQuantitatQuilos());
			return new ResponseEntity<>(fruitRepository.save(_fruit), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			fruitRepository.deleteById(id);
		//	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Fruit>> getAll(@RequestParam(required = false) String nombre) {
		try {
			List<Fruit> fruits = new ArrayList<Fruit>();

			if (nombre == null)
				fruitRepository.findAll().forEach(fruits::add);
			else
				fruitRepository.findByNombre(nombre).forEach(fruits::add);

			if (fruits.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(fruits, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Fruit> getOne(@PathVariable("id") int id) {
		Optional<Fruit> fruitData = fruitRepository.findById(id);

		if (fruitData.isPresent()) {
			return new ResponseEntity<>(fruitData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
