package ru.sfedu.rickandmortyapi2.interfaces

import ru.sfedu.rickandmortyapi2.model.api.character.Character
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode
import ru.sfedu.rickandmortyapi2.model.api.location.Location

interface getObjectInfo {
    fun getPage(page: Int) {}

    fun getCharacter(character: Character) {}

    fun getLocation(location: Location) {}

    fun getEpisode(episode: Episode) {}

}