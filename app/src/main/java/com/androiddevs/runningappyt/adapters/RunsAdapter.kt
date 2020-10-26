package com.androiddevs.runningappyt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.other.TrackingUtility
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunsAdapter(
    private val onDeleteItem: (run: Run) -> Unit
) : ListAdapter<Run, RunsAdapter.RunViewHolder>(object : DiffUtil.ItemCallback<Run>(){
    override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}){

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.findViewById<View>(R.id.btnDelete).setOnClickListener {
                onDeleteItem(getItem(adapterPosition))
            }
        }

        fun bind(run: Run) {
            itemView.apply {
                Glide.with(this).load(run.img).into(ivRunImage)

                val calendar = Calendar.getInstance().apply {
                    timeInMillis = run.timestamp
                }
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                tvDate.text = dateFormat.format(calendar.time)

                tvAvgSpeed.text = "${run.avgSpeedInKMH} Km/h"

                tvCalories.text = "${run.caloriesBurned} Kcal"

                tvDistance.text = "${run.distanceInMeters / 1000f} Km"

                tvTime.text = TrackingUtility.getFormattedStopWatchText(run.timeInMillis)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_run,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}