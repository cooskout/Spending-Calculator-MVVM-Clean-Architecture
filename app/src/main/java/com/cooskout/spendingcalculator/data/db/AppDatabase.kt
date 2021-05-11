package com.cooskout.spendingcalculator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cooskout.spendingcalculator.data.dao.TaggedSmsDao
import com.cooskout.spendingcalculator.data.entities.Tag
import com.cooskout.spendingcalculator.utils.DATABASE_NAME


@Database(
    entities = [Tag::class],
    version = 3
)

abstract class AppDatabase : RoomDatabase() {

    abstract val taggedSmsDao: TaggedSmsDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
    }

}