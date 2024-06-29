package kr.ac.kopo.foodrecommend;

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


        val btn1 : Button = findViewById(R.id.mylist)
        val btn2 : Button = findViewById(R.id.recipe)

        btn1.setOnClickListener{
            val intent = Intent(this, MyListActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener{
            val intent = Intent(this, RecipeActivity::class.java)
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

        // Back button click listener
        findViewById<View>(R.id.Back).setOnClickListener {
            onBackPressed()
        }

        // Home button click listener
        findViewById<View>(R.id.Home).setOnClickListener {
            // Navigate to main activity or perform desired action
            // For simplicity, let's assume it finishes the current activity
            finish()
        }

        // Click listener for changing images on left side click
        findViewById<View>(R.id.prev).setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % imageArray.size
            backendImg.setImageResource(imageArray[currentImageIndex])
        }



    }
}
