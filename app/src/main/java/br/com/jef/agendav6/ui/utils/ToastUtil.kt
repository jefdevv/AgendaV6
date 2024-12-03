package br.com.jef.agendav6.ui.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun mostrarMensagem(context: Context, mensagem: String, duracao: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, mensagem, duracao).show()
    }

}