package ru.sfedu.rickandmortyapi2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.rickandmortyapi2.databinding.CharacterItemListBinding
import ru.sfedu.rickandmortyapi2.glide.GlideManager
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.character.Character

class CharacterAdapter(
    val list: List<Character>,
    val getObjectInfo: getObjectInfo,
    val context: Context
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, getObjectInfo, context)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CharacterViewHolder(
        private val characterItemListBinding: CharacterItemListBinding,
        private val getObjectInfo: getObjectInfo,
        private val context: Context
    ) : RecyclerView.ViewHolder(characterItemListBinding.root) {
        fun bind(character: Character) {
            characterItemListBinding.textViewCharacterName.text = character.characterName
            characterItemListBinding.textViewStatus.text = character.characterStatus
            characterItemListBinding.textViewSpecies.text = character.characterSpecies
            characterItemListBinding.textViewGender.text = character.characterGender
            GlideManager().setImage(
                characterItemListBinding.imageCharacter,
                character.characterImage
            )

            characterItemListBinding.root.setOnClickListener {
                getObjectInfo.getCharacter(character)
            }
        }

    }
}