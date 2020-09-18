package org.moeawebframework.moeawebframework.repositories

import org.moeawebframework.moeawebframework.entities.Problem
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface ProblemRepository : R2dbcRepository<Problem, Long> {

  fun findByProblemSha256(problemSha256: String): Mono<Problem>

  fun existsByProblemSha256(problemSha256: String): Boolean

}