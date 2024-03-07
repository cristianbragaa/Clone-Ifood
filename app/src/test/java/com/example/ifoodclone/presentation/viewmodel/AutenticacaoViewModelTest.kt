package com.example.ifoodclone.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ifoodclone.domain.model.Usuario
import com.example.ifoodclone.domain.usecase.AutenticacaoUseCase
import com.example.ifoodclone.domain.usecase.ResultadoAutenticacao
import com.example.ifoodclone.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AutenticacaoViewModelTest {

    /*
    * InstantTaskExecutorRule() ->Troca o executor de segundo plano usado
    * pelos componentes de arquitetura  por outro diferente que executa
    * cada tarefa de forma síncrona, mais indicado e recomendado para testes.
    * */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var autenticacaoViewModel: AutenticacaoViewModel

    @Mock
    lateinit var autenticacaoUseCase: AutenticacaoUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        autenticacaoViewModel = AutenticacaoViewModel(autenticacaoUseCase)
    }

    @Test
    fun cadastrarUsuario_cadastraUsuarioFirebase_retornaVerdadeiro() = runTest {
        val usuario = Usuario("cristian", "cris@gmail.com", "123456")

        Mockito.`when`(autenticacaoUseCase.validarCadastroUsuario(usuario)).thenReturn(
            ResultadoAutenticacao()
        )
        Mockito.`when`(autenticacaoUseCase.cadastrarUsuario(usuario)).thenReturn(
            true
        )
        autenticacaoViewModel.cadastrarUsuario(usuario)

        //assert
        val retorno = autenticacaoViewModel.sucesso.getOrAwaitValue()
        assertThat(retorno).isTrue()
    }

    @Test
    fun logarUsuario_logaUsuarioFirebase_retornaVerdadeiro() = runTest {
        val usuario = Usuario("cristian", "cris@gmail.com", "123456")

        Mockito.`when`(autenticacaoUseCase.validarLoginUsuario(usuario)).thenReturn(
            ResultadoAutenticacao()
        )
        Mockito.`when`(autenticacaoUseCase.logarUsuario(usuario)).thenReturn(
            true
        )
        autenticacaoViewModel.logarUsuario(usuario)

        //assert
        val retorno = autenticacaoViewModel.sucesso.getOrAwaitValue()
        assertThat(retorno).isTrue()
    }

    @After
    fun tearDown() {
    }
}