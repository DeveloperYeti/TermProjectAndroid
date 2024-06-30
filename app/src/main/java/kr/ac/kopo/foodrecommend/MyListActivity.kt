package kr.ac.kopo.foodrecommend

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MyListActivity : AppCompatActivity() {

    private lateinit var memo1Layout: LinearLayout
    private lateinit var memo1Text: TextView
    private lateinit var memo1Edit: EditText
    private lateinit var memoListView: ListView
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button

    private lateinit var adapter: ArrayAdapter<String>
    private val itemList = mutableListOf<String>()

    private val db = FirebaseFirestore.getInstance()
    private val collectionName = "mealBucketList"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mylist)

        memo1Layout = findViewById(R.id.memo1)
        memo1Text = findViewById(R.id.memo1_text)
        memo1Edit = findViewById(R.id.memo1_edit)
        memoListView = findViewById(R.id.memo_list_view)
        saveButton = findViewById(R.id.save_button)
        deleteButton = findViewById(R.id.delete_button)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        memoListView.adapter = adapter

        memo1Layout.setOnClickListener {
            memo1Text.visibility = View.GONE
            memo1Edit.visibility = View.VISIBLE
            memo1Edit.requestFocus()
        }

        saveButton.setOnClickListener {
            val memoText = memo1Edit.text.toString().trim()
            if (memoText.isNotEmpty()) {
                addItemToBucketList(memoText)
                memo1Edit.text.clear()
                memo1Edit.visibility = View.GONE
                memo1Text.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "메모를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        memoListView.setOnItemClickListener { parent, view, position, id ->
            val selectedMemo = itemList[position]
            deleteItemFromBucketList(selectedMemo)
        }

        deleteButton.setOnClickListener {
            val selectedMemo = memoListView.selectedItem as String?
            if (selectedMemo != null) {
                deleteItemFromBucketList(selectedMemo)
            } else {
                Toast.makeText(this, "삭제할 메모를 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }

        loadItemsFromFirestore()
    }

    private fun addItemToBucketList(item: String) {
        db.collection(collectionName)
            .add(mapOf("item" to item))
            .addOnSuccessListener { documentReference ->
                itemList.add(item)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "먹킷 리스트에 항목이 추가되었습니다.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "먹킷 리스트에 항목 추가에 실패했습니다.", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Error adding item to bucket list", e)
            }
    }

    private fun deleteItemFromBucketList(item: String) {
        db.collection(collectionName)
            .whereEqualTo("item", item)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete()
                        .addOnSuccessListener {
                            itemList.remove(item)
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, "먹킷 리스트에서 항목이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "먹킷 리스트에서 항목 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "Error deleting item from bucket list", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error querying item from bucket list", e)
            }
    }

    private fun loadItemsFromFirestore() {
        db.collection(collectionName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                itemList.clear()
                for (document in querySnapshot.documents) {
                    val item = document.getString("item")
                    if (item != null) {
                        itemList.add(item)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error fetching items from bucket list", e)
            }
    }

    companion object {
        private const val TAG = "MyListActivity"
    }
}
