package com.example.ifoodclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ifoodclone.databinding.ItemRvLojasBinding
import com.example.ifoodclone.databinding.ItemRvUltimasLojasBinding
import com.example.ifoodclone.domain.model.Loja
import com.example.ifoodclone.helper.TipoLayout
import com.squareup.picasso.Picasso

class LojasAdapter(
    private val tipoLayout: TipoLayout,
    private val onClick: () -> Unit
): RecyclerView.Adapter<ViewHolder>() {

    private var listaLojas = emptyList<Loja>()

    fun adicionarLista(lista: List<Loja>) {
        listaLojas = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (tipoLayout == TipoLayout.HORIZONTAL) {
            val bindingUltimasLojas = ItemRvUltimasLojasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UltimasLojasViewHolder(bindingUltimasLojas)
        } else {
            val bindingLojas = ItemRvLojasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LojasViewHolder(bindingLojas)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loja = listaLojas[position]
        when(holder) {
            is UltimasLojasViewHolder -> holder.bind(loja)
            is LojasViewHolder -> holder.bind(loja)
        }
    }

    override fun getItemCount() = listaLojas.count()

    inner class UltimasLojasViewHolder(private val binding: ItemRvUltimasLojasBinding): ViewHolder(binding.root) {

        private var ultimasLojasNome = binding.textUltimaLojaNome
        private var ultimasLojasImagem = binding.imageUltimaLoja
        private val clItemUltimasLojas = binding.clItemUltimasLojas

        fun bind(loja: Loja) {
            ultimasLojasNome.text = loja.nome

            if (loja.foto.isNotEmpty()) {
                Picasso.get()
                    .load(loja.foto)
                    .into(ultimasLojasImagem)
            }

            clItemUltimasLojas.setOnClickListener {
                onClick()
            }
        }
    }

    inner class LojasViewHolder(private val binding: ItemRvLojasBinding): RecyclerView.ViewHolder(binding.root) {

        private var lojasNome = binding.textLojaNome
        private var lojasImagem = binding.imageLojaPerfil
        private val clItemLojas = binding.clItemLojas

        fun bind(loja: Loja) {
            lojasNome.text = loja.nome
            clItemLojas.setOnClickListener {
                onClick()
            }

            if (loja.foto.isNotEmpty()) {
                Picasso.get()
                    .load(loja.foto)
                    .into(lojasImagem)
            }
        }
    }

}