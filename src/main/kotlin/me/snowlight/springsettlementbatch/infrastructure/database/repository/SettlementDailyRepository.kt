package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository: JpaRepository<SettlementDaily, Long>