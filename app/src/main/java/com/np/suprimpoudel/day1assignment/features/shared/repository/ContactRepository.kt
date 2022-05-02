package com.np.suprimpoudel.day1assignment.features.shared.repository

import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail

class ContactRepository {
    private val contactDetails = ArrayList<ContactDetail>()

    fun getAllContactDetail(): ArrayList<ContactDetail> = contactDetails

    fun addContactDetail(contactDetail: ContactDetail) {
        contactDetails.add(contactDetail)
    }

    fun removeContactDetail(position: Int) {
        contactDetails.removeAt(position)
    }

    fun updateContactDetail(nameP: String, addressP: String, phoneNumberP: String, position: Int) {
        contactDetails[position].name = nameP
        contactDetails[position].address = addressP
        contactDetails[position].phoneNumber = phoneNumberP
    }

    fun getSingleContactDetail(position: Int): ContactDetail = contactDetails[position]
}