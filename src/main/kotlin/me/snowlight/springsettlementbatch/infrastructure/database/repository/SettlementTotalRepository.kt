package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementTotal
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementTotalRepository: JpaRepository<SettlementTotal, Long>