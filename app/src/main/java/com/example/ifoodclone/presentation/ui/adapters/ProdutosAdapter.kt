package com.example.ifoodclone.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ifoodclone.databinding.ItemRvProdutoBinding
import com.example.ifoodclone.databinding.ItemRvProdutoDestaqueBinding
import com.example.ifoodclone.databinding.ItemRvUltimasLojasBinding
import com.example.ifoodclone.domain.model.Produto
import com.example.ifoodclone.helper.TipoLayout
import com.squareup.picasso.Picasso

class ProdutosAdapter(
    private val tipoLayout: TipoLayout,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    private var listaProdutos = emptyList<Produto>()

    fun adicionarListaProdutos(lista: List<Produto>) {
        listaProdutos = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (tipoLayout == TipoLayout.HORIZONTAL) {

            val bindingProdutosDestaque = ItemRvProdutoDestaqueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ProdutosDestaqueViewHolder(bindingProdutosDestaque)
        }

        val bindingProdutos = ItemRvProdutoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProdutosViewHolder(bindingProdutos)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = listaProdutos[position]
        when(holder) {
            is ProdutosAdapter.ProdutosDestaqueViewHolder -> holder.bindDestaquesProduto(produto)
            is ProdutosAdapter.ProdutosViewHolder -> holder.bindProduto(produto)
        }
    }

    override fun getItemCount(): Int {
        return listaProdutos.count()
    }

    inner class ProdutosDestaqueViewHolder(private val binding: ItemRvProdutoDestaqueBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imagem = binding.imageProdutoDestaque
        private val preco1 = binding.textPreco1Destaque
        private val preco2 = binding.textPreco2Destaque
        private val titulo = binding.textTituloDestaque

        fun bindDestaquesProduto(produto: Produto) {
            titulo.text = produto.titulo

            if (produto.precoDesconto.isNotEmpty()) {
                preco1.text = produto.precoDesconto
                preco2.text = produto.preco
            } else {
                preco1.text = produto.preco
            }

            if (produto.imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(produto.imageUrl)
                    .into(imagem)
            }

            binding.clickItemProdutoDestaque.setOnClickListener {
                onClick()
            }
        }
    }

    inner class ProdutosViewHolder(private val binding: ItemRvProdutoBinding ) : RecyclerView.ViewHolder(binding.root) {

        private val imagem = binding.imageProduto
        private val titulo = binding.textTituloProduto
        private val descricao = binding.textDescricaoProduto
        private val preco = binding.textPrecoProduto
        fun bindProduto(produto: Produto) {
            titulo.text = produto.titulo
            descricao.text = produto.descricao
            preco.text = produto.preco

            if (produto.imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(produto.imageUrl)
                    .into(imagem)
            }

            binding.clickItemProduto.setOnClickListener {
                onClick()
            }
        }
    }


}