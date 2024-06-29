package kr.ac.kopo.foodrecommend

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mylist)

        setupMemoSection(R.id.memo1, R.id.memo1_text, R.id.memo1_edit)
        setupMemoSection(R.id.memo2, R.id.memo2_text, R.id.memo2_edit)
        setupMemoSection(R.id.memo3, R.id.memo3_text, R.id.memo3_edit)
        setupMemoSection(R.id.memo4, R.id.memo4_text, R.id.memo4_edit)

        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            saveMemos()
        }
    }

    private fun setupMemoSection(memoLayoutId: Int, textViewId: Int, editTextId: Int) {
        val memoLayout: LinearLayout = findViewById(memoLayoutId)
        val textView: TextView = findViewById(textViewId)
        val editText: EditText = findViewById(editTextId)

        memoLayout.setOnClickListener {
            textView.visibility = View.GONE
            editText.visibility = View.VISIBLE
            editText.requestFocus()
        }
    }

    private fun saveMemos() {
        saveMemo(R.id.memo1_text, R.id.memo1_edit)
        saveMemo(R.id.memo2_text, R.id.memo2_edit)
        saveMemo(R.id.memo3_text, R.id.memo3_edit)
        saveMemo(R.id.memo4_text, R.id.memo4_edit)
    }

    private fun saveMemo(textViewId: Int, editTextId: Int) {
        val textView: TextView = findViewById(textViewId)
        val editText: EditText = findViewById(editTextId)
        val memoText = editText.text.toString()

        if (memoText.isNotEmpty()) {
            textView.text = memoText
        }

        editText.visibility = View.GONE
        textView.visibility = View.VISIBLE
    }
}
