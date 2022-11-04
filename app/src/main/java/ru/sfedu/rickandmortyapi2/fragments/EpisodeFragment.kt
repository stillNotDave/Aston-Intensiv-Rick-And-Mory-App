package ru.sfedu.rickandmortyapi2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sfedu.rickandmortyapi2.adapters.EpisodeAdapter
import ru.sfedu.rickandmortyapi2.databinding.EpisodeFragmentBinding
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.episode.Episode
import ru.sfedu.rickandmortyapi2.viewmodel.EpisodeViewModel
import ru.sfedu.rickandmortyapi2.viewmodel.GetEpisodeViewModel

class EpisodeFragment : Fragment(), getObjectInfo {
    lateinit var binding: EpisodeFragmentBinding
    private val episodeViewModel: EpisodeViewModel by activityViewModels()
    private val getEpisodeViewModel: GetEpisodeViewModel by activityViewModels()
    private val supportFragment = SupportFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EpisodeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeViewModel.resultEpisodeApi.observe(viewLifecycleOwner, Observer {
            keepRecyclerViewEpisode(it.episode)
        })

        binding.progressBarEpisode.visibility = View.GONE
        getClicks()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun keepRecyclerViewEpisode(episodeList: List<Episode>) {
        binding.recyclerViewEpisode.apply {
            adapter = EpisodeAdapter(episodeList, this@EpisodeFragment)
            //layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun getEpisode(episode: Episode) {
        getEpisodeViewModel.setEpisode(episode)
        val episodeItemFragment = EpisodeItemFragment()
        supportFragment.startFragment(episodeItemFragment, activity, true)
    }

    private fun getClicks() {
        binding.imageViewRefreshEpisode.setOnClickListener {
            binding.progressBarEpisode.visibility = View.VISIBLE
            refreshList()
        }
    }

    private fun refreshList() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            episodeViewModel.getInfo()
            withContext(Dispatchers.Main) {
                binding.progressBarEpisode.visibility = View.GONE
            }
        }
    }
}