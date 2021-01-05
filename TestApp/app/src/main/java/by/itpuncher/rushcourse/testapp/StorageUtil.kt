package by.itpuncher.rushcourse.testapp

import android.content.Context
import android.widget.Toast

object StorageUtil {

    fun saveToFile(context: Context, text: String) {
        if (text.isNotEmpty()) {
            val fileName = System.currentTimeMillis().toString()
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
                    .use {
                        it.write(text.toByteArray())
                    }
        } else {
            Toast.makeText(context, context.getString(R.string.no_data), Toast.LENGTH_SHORT).show()
        }
    }

    fun loadFromFile(context: Context) : List<Pair<Long, String>> {
        val result: ArrayList<Pair<Long, String>> = arrayListOf()
        val files = context.fileList()
        if (files.isNotEmpty()) {
            files.forEach { fileName ->
                context.openFileInput(fileName)
                        .use { stream ->
                            val text = stream.bufferedReader()
                                    .use { it.readText() }
                            result.add(Pair(fileName.toLong(), text))
                        }
            }
        }
        return result
    }
}
