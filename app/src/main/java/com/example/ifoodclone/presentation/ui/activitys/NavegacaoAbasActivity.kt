package com.example.ifoodclone.presentation.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ifoodclone.databinding.ActivityNavegacaoAbasBinding
import com.example.ifoodclone.presentation.ui.fragments.BuscaFragment
import com.example.ifoodclone.presentation.ui.fragments.HomeFragment
import com.example.ifoodclone.presentation.ui.fragments.PedidosFragment
import com.example.ifoodclone.presentation.ui.fragments.PerfilFragment
import com.google.android.material.tabs.TabLayoutMediator

class NavegacaoAbasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavegacaoAbasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavegacaoAbasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarNavegacaoAbas()
    }

    private fun inicializarNavegacaoAbas() {
        val tabLayout = binding.tabLayoutNavegacao
        val viewPager = binding.viewPagerNavegacao

        /*val listaAbas = listOf(
            MinhaAba("Inicio", HomeFragment()),
            MinhaAba("Busca", BuscaFragment()),
            MinhaAba("Pedidos", PedidosFragment()),
            MinhaAba("Perfil", PerfilFragment())
        )*/
        val listaAbas = listOf("Inicio", "Busca", "Pedidos", "Perfil")

        viewPager.adapter = ViewPagerAdapter(
            listaAbas, supportFragmentManager, lifecycle
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listaAbas[position]
        }.attach()
    }

}

data class MinhaAba(
    val aba: String,
    val fragment: Fragment
)

class ViewPagerAdapter(
    private val listaAbas: List<String>,
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return listaAbas.count()
    }

    override fun createFragment(position: Int): Fragment {
        /* val aba = listaAbas[position]
         return aba.fragment*/
        when (position) {
            1 -> return BuscaFragment()
            2 -> return PedidosFragment()
            3 -> return PerfilFragment()
        }
        return HomeFragment()
    }
}