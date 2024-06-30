package kr.ac.kopo.foodrecommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class Recipe(
    val title: String,
    val description: String,
    val imageUrl: String
)
class RecipeAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewRecipe: ImageView = itemView.findViewById(R.id.imageViewRecipe)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

        fun bind(recipe: Recipe) {
            // 레시피 이미지 설정 (Glide 사용 예시)
            Glide.with(itemView)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.loading) // 이미지 로딩 중 표시할 이미지 설정
                .error(R.drawable.loading) // 이미지 로드 실패 시 표시할 이미지 설정
                .into(imageViewRecipe)

            textViewTitle.text = recipe.title
            textViewDescription.text = recipe.description
        }
    }
}
