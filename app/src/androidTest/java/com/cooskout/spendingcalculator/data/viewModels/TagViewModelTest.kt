package com.cooskout.spendingcalculator.data.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.cooskout.spendingcalculator.data.dao.TaggedSmsDao
import com.cooskout.spendingcalculator.data.datasource.SmsDataSource
import com.cooskout.spendingcalculator.data.db.AppDatabase
import com.cooskout.spendingcalculator.data.repository.SmsRepository
import com.cooskout.spendingcalculator.data.testUtils.getValueForEvents
import com.cooskout.spendingcalculator.presentation.viewmodels.AddTagViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TagViewModelTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var smsRepository: SmsRepository
    private lateinit var smsDataSource: SmsDataSource
    private lateinit var viewModel: AddTagViewModel
    private lateinit var taggedSmsDao: TaggedSmsDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        taggedSmsDao = appDatabase.taggedSmsDao

        smsDataSource = SmsDataSource(context)
        smsRepository = SmsRepository(smsDataSource, taggedSmsDao)

        viewModel = AddTagViewModel(smsRepository)

    }

    @After
    fun tearDown() {
        appDatabase.clearAllTables()
        appDatabase.close()
    }

    @Test
    fun saveTagTest() = runBlocking {
        viewModel.tag = "New Ronnie Message"
        viewModel.id = "newIdTest"

        viewModel.saveTags()

        ViewMatchers.assertThat(
            getValueForEvents(viewModel.message),
            equalTo("Tag Added Successfully")
        )
    }


}