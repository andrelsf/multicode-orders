package br.dev.multicode.mcorders.repositories;

import br.dev.multicode.mcorders.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
