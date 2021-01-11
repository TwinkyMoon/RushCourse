package by.itpuncher.rushcourse.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.thread

const val CREATE_NOTE_CODE = 101

class MainActivity : AppCompatActivity() {

    val adapter = RecordsAdapter()
    lateinit var recyclerView: RecyclerView
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress)
        supportActionBar?.hide()
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this,  CreateNoteActivity::class.java)
            startActivityForResult(intent, CREATE_NOTE_CODE)
        }

        recyclerView = findViewById(R.id.recycler_view)
        val lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter

        loadData()
    }

    private fun loadData() {
        recyclerView.visibility = View.GONE
        progress.visibility = View.VISIBLE
        thread(start = true) {
            val data = StorageUtil.loadFromFile(this)
            val records: ArrayList<Record> = arrayListOf()
            data.forEach { records.add(Record.build(it.first, it.second)) }
            runOnUiThread {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.setData(records)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_NOTE_CODE) {
            val record = data?.getSerializableExtra(RECORD)
            if (record != null) {
                adapter.addData(record as Record)
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
