package org.moeawebframework.moeawebframework.controllers

import org.moeawebframework.moeawebframework.dto.QueueItemDTO
import org.moeawebframework.moeawebframework.dto.QueueItemResponseDTO
import org.moeawebframework.moeawebframework.exceptions.QueueItemNotFoundException
import org.moeawebframework.moeawebframework.services.PublicService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("public/queue")
@CrossOrigin
class PublicQueueController(
  private val publicService: PublicService
) {

  @PostMapping
  suspend fun addQueueItem(@Valid @RequestBody queueItemDTO: QueueItemDTO): Map<String, String> {
    return mapOf(Pair("rabbitId", publicService.addQueueItem(queueItemDTO)))
  }

  @GetMapping("{rabbitIds}")
  suspend fun getQueueItem(@PathVariable rabbitIds: Array<String>): ArrayList<QueueItemResponseDTO> {
    val queueItemResponseDTOs = ArrayList<QueueItemResponseDTO>()
    rabbitIds.forEach {
      val queueItem = publicService.getQueueItem(it) ?: return@forEach
      queueItemResponseDTOs.add(QueueItemResponseDTO(queueItem))
    }
    return queueItemResponseDTOs
  }

  @PutMapping("{rabbitId}")
  suspend fun startQueueItemProcessing(@PathVariable rabbitId: String) {
    publicService.startProcessing(rabbitId)
  }

  @PutMapping("cancel/{rabbitId}")
  suspend fun cancelQueueItemProcessing(@PathVariable rabbitId: String) {
    publicService.cancelProcessing(rabbitId)
  }

  @DeleteMapping("{rabbitId}")
  suspend fun deleteQueueItem(@PathVariable rabbitId: String) {
    publicService.deleteQueueItem(rabbitId)
  }

}