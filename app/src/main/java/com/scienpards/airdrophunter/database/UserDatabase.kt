package com.scienpards.airdrophunter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scienpards.airdrophunter.dao.LogDao
import com.scienpards.airdrophunter.dao.UserDao
import com.scienpards.airdrophunter.models.Log
import com.scienpards.airdrophunter.models.UserModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Database(entities = [UserModel::class,Log::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun logDao(): LogDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePassphrase(): SupportFactory {
        val passphrase: ByteArray =
            net.sqlcipher.database.SQLiteDatabase.getBytes("your_secure_passphrase".toCharArray())
        return SupportFactory(passphrase)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        factory: SupportFactory
    ): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "encrypted_users.db"
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideLogDao(database: UserDatabase): LogDao {
        return database.logDao()
    }
}
