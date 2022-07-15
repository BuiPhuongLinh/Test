package com.template.basekotlincleanarchitecture.forecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.basekotlincleanarchitecture.databinding.ItemForecastBinding
import com.template.domain.entities.Hourly
import com.template.sharelocal.UTCtoLocal

class ForeCastAdapter(private val hourlyList: MutableList<Hourly>) :
    RecyclerView.Adapter<ForeCastAdapter.ViewHolder>() {

    fun addList(hourlyListData: List<Hourly>) {
        hourlyList.clear()
        hourlyList.addAll(hourlyListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hourlyList[position])

    }

    override fun getItemCount(): Int = hourlyList.size

    class ViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(hourly: Hourly) {
            binding.tvDay.text = "${hourly.dt?.UTCtoLocal()} : "
            binding.tvTemp.text = "${hourly.temp?.toInt()} C"
        }
    }
}