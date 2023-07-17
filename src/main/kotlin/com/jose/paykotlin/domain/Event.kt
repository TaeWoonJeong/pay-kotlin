package com.jose.paykotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val ticketLimit: Long,

    @OneToMany(mappedBy = "event")
    val eventTickets: List<EventTicket> = mutableListOf()
) {
    fun isClosed(): Boolean {
        return eventTickets.size >= ticketLimit
    }
}
