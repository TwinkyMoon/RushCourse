package by.itpuncher.rushcourse.testapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val RECORD = "RECORD"
class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.activity_create_note_title)
        setContentView(R.layout.activity_create_note)
        val editText = findViewById<EditText>(R.id.edit_text)
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            val createTime = System.currentTimeMillis()
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                StorageUtil.saveToFile(this, createTime, text)
                val data = Intent()
                data.putExtra(RECORD, Record.build(createTime, text))
                setResult(200, data)
            }
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
