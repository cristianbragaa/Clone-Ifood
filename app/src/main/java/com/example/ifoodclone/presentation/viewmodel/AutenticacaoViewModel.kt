package com.example.ifoodclone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifoodclone.domain.model.Usuario
import com.example.ifoodclone.domain.usecase.AutenticacaoUseCase
import com.example.ifoodclone.domain.usecase.ResultadoAutenticacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
    private val autenticacaoUseCase: AutenticacaoUseCase
) : ViewModel() {

    private val _carregando = MutableLiveData<Boolean>()
    val carregando: LiveData<Boolean> = _carregando

    private val _resultadoValidacao = MutableLiveData<ResultadoAutenticacao>()
    val validacao: LiveData<ResultadoAutenticacao> = _resultadoValidacao

    private val _sucesso = MutableLiveData<Boolean>()
    val sucesso: LiveData<Boolean> = _sucesso

    private val _usuarioEstaLogado = MutableLiveData<Boolean>()
    val usuarioEstaLogado: LiveData<Boolean> = _usuarioEstaLogado

    fun usuarioEstaLogado() {
        viewModelScope.launch(Dispatchers.IO) {
            val retorno = autenticacaoUseCase.usuarioEstaLogado()
            if (retorno) {
                _carregando.postValue(true)
            }
            _usuarioEstaLogado.postValue(retorno)
            _carregando.postValue(false)
        }
    }

    fun logarUsuario(usuario: Usuario) {
        val resultadoAutenticacao = autenticacaoUseCase.validarLoginUsuario(usuario)
        _resultadoValidacao.value = resultadoAutenticacao

        if (resultadoAutenticacao.sucessoLogin) {
            _carregando.postValue(true)
            viewModelScope.launch(Dispatchers.IO) {
                val retornoLogin = autenticacaoUseCase.logarUsuario(usuario)
                _sucesso.postValue(retornoLogin)
                _carregando.postValue(false)

            }
        }
    }

    fun cadastrarUsuario(usuario: Usuario) {
        val resultadoAutenticacao = autenticacaoUseCase.validarCadastroUsuario(usuario)
        _resultadoValidacao.value = resultadoAutenticacao

        if (resultadoAutenticacao.sucessoCadastro) {
            _carregando.postValue(true)
            viewModelScope.launch(Dispatchers.IO) {
                val retornoCadastro = autenticacaoUseCase.cadastrarUsuario(usuario)
                _sucesso.postValue(retornoCadastro)
                _carregando.postValue(false)

            }
        }
    }
}