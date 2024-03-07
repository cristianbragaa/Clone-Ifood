package com.example.ifoodclone.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun View.esconderTeclado() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    if (inputMethodManager.isAcceptingText) { //Teclado na tela (possível digitar um texto)
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.exibirTecladoEFoco() {
    if (requestFocus()) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.showSoftInput(this, 0)
    }
}

fun Activity.exibirMensagemToast(textoMensagem: String) {
    Toast.makeText(
        this,
        textoMensagem,
        Toast.LENGTH_LONG
    ).show()
}


