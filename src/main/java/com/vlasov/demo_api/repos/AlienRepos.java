package com.vlasov.demo_api.repos;

import com.vlasov.demo_api.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestResource(collectionResourceRel = "aliens", path = "aliens")
public interface AlienRepos extends JpaRepository<Alien, Integer> {

}
