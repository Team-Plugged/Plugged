package com.plugged.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.plugged.api.ApiHelper
import com.plugged.repository.PluggedRepository
import com.plugged.utils.NetWorkHelper

class PluggedViewModel @ViewModelInject constructor(
    private val repository: PluggedRepository,
    private val networkHelper: NetWorkHelper
) : ViewModel() {





}