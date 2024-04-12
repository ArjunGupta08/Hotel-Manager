package com.arjungupta08.hotelmanager.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class UtilityCollections {
    companion object {
        fun getCollectionReferenceForUser() : CollectionReference {
            val currentUser = FirebaseAuth.getInstance().currentUser
            return FirebaseFirestore.getInstance().collection("user")
                .document(currentUser?.uid!!).collection("my_user")
        }
    }
}