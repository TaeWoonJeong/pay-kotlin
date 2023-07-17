package com.jose.paykotlin.service

import com.jose.paykotlin.domain.Event
import com.jose.paykotlin.domain.EventTicket
import com.jose.paykotlin.logger
import com.jose.paykotlin.repository.EventRepository
import com.jose.paykotlin.repository.EventTicketRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val eventTicketRepository: EventTicketRepository
) {
    val log = logger()

    @Transactional
    fun createEvent(userId: String, email: String, ticketLimit: Long) {
        val event = eventRepository.save(
            Event(
                ticketLimit = ticketLimit
            )
        )
        log.info("${event.id} ${event.ticketLimit}")
    }

    @Transactional
    fun createEventTicketNoLock(eventId: Long, userId: String, email: String) {
        val event: Event = eventRepository.findById(eventId).orElseThrow()
        if (event.isClosed()) {
            throw RuntimeException("마감 되었습니다.")
        }
        val savedEventTicket = eventTicketRepository.save(EventTicket(event = event, userId = userId, email = email))
        log.info("${savedEventTicket.event} ${savedEventTicket.id}")
    }
}
