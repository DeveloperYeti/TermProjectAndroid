package kr.ac.kopo.foodrecommend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class RecipeActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var imageViewPreview: ImageView

    private var selectedImageUri: Uri? = null

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)

        editTextTitle = findViewById(R.id.editTextRecipeTitle)
        editTextDescription = findViewById(R.id.editTextRecipeDescription)
        imageViewPreview = findViewById(R.id.imageViewPreview)

        val buttonLoadImage = findViewById<Button>(R.id.buttonLoadImage)
        val buttonAddRecipe = findViewById<Button>(R.id.buttonAddRecipe)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonLoadImage.setOnClickListener {
            openGallery()
        }

        buttonAddRecipe.setOnClickListener {
            addRecipe()
        }

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()
            val imageUrl = selectedImageUri.toString()

            if (title.isNotBlank() && description.isNotBlank() && selectedImageUri != null) {
                val recipe = Recipe(title, description, imageUrl)
                saveRecipeToFirestore(recipe)
            } else {
                Toast.makeText(this, "모든 필드를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                selectedImageUri = data.data
                imageViewPreview.setImageURI(selectedImageUri)
            }
        }
    }

    private fun addRecipe() {
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()
        val imageUrl = selectedImageUri.toString()

        if (title.isNotBlank() && description.isNotBlank() && selectedImageUri != null) {
            val recipe = Recipe(title, description, imageUrl)
            saveRecipeToFirestore(recipe)
        } else {
            Toast.makeText(this, "모든 필드를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveRecipeToFirestore(recipe: Recipe) {
        db.collection("recipes")
            .add(recipe)
            .addOnSuccessListener { _ ->
                Toast.makeText(this, "Recipe 추가: ${recipe.title}", Toast.LENGTH_SHORT).show()
                clearFields()
            }
            .addOnFailureListener { _ ->
                Toast.makeText(this, "Recipe 추가 실패", Toast.LENGTH_SHORT).show()
                clearFields()
            }
    }


    private fun clearFields() {
        editTextTitle.text.clear()
        editTextDescription.text.clear()
        imageViewPreview.setImageResource(android.R.color.darker_gray)
        selectedImageUri = null
    }
}
