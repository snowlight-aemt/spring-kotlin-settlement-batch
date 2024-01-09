package me.snowlight.springsettlementbatch.interfaces.controller

import me.snowlight.springsettlementbatch.application.ClaimCompleteFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/claim/complete")
class ClaimCompleteController(
    private val claimCompleteFacade: ClaimCompleteFacade
) {

    @PutMapping("/claim_no/{claimNo}")
    fun completedOrder(@PathVariable claimNo: Long) {
        claimCompleteFacade.complete(claimNo)
    }
}