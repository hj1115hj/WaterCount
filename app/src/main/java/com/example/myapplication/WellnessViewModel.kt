package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class WellnessViewModel : ViewModel() {

    private val _tasks = MutableLiveData(getWellnessTasks())
    val tasks: LiveData<List<WellnessTask>>
        get() = _tasks

    fun remove(item: WellnessTask) {
        val newValue = _tasks.value?.toMutableList()
        newValue?.let {
            it.remove(item)
        }
        _tasks.postValue(newValue)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
        val newValue = _tasks.value?.toMutableList()
        val index = newValue?.indexOf(item)!!
        newValue.let {
            it[index] = item.copy(checked = checked)
        }
        _tasks.postValue(newValue)
    }

}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i", false) }