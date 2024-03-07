package com.example.ifoodclone.helper

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.ifoodclone.R

class AlertaMensagem(private val activity: Activity) {
    private var alertDialog: AlertDialog? = null

    //Converter xml para view
    private val view = activity.layoutInflater.inflate(
        R.layout.layout_carregando, null
    )

    fun exibirDialog(textoMensagem: String) {
        val alertBuilder = AlertDialog.Builder(activity)
            .setTitle(textoMensagem)
            .setView(view)
            .setCancelable(false)

        alertDialog = alertBuilder.create()
        alertDialog?.show()
    }

    fun fecharDialog() {
        alertDialog?.dismiss()
    }
}

