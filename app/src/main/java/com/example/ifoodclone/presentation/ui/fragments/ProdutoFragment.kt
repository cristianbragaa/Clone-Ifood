package com.example.ifoodclone.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.FragmentProdutoBinding
import com.example.ifoodclone.domain.model.Adicional
import com.example.ifoodclone.domain.model.Produto
import com.example.ifoodclone.presentation.ui.adapters.AdicionaisAdapter

class ProdutoFragment : Fragment() {

    private lateinit var binding: FragmentProdutoBinding

    private lateinit var adicionaisAdapter: AdicionaisAdapter

    private val listaAdicionais = listOf(
        Adicional(
            "03 PIZZAS DE 35CM + COCA-COLA 2,5L",
            "Peça uma pizza com até dois sabores + refrigerante Coca-Cola",
            "R$ 155,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061640_35NL_i.jpg"
        ),
        Adicional(
            "03 PIZZAS DE 35CM + FRUKI 2L",
            "Peça uma pizza com até dois sabores + refrigerante fruki",
            "R$ 151,50",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061552_1HY2_i.jpg"
        ),
        Adicional(
            "RAVIOLE VERDE AOS QUATRO QUEIJOS PARA 02 PESSOAS",
            "Raviole de massa verde recheado com ricota e nozes.",
            "R$ 115,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/202003281118_Qlsw_p.jpg"
        ),
        Adicional(
            "RISOTTO CALABRESE P/ 02 PESSOAS",
            "Arroz especial para risoto com linguiça calabresa fatiada.",
            "R$ 89,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/201902201545_risot.jpg"
        ),
        Adicional(
            "HAMBÚRGUER MOOMO",
            "Hambúrguer de carne bovina de 180g, pão brioche, cheddar, bacon, cebola crispy e o nosso sensacional molho La Vaca.",
            "R$ 19,99",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/d2bd512b-3f9a-4b6e-a111-2f5d037ba18d/202402031601_5DY8_i.jpg"
        ),
        Adicional(
            "CERVEJA LONG NECK EXTRA CORONA 330 ML",
            "Produto para maiores de 18 anos",
            "R$ 9,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/820af392-002c-47b1-bfae-d7ef31743c7f/202306181621_1zbyb2moe2o.jpg"
        ),
        Adicional(
            "CERVEJA HEINEKEN 330ML",
            "Produto para maiores de 18 anos",
            "R$ 9,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/820af392-002c-47b1-bfae-d7ef31743c7f/202210180607_gwjieubx17e.jpg"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutoBinding.inflate(inflater, container, false)

        inicializarToolbar()
        inicializarRvAdicionais()

        return binding.root
    }

    private fun inicializarToolbar() {

        with(binding) {
            btnNavProdutoVoltar.setOnClickListener {
                findNavController().navigate(R.id.lojaFragment)
            }
        }
    }

    private fun inicializarRvAdicionais() {
        adicionaisAdapter = AdicionaisAdapter()
        adicionaisAdapter.adicionarListaAdicionais(listaAdicionais)

        binding.rvAdicionais.adapter = adicionaisAdapter
        binding.rvAdicionais.layoutManager = LinearLayoutManager(context)
    }

}