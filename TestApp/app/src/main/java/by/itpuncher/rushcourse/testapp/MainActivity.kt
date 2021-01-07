package by.itpuncher.rushcourse.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this,  CreateNoteActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val lm = LinearLayoutManager(this)
        recyclerView.layoutManager = lm
        val adapter = RecordsAdapter()
        recyclerView.adapter = adapter

        val data = StorageUtil.loadFromFile(this)
        val records: ArrayList<Record> = arrayListOf()
        data.forEach { records.add(Record(it.first.toString(), it.second)) }

        adapter.setData(records)
    }
}
