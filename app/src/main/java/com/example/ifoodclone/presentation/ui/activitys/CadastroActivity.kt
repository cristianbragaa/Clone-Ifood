package com.example.ifoodclone.presentation.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.ActivityCadastroBinding
import com.example.ifoodclone.domain.model.Usuario
import com.example.ifoodclone.helper.AlertaMensagem
import com.example.ifoodclone.helper.esconderTeclado
import com.example.ifoodclone.helper.exibirMensagemToast
import com.example.ifoodclone.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroBinding.inflate(layoutInflater) }
    private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

    private val alertaMensagem by lazy { AlertaMensagem(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        inicializarViews()
        inicializarListeners()
        inicializarObservables()
    }

    private fun inicializarViews() {
        inicializarToolbar()
    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeToolbar.toolbarPrincipal
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Cadastro de usuário"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun inicializarListeners() {
        with(binding) {
            btnCadastrar.setOnClickListener { view ->
                view.esconderTeclado()

                editCadastroNome.clearFocus()
                editCadastroEmail.clearFocus()
                editCadastroSenha.clearFocus()
                editCadastroTelefone.clearFocus()

                autenticacaoViewModel.cadastrarUsuario(
                    Usuario(
                        nome = editCadastroNome.text.toString(),
                        email = editCadastroEmail.text.toString(),
                        senha = editCadastroSenha.text.toString(),
                        telefone = editCadastroTelefone.text.toString()
                    )
                )
            }
        }
    }

    private fun inicializarObservables() {
        autenticacaoViewModel.validacao.observe(this) { rAutenticacao ->
            with(binding) {
                editCadastroNome.error = if (rAutenticacao.nomeInvalido)
                    getString(R.string.erro_nome_cadastro) else null
                editCadastroEmail.error = if (rAutenticacao.emailInvalido)
                    getString(R.string.erro_email_cadastro) else null
                editCadastroSenha.error = if (rAutenticacao.senhaInvalida)
                    getString(R.string.erro_senha_cadastro) else null
                editCadastroTelefone.error = if (rAutenticacao.telefoneInvalido)
                    getString(R.string.erro_telefone_cadastro) else null

            }
        }

        autenticacaoViewModel.carregando.observe(this) { carregando ->
            if (carregando) {
                alertaMensagem.exibirDialog("Cadastrando usuário")
            } else {
                alertaMensagem.fecharDialog()
            }
        }

        autenticacaoViewModel.sucesso.observe(this) { sucessoCadastro ->
            if (sucessoCadastro) {
                navegarTelaLogin()
                exibirMensagemToast("Sucesso ao cadastrar usuário")
            } else {
                exibirMensagemToast("Erro ao cadastrar usuário, verifique os requisitos para o cadastro")
            }
        }
    }

    private fun navegarTelaLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java)
        )
        finish()
    }

}