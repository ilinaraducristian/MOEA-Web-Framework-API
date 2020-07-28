package org.moeawebframework.moeawebframework.controllers

import org.moeawebframework.moeawebframework.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("problem")
class ProblemController(
    private val userService: UserService
) {

  @GetMapping
  fun upload() {
//    userService.uploadProblem(problem)
  }

}