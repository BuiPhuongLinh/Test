package com.cmc.demoweather.forecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cmc.demoweather.databinding.ItemForecastBinding
import com.cmc.domain.entities.Hourly

class ForeCastAdapter(
    private val hourlyList: MutableList<Hourly>
) :
    RecyclerView.Adapter<ForeCastAdapter.ViewHolder>() {

    fun addList(hourlyListData: List<Hourly>) {
        hourlyList.clear()
        hourlyList.addAll(hourlyListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hourlyList[position])

    }

    override fun getItemCount(): Int = hourlyList.size

    class ViewHolder(
        private val binding: ItemForecastBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hourly: Hourly) {
            binding.foreCastHourModel = hourly
            binding.executePendingBindings()
        }
    }
}