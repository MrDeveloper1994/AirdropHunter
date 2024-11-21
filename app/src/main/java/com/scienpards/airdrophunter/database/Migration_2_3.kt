package com.scienpards.airdrophunter.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL("""
            CREATE TABLE users_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                phone INTEGER NOT NULL,
                userId TEXT,
                userHash TEXT,
                goats TEXT NOT NULL
            )
        """)


        database.execSQL("""
            INSERT INTO users_new (id, phone, userId, userHash, goats)
            SELECT id, phone, userId, userHash, notPixel 
            FROM users
        """)


        database.execSQL("DROP TABLE users")


        database.execSQL("ALTER TABLE users_new RENAME TO users")
    }
}
