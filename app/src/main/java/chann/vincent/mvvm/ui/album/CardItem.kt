package chann.vincent.mvvm.ui.album
import android.content.Context
import chann.vincent.mvvm.R
import chann.vincent.mvvm.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_card.*


open class CardItem(
    private val id: Int,
    private val title: String? = "",
    private val thumbnailUrl: String? = null,
    private val context: Context) : Item() {

    override fun getLayout() = R.layout.item_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = "${id} - ${title}"
        GlideApp.with(context)
            .load(thumbnailUrl)
            .into(viewHolder.image)
    }
}