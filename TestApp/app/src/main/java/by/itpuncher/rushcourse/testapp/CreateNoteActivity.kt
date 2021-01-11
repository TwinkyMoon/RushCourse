package by.itpuncher.rushcourse.testapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val RECORD = "RECORD"
class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val editText = findViewById<EditText>(R.id.edit_text)

        if (intent.hasExtra(RECORD)) {
            val record = intent.getSerializableExtra(RECORD) as Record
            supportActionBar?.setTitle(record.title)
            editText.setText(record.text)
            editText.isEnabled = false
            findViewById<Button>(R.id.btn_save).visibility = View.GONE
            return
        }


        supportActionBar?.setTitle(R.string.activity_create_note_title)
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
