package org.moeawebframework.moeawebframework.dao

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface Dao<T> {

  fun get(id: Long): Mono<T>

  fun getAll(): Flux<T>

  fun save(t: T): Mono<T>

  fun update(t: T, fields: HashMap<String, Any?>): Mono<Void>

  fun delete(t: T): Mono<Void>

}