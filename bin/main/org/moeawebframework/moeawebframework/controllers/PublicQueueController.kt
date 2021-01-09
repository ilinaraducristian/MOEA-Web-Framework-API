package org.moeawebframework.moeawebframework.controllers

import org.moeawebframework.moeawebframework.dto.QueueItemDTO
import org.moeawebframework.moeawebframework.dto.QueueItemResponseDTO
import org.moeawebframework.moeawebframework.exceptions.QueueItemNotFoundException
import org.moeawebframework.moeawebframework.services.PublicService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("public/queue")
class PublicQueueController(
    private val publicService: PublicService
) {

  @PostMapping
  suspend fun addQueueItem(@Valid @RequestBody queueItemDTO: QueueItemDTO): String {
    return publicService.addQueueItem(queueItemDTO)
  }

  @GetMapping("{rabbitId}")
  suspend fun getQueueItem(@PathVariable rabbitId: Array<String>): QueueItemResponseDTO {
    val queueItem = publicService.getQueueItem(rabbitId)
        ?: throw RuntimeException(QueueItemNotFoundException)
    return QueueItemResponseDTO(queueItem)
  }

  @PostMapping("getQueueItems")
  suspend fun getQueueItems(@RequestBody rabbitIds: Array<String>): ArrayList<QueueItemResponseDTO> {
    val queue = ArrayList<QueueItemResponseDTO>()
    rabbitIds.forEach {
      val queueItem = publicService.getQueueItem(it) ?: return@forEach
      queue.add(QueueItemResponseDTO(queueItem))
    }
    return queue
  }

  @PostMapping("{rabbitId}")
  suspend fun startQueueItemProcessing(@PathVariable rabbitId: String) {
    publicService.startProcessing(rabbitId)
  }

  @PostMapping("cancel/{rabbitId}")
  suspend fun cancelQueueItemProcessing(@PathVariable rabbitId: String) {
    publicService.cancelProcessing(rabbitId)
  }

  @DeleteMapping("{rabbitId}")
  suspend fun deleteQueueItem(@PathVariable rabbitId: String) {
    publicService.deleteQueueItem(rabbitId)
  }

}