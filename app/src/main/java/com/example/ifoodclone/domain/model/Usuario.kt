package com.example.ifoodclone.domain.model

data class Usuario(
    val nome: String = "",
    val email: String,
    val senha: String,
    val telefone: String = ""
)
