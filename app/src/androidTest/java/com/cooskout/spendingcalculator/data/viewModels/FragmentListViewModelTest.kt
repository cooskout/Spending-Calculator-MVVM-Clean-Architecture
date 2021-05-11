package com.cooskout.spendingcalculator.data.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.cooskout.spendingcalculator.data.dao.TaggedSmsDao
import com.cooskout.spendingcalculator.data.datasource.SmsDataSource
import com.cooskout.spendingcalculator.data.db.AppDatabase
import com.cooskout.spendingcalculator.data.entities.Tag
import com.cooskout.spendingcalculator.data.repository.SmsRepository
import com.cooskout.spendingcalculator.presentation.viewmodels.FragmentListViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FragmentListViewModelTest() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var smsRepository: SmsRepository
    private lateinit var smsDataSource: SmsDataSource
    private lateinit var viewModel: FragmentListViewModel
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

        viewModel = FragmentListViewModel(smsRepository)

    }

    @After
    fun tearDown() {
        appDatabase.clearAllTables()
        appDatabase.close()
    }

    @Test
    fun checkIfMessagedHasTag() = runBlocking {

        val addTag = Tag("New Test", "newIdAgain")

        taggedSmsDao.insertTag(addTag)

        assertThat(taggedSmsDao.getTagsWithId("newIdAgain")?.tag, equalTo(addTag.tag))
    }


    @Test
    fun checkIfMessagedHasTagWithNullValue() = runBlocking {

        assertThat(viewModel.checkIfMessagedHasTag("hh"), equalTo(null))
    }


}