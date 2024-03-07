package com.example.ifoodclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ifoodclone.databinding.ItemRvCategoriasBinding
import com.example.ifoodclone.domain.model.Categoria
import com.squareup.picasso.Picasso

class CategoriasAdapter : Adapter<CategoriasAdapter.CategoriaViewHolder>() {

    private var listaCategorias = emptyList<Categoria>()

    fun adicionarListaCategorias(lista: List<Categoria>) {
        listaCategorias = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val binding = ItemRvCategoriasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.bind(listaCategorias[position])
    }

    override fun getItemCount(): Int {
        return listaCategorias.count()
    }


    inner class CategoriaViewHolder(private val binding: ItemRvCategoriasBinding): RecyclerView.ViewHolder(binding.root) {

        private val textCategoriaNome = binding.textCategoriaNome
        private val imagemCategoria = binding.imageCategoria

        fun bind(categoria: Categoria) {
            textCategoriaNome.text = categoria.nome

            if (categoria.urlImagem.isNotEmpty()) {
                Picasso.get()
                    .load(categoria.urlImagem)
                    .into(imagemCategoria)
            }
        }

    }

}