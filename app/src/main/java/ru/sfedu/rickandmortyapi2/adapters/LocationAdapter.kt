package ru.sfedu.rickandmortyapi2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sfedu.rickandmortyapi2.databinding.LocationItemListBinding
import ru.sfedu.rickandmortyapi2.interfaces.getObjectInfo
import ru.sfedu.rickandmortyapi2.model.api.location.Location

class LocationAdapter(
    val list: List<Location>,
    val getObjectInfo: getObjectInfo
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = LocationItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding, getObjectInfo)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class LocationViewHolder(
        private val locationItemListBinding: LocationItemListBinding,
        private val getObjectInfo: getObjectInfo
    ) : RecyclerView.ViewHolder(locationItemListBinding.root) {
        fun bind(location: Location) {
            locationItemListBinding.textViewLocationName.text = location.locationName
            locationItemListBinding.textViewLocationType.text = location.locationType

            locationItemListBinding.root.setOnClickListener {
                getObjectInfo.getLocation(location)
            }
        }
    }
}
