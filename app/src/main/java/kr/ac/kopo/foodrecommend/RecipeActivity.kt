package kr.ac.kopo.foodrecommend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)  // recipe.xml을 레이아웃으로 설정
    }
}
