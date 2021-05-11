package com.cooskout.spendingcalculator.data.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.cooskout.spendingcalculator.data.dao.TaggedSmsDao
import com.cooskout.spendingcalculator.data.datasource.SmsDataSource
import com.cooskout.spendingcalculator.data.db.AppDatabase
import com.cooskout.spendingcalculator.data.repository.SmsRepository
import com.cooskout.spendingcalculator.presentation.viewmodels.FragmentPieChartViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentPieChartViewModelTest : ViewModel() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var smsRepository: SmsRepository
    private lateinit var smsDataSource: SmsDataSource
    private lateinit var viewModel: FragmentPieChartViewModel
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

        viewModel = FragmentPieChartViewModel(smsRepository)

    }

    @After
    fun tearDown() {
        appDatabase.clearAllTables()
        appDatabase.close()
    }


    @Test
    fun testNumberFormat() = runBlocking {
        val testFormat = viewModel.getformatedAmount(20000.00)
        ViewMatchers.assertThat(
            testFormat,
            equalTo("20,000.00")
        )

    }

}