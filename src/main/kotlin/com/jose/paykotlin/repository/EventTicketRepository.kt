package com.jose.paykotlin.repository

import com.jose.paykotlin.domain.EventTicket
import org.springframework.data.jpa.repository.JpaRepository

interface EventTicketRepository : JpaRepository<EventTicket, Long>
