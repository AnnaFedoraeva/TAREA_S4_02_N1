package cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.domain.Fruit;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer> {

	Iterable<Fruit> findByNombre(String title);
	

}
