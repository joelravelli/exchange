package com.joel.exchange.data

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.h2.api.Trigger
import java.sql.Connection
import java.sql.SQLException
import java.util.*

class UpdateLogTrigger : Trigger {

    private var logger: Logger = LogManager.getLogger(UpdateLogTrigger::class.java)
    @Throws(SQLException::class)
    override fun fire(conn: Connection, oldRow: Array<Any>?, newRow: Array<Any>?) {
        logger.info("Update Trigger STARTED")
        if (newRow?.get(4) == null) {   // index number 1 means the column called "updateAt"
            newRow?.set(4, Date())
        }
        logger.info("Update Trigger ENDED")
    }
}