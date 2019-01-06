package br.ufrpe.loftapp2.credentials

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var userId: String,
    var name: String?,
    var phone: String?,
    var email: String?,
    var password: String?
)