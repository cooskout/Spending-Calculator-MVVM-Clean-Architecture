package com.cooskout.spendingcalculator.data.repository

import androidx.lifecycle.MutableLiveData
import com.cooskout.spendingcalculator.data.dao.TaggedSmsDao
import com.cooskout.spendingcalculator.data.datasource.SmsDataSource
import com.cooskout.spendingcalculator.data.entities.Tag
import com.cooskout.spendingcalculator.domain.SmsData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmsRepository @Inject constructor(
    private val smsDataSource: SmsDataSource,
    private val taggedSmsDao: TaggedSmsDao
) {
    init {
        smsDataSource.getSms()
    }

    fun getSmsLiveData(): MutableLiveData<SmsData> {
        smsDataSource.getSms()
        return smsDataSource.smsLiveData
    }

    suspend fun checkIfMessageIsSaved(id: String) = taggedSmsDao.getTagsWithId(id)?.tag
    suspend fun insertTaggedMessageId(tag: Tag) = taggedSmsDao.insertTag(tag)
    suspend fun getTaggedMessages(tagString: String) = taggedSmsDao.getTags(tagString)


}