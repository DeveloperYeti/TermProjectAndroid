package kr.ac.kopo.foodrecommend

import kr.ac.kopo.foodrecommend.MyListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var forwardImg: ImageView
    private lateinit var backendImg: ImageView
    private val imageArray = intArrayOf(R.drawable.kimchi, R.drawable.pizza, R.drawable.blacknoodle)
    private var currentImageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forwardImg = findViewById(R.id.forward_img)
        backendImg = findViewById(R.id.backend_img)

        // Set initial images
        forwardImg.setImageResource(imageArray[currentImageIndex])
        backendImg.setImageResource(imageArray[currentImageIndex])

        // Button setup
        val btnMyList: Button = findViewById(R.id.mylist)
        val btnRecipe: Button = findViewById(R.id.recipe)

        // Button click listeners
        btnMyList.setOnClickListener {
            val intent = Intent(this, MyListActivity::class.java)
            startActivity(intent)
        }

        btnRecipe.setOnClickListener {
            // RecipeActivity로의 인텐트 설정
            val intent = Intent(this, kr.ac.kopo.foodrecommend.RecipeActivity::class.java)
            startActivity(intent)
        }

        // Next button click listener
        findViewById<View>(R.id.next_img).setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % imageArray.size
            forwardImg.setImageResource(imageArray[currentImageIndex])
        }

        // Prev button click listener
        findViewById<View>(R.id.prev_img).setOnClickListener {
            currentImageIndex = (currentImageIndex - 1 + imageArray.size) % imageArray.size
            forwardImg.setImageResource(imageArray[currentImageIndex])
        }
    }
}
