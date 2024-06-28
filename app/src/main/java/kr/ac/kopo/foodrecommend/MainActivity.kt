package kr.ac.kopo.foodrecommend;

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var forwardImg: ImageView
    private lateinit var backendImg: ImageView
    private val imageArray = intArrayOf(R.drawable.img, R.drawable.image2, R.drawable.image3)
    private var currentImageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forwardImg = findViewById(R.id.forward_img)
        backendImg = findViewById(R.id.backend_img)
        forwardImg.setImageResource(imageArray[currentImageIndex])

        findViewById<View>(R.id.next_img).setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % imageArray.size
            forwardImg.setImageResource(imageArray[currentImageIndex])
        }

        findViewById<View>(R.id.prev_img).setOnClickListener {
            currentImageIndex = (currentImageIndex - 1 + imageArray.size) % imageArray.size
            forwardImg.setImageResource(imageArray[currentImageIndex])
        }
    }
}
