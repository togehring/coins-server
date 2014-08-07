package de.tgehring.coins.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tgehring.coins.server.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
