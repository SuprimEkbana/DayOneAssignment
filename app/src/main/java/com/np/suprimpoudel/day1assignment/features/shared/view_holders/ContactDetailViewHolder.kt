package com.np.suprimpoudel.day1assignment.features.shared.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.np.suprimpoudel.day1assignment.R
import com.np.suprimpoudel.day1assignment.features.`interface`.ContactListOnClick
import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail

class ContactDetailViewHolder(itemView: View, listener: ContactListOnClick) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(contactDetail: ContactDetail) {
        contactName.text = contactDetail.name!!
        contactAddress.text = contactDetail.address!!
        contactPhoneNumber.text = contactDetail.phoneNumber!!
    }

    private val contactName: TextView = itemView.findViewById(R.id.txtContactName)
    private val contactAddress: TextView = itemView.findViewById(R.id.txtAddress)
    private val contactPhoneNumber: TextView = itemView.findViewById(R.id.txtPhoneNumber)
    private val deleteIcon: ImageView = itemView.findViewById(R.id.imgDeleteIcon)

    init {
        deleteIcon.setOnClickListener {
            listener.onDeleteIconClicked(adapterPosition)
        }
        itemView.setOnClickListener {
            listener.onContactTileClicked(adapterPosition)
        }
    }
}