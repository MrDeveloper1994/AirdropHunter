package com.scienpards.airdrophunter.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS logs (
                logId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                phoneNumber INTEGER NOT NULL,
                statusMessage TEXT NOT NULL,
                timestampRequest INTEGER NOT NULL,
                endpoint TEXT NOT NULL,
                timestampResponse INTEGER NOT NULL,
                statusCode INTEGER NOT NULL
            )
            """
        )
    }
}
