package com.skilldistillery.jpavideostore.data;

import com.skilldistillery.jpavideostore.entities.Actor;

public interface ActorDAO {
	Actor create(Actor actor);
	Actor update(int actorId, Actor actor);
	boolean destroy(int id);
}