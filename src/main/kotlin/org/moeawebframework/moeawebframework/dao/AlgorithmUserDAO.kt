package org.moeawebframework.moeawebframework.dao

import org.moeawebframework.moeawebframework.entities.AlgorithmUser
import org.moeawebframework.moeawebframework.entities.ProblemUser
import org.moeawebframework.moeawebframework.repositories.AlgorithmUserRepository
import org.moeawebframework.moeawebframework.repositories.ProblemUserRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class AlgorithmUserDAO(
    private val algorithmUserRepository: AlgorithmUserRepository
) : Dao<AlgorithmUser> {
  override fun get(id: Long): Mono<AlgorithmUser> {
    return algorithmUserRepository.findById(id)
  }

  override fun getAll(): Flux<AlgorithmUser> {
    return algorithmUserRepository.findAll()
  }

  override fun save(t: AlgorithmUser): Mono<AlgorithmUser> {
    return algorithmUserRepository.save(t)
  }

  override fun update(t: AlgorithmUser, fields: HashMap<String, Any?>): Mono<Void> {
    return Mono.empty()
  }

  override fun delete(t: AlgorithmUser): Mono<Void> {
    return algorithmUserRepository.delete(t)
  }

  fun getByUserId(userId: Long): Mono<AlgorithmUser> {
    return algorithmUserRepository.findByUserId(userId)
  }

//  fun existsByMd5(sha256: String): Boolean {
////    return problemUserRepository.existsByMd5(sha256)
//    return true
//  }

//  fun findBySha256(sha256: String): Mono<ProblemUser> {
//    return problemUserRepository.findBySha256(sha256)
//  }

}