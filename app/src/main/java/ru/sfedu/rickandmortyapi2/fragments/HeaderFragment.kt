package ru.sfedu.rickandmortyapi2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sfedu.rickandmortyapi2.databinding.HeaderFragmentBinding

class HeaderFragment : Fragment() {
    lateinit var binding: HeaderFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    //View?
    ): View? {
        binding = HeaderFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}