package org.moeawebframework.moeawebframework.controllers

import org.moeawebframework.moeawebframework.services.PublicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("public")
class PublicController(
    private val publicService: PublicService
) {

  @GetMapping
  suspend fun getUserData(): Map<String, List<Any>> {
    return publicService.getDefaultData()
  }

}