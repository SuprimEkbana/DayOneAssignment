package com.np.suprimpoudel.day1assignment.features.profile

import com.np.suprimpoudel.day1assignment.features.shared.models.ContactDetail
import com.np.suprimpoudel.day1assignment.features.shared.repository.ContactRepository

class ContactInteractor {
    private val contactRepository = ContactRepository()

    fun showAllContactDetail() = contactRepository.getAllContactDetail()

    fun addContactDetail(contactDetail: ContactDetail) =
        contactRepository.addContactDetail(contactDetail)

    fun updateContactDetail(name: String, address: String, phoneNumber: String, position: Int) =
        contactRepository.updateContactDetail(name, address, phoneNumber, position)

    fun removeContactDetail(position: Int) = contactRepository.removeContactDetail(position)

    fun getSingleContactDetail(position: Int) = contactRepository.getSingleContactDetail(position)
}