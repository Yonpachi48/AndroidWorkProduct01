package app.takahashi.yonpachi.androidworkproduct01

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.takahashi.yonpachi.androidworkproduct01.databinding.ItemDataCellBinding


class RecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val realmDataList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentTextView.text = realmDataList[position]
    }

    override fun getItemCount(): Int {
        return realmDataList.size
    }

    class ViewHolder(binding: ItemDataCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentTextView: TextView = binding.contentTextView
    }
}