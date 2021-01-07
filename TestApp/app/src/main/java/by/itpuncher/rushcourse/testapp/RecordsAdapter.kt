package by.itpuncher.rushcourse.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: ArrayList<Record> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecordViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(records: ArrayList<Record>) {
        items.clear()
        items.addAll(records)
        notifyDataSetChanged()
    }

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvText = itemView.findViewById<TextView>(R.id.tv_text)

        fun bind(record: Record) {
            tvTitle.text = record.title
            tvText.text = record.text
        }
    }
}
