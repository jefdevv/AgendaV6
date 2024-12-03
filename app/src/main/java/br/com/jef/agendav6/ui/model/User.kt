package br.com.jef.agendav6.ui.model

import java.io.Serializable

class User(
    var name: String,
    val typeTel: String,
    val telephone: String,
    val birthDate: String,
    val gender: String
) : Serializable