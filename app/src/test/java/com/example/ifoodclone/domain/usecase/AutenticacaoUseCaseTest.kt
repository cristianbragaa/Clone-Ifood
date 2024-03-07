package com.example.ifoodclone.domain.usecase

import com.example.ifoodclone.data.remote.firebase.repository.IAutenticacaoRepository
import com.example.ifoodclone.domain.model.Usuario
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AutenticacaoUseCaseTest {

    @Mock
    lateinit var repository: IAutenticacaoRepository

    private lateinit var autenticacaoUseCase: AutenticacaoUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        autenticacaoUseCase = AutenticacaoUseCase(repository)
    }

    @Test
    fun validarCadastroUsuario_validaDadosPreenchidosCorretamente_retornaVerdadeiro() {
        val usuario = Usuario("cristian", "cris@gmail.com", "123456", "996390664")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isTrue()
    }

    @Test
    fun validarCadastroUsuario_validaNomeInvalido_retornaFalso() {
        val usuario = Usuario("", "cris@gmail.com", "123456", "996390664")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isFalse()
    }

    @Test
    fun validarCadastroUsuario_validaEmailInvalido_retornaFalso() {
        val usuario = Usuario("cristian", "", "123456", "996390664")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isFalse()
    }

    @Test
    fun validarCadastroUsuario_validaSenhalInvalida_retornaFalso() {
        val usuario = Usuario("cristian", "cris@gmail.com", "", "996390664")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isFalse()
    }

    @Test
    fun validarCadastroUsuario_validaSenhalInvalidaMenorSeisCaracteres_retornaFalso() {
        val usuario = Usuario("cristian", "cris@gmail.com", "123", "996390664")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isFalse()
    }

    @Test
    fun validarCadastroUsuario_validaTelefonelInvalido_retornaFalso() {
        val usuario = Usuario("cristian", "cris@gmail.com", "123456", "")

        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)

        assertThat(resultadoAutenticacao.sucessoCadastro).isFalse()
    }

    @After
    fun tearDown() {
    }
}