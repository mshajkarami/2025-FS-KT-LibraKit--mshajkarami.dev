package dev.mshajkarami.fs.kt.bookstack.data.repo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import dev.mshajkarami.fs.kt.bookstack.common.ResultState
import dev.mshajkarami.fs.kt.bookstack.common.model.BookCategoryModel
import dev.mshajkarami.fs.kt.bookstack.common.model.BookModel
import dev.mshajkarami.fs.kt.bookstack.domin.repository.AllBookRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class AllBookRepoImpl @Inject constructor(val firebaseDatabase: FirebaseDatabase) :
    AllBookRepository {
    override fun getAllBooks(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var items: List<BookModel> = emptyList()

                items = snapshot.children.map { value ->
                    value.getValue<BookModel>()!!
                }

                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)

        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookCategoryModel> = emptyList()

                items = snapshot.children.map {
                    value ->
                    value.getValue<BookCategoryModel>()!!
                }

                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("BooksCategory").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var items : List<BookModel> = emptyList()

                items = snapshot.children.filter { value ->
                    value.getValue<BookModel>()!!.bookCategory == category
                }.map {
                    value->
                    value.getValue<BookModel>()!!
                }
                trySend(ResultState.Success(items))
            }


            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

}