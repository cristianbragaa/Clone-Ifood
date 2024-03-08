package com.example.ifoodclone.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ifoodclone.R
import com.example.ifoodclone.databinding.FragmentProdutoBinding

class ProdutoFragment : Fragment() {

    private lateinit var binding: FragmentProdutoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutoBinding.inflate(inflater, container, false)



        return binding.root
    }

}