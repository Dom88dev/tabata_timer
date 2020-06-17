package com.dom_project.tabata_timer.ui.binding_adpters

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.dom_project.tabata_timer.R

/*
 뷰모델에 관찰가능한 boolean값을 할당해주어 양방향 데이터 바인딩 어댑터를 하려 했으나 리스너용 데이터바인딩어댑털터를 추가하여 단방향으로 함
 할당한 boolean 객체값이 변경되면 리프레쉬 로딩 애니메이션이 나타남(초기 네트워크 통신시 변경해주어 로딩 애니메이션 처리)
 */
@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    if (isRefreshing != visible) isRefreshing = visible
}

/*
 대신 onRefreshListener를 () -> Unit 타입으로 하여 지정해준다. 리프레싱 이벤트가 발생하면 지정한 () -> Unit 개체가 실행된다.
 */
@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.OnRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(listener)
}

/*
 이미지 리소스의 URI를 스트링으로 지정해주면 글라이드를 이용해 해당 이미지뷰에 이미지 리소스를 렌더링하도록 처리
 이미지뷰에 설정해놓은 scaleType에 따라 글라이드에서 scaleType이 적용되도록 처리해놓음.
 */
@BindingAdapter("imageUri")
fun loadImage(view: ImageView, uri: String?) {
    Log.e("BINDING_FUNCTION", "loadImage($uri)")
    if (uri != null) {
        if (uri.endsWith("gif")) {
            when (view.scaleType) {
                ImageView.ScaleType.CENTER_CROP -> {
                    Glide.with(view.context).asGif().load(uri).thumbnail(.1f).centerCrop().into(view)
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    Glide.with(view.context).asGif().load(uri).thumbnail(.1f).centerInside().into(view)
                }
                else -> {
                    Glide.with(view.context).asGif().load(uri).thumbnail(.1f).fitCenter().into(view)
                }
            }
        } else {
            when (view.scaleType) {
                ImageView.ScaleType.CENTER_CROP -> {
                    Glide.with(view.context).load(uri).thumbnail(.1f).centerCrop().into(view)
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    Glide.with(view.context).load(uri).thumbnail(.1f).centerInside().into(view)
                }
                else -> {
                    Glide.with(view.context).load(uri).thumbnail(.1f).fitCenter().into(view)
                }
            }
        }
    }
}

@BindingAdapter("imageUri", "radius", requireAll = true)
fun loadImage(view: ImageView, uri: String?, radius: Int) {
    Log.e("BINDING_FUNCTION", "loadImage($uri) / round corner($radius)")
    if (uri != null) {
        when (view.scaleType) {
            ImageView.ScaleType.CENTER_CROP -> {
                Glide.with(view.context).load(uri).thumbnail(.1f).apply(RequestOptions().transform(RoundedCorners(radius), CenterCrop())).into(view)
            }
            ImageView.ScaleType.CENTER_INSIDE -> {
                Glide.with(view.context).load(uri).thumbnail(.1f).apply(RequestOptions().transform(RoundedCorners(radius), CenterInside())).into(view)
            }
            else -> {
                Glide.with(view.context).load(uri).thumbnail(.1f).apply(RequestOptions().transform(RoundedCorners(radius), FitCenter())).into(view)
            }
        }
    }
}

@BindingAdapter("text")
fun setText(view: TextView, text: Int) {
    view.text = text.toString()
}
@BindingAdapter("text")
fun setText(view: TextView, text: Long) {
    view.text = text.toString()
}

@BindingAdapter("divider")
fun setDivider(view: RecyclerView, divider: Boolean) {
    if (divider && view.layoutManager is LinearLayoutManager) {
        val itemDecoration = DividerItemDecoration(view.context, (view.layoutManager as LinearLayoutManager).orientation)
        itemDecoration.setDrawable(view.context.getDrawable(R.drawable.divider_in_recyclerview_tranparent)!!)
        view.addItemDecoration(itemDecoration)
    }
}

@BindingAdapter("isVisible")
fun setVisibleGifLabel(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

