package com.example.ifoodclone.domain.usecase

import com.example.ifoodclone.data.remote.firebase.repository.IAutenticacaoRepository
import com.example.ifoodclone.domain.model.Usuario
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import javax.inject.Inject

class AutenticacaoUseCase @Inject constructor(
    private val iAutenticacaoRepository: IAutenticacaoRepository
) {

    fun validarCadastroUsuario(usuario: Usuario): ResultadoAutenticacao {
        val resultadoAutenticacao = ResultadoAutenticacao()

        if (!usuario.nome.nonEmpty())
            resultadoAutenticacao.nomeInvalido = true
        if (!usuario.email.validEmail())
            resultadoAutenticacao.emailInvalido = true

        val senha = usuario.senha.validator()
            .nonEmpty()
            .minLength(6)
            .check()

        if (!senha)
            resultadoAutenticacao.senhaInvalida = true
        if (!usuario.telefone.nonEmpty())
            resultadoAutenticacao.telefoneInvalido = true

        return resultadoAutenticacao
    }

    fun validarLoginUsuario(usuario: Usuario): ResultadoAutenticacao {
        val resultadoAutenticacao = ResultadoAutenticacao()

        if (!usuario.email.validEmail())
            resultadoAutenticacao.emailInvalido = true

        val senha = usuario.senha.validator()
            .nonEmpty()
            .minLength(6)
            .check()

        if (!senha)
            resultadoAutenticacao.senhaInvalida = true

        return resultadoAutenticacao
    }

    suspend fun cadastrarUsuario(usuario: Usuario): Boolean {
        return try {
            iAutenticacaoRepository.cadastrarUsuario(usuario)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun logarUsuario(usuario: Usuario): Boolean {
        return try {
            iAutenticacaoRepository.logarUsuario(usuario)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun usuarioEstaLogado(): Boolean {
        return try {
            iAutenticacaoRepository.usuarioEstaLogado()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}