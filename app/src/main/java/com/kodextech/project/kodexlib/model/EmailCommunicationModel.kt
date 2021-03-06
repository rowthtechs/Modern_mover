package com.kodextech.project.kodexlib.model

import java.io.Serializable

data class EmailCommunicationModel(
    var status: Boolean? = null,
    var message: String? = null,
    var data: ArrayList<Data> = arrayListOf()
) : Serializable

data class Data(
    var id: Int? = null,
    var Order: String? = null,
    var Name: String? = null,
    var Email: String? = null,
    var Date: String? = null,
    var Time: String? = null,
    var sent: String? = null
) : Serializable