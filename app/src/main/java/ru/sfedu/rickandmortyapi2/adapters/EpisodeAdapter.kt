package ru.sfedu.rickandmortyapi2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.rickandmortyapi2.databinding.EpisodeItemListBinding
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode

class EpisodeAdapter(
    val list: List<Episode>,
    val getObjectInfo: getObjectInfo
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding, getObjectInfo)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EpisodeViewHolder(
        private val episodeItemListBinding: EpisodeItemListBinding,
        private val getObjectInfo: getObjectInfo
    ) : RecyclerView.ViewHolder(episodeItemListBinding.root) {
        fun bind(episode: Episode) {
            episodeItemListBinding.textViewEpisodeName.text = episode.episodeName
            episodeItemListBinding.textViewEpisodeAirDate.text = episode.episodeAirDate

            episodeItemListBinding.root.setOnClickListener {
                getObjectInfo.getEpisode(episode)
            }
        }
    }
}