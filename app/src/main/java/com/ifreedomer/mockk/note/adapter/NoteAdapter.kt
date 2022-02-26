package com.ifreedomer.mockk.note.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ifreedomer.mockk.R
import com.ifreedomer.mockk.note.entity.NoteEntity
import java.text.SimpleDateFormat

class NoteAdapter(layoutId: Int, data: MutableList<NoteEntity>) :
    BaseQuickAdapter<NoteEntity, BaseViewHolder>(layoutResId = layoutId, data = data) {
    override fun convert(holder: BaseViewHolder, item: NoteEntity) {
        holder.setText(R.id.title_tv,item.title)
        holder.setText(R.id.time_tv,"${item.time}")
        holder.setBackgroundColor(R.id.background,item.color)
        var backgroundView = holder.getView<View>(R.id.background)
        backgroundView.alpha = 0.5f
    }

}