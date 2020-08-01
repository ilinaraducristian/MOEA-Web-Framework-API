package org.moeawebframework.moeawebframework.repositories

import org.moeawebframework.moeawebframework.entities.ProblemUser
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProblemUserRepository : R2dbcRepository<ProblemUser, Long> {

  fun findByUserId(userId: Long): Flux<ProblemUser>
  fun findByUserUsername(username: String): Flux<ProblemUser>

}