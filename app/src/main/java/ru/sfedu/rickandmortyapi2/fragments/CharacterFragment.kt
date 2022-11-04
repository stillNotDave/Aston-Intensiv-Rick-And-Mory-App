package ru.sfedu.rickandmortyapi2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sfedu.rickandmortyapi2.databinding.CharacterFragmentBinding
import ru.sfedu.rickandmortyapi2.viewmodel.CharacterViewModel
import ru.sfedu.rickandmortyapi2.viewmodel.GetCharacterViewModel
import androidx.lifecycle.Observer
import ru.sfedu.rickandmortyapi2.adapters.CharacterAdapter
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.character.Character

class CharacterFragment : Fragment(), getObjectInfo {
    lateinit var binding: CharacterFragmentBinding
    private val characterViewModel: CharacterViewModel by activityViewModels()
    private val getCharacterViewModel: GetCharacterViewModel by activityViewModels()
    private val supportFragment = SupportFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.resultCharacterApi.observe(viewLifecycleOwner, Observer {
            keepRecyclerViewCharacter(it.characterList)
        })

        binding.progressBarCharacter.visibility = View.GONE
        getClicks()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun keepRecyclerViewCharacter(characterList: List<Character>) {
        binding.recyclerViewCharacter.apply {
            adapter = CharacterAdapter(characterList, this@CharacterFragment, context)
            layoutManager = GridLayoutManager(context, 2)

        }
    }

    override fun getCharacter(character: Character) {
        getCharacterViewModel.setCharacter(character)
        val characterItemFragment = CharacterItemFragment()
        supportFragment.startFragment(characterItemFragment, activity, true)
    }

    private fun getClicks() {
        binding.imageViewRefreshCharacter.setOnClickListener {
            binding.progressBarCharacter.visibility = View.VISIBLE
            refreshList()
        }
    }

    private fun refreshList() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            characterViewModel.getInfo()
            withContext(Dispatchers.Main) {
                binding.progressBarCharacter.visibility = View.GONE
            }
        }
    }

}