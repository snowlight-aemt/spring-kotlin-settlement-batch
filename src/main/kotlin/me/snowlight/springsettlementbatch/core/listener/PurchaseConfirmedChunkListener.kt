package me.snowlight.springsettlementbatch.core.listener

import org.slf4j.LoggerFactory
import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.scope.context.ChunkContext

class PurchaseConfirmedChunkListener: ChunkListener {
    private val log = LoggerFactory.getLogger(PurchaseConfirmedChunkListener::class.java)

    override fun beforeChunk(context: ChunkContext) {
        log.error("배치 진행 전 : ${context.toString()}")
        super.beforeChunk(context)
    }

    override fun afterChunk(context: ChunkContext) {
        log.error("배치 진행 후 : ${context.toString()}")
        super.afterChunk(context)
    }

    override fun afterChunkError(context: ChunkContext) {
        log.error("배치 진행 중 에러가 발생하여 Listener 가 호출된다. : ${context.toString()}")
        super.afterChunkError(context)
    }
}