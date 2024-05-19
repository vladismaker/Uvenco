package com.application.uvencotest.viewmodels

import androidx.lifecycle.ViewModel
import com.application.uvencotest.dagger.Clock
import com.application.uvencotest.dagger.TemperatureProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.uvencotest.App
import com.application.uvencotest.data.DataItemCard
import com.application.uvencotest.data.DataProvider
import com.application.uvencotest.repositories.RepositoryCupsImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

class MainViewModel @Inject constructor(
    private val clock: Clock,
    private val temperatureProvider: TemperatureProvider
) : ViewModel() {
    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String> = _currentTime

    private val _currentTemperature = MutableLiveData<String>()
    val currentTemperature: LiveData<String> = _currentTemperature

    val liveDataEmpty = MutableLiveData<Boolean>()
    val liveDataArrayCups = MutableLiveData<List<DataItemCard>>()
    val liveDataItemCard = MutableLiveData<DataItemCard>()

    private val notesRepository = RepositoryCupsImpl.getInstance()

    // Получение текущего времени
    private fun getTime(): String {
        val currentTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(currentTime)
    }

    init {
        val timerScope = CoroutineScope(Dispatchers.IO)

        timerScope.launch {
            val timerTime = fixedRateTimer(period = 3000) {
                _currentTime.postValue(clock.getCurrentTime())
            }

            val timerTemperature = fixedRateTimer(period = 1000) {
                _currentTemperature.postValue(temperatureProvider.getRandomTemperature())
            }
            
            viewModelScope.coroutineContext[Job]!!.invokeOnCompletion {
                timerTime.cancel()
                timerTemperature.cancel()
            }
        }
    }

    //Добавляем данные, если их нет
    //Если есть отображаем список
    fun setDataInArrayCupsList(){

        App.ioScope.launch{
            val dataList = async { notesRepository.getRepositoryArrayCups() }.await()
            if (dataList.isEmpty()){
                liveDataEmpty.postValue(true)
                val next = async {notesRepository.addRepositoryArrayCupsList(DataProvider.beginCupsList)}.await()

                if (next){
                    val dataAgainList = async { notesRepository.getRepositoryArrayCups() }.await()
                    if (dataAgainList.isEmpty()){
                        liveDataEmpty.postValue(true)
                    }else{
                        liveDataEmpty.postValue(false)
                        liveDataArrayCups.postValue(dataAgainList)
                        liveDataItemCard.postValue(dataAgainList[0])
                    }

                }
            }else{
                liveDataArrayCups.postValue(dataList)
                liveDataItemCard.postValue(dataList[0])
                liveDataEmpty.postValue(false)
            }
        }
    }

    //Данные поменялись
    //Меняем список данных
    fun saveDataChange(dataItemCard: DataItemCard){
        App.ioScope.launch{
            val arraySize = liveDataArrayCups.value?.size
            val newCupsList: MutableList<DataItemCard> = mutableListOf()
            if (arraySize!= null){
                for (i in 0 until arraySize){
                    newCupsList.add(DataItemCard((i+1), dataItemCard.title, dataItemCard.imageId, dataItemCard.volume, dataItemCard.price))
                }
            }
            notesRepository.repositoryDeleteAllArrayCupsList()

            val next = async {notesRepository.addRepositoryArrayCupsList(newCupsList)}.await()

            if (next){
                val dataAgainList = async { notesRepository.getRepositoryArrayCups() }.await()
                if (dataAgainList.isEmpty()){
                    liveDataEmpty.postValue(true)
                }else{
                    liveDataArrayCups.postValue(dataAgainList)
                    liveDataItemCard.postValue(dataAgainList[0])
                    liveDataEmpty.postValue(false)
                }

            }
        }
    }
}