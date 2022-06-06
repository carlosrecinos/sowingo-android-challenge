package com.recinos.sowingo_android_challenge.main.view

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.recinos.sowingo_android_challenge.R
import com.recinos.sowingo_android_challenge.main.model.ui.ProductCardViewModel
import java.text.NumberFormat

class ProductsAdapter(val context: Context, private var mList: List<ProductCardViewModel>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_product_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val format = NumberFormat.getCurrencyInstance()
        val viewModel = mList[position]

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))

        holder.titleView.text = viewModel.title
        holder.priceTextView.text = format.format(viewModel.price)
        holder.strikethroughTextView.text = format.format(viewModel.listPrice)
        viewModel.badgeImageUrl?.let {
            Glide.with(context)
                .load(it)
                .apply(requestOptions)
                .skipMemoryCache(true)//for caching the image url in case phone is offline
                .into(holder.badgeImageView)
        } ?: run {
            holder.badgeImageView.setImageDrawable(null)
        }
        if (viewModel.isFavorite == true) {
            holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart))
        } else {
            holder.favoriteButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_outlined))
        }

        Glide.with(context)
            .load(viewModel.image)
            .apply(requestOptions)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(holder.imageView)

        holder.favoriteButton.setOnClickListener {
            onClickListener.onClick(viewModel, position)
        }




    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setElements(list: List<ProductCardViewModel>) {
        mList = list
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.product_image_view)
        val titleView: TextView = itemView.findViewById(R.id.title_text_view)
        val priceTextView: TextView = itemView.findViewById(R.id.price_text_view)
        val strikethroughTextView: TextView = itemView.findViewById(R.id.strike_price_text_view)
        val favoriteButton: FloatingActionButton = itemView.findViewById(R.id.product_favorite_button)
        val badgeImageView: ImageView = itemView.findViewById(R.id.best_seller_badge)

        init {
            strikethroughTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}