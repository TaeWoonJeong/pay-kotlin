package com.jose.paykotlin.controller

import com.jose.paykotlin.request.event.EventRequest
import com.jose.paykotlin.request.user.UserRequest
import com.jose.paykotlin.service.EventService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
) {
    @PostMapping("/events")
    fun createEvent(@RequestBody eventRequest: EventRequest) {
        eventService.createEvent(eventRequest.userId, eventRequest.email, eventRequest.ticketLimit)
    }

    @PostMapping("/events/nolock/{eventId}")
    fun createEventTicketNoLock(@PathVariable eventId: Long, @RequestBody userRequest: UserRequest) {
        eventService.createEventTicketNoLock(eventId, userRequest.userId, userRequest.email)
    }
}
