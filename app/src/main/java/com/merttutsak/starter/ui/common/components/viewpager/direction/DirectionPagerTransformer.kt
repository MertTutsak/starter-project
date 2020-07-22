package com.merttutsak.starter.ui.common.components.viewpager.direction

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.merttutsak.starter.ui.common.components.viewpager.base.BaseViewPager
import java.util.ArrayList

abstract class DirectionPagerTransformer : ViewPager.PageTransformer {
    val startOffset: Float = 0f

    var holderList = ArrayList<DirectionViewHolder>()

    abstract fun onTransform(page: View, position: Float)

    override fun transformPage(page: View, position: Float) {
        setDirection(getHolder(page), position)
        onTransform(page, position)
        addHolder(getHolder(page))
    }

    open class DirectionViewHolder(view: View) : BaseViewPager.ViewHolder(view) {
        var direction: ViewPagerDirection =
            ViewPagerDirection.NONE

        var state: ViewPagerState =
            ViewPagerState.NONE

        override fun bind(viewModel: BaseViewModel, position: Int) {

        }

        var lastPosition: Float = -1f
    }

    protected fun getHolder(page: View): DirectionViewHolder {
        holderList.forEach {
            if (it.view == page) {
                return it
            }
        }

        return DirectionViewHolder(page)
    }

    protected fun addHolder(holder: DirectionViewHolder) {
        if (!holderList.contains(holder)) {
            holderList.add(holder)
        } else {
            var index = holderList.indexOf(holder)
            holderList[index] = holder
        }
    }

    protected fun setDirection(holder: DirectionViewHolder, position: Float) {
        if (holder.lastPosition == position) {
            holder.state == ViewPagerState.STABLE
            return
        } else {
            holder.state == ViewPagerState.MOVE
        }

        if (position <= -1f) {
            holder.direction = ViewPagerDirection.ON_LEFT
        } else if (position >= 1f) {
            holder.direction = ViewPagerDirection.ON_RIGHT
        } else if (position < 0) {
            if (position > holder.lastPosition) {
                holder.direction = ViewPagerDirection.FROM_LEFT
            } else if (position < holder.lastPosition) {
                holder.direction = ViewPagerDirection.TO_LEFT
            }
        } else if (position > 0) {
            if (position >= holder.lastPosition) {
                holder.direction = ViewPagerDirection.TO_RIGHT
            } else if (position < holder.lastPosition) {
                holder.direction = ViewPagerDirection.FROM_RIGHT
            }
        } else {
            holder.direction = ViewPagerDirection.CENTER
        }
        holder.lastPosition = position
    }

    enum class ViewPagerState {
        STABLE,
        MOVE,
        NONE
    }

    enum class ViewPagerDirection {
        TO_LEFT,
        ON_LEFT,
        FROM_LEFT,
        TO_RIGHT,
        FROM_RIGHT,
        ON_RIGHT,
        CENTER,
        NONE
    }
}