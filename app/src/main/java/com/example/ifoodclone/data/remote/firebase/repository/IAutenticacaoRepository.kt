package com.example.ifoodclone.data.remote.firebase.repository

import com.example.ifoodclone.domain.model.Usuario

interface IAutenticacaoRepository {
    suspend fun cadastrarUsuario(usuario: Usuario): Boolean
    suspend fun logarUsuario(usuario: Usuario): Boolean
    suspend fun usuarioEstaLogado(): Boolean
}