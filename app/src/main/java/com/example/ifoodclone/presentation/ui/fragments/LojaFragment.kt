package com.example.ifoodclone.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ifoodclone.databinding.FragmentLojaBinding
import com.example.ifoodclone.domain.model.Produto

class LojaFragment : Fragment() {

    private lateinit var binding: FragmentLojaBinding

    private val listaProdutos = listOf(
        Produto(
            "03 PIZZAS DE 35CM + COCA-COLA 2,5L",
            "Peça uma pizza com até dois sabores + refrigerante Coca-Cola",
            "R$ 155,90",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061640_35NL_i.jpg"
        ),
        Produto(
            "03 PIZZAS DE 35CM + FRUKI 2L",
            "Peça uma pizza com até dois sabores + refrigerante fruki",
            "R$ 151,50",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/de2613c1-0e51-428e-b995-7f6efb916e78/202304061552_1HY2_i.jpg"
        ),
        Produto(
            "RAVIOLE VERDE AOS QUATRO QUEIJOS PARA 02 PESSOAS",
            "Raviole de massa verde recheado com ricota e nozes.",
            "R$ 115,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/202003281118_Qlsw_p.jpg"
        ),
        Produto(
            "RISOTTO CALABRESE P/ 02 PESSOAS",
            "Arroz especial para risoto com linguiça calabresa fatiada.",
            "R$ 89,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/32c1b6ad-322d-4c72-be2a-fe724d4f7adf/201902201545_risot.jpg"
        ),
        Produto(
            "HAMBÚRGUER MOOMO",
            "Hambúrguer de carne bovina de 180g, pão brioche, cheddar, bacon, cebola crispy e o nosso sensacional molho La Vaca.",
            "R$ 19,99",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/d2bd512b-3f9a-4b6e-a111-2f5d037ba18d/202402031601_5DY8_i.jpg"
        ),
        Produto(
            "CERVEJA LONG NECK EXTRA CORONA 330 ML",
            "Produto para maiores de 18 anos",
            "R$ 9,00",
            "https://static.ifood-static.com.br/image/upload/t_medium/pratos/820af392-002c-47b1-bfae-d7ef31743c7f/202306181621_1zbyb2moe2o.jpg"
        ),
        Produto(
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
        binding = FragmentLojaBinding.inflate(inflater, container , false)



        return binding.root
    }

}