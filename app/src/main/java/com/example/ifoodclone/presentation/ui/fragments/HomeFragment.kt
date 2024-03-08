package com.example.ifoodclone.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.FragmentHomeBinding
import com.example.ifoodclone.domain.model.Categoria
import com.example.ifoodclone.domain.model.Loja
import com.example.ifoodclone.helper.TipoLayout
import com.example.ifoodclone.helper.exibirMensagemToast
import com.example.ifoodclone.presentation.ui.adapters.CategoriasAdapter
import com.example.ifoodclone.presentation.ui.adapters.LojasAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var ultimasLojasAdapter: LojasAdapter
    private lateinit var lojasAdapter: LojasAdapter
    private lateinit var categoriasAdapter: CategoriasAdapter

    private val listaLojas = listOf(
        Loja(
            "Habib's",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/ff67b409-35b8-4ffd-9bca-021f1c19ca06/202103031117_5j46_i.jpg?imwidth=128",
            "lanches"
        ),
        Loja(
            "Toca do Lanche",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/280f7653-80e2-4763-b309-806b6d65103a/202001261742_FjMN_i.png?imwidth=128",
            "comida"
        ),
        Loja(
            "Burguer King",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/7184e420-f406-48ab-a56e-3c4b6cd144d2/202310201851_YERL.png?imwidth=128",
            "bebidas"
        ),
        Loja(
            "Mc Donalds",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/0079ca8f-ff51-4448-9ccb-84cb920f1136/202104062007_D8nj_i.png?imwidth=128",
            "lanches"
        ),
        Loja(
            "Espetão Ta na Brasa",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/f1c5074a-55d0-4c7c-a5fb-1a325a43d11f/202309091238_H9rt_i.jpg?imwidth=128",
            "comida"
        ),
        Loja(
            "Corujão Bebidas",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/0239d2c8-e085-4cab-bd37-36cd5b88bdc3/202010301503_SISQ_i.jpg?imwidth=128",
            "bebidas"
        ),
        Loja(
            "Habib's",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/ff67b409-35b8-4ffd-9bca-021f1c19ca06/202103031117_5j46_i.jpg?imwidth=128",
            "lanches"
        ),
        Loja(
            "Toca do Lanche",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/280f7653-80e2-4763-b309-806b6d65103a/202001261742_FjMN_i.png?imwidth=128",
            "comida"
        ),
        Loja(
            "Burguer King",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/7184e420-f406-48ab-a56e-3c4b6cd144d2/202310201851_YERL.png?imwidth=128",
            "bebidas"
        ),
        Loja(
            "Mc Donalds",
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/0079ca8f-ff51-4448-9ccb-84cb920f1136/202104062007_D8nj_i.png?imwidth=128",
            "lanches"
        )
    )
    private val listaCategorias = listOf(
        Categoria("Restaurantes", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Restaurantes3_vTNE.png?imwidth=128"),
        Categoria("Mercado", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Mercados_SMx3.png?imwidth=128"),
        Categoria("Farmácia", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Farmacia_4vdM.png?imwidth=128"),
        Categoria("Pet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Pet_NsoQ.png?imwidth=128"),
        Categoria("Bebidas", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Bebidas_X9kU.png?imwidth=128"),
        Categoria("Shopping", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/floreseperfume_LOhx.png?imwidth=128"),
        Categoria("Gourmet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Gourmet_uke7.png?imwidth=128"),
        Categoria("Super atacado", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Mercados_t4d6.png?imwidth=128")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        inicializarRvUltimasLojas()
        inicializarRvLojas()
        inicializarSliderPromocional()
        inicializarRvCategorias()
        inicializarFiltrosLojas()
        inicializarAvisoNotificacoes()

        return binding.root
    }

    private fun inicializarAvisoNotificacoes() {
        with(binding) {
            val menuItem = tbHome.menu.findItem(R.id.item_notificacao)
            val textAvisoNotificacao = menuItem.actionView?.findViewById(R.id.textAvisoNotificacao) as TextView
            textAvisoNotificacao.text = "1"
        }
    }

    private fun inicializarFiltrosLojas() {
        val filtroRetirar = binding.chipRetirar.isChecked
        val filtroEntregaGratis = binding.chipEntregaGratis.isChecked

        binding.chipOrdenacao.setOnClickListener {
            val listaOrdenacao = arrayOf("Ordenação Padrão","Crescente", "Decrescente")

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Escolha uma ordenação")
                .setCancelable(false)
                .setItems(listaOrdenacao) { _, posicao ->
                    val itemSelecionado = listaOrdenacao[posicao]
                    if (posicao != 0) {
                        binding.chipOrdenacao.text = itemSelecionado
                    } else {
                        binding.chipOrdenacao.text = "Ordenação"
                    }
                }.show()
        }
    }

    private fun inicializarRvCategorias() {
        categoriasAdapter = CategoriasAdapter()
        categoriasAdapter.adicionarListaCategorias(listaCategorias)

        binding.rvFiltros.adapter = categoriasAdapter
        binding.rvFiltros.layoutManager = GridLayoutManager(context, 4)
    }

    private fun inicializarSliderPromocional() {
        val listaSlides = mutableListOf<SlideModel>()

        listaSlides.add(
            SlideModel(
                imageUrl = "https://static.ifood-static.com.br/image/upload/t_high/discoveries/16promotionschurn19v3principal2_NfPY.png?imwidth=1920",
                scaleType = ScaleTypes.CENTER_CROP
            )
        )
        listaSlides.add(
            SlideModel(
                imageUrl = "https://static.ifood-static.com.br/image/upload/t_high/discoveries/22pecatudao2999principal_tQtg.png?imwidth=1920",
                scaleType = ScaleTypes.CENTER_CROP
            )
        )
        listaSlides.add(
            SlideModel(
                imageUrl = "https://static.ifood-static.com.br/image/upload/t_high/discoveries/22pecatudao12principal_cfJW.png?imwidth=1920",
                scaleType = ScaleTypes.CENTER_CROP
            )
        )
        listaSlides.add(
            SlideModel(
                imageUrl = "https://static.ifood-static.com.br/image/upload/t_high/discoveries/2812PromotionsCampanhasAtualizacaoCapasCapaPrincipal07_m8ye.png?imwidth=1920",
                scaleType = ScaleTypes.CENTER_CROP
            )
        )
        listaSlides.add(
            SlideModel(
                imageUrl = "https://static.ifood-static.com.br/image/upload/t_high/discoveries/2812PromotionsCampanhasAtualizacaoCapasCapaPrincipalentregagratis_0tY7.png?imwidth=1920",
                scaleType = ScaleTypes.CENTER_CROP
            )
        )

        binding.sliderPromocional.setImageList(listaSlides)
        binding.sliderPromocional.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {}

            override fun onItemSelected(position: Int) {
                activity?.exibirMensagemToast("Item clicado: $position")
            }
        })
    }

    private fun inicializarRvUltimasLojas() {
        ultimasLojasAdapter = LojasAdapter(TipoLayout.HORIZONTAL) {
            findNavController().navigate(R.id.lojaFragment)
        }
        ultimasLojasAdapter.adicionarLista(listaLojas)

        binding.rvUltimasLojas.adapter = ultimasLojasAdapter
        binding.rvUltimasLojas.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
    }

    private fun inicializarRvLojas() {
        lojasAdapter = LojasAdapter(TipoLayout.VERTICAL) {
            findNavController().navigate(R.id.lojaFragment)
        }
        lojasAdapter.adicionarLista(listaLojas)

        binding.rvLojas.adapter = lojasAdapter
        binding.rvLojas.layoutManager = LinearLayoutManager(context)
    }
}