package com.joel.exchange.data

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.h2.api.Trigger
import java.sql.Connection
import java.sql.SQLException
import java.util.*

class CreateLogTrigger : Trigger {

    private var logger: Logger = LogManager.getLogger(CreateLogTrigger::class.java)
    @Throws(SQLException::class)
    override fun fire(conn: Connection, oldRow: Array<Any>?, newRow: Array<Any>?) {
        logger.info("Insert Trigger STARTED")
        if (newRow?.get(1) == null) {   // index number 1 means the column called "createAt"
            newRow?.set(1, Date())
        }
        logger.info("Insert Trigger ENDED")
    }
}