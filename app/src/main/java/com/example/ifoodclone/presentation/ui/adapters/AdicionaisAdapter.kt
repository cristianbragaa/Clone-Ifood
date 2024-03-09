package com.example.ifoodclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ifoodclone.databinding.ItemRvAdicionalBinding
import com.example.ifoodclone.databinding.ItemRvCategoriasBinding
import com.example.ifoodclone.domain.model.Adicional
import com.example.ifoodclone.domain.model.Categoria
import com.squareup.picasso.Picasso

class AdicionaisAdapter : Adapter<AdicionaisAdapter.AdicionaisViewHolder>() {

    private var listaAdicionais = emptyList<Adicional>()

    fun adicionarListaAdicionais(lista: List<Adicional>) {
        listaAdicionais = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdicionaisViewHolder {
        val binding = ItemRvAdicionalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdicionaisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdicionaisViewHolder, position: Int) {
        holder.bind(listaAdicionais[position])
    }

    override fun getItemCount(): Int {
        return listaAdicionais.count()
    }


    inner class AdicionaisViewHolder(private val binding: ItemRvAdicionalBinding): RecyclerView.ViewHolder(binding.root) {

        private val titulo = binding.textAdicionalTitulo
        private val descricao = binding.textAdicionalDescricao
        private val preco = binding.textAdicionalPreco
        private val imagem = binding.imageAdicional

        fun bind(adicional: Adicional) {
            titulo.text = adicional.titulo
            descricao.text = adicional.descricao
            preco.text = adicional.preco

            if (adicional.urlImagem.isNotEmpty()) {
                Picasso.get()
                    .load(adicional.urlImagem)
                    .into(imagem)
            }
        }

    }

}