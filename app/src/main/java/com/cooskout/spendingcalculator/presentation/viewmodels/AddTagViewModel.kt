package com.cooskout.spendingcalculator.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooskout.spendingcalculator.data.entities.Tag
import com.cooskout.spendingcalculator.data.repository.SmsRepository
import com.cooskout.spendingcalculator.domain.Message
import com.cooskout.spendingcalculator.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddTagViewModel @ViewModelInject constructor(private val smsRepository: SmsRepository) :
    ViewModel() {

    private val _tagAdded = MutableLiveData<Event<String>>()
    val tagAdded: LiveData<Event<String>>
        get() = _tagAdded
    var tag: String? = null
    var message: Message? = null

    fun saveTags() {

        if (tag.isNullOrEmpty()) return

        if (tag?.trim()!!.isEmpty()) return

        viewModelScope.launch(Dispatchers.Default) {
            val newRowId = smsRepository.insertTaggedMessageId(
                Tag(
                    tag!!,
                    message!!.id, message!!
                )
            )
            withContext(Dispatchers.Main) {
                if (newRowId > -1) {
                    _tagAdded.value =
                        Event("Tag Added Successfully")
                } else {
                    _tagAdded.value =
                        Event("Error Occurred")
                }
            }
        }

    }


}