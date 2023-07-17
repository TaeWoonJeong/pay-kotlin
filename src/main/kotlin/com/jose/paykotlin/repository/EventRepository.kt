package com.jose.paykotlin.repository

import com.jose.paykotlin.domain.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long>
