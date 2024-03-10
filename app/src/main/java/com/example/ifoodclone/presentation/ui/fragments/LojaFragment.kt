package com.example.ifoodclone.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifoodclone.domain.model.Produto
import com.example.ifoodclone.helper.TipoLayout
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.FragmentLojaBinding
import com.example.ifoodclone.presentation.ui.adapters.ProdutosAdapter
import kotlin.math.abs

class LojaFragment : Fragment() {

    private lateinit var binding: FragmentLojaBinding

    private lateinit var produtosDestaqueAdapter: ProdutosAdapter
    private lateinit var produtosAdapter: ProdutosAdapter

    private val listaProdutos = listOf(
        Produto(
            "03 PIZZAS DE 35CM + COCA-COLA 2,5L",
            "Peça uma pizza com até dois sabores + refrigerante Coca-Cola",
            "R$ 155,90",
            "R$ 100,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061640_35NL_i.jpg"
        ),
        Produto(
            "03 PIZZAS DE 35CM + FRUKI 2L",
            "Peça uma pizza com até dois sabores + refrigerante fruki",
            "R$ 151,50",
            "R$ 140,50",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061552_1HY2_i.jpg"
        ),
        Produto(
            "RAVIOLE VERDE AOS QUATRO QUEIJOS PARA 02 PESSOAS",
            "Raviole de massa verde recheado com ricota e nozes.",
            "R$ 115,00",
            "R$ 99,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/202003281118_Qlsw_p.jpg"
        ),
        Produto(
            "RISOTTO CALABRESE P/ 02 PESSOAS",
            "Arroz especial para risoto com linguiça calabresa fatiada.",
            "R$ 89,00",
            "R$ 80,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/201902201545_risot.jpg"
        ),
        Produto(
            "HAMBÚRGUER MOOMO",
            "Hambúrguer de carne bovina de 180g, pão brioche, cheddar, bacon, cebola crispy e o nosso sensacional molho La Vaca.",
            "R$ 19,99",
            "R$ 10,99",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/d2bd512b-3f9a-4b6e-a111-2f5d037ba18d/202402031601_5DY8_i.jpg"
        ),
        Produto(
            "CERVEJA LONG NECK EXTRA CORONA 330 ML",
            "Produto para maiores de 18 anos",
            "R$ 9,00",
            "R$ 5,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/820af392-002c-47b1-bfae-d7ef31743c7f/202306181621_1zbyb2moe2o.jpg"
        ),
        Produto(
            "CERVEJA HEINEKEN 330ML",
            "Produto para maiores de 18 anos",
            "R$ 9,00",
            "",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/820af392-002c-47b1-bfae-d7ef31743c7f/202210180607_gwjieubx17e.jpg"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLojaBinding.inflate(inflater, container, false)

        inicializarToolbar()
        inicializarRvProdutosDestaque()
        inicializarRvProdutos()

        return binding.root
    }

    private fun inicializarToolbar() {
        with(binding) {
            btnNavLojaVoltar.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }

            val compatActivity = (activity as AppCompatActivity)
            compatActivity.setSupportActionBar(collapsedToolbar)

            compatActivity.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.loja_pesquisa, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return true
                }
            }, viewLifecycleOwner)


            /* appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                 val navVertical = abs(verticalOffset)
                 if (navVertical >= appBarLayout.totalScrollRange) {
                     textNavLojaTitulo.text = "TotalScroll"
                 } else if (navVertical == 0) {
                     textNavLojaTitulo.text = "Zero"
                 }
             }*/
        }

        //navController.currentDestination?.label = ""
        //toolbar.setupWithNavController(navController)
        //NavigationUI.setupWithNavController(toolbar, navController)
    }

    private fun inicializarRvProdutosDestaque() {
        produtosDestaqueAdapter = ProdutosAdapter(TipoLayout.HORIZONTAL) {
            findNavController().navigate(R.id.produtoFragment)
        }
        produtosDestaqueAdapter.adicionarListaProdutos(listaProdutos)

        binding.rvProdutosDestaque.adapter = produtosDestaqueAdapter
        binding.rvProdutosDestaque.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
    }

    private fun inicializarRvProdutos() {
        produtosAdapter = ProdutosAdapter(TipoLayout.VERTICAL) {
            findNavController().navigate(R.id.produtoFragment)
        }
        produtosAdapter.adicionarListaProdutos(listaProdutos)

        binding.rvProdutos.adapter = produtosAdapter
        binding.rvProdutos.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )
    }

}