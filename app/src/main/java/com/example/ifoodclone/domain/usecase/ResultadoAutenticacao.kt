package com.example.ifoodclone.domain.usecase

data class ResultadoAutenticacao(
    var nomeInvalido: Boolean = false,
    var emailInvalido: Boolean = false,
    var senhaInvalida: Boolean = false,
    var telefoneInvalido: Boolean = false
) {
    val sucessoCadastro: Boolean
        get() = !(nomeInvalido || emailInvalido || senhaInvalida || telefoneInvalido)

    val sucessoLogin: Boolean
        get() = !(emailInvalido || senhaInvalida)
}
