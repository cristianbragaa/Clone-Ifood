package com.example.ifoodclone.presentation.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.ActivityLoginBinding
import com.example.ifoodclone.domain.model.Usuario
import com.example.ifoodclone.helper.AlertaMensagem
import com.example.ifoodclone.helper.esconderTeclado
import com.example.ifoodclone.helper.exibirMensagemToast
import com.example.ifoodclone.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

    private val alertaMensagem by lazy { AlertaMensagem(this@LoginActivity)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        supportActionBar?.hide()

        //Verifica usuario logado
        autenticacaoViewModel.usuarioEstaLogado()

        inicializarListeners()
        inicializarObservables()
    }

    private fun inicializarListeners() {
        with(binding) {
            textLoginCadastro.setOnClickListener {
                startActivity(
                    Intent(applicationContext, CadastroActivity::class.java)
                )
            }

            btnEntrar.setOnClickListener { view ->
                view.esconderTeclado()

                editLoginEmail.clearFocus()
                editLoginSenha.clearFocus()

                autenticacaoViewModel.logarUsuario(
                    Usuario(
                        email = editLoginEmail.text.toString(),
                        senha = editLoginSenha.text.toString()
                    )
                )
            }
        }
    }

    private fun inicializarObservables() {
        autenticacaoViewModel.usuarioEstaLogado.observe(this) { usuarioLogado ->
            if (usuarioLogado) {
                navegarTelaPrincipal()
            }
        }

        autenticacaoViewModel.validacao.observe(this) { rAutenticacao ->
            with(binding) {
                editLoginEmail.error = if (rAutenticacao.emailInvalido)
                    getString(R.string.erro_email_login) else null
                editLoginSenha.error = if (rAutenticacao.senhaInvalida)
                    getString(R.string.erro_senha_login) else null
            }
        }

        autenticacaoViewModel.carregando.observe(this) { carregando ->
            if (carregando) {
                alertaMensagem.exibirDialog("Efetuando login de usuário")
            } else {
                alertaMensagem.fecharDialog()
            }
        }

        autenticacaoViewModel.sucesso.observe(this) { sucessoLogin ->
            if (sucessoLogin) {
                navegarTelaPrincipal()
                exibirMensagemToast("Sucesso ao logar usuário")
            } else {
                exibirMensagemToast("E-mail e senha inválidos, preencha novamente")
                limparCampos()
            }
        }
    }

    private fun limparCampos() {
        binding.editLoginEmail.setText("")
        binding.editLoginSenha.setText("")
    }

    private fun navegarTelaPrincipal() {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
        finish()
    }
}