package cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.domain.Fruit;
import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.repository.FruitRepository;

@Service
public class FruitService {

	@Autowired
	FruitRepository repo;

	public Fruit save(Fruit fruit) {
		return repo.save(fruit);
	}

	public Optional<Fruit> findById(int id) {
		return repo.findById(id);
	}

	public ResponseEntity<?> update(int id, Fruit fruit) {
		Optional<Fruit> fruitOp = findById(id);
		if (fruitOp.isPresent()) {
			Fruit fruitToUpdate = fruitOp.get();
			fruitToUpdate.setNombre(fruit.getNombre());
			fruitToUpdate.setQuantitatQuilos(fruit.getQuantitatQuilos());
			return new ResponseEntity<>(repo.save(fruitToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.", HttpStatus.OK);
		}
	}

	public ResponseEntity<?> delete(int id) {
		Optional<Fruit> fruitOp = findById(id);
		if (fruitOp.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<>("The fruit was deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.", HttpStatus.OK);
		}
	}

	public ResponseEntity<?> findAll() {
		if (repo.findAll().isEmpty()) {
			return new ResponseEntity<>("The list is empty.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> getOne(int id) {
		Optional<Fruit> fruit = repo.findById(id);
		if (fruit.isPresent()) {
			return new ResponseEntity<>(fruit, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>("The fruit with id " + id + " was not found.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	

