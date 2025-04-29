package org.umeboshi.thud.game.gameplay;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.umeboshi.thud.game.entities.GameSession;

import java.util.UUID;

@Repository
public interface GameplayRepository extends CrudRepository<GameSession, UUID> {
}
