package com.nickgua.demo1.common

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickgua.demo1.DemoApplication

abstract class BaseAndroidViewModel(
    application: Application = DemoApplication.appContext as Application
) : AndroidViewModel(application) {

    private val mContext: Context = application.applicationContext

    private val _pageStatus = MutableLiveData<PageStatus>()
    val pageStatus: LiveData<PageStatus> = _pageStatus

    val pageEvent = SingleLiveEvent<PageEvent>()

    abstract fun refresh()

    protected fun setPageStatus(state: PageStatus) {
        _pageStatus.value = state
    }

    protected fun setPageEvent(event: PageEvent) {
        pageEvent.value = event
    }

    protected fun getResString(@StringRes id: Int): String {
        return mContext.getString(id)
    }

    protected fun postEvent(event: PageEvent) {
        pageEvent.postValue(event)
    }
}